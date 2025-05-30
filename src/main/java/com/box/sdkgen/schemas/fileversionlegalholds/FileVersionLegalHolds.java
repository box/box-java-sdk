package com.box.sdkgen.schemas.fileversionlegalholds;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.fileversionlegalhold.FileVersionLegalHold;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class FileVersionLegalHolds extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  @JsonProperty("prev_marker")
  protected String prevMarker;

  protected List<FileVersionLegalHold> entries;

  public FileVersionLegalHolds() {
    super();
  }

  protected FileVersionLegalHolds(FileVersionLegalHoldsBuilder builder) {
    super();
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
    this.prevMarker = builder.prevMarker;
    this.entries = builder.entries;
  }

  public Long getLimit() {
    return limit;
  }

  public String getNextMarker() {
    return nextMarker;
  }

  public String getPrevMarker() {
    return prevMarker;
  }

  public List<FileVersionLegalHold> getEntries() {
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
    FileVersionLegalHolds casted = (FileVersionLegalHolds) o;
    return Objects.equals(limit, casted.limit)
        && Objects.equals(nextMarker, casted.nextMarker)
        && Objects.equals(prevMarker, casted.prevMarker)
        && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(limit, nextMarker, prevMarker, entries);
  }

  @Override
  public String toString() {
    return "FileVersionLegalHolds{"
        + "limit='"
        + limit
        + '\''
        + ", "
        + "nextMarker='"
        + nextMarker
        + '\''
        + ", "
        + "prevMarker='"
        + prevMarker
        + '\''
        + ", "
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class FileVersionLegalHoldsBuilder {

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    protected List<FileVersionLegalHold> entries;

    public FileVersionLegalHoldsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public FileVersionLegalHoldsBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public FileVersionLegalHoldsBuilder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      return this;
    }

    public FileVersionLegalHoldsBuilder entries(List<FileVersionLegalHold> entries) {
      this.entries = entries;
      return this;
    }

    public FileVersionLegalHolds build() {
      return new FileVersionLegalHolds(this);
    }
  }
}
