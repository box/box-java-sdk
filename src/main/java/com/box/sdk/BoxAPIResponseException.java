package com.box.sdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.eclipsesource.json.JsonObject;

/**
 * Thrown to indicate than an error occured while returning with a response from the Box API.
 */
public class BoxAPIResponseException extends BoxAPIException {

    private String message;
    private BoxAPIResponse responseObj;

    /**
     * Constructs a BoxAPIException that contains detailed message for underlying exception.
     *
     * @param message     a message explaining why the error occurred.
     * @param responseObj a response object from the server.
     */
    public BoxAPIResponseException(String message, BoxAPIResponse responseObj) {
        super(message, responseObj.getResponseCode(), responseObj.bodyToString());
        String requestId = "";
        String apiMessage = "";
        JsonObject responseJSON = null;
        this.responseObj = responseObj;

        Map<String, List<String>> responseHeaders = new TreeMap<String, List<String>>(String.CASE_INSENSITIVE_ORDER);
        for (String headerKey : responseObj.getHeaders().keySet()) {
            List<String> headerValues = new ArrayList<String>();
            headerValues.add(responseObj.getHeaderField(headerKey));
            responseHeaders.put(headerKey, headerValues);
        }

        this.setHeaders(responseHeaders);

        try {
            responseJSON = JsonObject.readFrom(responseObj.bodyToString());

            if (responseObj.bodyToString() != null && responseJSON.get("request_id") != null) {
                requestId = " | " + responseJSON.get("request_id").asString();
            }

            if (responseObj.bodyToString() != null && responseJSON.get("code") != null) {
                apiMessage += " " + responseJSON.get("code").asString();
            }

            if (responseObj.bodyToString() != null && responseJSON.get("message") != null) {
                apiMessage += " - " + responseJSON.get("message").asString();
            }

            this.setMessage(message + " [" + responseObj.getResponseCode() + requestId + "]" + apiMessage);

        } catch (Exception ex) {
            this.setMessage(message + " [" + responseObj.getResponseCode() + "]");
        }
    }

    /**
     * The message to return for the API exception.
     * @param message the constructed for the API exception.
     */
    protected void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return The constructed message for the API exception.
     */
    public String getMessage() {
        return this.message;
    }
}
