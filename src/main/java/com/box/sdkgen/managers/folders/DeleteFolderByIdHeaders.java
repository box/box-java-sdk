package com.box.sdkgen.managers.folders;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteFolderByIdHeaders {

  public String ifMatch;

  public Map<String, String> extraHeaders;

  public DeleteFolderByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteFolderByIdHeaders(Builder builder) {
    this.ifMatch = builder.ifMatch;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getIfMatch() {
    return ifMatch;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected String ifMatch;

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder ifMatch(String ifMatch) {
      this.ifMatch = ifMatch;
      return this;
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteFolderByIdHeaders build() {
      return new DeleteFolderByIdHeaders(this);
    }
  }
}
