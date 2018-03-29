Events
===========

The Box API provides an events endpoint that utilizes long-polling to send user
events in real-time. The SDK provides an `EventStream` class that automatically
handles long-polling and deduplicating events.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [User Events](#user-events)
  - [Deduplicating Events](#deduplicating-events)
- [Enterprise (Admin) Events](#enterprise-admin-events)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

User Events
-----------

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

Enterprise (Admin) Events
-------------------------

The Box API provides an `EventLog` class and a `getEnterpriseEvents` method
that reads from the admin-logs streams and returns an `Iterable<BoxEvent>` over
Enterprise [`BoxEvent`][box-event] records.  There is no real-time interface
to Admin Events, but you can specify start and end time/dates. This method
will only work with an API connection for an enterprise admin account.

```java
// get the last two hours of unfiltered enterprise events
Date startDate = new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 2));
Date endDate = new Date(System.currentTimeMillis());
EventLog eventLog = EventLog.getEnterpriseEvents(api, startDate, endDate);
for (BoxEvent event : eventLog) {
    System.out.println("Enterprise Event Created by User: "
            + event.getCreatedBy().getName()
            + " Login: " + event.getCreatedBy().getLogin()
            + " Event Type: " + event.getType()
            + " Created at: " + event.getCreatedAt().toString());
};
```

Additionally, you can set a limit of the number of enterprise events to be retrieved per response by specifying the
limit field.

```java
int LIMIT = 5;
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN-WITH-ADMIN-ACCESS");
EventLog eventLog = EventLog.getEnterpriseEvents(api, "STREAM-POSITION"
    new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 2)),
    new Date(System.currentTimeMillis()), LIMIT);
for (BoxEvent event : eventLog) {
    System.out.println("Enterprise Event Created by User: "
            + event.getCreatedBy().getName()
            + " Login: " + event.getCreatedBy().getLogin()
            + " Event Type: " + event.getType()
            + " Created at: " + event.getCreatedAt().toString());
    };
```
