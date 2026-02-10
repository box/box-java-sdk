package com.box.sdkgen.schemas.aiagentask;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aiagentbasictexttool.AiAgentBasicTextTool;
import com.box.sdkgen.schemas.aiagentlongtexttool.AiAgentLongTextTool;
import com.box.sdkgen.schemas.aiagentspreadsheettool.AiAgentSpreadsheetTool;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** The AI agent used to handle queries. */
@JsonFilter("nullablePropertyFilter")
public class AiAgentAsk extends SerializableObject {

  /** The type of AI agent used to handle queries. */
  @JsonDeserialize(using = AiAgentAskTypeField.AiAgentAskTypeFieldDeserializer.class)
  @JsonSerialize(using = AiAgentAskTypeField.AiAgentAskTypeFieldSerializer.class)
  protected EnumWrapper<AiAgentAskTypeField> type;

  @JsonProperty("long_text")
  protected AiAgentLongTextTool longText;

  @JsonProperty("basic_text")
  protected AiAgentBasicTextTool basicText;

  protected AiAgentSpreadsheetTool spreadsheet;

  @JsonProperty("long_text_multi")
  protected AiAgentLongTextTool longTextMulti;

  @JsonProperty("basic_text_multi")
  protected AiAgentBasicTextTool basicTextMulti;

  @JsonProperty("basic_image")
  protected AiAgentBasicTextTool basicImage;

  @JsonProperty("basic_image_multi")
  protected AiAgentBasicTextTool basicImageMulti;

  public AiAgentAsk() {
    super();
    this.type = new EnumWrapper<AiAgentAskTypeField>(AiAgentAskTypeField.AI_AGENT_ASK);
  }

  protected AiAgentAsk(Builder builder) {
    super();
    this.type = builder.type;
    this.longText = builder.longText;
    this.basicText = builder.basicText;
    this.spreadsheet = builder.spreadsheet;
    this.longTextMulti = builder.longTextMulti;
    this.basicTextMulti = builder.basicTextMulti;
    this.basicImage = builder.basicImage;
    this.basicImageMulti = builder.basicImageMulti;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<AiAgentAskTypeField> getType() {
    return type;
  }

  public AiAgentLongTextTool getLongText() {
    return longText;
  }

  public AiAgentBasicTextTool getBasicText() {
    return basicText;
  }

  public AiAgentSpreadsheetTool getSpreadsheet() {
    return spreadsheet;
  }

  public AiAgentLongTextTool getLongTextMulti() {
    return longTextMulti;
  }

  public AiAgentBasicTextTool getBasicTextMulti() {
    return basicTextMulti;
  }

  public AiAgentBasicTextTool getBasicImage() {
    return basicImage;
  }

  public AiAgentBasicTextTool getBasicImageMulti() {
    return basicImageMulti;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiAgentAsk casted = (AiAgentAsk) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(longText, casted.longText)
        && Objects.equals(basicText, casted.basicText)
        && Objects.equals(spreadsheet, casted.spreadsheet)
        && Objects.equals(longTextMulti, casted.longTextMulti)
        && Objects.equals(basicTextMulti, casted.basicTextMulti)
        && Objects.equals(basicImage, casted.basicImage)
        && Objects.equals(basicImageMulti, casted.basicImageMulti);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        type,
        longText,
        basicText,
        spreadsheet,
        longTextMulti,
        basicTextMulti,
        basicImage,
        basicImageMulti);
  }

  @Override
  public String toString() {
    return "AiAgentAsk{"
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
        + "spreadsheet='"
        + spreadsheet
        + '\''
        + ", "
        + "longTextMulti='"
        + longTextMulti
        + '\''
        + ", "
        + "basicTextMulti='"
        + basicTextMulti
        + '\''
        + ", "
        + "basicImage='"
        + basicImage
        + '\''
        + ", "
        + "basicImageMulti='"
        + basicImageMulti
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<AiAgentAskTypeField> type;

    protected AiAgentLongTextTool longText;

    protected AiAgentBasicTextTool basicText;

    protected AiAgentSpreadsheetTool spreadsheet;

    protected AiAgentLongTextTool longTextMulti;

    protected AiAgentBasicTextTool basicTextMulti;

    protected AiAgentBasicTextTool basicImage;

    protected AiAgentBasicTextTool basicImageMulti;

    public Builder() {
      super();
    }

    public Builder type(AiAgentAskTypeField type) {
      this.type = new EnumWrapper<AiAgentAskTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AiAgentAskTypeField> type) {
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

    public Builder spreadsheet(AiAgentSpreadsheetTool spreadsheet) {
      this.spreadsheet = spreadsheet;
      return this;
    }

    public Builder longTextMulti(AiAgentLongTextTool longTextMulti) {
      this.longTextMulti = longTextMulti;
      return this;
    }

    public Builder basicTextMulti(AiAgentBasicTextTool basicTextMulti) {
      this.basicTextMulti = basicTextMulti;
      return this;
    }

    public Builder basicImage(AiAgentBasicTextTool basicImage) {
      this.basicImage = basicImage;
      return this;
    }

    public Builder basicImageMulti(AiAgentBasicTextTool basicImageMulti) {
      this.basicImageMulti = basicImageMulti;
      return this;
    }

    public AiAgentAsk build() {
      if (this.type == null) {
        this.type = new EnumWrapper<AiAgentAskTypeField>(AiAgentAskTypeField.AI_AGENT_ASK);
      }
      return new AiAgentAsk(this);
    }
  }
}
