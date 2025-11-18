package com.box.sdkgen.internal.utils;

import com.box.sdkgen.box.errors.BoxSDKError;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.JsonManager;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.lang.JoseException;

public class UtilsManager {
  private static final int BUFFER_SIZE = 8192;

  private static final DateTimeFormatter OFFSET_DATE_TIME_FORMAT =
      new DateTimeFormatterBuilder()
          .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
          .optionalStart()
          .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
          .optionalEnd()
          .appendOffsetId()
          .toFormatter();
  private static final DateTimeFormatter OFFSET_DATE_TIME_FORMAT_WITH_MILLIS =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
  private static final DateTimeFormatter OFFSET_DATE_FORMAT =
      DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public static <K, V> Map<K, V> mapOf(Entry<K, V>... entries) {
    return Arrays.stream(entries)
        .collect(
            HashMap::new,
            (map, entry) -> map.put(entry.getKey(), entry.getValue()),
            HashMap::putAll);
  }

  public static <V> Set<V> setOf(V... values) {
    return Arrays.stream(values).collect(Collectors.toSet());
  }

  public static <K, V> Entry<K, V> entryOf(K key, V value) {
    return Entry.of(key, value);
  }

  public static <K, V> Map<K, V> mergeMaps(Map<K, V> map1, Map<K, V> map2) {
    Map<K, V> mergedMap = new HashMap<>();
    if (map1 != null) {
      mergedMap.putAll(map1);
    }
    if (map2 != null) {
      mergedMap.putAll(map2);
    }
    return mergedMap;
  }

  public static Map<String, String> prepareParams(Map<String, String> map) {
    map.values().removeIf(Objects::isNull);
    return map;
  }

  public static String convertToString(Object value) {
    if (value == null) {
      return null;
    }
    if (value instanceof EnumWrapper) {
      return ((EnumWrapper<?>) value).getStringValue();
    }
    if (value instanceof Valuable) {
      return ((Valuable) value).getValue();
    }
    if (value instanceof List) {
      List<?> list = (List<?>) value;
      if (!list.isEmpty() && list.get(0) instanceof SerializableObject) {
        return JsonManager.serialize(value).toString();
      } else {
        return ((List<?>) value)
            .stream().map(UtilsManager::convertToString).collect(Collectors.joining(","));
      }
    }
    if (value instanceof ArrayNode) {
      return convertToString(new ObjectMapper().convertValue(value, List.class));
    }
    if (value instanceof JsonNode) {
      return ((JsonNode) value).asText();
    }
    if (value instanceof SerializableObject) {
      return JsonManager.serialize(value).toString();
    }
    return value.toString();
  }

