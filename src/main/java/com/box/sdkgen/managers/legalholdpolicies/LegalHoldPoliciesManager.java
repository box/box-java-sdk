package com.box.sdkgen.managers.legalholdpolicies;

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
import com.box.sdkgen.schemas.legalholdpolicies.LegalHoldPolicies;
import com.box.sdkgen.schemas.legalholdpolicy.LegalHoldPolicy;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class LegalHoldPoliciesManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public LegalHoldPoliciesManager() {
    this.networkSession = new NetworkSession();
  }

  protected LegalHoldPoliciesManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public LegalHoldPolicies getLegalHoldPolicies() {
    return getLegalHoldPolicies(
        new GetLegalHoldPoliciesQueryParams(), new GetLegalHoldPoliciesHeaders());
  }

  public LegalHoldPolicies getLegalHoldPolicies(GetLegalHoldPoliciesQueryParams queryParams) {
    return getLegalHoldPolicies(queryParams, new GetLegalHoldPoliciesHeaders());
  }

  public LegalHoldPolicies getLegalHoldPolicies(GetLegalHoldPoliciesHeaders headers) {
    return getLegalHoldPolicies(new GetLegalHoldPoliciesQueryParams(), headers);
  }

  public LegalHoldPolicies getLegalHoldPolicies(
      GetLegalHoldPoliciesQueryParams queryParams, GetLegalHoldPoliciesHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("policy_name", convertToString(queryParams.getPolicyName())),
                entryOf("fields", convertToString(queryParams.getFields())),
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
                            "/2.0/legal_hold_policies"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), LegalHoldPolicies.class);
  }

  public LegalHoldPolicy createLegalHoldPolicy(CreateLegalHoldPolicyRequestBody requestBody) {
    return createLegalHoldPolicy(requestBody, new CreateLegalHoldPolicyHeaders());
  }

  public LegalHoldPolicy createLegalHoldPolicy(
      CreateLegalHoldPolicyRequestBody requestBody, CreateLegalHoldPolicyHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/legal_hold_policies"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), LegalHoldPolicy.class);
  }

  public LegalHoldPolicy getLegalHoldPolicyById(String legalHoldPolicyId) {
    return getLegalHoldPolicyById(legalHoldPolicyId, new GetLegalHoldPolicyByIdHeaders());
  }

  public LegalHoldPolicy getLegalHoldPolicyById(
      String legalHoldPolicyId, GetLegalHoldPolicyByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/legal_hold_policies/",
                            convertToString(legalHoldPolicyId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), LegalHoldPolicy.class);
  }

  public LegalHoldPolicy updateLegalHoldPolicyById(String legalHoldPolicyId) {
    return updateLegalHoldPolicyById(
        legalHoldPolicyId,
        new UpdateLegalHoldPolicyByIdRequestBody(),
        new UpdateLegalHoldPolicyByIdHeaders());
  }

  public LegalHoldPolicy updateLegalHoldPolicyById(
      String legalHoldPolicyId, UpdateLegalHoldPolicyByIdRequestBody requestBody) {
    return updateLegalHoldPolicyById(
        legalHoldPolicyId, requestBody, new UpdateLegalHoldPolicyByIdHeaders());
  }

  public LegalHoldPolicy updateLegalHoldPolicyById(
      String legalHoldPolicyId, UpdateLegalHoldPolicyByIdHeaders headers) {
    return updateLegalHoldPolicyById(
        legalHoldPolicyId, new UpdateLegalHoldPolicyByIdRequestBody(), headers);
  }

  public LegalHoldPolicy updateLegalHoldPolicyById(
      String legalHoldPolicyId,
      UpdateLegalHoldPolicyByIdRequestBody requestBody,
      UpdateLegalHoldPolicyByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/legal_hold_policies/",
                            convertToString(legalHoldPolicyId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), LegalHoldPolicy.class);
  }

  public void deleteLegalHoldPolicyById(String legalHoldPolicyId) {
    deleteLegalHoldPolicyById(legalHoldPolicyId, new DeleteLegalHoldPolicyByIdHeaders());
  }

  public void deleteLegalHoldPolicyById(
      String legalHoldPolicyId, DeleteLegalHoldPolicyByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/legal_hold_policies/",
                            convertToString(legalHoldPolicyId)),
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

    public LegalHoldPoliciesManager build() {
      return new LegalHoldPoliciesManager(this);
    }
  }
}
