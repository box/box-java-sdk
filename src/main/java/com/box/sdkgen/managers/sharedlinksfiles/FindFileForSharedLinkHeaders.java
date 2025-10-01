package com.box.sdkgen.managers.sharedlinksfiles;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class FindFileForSharedLinkHeaders {

  /**
   * Ensures an item is only returned if it has changed.
   *
   * <p>Pass in the item's last observed `etag` value into this header and the endpoint will fail
   * with a `304 Not Modified` if the item has not changed since.
   */
  public String ifNoneMatch;

  /**
   * A header containing the shared link and optional password for the shared link.
   *
   * <p>The format for this header is as follows:
   *
   * <p>`shared_link=[link]&amp;shared_link_password=[password]`.
   */
  public final String boxapi;

  /** Extra headers that will be included in the HTTP request. */
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
