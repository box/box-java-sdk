package com.box.sdkgen.managers.retentionpolicies;

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
import com.box.sdkgen.schemas.retentionpolicies.RetentionPolicies;
import com.box.sdkgen.schemas.retentionpolicy.RetentionPolicy;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class RetentionPoliciesManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public RetentionPoliciesManager() {
    this.networkSession = new NetworkSession();
  }

  protected RetentionPoliciesManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public RetentionPolicies getRetentionPolicies() {
    return getRetentionPolicies(
        new GetRetentionPoliciesQueryParams(), new GetRetentionPoliciesHeaders());
  }

  public RetentionPolicies getRetentionPolicies(GetRetentionPoliciesQueryParams queryParams) {
    return getRetentionPolicies(queryParams, new GetRetentionPoliciesHeaders());
  }

  public RetentionPolicies getRetentionPolicies(GetRetentionPoliciesHeaders headers) {
    return getRetentionPolicies(new GetRetentionPoliciesQueryParams(), headers);
  }

  public RetentionPolicies getRetentionPolicies(
      GetRetentionPoliciesQueryParams queryParams, GetRetentionPoliciesHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("policy_name", convertToString(queryParams.getPolicyName())),
                entryOf("policy_type", convertToString(queryParams.getPolicyType())),
                entryOf("created_by_user_id", convertToString(queryParams.getCreatedByUserId())),
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("marker", convertToString(queryParams.getMarker()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/retention_policies"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), RetentionPolicies.class);
  }

  public RetentionPolicy createRetentionPolicy(CreateRetentionPolicyRequestBody requestBody) {
    return createRetentionPolicy(requestBody, new CreateRetentionPolicyHeaders());
  }

  public RetentionPolicy createRetentionPolicy(
      CreateRetentionPolicyRequestBody requestBody, CreateRetentionPolicyHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/retention_policies"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), RetentionPolicy.class);
  }

  public RetentionPolicy getRetentionPolicyById(String retentionPolicyId) {
    return getRetentionPolicyById(
        retentionPolicyId,
        new GetRetentionPolicyByIdQueryParams(),
        new GetRetentionPolicyByIdHeaders());
  }

  public RetentionPolicy getRetentionPolicyById(
      String retentionPolicyId, GetRetentionPolicyByIdQueryParams queryParams) {
    return getRetentionPolicyById(
        retentionPolicyId, queryParams, new GetRetentionPolicyByIdHeaders());
  }

  public RetentionPolicy getRetentionPolicyById(
      String retentionPolicyId, GetRetentionPolicyByIdHeaders headers) {
    return getRetentionPolicyById(
        retentionPolicyId, new GetRetentionPolicyByIdQueryParams(), headers);
  }

  public RetentionPolicy getRetentionPolicyById(
      String retentionPolicyId,
      GetRetentionPolicyByIdQueryParams queryParams,
      GetRetentionPolicyByIdHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/retention_policies/",
                            convertToString(retentionPolicyId)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), RetentionPolicy.class);
  }

  public RetentionPolicy updateRetentionPolicyById(String retentionPolicyId) {
    return updateRetentionPolicyById(
        retentionPolicyId,
        new UpdateRetentionPolicyByIdRequestBody(),
        new UpdateRetentionPolicyByIdHeaders());
  }

  public RetentionPolicy updateRetentionPolicyById(
      String retentionPolicyId, UpdateRetentionPolicyByIdRequestBody requestBody) {
    return updateRetentionPolicyById(
        retentionPolicyId, requestBody, new UpdateRetentionPolicyByIdHeaders());
  }

  public RetentionPolicy updateRetentionPolicyById(
      String retentionPolicyId, UpdateRetentionPolicyByIdHeaders headers) {
    return updateRetentionPolicyById(
        retentionPolicyId, new UpdateRetentionPolicyByIdRequestBody(), headers);
  }

  public RetentionPolicy updateRetentionPolicyById(
      String retentionPolicyId,
      UpdateRetentionPolicyByIdRequestBody requestBody,
      UpdateRetentionPolicyByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/retention_policies/",
                            convertToString(retentionPolicyId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), RetentionPolicy.class);
  }

  public void deleteRetentionPolicyById(String retentionPolicyId) {
    deleteRetentionPolicyById(retentionPolicyId, new DeleteRetentionPolicyByIdHeaders());
  }

  public void deleteRetentionPolicyById(
      String retentionPolicyId, DeleteRetentionPolicyByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/retention_policies/",
                            convertToString(retentionPolicyId)),
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

    public RetentionPoliciesManager build() {
      return new RetentionPoliciesManager(this);
    }
  }
}
