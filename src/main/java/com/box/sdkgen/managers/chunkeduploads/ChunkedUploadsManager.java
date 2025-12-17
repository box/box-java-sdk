package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.bufferLength;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStreamFromBuffer;
import static com.box.sdkgen.internal.utils.UtilsManager.hexToBase64;
import static com.box.sdkgen.internal.utils.UtilsManager.iterateChunks;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;
import static com.box.sdkgen.internal.utils.UtilsManager.readByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.reduceIterator;

import com.box.sdkgen.internal.utils.Hash;
import com.box.sdkgen.internal.utils.HashName;
import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.uploadedpart.UploadedPart;
import com.box.sdkgen.schemas.uploadpart.UploadPart;
import com.box.sdkgen.schemas.uploadparts.UploadParts;
import com.box.sdkgen.schemas.uploadsession.UploadSession;
import com.box.sdkgen.serialization.json.JsonManager;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChunkedUploadsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public ChunkedUploadsManager() {
    this.networkSession = new NetworkSession();
  }

  protected ChunkedUploadsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Creates an upload session for a new file.
   *
   * @param requestBody Request body of createFileUploadSession method
   */
  public UploadSession createFileUploadSession(CreateFileUploadSessionRequestBody requestBody) {
    return createFileUploadSession(requestBody, new CreateFileUploadSessionHeaders());
  }

  /**
   * Creates an upload session for a new file.
   *
   * @param requestBody Request body of createFileUploadSession method
   * @param headers Headers of createFileUploadSession method
   */
  public UploadSession createFileUploadSession(
      CreateFileUploadSessionRequestBody requestBody, CreateFileUploadSessionHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getUploadUrl(),
                            "/2.0/files/upload_sessions"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadSession.class);
  }

  /**
   * Creates an upload session for an existing file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of createFileUploadSessionForExistingFile method
   */
  public UploadSession createFileUploadSessionForExistingFile(
      String fileId, CreateFileUploadSessionForExistingFileRequestBody requestBody) {
    return createFileUploadSessionForExistingFile(
        fileId, requestBody, new CreateFileUploadSessionForExistingFileHeaders());
  }

  /**
   * Creates an upload session for an existing file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of createFileUploadSessionForExistingFile method
   * @param headers Headers of createFileUploadSessionForExistingFile method
   */
  public UploadSession createFileUploadSessionForExistingFile(
      String fileId,
      CreateFileUploadSessionForExistingFileRequestBody requestBody,
      CreateFileUploadSessionForExistingFileHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getUploadUrl(),
                            "/2.0/files/",
                            convertToString(fileId),
                            "/upload_sessions"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadSession.class);
  }

  /**
   * Using this method with urls provided in response when creating a new upload session is
   * preferred to use over GetFileUploadSessionById method. This allows to always upload your
   * content to the closest Box data center and can significantly improve upload speed. Return
   * information about an upload session.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) endpoint.
   *
   * @param url URL of getFileUploadSessionById method
   */
  public UploadSession getFileUploadSessionByUrl(String url) {
    return getFileUploadSessionByUrl(url, new GetFileUploadSessionByUrlHeaders());
  }

  /**
   * Using this method with urls provided in response when creating a new upload session is
   * preferred to use over GetFileUploadSessionById method. This allows to always upload your
   * content to the closest Box data center and can significantly improve upload speed. Return
   * information about an upload session.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) endpoint.
   *
   * @param url URL of getFileUploadSessionById method
   * @param headers Headers of getFileUploadSessionById method
   */
  public UploadSession getFileUploadSessionByUrl(
      String url, GetFileUploadSessionByUrlHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(url, "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadSession.class);
  }

  /**
   * Return information about an upload session.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) endpoint.
   *
   * @param uploadSessionId The ID of the upload session. Example: "D5E3F7A"
   */
  public UploadSession getFileUploadSessionById(String uploadSessionId) {
    return getFileUploadSessionById(uploadSessionId, new GetFileUploadSessionByIdHeaders());
  }

  /**
   * Return information about an upload session.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) endpoint.
   *
   * @param uploadSessionId The ID of the upload session. Example: "D5E3F7A"
   * @param headers Headers of getFileUploadSessionById method
   */
  public UploadSession getFileUploadSessionById(
      String uploadSessionId, GetFileUploadSessionByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getUploadUrl(),
                            "/2.0/files/upload_sessions/",
                            convertToString(uploadSessionId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadSession.class);
  }

  /**
   * Using this method with urls provided in response when creating a new upload session is
   * preferred to use over UploadFilePart method. This allows to always upload your content to the
   * closest Box data center and can significantly improve upload speed. Uploads a chunk of a file
   * for an upload session.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) and [`Get upload
   * session`](https://developer.box.com/reference/get-files-upload-sessions-id) endpoints.
   *
   * @param url URL of uploadFilePart method
   * @param requestBody Request body of uploadFilePart method
   * @param headers Headers of uploadFilePart method
   */
  public UploadedPart uploadFilePartByUrl(
      String url, InputStream requestBody, UploadFilePartByUrlHeaders headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(
                    entryOf("digest", convertToString(headers.getDigest())),
                    entryOf("content-range", convertToString(headers.getContentRange()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(url, "PUT")
                    .headers(headersMap)
                    .fileStream(requestBody)
                    .contentType("application/octet-stream")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadedPart.class);
  }

  /**
   * Uploads a chunk of a file for an upload session.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) and [`Get upload
   * session`](https://developer.box.com/reference/get-files-upload-sessions-id) endpoints.
   *
   * @param uploadSessionId The ID of the upload session. Example: "D5E3F7A"
   * @param requestBody Request body of uploadFilePart method
   * @param headers Headers of uploadFilePart method
   */
  public UploadedPart uploadFilePart(
      String uploadSessionId, InputStream requestBody, UploadFilePartHeaders headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(
                    entryOf("digest", convertToString(headers.getDigest())),
                    entryOf("content-range", convertToString(headers.getContentRange()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getUploadUrl(),
                            "/2.0/files/upload_sessions/",
                            convertToString(uploadSessionId)),
                        "PUT")
                    .headers(headersMap)
                    .fileStream(requestBody)
                    .contentType("application/octet-stream")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadedPart.class);
  }

  /**
   * Using this method with urls provided in response when creating a new upload session is
   * preferred to use over DeleteFileUploadSessionById method. This allows to always upload your
   * content to the closest Box data center and can significantly improve upload speed. Abort an
   * upload session and discard all data uploaded.
   *
   * <p>This cannot be reversed.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) and [`Get upload
   * session`](https://developer.box.com/reference/get-files-upload-sessions-id) endpoints.
   *
   * @param url URL of deleteFileUploadSessionById method
   */
  public void deleteFileUploadSessionByUrl(String url) {
    deleteFileUploadSessionByUrl(url, new DeleteFileUploadSessionByUrlHeaders());
  }

  /**
   * Using this method with urls provided in response when creating a new upload session is
   * preferred to use over DeleteFileUploadSessionById method. This allows to always upload your
   * content to the closest Box data center and can significantly improve upload speed. Abort an
   * upload session and discard all data uploaded.
   *
   * <p>This cannot be reversed.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) and [`Get upload
   * session`](https://developer.box.com/reference/get-files-upload-sessions-id) endpoints.
   *
   * @param url URL of deleteFileUploadSessionById method
   * @param headers Headers of deleteFileUploadSessionById method
   */
  public void deleteFileUploadSessionByUrl(
      String url, DeleteFileUploadSessionByUrlHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(url, "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Abort an upload session and discard all data uploaded.
   *
   * <p>This cannot be reversed.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) and [`Get upload
   * session`](https://developer.box.com/reference/get-files-upload-sessions-id) endpoints.
   *
   * @param uploadSessionId The ID of the upload session. Example: "D5E3F7A"
   */
  public void deleteFileUploadSessionById(String uploadSessionId) {
    deleteFileUploadSessionById(uploadSessionId, new DeleteFileUploadSessionByIdHeaders());
  }

  /**
   * Abort an upload session and discard all data uploaded.
   *
   * <p>This cannot be reversed.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) and [`Get upload
   * session`](https://developer.box.com/reference/get-files-upload-sessions-id) endpoints.
   *
   * @param uploadSessionId The ID of the upload session. Example: "D5E3F7A"
   * @param headers Headers of deleteFileUploadSessionById method
   */
  public void deleteFileUploadSessionById(
      String uploadSessionId, DeleteFileUploadSessionByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getUploadUrl(),
                            "/2.0/files/upload_sessions/",
                            convertToString(uploadSessionId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Using this method with urls provided in response when creating a new upload session is
   * preferred to use over GetFileUploadSessionParts method. This allows to always upload your
   * content to the closest Box data center and can significantly improve upload speed. Return a
   * list of the chunks uploaded to the upload session so far.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) and [`Get upload
   * session`](https://developer.box.com/reference/get-files-upload-sessions-id) endpoints.
   *
   * @param url URL of getFileUploadSessionParts method
   */
  public UploadParts getFileUploadSessionPartsByUrl(String url) {
    return getFileUploadSessionPartsByUrl(
        url,
        new GetFileUploadSessionPartsByUrlQueryParams(),
        new GetFileUploadSessionPartsByUrlHeaders());
  }

  /**
   * Using this method with urls provided in response when creating a new upload session is
   * preferred to use over GetFileUploadSessionParts method. This allows to always upload your
   * content to the closest Box data center and can significantly improve upload speed. Return a
   * list of the chunks uploaded to the upload session so far.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) and [`Get upload
   * session`](https://developer.box.com/reference/get-files-upload-sessions-id) endpoints.
   *
   * @param url URL of getFileUploadSessionParts method
   * @param queryParams Query parameters of getFileUploadSessionParts method
   */
  public UploadParts getFileUploadSessionPartsByUrl(
      String url, GetFileUploadSessionPartsByUrlQueryParams queryParams) {
    return getFileUploadSessionPartsByUrl(
        url, queryParams, new GetFileUploadSessionPartsByUrlHeaders());
  }

  /**
   * Using this method with urls provided in response when creating a new upload session is
   * preferred to use over GetFileUploadSessionParts method. This allows to always upload your
   * content to the closest Box data center and can significantly improve upload speed. Return a
   * list of the chunks uploaded to the upload session so far.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) and [`Get upload
   * session`](https://developer.box.com/reference/get-files-upload-sessions-id) endpoints.
   *
   * @param url URL of getFileUploadSessionParts method
   * @param headers Headers of getFileUploadSessionParts method
   */
  public UploadParts getFileUploadSessionPartsByUrl(
      String url, GetFileUploadSessionPartsByUrlHeaders headers) {
    return getFileUploadSessionPartsByUrl(
        url, new GetFileUploadSessionPartsByUrlQueryParams(), headers);
  }

  /**
   * Using this method with urls provided in response when creating a new upload session is
   * preferred to use over GetFileUploadSessionParts method. This allows to always upload your
   * content to the closest Box data center and can significantly improve upload speed. Return a
   * list of the chunks uploaded to the upload session so far.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) and [`Get upload
   * session`](https://developer.box.com/reference/get-files-upload-sessions-id) endpoints.
   *
   * @param url URL of getFileUploadSessionParts method
   * @param queryParams Query parameters of getFileUploadSessionParts method
   * @param headers Headers of getFileUploadSessionParts method
   */
  public UploadParts getFileUploadSessionPartsByUrl(
      String url,
      GetFileUploadSessionPartsByUrlQueryParams queryParams,
      GetFileUploadSessionPartsByUrlHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("offset", convertToString(queryParams.getOffset())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(url, "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadParts.class);
  }

  /**
   * Return a list of the chunks uploaded to the upload session so far.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) and [`Get upload
   * session`](https://developer.box.com/reference/get-files-upload-sessions-id) endpoints.
   *
   * @param uploadSessionId The ID of the upload session. Example: "D5E3F7A"
   */
  public UploadParts getFileUploadSessionParts(String uploadSessionId) {
    return getFileUploadSessionParts(
        uploadSessionId,
        new GetFileUploadSessionPartsQueryParams(),
        new GetFileUploadSessionPartsHeaders());
  }

  /**
   * Return a list of the chunks uploaded to the upload session so far.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) and [`Get upload
   * session`](https://developer.box.com/reference/get-files-upload-sessions-id) endpoints.
   *
   * @param uploadSessionId The ID of the upload session. Example: "D5E3F7A"
   * @param queryParams Query parameters of getFileUploadSessionParts method
   */
  public UploadParts getFileUploadSessionParts(
      String uploadSessionId, GetFileUploadSessionPartsQueryParams queryParams) {
    return getFileUploadSessionParts(
        uploadSessionId, queryParams, new GetFileUploadSessionPartsHeaders());
  }

  /**
   * Return a list of the chunks uploaded to the upload session so far.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) and [`Get upload
   * session`](https://developer.box.com/reference/get-files-upload-sessions-id) endpoints.
   *
   * @param uploadSessionId The ID of the upload session. Example: "D5E3F7A"
   * @param headers Headers of getFileUploadSessionParts method
   */
  public UploadParts getFileUploadSessionParts(
      String uploadSessionId, GetFileUploadSessionPartsHeaders headers) {
    return getFileUploadSessionParts(
        uploadSessionId, new GetFileUploadSessionPartsQueryParams(), headers);
  }

  /**
   * Return a list of the chunks uploaded to the upload session so far.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) and [`Get upload
   * session`](https://developer.box.com/reference/get-files-upload-sessions-id) endpoints.
   *
   * @param uploadSessionId The ID of the upload session. Example: "D5E3F7A"
   * @param queryParams Query parameters of getFileUploadSessionParts method
   * @param headers Headers of getFileUploadSessionParts method
   */
  public UploadParts getFileUploadSessionParts(
      String uploadSessionId,
      GetFileUploadSessionPartsQueryParams queryParams,
      GetFileUploadSessionPartsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
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
                            this.networkSession.getBaseUrls().getUploadUrl(),
                            "/2.0/files/upload_sessions/",
                            convertToString(uploadSessionId),
                            "/parts"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadParts.class);
  }

  /**
   * Using this method with urls provided in response when creating a new upload session is
   * preferred to use over CreateFileUploadSessionCommit method. This allows to always upload your
   * content to the closest Box data center and can significantly improve upload speed. Close an
   * upload session and create a file from the uploaded chunks.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) and [`Get upload
   * session`](https://developer.box.com/reference/get-files-upload-sessions-id) endpoints.
   *
   * @param url URL of createFileUploadSessionCommit method
   * @param requestBody Request body of createFileUploadSessionCommit method
   * @param headers Headers of createFileUploadSessionCommit method
   */
  public Files createFileUploadSessionCommitByUrl(
      String url,
      CreateFileUploadSessionCommitByUrlRequestBody requestBody,
      CreateFileUploadSessionCommitByUrlHeaders headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(
                    entryOf("digest", convertToString(headers.getDigest())),
                    entryOf("if-match", convertToString(headers.getIfMatch())),
                    entryOf("if-none-match", convertToString(headers.getIfNoneMatch()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(url, "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    if (convertToString(response.getStatus()).equals("202")) {
      return null;
    }
    return JsonManager.deserialize(response.getData(), Files.class);
  }

  /**
   * Close an upload session and create a file from the uploaded chunks.
   *
   * <p>The actual endpoint URL is returned by the [`Create upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions) and [`Get upload
   * session`](https://developer.box.com/reference/get-files-upload-sessions-id) endpoints.
   *
   * @param uploadSessionId The ID of the upload session. Example: "D5E3F7A"
   * @param requestBody Request body of createFileUploadSessionCommit method
   * @param headers Headers of createFileUploadSessionCommit method
   */
  public Files createFileUploadSessionCommit(
      String uploadSessionId,
      CreateFileUploadSessionCommitRequestBody requestBody,
      CreateFileUploadSessionCommitHeaders headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(
                    entryOf("digest", convertToString(headers.getDigest())),
                    entryOf("if-match", convertToString(headers.getIfMatch())),
                    entryOf("if-none-match", convertToString(headers.getIfNoneMatch()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getUploadUrl(),
                            "/2.0/files/upload_sessions/",
                            convertToString(uploadSessionId),
                            "/commit"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    if (convertToString(response.getStatus()).equals("202")) {
      return null;
    }
    return JsonManager.deserialize(response.getData(), Files.class);
  }

  public PartAccumulator reducer(PartAccumulator acc, InputStream chunk) {
    long lastIndex = acc.getLastIndex();
    List<UploadPart> parts = acc.getParts();
    byte[] chunkBuffer = readByteStream(chunk);
    Hash hash = new Hash(HashName.SHA1);
    hash.updateHash(chunkBuffer);
    String sha1 = hash.digestHash("base64");
    String digest = String.join("", "sha=", sha1);
    int chunkSize = bufferLength(chunkBuffer);
    long bytesStart = lastIndex + 1;
    long bytesEnd = lastIndex + chunkSize;
    String contentRange =
        String.join(
            "",
            "bytes ",
            convertToString(bytesStart),
            "-",
            convertToString(bytesEnd),
            "/",
            convertToString(acc.getFileSize()));
    UploadedPart uploadedPart =
        this.uploadFilePartByUrl(
            acc.getUploadPartUrl(),
            generateByteStreamFromBuffer(chunkBuffer),
            new UploadFilePartByUrlHeaders(digest, contentRange));
    UploadPart part = uploadedPart.getPart();
    String partSha1 = hexToBase64(part.getSha1());
    assert partSha1.equals(sha1);
    assert part.getSize() == chunkSize;
    assert part.getOffset() == bytesStart;
    acc.getFileHash().updateHash(chunkBuffer);
    return new PartAccumulator(
        bytesEnd,
        Stream.concat(parts.stream(), Arrays.asList(part).stream()).collect(Collectors.toList()),
        acc.getFileSize(),
        acc.getUploadPartUrl(),
        acc.getFileHash());
  }

  /**
   * Starts the process of chunk uploading a big file. Should return a File object representing
   * uploaded file.
   *
   * @param file The stream of the file to upload.
   * @param fileName The name of the file, which will be used for storage in Box.
   * @param fileSize The total size of the file for the chunked upload in bytes.
   * @param parentFolderId The ID of the folder where the file should be uploaded.
   */
  public FileFull uploadBigFile(
      InputStream file, String fileName, long fileSize, String parentFolderId) {
    UploadSession uploadSession =
        this.createFileUploadSession(
            new CreateFileUploadSessionRequestBody(parentFolderId, fileSize, fileName));
    String uploadPartUrl = uploadSession.getSessionEndpoints().getUploadPart();
    String commitUrl = uploadSession.getSessionEndpoints().getCommit();
    String listPartsUrl = uploadSession.getSessionEndpoints().getListParts();
    long partSize = uploadSession.getPartSize();
    int totalParts = uploadSession.getTotalParts();
    assert partSize * totalParts >= fileSize;
    assert uploadSession.getNumPartsProcessed() == 0;
    Hash fileHash = new Hash(HashName.SHA1);
    Iterator<InputStream> chunksIterator = iterateChunks(file, partSize, fileSize);
    PartAccumulator results =
        reduceIterator(
            chunksIterator,
            this::reducer,
            new PartAccumulator(-1, Collections.emptyList(), fileSize, uploadPartUrl, fileHash));
    List<UploadPart> parts = results.getParts();
    UploadParts processedSessionParts = this.getFileUploadSessionPartsByUrl(listPartsUrl);
    assert processedSessionParts.getTotalCount() == totalParts;
    String sha1 = fileHash.digestHash("base64");
    String digest = String.join("", "sha=", sha1);
    Files committedSession =
        this.createFileUploadSessionCommitByUrl(
            commitUrl,
            new CreateFileUploadSessionCommitByUrlRequestBody(parts),
            new CreateFileUploadSessionCommitByUrlHeaders(digest));
    return committedSession.getEntries().get(0);
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

    public ChunkedUploadsManager build() {
      return new ChunkedUploadsManager(this);
    }
  }
}
