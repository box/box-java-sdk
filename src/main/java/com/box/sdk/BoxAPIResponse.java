package com.box.sdk;

import static com.box.sdk.StandardCharsets.UTF_8;
import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static java.lang.String.format;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.ParseException;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Used to read HTTP responses from the Box API.
 *
 * <p>
 * All responses from the REST API are read using this class or one of its subclasses. This class wraps {@link
 * HttpURLConnection} in order to provide a simpler interface that can automatically handle various conditions specific
 * to Box's API. When a response is contructed, it will throw a {@link BoxAPIException} if the response from the API
 * was an error. Therefore every BoxAPIResponse instance is guaranteed to represent a successful response.
 * </p>
 *
 * <p>
 * This class usually isn't instantiated directly, but is instead returned after calling {@link BoxAPIRequest#send}.
 * </p>
 */
public class BoxAPIResponse implements Closeable {
    private static final int BUFFER_SIZE = 8192;
    private static final BoxLogger LOGGER = BoxLogger.defaultLogger();
    private final Map<String, List<String>> headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private final long contentLength;
    private final String contentType;
    private final String requestMethod;
    private final String requestUrl;
    private int responseCode;
    private String bodyString;

    /**
     * The raw InputStream is the stream returned directly from HttpURLConnection.getInputStream(). We need to keep
     * track of this stream in case we need to access it after wrapping it inside another stream.
     */
    private InputStream rawInputStream;

    /**
     * The regular InputStream is the stream that will be returned by getBody(). This stream might be a GZIPInputStream
     * or a ProgressInputStream (or both) that wrap the raw InputStream.
     */
    private InputStream inputStream;

    /**
     * Constructs an empty BoxAPIResponse without an associated HttpURLConnection.
     */
    public BoxAPIResponse() {
        this.contentLength = 0;
        this.contentType = null;
        this.requestMethod = null;
        this.requestUrl = null;
    }

    /**
     * Constructs a BoxAPIResponse with a http response code and response headers.
     *
     * @param responseCode http response code
     * @param headers      map of headers
     */
    public BoxAPIResponse(
        int responseCode, String requestMethod, String requestUrl, Map<String, List<String>> headers
    ) {
        this(responseCode, requestMethod, requestUrl, headers, null, null, 0);
    }

    public BoxAPIResponse(int code,
                          String requestMethod,
                          String requestUrl,
                          Map<String, List<String>> headers,
                          InputStream body,
                          String contentType,
                          long contentLength
    ) {
        this.responseCode = code;
        this.requestMethod = requestMethod;
        this.requestUrl = requestUrl;
        if (headers != null) {
            this.headers.putAll(headers);
        }
        this.rawInputStream = body;
        this.contentType = contentType;
        this.contentLength = contentLength;
        storeBodyResponse(body);
        if (isSuccess(responseCode)) {
            this.logResponse();
        } else {
            this.logErrorResponse(this.responseCode);
            throw new BoxAPIResponseException("The API returned an error code", responseCode, null, headers);
        }
    }

