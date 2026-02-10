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
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * A standard representation of a file request, as returned from any file request API endpoints by
 * default.
 */
@JsonFilter("nullablePropertyFilter")
public class FileRequest extends SerializableObject {

  /** The unique identifier for this file request. */
  protected final String id;

  /** The value will always be `file_request`. */
  @JsonDeserialize(using = FileRequestTypeField.FileRequestTypeFieldDeserializer.class)
  @JsonSerialize(using = FileRequestTypeField.FileRequestTypeFieldSerializer.class)
  protected EnumWrapper<FileRequestTypeField> type;

  /**
   * The title of file request. This is shown in the Box UI to users uploading files.
   *
   * <p>This defaults to title of the file request that was copied to create this file request.
   */
  protected String title;

  /**
   * The optional description of this file request. This is shown in the Box UI to users uploading
   * files.
   *
   * <p>This defaults to description of the file request that was copied to create this file
   * request.
   */
  @Nullable protected String description;

  /**
   * The status of the file request. This defaults to `active`.
   *
   * <p>When the status is set to `inactive`, the file request will no longer accept new
   * submissions, and any visitor to the file request URL will receive a `HTTP 404` status code.
   *
   * <p>This defaults to status of file request that was copied to create this file request.
   */
  @JsonDeserialize(using = FileRequestStatusField.FileRequestStatusFieldDeserializer.class)
  @JsonSerialize(using = FileRequestStatusField.FileRequestStatusFieldSerializer.class)
  protected EnumWrapper<FileRequestStatusField> status;

  /**
   * Whether a file request submitter is required to provide their email address.
   *
   * <p>When this setting is set to true, the Box UI will show an email field on the file request
   * form.
   *
   * <p>This defaults to setting of file request that was copied to create this file request.
   */
  @JsonProperty("is_email_required")
  protected Boolean isEmailRequired;

  /**
   * Whether a file request submitter is required to provide a description of the files they are
   * submitting.
   *
   * <p>When this setting is set to true, the Box UI will show a description field on the file
   * request form.
   *
   * <p>This defaults to setting of file request that was copied to create this file request.
   */
  @JsonProperty("is_description_required")
  protected Boolean isDescriptionRequired;

  /**
   * The date after which a file request will no longer accept new submissions.
   *
   * <p>After this date, the `status` will automatically be set to `inactive`.
   */
  @JsonProperty("expires_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime expiresAt;

  protected final FolderMini folder;

  /**
   * The generated URL for this file request. This URL can be shared with users to let them upload
   * files to the associated folder.
   */
  protected String url;

  /**
   * The HTTP `etag` of this file. This can be used in combination with the `If-Match` header when
   * updating a file request. By providing that header, a change will only be performed on the file
   * request if the `etag` on the file request still matches the `etag` provided in the `If-Match`
   * header.
   */
  @Nullable protected String etag;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  /** The date and time when the file request was created. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected final OffsetDateTime createdAt;

  @JsonProperty("updated_by")
  protected UserMini updatedBy;

  /** The date and time when the file request was last updated. */
  @JsonProperty("updated_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected final OffsetDateTime updatedAt;

  public FileRequest(
      @JsonProperty("id") String id,
      @JsonProperty("folder") FolderMini folder,
      @JsonProperty("created_at") OffsetDateTime createdAt,
      @JsonProperty("updated_at") OffsetDateTime updatedAt) {
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

  public OffsetDateTime getExpiresAt() {
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

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public UserMini getUpdatedBy() {
    return updatedBy;
  }

  public OffsetDateTime getUpdatedAt() {
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

    protected OffsetDateTime expiresAt;

    protected final FolderMini folder;

    protected String url;

    protected String etag;

    protected UserMini createdBy;

    protected final OffsetDateTime createdAt;

    protected UserMini updatedBy;

    protected final OffsetDateTime updatedAt;

    public Builder(
        String id, FolderMini folder, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
      super();
      this.id = id;
      this.folder = folder;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
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

    public Builder expiresAt(OffsetDateTime expiresAt) {
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
      if (this.type == null) {
        this.type = new EnumWrapper<FileRequestTypeField>(FileRequestTypeField.FILE_REQUEST);
      }
      return new FileRequest(this);
    }
  }
}
