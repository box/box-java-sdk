package com.box.sdkgen.schemas.keywordskillcard;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class KeywordSkillCardEntriesField extends SerializableObject {

  protected String text;

  public KeywordSkillCardEntriesField() {
    super();
  }

  protected KeywordSkillCardEntriesField(KeywordSkillCardEntriesFieldBuilder builder) {
    super();
    this.text = builder.text;
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

  public static class KeywordSkillCardEntriesFieldBuilder {

    protected String text;

    public KeywordSkillCardEntriesFieldBuilder text(String text) {
      this.text = text;
      return this;
    }

    public KeywordSkillCardEntriesField build() {
      return new KeywordSkillCardEntriesField(this);
    }
  }
}
