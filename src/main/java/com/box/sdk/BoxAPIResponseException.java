package com.box.sdk;

import com.eclipsesource.json.JsonObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Thrown to indicate than an error occured while returning with a response from the Box API.
 */
public class BoxAPIResponseException extends BoxAPIException{

	private BoxAPIResponse responseObj;

	/**
	  * Constructs a BoxAPIException that contains detailed message for underlying exception.
	  * @param  response the response body returned by the Box server.
	  */
	 public BoxAPIResponseException(String message, BoxAPIResponse responseObj) {
		 super(message, responseObj.getResponseCode(), responseObj.bodyToString());
		 String requestId = "";
		 String apiMessage = "";
		 JsonObject responseJSON = null;
		 this.responseObj = responseObj;

		 Map<String, List<String>> responseHeaders = new HashMap<String, List<String>>();
		 for(String headerKey : responseObj.getHeaders().keySet()) {
		 	List<String> headerValues = new ArrayList<String>();
		 	headerValues.add(responseObj.getHeaderField(headerKey));
		 	responseHeaders.put(headerKey, headerValues);
		 }
		 this.headers = responseHeaders;

		 if(responseObj.bodyToString()!=null && !responseObj.bodyToString().equals("")) {
			 responseJSON = JsonObject.readFrom(responseObj.bodyToString());

			 if(responseObj.bodyToString()!=null && responseJSON.get("request_id")!=null) {
				requestId = " | " + responseJSON.get("request_id").asString();
			}

			if(responseObj.bodyToString()!=null && responseJSON.get("code")!=null) {
				apiMessage += " " + responseJSON.get("code").asString();
			}

			if(responseObj.bodyToString()!=null && responseJSON.get("message")!=null) {
				apiMessage += " - " + responseJSON.get("message").asString();
			}

			this.message = message + " [" +  responseObj.getResponseCode() + requestId + "]" + apiMessage;

		 } else {
			this.message = message + " [" + responseObj.getResponseCode() + "]";
		 }
	 }
}
