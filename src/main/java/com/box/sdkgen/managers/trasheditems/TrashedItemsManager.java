package com.box.sdkgen.managers.trasheditems;

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
import com.box.sdkgen.schemas.items.Items;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class TrashedItemsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public TrashedItemsManager() {
    this.networkSession = new NetworkSession();
  }

  protected TrashedItemsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves the files and folders that have been moved to the trash.
   *
   * <p>Any attribute in the full files or folders objects can be passed in with the `fields`
   * parameter to retrieve those specific attributes that are not returned by default.
   *
   * <p>This endpoint defaults to use offset-based pagination, yet also supports marker-based
   * pagination using the `marker` parameter.
   */
  public Items getTrashedItems() {
    return getTrashedItems(new GetTrashedItemsQueryParams(), new GetTrashedItemsHeaders());
  }

  /**
   * Retrieves the files and folders that have been moved to the trash.
   *
   * <p>Any attribute in the full files or folders objects can be passed in with the `fields`
   * parameter to retrieve those specific attributes that are not returned by default.
   *
   * <p>This endpoint defaults to use offset-based pagination, yet also supports marker-based
   * pagination using the `marker` parameter.
   *
   * @param queryParams Query parameters of getTrashedItems method
   */
  public Items getTrashedItems(GetTrashedItemsQueryParams queryParams) {
    return getTrashedItems(queryParams, new GetTrashedItemsHeaders());
  }

  /**
   * Retrieves the files and folders that have been moved to the trash.
   *
   * <p>Any attribute in the full files or folders objects can be passed in with the `fields`
   * parameter to retrieve those specific attributes that are not returned by default.
   *
   * <p>This endpoint defaults to use offset-based pagination, yet also supports marker-based
   * pagination using the `marker` parameter.
   *
   * @param headers Headers of getTrashedItems method
   */
  public Items getTrashedItems(GetTrashedItemsHeaders headers) {
    return getTrashedItems(new GetTrashedItemsQueryParams(), headers);
  }

  /**
   * Retrieves the files and folders that have been moved to the trash.
   *
   * <p>Any attribute in the full files or folders objects can be passed in with the `fields`
   * parameter to retrieve those specific attributes that are not returned by default.
   *
   * <p>This endpoint defaults to use offset-based pagination, yet also supports marker-based
   * pagination using the `marker` parameter.
   *
   * @param queryParams Query parameters of getTrashedItems method
   * @param headers Headers of getTrashedItems method
   */
  public Items getTrashedItems(
      GetTrashedItemsQueryParams queryParams, GetTrashedItemsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("offset", convertToString(queryParams.getOffset())),
                entryOf("usemarker", convertToString(queryParams.getUsemarker())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("direction", convertToString(queryParams.getDirection())),
                entryOf("sort", convertToString(queryParams.getSort()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/folders/trash/items"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Items.class);
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

    public TrashedItemsManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new TrashedItemsManager(this);
    }
  }
}
