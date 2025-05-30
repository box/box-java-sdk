package com.box.sdkgen.managers.sharedlinksfiles;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class AddShareLinkToFileHeaders {

  public Map<String, String> extraHeaders;

  public AddShareLinkToFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected AddShareLinkToFileHeaders(AddShareLinkToFileHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class AddShareLinkToFileHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public AddShareLinkToFileHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public AddShareLinkToFileHeaders build() {
      return new AddShareLinkToFileHeaders(this);
    }
  }
}
