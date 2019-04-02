package com.box.sdk;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

public class BoxEventTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(UnitTest.class)
    public void testIsEventLogUnique() throws IOException, ParseException {
        String getResult = "";
        final String eventURL = "/events";
        String startTime = "2019-02-02T21:48:38+0000";
        String endTime = "2019-02-02T23:48:40+0000";

        getResult = TestConfig.getFixture("BoxEvent/GetEnterpriseEvents200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(eventURL))
                .withQueryParam("stream_type", WireMock.equalTo("admin_logs"))
                .withQueryParam("limit", WireMock.equalTo("500"))
                .withQueryParam("created_after", WireMock.equalTo("2019-02-02T21:48:38+0000"))
                .withQueryParam("created_before", WireMock.equalTo("2019-02-02T23:48:40+0000"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(getResult)));

        Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ").parse(startTime);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ").parse(endTime);
        EventLog eventLog = EventLog.getEnterpriseEvents(this.api, startDate, endDate);
        Assert.assertTrue(eventLog.getSize() == 1);
    }

    @Test
    @Category(UnitTest.class)
    public void testEventLog() throws IOException, ParseException {
        String getResult = "";
        final String eventURL = "/events";
        String startTime = "2019-02-02T21:48:38+0000";
        String endTime = "2019-02-02T23:48:40+0000";

        getResult = TestConfig.getFixture("BoxEvent/GetEnterpriseEvents200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(eventURL))
                .withQueryParam("stream_type", WireMock.equalTo("admin_logs"))
                .withQueryParam("limit", WireMock.equalTo("500"))
                .withQueryParam("created_after", WireMock.equalTo("2019-02-02T21:48:38+0000"))
                .withQueryParam("created_before", WireMock.equalTo("2019-02-02T23:48:40+0000"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(getResult)));

        Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ").parse(startTime);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ").parse(endTime);
        EventLog eventLog = EventLog.getEnterpriseEvents(this.api, startDate, endDate);
        BoxEvent event = eventLog.iterator().next();
        Assert.assertEquals("54321", event.getActionBy().getID());
        Assert.assertEquals("12345", event.getSourceInfo().getID());
        Assert.assertEquals("Example User", event.getCreatedBy().getName());
        Assert.assertEquals(BoxEvent.Type.ADD_LOGIN_ACTIVITY_DEVICE, event.getType());
    }

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
