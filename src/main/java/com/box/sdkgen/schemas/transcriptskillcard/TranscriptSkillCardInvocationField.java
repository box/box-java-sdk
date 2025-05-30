package com.box.sdkgen.schemas.transcriptskillcard;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class TranscriptSkillCardInvocationField extends SerializableObject {

  @JsonDeserialize(
      using =
          TranscriptSkillCardInvocationTypeField.TranscriptSkillCardInvocationTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          TranscriptSkillCardInvocationTypeField.TranscriptSkillCardInvocationTypeFieldSerializer
              .class)
  protected EnumWrapper<TranscriptSkillCardInvocationTypeField> type;

  protected final String id;

  public TranscriptSkillCardInvocationField(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<TranscriptSkillCardInvocationTypeField>(
            TranscriptSkillCardInvocationTypeField.SKILL_INVOCATION);
  }

  protected TranscriptSkillCardInvocationField(TranscriptSkillCardInvocationFieldBuilder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
  }

  public EnumWrapper<TranscriptSkillCardInvocationTypeField> getType() {
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
    TranscriptSkillCardInvocationField casted = (TranscriptSkillCardInvocationField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "TranscriptSkillCardInvocationField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class TranscriptSkillCardInvocationFieldBuilder {

    protected EnumWrapper<TranscriptSkillCardInvocationTypeField> type;

    protected final String id;

    public TranscriptSkillCardInvocationFieldBuilder(String id) {
      this.id = id;
      this.type =
          new EnumWrapper<TranscriptSkillCardInvocationTypeField>(
              TranscriptSkillCardInvocationTypeField.SKILL_INVOCATION);
    }

    public TranscriptSkillCardInvocationFieldBuilder type(
        TranscriptSkillCardInvocationTypeField type) {
      this.type = new EnumWrapper<TranscriptSkillCardInvocationTypeField>(type);
      return this;
    }

    public TranscriptSkillCardInvocationFieldBuilder type(
        EnumWrapper<TranscriptSkillCardInvocationTypeField> type) {
      this.type = type;
      return this;
    }

    public TranscriptSkillCardInvocationField build() {
      return new TranscriptSkillCardInvocationField(this);
    }
  }
}
