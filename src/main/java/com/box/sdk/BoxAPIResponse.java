package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.zip.GZIPInputStream;

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
public class BoxAPIResponse {
    private static final BoxLogger LOGGER = BoxLogger.defaultLogger();
    private static final int BUFFER_SIZE = 8192;
    private final Map<String, String> headers;
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
        this.headers = null;
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
    public BoxAPIResponse(int responseCode, String requestMethod, String requestUrl, Map<String, String> headers) {
        this.responseCode = responseCode;
        this.requestMethod = requestMethod;
        this.requestUrl = requestUrl;
        this.headers = headers;
        this.contentLength = 0;
        this.contentType = null;
        this.logResponse();
    }

    public BoxAPIResponse(int code,
                          String requestMethod,
                          String requestUrl,
                          Map<String, String> headers,
                          InputStream body,
                          String contentType,
                          long contentLength
    ) {
        this.responseCode = code;
        this.requestMethod = requestMethod;
        this.requestUrl = requestUrl;
        this.headers = headers;
        this.rawInputStream = body;
        this.contentType = contentType;
        this.contentLength = contentLength;
        this.logResponse();
    }

    private static boolean isSuccess(int responseCode) {
        return responseCode >= 200 && responseCode < 300;
    }

    private static String readErrorStream(InputStream stream) {
        if (stream == null) {
            return null;
        }

        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[BUFFER_SIZE];

        try {
            int read = reader.read(buffer, 0, BUFFER_SIZE);
            while (read != -1) {
                builder.append(buffer, 0, read);
                read = reader.read(buffer, 0, BUFFER_SIZE);
            }

            reader.close();
        } catch (IOException e) {
            return null;
        }

        return builder.toString();
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
        return this.headers.get(fieldName);
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
            String contentEncoding = this.getContentEncoding();
            try {
                if (listener == null) {
                    this.inputStream = this.rawInputStream;
                } else {
                    this.inputStream = new ProgressInputStream(this.rawInputStream, listener,
                        this.getContentLength());
                }

                if (contentEncoding != null && contentEncoding.equalsIgnoreCase("gzip")) {
                    this.inputStream = new GZIPInputStream(this.inputStream);
                }
            } catch (IOException e) {
                throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
            }
        }

        return this.inputStream;
    }

    private String getContentEncoding() {
        return this.headers.get("content-encoding");
    }

    /**
     * Disconnects this response from the server and frees up any network resources. The body of this response can no
     * longer be read after it has been disconnected.
     */
    public void disconnect() {
        try {
            if (this.rawInputStream == null) {
                return;
            }

            // We need to manually read from the raw input stream in case there are any remaining bytes. There's a bug
            // where a wrapping GZIPInputStream may not read to the end of a chunked response, causing Java to not
            // return the connection to the connection pool.
            byte[] buffer = new byte[BUFFER_SIZE];
            int n = this.rawInputStream.read(buffer);
            while (n != -1) {
                n = this.rawInputStream.read(buffer);
            }
            this.rawInputStream.close();

            if (this.inputStream != null) {
                this.inputStream.close();
            }
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't finish closing the connection to the Box API due to a network error or "
                + "because the stream was already closed.", e);
        }
    }

    /**
     * @return A Map containg headers on this Box API Response.
     */
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public String toString() {
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        builder.append("Response");
        builder.append(lineSeparator);
        builder.append(this.requestMethod);
        builder.append(' ');
        builder.append(this.requestUrl);
        builder.append(lineSeparator);
        Optional.ofNullable(headers).orElse(new HashMap<>())
            .entrySet()
            .stream()
            .filter(e -> Optional.ofNullable(e.getValue()).orElse("").trim().length() > 0)
            .forEach(e -> builder.append(String.format("%s: %s%s", e.getKey(), e.getValue(), lineSeparator)));

//        String bodyString = this.bodyToString();
//        if (bodyString != null && !bodyString.equals("")) {
//            builder.append(lineSeparator);
//            builder.append(bodyString);
//        }

        return builder.toString().trim();
    }

    /**
     * Returns a string representation of this response's body. This method is used when logging this response's body.
     * By default, it returns an empty string (to avoid accidentally logging binary data) unless the response contained
     * an error message.
     *
     * @return a string representation of this response's body.
     */
    protected String bodyToString() {
        if (this.bodyString == null && !isSuccess(this.responseCode)) {
            this.bodyString = readErrorStream(this.getErrorStream());
        }

        return this.bodyString;
    }

    /**
     * Returns the response error stream, handling the case when it contains gzipped data.
     *
     * @return gzip decoded (if needed) error stream or null
     */
    private InputStream getErrorStream() {
        //TODO: OkHttp does not have error stream
//        InputStream errorStream = this.connection.getErrorStream();
//        if (errorStream != null) {
//            final String contentEncoding = this.connection.getContentEncoding();
//            if (contentEncoding != null && contentEncoding.equalsIgnoreCase("gzip")) {
//                try {
//                    errorStream = new GZIPInputStream(errorStream);
//                } catch (IOException e) {
//                    // just return the error stream as is
//                }
//            }
//        }
//
//        return errorStream;
        return null;
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
}
