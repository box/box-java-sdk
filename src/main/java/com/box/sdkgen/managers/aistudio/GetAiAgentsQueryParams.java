package com.box.sdkgen.managers.aistudio;

import java.util.List;

public class GetAiAgentsQueryParams {

  /**
   * The mode to filter the agent config to return. Possible values are: `ask`, `text_gen`, and
   * `extract`.
   */
  public List<String> mode;

  /** The fields to return in the response. */
  public List<String> fields;

  /**
   * The state of the agents to return. Possible values are: `enabled`, `disabled` and
   * `enabled_for_selected_users`.
   */
  public List<String> agentState;

  /** Whether to include the Box default agents in the response. */
  public Boolean includeBoxDefault;

  /** Defines the position marker at which to begin returning results. */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  public GetAiAgentsQueryParams() {}

  protected GetAiAgentsQueryParams(Builder builder) {
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

  public static class Builder {

    protected List<String> mode;

    protected List<String> fields;

    protected List<String> agentState;

    protected Boolean includeBoxDefault;

    protected String marker;

    protected Long limit;

    public Builder mode(List<String> mode) {
      this.mode = mode;
      return this;
    }

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public Builder agentState(List<String> agentState) {
      this.agentState = agentState;
      return this;
    }

    public Builder includeBoxDefault(Boolean includeBoxDefault) {
      this.includeBoxDefault = includeBoxDefault;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetAiAgentsQueryParams build() {
      return new GetAiAgentsQueryParams(this);
    }
  }
}
