package com.box.sdkgen.managers.filerequests;

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
import com.box.sdkgen.schemas.filerequest.FileRequest;
import com.box.sdkgen.schemas.filerequestcopyrequest.FileRequestCopyRequest;
import com.box.sdkgen.schemas.filerequestupdaterequest.FileRequestUpdateRequest;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class FileRequestsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public FileRequestsManager() {
    this.networkSession = new NetworkSession();
  }

  protected FileRequestsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves the information about a file request.
   *
   * @param fileRequestId The unique identifier that represent a file request.
   *     <p>The ID for any file request can be determined by visiting a file request builder in the
   *     web application and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/filerequest/123` the `file_request_id` is `123`. Example: "123"
   */
  public FileRequest getFileRequestById(String fileRequestId) {
    return getFileRequestById(fileRequestId, new GetFileRequestByIdHeaders());
  }

  /**
   * Retrieves the information about a file request.
   *
   * @param fileRequestId The unique identifier that represent a file request.
   *     <p>The ID for any file request can be determined by visiting a file request builder in the
   *     web application and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/filerequest/123` the `file_request_id` is `123`. Example: "123"
   * @param headers Headers of getFileRequestById method
   */
  public FileRequest getFileRequestById(String fileRequestId, GetFileRequestByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/file_requests/",
                            convertToString(fileRequestId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileRequest.class);
  }

  /**
   * Updates a file request. This can be used to activate or deactivate a file request.
   *
   * @param fileRequestId The unique identifier that represent a file request.
   *     <p>The ID for any file request can be determined by visiting a file request builder in the
   *     web application and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/filerequest/123` the `file_request_id` is `123`. Example: "123"
   * @param requestBody Request body of updateFileRequestById method
   */
  public FileRequest updateFileRequestById(
      String fileRequestId, FileRequestUpdateRequest requestBody) {
    return updateFileRequestById(fileRequestId, requestBody, new UpdateFileRequestByIdHeaders());
  }

  /**
   * Updates a file request. This can be used to activate or deactivate a file request.
   *
   * @param fileRequestId The unique identifier that represent a file request.
   *     <p>The ID for any file request can be determined by visiting a file request builder in the
   *     web application and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/filerequest/123` the `file_request_id` is `123`. Example: "123"
   * @param requestBody Request body of updateFileRequestById method
   * @param headers Headers of updateFileRequestById method
   */
  public FileRequest updateFileRequestById(
      String fileRequestId,
      FileRequestUpdateRequest requestBody,
      UpdateFileRequestByIdHeaders headers) {
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
                            "/2.0/file_requests/",
                            convertToString(fileRequestId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileRequest.class);
  }

  /**
   * Deletes a file request permanently.
   *
   * @param fileRequestId The unique identifier that represent a file request.
   *     <p>The ID for any file request can be determined by visiting a file request builder in the
   *     web application and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/filerequest/123` the `file_request_id` is `123`. Example: "123"
   */
  public void deleteFileRequestById(String fileRequestId) {
    deleteFileRequestById(fileRequestId, new DeleteFileRequestByIdHeaders());
  }

  /**
   * Deletes a file request permanently.
   *
   * @param fileRequestId The unique identifier that represent a file request.
   *     <p>The ID for any file request can be determined by visiting a file request builder in the
   *     web application and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/filerequest/123` the `file_request_id` is `123`. Example: "123"
   * @param headers Headers of deleteFileRequestById method
   */
  public void deleteFileRequestById(String fileRequestId, DeleteFileRequestByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/file_requests/",
                            convertToString(fileRequestId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Copies an existing file request that is already present on one folder, and applies it to
   * another folder.
   *
   * @param fileRequestId The unique identifier that represent a file request.
   *     <p>The ID for any file request can be determined by visiting a file request builder in the
   *     web application and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/filerequest/123` the `file_request_id` is `123`. Example: "123"
   * @param requestBody Request body of createFileRequestCopy method
   */
  public FileRequest createFileRequestCopy(
      String fileRequestId, FileRequestCopyRequest requestBody) {
    return createFileRequestCopy(fileRequestId, requestBody, new CreateFileRequestCopyHeaders());
  }

  /**
   * Copies an existing file request that is already present on one folder, and applies it to
   * another folder.
   *
   * @param fileRequestId The unique identifier that represent a file request.
   *     <p>The ID for any file request can be determined by visiting a file request builder in the
   *     web application and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/filerequest/123` the `file_request_id` is `123`. Example: "123"
   * @param requestBody Request body of createFileRequestCopy method
   * @param headers Headers of createFileRequestCopy method
   */
  public FileRequest createFileRequestCopy(
      String fileRequestId,
      FileRequestCopyRequest requestBody,
      CreateFileRequestCopyHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/file_requests/",
                            convertToString(fileRequestId),
                            "/copy"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileRequest.class);
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

    public FileRequestsManager build() {
      return new FileRequestsManager(this);
    }
  }
}
