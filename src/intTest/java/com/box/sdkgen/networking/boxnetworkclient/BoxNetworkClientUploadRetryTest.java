package com.box.sdkgen.networking.boxnetworkclient;

import static com.box.sdkgen.serialization.json.JsonManager.jsonToSerializedData;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.box.sdkgen.box.errors.BoxAPIError;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.MultipartItem;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.interceptors.Interceptor;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.networking.retries.BoxRetryStrategy;
import com.box.sdkgen.networking.retries.RetryStrategy;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.ConnectionSpec;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(value = 30, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
public class BoxNetworkClientUploadRetryTest {

  private static final byte[] FILE_CONTENT = randomBytes(256 * 1024);
  private static final int SUCCESS_STATUS = 201;

  private final List<byte[]> receivedBodies = Collections.synchronizedList(new ArrayList<>());
  private final List<String> receivedCustomHeaders =
      Collections.synchronizedList(new ArrayList<>());
  private final Queue<Integer> failureStatuses = new ConcurrentLinkedQueue<>();
  private final BoxNetworkClient client =
      new BoxNetworkClient(
          BoxNetworkClient.getDefaultOkHttpClientBuilder()
              .connectionSpecs(Collections.singletonList(ConnectionSpec.CLEARTEXT))
              .build());
  private final ExecutorService serverExecutor = Executors.newCachedThreadPool();
  private HttpServer server;
  private String url;

  @BeforeEach
  public void startServer() throws IOException {
    server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
    server.createContext("/", this::respond);
    server.setExecutor(serverExecutor);
    server.start();
    url = "http://127.0.0.1:" + server.getAddress().getPort() + "/files/content";
  }

  @AfterEach
  public void stopServer() {
    server.stop(0);
    serverExecutor.shutdownNow();
  }

  @Test
  public void testMultipartUploadWithByteArrayStreamIsRetriedWithFullContent() {
    failureStatuses.add(429);

    FetchResponse response = client.fetch(multipartUpload(new ByteArrayInputStream(FILE_CONTENT)));

    assertEquals(SUCCESS_STATUS, response.getStatus());
    assertEquals(2, receivedBodies.size());
    assertContainsFileContent(receivedBodies.get(0));
    assertArrayEquals(receivedBodies.get(0), receivedBodies.get(1));
  }

  @Test
  public void testMultipartUploadWithFileStreamIsRetriedWithFullContent() throws IOException {
    failureStatuses.add(503);

    try (FileInputStream stream = new FileInputStream(createTempFile())) {
      FetchResponse response = client.fetch(multipartUpload(stream));

      assertEquals(SUCCESS_STATUS, response.getStatus());
      assertFalse(stream.getChannel().isOpen());
    }
    assertEquals(2, receivedBodies.size());
    assertContainsFileContent(receivedBodies.get(0));
    assertArrayEquals(receivedBodies.get(0), receivedBodies.get(1));
  }

  @Test
  public void testMultipartUploadWithInterceptorIsRetriedWithFullContent() {
    failureStatuses.add(429);
    Interceptor headerInterceptor =
        new Interceptor() {
          @Override
          public FetchOptions beforeRequest(FetchOptions options) {
            return new FetchOptions.Builder(options.getUrl(), options.getMethod())
                .headers(Collections.singletonMap("X-Custom-Header", "value"))
                .contentType(options.getContentType())
                .multipartData(options.getMultipartData())
                .build();
          }

          @Override
          public FetchResponse afterRequest(FetchResponse response) {
            return response;
          }
        };
    NetworkSession networkSession =
        networkSessionWithoutRetryDelay().withInterceptors(Arrays.asList(headerInterceptor));

    FetchResponse response =
        client.fetch(multipartUpload(new ByteArrayInputStream(FILE_CONTENT), networkSession));

    assertEquals(SUCCESS_STATUS, response.getStatus());
    assertEquals(Arrays.asList("value", "value"), receivedCustomHeaders);
    assertEquals(2, receivedBodies.size());
    assertContainsFileContent(receivedBodies.get(0));
    assertArrayEquals(receivedBodies.get(0), receivedBodies.get(1));
  }

  @Test
  public void testMultipartUploadWithStreamThatCannotBeRewoundIsNotRetried() throws IOException {
    failureStatuses.add(429);
    InputStream stream = new BufferedInputStream(Files.newInputStream(createTempFile().toPath()));

    BoxAPIError error =
        assertThrows(BoxAPIError.class, () -> client.fetch(multipartUpload(stream)));

    assertEquals(429, error.getResponseInfo().getStatusCode());
    assertEquals("0", error.getResponseInfo().getHeaders().get("Retry-After"));
    assertEquals(1, receivedBodies.size());
    assertThrows(IOException.class, stream::available);
  }

  @Test
  public void testRetryStrategyIsCalledWhenUploadStreamCannotBeRewound() {
    failureStatuses.add(429);
    AtomicInteger retryStrategyCalls = new AtomicInteger();
    RetryStrategy retryStrategy =
        new RetryStrategy() {
          @Override
          public boolean shouldRetry(
              FetchOptions fetchOptions, FetchResponse fetchResponse, int attemptNumber) {
            retryStrategyCalls.incrementAndGet();
            return true;
          }

          @Override
          public double retryAfter(
              FetchOptions fetchOptions, FetchResponse fetchResponse, int attemptNumber) {
            return 0;
          }
        };
    NetworkSession networkSession =
        new NetworkSession.Builder().retryStrategy(retryStrategy).build();
    InputStream stream = new BufferedInputStream(new ByteArrayInputStream(FILE_CONTENT));

    assertThrows(BoxAPIError.class, () -> client.fetch(multipartUpload(stream, networkSession)));

    assertEquals(1, retryStrategyCalls.get());
    assertEquals(1, receivedBodies.size());
  }

  @Test
  public void testUploadPartWithStreamThatCannotBeRewoundIsRetriedWithFullContent() {
    failureStatuses.add(429);
    InputStream stream = new BufferedInputStream(new ByteArrayInputStream(FILE_CONTENT));
    FetchOptions options =
        new FetchOptions.Builder(url, "PUT")
            .contentType("application/octet-stream")
            .fileStream(stream)
            .networkSession(networkSessionWithoutRetryDelay())
            .build();

    FetchResponse response = client.fetch(options);

    assertEquals(SUCCESS_STATUS, response.getStatus());
    assertEquals(2, receivedBodies.size());
    assertArrayEquals(FILE_CONTENT, receivedBodies.get(0));
    assertArrayEquals(FILE_CONTENT, receivedBodies.get(1));
  }

  @Test
  public void testUploadErrorDoesNotIncludeFileContent() throws IOException {
    failureStatuses.add(409);

    try (FileInputStream stream = new FileInputStream(createTempFile())) {
      BoxAPIError error =
          assertThrows(BoxAPIError.class, () -> client.fetch(multipartUpload(stream)));

      assertEquals(409, error.getResponseInfo().getStatusCode());
      assertTrue(error.toString().contains("body='null'"));
    }
    assertEquals(1, receivedBodies.size());
  }

  private FetchOptions multipartUpload(InputStream fileStream) {
    return multipartUpload(fileStream, networkSessionWithoutRetryDelay());
  }

  private FetchOptions multipartUpload(InputStream fileStream, NetworkSession networkSession) {
    List<MultipartItem> parts =
        Arrays.asList(
            new MultipartItem.Builder("attributes")
                .data(jsonToSerializedData("{\"name\":\"file.bin\",\"parent\":{\"id\":\"0\"}}"))
                .build(),
            new MultipartItem.Builder("file").fileStream(fileStream).fileName("file.bin").build());
    return new FetchOptions.Builder(url, "POST")
        .contentType("multipart/form-data")
        .multipartData(parts)
        .networkSession(networkSession)
        .build();
  }

  private static NetworkSession networkSessionWithoutRetryDelay() {
    return new NetworkSession.Builder()
        .retryStrategy(new BoxRetryStrategy.Builder().retryBaseInterval(0).build())
        .build();
  }

  private void respond(HttpExchange exchange) throws IOException {
    receivedBodies.add(readAll(exchange.getRequestBody()));
    receivedCustomHeaders.add(exchange.getRequestHeaders().getFirst("X-Custom-Header"));
    Integer failureStatus = failureStatuses.poll();
    byte[] responseBody = "{}".getBytes(StandardCharsets.UTF_8);
    exchange.getResponseHeaders().add("Content-Type", "application/json");
    if (failureStatus != null && failureStatus == 429) {
      exchange.getResponseHeaders().add("Retry-After", "0");
    }
    exchange.sendResponseHeaders(
        failureStatus != null ? failureStatus : SUCCESS_STATUS, responseBody.length);
    try (OutputStream output = exchange.getResponseBody()) {
      output.write(responseBody);
    }
  }

  private static void assertContainsFileContent(byte[] body) {
    assertTrue(
        indexOf(body, FILE_CONTENT) >= 0, "Request body does not contain the full file content");
  }

  private static int indexOf(byte[] array, byte[] target) {
    outer:
    for (int i = 0; i <= array.length - target.length; i++) {
      for (int j = 0; j < target.length; j++) {
        if (array[i + j] != target[j]) {
          continue outer;
        }
      }
      return i;
    }
    return -1;
  }

  private static byte[] readAll(InputStream input) throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    byte[] buffer = new byte[8192];
    int read;
    while ((read = input.read(buffer)) != -1) {
      output.write(buffer, 0, read);
    }
    return output.toByteArray();
  }

  private static File createTempFile() throws IOException {
    File file = Files.createTempFile("upload-retry", ".bin").toFile();
    file.deleteOnExit();
    Files.write(file.toPath(), FILE_CONTENT);
    return file;
  }

  private static byte[] randomBytes(int size) {
    byte[] bytes = new byte[size];
    new Random(0).nextBytes(bytes);
    return bytes;
  }
}
