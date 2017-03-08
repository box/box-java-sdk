package com.box.sdk;

/**
 * Represents an authenticated connection to the Box API.
 *
 * <p>This class handles storing authentication information, token refresh, and rate-limiting. It can also be
 * used to configure the Box API endpoint URL in order to hit a different version of the API. Multiple instances of
 * BoxAPIConnection may be created to support multi-user login.</p>
 */
public class ReloadableBoxAPIConnection extends BoxAPIConnection {
    /**
     * Constructs a new ReloadableBoxAPIConnection with an access token that can be refreshed.
     * @param  clientID     the client ID to use when refreshing the access token.
     * @param  clientSecret the client secret to use when refreshing the access token.
     * @param  accessToken  an initial access token to use for authenticating with the API.
     * @param  refreshToken an initial refresh token to use when refreshing the access token.
     */
    public ReloadableBoxAPIConnection(String clientID, String clientSecret, String accessToken, String refreshToken) {
        super(clientID, clientSecret, accessToken, refreshToken);
    }

    /**
     * Gets an access token that can be used to authenticate an API request.
     * @return an access token that can be used to authenticate an API request.
     */
    @Override
    public String getAccessToken() { return this.accessToken; }

    @Override
    public boolean canRefresh() {
        if(super.canRefresh()) {
            for(BoxAPIConnectionListener listener: listeners)
                if (!listener.preRefresh(this))
                    return false;
            return true;
        }
        return false;
    }

    @Override
    public boolean needsRefresh() { return false; }
}
