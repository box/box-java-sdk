package com.box.sdkgen.schemas.aistudioagentask;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aistudioagentbasictexttool.AiStudioAgentBasicTextTool;
import com.box.sdkgen.schemas.aistudioagentlongtexttool.AiStudioAgentLongTextTool;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AiStudioAgentAsk extends SerializableObject {

  @JsonDeserialize(using = AiStudioAgentAskTypeField.AiStudioAgentAskTypeFieldDeserializer.class)
  @JsonSerialize(using = AiStudioAgentAskTypeField.AiStudioAgentAskTypeFieldSerializer.class)
  protected EnumWrapper<AiStudioAgentAskTypeField> type;

  @JsonProperty("access_state")
  protected final String accessState;

  protected final String description;

  @JsonProperty("custom_instructions")
  protected String customInstructions;

  @JsonProperty("long_text")
  protected AiStudioAgentLongTextTool longText;

  @JsonProperty("basic_text")
  protected AiStudioAgentBasicTextTool basicText;

  @JsonProperty("long_text_multi")
  protected AiStudioAgentLongTextTool longTextMulti;

  @JsonProperty("basic_text_multi")
  protected AiStudioAgentBasicTextTool basicTextMulti;

  public AiStudioAgentAsk(
      @JsonProperty("access_state") String accessState,
      @JsonProperty("description") String description) {
    super();
    this.accessState = accessState;
    this.description = description;
    this.type = new EnumWrapper<AiStudioAgentAskTypeField>(AiStudioAgentAskTypeField.AI_AGENT_ASK);
  }

  protected AiStudioAgentAsk(AiStudioAgentAskBuilder builder) {
    super();
    this.type = builder.type;
    this.accessState = builder.accessState;
    this.description = builder.description;
    this.customInstructions = builder.customInstructions;
    this.longText = builder.longText;
    this.basicText = builder.basicText;
    this.longTextMulti = builder.longTextMulti;
    this.basicTextMulti = builder.basicTextMulti;
  }

  public EnumWrapper<AiStudioAgentAskTypeField> getType() {
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

  public AiStudioAgentLongTextTool getLongTextMulti() {
    return longTextMulti;
  }

  public AiStudioAgentBasicTextTool getBasicTextMulti() {
    return basicTextMulti;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiStudioAgentAsk casted = (AiStudioAgentAsk) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(accessState, casted.accessState)
        && Objects.equals(description, casted.description)
        && Objects.equals(customInstructions, casted.customInstructions)
        && Objects.equals(longText, casted.longText)
        && Objects.equals(basicText, casted.basicText)
        && Objects.equals(longTextMulti, casted.longTextMulti)
        && Objects.equals(basicTextMulti, casted.basicTextMulti);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        type,
        accessState,
        description,
        customInstructions,
        longText,
        basicText,
        longTextMulti,
        basicTextMulti);
  }

  @Override
  public String toString() {
    return "AiStudioAgentAsk{"
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
        + ", "
        + "longTextMulti='"
        + longTextMulti
        + '\''
        + ", "
        + "basicTextMulti='"
        + basicTextMulti
        + '\''
        + "}";
  }

  public static class AiStudioAgentAskBuilder {

    protected EnumWrapper<AiStudioAgentAskTypeField> type;

    protected final String accessState;

    protected final String description;

    protected String customInstructions;

    protected AiStudioAgentLongTextTool longText;

    protected AiStudioAgentBasicTextTool basicText;

    protected AiStudioAgentLongTextTool longTextMulti;

    protected AiStudioAgentBasicTextTool basicTextMulti;

    public AiStudioAgentAskBuilder(String accessState, String description) {
      this.accessState = accessState;
      this.description = description;
      this.type =
          new EnumWrapper<AiStudioAgentAskTypeField>(AiStudioAgentAskTypeField.AI_AGENT_ASK);
    }

    public AiStudioAgentAskBuilder type(AiStudioAgentAskTypeField type) {
      this.type = new EnumWrapper<AiStudioAgentAskTypeField>(type);
      return this;
    }

    public AiStudioAgentAskBuilder type(EnumWrapper<AiStudioAgentAskTypeField> type) {
      this.type = type;
      return this;
    }

    public AiStudioAgentAskBuilder customInstructions(String customInstructions) {
      this.customInstructions = customInstructions;
      return this;
    }

    public AiStudioAgentAskBuilder longText(AiStudioAgentLongTextTool longText) {
      this.longText = longText;
      return this;
    }

    public AiStudioAgentAskBuilder basicText(AiStudioAgentBasicTextTool basicText) {
      this.basicText = basicText;
      return this;
    }

    public AiStudioAgentAskBuilder longTextMulti(AiStudioAgentLongTextTool longTextMulti) {
      this.longTextMulti = longTextMulti;
      return this;
    }

    public AiStudioAgentAskBuilder basicTextMulti(AiStudioAgentBasicTextTool basicTextMulti) {
      this.basicTextMulti = basicTextMulti;
      return this;
    }

    public AiStudioAgentAsk build() {
      return new AiStudioAgentAsk(this);
    }
  }
}
