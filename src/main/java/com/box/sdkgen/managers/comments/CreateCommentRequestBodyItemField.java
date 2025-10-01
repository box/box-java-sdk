package com.box.sdkgen.managers.comments;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateCommentRequestBodyItemField extends SerializableObject {

  /** The ID of the item. */
  protected final String id;

  /** The type of the item that this comment will be placed on. */
  @JsonDeserialize(
      using =
          CreateCommentRequestBodyItemTypeField.CreateCommentRequestBodyItemTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          CreateCommentRequestBodyItemTypeField.CreateCommentRequestBodyItemTypeFieldSerializer
              .class)
  protected final EnumWrapper<CreateCommentRequestBodyItemTypeField> type;

  public CreateCommentRequestBodyItemField(String id, CreateCommentRequestBodyItemTypeField type) {
    super();
    this.id = id;
    this.type = new EnumWrapper<CreateCommentRequestBodyItemTypeField>(type);
  }

  public CreateCommentRequestBodyItemField(
      @JsonProperty("id") String id,
      @JsonProperty("type") EnumWrapper<CreateCommentRequestBodyItemTypeField> type) {
    super();
    this.id = id;
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<CreateCommentRequestBodyItemTypeField> getType() {
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
    CreateCommentRequestBodyItemField casted = (CreateCommentRequestBodyItemField) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "CreateCommentRequestBodyItemField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }
}
