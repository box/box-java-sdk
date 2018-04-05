package com.box.sdk;

/**
 * Thrown to indicate than an error occured while returning with a response from the Box API.
 */
public class BoxAPIResponseException extends RuntimeException{

	private final String message;
	private final String response;
	private BoxAPIResponse responseObj;

	/**
	 * Constructs a BoxAPIResponseException with a specified message.
	 * @param message	a message explaining why the exception occurred.
	 */
	public BoxAPIResponseException(String message) {
		super(message);

		this.message = message;
		this.response = null;
		this.responseObj = null;
	}

	/**
	 * Constructs a BoxAPIResponseException with a detailed message for underlying exception.
	 * @param message		a message explaining why the exception occurred.
	 * @param responseBody	the response body returned by the Box server.
	 */
	public BoxAPIResponseException(String message, String responseBody) {
		super(message);

		this.message = message;
		this.response = responseBody;
		this.responseObj = null;
	}

	/**
	 * Constructs a BoxAPIResponseException that wraps another underlying exception with details about the server's response.
	 * @param message			a message explaining why the exception occurred.
	 * @param responseBody		the response body returned by the Box server.
	 * @param response			the response returned by the Box server.
	 */
	public BoxAPIResponseException(String message, String responseBody, BoxAPIResponse response) {
		super(message);

		this.message = message;
		this.response = responseBody;
		this.responseObj = response;
	}

}
