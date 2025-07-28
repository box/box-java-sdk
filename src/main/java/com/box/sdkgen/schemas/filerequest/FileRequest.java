package com.box.sdkgen.schemas.filerequest;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileRequest extends SerializableObject {

  protected final String id;

  @JsonDeserialize(using = FileRequestTypeField.FileRequestTypeFieldDeserializer.class)
  @JsonSerialize(using = FileRequestTypeField.FileRequestTypeFieldSerializer.class)
  protected EnumWrapper<FileRequestTypeField> type;

  protected String title;

  @Nullable protected String description;

  @JsonDeserialize(using = FileRequestStatusField.FileRequestStatusFieldDeserializer.class)
  @JsonSerialize(using = FileRequestStatusField.FileRequestStatusFieldSerializer.class)
  protected EnumWrapper<FileRequestStatusField> status;

  @JsonProperty("is_email_required")
  protected Boolean isEmailRequired;

  @JsonProperty("is_description_required")
  protected Boolean isDescriptionRequired;

  @JsonProperty("expires_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date expiresAt;

  protected final FolderMini folder;

  protected String url;

  @Nullable protected String etag;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected final Date createdAt;

  @JsonProperty("updated_by")
  protected UserMini updatedBy;

  @JsonProperty("updated_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected final Date updatedAt;

  public FileRequest(
      @JsonProperty("id") String id,
      @JsonProperty("folder") FolderMini folder,
      @JsonProperty("created_at") Date createdAt,
      @JsonProperty("updated_at") Date updatedAt) {
    super();
    this.id = id;
    this.folder = folder;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.type = new EnumWrapper<FileRequestTypeField>(FileRequestTypeField.FILE_REQUEST);
  }

  protected FileRequest(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.title = builder.title;
    this.description = builder.description;
    this.status = builder.status;
    this.isEmailRequired = builder.isEmailRequired;
    this.isDescriptionRequired = builder.isDescriptionRequired;
    this.expiresAt = builder.expiresAt;
    this.folder = builder.folder;
    this.url = builder.url;
    this.etag = builder.etag;
    this.createdBy = builder.createdBy;
    this.createdAt = builder.createdAt;
    this.updatedBy = builder.updatedBy;
    this.updatedAt = builder.updatedAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<FileRequestTypeField> getType() {
    return type;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public EnumWrapper<FileRequestStatusField> getStatus() {
    return status;
  }

  public Boolean getIsEmailRequired() {
    return isEmailRequired;
  }

  public Boolean getIsDescriptionRequired() {
    return isDescriptionRequired;
  }

  public Date getExpiresAt() {
    return expiresAt;
  }

  public FolderMini getFolder() {
    return folder;
  }

  public String getUrl() {
    return url;
  }

  public String getEtag() {
    return etag;
  }

  public UserMini getCreatedBy() {
    return createdBy;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public UserMini getUpdatedBy() {
    return updatedBy;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileRequest casted = (FileRequest) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(title, casted.title)
        && Objects.equals(description, casted.description)
        && Objects.equals(status, casted.status)
        && Objects.equals(isEmailRequired, casted.isEmailRequired)
        && Objects.equals(isDescriptionRequired, casted.isDescriptionRequired)
        && Objects.equals(expiresAt, casted.expiresAt)
        && Objects.equals(folder, casted.folder)
        && Objects.equals(url, casted.url)
        && Objects.equals(etag, casted.etag)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(updatedBy, casted.updatedBy)
        && Objects.equals(updatedAt, casted.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        title,
        description,
        status,
        isEmailRequired,
        isDescriptionRequired,
        expiresAt,
        folder,
        url,
        etag,
        createdBy,
        createdAt,
        updatedBy,
        updatedAt);
  }

  @Override
  public String toString() {
    return "FileRequest{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "title='"
        + title
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "isEmailRequired='"
        + isEmailRequired
        + '\''
        + ", "
        + "isDescriptionRequired='"
        + isDescriptionRequired
        + '\''
        + ", "
        + "expiresAt='"
        + expiresAt
        + '\''
        + ", "
        + "folder='"
        + folder
        + '\''
        + ", "
        + "url='"
        + url
        + '\''
        + ", "
        + "etag='"
        + etag
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
        + "updatedBy='"
        + updatedBy
        + '\''
        + ", "
        + "updatedAt='"
        + updatedAt
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<FileRequestTypeField> type;

    protected String title;

    protected String description;

    protected EnumWrapper<FileRequestStatusField> status;

    protected Boolean isEmailRequired;

    protected Boolean isDescriptionRequired;

    protected Date expiresAt;

    protected final FolderMini folder;

    protected String url;

    protected String etag;

    protected UserMini createdBy;

    protected final Date createdAt;

    protected UserMini updatedBy;

    protected final Date updatedAt;

    public Builder(String id, FolderMini folder, Date createdAt, Date updatedAt) {
      super();
      this.id = id;
      this.folder = folder;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
      this.type = new EnumWrapper<FileRequestTypeField>(FileRequestTypeField.FILE_REQUEST);
    }

    public Builder type(FileRequestTypeField type) {
      this.type = new EnumWrapper<FileRequestTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<FileRequestTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      this.markNullableFieldAsSet("description");
      return this;
    }

    public Builder status(FileRequestStatusField status) {
      this.status = new EnumWrapper<FileRequestStatusField>(status);
      return this;
    }

    public Builder status(EnumWrapper<FileRequestStatusField> status) {
      this.status = status;
      return this;
    }

    public Builder isEmailRequired(Boolean isEmailRequired) {
      this.isEmailRequired = isEmailRequired;
      return this;
    }

    public Builder isDescriptionRequired(Boolean isDescriptionRequired) {
      this.isDescriptionRequired = isDescriptionRequired;
      return this;
    }

    public Builder expiresAt(Date expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    public Builder url(String url) {
      this.url = url;
      return this;
    }

    public Builder etag(String etag) {
      this.etag = etag;
      this.markNullableFieldAsSet("etag");
      return this;
    }

    public Builder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder updatedBy(UserMini updatedBy) {
      this.updatedBy = updatedBy;
      return this;
    }

    public FileRequest build() {
      return new FileRequest(this);
    }
  }
}
