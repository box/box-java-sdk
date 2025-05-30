package com.box.sdkgen.managers.avatars;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetUserAvatarHeaders {

  public Map<String, String> extraHeaders;

  public GetUserAvatarHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetUserAvatarHeaders(GetUserAvatarHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetUserAvatarHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetUserAvatarHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetUserAvatarHeaders build() {
      return new GetUserAvatarHeaders(this);
    }
  }
}
