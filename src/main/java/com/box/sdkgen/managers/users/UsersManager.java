package com.box.sdkgen.managers.users;

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
import com.box.sdkgen.schemas.userfull.UserFull;
import com.box.sdkgen.schemas.users.Users;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class UsersManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public UsersManager() {
    this.networkSession = new NetworkSession();
  }

  protected UsersManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Returns a list of all users for the Enterprise along with their `user_id`, `public_name`, and
   * `login`.
   *
   * <p>The application and the authenticated user need to have the permission to look up users in
   * the entire enterprise.
   */
  public Users getUsers() {
    return getUsers(new GetUsersQueryParams(), new GetUsersHeaders());
  }

  /**
   * Returns a list of all users for the Enterprise along with their `user_id`, `public_name`, and
   * `login`.
   *
   * <p>The application and the authenticated user need to have the permission to look up users in
   * the entire enterprise.
   *
   * @param queryParams Query parameters of getUsers method
   */
  public Users getUsers(GetUsersQueryParams queryParams) {
    return getUsers(queryParams, new GetUsersHeaders());
  }

  /**
   * Returns a list of all users for the Enterprise along with their `user_id`, `public_name`, and
   * `login`.
   *
   * <p>The application and the authenticated user need to have the permission to look up users in
   * the entire enterprise.
   *
   * @param headers Headers of getUsers method
   */
  public Users getUsers(GetUsersHeaders headers) {
    return getUsers(new GetUsersQueryParams(), headers);
  }

  /**
   * Returns a list of all users for the Enterprise along with their `user_id`, `public_name`, and
   * `login`.
   *
   * <p>The application and the authenticated user need to have the permission to look up users in
   * the entire enterprise.
   *
   * @param queryParams Query parameters of getUsers method
   * @param headers Headers of getUsers method
   */
  public Users getUsers(GetUsersQueryParams queryParams, GetUsersHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("filter_term", convertToString(queryParams.getFilterTerm())),
                entryOf("user_type", convertToString(queryParams.getUserType())),
                entryOf(
                    "external_app_user_id", convertToString(queryParams.getExternalAppUserId())),
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("offset", convertToString(queryParams.getOffset())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("usemarker", convertToString(queryParams.getUsemarker())),
                entryOf("marker", convertToString(queryParams.getMarker()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/users"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Users.class);
  }

  /**
   * Creates a new managed user in an enterprise. This endpoint is only available to users and
   * applications with the right admin permissions.
   *
   * @param requestBody Request body of createUser method
   */
  public UserFull createUser(CreateUserRequestBody requestBody) {
    return createUser(requestBody, new CreateUserQueryParams(), new CreateUserHeaders());
  }

  /**
   * Creates a new managed user in an enterprise. This endpoint is only available to users and
   * applications with the right admin permissions.
   *
   * @param requestBody Request body of createUser method
   * @param queryParams Query parameters of createUser method
   */
  public UserFull createUser(CreateUserRequestBody requestBody, CreateUserQueryParams queryParams) {
    return createUser(requestBody, queryParams, new CreateUserHeaders());
  }

  /**
   * Creates a new managed user in an enterprise. This endpoint is only available to users and
   * applications with the right admin permissions.
   *
   * @param requestBody Request body of createUser method
   * @param headers Headers of createUser method
   */
  public UserFull createUser(CreateUserRequestBody requestBody, CreateUserHeaders headers) {
    return createUser(requestBody, new CreateUserQueryParams(), headers);
  }

  /**
   * Creates a new managed user in an enterprise. This endpoint is only available to users and
   * applications with the right admin permissions.
   *
   * @param requestBody Request body of createUser method
   * @param queryParams Query parameters of createUser method
   * @param headers Headers of createUser method
   */
  public UserFull createUser(
      CreateUserRequestBody requestBody,
      CreateUserQueryParams queryParams,
      CreateUserHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/users"),
                        "POST")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UserFull.class);
  }

  /**
   * Retrieves information about the user who is currently authenticated.
   *
   * <p>In the case of a client-side authenticated OAuth 2.0 application this will be the user who
   * authorized the app.
   *
   * <p>In the case of a JWT, server-side authenticated application this will be the service account
   * that belongs to the application by default.
   *
   * <p>Use the `As-User` header to change who this API call is made on behalf of.
   */
  public UserFull getUserMe() {
    return getUserMe(new GetUserMeQueryParams(), new GetUserMeHeaders());
  }

  /**
   * Retrieves information about the user who is currently authenticated.
   *
   * <p>In the case of a client-side authenticated OAuth 2.0 application this will be the user who
   * authorized the app.
   *
   * <p>In the case of a JWT, server-side authenticated application this will be the service account
   * that belongs to the application by default.
   *
   * <p>Use the `As-User` header to change who this API call is made on behalf of.
   *
   * @param queryParams Query parameters of getUserMe method
   */
  public UserFull getUserMe(GetUserMeQueryParams queryParams) {
    return getUserMe(queryParams, new GetUserMeHeaders());
  }

  /**
   * Retrieves information about the user who is currently authenticated.
   *
   * <p>In the case of a client-side authenticated OAuth 2.0 application this will be the user who
   * authorized the app.
   *
   * <p>In the case of a JWT, server-side authenticated application this will be the service account
   * that belongs to the application by default.
   *
   * <p>Use the `As-User` header to change who this API call is made on behalf of.
   *
   * @param headers Headers of getUserMe method
   */
  public UserFull getUserMe(GetUserMeHeaders headers) {
    return getUserMe(new GetUserMeQueryParams(), headers);
  }

  /**
   * Retrieves information about the user who is currently authenticated.
   *
   * <p>In the case of a client-side authenticated OAuth 2.0 application this will be the user who
   * authorized the app.
   *
   * <p>In the case of a JWT, server-side authenticated application this will be the service account
   * that belongs to the application by default.
   *
   * <p>Use the `As-User` header to change who this API call is made on behalf of.
   *
   * @param queryParams Query parameters of getUserMe method
   * @param headers Headers of getUserMe method
   */
  public UserFull getUserMe(GetUserMeQueryParams queryParams, GetUserMeHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/users/me"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UserFull.class);
  }

  /**
   * Retrieves information about a user in the enterprise.
   *
   * <p>The application and the authenticated user need to have the permission to look up users in
   * the entire enterprise.
   *
   * <p>This endpoint also returns a limited set of information for external users who are
   * collaborated on content owned by the enterprise for authenticated users with the right scopes.
   * In this case, disallowed fields will return null instead.
   *
   * @param userId The ID of the user. Example: "12345"
   */
  public UserFull getUserById(String userId) {
    return getUserById(userId, new GetUserByIdQueryParams(), new GetUserByIdHeaders());
  }

  /**
   * Retrieves information about a user in the enterprise.
   *
   * <p>The application and the authenticated user need to have the permission to look up users in
   * the entire enterprise.
   *
   * <p>This endpoint also returns a limited set of information for external users who are
   * collaborated on content owned by the enterprise for authenticated users with the right scopes.
   * In this case, disallowed fields will return null instead.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param queryParams Query parameters of getUserById method
   */
  public UserFull getUserById(String userId, GetUserByIdQueryParams queryParams) {
    return getUserById(userId, queryParams, new GetUserByIdHeaders());
  }

  /**
   * Retrieves information about a user in the enterprise.
   *
   * <p>The application and the authenticated user need to have the permission to look up users in
   * the entire enterprise.
   *
   * <p>This endpoint also returns a limited set of information for external users who are
   * collaborated on content owned by the enterprise for authenticated users with the right scopes.
   * In this case, disallowed fields will return null instead.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param headers Headers of getUserById method
   */
  public UserFull getUserById(String userId, GetUserByIdHeaders headers) {
    return getUserById(userId, new GetUserByIdQueryParams(), headers);
  }

  /**
   * Retrieves information about a user in the enterprise.
   *
   * <p>The application and the authenticated user need to have the permission to look up users in
   * the entire enterprise.
   *
   * <p>This endpoint also returns a limited set of information for external users who are
   * collaborated on content owned by the enterprise for authenticated users with the right scopes.
   * In this case, disallowed fields will return null instead.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param queryParams Query parameters of getUserById method
   * @param headers Headers of getUserById method
   */
  public UserFull getUserById(
      String userId, GetUserByIdQueryParams queryParams, GetUserByIdHeaders headers) {
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
                            "/2.0/users/",
                            convertToString(userId)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UserFull.class);
  }

  /**
   * Updates a managed or app user in an enterprise. This endpoint is only available to users and
   * applications with the right admin permissions.
   *
   * @param userId The ID of the user. Example: "12345"
   */
  public UserFull updateUserById(String userId) {
    return updateUserById(
        userId,
        new UpdateUserByIdRequestBody(),
        new UpdateUserByIdQueryParams(),
        new UpdateUserByIdHeaders());
  }

  /**
   * Updates a managed or app user in an enterprise. This endpoint is only available to users and
   * applications with the right admin permissions.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param requestBody Request body of updateUserById method
   */
  public UserFull updateUserById(String userId, UpdateUserByIdRequestBody requestBody) {
    return updateUserById(
        userId, requestBody, new UpdateUserByIdQueryParams(), new UpdateUserByIdHeaders());
  }

  /**
   * Updates a managed or app user in an enterprise. This endpoint is only available to users and
   * applications with the right admin permissions.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param queryParams Query parameters of updateUserById method
   */
  public UserFull updateUserById(String userId, UpdateUserByIdQueryParams queryParams) {
    return updateUserById(
        userId, new UpdateUserByIdRequestBody(), queryParams, new UpdateUserByIdHeaders());
  }

  /**
   * Updates a managed or app user in an enterprise. This endpoint is only available to users and
   * applications with the right admin permissions.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param requestBody Request body of updateUserById method
   * @param queryParams Query parameters of updateUserById method
   */
  public UserFull updateUserById(
      String userId, UpdateUserByIdRequestBody requestBody, UpdateUserByIdQueryParams queryParams) {
    return updateUserById(userId, requestBody, queryParams, new UpdateUserByIdHeaders());
  }

  /**
   * Updates a managed or app user in an enterprise. This endpoint is only available to users and
   * applications with the right admin permissions.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param headers Headers of updateUserById method
   */
  public UserFull updateUserById(String userId, UpdateUserByIdHeaders headers) {
    return updateUserById(
        userId, new UpdateUserByIdRequestBody(), new UpdateUserByIdQueryParams(), headers);
  }

  /**
   * Updates a managed or app user in an enterprise. This endpoint is only available to users and
   * applications with the right admin permissions.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param requestBody Request body of updateUserById method
   * @param headers Headers of updateUserById method
   */
  public UserFull updateUserById(
      String userId, UpdateUserByIdRequestBody requestBody, UpdateUserByIdHeaders headers) {
    return updateUserById(userId, requestBody, new UpdateUserByIdQueryParams(), headers);
  }

  /**
   * Updates a managed or app user in an enterprise. This endpoint is only available to users and
   * applications with the right admin permissions.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param queryParams Query parameters of updateUserById method
   * @param headers Headers of updateUserById method
   */
  public UserFull updateUserById(
      String userId, UpdateUserByIdQueryParams queryParams, UpdateUserByIdHeaders headers) {
    return updateUserById(userId, new UpdateUserByIdRequestBody(), queryParams, headers);
  }

  /**
   * Updates a managed or app user in an enterprise. This endpoint is only available to users and
   * applications with the right admin permissions.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param requestBody Request body of updateUserById method
   * @param queryParams Query parameters of updateUserById method
   * @param headers Headers of updateUserById method
   */
  public UserFull updateUserById(
      String userId,
      UpdateUserByIdRequestBody requestBody,
      UpdateUserByIdQueryParams queryParams,
      UpdateUserByIdHeaders headers) {
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
                            "/2.0/users/",
                            convertToString(userId)),
                        "PUT")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UserFull.class);
  }

  /**
   * Deletes a user. By default, this operation fails if the user still owns any content, was
   * recently active, or recently joined the enterprise from a free account. To proceed, move their
   * owned content first, or use the `force` parameter to delete the user and their files.
   *
   * @param userId The ID of the user. Example: "12345"
   */
  public void deleteUserById(String userId) {
    deleteUserById(userId, new DeleteUserByIdQueryParams(), new DeleteUserByIdHeaders());
  }

  /**
   * Deletes a user. By default, this operation fails if the user still owns any content, was
   * recently active, or recently joined the enterprise from a free account. To proceed, move their
   * owned content first, or use the `force` parameter to delete the user and their files.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param queryParams Query parameters of deleteUserById method
   */
  public void deleteUserById(String userId, DeleteUserByIdQueryParams queryParams) {
    deleteUserById(userId, queryParams, new DeleteUserByIdHeaders());
  }

  /**
   * Deletes a user. By default, this operation fails if the user still owns any content, was
   * recently active, or recently joined the enterprise from a free account. To proceed, move their
   * owned content first, or use the `force` parameter to delete the user and their files.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param headers Headers of deleteUserById method
   */
  public void deleteUserById(String userId, DeleteUserByIdHeaders headers) {
    deleteUserById(userId, new DeleteUserByIdQueryParams(), headers);
  }

  /**
   * Deletes a user. By default, this operation fails if the user still owns any content, was
   * recently active, or recently joined the enterprise from a free account. To proceed, move their
   * owned content first, or use the `force` parameter to delete the user and their files.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param queryParams Query parameters of deleteUserById method
   * @param headers Headers of deleteUserById method
   */
  public void deleteUserById(
      String userId, DeleteUserByIdQueryParams queryParams, DeleteUserByIdHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("notify", convertToString(queryParams.getNotify())),
                entryOf("force", convertToString(queryParams.getForce()))));
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
                            convertToString(userId)),
                        "DELETE")
                    .params(queryParamsMap)
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

    public UsersManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new UsersManager(this);
    }
  }
}
