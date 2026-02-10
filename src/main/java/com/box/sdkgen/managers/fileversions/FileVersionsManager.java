package com.box.sdkgen.managers.fileversions;

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
import com.box.sdkgen.schemas.fileversionfull.FileVersionFull;
import com.box.sdkgen.schemas.fileversions.FileVersions;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class FileVersionsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public FileVersionsManager() {
    this.networkSession = new NetworkSession();
  }

  protected FileVersionsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieve a list of the past versions for a file.
   *
   * <p>Versions are only tracked by Box users with premium accounts. To fetch the ID of the current
   * version of a file, use the `GET /file/:id` API.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public FileVersions getFileVersions(String fileId) {
    return getFileVersions(fileId, new GetFileVersionsQueryParams(), new GetFileVersionsHeaders());
  }

  /**
   * Retrieve a list of the past versions for a file.
   *
   * <p>Versions are only tracked by Box users with premium accounts. To fetch the ID of the current
   * version of a file, use the `GET /file/:id` API.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getFileVersions method
   */
  public FileVersions getFileVersions(String fileId, GetFileVersionsQueryParams queryParams) {
    return getFileVersions(fileId, queryParams, new GetFileVersionsHeaders());
  }

  /**
   * Retrieve a list of the past versions for a file.
   *
   * <p>Versions are only tracked by Box users with premium accounts. To fetch the ID of the current
   * version of a file, use the `GET /file/:id` API.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of getFileVersions method
   */
  public FileVersions getFileVersions(String fileId, GetFileVersionsHeaders headers) {
    return getFileVersions(fileId, new GetFileVersionsQueryParams(), headers);
  }

  /**
   * Retrieve a list of the past versions for a file.
   *
   * <p>Versions are only tracked by Box users with premium accounts. To fetch the ID of the current
   * version of a file, use the `GET /file/:id` API.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getFileVersions method
   * @param headers Headers of getFileVersions method
   */
  public FileVersions getFileVersions(
      String fileId, GetFileVersionsQueryParams queryParams, GetFileVersionsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("fields", convertToString(queryParams.getFields())),
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
                            "/2.0/files/",
                            convertToString(fileId),
                            "/versions"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileVersions.class);
  }

  /**
   * Retrieve a specific version of a file.
   *
   * <p>Versions are only tracked for Box users with premium accounts.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param fileVersionId The ID of the file version. Example: "1234"
   */
  public FileVersionFull getFileVersionById(String fileId, String fileVersionId) {
    return getFileVersionById(
        fileId,
        fileVersionId,
        new GetFileVersionByIdQueryParams(),
        new GetFileVersionByIdHeaders());
  }

  /**
   * Retrieve a specific version of a file.
   *
   * <p>Versions are only tracked for Box users with premium accounts.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param fileVersionId The ID of the file version. Example: "1234"
   * @param queryParams Query parameters of getFileVersionById method
   */
  public FileVersionFull getFileVersionById(
      String fileId, String fileVersionId, GetFileVersionByIdQueryParams queryParams) {
    return getFileVersionById(fileId, fileVersionId, queryParams, new GetFileVersionByIdHeaders());
  }

  /**
   * Retrieve a specific version of a file.
   *
   * <p>Versions are only tracked for Box users with premium accounts.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param fileVersionId The ID of the file version. Example: "1234"
   * @param headers Headers of getFileVersionById method
   */
  public FileVersionFull getFileVersionById(
      String fileId, String fileVersionId, GetFileVersionByIdHeaders headers) {
    return getFileVersionById(fileId, fileVersionId, new GetFileVersionByIdQueryParams(), headers);
  }

  /**
   * Retrieve a specific version of a file.
   *
   * <p>Versions are only tracked for Box users with premium accounts.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param fileVersionId The ID of the file version. Example: "1234"
   * @param queryParams Query parameters of getFileVersionById method
   * @param headers Headers of getFileVersionById method
   */
  public FileVersionFull getFileVersionById(
      String fileId,
      String fileVersionId,
      GetFileVersionByIdQueryParams queryParams,
      GetFileVersionByIdHeaders headers) {
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
                            "/versions/",
                            convertToString(fileVersionId)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileVersionFull.class);
  }

  /**
   * Move a file version to the trash.
   *
   * <p>Versions are only tracked for Box users with premium accounts.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param fileVersionId The ID of the file version. Example: "1234"
   */
  public void deleteFileVersionById(String fileId, String fileVersionId) {
    deleteFileVersionById(fileId, fileVersionId, new DeleteFileVersionByIdHeaders());
  }

  /**
   * Move a file version to the trash.
   *
   * <p>Versions are only tracked for Box users with premium accounts.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param fileVersionId The ID of the file version. Example: "1234"
   * @param headers Headers of deleteFileVersionById method
   */
  public void deleteFileVersionById(
      String fileId, String fileVersionId, DeleteFileVersionByIdHeaders headers) {
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
                            "/2.0/files/",
                            convertToString(fileId),
                            "/versions/",
                            convertToString(fileVersionId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Restores a specific version of a file after it was deleted. Don't use this endpoint to restore
   * Box Notes, as it works with file formats such as PDF, DOC, PPTX or similar.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param fileVersionId The ID of the file version. Example: "1234"
   */
  public FileVersionFull updateFileVersionById(String fileId, String fileVersionId) {
    return updateFileVersionById(
        fileId,
        fileVersionId,
        new UpdateFileVersionByIdRequestBody(),
        new UpdateFileVersionByIdHeaders());
  }

  /**
   * Restores a specific version of a file after it was deleted. Don't use this endpoint to restore
   * Box Notes, as it works with file formats such as PDF, DOC, PPTX or similar.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param fileVersionId The ID of the file version. Example: "1234"
   * @param requestBody Request body of updateFileVersionById method
   */
  public FileVersionFull updateFileVersionById(
      String fileId, String fileVersionId, UpdateFileVersionByIdRequestBody requestBody) {
    return updateFileVersionById(
        fileId, fileVersionId, requestBody, new UpdateFileVersionByIdHeaders());
  }

  /**
   * Restores a specific version of a file after it was deleted. Don't use this endpoint to restore
   * Box Notes, as it works with file formats such as PDF, DOC, PPTX or similar.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param fileVersionId The ID of the file version. Example: "1234"
   * @param headers Headers of updateFileVersionById method
   */
  public FileVersionFull updateFileVersionById(
      String fileId, String fileVersionId, UpdateFileVersionByIdHeaders headers) {
    return updateFileVersionById(
        fileId, fileVersionId, new UpdateFileVersionByIdRequestBody(), headers);
  }

  /**
   * Restores a specific version of a file after it was deleted. Don't use this endpoint to restore
   * Box Notes, as it works with file formats such as PDF, DOC, PPTX or similar.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param fileVersionId The ID of the file version. Example: "1234"
   * @param requestBody Request body of updateFileVersionById method
   * @param headers Headers of updateFileVersionById method
   */
  public FileVersionFull updateFileVersionById(
      String fileId,
      String fileVersionId,
      UpdateFileVersionByIdRequestBody requestBody,
      UpdateFileVersionByIdHeaders headers) {
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
                            "/versions/",
                            convertToString(fileVersionId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileVersionFull.class);
  }

  /**
   * Promote a specific version of a file.
   *
   * <p>If previous versions exist, this method can be used to promote one of the older versions to
   * the top of the version history.
   *
   * <p>This creates a new copy of the old version and puts it at the top of the versions history.
   * The file will have the exact same contents as the older version, with the same hash digest,
   * `etag`, and name as the original.
   *
   * <p>Other properties such as comments do not get updated to their former values.
   *
   * <p>Don't use this endpoint to restore Box Notes, as it works with file formats such as PDF,
   * DOC, PPTX or similar.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public FileVersionFull promoteFileVersion(String fileId) {
    return promoteFileVersion(
        fileId,
        new PromoteFileVersionRequestBody(),
        new PromoteFileVersionQueryParams(),
        new PromoteFileVersionHeaders());
  }

  /**
   * Promote a specific version of a file.
   *
   * <p>If previous versions exist, this method can be used to promote one of the older versions to
   * the top of the version history.
   *
   * <p>This creates a new copy of the old version and puts it at the top of the versions history.
   * The file will have the exact same contents as the older version, with the same hash digest,
   * `etag`, and name as the original.
   *
   * <p>Other properties such as comments do not get updated to their former values.
   *
   * <p>Don't use this endpoint to restore Box Notes, as it works with file formats such as PDF,
   * DOC, PPTX or similar.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of promoteFileVersion method
   */
  public FileVersionFull promoteFileVersion(
      String fileId, PromoteFileVersionRequestBody requestBody) {
    return promoteFileVersion(
        fileId, requestBody, new PromoteFileVersionQueryParams(), new PromoteFileVersionHeaders());
  }

  /**
   * Promote a specific version of a file.
   *
   * <p>If previous versions exist, this method can be used to promote one of the older versions to
   * the top of the version history.
   *
   * <p>This creates a new copy of the old version and puts it at the top of the versions history.
   * The file will have the exact same contents as the older version, with the same hash digest,
   * `etag`, and name as the original.
   *
   * <p>Other properties such as comments do not get updated to their former values.
   *
   * <p>Don't use this endpoint to restore Box Notes, as it works with file formats such as PDF,
   * DOC, PPTX or similar.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of promoteFileVersion method
   */
  public FileVersionFull promoteFileVersion(
      String fileId, PromoteFileVersionQueryParams queryParams) {
    return promoteFileVersion(
        fileId, new PromoteFileVersionRequestBody(), queryParams, new PromoteFileVersionHeaders());
  }

  /**
   * Promote a specific version of a file.
   *
   * <p>If previous versions exist, this method can be used to promote one of the older versions to
   * the top of the version history.
   *
   * <p>This creates a new copy of the old version and puts it at the top of the versions history.
   * The file will have the exact same contents as the older version, with the same hash digest,
   * `etag`, and name as the original.
   *
   * <p>Other properties such as comments do not get updated to their former values.
   *
   * <p>Don't use this endpoint to restore Box Notes, as it works with file formats such as PDF,
   * DOC, PPTX or similar.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of promoteFileVersion method
   * @param queryParams Query parameters of promoteFileVersion method
   */
  public FileVersionFull promoteFileVersion(
      String fileId,
      PromoteFileVersionRequestBody requestBody,
      PromoteFileVersionQueryParams queryParams) {
    return promoteFileVersion(fileId, requestBody, queryParams, new PromoteFileVersionHeaders());
  }

  /**
   * Promote a specific version of a file.
   *
   * <p>If previous versions exist, this method can be used to promote one of the older versions to
   * the top of the version history.
   *
   * <p>This creates a new copy of the old version and puts it at the top of the versions history.
   * The file will have the exact same contents as the older version, with the same hash digest,
   * `etag`, and name as the original.
   *
   * <p>Other properties such as comments do not get updated to their former values.
   *
   * <p>Don't use this endpoint to restore Box Notes, as it works with file formats such as PDF,
   * DOC, PPTX or similar.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of promoteFileVersion method
   */
  public FileVersionFull promoteFileVersion(String fileId, PromoteFileVersionHeaders headers) {
    return promoteFileVersion(
        fileId, new PromoteFileVersionRequestBody(), new PromoteFileVersionQueryParams(), headers);
  }

  /**
   * Promote a specific version of a file.
   *
   * <p>If previous versions exist, this method can be used to promote one of the older versions to
   * the top of the version history.
   *
   * <p>This creates a new copy of the old version and puts it at the top of the versions history.
   * The file will have the exact same contents as the older version, with the same hash digest,
   * `etag`, and name as the original.
   *
   * <p>Other properties such as comments do not get updated to their former values.
   *
   * <p>Don't use this endpoint to restore Box Notes, as it works with file formats such as PDF,
   * DOC, PPTX or similar.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of promoteFileVersion method
   * @param headers Headers of promoteFileVersion method
   */
  public FileVersionFull promoteFileVersion(
      String fileId, PromoteFileVersionRequestBody requestBody, PromoteFileVersionHeaders headers) {
    return promoteFileVersion(fileId, requestBody, new PromoteFileVersionQueryParams(), headers);
  }

  /**
   * Promote a specific version of a file.
   *
   * <p>If previous versions exist, this method can be used to promote one of the older versions to
   * the top of the version history.
   *
   * <p>This creates a new copy of the old version and puts it at the top of the versions history.
   * The file will have the exact same contents as the older version, with the same hash digest,
   * `etag`, and name as the original.
   *
   * <p>Other properties such as comments do not get updated to their former values.
   *
   * <p>Don't use this endpoint to restore Box Notes, as it works with file formats such as PDF,
   * DOC, PPTX or similar.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of promoteFileVersion method
   * @param headers Headers of promoteFileVersion method
   */
  public FileVersionFull promoteFileVersion(
      String fileId, PromoteFileVersionQueryParams queryParams, PromoteFileVersionHeaders headers) {
    return promoteFileVersion(fileId, new PromoteFileVersionRequestBody(), queryParams, headers);
  }

  /**
   * Promote a specific version of a file.
   *
   * <p>If previous versions exist, this method can be used to promote one of the older versions to
   * the top of the version history.
   *
   * <p>This creates a new copy of the old version and puts it at the top of the versions history.
   * The file will have the exact same contents as the older version, with the same hash digest,
   * `etag`, and name as the original.
   *
   * <p>Other properties such as comments do not get updated to their former values.
   *
   * <p>Don't use this endpoint to restore Box Notes, as it works with file formats such as PDF,
   * DOC, PPTX or similar.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of promoteFileVersion method
   * @param queryParams Query parameters of promoteFileVersion method
   * @param headers Headers of promoteFileVersion method
   */
  public FileVersionFull promoteFileVersion(
      String fileId,
      PromoteFileVersionRequestBody requestBody,
      PromoteFileVersionQueryParams queryParams,
      PromoteFileVersionHeaders headers) {
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
                            "/versions/current"),
                        "POST")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileVersionFull.class);
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

    public FileVersionsManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new FileVersionsManager(this);
    }
  }
}
