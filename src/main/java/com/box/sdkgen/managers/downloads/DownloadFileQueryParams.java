package com.box.sdkgen.managers.downloads;

public class DownloadFileQueryParams {

  public String version;

  public String accessToken;

  public DownloadFileQueryParams() {}

  protected DownloadFileQueryParams(DownloadFileQueryParamsBuilder builder) {
    this.version = builder.version;
    this.accessToken = builder.accessToken;
  }

  public String getVersion() {
    return version;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public static class DownloadFileQueryParamsBuilder {

    protected String version;

    protected String accessToken;

    public DownloadFileQueryParamsBuilder version(String version) {
      this.version = version;
      return this;
    }

    public DownloadFileQueryParamsBuilder accessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public DownloadFileQueryParams build() {
      return new DownloadFileQueryParams(this);
    }
  }
}
