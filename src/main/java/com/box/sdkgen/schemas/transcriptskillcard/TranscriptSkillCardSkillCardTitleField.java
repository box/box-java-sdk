package com.box.sdkgen.schemas.transcriptskillcard;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TranscriptSkillCardSkillCardTitleField extends SerializableObject {

  /** An optional identifier for the title. */
  protected String code;

  /** The actual title to show in the UI. */
  protected final String message;

  public TranscriptSkillCardSkillCardTitleField(@JsonProperty("message") String message) {
    super();
    this.message = message;
  }

  protected TranscriptSkillCardSkillCardTitleField(Builder builder) {
    super();
    this.code = builder.code;
    this.message = builder.message;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected String code;

    protected final String message;

    public Builder(String message) {
      super();
      this.message = message;
    }

    public Builder code(String code) {
      this.code = code;
      return this;
    }

    public TranscriptSkillCardSkillCardTitleField build() {
      return new TranscriptSkillCardSkillCardTitleField(this);
    }
  }
}
