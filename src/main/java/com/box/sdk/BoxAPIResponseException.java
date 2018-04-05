package com.box.sdk;

import com.eclipsesource.json.JsonObject;

import java.net.HttpURLConnection;

/**
 * Thrown to indicate than an error occured while returning with a response from the Box API.
 */
public class BoxAPIResponseException extends BoxAPIException{

	private BoxAPIResponse responseObj;
	private String message;

	/**
	  * Constructs a BoxAPIException that contains detailed message for underlying exception.
	  * @param  response the response body returned by the Box server.
	  */
	 public BoxAPIResponseException(String message, BoxAPIResponse responseObj) {
		 super(message, responseObj.getResponseCode(), responseObj.bodyToString());
		 String requestId = "";
		 String apiMessage = "";
		 JsonObject responseJSON = null;

		 if(responseObj.bodyToString()!=null) {
			 responseJSON = JsonObject.readFrom(responseObj.bodyToString());

			 if(responseObj.bodyToString()!=null && responseJSON.get("request_id")!=null) {
				requestId = " | " + responseJSON.get("request_id").toString();
			}

			if(responseObj.bodyToString()!=null && responseJSON.get("code")!=null) {
				apiMessage += " " + responseJSON.get("code").toString();
			}

			if(responseObj.bodyToString()!=null && responseJSON.get("message")!=null) {
				apiMessage += " - " + responseJSON.get("message").toString();
			}

			this.message = String.format("%s [%d%s]%s", message, responseObj.getResponseCode(), requestId, apiMessage)
		 } else {
			this.message = message + " [" + responseObj.getResponseCode() + "]";
		 }
	 }
}
