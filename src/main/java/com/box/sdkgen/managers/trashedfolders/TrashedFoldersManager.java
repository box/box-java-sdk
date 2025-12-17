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

  /**
   * Restores a folder that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the folder to in case the original
   * folder has been deleted.
   *
   * <p>During this operation, part of the file tree will be locked, mainly the source folder and
   * all of its descendants, as well as the destination folder.
   *
   * <p>For the duration of the operation, no other move, copy, delete, or restore operation can
   * performed on any of the locked folders.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   */
  public TrashFolderRestored restoreFolderFromTrash(String folderId) {
    return restoreFolderFromTrash(
        folderId,
        new RestoreFolderFromTrashRequestBody(),
        new RestoreFolderFromTrashQueryParams(),
        new RestoreFolderFromTrashHeaders());
  }

  /**
   * Restores a folder that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the folder to in case the original
   * folder has been deleted.
   *
   * <p>During this operation, part of the file tree will be locked, mainly the source folder and
   * all of its descendants, as well as the destination folder.
   *
   * <p>For the duration of the operation, no other move, copy, delete, or restore operation can
   * performed on any of the locked folders.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param requestBody Request body of restoreFolderFromTrash method
   */
  public TrashFolderRestored restoreFolderFromTrash(
      String folderId, RestoreFolderFromTrashRequestBody requestBody) {
    return restoreFolderFromTrash(
        folderId,
        requestBody,
        new RestoreFolderFromTrashQueryParams(),
        new RestoreFolderFromTrashHeaders());
  }

  /**
   * Restores a folder that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the folder to in case the original
   * folder has been deleted.
   *
   * <p>During this operation, part of the file tree will be locked, mainly the source folder and
   * all of its descendants, as well as the destination folder.
   *
   * <p>For the duration of the operation, no other move, copy, delete, or restore operation can
   * performed on any of the locked folders.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param queryParams Query parameters of restoreFolderFromTrash method
   */
  public TrashFolderRestored restoreFolderFromTrash(
      String folderId, RestoreFolderFromTrashQueryParams queryParams) {
    return restoreFolderFromTrash(
        folderId,
        new RestoreFolderFromTrashRequestBody(),
        queryParams,
        new RestoreFolderFromTrashHeaders());
  }

  /**
   * Restores a folder that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the folder to in case the original
   * folder has been deleted.
   *
   * <p>During this operation, part of the file tree will be locked, mainly the source folder and
   * all of its descendants, as well as the destination folder.
   *
   * <p>For the duration of the operation, no other move, copy, delete, or restore operation can
   * performed on any of the locked folders.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param requestBody Request body of restoreFolderFromTrash method
   * @param queryParams Query parameters of restoreFolderFromTrash method
   */
  public TrashFolderRestored restoreFolderFromTrash(
      String folderId,
      RestoreFolderFromTrashRequestBody requestBody,
      RestoreFolderFromTrashQueryParams queryParams) {
    return restoreFolderFromTrash(
        folderId, requestBody, queryParams, new RestoreFolderFromTrashHeaders());
  }

  /**
   * Restores a folder that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the folder to in case the original
   * folder has been deleted.
   *
   * <p>During this operation, part of the file tree will be locked, mainly the source folder and
   * all of its descendants, as well as the destination folder.
   *
   * <p>For the duration of the operation, no other move, copy, delete, or restore operation can
   * performed on any of the locked folders.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param headers Headers of restoreFolderFromTrash method
   */
  public TrashFolderRestored restoreFolderFromTrash(
      String folderId, RestoreFolderFromTrashHeaders headers) {
    return restoreFolderFromTrash(
        folderId,
        new RestoreFolderFromTrashRequestBody(),
        new RestoreFolderFromTrashQueryParams(),
        headers);
  }

  /**
   * Restores a folder that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the folder to in case the original
   * folder has been deleted.
   *
   * <p>During this operation, part of the file tree will be locked, mainly the source folder and
   * all of its descendants, as well as the destination folder.
   *
   * <p>For the duration of the operation, no other move, copy, delete, or restore operation can
   * performed on any of the locked folders.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param requestBody Request body of restoreFolderFromTrash method
   * @param headers Headers of restoreFolderFromTrash method
   */
  public TrashFolderRestored restoreFolderFromTrash(
      String folderId,
      RestoreFolderFromTrashRequestBody requestBody,
      RestoreFolderFromTrashHeaders headers) {
    return restoreFolderFromTrash(
        folderId, requestBody, new RestoreFolderFromTrashQueryParams(), headers);
  }

  /**
   * Restores a folder that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the folder to in case the original
   * folder has been deleted.
   *
   * <p>During this operation, part of the file tree will be locked, mainly the source folder and
   * all of its descendants, as well as the destination folder.
   *
   * <p>For the duration of the operation, no other move, copy, delete, or restore operation can
   * performed on any of the locked folders.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param queryParams Query parameters of restoreFolderFromTrash method
   * @param headers Headers of restoreFolderFromTrash method
   */
  public TrashFolderRestored restoreFolderFromTrash(
      String folderId,
      RestoreFolderFromTrashQueryParams queryParams,
      RestoreFolderFromTrashHeaders headers) {
    return restoreFolderFromTrash(
        folderId, new RestoreFolderFromTrashRequestBody(), queryParams, headers);
  }

  /**
   * Restores a folder that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the folder to in case the original
   * folder has been deleted.
   *
   * <p>During this operation, part of the file tree will be locked, mainly the source folder and
   * all of its descendants, as well as the destination folder.
   *
   * <p>For the duration of the operation, no other move, copy, delete, or restore operation can
   * performed on any of the locked folders.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param requestBody Request body of restoreFolderFromTrash method
   * @param queryParams Query parameters of restoreFolderFromTrash method
   * @param headers Headers of restoreFolderFromTrash method
   */
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

  /**
   * Retrieves a folder that has been moved to the trash.
   *
   * <p>Please note that only if the folder itself has been moved to the trash can it be retrieved
   * with this API call. If instead one of its parent folders was moved to the trash, only that
   * folder can be inspected using the [`GET
   * /folders/:id/trash`](https://developer.box.com/reference/get-folders-id-trash) API.
   *
   * <p>To list all items that have been moved to the trash, please use the [`GET
   * /folders/trash/items`](https://developer.box.com/reference/get-folders-trash-items/) API.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   */
  public TrashFolder getTrashedFolderById(String folderId) {
    return getTrashedFolderById(
        folderId, new GetTrashedFolderByIdQueryParams(), new GetTrashedFolderByIdHeaders());
  }

  /**
   * Retrieves a folder that has been moved to the trash.
   *
   * <p>Please note that only if the folder itself has been moved to the trash can it be retrieved
   * with this API call. If instead one of its parent folders was moved to the trash, only that
   * folder can be inspected using the [`GET
   * /folders/:id/trash`](https://developer.box.com/reference/get-folders-id-trash) API.
   *
   * <p>To list all items that have been moved to the trash, please use the [`GET
   * /folders/trash/items`](https://developer.box.com/reference/get-folders-trash-items/) API.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param queryParams Query parameters of getTrashedFolderById method
   */
  public TrashFolder getTrashedFolderById(
      String folderId, GetTrashedFolderByIdQueryParams queryParams) {
    return getTrashedFolderById(folderId, queryParams, new GetTrashedFolderByIdHeaders());
  }

  /**
   * Retrieves a folder that has been moved to the trash.
   *
   * <p>Please note that only if the folder itself has been moved to the trash can it be retrieved
   * with this API call. If instead one of its parent folders was moved to the trash, only that
   * folder can be inspected using the [`GET
   * /folders/:id/trash`](https://developer.box.com/reference/get-folders-id-trash) API.
   *
   * <p>To list all items that have been moved to the trash, please use the [`GET
   * /folders/trash/items`](https://developer.box.com/reference/get-folders-trash-items/) API.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param headers Headers of getTrashedFolderById method
   */
  public TrashFolder getTrashedFolderById(String folderId, GetTrashedFolderByIdHeaders headers) {
    return getTrashedFolderById(folderId, new GetTrashedFolderByIdQueryParams(), headers);
  }

  /**
   * Retrieves a folder that has been moved to the trash.
   *
   * <p>Please note that only if the folder itself has been moved to the trash can it be retrieved
   * with this API call. If instead one of its parent folders was moved to the trash, only that
   * folder can be inspected using the [`GET
   * /folders/:id/trash`](https://developer.box.com/reference/get-folders-id-trash) API.
   *
   * <p>To list all items that have been moved to the trash, please use the [`GET
   * /folders/trash/items`](https://developer.box.com/reference/get-folders-trash-items/) API.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param queryParams Query parameters of getTrashedFolderById method
   * @param headers Headers of getTrashedFolderById method
   */
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

  /**
   * Permanently deletes a folder that is in the trash. This action cannot be undone.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   */
  public void deleteTrashedFolderById(String folderId) {
    deleteTrashedFolderById(folderId, new DeleteTrashedFolderByIdHeaders());
  }

  /**
   * Permanently deletes a folder that is in the trash. This action cannot be undone.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param headers Headers of deleteTrashedFolderById method
   */
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
