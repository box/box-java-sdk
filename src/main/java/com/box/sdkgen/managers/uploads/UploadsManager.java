package com.box.sdkgen.managers.uploads;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.box.errors.BoxSDKError;
import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.MultipartItem;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.uploadurl.UploadUrl;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Arrays;
import java.util.Map;

public class UploadsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public UploadsManager() {
    this.networkSession = new NetworkSession();
  }

  protected UploadsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Update a file's content. For file sizes over 50MB we recommend using the Chunk Upload APIs.
   *
   * <p>The `attributes` part of the body must come **before** the `file` part. Requests that do not
   * follow this format when uploading the file will receive a HTTP `400` error with a
   * `metadata_after_file_contents` error code.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of uploadFileVersion method
   */
  public Files uploadFileVersion(String fileId, UploadFileVersionRequestBody requestBody) {
    return uploadFileVersion(
        fileId, requestBody, new UploadFileVersionQueryParams(), new UploadFileVersionHeaders());
  }

  /**
   * Update a file's content. For file sizes over 50MB we recommend using the Chunk Upload APIs.
   *
   * <p>The `attributes` part of the body must come **before** the `file` part. Requests that do not
   * follow this format when uploading the file will receive a HTTP `400` error with a
   * `metadata_after_file_contents` error code.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of uploadFileVersion method
   * @param queryParams Query parameters of uploadFileVersion method
   */
  public Files uploadFileVersion(
      String fileId,
      UploadFileVersionRequestBody requestBody,
      UploadFileVersionQueryParams queryParams) {
    return uploadFileVersion(fileId, requestBody, queryParams, new UploadFileVersionHeaders());
  }

  /**
   * Update a file's content. For file sizes over 50MB we recommend using the Chunk Upload APIs.
   *
   * <p>The `attributes` part of the body must come **before** the `file` part. Requests that do not
   * follow this format when uploading the file will receive a HTTP `400` error with a
   * `metadata_after_file_contents` error code.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of uploadFileVersion method
   * @param headers Headers of uploadFileVersion method
   */
  public Files uploadFileVersion(
      String fileId, UploadFileVersionRequestBody requestBody, UploadFileVersionHeaders headers) {
    return uploadFileVersion(fileId, requestBody, new UploadFileVersionQueryParams(), headers);
  }

  /**
   * Update a file's content. For file sizes over 50MB we recommend using the Chunk Upload APIs.
   *
   * <p>The `attributes` part of the body must come **before** the `file` part. Requests that do not
   * follow this format when uploading the file will receive a HTTP `400` error with a
   * `metadata_after_file_contents` error code.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of uploadFileVersion method
   * @param queryParams Query parameters of uploadFileVersion method
   * @param headers Headers of uploadFileVersion method
   */
  public Files uploadFileVersion(
      String fileId,
      UploadFileVersionRequestBody requestBody,
      UploadFileVersionQueryParams queryParams,
      UploadFileVersionHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(
                    entryOf("if-match", convertToString(headers.getIfMatch())),
                    entryOf("content-md5", convertToString(headers.getContentMd5()))),
                headers.getExtraHeaders()));
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
                            "/content"),
                        "POST")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .multipartData(
                        Arrays.asList(
                            new MultipartItem.Builder("attributes")
                                .data(JsonManager.serialize(requestBody.getAttributes()))
                                .build(),
                            new MultipartItem.Builder("file")
                                .fileStream(requestBody.getFile())
                                .fileName(requestBody.getFileFileName())
                                .contentType(requestBody.getFileContentType())
                                .build()))
                    .contentType("multipart/form-data")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Files.class);
  }

  /**
   * Performs a check to verify that a file will be accepted by Box before you upload the entire
   * file.
   */
  public UploadUrl preflightFileUploadCheck() {
    return preflightFileUploadCheck(
        new PreflightFileUploadCheckRequestBody(), new PreflightFileUploadCheckHeaders());
  }

  /**
   * Performs a check to verify that a file will be accepted by Box before you upload the entire
   * file.
   *
   * @param requestBody Request body of preflightFileUploadCheck method
   */
  public UploadUrl preflightFileUploadCheck(PreflightFileUploadCheckRequestBody requestBody) {
    return preflightFileUploadCheck(requestBody, new PreflightFileUploadCheckHeaders());
  }

  /**
   * Performs a check to verify that a file will be accepted by Box before you upload the entire
   * file.
   *
   * @param headers Headers of preflightFileUploadCheck method
   */
  public UploadUrl preflightFileUploadCheck(PreflightFileUploadCheckHeaders headers) {
    return preflightFileUploadCheck(new PreflightFileUploadCheckRequestBody(), headers);
  }

  /**
   * Performs a check to verify that a file will be accepted by Box before you upload the entire
   * file.
   *
   * @param requestBody Request body of preflightFileUploadCheck method
   * @param headers Headers of preflightFileUploadCheck method
   */
  public UploadUrl preflightFileUploadCheck(
      PreflightFileUploadCheckRequestBody requestBody, PreflightFileUploadCheckHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/files/content"),
                        "OPTIONS")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadUrl.class);
  }

  /**
   * Uploads a small file to Box. For file sizes over 50MB we recommend using the Chunk Upload APIs.
   *
   * <p>The `attributes` part of the body must come **before** the `file` part. Requests that do not
   * follow this format when uploading the file will receive a HTTP `400` error with a
   * `metadata_after_file_contents` error code.
   *
   * @param requestBody Request body of uploadFile method
   */
  public Files uploadFile(UploadFileRequestBody requestBody) {
    return uploadFile(requestBody, new UploadFileQueryParams(), new UploadFileHeaders());
  }

  /**
   * Uploads a small file to Box. For file sizes over 50MB we recommend using the Chunk Upload APIs.
   *
   * <p>The `attributes` part of the body must come **before** the `file` part. Requests that do not
   * follow this format when uploading the file will receive a HTTP `400` error with a
   * `metadata_after_file_contents` error code.
   *
   * @param requestBody Request body of uploadFile method
   * @param queryParams Query parameters of uploadFile method
   */
  public Files uploadFile(UploadFileRequestBody requestBody, UploadFileQueryParams queryParams) {
    return uploadFile(requestBody, queryParams, new UploadFileHeaders());
  }

  /**
   * Uploads a small file to Box. For file sizes over 50MB we recommend using the Chunk Upload APIs.
   *
   * <p>The `attributes` part of the body must come **before** the `file` part. Requests that do not
   * follow this format when uploading the file will receive a HTTP `400` error with a
   * `metadata_after_file_contents` error code.
   *
   * @param requestBody Request body of uploadFile method
   * @param headers Headers of uploadFile method
   */
  public Files uploadFile(UploadFileRequestBody requestBody, UploadFileHeaders headers) {
    return uploadFile(requestBody, new UploadFileQueryParams(), headers);
  }

  /**
   * Uploads a small file to Box. For file sizes over 50MB we recommend using the Chunk Upload APIs.
   *
   * <p>The `attributes` part of the body must come **before** the `file` part. Requests that do not
   * follow this format when uploading the file will receive a HTTP `400` error with a
   * `metadata_after_file_contents` error code.
   *
   * @param requestBody Request body of uploadFile method
   * @param queryParams Query parameters of uploadFile method
   * @param headers Headers of uploadFile method
   */
  public Files uploadFile(
      UploadFileRequestBody requestBody,
      UploadFileQueryParams queryParams,
      UploadFileHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("content-md5", convertToString(headers.getContentMd5()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getUploadUrl(),
                            "/2.0/files/content"),
                        "POST")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .multipartData(
                        Arrays.asList(
                            new MultipartItem.Builder("attributes")
                                .data(JsonManager.serialize(requestBody.getAttributes()))
                                .build(),
                            new MultipartItem.Builder("file")
                                .fileStream(requestBody.getFile())
                                .fileName(requestBody.getFileFileName())
                                .contentType(requestBody.getFileContentType())
                                .build()))
                    .contentType("multipart/form-data")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Files.class);
  }

  /**
   * Upload a file with a preflight check
   *
   * @param requestBody The requestBody parameter
   */
  public Files uploadWithPreflightCheck(UploadWithPreflightCheckRequestBody requestBody) {
    return uploadWithPreflightCheck(
        requestBody,
        new UploadWithPreflightCheckQueryParams(),
        new UploadWithPreflightCheckHeaders());
  }

  /**
   * Upload a file with a preflight check
   *
   * @param requestBody The requestBody parameter
   * @param queryParams Query parameters of uploadFile method
   */
  public Files uploadWithPreflightCheck(
      UploadWithPreflightCheckRequestBody requestBody,
      UploadWithPreflightCheckQueryParams queryParams) {
    return uploadWithPreflightCheck(
        requestBody, queryParams, new UploadWithPreflightCheckHeaders());
  }

  /**
   * Upload a file with a preflight check
   *
   * @param requestBody The requestBody parameter
   * @param headers Headers of uploadFile method
   */
  public Files uploadWithPreflightCheck(
      UploadWithPreflightCheckRequestBody requestBody, UploadWithPreflightCheckHeaders headers) {
    return uploadWithPreflightCheck(
        requestBody, new UploadWithPreflightCheckQueryParams(), headers);
  }

  /**
   * Upload a file with a preflight check
   *
   * @param requestBody The requestBody parameter
   * @param queryParams Query parameters of uploadFile method
   * @param headers Headers of uploadFile method
   */
  public Files uploadWithPreflightCheck(
      UploadWithPreflightCheckRequestBody requestBody,
      UploadWithPreflightCheckQueryParams queryParams,
      UploadWithPreflightCheckHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("content-md5", convertToString(headers.getContentMd5()))),
                headers.getExtraHeaders()));
    UploadUrl preflightUploadUrl =
        this.preflightFileUploadCheck(
            new PreflightFileUploadCheckRequestBody.Builder()
                .name(requestBody.getAttributes().getName())
                .size(requestBody.getAttributes().getSize())
                .parent(
                    new PreflightFileUploadCheckRequestBodyParentField.Builder()
                        .id(requestBody.getAttributes().getParent().getId())
                        .build())
                .build(),
            new PreflightFileUploadCheckHeaders.Builder()
                .extraHeaders(headers.getExtraHeaders())
                .build());
    if (preflightUploadUrl.getUploadUrl() == null
        || !(preflightUploadUrl.getUploadUrl().contains("http"))) {
      throw new BoxSDKError("Unable to get preflight upload URL");
    }
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(preflightUploadUrl.getUploadUrl(), "POST")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .multipartData(
                        Arrays.asList(
                            new MultipartItem.Builder("attributes")
                                .data(JsonManager.serialize(requestBody.getAttributes()))
                                .build(),
                            new MultipartItem.Builder("file")
                                .fileStream(requestBody.getFile())
                                .fileName(requestBody.getFileFileName())
                                .contentType(requestBody.getFileContentType())
                                .build()))
                    .contentType("multipart/form-data")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Files.class);
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

    public UploadsManager build() {
      return new UploadsManager(this);
    }
  }
}
