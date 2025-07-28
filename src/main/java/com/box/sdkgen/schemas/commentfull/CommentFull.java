package com.box.sdkgen.schemas.commentfull;

import com.box.sdkgen.schemas.comment.Comment;
import com.box.sdkgen.schemas.comment.CommentItemField;
import com.box.sdkgen.schemas.commentbase.CommentBaseTypeField;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CommentFull extends Comment {

  @JsonProperty("tagged_message")
  protected String taggedMessage;

  public CommentFull() {
    super();
  }

  protected CommentFull(Builder builder) {
    super(builder);
    this.taggedMessage = builder.taggedMessage;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getTaggedMessage() {
    return taggedMessage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentFull casted = (CommentFull) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(isReplyComment, casted.isReplyComment)
        && Objects.equals(message, casted.message)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt)
        && Objects.equals(item, casted.item)
        && Objects.equals(taggedMessage, casted.taggedMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, type, isReplyComment, message, createdBy, createdAt, modifiedAt, item, taggedMessage);
  }

  @Override
  public String toString() {
    return "CommentFull{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "isReplyComment='"
        + isReplyComment
        + '\''
        + ", "
        + "message='"
        + message
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "modifiedAt='"
        + modifiedAt
        + '\''
        + ", "
        + "item='"
        + item
        + '\''
        + ", "
        + "taggedMessage='"
        + taggedMessage
        + '\''
        + "}";
  }

  public static class Builder extends Comment.Builder {

    protected String taggedMessage;

    public Builder taggedMessage(String taggedMessage) {
      this.taggedMessage = taggedMessage;
      return this;
    }

    @Override
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    @Override
    public Builder type(CommentBaseTypeField type) {
      this.type = new EnumWrapper<CommentBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<CommentBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder isReplyComment(Boolean isReplyComment) {
      this.isReplyComment = isReplyComment;
      return this;
    }

    @Override
    public Builder message(String message) {
      this.message = message;
      return this;
    }

    @Override
    public Builder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    @Override
    public Builder createdAt(Date createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    @Override
    public Builder modifiedAt(Date modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    @Override
    public Builder item(CommentItemField item) {
      this.item = item;
      return this;
    }

    public CommentFull build() {
      return new CommentFull(this);
    }
  }
}
