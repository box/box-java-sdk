package com.box.sdkgen.schemas.keywordskillcard;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class KeywordSkillCardSkillCardTitleField extends SerializableObject {

  protected String code;

  protected final String message;

  public KeywordSkillCardSkillCardTitleField(@JsonProperty("message") String message) {
    super();
    this.message = message;
  }

  protected KeywordSkillCardSkillCardTitleField(
      KeywordSkillCardSkillCardTitleFieldBuilder builder) {
    super();
    this.code = builder.code;
    this.message = builder.message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KeywordSkillCardSkillCardTitleField casted = (KeywordSkillCardSkillCardTitleField) o;
    return Objects.equals(code, casted.code) && Objects.equals(message, casted.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }

  @Override
  public String toString() {
    return "KeywordSkillCardSkillCardTitleField{"
        + "code='"
        + code
        + '\''
        + ", "
        + "message='"
        + message
        + '\''
        + "}";
  }

  public static class KeywordSkillCardSkillCardTitleFieldBuilder {

    protected String code;

    protected final String message;

    public KeywordSkillCardSkillCardTitleFieldBuilder(String message) {
      this.message = message;
    }

    public KeywordSkillCardSkillCardTitleFieldBuilder code(String code) {
      this.code = code;
      return this;
    }

    public KeywordSkillCardSkillCardTitleField build() {
      return new KeywordSkillCardSkillCardTitleField(this);
    }
  }
}
