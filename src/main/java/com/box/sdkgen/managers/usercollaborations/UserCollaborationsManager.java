package com.box.sdkgen.managers.usercollaborations;

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
import com.box.sdkgen.schemas.collaboration.Collaboration;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class UserCollaborationsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public UserCollaborationsManager() {
    this.networkSession = new NetworkSession();
  }

  protected UserCollaborationsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves a single collaboration.
   *
   * @param collaborationId The ID of the collaboration. Example: "1234"
   */
  public Collaboration getCollaborationById(String collaborationId) {
    return getCollaborationById(
        collaborationId, new GetCollaborationByIdQueryParams(), new GetCollaborationByIdHeaders());
  }

  /**
   * Retrieves a single collaboration.
   *
   * @param collaborationId The ID of the collaboration. Example: "1234"
   * @param queryParams Query parameters of getCollaborationById method
   */
  public Collaboration getCollaborationById(
      String collaborationId, GetCollaborationByIdQueryParams queryParams) {
    return getCollaborationById(collaborationId, queryParams, new GetCollaborationByIdHeaders());
  }

  /**
   * Retrieves a single collaboration.
   *
   * @param collaborationId The ID of the collaboration. Example: "1234"
   * @param headers Headers of getCollaborationById method
   */
  public Collaboration getCollaborationById(
      String collaborationId, GetCollaborationByIdHeaders headers) {
    return getCollaborationById(collaborationId, new GetCollaborationByIdQueryParams(), headers);
  }

  /**
   * Retrieves a single collaboration.
   *
   * @param collaborationId The ID of the collaboration. Example: "1234"
   * @param queryParams Query parameters of getCollaborationById method
   * @param headers Headers of getCollaborationById method
   */
  public Collaboration getCollaborationById(
      String collaborationId,
      GetCollaborationByIdQueryParams queryParams,
      GetCollaborationByIdHeaders headers) {
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
                            "/2.0/collaborations/",
                            convertToString(collaborationId)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Collaboration.class);
  }

  /**
   * Updates a collaboration. Can be used to change the owner of an item, or to accept collaboration
   * invites. In case of accepting collaboration invite, role is not required.
   *
   * @param collaborationId The ID of the collaboration. Example: "1234"
   */
  public Collaboration updateCollaborationById(String collaborationId) {
    return updateCollaborationById(
        collaborationId,
        new UpdateCollaborationByIdRequestBody(),
        new UpdateCollaborationByIdHeaders());
  }

  /**
   * Updates a collaboration. Can be used to change the owner of an item, or to accept collaboration
   * invites. In case of accepting collaboration invite, role is not required.
   *
   * @param collaborationId The ID of the collaboration. Example: "1234"
   * @param requestBody Request body of updateCollaborationById method
   */
  public Collaboration updateCollaborationById(
      String collaborationId, UpdateCollaborationByIdRequestBody requestBody) {
    return updateCollaborationById(
        collaborationId, requestBody, new UpdateCollaborationByIdHeaders());
  }

  /**
   * Updates a collaboration. Can be used to change the owner of an item, or to accept collaboration
   * invites. In case of accepting collaboration invite, role is not required.
   *
   * @param collaborationId The ID of the collaboration. Example: "1234"
   * @param headers Headers of updateCollaborationById method
   */
  public Collaboration updateCollaborationById(
      String collaborationId, UpdateCollaborationByIdHeaders headers) {
    return updateCollaborationById(
        collaborationId, new UpdateCollaborationByIdRequestBody(), headers);
  }

  /**
   * Updates a collaboration. Can be used to change the owner of an item, or to accept collaboration
   * invites. In case of accepting collaboration invite, role is not required.
   *
   * @param collaborationId The ID of the collaboration. Example: "1234"
   * @param requestBody Request body of updateCollaborationById method
   * @param headers Headers of updateCollaborationById method
   */
  public Collaboration updateCollaborationById(
      String collaborationId,
      UpdateCollaborationByIdRequestBody requestBody,
      UpdateCollaborationByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/collaborations/",
                            convertToString(collaborationId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    if (convertToString(response.getStatus()).equals("204")) {
      return null;
    }
    return JsonManager.deserialize(response.getData(), Collaboration.class);
  }

  /**
   * Deletes a single collaboration.
   *
   * @param collaborationId The ID of the collaboration. Example: "1234"
   */
  public void deleteCollaborationById(String collaborationId) {
    deleteCollaborationById(collaborationId, new DeleteCollaborationByIdHeaders());
  }

  /**
   * Deletes a single collaboration.
   *
   * @param collaborationId The ID of the collaboration. Example: "1234"
   * @param headers Headers of deleteCollaborationById method
   */
  public void deleteCollaborationById(
      String collaborationId, DeleteCollaborationByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/collaborations/",
                            convertToString(collaborationId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Adds a collaboration for a single user or a single group to a file or folder.
   *
   * <p>Collaborations can be created using email address, user IDs, or a group IDs.
   *
   * <p>If a collaboration is being created with a group, access to this endpoint is dependent on
   * the group's ability to be invited.
   *
   * <p>If collaboration is in `pending` status, the following fields are redacted: - `login` and
   * `name` are hidden if a collaboration was created using `user_id`, - `name` is hidden if a
   * collaboration was created using `login`.
   *
   * @param requestBody Request body of createCollaboration method
   */
  public Collaboration createCollaboration(CreateCollaborationRequestBody requestBody) {
    return createCollaboration(
        requestBody, new CreateCollaborationQueryParams(), new CreateCollaborationHeaders());
  }

  /**
   * Adds a collaboration for a single user or a single group to a file or folder.
   *
   * <p>Collaborations can be created using email address, user IDs, or a group IDs.
   *
   * <p>If a collaboration is being created with a group, access to this endpoint is dependent on
   * the group's ability to be invited.
   *
   * <p>If collaboration is in `pending` status, the following fields are redacted: - `login` and
   * `name` are hidden if a collaboration was created using `user_id`, - `name` is hidden if a
   * collaboration was created using `login`.
   *
   * @param requestBody Request body of createCollaboration method
   * @param queryParams Query parameters of createCollaboration method
   */
  public Collaboration createCollaboration(
      CreateCollaborationRequestBody requestBody, CreateCollaborationQueryParams queryParams) {
    return createCollaboration(requestBody, queryParams, new CreateCollaborationHeaders());
  }

  /**
   * Adds a collaboration for a single user or a single group to a file or folder.
   *
   * <p>Collaborations can be created using email address, user IDs, or a group IDs.
   *
   * <p>If a collaboration is being created with a group, access to this endpoint is dependent on
   * the group's ability to be invited.
   *
   * <p>If collaboration is in `pending` status, the following fields are redacted: - `login` and
   * `name` are hidden if a collaboration was created using `user_id`, - `name` is hidden if a
   * collaboration was created using `login`.
   *
   * @param requestBody Request body of createCollaboration method
   * @param headers Headers of createCollaboration method
   */
  public Collaboration createCollaboration(
      CreateCollaborationRequestBody requestBody, CreateCollaborationHeaders headers) {
    return createCollaboration(requestBody, new CreateCollaborationQueryParams(), headers);
  }

  /**
   * Adds a collaboration for a single user or a single group to a file or folder.
   *
   * <p>Collaborations can be created using email address, user IDs, or a group IDs.
   *
   * <p>If a collaboration is being created with a group, access to this endpoint is dependent on
   * the group's ability to be invited.
   *
   * <p>If collaboration is in `pending` status, the following fields are redacted: - `login` and
   * `name` are hidden if a collaboration was created using `user_id`, - `name` is hidden if a
   * collaboration was created using `login`.
   *
   * @param requestBody Request body of createCollaboration method
   * @param queryParams Query parameters of createCollaboration method
   * @param headers Headers of createCollaboration method
   */
  public Collaboration createCollaboration(
      CreateCollaborationRequestBody requestBody,
      CreateCollaborationQueryParams queryParams,
      CreateCollaborationHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("notify", convertToString(queryParams.getNotify()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/collaborations"),
                        "POST")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Collaboration.class);
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

    public UserCollaborationsManager build() {
      return new UserCollaborationsManager(this);
    }
  }
}
