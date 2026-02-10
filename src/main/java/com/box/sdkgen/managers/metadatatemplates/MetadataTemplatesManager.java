package com.box.sdkgen.managers.metadatatemplates;

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
import com.box.sdkgen.schemas.metadatatemplate.MetadataTemplate;
import com.box.sdkgen.schemas.metadatatemplates.MetadataTemplates;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.List;
import java.util.Map;

public class MetadataTemplatesManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public MetadataTemplatesManager() {
    this.networkSession = new NetworkSession();
  }

  protected MetadataTemplatesManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Finds a metadata template by searching for the ID of an instance of the template.
   *
   * @param queryParams Query parameters of getMetadataTemplatesByInstanceId method
   */
  public MetadataTemplates getMetadataTemplatesByInstanceId(
      GetMetadataTemplatesByInstanceIdQueryParams queryParams) {
    return getMetadataTemplatesByInstanceId(
        queryParams, new GetMetadataTemplatesByInstanceIdHeaders());
  }

  /**
   * Finds a metadata template by searching for the ID of an instance of the template.
   *
   * @param queryParams Query parameters of getMetadataTemplatesByInstanceId method
   * @param headers Headers of getMetadataTemplatesByInstanceId method
   */
  public MetadataTemplates getMetadataTemplatesByInstanceId(
      GetMetadataTemplatesByInstanceIdQueryParams queryParams,
      GetMetadataTemplatesByInstanceIdHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf(
                    "metadata_instance_id", convertToString(queryParams.getMetadataInstanceId())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_templates"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTemplates.class);
  }

  /**
   * Retrieves a metadata template by its `scope` and `templateKey` values.
   *
   * <p>To find the `scope` and `templateKey` for a template, list all templates for an enterprise
   * or globally, or list all templates applied to a file or folder.
   *
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   */
  public MetadataTemplate getMetadataTemplate(GetMetadataTemplateScope scope, String templateKey) {
    return getMetadataTemplate(scope, templateKey, new GetMetadataTemplateHeaders());
  }

  /**
   * Retrieves a metadata template by its `scope` and `templateKey` values.
   *
   * <p>To find the `scope` and `templateKey` for a template, list all templates for an enterprise
   * or globally, or list all templates applied to a file or folder.
   *
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param headers Headers of getMetadataTemplate method
   */
  public MetadataTemplate getMetadataTemplate(
      GetMetadataTemplateScope scope, String templateKey, GetMetadataTemplateHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_templates/",
                            convertToString(scope),
                            "/",
                            convertToString(templateKey),
                            "/schema"),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTemplate.class);
  }

  /**
   * Updates a metadata template.
   *
   * <p>The metadata template can only be updated if the template already exists.
   *
   * <p>The update is applied atomically. If any errors occur during the application of the
   * operations, the metadata template will not be changed.
   *
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param requestBody Request body of updateMetadataTemplate method
   */
  public MetadataTemplate updateMetadataTemplate(
      UpdateMetadataTemplateScope scope,
      String templateKey,
      List<UpdateMetadataTemplateRequestBody> requestBody) {
    return updateMetadataTemplate(
        scope, templateKey, requestBody, new UpdateMetadataTemplateHeaders());
  }

  /**
   * Updates a metadata template.
   *
   * <p>The metadata template can only be updated if the template already exists.
   *
   * <p>The update is applied atomically. If any errors occur during the application of the
   * operations, the metadata template will not be changed.
   *
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param requestBody Request body of updateMetadataTemplate method
   * @param headers Headers of updateMetadataTemplate method
   */
  public MetadataTemplate updateMetadataTemplate(
      UpdateMetadataTemplateScope scope,
      String templateKey,
      List<UpdateMetadataTemplateRequestBody> requestBody,
      UpdateMetadataTemplateHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_templates/",
                            convertToString(scope),
                            "/",
                            convertToString(templateKey),
                            "/schema"),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json-patch+json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTemplate.class);
  }

  /**
   * Delete a metadata template and its instances. This deletion is permanent and can not be
   * reversed.
   *
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   */
  public void deleteMetadataTemplate(DeleteMetadataTemplateScope scope, String templateKey) {
    deleteMetadataTemplate(scope, templateKey, new DeleteMetadataTemplateHeaders());
  }

  /**
   * Delete a metadata template and its instances. This deletion is permanent and can not be
   * reversed.
   *
   * @param scope The scope of the metadata template. Example: "global"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param headers Headers of deleteMetadataTemplate method
   */
  public void deleteMetadataTemplate(
      DeleteMetadataTemplateScope scope,
      String templateKey,
      DeleteMetadataTemplateHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_templates/",
                            convertToString(scope),
                            "/",
                            convertToString(templateKey),
                            "/schema"),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Retrieves a metadata template by its ID.
   *
   * @param templateId The ID of the template. Example: "f7a9891f"
   */
  public MetadataTemplate getMetadataTemplateById(String templateId) {
    return getMetadataTemplateById(templateId, new GetMetadataTemplateByIdHeaders());
  }

  /**
   * Retrieves a metadata template by its ID.
   *
   * @param templateId The ID of the template. Example: "f7a9891f"
   * @param headers Headers of getMetadataTemplateById method
   */
  public MetadataTemplate getMetadataTemplateById(
      String templateId, GetMetadataTemplateByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_templates/",
                            convertToString(templateId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTemplate.class);
  }

  /**
   * Used to retrieve all generic, global metadata templates available to all enterprises using Box.
   */
  public MetadataTemplates getGlobalMetadataTemplates() {
    return getGlobalMetadataTemplates(
        new GetGlobalMetadataTemplatesQueryParams(), new GetGlobalMetadataTemplatesHeaders());
  }

  /**
   * Used to retrieve all generic, global metadata templates available to all enterprises using Box.
   *
   * @param queryParams Query parameters of getGlobalMetadataTemplates method
   */
  public MetadataTemplates getGlobalMetadataTemplates(
      GetGlobalMetadataTemplatesQueryParams queryParams) {
    return getGlobalMetadataTemplates(queryParams, new GetGlobalMetadataTemplatesHeaders());
  }

  /**
   * Used to retrieve all generic, global metadata templates available to all enterprises using Box.
   *
   * @param headers Headers of getGlobalMetadataTemplates method
   */
  public MetadataTemplates getGlobalMetadataTemplates(GetGlobalMetadataTemplatesHeaders headers) {
    return getGlobalMetadataTemplates(new GetGlobalMetadataTemplatesQueryParams(), headers);
  }

  /**
   * Used to retrieve all generic, global metadata templates available to all enterprises using Box.
   *
   * @param queryParams Query parameters of getGlobalMetadataTemplates method
   * @param headers Headers of getGlobalMetadataTemplates method
   */
  public MetadataTemplates getGlobalMetadataTemplates(
      GetGlobalMetadataTemplatesQueryParams queryParams,
      GetGlobalMetadataTemplatesHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_templates/global"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTemplates.class);
  }

  /**
   * Used to retrieve all metadata templates created to be used specifically within the user's
   * enterprise.
   */
  public MetadataTemplates getEnterpriseMetadataTemplates() {
    return getEnterpriseMetadataTemplates(
        new GetEnterpriseMetadataTemplatesQueryParams(),
        new GetEnterpriseMetadataTemplatesHeaders());
  }

  /**
   * Used to retrieve all metadata templates created to be used specifically within the user's
   * enterprise.
   *
   * @param queryParams Query parameters of getEnterpriseMetadataTemplates method
   */
  public MetadataTemplates getEnterpriseMetadataTemplates(
      GetEnterpriseMetadataTemplatesQueryParams queryParams) {
    return getEnterpriseMetadataTemplates(queryParams, new GetEnterpriseMetadataTemplatesHeaders());
  }

  /**
   * Used to retrieve all metadata templates created to be used specifically within the user's
   * enterprise.
   *
   * @param headers Headers of getEnterpriseMetadataTemplates method
   */
  public MetadataTemplates getEnterpriseMetadataTemplates(
      GetEnterpriseMetadataTemplatesHeaders headers) {
    return getEnterpriseMetadataTemplates(new GetEnterpriseMetadataTemplatesQueryParams(), headers);
  }

  /**
   * Used to retrieve all metadata templates created to be used specifically within the user's
   * enterprise.
   *
   * @param queryParams Query parameters of getEnterpriseMetadataTemplates method
   * @param headers Headers of getEnterpriseMetadataTemplates method
   */
  public MetadataTemplates getEnterpriseMetadataTemplates(
      GetEnterpriseMetadataTemplatesQueryParams queryParams,
      GetEnterpriseMetadataTemplatesHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_templates/enterprise"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTemplates.class);
  }

  /**
   * Creates a new metadata template that can be applied to files and folders.
   *
   * @param requestBody Request body of createMetadataTemplate method
   */
  public MetadataTemplate createMetadataTemplate(CreateMetadataTemplateRequestBody requestBody) {
    return createMetadataTemplate(requestBody, new CreateMetadataTemplateHeaders());
  }

  /**
   * Creates a new metadata template that can be applied to files and folders.
   *
   * @param requestBody Request body of createMetadataTemplate method
   * @param headers Headers of createMetadataTemplate method
   */
  public MetadataTemplate createMetadataTemplate(
      CreateMetadataTemplateRequestBody requestBody, CreateMetadataTemplateHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_templates/schema"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTemplate.class);
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

    public MetadataTemplatesManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new MetadataTemplatesManager(this);
    }
  }
}
