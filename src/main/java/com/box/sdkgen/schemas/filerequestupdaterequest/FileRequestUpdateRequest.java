package com.box.sdkgen.schemas.filerequestupdaterequest;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/** The request body to update a file request. */
@JsonFilter("nullablePropertyFilter")
public class FileRequestUpdateRequest extends SerializableObject {

  /**
   * An optional new title for the file request. This can be used to change the title of the file
   * request.
   *
   * <p>This will default to the value on the existing file request.
   */
  protected String title;

  /**
   * An optional new description for the file request. This can be used to change the description of
   * the file request.
   *
   * <p>This will default to the value on the existing file request.
   */
  protected String description;

  /**
   * An optional new status of the file request.
   *
   * <p>When the status is set to `inactive`, the file request will no longer accept new
   * submissions, and any visitor to the file request URL will receive a `HTTP 404` status code.
   *
   * <p>This will default to the value on the existing file request.
   */
  @JsonDeserialize(
      using =
          FileRequestUpdateRequestStatusField.FileRequestUpdateRequestStatusFieldDeserializer.class)
  @JsonSerialize(
      using =
          FileRequestUpdateRequestStatusField.FileRequestUpdateRequestStatusFieldSerializer.class)
  protected EnumWrapper<FileRequestUpdateRequestStatusField> status;

  /**
   * Whether a file request submitter is required to provide their email address.
   *
   * <p>When this setting is set to true, the Box UI will show an email field on the file request
   * form.
   *
   * <p>This will default to the value on the existing file request.
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
   * <p>This will default to the value on the existing file request.
   */
  @JsonProperty("is_description_required")
  protected Boolean isDescriptionRequired;

  /**
   * The date after which a file request will no longer accept new submissions.
   *
   * <p>After this date, the `status` will automatically be set to `inactive`.
   *
   * <p>This will default to the value on the existing file request.
   */
  @JsonProperty("expires_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime expiresAt;

  public FileRequestUpdateRequest() {
    super();
  }

  protected FileRequestUpdateRequest(Builder builder) {
    super();
    this.title = builder.title;
    this.description = builder.description;
    this.status = builder.status;
    this.isEmailRequired = builder.isEmailRequired;
    this.isDescriptionRequired = builder.isDescriptionRequired;
    this.expiresAt = builder.expiresAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public EnumWrapper<FileRequestUpdateRequestStatusField> getStatus() {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileRequestUpdateRequest casted = (FileRequestUpdateRequest) o;
    return Objects.equals(title, casted.title)
        && Objects.equals(description, casted.description)
        && Objects.equals(status, casted.status)
        && Objects.equals(isEmailRequired, casted.isEmailRequired)
        && Objects.equals(isDescriptionRequired, casted.isDescriptionRequired)
        && Objects.equals(expiresAt, casted.expiresAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        title, description, status, isEmailRequired, isDescriptionRequired, expiresAt);
  }

  @Override
  public String toString() {
    return "FileRequestUpdateRequest{"
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
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String title;

    protected String description;

    protected EnumWrapper<FileRequestUpdateRequestStatusField> status;

    protected Boolean isEmailRequired;

    protected Boolean isDescriptionRequired;

    protected OffsetDateTime expiresAt;

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder status(FileRequestUpdateRequestStatusField status) {
      this.status = new EnumWrapper<FileRequestUpdateRequestStatusField>(status);
      return this;
    }

    public Builder status(EnumWrapper<FileRequestUpdateRequestStatusField> status) {
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

    public FileRequestUpdateRequest build() {
      return new FileRequestUpdateRequest(this);
    }
  }
}
