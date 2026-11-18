package com.box.sdkgen.managers.aisessions;

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
import com.box.sdkgen.schemas.v2026r0.aisessionsv2026r0.AiSessionsV2026R0;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class AiSessionsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public AiSessionsManager() {
    this.networkSession = new NetworkSession();
  }

  protected AiSessionsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Returns a marker-paginated list of the AI sessions that the authenticated user can read and
   * that were created through the Box public API. Entries are ordered by `sort` and `direction`,
   * defaulting to `updated_at` descending so the most recently active session appears first.
   *
   * <p>The access token must have both the `ai.readwrite` and `root_readwrite` scopes.
   *
   * <p>A page may contain fewer than `limit` entries while further pages remain. Treat a non-null
   * `next_marker` as the only signal that more results exist.
   *
   * @param queryParams Query parameters of getAiSessionsV2026R0 method
   */
  public AiSessionsV2026R0 getAiSessionsV2026R0(GetAiSessionsV2026R0QueryParams queryParams) {
    return getAiSessionsV2026R0(queryParams, new GetAiSessionsV2026R0Headers());
  }

  /**
   * Returns a marker-paginated list of the AI sessions that the authenticated user can read and
   * that were created through the Box public API. Entries are ordered by `sort` and `direction`,
   * defaulting to `updated_at` descending so the most recently active session appears first.
   *
   * <p>The access token must have both the `ai.readwrite` and `root_readwrite` scopes.
   *
   * <p>A page may contain fewer than `limit` entries while further pages remain. Treat a non-null
   * `next_marker` as the only signal that more results exist.
   *
   * @param queryParams Query parameters of getAiSessionsV2026R0 method
   * @param headers Headers of getAiSessionsV2026R0 method
   */
  public AiSessionsV2026R0 getAiSessionsV2026R0(
      GetAiSessionsV2026R0QueryParams queryParams, GetAiSessionsV2026R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("source", convertToString(queryParams.getSource())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("sort", convertToString(queryParams.getSort())),
                entryOf("direction", convertToString(queryParams.getDirection())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("client_ids", convertToString(queryParams.getClientIds()))));
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
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/ai_sessions"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), AiSessionsV2026R0.class);
  }

  /**
   * Deletes an AI session created through the Box public API, together with its agent tasks. The
   * session's agent-generated files and uploaded files are moved to the trash. Files attached as
   * sources from elsewhere in Box are not affected. Deleting a session does not stop a running task
   * or notify the agent.
   *
   * <p>The access token must have both the `ai.readwrite` and `root_readwrite` scopes.
   *
   * @param aiSessionId The unique identifier of the AI session. This is the same value returned as
   *     `id` by the list endpoint and used as the A2A `contextId`. Example: "23489723"
   */
  public void deleteAiSessionByIdV2026R0(String aiSessionId) {
    deleteAiSessionByIdV2026R0(aiSessionId, new DeleteAiSessionByIdV2026R0Headers());
  }

  /**
   * Deletes an AI session created through the Box public API, together with its agent tasks. The
   * session's agent-generated files and uploaded files are moved to the trash. Files attached as
   * sources from elsewhere in Box are not affected. Deleting a session does not stop a running task
   * or notify the agent.
   *
   * <p>The access token must have both the `ai.readwrite` and `root_readwrite` scopes.
   *
   * @param aiSessionId The unique identifier of the AI session. This is the same value returned as
   *     `id` by the list endpoint and used as the A2A `contextId`. Example: "23489723"
   * @param headers Headers of deleteAiSessionByIdV2026R0 method
   */
  public void deleteAiSessionByIdV2026R0(
      String aiSessionId, DeleteAiSessionByIdV2026R0Headers headers) {
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
                            "/2.0/ai_sessions/",
                            convertToString(aiSessionId)),
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

    public Builder() {}

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public AiSessionsManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new AiSessionsManager(this);
    }
  }
}
