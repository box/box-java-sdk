package com.box.sdkgen.managers.fileversionretentions;

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
import com.box.sdkgen.schemas.fileversionretention.FileVersionRetention;
import com.box.sdkgen.schemas.fileversionretentions.FileVersionRetentions;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class FileVersionRetentionsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public FileVersionRetentionsManager() {
    this.networkSession = new NetworkSession();
  }

  protected FileVersionRetentionsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves all file version retentions for the given enterprise.
   *
   * <p>**Note**: File retention API is now **deprecated**. To get information about files and file
   * versions under retention, see [files under
   * retention](https://developer.box.com/reference/get-retention-policy-assignments-id-files-under-retention)
   * or [file versions under
   * retention](https://developer.box.com/reference/get-retention-policy-assignments-id-file-versions-under-retention)
   * endpoints.
   */
  public FileVersionRetentions getFileVersionRetentions() {
    return getFileVersionRetentions(
        new GetFileVersionRetentionsQueryParams(), new GetFileVersionRetentionsHeaders());
  }

  /**
   * Retrieves all file version retentions for the given enterprise.
   *
   * <p>**Note**: File retention API is now **deprecated**. To get information about files and file
   * versions under retention, see [files under
   * retention](https://developer.box.com/reference/get-retention-policy-assignments-id-files-under-retention)
   * or [file versions under
   * retention](https://developer.box.com/reference/get-retention-policy-assignments-id-file-versions-under-retention)
   * endpoints.
   *
   * @param queryParams Query parameters of getFileVersionRetentions method
   */
  public FileVersionRetentions getFileVersionRetentions(
      GetFileVersionRetentionsQueryParams queryParams) {
    return getFileVersionRetentions(queryParams, new GetFileVersionRetentionsHeaders());
  }

  /**
   * Retrieves all file version retentions for the given enterprise.
   *
   * <p>**Note**: File retention API is now **deprecated**. To get information about files and file
   * versions under retention, see [files under
   * retention](https://developer.box.com/reference/get-retention-policy-assignments-id-files-under-retention)
   * or [file versions under
   * retention](https://developer.box.com/reference/get-retention-policy-assignments-id-file-versions-under-retention)
   * endpoints.
   *
   * @param headers Headers of getFileVersionRetentions method
   */
  public FileVersionRetentions getFileVersionRetentions(GetFileVersionRetentionsHeaders headers) {
    return getFileVersionRetentions(new GetFileVersionRetentionsQueryParams(), headers);
  }

  /**
   * Retrieves all file version retentions for the given enterprise.
   *
   * <p>**Note**: File retention API is now **deprecated**. To get information about files and file
   * versions under retention, see [files under
   * retention](https://developer.box.com/reference/get-retention-policy-assignments-id-files-under-retention)
   * or [file versions under
   * retention](https://developer.box.com/reference/get-retention-policy-assignments-id-file-versions-under-retention)
   * endpoints.
   *
   * @param queryParams Query parameters of getFileVersionRetentions method
   * @param headers Headers of getFileVersionRetentions method
   */
  public FileVersionRetentions getFileVersionRetentions(
      GetFileVersionRetentionsQueryParams queryParams, GetFileVersionRetentionsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("file_id", convertToString(queryParams.getFileId())),
                entryOf("file_version_id", convertToString(queryParams.getFileVersionId())),
                entryOf("policy_id", convertToString(queryParams.getPolicyId())),
                entryOf("disposition_action", convertToString(queryParams.getDispositionAction())),
                entryOf("disposition_before", convertToString(queryParams.getDispositionBefore())),
                entryOf("disposition_after", convertToString(queryParams.getDispositionAfter())),
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
                            "/2.0/file_version_retentions"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileVersionRetentions.class);
  }

  /**
   * Returns information about a file version retention.
   *
   * <p>**Note**: File retention API is now **deprecated**. To get information about files and file
   * versions under retention, see [files under
   * retention](https://developer.box.com/reference/get-retention-policy-assignments-id-files-under-retention)
   * or [file versions under
   * retention](https://developer.box.com/reference/get-retention-policy-assignments-id-file-versions-under-retention)
   * endpoints.
   *
   * @param fileVersionRetentionId The ID of the file version retention. Example: "3424234"
   */
  public FileVersionRetention getFileVersionRetentionById(String fileVersionRetentionId) {
    return getFileVersionRetentionById(
        fileVersionRetentionId, new GetFileVersionRetentionByIdHeaders());
  }

  /**
   * Returns information about a file version retention.
   *
   * <p>**Note**: File retention API is now **deprecated**. To get information about files and file
   * versions under retention, see [files under
   * retention](https://developer.box.com/reference/get-retention-policy-assignments-id-files-under-retention)
   * or [file versions under
   * retention](https://developer.box.com/reference/get-retention-policy-assignments-id-file-versions-under-retention)
   * endpoints.
   *
   * @param fileVersionRetentionId The ID of the file version retention. Example: "3424234"
   * @param headers Headers of getFileVersionRetentionById method
   */
  public FileVersionRetention getFileVersionRetentionById(
      String fileVersionRetentionId, GetFileVersionRetentionByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/file_version_retentions/",
                            convertToString(fileVersionRetentionId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FileVersionRetention.class);
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

    public FileVersionRetentionsManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new FileVersionRetentionsManager(this);
    }
  }
}
