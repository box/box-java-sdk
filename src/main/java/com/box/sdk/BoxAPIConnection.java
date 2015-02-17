package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.eclipsesource.json.JsonObject;

/**
 * Represents an authenticated connection to the Box API.
 *
 * <p>This class handles storing authentication information, automatic token refresh, and rate-limiting. It can also be
 * used to configure the Box API endpoint URL in order to hit a different version of the API. Multiple instances of
 * BoxAPIConnection may be created to support multi-user login.</p>
 */
public class BoxAPIConnection {
    /**
     * The default maximum number of times an API request will be tried when an error occurs.
     */
    public static final int DEFAULT_MAX_ATTEMPTS = 3;

    private static final String TOKEN_URL_STRING = "https://www.box.com/api/oauth2/token";
    private static final String DEFAULT_BASE_URL = "https://api.box.com/2.0/";
    private static final String DEFAULT_BASE_UPLOAD_URL = "https://upload.box.com/api/2.0/";

    /**
     * The amount of buffer time, in milliseconds, to use when determining if an access token should be refreshed. For
     * example, if REFRESH_EPSILON = 60000 and the access token expires in less than one minute, it will be refreshed.
     */
    private static final long REFRESH_EPSILON = 60000;

    private final String clientID;
    private final String clientSecret;
    private final ReadWriteLock refreshLock;

    // These volatile fields are used when determining if the access token needs to be refreshed. Since they are used in
    // the double-checked lock in getAccessToken(), they must be atomic.
    private volatile long lastRefresh;
    private volatile long expires;

    private String userAgent;
    private String accessToken;
    private String refreshToken;
    private String tokenURL;
    private String baseURL;
    private String baseUploadURL;
    private boolean autoRefresh;
    private int maxRequestAttempts;
    private List<BoxAPIConnectionListener> listeners;
    private RequestInterceptor interceptor;

    /**
     * Constructs a new BoxAPIConnection that authenticates with a developer or access token.
     * @param  accessToken a developer or access token to use for authenticating with the API.
     */
    public BoxAPIConnection(String accessToken) {
        this(null, null, accessToken, null);
    }

