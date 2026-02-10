package com.box.sdkgen.schemas.aistudioagenttextgen;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aistudioagentbasicgentool.AiStudioAgentBasicGenTool;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** The AI agent to be used to generate text. */
@JsonFilter("nullablePropertyFilter")
public class AiStudioAgentTextGen extends SerializableObject {

  /** The type of AI agent used for generating text. */
  @JsonDeserialize(
      using = AiStudioAgentTextGenTypeField.AiStudioAgentTextGenTypeFieldDeserializer.class)
  @JsonSerialize(
      using = AiStudioAgentTextGenTypeField.AiStudioAgentTextGenTypeFieldSerializer.class)
  protected EnumWrapper<AiStudioAgentTextGenTypeField> type;

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

  @JsonProperty("basic_gen")
  protected AiStudioAgentBasicGenTool basicGen;

  public AiStudioAgentTextGen(
      @JsonProperty("access_state") String accessState,
      @JsonProperty("description") String description) {
    super();
    this.accessState = accessState;
    this.description = description;
    this.type =
        new EnumWrapper<AiStudioAgentTextGenTypeField>(
            AiStudioAgentTextGenTypeField.AI_AGENT_TEXT_GEN);
  }

  protected AiStudioAgentTextGen(Builder builder) {
    super();
    this.type = builder.type;
    this.accessState = builder.accessState;
    this.description = builder.description;
    this.customInstructions = builder.customInstructions;
    this.suggestedQuestions = builder.suggestedQuestions;
    this.basicGen = builder.basicGen;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<AiStudioAgentTextGenTypeField> getType() {
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

  public AiStudioAgentBasicGenTool getBasicGen() {
    return basicGen;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiStudioAgentTextGen casted = (AiStudioAgentTextGen) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(accessState, casted.accessState)
        && Objects.equals(description, casted.description)
        && Objects.equals(customInstructions, casted.customInstructions)
        && Objects.equals(suggestedQuestions, casted.suggestedQuestions)
        && Objects.equals(basicGen, casted.basicGen);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        type, accessState, description, customInstructions, suggestedQuestions, basicGen);
  }

  @Override
  public String toString() {
    return "AiStudioAgentTextGen{"
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
        + "basicGen='"
        + basicGen
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<AiStudioAgentTextGenTypeField> type;

    protected final String accessState;

    protected final String description;

    protected String customInstructions;

    protected List<String> suggestedQuestions;

    protected AiStudioAgentBasicGenTool basicGen;

    public Builder(String accessState, String description) {
      super();
      this.accessState = accessState;
      this.description = description;
    }

    public Builder type(AiStudioAgentTextGenTypeField type) {
      this.type = new EnumWrapper<AiStudioAgentTextGenTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AiStudioAgentTextGenTypeField> type) {
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

    public Builder basicGen(AiStudioAgentBasicGenTool basicGen) {
      this.basicGen = basicGen;
      return this;
    }

    public AiStudioAgentTextGen build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<AiStudioAgentTextGenTypeField>(
                AiStudioAgentTextGenTypeField.AI_AGENT_TEXT_GEN);
      }
      return new AiStudioAgentTextGen(this);
    }
  }
}
