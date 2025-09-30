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
public class StatusSkillCardSkillField extends SerializableObject {

  @JsonDeserialize(
      using = StatusSkillCardSkillTypeField.StatusSkillCardSkillTypeFieldDeserializer.class)
  @JsonSerialize(
      using = StatusSkillCardSkillTypeField.StatusSkillCardSkillTypeFieldSerializer.class)
  protected EnumWrapper<StatusSkillCardSkillTypeField> type;

  protected final String id;

  public StatusSkillCardSkillField(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<StatusSkillCardSkillTypeField>(StatusSkillCardSkillTypeField.SERVICE);
  }

  protected StatusSkillCardSkillField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<StatusSkillCardSkillTypeField> getType() {
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
    StatusSkillCardSkillField casted = (StatusSkillCardSkillField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "StatusSkillCardSkillField{" + "type='" + type + '\'' + ", " + "id='" + id + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<StatusSkillCardSkillTypeField> type;

    protected final String id;

    public Builder(String id) {
      super();
      this.id = id;
      this.type =
          new EnumWrapper<StatusSkillCardSkillTypeField>(StatusSkillCardSkillTypeField.SERVICE);
    }

    public Builder type(StatusSkillCardSkillTypeField type) {
      this.type = new EnumWrapper<StatusSkillCardSkillTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<StatusSkillCardSkillTypeField> type) {
      this.type = type;
      return this;
    }

    public StatusSkillCardSkillField build() {
      return new StatusSkillCardSkillField(this);
    }
  }
}
