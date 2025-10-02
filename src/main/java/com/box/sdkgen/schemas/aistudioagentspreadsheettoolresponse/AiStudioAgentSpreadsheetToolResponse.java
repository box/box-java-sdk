package com.box.sdkgen.schemas.aistudioagentspreadsheettoolresponse;

import com.box.sdkgen.schemas.aillmendpointparams.AiLlmEndpointParams;
import com.box.sdkgen.schemas.aillmendpointparamsaws.AiLlmEndpointParamsAws;
import com.box.sdkgen.schemas.aillmendpointparamsgoogle.AiLlmEndpointParamsGoogle;
import com.box.sdkgen.schemas.aillmendpointparamsibm.AiLlmEndpointParamsIbm;
import com.box.sdkgen.schemas.aillmendpointparamsopenai.AiLlmEndpointParamsOpenAi;
import com.box.sdkgen.schemas.aistudioagentspreadsheettool.AiStudioAgentSpreadsheetTool;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import java.util.Objects;

/** The AI agent tool used to handle spreadsheets and tabular data. */
@JsonFilter("nullablePropertyFilter")
public class AiStudioAgentSpreadsheetToolResponse extends AiStudioAgentSpreadsheetTool {

  /** Warnings concerning tool. */
  protected List<String> warnings;

  public AiStudioAgentSpreadsheetToolResponse() {
    super();
  }

  protected AiStudioAgentSpreadsheetToolResponse(Builder builder) {
    super(builder);
    this.warnings = builder.warnings;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<String> getWarnings() {
    return warnings;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiStudioAgentSpreadsheetToolResponse casted = (AiStudioAgentSpreadsheetToolResponse) o;
    return Objects.equals(model, casted.model)
        && Objects.equals(numTokensForCompletion, casted.numTokensForCompletion)
        && Objects.equals(llmEndpointParams, casted.llmEndpointParams)
        && Objects.equals(warnings, casted.warnings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, numTokensForCompletion, llmEndpointParams, warnings);
  }

  @Override
  public String toString() {
    return "AiStudioAgentSpreadsheetToolResponse{"
        + "model='"
        + model
        + '\''
        + ", "
        + "numTokensForCompletion='"
        + numTokensForCompletion
        + '\''
        + ", "
        + "llmEndpointParams='"
        + llmEndpointParams
        + '\''
        + ", "
        + "warnings='"
        + warnings
        + '\''
        + "}";
  }

  public static class Builder extends AiStudioAgentSpreadsheetTool.Builder {

    protected List<String> warnings;

    public Builder warnings(List<String> warnings) {
      this.warnings = warnings;
      return this;
    }

    @Override
    public Builder model(String model) {
      this.model = model;
      return this;
    }

    @Override
    public Builder numTokensForCompletion(Long numTokensForCompletion) {
      this.numTokensForCompletion = numTokensForCompletion;
      return this;
    }

    @Override
    public Builder llmEndpointParams(AiLlmEndpointParamsOpenAi llmEndpointParams) {
      this.llmEndpointParams = new AiLlmEndpointParams(llmEndpointParams);
      return this;
    }

    @Override
    public Builder llmEndpointParams(AiLlmEndpointParamsGoogle llmEndpointParams) {
      this.llmEndpointParams = new AiLlmEndpointParams(llmEndpointParams);
      return this;
    }

    @Override
    public Builder llmEndpointParams(AiLlmEndpointParamsAws llmEndpointParams) {
      this.llmEndpointParams = new AiLlmEndpointParams(llmEndpointParams);
      return this;
    }

    @Override
    public Builder llmEndpointParams(AiLlmEndpointParamsIbm llmEndpointParams) {
      this.llmEndpointParams = new AiLlmEndpointParams(llmEndpointParams);
      return this;
    }

    @Override
    public Builder llmEndpointParams(AiLlmEndpointParams llmEndpointParams) {
      this.llmEndpointParams = llmEndpointParams;
      return this;
    }

    public AiStudioAgentSpreadsheetToolResponse build() {
      return new AiStudioAgentSpreadsheetToolResponse(this);
    }
  }
}
