package com.box.sdkgen.managers.storagepolicies;

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
import com.box.sdkgen.schemas.storagepolicies.StoragePolicies;
import com.box.sdkgen.schemas.storagepolicy.StoragePolicy;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class StoragePoliciesManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public StoragePoliciesManager() {
    this.networkSession = new NetworkSession();
  }

  protected StoragePoliciesManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /** Fetches all the storage policies in the enterprise. */
  public StoragePolicies getStoragePolicies() {
    return getStoragePolicies(new GetStoragePoliciesQueryParams(), new GetStoragePoliciesHeaders());
  }

  /**
   * Fetches all the storage policies in the enterprise.
   *
   * @param queryParams Query parameters of getStoragePolicies method
   */
  public StoragePolicies getStoragePolicies(GetStoragePoliciesQueryParams queryParams) {
    return getStoragePolicies(queryParams, new GetStoragePoliciesHeaders());
  }

  /**
   * Fetches all the storage policies in the enterprise.
   *
   * @param headers Headers of getStoragePolicies method
   */
  public StoragePolicies getStoragePolicies(GetStoragePoliciesHeaders headers) {
    return getStoragePolicies(new GetStoragePoliciesQueryParams(), headers);
  }

  /**
   * Fetches all the storage policies in the enterprise.
   *
   * @param queryParams Query parameters of getStoragePolicies method
   * @param headers Headers of getStoragePolicies method
   */
  public StoragePolicies getStoragePolicies(
      GetStoragePoliciesQueryParams queryParams, GetStoragePoliciesHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
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
                            "/2.0/storage_policies"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), StoragePolicies.class);
  }

  /**
   * Fetches a specific storage policy.
   *
   * @param storagePolicyId The ID of the storage policy. Example: "34342"
   */
  public StoragePolicy getStoragePolicyById(String storagePolicyId) {
    return getStoragePolicyById(storagePolicyId, new GetStoragePolicyByIdHeaders());
  }

  /**
   * Fetches a specific storage policy.
   *
   * @param storagePolicyId The ID of the storage policy. Example: "34342"
   * @param headers Headers of getStoragePolicyById method
   */
  public StoragePolicy getStoragePolicyById(
      String storagePolicyId, GetStoragePolicyByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/storage_policies/",
                            convertToString(storagePolicyId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), StoragePolicy.class);
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

    public StoragePoliciesManager build() {
      return new StoragePoliciesManager(this);
    }
  }
}
