package com.box.sdkgen.managers.fileclassifications;

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

public class FileClassificationsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public FileClassificationsManager() {
    this.networkSession = new NetworkSession();
  }

  protected FileClassificationsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public Classification getClassificationOnFile(String fileId) {
    return getClassificationOnFile(fileId, new GetClassificationOnFileHeaders());
  }

  public Classification getClassificationOnFile(
      String fileId, GetClassificationOnFileHeaders headers) {
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
                            "/metadata/enterprise/securityClassification-6VMVochwUWo"),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Classification.class);
  }

  public Classification addClassificationToFile(String fileId) {
    return addClassificationToFile(
        fileId, new AddClassificationToFileRequestBody(), new AddClassificationToFileHeaders());
  }

  public Classification addClassificationToFile(
      String fileId, AddClassificationToFileRequestBody requestBody) {
    return addClassificationToFile(fileId, requestBody, new AddClassificationToFileHeaders());
  }

  public Classification addClassificationToFile(
      String fileId, AddClassificationToFileHeaders headers) {
    return addClassificationToFile(fileId, new AddClassificationToFileRequestBody(), headers);
  }

  public Classification addClassificationToFile(
      String fileId,
      AddClassificationToFileRequestBody requestBody,
      AddClassificationToFileHeaders headers) {
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

  public Classification updateClassificationOnFile(
      String fileId, List<UpdateClassificationOnFileRequestBody> requestBody) {
    return updateClassificationOnFile(fileId, requestBody, new UpdateClassificationOnFileHeaders());
  }

  public Classification updateClassificationOnFile(
      String fileId,
      List<UpdateClassificationOnFileRequestBody> requestBody,
      UpdateClassificationOnFileHeaders headers) {
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

  public void deleteClassificationFromFile(String fileId) {
    deleteClassificationFromFile(fileId, new DeleteClassificationFromFileHeaders());
  }

  public void deleteClassificationFromFile(
      String fileId, DeleteClassificationFromFileHeaders headers) {
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

    public FileClassificationsManager build() {
      return new FileClassificationsManager(this);
    }
  }
}
