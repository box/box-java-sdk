package com.box.sdkgen.test;

import static com.box.sdkgen.serialization.json.JsonManager.jsonToSerializedData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.box.sdkgen.box.jwtauth.BoxJWTAuth;
import com.box.sdkgen.box.jwtauth.JWTConfig;
import com.box.sdkgen.internal.utils.PrivateKeyDecryptor;
import com.box.sdkgen.networking.baseurls.BaseUrls;
import com.box.sdkgen.networking.boxnetworkclient.BoxNetworkClient;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.RequestDataFactory;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.interceptors.Interceptor;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.networking.retries.RetryStrategy;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;

public class BoxNetworkClientRetryTest {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void testNetworkExceptionRetryRegeneratesRequestData() throws Exception {
    try (MockWebServer server = new MockWebServer()) {
      server.enqueue(jsonResponse(200, "{\"ok\":true}"));
      server.start();

      List<String> bodies = new ArrayList<>();
      CountingDataFactory dataFactory = new CountingDataFactory();
      testNetworkClientThatFailsFirstRequest(bodies)
          .fetch(
              baseOptions(server.url("/").toString(), jsonToSerializedData("{\"attempt\":\"D1\"}"))
                  .dataFactory(dataFactory)
                  .networkSession(
                      new NetworkSession()
                          .withRetryStrategy(
                              retryWhen(
                                  (options, response, attempt) ->
                                      response.getStatus() == 0 && attempt < 2)))
                  .build());

      assertEquals(Arrays.asList("attempt=D1", "attempt=D2"), bodies);
      assertEquals(1, dataFactory.calls);
    }
  }

  @Test
  public void test500RetryRegeneratesRequestData() throws Exception {
    try (MockWebServer server = new MockWebServer()) {
      server.enqueue(jsonResponse(500, "{\"error\":\"retry\"}"));
      server.enqueue(jsonResponse(200, "{\"ok\":true}"));
      server.start();

      CountingDataFactory dataFactory = new CountingDataFactory();
      testNetworkClient()
          .fetch(
              baseOptions(server.url("/").toString(), jsonToSerializedData("{\"attempt\":\"D1\"}"))
                  .dataFactory(dataFactory)
                  .networkSession(
                      new NetworkSession()
                          .withRetryStrategy(
                              retryWhen(
                                  (options, response, attempt) -> response.getStatus() == 500)))
                  .build());

      assertEquals("attempt=D1", takeBody(server));
      assertEquals("attempt=D2", takeBody(server));
      assertEquals(1, dataFactory.calls);
    }
  }

  @Test
  public void testRetryStrategyReceivesRegeneratedRequestData() throws Exception {
    try (MockWebServer server = new MockWebServer()) {
      server.enqueue(jsonResponse(500, "{\"error\":\"retry\"}"));
      server.enqueue(jsonResponse(200, "{\"ok\":true}"));
      server.start();

      List<String> retryData = new ArrayList<>();
      CountingDataFactory dataFactory = new CountingDataFactory();
      testNetworkClient()
          .fetch(
              baseOptions(server.url("/").toString(), jsonToSerializedData("{\"attempt\":\"D1\"}"))
                  .dataFactory(dataFactory)
                  .networkSession(
                      new NetworkSession()
                          .withRetryStrategy(
                              retryWhen(
                                  (options, response, attempt) -> {
                                    retryData.add(options.getData().get("attempt").asText());
                                    return response.getStatus() == 500;
                                  })))
                  .build());

      assertEquals(Arrays.asList("D1", "D2"), retryData);
    }
  }

