package com.box.sdkgen.managers.avatars;

import java.io.InputStream;

public class CreateUserAvatarRequestBody {

  /**
   * The image file to be uploaded to Box. Accepted file extensions are `.jpg` or `.png`. The
   * maximum file size is 1MB.
   */
  public final InputStream pic;

  public String picFileName;

  public String picContentType;

  public CreateUserAvatarRequestBody(InputStream pic) {
    this.pic = pic;
  }

  protected CreateUserAvatarRequestBody(Builder builder) {
    this.pic = builder.pic;
    this.picFileName = builder.picFileName;
    this.picContentType = builder.picContentType;
  }

  public InputStream getPic() {
    return pic;
  }

  public String getPicFileName() {
    return picFileName;
  }

  public String getPicContentType() {
    return picContentType;
  }

  public static class Builder {

    protected final InputStream pic;

    protected String picFileName;

    protected String picContentType;

    public Builder(InputStream pic) {
      this.pic = pic;
    }

    public Builder picFileName(String picFileName) {
      this.picFileName = picFileName;
      return this;
    }

    public Builder picContentType(String picContentType) {
      this.picContentType = picContentType;
      return this;
    }

    public CreateUserAvatarRequestBody build() {
      return new CreateUserAvatarRequestBody(this);
    }
  }
}
