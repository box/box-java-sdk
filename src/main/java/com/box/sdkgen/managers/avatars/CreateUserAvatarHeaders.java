package com.box.sdkgen.managers.avatars;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateUserAvatarHeaders {

  public Map<String, String> extraHeaders;

  public CreateUserAvatarHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateUserAvatarHeaders(Builder builder) {
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

    public CreateUserAvatarHeaders build() {
      return new CreateUserAvatarHeaders(this);
    }
  }
}
