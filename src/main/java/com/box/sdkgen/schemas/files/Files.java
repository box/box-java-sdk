package com.box.sdkgen.schemas.files;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class Files extends SerializableObject {

  @JsonProperty("total_count")
  protected Long totalCount;

  protected List<FileFull> entries;

  public Files() {
    super();
  }

  protected Files(FilesBuilder builder) {
    super();
    this.totalCount = builder.totalCount;
    this.entries = builder.entries;
  }

  public Long getTotalCount() {
    return totalCount;
  }

  public List<FileFull> getEntries() {
    return entries;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Files casted = (Files) o;
    return Objects.equals(totalCount, casted.totalCount) && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, entries);
  }

  @Override
  public String toString() {
    return "Files{"
        + "totalCount='"
        + totalCount
        + '\''
        + ", "
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class FilesBuilder {

    protected Long totalCount;

    protected List<FileFull> entries;

    public FilesBuilder totalCount(Long totalCount) {
      this.totalCount = totalCount;
      return this;
    }

    public FilesBuilder entries(List<FileFull> entries) {
      this.entries = entries;
      return this;
    }

    public Files build() {
      return new Files(this);
    }
  }
}
