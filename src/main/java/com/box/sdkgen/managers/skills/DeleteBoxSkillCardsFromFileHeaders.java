package com.box.sdkgen.managers.skills;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteBoxSkillCardsFromFileHeaders {

  public Map<String, String> extraHeaders;

  public DeleteBoxSkillCardsFromFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteBoxSkillCardsFromFileHeaders(Builder builder) {
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

    public DeleteBoxSkillCardsFromFileHeaders build() {
      return new DeleteBoxSkillCardsFromFileHeaders(this);
    }
  }
}
