package com.box.sdk;

import com.box.sdk.http.HttpHeaders;
import com.box.sdk.http.HttpMethod;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @deprecated As of 2.39.0, BatchAPI Request will no longer be supported.
 * <p>
 * Used to make a bunch of HTTP Requests as a batch. Currently the number of requests that can be batched at once
 * is capped at <b>20</b> by the API layer. Also there are certain requests which <b>cannot</b> be performed
 * by Batch API. Check API documentation for more information.
 *
 * <p>The request itself is a BoxJSONRequest but extends it to provide additional functionality for aggregating
 * a bunch of requests and responding with multiple responses as if the requests were called individually</p>
 */
@Deprecated
public class BatchAPIRequest extends BoxJSONRequest {
    /**
     * Batch URL Template.
     */
    public static final URLTemplate BATCH_URL_TEMPLATE = new URLTemplate("batch");
    private final BoxAPIConnection api;

    /**
     * @param api an API connection for authenticating the request.
     * @deprecated As of 2.39.0, BatchAPI Request will no longer be supported.
     * <p>
     * Constructs an authenticated BatchRequest using a provided BoxAPIConnection.
     */
    @Deprecated
    public BatchAPIRequest(BoxAPIConnection api) {
        super(api, BATCH_URL_TEMPLATE.build(api.getBaseURL()), HttpMethod.GET);
        this.api = api;
    }

    /**
     * @param requests list of api requests that has to be executed in batch.
     * @return list of BoxAPIResponses
     * @deprecated As of 2.39.0, BatchAPI Request will no longer be supported.
     * <p>
     * Execute a set of API calls as batch request.
     */
    @Deprecated
    public List<BoxAPIResponse> execute(List<BoxAPIRequest> requests) {
        this.prepareRequest(requests);
        BoxJSONResponse batchResponse = (BoxJSONResponse) send();
        return this.parseResponse(batchResponse);
    }

    /**
     * @param requests list of api requests that has to be executed in batch.
     * @deprecated As of 2.39.0, BatchAPI Request will no longer be supported.
     * <p>
     * Prepare a batch api request using list of individual reuests.
     */
    @Deprecated
    protected void prepareRequest(List<BoxAPIRequest> requests) {
        JsonObject body = new JsonObject();
        JsonArray requestsJSONArray = new JsonArray();
        for (BoxAPIRequest request : requests) {
            JsonObject batchRequest = new JsonObject();
            batchRequest.add("method", request.getMethod());
            batchRequest.add("relative_url", request.getUrl().toString().substring(this.api.getBaseURL().length() - 1));
            //If the actual request has a JSON body then add it to vatch request
            if (request instanceof BoxJSONRequest) {
                BoxJSONRequest jsonRequest = (BoxJSONRequest) request;
                batchRequest.add("body", jsonRequest.getBodyAsJsonValue());
            }
            //Add any headers that are in the request, except Authorization
            if (request.getHeaders() != null) {
                JsonObject batchRequestHeaders = new JsonObject();
                for (RequestHeader header : request.getHeaders()) {
                    if (header.getKey() != null && !header.getKey().isEmpty()
                        && !HttpHeaders.AUTHORIZATION.equals(header.getKey())) {
                        batchRequestHeaders.add(header.getKey(), header.getValue());
                    }
                }
                batchRequest.add("headers", batchRequestHeaders);
            }

            //Add the request to array
            requestsJSONArray.add(batchRequest);
        }
        //Add the requests array to body
        body.add("requests", requestsJSONArray);
        super.setBody(body);
    }

    /**
     * @param batchResponse response of a batch api request
     * @return list of BoxAPIResponses
     * @deprecated As of 2.39.0, BatchAPI Request will no longer be supported.
     * <p>
     * Parses btch api response to create a list of BoxAPIResponse objects.
     */
    @Deprecated
    protected List<BoxAPIResponse> parseResponse(BoxJSONResponse batchResponse) {
        JsonObject responseJSON = Json.parse(batchResponse.getJSON()).asObject();
        List<BoxAPIResponse> responses = new ArrayList<>();
        Iterator<JsonValue> responseIterator = responseJSON.get("responses").asArray().iterator();
        while (responseIterator.hasNext()) {
            JsonObject jsonResponse = responseIterator.next().asObject();
            BoxAPIResponse response;

            //Gather headers
            Map<String, String> responseHeaders = new HashMap<>();

            if (jsonResponse.get("headers") != null) {
                JsonObject batchResponseHeadersObject = jsonResponse.get("headers").asObject();
                for (JsonObject.Member member : batchResponseHeadersObject) {
                    String headerName = member.getName();
                    String headerValue = member.getValue().asString();
                    responseHeaders.put(headerName, headerValue);
                }
            }

            // Construct a BoxAPIResponse when response is null, or a BoxJSONResponse when there's a response
            // (not anticipating any other response as per current APIs.
            // Ideally we should do it based on response header)
            if (jsonResponse.get("response") == null || jsonResponse.get("response").isNull()) {
                response =
                    new BoxAPIResponse(jsonResponse.get("status").asInt(), responseHeaders);
            } else {
                response =
                    new BoxJSONResponse(jsonResponse.get("status").asInt(), responseHeaders,
                        jsonResponse.get("response").asObject());
            }
            responses.add(response);
        }
        return responses;
    }
}