  @Test
  public void testInterceptorRunsOnEachRetryBeforeDataIsRebuilt() throws Exception {
    try (MockWebServer server = new MockWebServer()) {
      server.enqueue(jsonResponse(500, "{\"error\":\"retry\"}"));
      server.enqueue(jsonResponse(200, "{\"ok\":true}"));
      server.start();

      List<String> interceptorData = new ArrayList<>();
      NetworkSession networkSession =
          new NetworkSession()
              .withInterceptors(
                  Arrays.asList(
                      new Interceptor() {
                        @Override
                        public FetchOptions beforeRequest(FetchOptions options) {
                          interceptorData.add(options.getData().get("attempt").asText());
                          return options;
                        }

                        @Override
                        public FetchResponse afterRequest(FetchResponse response) {
                          return response;
                        }
                      }))
              .withRetryStrategy(
                  retryWhen((options, response, attempt) -> response.getStatus() == 500));

      CountingDataFactory dataFactory = new CountingDataFactory();
      testNetworkClient()
          .fetch(
              baseOptions(server.url("/").toString(), jsonToSerializedData("{\"attempt\":\"D1\"}"))
                  .dataFactory(dataFactory)
                  .networkSession(networkSession)
                  .build());

      assertEquals(Arrays.asList("D1", "D1"), interceptorData);
      assertEquals("attempt=D1", takeBody(server));
      assertEquals("attempt=D2", takeBody(server));
      assertEquals(1, dataFactory.calls);
    }
  }

  @Test
  public void testDataFactoryRemovedByInterceptorIsNotUsed() throws Exception {
    try (MockWebServer server = new MockWebServer()) {
      server.enqueue(jsonResponse(500, "{\"error\":\"retry\"}"));
      server.enqueue(jsonResponse(200, "{\"ok\":true}"));
      server.start();

      NetworkSession networkSession =
          new NetworkSession()
              .withInterceptors(
                  Arrays.asList(
                      interceptorReplacingOptions(
                          options ->
                              baseOptions(
                                      options.getUrl(), jsonToSerializedData("{\"attempt\":\"I\"}"))
                                  .build())))
              .withRetryStrategy(
                  retryWhen((options, response, attempt) -> response.getStatus() == 500));

      CountingDataFactory dataFactory = new CountingDataFactory();
      testNetworkClient()
          .fetch(
              baseOptions(server.url("/").toString(), jsonToSerializedData("{\"attempt\":\"D1\"}"))
                  .dataFactory(dataFactory)
                  .networkSession(networkSession)
                  .build());

      assertEquals("attempt=I", takeBody(server));
      assertEquals("attempt=I", takeBody(server));
      assertEquals(0, dataFactory.calls);
    }
  }

  @Test
  public void testDataFactoryAddedByInterceptorIsUsedOnRetry() throws Exception {
    try (MockWebServer server = new MockWebServer()) {
      server.enqueue(jsonResponse(500, "{\"error\":\"retry\"}"));
      server.enqueue(jsonResponse(200, "{\"ok\":true}"));
      server.start();

      CountingDataFactory dataFactory = new CountingDataFactory();
      NetworkSession networkSession =
          new NetworkSession()
              .withInterceptors(
                  Arrays.asList(
                      interceptorReplacingOptions(
                          options ->
                              baseOptions(options.getUrl(), options.getData())
                                  .dataFactory(dataFactory)
                                  .build())))
              .withRetryStrategy(
                  retryWhen((options, response, attempt) -> response.getStatus() == 500));

      testNetworkClient()
          .fetch(
              baseOptions(server.url("/").toString(), jsonToSerializedData("{\"attempt\":\"D1\"}"))
                  .networkSession(networkSession)
                  .build());

      assertEquals("attempt=D1", takeBody(server));
      assertEquals("attempt=D2", takeBody(server));
      assertEquals(1, dataFactory.calls);
    }
  }

