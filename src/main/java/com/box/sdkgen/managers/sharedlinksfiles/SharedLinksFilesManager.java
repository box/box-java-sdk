package com.box.sdkgen.managers.sharedlinksfiles;

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
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class SharedLinksFilesManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public SharedLinksFilesManager() {
    this.networkSession = new NetworkSession();
  }

  protected SharedLinksFilesManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public FileFull findFileForSharedLink(FindFileForSharedLinkHeaders headers) {
    return findFileForSharedLink(new FindFileForSharedLinkQueryParams(), headers);
  }

  public FileFull findFileForSharedLink(
      FindFileForSharedLinkQueryParams queryParams, FindFileForSharedLinkHeaders headers) {
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
                            "/2.0/shared_items"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileFull.class);
  }

  public FileFull getSharedLinkForFile(String fileId, GetSharedLinkForFileQueryParams queryParams) {
    return getSharedLinkForFile(fileId, queryParams, new GetSharedLinkForFileHeaders());
  }

  public FileFull getSharedLinkForFile(
      String fileId,
      GetSharedLinkForFileQueryParams queryParams,
      GetSharedLinkForFileHeaders headers) {
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
                            "/2.0/files/",
                            convertToString(fileId),
                            "#get_shared_link"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileFull.class);
  }

  public FileFull addShareLinkToFile(String fileId, AddShareLinkToFileQueryParams queryParams) {
    return addShareLinkToFile(
        fileId, new AddShareLinkToFileRequestBody(), queryParams, new AddShareLinkToFileHeaders());
  }

  public FileFull addShareLinkToFile(
      String fileId,
      AddShareLinkToFileRequestBody requestBody,
      AddShareLinkToFileQueryParams queryParams) {
    return addShareLinkToFile(fileId, requestBody, queryParams, new AddShareLinkToFileHeaders());
  }

  public FileFull addShareLinkToFile(
      String fileId, AddShareLinkToFileQueryParams queryParams, AddShareLinkToFileHeaders headers) {
    return addShareLinkToFile(fileId, new AddShareLinkToFileRequestBody(), queryParams, headers);
  }

  public FileFull addShareLinkToFile(
      String fileId,
      AddShareLinkToFileRequestBody requestBody,
      AddShareLinkToFileQueryParams queryParams,
      AddShareLinkToFileHeaders headers) {
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
                            "/2.0/files/",
                            convertToString(fileId),
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
    return JsonManager.deserialize(response.getData(), FileFull.class);
  }

  public FileFull updateSharedLinkOnFile(
      String fileId, UpdateSharedLinkOnFileQueryParams queryParams) {
    return updateSharedLinkOnFile(
        fileId,
        new UpdateSharedLinkOnFileRequestBody(),
        queryParams,
        new UpdateSharedLinkOnFileHeaders());
  }

  public FileFull updateSharedLinkOnFile(
      String fileId,
      UpdateSharedLinkOnFileRequestBody requestBody,
      UpdateSharedLinkOnFileQueryParams queryParams) {
    return updateSharedLinkOnFile(
        fileId, requestBody, queryParams, new UpdateSharedLinkOnFileHeaders());
  }

  public FileFull updateSharedLinkOnFile(
      String fileId,
      UpdateSharedLinkOnFileQueryParams queryParams,
      UpdateSharedLinkOnFileHeaders headers) {
    return updateSharedLinkOnFile(
        fileId, new UpdateSharedLinkOnFileRequestBody(), queryParams, headers);
  }

  public FileFull updateSharedLinkOnFile(
      String fileId,
      UpdateSharedLinkOnFileRequestBody requestBody,
      UpdateSharedLinkOnFileQueryParams queryParams,
      UpdateSharedLinkOnFileHeaders headers) {
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
                            "/2.0/files/",
                            convertToString(fileId),
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
    return JsonManager.deserialize(response.getData(), FileFull.class);
  }

  public FileFull removeSharedLinkFromFile(
      String fileId, RemoveSharedLinkFromFileQueryParams queryParams) {
    return removeSharedLinkFromFile(
        fileId,
        new RemoveSharedLinkFromFileRequestBody(),
        queryParams,
        new RemoveSharedLinkFromFileHeaders());
  }

  public FileFull removeSharedLinkFromFile(
      String fileId,
      RemoveSharedLinkFromFileRequestBody requestBody,
      RemoveSharedLinkFromFileQueryParams queryParams) {
    return removeSharedLinkFromFile(
        fileId, requestBody, queryParams, new RemoveSharedLinkFromFileHeaders());
  }

  public FileFull removeSharedLinkFromFile(
      String fileId,
      RemoveSharedLinkFromFileQueryParams queryParams,
      RemoveSharedLinkFromFileHeaders headers) {
    return removeSharedLinkFromFile(
        fileId, new RemoveSharedLinkFromFileRequestBody(), queryParams, headers);
  }

  public FileFull removeSharedLinkFromFile(
      String fileId,
      RemoveSharedLinkFromFileRequestBody requestBody,
      RemoveSharedLinkFromFileQueryParams queryParams,
      RemoveSharedLinkFromFileHeaders headers) {
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
                            "/2.0/files/",
                            convertToString(fileId),
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
    return JsonManager.deserialize(response.getData(), FileFull.class);
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

    public SharedLinksFilesManager build() {
      return new SharedLinksFilesManager(this);
    }
  }
}
