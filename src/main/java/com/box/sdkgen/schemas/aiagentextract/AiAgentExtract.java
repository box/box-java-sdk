package com.box.sdkgen.schemas.aiagentextract;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aiagentbasictexttool.AiAgentBasicTextTool;
import com.box.sdkgen.schemas.aiagentlongtexttool.AiAgentLongTextTool;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AiAgentExtract extends SerializableObject {

  @JsonDeserialize(using = AiAgentExtractTypeField.AiAgentExtractTypeFieldDeserializer.class)
  @JsonSerialize(using = AiAgentExtractTypeField.AiAgentExtractTypeFieldSerializer.class)
  protected EnumWrapper<AiAgentExtractTypeField> type;

  @JsonProperty("long_text")
  protected AiAgentLongTextTool longText;

  @JsonProperty("basic_text")
  protected AiAgentBasicTextTool basicText;

  public AiAgentExtract() {
    super();
    this.type = new EnumWrapper<AiAgentExtractTypeField>(AiAgentExtractTypeField.AI_AGENT_EXTRACT);
  }

  protected AiAgentExtract(AiAgentExtractBuilder builder) {
    super();
    this.type = builder.type;
    this.longText = builder.longText;
    this.basicText = builder.basicText;
  }

  public EnumWrapper<AiAgentExtractTypeField> getType() {
    return type;
  }

  public AiAgentLongTextTool getLongText() {
    return longText;
  }

  public AiAgentBasicTextTool getBasicText() {
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
    AiAgentExtract casted = (AiAgentExtract) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(longText, casted.longText)
        && Objects.equals(basicText, casted.basicText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, longText, basicText);
  }

  @Override
  public String toString() {
    return "AiAgentExtract{"
        + "type='"
        + type
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

  public static class AiAgentExtractBuilder {

    protected EnumWrapper<AiAgentExtractTypeField> type;

    protected AiAgentLongTextTool longText;

    protected AiAgentBasicTextTool basicText;

    public AiAgentExtractBuilder type(AiAgentExtractTypeField type) {
      this.type = new EnumWrapper<AiAgentExtractTypeField>(type);
      return this;
    }

    public AiAgentExtractBuilder type(EnumWrapper<AiAgentExtractTypeField> type) {
      this.type = type;
      return this;
    }

    public AiAgentExtractBuilder longText(AiAgentLongTextTool longText) {
      this.longText = longText;
      return this;
    }

    public AiAgentExtractBuilder basicText(AiAgentBasicTextTool basicText) {
      this.basicText = basicText;
      return this;
    }

    public AiAgentExtract build() {
      return new AiAgentExtract(this);
    }
  }
}
