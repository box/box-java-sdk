package com.box.sdkgen.managers.filemetadata;

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

public class FileMetadataManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public FileMetadataManager() {
    this.networkSession = new NetworkSession();
  }

  protected FileMetadataManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves all metadata for a given file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public Metadatas getFileMetadata(String fileId) {
    return getFileMetadata(fileId, new GetFileMetadataQueryParams(), new GetFileMetadataHeaders());
  }

  /**
   * Retrieves all metadata for a given file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getFileMetadata method
   */
  public Metadatas getFileMetadata(String fileId, GetFileMetadataQueryParams queryParams) {
    return getFileMetadata(fileId, queryParams, new GetFileMetadataHeaders());
  }

  /**
   * Retrieves all metadata for a given file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of getFileMetadata method
   */
  public Metadatas getFileMetadata(String fileId, GetFileMetadataHeaders headers) {
    return getFileMetadata(fileId, new GetFileMetadataQueryParams(), headers);
  }

  /**
   * Retrieves all metadata for a given file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getFileMetadata method
   * @param headers Headers of getFileMetadata method
   */
  public Metadatas getFileMetadata(
      String fileId, GetFileMetadataQueryParams queryParams, GetFileMetadataHeaders headers) {
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
                            "/2.0/files/",
                            convertToString(fileId),
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
   * Retrieves the instance of a metadata template that has been applied to a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   */
  public MetadataFull getFileMetadataById(
      String fileId, GetFileMetadataByIdScope scope, String templateKey) {
    return getFileMetadataById(
        fileId,
        scope,
        templateKey,
        new GetFileMetadataByIdQueryParams(),
        new GetFileMetadataByIdHeaders());
  }

  /**
   * Retrieves the instance of a metadata template that has been applied to a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param queryParams Query parameters of getFileMetadataById method
   */
  public MetadataFull getFileMetadataById(
      String fileId,
      GetFileMetadataByIdScope scope,
      String templateKey,
      GetFileMetadataByIdQueryParams queryParams) {
    return getFileMetadataById(
        fileId, scope, templateKey, queryParams, new GetFileMetadataByIdHeaders());
  }

  /**
   * Retrieves the instance of a metadata template that has been applied to a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param headers Headers of getFileMetadataById method
   */
  public MetadataFull getFileMetadataById(
      String fileId,
      GetFileMetadataByIdScope scope,
      String templateKey,
      GetFileMetadataByIdHeaders headers) {
    return getFileMetadataById(
        fileId, scope, templateKey, new GetFileMetadataByIdQueryParams(), headers);
  }

  /**
   * Retrieves the instance of a metadata template that has been applied to a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param queryParams Query parameters of getFileMetadataById method
   * @param headers Headers of getFileMetadataById method
   */
  public MetadataFull getFileMetadataById(
      String fileId,
      GetFileMetadataByIdScope scope,
      String templateKey,
      GetFileMetadataByIdQueryParams queryParams,
      GetFileMetadataByIdHeaders headers) {
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
                            "/2.0/files/",
                            convertToString(fileId),
                            "/metadata/",
                            convertToString(scope),
                            "/",
                            convertToString(templateKey)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataFull.class);
  }

  /**
   * Applies an instance of a metadata template to a file.
   *
   * <p>In most cases only values that are present in the metadata template will be accepted, except
   * for the `global.properties` template which accepts any key-value pair.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param requestBody Request body of createFileMetadataById method
   */
  public MetadataFull createFileMetadataById(
      String fileId,
      CreateFileMetadataByIdScope scope,
      String templateKey,
      Map<String, Object> requestBody) {
    return createFileMetadataById(
        fileId, scope, templateKey, requestBody, new CreateFileMetadataByIdHeaders());
  }

  /**
   * Applies an instance of a metadata template to a file.
   *
   * <p>In most cases only values that are present in the metadata template will be accepted, except
   * for the `global.properties` template which accepts any key-value pair.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param requestBody Request body of createFileMetadataById method
   * @param headers Headers of createFileMetadataById method
   */
  public MetadataFull createFileMetadataById(
      String fileId,
      CreateFileMetadataByIdScope scope,
      String templateKey,
      Map<String, Object> requestBody,
      CreateFileMetadataByIdHeaders headers) {
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
   * Updates a piece of metadata on a file.
   *
   * <p>The metadata instance can only be updated if the template has already been applied to the
   * file before. When editing metadata, only values that match the metadata template schema will be
   * accepted.
   *
   * <p>The update is applied atomically. If any errors occur during the application of the
   * operations, the metadata instance will not be changed.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param requestBody Request body of updateFileMetadataById method
   */
  public MetadataFull updateFileMetadataById(
      String fileId,
      UpdateFileMetadataByIdScope scope,
      String templateKey,
      List<UpdateFileMetadataByIdRequestBody> requestBody) {
    return updateFileMetadataById(
        fileId, scope, templateKey, requestBody, new UpdateFileMetadataByIdHeaders());
  }

  /**
   * Updates a piece of metadata on a file.
   *
   * <p>The metadata instance can only be updated if the template has already been applied to the
   * file before. When editing metadata, only values that match the metadata template schema will be
   * accepted.
   *
   * <p>The update is applied atomically. If any errors occur during the application of the
   * operations, the metadata instance will not be changed.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param requestBody Request body of updateFileMetadataById method
   * @param headers Headers of updateFileMetadataById method
   */
  public MetadataFull updateFileMetadataById(
      String fileId,
      UpdateFileMetadataByIdScope scope,
      String templateKey,
      List<UpdateFileMetadataByIdRequestBody> requestBody,
      UpdateFileMetadataByIdHeaders headers) {
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
   * Deletes a piece of file metadata.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   */
  public void deleteFileMetadataById(
      String fileId, DeleteFileMetadataByIdScope scope, String templateKey) {
    deleteFileMetadataById(fileId, scope, templateKey, new DeleteFileMetadataByIdHeaders());
  }

  /**
   * Deletes a piece of file metadata.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param headers Headers of deleteFileMetadataById method
   */
  public void deleteFileMetadataById(
      String fileId,
      DeleteFileMetadataByIdScope scope,
      String templateKey,
      DeleteFileMetadataByIdHeaders headers) {
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

    public FileMetadataManager build() {
      return new FileMetadataManager(this);
    }
  }
}
