package com.box.sdkgen.managers.shieldlists;

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
import com.box.sdkgen.schemas.v2025r0.shieldlistscreatev2025r0.ShieldListsCreateV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistsupdatev2025r0.ShieldListsUpdateV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistsv2025r0.ShieldListsV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistv2025r0.ShieldListV2025R0;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class ShieldListsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public ShieldListsManager() {
    this.networkSession = new NetworkSession();
  }

  protected ShieldListsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public ShieldListsV2025R0 getShieldListsV2025R0() {
    return getShieldListsV2025R0(new GetShieldListsV2025R0Headers());
  }

  public ShieldListsV2025R0 getShieldListsV2025R0(GetShieldListsV2025R0Headers headers) {
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
                            "/2.0/shield_lists"),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldListsV2025R0.class);
  }

  public ShieldListV2025R0 createShieldListV2025R0(ShieldListsCreateV2025R0 requestBody) {
    return createShieldListV2025R0(requestBody, new CreateShieldListV2025R0Headers());
  }

  public ShieldListV2025R0 createShieldListV2025R0(
      ShieldListsCreateV2025R0 requestBody, CreateShieldListV2025R0Headers headers) {
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
                            "/2.0/shield_lists"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldListV2025R0.class);
  }

  public ShieldListV2025R0 getShieldListByIdV2025R0(String shieldListId) {
    return getShieldListByIdV2025R0(shieldListId, new GetShieldListByIdV2025R0Headers());
  }

  public ShieldListV2025R0 getShieldListByIdV2025R0(
      String shieldListId, GetShieldListByIdV2025R0Headers headers) {
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
                            "/2.0/shield_lists/",
                            convertToString(shieldListId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldListV2025R0.class);
  }

  public void deleteShieldListByIdV2025R0(String shieldListId) {
    deleteShieldListByIdV2025R0(shieldListId, new DeleteShieldListByIdV2025R0Headers());
  }

  public void deleteShieldListByIdV2025R0(
      String shieldListId, DeleteShieldListByIdV2025R0Headers headers) {
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
                            "/2.0/shield_lists/",
                            convertToString(shieldListId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  public ShieldListV2025R0 updateShieldListByIdV2025R0(
      String shieldListId, ShieldListsUpdateV2025R0 requestBody) {
    return updateShieldListByIdV2025R0(
        shieldListId, requestBody, new UpdateShieldListByIdV2025R0Headers());
  }

  public ShieldListV2025R0 updateShieldListByIdV2025R0(
      String shieldListId,
      ShieldListsUpdateV2025R0 requestBody,
      UpdateShieldListByIdV2025R0Headers headers) {
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
                            "/2.0/shield_lists/",
                            convertToString(shieldListId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldListV2025R0.class);
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

    public ShieldListsManager build() {
      return new ShieldListsManager(this);
    }
  }
}
