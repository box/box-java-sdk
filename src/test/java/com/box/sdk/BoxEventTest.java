package com.box.sdk;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxEventTest {
    @Test
    @Category(UnitTest.class)
    public void newBoxEventHandlesUnknownEventType() {
        String eventJSON = "{ \"type\": \"event\", \"event_id\": \"f82c3ba03e41f7e8a7608363cc6c0390183c3f83\", "
            + "\"event_type\": \"UNKNOWN_EVENT_TYPE\" }";
        BoxEvent event = new BoxEvent(null, eventJSON);

        assertThat(event.getType(), is(BoxEvent.Type.UNKNOWN));
    }
}
