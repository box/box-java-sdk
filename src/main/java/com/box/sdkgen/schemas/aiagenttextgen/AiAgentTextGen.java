package com.box.sdkgen.schemas.aiagenttextgen;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aiagentbasicgentool.AiAgentBasicGenTool;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** The AI agent used for generating text. */
@JsonFilter("nullablePropertyFilter")
public class AiAgentTextGen extends SerializableObject {

  /** The type of AI agent used for generating text. */
  @JsonDeserialize(using = AiAgentTextGenTypeField.AiAgentTextGenTypeFieldDeserializer.class)
  @JsonSerialize(using = AiAgentTextGenTypeField.AiAgentTextGenTypeFieldSerializer.class)
  protected EnumWrapper<AiAgentTextGenTypeField> type;

  @JsonProperty("basic_gen")
  protected AiAgentBasicGenTool basicGen;

  public AiAgentTextGen() {
    super();
    this.type = new EnumWrapper<AiAgentTextGenTypeField>(AiAgentTextGenTypeField.AI_AGENT_TEXT_GEN);
  }

  protected AiAgentTextGen(Builder builder) {
    super();
    this.type = builder.type;
    this.basicGen = builder.basicGen;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<AiAgentTextGenTypeField> getType() {
    return type;
  }

  public AiAgentBasicGenTool getBasicGen() {
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
    AiAgentTextGen casted = (AiAgentTextGen) o;
    return Objects.equals(type, casted.type) && Objects.equals(basicGen, casted.basicGen);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, basicGen);
  }

  @Override
  public String toString() {
    return "AiAgentTextGen{" + "type='" + type + '\'' + ", " + "basicGen='" + basicGen + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<AiAgentTextGenTypeField> type;

    protected AiAgentBasicGenTool basicGen;

    public Builder() {
      super();
    }

    public Builder type(AiAgentTextGenTypeField type) {
      this.type = new EnumWrapper<AiAgentTextGenTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AiAgentTextGenTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder basicGen(AiAgentBasicGenTool basicGen) {
      this.basicGen = basicGen;
      return this;
    }

    public AiAgentTextGen build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<AiAgentTextGenTypeField>(AiAgentTextGenTypeField.AI_AGENT_TEXT_GEN);
      }
      return new AiAgentTextGen(this);
    }
  }
}
