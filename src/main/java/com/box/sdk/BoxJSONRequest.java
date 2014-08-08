package com.box.sdk;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoxJSONRequest extends BoxAPIRequest {
    private static final Logger LOGGER = Logger.getLogger(BoxFolder.class.getName());

    private String json;

    public BoxJSONRequest(OAuthSession session, URL url, String method) {
        super(session, url, method);

        this.getConnection().addRequestProperty("Content-Type", "application/json");
    }

    public void setJSON(String json) {
        try {
            this.getConnection().setFixedLengthStreamingMode(json.length());
            OutputStream stream = this.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        }

        this.json = json;
    }

    @Override
    protected void logRequest() {
        if (!LOGGER.isLoggable(Level.INFO)) {
            return;
        }

        StringBuilder builder = new StringBuilder(super.toString());
        HttpURLConnection connection = this.getConnection();
        builder.append(System.lineSeparator());
        builder.append(this.json);
        builder.append(System.lineSeparator());

        LOGGER.log(Level.INFO, builder.toString());
    }
}
