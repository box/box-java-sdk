package com.box.sdkgen.schemas.keywordskillcard;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class KeywordSkillCardInvocationField extends SerializableObject {

  @JsonDeserialize(
      using =
          KeywordSkillCardInvocationTypeField.KeywordSkillCardInvocationTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          KeywordSkillCardInvocationTypeField.KeywordSkillCardInvocationTypeFieldSerializer.class)
  protected EnumWrapper<KeywordSkillCardInvocationTypeField> type;

  protected final String id;

  public KeywordSkillCardInvocationField(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<KeywordSkillCardInvocationTypeField>(
            KeywordSkillCardInvocationTypeField.SKILL_INVOCATION);
  }

  protected KeywordSkillCardInvocationField(KeywordSkillCardInvocationFieldBuilder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
  }

  public EnumWrapper<KeywordSkillCardInvocationTypeField> getType() {
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
    KeywordSkillCardInvocationField casted = (KeywordSkillCardInvocationField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "KeywordSkillCardInvocationField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class KeywordSkillCardInvocationFieldBuilder {

    protected EnumWrapper<KeywordSkillCardInvocationTypeField> type;

    protected final String id;

    public KeywordSkillCardInvocationFieldBuilder(String id) {
      this.id = id;
      this.type =
          new EnumWrapper<KeywordSkillCardInvocationTypeField>(
              KeywordSkillCardInvocationTypeField.SKILL_INVOCATION);
    }

    public KeywordSkillCardInvocationFieldBuilder type(KeywordSkillCardInvocationTypeField type) {
      this.type = new EnumWrapper<KeywordSkillCardInvocationTypeField>(type);
      return this;
    }

    public KeywordSkillCardInvocationFieldBuilder type(
        EnumWrapper<KeywordSkillCardInvocationTypeField> type) {
      this.type = type;
      return this;
    }

    public KeywordSkillCardInvocationField build() {
      return new KeywordSkillCardInvocationField(this);
    }
  }
}
