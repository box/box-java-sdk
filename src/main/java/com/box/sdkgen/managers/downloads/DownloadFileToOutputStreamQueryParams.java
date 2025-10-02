package com.box.sdkgen.managers.downloads;

public class DownloadFileToOutputStreamQueryParams {

  /** The file version to download. */
  public String version;

  /**
   * An optional access token that can be used to pre-authenticate this request, which means that a
   * download link can be shared with a browser or a third party service without them needing to
   * know how to handle the authentication. When using this parameter, please make sure that the
   * access token is sufficiently scoped down to only allow read access to that file and no other
   * files or folders.
   */
  public String accessToken;

  public DownloadFileToOutputStreamQueryParams() {}

  protected DownloadFileToOutputStreamQueryParams(Builder builder) {
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

    public DownloadFileToOutputStreamQueryParams build() {
      return new DownloadFileToOutputStreamQueryParams(this);
    }
  }
}
