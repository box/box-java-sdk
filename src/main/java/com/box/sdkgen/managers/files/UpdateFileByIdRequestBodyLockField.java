package com.box.sdkgen.managers.files;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class UpdateFileByIdRequestBodyLockField extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateFileByIdRequestBodyLockAccessField
              .UpdateFileByIdRequestBodyLockAccessFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateFileByIdRequestBodyLockAccessField
              .UpdateFileByIdRequestBodyLockAccessFieldSerializer.class)
  protected EnumWrapper<UpdateFileByIdRequestBodyLockAccessField> access;

  @JsonProperty("expires_at")
  protected String expiresAt;

  @JsonProperty("is_download_prevented")
  protected Boolean isDownloadPrevented;

  public UpdateFileByIdRequestBodyLockField() {
    super();
  }

  protected UpdateFileByIdRequestBodyLockField(UpdateFileByIdRequestBodyLockFieldBuilder builder) {
    super();
    this.access = builder.access;
    this.expiresAt = builder.expiresAt;
    this.isDownloadPrevented = builder.isDownloadPrevented;
  }

  public EnumWrapper<UpdateFileByIdRequestBodyLockAccessField> getAccess() {
    return access;
  }

  public String getExpiresAt() {
    return expiresAt;
  }

  public Boolean getIsDownloadPrevented() {
    return isDownloadPrevented;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateFileByIdRequestBodyLockField casted = (UpdateFileByIdRequestBodyLockField) o;
    return Objects.equals(access, casted.access)
        && Objects.equals(expiresAt, casted.expiresAt)
        && Objects.equals(isDownloadPrevented, casted.isDownloadPrevented);
  }

  @Override
  public int hashCode() {
    return Objects.hash(access, expiresAt, isDownloadPrevented);
  }

  @Override
  public String toString() {
    return "UpdateFileByIdRequestBodyLockField{"
        + "access='"
        + access
        + '\''
        + ", "
        + "expiresAt='"
        + expiresAt
        + '\''
        + ", "
        + "isDownloadPrevented='"
        + isDownloadPrevented
        + '\''
        + "}";
  }

  public static class UpdateFileByIdRequestBodyLockFieldBuilder {

    protected EnumWrapper<UpdateFileByIdRequestBodyLockAccessField> access;

    protected String expiresAt;

    protected Boolean isDownloadPrevented;

    public UpdateFileByIdRequestBodyLockFieldBuilder access(
        UpdateFileByIdRequestBodyLockAccessField access) {
      this.access = new EnumWrapper<UpdateFileByIdRequestBodyLockAccessField>(access);
      return this;
    }

    public UpdateFileByIdRequestBodyLockFieldBuilder access(
        EnumWrapper<UpdateFileByIdRequestBodyLockAccessField> access) {
      this.access = access;
      return this;
    }

    public UpdateFileByIdRequestBodyLockFieldBuilder expiresAt(String expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    public UpdateFileByIdRequestBodyLockFieldBuilder isDownloadPrevented(
        Boolean isDownloadPrevented) {
      this.isDownloadPrevented = isDownloadPrevented;
      return this;
    }

    public UpdateFileByIdRequestBodyLockField build() {
      return new UpdateFileByIdRequestBodyLockField(this);
    }
  }
}
