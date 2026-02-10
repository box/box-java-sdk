package com.box.sdkgen.managers.foldermetadata;

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
import com.box.sdkgen.schemas.metadatafull.MetadataFull;
import com.box.sdkgen.schemas.metadatas.Metadatas;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.List;
import java.util.Map;

public class FolderMetadataManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public FolderMetadataManager() {
    this.networkSession = new NetworkSession();
  }

  protected FolderMetadataManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves all metadata for a given folder. This can not be used on the root folder with ID `0`.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   */
  public Metadatas getFolderMetadata(String folderId) {
    return getFolderMetadata(
        folderId, new GetFolderMetadataQueryParams(), new GetFolderMetadataHeaders());
  }

  /**
   * Retrieves all metadata for a given folder. This can not be used on the root folder with ID `0`.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param queryParams Query parameters of getFolderMetadata method
   */
  public Metadatas getFolderMetadata(String folderId, GetFolderMetadataQueryParams queryParams) {
    return getFolderMetadata(folderId, queryParams, new GetFolderMetadataHeaders());
  }

  /**
   * Retrieves all metadata for a given folder. This can not be used on the root folder with ID `0`.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param headers Headers of getFolderMetadata method
   */
  public Metadatas getFolderMetadata(String folderId, GetFolderMetadataHeaders headers) {
    return getFolderMetadata(folderId, new GetFolderMetadataQueryParams(), headers);
  }

  /**
   * Retrieves all metadata for a given folder. This can not be used on the root folder with ID `0`.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param queryParams Query parameters of getFolderMetadata method
   * @param headers Headers of getFolderMetadata method
   */
  public Metadatas getFolderMetadata(
      String folderId, GetFolderMetadataQueryParams queryParams, GetFolderMetadataHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("view", convertToString(queryParams.getView()))));
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
                            "/metadata"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Metadatas.class);
  }

  /**
   * Retrieves the instance of a metadata template that has been applied to a folder. This can not
   * be used on the root folder with ID `0`.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   */
  public MetadataFull getFolderMetadataById(
      String folderId, GetFolderMetadataByIdScope scope, String templateKey) {
    return getFolderMetadataById(folderId, scope, templateKey, new GetFolderMetadataByIdHeaders());
  }

  /**
   * Retrieves the instance of a metadata template that has been applied to a folder. This can not
   * be used on the root folder with ID `0`.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param headers Headers of getFolderMetadataById method
   */
  public MetadataFull getFolderMetadataById(
      String folderId,
      GetFolderMetadataByIdScope scope,
      String templateKey,
      GetFolderMetadataByIdHeaders headers) {
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
                            "/metadata/",
                            convertToString(scope),
                            "/",
                            convertToString(templateKey)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataFull.class);
  }

  /**
   * Applies an instance of a metadata template to a folder.
   *
   * <p>In most cases only values that are present in the metadata template will be accepted, except
   * for the `global.properties` template which accepts any key-value pair.
   *
   * <p>To display the metadata template in the Box web app the enterprise needs to be configured to
   * enable **Cascading Folder Level Metadata** for the user in the admin console.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param requestBody Request body of createFolderMetadataById method
   */
  public MetadataFull createFolderMetadataById(
      String folderId,
      CreateFolderMetadataByIdScope scope,
      String templateKey,
      Map<String, Object> requestBody) {
    return createFolderMetadataById(
        folderId, scope, templateKey, requestBody, new CreateFolderMetadataByIdHeaders());
  }

  /**
   * Applies an instance of a metadata template to a folder.
   *
   * <p>In most cases only values that are present in the metadata template will be accepted, except
   * for the `global.properties` template which accepts any key-value pair.
   *
   * <p>To display the metadata template in the Box web app the enterprise needs to be configured to
   * enable **Cascading Folder Level Metadata** for the user in the admin console.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param requestBody Request body of createFolderMetadataById method
   * @param headers Headers of createFolderMetadataById method
   */
  public MetadataFull createFolderMetadataById(
      String folderId,
      CreateFolderMetadataByIdScope scope,
      String templateKey,
      Map<String, Object> requestBody,
      CreateFolderMetadataByIdHeaders headers) {
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
                            "/metadata/",
                            convertToString(scope),
                            "/",
                            convertToString(templateKey)),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataFull.class);
  }

  /**
   * Updates a piece of metadata on a folder.
   *
   * <p>The metadata instance can only be updated if the template has already been applied to the
   * folder before. When editing metadata, only values that match the metadata template schema will
   * be accepted.
   *
   * <p>The update is applied atomically. If any errors occur during the application of the
   * operations, the metadata instance will not be changed.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param requestBody Request body of updateFolderMetadataById method
   */
  public MetadataFull updateFolderMetadataById(
      String folderId,
      UpdateFolderMetadataByIdScope scope,
      String templateKey,
      List<UpdateFolderMetadataByIdRequestBody> requestBody) {
    return updateFolderMetadataById(
        folderId, scope, templateKey, requestBody, new UpdateFolderMetadataByIdHeaders());
  }

  /**
   * Updates a piece of metadata on a folder.
   *
   * <p>The metadata instance can only be updated if the template has already been applied to the
   * folder before. When editing metadata, only values that match the metadata template schema will
   * be accepted.
   *
   * <p>The update is applied atomically. If any errors occur during the application of the
   * operations, the metadata instance will not be changed.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param requestBody Request body of updateFolderMetadataById method
   * @param headers Headers of updateFolderMetadataById method
   */
  public MetadataFull updateFolderMetadataById(
      String folderId,
      UpdateFolderMetadataByIdScope scope,
      String templateKey,
      List<UpdateFolderMetadataByIdRequestBody> requestBody,
      UpdateFolderMetadataByIdHeaders headers) {
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
                            "/metadata/",
                            convertToString(scope),
                            "/",
                            convertToString(templateKey)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json-patch+json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataFull.class);
  }

  /**
   * Deletes a piece of folder metadata.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   */
  public void deleteFolderMetadataById(
      String folderId, DeleteFolderMetadataByIdScope scope, String templateKey) {
    deleteFolderMetadataById(folderId, scope, templateKey, new DeleteFolderMetadataByIdHeaders());
  }

  /**
   * Deletes a piece of folder metadata.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param headers Headers of deleteFolderMetadataById method
   */
  public void deleteFolderMetadataById(
      String folderId,
      DeleteFolderMetadataByIdScope scope,
      String templateKey,
      DeleteFolderMetadataByIdHeaders headers) {
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
                            "/metadata/",
                            convertToString(scope),
                            "/",
                            convertToString(templateKey)),
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

    public FolderMetadataManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new FolderMetadataManager(this);
    }
  }
}
