package com.box.sdkgen.managers.files;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileByIdHeaders {

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

  /**
   * A header required to request specific `representations` of a file. Use this in combination with
   * the `fields` query parameter to request a specific file representation.
   *
   * <p>The general format for these representations is `X-Rep-Hints: [...]` where `[...]` is one or
   * many hints in the format `[fileType?query]`.
   *
   * <p>For example, to request a `png` representation in `32x32` as well as `64x64` pixel
   * dimensions provide the following hints.
   *
   * <p>`x-rep-hints: [jpg?dimensions=32x32][jpg?dimensions=64x64]`
   *
   * <p>Additionally, a `text` representation is available for all document file types in Box using
   * the `[extracted_text]` representation.
   *
   * <p>`x-rep-hints: [extracted_text]`.
   */
  public String xRepHints;

  /** Extra headers that will be included in the HTTP request. */
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
