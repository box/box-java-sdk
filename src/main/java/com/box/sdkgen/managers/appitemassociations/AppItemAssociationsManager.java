package com.box.sdkgen.managers.appitemassociations;

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
import com.box.sdkgen.schemas.appitemassociations.AppItemAssociations;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class AppItemAssociationsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public AppItemAssociationsManager() {
    this.networkSession = new NetworkSession();
  }

  protected AppItemAssociationsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * **This is a beta feature, which means that its availability might be limited.** Returns all app
   * items the file is associated with. This includes app items associated with ancestors of the
   * file. Assuming the context user has access to the file, the type/ids are revealed even if the
   * context user does not have **View** permission on the app item.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public AppItemAssociations getFileAppItemAssociations(String fileId) {
    return getFileAppItemAssociations(
        fileId,
        new GetFileAppItemAssociationsQueryParams(),
        new GetFileAppItemAssociationsHeaders());
  }

  /**
   * **This is a beta feature, which means that its availability might be limited.** Returns all app
   * items the file is associated with. This includes app items associated with ancestors of the
   * file. Assuming the context user has access to the file, the type/ids are revealed even if the
   * context user does not have **View** permission on the app item.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getFileAppItemAssociations method
   */
  public AppItemAssociations getFileAppItemAssociations(
      String fileId, GetFileAppItemAssociationsQueryParams queryParams) {
    return getFileAppItemAssociations(fileId, queryParams, new GetFileAppItemAssociationsHeaders());
  }

  /**
   * **This is a beta feature, which means that its availability might be limited.** Returns all app
   * items the file is associated with. This includes app items associated with ancestors of the
   * file. Assuming the context user has access to the file, the type/ids are revealed even if the
   * context user does not have **View** permission on the app item.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of getFileAppItemAssociations method
   */
  public AppItemAssociations getFileAppItemAssociations(
      String fileId, GetFileAppItemAssociationsHeaders headers) {
    return getFileAppItemAssociations(fileId, new GetFileAppItemAssociationsQueryParams(), headers);
  }

  /**
   * **This is a beta feature, which means that its availability might be limited.** Returns all app
   * items the file is associated with. This includes app items associated with ancestors of the
   * file. Assuming the context user has access to the file, the type/ids are revealed even if the
   * context user does not have **View** permission on the app item.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getFileAppItemAssociations method
   * @param headers Headers of getFileAppItemAssociations method
   */
  public AppItemAssociations getFileAppItemAssociations(
      String fileId,
      GetFileAppItemAssociationsQueryParams queryParams,
      GetFileAppItemAssociationsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("application_type", convertToString(queryParams.getApplicationType()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/files/",
                            convertToString(fileId),
                            "/app_item_associations"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), AppItemAssociations.class);
  }

  /**
   * **This is a beta feature, which means that its availability might be limited.** Returns all app
   * items the folder is associated with. This includes app items associated with ancestors of the
   * folder. Assuming the context user has access to the folder, the type/ids are revealed even if
   * the context user does not have **View** permission on the app item.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   */
  public AppItemAssociations getFolderAppItemAssociations(String folderId) {
    return getFolderAppItemAssociations(
        folderId,
        new GetFolderAppItemAssociationsQueryParams(),
        new GetFolderAppItemAssociationsHeaders());
  }

  /**
   * **This is a beta feature, which means that its availability might be limited.** Returns all app
   * items the folder is associated with. This includes app items associated with ancestors of the
   * folder. Assuming the context user has access to the folder, the type/ids are revealed even if
   * the context user does not have **View** permission on the app item.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param queryParams Query parameters of getFolderAppItemAssociations method
   */
  public AppItemAssociations getFolderAppItemAssociations(
      String folderId, GetFolderAppItemAssociationsQueryParams queryParams) {
    return getFolderAppItemAssociations(
        folderId, queryParams, new GetFolderAppItemAssociationsHeaders());
  }

  /**
   * **This is a beta feature, which means that its availability might be limited.** Returns all app
   * items the folder is associated with. This includes app items associated with ancestors of the
   * folder. Assuming the context user has access to the folder, the type/ids are revealed even if
   * the context user does not have **View** permission on the app item.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param headers Headers of getFolderAppItemAssociations method
   */
  public AppItemAssociations getFolderAppItemAssociations(
      String folderId, GetFolderAppItemAssociationsHeaders headers) {
    return getFolderAppItemAssociations(
        folderId, new GetFolderAppItemAssociationsQueryParams(), headers);
  }

  /**
   * **This is a beta feature, which means that its availability might be limited.** Returns all app
   * items the folder is associated with. This includes app items associated with ancestors of the
   * folder. Assuming the context user has access to the folder, the type/ids are revealed even if
   * the context user does not have **View** permission on the app item.
   *
   * @param folderId The unique identifier that represent a folder.
   *     <p>The ID for any folder can be determined by visiting this folder in the web application
   *     and copying the ID from the URL. For example, for the URL
   *     `https://*.app.box.com/folder/123` the `folder_id` is `123`.
   *     <p>The root folder of a Box account is always represented by the ID `0`. Example: "12345"
   * @param queryParams Query parameters of getFolderAppItemAssociations method
   * @param headers Headers of getFolderAppItemAssociations method
   */
  public AppItemAssociations getFolderAppItemAssociations(
      String folderId,
      GetFolderAppItemAssociationsQueryParams queryParams,
      GetFolderAppItemAssociationsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("application_type", convertToString(queryParams.getApplicationType()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/folders/",
                            convertToString(folderId),
                            "/app_item_associations"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), AppItemAssociations.class);
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

    public AppItemAssociationsManager build() {
      return new AppItemAssociationsManager(this);
    }
  }
}
