package com.box.sdkgen.managers.appitemassociations;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFolderAppItemAssociationsHeaders {

  public Map<String, String> extraHeaders;

  public GetFolderAppItemAssociationsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFolderAppItemAssociationsHeaders(
      GetFolderAppItemAssociationsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFolderAppItemAssociationsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFolderAppItemAssociationsHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFolderAppItemAssociationsHeaders build() {
      return new GetFolderAppItemAssociationsHeaders(this);
    }
  }
}
