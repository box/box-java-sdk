package com.box.sdkgen.managers.query;

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
import com.box.sdkgen.schemas.v2026r0.queryinsightsrequestbodyv2026r0.QueryInsightsRequestBodyV2026R0;
import com.box.sdkgen.schemas.v2026r0.queryinsightsv2026r0.QueryInsightsV2026R0;
import com.box.sdkgen.schemas.v2026r0.queryrequestbodyv2026r0.QueryRequestBodyV2026R0;
import com.box.sdkgen.schemas.v2026r0.queryresultsv2026r0.QueryResultsV2026R0;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class QueryManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public QueryManager() {
    this.networkSession = new NetworkSession();
  }

  protected QueryManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Runs a query to discover Box items using a logical predicate, similar to a SQL WHERE clause.
   * The predicate can filter across multiple metadata templates and item fields in a single
   * request. Results can be sorted, paginated with markers, and shaped with fields so each entry
   * includes additional item or metadata attributes.
   *
   * <p>This is the item-retrieval endpoint in the Query API (version 2026.0). Unlike the [Metadata
   * Query API](https://developer.box.com/reference/post-metadata-queries-execute-read), it is not
   * limited to a single metadata template or to files and folders. For totals and grouped metrics
   * over the same predicates, use [Query
   * Insights](https://developer.box.com/reference/v2026.0/post-query-insights) instead of paging
   * through every matching item.
   *
   * <p>For predicate syntax, parameters, examples, pagination behavior, and error codes, see the
   * [Box Query guide](https://developer.box.com/guides/query/box-query). For shared response
   * schemas, including Query results and result entries, see [Query
   * resources](https://developer.box.com/reference/v2026.0/query-resources).
   *
   * @param requestBody Request body of createQueryV2026R0 method
   */
  public QueryResultsV2026R0 createQueryV2026R0(QueryRequestBodyV2026R0 requestBody) {
    return createQueryV2026R0(requestBody, new CreateQueryV2026R0Headers());
  }

  /**
   * Runs a query to discover Box items using a logical predicate, similar to a SQL WHERE clause.
   * The predicate can filter across multiple metadata templates and item fields in a single
   * request. Results can be sorted, paginated with markers, and shaped with fields so each entry
   * includes additional item or metadata attributes.
   *
   * <p>This is the item-retrieval endpoint in the Query API (version 2026.0). Unlike the [Metadata
   * Query API](https://developer.box.com/reference/post-metadata-queries-execute-read), it is not
   * limited to a single metadata template or to files and folders. For totals and grouped metrics
   * over the same predicates, use [Query
   * Insights](https://developer.box.com/reference/v2026.0/post-query-insights) instead of paging
   * through every matching item.
   *
   * <p>For predicate syntax, parameters, examples, pagination behavior, and error codes, see the
   * [Box Query guide](https://developer.box.com/guides/query/box-query). For shared response
   * schemas, including Query results and result entries, see [Query
   * resources](https://developer.box.com/reference/v2026.0/query-resources).
   *
   * @param requestBody Request body of createQueryV2026R0 method
   * @param headers Headers of createQueryV2026R0 method
   */
  public QueryResultsV2026R0 createQueryV2026R0(
      QueryRequestBodyV2026R0 requestBody, CreateQueryV2026R0Headers headers) {
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
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/query"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), QueryResultsV2026R0.class);
  }

  /**
   * Computes aggregated metrics over Box items matching a query predicate. Filters are applied
   * first, followed by optional grouping, after which the requested metrics (such as `sum`, `avg`,
   * `min`, `max`, and `count`) are computed for each resulting group or over the entire filtered
   * dataset.
   *
   * @param requestBody Request body of createQueryInsightV2026R0 method
   */
  public QueryInsightsV2026R0 createQueryInsightV2026R0(
      QueryInsightsRequestBodyV2026R0 requestBody) {
    return createQueryInsightV2026R0(requestBody, new CreateQueryInsightV2026R0Headers());
  }

  /**
   * Computes aggregated metrics over Box items matching a query predicate. Filters are applied
   * first, followed by optional grouping, after which the requested metrics (such as `sum`, `avg`,
   * `min`, `max`, and `count`) are computed for each resulting group or over the entire filtered
   * dataset.
   *
   * @param requestBody Request body of createQueryInsightV2026R0 method
   * @param headers Headers of createQueryInsightV2026R0 method
   */
  public QueryInsightsV2026R0 createQueryInsightV2026R0(
      QueryInsightsRequestBodyV2026R0 requestBody, CreateQueryInsightV2026R0Headers headers) {
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
                            "/2.0/query_insights"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), QueryInsightsV2026R0.class);
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

    public QueryManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new QueryManager(this);
    }
  }
}
