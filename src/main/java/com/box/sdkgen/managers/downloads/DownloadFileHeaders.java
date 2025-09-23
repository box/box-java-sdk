package com.box.sdkgen.managers.downloads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DownloadFileHeaders {

  public String range;

  public String boxapi;

  public Map<String, String> extraHeaders;

  public DownloadFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DownloadFileHeaders(Builder builder) {
    this.range = builder.range;
    this.boxapi = builder.boxapi;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getRange() {
    return range;
  }

  public String getBoxapi() {
    return boxapi;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected String range;

    protected String boxapi;

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder range(String range) {
      this.range = range;
      return this;
    }

    public Builder boxapi(String boxapi) {
      this.boxapi = boxapi;
      return this;
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DownloadFileHeaders build() {
      return new DownloadFileHeaders(this);
    }
  }
}
