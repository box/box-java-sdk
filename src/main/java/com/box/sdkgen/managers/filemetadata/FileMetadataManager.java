package com.box.sdkgen.managers.filemetadata;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
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

  public Metadatas getFileMetadata(String fileId) {
    return getFileMetadata(fileId, new GetFileMetadataHeaders());
  }

  public Metadatas getFileMetadata(String fileId, GetFileMetadataHeaders headers) {
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
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Metadatas.class);
  }

  public MetadataFull getFileMetadataById(
      String fileId, GetFileMetadataByIdScope scope, String templateKey) {
    return getFileMetadataById(fileId, scope, templateKey, new GetFileMetadataByIdHeaders());
  }

  public MetadataFull getFileMetadataById(
      String fileId,
      GetFileMetadataByIdScope scope,
      String templateKey,
      GetFileMetadataByIdHeaders headers) {
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
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataFull.class);
  }

  public MetadataFull createFileMetadataById(
      String fileId,
      CreateFileMetadataByIdScope scope,
      String templateKey,
      Map<String, Object> requestBody) {
    return createFileMetadataById(
        fileId, scope, templateKey, requestBody, new CreateFileMetadataByIdHeaders());
  }

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

  public MetadataFull updateFileMetadataById(
      String fileId,
      UpdateFileMetadataByIdScope scope,
      String templateKey,
      List<UpdateFileMetadataByIdRequestBody> requestBody) {
    return updateFileMetadataById(
        fileId, scope, templateKey, requestBody, new UpdateFileMetadataByIdHeaders());
  }

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

  public void deleteFileMetadataById(
      String fileId, DeleteFileMetadataByIdScope scope, String templateKey) {
    deleteFileMetadataById(fileId, scope, templateKey, new DeleteFileMetadataByIdHeaders());
  }

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
