package com.box.sdkgen.schemas.useravatar;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UserAvatarPicUrlsField extends SerializableObject {

  /** The location of a small-sized avatar. */
  protected String small;

  /** The location of a large-sized avatar. */
  protected String large;

  /** The location of the avatar preview. */
  protected String preview;

  public UserAvatarPicUrlsField() {
    super();
  }

  protected UserAvatarPicUrlsField(Builder builder) {
    super();
    this.small = builder.small;
    this.large = builder.large;
    this.preview = builder.preview;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getSmall() {
    return small;
  }

  public String getLarge() {
    return large;
  }

  public String getPreview() {
    return preview;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserAvatarPicUrlsField casted = (UserAvatarPicUrlsField) o;
    return Objects.equals(small, casted.small)
        && Objects.equals(large, casted.large)
        && Objects.equals(preview, casted.preview);
  }

  @Override
  public int hashCode() {
    return Objects.hash(small, large, preview);
  }

  @Override
  public String toString() {
    return "UserAvatarPicUrlsField{"
        + "small='"
        + small
        + '\''
        + ", "
        + "large='"
        + large
        + '\''
        + ", "
        + "preview='"
        + preview
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String small;

    protected String large;

    protected String preview;

    public Builder small(String small) {
      this.small = small;
      return this;
    }

    public Builder large(String large) {
      this.large = large;
      return this;
    }

    public Builder preview(String preview) {
      this.preview = preview;
      return this;
    }

    public UserAvatarPicUrlsField build() {
      return new UserAvatarPicUrlsField(this);
    }
  }
}
