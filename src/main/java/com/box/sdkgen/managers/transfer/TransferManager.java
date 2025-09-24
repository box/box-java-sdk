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

  public FolderFull transferOwnedFolder(String userId, TransferOwnedFolderRequestBody requestBody) {
    return transferOwnedFolder(
        userId,
        requestBody,
        new TransferOwnedFolderQueryParams(),
        new TransferOwnedFolderHeaders());
  }

  public FolderFull transferOwnedFolder(
      String userId,
      TransferOwnedFolderRequestBody requestBody,
      TransferOwnedFolderQueryParams queryParams) {
    return transferOwnedFolder(userId, requestBody, queryParams, new TransferOwnedFolderHeaders());
  }

  public FolderFull transferOwnedFolder(
      String userId,
      TransferOwnedFolderRequestBody requestBody,
      TransferOwnedFolderHeaders headers) {
    return transferOwnedFolder(userId, requestBody, new TransferOwnedFolderQueryParams(), headers);
  }

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

    public TransferManager build() {
      return new TransferManager(this);
    }
  }
}
