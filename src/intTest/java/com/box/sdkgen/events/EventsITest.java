package com.box.sdkgen.events;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.epochSecondsToDateTime;
import static com.box.sdkgen.internal.utils.UtilsManager.getEpochTimeInSeconds;

import com.box.sdkgen.box.eventstream.EventStream;
import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.events.GetEventsQueryParams;
import com.box.sdkgen.managers.events.GetEventsQueryParamsEventTypeField;
import com.box.sdkgen.managers.events.GetEventsQueryParamsStreamTypeField;
import com.box.sdkgen.schemas.event.Event;
import com.box.sdkgen.schemas.events.Events;
import com.box.sdkgen.schemas.eventsource.EventSource;
import com.box.sdkgen.schemas.realtimeserver.RealtimeServer;
import com.box.sdkgen.schemas.realtimeservers.RealtimeServers;
import com.box.sdkgen.schemas.user.User;
import java.time.OffsetDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class EventsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testEvents() {
    Events events = client.getEvents().getEvents();
    assert events.getEntries().size() > 0;
    Event firstEvent = events.getEntries().get(0);
    assert convertToString(firstEvent.getCreatedBy().getType()).equals("user");
    assert !(convertToString(firstEvent.getEventType()).equals(""));
  }

  @Test
  public void testEventUpload() {
    Events events =
        client
            .getEvents()
            .getEvents(
                new GetEventsQueryParams.Builder()
                    .streamType(GetEventsQueryParamsStreamTypeField.ADMIN_LOGS)
                    .eventType(Arrays.asList(GetEventsQueryParamsEventTypeField.UPLOAD))
                    .build());
    assert events.getEntries().size() > 0;
    Event firstEvent = events.getEntries().get(0);
    assert convertToString(firstEvent.getEventType()).equals("UPLOAD");
    EventSource source = firstEvent.getSource().getEventSource();
    assert convertToString(source.getItemType()).equals("file")
        || convertToString(source.getItemType()).equals("folder");
    assert !(source.getItemId().equals(""));
    assert !(source.getItemName().equals(""));
  }

  @Test
  public void testEventDeleteUser() {
    Events events =
        client
            .getEvents()
            .getEvents(
                new GetEventsQueryParams.Builder()
                    .streamType(GetEventsQueryParamsStreamTypeField.ADMIN_LOGS)
                    .eventType(Arrays.asList(GetEventsQueryParamsEventTypeField.DELETE_USER))
                    .build());
    assert events.getEntries().size() > 0;
    Event firstEvent = events.getEntries().get(0);
    assert convertToString(firstEvent.getEventType()).equals("DELETE_USER");
    User source = firstEvent.getSource().getUser();
    assert convertToString(source.getType()).equals("user");
    assert !(source.getId().equals(""));
  }

  @Test
  public void testEventSourceFileOrFolder() {
    Events events =
        client
            .getEvents()
            .getEvents(
                new GetEventsQueryParams.Builder()
                    .streamType(GetEventsQueryParamsStreamTypeField.CHANGES)
                    .build());
    assert events.getEntries().size() > 0;
  }

  @Test
  public void testGetEventsWithLongPolling() {
    RealtimeServers servers = client.getEvents().getEventsWithLongPolling();
    assert servers.getEntries().size() > 0;
    RealtimeServer server = servers.getEntries().get(0);
    assert convertToString(server.getType()).equals("realtime_server");
    assert !(server.getUrl().equals(""));
  }

  @Test
  public void testGetEventsWithDateFilters() {
    long currentEpochTimeInSeconds = getEpochTimeInSeconds();
    long epochTimeInSecondsAWeekAgo = currentEpochTimeInSeconds - 7 * 24 * 60 * 60;
    OffsetDateTime createdAfterDate = epochSecondsToDateTime(epochTimeInSecondsAWeekAgo);
    OffsetDateTime createdBeforeDate = epochSecondsToDateTime(currentEpochTimeInSeconds);
    Events servers =
        client
            .getEvents()
            .getEvents(
                new GetEventsQueryParams.Builder()
                    .streamType(GetEventsQueryParamsStreamTypeField.ADMIN_LOGS)
                    .limit(1L)
                    .createdAfter(createdAfterDate)
                    .createdBefore(createdBeforeDate)
                    .build());
    assert servers.getEntries().size() == 1;
  }

  @Test
  public void testGetEventStream() {
    EventStream eventStream = client.getEvents().getEventStream();
    assert !(eventStream == null);
  }
}
