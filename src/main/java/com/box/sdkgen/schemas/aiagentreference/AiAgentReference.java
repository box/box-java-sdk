package com.box.sdkgen.schemas.aiagentreference;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** The AI agent used to handle queries. */
@JsonFilter("nullablePropertyFilter")
public class AiAgentReference extends SerializableObject {

  /** The type of AI agent used to handle queries. */
  @JsonDeserialize(using = AiAgentReferenceTypeField.AiAgentReferenceTypeFieldDeserializer.class)
  @JsonSerialize(using = AiAgentReferenceTypeField.AiAgentReferenceTypeFieldSerializer.class)
  protected EnumWrapper<AiAgentReferenceTypeField> type;

  /**
   * The ID of an Agent. This can be a numeric ID for custom agents (for example, `14031`) or a
   * unique identifier for pre-built agents (for example, `enhanced_extract_agent` for the [Enhanced
   * Extract Agent](g://box-ai/ai-tutorials/extract-metadata-structured/#enhanced-extract-agent)).
   */
  protected String id;

  public AiAgentReference() {
    super();
    this.type = new EnumWrapper<AiAgentReferenceTypeField>(AiAgentReferenceTypeField.AI_AGENT_ID);
  }

  protected AiAgentReference(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<AiAgentReferenceTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiAgentReference casted = (AiAgentReference) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "AiAgentReference{" + "type='" + type + '\'' + ", " + "id='" + id + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<AiAgentReferenceTypeField> type;

    protected String id;

    public Builder() {
      super();
      this.type = new EnumWrapper<AiAgentReferenceTypeField>(AiAgentReferenceTypeField.AI_AGENT_ID);
    }

    public Builder type(AiAgentReferenceTypeField type) {
      this.type = new EnumWrapper<AiAgentReferenceTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AiAgentReferenceTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public AiAgentReference build() {
      return new AiAgentReference(this);
    }
  }
}
