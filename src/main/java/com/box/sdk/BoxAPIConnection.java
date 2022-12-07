package com.box.sdk;

import static java.lang.String.format;
import static java.lang.String.join;
import static java.util.Collections.singletonList;
import static okhttp3.ConnectionSpec.MODERN_TLS;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Represents an authenticated connection to the Box API.
 *
 * <p>This class handles storing authentication information, automatic token refresh, and rate-limiting. It can also be
 * used to configure the Box API endpoint URL in order to hit a different version of the API. Multiple instances of
 * BoxAPIConnection may be created to support multi-user login.</p>
 */
public class BoxAPIConnection {

    /**
     * Used as a marker to setup connection to use default HostnameVerifier
     * Example:<pre>{@code
     * BoxApiConnection api = new BoxApiConnection(...);
     * HostnameVerifier myHostnameVerifier = ...
     * api.configureSslCertificatesValidation(DEFAULT_TRUST_MANAGER, myHostnameVerifier);
     * }</pre>
     */
    public static final X509TrustManager DEFAULT_TRUST_MANAGER = null;
    /**
     * Used as a marker to setup connection to use default HostnameVerifier
     * Example:<pre>{@code
     * BoxApiConnection api = new BoxApiConnection(...);
     * X509TrustManager myTrustManager = ...
     * api.configureSslCertificatesValidation(myTrustManager, DEFAULT_HOSTNAME_VERIFIER);
     * }</pre>
     */
    public static final HostnameVerifier DEFAULT_HOSTNAME_VERIFIER = null;

    /**
     * The default maximum number of times an API request will be retried after an error response
     * is received.
     */
    public static final int DEFAULT_MAX_RETRIES = 5;
    /**
     * Default authorization URL
     */
    protected static final String DEFAULT_BASE_AUTHORIZATION_URL = "https://account.box.com/api/";
    static final String AS_USER_HEADER = "As-User";

    private static final String API_VERSION = "2.0";
    private static final String OAUTH_SUFFIX = "oauth2/authorize";
    private static final String TOKEN_URL_SUFFIX = "oauth2/token";
    private static final String REVOKE_URL_SUFFIX = "oauth2/revoke";
    private static final String DEFAULT_BASE_URL = "https://api.box.com/";
    private static final String DEFAULT_BASE_UPLOAD_URL = "https://upload.box.com/api/";
    private static final String DEFAULT_BASE_APP_URL = "https://app.box.com";

    private static final String BOX_NOTIFICATIONS_HEADER = "Box-Notifications";

    private static final String JAVA_VERSION = System.getProperty("java.version");
    private static final String SDK_VERSION = "3.8.0";

    /**
     * The amount of buffer time, in milliseconds, to use when determining if an access token should be refreshed. For
     * example, if REFRESH_EPSILON = 60000 and the access token expires in less than one minute, it will be refreshed.
     */
    private static final long REFRESH_EPSILON = 60000;

    private final String clientID;
    private final String clientSecret;
    private final ReadWriteLock refreshLock;
    private X509TrustManager trustManager;
    private HostnameVerifier hostnameVerifier;

    // These volatile fields are used when determining if the access token needs to be refreshed. Since they are used in
    // the double-checked lock in getAccessToken(), they must be atomic.
    private volatile long lastRefresh;
    private volatile long expires;

    private Proxy proxy;
    private String proxyUsername;
    private String proxyPassword;

    private String userAgent;
    private String accessToken;
    private String refreshToken;
    private String tokenURL;
    private String revokeURL;
    private String baseURL;
    private String baseUploadURL;
    private String baseAppURL;
    private String baseAuthorizationURL;
    private boolean autoRefresh;
    private int maxRetryAttempts;
    private int connectTimeout;
    private int readTimeout;
    private final List<BoxAPIConnectionListener> listeners;
    private RequestInterceptor interceptor;
    private final Map<String, String> customHeaders;

    private OkHttpClient httpClient;
    private OkHttpClient noRedirectsHttpClient;
    private Authenticator authenticator;

    /**
     * Constructs a new BoxAPIConnection that authenticates with a developer or access token.
     *
     * @param accessToken a developer or access token to use for authenticating with the API.
     */
    public BoxAPIConnection(String accessToken) {
        this(null, null, accessToken, null);
    }

    /**
     * Constructs a new BoxAPIConnection with an access token that can be refreshed.
     *
     * @param clientID     the client ID to use when refreshing the access token.
     * @param clientSecret the client secret to use when refreshing the access token.
     * @param accessToken  an initial access token to use for authenticating with the API.
     * @param refreshToken an initial refresh token to use when refreshing the access token.
     */
    public BoxAPIConnection(String clientID, String clientSecret, String accessToken, String refreshToken) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.baseURL = fixBaseUrl(DEFAULT_BASE_URL);
        this.baseUploadURL = fixBaseUrl(DEFAULT_BASE_UPLOAD_URL);
        this.baseAppURL = DEFAULT_BASE_APP_URL;
        this.baseAuthorizationURL = DEFAULT_BASE_AUTHORIZATION_URL;
        this.autoRefresh = true;
        this.maxRetryAttempts = BoxGlobalSettings.getMaxRetryAttempts();
        this.connectTimeout = BoxGlobalSettings.getConnectTimeout();
        this.readTimeout = BoxGlobalSettings.getReadTimeout();
        this.refreshLock = new ReentrantReadWriteLock();
        this.userAgent = "Box Java SDK v" + SDK_VERSION + " (Java " + JAVA_VERSION + ")";
        this.listeners = new ArrayList<>();
        this.customHeaders = new HashMap<>();

