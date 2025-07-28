package com.box.sdkgen.managers.comments;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateCommentRequestBody extends SerializableObject {

  protected final String message;

  @JsonProperty("tagged_message")
  protected String taggedMessage;

  protected final CreateCommentRequestBodyItemField item;

  public CreateCommentRequestBody(
      @JsonProperty("message") String message,
      @JsonProperty("item") CreateCommentRequestBodyItemField item) {
    super();
    this.message = message;
    this.item = item;
  }

  protected CreateCommentRequestBody(Builder builder) {
    super();
    this.message = builder.message;
    this.taggedMessage = builder.taggedMessage;
    this.item = builder.item;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getMessage() {
    return message;
  }

  public String getTaggedMessage() {
    return taggedMessage;
  }

  public CreateCommentRequestBodyItemField getItem() {
    return item;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateCommentRequestBody casted = (CreateCommentRequestBody) o;
    return Objects.equals(message, casted.message)
        && Objects.equals(taggedMessage, casted.taggedMessage)
        && Objects.equals(item, casted.item);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, taggedMessage, item);
  }

  @Override
  public String toString() {
    return "CreateCommentRequestBody{"
        + "message='"
        + message
        + '\''
        + ", "
        + "taggedMessage='"
        + taggedMessage
        + '\''
        + ", "
        + "item='"
        + item
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String message;

    protected String taggedMessage;

    protected final CreateCommentRequestBodyItemField item;

    public Builder(String message, CreateCommentRequestBodyItemField item) {
      super();
      this.message = message;
      this.item = item;
    }

    public Builder taggedMessage(String taggedMessage) {
      this.taggedMessage = taggedMessage;
      return this;
    }

    public CreateCommentRequestBody build() {
      return new CreateCommentRequestBody(this);
    }
  }
}
