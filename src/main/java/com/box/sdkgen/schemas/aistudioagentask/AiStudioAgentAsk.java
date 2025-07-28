package com.box.sdkgen.schemas.aistudioagentask;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aistudioagentbasictexttool.AiStudioAgentBasicTextTool;
import com.box.sdkgen.schemas.aistudioagentlongtexttool.AiStudioAgentLongTextTool;
import com.box.sdkgen.schemas.aistudioagentspreadsheettool.AiStudioAgentSpreadsheetTool;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AiStudioAgentAsk extends SerializableObject {

  @JsonDeserialize(using = AiStudioAgentAskTypeField.AiStudioAgentAskTypeFieldDeserializer.class)
  @JsonSerialize(using = AiStudioAgentAskTypeField.AiStudioAgentAskTypeFieldSerializer.class)
  protected EnumWrapper<AiStudioAgentAskTypeField> type;

  @JsonProperty("access_state")
  protected final String accessState;

  protected final String description;

  @JsonProperty("custom_instructions")
  @Nullable
  protected String customInstructions;

  @JsonProperty("suggested_questions")
  protected List<String> suggestedQuestions;

  @JsonProperty("long_text")
  protected AiStudioAgentLongTextTool longText;

  @JsonProperty("basic_text")
  protected AiStudioAgentBasicTextTool basicText;

  @JsonProperty("basic_image")
  protected AiStudioAgentBasicTextTool basicImage;

  protected AiStudioAgentSpreadsheetTool spreadsheet;

  @JsonProperty("long_text_multi")
  protected AiStudioAgentLongTextTool longTextMulti;

  @JsonProperty("basic_text_multi")
  protected AiStudioAgentBasicTextTool basicTextMulti;

  @JsonProperty("basic_image_multi")
  protected AiStudioAgentBasicTextTool basicImageMulti;

  public AiStudioAgentAsk(
      @JsonProperty("access_state") String accessState,
      @JsonProperty("description") String description) {
    super();
    this.accessState = accessState;
    this.description = description;
    this.type = new EnumWrapper<AiStudioAgentAskTypeField>(AiStudioAgentAskTypeField.AI_AGENT_ASK);
  }

  protected AiStudioAgentAsk(Builder builder) {
    super();
    this.type = builder.type;
    this.accessState = builder.accessState;
    this.description = builder.description;
    this.customInstructions = builder.customInstructions;
    this.suggestedQuestions = builder.suggestedQuestions;
    this.longText = builder.longText;
    this.basicText = builder.basicText;
    this.basicImage = builder.basicImage;
    this.spreadsheet = builder.spreadsheet;
    this.longTextMulti = builder.longTextMulti;
    this.basicTextMulti = builder.basicTextMulti;
    this.basicImageMulti = builder.basicImageMulti;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public List<String> getSuggestedQuestions() {
    return suggestedQuestions;
  }

  public AiStudioAgentLongTextTool getLongText() {
    return longText;
  }

  public AiStudioAgentBasicTextTool getBasicText() {
    return basicText;
  }

  public AiStudioAgentBasicTextTool getBasicImage() {
    return basicImage;
  }

  public AiStudioAgentSpreadsheetTool getSpreadsheet() {
    return spreadsheet;
  }

  public AiStudioAgentLongTextTool getLongTextMulti() {
    return longTextMulti;
  }

  public AiStudioAgentBasicTextTool getBasicTextMulti() {
    return basicTextMulti;
  }

  public AiStudioAgentBasicTextTool getBasicImageMulti() {
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
    AiStudioAgentAsk casted = (AiStudioAgentAsk) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(accessState, casted.accessState)
        && Objects.equals(description, casted.description)
        && Objects.equals(customInstructions, casted.customInstructions)
        && Objects.equals(suggestedQuestions, casted.suggestedQuestions)
        && Objects.equals(longText, casted.longText)
        && Objects.equals(basicText, casted.basicText)
        && Objects.equals(basicImage, casted.basicImage)
        && Objects.equals(spreadsheet, casted.spreadsheet)
        && Objects.equals(longTextMulti, casted.longTextMulti)
        && Objects.equals(basicTextMulti, casted.basicTextMulti)
        && Objects.equals(basicImageMulti, casted.basicImageMulti);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        type,
        accessState,
        description,
        customInstructions,
        suggestedQuestions,
        longText,
        basicText,
        basicImage,
        spreadsheet,
        longTextMulti,
        basicTextMulti,
        basicImageMulti);
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
        + "suggestedQuestions='"
        + suggestedQuestions
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
        + "basicImageMulti='"
        + basicImageMulti
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<AiStudioAgentAskTypeField> type;

    protected final String accessState;

    protected final String description;

    protected String customInstructions;

    protected List<String> suggestedQuestions;

    protected AiStudioAgentLongTextTool longText;

    protected AiStudioAgentBasicTextTool basicText;

    protected AiStudioAgentBasicTextTool basicImage;

    protected AiStudioAgentSpreadsheetTool spreadsheet;

    protected AiStudioAgentLongTextTool longTextMulti;

    protected AiStudioAgentBasicTextTool basicTextMulti;

    protected AiStudioAgentBasicTextTool basicImageMulti;

    public Builder(String accessState, String description) {
      super();
      this.accessState = accessState;
      this.description = description;
      this.type =
          new EnumWrapper<AiStudioAgentAskTypeField>(AiStudioAgentAskTypeField.AI_AGENT_ASK);
    }

    public Builder type(AiStudioAgentAskTypeField type) {
      this.type = new EnumWrapper<AiStudioAgentAskTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AiStudioAgentAskTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder customInstructions(String customInstructions) {
      this.customInstructions = customInstructions;
      this.markNullableFieldAsSet("custom_instructions");
      return this;
    }

    public Builder suggestedQuestions(List<String> suggestedQuestions) {
      this.suggestedQuestions = suggestedQuestions;
      return this;
    }

    public Builder longText(AiStudioAgentLongTextTool longText) {
      this.longText = longText;
      return this;
    }

    public Builder basicText(AiStudioAgentBasicTextTool basicText) {
      this.basicText = basicText;
      return this;
    }

    public Builder basicImage(AiStudioAgentBasicTextTool basicImage) {
      this.basicImage = basicImage;
      return this;
    }

    public Builder spreadsheet(AiStudioAgentSpreadsheetTool spreadsheet) {
      this.spreadsheet = spreadsheet;
      return this;
    }

    public Builder longTextMulti(AiStudioAgentLongTextTool longTextMulti) {
      this.longTextMulti = longTextMulti;
      return this;
    }

    public Builder basicTextMulti(AiStudioAgentBasicTextTool basicTextMulti) {
      this.basicTextMulti = basicTextMulti;
      return this;
    }

    public Builder basicImageMulti(AiStudioAgentBasicTextTool basicImageMulti) {
      this.basicImageMulti = basicImageMulti;
      return this;
    }

    public AiStudioAgentAsk build() {
      return new AiStudioAgentAsk(this);
    }
  }
}
