package com.box.sdkgen.managers.aistudio;

import java.util.List;

public class GetAiAgentByIdQueryParams {

  /** The fields to return in the response. */
  public List<String> fields;

  public GetAiAgentByIdQueryParams() {}

  protected GetAiAgentByIdQueryParams(Builder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class Builder {

    protected List<String> fields;

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetAiAgentByIdQueryParams build() {
      return new GetAiAgentByIdQueryParams(this);
    }
  }
}
