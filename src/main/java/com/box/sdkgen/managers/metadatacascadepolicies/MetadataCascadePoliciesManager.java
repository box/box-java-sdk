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

  /**
   * Retrieves a list of all the metadata cascade policies that are applied to a given folder. This
   * can not be used on the root folder with ID `0`.
   *
   * @param queryParams Query parameters of getMetadataCascadePolicies method
   */
  public MetadataCascadePolicies getMetadataCascadePolicies(
      GetMetadataCascadePoliciesQueryParams queryParams) {
    return getMetadataCascadePolicies(queryParams, new GetMetadataCascadePoliciesHeaders());
  }

  /**
   * Retrieves a list of all the metadata cascade policies that are applied to a given folder. This
   * can not be used on the root folder with ID `0`.
   *
   * @param queryParams Query parameters of getMetadataCascadePolicies method
   * @param headers Headers of getMetadataCascadePolicies method
   */
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

  /**
   * Creates a new metadata cascade policy that applies a given metadata template to a given folder
   * and automatically cascades it down to any files within that folder.
   *
   * <p>In order for the policy to be applied a metadata instance must first be applied to the
   * folder the policy is to be applied to.
   *
   * @param requestBody Request body of createMetadataCascadePolicy method
   */
  public MetadataCascadePolicy createMetadataCascadePolicy(
      CreateMetadataCascadePolicyRequestBody requestBody) {
    return createMetadataCascadePolicy(requestBody, new CreateMetadataCascadePolicyHeaders());
  }

  /**
   * Creates a new metadata cascade policy that applies a given metadata template to a given folder
   * and automatically cascades it down to any files within that folder.
   *
   * <p>In order for the policy to be applied a metadata instance must first be applied to the
   * folder the policy is to be applied to.
   *
   * @param requestBody Request body of createMetadataCascadePolicy method
   * @param headers Headers of createMetadataCascadePolicy method
   */
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

  /**
   * Retrieve a specific metadata cascade policy assigned to a folder.
   *
   * @param metadataCascadePolicyId The ID of the metadata cascade policy. Example:
   *     "6fd4ff89-8fc1-42cf-8b29-1890dedd26d7"
   */
  public MetadataCascadePolicy getMetadataCascadePolicyById(String metadataCascadePolicyId) {
    return getMetadataCascadePolicyById(
        metadataCascadePolicyId, new GetMetadataCascadePolicyByIdHeaders());
  }

  /**
   * Retrieve a specific metadata cascade policy assigned to a folder.
   *
   * @param metadataCascadePolicyId The ID of the metadata cascade policy. Example:
   *     "6fd4ff89-8fc1-42cf-8b29-1890dedd26d7"
   * @param headers Headers of getMetadataCascadePolicyById method
   */
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

  /**
   * Deletes a metadata cascade policy.
   *
   * @param metadataCascadePolicyId The ID of the metadata cascade policy. Example:
   *     "6fd4ff89-8fc1-42cf-8b29-1890dedd26d7"
   */
  public void deleteMetadataCascadePolicyById(String metadataCascadePolicyId) {
    deleteMetadataCascadePolicyById(
        metadataCascadePolicyId, new DeleteMetadataCascadePolicyByIdHeaders());
  }

  /**
   * Deletes a metadata cascade policy.
   *
   * @param metadataCascadePolicyId The ID of the metadata cascade policy. Example:
   *     "6fd4ff89-8fc1-42cf-8b29-1890dedd26d7"
   * @param headers Headers of deleteMetadataCascadePolicyById method
   */
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

  /**
   * Force the metadata on a folder with a metadata cascade policy to be applied to all of its
   * children. This can be used after creating a new cascade policy to enforce the metadata to be
   * cascaded down to all existing files within that folder.
   *
   * @param metadataCascadePolicyId The ID of the cascade policy to force-apply. Example:
   *     "6fd4ff89-8fc1-42cf-8b29-1890dedd26d7"
   * @param requestBody Request body of applyMetadataCascadePolicy method
   */
  public void applyMetadataCascadePolicy(
      String metadataCascadePolicyId, ApplyMetadataCascadePolicyRequestBody requestBody) {
    applyMetadataCascadePolicy(
        metadataCascadePolicyId, requestBody, new ApplyMetadataCascadePolicyHeaders());
  }

  /**
   * Force the metadata on a folder with a metadata cascade policy to be applied to all of its
   * children. This can be used after creating a new cascade policy to enforce the metadata to be
   * cascaded down to all existing files within that folder.
   *
   * @param metadataCascadePolicyId The ID of the cascade policy to force-apply. Example:
   *     "6fd4ff89-8fc1-42cf-8b29-1890dedd26d7"
   * @param requestBody Request body of applyMetadataCascadePolicy method
   * @param headers Headers of applyMetadataCascadePolicy method
   */
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

    public Builder() {}

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public MetadataCascadePoliciesManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new MetadataCascadePoliciesManager(this);
    }
  }
}
