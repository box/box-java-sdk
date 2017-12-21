package com.box.sdk;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * {@link BoxInvite} related tests.
 */
public class BoxInviteTest {

    @Test
    @Category(UnitTest.class)
    public void getInfoMakesCorrectCall() {

        final String id = "783645";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                assertEquals("https://api.box.com/2.0/invites/" + id,
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"type\":\"invite\",\"id\":\"" + id + "\"}";
                    }
                };
            }
        });

        BoxInvite invite = new BoxInvite(api, id);
        BoxInvite.Info info = invite.getInfo();
        assertEquals(id, info.getID());
    }
}
