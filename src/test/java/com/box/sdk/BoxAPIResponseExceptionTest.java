package com.box.sdk;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 */
public class BoxAPIResponseExceptionTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(53620);

    private final BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    public void testAPIResponseExceptionReturnsCorrectErrorMessage() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection("");

        final JsonObject fakeJSONResponse = Json.parse("{\n"
            + "            \"type\": \"error\",\n"
            + "            \"status\": \"409\",\n"
            + "            \"code\": \"item_name_in_use\",\n"
            + "            \"context_info\": {\n"
            + "            \"conflicts\": [\n"
            + "                 {\n"
            + "                     \"type\": \"folder\",\n"
            + "                     \"id\": \"12345\",\n"
            + "                     \"sequence_id\": \"1\",\n"
            + "                     \"etag\": \"1\",\n"
            + "                     \"name\": \"Helpful things\"\n"
            + "                 }\n"
            + "              ]\n"
            + "            },\n"
            + "            \"help_url\": \"http://developers.box.com/docs/#errors\",\n"
            + "            \"message\": \"Item with the same name already exists\",\n"
            + "            \"request_id\": \"5678\"\n"
            + "         }").asObject();

        stubFor(post(urlEqualTo("/folders"))
            .willReturn(aResponse()
                .withStatus(409)
                .withHeader("Content-Type", "application/json")
                .withBody(fakeJSONResponse.toString())));

        URL url = new URL("http://localhost:53620/folders");
        BoxAPIRequest request = new BoxAPIRequest(api, url, "POST");

        try {
            request.send();
        } catch (BoxAPIResponseException e) {
            assertEquals(409, e.getResponseCode());
            assertEquals("The API returned an error code [409 | 5678] item_name_in_use - "
                + "Item with the same name already exists", e.getMessage());
            return;
        }

        Assert.fail("Never threw a BoxAPIResponseException");
    }

    @Test
    public void testAPIResponseExceptionMissingFieldsReturnsCorrectErrorMessage() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection("");
        final JsonObject fakeJSONResponse = Json.parse("{\n"
            + "            \"type\": \"error\",\n"
            + "            \"status\": \"409\",\n"
            + "            \"context_info\": {\n"
            + "            \"conflicts\": [\n"
            + "                 {\n"
            + "                     \"type\": \"folder\",\n"
            + "                     \"id\": \"12345\",\n"
            + "                     \"sequence_id\": \"1\",\n"
            + "                     \"etag\": \"1\",\n"
            + "                     \"name\": \"Helpful things\"\n"
            + "                 }\n"
            + "              ]\n"
            + "            },\n"
            + "            \"help_url\": \"http://developers.box.com/docs/#errors\"\n"
            + "        }").asObject();

        stubFor(post(urlEqualTo("/folders"))
            .willReturn(aResponse()
                .withStatus(409)
                .withHeader("Content-Type", "application/json")
                .withBody(fakeJSONResponse.toString())));

        URL url = new URL("http://localhost:53620/folders");
        BoxAPIRequest request = new BoxAPIRequest(api, url, "POST");

        try {
            request.send();
        } catch (BoxAPIResponseException e) {
            assertEquals(409, e.getResponseCode());
            assertEquals("The API returned an error code [409]", e.getMessage());
            return;
        }

        Assert.fail("Never threw a BoxAPIResponseException");
    }

    @Test
    public void testAPIResponseExceptionMissingBodyReturnsCorrectErrorMessage() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection("");

        stubFor(post(urlEqualTo("/folders"))
            .willReturn(aResponse()
                .withStatus(403)));

        URL url = new URL("http://localhost:53620/folders");
        BoxAPIRequest request = new BoxAPIRequest(api, url, "POST");

        try {
            request.send();
        } catch (BoxAPIResponseException e) {
            assertEquals(403, e.getResponseCode());
            assertEquals("", e.getResponse());
            assertEquals("The API returned an error code [403]", e.getMessage());
            return;
        }

        Assert.fail("Never threw a BoxAPIResponseException");
    }

    @Test
    public void testAPIResponseExceptionWithHTMLBodyReturnsCorrectErrorMessage() throws MalformedURLException {

        String body = "<html><body><h1>500 Server Error</h1></body></html>";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setMaxRetryAttempts(1);

        stubFor(post(urlEqualTo("/folders"))
            .willReturn(aResponse()
                .withBody(body)
                .withStatus(500)));

        URL url = new URL("http://localhost:53620/folders");
        BoxAPIRequest request = new BoxAPIRequest(api, url, "POST");

        try {
            request.send();
        } catch (BoxAPIResponseException e) {
            assertEquals(500, e.getResponseCode());
            assertEquals(body, e.getResponse());
            assertEquals("The API returned an error code [500]", e.getMessage());
            return;
        }

        Assert.fail("Never threw a BoxAPIResponseException");
    }

    @Test
    public void testResponseExceptionHeadersIsCaseInsensitive() {
        Map<String, String> headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        headers.put("FOO", "bAr");
        BoxAPIResponse responseObject = new BoxAPIResponse(202, headers);
        BoxAPIResponseException responseException = new BoxAPIResponseException("Test Message", responseObject);

        Assert.assertTrue(responseException.getHeaders().containsKey("foo"));
        Assert.assertTrue(responseException.getHeaders().containsKey("fOo"));
        Assert.assertTrue(responseException.getHeaders().containsKey("FOO"));
        assertEquals("bAr", responseException.getHeaders().get("foo").get(0));
    }

    @Test
    public void testGetResponseHeadersWithNoRequestID() throws IOException {
        final String userURL = "/users/12345";

        String result = TestConfig.getFixture("BoxException/BoxResponseException403");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(userURL))
            .willReturn(WireMock.aResponse()
                .withHeader("BOX-REQUEST-ID", "11111")
                .withStatus(403)
                .withBody(result)));

        try {
            BoxUser user = new BoxUser(this.api, "12345");
            assertNotNull(user.getInfo());
        } catch (Exception e) {
            assertEquals("The API returned an error code [403 | .11111] Forbidden", e.getMessage());
        }
    }

    @Test
    public void testGetResponseExceptionCorrectlyWithAllID() throws IOException {
        final String userURL = "/users/12345";

        String result = TestConfig.getFixture("BoxException/BoxResponseException403WithRequestID");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(userURL))
            .willReturn(WireMock.aResponse()
                .withHeader("BOX-REQUEST-ID", "11111")
                .withStatus(403)
                .withBody(result)));

        try {
            BoxUser user = new BoxUser(this.api, "12345");
            assertNotNull(user.getInfo());
        } catch (Exception e) {
            assertEquals("The API returned an error code [403 | 22222.11111] Forbidden", e.getMessage());
        }
    }

    @Test
    public void testGetResponseExceptionErrorAndErrorDescription() throws IOException {
        final String userURL = "/users/12345";

        String result = TestConfig.getFixture("BoxException/BoxResponseException400WithErrorAndErrorDescription");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(userURL))
            .willReturn(WireMock.aResponse()
                .withHeader("BOX-REQUEST-ID", "11111")
                .withStatus(403)
                .withBody(result)));


        try {
            new BoxUser(this.api, "12345");
        } catch (Exception e) {
            assertEquals(
                "The API returned an error code [403 | 22222.11111] Forbidden - Unauthorized Access",
                e.getMessage()
            );
        }

    }

    @Test
    public void testGetResponseHeadersCorrectlyWithEmptyBody() {
        final String userURL = "/users/12345";

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(userURL))
            .willReturn(WireMock.aResponse()
                .withHeader("BOX-REQUEST-ID", "11111")
                .withStatus(403)));

        try {
            BoxUser user = new BoxUser(this.api, "12345");
            assertNotNull(user.getInfo());
        } catch (Exception e) {
            assertEquals("The API returned an error code [403 | .11111]", e.getMessage());
        }
    }

    @Test
    public void testGeneratingWhenContextInfoPresentWithOneError() {
        // given
        String errorJsonString = "{\n"
            + "  \"type\": \"error\",\n"
            + "  \"status\": 400,\n"
            + "  \"code\": \"bad_request\",\n"
            + "  \"context_info\": {\n"
            + "    \"errors\": [\n"
            + "      {\n"
            + "        \"reason\": \"invalid_parameter\",\n"
            + "        \"name\": \"stream_type\",\n"
            + "        \"message\": \"Invalid value 'admin_logs_streaming'.\"\n"
            + "      }\n"
            + "    ]\n"
            + "  },\n"
            + "  \"help_url\": \"http:\\/\\/developers.box.com\\/docs\\/#errors\",\n"
            + "  \"message\": \"Bad Request\",\n"
            + "  \"request_id\": \"feo3k0gwg4ji04zl\"\n"
            + "}";
        BoxAPIResponse response = mock(BoxAPIResponse.class);
        when(response.bodyToString()).thenReturn(errorJsonString);
        when(response.getResponseCode()).thenReturn(400);

        //when
        BoxAPIResponseException exception = new BoxAPIResponseException("Bad Request", response);

        assertThat(exception.getMessage(), is("Bad Request [400 | feo3k0gwg4ji04zl] bad_request - Bad Request"));
        assertThat(exception.getResponseCode(), is(400));
        assertThat(exception.getResponse(), is(errorJsonString)
        );
    }
}
