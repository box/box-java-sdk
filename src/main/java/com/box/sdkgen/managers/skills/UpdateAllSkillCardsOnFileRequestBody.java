package com.box.sdkgen.managers.skills;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class UpdateAllSkillCardsOnFileRequestBody extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateAllSkillCardsOnFileRequestBodyStatusField
              .UpdateAllSkillCardsOnFileRequestBodyStatusFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateAllSkillCardsOnFileRequestBodyStatusField
              .UpdateAllSkillCardsOnFileRequestBodyStatusFieldSerializer.class)
  protected final EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyStatusField> status;

  protected final UpdateAllSkillCardsOnFileRequestBodyMetadataField metadata;

  protected final UpdateAllSkillCardsOnFileRequestBodyFileField file;

  @JsonProperty("file_version")
  protected UpdateAllSkillCardsOnFileRequestBodyFileVersionField fileVersion;

  protected UpdateAllSkillCardsOnFileRequestBodyUsageField usage;

  public UpdateAllSkillCardsOnFileRequestBody(
      @JsonProperty("status") EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyStatusField> status,
      @JsonProperty("metadata") UpdateAllSkillCardsOnFileRequestBodyMetadataField metadata,
      @JsonProperty("file") UpdateAllSkillCardsOnFileRequestBodyFileField file) {
    super();
    this.status = status;
    this.metadata = metadata;
    this.file = file;
  }

  public UpdateAllSkillCardsOnFileRequestBody(
      UpdateAllSkillCardsOnFileRequestBodyStatusField status,
      UpdateAllSkillCardsOnFileRequestBodyMetadataField metadata,
      UpdateAllSkillCardsOnFileRequestBodyFileField file) {
    super();
    this.status = new EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyStatusField>(status);
    this.metadata = metadata;
    this.file = file;
  }

  protected UpdateAllSkillCardsOnFileRequestBody(
      UpdateAllSkillCardsOnFileRequestBodyBuilder builder) {
    super();
    this.status = builder.status;
    this.metadata = builder.metadata;
    this.file = builder.file;
    this.fileVersion = builder.fileVersion;
    this.usage = builder.usage;
  }

  public EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyStatusField> getStatus() {
    return status;
  }

  public UpdateAllSkillCardsOnFileRequestBodyMetadataField getMetadata() {
    return metadata;
  }

  public UpdateAllSkillCardsOnFileRequestBodyFileField getFile() {
    return file;
  }

  public UpdateAllSkillCardsOnFileRequestBodyFileVersionField getFileVersion() {
    return fileVersion;
  }

  public UpdateAllSkillCardsOnFileRequestBodyUsageField getUsage() {
    return usage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateAllSkillCardsOnFileRequestBody casted = (UpdateAllSkillCardsOnFileRequestBody) o;
    return Objects.equals(status, casted.status)
        && Objects.equals(metadata, casted.metadata)
        && Objects.equals(file, casted.file)
        && Objects.equals(fileVersion, casted.fileVersion)
        && Objects.equals(usage, casted.usage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, metadata, file, fileVersion, usage);
  }

  @Override
  public String toString() {
    return "UpdateAllSkillCardsOnFileRequestBody{"
        + "status='"
        + status
        + '\''
        + ", "
        + "metadata='"
        + metadata
        + '\''
        + ", "
        + "file='"
        + file
        + '\''
        + ", "
        + "fileVersion='"
        + fileVersion
        + '\''
        + ", "
        + "usage='"
        + usage
        + '\''
        + "}";
  }

  public static class UpdateAllSkillCardsOnFileRequestBodyBuilder {

    protected final EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyStatusField> status;

    protected final UpdateAllSkillCardsOnFileRequestBodyMetadataField metadata;

    protected final UpdateAllSkillCardsOnFileRequestBodyFileField file;

    protected UpdateAllSkillCardsOnFileRequestBodyFileVersionField fileVersion;

    protected UpdateAllSkillCardsOnFileRequestBodyUsageField usage;

    public UpdateAllSkillCardsOnFileRequestBodyBuilder(
        EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyStatusField> status,
        UpdateAllSkillCardsOnFileRequestBodyMetadataField metadata,
        UpdateAllSkillCardsOnFileRequestBodyFileField file) {
      this.status = status;
      this.metadata = metadata;
      this.file = file;
    }

    public UpdateAllSkillCardsOnFileRequestBodyBuilder(
        UpdateAllSkillCardsOnFileRequestBodyStatusField status,
        UpdateAllSkillCardsOnFileRequestBodyMetadataField metadata,
        UpdateAllSkillCardsOnFileRequestBodyFileField file) {
      this.status = new EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyStatusField>(status);
      this.metadata = metadata;
      this.file = file;
    }

    public UpdateAllSkillCardsOnFileRequestBodyBuilder fileVersion(
        UpdateAllSkillCardsOnFileRequestBodyFileVersionField fileVersion) {
      this.fileVersion = fileVersion;
      return this;
    }

    public UpdateAllSkillCardsOnFileRequestBodyBuilder usage(
        UpdateAllSkillCardsOnFileRequestBodyUsageField usage) {
      this.usage = usage;
      return this;
    }

    public UpdateAllSkillCardsOnFileRequestBody build() {
      return new UpdateAllSkillCardsOnFileRequestBody(this);
    }
  }
}
