package com.box.sdkgen.managers.sessiontermination;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class TerminateUsersSessionsHeaders {

  public Map<String, String> extraHeaders;

  public TerminateUsersSessionsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected TerminateUsersSessionsHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public TerminateUsersSessionsHeaders build() {
      return new TerminateUsersSessionsHeaders(this);
    }
  }
}
