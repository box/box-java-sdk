package com.box.sdkgen.managers.downloads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetDownloadFileUrlHeaders {

  public String range;

  public String boxapi;

  public Map<String, String> extraHeaders;

  public GetDownloadFileUrlHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetDownloadFileUrlHeaders(GetDownloadFileUrlHeadersBuilder builder) {
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

  public static class GetDownloadFileUrlHeadersBuilder {

    protected String range;

    protected String boxapi;

    protected Map<String, String> extraHeaders;

    public GetDownloadFileUrlHeadersBuilder range(String range) {
      this.range = range;
      return this;
    }

    public GetDownloadFileUrlHeadersBuilder boxapi(String boxapi) {
      this.boxapi = boxapi;
      return this;
    }

    public GetDownloadFileUrlHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetDownloadFileUrlHeaders build() {
      return new GetDownloadFileUrlHeaders(this);
    }
  }
}
