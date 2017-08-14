package com.box.sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.box.sdk.http.HttpHeaders;
import com.box.sdk.http.HttpMethod;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import org.jose4j.json.internal.json_simple.JSONArray;
import org.jose4j.json.internal.json_simple.JSONObject;

/**
 *
 */
public class BatchAPIRequest extends BoxJSONRequest{
    private static final Logger LOGGER = Logger.getLogger(BoxAPIRequest.class.getName());
    public static final URLTemplate RECENTS_URL_TEMPLATE = new URLTemplate("batch");
    private final BoxAPIConnection api;

    /**
     * Constructs an authenticated BatchRequest using a provided BoxAPIConnection.
     * @param  api    an API connection for authenticating the request.
     */
    public BatchAPIRequest(BoxAPIConnection api) {
        super(api, RECENTS_URL_TEMPLATE.build(api.getBaseURL()), HttpMethod.GET);
        this.api = api;
    }

    /**
     * Execute a set of API calls as batch request
     * @return
     */
    public List<BoxAPIResponse> execute(List<BoxAPIRequest> requests) {
        JsonObject body = new JsonObject();
        JsonArray requestsJSONArray = new JsonArray();
        for(BoxAPIRequest request: requests) {
            JsonObject batchRequest = new JsonObject();
            batchRequest.add("method", request.getMethod());
            batchRequest.add("relative_url", request.getUrl().toString().substring(this.api.getBaseURL().length()-1));
            //If the actual request has a JSON body then add it to vatch request
            if (request instanceof BoxJSONRequest) {
                BoxJSONRequest jsonRequest = (BoxJSONRequest) request;
                batchRequest.add("body", jsonRequest.getBodyAsJsonObject());
            }
            //Add any headers that are in the request, except Authorization
            if (request.getHeaders() != null) {
                JsonObject batchRequestHeaders = new JsonObject();
                for(RequestHeader header: request.getHeaders()) {
                    if (header.getKey() != null && !header.getKey().isEmpty()
                        && HttpHeaders.AUTHORIZATION.equals(header.getKey())) {
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

        BoxJSONResponse batchResponse = (BoxJSONResponse) super.send();
        JsonObject responseJSON = JsonObject.readFrom(batchResponse.getJSON());
        List<BoxAPIResponse> responses = new ArrayList<BoxAPIResponse>();
        Iterator<JsonValue> iterator = responseJSON.get("responses").asArray().iterator();
        while(iterator.hasNext()) {
            JsonObject jsonResponse = iterator.next().asObject();
            BoxAPIResponse response =
                new BoxAPIResponse(jsonResponse.get("status").asInt(), jsonResponse.get("response").toString());
            responses.add(response);
        }
        return responses;
    }
}
