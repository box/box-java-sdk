package com.box.sdkgen.managers.downloads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DownloadFileHeaders {

  /**
   * The byte range of the content to download.
   *
   * <p>The format `bytes={start_byte}-{end_byte}` can be used to specify what section of the file
   * to download.
   */
  public String range;

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

  public DownloadFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DownloadFileHeaders(Builder builder) {
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

  public static class Builder {

    protected String range;

    protected String boxapi;

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder range(String range) {
      this.range = range;
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

    public DownloadFileHeaders build() {
      return new DownloadFileHeaders(this);
    }
  }
}
