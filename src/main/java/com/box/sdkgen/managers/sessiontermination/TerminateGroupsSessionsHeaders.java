package com.box.sdkgen.managers.sessiontermination;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class TerminateGroupsSessionsHeaders {

  public Map<String, String> extraHeaders;

  public TerminateGroupsSessionsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected TerminateGroupsSessionsHeaders(TerminateGroupsSessionsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class TerminateGroupsSessionsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public TerminateGroupsSessionsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public TerminateGroupsSessionsHeaders build() {
      return new TerminateGroupsSessionsHeaders(this);
    }
  }
}
