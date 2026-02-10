package com.box.sdkgen.managers.enterpriseconfigurations;

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
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationv2025r0.EnterpriseConfigurationV2025R0;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class EnterpriseConfigurationsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public EnterpriseConfigurationsManager() {
    this.networkSession = new NetworkSession();
  }

  protected EnterpriseConfigurationsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves the configuration for an enterprise.
   *
   * @param enterpriseId The ID of the enterprise. Example: "3442311"
   * @param queryParams Query parameters of getEnterpriseConfigurationByIdV2025R0 method
   */
  public EnterpriseConfigurationV2025R0 getEnterpriseConfigurationByIdV2025R0(
      String enterpriseId, GetEnterpriseConfigurationByIdV2025R0QueryParams queryParams) {
    return getEnterpriseConfigurationByIdV2025R0(
        enterpriseId, queryParams, new GetEnterpriseConfigurationByIdV2025R0Headers());
  }

  /**
   * Retrieves the configuration for an enterprise.
   *
   * @param enterpriseId The ID of the enterprise. Example: "3442311"
   * @param queryParams Query parameters of getEnterpriseConfigurationByIdV2025R0 method
   * @param headers Headers of getEnterpriseConfigurationByIdV2025R0 method
   */
  public EnterpriseConfigurationV2025R0 getEnterpriseConfigurationByIdV2025R0(
      String enterpriseId,
      GetEnterpriseConfigurationByIdV2025R0QueryParams queryParams,
      GetEnterpriseConfigurationByIdV2025R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("categories", convertToString(queryParams.getCategories()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/enterprise_configurations/",
                            convertToString(enterpriseId)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), EnterpriseConfigurationV2025R0.class);
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

    public EnterpriseConfigurationsManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new EnterpriseConfigurationsManager(this);
    }
  }
}
