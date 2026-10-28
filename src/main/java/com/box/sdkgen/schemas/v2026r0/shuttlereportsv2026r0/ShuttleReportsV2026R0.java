package com.box.sdkgen.schemas.v2026r0.shuttlereportsv2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.shuttlereportv2026r0.ShuttleReportV2026R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** A paginated list of shuttle reports. */
@JsonFilter("nullablePropertyFilter")
public class ShuttleReportsV2026R0 extends SerializableObject {

  /** A list of shuttle reports. */
  protected final List<ShuttleReportV2026R0> entries;

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

  public ShuttleReportsV2026R0(@JsonProperty("entries") List<ShuttleReportV2026R0> entries) {
    super();
    this.entries = entries;
  }

  protected ShuttleReportsV2026R0(Builder builder) {
    super();
    this.entries = builder.entries;
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
    this.prevMarker = builder.prevMarker;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<ShuttleReportV2026R0> getEntries() {
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
    ShuttleReportsV2026R0 casted = (ShuttleReportsV2026R0) o;
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
    return "ShuttleReportsV2026R0{"
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

    protected final List<ShuttleReportV2026R0> entries;

    protected Long limit;

    protected String nextMarker;

    protected String prevMarker;

    public Builder(List<ShuttleReportV2026R0> entries) {
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

    public ShuttleReportsV2026R0 build() {
      return new ShuttleReportsV2026R0(this);
    }
  }
}
