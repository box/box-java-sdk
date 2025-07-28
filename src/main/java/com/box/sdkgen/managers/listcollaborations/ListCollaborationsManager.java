package com.box.sdkgen.managers.listcollaborations;

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
import com.box.sdkgen.schemas.collaborations.Collaborations;
import com.box.sdkgen.schemas.collaborationsoffsetpaginated.CollaborationsOffsetPaginated;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class ListCollaborationsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public ListCollaborationsManager() {
    this.networkSession = new NetworkSession();
  }

  protected ListCollaborationsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public Collaborations getFileCollaborations(String fileId) {
    return getFileCollaborations(
        fileId, new GetFileCollaborationsQueryParams(), new GetFileCollaborationsHeaders());
  }

  public Collaborations getFileCollaborations(
      String fileId, GetFileCollaborationsQueryParams queryParams) {
    return getFileCollaborations(fileId, queryParams, new GetFileCollaborationsHeaders());
  }

  public Collaborations getFileCollaborations(String fileId, GetFileCollaborationsHeaders headers) {
    return getFileCollaborations(fileId, new GetFileCollaborationsQueryParams(), headers);
  }

  public Collaborations getFileCollaborations(
      String fileId,
      GetFileCollaborationsQueryParams queryParams,
      GetFileCollaborationsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("marker", convertToString(queryParams.getMarker()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/files/",
                            convertToString(fileId),
                            "/collaborations"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Collaborations.class);
  }

  public Collaborations getFolderCollaborations(String folderId) {
    return getFolderCollaborations(
        folderId, new GetFolderCollaborationsQueryParams(), new GetFolderCollaborationsHeaders());
  }

  public Collaborations getFolderCollaborations(
      String folderId, GetFolderCollaborationsQueryParams queryParams) {
    return getFolderCollaborations(folderId, queryParams, new GetFolderCollaborationsHeaders());
  }

  public Collaborations getFolderCollaborations(
      String folderId, GetFolderCollaborationsHeaders headers) {
    return getFolderCollaborations(folderId, new GetFolderCollaborationsQueryParams(), headers);
  }

  public Collaborations getFolderCollaborations(
      String folderId,
      GetFolderCollaborationsQueryParams queryParams,
      GetFolderCollaborationsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("marker", convertToString(queryParams.getMarker()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/folders/",
                            convertToString(folderId),
                            "/collaborations"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Collaborations.class);
  }

  public CollaborationsOffsetPaginated getCollaborations(GetCollaborationsQueryParams queryParams) {
    return getCollaborations(queryParams, new GetCollaborationsHeaders());
  }

  public CollaborationsOffsetPaginated getCollaborations(
      GetCollaborationsQueryParams queryParams, GetCollaborationsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("status", convertToString(queryParams.getStatus())),
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("offset", convertToString(queryParams.getOffset())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
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
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), CollaborationsOffsetPaginated.class);
  }

  public CollaborationsOffsetPaginated getGroupCollaborations(String groupId) {
    return getGroupCollaborations(
        groupId, new GetGroupCollaborationsQueryParams(), new GetGroupCollaborationsHeaders());
  }

  public CollaborationsOffsetPaginated getGroupCollaborations(
      String groupId, GetGroupCollaborationsQueryParams queryParams) {
    return getGroupCollaborations(groupId, queryParams, new GetGroupCollaborationsHeaders());
  }

  public CollaborationsOffsetPaginated getGroupCollaborations(
      String groupId, GetGroupCollaborationsHeaders headers) {
    return getGroupCollaborations(groupId, new GetGroupCollaborationsQueryParams(), headers);
  }

  public CollaborationsOffsetPaginated getGroupCollaborations(
      String groupId,
      GetGroupCollaborationsQueryParams queryParams,
      GetGroupCollaborationsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("offset", convertToString(queryParams.getOffset()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/groups/",
                            convertToString(groupId),
                            "/collaborations"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), CollaborationsOffsetPaginated.class);
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

    public ListCollaborationsManager build() {
      return new ListCollaborationsManager(this);
    }
  }
}
