package com.box.sdkgen.managers.invites;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetInviteByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetInviteByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetInviteByIdHeaders(GetInviteByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetInviteByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetInviteByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetInviteByIdHeaders build() {
      return new GetInviteByIdHeaders(this);
    }
  }
}
