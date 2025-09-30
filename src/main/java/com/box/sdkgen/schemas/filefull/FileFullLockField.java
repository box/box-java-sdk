package com.box.sdkgen.schemas.filefull;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileFullLockField extends SerializableObject {

  protected String id;

  @JsonDeserialize(using = FileFullLockTypeField.FileFullLockTypeFieldDeserializer.class)
  @JsonSerialize(using = FileFullLockTypeField.FileFullLockTypeFieldSerializer.class)
  protected EnumWrapper<FileFullLockTypeField> type;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  @JsonProperty("expired_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime expiredAt;

  @JsonProperty("is_download_prevented")
  protected Boolean isDownloadPrevented;

  @JsonDeserialize(using = FileFullLockAppTypeField.FileFullLockAppTypeFieldDeserializer.class)
  @JsonSerialize(using = FileFullLockAppTypeField.FileFullLockAppTypeFieldSerializer.class)
  @JsonProperty("app_type")
  @Nullable
  protected EnumWrapper<FileFullLockAppTypeField> appType;

  public FileFullLockField() {
    super();
  }

  protected FileFullLockField(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.createdBy = builder.createdBy;
    this.createdAt = builder.createdAt;
    this.expiredAt = builder.expiredAt;
    this.isDownloadPrevented = builder.isDownloadPrevented;
    this.appType = builder.appType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<FileFullLockTypeField> getType() {
    return type;
  }

  public UserMini getCreatedBy() {
    return createdBy;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getExpiredAt() {
    return expiredAt;
  }

  public Boolean getIsDownloadPrevented() {
    return isDownloadPrevented;
  }

  public EnumWrapper<FileFullLockAppTypeField> getAppType() {
    return appType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileFullLockField casted = (FileFullLockField) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(expiredAt, casted.expiredAt)
        && Objects.equals(isDownloadPrevented, casted.isDownloadPrevented)
        && Objects.equals(appType, casted.appType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, createdBy, createdAt, expiredAt, isDownloadPrevented, appType);
  }

  @Override
  public String toString() {
    return "FileFullLockField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
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
        + "expiredAt='"
        + expiredAt
        + '\''
        + ", "
        + "isDownloadPrevented='"
        + isDownloadPrevented
        + '\''
        + ", "
        + "appType='"
        + appType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<FileFullLockTypeField> type;

    protected UserMini createdBy;

    protected OffsetDateTime createdAt;

    protected OffsetDateTime expiredAt;

    protected Boolean isDownloadPrevented;

    protected EnumWrapper<FileFullLockAppTypeField> appType;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(FileFullLockTypeField type) {
      this.type = new EnumWrapper<FileFullLockTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<FileFullLockTypeField> type) {
      this.type = type;
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

    public Builder expiredAt(OffsetDateTime expiredAt) {
      this.expiredAt = expiredAt;
      return this;
    }

    public Builder isDownloadPrevented(Boolean isDownloadPrevented) {
      this.isDownloadPrevented = isDownloadPrevented;
      return this;
    }

    public Builder appType(FileFullLockAppTypeField appType) {
      this.appType = new EnumWrapper<FileFullLockAppTypeField>(appType);
      this.markNullableFieldAsSet("app_type");
      return this;
    }

    public Builder appType(EnumWrapper<FileFullLockAppTypeField> appType) {
      this.appType = appType;
      this.markNullableFieldAsSet("app_type");
      return this;
    }

    public FileFullLockField build() {
      return new FileFullLockField(this);
    }
  }
}
