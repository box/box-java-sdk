package com.box.sdkgen.managers.collaborationallowlistexempttargets;

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
import com.box.sdkgen.schemas.collaborationallowlistexempttarget.CollaborationAllowlistExemptTarget;
import com.box.sdkgen.schemas.collaborationallowlistexempttargets.CollaborationAllowlistExemptTargets;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class CollaborationAllowlistExemptTargetsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public CollaborationAllowlistExemptTargetsManager() {
    this.networkSession = new NetworkSession();
  }

  protected CollaborationAllowlistExemptTargetsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public CollaborationAllowlistExemptTargets getCollaborationWhitelistExemptTargets() {
    return getCollaborationWhitelistExemptTargets(
        new GetCollaborationWhitelistExemptTargetsQueryParams(),
        new GetCollaborationWhitelistExemptTargetsHeaders());
  }

  public CollaborationAllowlistExemptTargets getCollaborationWhitelistExemptTargets(
      GetCollaborationWhitelistExemptTargetsQueryParams queryParams) {
    return getCollaborationWhitelistExemptTargets(
        queryParams, new GetCollaborationWhitelistExemptTargetsHeaders());
  }

  public CollaborationAllowlistExemptTargets getCollaborationWhitelistExemptTargets(
      GetCollaborationWhitelistExemptTargetsHeaders headers) {
    return getCollaborationWhitelistExemptTargets(
        new GetCollaborationWhitelistExemptTargetsQueryParams(), headers);
  }

  public CollaborationAllowlistExemptTargets getCollaborationWhitelistExemptTargets(
      GetCollaborationWhitelistExemptTargetsQueryParams queryParams,
      GetCollaborationWhitelistExemptTargetsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
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
                            "/2.0/collaboration_whitelist_exempt_targets"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), CollaborationAllowlistExemptTargets.class);
  }

  public CollaborationAllowlistExemptTarget createCollaborationWhitelistExemptTarget(
      CreateCollaborationWhitelistExemptTargetRequestBody requestBody) {
    return createCollaborationWhitelistExemptTarget(
        requestBody, new CreateCollaborationWhitelistExemptTargetHeaders());
  }

  public CollaborationAllowlistExemptTarget createCollaborationWhitelistExemptTarget(
      CreateCollaborationWhitelistExemptTargetRequestBody requestBody,
      CreateCollaborationWhitelistExemptTargetHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/collaboration_whitelist_exempt_targets"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), CollaborationAllowlistExemptTarget.class);
  }

  public CollaborationAllowlistExemptTarget getCollaborationWhitelistExemptTargetById(
      String collaborationWhitelistExemptTargetId) {
    return getCollaborationWhitelistExemptTargetById(
        collaborationWhitelistExemptTargetId,
        new GetCollaborationWhitelistExemptTargetByIdHeaders());
  }

  public CollaborationAllowlistExemptTarget getCollaborationWhitelistExemptTargetById(
      String collaborationWhitelistExemptTargetId,
      GetCollaborationWhitelistExemptTargetByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/collaboration_whitelist_exempt_targets/",
                            convertToString(collaborationWhitelistExemptTargetId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), CollaborationAllowlistExemptTarget.class);
  }

  public void deleteCollaborationWhitelistExemptTargetById(
      String collaborationWhitelistExemptTargetId) {
    deleteCollaborationWhitelistExemptTargetById(
        collaborationWhitelistExemptTargetId,
        new DeleteCollaborationWhitelistExemptTargetByIdHeaders());
  }

  public void deleteCollaborationWhitelistExemptTargetById(
      String collaborationWhitelistExemptTargetId,
      DeleteCollaborationWhitelistExemptTargetByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/collaboration_whitelist_exempt_targets/",
                            convertToString(collaborationWhitelistExemptTargetId)),
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

    public CollaborationAllowlistExemptTargetsManager build() {
      return new CollaborationAllowlistExemptTargetsManager(this);
    }
  }
}
