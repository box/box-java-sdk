package com.box.sdkgen.managers.files;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
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
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date expiresAt;

  @JsonProperty("is_download_prevented")
  protected Boolean isDownloadPrevented;

  public UpdateFileByIdRequestBodyLockField() {
    super();
  }

  protected UpdateFileByIdRequestBodyLockField(Builder builder) {
    super();
    this.access = builder.access;
    this.expiresAt = builder.expiresAt;
    this.isDownloadPrevented = builder.isDownloadPrevented;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateFileByIdRequestBodyLockAccessField> getAccess() {
    return access;
  }

  public Date getExpiresAt() {
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

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateFileByIdRequestBodyLockAccessField> access;

    protected Date expiresAt;

    protected Boolean isDownloadPrevented;

    public Builder access(UpdateFileByIdRequestBodyLockAccessField access) {
      this.access = new EnumWrapper<UpdateFileByIdRequestBodyLockAccessField>(access);
      return this;
    }

    public Builder access(EnumWrapper<UpdateFileByIdRequestBodyLockAccessField> access) {
      this.access = access;
      return this;
    }

    public Builder expiresAt(Date expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    public Builder isDownloadPrevented(Boolean isDownloadPrevented) {
      this.isDownloadPrevented = isDownloadPrevented;
      return this;
    }

    public UpdateFileByIdRequestBodyLockField build() {
      return new UpdateFileByIdRequestBodyLockField(this);
    }
  }
}
