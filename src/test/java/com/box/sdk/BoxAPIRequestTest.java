package com.box.sdk;

import static com.box.sdk.TestUtils.createConnectionWith;
import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

import com.eclipsesource.json.ParseException;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPOutputStream;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

public class BoxAPIRequestTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));

    @Test
    public void requestRetriesTheDefaultNumberOfTimesWhenServerReturns500() {
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(500).withBody("{}")));
        Time mockTime = mock(Time.class);
        BackoffCounter backoffCounter = new BackoffCounter(mockTime);

        BoxAPIConnection api = createConnectionWith(boxMockUrl().toString());
        BoxAPIRequest request = new BoxAPIRequest(api, boxMockUrl(), "GET");
        request.setBackoffCounter(backoffCounter);

        try {
            request.send();
        } catch (BoxAPIException e) {
            verify(BoxAPIConnection.DEFAULT_MAX_RETRIES + 1, getRequestedFor(urlEqualTo("/")));
        }
    }

    @Test
    public void requestRetriesTheDefaultNumberOfTimesWhenServerReturns429() {
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(429).withBody("{}")));
        Time mockTime = mock(Time.class);
        BackoffCounter backoffCounter = new BackoffCounter(mockTime);

        BoxAPIConnection api = createConnectionWith(boxMockUrl().toString());
        BoxAPIRequest request = new BoxAPIRequest(api, boxMockUrl(), "GET");
        request.setBackoffCounter(backoffCounter);

        try {
            request.send();
        } catch (BoxAPIException e) {
            verify(BoxAPIConnection.DEFAULT_MAX_RETRIES + 1, getRequestedFor(urlEqualTo("/")));
        }
    }

    @Test
    public void requestRetriesTheDefaultNumberOfTimesWhenServerReturnsInvalidGrantInErrorField() {
        String body = "{\"error\":\"invalid_grant\",\"error_description\":\"Current date"
            + "\\/time MUST be before the expiration date\\/time listed in the 'exp' claim\"}";
        stubFor(get(urlEqualTo("/")).willReturn(aResponse()
            .withStatus(400)
            .withBody(body)));
        Time mockTime = mock(Time.class);
        BackoffCounter backoffCounter = new BackoffCounter(mockTime);

        BoxAPIConnection api = createConnectionWith(boxMockUrl().toString());
        BoxAPIRequest request = new BoxAPIRequest(api, boxMockUrl(), "GET");
        request.setBackoffCounter(backoffCounter);

        try {
            request.send();
        } catch (BoxAPIException e) {
            verify(BoxAPIConnection.DEFAULT_MAX_RETRIES + 1, getRequestedFor(urlEqualTo("/")));
        }
    }

    @Test
    public void requestRetriesTheNumberOfTimesConfiguredInTheAPIConnection() {
        final int expectedNumRetryAttempts = 1;
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(500).withBody("{}")));
        Time mockTime = mock(Time.class);
        BackoffCounter backoffCounter = new BackoffCounter(mockTime);

        BoxAPIConnection api = createConnectionWith(boxMockUrl().toString());
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
    public void requestRetriesUsingSecondsProvidedInRetryHeader() throws InterruptedException {
        final int expectedNumRetryAttempts = 1;
        stubFor(get(urlEqualTo("/")).willReturn(aResponse()
            .withStatus(429)
            .withBody("{}")
            .withHeader("Retry-After", "20")
        ));
        Time mockTime = mock(Time.class);
        BackoffCounter backoffCounter = new BackoffCounter(mockTime);

        BoxAPIConnection api = createConnectionWith(boxMockUrl().toString());
        api.setMaxRetryAttempts(expectedNumRetryAttempts);

        BoxAPIRequest request = new BoxAPIRequest(api, boxMockUrl(), "GET");
        request.setBackoffCounter(backoffCounter);

        try {
            request.send();
        } catch (BoxAPIException e) {
            Mockito.verify(mockTime).waitDuration(eq(20000));
        }
    }

    @Test
    public void requestSendsXBoxUAHeader() {

        stubFor(get(urlEqualTo("/")).willReturn(aResponse()
            .withStatus(202)
            .withHeader("Content-Type", APPLICATION_JSON)
            .withBody("{}")));

        BoxAPIConnection api = createConnectionWith(boxMockUrl().toString());
        BoxAPIRequest request = new BoxAPIRequest(api, boxMockUrl(), "GET");

        try (BoxAPIResponse response = request.send()) {
            assertNotNull(response);
            String headerRegex = "agent=box-java-sdk/\\d\\.\\d+\\.\\d+(-[a-zA-Z]+)?; env=Java/\\d+\\.\\d+\\.\\d+.*";
            RequestPatternBuilder requestPatternBuilder = RequestPatternBuilder.newRequestPattern()
                .withHeader("X-Box-UA", matching(headerRegex));
            verify(requestPatternBuilder);
        }
    }

    @Test
    public void requestDoesNotAllowModifyingBoxUAHeader() {

        BoxAPIConnection api = new BoxAPIConnectionForTests("");

        BoxAPIRequest request = new BoxAPIRequest(api, boxMockUrl(), "GET");

        try {
            request.addHeader("X-Box-UA", "foo");
            fail("Exception should have been thrown");
        } catch (IllegalArgumentException ex) {
            // Don't need to do anything
        }
    }

    @Test
    public void requestDoesNotAllowDuplicateAsUserHeader() {

        BoxAPIRequest request = new BoxAPIRequest(new BoxAPIConnectionForTests(""), boxMockUrl(), "GET");

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
    public void whenJsonCannotBeParseExceptionIsThrown() {
        BoxAPIConnection api = createConnectionWith(boxMockUrl().toString());
        BoxAPIRequest request = new BoxAPIRequest(api, boxMockUrl(), "GET");
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

    @Test
    public void handlesGZIPResponse() {
        BoxAPIConnection api = createConnectionWith(boxMockUrl().toString());
        BoxAPIRequest request = new BoxAPIRequest(api, boxMockUrl(), "GET");
        String jsonString = "{\"foo\":\"bar\"}";
        stubFor(get(urlEqualTo("/")).willReturn(
            aResponse()
                .withStatus(200)
                .withHeader("content-type", APPLICATION_JSON)
                .withHeader("content-encoding", "GZIP")
                .withBody(gzipped(jsonString))
        ));

        try (BoxAPIResponse response = request.send()) {
            assertThat(response.bodyToString(), is(jsonString));
        }
    }

    @Test
    public void willNotRetry400ErrorWithPlainTextResponse() {
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(400).withBody("Not a JSON")));

        BoxAPIConnection api = createConnectionWith(boxMockUrl().toString());

        BoxAPIRequest request = new BoxAPIRequest(api, boxMockUrl(), "GET");

        try {
            request.send();
        } catch (BoxAPIException e) {
            verify(1, getRequestedFor(urlEqualTo("/")));
            assertThat(e.getMessage(), is("API returned an error\nNot a JSON"));
            assertThat(e.getResponse(), is("Not a JSON"));
            assertThat(e.getResponseCode(), is(400));
        }
    }

    @Test
    public void willNotRetry400ErrorWithJsonResponse() {
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(400).withBody("{\"foo\":\"bar\"}")));

        BoxAPIConnection api = createConnectionWith(boxMockUrl().toString());

        BoxAPIRequest request = new BoxAPIRequest(api, boxMockUrl(), "GET");

        try {
            request.send();
        } catch (BoxAPIException e) {
            verify(1, getRequestedFor(urlEqualTo("/")));
            assertThat(e.getMessage(), is("The API returned an error code [400]"));
            assertThat(e.getResponse(), is("{\"foo\":\"bar\"}"));
            assertThat(e.getResponseCode(), is(400));
        }
    }

    @Test
    public void interceptorCanModifyRequest() {
        String headerName = "XX-My-Custom-Header";
        String headerValue = "some value";
        stubFor(get(urlEqualTo("/")).withHeader(headerName, equalTo(headerValue))
            .willReturn(aResponse().withStatus(200).withBody("{\"foo\":\"bar\"}")));

        BoxAPIConnection api = createConnectionWith(boxMockUrl().toString());
        api.setRequestInterceptor(request -> {
            request.addHeader(headerName, headerValue);
            return null;
        });

        new BoxAPIRequest(api, boxMockUrl(), "GET").send();
        verify(1, getRequestedFor(urlEqualTo("/")));
    }

    private byte[] gzipped(String str) {
        try {
            ByteArrayOutputStream obj = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(obj);
            gzip.write(str.getBytes(UTF_8));
            gzip.close();
            return obj.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private URL boxMockUrl() {
        try {
            return new URL(format("https://localhost:%d/", wireMockRule.httpsPort()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
