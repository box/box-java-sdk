package com.box.sdkgen.managers.sharedlinksweblinks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class FindWebLinkForSharedLinkHeaders {

  public String ifNoneMatch;

  public final String boxapi;

  public Map<String, String> extraHeaders;

  public FindWebLinkForSharedLinkHeaders(String boxapi) {
    this.boxapi = boxapi;
    this.extraHeaders = mapOf();
  }

  protected FindWebLinkForSharedLinkHeaders(FindWebLinkForSharedLinkHeadersBuilder builder) {
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

  public static class FindWebLinkForSharedLinkHeadersBuilder {

    protected String ifNoneMatch;

    protected final String boxapi;

    protected Map<String, String> extraHeaders;

    public FindWebLinkForSharedLinkHeadersBuilder(String boxapi) {
      this.boxapi = boxapi;
      this.extraHeaders = mapOf();
    }

    public FindWebLinkForSharedLinkHeadersBuilder ifNoneMatch(String ifNoneMatch) {
      this.ifNoneMatch = ifNoneMatch;
      return this;
    }

    public FindWebLinkForSharedLinkHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public FindWebLinkForSharedLinkHeaders build() {
      return new FindWebLinkForSharedLinkHeaders(this);
    }
  }
}
