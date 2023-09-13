package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.Collections;

public class BoxItemTest {
    @Test
    public void shouldUseRequestInterceptor() {
        String itemType = "file";
        String itemId = "some_id";
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(request -> {
            if ("https://api.box.com/2.0/shared_items".equals(request.getUrl().toString())) {
                JsonObject body = new JsonObject();
                body.add("type", itemType);
                body.add("id", itemId);
                return new BoxJSONResponse(
                        200,
                        request.getMethod(),
                        request.getUrl().toString(),
                        Collections.emptyMap(),
                        body
                );
            }
            return null;
        });

        BoxItem.Info item = BoxItem.getSharedItem(api, "some_link");
        MatcherAssert.assertThat(item.getType(), CoreMatchers.is(itemType));
        MatcherAssert.assertThat(item.getID(), CoreMatchers.is(itemId));
    }
}
