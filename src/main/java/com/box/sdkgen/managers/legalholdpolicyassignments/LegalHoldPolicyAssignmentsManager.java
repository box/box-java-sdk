package com.box.sdkgen.managers.legalholdpolicyassignments;

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
import com.box.sdkgen.schemas.filesonhold.FilesOnHold;
import com.box.sdkgen.schemas.legalholdpolicyassignment.LegalHoldPolicyAssignment;
import com.box.sdkgen.schemas.legalholdpolicyassignments.LegalHoldPolicyAssignments;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class LegalHoldPolicyAssignmentsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public LegalHoldPolicyAssignmentsManager() {
    this.networkSession = new NetworkSession();
  }

  protected LegalHoldPolicyAssignmentsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves a list of items a legal hold policy has been assigned to.
   *
   * @param queryParams Query parameters of getLegalHoldPolicyAssignments method
   */
  public LegalHoldPolicyAssignments getLegalHoldPolicyAssignments(
      GetLegalHoldPolicyAssignmentsQueryParams queryParams) {
    return getLegalHoldPolicyAssignments(queryParams, new GetLegalHoldPolicyAssignmentsHeaders());
  }

  /**
   * Retrieves a list of items a legal hold policy has been assigned to.
   *
   * @param queryParams Query parameters of getLegalHoldPolicyAssignments method
   * @param headers Headers of getLegalHoldPolicyAssignments method
   */
  public LegalHoldPolicyAssignments getLegalHoldPolicyAssignments(
      GetLegalHoldPolicyAssignmentsQueryParams queryParams,
      GetLegalHoldPolicyAssignmentsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("policy_id", convertToString(queryParams.getPolicyId())),
                entryOf("assign_to_type", convertToString(queryParams.getAssignToType())),
                entryOf("assign_to_id", convertToString(queryParams.getAssignToId())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/legal_hold_policy_assignments"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), LegalHoldPolicyAssignments.class);
  }

  /**
   * Assign a legal hold to an item type of: file, file version, folder, user, ownership, or
   * interactions.
   *
   * @param requestBody Request body of createLegalHoldPolicyAssignment method
   */
  public LegalHoldPolicyAssignment createLegalHoldPolicyAssignment(
      CreateLegalHoldPolicyAssignmentRequestBody requestBody) {
    return createLegalHoldPolicyAssignment(
        requestBody, new CreateLegalHoldPolicyAssignmentHeaders());
  }

  /**
   * Assign a legal hold to an item type of: file, file version, folder, user, ownership, or
   * interactions.
   *
   * @param requestBody Request body of createLegalHoldPolicyAssignment method
   * @param headers Headers of createLegalHoldPolicyAssignment method
   */
  public LegalHoldPolicyAssignment createLegalHoldPolicyAssignment(
      CreateLegalHoldPolicyAssignmentRequestBody requestBody,
      CreateLegalHoldPolicyAssignmentHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/legal_hold_policy_assignments"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), LegalHoldPolicyAssignment.class);
  }

  /**
   * Retrieve a legal hold policy assignment.
   *
   * @param legalHoldPolicyAssignmentId The ID of the legal hold policy assignment. Example:
   *     "753465"
   */
  public LegalHoldPolicyAssignment getLegalHoldPolicyAssignmentById(
      String legalHoldPolicyAssignmentId) {
    return getLegalHoldPolicyAssignmentById(
        legalHoldPolicyAssignmentId, new GetLegalHoldPolicyAssignmentByIdHeaders());
  }

  /**
   * Retrieve a legal hold policy assignment.
   *
   * @param legalHoldPolicyAssignmentId The ID of the legal hold policy assignment. Example:
   *     "753465"
   * @param headers Headers of getLegalHoldPolicyAssignmentById method
   */
  public LegalHoldPolicyAssignment getLegalHoldPolicyAssignmentById(
      String legalHoldPolicyAssignmentId, GetLegalHoldPolicyAssignmentByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/legal_hold_policy_assignments/",
                            convertToString(legalHoldPolicyAssignmentId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), LegalHoldPolicyAssignment.class);
  }

  /**
   * Remove a legal hold from an item.
   *
   * <p>This is an asynchronous process. The policy will not be fully removed yet when the response
   * returns.
   *
   * @param legalHoldPolicyAssignmentId The ID of the legal hold policy assignment. Example:
   *     "753465"
   */
  public void deleteLegalHoldPolicyAssignmentById(String legalHoldPolicyAssignmentId) {
    deleteLegalHoldPolicyAssignmentById(
        legalHoldPolicyAssignmentId, new DeleteLegalHoldPolicyAssignmentByIdHeaders());
  }

  /**
   * Remove a legal hold from an item.
   *
   * <p>This is an asynchronous process. The policy will not be fully removed yet when the response
   * returns.
   *
   * @param legalHoldPolicyAssignmentId The ID of the legal hold policy assignment. Example:
   *     "753465"
   * @param headers Headers of deleteLegalHoldPolicyAssignmentById method
   */
  public void deleteLegalHoldPolicyAssignmentById(
      String legalHoldPolicyAssignmentId, DeleteLegalHoldPolicyAssignmentByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/legal_hold_policy_assignments/",
                            convertToString(legalHoldPolicyAssignmentId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Get a list of files with current file versions for a legal hold assignment.
   *
   * <p>In some cases you may want to get previous file versions instead. In these cases, use the
   * `GET /legal_hold_policy_assignments/:id/file_versions_on_hold` API instead to return any
   * previous versions of a file for this legal hold policy assignment.
   *
   * <p>Due to ongoing re-architecture efforts this API might not return all file versions held for
   * this policy ID. Instead, this API will only return the latest file version held in the newly
   * developed architecture. The `GET /file_version_legal_holds` API can be used to fetch current
   * and past versions of files held within the legacy architecture.
   *
   * <p>This endpoint does not support returning any content that is on hold due to a Custodian
   * collaborating on a Hub.
   *
   * <p>The `GET /legal_hold_policy_assignments?policy_id={id}` API can be used to find a list of
   * policy assignments for a given policy ID.
   *
   * @param legalHoldPolicyAssignmentId The ID of the legal hold policy assignment. Example:
   *     "753465"
   */
  public FilesOnHold getLegalHoldPolicyAssignmentFileOnHold(String legalHoldPolicyAssignmentId) {
    return getLegalHoldPolicyAssignmentFileOnHold(
        legalHoldPolicyAssignmentId,
        new GetLegalHoldPolicyAssignmentFileOnHoldQueryParams(),
        new GetLegalHoldPolicyAssignmentFileOnHoldHeaders());
  }

  /**
   * Get a list of files with current file versions for a legal hold assignment.
   *
   * <p>In some cases you may want to get previous file versions instead. In these cases, use the
   * `GET /legal_hold_policy_assignments/:id/file_versions_on_hold` API instead to return any
   * previous versions of a file for this legal hold policy assignment.
   *
   * <p>Due to ongoing re-architecture efforts this API might not return all file versions held for
   * this policy ID. Instead, this API will only return the latest file version held in the newly
   * developed architecture. The `GET /file_version_legal_holds` API can be used to fetch current
   * and past versions of files held within the legacy architecture.
   *
   * <p>This endpoint does not support returning any content that is on hold due to a Custodian
   * collaborating on a Hub.
   *
   * <p>The `GET /legal_hold_policy_assignments?policy_id={id}` API can be used to find a list of
   * policy assignments for a given policy ID.
   *
   * @param legalHoldPolicyAssignmentId The ID of the legal hold policy assignment. Example:
   *     "753465"
   * @param queryParams Query parameters of getLegalHoldPolicyAssignmentFileOnHold method
   */
  public FilesOnHold getLegalHoldPolicyAssignmentFileOnHold(
      String legalHoldPolicyAssignmentId,
      GetLegalHoldPolicyAssignmentFileOnHoldQueryParams queryParams) {
    return getLegalHoldPolicyAssignmentFileOnHold(
        legalHoldPolicyAssignmentId,
        queryParams,
        new GetLegalHoldPolicyAssignmentFileOnHoldHeaders());
  }

  /**
   * Get a list of files with current file versions for a legal hold assignment.
   *
   * <p>In some cases you may want to get previous file versions instead. In these cases, use the
   * `GET /legal_hold_policy_assignments/:id/file_versions_on_hold` API instead to return any
   * previous versions of a file for this legal hold policy assignment.
   *
   * <p>Due to ongoing re-architecture efforts this API might not return all file versions held for
   * this policy ID. Instead, this API will only return the latest file version held in the newly
   * developed architecture. The `GET /file_version_legal_holds` API can be used to fetch current
   * and past versions of files held within the legacy architecture.
   *
   * <p>This endpoint does not support returning any content that is on hold due to a Custodian
   * collaborating on a Hub.
   *
   * <p>The `GET /legal_hold_policy_assignments?policy_id={id}` API can be used to find a list of
   * policy assignments for a given policy ID.
   *
   * @param legalHoldPolicyAssignmentId The ID of the legal hold policy assignment. Example:
   *     "753465"
   * @param headers Headers of getLegalHoldPolicyAssignmentFileOnHold method
   */
  public FilesOnHold getLegalHoldPolicyAssignmentFileOnHold(
      String legalHoldPolicyAssignmentId, GetLegalHoldPolicyAssignmentFileOnHoldHeaders headers) {
    return getLegalHoldPolicyAssignmentFileOnHold(
        legalHoldPolicyAssignmentId,
        new GetLegalHoldPolicyAssignmentFileOnHoldQueryParams(),
        headers);
  }

  /**
   * Get a list of files with current file versions for a legal hold assignment.
   *
   * <p>In some cases you may want to get previous file versions instead. In these cases, use the
   * `GET /legal_hold_policy_assignments/:id/file_versions_on_hold` API instead to return any
   * previous versions of a file for this legal hold policy assignment.
   *
   * <p>Due to ongoing re-architecture efforts this API might not return all file versions held for
   * this policy ID. Instead, this API will only return the latest file version held in the newly
   * developed architecture. The `GET /file_version_legal_holds` API can be used to fetch current
   * and past versions of files held within the legacy architecture.
   *
   * <p>This endpoint does not support returning any content that is on hold due to a Custodian
   * collaborating on a Hub.
   *
   * <p>The `GET /legal_hold_policy_assignments?policy_id={id}` API can be used to find a list of
   * policy assignments for a given policy ID.
   *
   * @param legalHoldPolicyAssignmentId The ID of the legal hold policy assignment. Example:
   *     "753465"
   * @param queryParams Query parameters of getLegalHoldPolicyAssignmentFileOnHold method
   * @param headers Headers of getLegalHoldPolicyAssignmentFileOnHold method
   */
  public FilesOnHold getLegalHoldPolicyAssignmentFileOnHold(
      String legalHoldPolicyAssignmentId,
      GetLegalHoldPolicyAssignmentFileOnHoldQueryParams queryParams,
      GetLegalHoldPolicyAssignmentFileOnHoldHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/legal_hold_policy_assignments/",
                            convertToString(legalHoldPolicyAssignmentId),
                            "/files_on_hold"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FilesOnHold.class);
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

    public LegalHoldPolicyAssignmentsManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new LegalHoldPolicyAssignmentsManager(this);
    }
  }
}
