package com.box.sdkgen.managers.metadatacascadepolicies;

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
import com.box.sdkgen.schemas.metadatacascadepolicies.MetadataCascadePolicies;
import com.box.sdkgen.schemas.metadatacascadepolicy.MetadataCascadePolicy;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class MetadataCascadePoliciesManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public MetadataCascadePoliciesManager() {
    this.networkSession = new NetworkSession();
  }

  protected MetadataCascadePoliciesManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public MetadataCascadePolicies getMetadataCascadePolicies(
      GetMetadataCascadePoliciesQueryParams queryParams) {
    return getMetadataCascadePolicies(queryParams, new GetMetadataCascadePoliciesHeaders());
  }

  public MetadataCascadePolicies getMetadataCascadePolicies(
      GetMetadataCascadePoliciesQueryParams queryParams,
      GetMetadataCascadePoliciesHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("folder_id", convertToString(queryParams.getFolderId())),
                entryOf("owner_enterprise_id", convertToString(queryParams.getOwnerEnterpriseId())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("offset", convertToString(queryParams.getOffset()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_cascade_policies"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataCascadePolicies.class);
  }

  public MetadataCascadePolicy createMetadataCascadePolicy(
      CreateMetadataCascadePolicyRequestBody requestBody) {
    return createMetadataCascadePolicy(requestBody, new CreateMetadataCascadePolicyHeaders());
  }

  public MetadataCascadePolicy createMetadataCascadePolicy(
      CreateMetadataCascadePolicyRequestBody requestBody,
      CreateMetadataCascadePolicyHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_cascade_policies"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataCascadePolicy.class);
  }

  public MetadataCascadePolicy getMetadataCascadePolicyById(String metadataCascadePolicyId) {
    return getMetadataCascadePolicyById(
        metadataCascadePolicyId, new GetMetadataCascadePolicyByIdHeaders());
  }

  public MetadataCascadePolicy getMetadataCascadePolicyById(
      String metadataCascadePolicyId, GetMetadataCascadePolicyByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_cascade_policies/",
                            convertToString(metadataCascadePolicyId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataCascadePolicy.class);
  }

  public void deleteMetadataCascadePolicyById(String metadataCascadePolicyId) {
    deleteMetadataCascadePolicyById(
        metadataCascadePolicyId, new DeleteMetadataCascadePolicyByIdHeaders());
  }

  public void deleteMetadataCascadePolicyById(
      String metadataCascadePolicyId, DeleteMetadataCascadePolicyByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_cascade_policies/",
                            convertToString(metadataCascadePolicyId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  public void applyMetadataCascadePolicy(
      String metadataCascadePolicyId, ApplyMetadataCascadePolicyRequestBody requestBody) {
    applyMetadataCascadePolicy(
        metadataCascadePolicyId, requestBody, new ApplyMetadataCascadePolicyHeaders());
  }

  public void applyMetadataCascadePolicy(
      String metadataCascadePolicyId,
      ApplyMetadataCascadePolicyRequestBody requestBody,
      ApplyMetadataCascadePolicyHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_cascade_policies/",
                            convertToString(metadataCascadePolicyId),
                            "/apply"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
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

    public MetadataCascadePoliciesManager build() {
      return new MetadataCascadePoliciesManager(this);
    }
  }
}
