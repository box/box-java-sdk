package com.box.sdkgen.managers.ai;

import com.box.sdkgen.serialization.json.EnumWrapper;

public class GetAiAgentDefaultConfigQueryParams {

  public final EnumWrapper<GetAiAgentDefaultConfigQueryParamsModeField> mode;

  public String language;

  public String model;

  public GetAiAgentDefaultConfigQueryParams(
      EnumWrapper<GetAiAgentDefaultConfigQueryParamsModeField> mode) {
    this.mode = mode;
  }

  public GetAiAgentDefaultConfigQueryParams(GetAiAgentDefaultConfigQueryParamsModeField mode) {
    this.mode = new EnumWrapper<GetAiAgentDefaultConfigQueryParamsModeField>(mode);
  }

  protected GetAiAgentDefaultConfigQueryParams(GetAiAgentDefaultConfigQueryParamsBuilder builder) {
    this.mode = builder.mode;
    this.language = builder.language;
    this.model = builder.model;
  }

  public EnumWrapper<GetAiAgentDefaultConfigQueryParamsModeField> getMode() {
    return mode;
  }

  public String getLanguage() {
    return language;
  }

  public String getModel() {
    return model;
  }

  public static class GetAiAgentDefaultConfigQueryParamsBuilder {

    protected final EnumWrapper<GetAiAgentDefaultConfigQueryParamsModeField> mode;

    protected String language;

    protected String model;

    public GetAiAgentDefaultConfigQueryParamsBuilder(
        EnumWrapper<GetAiAgentDefaultConfigQueryParamsModeField> mode) {
      this.mode = mode;
    }

    public GetAiAgentDefaultConfigQueryParamsBuilder(
        GetAiAgentDefaultConfigQueryParamsModeField mode) {
      this.mode = new EnumWrapper<GetAiAgentDefaultConfigQueryParamsModeField>(mode);
    }

    public GetAiAgentDefaultConfigQueryParamsBuilder language(String language) {
      this.language = language;
      return this;
    }

    public GetAiAgentDefaultConfigQueryParamsBuilder model(String model) {
      this.model = model;
      return this;
    }

    public GetAiAgentDefaultConfigQueryParams build() {
      return new GetAiAgentDefaultConfigQueryParams(this);
    }
  }
}
