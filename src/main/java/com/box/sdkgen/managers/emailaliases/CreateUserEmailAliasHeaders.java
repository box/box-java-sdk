package com.box.sdkgen.managers.emailaliases;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateUserEmailAliasHeaders {

  public Map<String, String> extraHeaders;

  public CreateUserEmailAliasHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateUserEmailAliasHeaders(CreateUserEmailAliasHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateUserEmailAliasHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateUserEmailAliasHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateUserEmailAliasHeaders build() {
      return new CreateUserEmailAliasHeaders(this);
    }
  }
}
