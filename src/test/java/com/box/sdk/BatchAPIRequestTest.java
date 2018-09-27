package com.box.sdk;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.box.sdk.http.HttpMethod;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

/**
 *
 */
public class BatchAPIRequestTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();
    @Test
    @Category(UnitTest.class)
    public void testThatBodyHasAllRequiredFieldsInEachResponse() {
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
                + "\t\t},\n"
                + "        {\n"
                + "        \t\"status\": 200,\n"
                + "        \t\"headers\": {},\n"
                + "        \t\"response\": {\n"
                + "        \t\t\"chunk_size\": 1,\n"
                + "        \t\t\"next_stream_position\": \"1152922980411393698\",\n"
                + "        \t\t\"entries\": [{\n"
                + "        \t\t\t\"source\": {\n"
                + "        \t\t\t\t\"type\": \"user\",\n"
                + "        \t\t\t\t\"id\": \"235699372\",\n"
                + "        \t\t\t\t\"name\": \"Cary Cheng\",\n"
                + "        \t\t\t\t\"login\": \"ccheng+demo@box.com\"\n"
                + "        \t\t\t},\n"
                + "        \t\t\t\"created_by\": {\n"
                + "        \t\t\t\t\"type\": \"user\",\n"
                + "        \t\t\t\t\"id\": \"235699372\",\n"
                + "        \t\t\t\t\"name\": \"Cary Cheng\",\n"
                + "        \t\t\t\t\"login\": \"ccheng+demo@box.com\"\n"
                + "        \t\t\t},\n"
                + "        \t\t\t\"created_at\": \"2016-10-06T18:42:26-07:00\",\n"
                + "        \t\t\t\"event_id\": \"f7369670-cc87-495f-af43-85287fd4d288\",\n"
                + "        \t\t\t\"event_type\": \"ADD_LOGIN_ACTIVITY_DEVICE\",\n"
                + "        \t\t\t\"ip_address\": \"24.130.143.167\",\n"
                + "        \t\t\t\"type\": \"event\",\n"
                + "        \t\t\t\"session_id\": null,\n"
                + "        \t\t\t\"additional_details\": null\n"
                + "        \t\t}]\n"
                + "        \t}\n"
                + "        },"
                + "        {\n"
                + "            \"status\": 404,\n"
                + "            \"headers\": {},\n"
                + "            \"response\": null\n"
                + "        }\n"
                + "\t]\n"
                + "}";
            JsonObject responseJson = JsonObject.readFrom(stringResponse);
            BoxJSONResponse batchResponse = new BoxJSONResponse(200, null, responseJson);
            List<BoxAPIResponse> responses = batchRequest.parseResponse(batchResponse);

            assertTrue("There should be three responses", responses.size() == 5);
            for (BoxAPIResponse response: responses) {
                if (response.getResponseCode() == 404) {
                    assertTrue("Always has response body", response != null);
                } else {
                    assertTrue("Always has JSON response body", ((BoxJSONResponse) response).getJsonObject() != null);
                }
            }

        } catch (Exception e) {
            fail("Exception during test execution " + e);
        }
    }

    @Test
    @Category(UnitTest.class)
    public void testMixedBodyTypesWorks() {

        BoxAPIConnection api = new BoxAPIConnection("");

        List<BoxAPIRequest> requests = new ArrayList<BoxAPIRequest>();


        //Create App User Request - uses JSON object as body
        URL createUserURL = BoxUser.USERS_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest createAppUserRequest = new BoxJSONRequest(createUserURL, HttpMethod.POST);
        createAppUserRequest.setBody("{\"name\":\"some-name\","
                + "\"is_platform_access_only\":true}");
        requests.add(createAppUserRequest);

        //Update Metadata Template request - uses JSON array as body
        URL updateMetadataTemplateURL = MetadataTemplate.METADATA_TEMPLATE_URL_TEMPLATE.build(api.getBaseURL(),
                "global", "properties");
        BoxJSONRequest updateMetadataTemplateRequest = new BoxJSONRequest(updateMetadataTemplateURL, HttpMethod.PUT);
        updateMetadataTemplateRequest.setBody("[{\"op\":\"removeField\",\"fieldKey\":\"foo\"}]");
        requests.add(updateMetadataTemplateRequest);

        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                assertEquals(
                        "https://api.box.com/2.0/batch",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {

                        JsonObject res = new JsonObject();
                        return "{\n"
                                + "\"responses\": [\n"
                                + "    {\n"
                                + "        \"status\": 201,\n"
                                + "        \"response\": {}\n"
                                + "    },\n"
                                + "    {\n"
                                + "        \"status\": 200,\n"
                                + "        \"response\": {}\n"
                                + "    }\n"
                                + "]\n"
                                + "}";
                    }
                };
            }
        });

        BatchAPIRequest batchRequest = new BatchAPIRequest(api);
        List<BoxAPIResponse> responses = batchRequest.execute(requests);

        BoxAPIResponse createUserResponse = responses.get(0);

        assertEquals(201, createUserResponse.getResponseCode());
        JsonObject userResponseJSON = JsonObject.readFrom(createUserResponse.bodyToString());
        assertEquals(0, userResponseJSON.size());

        BoxAPIResponse updateMetadataTemplateResponse = responses.get(1);
        assertEquals(200, updateMetadataTemplateResponse.getResponseCode());
        JsonObject metadataResponseJSON = JsonObject.readFrom(updateMetadataTemplateResponse.bodyToString());
        assertEquals(0, metadataResponseJSON.size());
    }

    @Test
    @Category(UnitTest.class)
    public void testBatchRequestEndToEnd() throws IOException {
        List<BoxAPIRequest> requests = new ArrayList<BoxAPIRequest>();
        String request = "";
        String response = "";
        final String fileID = "12345";
        final String scope = "global";
        final String template = "properties";
        final String batchURL = "/batch";
        JsonObject metadataObject = new JsonObject()
                .add("foo", "bar");

        JsonObject metadataResponseObject = new JsonObject()
                .add("foo", "bar")
                .add("$type", "properties")
                .add("$parent", "file_12345")
                .add("$id", "c1234-bda4-421a-a420-2142167bb98")
                .add("$version", 0)
                .add("$typeVersion", 5)
                .add("$template", "properties")
                .add("$scope", "global")
                .add("$canEdit", true);

        request = TestConfig.getFixture("BoxBatch/BatchCreateMetadataRequest");
        response = TestConfig.getFixture("BoxBatch/BatchCreateMetadataResponse");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(batchURL))
                .withRequestBody(WireMock.equalToJson(request))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));

        URL addMetadataOnFile = BoxFile.METADATA_URL_TEMPLATE.build(this.api.getBaseURL(),
                fileID, scope, template);
        BoxAPIRequest createMetadataRequest = new BoxAPIRequest(addMetadataOnFile, HttpMethod.POST);
        createMetadataRequest.setBody(metadataObject.toString());
        createMetadataRequest.addHeader("Content-Type", "application/json");
        requests.add(createMetadataRequest);

        BatchAPIRequest batchRequest = new BatchAPIRequest(this.api);
        List<BoxAPIResponse> responses = batchRequest.execute(requests);
        BoxJSONResponse createMetadataResponse = (BoxJSONResponse) responses.get(0);
        String metadataResponse = createMetadataResponse.getJSON().toString();
        assertEquals(metadataResponseObject.toString(), metadataResponse);
    }

}
