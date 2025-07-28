package com.box.sdkgen.schemas.keywordskillcard;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class KeywordSkillCardSkillField extends SerializableObject {

  @JsonDeserialize(
      using = KeywordSkillCardSkillTypeField.KeywordSkillCardSkillTypeFieldDeserializer.class)
  @JsonSerialize(
      using = KeywordSkillCardSkillTypeField.KeywordSkillCardSkillTypeFieldSerializer.class)
  protected EnumWrapper<KeywordSkillCardSkillTypeField> type;

  protected final String id;

  public KeywordSkillCardSkillField(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<KeywordSkillCardSkillTypeField>(KeywordSkillCardSkillTypeField.SERVICE);
  }

  protected KeywordSkillCardSkillField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<KeywordSkillCardSkillTypeField> getType() {
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
    KeywordSkillCardSkillField casted = (KeywordSkillCardSkillField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "KeywordSkillCardSkillField{" + "type='" + type + '\'' + ", " + "id='" + id + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<KeywordSkillCardSkillTypeField> type;

    protected final String id;

    public Builder(String id) {
      super();
      this.id = id;
      this.type =
          new EnumWrapper<KeywordSkillCardSkillTypeField>(KeywordSkillCardSkillTypeField.SERVICE);
    }

    public Builder type(KeywordSkillCardSkillTypeField type) {
      this.type = new EnumWrapper<KeywordSkillCardSkillTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<KeywordSkillCardSkillTypeField> type) {
      this.type = type;
      return this;
    }

    public KeywordSkillCardSkillField build() {
      return new KeywordSkillCardSkillField(this);
    }
  }
}
