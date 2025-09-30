package com.box.sdkgen.managers.trashedweblinks;

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
import com.box.sdkgen.schemas.trashweblink.TrashWebLink;
import com.box.sdkgen.schemas.trashweblinkrestored.TrashWebLinkRestored;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class TrashedWebLinksManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public TrashedWebLinksManager() {
    this.networkSession = new NetworkSession();
  }

  protected TrashedWebLinksManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public TrashWebLinkRestored restoreWeblinkFromTrash(String webLinkId) {
    return restoreWeblinkFromTrash(
        webLinkId,
        new RestoreWeblinkFromTrashRequestBody(),
        new RestoreWeblinkFromTrashQueryParams(),
        new RestoreWeblinkFromTrashHeaders());
  }

  public TrashWebLinkRestored restoreWeblinkFromTrash(
      String webLinkId, RestoreWeblinkFromTrashRequestBody requestBody) {
    return restoreWeblinkFromTrash(
        webLinkId,
        requestBody,
        new RestoreWeblinkFromTrashQueryParams(),
        new RestoreWeblinkFromTrashHeaders());
  }

  public TrashWebLinkRestored restoreWeblinkFromTrash(
      String webLinkId, RestoreWeblinkFromTrashQueryParams queryParams) {
    return restoreWeblinkFromTrash(
        webLinkId,
        new RestoreWeblinkFromTrashRequestBody(),
        queryParams,
        new RestoreWeblinkFromTrashHeaders());
  }

  public TrashWebLinkRestored restoreWeblinkFromTrash(
      String webLinkId,
      RestoreWeblinkFromTrashRequestBody requestBody,
      RestoreWeblinkFromTrashQueryParams queryParams) {
    return restoreWeblinkFromTrash(
        webLinkId, requestBody, queryParams, new RestoreWeblinkFromTrashHeaders());
  }

  public TrashWebLinkRestored restoreWeblinkFromTrash(
      String webLinkId, RestoreWeblinkFromTrashHeaders headers) {
    return restoreWeblinkFromTrash(
        webLinkId,
        new RestoreWeblinkFromTrashRequestBody(),
        new RestoreWeblinkFromTrashQueryParams(),
        headers);
  }

  public TrashWebLinkRestored restoreWeblinkFromTrash(
      String webLinkId,
      RestoreWeblinkFromTrashRequestBody requestBody,
      RestoreWeblinkFromTrashHeaders headers) {
    return restoreWeblinkFromTrash(
        webLinkId, requestBody, new RestoreWeblinkFromTrashQueryParams(), headers);
  }

  public TrashWebLinkRestored restoreWeblinkFromTrash(
      String webLinkId,
      RestoreWeblinkFromTrashQueryParams queryParams,
      RestoreWeblinkFromTrashHeaders headers) {
    return restoreWeblinkFromTrash(
        webLinkId, new RestoreWeblinkFromTrashRequestBody(), queryParams, headers);
  }

  public TrashWebLinkRestored restoreWeblinkFromTrash(
      String webLinkId,
      RestoreWeblinkFromTrashRequestBody requestBody,
      RestoreWeblinkFromTrashQueryParams queryParams,
      RestoreWeblinkFromTrashHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/web_links/",
                            convertToString(webLinkId)),
                        "POST")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TrashWebLinkRestored.class);
  }

  public TrashWebLink getTrashedWebLinkById(String webLinkId) {
    return getTrashedWebLinkById(
        webLinkId, new GetTrashedWebLinkByIdQueryParams(), new GetTrashedWebLinkByIdHeaders());
  }

  public TrashWebLink getTrashedWebLinkById(
      String webLinkId, GetTrashedWebLinkByIdQueryParams queryParams) {
    return getTrashedWebLinkById(webLinkId, queryParams, new GetTrashedWebLinkByIdHeaders());
  }

  public TrashWebLink getTrashedWebLinkById(
      String webLinkId, GetTrashedWebLinkByIdHeaders headers) {
    return getTrashedWebLinkById(webLinkId, new GetTrashedWebLinkByIdQueryParams(), headers);
  }

  public TrashWebLink getTrashedWebLinkById(
      String webLinkId,
      GetTrashedWebLinkByIdQueryParams queryParams,
      GetTrashedWebLinkByIdHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/web_links/",
                            convertToString(webLinkId),
                            "/trash"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TrashWebLink.class);
  }

  public void deleteTrashedWebLinkById(String webLinkId) {
    deleteTrashedWebLinkById(webLinkId, new DeleteTrashedWebLinkByIdHeaders());
  }

  public void deleteTrashedWebLinkById(String webLinkId, DeleteTrashedWebLinkByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/web_links/",
                            convertToString(webLinkId),
                            "/trash"),
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

    public TrashedWebLinksManager build() {
      return new TrashedWebLinksManager(this);
    }
  }
}
