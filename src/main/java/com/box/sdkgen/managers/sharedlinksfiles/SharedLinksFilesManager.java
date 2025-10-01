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

  /**
   * Returns the file represented by a shared link.
   *
   * <p>A shared file can be represented by a shared link, which can originate within the current
   * enterprise or within another.
   *
   * <p>This endpoint allows an application to retrieve information about a shared file when only
   * given a shared link.
   *
   * <p>The `shared_link_permission_options` array field can be returned by requesting it in the
   * `fields` query parameter.
   *
   * @param headers Headers of findFileForSharedLink method
   */
  public FileFull findFileForSharedLink(FindFileForSharedLinkHeaders headers) {
    return findFileForSharedLink(new FindFileForSharedLinkQueryParams(), headers);
  }

  /**
   * Returns the file represented by a shared link.
   *
   * <p>A shared file can be represented by a shared link, which can originate within the current
   * enterprise or within another.
   *
   * <p>This endpoint allows an application to retrieve information about a shared file when only
   * given a shared link.
   *
   * <p>The `shared_link_permission_options` array field can be returned by requesting it in the
   * `fields` query parameter.
   *
   * @param queryParams Query parameters of findFileForSharedLink method
   * @param headers Headers of findFileForSharedLink method
   */
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

  /**
   * Gets the information for a shared link on a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getSharedLinkForFile method
   */
  public FileFull getSharedLinkForFile(String fileId, GetSharedLinkForFileQueryParams queryParams) {
    return getSharedLinkForFile(fileId, queryParams, new GetSharedLinkForFileHeaders());
  }

  /**
   * Gets the information for a shared link on a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getSharedLinkForFile method
   * @param headers Headers of getSharedLinkForFile method
   */
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

  /**
   * Adds a shared link to a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of addShareLinkToFile method
   */
  public FileFull addShareLinkToFile(String fileId, AddShareLinkToFileQueryParams queryParams) {
    return addShareLinkToFile(
        fileId, new AddShareLinkToFileRequestBody(), queryParams, new AddShareLinkToFileHeaders());
  }

  /**
   * Adds a shared link to a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of addShareLinkToFile method
   * @param queryParams Query parameters of addShareLinkToFile method
   */
  public FileFull addShareLinkToFile(
      String fileId,
      AddShareLinkToFileRequestBody requestBody,
      AddShareLinkToFileQueryParams queryParams) {
    return addShareLinkToFile(fileId, requestBody, queryParams, new AddShareLinkToFileHeaders());
  }

  /**
   * Adds a shared link to a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of addShareLinkToFile method
   * @param headers Headers of addShareLinkToFile method
   */
  public FileFull addShareLinkToFile(
      String fileId, AddShareLinkToFileQueryParams queryParams, AddShareLinkToFileHeaders headers) {
    return addShareLinkToFile(fileId, new AddShareLinkToFileRequestBody(), queryParams, headers);
  }

  /**
   * Adds a shared link to a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of addShareLinkToFile method
   * @param queryParams Query parameters of addShareLinkToFile method
   * @param headers Headers of addShareLinkToFile method
   */
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

  /**
   * Updates a shared link on a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of updateSharedLinkOnFile method
   */
  public FileFull updateSharedLinkOnFile(
      String fileId, UpdateSharedLinkOnFileQueryParams queryParams) {
    return updateSharedLinkOnFile(
        fileId,
        new UpdateSharedLinkOnFileRequestBody(),
        queryParams,
        new UpdateSharedLinkOnFileHeaders());
  }

  /**
   * Updates a shared link on a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of updateSharedLinkOnFile method
   * @param queryParams Query parameters of updateSharedLinkOnFile method
   */
  public FileFull updateSharedLinkOnFile(
      String fileId,
      UpdateSharedLinkOnFileRequestBody requestBody,
      UpdateSharedLinkOnFileQueryParams queryParams) {
    return updateSharedLinkOnFile(
        fileId, requestBody, queryParams, new UpdateSharedLinkOnFileHeaders());
  }

  /**
   * Updates a shared link on a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of updateSharedLinkOnFile method
   * @param headers Headers of updateSharedLinkOnFile method
   */
  public FileFull updateSharedLinkOnFile(
      String fileId,
      UpdateSharedLinkOnFileQueryParams queryParams,
      UpdateSharedLinkOnFileHeaders headers) {
    return updateSharedLinkOnFile(
        fileId, new UpdateSharedLinkOnFileRequestBody(), queryParams, headers);
  }

  /**
   * Updates a shared link on a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of updateSharedLinkOnFile method
   * @param queryParams Query parameters of updateSharedLinkOnFile method
   * @param headers Headers of updateSharedLinkOnFile method
   */
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

  /**
   * Removes a shared link from a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of removeSharedLinkFromFile method
   */
  public FileFull removeSharedLinkFromFile(
      String fileId, RemoveSharedLinkFromFileQueryParams queryParams) {
    return removeSharedLinkFromFile(
        fileId,
        new RemoveSharedLinkFromFileRequestBody(),
        queryParams,
        new RemoveSharedLinkFromFileHeaders());
  }

  /**
   * Removes a shared link from a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of removeSharedLinkFromFile method
   * @param queryParams Query parameters of removeSharedLinkFromFile method
   */
  public FileFull removeSharedLinkFromFile(
      String fileId,
      RemoveSharedLinkFromFileRequestBody requestBody,
      RemoveSharedLinkFromFileQueryParams queryParams) {
    return removeSharedLinkFromFile(
        fileId, requestBody, queryParams, new RemoveSharedLinkFromFileHeaders());
  }

  /**
   * Removes a shared link from a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of removeSharedLinkFromFile method
   * @param headers Headers of removeSharedLinkFromFile method
   */
  public FileFull removeSharedLinkFromFile(
      String fileId,
      RemoveSharedLinkFromFileQueryParams queryParams,
      RemoveSharedLinkFromFileHeaders headers) {
    return removeSharedLinkFromFile(
        fileId, new RemoveSharedLinkFromFileRequestBody(), queryParams, headers);
  }

  /**
   * Removes a shared link from a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of removeSharedLinkFromFile method
   * @param queryParams Query parameters of removeSharedLinkFromFile method
   * @param headers Headers of removeSharedLinkFromFile method
   */
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