  @Test
  public void testRetryWithoutDataFactoryResendsSameRequestData() throws Exception {
    try (MockWebServer server = new MockWebServer()) {
      server.enqueue(jsonResponse(500, "{\"error\":\"retry\"}"));
      server.enqueue(jsonResponse(200, "{\"ok\":true}"));
      server.start();

      testNetworkClient()
          .fetch(
              baseOptions(server.url("/").toString(), jsonToSerializedData("{\"attempt\":\"D1\"}"))
                  .networkSession(
                      new NetworkSession()
                          .withRetryStrategy(
                              retryWhen(
                                  (options, response, attempt) -> response.getStatus() == 500)))
                  .build());

      assertEquals("attempt=D1", takeBody(server));
      assertEquals("attempt=D1", takeBody(server));
    }
  }

  @Test
  public void testWithDataCopiesFetchOptionsExceptData() throws Exception {
    RequestDataFactory dataFactory = new CountingDataFactory();
    NetworkSession networkSession = new NetworkSession();
    FetchOptions options =
        baseOptions("https://example.com", jsonToSerializedData("{\"attempt\":\"D1\"}"))
            .dataFactory(dataFactory)
            .fileStream(new ByteArrayInputStream("file".getBytes(StandardCharsets.UTF_8)))
            .responseFormat(ResponseFormat.JSON)
            .networkSession(networkSession)
            .followRedirects(false)
            .build();

    Method withData =
        BoxNetworkClient.class.getDeclaredMethod("withData", FetchOptions.class, JsonNode.class);
    withData.setAccessible(true);
    FetchOptions copied =
        (FetchOptions) withData.invoke(null, options, jsonToSerializedData("{\"attempt\":\"D2\"}"));

    for (Field field : FetchOptions.class.getDeclaredFields()) {
      field.setAccessible(true);
      if (field.getName().equals("data")) {
        assertEquals("D2", ((JsonNode) field.get(copied)).get("attempt").asText());
      } else {
        assertEquals(field.get(options), field.get(copied), "FetchOptions." + field.getName());
      }
    }
  }

  @Test
  public void testBoxJWTAuthRegeneratesAssertionOnRetry() throws Exception {
    try (MockWebServer server = new MockWebServer()) {
      server.enqueue(jsonResponse(500, "{\"error\":\"retry\"}"));
      server.enqueue(
          jsonResponse(
              200, "{\"access_token\":\"token\",\"expires_in\":3600,\"token_type\":\"bearer\"}"));
      server.start();

      KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
      generator.initialize(2048);
      KeyPair keyPair = generator.generateKeyPair();
      JWTConfig config =
          new JWTConfig.Builder(
                  "client-id", "client-secret", "jwt-key-id", "unused-private-key", "passphrase")
              .enterpriseId("enterprise-id")
              .privateKeyDecryptor(new FixedPrivateKeyDecryptor(keyPair.getPrivate()))
              .build();

      new BoxJWTAuth(config)
          .refreshToken(
              new NetworkSession()
                  .withCustomBaseUrls(
                      new BaseUrls.Builder()
                          .baseUrl(server.url("").toString().replaceAll("/$", ""))
                          .uploadUrl(server.url("").toString())
                          .oauth2Url(server.url("").toString())
                          .build())
                  .withNetworkClient(testNetworkClient())
                  .withRetryStrategy(
                      new RetryStrategy() {
                        @Override
                        public boolean shouldRetry(
                            FetchOptions options, FetchResponse response, int attemptNumber) {
                          return response.getStatus() == 500;
                        }

                        @Override
                        public double retryAfter(
                            FetchOptions options, FetchResponse response, int attemptNumber) {
                          return 1;
                        }
                      }));

      JwtPayload firstPayload = decodePayload(assertionFrom(takeBody(server)));
      JwtPayload secondPayload = decodePayload(assertionFrom(takeBody(server)));
      assertNotEquals(firstPayload.jti, secondPayload.jti);
      assertTrue(secondPayload.exp > firstPayload.exp);
    }
  }

  private FetchOptions.Builder baseOptions(String url, JsonNode data) {
    return new FetchOptions.Builder(url, "POST")
        .contentType("application/x-www-form-urlencoded")
        .responseFormat(ResponseFormat.JSON)
        .data(data);
  }

