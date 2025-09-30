package com.box.sdkgen.managers.retentionpolicyassignments;

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
import com.box.sdkgen.schemas.filesunderretention.FilesUnderRetention;
import com.box.sdkgen.schemas.retentionpolicyassignment.RetentionPolicyAssignment;
import com.box.sdkgen.schemas.retentionpolicyassignments.RetentionPolicyAssignments;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class RetentionPolicyAssignmentsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public RetentionPolicyAssignmentsManager() {
    this.networkSession = new NetworkSession();
  }

  protected RetentionPolicyAssignmentsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public RetentionPolicyAssignments getRetentionPolicyAssignments(String retentionPolicyId) {
    return getRetentionPolicyAssignments(
        retentionPolicyId,
        new GetRetentionPolicyAssignmentsQueryParams(),
        new GetRetentionPolicyAssignmentsHeaders());
  }

  public RetentionPolicyAssignments getRetentionPolicyAssignments(
      String retentionPolicyId, GetRetentionPolicyAssignmentsQueryParams queryParams) {
    return getRetentionPolicyAssignments(
        retentionPolicyId, queryParams, new GetRetentionPolicyAssignmentsHeaders());
  }

  public RetentionPolicyAssignments getRetentionPolicyAssignments(
      String retentionPolicyId, GetRetentionPolicyAssignmentsHeaders headers) {
    return getRetentionPolicyAssignments(
        retentionPolicyId, new GetRetentionPolicyAssignmentsQueryParams(), headers);
  }

  public RetentionPolicyAssignments getRetentionPolicyAssignments(
      String retentionPolicyId,
      GetRetentionPolicyAssignmentsQueryParams queryParams,
      GetRetentionPolicyAssignmentsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("type", convertToString(queryParams.getType())),
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("marker", convertToString(queryParams.getMarker())),
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
                            "/2.0/retention_policies/",
                            convertToString(retentionPolicyId),
                            "/assignments"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), RetentionPolicyAssignments.class);
  }

  public RetentionPolicyAssignment createRetentionPolicyAssignment(
      CreateRetentionPolicyAssignmentRequestBody requestBody) {
    return createRetentionPolicyAssignment(
        requestBody, new CreateRetentionPolicyAssignmentHeaders());
  }

  public RetentionPolicyAssignment createRetentionPolicyAssignment(
      CreateRetentionPolicyAssignmentRequestBody requestBody,
      CreateRetentionPolicyAssignmentHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/retention_policy_assignments"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), RetentionPolicyAssignment.class);
  }

  public RetentionPolicyAssignment getRetentionPolicyAssignmentById(
      String retentionPolicyAssignmentId) {
    return getRetentionPolicyAssignmentById(
        retentionPolicyAssignmentId,
        new GetRetentionPolicyAssignmentByIdQueryParams(),
        new GetRetentionPolicyAssignmentByIdHeaders());
  }

  public RetentionPolicyAssignment getRetentionPolicyAssignmentById(
      String retentionPolicyAssignmentId, GetRetentionPolicyAssignmentByIdQueryParams queryParams) {
    return getRetentionPolicyAssignmentById(
        retentionPolicyAssignmentId, queryParams, new GetRetentionPolicyAssignmentByIdHeaders());
  }

  public RetentionPolicyAssignment getRetentionPolicyAssignmentById(
      String retentionPolicyAssignmentId, GetRetentionPolicyAssignmentByIdHeaders headers) {
    return getRetentionPolicyAssignmentById(
        retentionPolicyAssignmentId, new GetRetentionPolicyAssignmentByIdQueryParams(), headers);
  }

  public RetentionPolicyAssignment getRetentionPolicyAssignmentById(
      String retentionPolicyAssignmentId,
      GetRetentionPolicyAssignmentByIdQueryParams queryParams,
      GetRetentionPolicyAssignmentByIdHeaders headers) {
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
                            "/2.0/retention_policy_assignments/",
                            convertToString(retentionPolicyAssignmentId)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), RetentionPolicyAssignment.class);
  }

  public void deleteRetentionPolicyAssignmentById(String retentionPolicyAssignmentId) {
    deleteRetentionPolicyAssignmentById(
        retentionPolicyAssignmentId, new DeleteRetentionPolicyAssignmentByIdHeaders());
  }

  public void deleteRetentionPolicyAssignmentById(
      String retentionPolicyAssignmentId, DeleteRetentionPolicyAssignmentByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/retention_policy_assignments/",
                            convertToString(retentionPolicyAssignmentId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  public FilesUnderRetention getFilesUnderRetentionPolicyAssignment(
      String retentionPolicyAssignmentId) {
    return getFilesUnderRetentionPolicyAssignment(
        retentionPolicyAssignmentId,
        new GetFilesUnderRetentionPolicyAssignmentQueryParams(),
        new GetFilesUnderRetentionPolicyAssignmentHeaders());
  }

  public FilesUnderRetention getFilesUnderRetentionPolicyAssignment(
      String retentionPolicyAssignmentId,
      GetFilesUnderRetentionPolicyAssignmentQueryParams queryParams) {
    return getFilesUnderRetentionPolicyAssignment(
        retentionPolicyAssignmentId,
        queryParams,
        new GetFilesUnderRetentionPolicyAssignmentHeaders());
  }

  public FilesUnderRetention getFilesUnderRetentionPolicyAssignment(
      String retentionPolicyAssignmentId, GetFilesUnderRetentionPolicyAssignmentHeaders headers) {
    return getFilesUnderRetentionPolicyAssignment(
        retentionPolicyAssignmentId,
        new GetFilesUnderRetentionPolicyAssignmentQueryParams(),
        headers);
  }

  public FilesUnderRetention getFilesUnderRetentionPolicyAssignment(
      String retentionPolicyAssignmentId,
      GetFilesUnderRetentionPolicyAssignmentQueryParams queryParams,
      GetFilesUnderRetentionPolicyAssignmentHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("marker", convertToString(queryParams.getMarker())),
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
                            "/2.0/retention_policy_assignments/",
                            convertToString(retentionPolicyAssignmentId),
                            "/files_under_retention"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FilesUnderRetention.class);
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

    public RetentionPolicyAssignmentsManager build() {
      return new RetentionPolicyAssignmentsManager(this);
    }
  }
}
