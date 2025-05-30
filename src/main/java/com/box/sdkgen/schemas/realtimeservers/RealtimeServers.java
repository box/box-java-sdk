package com.box.sdkgen.schemas.realtimeservers;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.realtimeserver.RealtimeServer;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class RealtimeServers extends SerializableObject {

  @JsonProperty("chunk_size")
  protected Long chunkSize;

  protected List<RealtimeServer> entries;

  public RealtimeServers() {
    super();
  }

  protected RealtimeServers(RealtimeServersBuilder builder) {
    super();
    this.chunkSize = builder.chunkSize;
    this.entries = builder.entries;
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

  public static class RealtimeServersBuilder {

    protected Long chunkSize;

    protected List<RealtimeServer> entries;

    public RealtimeServersBuilder chunkSize(Long chunkSize) {
      this.chunkSize = chunkSize;
      return this;
    }

    public RealtimeServersBuilder entries(List<RealtimeServer> entries) {
      this.entries = entries;
      return this;
    }

    public RealtimeServers build() {
      return new RealtimeServers(this);
    }
  }
}
