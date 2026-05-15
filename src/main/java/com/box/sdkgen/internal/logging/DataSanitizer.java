package com.box.sdkgen.internal.logging;

import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.sanitizeMap;
import static com.box.sdkgen.serialization.json.JsonManager.jsonToSerializedData;
import static com.box.sdkgen.serialization.json.JsonManager.sanitizeFormEncodedBodyFromString;
import static com.box.sdkgen.serialization.json.JsonManager.sanitizeSerializedData;
import static com.box.sdkgen.serialization.json.JsonManager.sdToJson;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;

public class DataSanitizer {

  public final Map<String, String> keysToSanitize;

  public DataSanitizer() {
    this.keysToSanitize =
        mapOf(
            entryOf("authorization", ""),
            entryOf("access_token", ""),
            entryOf("refresh_token", ""),
            entryOf("subject_token", ""),
            entryOf("token", ""),
            entryOf("client_id", ""),
            entryOf("client_secret", ""),
            entryOf("shared_link", ""),
            entryOf("download_url", ""),
            entryOf("jwt_private_key", ""),
            entryOf("jwt_private_key_passphrase", ""),
            entryOf("password", ""));
  }

  public Map<String, String> sanitizeHeaders(Map<String, String> headers) {
    return sanitizeMap(headers, this.keysToSanitize);
  }

  public JsonNode sanitizeBody(JsonNode body) {
    return sanitizeSerializedData(body, this.keysToSanitize);
  }

  public String sanitizeFormEncodedBody(String body) {
    return sanitizeFormEncodedBodyFromString(body, this.keysToSanitize);
  }

  public String sanitizeStringBody(String body) {
    return sanitizeStringBody(body, null);
  }

  public String sanitizeStringBody(String body, String contentType) {
    if (contentType.equals("application/json")
        || contentType.equals("application/json-patch+json")) {
      try {
        return sdToJson(this.sanitizeBody(jsonToSerializedData(body)));
      } catch (Exception exception) {
        return body;
      }
    }
    if (contentType.equals("application/x-www-form-urlencoded")) {
      return this.sanitizeFormEncodedBody(body);
    }
    return body;
  }
}
