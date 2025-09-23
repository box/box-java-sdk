package com.box.sdkgen.managers.sharedlinksfiles;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class FindFileForSharedLinkHeaders {

  public String ifNoneMatch;

  public final String boxapi;

  public Map<String, String> extraHeaders;

  public FindFileForSharedLinkHeaders(String boxapi) {
    this.boxapi = boxapi;
    this.extraHeaders = mapOf();
  }

  protected FindFileForSharedLinkHeaders(Builder builder) {
    this.ifNoneMatch = builder.ifNoneMatch;
    this.boxapi = builder.boxapi;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getIfNoneMatch() {
    return ifNoneMatch;
  }

  public String getBoxapi() {
    return boxapi;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected String ifNoneMatch;

    protected final String boxapi;

    protected Map<String, String> extraHeaders;

    public Builder(String boxapi) {
      this.boxapi = boxapi;
      this.extraHeaders = mapOf();
    }

    public Builder ifNoneMatch(String ifNoneMatch) {
      this.ifNoneMatch = ifNoneMatch;
      return this;
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public FindFileForSharedLinkHeaders build() {
      return new FindFileForSharedLinkHeaders(this);
    }
  }
}
