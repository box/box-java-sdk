package com.box.sdkgen.schemas.filerequest;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class FileRequest extends SerializableObject {

  protected final String id;

  @JsonDeserialize(using = FileRequestTypeField.FileRequestTypeFieldDeserializer.class)
  @JsonSerialize(using = FileRequestTypeField.FileRequestTypeFieldSerializer.class)
  protected EnumWrapper<FileRequestTypeField> type;

  protected String title;

  protected String description;

  @JsonDeserialize(using = FileRequestStatusField.FileRequestStatusFieldDeserializer.class)
  @JsonSerialize(using = FileRequestStatusField.FileRequestStatusFieldSerializer.class)
  protected EnumWrapper<FileRequestStatusField> status;

  @JsonProperty("is_email_required")
  protected Boolean isEmailRequired;

  @JsonProperty("is_description_required")
  protected Boolean isDescriptionRequired;

  @JsonProperty("expires_at")
  protected String expiresAt;

  protected final FolderMini folder;

  protected String url;

  protected String etag;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("created_at")
  protected final String createdAt;

  @JsonProperty("updated_by")
  protected UserMini updatedBy;

  @JsonProperty("updated_at")
  protected final String updatedAt;

  public FileRequest(
      @JsonProperty("id") String id,
      @JsonProperty("folder") FolderMini folder,
      @JsonProperty("created_at") String createdAt,
      @JsonProperty("updated_at") String updatedAt) {
    super();
    this.id = id;
    this.folder = folder;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.type = new EnumWrapper<FileRequestTypeField>(FileRequestTypeField.FILE_REQUEST);
  }

  protected FileRequest(FileRequestBuilder builder) {
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

  public String getExpiresAt() {
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

  public String getCreatedAt() {
    return createdAt;
  }

  public UserMini getUpdatedBy() {
    return updatedBy;
  }

  public String getUpdatedAt() {
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

  public static class FileRequestBuilder {

    protected final String id;

    protected EnumWrapper<FileRequestTypeField> type;

    protected String title;

    protected String description;

    protected EnumWrapper<FileRequestStatusField> status;

    protected Boolean isEmailRequired;

    protected Boolean isDescriptionRequired;

    protected String expiresAt;

    protected final FolderMini folder;

    protected String url;

    protected String etag;

    protected UserMini createdBy;

    protected final String createdAt;

    protected UserMini updatedBy;

    protected final String updatedAt;

    public FileRequestBuilder(String id, FolderMini folder, String createdAt, String updatedAt) {
      this.id = id;
      this.folder = folder;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
      this.type = new EnumWrapper<FileRequestTypeField>(FileRequestTypeField.FILE_REQUEST);
    }

    public FileRequestBuilder type(FileRequestTypeField type) {
      this.type = new EnumWrapper<FileRequestTypeField>(type);
      return this;
    }

    public FileRequestBuilder type(EnumWrapper<FileRequestTypeField> type) {
      this.type = type;
      return this;
    }

    public FileRequestBuilder title(String title) {
      this.title = title;
      return this;
    }

    public FileRequestBuilder description(String description) {
      this.description = description;
      return this;
    }

    public FileRequestBuilder status(FileRequestStatusField status) {
      this.status = new EnumWrapper<FileRequestStatusField>(status);
      return this;
    }

    public FileRequestBuilder status(EnumWrapper<FileRequestStatusField> status) {
      this.status = status;
      return this;
    }

    public FileRequestBuilder isEmailRequired(Boolean isEmailRequired) {
      this.isEmailRequired = isEmailRequired;
      return this;
    }

    public FileRequestBuilder isDescriptionRequired(Boolean isDescriptionRequired) {
      this.isDescriptionRequired = isDescriptionRequired;
      return this;
    }

    public FileRequestBuilder expiresAt(String expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    public FileRequestBuilder url(String url) {
      this.url = url;
      return this;
    }

    public FileRequestBuilder etag(String etag) {
      this.etag = etag;
      return this;
    }

    public FileRequestBuilder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public FileRequestBuilder updatedBy(UserMini updatedBy) {
      this.updatedBy = updatedBy;
      return this;
    }

    public FileRequest build() {
      return new FileRequest(this);
    }
  }
}
