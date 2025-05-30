package com.box.sdkgen.managers.downloads;

public class GetDownloadFileUrlQueryParams {

  public String version;

  public String accessToken;

  public GetDownloadFileUrlQueryParams() {}

  protected GetDownloadFileUrlQueryParams(GetDownloadFileUrlQueryParamsBuilder builder) {
    this.version = builder.version;
    this.accessToken = builder.accessToken;
  }

  public String getVersion() {
    return version;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public static class GetDownloadFileUrlQueryParamsBuilder {

    protected String version;

    protected String accessToken;

    public GetDownloadFileUrlQueryParamsBuilder version(String version) {
      this.version = version;
      return this;
    }

    public GetDownloadFileUrlQueryParamsBuilder accessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public GetDownloadFileUrlQueryParams build() {
      return new GetDownloadFileUrlQueryParams(this);
    }
  }
}
