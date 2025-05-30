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

  protected DownloadFileHeaders(DownloadFileHeadersBuilder builder) {
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

  public static class DownloadFileHeadersBuilder {

    protected String range;

    protected String boxapi;

    protected Map<String, String> extraHeaders;

    public DownloadFileHeadersBuilder range(String range) {
      this.range = range;
      return this;
    }

    public DownloadFileHeadersBuilder boxapi(String boxapi) {
      this.boxapi = boxapi;
      return this;
    }

    public DownloadFileHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DownloadFileHeaders build() {
      return new DownloadFileHeaders(this);
    }
  }
}
