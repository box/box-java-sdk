package com.box.sdkgen.managers.folderlocks;

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
import com.box.sdkgen.schemas.folderlock.FolderLock;
import com.box.sdkgen.schemas.folderlocks.FolderLocks;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class FolderLocksManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public FolderLocksManager() {
    this.networkSession = new NetworkSession();
  }

  protected FolderLocksManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves folder lock details for a given folder.
   *
   * <p>You must be authenticated as the owner or co-owner of the folder to use this endpoint.
   *
   * @param queryParams Query parameters of getFolderLocks method
   */
  public FolderLocks getFolderLocks(GetFolderLocksQueryParams queryParams) {
    return getFolderLocks(queryParams, new GetFolderLocksHeaders());
  }

  /**
   * Retrieves folder lock details for a given folder.
   *
   * <p>You must be authenticated as the owner or co-owner of the folder to use this endpoint.
   *
   * @param queryParams Query parameters of getFolderLocks method
   * @param headers Headers of getFolderLocks method
   */
  public FolderLocks getFolderLocks(
      GetFolderLocksQueryParams queryParams, GetFolderLocksHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("folder_id", convertToString(queryParams.getFolderId()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/folder_locks"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FolderLocks.class);
  }

  /**
   * Creates a folder lock on a folder, preventing it from being moved and/or deleted.
   *
   * <p>You must be authenticated as the owner or co-owner of the folder to use this endpoint.
   *
   * @param requestBody Request body of createFolderLock method
   */
  public FolderLock createFolderLock(CreateFolderLockRequestBody requestBody) {
    return createFolderLock(requestBody, new CreateFolderLockHeaders());
  }

  /**
   * Creates a folder lock on a folder, preventing it from being moved and/or deleted.
   *
   * <p>You must be authenticated as the owner or co-owner of the folder to use this endpoint.
   *
   * @param requestBody Request body of createFolderLock method
   * @param headers Headers of createFolderLock method
   */
  public FolderLock createFolderLock(
      CreateFolderLockRequestBody requestBody, CreateFolderLockHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/folder_locks"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FolderLock.class);
  }

  /**
   * Deletes a folder lock on a given folder.
   *
   * <p>You must be authenticated as the owner or co-owner of the folder to use this endpoint.
   *
   * @param folderLockId The ID of the folder lock. Example: "12345"
   */
  public void deleteFolderLockById(String folderLockId) {
    deleteFolderLockById(folderLockId, new DeleteFolderLockByIdHeaders());
  }

  /**
   * Deletes a folder lock on a given folder.
   *
   * <p>You must be authenticated as the owner or co-owner of the folder to use this endpoint.
   *
   * @param folderLockId The ID of the folder lock. Example: "12345"
   * @param headers Headers of deleteFolderLockById method
   */
  public void deleteFolderLockById(String folderLockId, DeleteFolderLockByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/folder_locks/",
                            convertToString(folderLockId)),
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

    public Builder() {}

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public FolderLocksManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new FolderLocksManager(this);
    }
  }
}
