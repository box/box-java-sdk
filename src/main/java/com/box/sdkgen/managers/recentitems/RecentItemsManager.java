package com.box.sdkgen.managers.recentitems;

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
import com.box.sdkgen.schemas.recentitems.RecentItems;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class RecentItemsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public RecentItemsManager() {
    this.networkSession = new NetworkSession();
  }

  protected RecentItemsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public RecentItems getRecentItems() {
    return getRecentItems(new GetRecentItemsQueryParams(), new GetRecentItemsHeaders());
  }

  public RecentItems getRecentItems(GetRecentItemsQueryParams queryParams) {
    return getRecentItems(queryParams, new GetRecentItemsHeaders());
  }

  public RecentItems getRecentItems(GetRecentItemsHeaders headers) {
    return getRecentItems(new GetRecentItemsQueryParams(), headers);
  }

  public RecentItems getRecentItems(
      GetRecentItemsQueryParams queryParams, GetRecentItemsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("marker", convertToString(queryParams.getMarker()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/recent_items"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), RecentItems.class);
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

    public RecentItemsManager build() {
      return new RecentItemsManager(this);
    }
  }
}