    /**
     * Constructs a new BoxAPIConnection with an access token that can be refreshed.
     * @param  clientID     the client ID to use when refreshing the access token.
     * @param  clientSecret the client secret to use when refreshing the access token.
     * @param  accessToken  an initial access token to use for authenticating with the API.
     * @param  refreshToken an initial refresh token to use when refreshing the access token.
     */
    public BoxAPIConnection(String clientID, String clientSecret, String accessToken, String refreshToken) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenURL = TOKEN_URL_STRING;
        this.baseURL = DEFAULT_BASE_URL;
        this.baseUploadURL = DEFAULT_BASE_UPLOAD_URL;
        this.autoRefresh = true;
        this.maxRequestAttempts = DEFAULT_MAX_ATTEMPTS;
        this.refreshLock = new ReentrantReadWriteLock();
        this.userAgent = "Box Java SDK v0.7.0";
        this.listeners = new ArrayList<BoxAPIConnectionListener>();
    }

    /**
     * Constructs a new BoxAPIConnection with an auth code that was obtained from the first half of OAuth.
     * @param  clientID     the client ID to use when exchanging the auth code for an access token.
     * @param  clientSecret the client secret to use when exchanging the auth code for an access token.
     * @param  authCode     an auth code obtained from the first half of the OAuth process.
     */
    public BoxAPIConnection(String clientID, String clientSecret, String authCode) {
        this(clientID, clientSecret, null, null);
        this.authenticate(authCode);
    }

    /**
     * Constructs a new BoxAPIConnection.
     * @param  clientID     the client ID to use when exchanging the auth code for an access token.
     * @param  clientSecret the client secret to use when exchanging the auth code for an access token.
     */
    public BoxAPIConnection(String clientID, String clientSecret) {
        this(clientID, clientSecret, null, null);
    }

    /**
     * Authenticates the API connection by obtaining access and refresh tokens using the auth code that was obtained
     * from the first half of OAuth.
     * @param authCode the auth code obtained from the first half of the OAuth process.
     */
    public void authenticate(String authCode) {
        URL url = null;
        try {
            url = new URL(this.tokenURL);
        } catch (MalformedURLException e) {
            assert false : "An invalid token URL indicates a bug in the SDK.";
            throw new RuntimeException("An invalid token URL indicates a bug in the SDK.", e);
        }

        String urlParameters = String.format("grant_type=authorization_code&code=%s&client_id=%s&client_secret=%s",
            authCode, this.clientID, this.clientSecret);

        BoxAPIRequest request = new BoxAPIRequest(url, "POST");
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setBody(urlParameters);

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        String json = response.getJSON();

        JsonObject jsonObject = JsonObject.readFrom(json);
        this.accessToken = jsonObject.get("access_token").asString();
        this.refreshToken = jsonObject.get("refresh_token").asString();
        this.lastRefresh = System.currentTimeMillis();
        this.expires = jsonObject.get("expires_in").asLong() * 1000;
    }

    /**
     * Sets the amount of time for which this connection's access token is valid before it must be refreshed.
     * @param milliseconds the number of milliseconds for which the access token is valid.
     */
    public void setExpires(long milliseconds) {
        this.expires = milliseconds;
    }

    /**
     * Gets the amount of time for which this connection's access token is valid.
     * @return the amount of time in milliseconds.
     */
    public long getExpires() {
        return this.expires;
    }

    /**
     * Gets the token URL that's used to request access tokens.  The default value is
     * "https://www.box.com/api/oauth2/token".
     * @return the token URL.
     */
    public String getTokenURL() {
        return this.tokenURL;
    }

    /**
     * Sets the token URL that's used to request access tokens.  For example, the default token URL is
     * "https://www.box.com/api/oauth2/token".
     * @param tokenURL the token URL.
     */
    public void setTokenURL(String tokenURL) {
        this.tokenURL = tokenURL;
    }

    /**
     * Gets the base URL that's used when sending requests to the Box API. The default value is
     * "https://api.box.com/2.0/".
     * @return the base URL.
     */
    public String getBaseURL() {
        return this.baseURL;
    }

    /**
     * Sets the base URL to be used when sending requests to the Box API. For example, the default base URL is
     * "https://api.box.com/2.0/".
     * @param baseURL a base URL
     */
    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    /**
     * Gets the base upload URL that's used when performing file uploads to Box.
     * @return the base upload URL.
     */
    public String getBaseUploadURL() {
        return this.baseUploadURL;
    }

    /**
     * Sets the base upload URL to be used when performing file uploads to Box.
     * @param baseUploadURL a base upload URL.
     */
    public void setBaseUploadURL(String baseUploadURL) {
        this.baseUploadURL = baseUploadURL;
    }

    /**
     * Gets the user agent that's used when sending requests to the Box API.
     * @return the user agent.
     */
    public String getUserAgent() {
        return this.userAgent;
    }

    /**
     * Sets the user agent to be used when sending requests to the Box API.
     * @param userAgent the user agent.
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * Gets an access token that can be used to authenticate an API request. This method will automatically refresh the
     * access token if it has expired since the last call to <code>getAccessToken()</code>.
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
     * @param accessToken a valid access token to use when authenticating API requests.
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Gets a refresh token that can be used to refresh an access token.
     * @return a valid refresh token.
     */
    public String getRefreshToken() {
        return this.refreshToken;
    }

    /**
     * Sets the refresh token to use when refreshing an access token.
     * @param refreshToken a valid refresh token.
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * Enables or disables automatic refreshing of this connection's access token. Defaults to true.
     * @param autoRefresh true to enable auto token refresh; otherwise false.
     */
    public void setAutoRefresh(boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    /**
     * Gets whether or not automatic refreshing of this connection's access token is enabled. Defaults to true.
     * @return true if auto token refresh is enabled; otherwise false.
     */
    public boolean getAutoRefresh() {
        return this.autoRefresh;
    }

    /**
     * Gets the maximum number of times an API request will be tried when an error occurs.
     * @return the maximum number of request attempts.
     */
    public int getMaxRequestAttempts() {
        return this.maxRequestAttempts;
    }

    /**
     * Sets the maximum number of times an API request will be tried when an error occurs.
     * @param attempts the maximum number of request attempts.
     */
    public void setMaxRequestAttempts(int attempts) {
        this.maxRequestAttempts = attempts;
    }

    /**
     * Determines if this connection's access token can be refreshed. An access token cannot be refreshed if a refresh
     * token was never set.
     * @return true if the access token can be refreshed; otherwise false.
     */
    public boolean canRefresh() {
        return this.refreshToken != null;
    }

    /**
     * Determines if this connection's access token has expired and needs to be refreshed.
     * @return true if the access token needs to be refreshed; otherwise false.
     */
    public boolean needsRefresh() {
        boolean needsRefresh;

        this.refreshLock.readLock().lock();
        if (this.expires == 0) {
            needsRefresh = false;
        } else {
            long now = System.currentTimeMillis();
            long tokenDuration = (now - this.lastRefresh);
            needsRefresh = (tokenDuration >= this.expires - REFRESH_EPSILON);
        }
        this.refreshLock.readLock().unlock();

        return needsRefresh;
    }

    /**
     * Refresh's this connection's access token using its refresh token.
     * @throws IllegalStateException if this connection's access token cannot be refreshed.
     */
    public void refresh() {
        this.refreshLock.writeLock().lock();

        if (!this.canRefresh()) {
            this.refreshLock.writeLock().unlock();
            throw new IllegalStateException("The BoxAPIConnection cannot be refreshed because it doesn't have a "
                + "refresh token.");
        }

        URL url = null;
        try {
            url = new URL(this.tokenURL);
        } catch (MalformedURLException e) {
            this.refreshLock.writeLock().unlock();
            assert false : "An invalid refresh URL indicates a bug in the SDK.";
            throw new RuntimeException("An invalid refresh URL indicates a bug in the SDK.", e);
        }

        String urlParameters = String.format("grant_type=refresh_token&refresh_token=%s&client_id=%s&client_secret=%s",
            this.refreshToken, this.clientID, this.clientSecret);

        BoxAPIRequest request = new BoxAPIRequest(url, "POST");
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setBody(urlParameters);

        String json;
        try {
            BoxJSONResponse response = (BoxJSONResponse) request.send();
            json = response.getJSON();
        } catch (BoxAPIException e) {
            this.refreshLock.writeLock().unlock();
            throw e;
        }

        JsonObject jsonObject = JsonObject.readFrom(json);
        this.accessToken = jsonObject.get("access_token").asString();
        this.refreshToken = jsonObject.get("refresh_token").asString();
        this.lastRefresh = System.currentTimeMillis();
        this.expires = jsonObject.get("expires_in").asLong() * 1000;

        this.notifyRefresh();

        this.refreshLock.writeLock().unlock();
    }

    /**
     * Notifies refresh event to all the listeners.
     */
    private void notifyRefresh() {
        for (BoxAPIConnectionListener listener : this.listeners) {
            listener.onRefresh();
        }
    }

    /**
     * Add a listener to listen to Box API connection events.
     * @param listener a listener to listen to Box API connection.
     */
    public void addListener(BoxAPIConnectionListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Remove a listener listening to Box API connection events.
     * @param listener the listener to remove.
     */
    public void removeListener(BoxAPIConnectionListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Gets the RequestInterceptor associated with this API connection.
     * @return the RequestInterceptor associated with this API connection.
     */
    public RequestInterceptor getRequestInterceptor() {
        return this.interceptor;
    }

    /**
     * Sets a RequestInterceptor that can intercept requests and manipulate them before they're sent to the Box API.
     * @param interceptor the RequestInterceptor.
     */
    public void setRequestInterceptor(RequestInterceptor interceptor) {
        this.interceptor = interceptor;
    }
}
