package com.box.sdkgen.managers.weblinks;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UpdateWebLinkByIdRequestBodyParentField extends SerializableObject {

  protected String id;

  @JsonProperty("user_id")
  protected String userId;

  public UpdateWebLinkByIdRequestBodyParentField() {
    super();
  }

  protected UpdateWebLinkByIdRequestBodyParentField(
      UpdateWebLinkByIdRequestBodyParentFieldBuilder builder) {
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
    UpdateWebLinkByIdRequestBodyParentField casted = (UpdateWebLinkByIdRequestBodyParentField) o;
    return Objects.equals(id, casted.id) && Objects.equals(userId, casted.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId);
  }

  @Override
  public String toString() {
    return "UpdateWebLinkByIdRequestBodyParentField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "userId='"
        + userId
        + '\''
        + "}";
  }

  public static class UpdateWebLinkByIdRequestBodyParentFieldBuilder {

    protected String id;

    protected String userId;

    public UpdateWebLinkByIdRequestBodyParentFieldBuilder id(String id) {
      this.id = id;
      return this;
    }

    public UpdateWebLinkByIdRequestBodyParentFieldBuilder userId(String userId) {
      this.userId = userId;
      return this;
    }

    public UpdateWebLinkByIdRequestBodyParentField build() {
      return new UpdateWebLinkByIdRequestBodyParentField(this);
    }
  }
}
