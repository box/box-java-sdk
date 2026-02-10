package com.box.sdkgen.managers.folders;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFolderByIdHeaders {

  /**
   * Ensures an item is only returned if it has changed.
   *
   * <p>Pass in the item's last observed `etag` value into this header and the endpoint will fail
   * with a `304 Not Modified` if the item has not changed since.
   */
  public String ifNoneMatch;

  /**
   * The URL, and optional password, for the shared link of this item.
   *
   * <p>This header can be used to access items that have not been explicitly shared with a user.
   *
   * <p>Use the format `shared_link=[link]` or if a password is required then use
   * `shared_link=[link]&amp;shared_link_password=[password]`.
   *
   * <p>This header can be used on the file or folder shared, as well as on any files or folders
   * nested within the item.
   */
  public String boxapi;

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public GetFolderByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFolderByIdHeaders(Builder builder) {
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

    protected String boxapi;

    protected Map<String, String> extraHeaders;

    public Builder() {}

    public Builder ifNoneMatch(String ifNoneMatch) {
      this.ifNoneMatch = ifNoneMatch;
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

    public GetFolderByIdHeaders build() {
      if (this.extraHeaders == null) {
        this.extraHeaders = mapOf();
      }
      return new GetFolderByIdHeaders(this);
    }
  }
}
