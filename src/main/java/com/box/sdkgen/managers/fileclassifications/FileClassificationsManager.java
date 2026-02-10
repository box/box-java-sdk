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

  /**
   * Retrieves the classification metadata instance that has been applied to a file.
   *
   * <p>This API can also be called by including the enterprise ID in the URL explicitly, for
   * example `/files/:id//enterprise_12345/securityClassification-6VMVochwUWo`.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public Classification getClassificationOnFile(String fileId) {
    return getClassificationOnFile(fileId, new GetClassificationOnFileHeaders());
  }

  /**
   * Retrieves the classification metadata instance that has been applied to a file.
   *
   * <p>This API can also be called by including the enterprise ID in the URL explicitly, for
   * example `/files/:id//enterprise_12345/securityClassification-6VMVochwUWo`.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of getClassificationOnFile method
   */
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

  /**
   * Adds a classification to a file by specifying the label of the classification to add.
   *
   * <p>This API can also be called by including the enterprise ID in the URL explicitly, for
   * example `/files/:id//enterprise_12345/securityClassification-6VMVochwUWo`.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public Classification addClassificationToFile(String fileId) {
    return addClassificationToFile(
        fileId, new AddClassificationToFileRequestBody(), new AddClassificationToFileHeaders());
  }

  /**
   * Adds a classification to a file by specifying the label of the classification to add.
   *
   * <p>This API can also be called by including the enterprise ID in the URL explicitly, for
   * example `/files/:id//enterprise_12345/securityClassification-6VMVochwUWo`.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of addClassificationToFile method
   */
  public Classification addClassificationToFile(
      String fileId, AddClassificationToFileRequestBody requestBody) {
    return addClassificationToFile(fileId, requestBody, new AddClassificationToFileHeaders());
  }

  /**
   * Adds a classification to a file by specifying the label of the classification to add.
   *
   * <p>This API can also be called by including the enterprise ID in the URL explicitly, for
   * example `/files/:id//enterprise_12345/securityClassification-6VMVochwUWo`.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of addClassificationToFile method
   */
  public Classification addClassificationToFile(
      String fileId, AddClassificationToFileHeaders headers) {
    return addClassificationToFile(fileId, new AddClassificationToFileRequestBody(), headers);
  }

  /**
   * Adds a classification to a file by specifying the label of the classification to add.
   *
   * <p>This API can also be called by including the enterprise ID in the URL explicitly, for
   * example `/files/:id//enterprise_12345/securityClassification-6VMVochwUWo`.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of addClassificationToFile method
   * @param headers Headers of addClassificationToFile method
   */
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

  /**
   * Updates a classification on a file.
   *
   * <p>The classification can only be updated if a classification has already been applied to the
   * file before. When editing classifications, only values are defined for the enterprise will be
   * accepted.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of updateClassificationOnFile method
   */
  public Classification updateClassificationOnFile(
      String fileId, List<UpdateClassificationOnFileRequestBody> requestBody) {
    return updateClassificationOnFile(fileId, requestBody, new UpdateClassificationOnFileHeaders());
  }

  /**
   * Updates a classification on a file.
   *
   * <p>The classification can only be updated if a classification has already been applied to the
   * file before. When editing classifications, only values are defined for the enterprise will be
   * accepted.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of updateClassificationOnFile method
   * @param headers Headers of updateClassificationOnFile method
   */
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

  /**
   * Removes any classifications from a file.
   *
   * <p>This API can also be called by including the enterprise ID in the URL explicitly, for
   * example `/files/:id//enterprise_12345/securityClassification-6VMVochwUWo`.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public void deleteClassificationFromFile(String fileId) {
    deleteClassificationFromFile(fileId, new DeleteClassificationFromFileHeaders());
  }

  /**
   * Removes any classifications from a file.
   *
   * <p>This API can also be called by including the enterprise ID in the URL explicitly, for
   * example `/files/:id//enterprise_12345/securityClassification-6VMVochwUWo`.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of deleteClassificationFromFile method
   */
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

    public Builder() {}

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public FileClassificationsManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new FileClassificationsManager(this);
    }
  }
}
