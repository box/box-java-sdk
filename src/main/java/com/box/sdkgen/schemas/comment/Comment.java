package com.box.sdkgen.schemas.comment;

import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.commentbase.CommentBase;
import com.box.sdkgen.schemas.commentbase.CommentBaseTypeField;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/** Standard representation of a comment. */
@JsonFilter("nullablePropertyFilter")
public class Comment extends CommentBase {

  /** Whether or not this comment is a reply to another comment. */
  @JsonProperty("is_reply_comment")
  protected Boolean isReplyComment;

  /** The text of the comment, as provided by the user. */
  protected String message;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  /** The time this comment was created. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  /** The time this comment was last modified. */
  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime modifiedAt;

  protected CommentItemField item;

  public Comment() {
    super();
  }

  protected Comment(Builder builder) {
    super(builder);
    this.isReplyComment = builder.isReplyComment;
    this.message = builder.message;
    this.createdBy = builder.createdBy;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    this.item = builder.item;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getModifiedAt() {
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

  public static class Builder extends CommentBase.Builder {

    protected Boolean isReplyComment;

    protected String message;

    protected UserMini createdBy;

    protected OffsetDateTime createdAt;

    protected OffsetDateTime modifiedAt;

    protected CommentItemField item;

    public Builder isReplyComment(Boolean isReplyComment) {
      this.isReplyComment = isReplyComment;
      return this;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder modifiedAt(OffsetDateTime modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public Builder item(CommentItemField item) {
      this.item = item;
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

    public Comment build() {
      return new Comment(this);
    }
  }
}
