package com.box.sdkgen.schemas.fileconflict;

import com.box.sdkgen.schemas.filebase.FileBaseTypeField;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.box.sdkgen.schemas.fileversionmini.FileVersionMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class FileConflict extends FileMini {

  public FileConflict(@JsonProperty("id") String id) {
    super(id);
  }

  protected FileConflict(FileConflictBuilder builder) {
    super(builder);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileConflict casted = (FileConflict) o;
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
    return "FileConflict{"
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

  public static class FileConflictBuilder extends FileMiniBuilder {

    public FileConflictBuilder(String id) {
      super(id);
    }

    @Override
    public FileConflictBuilder etag(String etag) {
      this.etag = etag;
      return this;
    }

    @Override
    public FileConflictBuilder type(FileBaseTypeField type) {
      this.type = new EnumWrapper<FileBaseTypeField>(type);
      return this;
    }

    @Override
    public FileConflictBuilder type(EnumWrapper<FileBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public FileConflictBuilder sequenceId(String sequenceId) {
      this.sequenceId = sequenceId;
      return this;
    }

    @Override
    public FileConflictBuilder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public FileConflictBuilder sha1(String sha1) {
      this.sha1 = sha1;
      return this;
    }

    @Override
    public FileConflictBuilder fileVersion(FileVersionMini fileVersion) {
      this.fileVersion = fileVersion;
      return this;
    }

    public FileConflict build() {
      return new FileConflict(this);
    }
  }
}
