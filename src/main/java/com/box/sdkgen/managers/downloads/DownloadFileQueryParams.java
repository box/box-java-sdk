package com.box.sdkgen.managers.downloads;

public class DownloadFileQueryParams {

  public String version;

  public String accessToken;

  public DownloadFileQueryParams() {}

  protected DownloadFileQueryParams(Builder builder) {
    this.version = builder.version;
    this.accessToken = builder.accessToken;
  }

  public String getVersion() {
    return version;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public static class Builder {

    protected String version;

    protected String accessToken;

    public Builder version(String version) {
      this.version = version;
      return this;
    }

    public Builder accessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public DownloadFileQueryParams build() {
      return new DownloadFileQueryParams(this);
    }
  }
}
