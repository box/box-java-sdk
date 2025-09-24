package com.box.sdk;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.eclipsesource.json.JsonObject;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Headers;
import org.junit.Test;

public class BoxSensitiveDataSanitizerTest {
  @Test
  public void removeSensitiveDataFromHeaders() {
    Map<String, String> headersMap = new HashMap<>();
    headersMap.put("authorization", "token");
    headersMap.put("user-agent", "java-sdk");

    Headers headers = Headers.of(headersMap);
    Headers sanitizedHeaders = BoxSensitiveDataSanitizer.sanitizeHeaders(headers);

    assertThat(sanitizedHeaders.size(), is(2));
    assertThat(sanitizedHeaders.get("authorization"), is("[REDACTED]"));
    assertThat(sanitizedHeaders.get("user-agent"), is("java-sdk"));
  }

  @Test
  public void removeAllHeadersWhenOnlySensitiveData() {
    Map<String, String> headersMap = new HashMap<>();
    headersMap.put("authorization", "token");
    headersMap.put("password", "123");

    Headers headers = Headers.of(headersMap);
    Headers sanitizedHeaders = BoxSensitiveDataSanitizer.sanitizeHeaders(headers);

    assertThat(sanitizedHeaders.size(), is(2));
    assertThat(sanitizedHeaders.get("authorization"), is("[REDACTED]"));
    assertThat(sanitizedHeaders.get("password"), is("[REDACTED]"));
  }

  @Test
  public void removeSensitiveDataFromHeadersWhenUppercase() {
    Map<String, String> headersMap = new HashMap<>();
    headersMap.put("Authorization", "token");
    headersMap.put("user-agent", "java-sdk");

    Headers headers = Headers.of(headersMap);
    Headers sanitizedHeaders = BoxSensitiveDataSanitizer.sanitizeHeaders(headers);

    assertThat(sanitizedHeaders.size(), is(2));
    assertThat(sanitizedHeaders.get("Authorization"), is("[REDACTED]"));
    assertThat(sanitizedHeaders.get("user-agent"), is("java-sdk"));
  }

  @Test
  public void headersNotRemovedWhenNoSensitiveData() {
    Map<String, String> headersMap = new HashMap<>();
    headersMap.put("accept", "application/json");
    headersMap.put("user-agent", "java-sdk");

    Headers headers = Headers.of(headersMap);
    Headers sanitizedHeaders = BoxSensitiveDataSanitizer.sanitizeHeaders(headers);

    assertThat(sanitizedHeaders.size(), is(2));
    assertThat(sanitizedHeaders.get("accept"), is("application/json"));
    assertThat(sanitizedHeaders.get("user-agent"), is("java-sdk"));
  }

  @Test
  public void returnEmptyHeadersWhenEmptyHeadersPassed() {
    Headers headers = Headers.of(new HashMap<>());
    Headers sanitizedHeaders = BoxSensitiveDataSanitizer.sanitizeHeaders(headers);

    assertThat(sanitizedHeaders.size(), is(0));
  }

  @Test
  public void sanitizeAddedKeys() {
    Map<String, String> headersMap = new HashMap<>();
    headersMap.put("x-auth", "token");

    Headers headers = Headers.of(headersMap);
    BoxSensitiveDataSanitizer.addKeyToSanitize("x-auth");
    Headers sanitizedHeaders = BoxSensitiveDataSanitizer.sanitizeHeaders(headers);

    assertThat(sanitizedHeaders.size(), is(1));
    assertThat(sanitizedHeaders.get("x-auth"), is("[REDACTED]"));
  }

  @Test
  public void removeSensitiveDataFromJsonBody() {
    JsonObject body = new JsonObject().add("authorization", "token").add("user-agent", "java-sdk");
    JsonObject sanitizedBody = BoxSensitiveDataSanitizer.sanitizeJsonBody(body);

    assertThat(sanitizedBody.get("authorization").asString(), is("[REDACATED]"));
    assertThat(sanitizedBody.get("user-agent").asString(), is("java-sdk"));
  }
}
