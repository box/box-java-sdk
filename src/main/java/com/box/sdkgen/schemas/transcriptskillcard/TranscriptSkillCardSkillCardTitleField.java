package com.box.sdkgen.schemas.transcriptskillcard;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class TranscriptSkillCardSkillCardTitleField extends SerializableObject {

  protected String code;

  protected final String message;

  public TranscriptSkillCardSkillCardTitleField(@JsonProperty("message") String message) {
    super();
    this.message = message;
  }

  protected TranscriptSkillCardSkillCardTitleField(
      TranscriptSkillCardSkillCardTitleFieldBuilder builder) {
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
    TranscriptSkillCardSkillCardTitleField casted = (TranscriptSkillCardSkillCardTitleField) o;
    return Objects.equals(code, casted.code) && Objects.equals(message, casted.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }

  @Override
  public String toString() {
    return "TranscriptSkillCardSkillCardTitleField{"
        + "code='"
        + code
        + '\''
        + ", "
        + "message='"
        + message
        + '\''
        + "}";
  }

  public static class TranscriptSkillCardSkillCardTitleFieldBuilder {

    protected String code;

    protected final String message;

    public TranscriptSkillCardSkillCardTitleFieldBuilder(String message) {
      this.message = message;
    }

    public TranscriptSkillCardSkillCardTitleFieldBuilder code(String code) {
      this.code = code;
      return this;
    }

    public TranscriptSkillCardSkillCardTitleField build() {
      return new TranscriptSkillCardSkillCardTitleField(this);
    }
  }
}
