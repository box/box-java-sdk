package com.box.sdkgen.schemas.realtimeservers;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.realtimeserver.RealtimeServer;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** A list of real-time servers that can be used for long-polling. */
@JsonFilter("nullablePropertyFilter")
public class RealtimeServers extends SerializableObject {

  /** The number of items in this response. */
  @JsonProperty("chunk_size")
  protected Long chunkSize;

  /** A list of real-time servers. */
  protected List<RealtimeServer> entries;

  public RealtimeServers() {
    super();
  }

  protected RealtimeServers(Builder builder) {
    super();
    this.chunkSize = builder.chunkSize;
    this.entries = builder.entries;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Long getChunkSize() {
    return chunkSize;
  }

  public List<RealtimeServer> getEntries() {
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
    RealtimeServers casted = (RealtimeServers) o;
    return Objects.equals(chunkSize, casted.chunkSize) && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(chunkSize, entries);
  }

  @Override
  public String toString() {
    return "RealtimeServers{"
        + "chunkSize='"
        + chunkSize
        + '\''
        + ", "
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Long chunkSize;

    protected List<RealtimeServer> entries;

    public Builder chunkSize(Long chunkSize) {
      this.chunkSize = chunkSize;
      return this;
    }

    public Builder entries(List<RealtimeServer> entries) {
      this.entries = entries;
      return this;
    }

    public RealtimeServers build() {
      return new RealtimeServers(this);
    }
  }
}
