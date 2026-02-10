package com.box.sdkgen.schemas.filemini;

import com.box.sdkgen.schemas.filebase.FileBase;
import com.box.sdkgen.schemas.filebase.FileBaseTypeField;
import com.box.sdkgen.schemas.fileversionmini.FileVersionMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** A mini representation of a file, used when nested under another resource. */
@JsonFilter("nullablePropertyFilter")
public class FileMini extends FileBase {

  @JsonProperty("sequence_id")
  protected String sequenceId;

  /** The name of the file. */
  protected String name;

  /**
   * The SHA1 hash of the file. This can be used to compare the contents of a file on Box with a
   * local file.
   */
  protected String sha1;

  @JsonProperty("file_version")
  protected FileVersionMini fileVersion;

  public FileMini(@JsonProperty("id") String id) {
    super(id);
  }

  protected FileMini(Builder builder) {
    super(builder);
    this.sequenceId = builder.sequenceId;
    this.name = builder.name;
    this.sha1 = builder.sha1;
    this.fileVersion = builder.fileVersion;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getSequenceId() {
    return sequenceId;
  }

  public String getName() {
    return name;
  }

  public String getSha1() {
    return sha1;
  }

  public FileVersionMini getFileVersion() {
    return fileVersion;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileMini casted = (FileMini) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(etag, casted.etag)
        && Objects.equals(type, casted.type)
        && Objects.equals(sequenceId, casted.sequenceId)
        && Objects.equals(name, casted.name)
        && Objects.equals(sha1, casted.sha1)
        && Objects.equals(fileVersion, casted.fileVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, etag, type, sequenceId, name, sha1, fileVersion);
  }

  @Override
  public String toString() {
    return "FileMini{"
        + "id='"
        + id
        + '\''
        + ", "
        + "etag='"
        + etag
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "sequenceId='"
        + sequenceId
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "sha1='"
        + sha1
        + '\''
        + ", "
        + "fileVersion='"
        + fileVersion
        + '\''
        + "}";
  }

  public static class Builder extends FileBase.Builder {

    protected String sequenceId;

    protected String name;

    protected String sha1;

    protected FileVersionMini fileVersion;

    public Builder(String id) {
      super(id);
    }

    public Builder sequenceId(String sequenceId) {
      this.sequenceId = sequenceId;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder sha1(String sha1) {
      this.sha1 = sha1;
      return this;
    }

    public Builder fileVersion(FileVersionMini fileVersion) {
      this.fileVersion = fileVersion;
      return this;
    }

    @Override
    public Builder etag(String etag) {
      this.etag = etag;
      this.markNullableFieldAsSet("etag");
      return this;
    }

    @Override
    public Builder type(FileBaseTypeField type) {
      this.type = new EnumWrapper<FileBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<FileBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public FileMini build() {
      if (this.type == null) {
        this.type = new EnumWrapper<FileBaseTypeField>(FileBaseTypeField.FILE);
      }
      return new FileMini(this);
    }
  }
}
