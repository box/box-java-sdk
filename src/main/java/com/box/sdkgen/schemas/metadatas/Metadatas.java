package com.box.sdkgen.schemas.metadatas;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.metadata.Metadata;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import java.util.Objects;

/** A list of metadata instances that have been applied to a file or folder. */
@JsonFilter("nullablePropertyFilter")
public class Metadatas extends SerializableObject {

  /** A list of metadata instances, as applied to this file or folder. */
  protected List<Metadata> entries;

  /** The limit that was used for this page of results. */
  protected Long limit;

  public Metadatas() {
    super();
  }

  protected Metadatas(Builder builder) {
    super();
    this.entries = builder.entries;
    this.limit = builder.limit;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<Metadata> getEntries() {
    return entries;
  }

  public Long getLimit() {
    return limit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Metadatas casted = (Metadatas) o;
    return Objects.equals(entries, casted.entries) && Objects.equals(limit, casted.limit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entries, limit);
  }

  @Override
  public String toString() {
    return "Metadatas{" + "entries='" + entries + '\'' + ", " + "limit='" + limit + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<Metadata> entries;

    protected Long limit;

    public Builder entries(List<Metadata> entries) {
      this.entries = entries;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Metadatas build() {
      return new Metadatas(this);
    }
  }
}
