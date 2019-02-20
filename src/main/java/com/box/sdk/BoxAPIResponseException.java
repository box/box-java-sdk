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
        StringBuilder builder = null;
        JsonObject responseJSON = null;
        this.responseObj = responseObj;

        Map<String, List<String>> responseHeaders = new TreeMap<String, List<String>>(String.CASE_INSENSITIVE_ORDER);
        for (String headerKey : responseObj.getHeaders().keySet()) {
            List<String> headerValues = new ArrayList<String>();
            headerValues.add(responseObj.getHeaderField(headerKey));
            responseHeaders.put(headerKey, headerValues);
        }

        this.setHeaders(responseHeaders);

        if (this.getHeaders().containsKey("BOX-REQUEST-ID")) {
            requestId = "." + this.getHeaders().get("BOX-REQUEST-ID").get(0).toString();
            builder = new StringBuilder(requestId);
        }

        try {
            responseJSON = JsonObject.readFrom(responseObj.bodyToString());

            if (responseObj.bodyToString() != null) {
                if (responseJSON.get("request_id") != null) {
                    builder.insert(0, responseJSON.get("request_id").asString());
                }

                if (responseJSON.get("code") != null) {
                    apiMessage += " " + responseJSON.get("code").asString();
                }

                if (responseJSON.get("message") != null) {
                    apiMessage += " - " + responseJSON.get("message").asString();
                }
            }

            if (!builder.toString().isEmpty()) {
                this.setMessage(message + " [" + responseObj.getResponseCode() + " | " + builder.toString() + "]"
                        + apiMessage);
            } else {
                this.setMessage(message + " [" + responseObj.getResponseCode() + "]" + apiMessage);
            }

        } catch (Exception ex) {
            this.setMessage(message + " [" + responseObj.getResponseCode() + "]" + " | " + builder.toString());
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
