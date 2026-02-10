package com.box.sdkgen.managers.collections;

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
import com.box.sdkgen.schemas.collection.Collection;
import com.box.sdkgen.schemas.collections.Collections;
import com.box.sdkgen.schemas.itemsoffsetpaginated.ItemsOffsetPaginated;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class CollectionsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public CollectionsManager() {
    this.networkSession = new NetworkSession();
  }

  protected CollectionsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves all collections for a given user.
   *
   * <p>Currently, only the `favorites` collection is supported.
   */
  public Collections getCollections() {
    return getCollections(new GetCollectionsQueryParams(), new GetCollectionsHeaders());
  }

  /**
   * Retrieves all collections for a given user.
   *
   * <p>Currently, only the `favorites` collection is supported.
   *
   * @param queryParams Query parameters of getCollections method
   */
  public Collections getCollections(GetCollectionsQueryParams queryParams) {
    return getCollections(queryParams, new GetCollectionsHeaders());
  }

  /**
   * Retrieves all collections for a given user.
   *
   * <p>Currently, only the `favorites` collection is supported.
   *
   * @param headers Headers of getCollections method
   */
  public Collections getCollections(GetCollectionsHeaders headers) {
    return getCollections(new GetCollectionsQueryParams(), headers);
  }

  /**
   * Retrieves all collections for a given user.
   *
   * <p>Currently, only the `favorites` collection is supported.
   *
   * @param queryParams Query parameters of getCollections method
   * @param headers Headers of getCollections method
   */
  public Collections getCollections(
      GetCollectionsQueryParams queryParams, GetCollectionsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("offset", convertToString(queryParams.getOffset())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/collections"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Collections.class);
  }

  /**
   * Retrieves the files and/or folders contained within this collection.
   *
   * @param collectionId The ID of the collection. Example: "926489"
   */
  public ItemsOffsetPaginated getCollectionItems(String collectionId) {
    return getCollectionItems(
        collectionId, new GetCollectionItemsQueryParams(), new GetCollectionItemsHeaders());
  }

  /**
   * Retrieves the files and/or folders contained within this collection.
   *
   * @param collectionId The ID of the collection. Example: "926489"
   * @param queryParams Query parameters of getCollectionItems method
   */
  public ItemsOffsetPaginated getCollectionItems(
      String collectionId, GetCollectionItemsQueryParams queryParams) {
    return getCollectionItems(collectionId, queryParams, new GetCollectionItemsHeaders());
  }

  /**
   * Retrieves the files and/or folders contained within this collection.
   *
   * @param collectionId The ID of the collection. Example: "926489"
   * @param headers Headers of getCollectionItems method
   */
  public ItemsOffsetPaginated getCollectionItems(
      String collectionId, GetCollectionItemsHeaders headers) {
    return getCollectionItems(collectionId, new GetCollectionItemsQueryParams(), headers);
  }

  /**
   * Retrieves the files and/or folders contained within this collection.
   *
   * @param collectionId The ID of the collection. Example: "926489"
   * @param queryParams Query parameters of getCollectionItems method
   * @param headers Headers of getCollectionItems method
   */
  public ItemsOffsetPaginated getCollectionItems(
      String collectionId,
      GetCollectionItemsQueryParams queryParams,
      GetCollectionItemsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("offset", convertToString(queryParams.getOffset())),
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
                            "/2.0/collections/",
                            convertToString(collectionId),
                            "/items"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ItemsOffsetPaginated.class);
  }

  /**
   * Retrieves a collection by its ID.
   *
   * @param collectionId The ID of the collection. Example: "926489"
   */
  public Collection getCollectionById(String collectionId) {
    return getCollectionById(collectionId, new GetCollectionByIdHeaders());
  }

  /**
   * Retrieves a collection by its ID.
   *
   * @param collectionId The ID of the collection. Example: "926489"
   * @param headers Headers of getCollectionById method
   */
  public Collection getCollectionById(String collectionId, GetCollectionByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/collections/",
                            convertToString(collectionId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Collection.class);
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

    public CollectionsManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new CollectionsManager(this);
    }
  }
}
