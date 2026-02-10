package com.box.sdkgen.schemas.aistudioagentaskresponse;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aistudioagentbasictexttoolresponse.AiStudioAgentBasicTextToolResponse;
import com.box.sdkgen.schemas.aistudioagentlongtexttoolresponse.AiStudioAgentLongTextToolResponse;
import com.box.sdkgen.schemas.aistudioagentspreadsheettoolresponse.AiStudioAgentSpreadsheetToolResponse;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** The AI agent to be used to ask questions. */
@JsonFilter("nullablePropertyFilter")
public class AiStudioAgentAskResponse extends SerializableObject {

  /** The type of AI agent used to ask questions. */
  @JsonDeserialize(
      using = AiStudioAgentAskResponseTypeField.AiStudioAgentAskResponseTypeFieldDeserializer.class)
  @JsonSerialize(
      using = AiStudioAgentAskResponseTypeField.AiStudioAgentAskResponseTypeFieldSerializer.class)
  protected EnumWrapper<AiStudioAgentAskResponseTypeField> type;

  /** The state of the AI Agent capability. Possible values are: `enabled` and `disabled`. */
  @JsonProperty("access_state")
  protected final String accessState;

  /** The description of the AI agent. */
  protected final String description;

  /** Custom instructions for the AI agent. */
  @JsonProperty("custom_instructions")
  @Nullable
  protected String customInstructions;

  /**
   * Suggested questions for the AI agent. If null, suggested question will be generated. If empty,
   * no suggested questions will be displayed.
   */
  @JsonProperty("suggested_questions")
  protected List<String> suggestedQuestions;

  @JsonProperty("long_text")
  protected AiStudioAgentLongTextToolResponse longText;

  @JsonProperty("basic_text")
  protected AiStudioAgentBasicTextToolResponse basicText;

  @JsonProperty("basic_image")
  protected AiStudioAgentBasicTextToolResponse basicImage;

  protected AiStudioAgentSpreadsheetToolResponse spreadsheet;

  @JsonProperty("long_text_multi")
  protected AiStudioAgentLongTextToolResponse longTextMulti;

  @JsonProperty("basic_text_multi")
  protected AiStudioAgentBasicTextToolResponse basicTextMulti;

  @JsonProperty("basic_image_multi")
  protected AiStudioAgentBasicTextToolResponse basicImageMulti;

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

  protected AiStudioAgentAskResponse(Builder builder) {
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

  public List<String> getSuggestedQuestions() {
    return suggestedQuestions;
  }

  public AiStudioAgentLongTextToolResponse getLongText() {
    return longText;
  }

  public AiStudioAgentBasicTextToolResponse getBasicText() {
    return basicText;
  }

  public AiStudioAgentBasicTextToolResponse getBasicImage() {
    return basicImage;
  }

  public AiStudioAgentSpreadsheetToolResponse getSpreadsheet() {
    return spreadsheet;
  }

  public AiStudioAgentLongTextToolResponse getLongTextMulti() {
    return longTextMulti;
  }

  public AiStudioAgentBasicTextToolResponse getBasicTextMulti() {
    return basicTextMulti;
  }

  public AiStudioAgentBasicTextToolResponse getBasicImageMulti() {
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
    AiStudioAgentAskResponse casted = (AiStudioAgentAskResponse) o;
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

    protected EnumWrapper<AiStudioAgentAskResponseTypeField> type;

    protected final String accessState;

    protected final String description;

    protected String customInstructions;

    protected List<String> suggestedQuestions;

    protected AiStudioAgentLongTextToolResponse longText;

    protected AiStudioAgentBasicTextToolResponse basicText;

    protected AiStudioAgentBasicTextToolResponse basicImage;

    protected AiStudioAgentSpreadsheetToolResponse spreadsheet;

    protected AiStudioAgentLongTextToolResponse longTextMulti;

    protected AiStudioAgentBasicTextToolResponse basicTextMulti;

    protected AiStudioAgentBasicTextToolResponse basicImageMulti;

    public Builder(String accessState, String description) {
      super();
      this.accessState = accessState;
      this.description = description;
    }

    public Builder type(AiStudioAgentAskResponseTypeField type) {
      this.type = new EnumWrapper<AiStudioAgentAskResponseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AiStudioAgentAskResponseTypeField> type) {
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

    public Builder longText(AiStudioAgentLongTextToolResponse longText) {
      this.longText = longText;
      return this;
    }

    public Builder basicText(AiStudioAgentBasicTextToolResponse basicText) {
      this.basicText = basicText;
      return this;
    }

    public Builder basicImage(AiStudioAgentBasicTextToolResponse basicImage) {
      this.basicImage = basicImage;
      return this;
    }

    public Builder spreadsheet(AiStudioAgentSpreadsheetToolResponse spreadsheet) {
      this.spreadsheet = spreadsheet;
      return this;
    }

    public Builder longTextMulti(AiStudioAgentLongTextToolResponse longTextMulti) {
      this.longTextMulti = longTextMulti;
      return this;
    }

    public Builder basicTextMulti(AiStudioAgentBasicTextToolResponse basicTextMulti) {
      this.basicTextMulti = basicTextMulti;
      return this;
    }

    public Builder basicImageMulti(AiStudioAgentBasicTextToolResponse basicImageMulti) {
      this.basicImageMulti = basicImageMulti;
      return this;
    }

    public AiStudioAgentAskResponse build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<AiStudioAgentAskResponseTypeField>(
                AiStudioAgentAskResponseTypeField.AI_AGENT_ASK);
      }
      return new AiStudioAgentAskResponse(this);
    }
  }
}
