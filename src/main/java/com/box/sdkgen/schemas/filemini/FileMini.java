package com.box.sdkgen.schemas.filemini;

import com.box.sdkgen.schemas.filebase.FileBase;
import com.box.sdkgen.schemas.filebase.FileBaseTypeField;
import com.box.sdkgen.schemas.fileversionmini.FileVersionMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class FileMini extends FileBase {

  @JsonProperty("sequence_id")
  protected String sequenceId;

  protected String name;

  protected String sha1;

  @JsonProperty("file_version")
  protected FileVersionMini fileVersion;

  public FileMini(@JsonProperty("id") String id) {
    super(id);
  }

  protected FileMini(FileMiniBuilder builder) {
    super(builder);
    this.sequenceId = builder.sequenceId;
    this.name = builder.name;
    this.sha1 = builder.sha1;
    this.fileVersion = builder.fileVersion;
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

  public static class FileMiniBuilder extends FileBaseBuilder {

    protected String sequenceId;

    protected String name;

    protected String sha1;

    protected FileVersionMini fileVersion;

    public FileMiniBuilder(String id) {
      super(id);
    }

    public FileMiniBuilder sequenceId(String sequenceId) {
      this.sequenceId = sequenceId;
      return this;
    }

    public FileMiniBuilder name(String name) {
      this.name = name;
      return this;
    }

    public FileMiniBuilder sha1(String sha1) {
      this.sha1 = sha1;
      return this;
    }

    public FileMiniBuilder fileVersion(FileVersionMini fileVersion) {
      this.fileVersion = fileVersion;
      return this;
    }

    @Override
    public FileMiniBuilder etag(String etag) {
      this.etag = etag;
      return this;
    }

    @Override
    public FileMiniBuilder type(FileBaseTypeField type) {
      this.type = new EnumWrapper<FileBaseTypeField>(type);
      return this;
    }

    @Override
    public FileMiniBuilder type(EnumWrapper<FileBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public FileMini build() {
      return new FileMini(this);
    }
  }
}
