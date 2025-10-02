package com.box.sdkgen.schemas.aiagentextract;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aiagentbasictexttool.AiAgentBasicTextTool;
import com.box.sdkgen.schemas.aiagentlongtexttool.AiAgentLongTextTool;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** The AI agent to be used for extraction. */
@JsonFilter("nullablePropertyFilter")
public class AiAgentExtract extends SerializableObject {

  /** The type of AI agent to be used for extraction. */
  @JsonDeserialize(using = AiAgentExtractTypeField.AiAgentExtractTypeFieldDeserializer.class)
  @JsonSerialize(using = AiAgentExtractTypeField.AiAgentExtractTypeFieldSerializer.class)
  protected EnumWrapper<AiAgentExtractTypeField> type;

  @JsonProperty("long_text")
  protected AiAgentLongTextTool longText;

  @JsonProperty("basic_text")
  protected AiAgentBasicTextTool basicText;

  @JsonProperty("basic_image")
  protected AiAgentBasicTextTool basicImage;

  public AiAgentExtract() {
    super();
    this.type = new EnumWrapper<AiAgentExtractTypeField>(AiAgentExtractTypeField.AI_AGENT_EXTRACT);
  }

  protected AiAgentExtract(Builder builder) {
    super();
    this.type = builder.type;
    this.longText = builder.longText;
    this.basicText = builder.basicText;
    this.basicImage = builder.basicImage;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public AiAgentBasicTextTool getBasicImage() {
    return basicImage;
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
        && Objects.equals(basicText, casted.basicText)
        && Objects.equals(basicImage, casted.basicImage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, longText, basicText, basicImage);
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
        + ", "
        + "basicImage='"
        + basicImage
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<AiAgentExtractTypeField> type;

    protected AiAgentLongTextTool longText;

    protected AiAgentBasicTextTool basicText;

    protected AiAgentBasicTextTool basicImage;

    public Builder() {
      super();
      this.type =
          new EnumWrapper<AiAgentExtractTypeField>(AiAgentExtractTypeField.AI_AGENT_EXTRACT);
    }

    public Builder type(AiAgentExtractTypeField type) {
      this.type = new EnumWrapper<AiAgentExtractTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AiAgentExtractTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder longText(AiAgentLongTextTool longText) {
      this.longText = longText;
      return this;
    }

    public Builder basicText(AiAgentBasicTextTool basicText) {
      this.basicText = basicText;
      return this;
    }

    public Builder basicImage(AiAgentBasicTextTool basicImage) {
      this.basicImage = basicImage;
      return this;
    }

    public AiAgentExtract build() {
      return new AiAgentExtract(this);
    }
  }
}
