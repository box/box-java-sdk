package com.box.sdkgen.managers.trashedfiles;

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
import com.box.sdkgen.schemas.trashfile.TrashFile;
import com.box.sdkgen.schemas.trashfilerestored.TrashFileRestored;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class TrashedFilesManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public TrashedFilesManager() {
    this.networkSession = new NetworkSession();
  }

  protected TrashedFilesManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Restores a file that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the file to in case the original folder
   * has been deleted.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public TrashFileRestored restoreFileFromTrash(String fileId) {
    return restoreFileFromTrash(
        fileId,
        new RestoreFileFromTrashRequestBody(),
        new RestoreFileFromTrashQueryParams(),
        new RestoreFileFromTrashHeaders());
  }

  /**
   * Restores a file that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the file to in case the original folder
   * has been deleted.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of restoreFileFromTrash method
   */
  public TrashFileRestored restoreFileFromTrash(
      String fileId, RestoreFileFromTrashRequestBody requestBody) {
    return restoreFileFromTrash(
        fileId,
        requestBody,
        new RestoreFileFromTrashQueryParams(),
        new RestoreFileFromTrashHeaders());
  }

  /**
   * Restores a file that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the file to in case the original folder
   * has been deleted.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of restoreFileFromTrash method
   */
  public TrashFileRestored restoreFileFromTrash(
      String fileId, RestoreFileFromTrashQueryParams queryParams) {
    return restoreFileFromTrash(
        fileId,
        new RestoreFileFromTrashRequestBody(),
        queryParams,
        new RestoreFileFromTrashHeaders());
  }

  /**
   * Restores a file that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the file to in case the original folder
   * has been deleted.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of restoreFileFromTrash method
   * @param queryParams Query parameters of restoreFileFromTrash method
   */
  public TrashFileRestored restoreFileFromTrash(
      String fileId,
      RestoreFileFromTrashRequestBody requestBody,
      RestoreFileFromTrashQueryParams queryParams) {
    return restoreFileFromTrash(
        fileId, requestBody, queryParams, new RestoreFileFromTrashHeaders());
  }

  /**
   * Restores a file that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the file to in case the original folder
   * has been deleted.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of restoreFileFromTrash method
   */
  public TrashFileRestored restoreFileFromTrash(
      String fileId, RestoreFileFromTrashHeaders headers) {
    return restoreFileFromTrash(
        fileId,
        new RestoreFileFromTrashRequestBody(),
        new RestoreFileFromTrashQueryParams(),
        headers);
  }

  /**
   * Restores a file that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the file to in case the original folder
   * has been deleted.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of restoreFileFromTrash method
   * @param headers Headers of restoreFileFromTrash method
   */
  public TrashFileRestored restoreFileFromTrash(
      String fileId,
      RestoreFileFromTrashRequestBody requestBody,
      RestoreFileFromTrashHeaders headers) {
    return restoreFileFromTrash(
        fileId, requestBody, new RestoreFileFromTrashQueryParams(), headers);
  }

  /**
   * Restores a file that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the file to in case the original folder
   * has been deleted.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of restoreFileFromTrash method
   * @param headers Headers of restoreFileFromTrash method
   */
  public TrashFileRestored restoreFileFromTrash(
      String fileId,
      RestoreFileFromTrashQueryParams queryParams,
      RestoreFileFromTrashHeaders headers) {
    return restoreFileFromTrash(
        fileId, new RestoreFileFromTrashRequestBody(), queryParams, headers);
  }

  /**
   * Restores a file that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the file to in case the original folder
   * has been deleted.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of restoreFileFromTrash method
   * @param queryParams Query parameters of restoreFileFromTrash method
   * @param headers Headers of restoreFileFromTrash method
   */
  public TrashFileRestored restoreFileFromTrash(
      String fileId,
      RestoreFileFromTrashRequestBody requestBody,
      RestoreFileFromTrashQueryParams queryParams,
      RestoreFileFromTrashHeaders headers) {
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
                            "/2.0/files/",
                            convertToString(fileId)),
                        "POST")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TrashFileRestored.class);
  }

  /**
   * Retrieves a file that has been moved to the trash.
   *
   * <p>Please note that only if the file itself has been moved to the trash can it be retrieved
   * with this API call. If instead one of its parent folders was moved to the trash, only that
   * folder can be inspected using the [`GET
   * /folders/:id/trash`](https://developer.box.com/reference/get-folders-id-trash) API.
   *
   * <p>To list all items that have been moved to the trash, please use the [`GET
   * /folders/trash/items`](https://developer.box.com/reference/get-folders-trash-items/) API.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public TrashFile getTrashedFileById(String fileId) {
    return getTrashedFileById(
        fileId, new GetTrashedFileByIdQueryParams(), new GetTrashedFileByIdHeaders());
  }

  /**
   * Retrieves a file that has been moved to the trash.
   *
   * <p>Please note that only if the file itself has been moved to the trash can it be retrieved
   * with this API call. If instead one of its parent folders was moved to the trash, only that
   * folder can be inspected using the [`GET
   * /folders/:id/trash`](https://developer.box.com/reference/get-folders-id-trash) API.
   *
   * <p>To list all items that have been moved to the trash, please use the [`GET
   * /folders/trash/items`](https://developer.box.com/reference/get-folders-trash-items/) API.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getTrashedFileById method
   */
  public TrashFile getTrashedFileById(String fileId, GetTrashedFileByIdQueryParams queryParams) {
    return getTrashedFileById(fileId, queryParams, new GetTrashedFileByIdHeaders());
  }

  /**
   * Retrieves a file that has been moved to the trash.
   *
   * <p>Please note that only if the file itself has been moved to the trash can it be retrieved
   * with this API call. If instead one of its parent folders was moved to the trash, only that
   * folder can be inspected using the [`GET
   * /folders/:id/trash`](https://developer.box.com/reference/get-folders-id-trash) API.
   *
   * <p>To list all items that have been moved to the trash, please use the [`GET
   * /folders/trash/items`](https://developer.box.com/reference/get-folders-trash-items/) API.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of getTrashedFileById method
   */
  public TrashFile getTrashedFileById(String fileId, GetTrashedFileByIdHeaders headers) {
    return getTrashedFileById(fileId, new GetTrashedFileByIdQueryParams(), headers);
  }

  /**
   * Retrieves a file that has been moved to the trash.
   *
   * <p>Please note that only if the file itself has been moved to the trash can it be retrieved
   * with this API call. If instead one of its parent folders was moved to the trash, only that
   * folder can be inspected using the [`GET
   * /folders/:id/trash`](https://developer.box.com/reference/get-folders-id-trash) API.
   *
   * <p>To list all items that have been moved to the trash, please use the [`GET
   * /folders/trash/items`](https://developer.box.com/reference/get-folders-trash-items/) API.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getTrashedFileById method
   * @param headers Headers of getTrashedFileById method
   */
  public TrashFile getTrashedFileById(
      String fileId, GetTrashedFileByIdQueryParams queryParams, GetTrashedFileByIdHeaders headers) {
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
                            "/2.0/files/",
                            convertToString(fileId),
                            "/trash"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TrashFile.class);
  }

  /**
   * Permanently deletes a file that is in the trash. This action cannot be undone.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public void deleteTrashedFileById(String fileId) {
    deleteTrashedFileById(fileId, new DeleteTrashedFileByIdHeaders());
  }

  /**
   * Permanently deletes a file that is in the trash. This action cannot be undone.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of deleteTrashedFileById method
   */
  public void deleteTrashedFileById(String fileId, DeleteTrashedFileByIdHeaders headers) {
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

    public TrashedFilesManager build() {
      return new TrashedFilesManager(this);
    }
  }
}