  public static void writeInputStreamToOutputStream(InputStream input, OutputStream output) {
    try {
      byte[] buffer = new byte[BUFFER_SIZE];
      int n = input.read(buffer);
      while (n != -1) {
        output.write(buffer, 0, n);
        n = input.read(buffer);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        input.close();
        output.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static String getUuid() {
    return UUID.randomUUID().toString();
  }

  public static byte[] generateByteBuffer(int size) {
    byte[] bytes = new byte[size];
    Arrays.fill(bytes, (byte) 0);
    return bytes;
  }

  public static InputStream generateByteStream(int size) {
    byte[] bytes = generateByteBuffer(size);
    return new ByteArrayInputStream(bytes);
  }

  public static InputStream generateByteStreamFromBuffer(byte[] buffer) {
    return new ByteArrayInputStream(buffer);
  }

  public static byte[] readByteStream(InputStream inputStream) {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    byte[] data = new byte[BUFFER_SIZE];
    int bytesRead;
    try {
      while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
        buffer.write(data, 0, bytesRead);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        inputStream.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    return buffer.toByteArray();
  }

  public static boolean bufferEquals(byte[] buffer1, byte[] buffer2) {
    return Arrays.equals(buffer1, buffer2);
  }

  public static int bufferLength(byte[] buffer) {
    return buffer.length;
  }

  public static InputStream decodeBase64ByteStream(String value) {
    return new ByteArrayInputStream(Base64.getDecoder().decode(value));
  }

  public static String decodeBase64(String value) {
    return new String(Base64.getDecoder().decode(value));
  }

  public static InputStream stringToByteStream(String value) {
    return new ByteArrayInputStream(value.getBytes());
  }

  public static OutputStream getFileOutputStream(String filePath) {
    try {
      return new FileOutputStream(filePath);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public static void closeFileOutputStream(OutputStream outputStream) {
    try {
      outputStream.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static byte[] readBufferFromFile(String filePath) {
    try {
      InputStream inputStream = Files.newInputStream(Paths.get(filePath));
      return readByteStream(inputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static String getEnvVar(String envVar) {
    return System.getenv(envVar);
  }

  public static void delayInSeconds(int seconds) {
    try {
      Thread.sleep(seconds * 1000L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static String readTextFromFile(String filePath) {
    try {
      return new String(Files.readAllBytes(Paths.get(filePath)));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static boolean isBrowser() {
    return false;
  }

  public static long getEpochTimeInSeconds() {
    return System.currentTimeMillis() / 1000;
  }

  public static String createJwtAssertion(
      Map<String, Object> claims, JwtKey jwtKey, JwtSignOptions jwtOptions) {
    JwtClaims jwtClaims = new JwtClaims();
    jwtClaims.setIssuer(jwtOptions.getIssuer());
    jwtClaims.setAudience(jwtOptions.getAudience());
    jwtClaims.setExpirationTime(NumericDate.fromSeconds((Long) claims.get("exp")));

    jwtClaims.setSubject(jwtOptions.getSubject());
    jwtClaims.setClaim("box_sub_type", claims.get("box_sub_type"));
    jwtClaims.setGeneratedJwtId(64);

    JsonWebSignature jws = new JsonWebSignature();
    jws.setPayload(jwtClaims.toJson());
    jws.setKey(
        jwtOptions.privateKeyDecryptor.decryptPrivateKey(jwtKey.getKey(), jwtKey.getPassphrase()));
    jws.setAlgorithmHeaderValue(jwtOptions.getAlgorithm().getValue());
    jws.setHeader("typ", "JWT");
    if ((jwtOptions.getKeyid() != null) && !jwtOptions.getKeyid().isEmpty()) {
      jws.setHeader("kid", jwtOptions.getKeyid());
    }

    String assertion;

    try {
      assertion = jws.getCompactSerialization();
    } catch (JoseException e) {
      throw new BoxSDKError("Error serializing JSON Web Token assertion.", e);
    }

    return assertion;
  }

  public static JsonNode getValueFromObjectRawData(SerializableObject obj, String key) {
    JsonNode value = obj.getRawData();
    for (String k : key.split("\\.")) {
      if (value == null || !value.has(k)) {
        return null;
      }
      value = value.get(k);
    }

    return value;
  }

  public static double random(double min, double max) {
    return Math.random() * (max - min) + min;
  }

  public static String hexToBase64(String hex) {
    return Base64.getEncoder().encodeToString(new BigInteger(hex, 16).toByteArray());
  }

  public static Iterator<InputStream> iterateChunks(
      InputStream stream, long chunkSize, long fileSize) {
    return new Iterator<InputStream>() {
      private boolean streamIsFinished = false;

      @Override
      public boolean hasNext() {
        return !streamIsFinished;
      }

      @Override
      public InputStream next() {
        try {
          byte[] buffer = new byte[(int) chunkSize];
          int bytesRead = 0;

          while (bytesRead < chunkSize) {
            int read = stream.read(buffer, bytesRead, (int) (chunkSize - bytesRead));
            if (read == -1) {
              // End of stream
              streamIsFinished = true;
              break;
            }
            bytesRead += read;
          }

          if (bytesRead == 0) {
            // No more data to yield
            streamIsFinished = true;
            return null;
          }

          // Return the chunk as a ByteArrayInputStream
          return new ByteArrayInputStream(buffer, 0, bytesRead);
        } catch (Exception e) {
          throw new RuntimeException("Error reading from stream", e);
        }
      }
    };
  }

  /**
   * Reduces an iterator using a reducer function and an initial value.
   *
   * @param <Accumulator> The type of the accumulator (result)
   * @param <T> The type of the items in the iterator
   * @param iterator The iterator to process
   * @param reducer The reducer function
   * @param initialValue The initial value for the accumulator
   * @return The accumulated result
   */
  public static <Accumulator, T> Accumulator reduceIterator(
      Iterator<T> iterator,
      BiFunction<Accumulator, T, Accumulator> reducer,
      Accumulator initialValue) {
    Accumulator result = initialValue;

    while (iterator.hasNext()) {
      T item = iterator.next();
      result = reducer.apply(result, item);
    }

    return result;
  }

  public static Map<String, String> sanitizeMap(
      Map<String, String> dictionary, Map<String, String> keysToSanitize) {
    return dictionary.entrySet().stream()
        .collect(
            Collectors.toMap(
                Map.Entry::getKey,
                entry ->
                    keysToSanitize.containsKey(entry.getKey().toLowerCase(Locale.ROOT))
                        ? JsonManager.sanitizedValue()
                        : entry.getValue()));
  }

  public static OffsetDateTime dateTimeFromString(String dateString) {
    try {
      return OffsetDateTime.parse(dateString, OFFSET_DATE_TIME_FORMAT);
    } catch (DateTimeParseException e) {
      return null;
    }
  }

  public static String dateTimeToString(OffsetDateTime dateTime) {
    return dateTime.format(OFFSET_DATE_TIME_FORMAT_WITH_MILLIS);
  }

  public static OffsetDateTime dateFromString(String dateString) {
    try {
      // For date-only strings, parse as date and convert to OffsetDateTime at start of day UTC
      if (dateString.matches("\\d{4}-\\d{2}-\\d{2}")) {
        return OffsetDateTime.parse(dateString + "T00:00:00Z");
      }
      // Otherwise try to parse as full OffsetDateTime
      return dateTimeFromString(dateString);
    } catch (DateTimeParseException e) {
      return null;
    }
  }

  public static String dateToString(OffsetDateTime date) {
    return date.format(OFFSET_DATE_FORMAT);
  }

  public static String escapeUnicode(String value) {
    if (value == null) {
      return null;
    }

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < value.length(); i++) {
      char ch = value.charAt(i);
      if (ch >= 0x007F) {
        result.append(String.format("\\u%04x", (int) ch));
      } else if (ch == '\\') {
        result.append("\\\\");
      } else if (ch == '\n') {
        result.append("\\n");
      } else if (ch == '\r') {
        result.append("\\r");
      } else if (ch == '\t') {
        result.append("\\t");
      } else if (ch == '/') {
        if (i == 0 || value.charAt(i - 1) != '\\') {
          result.append("\\/");
        } else {
          result.append(ch);
        }
      } else {
        result.append(ch);
      }
    }
    return result.toString();
  }

  public static OffsetDateTime epochSecondsToDateTime(long seconds) {
    return OffsetDateTime.ofInstant(java.time.Instant.ofEpochSecond(seconds), ZoneOffset.UTC);
  }

  public static long dateTimeToEpochSeconds(OffsetDateTime dateTime) {
    return dateTime.toEpochSecond();
  }

  public static boolean compareSignatures(String expectedSignature, String receivedSignature) {
    if (expectedSignature == null || receivedSignature == null) {
      return false;
    }
    byte[] expectedBytes = expectedSignature.getBytes(StandardCharsets.UTF_8);
    byte[] receivedBytes = receivedSignature.getBytes(StandardCharsets.UTF_8);
    return MessageDigest.isEqual(expectedBytes, receivedBytes);
  }

  public static String computeWebhookSignature(
      String body, Map<String, String> headers, String signatureKey, boolean escapeBody) {
    if (signatureKey == null) {
      return null;
    }
    if (!"1".equals(headers.get("box-signature-version"))) {
      return null;
    }
    if (!"HmacSHA256".equals(headers.get("box-signature-algorithm"))) {
      return null;
    }
    if (!headers.containsKey("box-delivery-timestamp")) {
      return null;
    }

    try {
      String escapedBody = escapeBody ? escapeUnicode(body) : body;
      byte[] encodedSignatureKey = signatureKey.getBytes("UTF-8");
      byte[] encodedBody = escapedBody.getBytes("UTF-8");
      byte[] encodedTimestamp = headers.get("box-delivery-timestamp").getBytes("UTF-8");
      Mac mac = Mac.getInstance("HmacSHA256");
      SecretKeySpec secretKeySpec = new SecretKeySpec(encodedSignatureKey, "HmacSHA256");
      mac.init(secretKeySpec);
      mac.update(encodedBody);
      mac.update(encodedTimestamp);
      byte[] hmacDigest = mac.doFinal();
      return Base64.getEncoder().encodeToString(hmacDigest);
    } catch (Exception e) {
      return null;
    }
  }
}
