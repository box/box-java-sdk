package com.box.sdkgen.schemas.aiagentreference;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AiAgentReference extends SerializableObject {

  @JsonDeserialize(using = AiAgentReferenceTypeField.AiAgentReferenceTypeFieldDeserializer.class)
  @JsonSerialize(using = AiAgentReferenceTypeField.AiAgentReferenceTypeFieldSerializer.class)
  protected EnumWrapper<AiAgentReferenceTypeField> type;

  protected String id;

  public AiAgentReference() {
    super();
    this.type = new EnumWrapper<AiAgentReferenceTypeField>(AiAgentReferenceTypeField.AI_AGENT_ID);
  }

  protected AiAgentReference(AiAgentReferenceBuilder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
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

  public static class AiAgentReferenceBuilder {

    protected EnumWrapper<AiAgentReferenceTypeField> type;

    protected String id;

    public AiAgentReferenceBuilder type(AiAgentReferenceTypeField type) {
      this.type = new EnumWrapper<AiAgentReferenceTypeField>(type);
      return this;
    }

    public AiAgentReferenceBuilder type(EnumWrapper<AiAgentReferenceTypeField> type) {
      this.type = type;
      return this;
    }

    public AiAgentReferenceBuilder id(String id) {
      this.id = id;
      return this;
    }

    public AiAgentReference build() {
      return new AiAgentReference(this);
    }
  }
}
