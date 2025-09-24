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

  public StoragePolicyAssignments getStoragePolicyAssignments(
      GetStoragePolicyAssignmentsQueryParams queryParams) {
    return getStoragePolicyAssignments(queryParams, new GetStoragePolicyAssignmentsHeaders());
  }

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

  public StoragePolicyAssignment createStoragePolicyAssignment(
      CreateStoragePolicyAssignmentRequestBody requestBody) {
    return createStoragePolicyAssignment(requestBody, new CreateStoragePolicyAssignmentHeaders());
  }

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

  public StoragePolicyAssignment getStoragePolicyAssignmentById(String storagePolicyAssignmentId) {
    return getStoragePolicyAssignmentById(
        storagePolicyAssignmentId, new GetStoragePolicyAssignmentByIdHeaders());
  }

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

  public StoragePolicyAssignment updateStoragePolicyAssignmentById(
      String storagePolicyAssignmentId, UpdateStoragePolicyAssignmentByIdRequestBody requestBody) {
    return updateStoragePolicyAssignmentById(
        storagePolicyAssignmentId, requestBody, new UpdateStoragePolicyAssignmentByIdHeaders());
  }

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

  public void deleteStoragePolicyAssignmentById(String storagePolicyAssignmentId) {
    deleteStoragePolicyAssignmentById(
        storagePolicyAssignmentId, new DeleteStoragePolicyAssignmentByIdHeaders());
  }

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
