package com.box.sdkgen.schemas.comments;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class CommentsOrderField extends SerializableObject {

  protected String by;

  @JsonDeserialize(
      using = CommentsOrderDirectionField.CommentsOrderDirectionFieldDeserializer.class)
  @JsonSerialize(using = CommentsOrderDirectionField.CommentsOrderDirectionFieldSerializer.class)
  protected EnumWrapper<CommentsOrderDirectionField> direction;

  public CommentsOrderField() {
    super();
  }

  protected CommentsOrderField(CommentsOrderFieldBuilder builder) {
    super();
    this.by = builder.by;
    this.direction = builder.direction;
  }

  public String getBy() {
    return by;
  }

  public EnumWrapper<CommentsOrderDirectionField> getDirection() {
    return direction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentsOrderField casted = (CommentsOrderField) o;
    return Objects.equals(by, casted.by) && Objects.equals(direction, casted.direction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(by, direction);
  }

  @Override
  public String toString() {
    return "CommentsOrderField{"
        + "by='"
        + by
        + '\''
        + ", "
        + "direction='"
        + direction
        + '\''
        + "}";
  }

  public static class CommentsOrderFieldBuilder {

    protected String by;

    protected EnumWrapper<CommentsOrderDirectionField> direction;

    public CommentsOrderFieldBuilder by(String by) {
      this.by = by;
      return this;
    }

    public CommentsOrderFieldBuilder direction(CommentsOrderDirectionField direction) {
      this.direction = new EnumWrapper<CommentsOrderDirectionField>(direction);
      return this;
    }

    public CommentsOrderFieldBuilder direction(EnumWrapper<CommentsOrderDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public CommentsOrderField build() {
      return new CommentsOrderField(this);
    }
  }
}
