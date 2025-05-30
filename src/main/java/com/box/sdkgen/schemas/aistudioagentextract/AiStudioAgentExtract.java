package com.box.sdkgen.schemas.aistudioagentextract;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aistudioagentbasictexttool.AiStudioAgentBasicTextTool;
import com.box.sdkgen.schemas.aistudioagentlongtexttool.AiStudioAgentLongTextTool;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AiStudioAgentExtract extends SerializableObject {

  @JsonDeserialize(
      using = AiStudioAgentExtractTypeField.AiStudioAgentExtractTypeFieldDeserializer.class)
  @JsonSerialize(
      using = AiStudioAgentExtractTypeField.AiStudioAgentExtractTypeFieldSerializer.class)
  protected EnumWrapper<AiStudioAgentExtractTypeField> type;

  @JsonProperty("access_state")
  protected final String accessState;

  protected final String description;

  @JsonProperty("custom_instructions")
  protected String customInstructions;

  @JsonProperty("long_text")
  protected AiStudioAgentLongTextTool longText;

  @JsonProperty("basic_text")
  protected AiStudioAgentBasicTextTool basicText;

  public AiStudioAgentExtract(
      @JsonProperty("access_state") String accessState,
      @JsonProperty("description") String description) {
    super();
    this.accessState = accessState;
    this.description = description;
    this.type =
        new EnumWrapper<AiStudioAgentExtractTypeField>(
            AiStudioAgentExtractTypeField.AI_AGENT_EXTRACT);
  }

  protected AiStudioAgentExtract(AiStudioAgentExtractBuilder builder) {
    super();
    this.type = builder.type;
    this.accessState = builder.accessState;
    this.description = builder.description;
    this.customInstructions = builder.customInstructions;
    this.longText = builder.longText;
    this.basicText = builder.basicText;
  }

  public EnumWrapper<AiStudioAgentExtractTypeField> getType() {
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

  public AiStudioAgentLongTextTool getLongText() {
    return longText;
  }

  public AiStudioAgentBasicTextTool getBasicText() {
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
    AiStudioAgentExtract casted = (AiStudioAgentExtract) o;
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
    return "AiStudioAgentExtract{"
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

  public static class AiStudioAgentExtractBuilder {

    protected EnumWrapper<AiStudioAgentExtractTypeField> type;

    protected final String accessState;

    protected final String description;

    protected String customInstructions;

    protected AiStudioAgentLongTextTool longText;

    protected AiStudioAgentBasicTextTool basicText;

    public AiStudioAgentExtractBuilder(String accessState, String description) {
      this.accessState = accessState;
      this.description = description;
      this.type =
          new EnumWrapper<AiStudioAgentExtractTypeField>(
              AiStudioAgentExtractTypeField.AI_AGENT_EXTRACT);
    }

    public AiStudioAgentExtractBuilder type(AiStudioAgentExtractTypeField type) {
      this.type = new EnumWrapper<AiStudioAgentExtractTypeField>(type);
      return this;
    }

    public AiStudioAgentExtractBuilder type(EnumWrapper<AiStudioAgentExtractTypeField> type) {
      this.type = type;
      return this;
    }

    public AiStudioAgentExtractBuilder customInstructions(String customInstructions) {
      this.customInstructions = customInstructions;
      return this;
    }

    public AiStudioAgentExtractBuilder longText(AiStudioAgentLongTextTool longText) {
      this.longText = longText;
      return this;
    }

    public AiStudioAgentExtractBuilder basicText(AiStudioAgentBasicTextTool basicText) {
      this.basicText = basicText;
      return this;
    }

    public AiStudioAgentExtract build() {
      return new AiStudioAgentExtract(this);
    }
  }
}
