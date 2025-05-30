package com.box.sdkgen.managers.skills;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateBoxSkillCardsOnFileHeaders {

  public Map<String, String> extraHeaders;

  public UpdateBoxSkillCardsOnFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateBoxSkillCardsOnFileHeaders(UpdateBoxSkillCardsOnFileHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateBoxSkillCardsOnFileHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateBoxSkillCardsOnFileHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateBoxSkillCardsOnFileHeaders build() {
      return new UpdateBoxSkillCardsOnFileHeaders(this);
    }
  }
}
