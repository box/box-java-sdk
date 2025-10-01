package com.box.sdkgen.managers.ai;

import com.box.sdkgen.serialization.json.EnumWrapper;

public class GetAiAgentDefaultConfigQueryParams {

  /** The mode to filter the agent config to return. */
  public final EnumWrapper<GetAiAgentDefaultConfigQueryParamsModeField> mode;

  /**
   * The ISO language code to return the agent config for. If the language is not supported the
   * default agent config is returned.
   */
  public String language;

  /** The model to return the default agent config for. */
  public String model;

  public GetAiAgentDefaultConfigQueryParams(GetAiAgentDefaultConfigQueryParamsModeField mode) {
    this.mode = new EnumWrapper<GetAiAgentDefaultConfigQueryParamsModeField>(mode);
  }

  public GetAiAgentDefaultConfigQueryParams(
      EnumWrapper<GetAiAgentDefaultConfigQueryParamsModeField> mode) {
    this.mode = mode;
  }

  protected GetAiAgentDefaultConfigQueryParams(Builder builder) {
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

  public static class Builder {

    protected final EnumWrapper<GetAiAgentDefaultConfigQueryParamsModeField> mode;

    protected String language;

    protected String model;

    public Builder(GetAiAgentDefaultConfigQueryParamsModeField mode) {
      this.mode = new EnumWrapper<GetAiAgentDefaultConfigQueryParamsModeField>(mode);
    }

    public Builder(EnumWrapper<GetAiAgentDefaultConfigQueryParamsModeField> mode) {
      this.mode = mode;
    }

    public Builder language(String language) {
      this.language = language;
      return this;
    }

    public Builder model(String model) {
      this.model = model;
      return this;
    }

    public GetAiAgentDefaultConfigQueryParams build() {
      return new GetAiAgentDefaultConfigQueryParams(this);
    }
  }
}
