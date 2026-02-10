package com.box.sdkgen.managers.sharedlinksappitems;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class FindAppItemForSharedLinkHeaders {

  /**
   * A header containing the shared link and optional password for the shared link.
   *
   * <p>The format for this header is `shared_link=[link]&amp;shared_link_password=[password]`.
   */
  public final String boxapi;

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public FindAppItemForSharedLinkHeaders(String boxapi) {
    this.boxapi = boxapi;
    this.extraHeaders = mapOf();
  }

  protected FindAppItemForSharedLinkHeaders(Builder builder) {
    this.boxapi = builder.boxapi;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getBoxapi() {
    return boxapi;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected final String boxapi;

    protected Map<String, String> extraHeaders;

    public Builder(String boxapi) {
      this.boxapi = boxapi;
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public FindAppItemForSharedLinkHeaders build() {
      if (this.extraHeaders == null) {
        this.extraHeaders = mapOf();
      }
      return new FindAppItemForSharedLinkHeaders(this);
    }
  }
}
