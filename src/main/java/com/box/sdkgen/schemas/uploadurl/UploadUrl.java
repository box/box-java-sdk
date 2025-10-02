package com.box.sdkgen.schemas.uploadurl;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** The details for the upload session for the file. */
@JsonFilter("nullablePropertyFilter")
public class UploadUrl extends SerializableObject {

  /** A URL for an upload session that can be used to upload the file. */
  @JsonProperty("upload_url")
  protected String uploadUrl;

  /** An optional access token to use to upload the file. */
  @JsonProperty("upload_token")
  protected String uploadToken;

  public UploadUrl() {
    super();
  }

  protected UploadUrl(Builder builder) {
    super();
    this.uploadUrl = builder.uploadUrl;
    this.uploadToken = builder.uploadToken;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getUploadUrl() {
    return uploadUrl;
  }

  public String getUploadToken() {
    return uploadToken;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadUrl casted = (UploadUrl) o;
    return Objects.equals(uploadUrl, casted.uploadUrl)
        && Objects.equals(uploadToken, casted.uploadToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uploadUrl, uploadToken);
  }

  @Override
  public String toString() {
    return "UploadUrl{"
        + "uploadUrl='"
        + uploadUrl
        + '\''
        + ", "
        + "uploadToken='"
        + uploadToken
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String uploadUrl;

    protected String uploadToken;

    public Builder uploadUrl(String uploadUrl) {
      this.uploadUrl = uploadUrl;
      return this;
    }

    public Builder uploadToken(String uploadToken) {
      this.uploadToken = uploadToken;
      return this;
    }

    public UploadUrl build() {
      return new UploadUrl(this);
    }
  }
}
