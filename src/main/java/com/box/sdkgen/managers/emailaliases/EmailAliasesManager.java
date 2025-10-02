package com.box.sdkgen.managers.emailaliases;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.emailalias.EmailAlias;
import com.box.sdkgen.schemas.emailaliases.EmailAliases;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class EmailAliasesManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public EmailAliasesManager() {
    this.networkSession = new NetworkSession();
  }

  protected EmailAliasesManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves all email aliases for a user. The collection does not include the primary login for
   * the user.
   *
   * @param userId The ID of the user. Example: "12345"
   */
  public EmailAliases getUserEmailAliases(String userId) {
    return getUserEmailAliases(userId, new GetUserEmailAliasesHeaders());
  }

  /**
   * Retrieves all email aliases for a user. The collection does not include the primary login for
   * the user.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param headers Headers of getUserEmailAliases method
   */
  public EmailAliases getUserEmailAliases(String userId, GetUserEmailAliasesHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/users/",
                            convertToString(userId),
                            "/email_aliases"),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), EmailAliases.class);
  }

  /**
   * Adds a new email alias to a user account..
   *
   * @param userId The ID of the user. Example: "12345"
   * @param requestBody Request body of createUserEmailAlias method
   */
  public EmailAlias createUserEmailAlias(
      String userId, CreateUserEmailAliasRequestBody requestBody) {
    return createUserEmailAlias(userId, requestBody, new CreateUserEmailAliasHeaders());
  }

  /**
   * Adds a new email alias to a user account..
   *
   * @param userId The ID of the user. Example: "12345"
   * @param requestBody Request body of createUserEmailAlias method
   * @param headers Headers of createUserEmailAlias method
   */
  public EmailAlias createUserEmailAlias(
      String userId,
      CreateUserEmailAliasRequestBody requestBody,
      CreateUserEmailAliasHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/users/",
                            convertToString(userId),
                            "/email_aliases"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), EmailAlias.class);
  }

  /**
   * Removes an email alias from a user.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param emailAliasId The ID of the email alias. Example: "23432"
   */
  public void deleteUserEmailAliasById(String userId, String emailAliasId) {
    deleteUserEmailAliasById(userId, emailAliasId, new DeleteUserEmailAliasByIdHeaders());
  }

  /**
   * Removes an email alias from a user.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param emailAliasId The ID of the email alias. Example: "23432"
   * @param headers Headers of deleteUserEmailAliasById method
   */
  public void deleteUserEmailAliasById(
      String userId, String emailAliasId, DeleteUserEmailAliasByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/users/",
                            convertToString(userId),
                            "/email_aliases/",
                            convertToString(emailAliasId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  public Authentication getAuth() {
    return auth;
  }

  public NetworkSession getNetworkSession() {
    return networkSession;
  }

  public static class Builder {

    protected Authentication auth;

    protected NetworkSession networkSession;

    public Builder() {
      this.networkSession = new NetworkSession();
    }

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public EmailAliasesManager build() {
      return new EmailAliasesManager(this);
    }
  }
}
