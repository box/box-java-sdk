package com.box.sdkgen.schemas.v2026r0.shuttlesubjectmappingsv2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.shuttlesubjectmappingv2026r0.ShuttleSubjectMappingV2026R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** A paginated list of shuttle subject mappings. */
@JsonFilter("nullablePropertyFilter")
public class ShuttleSubjectMappingsV2026R0 extends SerializableObject {

  /** A list of shuttle subject mappings. */
  protected final List<ShuttleSubjectMappingV2026R0> entries;

  /**
   * The limit that was used for these entries. This will be the same as the `limit` query parameter
   * unless that value exceeded the maximum value allowed. The maximum value varies by API.
   */
  protected Long limit;

  /** The marker for the start of the next page of results. */
  @JsonProperty("next_marker")
  @Nullable
  protected String nextMarker;

  /** The marker for the start of the previous page of results. */
  @JsonProperty("prev_marker")
  @Nullable
  protected String prevMarker;

  public ShuttleSubjectMappingsV2026R0(
      @JsonProperty("entries") List<ShuttleSubjectMappingV2026R0> entries) {
    super();
    this.entries = entries;
  }

  protected ShuttleSubjectMappingsV2026R0(Builder builder) {
    super();
    this.entries = builder.entries;
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
    this.prevMarker = builder.prevMarker;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<ShuttleSubjectMappingV2026R0> getEntries() {
    return entries;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleSubjectMappingsV2026R0 casted = (ShuttleSubjectMappingsV2026R0) o;
    return Objects.equals(entries, casted.entries)
        && Objects.equals(limit, casted.limit)
        && Objects.equals(nextMarker, casted.nextMarker)
        && Objects.equals(prevMarker, casted.prevMarker);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entries, limit, nextMarker, prevMarker);
  }

  @Override
  public String toString() {
    return "ShuttleSubjectMappingsV2026R0{"
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
        + ", "
        + "prevMarker='"
        + prevMarker
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final List<ShuttleSubjectMappingV2026R0> entries;

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    public Builder(List<ShuttleSubjectMappingV2026R0> entries) {
      super();
      this.entries = entries;
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

    public Builder prevMarker(String prevMarker) {
      this.prevMarker = prevMarker;
      this.markNullableFieldAsSet("prev_marker");
      return this;
    }

    public ShuttleSubjectMappingsV2026R0 build() {
      return new ShuttleSubjectMappingsV2026R0(this);
    }
  }
}
