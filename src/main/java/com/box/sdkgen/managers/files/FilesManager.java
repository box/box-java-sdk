package com.box.sdkgen.managers.files;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.box.errors.BoxSDKError;
import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.serialization.json.JsonManager;
import java.io.InputStream;
import java.util.Map;

public class FilesManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public FilesManager() {
    this.networkSession = new NetworkSession();
  }

  protected FilesManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves the details about a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public FileFull getFileById(String fileId) {
    return getFileById(fileId, new GetFileByIdQueryParams(), new GetFileByIdHeaders());
  }

  /**
   * Retrieves the details about a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getFileById method
   */
  public FileFull getFileById(String fileId, GetFileByIdQueryParams queryParams) {
    return getFileById(fileId, queryParams, new GetFileByIdHeaders());
  }

  /**
   * Retrieves the details about a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of getFileById method
   */
  public FileFull getFileById(String fileId, GetFileByIdHeaders headers) {
    return getFileById(fileId, new GetFileByIdQueryParams(), headers);
  }

  /**
   * Retrieves the details about a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getFileById method
   * @param headers Headers of getFileById method
   */
  public FileFull getFileById(
      String fileId, GetFileByIdQueryParams queryParams, GetFileByIdHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(
                    entryOf("if-none-match", convertToString(headers.getIfNoneMatch())),
                    entryOf("boxapi", convertToString(headers.getBoxapi())),
                    entryOf("x-rep-hints", convertToString(headers.getXRepHints()))),
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
                            convertToString(fileId)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileFull.class);
  }

  /**
   * Updates a file. This can be used to rename or move a file, create a shared link, or lock a
   * file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public FileFull updateFileById(String fileId) {
    return updateFileById(
        fileId,
        new UpdateFileByIdRequestBody(),
        new UpdateFileByIdQueryParams(),
        new UpdateFileByIdHeaders());
  }

  /**
   * Updates a file. This can be used to rename or move a file, create a shared link, or lock a
   * file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of updateFileById method
   */
  public FileFull updateFileById(String fileId, UpdateFileByIdRequestBody requestBody) {
    return updateFileById(
        fileId, requestBody, new UpdateFileByIdQueryParams(), new UpdateFileByIdHeaders());
  }

  /**
   * Updates a file. This can be used to rename or move a file, create a shared link, or lock a
   * file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of updateFileById method
   */
  public FileFull updateFileById(String fileId, UpdateFileByIdQueryParams queryParams) {
    return updateFileById(
        fileId, new UpdateFileByIdRequestBody(), queryParams, new UpdateFileByIdHeaders());
  }

  /**
   * Updates a file. This can be used to rename or move a file, create a shared link, or lock a
   * file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of updateFileById method
   * @param queryParams Query parameters of updateFileById method
   */
  public FileFull updateFileById(
      String fileId, UpdateFileByIdRequestBody requestBody, UpdateFileByIdQueryParams queryParams) {
    return updateFileById(fileId, requestBody, queryParams, new UpdateFileByIdHeaders());
  }

  /**
   * Updates a file. This can be used to rename or move a file, create a shared link, or lock a
   * file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of updateFileById method
   */
  public FileFull updateFileById(String fileId, UpdateFileByIdHeaders headers) {
    return updateFileById(
        fileId, new UpdateFileByIdRequestBody(), new UpdateFileByIdQueryParams(), headers);
  }

  /**
   * Updates a file. This can be used to rename or move a file, create a shared link, or lock a
   * file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of updateFileById method
   * @param headers Headers of updateFileById method
   */
  public FileFull updateFileById(
      String fileId, UpdateFileByIdRequestBody requestBody, UpdateFileByIdHeaders headers) {
    return updateFileById(fileId, requestBody, new UpdateFileByIdQueryParams(), headers);
  }

  /**
   * Updates a file. This can be used to rename or move a file, create a shared link, or lock a
   * file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of updateFileById method
   * @param headers Headers of updateFileById method
   */
  public FileFull updateFileById(
      String fileId, UpdateFileByIdQueryParams queryParams, UpdateFileByIdHeaders headers) {
    return updateFileById(fileId, new UpdateFileByIdRequestBody(), queryParams, headers);
  }

  /**
   * Updates a file. This can be used to rename or move a file, create a shared link, or lock a
   * file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of updateFileById method
   * @param queryParams Query parameters of updateFileById method
   * @param headers Headers of updateFileById method
   */
  public FileFull updateFileById(
      String fileId,
      UpdateFileByIdRequestBody requestBody,
      UpdateFileByIdQueryParams queryParams,
      UpdateFileByIdHeaders headers) {
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
                            "/2.0/files/",
                            convertToString(fileId)),
                        "PUT")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileFull.class);
  }

  /**
   * Deletes a file, either permanently or by moving it to the trash.
   *
   * <p>The enterprise settings determine whether the item will be permanently deleted from Box or
   * moved to the trash.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public void deleteFileById(String fileId) {
    deleteFileById(fileId, new DeleteFileByIdHeaders());
  }

  /**
   * Deletes a file, either permanently or by moving it to the trash.
   *
   * <p>The enterprise settings determine whether the item will be permanently deleted from Box or
   * moved to the trash.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of deleteFileById method
   */
  public void deleteFileById(String fileId, DeleteFileByIdHeaders headers) {
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
                            convertToString(fileId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Creates a copy of a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of copyFile method
   */
  public FileFull copyFile(String fileId, CopyFileRequestBody requestBody) {
    return copyFile(fileId, requestBody, new CopyFileQueryParams(), new CopyFileHeaders());
  }

  /**
   * Creates a copy of a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of copyFile method
   * @param queryParams Query parameters of copyFile method
   */
  public FileFull copyFile(
      String fileId, CopyFileRequestBody requestBody, CopyFileQueryParams queryParams) {
    return copyFile(fileId, requestBody, queryParams, new CopyFileHeaders());
  }

  /**
   * Creates a copy of a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of copyFile method
   * @param headers Headers of copyFile method
   */
  public FileFull copyFile(
      String fileId, CopyFileRequestBody requestBody, CopyFileHeaders headers) {
    return copyFile(fileId, requestBody, new CopyFileQueryParams(), headers);
  }

  /**
   * Creates a copy of a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of copyFile method
   * @param queryParams Query parameters of copyFile method
   * @param headers Headers of copyFile method
   */
  public FileFull copyFile(
      String fileId,
      CopyFileRequestBody requestBody,
      CopyFileQueryParams queryParams,
      CopyFileHeaders headers) {
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
    return JsonManager.deserialize(response.getData(), FileFull.class);
  }

  /**
   * Retrieves a thumbnail, or smaller image representation, of a file.
   *
   * <p>Sizes of `32x32`,`64x64`, `128x128`, and `256x256` can be returned in the `.png` format and
   * sizes of `32x32`, `160x160`, and `320x320` can be returned in the `.jpg` format.
   *
   * <p>Thumbnails can be generated for the image and video file formats listed [found on our
   * community site][1].
   *
   * <p>[1]:
   * https://community.box.com/t5/Migrating-and-Previewing-Content/File-Types-and-Fonts-Supported-in-Box-Content-Preview/ta-p/327
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param extension The file format for the thumbnail. Example: "png"
   */
  public String getFileThumbnailUrl(String fileId, GetFileThumbnailUrlExtension extension) {
    return getFileThumbnailUrl(
        fileId, extension, new GetFileThumbnailUrlQueryParams(), new GetFileThumbnailUrlHeaders());
  }

  /**
   * Retrieves a thumbnail, or smaller image representation, of a file.
   *
   * <p>Sizes of `32x32`,`64x64`, `128x128`, and `256x256` can be returned in the `.png` format and
   * sizes of `32x32`, `160x160`, and `320x320` can be returned in the `.jpg` format.
   *
   * <p>Thumbnails can be generated for the image and video file formats listed [found on our
   * community site][1].
   *
   * <p>[1]:
   * https://community.box.com/t5/Migrating-and-Previewing-Content/File-Types-and-Fonts-Supported-in-Box-Content-Preview/ta-p/327
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param extension The file format for the thumbnail. Example: "png"
   * @param queryParams Query parameters of getFileThumbnailById method
   */
  public String getFileThumbnailUrl(
      String fileId,
      GetFileThumbnailUrlExtension extension,
      GetFileThumbnailUrlQueryParams queryParams) {
    return getFileThumbnailUrl(fileId, extension, queryParams, new GetFileThumbnailUrlHeaders());
  }

  /**
   * Retrieves a thumbnail, or smaller image representation, of a file.
   *
   * <p>Sizes of `32x32`,`64x64`, `128x128`, and `256x256` can be returned in the `.png` format and
   * sizes of `32x32`, `160x160`, and `320x320` can be returned in the `.jpg` format.
   *
   * <p>Thumbnails can be generated for the image and video file formats listed [found on our
   * community site][1].
   *
   * <p>[1]:
   * https://community.box.com/t5/Migrating-and-Previewing-Content/File-Types-and-Fonts-Supported-in-Box-Content-Preview/ta-p/327
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param extension The file format for the thumbnail. Example: "png"
   * @param headers Headers of getFileThumbnailById method
   */
  public String getFileThumbnailUrl(
      String fileId, GetFileThumbnailUrlExtension extension, GetFileThumbnailUrlHeaders headers) {
    return getFileThumbnailUrl(fileId, extension, new GetFileThumbnailUrlQueryParams(), headers);
  }

  /**
   * Retrieves a thumbnail, or smaller image representation, of a file.
   *
   * <p>Sizes of `32x32`,`64x64`, `128x128`, and `256x256` can be returned in the `.png` format and
   * sizes of `32x32`, `160x160`, and `320x320` can be returned in the `.jpg` format.
   *
   * <p>Thumbnails can be generated for the image and video file formats listed [found on our
   * community site][1].
   *
   * <p>[1]:
   * https://community.box.com/t5/Migrating-and-Previewing-Content/File-Types-and-Fonts-Supported-in-Box-Content-Preview/ta-p/327
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param extension The file format for the thumbnail. Example: "png"
   * @param queryParams Query parameters of getFileThumbnailById method
   * @param headers Headers of getFileThumbnailById method
   */
  public String getFileThumbnailUrl(
      String fileId,
      GetFileThumbnailUrlExtension extension,
      GetFileThumbnailUrlQueryParams queryParams,
      GetFileThumbnailUrlHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("min_height", convertToString(queryParams.getMinHeight())),
                entryOf("min_width", convertToString(queryParams.getMinWidth())),
                entryOf("max_height", convertToString(queryParams.getMaxHeight())),
                entryOf("max_width", convertToString(queryParams.getMaxWidth()))));
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
                            "/thumbnail.",
                            convertToString(extension)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .followRedirects(false)
                    .build());
    if (response.getHeaders().containsKey("location")) {
      return response.getHeaders().get("location");
    }
    if (response.getHeaders().containsKey("Location")) {
      return response.getHeaders().get("Location");
    }
    throw new BoxSDKError("No location header in response");
  }

  /**
   * Retrieves a thumbnail, or smaller image representation, of a file.
   *
   * <p>Sizes of `32x32`,`64x64`, `128x128`, and `256x256` can be returned in the `.png` format and
   * sizes of `32x32`, `160x160`, and `320x320` can be returned in the `.jpg` format.
   *
   * <p>Thumbnails can be generated for the image and video file formats listed [found on our
   * community site][1].
   *
   * <p>[1]:
   * https://community.box.com/t5/Migrating-and-Previewing-Content/File-Types-and-Fonts-Supported-in-Box-Content-Preview/ta-p/327
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param extension The file format for the thumbnail. Example: "png"
   */
  public InputStream getFileThumbnailById(String fileId, GetFileThumbnailByIdExtension extension) {
    return getFileThumbnailById(
        fileId,
        extension,
        new GetFileThumbnailByIdQueryParams(),
        new GetFileThumbnailByIdHeaders());
  }

  /**
   * Retrieves a thumbnail, or smaller image representation, of a file.
   *
   * <p>Sizes of `32x32`,`64x64`, `128x128`, and `256x256` can be returned in the `.png` format and
   * sizes of `32x32`, `160x160`, and `320x320` can be returned in the `.jpg` format.
   *
   * <p>Thumbnails can be generated for the image and video file formats listed [found on our
   * community site][1].
   *
   * <p>[1]:
   * https://community.box.com/t5/Migrating-and-Previewing-Content/File-Types-and-Fonts-Supported-in-Box-Content-Preview/ta-p/327
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param extension The file format for the thumbnail. Example: "png"
   * @param queryParams Query parameters of getFileThumbnailById method
   */
  public InputStream getFileThumbnailById(
      String fileId,
      GetFileThumbnailByIdExtension extension,
      GetFileThumbnailByIdQueryParams queryParams) {
    return getFileThumbnailById(fileId, extension, queryParams, new GetFileThumbnailByIdHeaders());
  }

  /**
   * Retrieves a thumbnail, or smaller image representation, of a file.
   *
   * <p>Sizes of `32x32`,`64x64`, `128x128`, and `256x256` can be returned in the `.png` format and
   * sizes of `32x32`, `160x160`, and `320x320` can be returned in the `.jpg` format.
   *
   * <p>Thumbnails can be generated for the image and video file formats listed [found on our
   * community site][1].
   *
   * <p>[1]:
   * https://community.box.com/t5/Migrating-and-Previewing-Content/File-Types-and-Fonts-Supported-in-Box-Content-Preview/ta-p/327
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param extension The file format for the thumbnail. Example: "png"
   * @param headers Headers of getFileThumbnailById method
   */
  public InputStream getFileThumbnailById(
      String fileId, GetFileThumbnailByIdExtension extension, GetFileThumbnailByIdHeaders headers) {
    return getFileThumbnailById(fileId, extension, new GetFileThumbnailByIdQueryParams(), headers);
  }

  /**
   * Retrieves a thumbnail, or smaller image representation, of a file.
   *
   * <p>Sizes of `32x32`,`64x64`, `128x128`, and `256x256` can be returned in the `.png` format and
   * sizes of `32x32`, `160x160`, and `320x320` can be returned in the `.jpg` format.
   *
   * <p>Thumbnails can be generated for the image and video file formats listed [found on our
   * community site][1].
   *
   * <p>[1]:
   * https://community.box.com/t5/Migrating-and-Previewing-Content/File-Types-and-Fonts-Supported-in-Box-Content-Preview/ta-p/327
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param extension The file format for the thumbnail. Example: "png"
   * @param queryParams Query parameters of getFileThumbnailById method
   * @param headers Headers of getFileThumbnailById method
   */
  public InputStream getFileThumbnailById(
      String fileId,
      GetFileThumbnailByIdExtension extension,
      GetFileThumbnailByIdQueryParams queryParams,
      GetFileThumbnailByIdHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("min_height", convertToString(queryParams.getMinHeight())),
                entryOf("min_width", convertToString(queryParams.getMinWidth())),
                entryOf("max_height", convertToString(queryParams.getMaxHeight())),
                entryOf("max_width", convertToString(queryParams.getMaxWidth()))));
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
                            "/thumbnail.",
                            convertToString(extension)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.BINARY)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    if (convertToString(response.getStatus()).equals("202")) {
      return null;
    }
    return response.getContent();
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

    public FilesManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new FilesManager(this);
    }
  }
}
