package com.box.sdk;

import java.text.ParseException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

public class BoxEventTest {
    @Test
    @Category(UnitTest.class)
    public void newBoxEventHandlesUnknownEventType() {
        String eventJSON = "{ \"type\": \"event\", \"event_id\": \"f82c3ba03e41f7e8a7608363cc6c0390183c3f83\", "
            + "\"event_type\": \"UNKNOWN_EVENT_TYPE\" }";
        BoxEvent event = new BoxEvent(null, eventJSON);

        assertThat(event.getType(), is(BoxEvent.Type.UNKNOWN));
    }

    @Test
    @Category(UnitTest.class)
    public void newBoxEventShouldParseAllFieldsCorrectly() throws ParseException {
        final String eventID = "non-empty event ID";
        final String sessionID = "non-empty session ID";
        final String userID = "non-empty user ID";
        final String userName = "non-empty user name";
        final String userLogin = "non-empty user login";
        final String createdAt = "2014-12-18T16:25:15-08:00";
        final String ipAddress = "non-empty IP";
        final String detailsType = "non-empty details type";
        final boolean isPerformedByAdmin = true;

        JsonObject eventJSON = new JsonObject()
            .add("event_id", eventID)
            .add("session_id", sessionID)
            .add("created_by", new JsonObject()
                .add("type", "user")
                .add("id", userID)
                .add("name", userName)
                .add("login", userLogin))
            .add("created_at", createdAt)
            .add("ip_address", ipAddress)
            .add("additional_details", new JsonObject()
                .add("type", detailsType)
                .add("is_performed_by_admin", isPerformedByAdmin))
            .add("accessible_by", new JsonObject()
                .add("type", "user")
                .add("id", userID)
                .add("name", userName)
                .add("login", userLogin));

        BoxEvent event = new BoxEvent(null, eventJSON);
        assertEquals(eventID, event.getID());
        assertEquals(sessionID, event.getSessionID());
        assertEquals(userID, event.getCreatedBy().getID());
        assertEquals(userName, event.getCreatedBy().getName());
        assertEquals(userLogin, event.getCreatedBy().getLogin());
        assertEquals(BoxDateFormat.parse(createdAt), event.getCreatedAt());
        assertEquals(ipAddress, event.getIPAddress());
        assertEquals(detailsType, event.getAdditionalDetails().get("type").asString());
        assertEquals(isPerformedByAdmin, event.getAdditionalDetails().get("is_performed_by_admin").asBoolean());
        assertEquals(userID, event.getAccessibleBy().getID());
        assertEquals(userName, event.getAccessibleBy().getName());
        assertEquals(userLogin, ((BoxUser.Info) event.getAccessibleBy()).getLogin());
    }

    @Test
    @Category(UnitTest.class)
    public void newBoxEventShouldParseAccessibleByFieldCorrectlyWhenItIsAGroup() throws ParseException {
        final String eventID = "non-empty event ID";
        final String groupID = "non-empty group ID";
        final String groupName = "non-empty group name";
        JsonObject eventJSON = new JsonObject()
            .add("event_id", eventID)
            .add("accessible_by", new JsonObject()
                .add("type", "group")
                .add("id", groupID)
                .add("name", groupName));

        BoxEvent event = new BoxEvent(null, eventJSON);
        assertEquals(eventID, event.getID());

        assertTrue(event.getAccessibleBy() instanceof BoxGroup.Info);
        BoxGroup.Info parsedGroupInfo = (BoxGroup.Info) event.getAccessibleBy();
        assertEquals(groupID, parsedGroupInfo.getID());
        assertEquals(groupName, parsedGroupInfo.getName());
    }
}
