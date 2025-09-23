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

  public Collaboration getCollaborationById(String collaborationId) {
    return getCollaborationById(
        collaborationId, new GetCollaborationByIdQueryParams(), new GetCollaborationByIdHeaders());
  }

  public Collaboration getCollaborationById(
      String collaborationId, GetCollaborationByIdQueryParams queryParams) {
    return getCollaborationById(collaborationId, queryParams, new GetCollaborationByIdHeaders());
  }

  public Collaboration getCollaborationById(
      String collaborationId, GetCollaborationByIdHeaders headers) {
    return getCollaborationById(collaborationId, new GetCollaborationByIdQueryParams(), headers);
  }

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

  public Collaboration updateCollaborationById(
      String collaborationId, UpdateCollaborationByIdRequestBody requestBody) {
    return updateCollaborationById(
        collaborationId, requestBody, new UpdateCollaborationByIdHeaders());
  }

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

  public void deleteCollaborationById(String collaborationId) {
    deleteCollaborationById(collaborationId, new DeleteCollaborationByIdHeaders());
  }

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

  public Collaboration createCollaboration(CreateCollaborationRequestBody requestBody) {
    return createCollaboration(
        requestBody, new CreateCollaborationQueryParams(), new CreateCollaborationHeaders());
  }

  public Collaboration createCollaboration(
      CreateCollaborationRequestBody requestBody, CreateCollaborationQueryParams queryParams) {
    return createCollaboration(requestBody, queryParams, new CreateCollaborationHeaders());
  }

  public Collaboration createCollaboration(
      CreateCollaborationRequestBody requestBody, CreateCollaborationHeaders headers) {
    return createCollaboration(requestBody, new CreateCollaborationQueryParams(), headers);
  }

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
