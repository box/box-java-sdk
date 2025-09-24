package com.box.sdkgen.schemas.v2025r0.hubcollaborationsv2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.hubcollaborationv2025r0.HubCollaborationV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class HubCollaborationsV2025R0 extends SerializableObject {

  protected List<HubCollaborationV2025R0> entries;

  protected Long limit;

  @JsonProperty("next_marker")
  @Nullable
  protected String nextMarker;

  public HubCollaborationsV2025R0() {
    super();
  }

  protected HubCollaborationsV2025R0(Builder builder) {
    super();
    this.entries = builder.entries;
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<HubCollaborationV2025R0> getEntries() {
    return entries;
  }

  public Long getLimit() {
    return limit;
  }

  public String getNextMarker() {
    return nextMarker;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HubCollaborationsV2025R0 casted = (HubCollaborationsV2025R0) o;
    return Objects.equals(entries, casted.entries)
        && Objects.equals(limit, casted.limit)
        && Objects.equals(nextMarker, casted.nextMarker);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entries, limit, nextMarker);
  }

  @Override
  public String toString() {
    return "HubCollaborationsV2025R0{"
        + "entries='"
        + entries
        + '\''
        + ", "
        + "limit='"
        + limit
        + '\''
        + ", "
        + "nextMarker='"
        + nextMarker
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<HubCollaborationV2025R0> entries;

    protected Long limit;

    protected String nextMarker;

    public Builder entries(List<HubCollaborationV2025R0> entries) {
      this.entries = entries;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      this.markNullableFieldAsSet("next_marker");
      return this;
    }

    public HubCollaborationsV2025R0 build() {
      return new HubCollaborationsV2025R0(this);
    }
  }
}
