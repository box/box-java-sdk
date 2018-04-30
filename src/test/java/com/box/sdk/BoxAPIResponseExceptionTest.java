package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.eclipsesource.json.JsonObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

/**
 *
 */
public class BoxAPIResponseExceptionTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(53620);

    @Test
    @Category(UnitTest.class)
    public void testAPIResponseExceptionReturnsCorrectErrorMessage() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection("");

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
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
                + "         }");

        stubFor(post(urlEqualTo("/folders"))
                .willReturn(aResponse()
                        .withStatus(409)
                        .withHeader("Content-Type", "application/json")
                        .withBody(fakeJSONResponse.toString())));

        URL url = new URL("http://localhost:53620/folders");
        BoxAPIRequest request = new BoxAPIRequest(api, url, "POST");

        try {
            BoxJSONResponse response = (BoxJSONResponse) request.send();
        } catch (BoxAPIResponseException e) {
            Assert.assertEquals(409, e.getResponseCode());
            Assert.assertEquals("The API returned an error code [409 | 5678] item_name_in_use - "
                    + "Item with the same name already exists", e.getMessage());
            return;
        }

        Assert.fail("Never threw a BoxAPIResponseException");
    }

    @Test
    @Category(UnitTest.class)
    public void testAPIResponseExceptionMissingFieldsReturnsCorrectErrorMessage() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection("");
        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
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
                + "        }");

        stubFor(post(urlEqualTo("/folders"))
                .willReturn(aResponse()
                        .withStatus(409)
                        .withHeader("Content-Type", "application/json")
                        .withBody(fakeJSONResponse.toString())));

        URL url = new URL("http://localhost:53620/folders");
        BoxAPIRequest request = new BoxAPIRequest(api, url, "POST");

        try {
            BoxJSONResponse response = (BoxJSONResponse) request.send();
        } catch (BoxAPIResponseException e) {
            Assert.assertEquals(409, e.getResponseCode());
            Assert.assertEquals("The API returned an error code [409]", e.getMessage());
            return;
        }

        Assert.fail("Never threw a BoxAPIResponseException");
    }

    @Test
    @Category(UnitTest.class)
    public void testAPIResponseExceptionMissingBodyReturnsCorrectErrorMessage() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection("");

        stubFor(post(urlEqualTo("/folders"))
                .willReturn(aResponse()
                        .withStatus(403)));

        URL url = new URL("http://localhost:53620/folders");
        BoxAPIRequest request = new BoxAPIRequest(api, url, "POST");

        try {
            BoxJSONResponse response = (BoxJSONResponse) request.send();
        } catch (BoxAPIResponseException e) {
            Assert.assertEquals(403, e.getResponseCode());
            Assert.assertEquals("", e.getResponse());
            Assert.assertEquals("The API returned an error code [403]", e.getMessage());
            return;
        }

        Assert.fail("Never threw a BoxAPIResponseException");
    }
}