    private void storeBodyResponse(InputStream body) {
        try {
            if (contentType != null && body != null && contentType.contains(APPLICATION_JSON) && body.available() > 0) {
                InputStreamReader reader = new InputStreamReader(this.getBody(), UTF_8);
                StringBuilder builder = new StringBuilder();
                char[] buffer = new char[BUFFER_SIZE];

                int read = reader.read(buffer, 0, BUFFER_SIZE);
                while (read != -1) {
                    builder.append(buffer, 0, read);
                    read = reader.read(buffer, 0, BUFFER_SIZE);
                }
                reader.close();
                this.disconnect();
                bodyString = builder.toString();
                rawInputStream = new ByteArrayInputStream(bodyString.getBytes(UTF_8));
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read body stream", e);
        }
    }

    private static boolean isSuccess(int responseCode) {
        return responseCode >= 200 && responseCode < 400;
    }

    static BoxAPIResponse toBoxResponse(Response response) {
        if (!response.isSuccessful() && !response.isRedirect()) {
            throw new BoxAPIResponseException(
                "The API returned an error code",
                response.code(),
                Optional.ofNullable(response.body()).map(body -> {
                    try {
                        return body.string();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).orElse("Body was null"),
                response.headers().toMultimap()
            );
        }
        ResponseBody responseBody = response.body();
        if (responseBody.contentLength() == 0 || responseBody.contentType() == null) {
            try {
                return new BoxAPIResponse(response.code(),
                        response.request().method(),
                        response.request().url().toString(),
                        response.headers().toMultimap()
                );
            } finally {
                responseBody.close();
            }
        }
        if (responseBody != null && responseBody.contentType() != null) {
            if (responseBody.contentType().toString().contains(APPLICATION_JSON)) {
                String bodyAsString = "";
                try {
                    bodyAsString = responseBody.string();
                    return new BoxJSONResponse(response.code(),
                        response.request().method(),
                        response.request().url().toString(),
                        response.headers().toMultimap(),
                        Json.parse(bodyAsString).asObject()
                    );
                } catch (ParseException e) {
                    throw new BoxAPIException(format("Error parsing JSON:\n%s", bodyAsString), e);
                } catch (IOException e) {
                    throw new RuntimeException("Error getting response to string", e);
                } finally {
                    responseBody.close();
                }
            }
        }
        return new BoxAPIResponse(response.code(),
            response.request().method(),
            response.request().url().toString(),
            response.headers().toMultimap(),
            responseBody.byteStream(),
            Optional.ofNullable(responseBody.contentType()).map(MediaType::toString).orElse(null),
            responseBody.contentLength()
        );
    }

    /**
     * Gets the response code returned by the API.
     *
     * @return the response code returned by the API.
     */
    public int getResponseCode() {
        return this.responseCode;
    }

    /**
     * Gets the length of this response's body as indicated by the "Content-Length" header.
     *
     * @return the length of the response's body.
     */
    public long getContentLength() {
        return this.contentLength;
    }

    /**
     * Gets the value of the given header field.
     *
     * @param fieldName name of the header field.
     * @return value of the header.
     */
    public String getHeaderField(String fieldName) {
        return Optional.ofNullable(this.headers.get(fieldName)).map((l) -> l.get(0)).orElse("");
    }

    /**
     * Gets an InputStream for reading this response's body.
     *
     * @return an InputStream for reading the response's body.
     */
    public InputStream getBody() {
        return this.getBody(null);
    }

    /**
     * Gets an InputStream for reading this response's body which will report its read progress to a ProgressListener.
     *
     * @param listener a listener for monitoring the read progress of the body.
     * @return an InputStream for reading the response's body.
     */
    public InputStream getBody(ProgressListener listener) {
        if (this.inputStream == null) {
            if (listener == null) {
                this.inputStream = this.rawInputStream;
            } else {
                this.inputStream = new ProgressInputStream(this.rawInputStream, listener, this.getContentLength());
            }
        }
        return this.inputStream;
    }

    /**
     * Disconnects this response from the server and frees up any network resources. The body of this response can no
     * longer be read after it has been disconnected.
     */
    public void disconnect() {
        this.close();
    }

    /**
     * @return A Map containg headers on this Box API Response.
     */
    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    @Override
    public String toString() {
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        builder.append("Response")
            .append(lineSeparator)
            .append(this.requestMethod)
            .append(' ')
            .append(this.requestUrl)
            .append(lineSeparator)
            .append(contentType != null ? "Content-Type: " + contentType + lineSeparator : "")
            .append(headers.isEmpty() ? "" : "Headers:" + lineSeparator);
        headers.entrySet()
            .stream()
            .filter(Objects::nonNull)
            .forEach(e -> builder.append(format("%s: [%s]%s", e.getKey().toLowerCase(), e.getValue(), lineSeparator)));

        String bodyString = this.bodyToString();
        if (bodyString != null && !bodyString.equals("")) {
            builder.append("Body:").append(lineSeparator).append(bodyString);
        }

        return builder.toString().trim();
    }

    @Override
    public void close() {
        try {
            if (this.inputStream == null && this.rawInputStream != null) {
                this.rawInputStream.close();
            }
            if (this.inputStream != null) {
                this.inputStream.close();
            }
        } catch (IOException e) {
            throw new BoxAPIException(
                "Couldn't finish closing the connection to the Box API due to a network error or "
                    + "because the stream was already closed.", e
            );
        }
    }

    /**
     * Returns a string representation of this response's body. This method is used when logging this response's body.
     * By default, it returns an empty string (to avoid accidentally logging binary data) unless the response contained
     * an error message.
     *
     * @return a string representation of this response's body.
     */
    protected String bodyToString() {
        return this.bodyString;
    }

    private void logResponse() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(this.toString());
        }
    }

    private void logErrorResponse(int responseCode) {
        if (responseCode < 500 && LOGGER.isWarnEnabled()) {
            LOGGER.warn(this.toString());
        }
        if (responseCode >= 500 && LOGGER.isErrorEnabled()) {
            LOGGER.error(this.toString());
        }
    }

    protected String getRequestMethod() {
        return requestMethod;
    }

    protected String getRequestUrl() {
        return requestUrl;
    }
}
