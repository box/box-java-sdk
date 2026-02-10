package com.box.sdkgen.managers.integrationmappings;

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
import com.box.sdkgen.schemas.integrationmapping.IntegrationMapping;
import com.box.sdkgen.schemas.integrationmappings.IntegrationMappings;
import com.box.sdkgen.schemas.integrationmappingslackcreaterequest.IntegrationMappingSlackCreateRequest;
import com.box.sdkgen.schemas.integrationmappingsteams.IntegrationMappingsTeams;
import com.box.sdkgen.schemas.integrationmappingteams.IntegrationMappingTeams;
import com.box.sdkgen.schemas.integrationmappingteamscreaterequest.IntegrationMappingTeamsCreateRequest;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class IntegrationMappingsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public IntegrationMappingsManager() {
    this.networkSession = new NetworkSession();
  }

  protected IntegrationMappingsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Lists [Slack integration
   * mappings](https://support.box.com/hc/en-us/articles/4415585987859-Box-as-the-Content-Layer-for-Slack)
   * in a users' enterprise.
   *
   * <p>You need Admin or Co-Admin role to use this endpoint.
   */
  public IntegrationMappings getSlackIntegrationMapping() {
    return getSlackIntegrationMapping(
        new GetSlackIntegrationMappingQueryParams(), new GetSlackIntegrationMappingHeaders());
  }

  /**
   * Lists [Slack integration
   * mappings](https://support.box.com/hc/en-us/articles/4415585987859-Box-as-the-Content-Layer-for-Slack)
   * in a users' enterprise.
   *
   * <p>You need Admin or Co-Admin role to use this endpoint.
   *
   * @param queryParams Query parameters of getSlackIntegrationMapping method
   */
  public IntegrationMappings getSlackIntegrationMapping(
      GetSlackIntegrationMappingQueryParams queryParams) {
    return getSlackIntegrationMapping(queryParams, new GetSlackIntegrationMappingHeaders());
  }

  /**
   * Lists [Slack integration
   * mappings](https://support.box.com/hc/en-us/articles/4415585987859-Box-as-the-Content-Layer-for-Slack)
   * in a users' enterprise.
   *
   * <p>You need Admin or Co-Admin role to use this endpoint.
   *
   * @param headers Headers of getSlackIntegrationMapping method
   */
  public IntegrationMappings getSlackIntegrationMapping(GetSlackIntegrationMappingHeaders headers) {
    return getSlackIntegrationMapping(new GetSlackIntegrationMappingQueryParams(), headers);
  }

  /**
   * Lists [Slack integration
   * mappings](https://support.box.com/hc/en-us/articles/4415585987859-Box-as-the-Content-Layer-for-Slack)
   * in a users' enterprise.
   *
   * <p>You need Admin or Co-Admin role to use this endpoint.
   *
   * @param queryParams Query parameters of getSlackIntegrationMapping method
   * @param headers Headers of getSlackIntegrationMapping method
   */
  public IntegrationMappings getSlackIntegrationMapping(
      GetSlackIntegrationMappingQueryParams queryParams,
      GetSlackIntegrationMappingHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("partner_item_type", convertToString(queryParams.getPartnerItemType())),
                entryOf("partner_item_id", convertToString(queryParams.getPartnerItemId())),
                entryOf("box_item_id", convertToString(queryParams.getBoxItemId())),
                entryOf("box_item_type", convertToString(queryParams.getBoxItemType())),
                entryOf(
                    "is_manually_created", convertToString(queryParams.getIsManuallyCreated()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/integration_mappings/slack"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), IntegrationMappings.class);
  }

  /**
   * Creates a [Slack integration
   * mapping](https://support.box.com/hc/en-us/articles/4415585987859-Box-as-the-Content-Layer-for-Slack)
   * by mapping a Slack channel to a Box item.
   *
   * <p>You need Admin or Co-Admin role to use this endpoint.
   *
   * @param requestBody Request body of createSlackIntegrationMapping method
   */
  public IntegrationMapping createSlackIntegrationMapping(
      IntegrationMappingSlackCreateRequest requestBody) {
    return createSlackIntegrationMapping(requestBody, new CreateSlackIntegrationMappingHeaders());
  }

  /**
   * Creates a [Slack integration
   * mapping](https://support.box.com/hc/en-us/articles/4415585987859-Box-as-the-Content-Layer-for-Slack)
   * by mapping a Slack channel to a Box item.
   *
   * <p>You need Admin or Co-Admin role to use this endpoint.
   *
   * @param requestBody Request body of createSlackIntegrationMapping method
   * @param headers Headers of createSlackIntegrationMapping method
   */
  public IntegrationMapping createSlackIntegrationMapping(
      IntegrationMappingSlackCreateRequest requestBody,
      CreateSlackIntegrationMappingHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/integration_mappings/slack"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), IntegrationMapping.class);
  }

  /**
   * Updates a [Slack integration
   * mapping](https://support.box.com/hc/en-us/articles/4415585987859-Box-as-the-Content-Layer-for-Slack).
   * Supports updating the Box folder ID and options.
   *
   * <p>You need Admin or Co-Admin role to use this endpoint.
   *
   * @param integrationMappingId An ID of an integration mapping. Example: "11235432"
   */
  public IntegrationMapping updateSlackIntegrationMappingById(String integrationMappingId) {
    return updateSlackIntegrationMappingById(
        integrationMappingId,
        new UpdateSlackIntegrationMappingByIdRequestBody(),
        new UpdateSlackIntegrationMappingByIdHeaders());
  }

  /**
   * Updates a [Slack integration
   * mapping](https://support.box.com/hc/en-us/articles/4415585987859-Box-as-the-Content-Layer-for-Slack).
   * Supports updating the Box folder ID and options.
   *
   * <p>You need Admin or Co-Admin role to use this endpoint.
   *
   * @param integrationMappingId An ID of an integration mapping. Example: "11235432"
   * @param requestBody Request body of updateSlackIntegrationMappingById method
   */
  public IntegrationMapping updateSlackIntegrationMappingById(
      String integrationMappingId, UpdateSlackIntegrationMappingByIdRequestBody requestBody) {
    return updateSlackIntegrationMappingById(
        integrationMappingId, requestBody, new UpdateSlackIntegrationMappingByIdHeaders());
  }

  /**
   * Updates a [Slack integration
   * mapping](https://support.box.com/hc/en-us/articles/4415585987859-Box-as-the-Content-Layer-for-Slack).
   * Supports updating the Box folder ID and options.
   *
   * <p>You need Admin or Co-Admin role to use this endpoint.
   *
   * @param integrationMappingId An ID of an integration mapping. Example: "11235432"
   * @param headers Headers of updateSlackIntegrationMappingById method
   */
  public IntegrationMapping updateSlackIntegrationMappingById(
      String integrationMappingId, UpdateSlackIntegrationMappingByIdHeaders headers) {
    return updateSlackIntegrationMappingById(
        integrationMappingId, new UpdateSlackIntegrationMappingByIdRequestBody(), headers);
  }

  /**
   * Updates a [Slack integration
   * mapping](https://support.box.com/hc/en-us/articles/4415585987859-Box-as-the-Content-Layer-for-Slack).
   * Supports updating the Box folder ID and options.
   *
   * <p>You need Admin or Co-Admin role to use this endpoint.
   *
   * @param integrationMappingId An ID of an integration mapping. Example: "11235432"
   * @param requestBody Request body of updateSlackIntegrationMappingById method
   * @param headers Headers of updateSlackIntegrationMappingById method
   */
  public IntegrationMapping updateSlackIntegrationMappingById(
      String integrationMappingId,
      UpdateSlackIntegrationMappingByIdRequestBody requestBody,
      UpdateSlackIntegrationMappingByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/integration_mappings/slack/",
                            convertToString(integrationMappingId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), IntegrationMapping.class);
  }

  /**
   * Deletes a [Slack integration
   * mapping](https://support.box.com/hc/en-us/articles/4415585987859-Box-as-the-Content-Layer-for-Slack).
   *
   * <p>You need Admin or Co-Admin role to use this endpoint.
   *
   * @param integrationMappingId An ID of an integration mapping. Example: "11235432"
   */
  public void deleteSlackIntegrationMappingById(String integrationMappingId) {
    deleteSlackIntegrationMappingById(
        integrationMappingId, new DeleteSlackIntegrationMappingByIdHeaders());
  }

  /**
   * Deletes a [Slack integration
   * mapping](https://support.box.com/hc/en-us/articles/4415585987859-Box-as-the-Content-Layer-for-Slack).
   *
   * <p>You need Admin or Co-Admin role to use this endpoint.
   *
   * @param integrationMappingId An ID of an integration mapping. Example: "11235432"
   * @param headers Headers of deleteSlackIntegrationMappingById method
   */
  public void deleteSlackIntegrationMappingById(
      String integrationMappingId, DeleteSlackIntegrationMappingByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/integration_mappings/slack/",
                            convertToString(integrationMappingId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Lists [Teams integration
   * mappings](https://support.box.com/hc/en-us/articles/360044681474-Using-Box-for-Teams) in a
   * users' enterprise. You need Admin or Co-Admin role to use this endpoint.
   */
  public IntegrationMappingsTeams getTeamsIntegrationMapping() {
    return getTeamsIntegrationMapping(
        new GetTeamsIntegrationMappingQueryParams(), new GetTeamsIntegrationMappingHeaders());
  }

  /**
   * Lists [Teams integration
   * mappings](https://support.box.com/hc/en-us/articles/360044681474-Using-Box-for-Teams) in a
   * users' enterprise. You need Admin or Co-Admin role to use this endpoint.
   *
   * @param queryParams Query parameters of getTeamsIntegrationMapping method
   */
  public IntegrationMappingsTeams getTeamsIntegrationMapping(
      GetTeamsIntegrationMappingQueryParams queryParams) {
    return getTeamsIntegrationMapping(queryParams, new GetTeamsIntegrationMappingHeaders());
  }

  /**
   * Lists [Teams integration
   * mappings](https://support.box.com/hc/en-us/articles/360044681474-Using-Box-for-Teams) in a
   * users' enterprise. You need Admin or Co-Admin role to use this endpoint.
   *
   * @param headers Headers of getTeamsIntegrationMapping method
   */
  public IntegrationMappingsTeams getTeamsIntegrationMapping(
      GetTeamsIntegrationMappingHeaders headers) {
    return getTeamsIntegrationMapping(new GetTeamsIntegrationMappingQueryParams(), headers);
  }

  /**
   * Lists [Teams integration
   * mappings](https://support.box.com/hc/en-us/articles/360044681474-Using-Box-for-Teams) in a
   * users' enterprise. You need Admin or Co-Admin role to use this endpoint.
   *
   * @param queryParams Query parameters of getTeamsIntegrationMapping method
   * @param headers Headers of getTeamsIntegrationMapping method
   */
  public IntegrationMappingsTeams getTeamsIntegrationMapping(
      GetTeamsIntegrationMappingQueryParams queryParams,
      GetTeamsIntegrationMappingHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("partner_item_type", convertToString(queryParams.getPartnerItemType())),
                entryOf("partner_item_id", convertToString(queryParams.getPartnerItemId())),
                entryOf("box_item_id", convertToString(queryParams.getBoxItemId())),
                entryOf("box_item_type", convertToString(queryParams.getBoxItemType()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/integration_mappings/teams"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), IntegrationMappingsTeams.class);
  }

  /**
   * Creates a [Teams integration
   * mapping](https://support.box.com/hc/en-us/articles/360044681474-Using-Box-for-Teams) by mapping
   * a Teams channel to a Box item. You need Admin or Co-Admin role to use this endpoint.
   *
   * @param requestBody Request body of createTeamsIntegrationMapping method
   */
  public IntegrationMappingTeams createTeamsIntegrationMapping(
      IntegrationMappingTeamsCreateRequest requestBody) {
    return createTeamsIntegrationMapping(requestBody, new CreateTeamsIntegrationMappingHeaders());
  }

  /**
   * Creates a [Teams integration
   * mapping](https://support.box.com/hc/en-us/articles/360044681474-Using-Box-for-Teams) by mapping
   * a Teams channel to a Box item. You need Admin or Co-Admin role to use this endpoint.
   *
   * @param requestBody Request body of createTeamsIntegrationMapping method
   * @param headers Headers of createTeamsIntegrationMapping method
   */
  public IntegrationMappingTeams createTeamsIntegrationMapping(
      IntegrationMappingTeamsCreateRequest requestBody,
      CreateTeamsIntegrationMappingHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/integration_mappings/teams"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), IntegrationMappingTeams.class);
  }

  /**
   * Updates a [Teams integration
   * mapping](https://support.box.com/hc/en-us/articles/360044681474-Using-Box-for-Teams). Supports
   * updating the Box folder ID and options. You need Admin or Co-Admin role to use this endpoint.
   *
   * @param integrationMappingId An ID of an integration mapping. Example: "11235432"
   */
  public IntegrationMappingTeams updateTeamsIntegrationMappingById(String integrationMappingId) {
    return updateTeamsIntegrationMappingById(
        integrationMappingId,
        new UpdateTeamsIntegrationMappingByIdRequestBody(),
        new UpdateTeamsIntegrationMappingByIdHeaders());
  }

  /**
   * Updates a [Teams integration
   * mapping](https://support.box.com/hc/en-us/articles/360044681474-Using-Box-for-Teams). Supports
   * updating the Box folder ID and options. You need Admin or Co-Admin role to use this endpoint.
   *
   * @param integrationMappingId An ID of an integration mapping. Example: "11235432"
   * @param requestBody Request body of updateTeamsIntegrationMappingById method
   */
  public IntegrationMappingTeams updateTeamsIntegrationMappingById(
      String integrationMappingId, UpdateTeamsIntegrationMappingByIdRequestBody requestBody) {
    return updateTeamsIntegrationMappingById(
        integrationMappingId, requestBody, new UpdateTeamsIntegrationMappingByIdHeaders());
  }

  /**
   * Updates a [Teams integration
   * mapping](https://support.box.com/hc/en-us/articles/360044681474-Using-Box-for-Teams). Supports
   * updating the Box folder ID and options. You need Admin or Co-Admin role to use this endpoint.
   *
   * @param integrationMappingId An ID of an integration mapping. Example: "11235432"
   * @param headers Headers of updateTeamsIntegrationMappingById method
   */
  public IntegrationMappingTeams updateTeamsIntegrationMappingById(
      String integrationMappingId, UpdateTeamsIntegrationMappingByIdHeaders headers) {
    return updateTeamsIntegrationMappingById(
        integrationMappingId, new UpdateTeamsIntegrationMappingByIdRequestBody(), headers);
  }

  /**
   * Updates a [Teams integration
   * mapping](https://support.box.com/hc/en-us/articles/360044681474-Using-Box-for-Teams). Supports
   * updating the Box folder ID and options. You need Admin or Co-Admin role to use this endpoint.
   *
   * @param integrationMappingId An ID of an integration mapping. Example: "11235432"
   * @param requestBody Request body of updateTeamsIntegrationMappingById method
   * @param headers Headers of updateTeamsIntegrationMappingById method
   */
  public IntegrationMappingTeams updateTeamsIntegrationMappingById(
      String integrationMappingId,
      UpdateTeamsIntegrationMappingByIdRequestBody requestBody,
      UpdateTeamsIntegrationMappingByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/integration_mappings/teams/",
                            convertToString(integrationMappingId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), IntegrationMappingTeams.class);
  }

  /**
   * Deletes a [Teams integration
   * mapping](https://support.box.com/hc/en-us/articles/360044681474-Using-Box-for-Teams). You need
   * Admin or Co-Admin role to use this endpoint.
   *
   * @param integrationMappingId An ID of an integration mapping. Example: "11235432"
   */
  public void deleteTeamsIntegrationMappingById(String integrationMappingId) {
    deleteTeamsIntegrationMappingById(
        integrationMappingId, new DeleteTeamsIntegrationMappingByIdHeaders());
  }

  /**
   * Deletes a [Teams integration
   * mapping](https://support.box.com/hc/en-us/articles/360044681474-Using-Box-for-Teams). You need
   * Admin or Co-Admin role to use this endpoint.
   *
   * @param integrationMappingId An ID of an integration mapping. Example: "11235432"
   * @param headers Headers of deleteTeamsIntegrationMappingById method
   */
  public void deleteTeamsIntegrationMappingById(
      String integrationMappingId, DeleteTeamsIntegrationMappingByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/integration_mappings/teams/",
                            convertToString(integrationMappingId)),
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

    public IntegrationMappingsManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new IntegrationMappingsManager(this);
    }
  }
}
