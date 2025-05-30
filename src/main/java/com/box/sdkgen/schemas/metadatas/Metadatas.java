package com.box.sdkgen.schemas.metadatas;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.metadata.Metadata;
import java.util.List;
import java.util.Objects;

public class Metadatas extends SerializableObject {

  protected List<Metadata> entries;

  protected Long limit;

  public Metadatas() {
    super();
  }

  protected Metadatas(MetadatasBuilder builder) {
    super();
    this.entries = builder.entries;
    this.limit = builder.limit;
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

  public static class MetadatasBuilder {

    protected List<Metadata> entries;

    protected Long limit;

    public MetadatasBuilder entries(List<Metadata> entries) {
      this.entries = entries;
      return this;
    }

    public MetadatasBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Metadatas build() {
      return new Metadatas(this);
    }
  }
}
