package com.box.sdkgen.managers.skills;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateBoxSkillCardsOnFileHeaders {

  public Map<String, String> extraHeaders;

  public CreateBoxSkillCardsOnFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateBoxSkillCardsOnFileHeaders(CreateBoxSkillCardsOnFileHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateBoxSkillCardsOnFileHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateBoxSkillCardsOnFileHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateBoxSkillCardsOnFileHeaders build() {
      return new CreateBoxSkillCardsOnFileHeaders(this);
    }
  }
}
