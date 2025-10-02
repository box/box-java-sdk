package com.box.sdkgen.managers.fileversionlegalholds;

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
import com.box.sdkgen.schemas.fileversionlegalhold.FileVersionLegalHold;
import com.box.sdkgen.schemas.fileversionlegalholds.FileVersionLegalHolds;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class FileVersionLegalHoldsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public FileVersionLegalHoldsManager() {
    this.networkSession = new NetworkSession();
  }

  protected FileVersionLegalHoldsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves information about the legal hold policies assigned to a file version.
   *
   * @param fileVersionLegalHoldId The ID of the file version legal hold. Example: "2348213"
   */
  public FileVersionLegalHold getFileVersionLegalHoldById(String fileVersionLegalHoldId) {
    return getFileVersionLegalHoldById(
        fileVersionLegalHoldId, new GetFileVersionLegalHoldByIdHeaders());
  }

  /**
   * Retrieves information about the legal hold policies assigned to a file version.
   *
   * @param fileVersionLegalHoldId The ID of the file version legal hold. Example: "2348213"
   * @param headers Headers of getFileVersionLegalHoldById method
   */
  public FileVersionLegalHold getFileVersionLegalHoldById(
      String fileVersionLegalHoldId, GetFileVersionLegalHoldByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/file_version_legal_holds/",
                            convertToString(fileVersionLegalHoldId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileVersionLegalHold.class);
  }

  /**
   * Get a list of file versions on legal hold for a legal hold assignment.
   *
   * <p>Due to ongoing re-architecture efforts this API might not return all file versions for this
   * policy ID.
   *
   * <p>Instead, this API will only return file versions held in the legacy architecture. Two new
   * endpoints will available to request any file versions held in the new architecture.
   *
   * <p>For file versions held in the new architecture, the `GET
   * /legal_hold_policy_assignments/:id/file_versions_on_hold` API can be used to return all past
   * file versions available for this policy assignment, and the `GET
   * /legal_hold_policy_assignments/:id/files_on_hold` API can be used to return any current
   * (latest) versions of a file under legal hold.
   *
   * <p>The `GET /legal_hold_policy_assignments?policy_id={id}` API can be used to find a list of
   * policy assignments for a given policy ID.
   *
   * <p>Once the re-architecture is completed this API will be deprecated.
   *
   * @param queryParams Query parameters of getFileVersionLegalHolds method
   */
  public FileVersionLegalHolds getFileVersionLegalHolds(
      GetFileVersionLegalHoldsQueryParams queryParams) {
    return getFileVersionLegalHolds(queryParams, new GetFileVersionLegalHoldsHeaders());
  }

  /**
   * Get a list of file versions on legal hold for a legal hold assignment.
   *
   * <p>Due to ongoing re-architecture efforts this API might not return all file versions for this
   * policy ID.
   *
   * <p>Instead, this API will only return file versions held in the legacy architecture. Two new
   * endpoints will available to request any file versions held in the new architecture.
   *
   * <p>For file versions held in the new architecture, the `GET
   * /legal_hold_policy_assignments/:id/file_versions_on_hold` API can be used to return all past
   * file versions available for this policy assignment, and the `GET
   * /legal_hold_policy_assignments/:id/files_on_hold` API can be used to return any current
   * (latest) versions of a file under legal hold.
   *
   * <p>The `GET /legal_hold_policy_assignments?policy_id={id}` API can be used to find a list of
   * policy assignments for a given policy ID.
   *
   * <p>Once the re-architecture is completed this API will be deprecated.
   *
   * @param queryParams Query parameters of getFileVersionLegalHolds method
   * @param headers Headers of getFileVersionLegalHolds method
   */
  public FileVersionLegalHolds getFileVersionLegalHolds(
      GetFileVersionLegalHoldsQueryParams queryParams, GetFileVersionLegalHoldsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("policy_id", convertToString(queryParams.getPolicyId())),
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
                            "/2.0/file_version_legal_holds"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileVersionLegalHolds.class);
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

    public FileVersionLegalHoldsManager build() {
      return new FileVersionLegalHoldsManager(this);
    }
  }
}
