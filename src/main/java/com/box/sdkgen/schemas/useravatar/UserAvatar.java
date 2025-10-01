package com.box.sdkgen.schemas.useravatar;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** A resource holding URLs to the avatar uploaded to a Box application. */
@JsonFilter("nullablePropertyFilter")
public class UserAvatar extends SerializableObject {

  /** Represents an object with user avatar URLs. */
  @JsonProperty("pic_urls")
  protected UserAvatarPicUrlsField picUrls;

  public UserAvatar() {
    super();
  }

  protected UserAvatar(Builder builder) {
    super();
    this.picUrls = builder.picUrls;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public UserAvatarPicUrlsField getPicUrls() {
    return picUrls;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserAvatar casted = (UserAvatar) o;
    return Objects.equals(picUrls, casted.picUrls);
  }

  @Override
  public int hashCode() {
    return Objects.hash(picUrls);
  }

  @Override
  public String toString() {
    return "UserAvatar{" + "picUrls='" + picUrls + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected UserAvatarPicUrlsField picUrls;

    public Builder picUrls(UserAvatarPicUrlsField picUrls) {
      this.picUrls = picUrls;
      return this;
    }

    public UserAvatar build() {
      return new UserAvatar(this);
    }
  }
}
