package com.box.sdkgen.managers.classifications;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.classificationtemplate.ClassificationTemplate;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.List;
import java.util.Map;

public class ClassificationsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public ClassificationsManager() {
    this.networkSession = new NetworkSession();
  }

  protected ClassificationsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves the classification metadata template and lists all the classifications available to
   * this enterprise.
   *
   * <p>This API can also be called by including the enterprise ID in the URL explicitly, for
   * example `/metadata_templates/enterprise_12345/securityClassification-6VMVochwUWo/schema`.
   */
  public ClassificationTemplate getClassificationTemplate() {
    return getClassificationTemplate(new GetClassificationTemplateHeaders());
  }

  /**
   * Retrieves the classification metadata template and lists all the classifications available to
   * this enterprise.
   *
   * <p>This API can also be called by including the enterprise ID in the URL explicitly, for
   * example `/metadata_templates/enterprise_12345/securityClassification-6VMVochwUWo/schema`.
   *
   * @param headers Headers of getClassificationTemplate method
   */
  public ClassificationTemplate getClassificationTemplate(
      GetClassificationTemplateHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_templates/enterprise/securityClassification-6VMVochwUWo/schema"),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ClassificationTemplate.class);
  }

  /**
   * Adds one or more new classifications to the list of classifications available to the
   * enterprise.
   *
   * <p>This API can also be called by including the enterprise ID in the URL explicitly, for
   * example `/metadata_templates/enterprise_12345/securityClassification-6VMVochwUWo/schema`.
   *
   * @param requestBody Request body of addClassification method
   */
  public ClassificationTemplate addClassification(List<AddClassificationRequestBody> requestBody) {
    return addClassification(requestBody, new AddClassificationHeaders());
  }

  /**
   * Adds one or more new classifications to the list of classifications available to the
   * enterprise.
   *
   * <p>This API can also be called by including the enterprise ID in the URL explicitly, for
   * example `/metadata_templates/enterprise_12345/securityClassification-6VMVochwUWo/schema`.
   *
   * @param requestBody Request body of addClassification method
   * @param headers Headers of addClassification method
   */
  public ClassificationTemplate addClassification(
      List<AddClassificationRequestBody> requestBody, AddClassificationHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_templates/enterprise/securityClassification-6VMVochwUWo/schema#add"),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ClassificationTemplate.class);
  }

  /**
   * Updates the labels and descriptions of one or more classifications available to the enterprise.
   *
   * <p>This API can also be called by including the enterprise ID in the URL explicitly, for
   * example `/metadata_templates/enterprise_12345/securityClassification-6VMVochwUWo/schema`.
   *
   * @param requestBody Request body of updateClassification method
   */
  public ClassificationTemplate updateClassification(
      List<UpdateClassificationRequestBody> requestBody) {
    return updateClassification(requestBody, new UpdateClassificationHeaders());
  }

  /**
   * Updates the labels and descriptions of one or more classifications available to the enterprise.
   *
   * <p>This API can also be called by including the enterprise ID in the URL explicitly, for
   * example `/metadata_templates/enterprise_12345/securityClassification-6VMVochwUWo/schema`.
   *
   * @param requestBody Request body of updateClassification method
   * @param headers Headers of updateClassification method
   */
  public ClassificationTemplate updateClassification(
      List<UpdateClassificationRequestBody> requestBody, UpdateClassificationHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_templates/enterprise/securityClassification-6VMVochwUWo/schema#update"),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json-patch+json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ClassificationTemplate.class);
  }

  /**
   * When an enterprise does not yet have any classifications, this API call initializes the
   * classification template with an initial set of classifications.
   *
   * <p>If an enterprise already has a classification, the template will already exist and instead
   * an API call should be made to add additional classifications.
   *
   * @param requestBody Request body of createClassificationTemplate method
   */
  public ClassificationTemplate createClassificationTemplate(
      CreateClassificationTemplateRequestBody requestBody) {
    return createClassificationTemplate(requestBody, new CreateClassificationTemplateHeaders());
  }

  /**
   * When an enterprise does not yet have any classifications, this API call initializes the
   * classification template with an initial set of classifications.
   *
   * <p>If an enterprise already has a classification, the template will already exist and instead
   * an API call should be made to add additional classifications.
   *
   * @param requestBody Request body of createClassificationTemplate method
   * @param headers Headers of createClassificationTemplate method
   */
  public ClassificationTemplate createClassificationTemplate(
      CreateClassificationTemplateRequestBody requestBody,
      CreateClassificationTemplateHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_templates/schema#classifications"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ClassificationTemplate.class);
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

    public ClassificationsManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new ClassificationsManager(this);
    }
  }
}
