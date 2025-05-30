package com.box.sdkgen.schemas.aistudioagentaskresponse;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aistudioagentbasictexttoolresponse.AiStudioAgentBasicTextToolResponse;
import com.box.sdkgen.schemas.aistudioagentlongtexttoolresponse.AiStudioAgentLongTextToolResponse;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AiStudioAgentAskResponse extends SerializableObject {

  @JsonDeserialize(
      using = AiStudioAgentAskResponseTypeField.AiStudioAgentAskResponseTypeFieldDeserializer.class)
  @JsonSerialize(
      using = AiStudioAgentAskResponseTypeField.AiStudioAgentAskResponseTypeFieldSerializer.class)
  protected EnumWrapper<AiStudioAgentAskResponseTypeField> type;

  @JsonProperty("access_state")
  protected final String accessState;

  protected final String description;

  @JsonProperty("custom_instructions")
  protected String customInstructions;

  @JsonProperty("long_text")
  protected AiStudioAgentLongTextToolResponse longText;

  @JsonProperty("basic_text")
  protected AiStudioAgentBasicTextToolResponse basicText;

  @JsonProperty("long_text_multi")
  protected AiStudioAgentLongTextToolResponse longTextMulti;

  @JsonProperty("basic_text_multi")
  protected AiStudioAgentBasicTextToolResponse basicTextMulti;

  public AiStudioAgentAskResponse(
      @JsonProperty("access_state") String accessState,
      @JsonProperty("description") String description) {
    super();
    this.accessState = accessState;
    this.description = description;
    this.type =
        new EnumWrapper<AiStudioAgentAskResponseTypeField>(
            AiStudioAgentAskResponseTypeField.AI_AGENT_ASK);
  }

  protected AiStudioAgentAskResponse(AiStudioAgentAskResponseBuilder builder) {
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

  public EnumWrapper<AiStudioAgentAskResponseTypeField> getType() {
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

  public AiStudioAgentLongTextToolResponse getLongTextMulti() {
    return longTextMulti;
  }

  public AiStudioAgentBasicTextToolResponse getBasicTextMulti() {
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
    AiStudioAgentAskResponse casted = (AiStudioAgentAskResponse) o;
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
    return "AiStudioAgentAskResponse{"
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

  public static class AiStudioAgentAskResponseBuilder {

    protected EnumWrapper<AiStudioAgentAskResponseTypeField> type;

    protected final String accessState;

    protected final String description;

    protected String customInstructions;

    protected AiStudioAgentLongTextToolResponse longText;

    protected AiStudioAgentBasicTextToolResponse basicText;

    protected AiStudioAgentLongTextToolResponse longTextMulti;

    protected AiStudioAgentBasicTextToolResponse basicTextMulti;

    public AiStudioAgentAskResponseBuilder(String accessState, String description) {
      this.accessState = accessState;
      this.description = description;
      this.type =
          new EnumWrapper<AiStudioAgentAskResponseTypeField>(
              AiStudioAgentAskResponseTypeField.AI_AGENT_ASK);
    }

    public AiStudioAgentAskResponseBuilder type(AiStudioAgentAskResponseTypeField type) {
      this.type = new EnumWrapper<AiStudioAgentAskResponseTypeField>(type);
      return this;
    }

    public AiStudioAgentAskResponseBuilder type(
        EnumWrapper<AiStudioAgentAskResponseTypeField> type) {
      this.type = type;
      return this;
    }

    public AiStudioAgentAskResponseBuilder customInstructions(String customInstructions) {
      this.customInstructions = customInstructions;
      return this;
    }

    public AiStudioAgentAskResponseBuilder longText(AiStudioAgentLongTextToolResponse longText) {
      this.longText = longText;
      return this;
    }

    public AiStudioAgentAskResponseBuilder basicText(AiStudioAgentBasicTextToolResponse basicText) {
      this.basicText = basicText;
      return this;
    }

    public AiStudioAgentAskResponseBuilder longTextMulti(
        AiStudioAgentLongTextToolResponse longTextMulti) {
      this.longTextMulti = longTextMulti;
      return this;
    }

    public AiStudioAgentAskResponseBuilder basicTextMulti(
        AiStudioAgentBasicTextToolResponse basicTextMulti) {
      this.basicTextMulti = basicTextMulti;
      return this;
    }

    public AiStudioAgentAskResponse build() {
      return new AiStudioAgentAskResponse(this);
    }
  }
}
