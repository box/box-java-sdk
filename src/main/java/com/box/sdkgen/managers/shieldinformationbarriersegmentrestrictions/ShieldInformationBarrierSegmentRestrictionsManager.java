package com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions;

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
import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestriction.ShieldInformationBarrierSegmentRestriction;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictions.ShieldInformationBarrierSegmentRestrictions;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class ShieldInformationBarrierSegmentRestrictionsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public ShieldInformationBarrierSegmentRestrictionsManager() {
    this.networkSession = new NetworkSession();
  }

  protected ShieldInformationBarrierSegmentRestrictionsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public ShieldInformationBarrierSegmentRestriction
      getShieldInformationBarrierSegmentRestrictionById(
          String shieldInformationBarrierSegmentRestrictionId) {
    return getShieldInformationBarrierSegmentRestrictionById(
        shieldInformationBarrierSegmentRestrictionId,
        new GetShieldInformationBarrierSegmentRestrictionByIdHeaders());
  }

  public ShieldInformationBarrierSegmentRestriction
      getShieldInformationBarrierSegmentRestrictionById(
          String shieldInformationBarrierSegmentRestrictionId,
          GetShieldInformationBarrierSegmentRestrictionByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barrier_segment_restrictions/",
                            convertToString(shieldInformationBarrierSegmentRestrictionId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(
        response.getData(), ShieldInformationBarrierSegmentRestriction.class);
  }

  public void deleteShieldInformationBarrierSegmentRestrictionById(
      String shieldInformationBarrierSegmentRestrictionId) {
    deleteShieldInformationBarrierSegmentRestrictionById(
        shieldInformationBarrierSegmentRestrictionId,
        new DeleteShieldInformationBarrierSegmentRestrictionByIdHeaders());
  }

  public void deleteShieldInformationBarrierSegmentRestrictionById(
      String shieldInformationBarrierSegmentRestrictionId,
      DeleteShieldInformationBarrierSegmentRestrictionByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barrier_segment_restrictions/",
                            convertToString(shieldInformationBarrierSegmentRestrictionId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  public ShieldInformationBarrierSegmentRestrictions getShieldInformationBarrierSegmentRestrictions(
      GetShieldInformationBarrierSegmentRestrictionsQueryParams queryParams) {
    return getShieldInformationBarrierSegmentRestrictions(
        queryParams, new GetShieldInformationBarrierSegmentRestrictionsHeaders());
  }

  public ShieldInformationBarrierSegmentRestrictions getShieldInformationBarrierSegmentRestrictions(
      GetShieldInformationBarrierSegmentRestrictionsQueryParams queryParams,
      GetShieldInformationBarrierSegmentRestrictionsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf(
                    "shield_information_barrier_segment_id",
                    convertToString(queryParams.getShieldInformationBarrierSegmentId())),
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
                            "/2.0/shield_information_barrier_segment_restrictions"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(
        response.getData(), ShieldInformationBarrierSegmentRestrictions.class);
  }

  public ShieldInformationBarrierSegmentRestriction
      createShieldInformationBarrierSegmentRestriction(
          CreateShieldInformationBarrierSegmentRestrictionRequestBody requestBody) {
    return createShieldInformationBarrierSegmentRestriction(
        requestBody, new CreateShieldInformationBarrierSegmentRestrictionHeaders());
  }

  public ShieldInformationBarrierSegmentRestriction
      createShieldInformationBarrierSegmentRestriction(
          CreateShieldInformationBarrierSegmentRestrictionRequestBody requestBody,
          CreateShieldInformationBarrierSegmentRestrictionHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barrier_segment_restrictions"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(
        response.getData(), ShieldInformationBarrierSegmentRestriction.class);
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

    public ShieldInformationBarrierSegmentRestrictionsManager build() {
      return new ShieldInformationBarrierSegmentRestrictionsManager(this);
    }
  }
}
