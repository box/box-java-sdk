package com.box.sdkgen.managers.fileversionlegalholds;

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
import com.box.sdkgen.schemas.fileversionlegalhold.FileVersionLegalHold;
import com.box.sdkgen.schemas.fileversionlegalholds.FileVersionLegalHolds;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class FileVersionLegalHoldsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public FileVersionLegalHoldsManager() {
    this.networkSession = new NetworkSession();
  }

  protected FileVersionLegalHoldsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public FileVersionLegalHold getFileVersionLegalHoldById(String fileVersionLegalHoldId) {
    return getFileVersionLegalHoldById(
        fileVersionLegalHoldId, new GetFileVersionLegalHoldByIdHeaders());
  }

  public FileVersionLegalHold getFileVersionLegalHoldById(
      String fileVersionLegalHoldId, GetFileVersionLegalHoldByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/file_version_legal_holds/",
                            convertToString(fileVersionLegalHoldId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileVersionLegalHold.class);
  }

  public FileVersionLegalHolds getFileVersionLegalHolds(
      GetFileVersionLegalHoldsQueryParams queryParams) {
    return getFileVersionLegalHolds(queryParams, new GetFileVersionLegalHoldsHeaders());
  }

  public FileVersionLegalHolds getFileVersionLegalHolds(
      GetFileVersionLegalHoldsQueryParams queryParams, GetFileVersionLegalHoldsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("policy_id", convertToString(queryParams.getPolicyId())),
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
                            "/2.0/file_version_legal_holds"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileVersionLegalHolds.class);
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

    public FileVersionLegalHoldsManager build() {
      return new FileVersionLegalHoldsManager(this);
    }
  }
}
