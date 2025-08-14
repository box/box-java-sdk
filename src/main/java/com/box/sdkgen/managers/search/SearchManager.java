package com.box.sdkgen.managers.search;

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
import com.box.sdkgen.schemas.metadataquery.MetadataQuery;
import com.box.sdkgen.schemas.metadataqueryresults.MetadataQueryResults;
import com.box.sdkgen.schemas.searchresultsresponse.SearchResultsResponse;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class SearchManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public SearchManager() {
    this.networkSession = new NetworkSession();
  }

  protected SearchManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public MetadataQueryResults searchByMetadataQuery(MetadataQuery requestBody) {
    return searchByMetadataQuery(requestBody, new SearchByMetadataQueryHeaders());
  }

  public MetadataQueryResults searchByMetadataQuery(
      MetadataQuery requestBody, SearchByMetadataQueryHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_queries/execute_read"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataQueryResults.class);
  }

  public SearchResultsResponse searchForContent() {
    return searchForContent(new SearchForContentQueryParams(), new SearchForContentHeaders());
  }

  public SearchResultsResponse searchForContent(SearchForContentQueryParams queryParams) {
    return searchForContent(queryParams, new SearchForContentHeaders());
  }

  public SearchResultsResponse searchForContent(SearchForContentHeaders headers) {
    return searchForContent(new SearchForContentQueryParams(), headers);
  }

  public SearchResultsResponse searchForContent(
      SearchForContentQueryParams queryParams, SearchForContentHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("query", convertToString(queryParams.getQuery())),
                entryOf("scope", convertToString(queryParams.getScope())),
                entryOf("file_extensions", convertToString(queryParams.getFileExtensions())),
                entryOf("created_at_range", convertToString(queryParams.getCreatedAtRange())),
                entryOf("updated_at_range", convertToString(queryParams.getUpdatedAtRange())),
                entryOf("size_range", convertToString(queryParams.getSizeRange())),
                entryOf("owner_user_ids", convertToString(queryParams.getOwnerUserIds())),
                entryOf(
                    "recent_updater_user_ids",
                    convertToString(queryParams.getRecentUpdaterUserIds())),
                entryOf("ancestor_folder_ids", convertToString(queryParams.getAncestorFolderIds())),
                entryOf("content_types", convertToString(queryParams.getContentTypes())),
                entryOf("type", convertToString(queryParams.getType())),
                entryOf("trash_content", convertToString(queryParams.getTrashContent())),
                entryOf("mdfilters", convertToString(queryParams.getMdfilters())),
                entryOf("sort", convertToString(queryParams.getSort())),
                entryOf("direction", convertToString(queryParams.getDirection())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf(
                    "include_recent_shared_links",
                    convertToString(queryParams.getIncludeRecentSharedLinks())),
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("offset", convertToString(queryParams.getOffset())),
                entryOf("deleted_user_ids", convertToString(queryParams.getDeletedUserIds())),
                entryOf("deleted_at_range", convertToString(queryParams.getDeletedAtRange()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/search"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), SearchResultsResponse.class);
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

    public SearchManager build() {
      return new SearchManager(this);
    }
  }
}
