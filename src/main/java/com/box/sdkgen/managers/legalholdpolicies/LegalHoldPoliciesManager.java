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

  /** Retrieves a list of legal hold policies that belong to an enterprise. */
  public LegalHoldPolicies getLegalHoldPolicies() {
    return getLegalHoldPolicies(
        new GetLegalHoldPoliciesQueryParams(), new GetLegalHoldPoliciesHeaders());
  }

  /**
   * Retrieves a list of legal hold policies that belong to an enterprise.
   *
   * @param queryParams Query parameters of getLegalHoldPolicies method
   */
  public LegalHoldPolicies getLegalHoldPolicies(GetLegalHoldPoliciesQueryParams queryParams) {
    return getLegalHoldPolicies(queryParams, new GetLegalHoldPoliciesHeaders());
  }

  /**
   * Retrieves a list of legal hold policies that belong to an enterprise.
   *
   * @param headers Headers of getLegalHoldPolicies method
   */
  public LegalHoldPolicies getLegalHoldPolicies(GetLegalHoldPoliciesHeaders headers) {
    return getLegalHoldPolicies(new GetLegalHoldPoliciesQueryParams(), headers);
  }

  /**
   * Retrieves a list of legal hold policies that belong to an enterprise.
   *
   * @param queryParams Query parameters of getLegalHoldPolicies method
   * @param headers Headers of getLegalHoldPolicies method
   */
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

  /**
   * Create a new legal hold policy.
   *
   * @param requestBody Request body of createLegalHoldPolicy method
   */
  public LegalHoldPolicy createLegalHoldPolicy(CreateLegalHoldPolicyRequestBody requestBody) {
    return createLegalHoldPolicy(requestBody, new CreateLegalHoldPolicyHeaders());
  }

  /**
   * Create a new legal hold policy.
   *
   * @param requestBody Request body of createLegalHoldPolicy method
   * @param headers Headers of createLegalHoldPolicy method
   */
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

  /**
   * Retrieve a legal hold policy.
   *
   * @param legalHoldPolicyId The ID of the legal hold policy. Example: "324432"
   */
  public LegalHoldPolicy getLegalHoldPolicyById(String legalHoldPolicyId) {
    return getLegalHoldPolicyById(legalHoldPolicyId, new GetLegalHoldPolicyByIdHeaders());
  }

  /**
   * Retrieve a legal hold policy.
   *
   * @param legalHoldPolicyId The ID of the legal hold policy. Example: "324432"
   * @param headers Headers of getLegalHoldPolicyById method
   */
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

  /**
   * Update legal hold policy.
   *
   * @param legalHoldPolicyId The ID of the legal hold policy. Example: "324432"
   */
  public LegalHoldPolicy updateLegalHoldPolicyById(String legalHoldPolicyId) {
    return updateLegalHoldPolicyById(
        legalHoldPolicyId,
        new UpdateLegalHoldPolicyByIdRequestBody(),
        new UpdateLegalHoldPolicyByIdHeaders());
  }

  /**
   * Update legal hold policy.
   *
   * @param legalHoldPolicyId The ID of the legal hold policy. Example: "324432"
   * @param requestBody Request body of updateLegalHoldPolicyById method
   */
  public LegalHoldPolicy updateLegalHoldPolicyById(
      String legalHoldPolicyId, UpdateLegalHoldPolicyByIdRequestBody requestBody) {
    return updateLegalHoldPolicyById(
        legalHoldPolicyId, requestBody, new UpdateLegalHoldPolicyByIdHeaders());
  }

  /**
   * Update legal hold policy.
   *
   * @param legalHoldPolicyId The ID of the legal hold policy. Example: "324432"
   * @param headers Headers of updateLegalHoldPolicyById method
   */
  public LegalHoldPolicy updateLegalHoldPolicyById(
      String legalHoldPolicyId, UpdateLegalHoldPolicyByIdHeaders headers) {
    return updateLegalHoldPolicyById(
        legalHoldPolicyId, new UpdateLegalHoldPolicyByIdRequestBody(), headers);
  }

  /**
   * Update legal hold policy.
   *
   * @param legalHoldPolicyId The ID of the legal hold policy. Example: "324432"
   * @param requestBody Request body of updateLegalHoldPolicyById method
   * @param headers Headers of updateLegalHoldPolicyById method
   */
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

  /**
   * Delete an existing legal hold policy.
   *
   * <p>This is an asynchronous process. The policy will not be fully deleted yet when the response
   * returns.
   *
   * @param legalHoldPolicyId The ID of the legal hold policy. Example: "324432"
   */
  public void deleteLegalHoldPolicyById(String legalHoldPolicyId) {
    deleteLegalHoldPolicyById(legalHoldPolicyId, new DeleteLegalHoldPolicyByIdHeaders());
  }

  /**
   * Delete an existing legal hold policy.
   *
   * <p>This is an asynchronous process. The policy will not be fully deleted yet when the response
   * returns.
   *
   * @param legalHoldPolicyId The ID of the legal hold policy. Example: "324432"
   * @param headers Headers of deleteLegalHoldPolicyById method
   */
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

    public Builder() {}

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public LegalHoldPoliciesManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new LegalHoldPoliciesManager(this);
    }
  }
}
