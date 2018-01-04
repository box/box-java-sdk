package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class BoxAPIRequestTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(53620);

    @Test
    @Category(UnitTest.class)
    public void requestRetriesTheDefaultNumberOfTimesWhenServerReturns500() throws MalformedURLException {
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(500)));
        Time mockTime = mock(Time.class);
        BackoffCounter backoffCounter = new BackoffCounter(mockTime);

        URL url = new URL("http://localhost:53620/");
        BoxAPIRequest request = new BoxAPIRequest(url, "GET");
        request.setBackoffCounter(backoffCounter);

        try {
            request.send();
        } catch (BoxAPIException e) {
            verify(BoxAPIConnection.DEFAULT_MAX_ATTEMPTS, getRequestedFor(urlEqualTo("/")));
        }
    }

    @Test
    @Category(UnitTest.class)
    public void requestRetriesTheDefaultNumberOfTimesWhenServerReturns429() throws MalformedURLException {
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(429)));
        Time mockTime = mock(Time.class);
        BackoffCounter backoffCounter = new BackoffCounter(mockTime);

        URL url = new URL("http://localhost:53620/");
        BoxAPIRequest request = new BoxAPIRequest(url, "GET");
        request.setBackoffCounter(backoffCounter);

        try {
            request.send();
        } catch (BoxAPIException e) {
            verify(BoxAPIConnection.DEFAULT_MAX_ATTEMPTS, getRequestedFor(urlEqualTo("/")));
        }
    }

    @Test
    @Category(UnitTest.class)
    public void requestRetriesTheNumberOfTimesConfiguredInTheAPIConnection() throws MalformedURLException {
        final int expectedNumAttempts = 1;
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(500)));
        Time mockTime = mock(Time.class);
        BackoffCounter backoffCounter = new BackoffCounter(mockTime);

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setMaxRequestAttempts(expectedNumAttempts);

        URL url = new URL("http://localhost:53620/");
        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        request.setBackoffCounter(backoffCounter);

        try {
            request.send();
        } catch (BoxAPIException e) {
            verify(expectedNumAttempts, getRequestedFor(urlEqualTo("/")));
        }
    }
}
