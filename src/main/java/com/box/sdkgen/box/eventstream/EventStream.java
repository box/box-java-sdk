package com.box.sdkgen.box.eventstream;

import com.box.sdkgen.managers.events.EventsManager;
import com.box.sdkgen.managers.events.GetEventStreamHeaders;
import com.box.sdkgen.managers.events.GetEventStreamQueryParams;
import com.box.sdkgen.schemas.event.Event;
import java.util.Iterator;

public class EventStream implements Iterable<Event> {

  EventStreamIterator iterator;

  private EventStream(Builder builder) {
    this.iterator =
        new EventStreamIterator(builder.eventsManager, builder.queryParams, builder.headersInput);
  }

  @Override
  public Iterator<Event> iterator() {
    return this.iterator;
  }

  public void stop() {
    this.iterator.stop();
  }

  public static class Builder {
    final EventsManager eventsManager;
    final GetEventStreamQueryParams queryParams;
    GetEventStreamHeaders headersInput;

    public Builder(EventsManager eventsManager, GetEventStreamQueryParams queryParams) {
      this.eventsManager = eventsManager;
      this.queryParams = queryParams;
      this.headersInput = new GetEventStreamHeaders();
    }

    public Builder headersInput(GetEventStreamHeaders headersInput) {
      this.headersInput = headersInput != null ? headersInput : new GetEventStreamHeaders();
      return this;
    }

    public EventStream build() {
      return new EventStream(this);
    }
  }
}
