package com.box.sdkgen.managers.trashedfolders;

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
import com.box.sdkgen.schemas.trashfolder.TrashFolder;
import com.box.sdkgen.schemas.trashfolderrestored.TrashFolderRestored;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class TrashedFoldersManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public TrashedFoldersManager() {
    this.networkSession = new NetworkSession();
  }

  protected TrashedFoldersManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public TrashFolderRestored restoreFolderFromTrash(String folderId) {
    return restoreFolderFromTrash(
        folderId,
        new RestoreFolderFromTrashRequestBody(),
        new RestoreFolderFromTrashQueryParams(),
        new RestoreFolderFromTrashHeaders());
  }

  public TrashFolderRestored restoreFolderFromTrash(
      String folderId, RestoreFolderFromTrashRequestBody requestBody) {
    return restoreFolderFromTrash(
        folderId,
        requestBody,
        new RestoreFolderFromTrashQueryParams(),
        new RestoreFolderFromTrashHeaders());
  }

  public TrashFolderRestored restoreFolderFromTrash(
      String folderId, RestoreFolderFromTrashQueryParams queryParams) {
    return restoreFolderFromTrash(
        folderId,
        new RestoreFolderFromTrashRequestBody(),
        queryParams,
        new RestoreFolderFromTrashHeaders());
  }

  public TrashFolderRestored restoreFolderFromTrash(
      String folderId,
      RestoreFolderFromTrashRequestBody requestBody,
      RestoreFolderFromTrashQueryParams queryParams) {
    return restoreFolderFromTrash(
        folderId, requestBody, queryParams, new RestoreFolderFromTrashHeaders());
  }

  public TrashFolderRestored restoreFolderFromTrash(
      String folderId, RestoreFolderFromTrashHeaders headers) {
    return restoreFolderFromTrash(
        folderId,
        new RestoreFolderFromTrashRequestBody(),
        new RestoreFolderFromTrashQueryParams(),
        headers);
  }

  public TrashFolderRestored restoreFolderFromTrash(
      String folderId,
      RestoreFolderFromTrashRequestBody requestBody,
      RestoreFolderFromTrashHeaders headers) {
    return restoreFolderFromTrash(
        folderId, requestBody, new RestoreFolderFromTrashQueryParams(), headers);
  }

  public TrashFolderRestored restoreFolderFromTrash(
      String folderId,
      RestoreFolderFromTrashQueryParams queryParams,
      RestoreFolderFromTrashHeaders headers) {
    return restoreFolderFromTrash(
        folderId, new RestoreFolderFromTrashRequestBody(), queryParams, headers);
  }

  public TrashFolderRestored restoreFolderFromTrash(
      String folderId,
      RestoreFolderFromTrashRequestBody requestBody,
      RestoreFolderFromTrashQueryParams queryParams,
      RestoreFolderFromTrashHeaders headers) {
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
                            "/2.0/folders/",
                            convertToString(folderId)),
                        "POST")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TrashFolderRestored.class);
  }

  public TrashFolder getTrashedFolderById(String folderId) {
    return getTrashedFolderById(
        folderId, new GetTrashedFolderByIdQueryParams(), new GetTrashedFolderByIdHeaders());
  }

  public TrashFolder getTrashedFolderById(
      String folderId, GetTrashedFolderByIdQueryParams queryParams) {
    return getTrashedFolderById(folderId, queryParams, new GetTrashedFolderByIdHeaders());
  }

  public TrashFolder getTrashedFolderById(String folderId, GetTrashedFolderByIdHeaders headers) {
    return getTrashedFolderById(folderId, new GetTrashedFolderByIdQueryParams(), headers);
  }

  public TrashFolder getTrashedFolderById(
      String folderId,
      GetTrashedFolderByIdQueryParams queryParams,
      GetTrashedFolderByIdHeaders headers) {
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
                            "/2.0/folders/",
                            convertToString(folderId),
                            "/trash"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TrashFolder.class);
  }

  public void deleteTrashedFolderById(String folderId) {
    deleteTrashedFolderById(folderId, new DeleteTrashedFolderByIdHeaders());
  }

  public void deleteTrashedFolderById(String folderId, DeleteTrashedFolderByIdHeaders headers) {
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
                            "/trash"),
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

    public TrashedFoldersManager build() {
      return new TrashedFoldersManager(this);
    }
  }
}
