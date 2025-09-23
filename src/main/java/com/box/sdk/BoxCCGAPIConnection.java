package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_FORM_URLENCODED;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import java.net.URL;

/** Creates and manages Client Credentials Grant API connection. */
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
  private BoxCCGAPIConnection(
      String clientID, String clientSecret, String accessToken, String refreshToken) {
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
   * @param clientId the client ID to use when getting the access token.
   * @param clientSecret the client secret to use when getting the access token.
   * @param enterpriseId the enterprise ID to use when getting the access token.
   * @return Client Credentials Grant API connection.
   */
  public static BoxCCGAPIConnection applicationServiceAccountConnection(
      String clientId, String clientSecret, String enterpriseId) {
    BoxCCGAPIConnection api = new BoxCCGAPIConnection(clientId, clientSecret);
    api.subjectType = ENTERPRISE_SUBJECT_TYPE;
    api.subjectId = enterpriseId;
    return api;
  }

  /**
   * Creates connection that authenticates as a User
   *
   * @param clientId the client ID to use when getting the access token.
   * @param clientSecret the client secret to use when getting the access token.
   * @param userId the user ID to use when getting the access token.
   * @return Client Credentials Grant API connection.
   */
  public static BoxCCGAPIConnection userConnection(
      String clientId, String clientSecret, String userId) {
    BoxCCGAPIConnection api = new BoxCCGAPIConnection(clientId, clientSecret);
    api.subjectType = USER_SUBJECT_TYPE;
    api.subjectId = userId;
    return api;
  }

  /**
   * Restores a BoxAPIConnection from a saved state.
   *
   * @param clientID the client ID to use with the connection.
   * @param clientSecret the client secret to use with the connection.
   * @param state the saved state that was created with {@link #save}.
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
    String urlParameters =
        String.format(
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

  public boolean isUserConnection() {
    return subjectType.equals(USER_SUBJECT_TYPE);
  }

  @Override
  public String save() {
    JsonObject state = Json.parse(super.save()).asObject();
    state.add("subjectType", this.subjectType);
    state.add("subjectId", this.subjectId);
    return state.toString();
  }

  @Override
  public void restore(String state) {
    super.restore(state);

    JsonObject json = Json.parse(state).asObject();
    this.subjectType = json.get("subjectType").asString();
    this.subjectId = json.get("subjectId").asString();
  }
}
