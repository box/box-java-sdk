package com.box.sdk;

/**
 * This API connection uses a shared link (along with an optional password) to authenticate with the Box API. It wraps a
 * preexisting BoxAPIConnection in order to provide additional access to items that are accessible with a shared link.
 * @deprecated Use {@link BoxItem#getSharedItem(BoxAPIConnection, String, String, String...)} instead
 */
public class SharedLinkAPIConnection extends BoxAPIConnection {
    private final BoxAPIConnection wrappedConnection;
    private final String sharedLink;
    private final String sharedLinkPassword;

    public SharedLinkAPIConnection(BoxAPIConnection connection, String sharedLink) {
        this(connection, sharedLink, null);
    }

    public SharedLinkAPIConnection(BoxAPIConnection connection, String sharedLink, String sharedLinkPassword) {
        //this is a hack to maintain backward compatibility and to prevent confusing the compiler
        //between two possible BoxApiConnection constructors for super(null)
        super("");

        this.wrappedConnection = connection;
        this.sharedLink = sharedLink;
        this.sharedLinkPassword = sharedLinkPassword;
    }

    @Override
    public long getExpires() {
        return this.wrappedConnection.getExpires();
    }

    @Override
    public void setExpires(long milliseconds) {
        this.wrappedConnection.setExpires(milliseconds);
    }

    @Override
    public String getBaseURL() {
        return this.wrappedConnection.getBaseURL();
    }

    @Override
    public void setBaseURL(String baseURL) {
        this.wrappedConnection.setBaseURL(baseURL);
    }

    @Override
    public String getBaseUploadURL() {
        return this.wrappedConnection.getBaseUploadURL();
    }

    @Override
    public void setBaseUploadURL(String baseUploadURL) {
        this.wrappedConnection.setBaseUploadURL(baseUploadURL);
    }

    @Override
    public String getUserAgent() {
        return this.wrappedConnection.getUserAgent();
    }

    @Override
    public void setUserAgent(String userAgent) {
        this.wrappedConnection.setUserAgent(userAgent);
    }

    @Override
    public String getAccessToken() {
        return this.wrappedConnection.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        this.wrappedConnection.setAccessToken(accessToken);
    }

    @Override
    public String getRefreshToken() {
        return this.wrappedConnection.getRefreshToken();
    }

    @Override
    public void setRefreshToken(String refreshToken) {
        this.wrappedConnection.setRefreshToken(refreshToken);
    }

    @Override
    public boolean getAutoRefresh() {
        return this.wrappedConnection.getAutoRefresh();
    }

    @Override
    public void setAutoRefresh(boolean autoRefresh) {
        this.wrappedConnection.setAutoRefresh(autoRefresh);
    }

    /**
     * Gets the maximum number of times an API request will be retried after an error response
     * is received.
     *
     * @return the maximum number of request attempts.
     */
    @Override
    public int getMaxRetryAttempts() {
        return this.wrappedConnection.getMaxRetryAttempts();
    }

    /**
     * Sets the maximum number of times an API request will be retried after an error response
     * is received.
     *
     * @param attempts the maximum number of request attempts.
     */
    @Override
    public void setMaxRetryAttempts(int attempts) {
        this.wrappedConnection.setMaxRetryAttempts(attempts);
    }

    @Override
    public boolean canRefresh() {
        return this.wrappedConnection.canRefresh();
    }

    @Override
    public boolean needsRefresh() {
        return this.wrappedConnection.needsRefresh();
    }

    @Override
    public void refresh() {
        this.wrappedConnection.refresh();
    }

    @Override
    String lockAccessToken() {
        return this.wrappedConnection.lockAccessToken();
    }

    @Override
    void unlockAccessToken() {
        this.wrappedConnection.unlockAccessToken();
    }

    @Override
    public RequestInterceptor getRequestInterceptor() {
        return this.wrappedConnection.getRequestInterceptor();
    }

    /**
     * Gets the shared link used for accessing shared items.
     *
     * @return the shared link used for accessing shared items.
     */
    String getSharedLink() {
        return this.sharedLink;
    }

    /**
     * Gets the shared link password used for accessing shared items.
     *
     * @return the shared link password used for accessing shared items.
     */
    String getSharedLinkPassword() {
        return this.sharedLinkPassword;
    }
}
