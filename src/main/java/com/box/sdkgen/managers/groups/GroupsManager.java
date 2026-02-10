package com.box.sdkgen.managers.groups;

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
import com.box.sdkgen.schemas.groupfull.GroupFull;
import com.box.sdkgen.schemas.groups.Groups;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class GroupsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public GroupsManager() {
    this.networkSession = new NetworkSession();
  }

  protected GroupsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves all of the groups for a given enterprise. The user must have admin permissions to
   * inspect enterprise's groups.
   */
  public Groups getGroups() {
    return getGroups(new GetGroupsQueryParams(), new GetGroupsHeaders());
  }

  /**
   * Retrieves all of the groups for a given enterprise. The user must have admin permissions to
   * inspect enterprise's groups.
   *
   * @param queryParams Query parameters of getGroups method
   */
  public Groups getGroups(GetGroupsQueryParams queryParams) {
    return getGroups(queryParams, new GetGroupsHeaders());
  }

  /**
   * Retrieves all of the groups for a given enterprise. The user must have admin permissions to
   * inspect enterprise's groups.
   *
   * @param headers Headers of getGroups method
   */
  public Groups getGroups(GetGroupsHeaders headers) {
    return getGroups(new GetGroupsQueryParams(), headers);
  }

  /**
   * Retrieves all of the groups for a given enterprise. The user must have admin permissions to
   * inspect enterprise's groups.
   *
   * @param queryParams Query parameters of getGroups method
   * @param headers Headers of getGroups method
   */
  public Groups getGroups(GetGroupsQueryParams queryParams, GetGroupsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("filter_term", convertToString(queryParams.getFilterTerm())),
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("offset", convertToString(queryParams.getOffset()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/groups"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Groups.class);
  }

  /**
   * Creates a new group of users in an enterprise. Only users with admin permissions can create new
   * groups.
   *
   * @param requestBody Request body of createGroup method
   */
  public GroupFull createGroup(CreateGroupRequestBody requestBody) {
    return createGroup(requestBody, new CreateGroupQueryParams(), new CreateGroupHeaders());
  }

  /**
   * Creates a new group of users in an enterprise. Only users with admin permissions can create new
   * groups.
   *
   * @param requestBody Request body of createGroup method
   * @param queryParams Query parameters of createGroup method
   */
  public GroupFull createGroup(
      CreateGroupRequestBody requestBody, CreateGroupQueryParams queryParams) {
    return createGroup(requestBody, queryParams, new CreateGroupHeaders());
  }

  /**
   * Creates a new group of users in an enterprise. Only users with admin permissions can create new
   * groups.
   *
   * @param requestBody Request body of createGroup method
   * @param headers Headers of createGroup method
   */
  public GroupFull createGroup(CreateGroupRequestBody requestBody, CreateGroupHeaders headers) {
    return createGroup(requestBody, new CreateGroupQueryParams(), headers);
  }

  /**
   * Creates a new group of users in an enterprise. Only users with admin permissions can create new
   * groups.
   *
   * @param requestBody Request body of createGroup method
   * @param queryParams Query parameters of createGroup method
   * @param headers Headers of createGroup method
   */
  public GroupFull createGroup(
      CreateGroupRequestBody requestBody,
      CreateGroupQueryParams queryParams,
      CreateGroupHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/groups"),
                        "POST")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), GroupFull.class);
  }

  /**
   * Retrieves information about a group. Only members of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupId The ID of the group. Example: "57645"
   */
  public GroupFull getGroupById(String groupId) {
    return getGroupById(groupId, new GetGroupByIdQueryParams(), new GetGroupByIdHeaders());
  }

  /**
   * Retrieves information about a group. Only members of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param queryParams Query parameters of getGroupById method
   */
  public GroupFull getGroupById(String groupId, GetGroupByIdQueryParams queryParams) {
    return getGroupById(groupId, queryParams, new GetGroupByIdHeaders());
  }

  /**
   * Retrieves information about a group. Only members of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param headers Headers of getGroupById method
   */
  public GroupFull getGroupById(String groupId, GetGroupByIdHeaders headers) {
    return getGroupById(groupId, new GetGroupByIdQueryParams(), headers);
  }

  /**
   * Retrieves information about a group. Only members of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param queryParams Query parameters of getGroupById method
   * @param headers Headers of getGroupById method
   */
  public GroupFull getGroupById(
      String groupId, GetGroupByIdQueryParams queryParams, GetGroupByIdHeaders headers) {
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
                            "/2.0/groups/",
                            convertToString(groupId)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), GroupFull.class);
  }

  /**
   * Updates a specific group. Only admins of this group or users with admin-level permissions will
   * be able to use this API.
   *
   * @param groupId The ID of the group. Example: "57645"
   */
  public GroupFull updateGroupById(String groupId) {
    return updateGroupById(
        groupId,
        new UpdateGroupByIdRequestBody(),
        new UpdateGroupByIdQueryParams(),
        new UpdateGroupByIdHeaders());
  }

  /**
   * Updates a specific group. Only admins of this group or users with admin-level permissions will
   * be able to use this API.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param requestBody Request body of updateGroupById method
   */
  public GroupFull updateGroupById(String groupId, UpdateGroupByIdRequestBody requestBody) {
    return updateGroupById(
        groupId, requestBody, new UpdateGroupByIdQueryParams(), new UpdateGroupByIdHeaders());
  }

  /**
   * Updates a specific group. Only admins of this group or users with admin-level permissions will
   * be able to use this API.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param queryParams Query parameters of updateGroupById method
   */
  public GroupFull updateGroupById(String groupId, UpdateGroupByIdQueryParams queryParams) {
    return updateGroupById(
        groupId, new UpdateGroupByIdRequestBody(), queryParams, new UpdateGroupByIdHeaders());
  }

  /**
   * Updates a specific group. Only admins of this group or users with admin-level permissions will
   * be able to use this API.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param requestBody Request body of updateGroupById method
   * @param queryParams Query parameters of updateGroupById method
   */
  public GroupFull updateGroupById(
      String groupId,
      UpdateGroupByIdRequestBody requestBody,
      UpdateGroupByIdQueryParams queryParams) {
    return updateGroupById(groupId, requestBody, queryParams, new UpdateGroupByIdHeaders());
  }

  /**
   * Updates a specific group. Only admins of this group or users with admin-level permissions will
   * be able to use this API.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param headers Headers of updateGroupById method
   */
  public GroupFull updateGroupById(String groupId, UpdateGroupByIdHeaders headers) {
    return updateGroupById(
        groupId, new UpdateGroupByIdRequestBody(), new UpdateGroupByIdQueryParams(), headers);
  }

  /**
   * Updates a specific group. Only admins of this group or users with admin-level permissions will
   * be able to use this API.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param requestBody Request body of updateGroupById method
   * @param headers Headers of updateGroupById method
   */
  public GroupFull updateGroupById(
      String groupId, UpdateGroupByIdRequestBody requestBody, UpdateGroupByIdHeaders headers) {
    return updateGroupById(groupId, requestBody, new UpdateGroupByIdQueryParams(), headers);
  }

  /**
   * Updates a specific group. Only admins of this group or users with admin-level permissions will
   * be able to use this API.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param queryParams Query parameters of updateGroupById method
   * @param headers Headers of updateGroupById method
   */
  public GroupFull updateGroupById(
      String groupId, UpdateGroupByIdQueryParams queryParams, UpdateGroupByIdHeaders headers) {
    return updateGroupById(groupId, new UpdateGroupByIdRequestBody(), queryParams, headers);
  }

  /**
   * Updates a specific group. Only admins of this group or users with admin-level permissions will
   * be able to use this API.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param requestBody Request body of updateGroupById method
   * @param queryParams Query parameters of updateGroupById method
   * @param headers Headers of updateGroupById method
   */
  public GroupFull updateGroupById(
      String groupId,
      UpdateGroupByIdRequestBody requestBody,
      UpdateGroupByIdQueryParams queryParams,
      UpdateGroupByIdHeaders headers) {
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
                            "/2.0/groups/",
                            convertToString(groupId)),
                        "PUT")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), GroupFull.class);
  }

  /**
   * Permanently deletes a group. Only users with admin-level permissions will be able to use this
   * API.
   *
   * @param groupId The ID of the group. Example: "57645"
   */
  public void deleteGroupById(String groupId) {
    deleteGroupById(groupId, new DeleteGroupByIdHeaders());
  }

  /**
   * Permanently deletes a group. Only users with admin-level permissions will be able to use this
   * API.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param headers Headers of deleteGroupById method
   */
  public void deleteGroupById(String groupId, DeleteGroupByIdHeaders headers) {
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
                            convertToString(groupId)),
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

    public GroupsManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new GroupsManager(this);
    }
  }
}
