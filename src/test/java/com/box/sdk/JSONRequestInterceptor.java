package com.box.sdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.fail;

import com.eclipsesource.json.JsonObject;

public abstract class JSONRequestInterceptor implements RequestInterceptor {
    public static RequestInterceptor respondWith(final JsonObject json) {
        return new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return json.toString();
                    }
                };
            }
        };
    }

    @Override
    public BoxAPIResponse onRequest(BoxAPIRequest request) {
        BufferedReader bodyReader = new BufferedReader(new InputStreamReader(request.getBody(),
            StandardCharsets.UTF_8));

        JsonObject json = null;
        try {
            json = JsonObject.readFrom(bodyReader);
            bodyReader.close();
        } catch (IOException e) {
            fail(e.getMessage());
        }

        return this.onJSONRequest((BoxJSONRequest) request, json);
    }

    protected abstract BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json);
}
