package com.box.sdkgen.schemas.metadatatemplates;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.metadatatemplate.MetadataTemplate;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class MetadataTemplates extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  @JsonProperty("prev_marker")
  protected String prevMarker;

  protected List<MetadataTemplate> entries;

  public MetadataTemplates() {
    super();
  }

  protected MetadataTemplates(MetadataTemplatesBuilder builder) {
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

  public List<MetadataTemplate> getEntries() {
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
    MetadataTemplates casted = (MetadataTemplates) o;
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
    return "MetadataTemplates{"
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

  public static class MetadataTemplatesBuilder {

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    protected List<MetadataTemplate> entries;

    public MetadataTemplatesBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public MetadataTemplatesBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public MetadataTemplatesBuilder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      return this;
    }

    public MetadataTemplatesBuilder entries(List<MetadataTemplate> entries) {
      this.entries = entries;
      return this;
    }

    public MetadataTemplates build() {
      return new MetadataTemplates(this);
    }
  }
}
