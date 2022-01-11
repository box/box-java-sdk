# Events

The Box API provides an events endpoint that utilizes long-polling to send user
events in real-time. The SDK provides an `EventStream` class that automatically
handles long-polling and deduplicating events.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [User Events](#user-events)
  - [Deduplicating Events](#deduplicating-events)
- [Enterprise (Admin) Events](#enterprise-admin-events)
  - [Historical Querying](#historical-querying)
  - [Live Monitoring](#live-monitoring)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## User Events

Subscribing to user events works by creating an `EventStream` and attaching
listeners for the events that are fetched in near-real time from the API.

When the `EventStream` is started, it will begin long-polling on a separate
thread. Events received from the API are then forwarded to any listeners as a
[`BoxEvent`][box-event] object.

To create an `EventStream` starting from the current point in time, use
the [`EventStream(BoxAPIConnection api)`][event-stream] constructor.  To
start from a known stream position, pass the stream position to the
[`EventStream(BoxAPIConnection api, long streamPosition)`][event-stream-position]
constructor.

<!-- sample get_events -->
```java
EventStream stream = new EventStream(api);
        stream.addListener(new EventListener() {
public void onEvent(BoxEvent event) {
        // Handle the event.
        }
        });
        stream.start();
```

Keep in mind that events are received on a separate thread, so things like UI
operations may need to be explicitly delegated back to the UI thread.

When you're done listening for events, be sure to call `stream.stop()` to stop
long-polling.

[event-stream]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/EventStream.html#EventStream-com.box.sdk.BoxAPIConnection-
[event-stream-position]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/EventStream.html#EventStream-com.box.sdk.BoxAPIConnection-long-
[box-event]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxEvent.html

### Deduplicating Events

Since the Box API [may send duplicate events](https://developers.box.com/docs/#events),
the `EventStream` will remember the last 512 received events and automatically
ignore them.

## Enterprise (Admin) Events

### Historical Querying

The Box API provides an `EventLog` class and a
`getEnterpriseEvents(BoxAPIConnection api, EnterpriseEventsRequest enterpriseEventsRequest)` method
that reads from the `admin-logs` stream and returns an `Iterable<BoxEvent>` over
Enterprise [`BoxEvent`][box-event] records. The emphasis for this stream is on completeness over latency,
which means that Box will deliver admin events in chronological order and without duplicates,
but with higher latency. You can specify start and end time/dates. This method
will only work with an API connection for an enterprise admin account or service account with a manage enterprise properties.

<!-- sample get_events enterprise -->
```java
// get the last two hours of unfiltered enterprise events
Date startDate = new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 2));
        Date endDate = new Date(System.currentTimeMillis());
        EnterpriseEventsRequest request = new EnterpriseEventsRequest()
        .after(startDate)
        .before(endDate);
        EventLog eventLog = EventLog.getEnterpriseEvents(api, request);
        for (BoxEvent event : eventLog) {
        System.out.println("Enterprise Event Created by User: "
        + event.getCreatedBy().getName()
        + " Login: " + event.getCreatedBy().getLogin()
        + " Event Type: " + event.getEventType()
        + " Created at: " + event.getCreatedAt().toString()
        );
        };
```

Additionally, you can set a limit of the number of enterprise events to be retrieved per response by specifying the
limit field.

```java
// get first 20 events
EnterpriseEventsRequest request = new EnterpriseEventsRequest()
        .limit(20);
        EventLog eventLog = EventLog.getEnterpriseEvents(api, request);
        for (BoxEvent event : eventLog) {
        System.out.println("Enterprise Event Created by User: "
        + event.getCreatedBy().getName()
        + " Login: " + event.getCreatedBy().getLogin()
        + " Event Type: " + event.getEventType()
        + " Created at: " + event.getCreatedAt().toString()
        );
        };
```

<!-- sample get_events enterprise_filter -->
You can also filter events by type.
```java
// filter events by type
EnterpriseEventsRequest request = new EnterpriseEventsRequest()
        .types(EventType.ITEM_CREATE, EventType.ITEM_OPEN);
        EventLog eventLog = EventLog.getEnterpriseEvents(api, request);
        for (BoxEvent event : eventLog){
        System.out.println("Enterprise Event Created by User: "
        + event.getCreatedBy().getName()
        + " Login: " + event.getCreatedBy().getLogin()
        + " Event Type: " + event.getEventType()
        + " Created at: " + event.getCreatedAt().toString()
        );
        };
```

You can also filter events by type name. This is usefull if a new event type is introduced and is not mapped to
`BoxEvent.EventType`.
```java
// filter events by type name
EnterpriseEventsRequest request = new EnterpriseEventsRequest()
  .typeNames("ITEM_CREATE", "ITEM_OPEN");
EventLog eventLog = EventLog.getEnterpriseEvents(api, request);
for (BoxEvent event : eventLog){
  System.out.println("Enterprise Event Created by User: "
    + event.getCreatedBy().getName()
    + " Login: " + event.getCreatedBy().getLogin()
    + " Event Type: " + event.getEventType()
    + " Event Type Name: " + event.getTypeName()
    + " Created at: " + event.getCreatedAt().toString()
  );
};
```
Bear in mind that if an event type is not mapped to `BoxEvent.EventType` the value of `BoxEvent#getEventType()` will
be `BoxEvent.EventType.UNKNOWN` but `BoxEvent#getTypeName()` will return its name.

If you want to progress within a stream you can use position parameter:
```java
EnterpriseEventsRequest request1 = new EnterpriseEventsRequest().limit(20);
EventLog eventLog1 = EventLog.getEnterpriseEvents(api, request1);
// process revieved events
EnterpriseEventsRequest request2 = new EnterpriseEventsRequest().limit(20)
    .position(eventLog1.getNextStreamPosition()); // get events from the next position
EventLog eventLog2 = EventLog.getEnterpriseEvents(api, request2);
// process revieved events
```

### Live Monitoring
To monitor recent events that have been generated within Box across the enterprise use
`EventLog#getEnterpriseEventsStream(BoxAPIConnection api, EnterpriseEventsStreamRequest enterpriseEventsStreamRequest)`,
method that reads from the `admin-logs-streaming` stream and returns an `Iterable<BoxEvent>` over
Enterprise [`BoxEvent`][box-event] records.
The emphasis for this feed is on low latency rather than chronological accuracy, which means that Box may return
events more than once and out of chronological order. Events are returned via the API around 12 seconds after they
are processed by Box (the 12 seconds buffer ensures that new events are not written after your cursor position).
Only two weeks of events are available via this feed, and you cannot set start and end time/dates. This method
will only work with an API connection for an enterprise admin account or service account with a manage enterprise properties.

<!-- sample get_events enterprise_stream -->
```java
EnterpriseEventsStreamRequest request = new EnterpriseEventsStreamRequest()
EventLog eventLog = EventLog.getEnterpriseEventsStream(api, request);
for (BoxEvent event : eventLog) {
    System.out.println("Enterprise Event Created by User: "
        + event.getCreatedBy().getName()
        + " Login: " + event.getCreatedBy().getLogin()
        + " Event Type: " + event.getEventType()
        + " Created at: " + event.getCreatedAt().toString()
    );
};
```

You can limit number of events returned.
```java
// get first 20 events
EnterpriseEventsStreamRequest request = new EnterpriseEventsStreamRequest()
    .limit(20)
EventLog eventLog = EventLog.getEnterpriseEventsStream(api, request);
```

<!-- sample get_events enterprise_stream_filter -->
You can also filter events by type.
```java
// filter events by type
EnterpriseEventsStreamRequest request = new EnterpriseEventsStreamRequest()
    .types(EventType.ITEM_CREATE, EventType.ITEM_OPEN);
EventLog eventLog = EventLog.getEnterpriseEventsStream(api, request);
for (BoxEvent event : eventLog){
    System.out.println("Enterprise Event Created by User: "
        + event.getCreatedBy().getName()
        + " Login: " + event.getCreatedBy().getLogin()
        + " Event Type: " + event.getEventType()
        + " Created at: " + event.getCreatedAt().toString()
    );
};
```

You can also filter events by type name. This is usefull if a new event type is introduced and is not mapped to
`BoxEvent.EventType`.
```java
// filter events by type name
EnterpriseEventsRequest request = new EnterpriseEventsStreamRequest()
  .typeNames("ITEM_CREATE", "ITEM_OPEN");
EventLog eventLog = EventLog.getEnterpriseEventsStream(api, request);
for (BoxEvent event : eventLog){
  System.out.println("Enterprise Event Created by User: "
    + event.getCreatedBy().getName()
    + " Login: " + event.getCreatedBy().getLogin()
    + " Event Type: " + event.getEventType()
    + " Event Type Name: " + event.getTypeName()
    + " Created at: " + event.getCreatedAt().toString()
  );
};
```
Bear in mind that if an event type is not mapped to `BoxEvent.EventType` the value of `BoxEvent#getEventType()` will
be `BoxEvent.EventType.UNKNOWN` but `BoxEvent#getTypeName()` will return its name.

If you want to progress within a stream you can use position parameter:
```java
EnterpriseEventsStreamRequest request1 = new EnterpriseEventsStreamRequest().limit(20);
EventLog eventLog1 = EventLog.getEnterpriseEventsStream(api, request1);
// process revieved events
EnterpriseEventsStreamRequest request2 = new EnterpriseEventsStreamRequest().limit(20)
    .position(eventLog1.getNextStreamPosition()); // get events from the next position
EventLog eventLog2 = EventLog.getEnterpriseEventsStream(api, request2);
// process revieved events
```
If you have the next stream position, and make a subsequent call, the API will return immediately
even when there are no events, the next stream position will be returned.
If you have a stream position that is older than two weeks than API will return no events and next
stream position.
