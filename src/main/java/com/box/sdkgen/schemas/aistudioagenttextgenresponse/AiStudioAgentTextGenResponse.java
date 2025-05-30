package com.box.sdkgen.schemas.aistudioagenttextgenresponse;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aistudioagentbasicgentoolresponse.AiStudioAgentBasicGenToolResponse;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AiStudioAgentTextGenResponse extends SerializableObject {

  @JsonDeserialize(
      using =
          AiStudioAgentTextGenResponseTypeField.AiStudioAgentTextGenResponseTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          AiStudioAgentTextGenResponseTypeField.AiStudioAgentTextGenResponseTypeFieldSerializer
              .class)
  protected EnumWrapper<AiStudioAgentTextGenResponseTypeField> type;

  @JsonProperty("access_state")
  protected final String accessState;

  protected final String description;

  @JsonProperty("custom_instructions")
  protected String customInstructions;

  @JsonProperty("basic_gen")
  protected AiStudioAgentBasicGenToolResponse basicGen;

  public AiStudioAgentTextGenResponse(
      @JsonProperty("access_state") String accessState,
      @JsonProperty("description") String description) {
    super();
    this.accessState = accessState;
    this.description = description;
    this.type =
        new EnumWrapper<AiStudioAgentTextGenResponseTypeField>(
            AiStudioAgentTextGenResponseTypeField.AI_AGENT_TEXT_GEN);
  }

  protected AiStudioAgentTextGenResponse(AiStudioAgentTextGenResponseBuilder builder) {
    super();
    this.type = builder.type;
    this.accessState = builder.accessState;
    this.description = builder.description;
    this.customInstructions = builder.customInstructions;
    this.basicGen = builder.basicGen;
  }

  public EnumWrapper<AiStudioAgentTextGenResponseTypeField> getType() {
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

  public AiStudioAgentBasicGenToolResponse getBasicGen() {
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
    AiStudioAgentTextGenResponse casted = (AiStudioAgentTextGenResponse) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(accessState, casted.accessState)
        && Objects.equals(description, casted.description)
        && Objects.equals(customInstructions, casted.customInstructions)
        && Objects.equals(basicGen, casted.basicGen);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, accessState, description, customInstructions, basicGen);
  }

  @Override
  public String toString() {
    return "AiStudioAgentTextGenResponse{"
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
        + "basicGen='"
        + basicGen
        + '\''
        + "}";
  }

  public static class AiStudioAgentTextGenResponseBuilder {

    protected EnumWrapper<AiStudioAgentTextGenResponseTypeField> type;

    protected final String accessState;

    protected final String description;

    protected String customInstructions;

    protected AiStudioAgentBasicGenToolResponse basicGen;

    public AiStudioAgentTextGenResponseBuilder(String accessState, String description) {
      this.accessState = accessState;
      this.description = description;
      this.type =
          new EnumWrapper<AiStudioAgentTextGenResponseTypeField>(
              AiStudioAgentTextGenResponseTypeField.AI_AGENT_TEXT_GEN);
    }

    public AiStudioAgentTextGenResponseBuilder type(AiStudioAgentTextGenResponseTypeField type) {
      this.type = new EnumWrapper<AiStudioAgentTextGenResponseTypeField>(type);
      return this;
    }

    public AiStudioAgentTextGenResponseBuilder type(
        EnumWrapper<AiStudioAgentTextGenResponseTypeField> type) {
      this.type = type;
      return this;
    }

    public AiStudioAgentTextGenResponseBuilder customInstructions(String customInstructions) {
      this.customInstructions = customInstructions;
      return this;
    }

    public AiStudioAgentTextGenResponseBuilder basicGen(
        AiStudioAgentBasicGenToolResponse basicGen) {
      this.basicGen = basicGen;
      return this;
    }

    public AiStudioAgentTextGenResponse build() {
      return new AiStudioAgentTextGenResponse(this);
    }
  }
}
