package com.box.sdkgen.schemas.statusskillcard;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class StatusSkillCardSkillCardTitleField extends SerializableObject {

  protected String code;

  protected final String message;

  public StatusSkillCardSkillCardTitleField(@JsonProperty("message") String message) {
    super();
    this.message = message;
  }

  protected StatusSkillCardSkillCardTitleField(StatusSkillCardSkillCardTitleFieldBuilder builder) {
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
    StatusSkillCardSkillCardTitleField casted = (StatusSkillCardSkillCardTitleField) o;
    return Objects.equals(code, casted.code) && Objects.equals(message, casted.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }

  @Override
  public String toString() {
    return "StatusSkillCardSkillCardTitleField{"
        + "code='"
        + code
        + '\''
        + ", "
        + "message='"
        + message
        + '\''
        + "}";
  }

  public static class StatusSkillCardSkillCardTitleFieldBuilder {

    protected String code;

    protected final String message;

    public StatusSkillCardSkillCardTitleFieldBuilder(String message) {
      this.message = message;
    }

    public StatusSkillCardSkillCardTitleFieldBuilder code(String code) {
      this.code = code;
      return this;
    }

    public StatusSkillCardSkillCardTitleField build() {
      return new StatusSkillCardSkillCardTitleField(this);
    }
  }
}
