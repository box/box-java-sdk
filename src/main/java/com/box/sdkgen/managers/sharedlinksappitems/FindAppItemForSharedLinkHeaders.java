package com.box.sdkgen.managers.sharedlinksappitems;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class FindAppItemForSharedLinkHeaders {

  public final String boxapi;

  public Map<String, String> extraHeaders;

  public FindAppItemForSharedLinkHeaders(String boxapi) {
    this.boxapi = boxapi;
    this.extraHeaders = mapOf();
  }

  protected FindAppItemForSharedLinkHeaders(FindAppItemForSharedLinkHeadersBuilder builder) {
    this.boxapi = builder.boxapi;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getBoxapi() {
    return boxapi;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class FindAppItemForSharedLinkHeadersBuilder {

    protected final String boxapi;

    protected Map<String, String> extraHeaders;

    public FindAppItemForSharedLinkHeadersBuilder(String boxapi) {
      this.boxapi = boxapi;
      this.extraHeaders = mapOf();
    }

    public FindAppItemForSharedLinkHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public FindAppItemForSharedLinkHeaders build() {
      return new FindAppItemForSharedLinkHeaders(this);
    }
  }
}
