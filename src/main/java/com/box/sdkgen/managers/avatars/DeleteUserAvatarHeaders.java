package com.box.sdkgen.managers.avatars;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteUserAvatarHeaders {

  public Map<String, String> extraHeaders;

  public DeleteUserAvatarHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteUserAvatarHeaders(Builder builder) {
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

    public DeleteUserAvatarHeaders build() {
      return new DeleteUserAvatarHeaders(this);
    }
  }
}
