package com.box.sdk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public class EventStream {
    private static final int LIMIT = 800;
    private static final int LRU_SIZE = 512;
    private static final URLTemplate EVENT_URL = new URLTemplate("events?limit=" + LIMIT + "&stream_position=%s");

    private final OAuthSession session;
    private final Collection<EventListener> listeners;
    private final Object listenerLock;
    private final LinkedHashSet<String> receivedEvents;

    private boolean started;
    private Poller poller;

    public EventStream(OAuthSession session) {
        this.session = session;
        this.listeners = new ArrayList<EventListener>();
        this.listenerLock = new Object();
        this.receivedEvents = new LinkedHashSet<String>(LRU_SIZE);
    }

    public void addListener(EventListener listener) {
        synchronized (this.listenerLock) {
            this.listeners.add(listener);
        }
    }

    public void stop() {
        if (!this.started) {
            throw new IllegalStateException("Cannot stop the EventStream because it isn't started.");
        }

        this.poller.stop();
        this.started = false;
    }

    public void start() {
        if (this.started) {
            throw new IllegalStateException("Cannot start the EventStream because it isn't stopped.");
        }

        BoxAPIRequest request = new BoxAPIRequest(this.session, EVENT_URL.build("now"), "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        final long initialPosition = jsonObject.get("next_stream_position").asLong();
        this.poller = new Poller(initialPosition);

        new Thread(this.poller).start();

        this.started = true;
    }

    private void notifyListeners(BoxEvent event) {
        synchronized (this.listenerLock) {
            boolean newEvent = this.receivedEvents.add(event.getID());
            if (newEvent) {
                if (this.receivedEvents.size() > LRU_SIZE) {
                    this.receivedEvents.iterator().remove();
                }

                for (EventListener listener : this.listeners) {
                    listener.onEvent(event);
                }
            }
        }
    }

    private class Poller implements Runnable {
        private final long initialPosition;
        private final Object setServerLock;

        private RealtimeServerConnection server;
        private boolean stopped;

        public Poller(long initialPosition) {
            this.initialPosition = initialPosition;
            this.setServerLock = new Object();
            this.server = new RealtimeServerConnection(EventStream.this.session);
        }

        @Override
        public void run() {
            long position = this.initialPosition;
            while (!this.stopped) {
                if (this.server.getRemainingRetries() == 0) {
                    synchronized (this.setServerLock) {
                        this.server = new RealtimeServerConnection(EventStream.this.session);
                    }
                }

                if (this.server.waitForChange(position)) {
                    if (this.stopped) {
                        break;
                    }

                    BoxAPIRequest request = new BoxAPIRequest(EventStream.this.session, EVENT_URL.build(position),
                        "GET");
                    BoxJSONResponse response = (BoxJSONResponse) request.send();
                    JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
                    position = jsonObject.get("next_stream_position").asLong();
                    JsonArray entriesArray = jsonObject.get("entries").asArray();
                    for (JsonValue entry : entriesArray) {
                        BoxEvent event = new BoxEvent(EventStream.this.session, entry.asObject());
                        EventStream.this.notifyListeners(event);
                    }
                }
            }
        }

        public void stop() {
            synchronized (this.setServerLock) {
                this.server.close();
            }
        }
    }
}
