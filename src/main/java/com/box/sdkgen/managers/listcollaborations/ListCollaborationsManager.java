package com.box.sdkgen.managers.listcollaborations;

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
import com.box.sdkgen.schemas.collaborations.Collaborations;
import com.box.sdkgen.schemas.collaborationsoffsetpaginated.CollaborationsOffsetPaginated;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class ListCollaborationsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public ListCollaborationsManager() {
    this.networkSession = new NetworkSession();
  }

  protected ListCollaborationsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves a list of pending and active collaborations for a file. This returns all the users
   * that have access to the file or have been invited to the file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public Collaborations getFileCollaborations(String fileId) {
    return getFileCollaborations(
        fileId, new GetFileCollaborationsQueryParams(), new GetFileCollaborationsHeaders());
  }

  /**
   * Retrieves a list of pending and active collaborations for a file. This returns all the users
   * that have access to the file or have been invited to the file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getFileCollaborations method
   */
  public Collaborations getFileCollaborations(
      String fileId, GetFileCollaborationsQueryParams queryParams) {
    return getFileCollaborations(fileId, queryParams, new GetFileCollaborationsHeaders());
  }

  /**
   * Retrieves a list of pending and active collaborations for a file. This returns all the users
   * that have access to the file or have been invited to the file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of getFileCollaborations method
   */
  public Collaborations getFileCollaborations(String fileId, GetFileCollaborationsHeaders headers) {
    return getFileCollaborations(fileId, new GetFileCollaborationsQueryParams(), headers);
  }

  /**
   * Retrieves a list of pending and active collaborations for a file. This returns all the users
   * that have access to the file or have been invited to the file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getFileCollaborations method
   * @param headers Headers of getFileCollaborations method
   */
  public Collaborations getFileCollaborations(
      String fileId,
      GetFileCollaborationsQueryParams queryParams,
      GetFileCollaborationsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("marker", convertToString(queryParams.getMarker()))));
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
                            "/collaborations"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Collaborations.class);
  }

  /**
   * Retrieves a list of pending and active collaborations for a folder. This returns all the users
   * that have access to the folder or have been invited to the folder.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`. Example: "12345"
   */
  public Collaborations getFolderCollaborations(String folderId) {
    return getFolderCollaborations(
        folderId, new GetFolderCollaborationsQueryParams(), new GetFolderCollaborationsHeaders());
  }

  /**
   * Retrieves a list of pending and active collaborations for a folder. This returns all the users
   * that have access to the folder or have been invited to the folder.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getFolderCollaborations method
   */
  public Collaborations getFolderCollaborations(
      String folderId, GetFolderCollaborationsQueryParams queryParams) {
    return getFolderCollaborations(folderId, queryParams, new GetFolderCollaborationsHeaders());
  }

  /**
   * Retrieves a list of pending and active collaborations for a folder. This returns all the users
   * that have access to the folder or have been invited to the folder.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`. Example: "12345"
   * @param headers Headers of getFolderCollaborations method
   */
  public Collaborations getFolderCollaborations(
      String folderId, GetFolderCollaborationsHeaders headers) {
    return getFolderCollaborations(folderId, new GetFolderCollaborationsQueryParams(), headers);
  }

  /**
   * Retrieves a list of pending and active collaborations for a folder. This returns all the users
   * that have access to the folder or have been invited to the folder.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getFolderCollaborations method
   * @param headers Headers of getFolderCollaborations method
   */
  public Collaborations getFolderCollaborations(
      String folderId,
      GetFolderCollaborationsQueryParams queryParams,
      GetFolderCollaborationsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("marker", convertToString(queryParams.getMarker()))));
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
                            "/collaborations"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Collaborations.class);
  }

  /**
   * Retrieves all pending collaboration invites for this user.
   *
   * @param queryParams Query parameters of getCollaborations method
   */
  public CollaborationsOffsetPaginated getCollaborations(GetCollaborationsQueryParams queryParams) {
    return getCollaborations(queryParams, new GetCollaborationsHeaders());
  }

  /**
   * Retrieves all pending collaboration invites for this user.
   *
   * @param queryParams Query parameters of getCollaborations method
   * @param headers Headers of getCollaborations method
   */
  public CollaborationsOffsetPaginated getCollaborations(
      GetCollaborationsQueryParams queryParams, GetCollaborationsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("status", convertToString(queryParams.getStatus())),
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
                            "/2.0/collaborations"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), CollaborationsOffsetPaginated.class);
  }

  /**
   * Retrieves all the collaborations for a group. The user must have admin permissions to inspect
   * enterprise's groups.
   *
   * <p>Each collaboration object has details on which files or folders the group has access to and
   * with what role.
   *
   * @param groupId The ID of the group. Example: "57645"
   */
  public CollaborationsOffsetPaginated getGroupCollaborations(String groupId) {
    return getGroupCollaborations(
        groupId, new GetGroupCollaborationsQueryParams(), new GetGroupCollaborationsHeaders());
  }

  /**
   * Retrieves all the collaborations for a group. The user must have admin permissions to inspect
   * enterprise's groups.
   *
   * <p>Each collaboration object has details on which files or folders the group has access to and
   * with what role.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param queryParams Query parameters of getGroupCollaborations method
   */
  public CollaborationsOffsetPaginated getGroupCollaborations(
      String groupId, GetGroupCollaborationsQueryParams queryParams) {
    return getGroupCollaborations(groupId, queryParams, new GetGroupCollaborationsHeaders());
  }

  /**
   * Retrieves all the collaborations for a group. The user must have admin permissions to inspect
   * enterprise's groups.
   *
   * <p>Each collaboration object has details on which files or folders the group has access to and
   * with what role.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param headers Headers of getGroupCollaborations method
   */
  public CollaborationsOffsetPaginated getGroupCollaborations(
      String groupId, GetGroupCollaborationsHeaders headers) {
    return getGroupCollaborations(groupId, new GetGroupCollaborationsQueryParams(), headers);
  }

  /**
   * Retrieves all the collaborations for a group. The user must have admin permissions to inspect
   * enterprise's groups.
   *
   * <p>Each collaboration object has details on which files or folders the group has access to and
   * with what role.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param queryParams Query parameters of getGroupCollaborations method
   * @param headers Headers of getGroupCollaborations method
   */
  public CollaborationsOffsetPaginated getGroupCollaborations(
      String groupId,
      GetGroupCollaborationsQueryParams queryParams,
      GetGroupCollaborationsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("offset", convertToString(queryParams.getOffset()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/groups/",
                            convertToString(groupId),
                            "/collaborations"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), CollaborationsOffsetPaginated.class);
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

    public ListCollaborationsManager build() {
      return new ListCollaborationsManager(this);
    }
  }
}
