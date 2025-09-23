package com.box.sdkgen.managers.archives;

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
import com.box.sdkgen.schemas.v2025r0.archivesv2025r0.ArchivesV2025R0;
import com.box.sdkgen.schemas.v2025r0.archivev2025r0.ArchiveV2025R0;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class ArchivesManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public ArchivesManager() {
    this.networkSession = new NetworkSession();
  }

  protected ArchivesManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public ArchivesV2025R0 getArchivesV2025R0() {
    return getArchivesV2025R0(new GetArchivesV2025R0QueryParams(), new GetArchivesV2025R0Headers());
  }

  public ArchivesV2025R0 getArchivesV2025R0(GetArchivesV2025R0QueryParams queryParams) {
    return getArchivesV2025R0(queryParams, new GetArchivesV2025R0Headers());
  }

  public ArchivesV2025R0 getArchivesV2025R0(GetArchivesV2025R0Headers headers) {
    return getArchivesV2025R0(new GetArchivesV2025R0QueryParams(), headers);
  }

  public ArchivesV2025R0 getArchivesV2025R0(
      GetArchivesV2025R0QueryParams queryParams, GetArchivesV2025R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("marker", convertToString(queryParams.getMarker()))));
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
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/archives"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ArchivesV2025R0.class);
  }

  public ArchiveV2025R0 createArchiveV2025R0(CreateArchiveV2025R0RequestBody requestBody) {
    return createArchiveV2025R0(requestBody, new CreateArchiveV2025R0Headers());
  }

  public ArchiveV2025R0 createArchiveV2025R0(
      CreateArchiveV2025R0RequestBody requestBody, CreateArchiveV2025R0Headers headers) {
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
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/archives"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ArchiveV2025R0.class);
  }

  public void deleteArchiveByIdV2025R0(String archiveId) {
    deleteArchiveByIdV2025R0(archiveId, new DeleteArchiveByIdV2025R0Headers());
  }

  public void deleteArchiveByIdV2025R0(String archiveId, DeleteArchiveByIdV2025R0Headers headers) {
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
                            "/2.0/archives/",
                            convertToString(archiveId)),
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

    public ArchivesManager build() {
      return new ArchivesManager(this);
    }
  }
}
