package com.box.sdkgen.schemas.statusskillcard;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

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

  protected StatusSkillCardSkillField(StatusSkillCardSkillFieldBuilder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
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

  public static class StatusSkillCardSkillFieldBuilder {

    protected EnumWrapper<StatusSkillCardSkillTypeField> type;

    protected final String id;

    public StatusSkillCardSkillFieldBuilder(String id) {
      this.id = id;
      this.type =
          new EnumWrapper<StatusSkillCardSkillTypeField>(StatusSkillCardSkillTypeField.SERVICE);
    }

    public StatusSkillCardSkillFieldBuilder type(StatusSkillCardSkillTypeField type) {
      this.type = new EnumWrapper<StatusSkillCardSkillTypeField>(type);
      return this;
    }

    public StatusSkillCardSkillFieldBuilder type(EnumWrapper<StatusSkillCardSkillTypeField> type) {
      this.type = type;
      return this;
    }

    public StatusSkillCardSkillField build() {
      return new StatusSkillCardSkillField(this);
    }
  }
}
