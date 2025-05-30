package com.box.sdkgen.schemas.useravatar;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UserAvatar extends SerializableObject {

  @JsonProperty("pic_urls")
  protected UserAvatarPicUrlsField picUrls;

  public UserAvatar() {
    super();
  }

  protected UserAvatar(UserAvatarBuilder builder) {
    super();
    this.picUrls = builder.picUrls;
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

  public static class UserAvatarBuilder {

    protected UserAvatarPicUrlsField picUrls;

    public UserAvatarBuilder picUrls(UserAvatarPicUrlsField picUrls) {
      this.picUrls = picUrls;
      return this;
    }

    public UserAvatar build() {
      return new UserAvatar(this);
    }
  }
}
