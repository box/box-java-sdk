package com.box.sdkgen.managers.weblinks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetWebLinkByIdHeaders {

  public String boxapi;

  public Map<String, String> extraHeaders;

  public GetWebLinkByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetWebLinkByIdHeaders(GetWebLinkByIdHeadersBuilder builder) {
    this.boxapi = builder.boxapi;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getBoxapi() {
    return boxapi;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetWebLinkByIdHeadersBuilder {

    protected String boxapi;

    protected Map<String, String> extraHeaders;

    public GetWebLinkByIdHeadersBuilder boxapi(String boxapi) {
      this.boxapi = boxapi;
      return this;
    }

    public GetWebLinkByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetWebLinkByIdHeaders build() {
      return new GetWebLinkByIdHeaders(this);
    }
  }
}
