package com.box.sdkgen.managers.foldermetadata;

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

  public Metadatas getFolderMetadata(String folderId) {
    return getFolderMetadata(folderId, new GetFolderMetadataHeaders());
  }

  public Metadatas getFolderMetadata(String folderId, GetFolderMetadataHeaders headers) {
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
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Metadatas.class);
  }

  public MetadataFull getFolderMetadataById(
      String folderId, GetFolderMetadataByIdScope scope, String templateKey) {
    return getFolderMetadataById(folderId, scope, templateKey, new GetFolderMetadataByIdHeaders());
  }

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

  public MetadataFull createFolderMetadataById(
      String folderId,
      CreateFolderMetadataByIdScope scope,
      String templateKey,
      Map<String, Object> requestBody) {
    return createFolderMetadataById(
        folderId, scope, templateKey, requestBody, new CreateFolderMetadataByIdHeaders());
  }

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

  public MetadataFull updateFolderMetadataById(
      String folderId,
      UpdateFolderMetadataByIdScope scope,
      String templateKey,
      List<UpdateFolderMetadataByIdRequestBody> requestBody) {
    return updateFolderMetadataById(
        folderId, scope, templateKey, requestBody, new UpdateFolderMetadataByIdHeaders());
  }

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

  public void deleteFolderMetadataById(
      String folderId, DeleteFolderMetadataByIdScope scope, String templateKey) {
    deleteFolderMetadataById(folderId, scope, templateKey, new DeleteFolderMetadataByIdHeaders());
  }

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

    public FolderMetadataManager build() {
      return new FolderMetadataManager(this);
    }
  }
}
