package com.box.sdkgen.managers.folders;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateFolderByIdRequestBodyParentField extends SerializableObject {

  /** The ID of parent item. */
  protected String id;

  /**
   * The input for `user_id` is optional. Moving to non-root folder is not allowed when `user_id` is
   * present. Parent folder id should be zero when `user_id` is provided.
   */
  @JsonProperty("user_id")
  protected String userId;

  public UpdateFolderByIdRequestBodyParentField() {
    super();
  }

  protected UpdateFolderByIdRequestBodyParentField(Builder builder) {
    super();
    this.id = builder.id;
    this.userId = builder.userId;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateFolderByIdRequestBodyParentField casted = (UpdateFolderByIdRequestBodyParentField) o;
    return Objects.equals(id, casted.id) && Objects.equals(userId, casted.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId);
  }

  @Override
  public String toString() {
    return "UpdateFolderByIdRequestBodyParentField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "userId='"
        + userId
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected String userId;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder userId(String userId) {
      this.userId = userId;
      return this;
    }

    public UpdateFolderByIdRequestBodyParentField build() {
      return new UpdateFolderByIdRequestBodyParentField(this);
    }
  }
}
