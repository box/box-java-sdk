package com.box.sdkgen.schemas.timelineskillcard;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TimelineSkillCardSkillField extends SerializableObject {

  @JsonDeserialize(
      using = TimelineSkillCardSkillTypeField.TimelineSkillCardSkillTypeFieldDeserializer.class)
  @JsonSerialize(
      using = TimelineSkillCardSkillTypeField.TimelineSkillCardSkillTypeFieldSerializer.class)
  protected EnumWrapper<TimelineSkillCardSkillTypeField> type;

  protected final String id;

  public TimelineSkillCardSkillField(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<TimelineSkillCardSkillTypeField>(TimelineSkillCardSkillTypeField.SERVICE);
  }

  protected TimelineSkillCardSkillField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<TimelineSkillCardSkillTypeField> getType() {
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
    TimelineSkillCardSkillField casted = (TimelineSkillCardSkillField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "TimelineSkillCardSkillField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<TimelineSkillCardSkillTypeField> type;

    protected final String id;

    public Builder(String id) {
      super();
      this.id = id;
      this.type =
          new EnumWrapper<TimelineSkillCardSkillTypeField>(TimelineSkillCardSkillTypeField.SERVICE);
    }

    public Builder type(TimelineSkillCardSkillTypeField type) {
      this.type = new EnumWrapper<TimelineSkillCardSkillTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TimelineSkillCardSkillTypeField> type) {
      this.type = type;
      return this;
    }

    public TimelineSkillCardSkillField build() {
      return new TimelineSkillCardSkillField(this);
    }
  }
}
