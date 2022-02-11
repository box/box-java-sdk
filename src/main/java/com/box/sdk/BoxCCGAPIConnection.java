package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_FORM_URLENCODED;

import com.eclipsesource.json.JsonObject;
import java.net.URL;

/**
 *
 */
public class BoxCCGAPIConnection extends BoxAPIConnection {

    static final String ENTERPRISE_SUBJECT_TYPE = "enterprise";
    static final String USER_SUBJECT_TYPE = "user";
    private String subjectType;
    private String subjectId;

    public static BoxCCGAPIConnection applicationServiceAccountConnection(
        String clientId, String clientSecret, String enterpriseId
    ) {
        BoxCCGAPIConnection api = new BoxCCGAPIConnection(clientId, clientSecret);
        api.subjectType = ENTERPRISE_SUBJECT_TYPE;
        api.subjectId = enterpriseId;
        return api;
    }

    public static BoxAPIConnection userConnection(String clientId, String clientSecret, String userId) {
        BoxCCGAPIConnection api = new BoxCCGAPIConnection(clientId, clientSecret);
        api.subjectType = USER_SUBJECT_TYPE;
        api.subjectId = userId;
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

}