  private MockResponse jsonResponse(int status, String body) {
    return new MockResponse()
        .setResponseCode(status)
        .setHeader("Content-Type", "application/json")
        .setHeader("Retry-After", "0")
        .setBody(body);
  }

  private BoxNetworkClient testNetworkClient() {
    return new BoxNetworkClient(
        new OkHttpClient.Builder()
            .connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT))
            .followRedirects(false)
            .retryOnConnectionFailure(false)
            .build());
  }

  private BoxNetworkClient testNetworkClientThatFailsFirstRequest(List<String> bodies) {
    AtomicBoolean firstRequest = new AtomicBoolean(true);
    return new BoxNetworkClient(
        new OkHttpClient.Builder()
            .connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT))
            .followRedirects(false)
            .retryOnConnectionFailure(false)
            .addInterceptor(
                chain -> {
                  Request request = chain.request();
                  bodies.add(requestBodyToString(request));
                  if (firstRequest.getAndSet(false)) {
                    throw new IOException("Simulated network exception");
                  }
                  return chain.proceed(request);
                })
            .build());
  }

  private String requestBodyToString(Request request) throws IOException {
    if (request.body() == null) {
      return "";
    }
    okio.Buffer buffer = new okio.Buffer();
    request.body().writeTo(buffer);
    return buffer.readUtf8();
  }

  private String takeBody(MockWebServer server) throws Exception {
    RecordedRequest request = server.takeRequest();
    return request.getBody().readUtf8();
  }

  private RetryStrategy retryWhen(RetryPredicate predicate) {
    return new RetryStrategy() {
      @Override
      public boolean shouldRetry(FetchOptions options, FetchResponse response, int attemptNumber) {
        return predicate.shouldRetry(options, response, attemptNumber);
      }

      @Override
      public double retryAfter(FetchOptions options, FetchResponse response, int attemptNumber) {
        return 0;
      }
    };
  }

  private String assertionFrom(String body) throws java.io.UnsupportedEncodingException {
    for (String part : body.split("&")) {
      String[] keyValue = part.split("=", 2);
      if (keyValue.length == 2 && Objects.equals(keyValue[0], "assertion")) {
        return java.net.URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8.name());
      }
    }
    throw new AssertionError("Missing JWT assertion");
  }

  private JwtPayload decodePayload(String assertion) throws Exception {
    String[] parts = assertion.split("\\.");
    assertEquals(3, parts.length);
    byte[] payload = Base64.getUrlDecoder().decode(parts[1]);
    return objectMapper.readValue(payload, JwtPayload.class);
  }

  private Interceptor interceptorReplacingOptions(
      java.util.function.UnaryOperator<FetchOptions> replace) {
    return new Interceptor() {
      @Override
      public FetchOptions beforeRequest(FetchOptions options) {
        return replace.apply(options);
      }

      @Override
      public FetchResponse afterRequest(FetchResponse response) {
        return response;
      }
    };
  }

  private interface RetryPredicate {
    boolean shouldRetry(FetchOptions options, FetchResponse response, int attemptNumber);
  }

  private static class CountingDataFactory implements RequestDataFactory {
    int calls = 0;

    @Override
    public JsonNode create() {
      calls += 1;
      return jsonToSerializedData("{\"attempt\":\"D" + (calls + 1) + "\"}");
    }
  }

  private static class FixedPrivateKeyDecryptor implements PrivateKeyDecryptor {
    private final PrivateKey privateKey;

    FixedPrivateKeyDecryptor(PrivateKey privateKey) {
      this.privateKey = privateKey;
    }

    @Override
    public PrivateKey decryptPrivateKey(String encryptedPrivateKey, String passphrase) {
      return privateKey;
    }
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class JwtPayload {
    public String jti;
    public long exp;
  }
}
