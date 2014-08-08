package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;

import com.eclipsesource.json.JsonObject;

public class OAuthSession {
    private static final String TOKEN_URL_STRING = "https://www.box.com/api/oauth2/token"
        + "?grant_type=authorization_code&code={0}&client_id={1}&client_secret={2}";
    private static final String REFRESH_URL_STRING = "https://www.box.com/api/oauth2/token"
        + "?grant_type=refresh_token&refresh_token={0}&client_id={1}&client_secret={2}";

    private final String clientID;
    private final String clientSecret;

    private String accessToken;
    private String refreshToken;

    public OAuthSession(String accessToken) {
        this(null, null, accessToken, null);
    }

    public OAuthSession(String clientID, String clientSecret, String accessToken, String refreshToken) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public OAuthSession(String clientID, String clientSecret, String authCode) {
        this(clientID, clientSecret, null, null);

        URL url = null;
        try {
            url = new URL(String.format(TOKEN_URL_STRING, authCode, clientID, clientSecret));
        } catch (MalformedURLException e) {
            assert false : "An invalid token URL indicates a bug in the SDK.";
        }

        BoxAPIRequest request = new BoxAPIRequest(url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        String json = response.getJSON();

        JsonObject jsonObject = JsonObject.readFrom(json);
        this.accessToken = jsonObject.get("access_token").asString();
        this.refreshToken = jsonObject.get("refresh_token").asString();
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public boolean canRefresh() {
        return this.refreshToken != null;
    }

    public void refresh() {
        if (!this.canRefresh()) {
            throw new IllegalStateException("The OAuthSession cannot be refreshed because it doesn't have a refresh "
                + "token.");
        }

        URL url = null;
        try {
            url = new URL(String.format(REFRESH_URL_STRING, this.refreshToken, this.clientID, this.clientSecret));
        } catch (MalformedURLException e) {
            assert false : "An invalid refresh URL indicates a bug in the SDK.";
        }

        BoxAPIRequest request = new BoxAPIRequest(url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        String json = response.getJSON();

        JsonObject jsonObject = JsonObject.readFrom(json);
        this.accessToken = jsonObject.get("access_token").asString();
        this.refreshToken = jsonObject.get("refresh_token").asString();
    }
}
