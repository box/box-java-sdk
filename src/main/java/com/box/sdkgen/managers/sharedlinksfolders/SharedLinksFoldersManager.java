package com.box.sdkgen.managers.sharedlinksfolders;

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
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class SharedLinksFoldersManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public SharedLinksFoldersManager() {
    this.networkSession = new NetworkSession();
  }

  protected SharedLinksFoldersManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public FolderFull findFolderForSharedLink(FindFolderForSharedLinkHeaders headers) {
    return findFolderForSharedLink(new FindFolderForSharedLinkQueryParams(), headers);
  }

  public FolderFull findFolderForSharedLink(
      FindFolderForSharedLinkQueryParams queryParams, FindFolderForSharedLinkHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(
                    entryOf("if-none-match", convertToString(headers.getIfNoneMatch())),
                    entryOf("boxapi", convertToString(headers.getBoxapi()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shared_items#folders"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FolderFull.class);
  }

  public FolderFull getSharedLinkForFolder(
      String folderId, GetSharedLinkForFolderQueryParams queryParams) {
    return getSharedLinkForFolder(folderId, queryParams, new GetSharedLinkForFolderHeaders());
  }

  public FolderFull getSharedLinkForFolder(
      String folderId,
      GetSharedLinkForFolderQueryParams queryParams,
      GetSharedLinkForFolderHeaders headers) {
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
                            "/2.0/folders/",
                            convertToString(folderId),
                            "#get_shared_link"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FolderFull.class);
  }

  public FolderFull addShareLinkToFolder(
      String folderId, AddShareLinkToFolderQueryParams queryParams) {
    return addShareLinkToFolder(
        folderId,
        new AddShareLinkToFolderRequestBody(),
        queryParams,
        new AddShareLinkToFolderHeaders());
  }

  public FolderFull addShareLinkToFolder(
      String folderId,
      AddShareLinkToFolderRequestBody requestBody,
      AddShareLinkToFolderQueryParams queryParams) {
    return addShareLinkToFolder(
        folderId, requestBody, queryParams, new AddShareLinkToFolderHeaders());
  }

  public FolderFull addShareLinkToFolder(
      String folderId,
      AddShareLinkToFolderQueryParams queryParams,
      AddShareLinkToFolderHeaders headers) {
    return addShareLinkToFolder(
        folderId, new AddShareLinkToFolderRequestBody(), queryParams, headers);
  }

  public FolderFull addShareLinkToFolder(
      String folderId,
      AddShareLinkToFolderRequestBody requestBody,
      AddShareLinkToFolderQueryParams queryParams,
      AddShareLinkToFolderHeaders headers) {
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
                            "/2.0/folders/",
                            convertToString(folderId),
                            "#add_shared_link"),
                        "PUT")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FolderFull.class);
  }

  public FolderFull updateSharedLinkOnFolder(
      String folderId, UpdateSharedLinkOnFolderQueryParams queryParams) {
    return updateSharedLinkOnFolder(
        folderId,
        new UpdateSharedLinkOnFolderRequestBody(),
        queryParams,
        new UpdateSharedLinkOnFolderHeaders());
  }

  public FolderFull updateSharedLinkOnFolder(
      String folderId,
      UpdateSharedLinkOnFolderRequestBody requestBody,
      UpdateSharedLinkOnFolderQueryParams queryParams) {
    return updateSharedLinkOnFolder(
        folderId, requestBody, queryParams, new UpdateSharedLinkOnFolderHeaders());
  }

  public FolderFull updateSharedLinkOnFolder(
      String folderId,
      UpdateSharedLinkOnFolderQueryParams queryParams,
      UpdateSharedLinkOnFolderHeaders headers) {
    return updateSharedLinkOnFolder(
        folderId, new UpdateSharedLinkOnFolderRequestBody(), queryParams, headers);
  }

  public FolderFull updateSharedLinkOnFolder(
      String folderId,
      UpdateSharedLinkOnFolderRequestBody requestBody,
      UpdateSharedLinkOnFolderQueryParams queryParams,
      UpdateSharedLinkOnFolderHeaders headers) {
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
                            "/2.0/folders/",
                            convertToString(folderId),
                            "#update_shared_link"),
                        "PUT")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FolderFull.class);
  }

  public FolderFull removeSharedLinkFromFolder(
      String folderId, RemoveSharedLinkFromFolderQueryParams queryParams) {
    return removeSharedLinkFromFolder(
        folderId,
        new RemoveSharedLinkFromFolderRequestBody(),
        queryParams,
        new RemoveSharedLinkFromFolderHeaders());
  }

  public FolderFull removeSharedLinkFromFolder(
      String folderId,
      RemoveSharedLinkFromFolderRequestBody requestBody,
      RemoveSharedLinkFromFolderQueryParams queryParams) {
    return removeSharedLinkFromFolder(
        folderId, requestBody, queryParams, new RemoveSharedLinkFromFolderHeaders());
  }

  public FolderFull removeSharedLinkFromFolder(
      String folderId,
      RemoveSharedLinkFromFolderQueryParams queryParams,
      RemoveSharedLinkFromFolderHeaders headers) {
    return removeSharedLinkFromFolder(
        folderId, new RemoveSharedLinkFromFolderRequestBody(), queryParams, headers);
  }

  public FolderFull removeSharedLinkFromFolder(
      String folderId,
      RemoveSharedLinkFromFolderRequestBody requestBody,
      RemoveSharedLinkFromFolderQueryParams queryParams,
      RemoveSharedLinkFromFolderHeaders headers) {
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
                            "/2.0/folders/",
                            convertToString(folderId),
                            "#remove_shared_link"),
                        "PUT")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FolderFull.class);
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

    public SharedLinksFoldersManager build() {
      return new SharedLinksFoldersManager(this);
    }
  }
}
