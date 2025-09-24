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

  public ZipDownload createZipDownload(ZipDownloadRequest requestBody) {
    return createZipDownload(requestBody, new CreateZipDownloadHeaders());
  }

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

  public InputStream getZipDownloadContent(String downloadUrl) {
    return getZipDownloadContent(downloadUrl, new GetZipDownloadContentHeaders());
  }

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

  public ZipDownloadStatus getZipDownloadStatus(String statusUrl) {
    return getZipDownloadStatus(statusUrl, new GetZipDownloadStatusHeaders());
  }

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

  public InputStream downloadZip(ZipDownloadRequest requestBody) {
    return downloadZip(requestBody, new DownloadZipHeaders());
  }

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
