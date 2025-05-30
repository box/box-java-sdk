package com.box.sdkgen.schemas.aistudioagentextractresponse;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aistudioagentbasictexttoolresponse.AiStudioAgentBasicTextToolResponse;
import com.box.sdkgen.schemas.aistudioagentlongtexttoolresponse.AiStudioAgentLongTextToolResponse;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AiStudioAgentExtractResponse extends SerializableObject {

  @JsonDeserialize(
      using =
          AiStudioAgentExtractResponseTypeField.AiStudioAgentExtractResponseTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          AiStudioAgentExtractResponseTypeField.AiStudioAgentExtractResponseTypeFieldSerializer
              .class)
  protected EnumWrapper<AiStudioAgentExtractResponseTypeField> type;

  @JsonProperty("access_state")
  protected final String accessState;

  protected final String description;

  @JsonProperty("custom_instructions")
  protected String customInstructions;

  @JsonProperty("long_text")
  protected AiStudioAgentLongTextToolResponse longText;

  @JsonProperty("basic_text")
  protected AiStudioAgentBasicTextToolResponse basicText;

  public AiStudioAgentExtractResponse(
      @JsonProperty("access_state") String accessState,
      @JsonProperty("description") String description) {
    super();
    this.accessState = accessState;
    this.description = description;
    this.type =
        new EnumWrapper<AiStudioAgentExtractResponseTypeField>(
            AiStudioAgentExtractResponseTypeField.AI_AGENT_EXTRACT);
  }

  protected AiStudioAgentExtractResponse(AiStudioAgentExtractResponseBuilder builder) {
    super();
    this.type = builder.type;
    this.accessState = builder.accessState;
    this.description = builder.description;
    this.customInstructions = builder.customInstructions;
    this.longText = builder.longText;
    this.basicText = builder.basicText;
  }

  public EnumWrapper<AiStudioAgentExtractResponseTypeField> getType() {
    return type;
  }

  public String getAccessState() {
    return accessState;
  }

  public String getDescription() {
    return description;
  }

  public String getCustomInstructions() {
    return customInstructions;
  }

  public AiStudioAgentLongTextToolResponse getLongText() {
    return longText;
  }

  public AiStudioAgentBasicTextToolResponse getBasicText() {
    return basicText;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiStudioAgentExtractResponse casted = (AiStudioAgentExtractResponse) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(accessState, casted.accessState)
        && Objects.equals(description, casted.description)
        && Objects.equals(customInstructions, casted.customInstructions)
        && Objects.equals(longText, casted.longText)
        && Objects.equals(basicText, casted.basicText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, accessState, description, customInstructions, longText, basicText);
  }

  @Override
  public String toString() {
    return "AiStudioAgentExtractResponse{"
        + "type='"
        + type
        + '\''
        + ", "
        + "accessState='"
        + accessState
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "customInstructions='"
        + customInstructions
        + '\''
        + ", "
        + "longText='"
        + longText
        + '\''
        + ", "
        + "basicText='"
        + basicText
        + '\''
        + "}";
  }

  public static class AiStudioAgentExtractResponseBuilder {

    protected EnumWrapper<AiStudioAgentExtractResponseTypeField> type;

    protected final String accessState;

    protected final String description;

    protected String customInstructions;

    protected AiStudioAgentLongTextToolResponse longText;

    protected AiStudioAgentBasicTextToolResponse basicText;

    public AiStudioAgentExtractResponseBuilder(String accessState, String description) {
      this.accessState = accessState;
      this.description = description;
      this.type =
          new EnumWrapper<AiStudioAgentExtractResponseTypeField>(
              AiStudioAgentExtractResponseTypeField.AI_AGENT_EXTRACT);
    }

    public AiStudioAgentExtractResponseBuilder type(AiStudioAgentExtractResponseTypeField type) {
      this.type = new EnumWrapper<AiStudioAgentExtractResponseTypeField>(type);
      return this;
    }

    public AiStudioAgentExtractResponseBuilder type(
        EnumWrapper<AiStudioAgentExtractResponseTypeField> type) {
      this.type = type;
      return this;
    }

    public AiStudioAgentExtractResponseBuilder customInstructions(String customInstructions) {
      this.customInstructions = customInstructions;
      return this;
    }

    public AiStudioAgentExtractResponseBuilder longText(
        AiStudioAgentLongTextToolResponse longText) {
      this.longText = longText;
      return this;
    }

    public AiStudioAgentExtractResponseBuilder basicText(
        AiStudioAgentBasicTextToolResponse basicText) {
      this.basicText = basicText;
      return this;
    }

    public AiStudioAgentExtractResponse build() {
      return new AiStudioAgentExtractResponse(this);
    }
  }
}
