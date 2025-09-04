package com.box.sdkgen.box.eventstream;

import com.box.sdkgen.box.errors.BoxSDKError;
import com.box.sdkgen.managers.events.EventsManager;
import com.box.sdkgen.managers.events.GetEventStreamHeaders;
import com.box.sdkgen.managers.events.GetEventStreamQueryParams;
import com.box.sdkgen.managers.events.GetEventsHeaders;
import com.box.sdkgen.managers.events.GetEventsQueryParams;
import com.box.sdkgen.managers.events.GetEventsQueryParamsEventTypeField;
import com.box.sdkgen.managers.events.GetEventsQueryParamsStreamTypeField;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.schemas.event.Event;
import com.box.sdkgen.schemas.events.Events;
import com.box.sdkgen.schemas.realtimeserver.RealtimeServer;
import com.box.sdkgen.schemas.realtimeservers.RealtimeServers;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

enum RealtimeServerEvent {
  NEW_CHANGE("new_change"),
  RECONNECT("reconnect");

  private final String value;

  RealtimeServerEvent(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}

enum EventStreamAction {
  FETCH_EVENTS,
  RECONNECT,
  RETRY,
  STOP
}

class EventStreamIterator implements Iterator<Event> {
  private static final int DEDUPLICATION_SIZE = 1000;
  private final EventsManager eventsManager;
  private final GetEventStreamQueryParams queryParams;
  private final GetEventStreamHeaders headersInput;
  private String streamPosition;
  private RealtimeServer longPollInfo;
  private volatile boolean stopped;
  private final CountDownLatch stopLatch;
  private final Map<String, Boolean> dedupHash;
  private final List<Event> eventQueue;
  private boolean started;
  private int longPollingRetries;
  private static final int DEFAULT_MAX_RETRIES = 10;

  EventStreamIterator(
      EventsManager eventsManager,
      GetEventStreamQueryParams queryParams,
      GetEventStreamHeaders headersInput) {
    this.eventsManager = eventsManager;
    this.queryParams = queryParams;
    this.headersInput = headersInput;
    this.streamPosition =
        queryParams.getStreamPosition() != null ? queryParams.getStreamPosition() : "now";
    this.longPollInfo = null;
    this.started = false;
    this.stopped = false;
    this.stopLatch = new CountDownLatch(1);
    this.dedupHash = new HashMap<>();
    this.eventQueue = new ArrayList<>();
    this.longPollingRetries = 0;
  }

  @Override
  public boolean hasNext() {
    return !eventQueue.isEmpty() || (!stopped && stopLatch.getCount() > 0);
  }

  @Override
  public Event next() {
    if (!hasNext()) {
      stop();
      throw new NoSuchElementException("No more events available or stream stopped");
    }
    if (!eventQueue.isEmpty()) {
      return eventQueue.remove(0);
    }
    if (!started) {
      started = true;
      fetchEvents();
      if (!eventQueue.isEmpty()) {
        return eventQueue.remove(0);
      }
    }

    getLongPollInfo();
    while (!stopped && stopLatch.getCount() > 0) {
      try {
        EventStreamAction action = doLongPoll();
        switch (action) {
          case FETCH_EVENTS:
            fetchEvents();
            if (!eventQueue.isEmpty()) {
              return eventQueue.remove(0);
            }
            break;
          case RETRY:
            try {
              Thread.sleep(5000);
            } catch (InterruptedException ie) {
              Thread.currentThread().interrupt();
            }
            continue;
          case RECONNECT:
            getLongPollInfo();
            continue;
          case STOP:
            stop();
            break;
        }
      } catch (Exception e) {
        boolean isBoxError = e instanceof BoxSDKError;
        if (!isBoxError) {
          throw e;
        }
      }
    }
    throw new NoSuchElementException("Stream stopped");
  }

  public void stop() {
    if (!stopped) {
      this.stopped = true;
      this.stopLatch.countDown();
    }
  }

  private void getLongPollInfo() throws BoxSDKError {
    if (stopped || stopLatch.getCount() == 0) {
      return;
    }

    try {
      RealtimeServers info = eventsManager.getEventsWithLongPolling();
      RealtimeServer server = null;
      if (info.getEntries() != null) {
        for (RealtimeServer entry : info.getEntries()) {
          if (entry.getType().equals("realtime_server")) {
            server = entry;
            break;
          }
        }
      }

      if (server == null) {
        throw new BoxSDKError("No realtime server found in the response.");
      }

      this.longPollInfo = server;
      this.longPollingRetries = 0;

    } catch (Exception e) {
      if (!stopped && stopLatch.getCount() > 0) {
        throw new BoxSDKError(
            "Failed to fetch long polling info: " + (e.getMessage() != null ? e.getMessage() : ""),
            e);
      }
    }
  }

