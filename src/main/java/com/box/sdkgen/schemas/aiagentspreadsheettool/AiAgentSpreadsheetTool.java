package com.box.sdkgen.schemas.aiagentspreadsheettool;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aillmendpointparams.AiLlmEndpointParams;
import com.box.sdkgen.schemas.aillmendpointparamsaws.AiLlmEndpointParamsAws;
import com.box.sdkgen.schemas.aillmendpointparamsgoogle.AiLlmEndpointParamsGoogle;
import com.box.sdkgen.schemas.aillmendpointparamsibm.AiLlmEndpointParamsIbm;
import com.box.sdkgen.schemas.aillmendpointparamsopenai.AiLlmEndpointParamsOpenAi;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** The AI agent tool used to handle spreadsheets and tabular data. */
@JsonFilter("nullablePropertyFilter")
public class AiAgentSpreadsheetTool extends SerializableObject {

  /**
   * The model used for the AI agent for spreadsheets. For specific model values, see the [available
   * models list](g://box-ai/supported-models).
   */
  protected String model;

  /** The number of tokens for completion. */
  @JsonProperty("num_tokens_for_completion")
  protected Long numTokensForCompletion;

  @JsonProperty("llm_endpoint_params")
  protected AiLlmEndpointParams llmEndpointParams;

  public AiAgentSpreadsheetTool() {
    super();
  }

  protected AiAgentSpreadsheetTool(Builder builder) {
    super();
    this.model = builder.model;
    this.numTokensForCompletion = builder.numTokensForCompletion;
    this.llmEndpointParams = builder.llmEndpointParams;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getModel() {
    return model;
  }

  public Long getNumTokensForCompletion() {
    return numTokensForCompletion;
  }

  public AiLlmEndpointParams getLlmEndpointParams() {
    return llmEndpointParams;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiAgentSpreadsheetTool casted = (AiAgentSpreadsheetTool) o;
    return Objects.equals(model, casted.model)
        && Objects.equals(numTokensForCompletion, casted.numTokensForCompletion)
        && Objects.equals(llmEndpointParams, casted.llmEndpointParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, numTokensForCompletion, llmEndpointParams);
  }

  @Override
  public String toString() {
    return "AiAgentSpreadsheetTool{"
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
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String model;

    protected Long numTokensForCompletion;

    protected AiLlmEndpointParams llmEndpointParams;

    public Builder model(String model) {
      this.model = model;
      return this;
    }

    public Builder numTokensForCompletion(Long numTokensForCompletion) {
      this.numTokensForCompletion = numTokensForCompletion;
      return this;
    }

    public Builder llmEndpointParams(AiLlmEndpointParamsOpenAi llmEndpointParams) {
      this.llmEndpointParams = new AiLlmEndpointParams(llmEndpointParams);
      return this;
    }

    public Builder llmEndpointParams(AiLlmEndpointParamsGoogle llmEndpointParams) {
      this.llmEndpointParams = new AiLlmEndpointParams(llmEndpointParams);
      return this;
    }

    public Builder llmEndpointParams(AiLlmEndpointParamsAws llmEndpointParams) {
      this.llmEndpointParams = new AiLlmEndpointParams(llmEndpointParams);
      return this;
    }

    public Builder llmEndpointParams(AiLlmEndpointParamsIbm llmEndpointParams) {
      this.llmEndpointParams = new AiLlmEndpointParams(llmEndpointParams);
      return this;
    }

    public Builder llmEndpointParams(AiLlmEndpointParams llmEndpointParams) {
      this.llmEndpointParams = llmEndpointParams;
      return this;
    }

    public AiAgentSpreadsheetTool build() {
      return new AiAgentSpreadsheetTool(this);
    }
  }
}
