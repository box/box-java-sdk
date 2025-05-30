package com.box.sdkgen.schemas.aiagentbasictexttool;

import com.box.sdkgen.schemas.aiagentbasictexttoolbase.AiAgentBasicTextToolBase;
import com.box.sdkgen.schemas.aillmendpointparams.AiLlmEndpointParams;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AiAgentBasicTextTool extends AiAgentBasicTextToolBase {

  @JsonProperty("system_message")
  protected String systemMessage;

  @JsonProperty("prompt_template")
  protected String promptTemplate;

  public AiAgentBasicTextTool() {
    super();
  }

  protected AiAgentBasicTextTool(AiAgentBasicTextToolBuilder builder) {
    super(builder);
    this.systemMessage = builder.systemMessage;
    this.promptTemplate = builder.promptTemplate;
  }

  public String getSystemMessage() {
    return systemMessage;
  }

  public String getPromptTemplate() {
    return promptTemplate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiAgentBasicTextTool casted = (AiAgentBasicTextTool) o;
    return Objects.equals(model, casted.model)
        && Objects.equals(numTokensForCompletion, casted.numTokensForCompletion)
        && Objects.equals(llmEndpointParams, casted.llmEndpointParams)
        && Objects.equals(systemMessage, casted.systemMessage)
        && Objects.equals(promptTemplate, casted.promptTemplate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        model, numTokensForCompletion, llmEndpointParams, systemMessage, promptTemplate);
  }

  @Override
  public String toString() {
    return "AiAgentBasicTextTool{"
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
        + "systemMessage='"
        + systemMessage
        + '\''
        + ", "
        + "promptTemplate='"
        + promptTemplate
        + '\''
        + "}";
  }

  public static class AiAgentBasicTextToolBuilder extends AiAgentBasicTextToolBaseBuilder {

    protected String systemMessage;

    protected String promptTemplate;

    public AiAgentBasicTextToolBuilder systemMessage(String systemMessage) {
      this.systemMessage = systemMessage;
      return this;
    }

    public AiAgentBasicTextToolBuilder promptTemplate(String promptTemplate) {
      this.promptTemplate = promptTemplate;
      return this;
    }

    @Override
    public AiAgentBasicTextToolBuilder model(String model) {
      this.model = model;
      return this;
    }

    @Override
    public AiAgentBasicTextToolBuilder numTokensForCompletion(Long numTokensForCompletion) {
      this.numTokensForCompletion = numTokensForCompletion;
      return this;
    }

    @Override
    public AiAgentBasicTextToolBuilder llmEndpointParams(AiLlmEndpointParams llmEndpointParams) {
      this.llmEndpointParams = llmEndpointParams;
      return this;
    }

    public AiAgentBasicTextTool build() {
      return new AiAgentBasicTextTool(this);
    }
  }
}
