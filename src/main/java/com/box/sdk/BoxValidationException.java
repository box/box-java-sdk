package com.box.sdk;

/**
 * Thrown to indicate that an error occurred while validating a Box API call.
 */
public class BoxValidationException {
	private final String message;

	/**
	 * Constructs a BoxValidationException with a specified message.
	 * @param message a message explaining why the exception occurred.
	 */
	public BoxValidationException(String message) {

		this.message = message;
	}

	
}
