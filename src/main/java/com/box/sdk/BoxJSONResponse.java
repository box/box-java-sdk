package com.box.sdk;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class BoxJSONResponse extends BoxAPIResponse {
    private static final int BUFFER_SIZE = 8192;

    private String json;

    public BoxJSONResponse(HttpURLConnection connection) {
        super(connection);
    }

    public String getJSON() {
        if (this.json != null) {
            return this.json;
        }

        InputStreamReader reader = new InputStreamReader(this.getBody(), StandardCharsets.UTF_8);
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[BUFFER_SIZE];

        try {
            int read = reader.read(buffer, 0, BUFFER_SIZE);
            while (read != -1) {
                builder.append(buffer, 0, read);
                read = reader.read(buffer, 0, BUFFER_SIZE);
            }

            reader.close();
            this.disconnect();
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        }
        this.json = builder.toString();
        return this.json;
    }

    @Override
    protected String bodyToString() {
        String bodyString = super.bodyToString();
        if (bodyString == null) {
            return this.getJSON();
        } else {
            return bodyString;
        }
    }
}
