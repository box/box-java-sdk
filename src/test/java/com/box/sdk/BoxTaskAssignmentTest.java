package com.box.sdk;

import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxTaskAssignment} related tests.
 */
public class BoxTaskAssignmentTest {

    /**
     * Unit test for {@link BoxTaskAssignment#getInfo()}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/task_assignments/0",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        new BoxTaskAssignment(api, "0").getInfo();
    }

    /**
     * Unit test for {@link BoxTaskAssignment#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoSendsCorrectRequestWithFields() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/task_assignments/0?fields=item%2Cmessage",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        new BoxTaskAssignment(api, "0").getInfo("item", "message");
    }

    /**
     * Unit test for {@link BoxTaskAssignment#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoParseAllFieldsCorrectly() throws ParseException {
        final String id = "2698512";
        final String itemID = "8018809384";
        final String itemSequenceID = "0";
        final String itemEtag = "0";
        final String itemSha1 = "7840095ee096ee8297676a138d4e316eabb3ec96";
        final String itemName = "scrumworksToTrello.js";
        final String assignedToID = "1992432";
        final String assignedToName = "rhaegar@box.com";
        final String assignedToLogin = "rhaegar@box.com";
        final String message = null;
        final Date completedAt = null;
        final Date assignedAt = BoxDateFormat.parse("2013-05-10T11:43:41-07:00");
        final Date remindedAt = null;
        final BoxTaskAssignment.ResolutionState resolutionState = BoxTaskAssignment.ResolutionState.INCOMPLETE;
        final String assignedByID = "11993747";
        final String assignedByName = "sean";
        final String assignedByLogin = "sean@box.com";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"task_assignment\",\n"
                + "    \"id\": \"2698512\",\n"
                + "    \"item\": {\n"
                + "        \"type\": \"file\",\n"
                + "        \"id\": \"8018809384\",\n"
                + "        \"sequence_id\": \"0\",\n"
                + "        \"etag\": \"0\",\n"
                + "        \"sha1\": \"7840095ee096ee8297676a138d4e316eabb3ec96\",\n"
                + "        \"name\": \"scrumworksToTrello.js\"\n"
                + "    },\n"
                + "    \"assigned_to\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"1992432\",\n"
                + "        \"name\": \"rhaegar@box.com\",\n"
                + "        \"login\": \"rhaegar@box.com\"\n"
                + "    },\n"
                + "    \"message\": null,\n"
                + "    \"completed_at\": null,\n"
                + "    \"assigned_at\": \"2013-05-10T11:43:41-07:00\",\n"
                + "    \"reminded_at\": null,\n"
                + "    \"resolution_state\": \"incomplete\",\n"
                + "    \"assigned_by\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"11993747\",\n"
                + "        \"name\": \"sean\",\n"
                + "        \"login\": \"sean@box.com\"\n"
                + "    }\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxTaskAssignment.Info info = new BoxTaskAssignment(api, id).getInfo("item", "message");
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(itemID, info.getItem().getID());
        Assert.assertEquals(itemSequenceID, info.getItem().getSequenceID());
        Assert.assertEquals(itemEtag, info.getItem().getEtag());
        Assert.assertEquals(itemSha1, ((BoxFile.Info) info.getItem()).getSha1());
        Assert.assertEquals(itemName, info.getItem().getName());
        Assert.assertEquals(assignedToID, info.getAssignedTo().getID());
        Assert.assertEquals(assignedToName, info.getAssignedTo().getName());
        Assert.assertEquals(assignedToLogin, info.getAssignedTo().getLogin());
        Assert.assertEquals(message, info.getMessage());
        Assert.assertEquals(completedAt, info.getCompletedAt());
        Assert.assertEquals(assignedAt, info.getAssignedAt());
        Assert.assertEquals(remindedAt, info.getRemindedAt());
        Assert.assertEquals(resolutionState, info.getResolutionState());
        Assert.assertEquals(assignedByID, info.getAssignedBy().getID());
        Assert.assertEquals(assignedByName, info.getAssignedBy().getName());
        Assert.assertEquals(assignedByLogin, info.getAssignedBy().getLogin());
    }

    /**
     * Unit test for {@link BoxTaskAssignment#updateInfo(BoxTaskAssignment.Info)}
     */
    @Test
    @Category(UnitTest.class)
    public void testUpdateInfoSendsCorrectJson() {
        final String message = "hello!";
        final String resolutionState = "approved";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/task_assignments/0",
                        request.getUrl().toString());
                Assert.assertEquals(message, json.get("message").asString());
                Assert.assertEquals(resolutionState, json.get("resolution_state").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxTaskAssignment assignment = new BoxTaskAssignment(api, "0");
        BoxTaskAssignment.Info info = assignment.new Info();
        info.addPendingChange("message", message);
        info.addPendingChange("resolution_state", resolutionState);
        assignment.updateInfo(info);
    }

