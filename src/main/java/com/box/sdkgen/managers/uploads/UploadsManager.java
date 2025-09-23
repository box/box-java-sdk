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

  public Files uploadFileVersion(String fileId, UploadFileVersionRequestBody requestBody) {
    return uploadFileVersion(
        fileId, requestBody, new UploadFileVersionQueryParams(), new UploadFileVersionHeaders());
  }

  public Files uploadFileVersion(
      String fileId,
      UploadFileVersionRequestBody requestBody,
      UploadFileVersionQueryParams queryParams) {
    return uploadFileVersion(fileId, requestBody, queryParams, new UploadFileVersionHeaders());
  }

  public Files uploadFileVersion(
      String fileId, UploadFileVersionRequestBody requestBody, UploadFileVersionHeaders headers) {
    return uploadFileVersion(fileId, requestBody, new UploadFileVersionQueryParams(), headers);
  }

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

  public UploadUrl preflightFileUploadCheck() {
    return preflightFileUploadCheck(
        new PreflightFileUploadCheckRequestBody(), new PreflightFileUploadCheckHeaders());
  }

  public UploadUrl preflightFileUploadCheck(PreflightFileUploadCheckRequestBody requestBody) {
    return preflightFileUploadCheck(requestBody, new PreflightFileUploadCheckHeaders());
  }

  public UploadUrl preflightFileUploadCheck(PreflightFileUploadCheckHeaders headers) {
    return preflightFileUploadCheck(new PreflightFileUploadCheckRequestBody(), headers);
  }

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

  public Files uploadFile(UploadFileRequestBody requestBody) {
    return uploadFile(requestBody, new UploadFileQueryParams(), new UploadFileHeaders());
  }

  public Files uploadFile(UploadFileRequestBody requestBody, UploadFileQueryParams queryParams) {
    return uploadFile(requestBody, queryParams, new UploadFileHeaders());
  }

  public Files uploadFile(UploadFileRequestBody requestBody, UploadFileHeaders headers) {
    return uploadFile(requestBody, new UploadFileQueryParams(), headers);
  }

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

  public Files uploadWithPreflightCheck(UploadWithPreflightCheckRequestBody requestBody) {
    return uploadWithPreflightCheck(
        requestBody,
        new UploadWithPreflightCheckQueryParams(),
        new UploadWithPreflightCheckHeaders());
  }

  public Files uploadWithPreflightCheck(
      UploadWithPreflightCheckRequestBody requestBody,
      UploadWithPreflightCheckQueryParams queryParams) {
    return uploadWithPreflightCheck(
        requestBody, queryParams, new UploadWithPreflightCheckHeaders());
  }

  public Files uploadWithPreflightCheck(
      UploadWithPreflightCheckRequestBody requestBody, UploadWithPreflightCheckHeaders headers) {
    return uploadWithPreflightCheck(
        requestBody, new UploadWithPreflightCheckQueryParams(), headers);
  }

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
