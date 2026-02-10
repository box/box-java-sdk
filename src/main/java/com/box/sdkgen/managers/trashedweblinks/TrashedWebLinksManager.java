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

  /**
   * Restores a web link that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the web link to in case the original
   * folder has been deleted.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   */
  public TrashWebLinkRestored restoreWeblinkFromTrash(String webLinkId) {
    return restoreWeblinkFromTrash(
        webLinkId,
        new RestoreWeblinkFromTrashRequestBody(),
        new RestoreWeblinkFromTrashQueryParams(),
        new RestoreWeblinkFromTrashHeaders());
  }

  /**
   * Restores a web link that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the web link to in case the original
   * folder has been deleted.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   * @param requestBody Request body of restoreWeblinkFromTrash method
   */
  public TrashWebLinkRestored restoreWeblinkFromTrash(
      String webLinkId, RestoreWeblinkFromTrashRequestBody requestBody) {
    return restoreWeblinkFromTrash(
        webLinkId,
        requestBody,
        new RestoreWeblinkFromTrashQueryParams(),
        new RestoreWeblinkFromTrashHeaders());
  }

  /**
   * Restores a web link that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the web link to in case the original
   * folder has been deleted.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   * @param queryParams Query parameters of restoreWeblinkFromTrash method
   */
  public TrashWebLinkRestored restoreWeblinkFromTrash(
      String webLinkId, RestoreWeblinkFromTrashQueryParams queryParams) {
    return restoreWeblinkFromTrash(
        webLinkId,
        new RestoreWeblinkFromTrashRequestBody(),
        queryParams,
        new RestoreWeblinkFromTrashHeaders());
  }

  /**
   * Restores a web link that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the web link to in case the original
   * folder has been deleted.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   * @param requestBody Request body of restoreWeblinkFromTrash method
   * @param queryParams Query parameters of restoreWeblinkFromTrash method
   */
  public TrashWebLinkRestored restoreWeblinkFromTrash(
      String webLinkId,
      RestoreWeblinkFromTrashRequestBody requestBody,
      RestoreWeblinkFromTrashQueryParams queryParams) {
    return restoreWeblinkFromTrash(
        webLinkId, requestBody, queryParams, new RestoreWeblinkFromTrashHeaders());
  }

  /**
   * Restores a web link that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the web link to in case the original
   * folder has been deleted.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   * @param headers Headers of restoreWeblinkFromTrash method
   */
  public TrashWebLinkRestored restoreWeblinkFromTrash(
      String webLinkId, RestoreWeblinkFromTrashHeaders headers) {
    return restoreWeblinkFromTrash(
        webLinkId,
        new RestoreWeblinkFromTrashRequestBody(),
        new RestoreWeblinkFromTrashQueryParams(),
        headers);
  }

  /**
   * Restores a web link that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the web link to in case the original
   * folder has been deleted.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   * @param requestBody Request body of restoreWeblinkFromTrash method
   * @param headers Headers of restoreWeblinkFromTrash method
   */
  public TrashWebLinkRestored restoreWeblinkFromTrash(
      String webLinkId,
      RestoreWeblinkFromTrashRequestBody requestBody,
      RestoreWeblinkFromTrashHeaders headers) {
    return restoreWeblinkFromTrash(
        webLinkId, requestBody, new RestoreWeblinkFromTrashQueryParams(), headers);
  }

  /**
   * Restores a web link that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the web link to in case the original
   * folder has been deleted.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   * @param queryParams Query parameters of restoreWeblinkFromTrash method
   * @param headers Headers of restoreWeblinkFromTrash method
   */
  public TrashWebLinkRestored restoreWeblinkFromTrash(
      String webLinkId,
      RestoreWeblinkFromTrashQueryParams queryParams,
      RestoreWeblinkFromTrashHeaders headers) {
    return restoreWeblinkFromTrash(
        webLinkId, new RestoreWeblinkFromTrashRequestBody(), queryParams, headers);
  }

  /**
   * Restores a web link that has been moved to the trash.
   *
   * <p>An optional new parent ID can be provided to restore the web link to in case the original
   * folder has been deleted.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   * @param requestBody Request body of restoreWeblinkFromTrash method
   * @param queryParams Query parameters of restoreWeblinkFromTrash method
   * @param headers Headers of restoreWeblinkFromTrash method
   */
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

  /**
   * Retrieves a web link that has been moved to the trash.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   */
  public TrashWebLink getTrashedWebLinkById(String webLinkId) {
    return getTrashedWebLinkById(
        webLinkId, new GetTrashedWebLinkByIdQueryParams(), new GetTrashedWebLinkByIdHeaders());
  }

  /**
   * Retrieves a web link that has been moved to the trash.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   * @param queryParams Query parameters of getTrashedWebLinkById method
   */
  public TrashWebLink getTrashedWebLinkById(
      String webLinkId, GetTrashedWebLinkByIdQueryParams queryParams) {
    return getTrashedWebLinkById(webLinkId, queryParams, new GetTrashedWebLinkByIdHeaders());
  }

  /**
   * Retrieves a web link that has been moved to the trash.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   * @param headers Headers of getTrashedWebLinkById method
   */
  public TrashWebLink getTrashedWebLinkById(
      String webLinkId, GetTrashedWebLinkByIdHeaders headers) {
    return getTrashedWebLinkById(webLinkId, new GetTrashedWebLinkByIdQueryParams(), headers);
  }

  /**
   * Retrieves a web link that has been moved to the trash.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   * @param queryParams Query parameters of getTrashedWebLinkById method
   * @param headers Headers of getTrashedWebLinkById method
   */
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

  /**
   * Permanently deletes a web link that is in the trash. This action cannot be undone.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   */
  public void deleteTrashedWebLinkById(String webLinkId) {
    deleteTrashedWebLinkById(webLinkId, new DeleteTrashedWebLinkByIdHeaders());
  }

  /**
   * Permanently deletes a web link that is in the trash. This action cannot be undone.
   *
   * @param webLinkId The ID of the web link. Example: "12345"
   * @param headers Headers of deleteTrashedWebLinkById method
   */
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

    public Builder() {}

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public TrashedWebLinksManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new TrashedWebLinksManager(this);
    }
  }
}