  private EventStreamAction doLongPoll() throws BoxSDKError {
    if (stopped || stopLatch.getCount() == 0) {
      return EventStreamAction.STOP;
    }

    try {
      int maxRetries =
          (longPollInfo != null && longPollInfo.getMaxRetries() != null)
              ? Integer.parseInt(longPollInfo.getMaxRetries())
              : DEFAULT_MAX_RETRIES;

      if (longPollInfo == null || longPollingRetries > maxRetries) {
        getLongPollInfo();
      }

      longPollingRetries++;

      String longPollUrl = longPollInfo.getUrl();
      String separator = longPollUrl.contains("?") ? "&" : "?";
      String longPollWithStreamPosition =
          longPollUrl + separator + "stream_position=" + streamPosition;
      Map<String, String> headers = new HashMap<>();
      headers.put("Content-Type", "application/json");

      FetchResponse response =
          eventsManager
              .getNetworkSession()
              .getNetworkClient()
              .fetch(
                  new FetchOptions.Builder(longPollWithStreamPosition, "GET")
                      .responseFormat(ResponseFormat.JSON)
                      .auth(eventsManager.getAuth())
                      .networkSession(eventsManager.getNetworkSession())
                      .headers(headers)
                      .build());

      if (stopped || stopLatch.getCount() == 0) {
        return EventStreamAction.STOP;
      }

      if (response.getStatus() == 200 && response.getData() != null) {
        JsonNode message = response.getData();
        String messageText = message.has("message") ? message.get("message").asText() : null;

        if (RealtimeServerEvent.NEW_CHANGE.getValue().equals(messageText)) {
          return EventStreamAction.FETCH_EVENTS;
        } else if (RealtimeServerEvent.RECONNECT.getValue().equals(messageText)) {
          return EventStreamAction.RECONNECT;
        }
      }
      return EventStreamAction.RETRY;
    } catch (Exception e) {
      if (!stopped && stopLatch.getCount() > 0) {
        return EventStreamAction.RETRY;
      }
      return EventStreamAction.STOP;
    }
  }

  private void fetchEvents() throws BoxSDKError {
    if (stopped || stopLatch.getCount() == 0) {
      return;
    }

    try {
      GetEventsQueryParams.Builder fetchParamsBuilder =
          new GetEventsQueryParams.Builder().streamPosition(streamPosition);

      if (queryParams.getLimit() != null) {
        fetchParamsBuilder.limit(queryParams.getLimit());
      }
      if (queryParams.getCreatedAfter() != null) {
        fetchParamsBuilder.createdAfter(queryParams.getCreatedAfter());
      }
      if (queryParams.getCreatedBefore() != null) {
        fetchParamsBuilder.createdBefore(queryParams.getCreatedBefore());
      }

      if (queryParams.getStreamType() != null) {
        String streamTypeValue = queryParams.getStreamType().getValue();
        GetEventsQueryParamsStreamTypeField streamType = convertStreamType(streamTypeValue);
        fetchParamsBuilder.streamType(streamType);
      }

      List<? extends Valuable> eventTypes =
          queryParams.getEventType() != null
              ? queryParams.getEventType().stream()
                  .map(enumWrapper -> convertEventType(enumWrapper.getValue()))
                  .collect(Collectors.toList())
              : null;
      if (eventTypes != null) {
        fetchParamsBuilder.eventType(eventTypes);
      }

      GetEventsQueryParams fetchParams = fetchParamsBuilder.build();
      GetEventsHeaders fetchHeaders =
          new GetEventsHeaders.Builder()
              .extraHeaders(
                  headersInput.getExtraHeaders() != null
                      ? headersInput.getExtraHeaders()
                      : new HashMap<>())
              .build();

      Events events = eventsManager.getEvents(fetchParams, fetchHeaders);
      if (events.getEntries() != null && !events.getEntries().isEmpty()) {
        for (Event event : events.getEntries()) {
          String eventId = event.getEventId();
          if (eventId != null && !dedupHash.containsKey(eventId)) {
            dedupHash.put(eventId, true);
            if (stopped || stopLatch.getCount() == 0) {
              return;
            }
            eventQueue.add(event);
          }
        }
      }

      streamPosition =
          events.getNextStreamPosition() != null
              ? (events.getNextStreamPosition().isString()
                  ? events.getNextStreamPosition().getString()
                  : String.valueOf(events.getNextStreamPosition().getLongNumber()))
              : "now";

      if (dedupHash.size() >= DEDUPLICATION_SIZE) {
        dedupHash.clear();
        for (Event event : events.getEntries()) {
          String eventId = event.getEventId();
          if (eventId != null) {
            dedupHash.put(eventId, true);
          }
        }
      }
    } catch (Exception e) {
      if (!stopped && stopLatch.getCount() > 0) {
        if (e instanceof BoxSDKError) {
          throw (BoxSDKError) e;
        } else {
          throw new BoxSDKError(
              "Failed to fetch events: " + (e.getMessage() != null ? e.getMessage() : ""), e);
        }
      }
    }
  }

  private GetEventsQueryParamsStreamTypeField convertStreamType(String streamTypeValue) {
    if (streamTypeValue == null) return null;
    try {
      return GetEventsQueryParamsStreamTypeField.valueOf(
          streamTypeValue.toUpperCase().replace(" ", "_"));
    } catch (IllegalArgumentException e) {
      throw new BoxSDKError("Invalid stream type: " + streamTypeValue);
    }
  }

  private Valuable convertEventType(String eventTypeValue) {
    if (eventTypeValue == null) return null;
    try {
      return GetEventsQueryParamsEventTypeField.valueOf(
          eventTypeValue.toUpperCase().replace(" ", "_"));
    } catch (IllegalArgumentException e) {
      throw new BoxSDKError("Invalid event type: " + eventTypeValue);
    }
  }
}
