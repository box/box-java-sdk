package com.box.sdkgen.managers.signtemplates;

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
import com.box.sdkgen.schemas.signtemplate.SignTemplate;
import com.box.sdkgen.schemas.signtemplates.SignTemplates;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class SignTemplatesManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public SignTemplatesManager() {
    this.networkSession = new NetworkSession();
  }

  protected SignTemplatesManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /** Gets Box Sign templates created by a user. */
  public SignTemplates getSignTemplates() {
    return getSignTemplates(new GetSignTemplatesQueryParams(), new GetSignTemplatesHeaders());
  }

  /**
   * Gets Box Sign templates created by a user.
   *
   * @param queryParams Query parameters of getSignTemplates method
   */
  public SignTemplates getSignTemplates(GetSignTemplatesQueryParams queryParams) {
    return getSignTemplates(queryParams, new GetSignTemplatesHeaders());
  }

  /**
   * Gets Box Sign templates created by a user.
   *
   * @param headers Headers of getSignTemplates method
   */
  public SignTemplates getSignTemplates(GetSignTemplatesHeaders headers) {
    return getSignTemplates(new GetSignTemplatesQueryParams(), headers);
  }

  /**
   * Gets Box Sign templates created by a user.
   *
   * @param queryParams Query parameters of getSignTemplates method
   * @param headers Headers of getSignTemplates method
   */
  public SignTemplates getSignTemplates(
      GetSignTemplatesQueryParams queryParams, GetSignTemplatesHeaders headers) {
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
                            "/2.0/sign_templates"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), SignTemplates.class);
  }

  /**
   * Fetches details of a specific Box Sign template.
   *
   * @param templateId The ID of a Box Sign template. Example:
   *     "123075213-7d117509-8f05-42e4-a5ef-5190a319d41d"
   */
  public SignTemplate getSignTemplateById(String templateId) {
    return getSignTemplateById(templateId, new GetSignTemplateByIdHeaders());
  }

  /**
   * Fetches details of a specific Box Sign template.
   *
   * @param templateId The ID of a Box Sign template. Example:
   *     "123075213-7d117509-8f05-42e4-a5ef-5190a319d41d"
   * @param headers Headers of getSignTemplateById method
   */
  public SignTemplate getSignTemplateById(String templateId, GetSignTemplateByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/sign_templates/",
                            convertToString(templateId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), SignTemplate.class);
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

    public SignTemplatesManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new SignTemplatesManager(this);
    }
  }
}
