package com.box.sdk;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.box.sdk.http.HttpMethod;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 */
public class BatchAPIRequestTest {
    @Test
    @Category(UnitTest.class)
    public void testThatBodyHasAllRequiredFieldsInEachResponse() {
        try {
            final String anyClientID = "";
            final String anyClientSecret = "";
            final String anyAccessToken = "";
            final String anyRefreshToken = "";

            BoxAPIConnection api = new BoxAPIConnection(anyClientID, anyClientSecret, anyAccessToken, anyRefreshToken);

            List<BoxAPIRequest> requests = new ArrayList<BoxAPIRequest>();

            //Get current user request
            URL getMeURL = BoxUser.GET_ME_URL.build(api.getBaseURL());
            BoxAPIRequest getMeRequest = new BoxAPIRequest(getMeURL, HttpMethod.GET);
            requests.add(getMeRequest);

            //Create App User Request
            URL createUserURL = BoxUser.USERS_URL_TEMPLATE.build(api.getBaseURL());
            BoxJSONRequest createAppUserRequest = new BoxJSONRequest(createUserURL, HttpMethod.POST);
            createAppUserRequest.setBody("{\"name\":\"some-name\","
                + "\"is_platform_access_only\":true}");
            requests.add(createAppUserRequest);

            //Get Root Folder Request
            URL getRootFolderURL = BoxFolder.FOLDER_INFO_URL_TEMPLATE.build(api.getBaseURL(), 0);
            BoxAPIRequest getRootFolderRequest = new BoxAPIRequest(getRootFolderURL, HttpMethod.GET);
            requests.add(getRootFolderRequest);

            BatchAPIRequest batchRequest = new BatchAPIRequest(api);
            batchRequest.prepareRequest(requests);

            assertTrue("requests field should be an array",
                (batchRequest.getBodyAsJsonObject().get("requests").isArray()));

            JsonArray requestsArray = batchRequest.getBodyAsJsonObject().get("requests").asArray();
            for (JsonValue request : requestsArray) {
                assertTrue("each request should be an object",
                    (request.isObject()));
                assertTrue("each request should have a relative_url that is string",
                    (((JsonObject) request).get("relative_url").isString()));
                assertTrue("each request should have a method that is string",
                    (((JsonObject) request).get("method").isString()));
                assertTrue("each request should have headers as object",
                    (((JsonObject) request).get("headers").isObject()));
                if (((JsonObject) request).get("method").asString().equals(HttpMethod.POST)) {
                    assertTrue("request should have body when method is POST",
                        (((JsonObject) request).get("body").isObject()));
                }
            }

        } catch (Exception e) {
            fail("Exception during test execution " + e);
        }
    }

    @Test
    @Category(UnitTest.class)
    public void testTheParsingResponseWorksAsIntended() {
        try {
            final String anyClientID = "";
            final String anyClientSecret = "";
            final String anyAccessToken = "";
            final String anyRefreshToken = "";

            BoxAPIConnection api = new BoxAPIConnection(anyClientID, anyClientSecret, anyAccessToken, anyRefreshToken);
            BatchAPIRequest batchRequest = new BatchAPIRequest(api);
            String stringResponse = "{\n"
                + "\t\"responses\": [{\n"
                + "\t\t\t\"status\": 201,\n"
                + "\t\t\t\"response\": {\n"
                + "\t\t\t\t\"type\": \"collaboration\",\n"
                + "\t\t\t\t\"id\": \"123\"\n"
                + "\t\t\t}\n"
                + "\t\t},\n"
                + "\t\t{\n"
                + "\t\t\t\"status\": 404,\n"
                + "\t\t\t\"response\": {\n"
                + "\t\t\t\t\"type\": \"error\",\n"
                + "\t\t\t\t\"status\": 404,\n"
                + "\t\t\t\t\"code\": \"not_found\",\n"
                + "\t\t\t\t\"message\": \"Not Found\",\n"
                + "\t\t\t\t\"request_id\": \"139334760758014ea2935ab\"\n"
                + "\t\t\t}\n"
                + "\t\t},\n"
                + "\t\t{\n"
                + "\t\t\t\"status\": 200,\n"
                + "\t\t\t\"response\": {\n"
                + "\t\t\t\t\"type\": \"collaboration\",\n"
                + "\t\t\t\t\"id\": \"456\"\n"
                + "\t\t\t}\n"
                + "\t\t}\n"
                + "\t]\n"
                + "}";
            JsonObject responseJson = JsonObject.readFrom(stringResponse);
            BoxJSONResponse batchResponse = new BoxJSONResponse(200, null, responseJson);
            List<BoxAPIResponse> responses = batchRequest.parseResponse(batchResponse);

            assertTrue("There should be three responses", responses.size() == 3);
            for (BoxAPIResponse response: responses) {
                assertTrue("Always has response body", ((BoxJSONResponse) response).getJsonObject() != null);
            }

        } catch (Exception e) {
            fail("Exception during test execution " + e);
        }
    }

}
