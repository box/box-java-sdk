package com.box.sdkgen.schemas.useravatar;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class UserAvatarPicUrlsField extends SerializableObject {

  protected String small;

  protected String large;

  protected String preview;

  public UserAvatarPicUrlsField() {
    super();
  }

  protected UserAvatarPicUrlsField(UserAvatarPicUrlsFieldBuilder builder) {
    super();
    this.small = builder.small;
    this.large = builder.large;
    this.preview = builder.preview;
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

  public static class UserAvatarPicUrlsFieldBuilder {

    protected String small;

    protected String large;

    protected String preview;

    public UserAvatarPicUrlsFieldBuilder small(String small) {
      this.small = small;
      return this;
    }

    public UserAvatarPicUrlsFieldBuilder large(String large) {
      this.large = large;
      return this;
    }

    public UserAvatarPicUrlsFieldBuilder preview(String preview) {
      this.preview = preview;
      return this;
    }

    public UserAvatarPicUrlsField build() {
      return new UserAvatarPicUrlsField(this);
    }
  }
}
