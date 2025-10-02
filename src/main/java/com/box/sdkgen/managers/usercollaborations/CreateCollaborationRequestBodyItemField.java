package com.box.sdkgen.managers.usercollaborations;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateCollaborationRequestBodyItemField extends SerializableObject {

  /** The type of the item that this collaboration will be granted access to. */
  @JsonDeserialize(
      using =
          CreateCollaborationRequestBodyItemTypeField
              .CreateCollaborationRequestBodyItemTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateCollaborationRequestBodyItemTypeField
              .CreateCollaborationRequestBodyItemTypeFieldSerializer.class)
  protected EnumWrapper<CreateCollaborationRequestBodyItemTypeField> type;

  /** The ID of the item that will be granted access to. */
  protected String id;

  public CreateCollaborationRequestBodyItemField() {
    super();
  }

  protected CreateCollaborationRequestBodyItemField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<CreateCollaborationRequestBodyItemTypeField> getType() {
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
    CreateCollaborationRequestBodyItemField casted = (CreateCollaborationRequestBodyItemField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "CreateCollaborationRequestBodyItemField{"
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

    protected EnumWrapper<CreateCollaborationRequestBodyItemTypeField> type;

    protected String id;

    public Builder type(CreateCollaborationRequestBodyItemTypeField type) {
      this.type = new EnumWrapper<CreateCollaborationRequestBodyItemTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<CreateCollaborationRequestBodyItemTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public CreateCollaborationRequestBodyItemField build() {
      return new CreateCollaborationRequestBodyItemField(this);
    }
  }
}
