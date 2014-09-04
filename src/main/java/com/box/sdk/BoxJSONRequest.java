package com.box.sdk;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoxJSONRequest extends BoxAPIRequest {
    private static final Logger LOGGER = Logger.getLogger(BoxFolder.class.getName());

    private String json;

    public BoxJSONRequest(BoxAPIConnection api, URL url, String method) {
        super(api, url, method);

        this.getConnection().addRequestProperty("Content-Type", "application/json");
    }

    @Override
    public void setBody(String body) {
        super.setBody(body);
        this.json = body;
    }

    @Override
    protected void logRequest() {
        if (!LOGGER.isLoggable(Level.INFO)) {
            return;
        }

        StringBuilder builder = new StringBuilder(super.toString());
        builder.append(System.lineSeparator());
        builder.append(this.json);
        builder.append(System.lineSeparator());

        LOGGER.log(Level.INFO, builder.toString());
    }
}
