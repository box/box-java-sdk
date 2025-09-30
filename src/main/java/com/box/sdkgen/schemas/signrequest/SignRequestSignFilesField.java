package com.box.sdkgen.schemas.signrequest;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class SignRequestSignFilesField extends SerializableObject {

  protected List<FileMini> files;

  @JsonProperty("is_ready_for_download")
  protected Boolean isReadyForDownload;

  public SignRequestSignFilesField() {
    super();
  }

  protected SignRequestSignFilesField(Builder builder) {
    super();
    this.files = builder.files;
    this.isReadyForDownload = builder.isReadyForDownload;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<FileMini> getFiles() {
    return files;
  }

  public Boolean getIsReadyForDownload() {
    return isReadyForDownload;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignRequestSignFilesField casted = (SignRequestSignFilesField) o;
    return Objects.equals(files, casted.files)
        && Objects.equals(isReadyForDownload, casted.isReadyForDownload);
  }

  @Override
  public int hashCode() {
    return Objects.hash(files, isReadyForDownload);
  }

  @Override
  public String toString() {
    return "SignRequestSignFilesField{"
        + "files='"
        + files
        + '\''
        + ", "
        + "isReadyForDownload='"
        + isReadyForDownload
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<FileMini> files;

    protected Boolean isReadyForDownload;

    public Builder files(List<FileMini> files) {
      this.files = files;
      return this;
    }

    public Builder isReadyForDownload(Boolean isReadyForDownload) {
      this.isReadyForDownload = isReadyForDownload;
      return this;
    }

    public SignRequestSignFilesField build() {
      return new SignRequestSignFilesField(this);
    }
  }
}
