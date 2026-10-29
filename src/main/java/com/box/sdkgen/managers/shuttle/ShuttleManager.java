package com.box.sdkgen.managers.shuttle;

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
import com.box.sdkgen.schemas.v2026r0.shuttleagentcredentialsv2026r0.ShuttleAgentCredentialsV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlediscoveredsubjectsv2026r0.ShuttleDiscoveredSubjectsV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobcreaterequestv2026r0.ShuttleJobCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobschedulecreaterequestv2026r0.ShuttleJobScheduleCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobschedulesv2026r0.ShuttleJobSchedulesV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobscheduleupdaterequestv2026r0.ShuttleJobScheduleUpdateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobschedulev2026r0.ShuttleJobScheduleV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobstartrequestv2026r0.ShuttleJobStartRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobsv2026r0.ShuttleJobsV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobupdaterequestv2026r0.ShuttleJobUpdateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobv2026r0.ShuttleJobV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlemappingerrorsv2026r0.ShuttleMappingErrorsV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathentriesv2026r0.ShuttlePathEntriesV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingcreaterequestv2026r0.ShuttlePathMappingCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingimportcreaterequestv2026r0.ShuttlePathMappingImportCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingsv2026r0.ShuttlePathMappingsV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingupdaterequestv2026r0.ShuttlePathMappingUpdateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingv2026r0.ShuttlePathMappingV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlereportcreaterequestv2026r0.ShuttleReportCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlereportsv2026r0.ShuttleReportsV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlereportv2026r0.ShuttleReportV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlerunsv2026r0.ShuttleRunsV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlerunv2026r0.ShuttleRunV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlesubjectmappingcreaterequestv2026r0.ShuttleSubjectMappingCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlesubjectmappingimportcreaterequestv2026r0.ShuttleSubjectMappingImportCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlesubjectmappingsv2026r0.ShuttleSubjectMappingsV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlesubjectmappingupdaterequestv2026r0.ShuttleSubjectMappingUpdateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlesubjectmappingv2026r0.ShuttleSubjectMappingV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlesystemcreaterequestv2026r0.ShuttleSystemCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlesystemsv2026r0.ShuttleSystemsV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlesystemupdaterequestv2026r0.ShuttleSystemUpdateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlesystemv2026r0.ShuttleSystemV2026R0;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class ShuttleManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public ShuttleManager() {
    this.networkSession = new NetworkSession();
  }

  protected ShuttleManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /** Returns a paginated list of shuttle systems accessible to the authenticated user. */
  public ShuttleSystemsV2026R0 getShuttleSystemsV2026R0() {
    return getShuttleSystemsV2026R0(
        new GetShuttleSystemsV2026R0QueryParams(), new GetShuttleSystemsV2026R0Headers());
  }

  /**
   * Returns a paginated list of shuttle systems accessible to the authenticated user.
   *
   * @param queryParams Query parameters of getShuttleSystemsV2026R0 method
   */
  public ShuttleSystemsV2026R0 getShuttleSystemsV2026R0(
      GetShuttleSystemsV2026R0QueryParams queryParams) {
    return getShuttleSystemsV2026R0(queryParams, new GetShuttleSystemsV2026R0Headers());
  }

  /**
   * Returns a paginated list of shuttle systems accessible to the authenticated user.
   *
   * @param headers Headers of getShuttleSystemsV2026R0 method
   */
  public ShuttleSystemsV2026R0 getShuttleSystemsV2026R0(GetShuttleSystemsV2026R0Headers headers) {
    return getShuttleSystemsV2026R0(new GetShuttleSystemsV2026R0QueryParams(), headers);
  }

  /**
   * Returns a paginated list of shuttle systems accessible to the authenticated user.
   *
   * @param queryParams Query parameters of getShuttleSystemsV2026R0 method
   * @param headers Headers of getShuttleSystemsV2026R0 method
   */
  public ShuttleSystemsV2026R0 getShuttleSystemsV2026R0(
      GetShuttleSystemsV2026R0QueryParams queryParams, GetShuttleSystemsV2026R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_systems"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleSystemsV2026R0.class);
  }

  /**
   * Creates a new shuttle system. Only Windows agent systems can be created through the public API;
   * other system types must be configured through the Shuttle application.
   *
   * @param requestBody Request body of createShuttleSystemV2026R0 method
   */
  public ShuttleSystemV2026R0 createShuttleSystemV2026R0(
      ShuttleSystemCreateRequestV2026R0 requestBody) {
    return createShuttleSystemV2026R0(requestBody, new CreateShuttleSystemV2026R0Headers());
  }

  /**
   * Creates a new shuttle system. Only Windows agent systems can be created through the public API;
   * other system types must be configured through the Shuttle application.
   *
   * @param requestBody Request body of createShuttleSystemV2026R0 method
   * @param headers Headers of createShuttleSystemV2026R0 method
   */
  public ShuttleSystemV2026R0 createShuttleSystemV2026R0(
      ShuttleSystemCreateRequestV2026R0 requestBody, CreateShuttleSystemV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_systems"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleSystemV2026R0.class);
  }

  /**
   * Returns details for a single shuttle system identified by its ID, including its type and
   * connection state.
   *
   * @param shuttleSystemId The unique identifier for the shuttle system. Example:
   *     "6a315c0754833bfc5df067bf"
   */
  public ShuttleSystemV2026R0 getShuttleSystemByIdV2026R0(String shuttleSystemId) {
    return getShuttleSystemByIdV2026R0(shuttleSystemId, new GetShuttleSystemByIdV2026R0Headers());
  }

  /**
   * Returns details for a single shuttle system identified by its ID, including its type and
   * connection state.
   *
   * @param shuttleSystemId The unique identifier for the shuttle system. Example:
   *     "6a315c0754833bfc5df067bf"
   * @param headers Headers of getShuttleSystemByIdV2026R0 method
   */
  public ShuttleSystemV2026R0 getShuttleSystemByIdV2026R0(
      String shuttleSystemId, GetShuttleSystemByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_systems/",
                            convertToString(shuttleSystemId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleSystemV2026R0.class);
  }

  /**
   * Updates the mutable properties of an existing shuttle system identified by its ID. Only the
   * fields supplied in the request body are changed.
   *
   * @param shuttleSystemId The unique identifier for the shuttle system. Example:
   *     "6a315c0754833bfc5df067bf"
   * @param requestBody Request body of updateShuttleSystemByIdV2026R0 method
   */
  public ShuttleSystemV2026R0 updateShuttleSystemByIdV2026R0(
      String shuttleSystemId, ShuttleSystemUpdateRequestV2026R0 requestBody) {
    return updateShuttleSystemByIdV2026R0(
        shuttleSystemId, requestBody, new UpdateShuttleSystemByIdV2026R0Headers());
  }

  /**
   * Updates the mutable properties of an existing shuttle system identified by its ID. Only the
   * fields supplied in the request body are changed.
   *
   * @param shuttleSystemId The unique identifier for the shuttle system. Example:
   *     "6a315c0754833bfc5df067bf"
   * @param requestBody Request body of updateShuttleSystemByIdV2026R0 method
   * @param headers Headers of updateShuttleSystemByIdV2026R0 method
   */
  public ShuttleSystemV2026R0 updateShuttleSystemByIdV2026R0(
      String shuttleSystemId,
      ShuttleSystemUpdateRequestV2026R0 requestBody,
      UpdateShuttleSystemByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_systems/",
                            convertToString(shuttleSystemId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleSystemV2026R0.class);
  }

  /**
   * Permanently deletes the shuttle system identified by its ID. This does not affect content that
   * has already been migrated.
   *
   * @param shuttleSystemId The unique identifier for the shuttle system. Example:
   *     "6a315c0754833bfc5df067bf"
   */
  public void deleteShuttleSystemByIdV2026R0(String shuttleSystemId) {
    deleteShuttleSystemByIdV2026R0(shuttleSystemId, new DeleteShuttleSystemByIdV2026R0Headers());
  }

  /**
   * Permanently deletes the shuttle system identified by its ID. This does not affect content that
   * has already been migrated.
   *
   * @param shuttleSystemId The unique identifier for the shuttle system. Example:
   *     "6a315c0754833bfc5df067bf"
   * @param headers Headers of deleteShuttleSystemByIdV2026R0 method
   */
  public void deleteShuttleSystemByIdV2026R0(
      String shuttleSystemId, DeleteShuttleSystemByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_systems/",
                            convertToString(shuttleSystemId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Issues a new installation token for the Windows shuttle system identified by its ID. Paste the
   * returned token into the Windows agent installer to register the agent with this system.
   *
   * @param shuttleSystemId The unique identifier for the shuttle system. Example:
   *     "6a315c0754833bfc5df067bf"
   */
  public ShuttleAgentCredentialsV2026R0 createShuttleSystemIssueRegistrationTokenV2026R0(
      String shuttleSystemId) {
    return createShuttleSystemIssueRegistrationTokenV2026R0(
        shuttleSystemId, new CreateShuttleSystemIssueRegistrationTokenV2026R0Headers());
  }

  /**
   * Issues a new installation token for the Windows shuttle system identified by its ID. Paste the
   * returned token into the Windows agent installer to register the agent with this system.
   *
   * @param shuttleSystemId The unique identifier for the shuttle system. Example:
   *     "6a315c0754833bfc5df067bf"
   * @param headers Headers of createShuttleSystemIssueRegistrationTokenV2026R0 method
   */
  public ShuttleAgentCredentialsV2026R0 createShuttleSystemIssueRegistrationTokenV2026R0(
      String shuttleSystemId, CreateShuttleSystemIssueRegistrationTokenV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_systems/",
                            convertToString(shuttleSystemId),
                            "/issue_registration_token"),
                        "POST")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleAgentCredentialsV2026R0.class);
  }

  /** Returns a paginated list of shuttle jobs accessible to the authenticated user. */
  public ShuttleJobsV2026R0 getShuttleJobsV2026R0() {
    return getShuttleJobsV2026R0(
        new GetShuttleJobsV2026R0QueryParams(), new GetShuttleJobsV2026R0Headers());
  }

  /**
   * Returns a paginated list of shuttle jobs accessible to the authenticated user.
   *
   * @param queryParams Query parameters of getShuttleJobsV2026R0 method
   */
  public ShuttleJobsV2026R0 getShuttleJobsV2026R0(GetShuttleJobsV2026R0QueryParams queryParams) {
    return getShuttleJobsV2026R0(queryParams, new GetShuttleJobsV2026R0Headers());
  }

  /**
   * Returns a paginated list of shuttle jobs accessible to the authenticated user.
   *
   * @param headers Headers of getShuttleJobsV2026R0 method
   */
  public ShuttleJobsV2026R0 getShuttleJobsV2026R0(GetShuttleJobsV2026R0Headers headers) {
    return getShuttleJobsV2026R0(new GetShuttleJobsV2026R0QueryParams(), headers);
  }

  /**
   * Returns a paginated list of shuttle jobs accessible to the authenticated user.
   *
   * @param queryParams Query parameters of getShuttleJobsV2026R0 method
   * @param headers Headers of getShuttleJobsV2026R0 method
   */
  public ShuttleJobsV2026R0 getShuttleJobsV2026R0(
      GetShuttleJobsV2026R0QueryParams queryParams, GetShuttleJobsV2026R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_jobs"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleJobsV2026R0.class);
  }

  /**
   * Creates a new shuttle job that describes a migration from the given source system into Box.
   *
   * @param requestBody Request body of createShuttleJobV2026R0 method
   */
  public ShuttleJobV2026R0 createShuttleJobV2026R0(ShuttleJobCreateRequestV2026R0 requestBody) {
    return createShuttleJobV2026R0(requestBody, new CreateShuttleJobV2026R0Headers());
  }

  /**
   * Creates a new shuttle job that describes a migration from the given source system into Box.
   *
   * @param requestBody Request body of createShuttleJobV2026R0 method
   * @param headers Headers of createShuttleJobV2026R0 method
   */
  public ShuttleJobV2026R0 createShuttleJobV2026R0(
      ShuttleJobCreateRequestV2026R0 requestBody, CreateShuttleJobV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_jobs"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleJobV2026R0.class);
  }

  /**
   * Returns details for a single shuttle job identified by its ID, including its current state,
   * mapping-preparation state, and configured source system.
   *
   * @param shuttleJobId The unique identifier for the shuttle job. Example:
   *     "6a3ba68e810d9156194530a1"
   */
  public ShuttleJobV2026R0 getShuttleJobByIdV2026R0(String shuttleJobId) {
    return getShuttleJobByIdV2026R0(shuttleJobId, new GetShuttleJobByIdV2026R0Headers());
  }

  /**
   * Returns details for a single shuttle job identified by its ID, including its current state,
   * mapping-preparation state, and configured source system.
   *
   * @param shuttleJobId The unique identifier for the shuttle job. Example:
   *     "6a3ba68e810d9156194530a1"
   * @param headers Headers of getShuttleJobByIdV2026R0 method
   */
  public ShuttleJobV2026R0 getShuttleJobByIdV2026R0(
      String shuttleJobId, GetShuttleJobByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_jobs/",
                            convertToString(shuttleJobId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleJobV2026R0.class);
  }

  /**
   * Updates the mutable properties of an existing shuttle job identified by its ID. Only the fields
   * supplied in the request body are changed.
   *
   * @param shuttleJobId The unique identifier for the shuttle job. Example:
   *     "6a3ba68e810d9156194530a1"
   * @param requestBody Request body of updateShuttleJobByIdV2026R0 method
   */
  public ShuttleJobV2026R0 updateShuttleJobByIdV2026R0(
      String shuttleJobId, ShuttleJobUpdateRequestV2026R0 requestBody) {
    return updateShuttleJobByIdV2026R0(
        shuttleJobId, requestBody, new UpdateShuttleJobByIdV2026R0Headers());
  }

  /**
   * Updates the mutable properties of an existing shuttle job identified by its ID. Only the fields
   * supplied in the request body are changed.
   *
   * @param shuttleJobId The unique identifier for the shuttle job. Example:
   *     "6a3ba68e810d9156194530a1"
   * @param requestBody Request body of updateShuttleJobByIdV2026R0 method
   * @param headers Headers of updateShuttleJobByIdV2026R0 method
   */
  public ShuttleJobV2026R0 updateShuttleJobByIdV2026R0(
      String shuttleJobId,
      ShuttleJobUpdateRequestV2026R0 requestBody,
      UpdateShuttleJobByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_jobs/",
                            convertToString(shuttleJobId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleJobV2026R0.class);
  }

  /**
   * Permanently deletes the shuttle job identified by its ID. This does not affect content that has
   * already been migrated.
   *
   * @param shuttleJobId The unique identifier for the shuttle job. Example:
   *     "6a3ba68e810d9156194530a1"
   */
  public void deleteShuttleJobByIdV2026R0(String shuttleJobId) {
    deleteShuttleJobByIdV2026R0(shuttleJobId, new DeleteShuttleJobByIdV2026R0Headers());
  }

  /**
   * Permanently deletes the shuttle job identified by its ID. This does not affect content that has
   * already been migrated.
   *
   * @param shuttleJobId The unique identifier for the shuttle job. Example:
   *     "6a3ba68e810d9156194530a1"
   * @param headers Headers of deleteShuttleJobByIdV2026R0 method
   */
  public void deleteShuttleJobByIdV2026R0(
      String shuttleJobId, DeleteShuttleJobByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_jobs/",
                            convertToString(shuttleJobId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Starts the job in the given mode. When no body is provided the job runs in simulation mode.
   *
   * @param shuttleJobId The unique identifier for the shuttle job. Example:
   *     "6a3ba68e810d9156194530a1"
   */
  public void createShuttleJobStartV2026R0(String shuttleJobId) {
    createShuttleJobStartV2026R0(shuttleJobId, null, new CreateShuttleJobStartV2026R0Headers());
  }

  /**
   * Starts the job in the given mode. When no body is provided the job runs in simulation mode.
   *
   * @param shuttleJobId The unique identifier for the shuttle job. Example:
   *     "6a3ba68e810d9156194530a1"
   * @param requestBody Request body of createShuttleJobStartV2026R0 method
   */
  public void createShuttleJobStartV2026R0(
      String shuttleJobId, ShuttleJobStartRequestV2026R0 requestBody) {
    createShuttleJobStartV2026R0(
        shuttleJobId, requestBody, new CreateShuttleJobStartV2026R0Headers());
  }

  /**
   * Starts the job in the given mode. When no body is provided the job runs in simulation mode.
   *
   * @param shuttleJobId The unique identifier for the shuttle job. Example:
   *     "6a3ba68e810d9156194530a1"
   * @param headers Headers of createShuttleJobStartV2026R0 method
   */
  public void createShuttleJobStartV2026R0(
      String shuttleJobId, CreateShuttleJobStartV2026R0Headers headers) {
    createShuttleJobStartV2026R0(shuttleJobId, null, headers);
  }

  /**
   * Starts the job in the given mode. When no body is provided the job runs in simulation mode.
   *
   * @param shuttleJobId The unique identifier for the shuttle job. Example:
   *     "6a3ba68e810d9156194530a1"
   * @param requestBody Request body of createShuttleJobStartV2026R0 method
   * @param headers Headers of createShuttleJobStartV2026R0 method
   */
  public void createShuttleJobStartV2026R0(
      String shuttleJobId,
      ShuttleJobStartRequestV2026R0 requestBody,
      CreateShuttleJobStartV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_jobs/",
                            convertToString(shuttleJobId),
                            "/start"),
                        "POST")
                    .headers(headersMap)
                    .data((!(requestBody == null) ? JsonManager.serialize(requestBody) : null))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Stops a running shuttle job identified by its ID. Any work already in progress is allowed to
   * finish before the job transitions to a stopped state.
   *
   * @param shuttleJobId The unique identifier for the shuttle job. Example:
   *     "6a3ba68e810d9156194530a1"
   */
  public void createShuttleJobStopV2026R0(String shuttleJobId) {
    createShuttleJobStopV2026R0(shuttleJobId, new CreateShuttleJobStopV2026R0Headers());
  }

  /**
   * Stops a running shuttle job identified by its ID. Any work already in progress is allowed to
   * finish before the job transitions to a stopped state.
   *
   * @param shuttleJobId The unique identifier for the shuttle job. Example:
   *     "6a3ba68e810d9156194530a1"
   * @param headers Headers of createShuttleJobStopV2026R0 method
   */
  public void createShuttleJobStopV2026R0(
      String shuttleJobId, CreateShuttleJobStopV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_jobs/",
                            convertToString(shuttleJobId),
                            "/stop"),
                        "POST")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Validates the configured account and path mappings before a transfer. Only relevant when
   * account mapping is enabled.
   *
   * @param shuttleJobId The unique identifier for the shuttle job. Example:
   *     "6a3ba68e810d9156194530a1"
   */
  public void createShuttleJobPrepareMigrationV2026R0(String shuttleJobId) {
    createShuttleJobPrepareMigrationV2026R0(
        shuttleJobId, new CreateShuttleJobPrepareMigrationV2026R0Headers());
  }

  /**
   * Validates the configured account and path mappings before a transfer. Only relevant when
   * account mapping is enabled.
   *
   * @param shuttleJobId The unique identifier for the shuttle job. Example:
   *     "6a3ba68e810d9156194530a1"
   * @param headers Headers of createShuttleJobPrepareMigrationV2026R0 method
   */
  public void createShuttleJobPrepareMigrationV2026R0(
      String shuttleJobId, CreateShuttleJobPrepareMigrationV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_jobs/",
                            convertToString(shuttleJobId),
                            "/prepare_migration"),
                        "POST")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Returns a paginated list of runs for the shuttle job identified by `job_id`.
   *
   * @param queryParams Query parameters of getShuttleRunsV2026R0 method
   */
  public ShuttleRunsV2026R0 getShuttleRunsV2026R0(GetShuttleRunsV2026R0QueryParams queryParams) {
    return getShuttleRunsV2026R0(queryParams, new GetShuttleRunsV2026R0Headers());
  }

  /**
   * Returns a paginated list of runs for the shuttle job identified by `job_id`.
   *
   * @param queryParams Query parameters of getShuttleRunsV2026R0 method
   * @param headers Headers of getShuttleRunsV2026R0 method
   */
  public ShuttleRunsV2026R0 getShuttleRunsV2026R0(
      GetShuttleRunsV2026R0QueryParams queryParams, GetShuttleRunsV2026R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("job_id", convertToString(queryParams.getJobId())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_runs"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleRunsV2026R0.class);
  }

  /**
   * Returns details for a single shuttle run identified by its ID, including its mode, state,
   * timing, and transfer statistics.
   *
   * @param shuttleRunId The unique identifier for the shuttle run, in the form
   *     `{job_id}:{run_number}`. Example: "6a3ba68e810d9156194530a1:42"
   */
  public ShuttleRunV2026R0 getShuttleRunByIdV2026R0(String shuttleRunId) {
    return getShuttleRunByIdV2026R0(shuttleRunId, new GetShuttleRunByIdV2026R0Headers());
  }

  /**
   * Returns details for a single shuttle run identified by its ID, including its mode, state,
   * timing, and transfer statistics.
   *
   * @param shuttleRunId The unique identifier for the shuttle run, in the form
   *     `{job_id}:{run_number}`. Example: "6a3ba68e810d9156194530a1:42"
   * @param headers Headers of getShuttleRunByIdV2026R0 method
   */
  public ShuttleRunV2026R0 getShuttleRunByIdV2026R0(
      String shuttleRunId, GetShuttleRunByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_runs/",
                            convertToString(shuttleRunId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleRunV2026R0.class);
  }

  /**
   * Returns a paginated list of schedules for the shuttle job identified by `job_id`.
   *
   * @param queryParams Query parameters of getShuttleJobSchedulesV2026R0 method
   */
  public ShuttleJobSchedulesV2026R0 getShuttleJobSchedulesV2026R0(
      GetShuttleJobSchedulesV2026R0QueryParams queryParams) {
    return getShuttleJobSchedulesV2026R0(queryParams, new GetShuttleJobSchedulesV2026R0Headers());
  }

  /**
   * Returns a paginated list of schedules for the shuttle job identified by `job_id`.
   *
   * @param queryParams Query parameters of getShuttleJobSchedulesV2026R0 method
   * @param headers Headers of getShuttleJobSchedulesV2026R0 method
   */
  public ShuttleJobSchedulesV2026R0 getShuttleJobSchedulesV2026R0(
      GetShuttleJobSchedulesV2026R0QueryParams queryParams,
      GetShuttleJobSchedulesV2026R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("job_id", convertToString(queryParams.getJobId())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_job_schedules"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleJobSchedulesV2026R0.class);
  }

  /**
   * Creates a schedule that starts runs of the shuttle job identified by `job_id`. The schedule
   * starts a run either at a wall-clock time or after another job's run meets a condition.
   * Schedules cannot start a run in `scan` mode.
   *
   * @param requestBody Request body of createShuttleJobScheduleV2026R0 method
   */
  public ShuttleJobScheduleV2026R0 createShuttleJobScheduleV2026R0(
      ShuttleJobScheduleCreateRequestV2026R0 requestBody) {
    return createShuttleJobScheduleV2026R0(
        requestBody, new CreateShuttleJobScheduleV2026R0Headers());
  }

  /**
   * Creates a schedule that starts runs of the shuttle job identified by `job_id`. The schedule
   * starts a run either at a wall-clock time or after another job's run meets a condition.
   * Schedules cannot start a run in `scan` mode.
   *
   * @param requestBody Request body of createShuttleJobScheduleV2026R0 method
   * @param headers Headers of createShuttleJobScheduleV2026R0 method
   */
  public ShuttleJobScheduleV2026R0 createShuttleJobScheduleV2026R0(
      ShuttleJobScheduleCreateRequestV2026R0 requestBody,
      CreateShuttleJobScheduleV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_job_schedules"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleJobScheduleV2026R0.class);
  }

  /**
   * Retrieves the shuttle job schedule identified by its ID.
   *
   * @param shuttleJobScheduleId The unique identifier for the shuttle job schedule. Example:
   *     "7c1ba68e810d9156194530b2"
   */
  public ShuttleJobScheduleV2026R0 getShuttleJobScheduleByIdV2026R0(String shuttleJobScheduleId) {
    return getShuttleJobScheduleByIdV2026R0(
        shuttleJobScheduleId, new GetShuttleJobScheduleByIdV2026R0Headers());
  }

  /**
   * Retrieves the shuttle job schedule identified by its ID.
   *
   * @param shuttleJobScheduleId The unique identifier for the shuttle job schedule. Example:
   *     "7c1ba68e810d9156194530b2"
   * @param headers Headers of getShuttleJobScheduleByIdV2026R0 method
   */
  public ShuttleJobScheduleV2026R0 getShuttleJobScheduleByIdV2026R0(
      String shuttleJobScheduleId, GetShuttleJobScheduleByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_job_schedules/",
                            convertToString(shuttleJobScheduleId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleJobScheduleV2026R0.class);
  }

  /**
   * Updates the mutable properties of an existing shuttle job schedule identified by its ID. Only
   * the fields supplied in the request body are changed. The owning job cannot be changed.
   *
   * @param shuttleJobScheduleId The unique identifier for the shuttle job schedule. Example:
   *     "7c1ba68e810d9156194530b2"
   * @param requestBody Request body of updateShuttleJobScheduleByIdV2026R0 method
   */
  public ShuttleJobScheduleV2026R0 updateShuttleJobScheduleByIdV2026R0(
      String shuttleJobScheduleId, ShuttleJobScheduleUpdateRequestV2026R0 requestBody) {
    return updateShuttleJobScheduleByIdV2026R0(
        shuttleJobScheduleId, requestBody, new UpdateShuttleJobScheduleByIdV2026R0Headers());
  }

  /**
   * Updates the mutable properties of an existing shuttle job schedule identified by its ID. Only
   * the fields supplied in the request body are changed. The owning job cannot be changed.
   *
   * @param shuttleJobScheduleId The unique identifier for the shuttle job schedule. Example:
   *     "7c1ba68e810d9156194530b2"
   * @param requestBody Request body of updateShuttleJobScheduleByIdV2026R0 method
   * @param headers Headers of updateShuttleJobScheduleByIdV2026R0 method
   */
  public ShuttleJobScheduleV2026R0 updateShuttleJobScheduleByIdV2026R0(
      String shuttleJobScheduleId,
      ShuttleJobScheduleUpdateRequestV2026R0 requestBody,
      UpdateShuttleJobScheduleByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_job_schedules/",
                            convertToString(shuttleJobScheduleId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleJobScheduleV2026R0.class);
  }

  /**
   * Permanently deletes the shuttle job schedule identified by its ID. This does not affect runs
   * that have already started.
   *
   * @param shuttleJobScheduleId The unique identifier for the shuttle job schedule. Example:
   *     "7c1ba68e810d9156194530b2"
   */
  public void deleteShuttleJobScheduleByIdV2026R0(String shuttleJobScheduleId) {
    deleteShuttleJobScheduleByIdV2026R0(
        shuttleJobScheduleId, new DeleteShuttleJobScheduleByIdV2026R0Headers());
  }

  /**
   * Permanently deletes the shuttle job schedule identified by its ID. This does not affect runs
   * that have already started.
   *
   * @param shuttleJobScheduleId The unique identifier for the shuttle job schedule. Example:
   *     "7c1ba68e810d9156194530b2"
   * @param headers Headers of deleteShuttleJobScheduleByIdV2026R0 method
   */
  public void deleteShuttleJobScheduleByIdV2026R0(
      String shuttleJobScheduleId, DeleteShuttleJobScheduleByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_job_schedules/",
                            convertToString(shuttleJobScheduleId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Returns a paginated list of path mappings for the shuttle job identified by `job_id`.
   *
   * @param queryParams Query parameters of getShuttlePathMappingsV2026R0 method
   */
  public ShuttlePathMappingsV2026R0 getShuttlePathMappingsV2026R0(
      GetShuttlePathMappingsV2026R0QueryParams queryParams) {
    return getShuttlePathMappingsV2026R0(queryParams, new GetShuttlePathMappingsV2026R0Headers());
  }

  /**
   * Returns a paginated list of path mappings for the shuttle job identified by `job_id`.
   *
   * @param queryParams Query parameters of getShuttlePathMappingsV2026R0 method
   * @param headers Headers of getShuttlePathMappingsV2026R0 method
   */
  public ShuttlePathMappingsV2026R0 getShuttlePathMappingsV2026R0(
      GetShuttlePathMappingsV2026R0QueryParams queryParams,
      GetShuttlePathMappingsV2026R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("job_id", convertToString(queryParams.getJobId())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_path_mappings"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttlePathMappingsV2026R0.class);
  }

  /**
   * Creates a path mapping that connects a source location on the migrated system to a destination
   * in Box for the given shuttle job.
   *
   * @param requestBody Request body of createShuttlePathMappingV2026R0 method
   */
  public ShuttlePathMappingV2026R0 createShuttlePathMappingV2026R0(
      ShuttlePathMappingCreateRequestV2026R0 requestBody) {
    return createShuttlePathMappingV2026R0(
        requestBody, new CreateShuttlePathMappingV2026R0Headers());
  }

  /**
   * Creates a path mapping that connects a source location on the migrated system to a destination
   * in Box for the given shuttle job.
   *
   * @param requestBody Request body of createShuttlePathMappingV2026R0 method
   * @param headers Headers of createShuttlePathMappingV2026R0 method
   */
  public ShuttlePathMappingV2026R0 createShuttlePathMappingV2026R0(
      ShuttlePathMappingCreateRequestV2026R0 requestBody,
      CreateShuttlePathMappingV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_path_mappings"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttlePathMappingV2026R0.class);
  }

  /**
   * Returns details for a single shuttle path mapping identified by its ID, including the source
   * location and destination, if any.
   *
   * @param shuttlePathMappingId The unique identifier for the shuttle path mapping. Example:
   *     "6a3ba7a24ea17bc9461eeacd"
   */
  public ShuttlePathMappingV2026R0 getShuttlePathMappingByIdV2026R0(String shuttlePathMappingId) {
    return getShuttlePathMappingByIdV2026R0(
        shuttlePathMappingId, new GetShuttlePathMappingByIdV2026R0Headers());
  }

  /**
   * Returns details for a single shuttle path mapping identified by its ID, including the source
   * location and destination, if any.
   *
   * @param shuttlePathMappingId The unique identifier for the shuttle path mapping. Example:
   *     "6a3ba7a24ea17bc9461eeacd"
   * @param headers Headers of getShuttlePathMappingByIdV2026R0 method
   */
  public ShuttlePathMappingV2026R0 getShuttlePathMappingByIdV2026R0(
      String shuttlePathMappingId, GetShuttlePathMappingByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_path_mappings/",
                            convertToString(shuttlePathMappingId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttlePathMappingV2026R0.class);
  }

  /**
   * Updates the mutable properties of an existing shuttle path mapping identified by its ID. Only
   * the fields supplied in the request body are changed.
   *
   * @param shuttlePathMappingId The unique identifier for the shuttle path mapping. Example:
   *     "6a3ba7a24ea17bc9461eeacd"
   * @param requestBody Request body of updateShuttlePathMappingByIdV2026R0 method
   */
  public ShuttlePathMappingV2026R0 updateShuttlePathMappingByIdV2026R0(
      String shuttlePathMappingId, ShuttlePathMappingUpdateRequestV2026R0 requestBody) {
    return updateShuttlePathMappingByIdV2026R0(
        shuttlePathMappingId, requestBody, new UpdateShuttlePathMappingByIdV2026R0Headers());
  }

  /**
   * Updates the mutable properties of an existing shuttle path mapping identified by its ID. Only
   * the fields supplied in the request body are changed.
   *
   * @param shuttlePathMappingId The unique identifier for the shuttle path mapping. Example:
   *     "6a3ba7a24ea17bc9461eeacd"
   * @param requestBody Request body of updateShuttlePathMappingByIdV2026R0 method
   * @param headers Headers of updateShuttlePathMappingByIdV2026R0 method
   */
  public ShuttlePathMappingV2026R0 updateShuttlePathMappingByIdV2026R0(
      String shuttlePathMappingId,
      ShuttlePathMappingUpdateRequestV2026R0 requestBody,
      UpdateShuttlePathMappingByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_path_mappings/",
                            convertToString(shuttlePathMappingId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttlePathMappingV2026R0.class);
  }

  /**
   * Permanently deletes the shuttle path mapping identified by its ID. This does not affect content
   * that has already been migrated.
   *
   * @param shuttlePathMappingId The unique identifier for the shuttle path mapping. Example:
   *     "6a3ba7a24ea17bc9461eeacd"
   */
  public void deleteShuttlePathMappingByIdV2026R0(String shuttlePathMappingId) {
    deleteShuttlePathMappingByIdV2026R0(
        shuttlePathMappingId, new DeleteShuttlePathMappingByIdV2026R0Headers());
  }

  /**
   * Permanently deletes the shuttle path mapping identified by its ID. This does not affect content
   * that has already been migrated.
   *
   * @param shuttlePathMappingId The unique identifier for the shuttle path mapping. Example:
   *     "6a3ba7a24ea17bc9461eeacd"
   * @param headers Headers of deleteShuttlePathMappingByIdV2026R0 method
   */
  public void deleteShuttlePathMappingByIdV2026R0(
      String shuttlePathMappingId, DeleteShuttlePathMappingByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_path_mappings/",
                            convertToString(shuttlePathMappingId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Accepts a spreadsheet of path mappings, already stored in Box, and replaces the job's path
   * mappings. A file that is not a mapping workbook, or that exceeds the size or row limits (10 MiB
   * Box download limit; 100,000 mapping rows), returns `400` synchronously and leaves the job
   * unchanged. An accepted import always replaces every path mapping on the job. When all rows are
   * valid the workbook is deployed in full. When any row is invalid the job ends with no path
   * mappings and `mapping_state` becomes `paths_import_rejected` until the next import or the next
   * manual path-mapping change. Row problems are listed by `GET /shuttle_mapping_errors?job_id=…`
   * with category `path_import`, messages of the form `Row N: …`, at most 500 entries plus one
   * overflow entry. Validation and deployment are asynchronous after acceptance. Poll `GET
   * /shuttle_jobs/{shuttle_job_id}` while `mapping_state` is `importing_paths`. Only one import may
   * be in flight per job.
   *
   * @param requestBody Request body of createShuttlePathMappingImportV2026R0 method
   */
  public void createShuttlePathMappingImportV2026R0(
      ShuttlePathMappingImportCreateRequestV2026R0 requestBody) {
    createShuttlePathMappingImportV2026R0(
        requestBody, new CreateShuttlePathMappingImportV2026R0Headers());
  }

  /**
   * Accepts a spreadsheet of path mappings, already stored in Box, and replaces the job's path
   * mappings. A file that is not a mapping workbook, or that exceeds the size or row limits (10 MiB
   * Box download limit; 100,000 mapping rows), returns `400` synchronously and leaves the job
   * unchanged. An accepted import always replaces every path mapping on the job. When all rows are
   * valid the workbook is deployed in full. When any row is invalid the job ends with no path
   * mappings and `mapping_state` becomes `paths_import_rejected` until the next import or the next
   * manual path-mapping change. Row problems are listed by `GET /shuttle_mapping_errors?job_id=…`
   * with category `path_import`, messages of the form `Row N: …`, at most 500 entries plus one
   * overflow entry. Validation and deployment are asynchronous after acceptance. Poll `GET
   * /shuttle_jobs/{shuttle_job_id}` while `mapping_state` is `importing_paths`. Only one import may
   * be in flight per job.
   *
   * @param requestBody Request body of createShuttlePathMappingImportV2026R0 method
   * @param headers Headers of createShuttlePathMappingImportV2026R0 method
   */
  public void createShuttlePathMappingImportV2026R0(
      ShuttlePathMappingImportCreateRequestV2026R0 requestBody,
      CreateShuttlePathMappingImportV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_path_mapping_imports"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Accepts a spreadsheet of subject (user and group) mappings, already stored in Box, and replaces
   * the job's subject mappings. A file that is not a subject-mapping workbook, or that exceeds the
   * size or row limits (10 MiB Box download limit; 50,000 mapping rows), returns `400`
   * synchronously and leaves the job unchanged. An accepted import always replaces every subject
   * mapping on the job. When all rows are valid the workbook is deployed in full. When any row is
   * invalid the job ends with no subject mappings and `mapping_state` becomes
   * `subjects_import_rejected` until the next import or the next manual subject-mapping change. Row
   * problems are listed by `GET /shuttle_mapping_errors?job_id=…` with category `subject_import`,
   * messages of the form `Row N: …`, at most 500 entries plus one overflow entry. Validation and
   * deployment are asynchronous after acceptance. Poll `GET /shuttle_jobs/{shuttle_job_id}` while
   * `mapping_state` is `importing_subjects`. Only one import may be in flight per job. The job must
   * map subjects (`account_mapping` enabled) and its source scan must be finished; otherwise the
   * request returns `409`.
   *
   * <p>Expected workbook format (API clients cannot download the UI template): sheets `Users`
   * and/or `Groups` (a sheet named `Subjects` is also accepted). Columns are `Source User
   * &lt;Key&gt;`, `Target User &lt;Key&gt;`, `Skip` on `Users`, and `Source Group &lt;Key&gt;`,
   * `Target Group &lt;Key&gt;`, `Skip` on `Groups`, where `&lt;Key&gt;` is the primary identifier
   * for that role and system: - Users: `Email` for most systems; `ID` for `SharePoint`
   * (2007/2010/2013); `Name` for Windows (`windows_union`, stored as the user id). - Groups: `Name`
   * for most systems; `Email` for Google Drive admin groups. Source rows for a template can be
   * taken from `GET /shuttle_discovered_subjects`.
   *
   * @param requestBody Request body of createShuttleSubjectMappingImportV2026R0 method
   */
  public void createShuttleSubjectMappingImportV2026R0(
      ShuttleSubjectMappingImportCreateRequestV2026R0 requestBody) {
    createShuttleSubjectMappingImportV2026R0(
        requestBody, new CreateShuttleSubjectMappingImportV2026R0Headers());
  }

  /**
   * Accepts a spreadsheet of subject (user and group) mappings, already stored in Box, and replaces
   * the job's subject mappings. A file that is not a subject-mapping workbook, or that exceeds the
   * size or row limits (10 MiB Box download limit; 50,000 mapping rows), returns `400`
   * synchronously and leaves the job unchanged. An accepted import always replaces every subject
   * mapping on the job. When all rows are valid the workbook is deployed in full. When any row is
   * invalid the job ends with no subject mappings and `mapping_state` becomes
   * `subjects_import_rejected` until the next import or the next manual subject-mapping change. Row
   * problems are listed by `GET /shuttle_mapping_errors?job_id=…` with category `subject_import`,
   * messages of the form `Row N: …`, at most 500 entries plus one overflow entry. Validation and
   * deployment are asynchronous after acceptance. Poll `GET /shuttle_jobs/{shuttle_job_id}` while
   * `mapping_state` is `importing_subjects`. Only one import may be in flight per job. The job must
   * map subjects (`account_mapping` enabled) and its source scan must be finished; otherwise the
   * request returns `409`.
   *
   * <p>Expected workbook format (API clients cannot download the UI template): sheets `Users`
   * and/or `Groups` (a sheet named `Subjects` is also accepted). Columns are `Source User
   * &lt;Key&gt;`, `Target User &lt;Key&gt;`, `Skip` on `Users`, and `Source Group &lt;Key&gt;`,
   * `Target Group &lt;Key&gt;`, `Skip` on `Groups`, where `&lt;Key&gt;` is the primary identifier
   * for that role and system: - Users: `Email` for most systems; `ID` for `SharePoint`
   * (2007/2010/2013); `Name` for Windows (`windows_union`, stored as the user id). - Groups: `Name`
   * for most systems; `Email` for Google Drive admin groups. Source rows for a template can be
   * taken from `GET /shuttle_discovered_subjects`.
   *
   * @param requestBody Request body of createShuttleSubjectMappingImportV2026R0 method
   * @param headers Headers of createShuttleSubjectMappingImportV2026R0 method
   */
  public void createShuttleSubjectMappingImportV2026R0(
      ShuttleSubjectMappingImportCreateRequestV2026R0 requestBody,
      CreateShuttleSubjectMappingImportV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_subject_mapping_imports"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Returns a paginated list of subject mappings for the shuttle job identified by `job_id`.
   *
   * @param queryParams Query parameters of getShuttleSubjectMappingsV2026R0 method
   */
  public ShuttleSubjectMappingsV2026R0 getShuttleSubjectMappingsV2026R0(
      GetShuttleSubjectMappingsV2026R0QueryParams queryParams) {
    return getShuttleSubjectMappingsV2026R0(
        queryParams, new GetShuttleSubjectMappingsV2026R0Headers());
  }

  /**
   * Returns a paginated list of subject mappings for the shuttle job identified by `job_id`.
   *
   * @param queryParams Query parameters of getShuttleSubjectMappingsV2026R0 method
   * @param headers Headers of getShuttleSubjectMappingsV2026R0 method
   */
  public ShuttleSubjectMappingsV2026R0 getShuttleSubjectMappingsV2026R0(
      GetShuttleSubjectMappingsV2026R0QueryParams queryParams,
      GetShuttleSubjectMappingsV2026R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("job_id", convertToString(queryParams.getJobId())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_subject_mappings"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleSubjectMappingsV2026R0.class);
  }

  /**
   * Creates a subject mapping that connects a discovered source subject to a Box user or group for
   * the given shuttle job.
   *
   * @param requestBody Request body of createShuttleSubjectMappingV2026R0 method
   */
  public ShuttleSubjectMappingV2026R0 createShuttleSubjectMappingV2026R0(
      ShuttleSubjectMappingCreateRequestV2026R0 requestBody) {
    return createShuttleSubjectMappingV2026R0(
        requestBody, new CreateShuttleSubjectMappingV2026R0Headers());
  }

  /**
   * Creates a subject mapping that connects a discovered source subject to a Box user or group for
   * the given shuttle job.
   *
   * @param requestBody Request body of createShuttleSubjectMappingV2026R0 method
   * @param headers Headers of createShuttleSubjectMappingV2026R0 method
   */
  public ShuttleSubjectMappingV2026R0 createShuttleSubjectMappingV2026R0(
      ShuttleSubjectMappingCreateRequestV2026R0 requestBody,
      CreateShuttleSubjectMappingV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_subject_mappings"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleSubjectMappingV2026R0.class);
  }

  /**
   * Returns details for a single shuttle subject mapping identified by its ID, including the source
   * subject and Box target, if any.
   *
   * @param shuttleSubjectMappingId The unique identifier for the shuttle subject mapping. Example:
   *     "6a3ba7b12ea17bc9461eeace"
   */
  public ShuttleSubjectMappingV2026R0 getShuttleSubjectMappingByIdV2026R0(
      String shuttleSubjectMappingId) {
    return getShuttleSubjectMappingByIdV2026R0(
        shuttleSubjectMappingId, new GetShuttleSubjectMappingByIdV2026R0Headers());
  }

  /**
   * Returns details for a single shuttle subject mapping identified by its ID, including the source
   * subject and Box target, if any.
   *
   * @param shuttleSubjectMappingId The unique identifier for the shuttle subject mapping. Example:
   *     "6a3ba7b12ea17bc9461eeace"
   * @param headers Headers of getShuttleSubjectMappingByIdV2026R0 method
   */
  public ShuttleSubjectMappingV2026R0 getShuttleSubjectMappingByIdV2026R0(
      String shuttleSubjectMappingId, GetShuttleSubjectMappingByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_subject_mappings/",
                            convertToString(shuttleSubjectMappingId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleSubjectMappingV2026R0.class);
  }

  /**
   * Updates the mutable properties of an existing shuttle subject mapping identified by its ID.
   * Only the fields supplied in the request body are changed.
   *
   * @param shuttleSubjectMappingId The unique identifier for the shuttle subject mapping. Example:
   *     "6a3ba7b12ea17bc9461eeace"
   * @param requestBody Request body of updateShuttleSubjectMappingByIdV2026R0 method
   */
  public ShuttleSubjectMappingV2026R0 updateShuttleSubjectMappingByIdV2026R0(
      String shuttleSubjectMappingId, ShuttleSubjectMappingUpdateRequestV2026R0 requestBody) {
    return updateShuttleSubjectMappingByIdV2026R0(
        shuttleSubjectMappingId, requestBody, new UpdateShuttleSubjectMappingByIdV2026R0Headers());
  }

  /**
   * Updates the mutable properties of an existing shuttle subject mapping identified by its ID.
   * Only the fields supplied in the request body are changed.
   *
   * @param shuttleSubjectMappingId The unique identifier for the shuttle subject mapping. Example:
   *     "6a3ba7b12ea17bc9461eeace"
   * @param requestBody Request body of updateShuttleSubjectMappingByIdV2026R0 method
   * @param headers Headers of updateShuttleSubjectMappingByIdV2026R0 method
   */
  public ShuttleSubjectMappingV2026R0 updateShuttleSubjectMappingByIdV2026R0(
      String shuttleSubjectMappingId,
      ShuttleSubjectMappingUpdateRequestV2026R0 requestBody,
      UpdateShuttleSubjectMappingByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_subject_mappings/",
                            convertToString(shuttleSubjectMappingId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleSubjectMappingV2026R0.class);
  }

  /**
   * Permanently deletes the shuttle subject mapping identified by its ID.
   *
   * @param shuttleSubjectMappingId The unique identifier for the shuttle subject mapping. Example:
   *     "6a3ba7b12ea17bc9461eeace"
   */
  public void deleteShuttleSubjectMappingByIdV2026R0(String shuttleSubjectMappingId) {
    deleteShuttleSubjectMappingByIdV2026R0(
        shuttleSubjectMappingId, new DeleteShuttleSubjectMappingByIdV2026R0Headers());
  }

  /**
   * Permanently deletes the shuttle subject mapping identified by its ID.
   *
   * @param shuttleSubjectMappingId The unique identifier for the shuttle subject mapping. Example:
   *     "6a3ba7b12ea17bc9461eeace"
   * @param headers Headers of deleteShuttleSubjectMappingByIdV2026R0 method
   */
  public void deleteShuttleSubjectMappingByIdV2026R0(
      String shuttleSubjectMappingId, DeleteShuttleSubjectMappingByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_subject_mappings/",
                            convertToString(shuttleSubjectMappingId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Returns a paginated list of subjects discovered on the source system for the shuttle job
   * identified by `job_id`.
   *
   * @param queryParams Query parameters of getShuttleDiscoveredSubjectsV2026R0 method
   */
  public ShuttleDiscoveredSubjectsV2026R0 getShuttleDiscoveredSubjectsV2026R0(
      GetShuttleDiscoveredSubjectsV2026R0QueryParams queryParams) {
    return getShuttleDiscoveredSubjectsV2026R0(
        queryParams, new GetShuttleDiscoveredSubjectsV2026R0Headers());
  }

  /**
   * Returns a paginated list of subjects discovered on the source system for the shuttle job
   * identified by `job_id`.
   *
   * @param queryParams Query parameters of getShuttleDiscoveredSubjectsV2026R0 method
   * @param headers Headers of getShuttleDiscoveredSubjectsV2026R0 method
   */
  public ShuttleDiscoveredSubjectsV2026R0 getShuttleDiscoveredSubjectsV2026R0(
      GetShuttleDiscoveredSubjectsV2026R0QueryParams queryParams,
      GetShuttleDiscoveredSubjectsV2026R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("job_id", convertToString(queryParams.getJobId())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_discovered_subjects"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleDiscoveredSubjectsV2026R0.class);
  }

  /**
   * Returns a paginated list of source or target path entries for a shuttle job. Call with only
   * `job_id` and `side` to list the roots, then browse one level at a time using `path`, `path_id`,
   * or both. Each entry includes `path`, `path_id`, `is_directory`, and a `scope` object (`account`
   * or `shared_space`) so a mapping can be created from that row without extra browse state. Valid
   * values depend on the selected system.
   *
   * @param queryParams Query parameters of getShuttlePathEntriesV2026R0 method
   */
  public ShuttlePathEntriesV2026R0 getShuttlePathEntriesV2026R0(
      GetShuttlePathEntriesV2026R0QueryParams queryParams) {
    return getShuttlePathEntriesV2026R0(queryParams, new GetShuttlePathEntriesV2026R0Headers());
  }

  /**
   * Returns a paginated list of source or target path entries for a shuttle job. Call with only
   * `job_id` and `side` to list the roots, then browse one level at a time using `path`, `path_id`,
   * or both. Each entry includes `path`, `path_id`, `is_directory`, and a `scope` object (`account`
   * or `shared_space`) so a mapping can be created from that row without extra browse state. Valid
   * values depend on the selected system.
   *
   * @param queryParams Query parameters of getShuttlePathEntriesV2026R0 method
   * @param headers Headers of getShuttlePathEntriesV2026R0 method
   */
  public ShuttlePathEntriesV2026R0 getShuttlePathEntriesV2026R0(
      GetShuttlePathEntriesV2026R0QueryParams queryParams,
      GetShuttlePathEntriesV2026R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("job_id", convertToString(queryParams.getJobId())),
                entryOf("side", convertToString(queryParams.getSide())),
                entryOf("path", convertToString(queryParams.getPath())),
                entryOf("path_id", convertToString(queryParams.getPathId())),
                entryOf("account_email", convertToString(queryParams.getAccountEmail())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_path_entries"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttlePathEntriesV2026R0.class);
  }

  /**
   * Returns a paginated list of mapping errors for a shuttle job. Only the latest record for each
   * error is returned. The revision field identifies the mapping revision in which that error was
   * generated.
   *
   * @param queryParams Query parameters of getShuttleMappingErrorsV2026R0 method
   */
  public ShuttleMappingErrorsV2026R0 getShuttleMappingErrorsV2026R0(
      GetShuttleMappingErrorsV2026R0QueryParams queryParams) {
    return getShuttleMappingErrorsV2026R0(queryParams, new GetShuttleMappingErrorsV2026R0Headers());
  }

  /**
   * Returns a paginated list of mapping errors for a shuttle job. Only the latest record for each
   * error is returned. The revision field identifies the mapping revision in which that error was
   * generated.
   *
   * @param queryParams Query parameters of getShuttleMappingErrorsV2026R0 method
   * @param headers Headers of getShuttleMappingErrorsV2026R0 method
   */
  public ShuttleMappingErrorsV2026R0 getShuttleMappingErrorsV2026R0(
      GetShuttleMappingErrorsV2026R0QueryParams queryParams,
      GetShuttleMappingErrorsV2026R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("job_id", convertToString(queryParams.getJobId())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_mapping_errors"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleMappingErrorsV2026R0.class);
  }

  /**
   * Returns a paginated list of reports generated for the shuttle run identified by `run_id`.
   *
   * @param queryParams Query parameters of getShuttleReportsV2026R0 method
   */
  public ShuttleReportsV2026R0 getShuttleReportsV2026R0(
      GetShuttleReportsV2026R0QueryParams queryParams) {
    return getShuttleReportsV2026R0(queryParams, new GetShuttleReportsV2026R0Headers());
  }

  /**
   * Returns a paginated list of reports generated for the shuttle run identified by `run_id`.
   *
   * @param queryParams Query parameters of getShuttleReportsV2026R0 method
   * @param headers Headers of getShuttleReportsV2026R0 method
   */
  public ShuttleReportsV2026R0 getShuttleReportsV2026R0(
      GetShuttleReportsV2026R0QueryParams queryParams, GetShuttleReportsV2026R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("run_id", convertToString(queryParams.getRunId())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_reports"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleReportsV2026R0.class);
  }

  /**
   * Requests asynchronous generation of a report for a shuttle run. The report is returned
   * immediately in `pending` or `processing` status. Poll `GET
   * /shuttle_reports/{shuttle_report_id}` while `status` is `pending` or `processing`. Stop when
   * `status` is `complete`, `failed`, `canceled`, or `unknown`. Repeating this request with the
   * same parameters reuses the existing report instead of creating a duplicate.
   *
   * @param requestBody Request body of createShuttleReportV2026R0 method
   */
  public ShuttleReportV2026R0 createShuttleReportV2026R0(
      ShuttleReportCreateRequestV2026R0 requestBody) {
    return createShuttleReportV2026R0(requestBody, new CreateShuttleReportV2026R0Headers());
  }

  /**
   * Requests asynchronous generation of a report for a shuttle run. The report is returned
   * immediately in `pending` or `processing` status. Poll `GET
   * /shuttle_reports/{shuttle_report_id}` while `status` is `pending` or `processing`. Stop when
   * `status` is `complete`, `failed`, `canceled`, or `unknown`. Repeating this request with the
   * same parameters reuses the existing report instead of creating a duplicate.
   *
   * @param requestBody Request body of createShuttleReportV2026R0 method
   * @param headers Headers of createShuttleReportV2026R0 method
   */
  public ShuttleReportV2026R0 createShuttleReportV2026R0(
      ShuttleReportCreateRequestV2026R0 requestBody, CreateShuttleReportV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_reports"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleReportV2026R0.class);
  }

  /**
   * Returns details for a single shuttle report identified by its ID, including generation status
   * and, once generation finishes, the Box output reference.
   *
   * @param shuttleReportId The unique identifier for the shuttle report. Example:
   *     "6a3ba8d14f2c91e8370b4d12"
   */
  public ShuttleReportV2026R0 getShuttleReportByIdV2026R0(String shuttleReportId) {
    return getShuttleReportByIdV2026R0(shuttleReportId, new GetShuttleReportByIdV2026R0Headers());
  }

  /**
   * Returns details for a single shuttle report identified by its ID, including generation status
   * and, once generation finishes, the Box output reference.
   *
   * @param shuttleReportId The unique identifier for the shuttle report. Example:
   *     "6a3ba8d14f2c91e8370b4d12"
   * @param headers Headers of getShuttleReportByIdV2026R0 method
   */
  public ShuttleReportV2026R0 getShuttleReportByIdV2026R0(
      String shuttleReportId, GetShuttleReportByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shuttle_reports/",
                            convertToString(shuttleReportId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShuttleReportV2026R0.class);
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

    public ShuttleManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new ShuttleManager(this);
    }
  }
}
