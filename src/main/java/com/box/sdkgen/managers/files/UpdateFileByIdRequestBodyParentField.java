package com.box.sdkgen.managers.files;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UpdateFileByIdRequestBodyParentField extends SerializableObject {

  protected String id;

  @JsonProperty("user_id")
  protected String userId;

  public UpdateFileByIdRequestBodyParentField() {
    super();
  }

  protected UpdateFileByIdRequestBodyParentField(
      UpdateFileByIdRequestBodyParentFieldBuilder builder) {
    super();
    this.id = builder.id;
    this.userId = builder.userId;
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
    UpdateFileByIdRequestBodyParentField casted = (UpdateFileByIdRequestBodyParentField) o;
    return Objects.equals(id, casted.id) && Objects.equals(userId, casted.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId);
  }

  @Override
  public String toString() {
    return "UpdateFileByIdRequestBodyParentField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "userId='"
        + userId
        + '\''
        + "}";
  }

  public static class UpdateFileByIdRequestBodyParentFieldBuilder {

    protected String id;

    protected String userId;

    public UpdateFileByIdRequestBodyParentFieldBuilder id(String id) {
      this.id = id;
      return this;
    }

    public UpdateFileByIdRequestBodyParentFieldBuilder userId(String userId) {
      this.userId = userId;
      return this;
    }

    public UpdateFileByIdRequestBodyParentField build() {
      return new UpdateFileByIdRequestBodyParentField(this);
    }
  }
}
