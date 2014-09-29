package com.box.sdk;

import java.net.URL;

public class BoxJSONRequest extends BoxAPIRequest {
    private String json;

    public BoxJSONRequest(BoxAPIConnection api, URL url, String method) {
        super(api, url, method);

        this.addHeader("Content-Type", "application/json");
    }

    @Override
    public void setBody(String body) {
        super.setBody(body);
        this.json = body;
    }

    @Override
    protected String bodyToString() {
        return this.json;
    }
}
