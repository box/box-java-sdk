package com.box.sdkgen.schemas.integrationmappingsteams;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.integrationmappingteams.IntegrationMappingTeams;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import java.util.Objects;

/** A list of integration mapping objects. */
@JsonFilter("nullablePropertyFilter")
public class IntegrationMappingsTeams extends SerializableObject {

  /** A list of integration mappings. */
  protected List<IntegrationMappingTeams> entries;

  public IntegrationMappingsTeams() {
    super();
  }

  protected IntegrationMappingsTeams(Builder builder) {
    super();
    this.entries = builder.entries;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<IntegrationMappingTeams> getEntries() {
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
    IntegrationMappingsTeams casted = (IntegrationMappingsTeams) o;
    return Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entries);
  }

  @Override
  public String toString() {
    return "IntegrationMappingsTeams{" + "entries='" + entries + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<IntegrationMappingTeams> entries;

    public Builder entries(List<IntegrationMappingTeams> entries) {
      this.entries = entries;
      return this;
    }

    public IntegrationMappingsTeams build() {
      return new IntegrationMappingsTeams(this);
    }
  }
}
