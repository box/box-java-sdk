package com.box.sdkgen.managers.invites;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.invite.Invite;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class InvitesManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public InvitesManager() {
    this.networkSession = new NetworkSession();
  }

  protected InvitesManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Invites an existing external user to join an enterprise.
   *
   * <p>The existing user can not be part of another enterprise and must already have a Box account.
   * Once invited, the user will receive an email and are prompted to accept the invitation within
   * the Box web application.
   *
   * <p>This method requires the "Manage An Enterprise" scope enabled for the application, which can
   * be enabled within the developer console.
   *
   * @param requestBody Request body of createInvite method
   */
  public Invite createInvite(CreateInviteRequestBody requestBody) {
    return createInvite(requestBody, new CreateInviteQueryParams(), new CreateInviteHeaders());
  }

  /**
   * Invites an existing external user to join an enterprise.
   *
   * <p>The existing user can not be part of another enterprise and must already have a Box account.
   * Once invited, the user will receive an email and are prompted to accept the invitation within
   * the Box web application.
   *
   * <p>This method requires the "Manage An Enterprise" scope enabled for the application, which can
   * be enabled within the developer console.
   *
   * @param requestBody Request body of createInvite method
   * @param queryParams Query parameters of createInvite method
   */
  public Invite createInvite(
      CreateInviteRequestBody requestBody, CreateInviteQueryParams queryParams) {
    return createInvite(requestBody, queryParams, new CreateInviteHeaders());
  }

  /**
   * Invites an existing external user to join an enterprise.
   *
   * <p>The existing user can not be part of another enterprise and must already have a Box account.
   * Once invited, the user will receive an email and are prompted to accept the invitation within
   * the Box web application.
   *
   * <p>This method requires the "Manage An Enterprise" scope enabled for the application, which can
   * be enabled within the developer console.
   *
   * @param requestBody Request body of createInvite method
   * @param headers Headers of createInvite method
   */
  public Invite createInvite(CreateInviteRequestBody requestBody, CreateInviteHeaders headers) {
    return createInvite(requestBody, new CreateInviteQueryParams(), headers);
  }

  /**
   * Invites an existing external user to join an enterprise.
   *
   * <p>The existing user can not be part of another enterprise and must already have a Box account.
   * Once invited, the user will receive an email and are prompted to accept the invitation within
   * the Box web application.
   *
   * <p>This method requires the "Manage An Enterprise" scope enabled for the application, which can
   * be enabled within the developer console.
   *
   * @param requestBody Request body of createInvite method
   * @param queryParams Query parameters of createInvite method
   * @param headers Headers of createInvite method
   */
  public Invite createInvite(
      CreateInviteRequestBody requestBody,
      CreateInviteQueryParams queryParams,
      CreateInviteHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/invites"),
                        "POST")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Invite.class);
  }

  /**
   * Returns the status of a user invite.
   *
   * @param inviteId The ID of an invite. Example: "213723"
   */
  public Invite getInviteById(String inviteId) {
    return getInviteById(inviteId, new GetInviteByIdQueryParams(), new GetInviteByIdHeaders());
  }

  /**
   * Returns the status of a user invite.
   *
   * @param inviteId The ID of an invite. Example: "213723"
   * @param queryParams Query parameters of getInviteById method
   */
  public Invite getInviteById(String inviteId, GetInviteByIdQueryParams queryParams) {
    return getInviteById(inviteId, queryParams, new GetInviteByIdHeaders());
  }

  /**
   * Returns the status of a user invite.
   *
   * @param inviteId The ID of an invite. Example: "213723"
   * @param headers Headers of getInviteById method
   */
  public Invite getInviteById(String inviteId, GetInviteByIdHeaders headers) {
    return getInviteById(inviteId, new GetInviteByIdQueryParams(), headers);
  }

  /**
   * Returns the status of a user invite.
   *
   * @param inviteId The ID of an invite. Example: "213723"
   * @param queryParams Query parameters of getInviteById method
   * @param headers Headers of getInviteById method
   */
  public Invite getInviteById(
      String inviteId, GetInviteByIdQueryParams queryParams, GetInviteByIdHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/invites/",
                            convertToString(inviteId)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Invite.class);
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

    public InvitesManager build() {
      return new InvitesManager(this);
    }
  }
}
