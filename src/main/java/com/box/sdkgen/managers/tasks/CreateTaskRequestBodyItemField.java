package com.box.sdkgen.managers.tasks;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateTaskRequestBodyItemField extends SerializableObject {

  /** The ID of the file. */
  protected String id;

  /** The value will always be `file`. */
  @JsonDeserialize(
      using =
          CreateTaskRequestBodyItemTypeField.CreateTaskRequestBodyItemTypeFieldDeserializer.class)
  @JsonSerialize(
      using = CreateTaskRequestBodyItemTypeField.CreateTaskRequestBodyItemTypeFieldSerializer.class)
  protected EnumWrapper<CreateTaskRequestBodyItemTypeField> type;

  public CreateTaskRequestBodyItemField() {
    super();
  }

  protected CreateTaskRequestBodyItemField(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<CreateTaskRequestBodyItemTypeField> getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateTaskRequestBodyItemField casted = (CreateTaskRequestBodyItemField) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "CreateTaskRequestBodyItemField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<CreateTaskRequestBodyItemTypeField> type;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(CreateTaskRequestBodyItemTypeField type) {
      this.type = new EnumWrapper<CreateTaskRequestBodyItemTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<CreateTaskRequestBodyItemTypeField> type) {
      this.type = type;
      return this;
    }

    public CreateTaskRequestBodyItemField build() {
      return new CreateTaskRequestBodyItemField(this);
    }
  }
}
