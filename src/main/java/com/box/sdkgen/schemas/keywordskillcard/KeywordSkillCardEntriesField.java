package com.box.sdkgen.schemas.keywordskillcard;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class KeywordSkillCardEntriesField extends SerializableObject {

  protected String text;

  public KeywordSkillCardEntriesField() {
    super();
  }

  protected KeywordSkillCardEntriesField(Builder builder) {
    super();
    this.text = builder.text;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getText() {
    return text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KeywordSkillCardEntriesField casted = (KeywordSkillCardEntriesField) o;
    return Objects.equals(text, casted.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text);
  }

  @Override
  public String toString() {
    return "KeywordSkillCardEntriesField{" + "text='" + text + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String text;

    public Builder text(String text) {
      this.text = text;
      return this;
    }

    public KeywordSkillCardEntriesField build() {
      return new KeywordSkillCardEntriesField(this);
    }
  }
}
