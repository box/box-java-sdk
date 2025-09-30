package com.box.sdkgen.managers.folderclassifications;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.classification.Classification;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.List;
import java.util.Map;

public class FolderClassificationsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public FolderClassificationsManager() {
    this.networkSession = new NetworkSession();
  }

  protected FolderClassificationsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public Classification getClassificationOnFolder(String folderId) {
    return getClassificationOnFolder(folderId, new GetClassificationOnFolderHeaders());
  }

  public Classification getClassificationOnFolder(
      String folderId, GetClassificationOnFolderHeaders headers) {
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
                            "/metadata/enterprise/securityClassification-6VMVochwUWo"),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Classification.class);
  }

  public Classification addClassificationToFolder(String folderId) {
    return addClassificationToFolder(
        folderId,
        new AddClassificationToFolderRequestBody(),
        new AddClassificationToFolderHeaders());
  }

  public Classification addClassificationToFolder(
      String folderId, AddClassificationToFolderRequestBody requestBody) {
    return addClassificationToFolder(folderId, requestBody, new AddClassificationToFolderHeaders());
  }

  public Classification addClassificationToFolder(
      String folderId, AddClassificationToFolderHeaders headers) {
    return addClassificationToFolder(folderId, new AddClassificationToFolderRequestBody(), headers);
  }

  public Classification addClassificationToFolder(
      String folderId,
      AddClassificationToFolderRequestBody requestBody,
      AddClassificationToFolderHeaders headers) {
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
                            "/metadata/enterprise/securityClassification-6VMVochwUWo"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Classification.class);
  }

  public Classification updateClassificationOnFolder(
      String folderId, List<UpdateClassificationOnFolderRequestBody> requestBody) {
    return updateClassificationOnFolder(
        folderId, requestBody, new UpdateClassificationOnFolderHeaders());
  }

  public Classification updateClassificationOnFolder(
      String folderId,
      List<UpdateClassificationOnFolderRequestBody> requestBody,
      UpdateClassificationOnFolderHeaders headers) {
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
                            "/metadata/enterprise/securityClassification-6VMVochwUWo"),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json-patch+json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Classification.class);
  }

  public void deleteClassificationFromFolder(String folderId) {
    deleteClassificationFromFolder(folderId, new DeleteClassificationFromFolderHeaders());
  }

  public void deleteClassificationFromFolder(
      String folderId, DeleteClassificationFromFolderHeaders headers) {
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
                            "/metadata/enterprise/securityClassification-6VMVochwUWo"),
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

    public FolderClassificationsManager build() {
      return new FolderClassificationsManager(this);
    }
  }
}
