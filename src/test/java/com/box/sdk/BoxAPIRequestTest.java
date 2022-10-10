package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import com.eclipsesource.json.ParseException;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Rule;
import org.junit.Test;

public class BoxAPIRequestTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

    @Test
    public void requestRetriesTheDefaultNumberOfTimesWhenServerReturns500() throws MalformedURLException {
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(500)));
        Time mockTime = mock(Time.class);
        BackoffCounter backoffCounter = new BackoffCounter(mockTime);

        BoxAPIRequest request = new BoxAPIRequest(new BoxAPIConnection(""), boxMockUrl(), "GET");
        request.setBackoffCounter(backoffCounter);

        try {
            request.send();
        } catch (BoxAPIException e) {
            verify(BoxAPIConnection.DEFAULT_MAX_RETRIES + 1, getRequestedFor(urlEqualTo("/")));
        }
    }

    @Test
    public void requestRetriesTheDefaultNumberOfTimesWhenServerReturns429() throws MalformedURLException {
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(429)));
        Time mockTime = mock(Time.class);
        BackoffCounter backoffCounter = new BackoffCounter(mockTime);

        BoxAPIRequest request = new BoxAPIRequest(new BoxAPIConnection(""), boxMockUrl(), "GET");
        request.setBackoffCounter(backoffCounter);

        try {
            request.send();
        } catch (BoxAPIException e) {
            verify(BoxAPIConnection.DEFAULT_MAX_RETRIES + 1, getRequestedFor(urlEqualTo("/")));
        }
    }

    @Test
    public void requestRetriesTheDefaultNumberOfTimesWhenServerReturnsInvalidGrantInErrorField()
        throws MalformedURLException {
        String body = "{\"error\":\"invalid_grant\",\"error_description\":\"Current date"
            + "\\/time MUST be before the expiration date\\/time listed in the 'exp' claim\"}";
        stubFor(get(urlEqualTo("/")).willReturn(aResponse()
            .withStatus(400)
            .withBody(body)));
        Time mockTime = mock(Time.class);
        BackoffCounter backoffCounter = new BackoffCounter(mockTime);

        BoxAPIRequest request = new BoxAPIRequest(new BoxAPIConnection(""), boxMockUrl(), "GET");
        request.setBackoffCounter(backoffCounter);

        try {
            request.send();
        } catch (BoxAPIException e) {
            verify(BoxAPIConnection.DEFAULT_MAX_RETRIES + 1, getRequestedFor(urlEqualTo("/")));
        }
    }

    @Test
    public void requestRetriesTheNumberOfTimesConfiguredInTheAPIConnection() throws MalformedURLException {
        final int expectedNumRetryAttempts = 1;
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(500)));
        Time mockTime = mock(Time.class);
        BackoffCounter backoffCounter = new BackoffCounter(mockTime);

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setMaxRetryAttempts(expectedNumRetryAttempts);

        BoxAPIRequest request = new BoxAPIRequest(api, boxMockUrl(), "GET");
        request.setBackoffCounter(backoffCounter);

        try {
            request.send();
        } catch (BoxAPIException e) {
            verify(expectedNumRetryAttempts + 1, getRequestedFor(urlEqualTo("/")));
        }
    }

    @Test
    public void requestSendsXBoxUAHeader() throws MalformedURLException {

        stubFor(get(urlEqualTo("/")).willReturn(aResponse()
            .withStatus(202)
            .withHeader("Content-Type", APPLICATION_JSON)
            .withBody("{}")));

        BoxAPIRequest request = new BoxAPIRequest(new BoxAPIConnection(""), boxMockUrl(), "GET");

        request.send();

        String headerRegex = "agent=box-java-sdk/\\d\\.\\d+\\.\\d+(-[a-zA-Z]+)?; env=Java/\\d+\\.\\d+\\.\\d+.*";
        RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern()
            .withHeader("X-Box-UA", matching(headerRegex));
        verify(requestPatternBuilder);
    }

    @Test
    public void requestDoesNotAllowModifyingBoxUAHeader() throws MalformedURLException {

        BoxAPIConnection api = new BoxAPIConnection("");

        BoxAPIRequest request = new BoxAPIRequest(api, boxMockUrl(), "GET");

        try {
            request.addHeader("X-Box-UA", "foo");
            fail("Exception should have been thrown");
        } catch (IllegalArgumentException ex) {
            // Don't need to do anything
        }
    }

    @Test
    public void requestDoesNotAllowDuplicateAsUserHeader() throws MalformedURLException {

        BoxAPIRequest request = new BoxAPIRequest(new BoxAPIConnection(""), boxMockUrl(), "GET");

        request.addHeader("As-User", "12345");
        request.addHeader("As-User", "67890");

        boolean headerFound = false;
        String headerValue = null;
        for (BoxAPIRequest.RequestHeader header : request.getHeaders()) {
            if (header.getKey().equals("As-User")) {
                if (headerFound) {
                    fail("Duplicate As-User header found!");
                    return;
                }

                headerFound = true;
                headerValue = header.getValue();
            }
        }

        assertEquals("67890", headerValue);
    }

    @Test
    public void whenJsonCannotBeParseExceptionIsThrown() throws IOException {
        BoxAPIRequest request = new BoxAPIRequest(new BoxAPIConnection(""), boxMockUrl(), "GET");
        stubFor(get(urlEqualTo("/")).willReturn(
            aResponse()
                .withStatus(200)
                .withHeader("content-type", APPLICATION_JSON)
                .withBody("Not a Json".getBytes(UTF_8))
        ));
        try {
            request.send();
        } catch (BoxAPIException e) {
            assertThat(e.getMessage(), is("Error parsing JSON:\nNot a Json"));
            assertThat(e.getCause().getClass(), is(ParseException.class));
        }
    }

    private URL boxMockUrl() throws MalformedURLException {
        return new URL(format("http://localhost:%d/", wireMockRule.port()));
    }
}
