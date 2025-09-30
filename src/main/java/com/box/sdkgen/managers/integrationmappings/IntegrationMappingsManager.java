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

  public IntegrationMappings getSlackIntegrationMapping() {
    return getSlackIntegrationMapping(
        new GetSlackIntegrationMappingQueryParams(), new GetSlackIntegrationMappingHeaders());
  }

  public IntegrationMappings getSlackIntegrationMapping(
      GetSlackIntegrationMappingQueryParams queryParams) {
    return getSlackIntegrationMapping(queryParams, new GetSlackIntegrationMappingHeaders());
  }

  public IntegrationMappings getSlackIntegrationMapping(GetSlackIntegrationMappingHeaders headers) {
    return getSlackIntegrationMapping(new GetSlackIntegrationMappingQueryParams(), headers);
  }

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

  public IntegrationMapping createSlackIntegrationMapping(
      IntegrationMappingSlackCreateRequest requestBody) {
    return createSlackIntegrationMapping(requestBody, new CreateSlackIntegrationMappingHeaders());
  }

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

  public IntegrationMapping updateSlackIntegrationMappingById(String integrationMappingId) {
    return updateSlackIntegrationMappingById(
        integrationMappingId,
        new UpdateSlackIntegrationMappingByIdRequestBody(),
        new UpdateSlackIntegrationMappingByIdHeaders());
  }

  public IntegrationMapping updateSlackIntegrationMappingById(
      String integrationMappingId, UpdateSlackIntegrationMappingByIdRequestBody requestBody) {
    return updateSlackIntegrationMappingById(
        integrationMappingId, requestBody, new UpdateSlackIntegrationMappingByIdHeaders());
  }

  public IntegrationMapping updateSlackIntegrationMappingById(
      String integrationMappingId, UpdateSlackIntegrationMappingByIdHeaders headers) {
    return updateSlackIntegrationMappingById(
        integrationMappingId, new UpdateSlackIntegrationMappingByIdRequestBody(), headers);
  }

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

  public void deleteSlackIntegrationMappingById(String integrationMappingId) {
    deleteSlackIntegrationMappingById(
        integrationMappingId, new DeleteSlackIntegrationMappingByIdHeaders());
  }

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

  public IntegrationMappingsTeams getTeamsIntegrationMapping() {
    return getTeamsIntegrationMapping(
        new GetTeamsIntegrationMappingQueryParams(), new GetTeamsIntegrationMappingHeaders());
  }

  public IntegrationMappingsTeams getTeamsIntegrationMapping(
      GetTeamsIntegrationMappingQueryParams queryParams) {
    return getTeamsIntegrationMapping(queryParams, new GetTeamsIntegrationMappingHeaders());
  }

  public IntegrationMappingsTeams getTeamsIntegrationMapping(
      GetTeamsIntegrationMappingHeaders headers) {
    return getTeamsIntegrationMapping(new GetTeamsIntegrationMappingQueryParams(), headers);
  }

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

  public IntegrationMappingTeams createTeamsIntegrationMapping(
      IntegrationMappingTeamsCreateRequest requestBody) {
    return createTeamsIntegrationMapping(requestBody, new CreateTeamsIntegrationMappingHeaders());
  }

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

  public IntegrationMappingTeams updateTeamsIntegrationMappingById(String integrationMappingId) {
    return updateTeamsIntegrationMappingById(
        integrationMappingId,
        new UpdateTeamsIntegrationMappingByIdRequestBody(),
        new UpdateTeamsIntegrationMappingByIdHeaders());
  }

  public IntegrationMappingTeams updateTeamsIntegrationMappingById(
      String integrationMappingId, UpdateTeamsIntegrationMappingByIdRequestBody requestBody) {
    return updateTeamsIntegrationMappingById(
        integrationMappingId, requestBody, new UpdateTeamsIntegrationMappingByIdHeaders());
  }

  public IntegrationMappingTeams updateTeamsIntegrationMappingById(
      String integrationMappingId, UpdateTeamsIntegrationMappingByIdHeaders headers) {
    return updateTeamsIntegrationMappingById(
        integrationMappingId, new UpdateTeamsIntegrationMappingByIdRequestBody(), headers);
  }

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

  public void deleteTeamsIntegrationMappingById(String integrationMappingId) {
    deleteTeamsIntegrationMappingById(
        integrationMappingId, new DeleteTeamsIntegrationMappingByIdHeaders());
  }

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

    public IntegrationMappingsManager build() {
      return new IntegrationMappingsManager(this);
    }
  }
}
