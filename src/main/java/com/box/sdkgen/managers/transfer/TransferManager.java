package com.box.sdkgen.managers.transfer;

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
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class TransferManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public TransferManager() {
    this.networkSession = new NetworkSession();
  }

  protected TransferManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Move all of the items (files, folders and workflows) owned by a user into another user's
   * account.
   *
   * <p>Only the root folder (`0`) can be transferred.
   *
   * <p>Folders can only be moved across users by users with administrative permissions.
   *
   * <p>All existing shared links and folder-level collaborations are transferred during the
   * operation. Please note that while collaborations at the individual file-level are transferred
   * during the operation, the collaborations are deleted when the original user is deleted.
   *
   * <p>If the user has a large number of items across all folders, the call will be run
   * asynchronously. If the operation is not completed within 10 minutes, the user will receive a
   * 200 OK response, and the operation will continue running.
   *
   * <p>If the destination path has a metadata cascade policy attached to any of the parent folders,
   * a metadata cascade operation will be kicked off asynchronously.
   *
   * <p>There is currently no way to check for when this operation is finished.
   *
   * <p>The destination folder's name will be in the format `{User}'s Files and Folders`, where
   * `{User}` is the display name of the user.
   *
   * <p>To make this API call your application will need to have the "Read and write all files and
   * folders stored in Box" scope enabled.
   *
   * <p>Please make sure the destination user has access to `Relay` or `Relay Lite`, and has access
   * to the files and folders involved in the workflows being transferred.
   *
   * <p>Admins will receive an email when the operation is completed.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param requestBody Request body of transferOwnedFolder method
   */
  public FolderFull transferOwnedFolder(String userId, TransferOwnedFolderRequestBody requestBody) {
    return transferOwnedFolder(
        userId,
        requestBody,
        new TransferOwnedFolderQueryParams(),
        new TransferOwnedFolderHeaders());
  }

  /**
   * Move all of the items (files, folders and workflows) owned by a user into another user's
   * account.
   *
   * <p>Only the root folder (`0`) can be transferred.
   *
   * <p>Folders can only be moved across users by users with administrative permissions.
   *
   * <p>All existing shared links and folder-level collaborations are transferred during the
   * operation. Please note that while collaborations at the individual file-level are transferred
   * during the operation, the collaborations are deleted when the original user is deleted.
   *
   * <p>If the user has a large number of items across all folders, the call will be run
   * asynchronously. If the operation is not completed within 10 minutes, the user will receive a
   * 200 OK response, and the operation will continue running.
   *
   * <p>If the destination path has a metadata cascade policy attached to any of the parent folders,
   * a metadata cascade operation will be kicked off asynchronously.
   *
   * <p>There is currently no way to check for when this operation is finished.
   *
   * <p>The destination folder's name will be in the format `{User}'s Files and Folders`, where
   * `{User}` is the display name of the user.
   *
   * <p>To make this API call your application will need to have the "Read and write all files and
   * folders stored in Box" scope enabled.
   *
   * <p>Please make sure the destination user has access to `Relay` or `Relay Lite`, and has access
   * to the files and folders involved in the workflows being transferred.
   *
   * <p>Admins will receive an email when the operation is completed.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param requestBody Request body of transferOwnedFolder method
   * @param queryParams Query parameters of transferOwnedFolder method
   */
  public FolderFull transferOwnedFolder(
      String userId,
      TransferOwnedFolderRequestBody requestBody,
      TransferOwnedFolderQueryParams queryParams) {
    return transferOwnedFolder(userId, requestBody, queryParams, new TransferOwnedFolderHeaders());
  }

  /**
   * Move all of the items (files, folders and workflows) owned by a user into another user's
   * account.
   *
   * <p>Only the root folder (`0`) can be transferred.
   *
   * <p>Folders can only be moved across users by users with administrative permissions.
   *
   * <p>All existing shared links and folder-level collaborations are transferred during the
   * operation. Please note that while collaborations at the individual file-level are transferred
   * during the operation, the collaborations are deleted when the original user is deleted.
   *
   * <p>If the user has a large number of items across all folders, the call will be run
   * asynchronously. If the operation is not completed within 10 minutes, the user will receive a
   * 200 OK response, and the operation will continue running.
   *
   * <p>If the destination path has a metadata cascade policy attached to any of the parent folders,
   * a metadata cascade operation will be kicked off asynchronously.
   *
   * <p>There is currently no way to check for when this operation is finished.
   *
   * <p>The destination folder's name will be in the format `{User}'s Files and Folders`, where
   * `{User}` is the display name of the user.
   *
   * <p>To make this API call your application will need to have the "Read and write all files and
   * folders stored in Box" scope enabled.
   *
   * <p>Please make sure the destination user has access to `Relay` or `Relay Lite`, and has access
   * to the files and folders involved in the workflows being transferred.
   *
   * <p>Admins will receive an email when the operation is completed.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param requestBody Request body of transferOwnedFolder method
   * @param headers Headers of transferOwnedFolder method
   */
  public FolderFull transferOwnedFolder(
      String userId,
      TransferOwnedFolderRequestBody requestBody,
      TransferOwnedFolderHeaders headers) {
    return transferOwnedFolder(userId, requestBody, new TransferOwnedFolderQueryParams(), headers);
  }

  /**
   * Move all of the items (files, folders and workflows) owned by a user into another user's
   * account.
   *
   * <p>Only the root folder (`0`) can be transferred.
   *
   * <p>Folders can only be moved across users by users with administrative permissions.
   *
   * <p>All existing shared links and folder-level collaborations are transferred during the
   * operation. Please note that while collaborations at the individual file-level are transferred
   * during the operation, the collaborations are deleted when the original user is deleted.
   *
   * <p>If the user has a large number of items across all folders, the call will be run
   * asynchronously. If the operation is not completed within 10 minutes, the user will receive a
   * 200 OK response, and the operation will continue running.
   *
   * <p>If the destination path has a metadata cascade policy attached to any of the parent folders,
   * a metadata cascade operation will be kicked off asynchronously.
   *
   * <p>There is currently no way to check for when this operation is finished.
   *
   * <p>The destination folder's name will be in the format `{User}'s Files and Folders`, where
   * `{User}` is the display name of the user.
   *
   * <p>To make this API call your application will need to have the "Read and write all files and
   * folders stored in Box" scope enabled.
   *
   * <p>Please make sure the destination user has access to `Relay` or `Relay Lite`, and has access
   * to the files and folders involved in the workflows being transferred.
   *
   * <p>Admins will receive an email when the operation is completed.
   *
   * @param userId The ID of the user. Example: "12345"
   * @param requestBody Request body of transferOwnedFolder method
   * @param queryParams Query parameters of transferOwnedFolder method
   * @param headers Headers of transferOwnedFolder method
   */
  public FolderFull transferOwnedFolder(
      String userId,
      TransferOwnedFolderRequestBody requestBody,
      TransferOwnedFolderQueryParams queryParams,
      TransferOwnedFolderHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("notify", convertToString(queryParams.getNotify()))));
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
                            "/folders/0"),
                        "PUT")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), FolderFull.class);
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

    public TransferManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new TransferManager(this);
    }
  }
}
