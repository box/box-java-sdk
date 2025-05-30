package com.box.sdkgen.schemas.integrationmappings;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.integrationmapping.IntegrationMapping;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class IntegrationMappings extends SerializableObject {

  protected Long limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  protected List<IntegrationMapping> entries;

  public IntegrationMappings() {
    super();
  }

  protected IntegrationMappings(IntegrationMappingsBuilder builder) {
    super();
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
    this.entries = builder.entries;
  }

  public Long getLimit() {
    return limit;
  }

  public String getNextMarker() {
    return nextMarker;
  }

  public List<IntegrationMapping> getEntries() {
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
    IntegrationMappings casted = (IntegrationMappings) o;
    return Objects.equals(limit, casted.limit)
        && Objects.equals(nextMarker, casted.nextMarker)
        && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(limit, nextMarker, entries);
  }

  @Override
  public String toString() {
    return "IntegrationMappings{"
        + "limit='"
        + limit
        + '\''
        + ", "
        + "nextMarker='"
        + nextMarker
        + '\''
        + ", "
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class IntegrationMappingsBuilder {

    protected Long limit;

    protected String nextMarker;

    protected List<IntegrationMapping> entries;

    public IntegrationMappingsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public IntegrationMappingsBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public IntegrationMappingsBuilder entries(List<IntegrationMapping> entries) {
      this.entries = entries;
      return this;
    }

    public IntegrationMappings build() {
      return new IntegrationMappings(this);
    }
  }
}
