package com.box.sdkgen.managers.downloads;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;
import static com.box.sdkgen.internal.utils.UtilsManager.writeInputStreamToOutputStream;

import com.box.sdkgen.box.errors.BoxSDKError;
import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class DownloadsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public DownloadsManager() {
    this.networkSession = new NetworkSession();
  }

  protected DownloadsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public String getDownloadFileUrl(String fileId) {
    return getDownloadFileUrl(
        fileId, new GetDownloadFileUrlQueryParams(), new GetDownloadFileUrlHeaders());
  }

  public String getDownloadFileUrl(String fileId, GetDownloadFileUrlQueryParams queryParams) {
    return getDownloadFileUrl(fileId, queryParams, new GetDownloadFileUrlHeaders());
  }

  public String getDownloadFileUrl(String fileId, GetDownloadFileUrlHeaders headers) {
    return getDownloadFileUrl(fileId, new GetDownloadFileUrlQueryParams(), headers);
  }

  public String getDownloadFileUrl(
      String fileId, GetDownloadFileUrlQueryParams queryParams, GetDownloadFileUrlHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("version", convertToString(queryParams.getVersion())),
                entryOf("access_token", convertToString(queryParams.getAccessToken()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(
                    entryOf("range", convertToString(headers.getRange())),
                    entryOf("boxapi", convertToString(headers.getBoxapi()))),
                headers.getExtraHeaders()));
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
                            "/content"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .followRedirects(false)
                    .build());
    if (response.getHeaders().containsKey("location")) {
      return response.getHeaders().get("location");
    }
    if (response.getHeaders().containsKey("Location")) {
      return response.getHeaders().get("Location");
    }
    throw new BoxSDKError("No location header in response");
  }

  public InputStream downloadFile(String fileId) {
    return downloadFile(fileId, new DownloadFileQueryParams(), new DownloadFileHeaders());
  }

  public InputStream downloadFile(String fileId, DownloadFileQueryParams queryParams) {
    return downloadFile(fileId, queryParams, new DownloadFileHeaders());
  }

  public InputStream downloadFile(String fileId, DownloadFileHeaders headers) {
    return downloadFile(fileId, new DownloadFileQueryParams(), headers);
  }

  public InputStream downloadFile(
      String fileId, DownloadFileQueryParams queryParams, DownloadFileHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("version", convertToString(queryParams.getVersion())),
                entryOf("access_token", convertToString(queryParams.getAccessToken()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(
                    entryOf("range", convertToString(headers.getRange())),
                    entryOf("boxapi", convertToString(headers.getBoxapi()))),
                headers.getExtraHeaders()));
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
                            "/content"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.BINARY)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    if (convertToString(response.getStatus()).equals("202")) {
      return null;
    }
    return response.getContent();
  }

  public void downloadFileToOutputStream(String fileId, OutputStream outputStream) {
    downloadFileToOutputStream(
        fileId,
        outputStream,
        new DownloadFileToOutputStreamQueryParams(),
        new DownloadFileToOutputStreamHeaders());
  }

  public void downloadFileToOutputStream(
      String fileId, OutputStream outputStream, DownloadFileToOutputStreamQueryParams queryParams) {
    downloadFileToOutputStream(
        fileId, outputStream, queryParams, new DownloadFileToOutputStreamHeaders());
  }

  public void downloadFileToOutputStream(
      String fileId, OutputStream outputStream, DownloadFileToOutputStreamHeaders headers) {
    downloadFileToOutputStream(
        fileId, outputStream, new DownloadFileToOutputStreamQueryParams(), headers);
  }

  public void downloadFileToOutputStream(
      String fileId,
      OutputStream outputStream,
      DownloadFileToOutputStreamQueryParams queryParams,
      DownloadFileToOutputStreamHeaders headers) {
    InputStream downloadStream =
        this.downloadFile(
            fileId,
            new DownloadFileQueryParams.Builder()
                .version(queryParams.getVersion())
                .accessToken(queryParams.getAccessToken())
                .build(),
            new DownloadFileHeaders.Builder()
                .range(headers.getRange())
                .boxapi(headers.getBoxapi())
                .extraHeaders(headers.getExtraHeaders())
                .build());
    writeInputStreamToOutputStream(downloadStream, outputStream);
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

    public DownloadsManager build() {
      return new DownloadsManager(this);
    }
  }
}
