package com.box.sdkgen.schemas.comment;

import com.box.sdkgen.schemas.commentbase.CommentBase;
import com.box.sdkgen.schemas.commentbase.CommentBaseTypeField;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class Comment extends CommentBase {

  @JsonProperty("is_reply_comment")
  protected Boolean isReplyComment;

  protected String message;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  protected CommentItemField item;

  public Comment() {
    super();
  }

  protected Comment(CommentBuilder builder) {
    super(builder);
    this.isReplyComment = builder.isReplyComment;
    this.message = builder.message;
    this.createdBy = builder.createdBy;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    this.item = builder.item;
  }

  public Boolean getIsReplyComment() {
    return isReplyComment;
  }

  public String getMessage() {
    return message;
  }

  public UserMini getCreatedBy() {
    return createdBy;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getModifiedAt() {
    return modifiedAt;
  }

  public CommentItemField getItem() {
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
    Comment casted = (Comment) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(isReplyComment, casted.isReplyComment)
        && Objects.equals(message, casted.message)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt)
        && Objects.equals(item, casted.item);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, isReplyComment, message, createdBy, createdAt, modifiedAt, item);
  }

  @Override
  public String toString() {
    return "Comment{"
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
        + "}";
  }

  public static class CommentBuilder extends CommentBaseBuilder {

    protected Boolean isReplyComment;

    protected String message;

    protected UserMini createdBy;

    protected String createdAt;

    protected String modifiedAt;

    protected CommentItemField item;

    public CommentBuilder isReplyComment(Boolean isReplyComment) {
      this.isReplyComment = isReplyComment;
      return this;
    }

    public CommentBuilder message(String message) {
      this.message = message;
      return this;
    }

    public CommentBuilder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public CommentBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public CommentBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public CommentBuilder item(CommentItemField item) {
      this.item = item;
      return this;
    }

    @Override
    public CommentBuilder id(String id) {
      this.id = id;
      return this;
    }

    @Override
    public CommentBuilder type(CommentBaseTypeField type) {
      this.type = new EnumWrapper<CommentBaseTypeField>(type);
      return this;
    }

    @Override
    public CommentBuilder type(EnumWrapper<CommentBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public Comment build() {
      return new Comment(this);
    }
  }
}
