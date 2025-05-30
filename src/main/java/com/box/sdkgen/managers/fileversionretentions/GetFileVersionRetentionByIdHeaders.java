package com.box.sdkgen.managers.fileversionretentions;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileVersionRetentionByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetFileVersionRetentionByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileVersionRetentionByIdHeaders(GetFileVersionRetentionByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileVersionRetentionByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileVersionRetentionByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileVersionRetentionByIdHeaders build() {
      return new GetFileVersionRetentionByIdHeaders(this);
    }
  }
}
