package com.box.sdk;

import static com.box.sdk.internal.utils.CollectionUtils.mapToString;
import static java.lang.String.format;

import com.box.sdk.http.ContentType;
import com.box.sdk.http.HttpHeaders;
import com.box.sdk.http.HttpMethod;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.ParseException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Used to make HTTP requests to the Box API.
 *
 * <p>All requests to the REST API are sent using this class or one of its subclasses. This class wraps {@link
 * HttpURLConnection} in order to provide a simpler interface that can automatically handle various conditions specific
 * to Box's API. Requests will be authenticated using a {@link BoxAPIConnection} (if one is provided), so it isn't
 * necessary to add authorization headers. Requests can also be sent more than once, unlike with HttpURLConnection. If
 * an error occurs while sending a request, it will be automatically retried (with a back off delay) up to the maximum
 * number of times set in the BoxAPIConnection.</p>
 *
 * <p>Specifying a body for a BoxAPIRequest is done differently than it is with HttpURLConnection. Instead of writing to
 * an OutputStream, the request is provided an {@link InputStream} which will be read when the {@link #send} method is
 * called. This makes it easy to retry requests since the stream can automatically reset and reread with each attempt.
 * If the stream cannot be reset, then a new stream will need to be provided before each call to send. There is also a
 * convenience method for specifying the body as a String, which simply wraps the String with an InputStream.</p>
 */
public class BoxAPIRequest {
    private static final BoxLogger LOGGER = BoxLogger.defaultLogger();
    private static final String ERROR_CREATING_REQUEST_BODY = "Error creating request body";
    private static final int BUFFER_SIZE = 8192;
    private final BoxAPIConnection api;
    private final List<RequestHeader> headers;
    private final String method;
    private final URL url;
    private BackoffCounter backoffCounter;
    private int connectTimeout;
    private int readTimeout;
    private InputStream body;
    private long bodyLength;
    private boolean shouldAuthenticate;
    private boolean followRedirects = true;
    private final String mediaType;

    /**
     * Constructs an authenticated BoxAPIRequest using a provided BoxAPIConnection.
     *
     * @param api    an API connection for authenticating the request.
     * @param url    the URL of the request.
     * @param method the HTTP method of the request.
     */
    public BoxAPIRequest(BoxAPIConnection api, URL url, String method) {
        this(api, url, method, ContentType.APPLICATION_FORM_URLENCODED);
    }

    protected BoxAPIRequest(BoxAPIConnection api, URL url, String method, String mediaType) {
        this.api = api;
        this.url = url;
        this.method = method;
        this.mediaType = mediaType;
        this.headers = new ArrayList<>();
        if (api != null) {
            Map<String, String> customHeaders = api.getHeaders();
            if (customHeaders != null) {
                for (String header : customHeaders.keySet()) {
                    this.addHeader(header, customHeaders.get(header));
                }
            }
            this.headers.add(new RequestHeader("X-Box-UA", api.getBoxUAHeader()));
        }
        this.backoffCounter = new BackoffCounter(new Time());
        this.shouldAuthenticate = true;
        if (api != null) {
            this.connectTimeout = api.getConnectTimeout();
            this.readTimeout = api.getReadTimeout();
        } else {
            this.connectTimeout = BoxGlobalSettings.getConnectTimeout();
            this.readTimeout = BoxGlobalSettings.getReadTimeout();
        }

        this.addHeader("Accept-Charset", "utf-8");
    }

    /**
     * Constructs an authenticated BoxAPIRequest using a provided BoxAPIConnection.
     *
     * @param api    an API connection for authenticating the request.
     * @param url    the URL of the request.
     * @param method the HTTP method of the request.
     */
    public BoxAPIRequest(BoxAPIConnection api, URL url, HttpMethod method) {
        this(api, url, method.name());
    }

    /**
     * Constructs an request, using URL and HttpMethod.
     *
     * @param url    the URL of the request.
     * @param method the HTTP method of the request.
     */
    public BoxAPIRequest(URL url, HttpMethod method) {
        this(null, url, method.name());
    }

    /**
     * @param apiException BoxAPIException thrown
     * @return true if the request is one that should be retried, otherwise false
     */
    public static boolean isRequestRetryable(BoxAPIException apiException) {
        // Only requests that failed to send should be retried
        return (Objects.equals(apiException.getMessage(), ERROR_CREATING_REQUEST_BODY));
    }

    /**
     * @param responseCode HTTP error code of the response
     * @param apiException BoxAPIException thrown
     * @return true if the response is one that should be retried, otherwise false
     */
    public static boolean isResponseRetryable(int responseCode, BoxAPIException apiException) {
        if (responseCode >= 500 || responseCode == 429) {
            return true;
        }
        return isClockSkewError(responseCode, apiException);
    }

    private static boolean isClockSkewError(int responseCode, BoxAPIException apiException) {
        String response = apiException.getResponse();
        if (response == null || response.length() == 0) {
            return false;
        }
        String message = apiException.getMessage();
        String errorCode = "";

        try {
            JsonObject responseBody = Json.parse(response).asObject();
            if (responseBody.get("code") != null) {
                errorCode = responseBody.get("code").toString();
            } else if (responseBody.get("error") != null) {
                errorCode = responseBody.get("error").toString();
            }

            return responseCode == 400 && errorCode.contains("invalid_grant") && message.contains("exp");
        } catch (ParseException e) {
            // 400 error which is not a JSON will not trigger a retry
            throw new BoxAPIException("API returned an error", responseCode, response);
        }
    }

    /**
     * Adds an HTTP header to this request.
     *
     * @param key   the header key.
     * @param value the header value.
     */
    public void addHeader(String key, String value) {
        if (key.equals("As-User")) {
            for (int i = 0; i < this.headers.size(); i++) {
                if (this.headers.get(i).getKey().equals("As-User")) {
                    this.headers.remove(i);
                }
            }
        }
        if (key.equals("X-Box-UA")) {
            throw new IllegalArgumentException("Altering the X-Box-UA header is not permitted");
        }
        this.headers.add(new RequestHeader(key, value));
    }

    /**
     * Gets the connect timeout for the request.
     *
     * @return the request connection timeout.
     */
    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    /**
     * Sets a Connect timeout for this request in milliseconds.
     *
     * @param timeout the timeout in milliseconds.
     */
    public void setConnectTimeout(int timeout) {
        this.connectTimeout = timeout;
    }

    /**
     * Gets the read timeout for the request.
     *
     * @return the request's read timeout.
     */
    public int getReadTimeout() {
        return this.readTimeout;
    }

    /**
     * Sets a read timeout for this request in milliseconds.
     *
     * @param timeout the timeout in milliseconds.
     */
    public void setReadTimeout(int timeout) {
        this.readTimeout = timeout;
    }

    /**
     * Sets whether or not to follow redirects (i.e. Location header)
     *
     * @param followRedirects true to follow, false to not follow
     */
    public void setFollowRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects;
    }

    /**
     * Gets the stream containing contents of this request's body.
     *
     * <p>Note that any bytes that read from the returned stream won't be sent unless the stream is reset back to its
     * initial position.</p>
     *
     * @return an InputStream containing the contents of this request's body.
     */
    public InputStream getBody() {
        return this.body;
    }

    /**
     * Sets the request body to the contents of an InputStream.
     *
     * <p>The stream must support the {@link InputStream#reset} method if auto-retry is used or if the request needs to
     * be resent. Otherwise, the body must be manually set before each call to {@link #send}.</p>
     *
     * @param stream an InputStream containing the contents of the body.
     */
    public void setBody(InputStream stream) {
        this.body = stream;
    }

    /**
     * Sets the request body to the contents of a String.
     *
     * <p>If the contents of the body are large, then it may be more efficient to use an {@link InputStream} instead of
     * a String. Using a String requires that the entire body be in memory before sending the request.</p>
     *
     * @param body a String containing the contents of the body.
     */
    public void setBody(String body) {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        this.bodyLength = bytes.length;
        this.body = new ByteArrayInputStream(bytes);
    }

    /**
     * Sets the request body to the contents of an InputStream.
     *
     * <p>Providing the length of the InputStream allows for the progress of the request to be monitored when calling
     * {@link #send(ProgressListener)}.</p>
     *
     * <p> See {@link #setBody(InputStream)} for more information on setting the body of the request.</p>
     *
     * @param stream an InputStream containing the contents of the body.
     * @param length the expected length of the stream.
     */
    public void setBody(InputStream stream, long length) {
        this.bodyLength = length;
        this.body = stream;
    }

    /**
     * Gets the URL from the request.
     *
     * @return a URL containing the URL of the request.
     */
    public URL getUrl() {
        return this.url;
    }

    /**
     * Gets the http method from the request.
     *
     * @return http method
     */
    public String getMethod() {
        return this.method;
    }

    /**
     * Get headers as list of RequestHeader objects.
     *
     * @return headers as list of RequestHeader objects
     */
    List<RequestHeader> getHeaders() {
        return this.headers;
    }

    /**
     * Sends this request and returns a BoxAPIResponse containing the server's response.
     *
     * <p>The type of the returned BoxAPIResponse will be based on the content type returned by the server, allowing it
     * to be cast to a more specific type. For example, if it's known that the API call will return a JSON response,
     * then it can be cast to a {@link BoxJSONResponse} like so:</p>
     *
     * <pre>BoxJSONResponse response = (BoxJSONResponse) request.sendWithoutRetry();</pre>
     *
     * @return a {@link BoxAPIResponse} containing the server's response.
     * @throws BoxAPIException if the server returns an error code or if a network error occurs.
     */
    public BoxAPIResponse sendWithoutRetry() {
        return this.trySend(null);
    }

    /**
     * Sends this request and returns a BoxAPIResponse containing the server's response.
     *
     * <p>The type of the returned BoxAPIResponse will be based on the content type returned by the server, allowing it
     * to be cast to a more specific type. For example, if it's known that the API call will return a JSON response,
     * then it can be cast to a {@link BoxJSONResponse} like so:</p>
     *
     * <pre>BoxJSONResponse response = (BoxJSONResponse) request.send();</pre>
     *
     * <p>If the server returns an error code or if a network error occurs, then the request will be automatically
     * retried. If the maximum number of retries is reached and an error still occurs, then a {@link BoxAPIException}
     * will be thrown.</p>
     *
     * <p> See {@link #send} for more information on sending requests.</p>
     *
     * @return a {@link BoxAPIResponse} containing the server's response.
     * @throws BoxAPIException if the server returns an error code or if a network error occurs.
     */
    public BoxAPIResponse send() {
        return this.send(null);
    }

    /**
     * Sends this request while monitoring its progress and returns a BoxAPIResponse containing the server's response.
     *
     * <p>The type of the returned BoxAPIResponse will be based on the content type returned by the server, allowing it
     * to be cast to a more specific type. For example, if it's known that the API call will return a JSON response,
     * then it can be cast to a {@link BoxJSONResponse} like so:</p>
     *
     * <p>If the server returns an error code or if a network error occurs, then the request will be automatically
     * retried. If the maximum number of retries is reached and an error still occurs, then a {@link BoxAPIException}
     * will be thrown.</p>
     *
     * <p>A ProgressListener is generally only useful when the size of the request is known beforehand. If the size is
     * unknown, then the ProgressListener will be updated for each byte sent, but the total number of bytes will be
     * reported as 0.</p>
     *
     * <p> See {@link #send} for more information on sending requests.</p>
     *
     * @param listener a listener for monitoring the progress of the request.
     * @return a {@link BoxAPIResponse} containing the server's response.
     * @throws BoxAPIException if the server returns an error code or if a network error occurs.
     */
    public BoxAPIResponse send(ProgressListener listener) {
        if (this.api == null) {
            this.backoffCounter.reset(BoxGlobalSettings.getMaxRetryAttempts() + 1);
        } else {
            this.backoffCounter.reset(this.api.getMaxRetryAttempts() + 1);
        }

        while (this.backoffCounter.getAttemptsRemaining() > 0) {
            try {
                return this.trySend(listener);
            } catch (BoxAPIException apiException) {
                if (!this.backoffCounter.decrement()
                    || (!isRequestRetryable(apiException)
                    && !isResponseRetryable(apiException.getResponseCode(), apiException))) {
                    throw apiException;
                }

                LOGGER.warn(
                    format("Retrying request due to transient error status=%d body=%s headers=%s",
                        apiException.getResponseCode(),
                        apiException.getResponse(),
                        mapToString(apiException.getHeaders()))
                );

                try {
                    this.resetBody();
                } catch (IOException ioException) {
                    throw apiException;
                }

                try {
                    List<String> retryAfterHeader = apiException.getHeaders().get("Retry-After");
                    if (retryAfterHeader == null) {
                        this.backoffCounter.waitBackoff();
                    } else {
                        int retryAfterDelay = Integer.parseInt(retryAfterHeader.get(0)) * 1000;
                        this.backoffCounter.waitBackoff(retryAfterDelay);
                    }
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                    throw apiException;
                }
            }
        }

        throw new RuntimeException();
    }

    /**
     * Disables adding authentication header to request.
     * Useful when you want to add your own authentication method.
     * Default value is `true` and SKD will add authenticaton header to request.
     *
     * @param shouldAuthenticate use `false` to disable authentication.
     */
    public void shouldAuthenticate(boolean shouldAuthenticate) {
        this.shouldAuthenticate = shouldAuthenticate;
    }

    /**
     * Sends a request to upload a file part and returns a BoxFileUploadSessionPart containing information
     * about the upload part. This method is separate from send() because it has custom retry logic.
     *
     * <p>If the server returns an error code or if a network error occurs, then the request will be automatically
     * retried. If the maximum number of retries is reached and an error still occurs, then a {@link BoxAPIException}
     * will be thrown.</p>
     *
     * @param session The BoxFileUploadSession uploading the part
     * @param offset  Offset of the part being uploaded
     * @return A {@link BoxFileUploadSessionPart} part that has been uploaded.
     * @throws BoxAPIException if the server returns an error code or if a network error occurs.
     */
    BoxFileUploadSessionPart sendForUploadPart(BoxFileUploadSession session, long offset) {
        if (this.api == null) {
            this.backoffCounter.reset(BoxGlobalSettings.getMaxRetryAttempts() + 1);
        } else {
            this.backoffCounter.reset(this.api.getMaxRetryAttempts() + 1);
        }

        while (this.backoffCounter.getAttemptsRemaining() > 0) {
            try (BoxJSONResponse response = (BoxJSONResponse) this.trySend(null)) {
                // upload sends binary data but response is JSON
                JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
                return new BoxFileUploadSessionPart((JsonObject) jsonObject.get("part"));
            } catch (BoxAPIException apiException) {
                if (!this.backoffCounter.decrement()
                    || (!isRequestRetryable(apiException)
                    && !isResponseRetryable(apiException.getResponseCode(), apiException))) {
                    throw apiException;
                }
                if (apiException.getResponseCode() == 500) {
                    try {
                        Iterable<BoxFileUploadSessionPart> parts = session.listParts();
                        for (BoxFileUploadSessionPart part : parts) {
                            if (part.getOffset() == offset) {
                                return part;
                            }
                        }
                    } catch (BoxAPIException e) {
                        // ignoring exception as we are retrying
                    }
                }
                LOGGER.warn(format(
                    "Retrying request due to transient error status=%d body=%s",
                    apiException.getResponseCode(),
                    apiException.getResponse()
                ));

                try {
                    this.resetBody();
                } catch (IOException ioException) {
                    throw apiException;
                }

                try {
                    this.backoffCounter.waitBackoff();
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                    throw apiException;
                }
            }
        }

        throw new RuntimeException();
    }

    /**
     * Returns a String containing the URL, HTTP method, headers and body of this request.
     *
     * @return a String containing information about this request.
     */
    @Override
    public String toString() {
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        builder.append("Request");
        builder.append(lineSeparator);
        builder.append(this.method);
        builder.append(' ');
        builder.append(this.url.toString());
        builder.append(lineSeparator);

        String bodyString = this.bodyToString();
        if (bodyString != null) {
            builder.append(lineSeparator);
            builder.append(bodyString);
        }

        return builder.toString().trim();
    }

    /**
     * Returns a String representation of this request's body used in {@link #toString}. This method returns
     * null by default.
     *
     * <p>A subclass may want override this method if the body can be converted to a String for logging or debugging
     * purposes.</p>
     *
     * @return a String representation of this request's body.
     */
    protected String bodyToString() {
        return null;
    }

    private void writeWithBuffer(OutputStream output, ProgressListener listener) {
        try {
            OutputStream finalOutput = output;
            if (listener != null) {
                finalOutput = new ProgressOutputStream(output, listener, this.bodyLength);
            }
            byte[] buffer = new byte[BUFFER_SIZE];
            int b = this.body.read(buffer);
            while (b != -1) {
                finalOutput.write(buffer, 0, b);
                b = this.body.read(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writting body", e);
        }
    }

    /**
     * Resets the InputStream containing this request's body.
     *
     * <p>This method will be called before each attempt to resend the request, giving subclasses an opportunity to
     * reset any streams that need to be read when sending the body.</p>
     *
     * @throws IOException if the stream cannot be reset.
     */
    protected void resetBody() throws IOException {
        if (this.body != null) {
            this.body.reset();
        }
    }

    void setBackoffCounter(BackoffCounter counter) {
        this.backoffCounter = counter;
    }

    private BoxAPIResponse trySend(ProgressListener listener) {
        if (this.api != null) {
            RequestInterceptor interceptor = this.api.getRequestInterceptor();
            if (interceptor != null) {
                BoxAPIResponse response = interceptor.onRequest(this);
                if (response != null) {
                    return response;
                }
            }
        }

        Request.Builder requestBuilder = new Request.Builder().url(getUrl());

        if (this.shouldAuthenticate) {
            requestBuilder.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + this.api.lockAccessToken());
        }
        requestBuilder.addHeader("User-Agent", this.api.getUserAgent());
        requestBuilder.addHeader("X-Box-UA", this.api.getBoxUAHeader());
        headers.forEach(h -> {
            requestBuilder.removeHeader(h.getKey());
            requestBuilder.addHeader(h.getKey(), h.getValue());
        });

        if (this.api instanceof SharedLinkAPIConnection) {
            SharedLinkAPIConnection sharedItemAPI = (SharedLinkAPIConnection) this.api;
            String sharedLink = sharedItemAPI.getSharedLink();
            String boxAPIValue = "shared_link=" + sharedLink;
            String sharedLinkPassword = sharedItemAPI.getSharedLinkPassword();
            if (sharedLinkPassword != null) {
                boxAPIValue += "&shared_link_password=" + sharedLinkPassword;
            }
            requestBuilder.addHeader("BoxApi", boxAPIValue);
        }

        try {
            long start = System.currentTimeMillis();
            writeMethodWithBody(requestBuilder, listener);
            Request request = requestBuilder.build();
            Response response;
            if (this.followRedirects) {
                response = api.execute(request);
            } else {
                response = api.executeWithoutRedirect(request);
            }
            logDebug(format("[trySend] connection.connect() took %dms%n", (System.currentTimeMillis() - start)));

            BoxAPIResponse result = BoxAPIResponse.toBoxResponse(response);
            this.logRequest();
            long getResponseStart = System.currentTimeMillis();
            logDebug(format(
                "[trySend] Get Response (read network) took %dms%n", System.currentTimeMillis() - getResponseStart
            ));
            return result;

        } finally {
            if (this.shouldAuthenticate) {
                this.api.unlockAccessToken();
            }
        }

    }

    protected void writeMethodWithBody(Request.Builder requestBuilder, ProgressListener listener) {
        ByteArrayOutputStream bodyBytes = new ByteArrayOutputStream();
        if (body != null) {
            long writeStart = System.currentTimeMillis();
            writeWithBuffer(bodyBytes, listener);
            logDebug(format("[trySend] Body write took %dms%n", (System.currentTimeMillis() - writeStart)));
        }
        if (method.equals("GET")) {
            requestBuilder.get();
        }
        if (method.equals("DELETE")) {
            requestBuilder.delete();
        }
        if (method.equals("OPTIONS")) {
            if (body == null) {
                requestBuilder.method("OPTIONS", null);
            } else {
                requestBuilder.method("OPTIONS", RequestBody.create(bodyBytes.toByteArray(), mediaType()));
            }
        }
        if (method.equals("POST")) {
            requestBuilder.post(RequestBody.create(bodyBytes.toByteArray(), mediaType()));
        }
        if (method.equals("PUT")) {
            requestBuilder.put(RequestBody.create(bodyBytes.toByteArray(), mediaType()));
        }
    }

    private void logDebug(String message) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(message);
        }
    }

    private void logRequest() {
        logDebug(this.toString());
    }

    /**
     * Class for mapping a request header and value.
     */
    public static final class RequestHeader {
        private final String key;
        private final String value;

        /**
         * Construct a request header from header key and value.
         *
         * @param key   header name
         * @param value header value
         */
        public RequestHeader(String key, String value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Get header key.
         *
         * @return http header name
         */
        public String getKey() {
            return this.key;
        }

        /**
         * Get header value.
         *
         * @return http header value
         */
        public String getValue() {
            return this.value;
        }
    }

    protected MediaType mediaType() {
        return MediaType.parse(mediaType);
    }
}
