package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import java.util.List;
import java.util.Map;

/** Thrown to indicate than an error occured while returning with a response from the Box API. */
public class BoxAPIResponseException extends BoxAPIException {
  static final long serialVersionUID = -7515717760101647173L;
  private String message;

  /**
   * Constructs a BoxAPIException that contains detailed message for underlying exception.
   *
   * @param message a message explaining why the error occurred.
   * @param responseCode a response code.
   * @param bodyString a response body.
   * @param responseHeaders response headers.
   */
  public BoxAPIResponseException(
      String message,
      int responseCode,
      String bodyString,
      Map<String, List<String>> responseHeaders) {
    super(message, responseCode, bodyString);
    String requestId = "";
    String apiMessage = "";
    JsonObject responseJSON = null;

    this.setHeaders(responseHeaders);

    if (this.getHeaders().containsKey("BOX-REQUEST-ID")) {
      requestId += "." + this.getHeaders().get("BOX-REQUEST-ID").get(0);
    }

    try {
      responseJSON = Json.parse(bodyString).asObject();
    } catch (Exception ex) {
      // Continue because we will construct the exception message below and return it to user.
    }

    if (responseJSON != null) {
      if (responseJSON.get("request_id") != null) {
        requestId = responseJSON.get("request_id").asString() + requestId;
      }

      if (responseJSON.get("code") != null) {
        apiMessage += " " + responseJSON.get("code").asString();
      } else if (responseJSON.get("error") != null) {
        apiMessage += " " + responseJSON.get("error").asString();
      }

      if (responseJSON.get("message") != null) {
        apiMessage += " - " + responseJSON.get("message").asString();
      } else if (responseJSON.get("error_description") != null) {
        apiMessage += " - " + responseJSON.get("error_description").asString();
      }
    }

    if (!requestId.isEmpty()) {
      this.setMessage(message + " [" + responseCode + " | " + requestId + "]" + apiMessage);
    } else {
      this.setMessage(message + " [" + responseCode + "]" + apiMessage);
    }
  }

  /** @return The constructed message for the API exception. */
  public String getMessage() {
    return this.message;
  }

  /**
   * The message to return for the API exception.
   *
   * @param message the constructed for the API exception.
   */
  protected void setMessage(String message) {
    this.message = message;
  }
}
