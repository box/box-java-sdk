package com.box.sdkgen.managers.sessiontermination;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class TerminateUsersSessionsHeaders {

  public Map<String, String> extraHeaders;

  public TerminateUsersSessionsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected TerminateUsersSessionsHeaders(TerminateUsersSessionsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class TerminateUsersSessionsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public TerminateUsersSessionsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public TerminateUsersSessionsHeaders build() {
      return new TerminateUsersSessionsHeaders(this);
    }
  }
}
