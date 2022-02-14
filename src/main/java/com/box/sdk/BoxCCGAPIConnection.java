package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_FORM_URLENCODED;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import java.net.URL;

/**
 * Creates and manages Client Credentials Grant API connection.
 */
public final class BoxCCGAPIConnection extends BoxAPIConnection {

    static final String ENTERPRISE_SUBJECT_TYPE = "enterprise";
    static final String USER_SUBJECT_TYPE = "user";
    private String subjectType;
    private String subjectId;

    // Hiding constructor
    private BoxCCGAPIConnection(String accessToken) {
        super(accessToken);
    }

    // Hiding constructor
    private BoxCCGAPIConnection(String clientID, String clientSecret, String accessToken, String refreshToken) {
        super(clientID, clientSecret, accessToken, refreshToken);
    }

    // Hiding constructor
    private BoxCCGAPIConnection(String clientID, String clientSecret, String authCode) {
        super(clientID, clientSecret, authCode);
    }

    // Hiding constructor
    private BoxCCGAPIConnection(String clientID, String clientSecret) {
        super(clientID, clientSecret);
    }

    // Hiding constructor
    private BoxCCGAPIConnection(BoxConfig boxConfig) {
        super(boxConfig);
    }

    /**
     * Creates connection that authenticates as a Service Account
     *
     * @param clientId     the client ID to use when getting the access token.
     * @param clientSecret the client secret to use when getting the access token.
     * @param enterpriseId the enterprise ID to use when getting the access token.
     * @return Client Credentials Grant API connection.
     */
    public static BoxCCGAPIConnection applicationServiceAccountConnection(
        String clientId, String clientSecret, String enterpriseId
    ) {
        BoxCCGAPIConnection api = new BoxCCGAPIConnection(clientId, clientSecret);
        api.subjectType = ENTERPRISE_SUBJECT_TYPE;
        api.subjectId = enterpriseId;
        return api;
    }

    /**
     * Creates connection that authenticates as a User
     *
     * @param clientId     the client ID to use when getting the access token.
     * @param clientSecret the client secret to use when getting the access token.
     * @param userId       the user ID to use when getting the access token.
     * @return Client Credentials Grant API connection.
     */
    public static BoxCCGAPIConnection userConnection(String clientId, String clientSecret, String userId) {
        BoxCCGAPIConnection api = new BoxCCGAPIConnection(clientId, clientSecret);
        api.subjectType = USER_SUBJECT_TYPE;
        api.subjectId = userId;
        return api;
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
    public static BoxCCGAPIConnection restore(String clientID, String clientSecret, String state) {
        BoxCCGAPIConnection api = new BoxCCGAPIConnection(clientID, clientSecret);
        api.restore(state);
        return api;
    }

    @Override
    protected BoxAPIRequest createTokenRequest(URL url) {
        String urlParameters = String.format(
            "grant_type=client_credentials&client_id=%s&client_secret=%s&box_subject_type=%s&box_subject_id=%s",
            this.getClientID(), this.getClientSecret(), this.subjectType, this.subjectId);
        BoxAPIRequest request = new BoxAPIRequest(this, url, "POST");
        request.shouldAuthenticate(false);
        request.setBody(urlParameters);
        request.addHeader("Content-Type", APPLICATION_FORM_URLENCODED);
        return request;
    }

    @Override
    protected void extractTokens(JsonObject jsonObject) {
        this.setAccessToken(jsonObject.get("access_token").asString());
        this.setLastRefresh(System.currentTimeMillis());
        this.setExpires(jsonObject.get("expires_in").asLong() * 1000);
    }

    @Override
    public boolean canRefresh() {
        return true;
    }

    /**
     * Set this API connection to make API calls on behalf of another users, impersonating them. This
     * functionality can only be used when using service connection, otherwise it will throw
     * IllegalStateException
     *
     * @param userID the ID of the user to act as.
     * @throws IllegalStateException when called on User connection
     */
    @Override
    public void asUser(String userID) {
        if (isUserConnection()) {
            throw new IllegalStateException("Cannot add As-User header to connection created for a user.");
        }
        super.asUser(userID);
    }

    /**
     * Removes impersonation and returns connection to a service one. This undoes any previous calls to asUser().
     *
     * @throws IllegalStateException when called on User connection
     * @see #asUser
     */
    @Override
    public void asSelf() {
        if (isUserConnection()) {
            throw new IllegalStateException("Cannot remove As-User header from connection created for a user.");
        }
        super.asSelf();
    }

    public boolean isUserConnection() {
        return subjectType.equals(USER_SUBJECT_TYPE);
    }

    @Override
    public String save() {
        JsonObject state = new JsonObject()
            .add("accessToken", this.getAccessToken())
            .add("lastRefresh", this.getLastRefresh())
            .add("expires", this.getExpires())
            .add("userAgent", this.getUserAgent())
            .add("tokenURL", this.getTokenURL())
            .add("baseURL", this.getBaseURL())
            .add("baseUploadURL", this.getBaseUploadURL())
            .add("autoRefresh", this.getAutoRefresh())
            .add("maxRetryAttempts", this.getMaxRetryAttempts())
            .add("subjectType", this.subjectType)
            .add("subjectId", this.subjectId);
        return state.toString();
    }

    @Override
    public void restore(String state) {
        JsonObject json = Json.parse(state).asObject();
        String accessToken = json.get("accessToken").asString();
        long lastRefresh = json.get("lastRefresh").asLong();
        long expires = json.get("expires").asLong();
        String userAgent = json.get("userAgent").asString();
        String tokenURL = json.get("tokenURL").asString();
        String baseURL = json.get("baseURL").asString();
        String baseUploadURL = json.get("baseUploadURL").asString();
        boolean autoRefresh = json.get("autoRefresh").asBoolean();
        String subjectType = json.get("subjectType").asString();
        String subjectId = json.get("subjectId").asString();

        int maxRetryAttempts = -1;
        if (json.names().contains("maxRetryAttempts")) {
            maxRetryAttempts = json.get("maxRetryAttempts").asInt();
        }

        this.setAccessToken(accessToken);
        setLastRefresh(lastRefresh);
        setExpires(expires);
        setUserAgent(userAgent);
        setTokenURL(tokenURL);
        setBaseURL(baseURL);
        setBaseUploadURL(baseUploadURL);
        setAutoRefresh(autoRefresh);
        setMaxRetryAttempts(maxRetryAttempts);
        this.subjectType = subjectType;
        this.subjectId = subjectId;

    }
}
