package com.box.sdkgen.managers.sharedlinksweblinks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class AddShareLinkToWebLinkHeaders {

  public Map<String, String> extraHeaders;

  public AddShareLinkToWebLinkHeaders() {
    this.extraHeaders = mapOf();
  }

  protected AddShareLinkToWebLinkHeaders(AddShareLinkToWebLinkHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class AddShareLinkToWebLinkHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public AddShareLinkToWebLinkHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public AddShareLinkToWebLinkHeaders build() {
      return new AddShareLinkToWebLinkHeaders(this);
    }
  }
}
