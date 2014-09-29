Events
======

The Box API provides an events endpoint that utilizes long-polling to send events in real-time. The SDK provides an `EventStream` class that automatically handles long-polling and deduplicating events.

Listening to the EventStream
----------------------------

When the `EventStream` is started, it will begin long-polling on a separate thread. Events received from the API are then forwarded to any listeners.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");
EventStream stream = new EventStream(api);
stream.addListener(new EventListener() {
    public void onEvent(BoxEvent event) {
        // Handle the event.
    }
});
stream.start();
```

Keep in mind that events are received on a separate thread, so things like UI operations may need to be explicitly delegated back to the UI thread.

When you're done listening for events, be sure to call `stream.stop()` to stop long-polling.

### Deduplicating Events

Since the Box API [may send duplicate events](https://developers.box.com/docs/#events), the `EventStream` will remember the last 512 received events and automatically ignore them.
