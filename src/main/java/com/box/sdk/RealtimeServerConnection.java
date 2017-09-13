package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

class RealtimeServerConnection {
    public static final URLTemplate EVENT_URL = new URLTemplate("events");
    public static final URLTemplate EVENT_POSITION_URL = new URLTemplate("events?stream_position=%s");

    private final BoxAPIConnection api;
    private final int timeout;
    private final String serverURLString;

    private int retries;
    private BoxJSONResponse response;

    RealtimeServerConnection(BoxAPIConnection api) {
        BoxAPIRequest request = new BoxAPIRequest(api, EVENT_URL.build(api.getBaseURL()), "OPTIONS");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        JsonArray entries = jsonObject.get("entries").asArray();
        JsonObject firstEntry = entries.get(0).asObject();
        this.serverURLString = firstEntry.get("url").asString();
        this.retries = Integer.parseInt(firstEntry.get("max_retries").asString());
        this.timeout = firstEntry.get("retry_timeout").asInt();
        this.api = api;
    }

    int getRemainingRetries() {
        return this.retries;
    }

    boolean waitForChange(long position) {
        if (this.retries < 1) {
            throw new IllegalStateException("No more retries are allowed.");
        }

        URL url;
        try {
            String u = this.serverURLString + "&stream_position=" + position;
            url = new URL(u);
        } catch (MalformedURLException e) {
            throw new BoxAPIException("The long poll URL was malformed.", e);
        }

        while (this.retries > 0) {
            this.retries--;
            try {
                BoxAPIRequest request = new BoxAPIRequest(this.api, url, "GET");
                request.setConnectTimeout(this.timeout * 1000);
                request.setReadTimeout(this.timeout * 1000);
                this.response = (BoxJSONResponse) request.send();
                JsonObject jsonObject = JsonObject.readFrom(this.response.getJSON());
                String message = jsonObject.get("message").asString();
                if (message.equals("new_change")) {
                    return true;
                }
            } catch (BoxAPIException e) {
                break;
            }
        }

        return false;
    }
}
