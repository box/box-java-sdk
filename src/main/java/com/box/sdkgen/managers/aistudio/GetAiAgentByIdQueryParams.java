package com.box.sdkgen.managers.aistudio;

import java.util.List;

public class GetAiAgentByIdQueryParams {

  public List<String> fields;

  public GetAiAgentByIdQueryParams() {}

  protected GetAiAgentByIdQueryParams(GetAiAgentByIdQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class GetAiAgentByIdQueryParamsBuilder {

    protected List<String> fields;

    public GetAiAgentByIdQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetAiAgentByIdQueryParams build() {
      return new GetAiAgentByIdQueryParams(this);
    }
  }
}
