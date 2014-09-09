package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class BoxAPIRequestTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    @Test(expected = BoxAPIException.class)
    @Category(UnitTest.class)
    public void requestRetriesTheDefaultNumberOfTimesWhenServerReturns500() throws MalformedURLException {
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(500)));

        URL url = new URL("http://localhost:8080/");
        BoxAPIRequest request = new BoxAPIRequest(url, "GET");
        request.send();

        verify(BoxAPIConnection.DEFAULT_MAX_ATTEMPTS, getRequestedFor(urlEqualTo("/")));
    }
}
