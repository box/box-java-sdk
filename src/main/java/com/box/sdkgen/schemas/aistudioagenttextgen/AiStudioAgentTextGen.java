package com.box.sdkgen.schemas.aistudioagenttextgen;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aistudioagentbasicgentool.AiStudioAgentBasicGenTool;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AiStudioAgentTextGen extends SerializableObject {

  @JsonDeserialize(
      using = AiStudioAgentTextGenTypeField.AiStudioAgentTextGenTypeFieldDeserializer.class)
  @JsonSerialize(
      using = AiStudioAgentTextGenTypeField.AiStudioAgentTextGenTypeFieldSerializer.class)
  protected EnumWrapper<AiStudioAgentTextGenTypeField> type;

  @JsonProperty("access_state")
  protected final String accessState;

  protected final String description;

  @JsonProperty("custom_instructions")
  protected String customInstructions;

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

  protected AiStudioAgentTextGen(AiStudioAgentTextGenBuilder builder) {
    super();
    this.type = builder.type;
    this.accessState = builder.accessState;
    this.description = builder.description;
    this.customInstructions = builder.customInstructions;
    this.basicGen = builder.basicGen;
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
        && Objects.equals(basicGen, casted.basicGen);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, accessState, description, customInstructions, basicGen);
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
        + "basicGen='"
        + basicGen
        + '\''
        + "}";
  }

  public static class AiStudioAgentTextGenBuilder {

    protected EnumWrapper<AiStudioAgentTextGenTypeField> type;

    protected final String accessState;

    protected final String description;

    protected String customInstructions;

    protected AiStudioAgentBasicGenTool basicGen;

    public AiStudioAgentTextGenBuilder(String accessState, String description) {
      this.accessState = accessState;
      this.description = description;
      this.type =
          new EnumWrapper<AiStudioAgentTextGenTypeField>(
              AiStudioAgentTextGenTypeField.AI_AGENT_TEXT_GEN);
    }

    public AiStudioAgentTextGenBuilder type(AiStudioAgentTextGenTypeField type) {
      this.type = new EnumWrapper<AiStudioAgentTextGenTypeField>(type);
      return this;
    }

    public AiStudioAgentTextGenBuilder type(EnumWrapper<AiStudioAgentTextGenTypeField> type) {
      this.type = type;
      return this;
    }

    public AiStudioAgentTextGenBuilder customInstructions(String customInstructions) {
      this.customInstructions = customInstructions;
      return this;
    }

    public AiStudioAgentTextGenBuilder basicGen(AiStudioAgentBasicGenTool basicGen) {
      this.basicGen = basicGen;
      return this;
    }

    public AiStudioAgentTextGen build() {
      return new AiStudioAgentTextGen(this);
    }
  }
}
