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

  public FileRequest getFileRequestById(String fileRequestId) {
    return getFileRequestById(fileRequestId, new GetFileRequestByIdHeaders());
  }

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

  public FileRequest updateFileRequestById(
      String fileRequestId, FileRequestUpdateRequest requestBody) {
    return updateFileRequestById(fileRequestId, requestBody, new UpdateFileRequestByIdHeaders());
  }

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

  public void deleteFileRequestById(String fileRequestId) {
    deleteFileRequestById(fileRequestId, new DeleteFileRequestByIdHeaders());
  }

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

  public FileRequest createFileRequestCopy(
      String fileRequestId, FileRequestCopyRequest requestBody) {
    return createFileRequestCopy(fileRequestId, requestBody, new CreateFileRequestCopyHeaders());
  }

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