    /**
     * Unit test for {@link BoxTaskAssignment#updateInfo(BoxTaskAssignment.Info)}
     */
    @Test
    @Category(UnitTest.class)
    public void testUpdateInfoParseAllFieldsCorrectly() throws ParseException {
        final String id = "2698512";
        final String itemID = "8018809384";
        final String itemSequenceID = "0";
        final String itemEtag = "0";
        final String itemSha1 = "7840095ee096ee8297676a138d4e316eabb3ec96";
        final String itemName = "scrumworksToTrello.js";
        final String assignedToID = "1992432";
        final String assignedToName = "rhaegar@box.com";
        final String assignedToLogin = "rhaegar@box.com";
        final String message = "hello!!!";
        final Date completedAt = null;
        final Date assignedAt = BoxDateFormat.parse("2013-05-10T11:43:41-07:00");
        final Date remindedAt = null;
        final BoxTaskAssignment.ResolutionState resolutionState = BoxTaskAssignment.ResolutionState.INCOMPLETE;
        final String assignedByID = "11993747";
        final String assignedByName = "sean";
        final String assignedByLogin = "sean@box.com";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"task_assignment\",\n"
                + "    \"id\": \"2698512\",\n"
                + "    \"item\": {\n"
                + "        \"type\": \"file\",\n"
                + "        \"id\": \"8018809384\",\n"
                + "        \"sequence_id\": \"0\",\n"
                + "        \"etag\": \"0\",\n"
                + "        \"sha1\": \"7840095ee096ee8297676a138d4e316eabb3ec96\",\n"
                + "        \"name\": \"scrumworksToTrello.js\"\n"
                + "    },\n"
                + "    \"assigned_to\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"1992432\",\n"
                + "        \"name\": \"rhaegar@box.com\",\n"
                + "        \"login\": \"rhaegar@box.com\"\n"
                + "    },\n"
                + "    \"message\": \"hello!!!\",\n"
                + "    \"completed_at\": null,\n"
                + "    \"assigned_at\": \"2013-05-10T11:43:41-07:00\",\n"
                + "    \"reminded_at\": null,\n"
                + "    \"resolution_state\": \"incomplete\",\n"
                + "    \"assigned_by\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"11993747\",\n"
                + "        \"name\": \"sean\",\n"
                + "        \"login\": \"sean@box.com\"\n"
                + "    }\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxTaskAssignment assignment = new BoxTaskAssignment(api, id);
        BoxTaskAssignment.Info info = assignment.new Info();
        info.addPendingChange("message", message);
        assignment.updateInfo(info);
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(itemID, info.getItem().getID());
        Assert.assertEquals(itemSequenceID, info.getItem().getSequenceID());
        Assert.assertEquals(itemEtag, info.getItem().getEtag());
        Assert.assertEquals(itemSha1, ((BoxFile.Info) info.getItem()).getSha1());
        Assert.assertEquals(itemName, info.getItem().getName());
        Assert.assertEquals(assignedToID, info.getAssignedTo().getID());
        Assert.assertEquals(assignedToName, info.getAssignedTo().getName());
        Assert.assertEquals(assignedToLogin, info.getAssignedTo().getLogin());
        Assert.assertEquals(message, info.getMessage());
        Assert.assertEquals(completedAt, info.getCompletedAt());
        Assert.assertEquals(assignedAt, info.getAssignedAt());
        Assert.assertEquals(remindedAt, info.getRemindedAt());
        Assert.assertEquals(resolutionState, info.getResolutionState());
        Assert.assertEquals(assignedByID, info.getAssignedBy().getID());
        Assert.assertEquals(assignedByName, info.getAssignedBy().getName());
        Assert.assertEquals(assignedByLogin, info.getAssignedBy().getLogin());
    }

    /**
     * Unit test for {@link BoxTaskAssignment#updateInfo(BoxTaskAssignment.Info)}
     */
    @Test
    @Category(UnitTest.class)
    public void testDeleteSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/task_assignments/0",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        new BoxTaskAssignment(api, "0").delete();
    }
}