        buildHttpClients();
    }

    /**
     * Constructs a new BoxAPIConnection with an auth code that was obtained from the first half of OAuth.
     *
     * @param clientID     the client ID to use when exchanging the auth code for an access token.
     * @param clientSecret the client secret to use when exchanging the auth code for an access token.
     * @param authCode     an auth code obtained from the first half of the OAuth process.
     */
    public BoxAPIConnection(String clientID, String clientSecret, String authCode) {
        this(clientID, clientSecret, null, null);
        this.authenticate(authCode);
    }

    /**
     * Constructs a new BoxAPIConnection.
     *
     * @param clientID     the client ID to use when exchanging the auth code for an access token.
     * @param clientSecret the client secret to use when exchanging the auth code for an access token.
     */
    public BoxAPIConnection(String clientID, String clientSecret) {
        this(clientID, clientSecret, null, null);
    }

    /**
     * Constructs a new BoxAPIConnection levaraging BoxConfig.
     *
     * @param boxConfig BoxConfig file, which should have clientId and clientSecret
     */
    public BoxAPIConnection(BoxConfig boxConfig) {
        this(boxConfig.getClientId(), boxConfig.getClientSecret(), null, null);
    }

    private void buildHttpClients() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        if (trustManager != null) {
            try {
                SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, new TrustManager[]{trustManager}, new java.security.SecureRandom());
                httpClientBuilder.sslSocketFactory(sslContext.getSocketFactory(), trustManager);
            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                throw new RuntimeException(e);
            }
        }

        OkHttpClient.Builder builder = httpClientBuilder
            .followSslRedirects(true)
            .followRedirects(true)
            .connectTimeout(Duration.ofMillis(connectTimeout))
            .readTimeout(Duration.ofMillis(readTimeout))
            .connectionSpecs(singletonList(MODERN_TLS));

        if (hostnameVerifier != null) {
            httpClientBuilder.hostnameVerifier(hostnameVerifier);
        }

        if (proxy != null) {
            builder.proxy(proxy);
            if (proxyUsername != null && proxyPassword != null) {
                builder.proxyAuthenticator((route, response) -> {
                    String credential = Credentials.basic(proxyUsername, proxyPassword);
                    return response.request().newBuilder()
                        .header("Proxy-Authorization", credential)
                        .build();
                });
            }
            if (this.authenticator != null) {
                builder.proxyAuthenticator(authenticator);
            }
        }
        builder = modifyHttpClientBuilder(builder);

        this.httpClient = builder.build();
        this.noRedirectsHttpClient = new OkHttpClient.Builder(httpClient)
            .followSslRedirects(false)
            .followRedirects(false)
            .build();
    }

    protected OkHttpClient.Builder modifyHttpClientBuilder(OkHttpClient.Builder httpClientBuilder) {
        return httpClientBuilder;
    }

    /**
     * Sets a proxy authenticator that will be used when proxy requires authentication.
     * If you use {@link BoxAPIConnection#setProxyBasicAuthentication(String, String)} it adds an authenticator
     * that performs Basic authorization. By calling this method you can override this behaviour.
     * You do not need to call {@link BoxAPIConnection#setProxyBasicAuthentication(String, String)}
     * in order to set custom authenticator.
     *
     * @param authenticator Custom authenticator that will be called when proxy asks for authorization.
     */
    public void setProxyAuthenticator(Authenticator authenticator) {
        this.authenticator = authenticator;
        buildHttpClients();
    }

    /**
     * Restores a BoxAPIConnection from a saved state.
     *
     * @param clientID     the client ID to use with the connection.
     * @param clientSecret the client secret to use with the connection.
     * @param state        the saved state that was created with {@link #save}.
     * @return a restored API connection.
     * @see #save
     */
    public static BoxAPIConnection restore(String clientID, String clientSecret, String state) {
        BoxAPIConnection api = new BoxAPIConnection(clientID, clientSecret);
        api.restore(state);
        return api;
    }

    /**
     * Returns the default authorization URL which is used to perform the authorization_code based OAuth2 flow.
     * If custom Authorization URL is needed use instance method {@link BoxAPIConnection#getAuthorizationURL}
     *
     * @param clientID    the client ID to use with the connection.
     * @param redirectUri the URL to which Box redirects the browser when authentication completes.
     * @param state       the text string that you choose.
     *                    Box sends the same string to your redirect URL when authentication is complete.
     * @param scopes      this optional parameter identifies the Box scopes available
     *                    to the application once it's authenticated.
     * @return the authorization URL
     */
    public static URL getAuthorizationURL(String clientID, URI redirectUri, String state, List<String> scopes) {
        return createFullAuthorizationUrl(DEFAULT_BASE_AUTHORIZATION_URL, clientID, redirectUri, state, scopes);
    }

    private static URL createFullAuthorizationUrl(
        String authorizationUrl, String clientID, URI redirectUri, String state, List<String> scopes
    ) {
        URLTemplate template = new URLTemplate(authorizationUrl + OAUTH_SUFFIX);
        QueryStringBuilder queryBuilder = new QueryStringBuilder().appendParam("client_id", clientID)
            .appendParam("response_type", "code")
            .appendParam("redirect_uri", redirectUri.toString())
            .appendParam("state", state);

        if (scopes != null && !scopes.isEmpty()) {
            queryBuilder.appendParam("scope", join(" ", scopes));
        }

        return template.buildWithQuery("", queryBuilder.toString());
    }

    /**
     * Authenticates the API connection by obtaining access and refresh tokens using the auth code that was obtained
     * from the first half of OAuth.
     *
     * @param authCode the auth code obtained from the first half of the OAuth process.
     */
    public void authenticate(String authCode) {
        URL url;
        try {
            url = new URL(this.getTokenURL());
        } catch (MalformedURLException e) {
            assert false : "An invalid token URL indicates a bug in the SDK.";
            throw new RuntimeException("An invalid token URL indicates a bug in the SDK.", e);
        }

        String urlParameters = format("grant_type=authorization_code&code=%s&client_id=%s&client_secret=%s",
            authCode, this.clientID, this.clientSecret);

        BoxAPIRequest request = new BoxAPIRequest(this, url, "POST");
        request.shouldAuthenticate(false);
        request.setBody(urlParameters);

        // authentication uses form url encoded but response is JSON
        try (BoxJSONResponse response = (BoxJSONResponse) request.send()) {
            String json = response.getJSON();

            JsonObject jsonObject = Json.parse(json).asObject();
            this.accessToken = jsonObject.get("access_token").asString();
            this.refreshToken = jsonObject.get("refresh_token").asString();
            this.lastRefresh = System.currentTimeMillis();
            this.expires = jsonObject.get("expires_in").asLong() * 1000;
        }
    }

    /**
     * Gets the client ID.
     *
     * @return the client ID.
     */
    public String getClientID() {
        return this.clientID;
    }

    /**
     * Gets the client secret.
     *
     * @return the client secret.
     */
    public String getClientSecret() {
        return this.clientSecret;
    }

    /**
     * Gets the amount of time for which this connection's access token is valid.
     *
     * @return the amount of time in milliseconds.
     */
    public long getExpires() {
        return this.expires;
    }

    /**
     * Sets the amount of time for which this connection's access token is valid before it must be refreshed.
     *
     * @param milliseconds the number of milliseconds for which the access token is valid.
     */
    public void setExpires(long milliseconds) {
        this.expires = milliseconds;
    }

    /**
     * Gets the token URL that's used to request access tokens.  The default value is
     * "https://www.box.com/api/oauth2/token".
     * The URL is created from {@link BoxAPIConnection#baseURL} and {@link BoxAPIConnection#TOKEN_URL_SUFFIX}.
     *
     * @return the token URL.
     */
    public String getTokenURL() {
        if (this.tokenURL != null) {
            return this.tokenURL;
        } else {
            return this.baseURL + TOKEN_URL_SUFFIX;
        }
    }

    /**
     * Returns the URL used for token revocation.
     * The URL is created from {@link BoxAPIConnection#baseURL} and {@link BoxAPIConnection#REVOKE_URL_SUFFIX}.
     *
     * @return The url used for token revocation.
     */
    public String getRevokeURL() {
        if (this.revokeURL != null) {
            return this.revokeURL;
        } else {
            return this.baseURL + REVOKE_URL_SUFFIX;
        }
    }

    /**
     * Gets the base URL that's used when sending requests to the Box API.
     * The URL is created from {@link BoxAPIConnection#baseURL} and {@link BoxAPIConnection#API_VERSION}.
     * The default value is "https://api.box.com/2.0/".
     *
     * @return the base URL.
     */
    public String getBaseURL() {
        return this.baseURL + API_VERSION + "/";
    }

    /**
     * Sets the base URL to be used when sending requests to the Box API. For example, the default base URL is
     * "https://api.box.com/". This method changes how {@link BoxAPIConnection#getRevokeURL()}
     * and {@link BoxAPIConnection#getTokenURL()} are constructed.
     *
     * @param baseURL a base URL
     */
    public void setBaseURL(String baseURL) {
        this.baseURL = fixBaseUrl(baseURL);
    }

    /**
     * Gets the base upload URL that's used when performing file uploads to Box.
     * The URL is created from {@link BoxAPIConnection#baseUploadURL} and {@link BoxAPIConnection#API_VERSION}.
     *
     * @return the base upload URL.
     */
    public String getBaseUploadURL() {
        return this.baseUploadURL + API_VERSION + "/";
    }

    /**
     * Sets the base upload URL to be used when performing file uploads to Box.
     *
     * @param baseUploadURL a base upload URL.
     */
    public void setBaseUploadURL(String baseUploadURL) {
        this.baseUploadURL = fixBaseUrl(baseUploadURL);
    }

    /**
     * Returns the authorization URL which is used to perform the authorization_code based OAuth2 flow.
     * The URL is created from {@link BoxAPIConnection#baseAuthorizationURL} and {@link BoxAPIConnection#OAUTH_SUFFIX}.
     *
     * @param redirectUri the URL to which Box redirects the browser when authentication completes.
     * @param state       the text string that you choose.
     *                    Box sends the same string to your redirect URL when authentication is complete.
     * @param scopes      this optional parameter identifies the Box scopes available
     *                    to the application once it's authenticated.
     * @return the authorization URL
     */
    public URL getAuthorizationURL(URI redirectUri, String state, List<String> scopes) {
        return createFullAuthorizationUrl(
            this.baseAuthorizationURL + OAUTH_SUFFIX, this.clientID, redirectUri, state, scopes
        );
    }

    /**
     * Sets authorization base URL which is used to perform the authorization_code based OAuth2 flow.
     *
     * @param baseAuthorizationURL Authorization URL. Default value is https://account.box.com/api/.
     */
    public void setBaseAuthorizationURL(String baseAuthorizationURL) {
        this.baseAuthorizationURL = fixBaseUrl(baseAuthorizationURL);
    }

    /**
     * Gets the user agent that's used when sending requests to the Box API.
     *
     * @return the user agent.
     */
    public String getUserAgent() {
        return this.userAgent;
    }

    /**
     * Sets the user agent to be used when sending requests to the Box API.
     *
     * @param userAgent the user agent.
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * Gets the base App url. Used for e.g. file requests.
     *
     * @return the base App Url.
     */
    public String getBaseAppUrl() {
        return this.baseAppURL;
    }

    /**
     * Sets the base App url. Used for e.g. file requests.
     *
     * @param baseAppURL a base App Url.
     */
    public void setBaseAppUrl(String baseAppURL) {
        this.baseAppURL = baseAppURL;
    }

    /**
     * Gets an access token that can be used to authenticate an API request. This method will automatically refresh the
     * access token if it has expired since the last call to <code>getAccessToken()</code>.
     *
     * @return a valid access token that can be used to authenticate an API request.
     */
    public String getAccessToken() {
        if (this.autoRefresh && this.canRefresh() && this.needsRefresh()) {
            this.refreshLock.writeLock().lock();
            try {
                if (this.needsRefresh()) {
                    this.refresh();
                }
            } finally {
                this.refreshLock.writeLock().unlock();
            }
        }

        return this.accessToken;
    }

    /**
     * Sets the access token to use when authenticating API requests.
     *
     * @param accessToken a valid access token to use when authenticating API requests.
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Gets the refresh lock to be used when refreshing an access token.
     *
     * @return the refresh lock.
     */
    protected ReadWriteLock getRefreshLock() {
        return this.refreshLock;
    }

    /**
     * Gets a refresh token that can be used to refresh an access token.
     *
     * @return a valid refresh token.
     */
    public String getRefreshToken() {
        return this.refreshToken;
    }

    /**
     * Sets the refresh token to use when refreshing an access token.
     *
     * @param refreshToken a valid refresh token.
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * Gets the last time that the access token was refreshed.
     *
     * @return the last refresh time in milliseconds.
     */
    public long getLastRefresh() {
        return this.lastRefresh;
    }

    /**
     * Sets the last time that the access token was refreshed.
     *
     * <p>This value is used when determining if an access token needs to be auto-refreshed. If the amount of time since
     * the last refresh exceeds the access token's expiration time, then the access token will be refreshed.</p>
     *
     * @param lastRefresh the new last refresh time in milliseconds.
     */
    public void setLastRefresh(long lastRefresh) {
        this.lastRefresh = lastRefresh;
    }

    /**
     * Gets whether or not automatic refreshing of this connection's access token is enabled. Defaults to true.
     *
     * @return true if auto token refresh is enabled; otherwise false.
     */
    public boolean getAutoRefresh() {
        return this.autoRefresh;
    }

    /**
     * Enables or disables automatic refreshing of this connection's access token. Defaults to true.
     *
     * @param autoRefresh true to enable auto token refresh; otherwise false.
     */
    public void setAutoRefresh(boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }


    /**
     * Gets the maximum number of times an API request will be retried after an error response
     * is received.
     *
     * @return the maximum number of request attempts.
     */
    public int getMaxRetryAttempts() {
        return this.maxRetryAttempts;
    }

    /**
     * Sets the maximum number of times an API request will be retried after an error response
     * is received.
     *
     * @param attempts the maximum number of request attempts.
     */
    public void setMaxRetryAttempts(int attempts) {
        this.maxRetryAttempts = attempts;
    }

    /**
     * Gets the connect timeout for this connection in milliseconds.
     *
     * @return the number of milliseconds to connect before timing out.
     */
    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    /**
     * Sets the connect timeout for this connection.
     *
     * @param connectTimeout The number of milliseconds to wait for the connection to be established.
     */
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        buildHttpClients();
    }

    /**
     * Gets the read timeout for this connection in milliseconds.
     *
     * @return the number of milliseconds to wait for bytes to be read before timing out.
     */
    public int getReadTimeout() {
        return this.readTimeout;
    }

    /**
     * Sets the read timeout for this connection.
     *
     * @param readTimeout The number of milliseconds to wait for bytes to be read.
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
        buildHttpClients();
    }

    /**
     * Gets the proxy value to use for API calls to Box.
     *
     * @return the current proxy.
     */
    public Proxy getProxy() {
        return this.proxy;
    }

    /**
     * Sets the proxy to use for API calls to Box.
     *
     * @param proxy the proxy to use for API calls to Box.
     */
    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
        buildHttpClients();
    }

    /**
     * Gets the username to use for a proxy that requires basic auth.
     *
     * @return the username to use for a proxy that requires basic auth.
     */
    public String getProxyUsername() {
        return this.proxyUsername;
    }

    /**
     * Sets the username to use for a proxy that requires basic auth.
     *
     * @param proxyUsername the username to use for a proxy that requires basic auth.
     * @deprecated Use {@link BoxAPIConnection#setProxyBasicAuthentication(String, String)}
     */
    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
        buildHttpClients();
    }

    /**
     * Gets the password to use for a proxy that requires basic auth.
     *
     * @return the password to use for a proxy that requires basic auth.
     */
    public String getProxyPassword() {
        return this.proxyPassword;
    }

    /**
     * Sets the proxy user and password used in basic authentication
     *
     * @param proxyUsername Username to use for a proxy that requires basic auth.
     * @param proxyPassword Password to use for a proxy that requires basic auth.
     */
    public void setProxyBasicAuthentication(String proxyUsername, String proxyPassword) {
        this.proxyUsername = proxyUsername;
        this.proxyPassword = proxyPassword;
        buildHttpClients();
    }

    /**
     * Sets the password to use for a proxy that requires basic auth.
     *
     * @param proxyPassword the password to use for a proxy that requires basic auth.
     * @deprecated Use {@link BoxAPIConnection#setProxyBasicAuthentication(String, String)}
     */
    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
        buildHttpClients();
    }

    /**
     * Determines if this connection's access token can be refreshed. An access token cannot be refreshed if a refresh
     * token was never set.
     *
     * @return true if the access token can be refreshed; otherwise false.
     */
    public boolean canRefresh() {
        return this.refreshToken != null;
    }

    /**
     * Determines if this connection's access token has expired and needs to be refreshed.
     *
     * @return true if the access token needs to be refreshed; otherwise false.
     */
    public boolean needsRefresh() {
        boolean needsRefresh;

        this.refreshLock.readLock().lock();
        long now = System.currentTimeMillis();
        long tokenDuration = (now - this.lastRefresh);
        needsRefresh = (tokenDuration >= this.expires - REFRESH_EPSILON);
        this.refreshLock.readLock().unlock();

        return needsRefresh;
    }

    /**
     * Refresh's this connection's access token using its refresh token.
     *
     * @throws IllegalStateException if this connection's access token cannot be refreshed.
     */
    public void refresh() {
        this.refreshLock.writeLock().lock();

        if (!this.canRefresh()) {
            this.refreshLock.writeLock().unlock();
            throw new IllegalStateException("The BoxAPIConnection cannot be refreshed because it doesn't have a "
                + "refresh token.");
        }

        URL url;
        try {
            url = new URL(getTokenURL());
        } catch (MalformedURLException e) {
            this.refreshLock.writeLock().unlock();
            assert false : "An invalid refresh URL indicates a bug in the SDK.";
            throw new RuntimeException("An invalid refresh URL indicates a bug in the SDK.", e);
        }

        BoxAPIRequest request = createTokenRequest(url);

        String json;
        try (BoxAPIResponse boxAPIResponse = request.send()) {
            BoxJSONResponse response = (BoxJSONResponse) boxAPIResponse;
            json = response.getJSON();
        } catch (BoxAPIException e) {
            this.refreshLock.writeLock().unlock();
            this.notifyError(e);
            throw e;
        }

        try {
            extractTokens(Json.parse(json).asObject());

            this.notifyRefresh();
        } finally {
            this.refreshLock.writeLock().unlock();
        }
    }

    /**
     * Restores a saved connection state into this BoxAPIConnection.
     *
     * @param state the saved state that was created with {@link #save}.
     * @see #save
     */
    public void restore(String state) {
        JsonObject json = Json.parse(state).asObject();
        String accessToken = json.get("accessToken").asString();
        String refreshToken = Optional.ofNullable(json.get("refreshToken"))
            .map(v -> v.isNull() ? null : v.asString())
            .orElse(null);
        long lastRefresh = json.get("lastRefresh").asLong();
        long expires = json.get("expires").asLong();
        String userAgent = json.get("userAgent").asString();
        String tokenURL = getKeyValueOrDefault(json, "tokenURL", null);
        String revokeURL = getKeyValueOrDefault(json, "revokeURL", null);
        String baseURL = json.get("baseURL").asString();
        String baseUploadURL = json.get("baseUploadURL").asString();
        String authorizationURL = getKeyValueOrDefault(json, "authorizationURL", DEFAULT_BASE_AUTHORIZATION_URL);
        boolean autoRefresh = json.get("autoRefresh").asBoolean();

        // Try to read deprecated value
        int maxRequestAttempts = -1;
        if (json.names().contains("maxRequestAttempts")) {
            maxRequestAttempts = json.get("maxRequestAttempts").asInt();
        }

        int maxRetryAttempts = -1;
        if (json.names().contains("maxRetryAttempts")) {
            maxRetryAttempts = json.get("maxRetryAttempts").asInt();
        }

        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.lastRefresh = lastRefresh;
        this.expires = expires;
        this.userAgent = userAgent;
        this.tokenURL = tokenURL;
        this.revokeURL = revokeURL;
        this.setBaseURL(baseURL);
        this.setBaseUploadURL(baseUploadURL);
        this.setBaseAuthorizationURL(authorizationURL);
        this.autoRefresh = autoRefresh;

        // Try to use deprecated value "maxRequestAttempts", else use newer value "maxRetryAttempts"
        if (maxRequestAttempts > -1) {
            this.maxRetryAttempts = maxRequestAttempts - 1;
        } else {
            this.maxRetryAttempts = maxRetryAttempts;
        }

    }

    protected String getKeyValueOrDefault(JsonObject json, String key, String defaultValue) {
        return Optional.ofNullable(json.get(key))
            .filter(js -> !js.isNull())
            .map(JsonValue::asString)
            .orElse(defaultValue);
    }

    /**
     * Notifies a refresh event to all the listeners.
     */
    protected void notifyRefresh() {
        for (BoxAPIConnectionListener listener : this.listeners) {
            listener.onRefresh(this);
        }
    }

    /**
     * Notifies an error event to all the listeners.
     *
     * @param error A BoxAPIException instance.
     */
    protected void notifyError(BoxAPIException error) {
        for (BoxAPIConnectionListener listener : this.listeners) {
            listener.onError(this, error);
        }
    }

    /**
     * Add a listener to listen to Box API connection events.
     *
     * @param listener a listener to listen to Box API connection.
     */
    public void addListener(BoxAPIConnectionListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Remove a listener listening to Box API connection events.
     *
     * @param listener the listener to remove.
     */
    public void removeListener(BoxAPIConnectionListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Gets the RequestInterceptor associated with this API connection.
     *
     * @return the RequestInterceptor associated with this API connection.
     */
    public RequestInterceptor getRequestInterceptor() {
        return this.interceptor;
    }

    /**
     * Sets a RequestInterceptor that can intercept requests and manipulate them before they're sent to the Box API.
     *
     * @param interceptor the RequestInterceptor.
     */
    public void setRequestInterceptor(RequestInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    /**
     * Get a lower-scoped token restricted to a resource for the list of scopes that are passed.
     *
     * @param scopes   the list of scopes to which the new token should be restricted for
     * @param resource the resource for which the new token has to be obtained
     * @return scopedToken which has access token and other details
     * @throws BoxAPIException if resource is not a valid Box API endpoint or shared link
     */
    public ScopedToken getLowerScopedToken(List<String> scopes, String resource) {
        assert (scopes != null);
        assert (scopes.size() > 0);
        URL url;
        try {
            url = new URL(this.getTokenURL());
        } catch (MalformedURLException e) {
            assert false : "An invalid refresh URL indicates a bug in the SDK.";
            throw new BoxAPIException("An invalid refresh URL indicates a bug in the SDK.", e);
        }

        StringBuilder spaceSeparatedScopes = this.buildScopesForTokenDownscoping(scopes);

        String urlParameters = format("grant_type=urn:ietf:params:oauth:grant-type:token-exchange"
                + "&subject_token_type=urn:ietf:params:oauth:token-type:access_token&subject_token=%s"
                + "&scope=%s",
            this.getAccessToken(), spaceSeparatedScopes);

        if (resource != null) {

            ResourceLinkType resourceType = this.determineResourceLinkType(resource);

            if (resourceType == ResourceLinkType.APIEndpoint) {
                urlParameters = format(urlParameters + "&resource=%s", resource);
            } else if (resourceType == ResourceLinkType.SharedLink) {
                urlParameters = format(urlParameters + "&box_shared_link=%s", resource);
            } else if (resourceType == ResourceLinkType.Unknown) {
                String argExceptionMessage = format("Unable to determine resource type: %s", resource);
                BoxAPIException e = new BoxAPIException(argExceptionMessage);
                this.notifyError(e);
                throw e;
            } else {
                String argExceptionMessage = format("Unhandled resource type: %s", resource);
                BoxAPIException e = new BoxAPIException(argExceptionMessage);
                this.notifyError(e);
                throw e;
            }
        }

        BoxAPIRequest request = new BoxAPIRequest(this, url, "POST");
        request.shouldAuthenticate(false);
        request.setBody(urlParameters);

        String jsonResponse;
        try (BoxJSONResponse response = (BoxJSONResponse) request.send()) {
            jsonResponse = response.getJSON();
        } catch (BoxAPIException e) {
            this.notifyError(e);
            throw e;
        }

        JsonObject jsonObject = Json.parse(jsonResponse).asObject();
        ScopedToken token = new ScopedToken(jsonObject);
        token.setObtainedAt(System.currentTimeMillis());
        token.setExpiresIn(jsonObject.get("expires_in").asLong() * 1000);
        return token;
    }

    /**
     * Convert List<String> to space-delimited String.
     * Needed for versions prior to Java 8, which don't have String.join(delimiter, list)
     *
     * @param scopes the list of scopes to read from
     * @return space-delimited String of scopes
     */
    private StringBuilder buildScopesForTokenDownscoping(List<String> scopes) {
        StringBuilder spaceSeparatedScopes = new StringBuilder();
        for (int i = 0; i < scopes.size(); i++) {
            spaceSeparatedScopes.append(scopes.get(i));
            if (i < scopes.size() - 1) {
                spaceSeparatedScopes.append(" ");
            }
        }

        return spaceSeparatedScopes;
    }

    /**
     * Determines the type of resource, given a link to a Box resource.
     *
     * @param resourceLink the resource URL to check
     * @return ResourceLinkType that categorizes the provided resourceLink
     */
    protected ResourceLinkType determineResourceLinkType(String resourceLink) {

        ResourceLinkType resourceType = ResourceLinkType.Unknown;

        try {
            URL validUrl = new URL(resourceLink);
            String validURLStr = validUrl.toString();
            final String apiFilesEndpointPattern = ".*box.com/2.0/files/\\d+";
            final String apiFoldersEndpointPattern = ".*box.com/2.0/folders/\\d+";
            final String sharedLinkPattern = "(.*box.com/s/.*|.*box.com.*s=.*)";

            if (Pattern.matches(apiFilesEndpointPattern, validURLStr)
                || Pattern.matches(apiFoldersEndpointPattern, validURLStr)) {
                resourceType = ResourceLinkType.APIEndpoint;
            } else if (Pattern.matches(sharedLinkPattern, validURLStr)) {
                resourceType = ResourceLinkType.SharedLink;
            }
        } catch (MalformedURLException e) {
            //Swallow exception and return default ResourceLinkType set at top of function
        }

        return resourceType;
    }

    /**
     * Revokes the tokens associated with this API connection.  This results in the connection no
     * longer being able to make API calls until a fresh authorization is made by calling authenticate()
     */
    public void revokeToken() {

        URL url;
        try {
            url = new URL(getRevokeURL());
        } catch (MalformedURLException e) {
            assert false : "An invalid refresh URL indicates a bug in the SDK.";
            throw new RuntimeException("An invalid refresh URL indicates a bug in the SDK.", e);
        }

        String urlParameters = format("token=%s&client_id=%s&client_secret=%s",
            this.accessToken, this.clientID, this.clientSecret);

        BoxAPIRequest request = new BoxAPIRequest(this, url, "POST");
        request.shouldAuthenticate(false);
        request.setBody(urlParameters);

        request.send().close();
    }

    /**
     * Saves the state of this connection to a string so that it can be persisted and restored at a later time.
     *
     * <p>Note that proxy settings aren't automatically saved or restored. This is mainly due to security concerns
     * around persisting proxy authentication details to the state string. If your connection uses a proxy, you will
     * have to manually configure it again after restoring the connection.</p>
     *
     * @return the state of this connection.
     * @see #restore
     */
    public String save() {
        JsonObject state = new JsonObject()
            .add("accessToken", this.accessToken)
            .add("refreshToken", this.refreshToken)
            .add("lastRefresh", this.lastRefresh)
            .add("expires", this.expires)
            .add("userAgent", this.userAgent)
            .add("tokenURL", this.tokenURL)
            .add("revokeURL", this.revokeURL)
            .add("baseURL", this.baseURL)
            .add("baseUploadURL", this.baseUploadURL)
            .add("authorizationURL", this.baseAuthorizationURL)
            .add("autoRefresh", this.autoRefresh)
            .add("maxRetryAttempts", this.maxRetryAttempts);
        return state.toString();
    }

    String lockAccessToken() {
        if (this.autoRefresh && this.canRefresh() && this.needsRefresh()) {
            this.refreshLock.writeLock().lock();
            try {
                if (this.needsRefresh()) {
                    this.refresh();
                }
                this.refreshLock.readLock().lock();
            } finally {
                this.refreshLock.writeLock().unlock();
            }
        } else {
            this.refreshLock.readLock().lock();
        }

        return this.accessToken;
    }

    void unlockAccessToken() {
        this.refreshLock.readLock().unlock();
    }

    /**
     * Get the value for the X-Box-UA header.
     *
     * @return the header value.
     */
    String getBoxUAHeader() {

        return "agent=box-java-sdk/" + SDK_VERSION + "; env=Java/" + JAVA_VERSION;
    }

    /**
     * Sets a custom header to be sent on all requests through this API connection.
     *
     * @param header the header name.
     * @param value  the header value.
     */
    public void setCustomHeader(String header, String value) {
        this.customHeaders.put(header, value);
    }

    /**
     * Removes a custom header, so it will no longer be sent on requests through this API connection.
     *
     * @param header the header name.
     */
    public void removeCustomHeader(String header) {
        this.customHeaders.remove(header);
    }

    /**
     * Suppresses email notifications from API actions.  This is typically used by security or admin applications
     * to prevent spamming end users when doing automated processing on their content.
     */
    public void suppressNotifications() {
        this.setCustomHeader(BOX_NOTIFICATIONS_HEADER, "off");
    }

    /**
     * Re-enable email notifications from API actions if they have been suppressed.
     *
     * @see #suppressNotifications
     */
    public void enableNotifications() {
        this.removeCustomHeader(BOX_NOTIFICATIONS_HEADER);
    }

    /**
     * Set this API connection to make API calls on behalf of another users, impersonating them.  This
     * functionality can only be used by admins and service accounts.
     *
     * @param userID the ID of the user to act as.
     */
    public void asUser(String userID) {
        this.setCustomHeader(AS_USER_HEADER, userID);
    }

    /**
     * Sets this API connection to make API calls on behalf of the user with whom the access token is associated.
     * This undoes any previous calls to asUser().
     *
     * @see #asUser
     */
    public void asSelf() {
        this.removeCustomHeader(AS_USER_HEADER);
    }

    /**
     * Used to override default SSL certification handling. For example, you can provide your own
     * trust manager or hostname verifier to allow self-signed certificates.
     * You can check examples <a href="https://github.com/box/box-java-sdk/blob/main/doc/configuration.md#ssl-configuration">here</a>.
     *
     * @param trustManager     TrustManager that verifies certificates are valid.
     * @param hostnameVerifier HostnameVerifier that allows you to specify what hostnames are allowed.
     */
    public void configureSslCertificatesValidation(X509TrustManager trustManager, HostnameVerifier hostnameVerifier) {
        this.trustManager = trustManager;
        this.hostnameVerifier = hostnameVerifier;
        buildHttpClients();
    }

    Map<String, String> getHeaders() {
        return this.customHeaders;
    }

    protected void extractTokens(JsonObject jsonObject) {
        this.accessToken = jsonObject.get("access_token").asString();
        this.refreshToken = jsonObject.get("refresh_token").asString();
        this.lastRefresh = System.currentTimeMillis();
        this.expires = jsonObject.get("expires_in").asLong() * 1000;
    }

    protected BoxAPIRequest createTokenRequest(URL url) {
        String urlParameters = format("grant_type=refresh_token&refresh_token=%s&client_id=%s&client_secret=%s",
            this.refreshToken, this.clientID, this.clientSecret);

        BoxAPIRequest request = new BoxAPIRequest(this, url, "POST");
        request.shouldAuthenticate(false);
        request.setBody(urlParameters);
        return request;
    }

    private String fixBaseUrl(String baseUrl) {
        return baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
    }

    Response execute(Request request) {
        return executeOnClient(httpClient, request);
    }

    Response executeWithoutRedirect(Request request) {
        return executeOnClient(noRedirectsHttpClient, request);
    }

    private Response executeOnClient(OkHttpClient httpClient, Request request) {
        try {
            return httpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error. Request\n" + request, e);
        }
    }

    /**
     * Used to categorize the types of resource links.
     */
    protected enum ResourceLinkType {
        /**
         * Catch-all default for resource links that are unknown.
         */
        Unknown,

        /**
         * Resource URLs that point to an API endipoint such as https://api.box.com/2.0/files/:file_id.
         */
        APIEndpoint,

        /**
         * Resource URLs that point to a resource that has been shared
         * such as https://example.box.com/s/qwertyuiop1234567890asdfghjk
         * or https://example.app.box.com/notes/0987654321?s=zxcvbnm1234567890asdfghjk.
         */
        SharedLink
    }
}
