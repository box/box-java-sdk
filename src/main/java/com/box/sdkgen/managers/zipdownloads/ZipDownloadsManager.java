package com.box.sdkgen.managers.zipdownloads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.zipdownload.ZipDownload;
import com.box.sdkgen.schemas.zipdownloadrequest.ZipDownloadRequest;
import com.box.sdkgen.schemas.zipdownloadstatus.ZipDownloadStatus;
import com.box.sdkgen.serialization.json.JsonManager;
import java.io.InputStream;
import java.util.Map;

public class ZipDownloadsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public ZipDownloadsManager() {
    this.networkSession = new NetworkSession();
  }

  protected ZipDownloadsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Creates a request to download multiple files and folders as a single `zip` archive file. This
   * API does not return the archive but instead performs all the checks to ensure that the user has
   * access to all the items, and then returns a `download_url` and a `status_url` that can be used
   * to download the archive.
   *
   * <p>The limit for an archive is either the Account's upload limit or 10,000 files, whichever is
   * met first.
   *
   * <p>**Note**: Downloading a large file can be affected by various factors such as distance,
   * network latency, bandwidth, and congestion, as well as packet loss ratio and current server
   * load. For these reasons we recommend that a maximum ZIP archive total size does not exceed
   * 25GB.
   *
   * @param requestBody Request body of createZipDownload method
   */
  public ZipDownload createZipDownload(ZipDownloadRequest requestBody) {
    return createZipDownload(requestBody, new CreateZipDownloadHeaders());
  }

  /**
   * Creates a request to download multiple files and folders as a single `zip` archive file. This
   * API does not return the archive but instead performs all the checks to ensure that the user has
   * access to all the items, and then returns a `download_url` and a `status_url` that can be used
   * to download the archive.
   *
   * <p>The limit for an archive is either the Account's upload limit or 10,000 files, whichever is
   * met first.
   *
   * <p>**Note**: Downloading a large file can be affected by various factors such as distance,
   * network latency, bandwidth, and congestion, as well as packet loss ratio and current server
   * load. For these reasons we recommend that a maximum ZIP archive total size does not exceed
   * 25GB.
   *
   * @param requestBody Request body of createZipDownload method
   * @param headers Headers of createZipDownload method
   */
  public ZipDownload createZipDownload(
      ZipDownloadRequest requestBody, CreateZipDownloadHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/zip_downloads"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ZipDownload.class);
  }

  /**
   * Returns the contents of a `zip` archive in binary format. This URL does not require any form of
   * authentication and could be used in a user's browser to download the archive to a user's
   * device.
   *
   * <p>By default, this URL is only valid for a few seconds from the creation of the request for
   * this archive. Once a download has started it can not be stopped and resumed, instead a new
   * request for a zip archive would need to be created.
   *
   * <p>The URL of this endpoint should not be considered as fixed. Instead, use the [Create zip
   * download](e://post_zip_downloads) API to request to create a `zip` archive, and then follow the
   * `download_url` field in the response to this endpoint.
   *
   * @param downloadUrl The URL that can be used to download created `zip` archive. Example:
   *     `https://dl.boxcloud.com/2.0/zip_downloads/29l00nfxDyHOt7RphI9zT_w==nDnZEDjY2S8iEWWCHEEiptFxwoWojjlibZjJ6geuE5xnXENDTPxzgbks_yY=/content`
   */
  public InputStream getZipDownloadContent(String downloadUrl) {
    return getZipDownloadContent(downloadUrl, new GetZipDownloadContentHeaders());
  }

  /**
   * Returns the contents of a `zip` archive in binary format. This URL does not require any form of
   * authentication and could be used in a user's browser to download the archive to a user's
   * device.
   *
   * <p>By default, this URL is only valid for a few seconds from the creation of the request for
   * this archive. Once a download has started it can not be stopped and resumed, instead a new
   * request for a zip archive would need to be created.
   *
   * <p>The URL of this endpoint should not be considered as fixed. Instead, use the [Create zip
   * download](e://post_zip_downloads) API to request to create a `zip` archive, and then follow the
   * `download_url` field in the response to this endpoint.
   *
   * @param downloadUrl The URL that can be used to download created `zip` archive. Example:
   *     `https://dl.boxcloud.com/2.0/zip_downloads/29l00nfxDyHOt7RphI9zT_w==nDnZEDjY2S8iEWWCHEEiptFxwoWojjlibZjJ6geuE5xnXENDTPxzgbks_yY=/content`
   * @param headers Headers of getZipDownloadContent method
   */
  public InputStream getZipDownloadContent(
      String downloadUrl, GetZipDownloadContentHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(downloadUrl, "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.BINARY)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return response.getContent();
  }

  /**
   * Returns the download status of a `zip` archive, allowing an application to inspect the progress
   * of the download as well as the number of items that might have been skipped.
   *
   * <p>This endpoint can only be accessed once the download has started. Subsequently this endpoint
   * is valid for 12 hours from the start of the download.
   *
   * <p>The URL of this endpoint should not be considered as fixed. Instead, use the [Create zip
   * download](e://post_zip_downloads) API to request to create a `zip` archive, and then follow the
   * `status_url` field in the response to this endpoint.
   *
   * @param statusUrl The URL that can be used to get the status of the `zip` archive being
   *     downloaded. Example:
   *     `https://dl.boxcloud.com/2.0/zip_downloads/29l00nfxDyHOt7RphI9zT_w==nDnZEDjY2S8iEWWCHEEiptFxwoWojjlibZjJ6geuE5xnXENDTPxzgbks_yY=/status`
   */
  public ZipDownloadStatus getZipDownloadStatus(String statusUrl) {
    return getZipDownloadStatus(statusUrl, new GetZipDownloadStatusHeaders());
  }

  /**
   * Returns the download status of a `zip` archive, allowing an application to inspect the progress
   * of the download as well as the number of items that might have been skipped.
   *
   * <p>This endpoint can only be accessed once the download has started. Subsequently this endpoint
   * is valid for 12 hours from the start of the download.
   *
   * <p>The URL of this endpoint should not be considered as fixed. Instead, use the [Create zip
   * download](e://post_zip_downloads) API to request to create a `zip` archive, and then follow the
   * `status_url` field in the response to this endpoint.
   *
   * @param statusUrl The URL that can be used to get the status of the `zip` archive being
   *     downloaded. Example:
   *     `https://dl.boxcloud.com/2.0/zip_downloads/29l00nfxDyHOt7RphI9zT_w==nDnZEDjY2S8iEWWCHEEiptFxwoWojjlibZjJ6geuE5xnXENDTPxzgbks_yY=/status`
   * @param headers Headers of getZipDownloadStatus method
   */
  public ZipDownloadStatus getZipDownloadStatus(
      String statusUrl, GetZipDownloadStatusHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(statusUrl, "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ZipDownloadStatus.class);
  }

  /**
   * Creates a zip and downloads its content
   *
   * @param requestBody Zip download request body
   */
  public InputStream downloadZip(ZipDownloadRequest requestBody) {
    return downloadZip(requestBody, new DownloadZipHeaders());
  }

  /**
   * Creates a zip and downloads its content
   *
   * @param requestBody Zip download request body
   * @param headers Headers of zip download method
   */
  public InputStream downloadZip(ZipDownloadRequest requestBody, DownloadZipHeaders headers) {
    ZipDownload zipDownloadSession =
        this.createZipDownload(
            new ZipDownloadRequest.Builder(requestBody.getItems())
                .downloadFileName(requestBody.getDownloadFileName())
                .build(),
            new CreateZipDownloadHeaders.Builder().extraHeaders(headers.getExtraHeaders()).build());
    return this.getZipDownloadContent(
        zipDownloadSession.getDownloadUrl(),
        new GetZipDownloadContentHeaders.Builder().extraHeaders(headers.getExtraHeaders()).build());
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

    public ZipDownloadsManager build() {
      return new ZipDownloadsManager(this);
    }
  }
}
