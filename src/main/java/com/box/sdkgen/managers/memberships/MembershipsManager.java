package com.box.sdkgen.managers.memberships;

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
import com.box.sdkgen.schemas.groupmembership.GroupMembership;
import com.box.sdkgen.schemas.groupmemberships.GroupMemberships;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class MembershipsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public MembershipsManager() {
    this.networkSession = new NetworkSession();
  }

  protected MembershipsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves all the groups for a user. Only members of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param userId The ID of the user. Example: "12345"
   */
  public GroupMemberships getUserMemberships(String userId) {
    return getUserMemberships(
        userId, new GetUserMembershipsQueryParams(), new GetUserMembershipsHeaders());
  }

  /**
   * Retrieves all the groups for a user. Only members of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param queryParams Query parameters of getUserMemberships method
   */
  public GroupMemberships getUserMemberships(
      String userId, GetUserMembershipsQueryParams queryParams) {
    return getUserMemberships(userId, queryParams, new GetUserMembershipsHeaders());
  }

  /**
   * Retrieves all the groups for a user. Only members of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param headers Headers of getUserMemberships method
   */
  public GroupMemberships getUserMemberships(String userId, GetUserMembershipsHeaders headers) {
    return getUserMemberships(userId, new GetUserMembershipsQueryParams(), headers);
  }

  /**
   * Retrieves all the groups for a user. Only members of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param queryParams Query parameters of getUserMemberships method
   * @param headers Headers of getUserMemberships method
   */
  public GroupMemberships getUserMemberships(
      String userId, GetUserMembershipsQueryParams queryParams, GetUserMembershipsHeaders headers) {
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
                            "/2.0/users/",
                            convertToString(userId),
                            "/memberships"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), GroupMemberships.class);
  }

  /**
   * Retrieves all the members for a group. Only members of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupId The ID of the group. Example: "57645"
   */
  public GroupMemberships getGroupMemberships(String groupId) {
    return getGroupMemberships(
        groupId, new GetGroupMembershipsQueryParams(), new GetGroupMembershipsHeaders());
  }

  /**
   * Retrieves all the members for a group. Only members of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param queryParams Query parameters of getGroupMemberships method
   */
  public GroupMemberships getGroupMemberships(
      String groupId, GetGroupMembershipsQueryParams queryParams) {
    return getGroupMemberships(groupId, queryParams, new GetGroupMembershipsHeaders());
  }

  /**
   * Retrieves all the members for a group. Only members of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param headers Headers of getGroupMemberships method
   */
  public GroupMemberships getGroupMemberships(String groupId, GetGroupMembershipsHeaders headers) {
    return getGroupMemberships(groupId, new GetGroupMembershipsQueryParams(), headers);
  }

  /**
   * Retrieves all the members for a group. Only members of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupId The ID of the group. Example: "57645"
   * @param queryParams Query parameters of getGroupMemberships method
   * @param headers Headers of getGroupMemberships method
   */
  public GroupMemberships getGroupMemberships(
      String groupId,
      GetGroupMembershipsQueryParams queryParams,
      GetGroupMembershipsHeaders headers) {
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
                            "/memberships"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), GroupMemberships.class);
  }

  /**
   * Creates a group membership. Only users with admin-level permissions will be able to use this
   * API.
   *
   * @param requestBody Request body of createGroupMembership method
   */
  public GroupMembership createGroupMembership(CreateGroupMembershipRequestBody requestBody) {
    return createGroupMembership(
        requestBody, new CreateGroupMembershipQueryParams(), new CreateGroupMembershipHeaders());
  }

  /**
   * Creates a group membership. Only users with admin-level permissions will be able to use this
   * API.
   *
   * @param requestBody Request body of createGroupMembership method
   * @param queryParams Query parameters of createGroupMembership method
   */
  public GroupMembership createGroupMembership(
      CreateGroupMembershipRequestBody requestBody, CreateGroupMembershipQueryParams queryParams) {
    return createGroupMembership(requestBody, queryParams, new CreateGroupMembershipHeaders());
  }

  /**
   * Creates a group membership. Only users with admin-level permissions will be able to use this
   * API.
   *
   * @param requestBody Request body of createGroupMembership method
   * @param headers Headers of createGroupMembership method
   */
  public GroupMembership createGroupMembership(
      CreateGroupMembershipRequestBody requestBody, CreateGroupMembershipHeaders headers) {
    return createGroupMembership(requestBody, new CreateGroupMembershipQueryParams(), headers);
  }

  /**
   * Creates a group membership. Only users with admin-level permissions will be able to use this
   * API.
   *
   * @param requestBody Request body of createGroupMembership method
   * @param queryParams Query parameters of createGroupMembership method
   * @param headers Headers of createGroupMembership method
   */
  public GroupMembership createGroupMembership(
      CreateGroupMembershipRequestBody requestBody,
      CreateGroupMembershipQueryParams queryParams,
      CreateGroupMembershipHeaders headers) {
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
                            "/2.0/group_memberships"),
                        "POST")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), GroupMembership.class);
  }

  /**
   * Retrieves a specific group membership. Only admins of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupMembershipId The ID of the group membership. Example: "434534"
   */
  public GroupMembership getGroupMembershipById(String groupMembershipId) {
    return getGroupMembershipById(
        groupMembershipId,
        new GetGroupMembershipByIdQueryParams(),
        new GetGroupMembershipByIdHeaders());
  }

  /**
   * Retrieves a specific group membership. Only admins of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupMembershipId The ID of the group membership. Example: "434534"
   * @param queryParams Query parameters of getGroupMembershipById method
   */
  public GroupMembership getGroupMembershipById(
      String groupMembershipId, GetGroupMembershipByIdQueryParams queryParams) {
    return getGroupMembershipById(
        groupMembershipId, queryParams, new GetGroupMembershipByIdHeaders());
  }

  /**
   * Retrieves a specific group membership. Only admins of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupMembershipId The ID of the group membership. Example: "434534"
   * @param headers Headers of getGroupMembershipById method
   */
  public GroupMembership getGroupMembershipById(
      String groupMembershipId, GetGroupMembershipByIdHeaders headers) {
    return getGroupMembershipById(
        groupMembershipId, new GetGroupMembershipByIdQueryParams(), headers);
  }

  /**
   * Retrieves a specific group membership. Only admins of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupMembershipId The ID of the group membership. Example: "434534"
   * @param queryParams Query parameters of getGroupMembershipById method
   * @param headers Headers of getGroupMembershipById method
   */
  public GroupMembership getGroupMembershipById(
      String groupMembershipId,
      GetGroupMembershipByIdQueryParams queryParams,
      GetGroupMembershipByIdHeaders headers) {
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
                            "/2.0/group_memberships/",
                            convertToString(groupMembershipId)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), GroupMembership.class);
  }

  /**
   * Updates a user's group membership. Only admins of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupMembershipId The ID of the group membership. Example: "434534"
   */
  public GroupMembership updateGroupMembershipById(String groupMembershipId) {
    return updateGroupMembershipById(
        groupMembershipId,
        new UpdateGroupMembershipByIdRequestBody(),
        new UpdateGroupMembershipByIdQueryParams(),
        new UpdateGroupMembershipByIdHeaders());
  }

  /**
   * Updates a user's group membership. Only admins of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupMembershipId The ID of the group membership. Example: "434534"
   * @param requestBody Request body of updateGroupMembershipById method
   */
  public GroupMembership updateGroupMembershipById(
      String groupMembershipId, UpdateGroupMembershipByIdRequestBody requestBody) {
    return updateGroupMembershipById(
        groupMembershipId,
        requestBody,
        new UpdateGroupMembershipByIdQueryParams(),
        new UpdateGroupMembershipByIdHeaders());
  }

  /**
   * Updates a user's group membership. Only admins of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupMembershipId The ID of the group membership. Example: "434534"
   * @param queryParams Query parameters of updateGroupMembershipById method
   */
  public GroupMembership updateGroupMembershipById(
      String groupMembershipId, UpdateGroupMembershipByIdQueryParams queryParams) {
    return updateGroupMembershipById(
        groupMembershipId,
        new UpdateGroupMembershipByIdRequestBody(),
        queryParams,
        new UpdateGroupMembershipByIdHeaders());
  }

  /**
   * Updates a user's group membership. Only admins of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupMembershipId The ID of the group membership. Example: "434534"
   * @param requestBody Request body of updateGroupMembershipById method
   * @param queryParams Query parameters of updateGroupMembershipById method
   */
  public GroupMembership updateGroupMembershipById(
      String groupMembershipId,
      UpdateGroupMembershipByIdRequestBody requestBody,
      UpdateGroupMembershipByIdQueryParams queryParams) {
    return updateGroupMembershipById(
        groupMembershipId, requestBody, queryParams, new UpdateGroupMembershipByIdHeaders());
  }

  /**
   * Updates a user's group membership. Only admins of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupMembershipId The ID of the group membership. Example: "434534"
   * @param headers Headers of updateGroupMembershipById method
   */
  public GroupMembership updateGroupMembershipById(
      String groupMembershipId, UpdateGroupMembershipByIdHeaders headers) {
    return updateGroupMembershipById(
        groupMembershipId,
        new UpdateGroupMembershipByIdRequestBody(),
        new UpdateGroupMembershipByIdQueryParams(),
        headers);
  }

  /**
   * Updates a user's group membership. Only admins of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupMembershipId The ID of the group membership. Example: "434534"
   * @param requestBody Request body of updateGroupMembershipById method
   * @param headers Headers of updateGroupMembershipById method
   */
  public GroupMembership updateGroupMembershipById(
      String groupMembershipId,
      UpdateGroupMembershipByIdRequestBody requestBody,
      UpdateGroupMembershipByIdHeaders headers) {
    return updateGroupMembershipById(
        groupMembershipId, requestBody, new UpdateGroupMembershipByIdQueryParams(), headers);
  }

  /**
   * Updates a user's group membership. Only admins of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupMembershipId The ID of the group membership. Example: "434534"
   * @param queryParams Query parameters of updateGroupMembershipById method
   * @param headers Headers of updateGroupMembershipById method
   */
  public GroupMembership updateGroupMembershipById(
      String groupMembershipId,
      UpdateGroupMembershipByIdQueryParams queryParams,
      UpdateGroupMembershipByIdHeaders headers) {
    return updateGroupMembershipById(
        groupMembershipId, new UpdateGroupMembershipByIdRequestBody(), queryParams, headers);
  }

  /**
   * Updates a user's group membership. Only admins of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupMembershipId The ID of the group membership. Example: "434534"
   * @param requestBody Request body of updateGroupMembershipById method
   * @param queryParams Query parameters of updateGroupMembershipById method
   * @param headers Headers of updateGroupMembershipById method
   */
  public GroupMembership updateGroupMembershipById(
      String groupMembershipId,
      UpdateGroupMembershipByIdRequestBody requestBody,
      UpdateGroupMembershipByIdQueryParams queryParams,
      UpdateGroupMembershipByIdHeaders headers) {
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
                            "/2.0/group_memberships/",
                            convertToString(groupMembershipId)),
                        "PUT")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), GroupMembership.class);
  }

  /**
   * Deletes a specific group membership. Only admins of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupMembershipId The ID of the group membership. Example: "434534"
   */
  public void deleteGroupMembershipById(String groupMembershipId) {
    deleteGroupMembershipById(groupMembershipId, new DeleteGroupMembershipByIdHeaders());
  }

  /**
   * Deletes a specific group membership. Only admins of this group or users with admin-level
   * permissions will be able to use this API.
   *
   * @param groupMembershipId The ID of the group membership. Example: "434534"
   * @param headers Headers of deleteGroupMembershipById method
   */
  public void deleteGroupMembershipById(
      String groupMembershipId, DeleteGroupMembershipByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/group_memberships/",
                            convertToString(groupMembershipId)),
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

    public MembershipsManager build() {
      return new MembershipsManager(this);
    }
  }
}
