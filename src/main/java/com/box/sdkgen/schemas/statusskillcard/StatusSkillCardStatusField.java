package com.box.sdkgen.schemas.statusskillcard;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class StatusSkillCardStatusField extends SerializableObject {

  /**
   * A code for the status of this Skill invocation. By default each of these will have their own
   * accompanied messages. These can be adjusted by setting the `message` value on this object.
   */
  @JsonDeserialize(
      using = StatusSkillCardStatusCodeField.StatusSkillCardStatusCodeFieldDeserializer.class)
  @JsonSerialize(
      using = StatusSkillCardStatusCodeField.StatusSkillCardStatusCodeFieldSerializer.class)
  protected final EnumWrapper<StatusSkillCardStatusCodeField> code;

  /**
   * A custom message that can be provided with this status. This will be shown in the web app to
   * the end user.
   */
  protected String message;

  public StatusSkillCardStatusField(StatusSkillCardStatusCodeField code) {
    super();
    this.code = new EnumWrapper<StatusSkillCardStatusCodeField>(code);
  }

  public StatusSkillCardStatusField(
      @JsonProperty("code") EnumWrapper<StatusSkillCardStatusCodeField> code) {
    super();
    this.code = code;
  }

  protected StatusSkillCardStatusField(Builder builder) {
    super();
    this.code = builder.code;
    this.message = builder.message;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<StatusSkillCardStatusCodeField> getCode() {
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
    StatusSkillCardStatusField casted = (StatusSkillCardStatusField) o;
    return Objects.equals(code, casted.code) && Objects.equals(message, casted.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }

  @Override
  public String toString() {
    return "StatusSkillCardStatusField{"
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

    protected final EnumWrapper<StatusSkillCardStatusCodeField> code;

    protected String message;

    public Builder(StatusSkillCardStatusCodeField code) {
      super();
      this.code = new EnumWrapper<StatusSkillCardStatusCodeField>(code);
    }

    public Builder(EnumWrapper<StatusSkillCardStatusCodeField> code) {
      super();
      this.code = code;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public StatusSkillCardStatusField build() {
      return new StatusSkillCardStatusField(this);
    }
  }
}
