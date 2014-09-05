package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;

import com.eclipsesource.json.JsonObject;

public class BoxAPIConnection {
    private static final String TOKEN_URL_STRING = "https://www.box.com/api/oauth2/token";
    private static final String DEFAULT_BASE_URL = "https://api.box.com/2.0/";
    private static final long REFRESH_EPSILON = 60000;

    private final String clientID;
    private final String clientSecret;

    private long lastRefresh;
    private long expires;
    private String baseURL;
    private String accessToken;
    private String refreshToken;
    private boolean autoRefresh;

    public BoxAPIConnection(String accessToken) {
        this(null, null, accessToken, null);
    }

    public BoxAPIConnection(String clientID, String clientSecret, String accessToken, String refreshToken) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.accessToken = accessToken;
        this.setRefreshToken(refreshToken);
        this.baseURL = DEFAULT_BASE_URL;
        this.autoRefresh = true;
    }

    public BoxAPIConnection(String clientID, String clientSecret, String authCode) {
        this(clientID, clientSecret, null, null);

        URL url = null;
        try {
            url = new URL(String.format(TOKEN_URL_STRING, authCode, clientID, clientSecret));
        } catch (MalformedURLException e) {
            assert false : "An invalid token URL indicates a bug in the SDK.";
        }

        String urlParameters = String.format("grant_type=authorization_code&code=%s&client_id=%s&client_secret=%s",
            authCode, clientID, clientSecret);

        BoxAPIRequest request = new BoxAPIRequest(url, "POST");
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setBody(urlParameters);

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        String json = response.getJSON();

        JsonObject jsonObject = JsonObject.readFrom(json);
        this.accessToken = jsonObject.get("access_token").asString();
        this.setRefreshToken(jsonObject.get("refresh_token").asString());
        this.expires = jsonObject.get("expires_in").asLong() * 1000;
    }

    public void setExpires(long milliseconds) {
        this.expires = milliseconds;
    }

    public long getExpires() {
        return this.expires;
    }

    public String getBaseURL() {
        return this.baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getAccessToken() {
        if (this.canRefresh() && this.needsRefresh() && this.autoRefresh) {
            this.refresh();
        }

        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        this.lastRefresh = System.currentTimeMillis();
    }

    public void setAutoRefresh(boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    public boolean getAutoRefresh() {
        return this.autoRefresh;
    }

    public boolean canRefresh() {
        return this.refreshToken != null;
    }

    public boolean needsRefresh() {
        if (this.expires == 0) {
            return false;
        }

        long now = System.currentTimeMillis();
        long tokenDuration = (now - this.lastRefresh);
        return (tokenDuration >= this.expires - REFRESH_EPSILON);
    }

    public void refresh() {
        if (!this.canRefresh()) {
            throw new IllegalStateException("The BoxAPIConnection cannot be refreshed because it doesn't have a "
                + "refresh token.");
        }

        URL url = null;
        try {
            url = new URL(String.format(TOKEN_URL_STRING, this.refreshToken, this.clientID, this.clientSecret));
        } catch (MalformedURLException e) {
            assert false : "An invalid refresh URL indicates a bug in the SDK.";
        }

        String urlParameters = String.format("grant_type=refresh_token&refresh_token=%s&client_id=%s&client_secret=%s",
            this.refreshToken, this.clientID, this.clientSecret);

        BoxAPIRequest request = new BoxAPIRequest(url, "POST");
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setBody(urlParameters);

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        String json = response.getJSON();

        JsonObject jsonObject = JsonObject.readFrom(json);
        this.accessToken = jsonObject.get("access_token").asString();
        this.refreshToken = jsonObject.get("refresh_token").asString();
        this.expires = jsonObject.get("expires_in").asLong() * 1000;
    }
}
