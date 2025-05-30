package com.box.sdkgen.managers.invites;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateInviteHeaders {

  public Map<String, String> extraHeaders;

  public CreateInviteHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateInviteHeaders(CreateInviteHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateInviteHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateInviteHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateInviteHeaders build() {
      return new CreateInviteHeaders(this);
    }
  }
}
