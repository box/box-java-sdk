package com.box.sdkgen.managers.files;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileByIdHeaders {

  public String ifNoneMatch;

  public String boxapi;

  public String xRepHints;

  public Map<String, String> extraHeaders;

  public GetFileByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileByIdHeaders(Builder builder) {
    this.ifNoneMatch = builder.ifNoneMatch;
    this.boxapi = builder.boxapi;
    this.xRepHints = builder.xRepHints;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getIfNoneMatch() {
    return ifNoneMatch;
  }

  public String getBoxapi() {
    return boxapi;
  }

  public String getXRepHints() {
    return xRepHints;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected String ifNoneMatch;

    protected String boxapi;

    protected String xRepHints;

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder ifNoneMatch(String ifNoneMatch) {
      this.ifNoneMatch = ifNoneMatch;
      return this;
    }

    public Builder boxapi(String boxapi) {
      this.boxapi = boxapi;
      return this;
    }

    public Builder xRepHints(String xRepHints) {
      this.xRepHints = xRepHints;
      return this;
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileByIdHeaders build() {
      return new GetFileByIdHeaders(this);
    }
  }
}
