package com.box.sdkgen.managers.storagepolicyassignments;

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
import com.box.sdkgen.schemas.storagepolicyassignment.StoragePolicyAssignment;
import com.box.sdkgen.schemas.storagepolicyassignments.StoragePolicyAssignments;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class StoragePolicyAssignmentsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public StoragePolicyAssignmentsManager() {
    this.networkSession = new NetworkSession();
  }

  protected StoragePolicyAssignmentsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Fetches all the storage policy assignment for an enterprise or user.
   *
   * @param queryParams Query parameters of getStoragePolicyAssignments method
   */
  public StoragePolicyAssignments getStoragePolicyAssignments(
      GetStoragePolicyAssignmentsQueryParams queryParams) {
    return getStoragePolicyAssignments(queryParams, new GetStoragePolicyAssignmentsHeaders());
  }

  /**
   * Fetches all the storage policy assignment for an enterprise or user.
   *
   * @param queryParams Query parameters of getStoragePolicyAssignments method
   * @param headers Headers of getStoragePolicyAssignments method
   */
  public StoragePolicyAssignments getStoragePolicyAssignments(
      GetStoragePolicyAssignmentsQueryParams queryParams,
      GetStoragePolicyAssignmentsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("resolved_for_type", convertToString(queryParams.getResolvedForType())),
                entryOf("resolved_for_id", convertToString(queryParams.getResolvedForId()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/storage_policy_assignments"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), StoragePolicyAssignments.class);
  }

  /**
   * Creates a storage policy assignment for an enterprise or user.
   *
   * @param requestBody Request body of createStoragePolicyAssignment method
   */
  public StoragePolicyAssignment createStoragePolicyAssignment(
      CreateStoragePolicyAssignmentRequestBody requestBody) {
    return createStoragePolicyAssignment(requestBody, new CreateStoragePolicyAssignmentHeaders());
  }

  /**
   * Creates a storage policy assignment for an enterprise or user.
   *
   * @param requestBody Request body of createStoragePolicyAssignment method
   * @param headers Headers of createStoragePolicyAssignment method
   */
  public StoragePolicyAssignment createStoragePolicyAssignment(
      CreateStoragePolicyAssignmentRequestBody requestBody,
      CreateStoragePolicyAssignmentHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/storage_policy_assignments"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), StoragePolicyAssignment.class);
  }

  /**
   * Fetches a specific storage policy assignment.
   *
   * @param storagePolicyAssignmentId The ID of the storage policy assignment. Example: "932483"
   */
  public StoragePolicyAssignment getStoragePolicyAssignmentById(String storagePolicyAssignmentId) {
    return getStoragePolicyAssignmentById(
        storagePolicyAssignmentId, new GetStoragePolicyAssignmentByIdHeaders());
  }

  /**
   * Fetches a specific storage policy assignment.
   *
   * @param storagePolicyAssignmentId The ID of the storage policy assignment. Example: "932483"
   * @param headers Headers of getStoragePolicyAssignmentById method
   */
  public StoragePolicyAssignment getStoragePolicyAssignmentById(
      String storagePolicyAssignmentId, GetStoragePolicyAssignmentByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/storage_policy_assignments/",
                            convertToString(storagePolicyAssignmentId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), StoragePolicyAssignment.class);
  }

  /**
   * Updates a specific storage policy assignment.
   *
   * @param storagePolicyAssignmentId The ID of the storage policy assignment. Example: "932483"
   * @param requestBody Request body of updateStoragePolicyAssignmentById method
   */
  public StoragePolicyAssignment updateStoragePolicyAssignmentById(
      String storagePolicyAssignmentId, UpdateStoragePolicyAssignmentByIdRequestBody requestBody) {
    return updateStoragePolicyAssignmentById(
        storagePolicyAssignmentId, requestBody, new UpdateStoragePolicyAssignmentByIdHeaders());
  }

  /**
   * Updates a specific storage policy assignment.
   *
   * @param storagePolicyAssignmentId The ID of the storage policy assignment. Example: "932483"
   * @param requestBody Request body of updateStoragePolicyAssignmentById method
   * @param headers Headers of updateStoragePolicyAssignmentById method
   */
  public StoragePolicyAssignment updateStoragePolicyAssignmentById(
      String storagePolicyAssignmentId,
      UpdateStoragePolicyAssignmentByIdRequestBody requestBody,
      UpdateStoragePolicyAssignmentByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/storage_policy_assignments/",
                            convertToString(storagePolicyAssignmentId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), StoragePolicyAssignment.class);
  }

  /**
   * Delete a storage policy assignment.
   *
   * <p>Deleting a storage policy assignment on a user will have the user inherit the enterprise's
   * default storage policy.
   *
   * <p>There is a rate limit for calling this endpoint of only twice per user in a 24 hour time
   * frame.
   *
   * @param storagePolicyAssignmentId The ID of the storage policy assignment. Example: "932483"
   */
  public void deleteStoragePolicyAssignmentById(String storagePolicyAssignmentId) {
    deleteStoragePolicyAssignmentById(
        storagePolicyAssignmentId, new DeleteStoragePolicyAssignmentByIdHeaders());
  }

  /**
   * Delete a storage policy assignment.
   *
   * <p>Deleting a storage policy assignment on a user will have the user inherit the enterprise's
   * default storage policy.
   *
   * <p>There is a rate limit for calling this endpoint of only twice per user in a 24 hour time
   * frame.
   *
   * @param storagePolicyAssignmentId The ID of the storage policy assignment. Example: "932483"
   * @param headers Headers of deleteStoragePolicyAssignmentById method
   */
  public void deleteStoragePolicyAssignmentById(
      String storagePolicyAssignmentId, DeleteStoragePolicyAssignmentByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/storage_policy_assignments/",
                            convertToString(storagePolicyAssignmentId)),
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

    public StoragePolicyAssignmentsManager build() {
      return new StoragePolicyAssignmentsManager(this);
    }
  }
}
