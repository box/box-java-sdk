package com.box.sdkgen.schemas.fileversionmini;

import com.box.sdkgen.schemas.fileversionbase.FileVersionBase;
import com.box.sdkgen.schemas.fileversionbase.FileVersionBaseTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class FileVersionMini extends FileVersionBase {

  protected String sha1;

  public FileVersionMini(@JsonProperty("id") String id) {
    super(id);
  }

  protected FileVersionMini(FileVersionMiniBuilder builder) {
    super(builder);
    this.sha1 = builder.sha1;
  }

  public String getSha1() {
    return sha1;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileVersionMini casted = (FileVersionMini) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(sha1, casted.sha1);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, sha1);
  }

  @Override
  public String toString() {
    return "FileVersionMini{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "sha1='"
        + sha1
        + '\''
        + "}";
  }

  public static class FileVersionMiniBuilder extends FileVersionBaseBuilder {

    protected String sha1;

    public FileVersionMiniBuilder(String id) {
      super(id);
    }

    public FileVersionMiniBuilder sha1(String sha1) {
      this.sha1 = sha1;
      return this;
    }

    @Override
    public FileVersionMiniBuilder type(FileVersionBaseTypeField type) {
      this.type = new EnumWrapper<FileVersionBaseTypeField>(type);
      return this;
    }

    @Override
    public FileVersionMiniBuilder type(EnumWrapper<FileVersionBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public FileVersionMini build() {
      return new FileVersionMini(this);
    }
  }
}
