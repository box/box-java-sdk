package com.box.sdkgen.managers.comments;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class UpdateCommentByIdRequestBody extends SerializableObject {

  protected String message;

  public UpdateCommentByIdRequestBody() {
    super();
  }

  protected UpdateCommentByIdRequestBody(UpdateCommentByIdRequestBodyBuilder builder) {
    super();
    this.message = builder.message;
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
    UpdateCommentByIdRequestBody casted = (UpdateCommentByIdRequestBody) o;
    return Objects.equals(message, casted.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }

  @Override
  public String toString() {
    return "UpdateCommentByIdRequestBody{" + "message='" + message + '\'' + "}";
  }

  public static class UpdateCommentByIdRequestBodyBuilder {

    protected String message;

    public UpdateCommentByIdRequestBodyBuilder message(String message) {
      this.message = message;
      return this;
    }

    public UpdateCommentByIdRequestBody build() {
      return new UpdateCommentByIdRequestBody(this);
    }
  }
}
