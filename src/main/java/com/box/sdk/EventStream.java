package com.box.sdk;

import java.util.ArrayList;
import java.util.Collection;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Receives real-time events from the API and forwards them to {@link EventListener EventListeners}.
 *
 * <p>This class handles long polling the Box events endpoint in order to receive real-time user events.
  * When an EventStream is started, it begins long polling on a separate thread until the {@link #stop} method
  * is called.
  * Since the API may return duplicate events, EventStream also maintains a small cache of the most recently received
  * event IDs in order to automatically deduplicate events.</p>
  * <p>Note: Enterprise Events can be accessed by admin users with the EventLog.getEnterpriseEvents method</p>
 *
 */
public class EventStream {

    private static final int LIMIT = 800;
    private static final int STREAM_POSITION_NOW = -1;
    private static final int DEFAULT_POLLING_DELAY = 1000;

    /**
     * Events URL.
     */
    public static final URLTemplate EVENT_URL = new URLTemplate("events?limit=" + LIMIT + "&stream_position=%s");

    private final BoxAPIConnection api;
    private final long startingPosition;
    private final int pollingDelay;
    private final Collection<EventListener> listeners;
    private final Object listenerLock;

    private LRUCache<String> receivedEvents;
    private boolean started;
    private Poller poller;
    private Thread pollerThread;

    /**
     * Constructs an EventStream using an API connection.
     * @param  api the API connection to use.
     */
    public EventStream(BoxAPIConnection api) {
        this(api, STREAM_POSITION_NOW, DEFAULT_POLLING_DELAY);
    }

    /**
     * Constructs an EventStream using an API connection and a starting initial position.
     * @param api the API connection to use.
     * @param startingPosition the starting position of the event stream.
     */
    public EventStream(BoxAPIConnection api, long startingPosition) {
        this(api, startingPosition, DEFAULT_POLLING_DELAY);
    }

    /**
     * Constructs an EventStream using an API connection and a starting initial position with custom polling delay.
     * @param api the API connection to use.
     * @param startingPosition the starting position of the event stream.
     * @param pollingDelay the delay in milliseconds between successive calls to get more events.
     */
    public EventStream(BoxAPIConnection api, long startingPosition, int pollingDelay) {
        this.api = api;
        this.startingPosition = startingPosition;
        this.listeners = new ArrayList<EventListener>();
        this.listenerLock = new Object();
        this.pollingDelay = pollingDelay;
    }

    /**
     * Adds a listener that will be notified when an event is received.
     * @param listener the listener to add.
     */
    public void addListener(EventListener listener) {
        synchronized (this.listenerLock) {
            this.listeners.add(listener);
        }
    }

    /**
     * Indicates whether or not this EventStream has been started.
     * @return true if this EventStream has been started; otherwise false.
     */
    public boolean isStarted() {
        return this.started;
    }

    /**
     * Stops this EventStream and disconnects from the API.
     * @throws IllegalStateException if the EventStream is already stopped.
     */
    public void stop() {
        if (!this.started) {
            throw new IllegalStateException("Cannot stop the EventStream because it isn't started.");
        }

        this.started = false;
        this.pollerThread.interrupt();
    }

    /**
     * Starts this EventStream and begins long polling the API.
     * @throws IllegalStateException if the EventStream is already started.
     */
    public void start() {
        if (this.started) {
            throw new IllegalStateException("Cannot start the EventStream because it isn't stopped.");
        }

        final long initialPosition;

        if (this.startingPosition == STREAM_POSITION_NOW) {
            BoxAPIRequest request = new BoxAPIRequest(this.api, EVENT_URL.build(this.api.getBaseURL(), "now"), "GET");
            BoxJSONResponse response = (BoxJSONResponse) request.send();
            JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
            initialPosition = jsonObject.get("next_stream_position").asLong();
        } else {
            assert this.startingPosition >= 0 : "Starting position must be non-negative";
            initialPosition = this.startingPosition;
        }

        this.poller = new Poller(initialPosition);

        this.pollerThread = new Thread(this.poller);
        this.pollerThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                EventStream.this.notifyException(e);
            }
        });
        this.pollerThread.start();

        this.started = true;
    }

    /**
     * Indicates whether or not an event ID is a duplicate.
     *
     * <p>This method can be overridden by a subclass in order to provide custom de-duping logic.</p>
     *
     * @param  eventID the event ID.
     * @return         true if the event is a duplicate; otherwise false.
     */
    protected boolean isDuplicate(String eventID) {
        if (this.receivedEvents == null) {
            this.receivedEvents = new LRUCache<String>();
        }

        return !this.receivedEvents.add(eventID);
    }

    private void notifyNextPosition(long position) {
        synchronized (this.listenerLock) {
            for (EventListener listener : this.listeners) {
                listener.onNextPosition(position);
            }
        }
    }

    private void notifyEvent(BoxEvent event) {
        synchronized (this.listenerLock) {
            boolean isDuplicate = this.isDuplicate(event.getID());
            if (!isDuplicate) {
                for (EventListener listener : this.listeners) {
                    listener.onEvent(event);
                }
            }
        }
    }

    private void notifyException(Throwable e) {
        if (e instanceof InterruptedException && !this.started) {
            return;
        }

        this.stop();
        synchronized (this.listenerLock) {
            for (EventListener listener : this.listeners) {
                if (listener.onException(e)) {
                    return;
                }
            }
        }
    }

    private class Poller implements Runnable {
        private final long initialPosition;

        private RealtimeServerConnection server;

        public Poller(long initialPosition) {
            this.initialPosition = initialPosition;
            this.server = new RealtimeServerConnection(EventStream.this.api);
        }

        @Override
        public void run() {
            long position = this.initialPosition;
            while (!Thread.interrupted()) {
                if (this.server.getRemainingRetries() == 0) {
                    this.server = new RealtimeServerConnection(EventStream.this.api);
                }

                if (this.server.waitForChange(position)) {
                    if (Thread.interrupted()) {
                        return;
                    }

                    BoxAPIRequest request = new BoxAPIRequest(EventStream.this.api,
                        EVENT_URL.build(EventStream.this.api.getBaseURL(), position), "GET");
                    BoxJSONResponse response = (BoxJSONResponse) request.send();
                    JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
                    JsonArray entriesArray = jsonObject.get("entries").asArray();
                    for (JsonValue entry : entriesArray) {
                        BoxEvent event = new BoxEvent(EventStream.this.api, entry.asObject());
                        EventStream.this.notifyEvent(event);
                    }
                    position = jsonObject.get("next_stream_position").asLong();
                    EventStream.this.notifyNextPosition(position);
                    try {
                        // Delay re-polling to avoid making too many API calls
                        // Since duplicate events may appear in the stream, without any delay added
                        // the stream can make 3-5 requests per second and not produce any new
                        // events.  A short delay between calls balances latency for new events
                        // and the risk of hitting rate limits.
                        Thread.sleep(EventStream.this.pollingDelay);
                    } catch (InterruptedException ex) {
                        return;
                    }
                }
            }
        }
    }
}
