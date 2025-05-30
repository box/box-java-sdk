package com.box.sdkgen.managers.downloads;

public class DownloadFileToOutputStreamQueryParams {

  public String version;

  public String accessToken;

  public DownloadFileToOutputStreamQueryParams() {}

  protected DownloadFileToOutputStreamQueryParams(
      DownloadFileToOutputStreamQueryParamsBuilder builder) {
    this.version = builder.version;
    this.accessToken = builder.accessToken;
  }

  public String getVersion() {
    return version;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public static class DownloadFileToOutputStreamQueryParamsBuilder {

    protected String version;

    protected String accessToken;

    public DownloadFileToOutputStreamQueryParamsBuilder version(String version) {
      this.version = version;
      return this;
    }

    public DownloadFileToOutputStreamQueryParamsBuilder accessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public DownloadFileToOutputStreamQueryParams build() {
      return new DownloadFileToOutputStreamQueryParams(this);
    }
  }
}
