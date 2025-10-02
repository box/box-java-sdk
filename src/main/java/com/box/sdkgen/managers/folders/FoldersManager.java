package com.box.sdkgen.managers.folders;

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
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.items.Items;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class FoldersManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public FoldersManager() {
    this.networkSession = new NetworkSession();
  }

  protected FoldersManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves details for a folder, including the first 100 entries in the folder.
   *
   * <p>Passing `sort`, `direction`, `offset`, and `limit` parameters in query allows you to manage
   * the list of returned [folder items](r://folder--full#param-item-collection).
   *
   * <p>To fetch more items within the folder, use the [Get items in a
   * folder](e://get-folders-id-items) endpoint.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   */
  public FolderFull getFolderById(String folderId) {
    return getFolderById(folderId, new GetFolderByIdQueryParams(), new GetFolderByIdHeaders());
  }

  /**
   * Retrieves details for a folder, including the first 100 entries in the folder.
   *
   * <p>Passing `sort`, `direction`, `offset`, and `limit` parameters in query allows you to manage
   * the list of returned [folder items](r://folder--full#param-item-collection).
   *
   * <p>To fetch more items within the folder, use the [Get items in a
   * folder](e://get-folders-id-items) endpoint.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param queryParams Query parameters of getFolderById method
   */
  public FolderFull getFolderById(String folderId, GetFolderByIdQueryParams queryParams) {
    return getFolderById(folderId, queryParams, new GetFolderByIdHeaders());
  }

  /**
   * Retrieves details for a folder, including the first 100 entries in the folder.
   *
   * <p>Passing `sort`, `direction`, `offset`, and `limit` parameters in query allows you to manage
   * the list of returned [folder items](r://folder--full#param-item-collection).
   *
   * <p>To fetch more items within the folder, use the [Get items in a
   * folder](e://get-folders-id-items) endpoint.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param headers Headers of getFolderById method
   */
  public FolderFull getFolderById(String folderId, GetFolderByIdHeaders headers) {
    return getFolderById(folderId, new GetFolderByIdQueryParams(), headers);
  }

  /**
   * Retrieves details for a folder, including the first 100 entries in the folder.
   *
   * <p>Passing `sort`, `direction`, `offset`, and `limit` parameters in query allows you to manage
   * the list of returned [folder items](r://folder--full#param-item-collection).
   *
   * <p>To fetch more items within the folder, use the [Get items in a
   * folder](e://get-folders-id-items) endpoint.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param queryParams Query parameters of getFolderById method
   * @param headers Headers of getFolderById method
   */
  public FolderFull getFolderById(
      String folderId, GetFolderByIdQueryParams queryParams, GetFolderByIdHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("sort", convertToString(queryParams.getSort())),
                entryOf("direction", convertToString(queryParams.getDirection())),
                entryOf("offset", convertToString(queryParams.getOffset())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(
                    entryOf("if-none-match", convertToString(headers.getIfNoneMatch())),
                    entryOf("boxapi", convertToString(headers.getBoxapi()))),
                headers.getExtraHeaders()));
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
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FolderFull.class);
  }

  /**
   * Updates a folder. This can be also be used to move the folder, create shared links, update
   * collaborations, and more.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   */
  public FolderFull updateFolderById(String folderId) {
    return updateFolderById(
        folderId,
        new UpdateFolderByIdRequestBody(),
        new UpdateFolderByIdQueryParams(),
        new UpdateFolderByIdHeaders());
  }

  /**
   * Updates a folder. This can be also be used to move the folder, create shared links, update
   * collaborations, and more.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param requestBody Request body of updateFolderById method
   */
  public FolderFull updateFolderById(String folderId, UpdateFolderByIdRequestBody requestBody) {
    return updateFolderById(
        folderId, requestBody, new UpdateFolderByIdQueryParams(), new UpdateFolderByIdHeaders());
  }

  /**
   * Updates a folder. This can be also be used to move the folder, create shared links, update
   * collaborations, and more.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param queryParams Query parameters of updateFolderById method
   */
  public FolderFull updateFolderById(String folderId, UpdateFolderByIdQueryParams queryParams) {
    return updateFolderById(
        folderId, new UpdateFolderByIdRequestBody(), queryParams, new UpdateFolderByIdHeaders());
  }

  /**
   * Updates a folder. This can be also be used to move the folder, create shared links, update
   * collaborations, and more.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param requestBody Request body of updateFolderById method
   * @param queryParams Query parameters of updateFolderById method
   */
  public FolderFull updateFolderById(
      String folderId,
      UpdateFolderByIdRequestBody requestBody,
      UpdateFolderByIdQueryParams queryParams) {
    return updateFolderById(folderId, requestBody, queryParams, new UpdateFolderByIdHeaders());
  }

  /**
   * Updates a folder. This can be also be used to move the folder, create shared links, update
   * collaborations, and more.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param headers Headers of updateFolderById method
   */
  public FolderFull updateFolderById(String folderId, UpdateFolderByIdHeaders headers) {
    return updateFolderById(
        folderId, new UpdateFolderByIdRequestBody(), new UpdateFolderByIdQueryParams(), headers);
  }

  /**
   * Updates a folder. This can be also be used to move the folder, create shared links, update
   * collaborations, and more.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param requestBody Request body of updateFolderById method
   * @param headers Headers of updateFolderById method
   */
  public FolderFull updateFolderById(
      String folderId, UpdateFolderByIdRequestBody requestBody, UpdateFolderByIdHeaders headers) {
    return updateFolderById(folderId, requestBody, new UpdateFolderByIdQueryParams(), headers);
  }

  /**
   * Updates a folder. This can be also be used to move the folder, create shared links, update
   * collaborations, and more.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param queryParams Query parameters of updateFolderById method
   * @param headers Headers of updateFolderById method
   */
  public FolderFull updateFolderById(
      String folderId, UpdateFolderByIdQueryParams queryParams, UpdateFolderByIdHeaders headers) {
    return updateFolderById(folderId, new UpdateFolderByIdRequestBody(), queryParams, headers);
  }

  /**
   * Updates a folder. This can be also be used to move the folder, create shared links, update
   * collaborations, and more.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param requestBody Request body of updateFolderById method
   * @param queryParams Query parameters of updateFolderById method
   * @param headers Headers of updateFolderById method
   */
  public FolderFull updateFolderById(
      String folderId,
      UpdateFolderByIdRequestBody requestBody,
      UpdateFolderByIdQueryParams queryParams,
      UpdateFolderByIdHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("if-match", convertToString(headers.getIfMatch()))),
                headers.getExtraHeaders()));
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
                        "PUT")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FolderFull.class);
  }

  /**
   * Deletes a folder, either permanently or by moving it to the trash.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   */
  public void deleteFolderById(String folderId) {
    deleteFolderById(folderId, new DeleteFolderByIdQueryParams(), new DeleteFolderByIdHeaders());
  }

  /**
   * Deletes a folder, either permanently or by moving it to the trash.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param queryParams Query parameters of deleteFolderById method
   */
  public void deleteFolderById(String folderId, DeleteFolderByIdQueryParams queryParams) {
    deleteFolderById(folderId, queryParams, new DeleteFolderByIdHeaders());
  }

  /**
   * Deletes a folder, either permanently or by moving it to the trash.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param headers Headers of deleteFolderById method
   */
  public void deleteFolderById(String folderId, DeleteFolderByIdHeaders headers) {
    deleteFolderById(folderId, new DeleteFolderByIdQueryParams(), headers);
  }

  /**
   * Deletes a folder, either permanently or by moving it to the trash.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param queryParams Query parameters of deleteFolderById method
   * @param headers Headers of deleteFolderById method
   */
  public void deleteFolderById(
      String folderId, DeleteFolderByIdQueryParams queryParams, DeleteFolderByIdHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("recursive", convertToString(queryParams.getRecursive()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("if-match", convertToString(headers.getIfMatch()))),
                headers.getExtraHeaders()));
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
                        "DELETE")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Retrieves a page of items in a folder. These items can be files, folders, and web links.
   *
   * <p>To request more information about the folder itself, like its size, use the [Get a
   * folder](#get-folders-id) endpoint instead.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   */
  public Items getFolderItems(String folderId) {
    return getFolderItems(folderId, new GetFolderItemsQueryParams(), new GetFolderItemsHeaders());
  }

  /**
   * Retrieves a page of items in a folder. These items can be files, folders, and web links.
   *
   * <p>To request more information about the folder itself, like its size, use the [Get a
   * folder](#get-folders-id) endpoint instead.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param queryParams Query parameters of getFolderItems method
   */
  public Items getFolderItems(String folderId, GetFolderItemsQueryParams queryParams) {
    return getFolderItems(folderId, queryParams, new GetFolderItemsHeaders());
  }

  /**
   * Retrieves a page of items in a folder. These items can be files, folders, and web links.
   *
   * <p>To request more information about the folder itself, like its size, use the [Get a
   * folder](#get-folders-id) endpoint instead.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param headers Headers of getFolderItems method
   */
  public Items getFolderItems(String folderId, GetFolderItemsHeaders headers) {
    return getFolderItems(folderId, new GetFolderItemsQueryParams(), headers);
  }

  /**
   * Retrieves a page of items in a folder. These items can be files, folders, and web links.
   *
   * <p>To request more information about the folder itself, like its size, use the [Get a
   * folder](#get-folders-id) endpoint instead.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param queryParams Query parameters of getFolderItems method
   * @param headers Headers of getFolderItems method
   */
  public Items getFolderItems(
      String folderId, GetFolderItemsQueryParams queryParams, GetFolderItemsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("usemarker", convertToString(queryParams.getUsemarker())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("offset", convertToString(queryParams.getOffset())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("sort", convertToString(queryParams.getSort())),
                entryOf("direction", convertToString(queryParams.getDirection()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("boxapi", convertToString(headers.getBoxapi()))),
                headers.getExtraHeaders()));
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
                            "/items"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Items.class);
  }

  /**
   * Creates a new empty folder within the specified parent folder.
   *
   * @param requestBody Request body of createFolder method
   */
  public FolderFull createFolder(CreateFolderRequestBody requestBody) {
    return createFolder(requestBody, new CreateFolderQueryParams(), new CreateFolderHeaders());
  }

  /**
   * Creates a new empty folder within the specified parent folder.
   *
   * @param requestBody Request body of createFolder method
   * @param queryParams Query parameters of createFolder method
   */
  public FolderFull createFolder(
      CreateFolderRequestBody requestBody, CreateFolderQueryParams queryParams) {
    return createFolder(requestBody, queryParams, new CreateFolderHeaders());
  }

  /**
   * Creates a new empty folder within the specified parent folder.
   *
   * @param requestBody Request body of createFolder method
   * @param headers Headers of createFolder method
   */
  public FolderFull createFolder(CreateFolderRequestBody requestBody, CreateFolderHeaders headers) {
    return createFolder(requestBody, new CreateFolderQueryParams(), headers);
  }

  /**
   * Creates a new empty folder within the specified parent folder.
   *
   * @param requestBody Request body of createFolder method
   * @param queryParams Query parameters of createFolder method
   * @param headers Headers of createFolder method
   */
  public FolderFull createFolder(
      CreateFolderRequestBody requestBody,
      CreateFolderQueryParams queryParams,
      CreateFolderHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/folders"),
                        "POST")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FolderFull.class);
  }

  /**
   * Creates a copy of a folder within a destination folder.
   *
   * <p>The original folder will not be changed.
   *
   * @param folderId The unique identifier of the folder to copy.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder with the ID `0` can not be copied. Example: "0"
   * @param requestBody Request body of copyFolder method
   */
  public FolderFull copyFolder(String folderId, CopyFolderRequestBody requestBody) {
    return copyFolder(folderId, requestBody, new CopyFolderQueryParams(), new CopyFolderHeaders());
  }

  /**
   * Creates a copy of a folder within a destination folder.
   *
   * <p>The original folder will not be changed.
   *
   * @param folderId The unique identifier of the folder to copy.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder with the ID `0` can not be copied. Example: "0"
   * @param requestBody Request body of copyFolder method
   * @param queryParams Query parameters of copyFolder method
   */
  public FolderFull copyFolder(
      String folderId, CopyFolderRequestBody requestBody, CopyFolderQueryParams queryParams) {
    return copyFolder(folderId, requestBody, queryParams, new CopyFolderHeaders());
  }

  /**
   * Creates a copy of a folder within a destination folder.
   *
   * <p>The original folder will not be changed.
   *
   * @param folderId The unique identifier of the folder to copy.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder with the ID `0` can not be copied. Example: "0"
   * @param requestBody Request body of copyFolder method
   * @param headers Headers of copyFolder method
   */
  public FolderFull copyFolder(
      String folderId, CopyFolderRequestBody requestBody, CopyFolderHeaders headers) {
    return copyFolder(folderId, requestBody, new CopyFolderQueryParams(), headers);
  }

  /**
   * Creates a copy of a folder within a destination folder.
   *
   * <p>The original folder will not be changed.
   *
   * @param folderId The unique identifier of the folder to copy.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder with the ID `0` can not be copied. Example: "0"
   * @param requestBody Request body of copyFolder method
   * @param queryParams Query parameters of copyFolder method
   * @param headers Headers of copyFolder method
   */
  public FolderFull copyFolder(
      String folderId,
      CopyFolderRequestBody requestBody,
      CopyFolderQueryParams queryParams,
      CopyFolderHeaders headers) {
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
                            "/copy"),
                        "POST")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FolderFull.class);
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

    public FoldersManager build() {
      return new FoldersManager(this);
    }
  }
}
