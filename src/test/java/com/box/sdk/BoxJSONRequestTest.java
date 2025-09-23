package com.box.sdk;

import static com.box.sdk.TestUtils.createConnectionWith;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Rule;
import org.junit.Test;

public class BoxJSONRequestTest {
  @Rule
  public WireMockRule wireMockRule =
      new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));

  @Test
  public void shouldHandleApiResponse() {
    stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(202)));
    Time mockTime = mock(Time.class);
    BackoffCounter backoffCounter = new BackoffCounter(mockTime);

    BoxAPIConnection api = createConnectionWith(boxMockUrl().toString());
    BoxJSONRequest request = new BoxJSONRequest(api, boxMockUrl(), "GET");
    request.setBackoffCounter(backoffCounter);

    BoxJSONResponse response = request.send();
    assertThat(response.getResponseCode(), is(202));
  }

  private URL boxMockUrl() {
    try {
      return new URL(format("https://localhost:%d/", wireMockRule.httpsPort()));
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}
