package com.box.sdkgen.schemas.aistudioagentextractresponse;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aistudioagentbasictexttoolresponse.AiStudioAgentBasicTextToolResponse;
import com.box.sdkgen.schemas.aistudioagentlongtexttoolresponse.AiStudioAgentLongTextToolResponse;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** The AI agent to be used for metadata extraction. */
@JsonFilter("nullablePropertyFilter")
public class AiStudioAgentExtractResponse extends SerializableObject {

  /** The type of AI agent to be used for metadata extraction. */
  @JsonDeserialize(
      using =
          AiStudioAgentExtractResponseTypeField.AiStudioAgentExtractResponseTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          AiStudioAgentExtractResponseTypeField.AiStudioAgentExtractResponseTypeFieldSerializer
              .class)
  protected EnumWrapper<AiStudioAgentExtractResponseTypeField> type;

  /** The state of the AI Agent capability. Possible values are: `enabled` and `disabled`. */
  @JsonProperty("access_state")
  protected final String accessState;

  /** The description of the AI agent. */
  protected final String description;

  /** Custom instructions for the AI agent. */
  @JsonProperty("custom_instructions")
  @Nullable
  protected String customInstructions;

  @JsonProperty("long_text")
  protected AiStudioAgentLongTextToolResponse longText;

  @JsonProperty("basic_text")
  protected AiStudioAgentBasicTextToolResponse basicText;

  @JsonProperty("basic_image")
  protected AiStudioAgentBasicTextToolResponse basicImage;

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

  protected AiStudioAgentExtractResponse(Builder builder) {
    super();
    this.type = builder.type;
    this.accessState = builder.accessState;
    this.description = builder.description;
    this.customInstructions = builder.customInstructions;
    this.longText = builder.longText;
    this.basicText = builder.basicText;
    this.basicImage = builder.basicImage;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public AiStudioAgentBasicTextToolResponse getBasicImage() {
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
    AiStudioAgentExtractResponse casted = (AiStudioAgentExtractResponse) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(accessState, casted.accessState)
        && Objects.equals(description, casted.description)
        && Objects.equals(customInstructions, casted.customInstructions)
        && Objects.equals(longText, casted.longText)
        && Objects.equals(basicText, casted.basicText)
        && Objects.equals(basicImage, casted.basicImage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        type, accessState, description, customInstructions, longText, basicText, basicImage);
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
        + ", "
        + "basicImage='"
        + basicImage
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<AiStudioAgentExtractResponseTypeField> type;

    protected final String accessState;

    protected final String description;

    protected String customInstructions;

    protected AiStudioAgentLongTextToolResponse longText;

    protected AiStudioAgentBasicTextToolResponse basicText;

    protected AiStudioAgentBasicTextToolResponse basicImage;

    public Builder(String accessState, String description) {
      super();
      this.accessState = accessState;
      this.description = description;
    }

    public Builder type(AiStudioAgentExtractResponseTypeField type) {
      this.type = new EnumWrapper<AiStudioAgentExtractResponseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AiStudioAgentExtractResponseTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder customInstructions(String customInstructions) {
      this.customInstructions = customInstructions;
      this.markNullableFieldAsSet("custom_instructions");
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

    public AiStudioAgentExtractResponse build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<AiStudioAgentExtractResponseTypeField>(
                AiStudioAgentExtractResponseTypeField.AI_AGENT_EXTRACT);
      }
      return new AiStudioAgentExtractResponse(this);
    }
  }
}
