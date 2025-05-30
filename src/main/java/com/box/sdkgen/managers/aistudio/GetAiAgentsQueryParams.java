package com.box.sdkgen.managers.aistudio;

import java.util.List;

public class GetAiAgentsQueryParams {

  public List<String> mode;

  public List<String> fields;

  public List<String> agentState;

  public Boolean includeBoxDefault;

  public String marker;

  public Long limit;

  public GetAiAgentsQueryParams() {}

  protected GetAiAgentsQueryParams(GetAiAgentsQueryParamsBuilder builder) {
    this.mode = builder.mode;
    this.fields = builder.fields;
    this.agentState = builder.agentState;
    this.includeBoxDefault = builder.includeBoxDefault;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public List<String> getMode() {
    return mode;
  }

  public List<String> getFields() {
    return fields;
  }

  public List<String> getAgentState() {
    return agentState;
  }

  public Boolean getIncludeBoxDefault() {
    return includeBoxDefault;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class GetAiAgentsQueryParamsBuilder {

    protected List<String> mode;

    protected List<String> fields;

    protected List<String> agentState;

    protected Boolean includeBoxDefault;

    protected String marker;

    protected Long limit;

    public GetAiAgentsQueryParamsBuilder mode(List<String> mode) {
      this.mode = mode;
      return this;
    }

    public GetAiAgentsQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetAiAgentsQueryParamsBuilder agentState(List<String> agentState) {
      this.agentState = agentState;
      return this;
    }

    public GetAiAgentsQueryParamsBuilder includeBoxDefault(Boolean includeBoxDefault) {
      this.includeBoxDefault = includeBoxDefault;
      return this;
    }

    public GetAiAgentsQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetAiAgentsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetAiAgentsQueryParams build() {
      return new GetAiAgentsQueryParams(this);
    }
  }
}
