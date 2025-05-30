package com.box.sdkgen.managers.skills;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetBoxSkillCardsOnFileHeaders {

  public Map<String, String> extraHeaders;

  public GetBoxSkillCardsOnFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetBoxSkillCardsOnFileHeaders(GetBoxSkillCardsOnFileHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetBoxSkillCardsOnFileHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetBoxSkillCardsOnFileHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetBoxSkillCardsOnFileHeaders build() {
      return new GetBoxSkillCardsOnFileHeaders(this);
    }
  }
}
