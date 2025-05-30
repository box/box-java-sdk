package com.box.sdkgen.schemas.events;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.event.Event;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class Events extends SerializableObject {

  @JsonProperty("chunk_size")
  protected Long chunkSize;

  @JsonProperty("next_stream_position")
  protected EventsNextStreamPositionField nextStreamPosition;

  protected List<Event> entries;

  public Events() {
    super();
  }

  protected Events(EventsBuilder builder) {
    super();
    this.chunkSize = builder.chunkSize;
    this.nextStreamPosition = builder.nextStreamPosition;
    this.entries = builder.entries;
  }

  public Long getChunkSize() {
    return chunkSize;
  }

  public EventsNextStreamPositionField getNextStreamPosition() {
    return nextStreamPosition;
  }

  public List<Event> getEntries() {
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
    Events casted = (Events) o;
    return Objects.equals(chunkSize, casted.chunkSize)
        && Objects.equals(nextStreamPosition, casted.nextStreamPosition)
        && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(chunkSize, nextStreamPosition, entries);
  }

  @Override
  public String toString() {
    return "Events{"
        + "chunkSize='"
        + chunkSize
        + '\''
        + ", "
        + "nextStreamPosition='"
        + nextStreamPosition
        + '\''
        + ", "
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class EventsBuilder {

    protected Long chunkSize;

    protected EventsNextStreamPositionField nextStreamPosition;

    protected List<Event> entries;

    public EventsBuilder chunkSize(Long chunkSize) {
      this.chunkSize = chunkSize;
      return this;
    }

    public EventsBuilder nextStreamPosition(EventsNextStreamPositionField nextStreamPosition) {
      this.nextStreamPosition = nextStreamPosition;
      return this;
    }

    public EventsBuilder entries(List<Event> entries) {
      this.entries = entries;
      return this;
    }

    public Events build() {
      return new Events(this);
    }
  }
}
