package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxTask} related tests.
 */
public class BoxTaskTest {

    /**
     * Unit test for {@link BoxTask#getInfo()}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/tasks/0",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        new BoxTask(api, "0").getInfo();
    }

    /**
     * Unit test for {@link BoxTask#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoSendsCorrectRequestWithFields() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/tasks/0?fields=item%2Cdue_at",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        new BoxTask(api, "0").getInfo("item", "due_at");
    }

    /**
     * Unit test for {@link BoxTask#getInfo()}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoParseAllFieldsCorrectly() throws ParseException {
        final String id = "1839355";
        final String itemID = "7287087200";
        final String itemSequenceID = "0";
        final String itemEtag = "0";
        final String itemSha1 = "0bbd79a105c504f99573e3799756debba4c760cd";
        final String itemName = "box-logo.png";
        final Date dueAt = BoxDateFormat.parse("2014-04-03T11:09:43-07:00");
        final BoxTask.Action action = BoxTask.Action.REVIEW;
        final String message = "REVIEW PLZ K THX";
        final int assignmentCount = 0;
        final boolean isCompleted = false;
        final String createdByID = "11993747";
        final String createdByName = "sean";
        final String createdByLogin = "sean@box.com";
        final Date createdAt = BoxDateFormat.parse("2013-04-03T11:12:54-07:00");

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"task\",\n"
                + "    \"id\": \"1839355\",\n"
                + "    \"item\": {\n"
                + "        \"type\": \"file\",\n"
                + "        \"id\": \"7287087200\",\n"
                + "        \"sequence_id\": \"0\",\n"
                + "        \"etag\": \"0\",\n"
                + "        \"sha1\": \"0bbd79a105c504f99573e3799756debba4c760cd\",\n"
                + "        \"name\": \"box-logo.png\"\n"
                + "    },\n"
                + "    \"due_at\": \"2014-04-03T11:09:43-07:00\",\n"
                + "    \"action\": \"review\",\n"
                + "    \"message\": \"REVIEW PLZ K THX\",\n"
                + "    \"task_assignment_collection\": {\n"
                + "        \"total_count\": 0,\n"
                + "        \"entries\": []\n"
                + "    },\n"
                + "    \"is_completed\": false,\n"
                + "    \"created_by\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"11993747\",\n"
                + "        \"name\": \"sean\",\n"
                + "        \"login\": \"sean@box.com\"\n"
                + "    },\n"
                + "    \"created_at\": \"2013-04-03T11:12:54-07:00\"\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxTask.Info info = new BoxTask(api, id).getInfo();
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(itemID, info.getItem().getID());
        Assert.assertEquals(itemSequenceID, info.getItem().getSequenceID());
        Assert.assertEquals(itemEtag, info.getItem().getEtag());
        Assert.assertEquals(itemSha1, info.getItem().getSha1());
        Assert.assertEquals(itemName, info.getItem().getName());
        Assert.assertEquals(dueAt, info.getDueAt());
        Assert.assertEquals(action, info.getAction());
        Assert.assertEquals(message, info.getMessage());
        Assert.assertEquals(assignmentCount, info.getTaskAssignments().size());
        Assert.assertEquals(isCompleted, info.isCompleted());
        Assert.assertEquals(createdByID, info.getCreatedBy().getID());
        Assert.assertEquals(createdByName, info.getCreatedBy().getName());
        Assert.assertEquals(createdByLogin, info.getCreatedBy().getLogin());
        Assert.assertEquals(createdAt, info.getCreatedAt());
    }

    /**
     * Unit test for {@link BoxTask#updateInfo(BoxTask.Info)}
     */
    @Test
    @Category(UnitTest.class)
    public void testUpdateInfoSendsCorrectJson() throws ParseException {
        final String action = "review";
        final String message = "text message";
        final Date dueAt = BoxDateFormat.parse("2016-05-09T17:41:27-07:00");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/tasks/0",
                        request.getUrl().toString());
                Assert.assertEquals(action, json.get("action").asString());
                Assert.assertEquals(message, json.get("message").asString());
                try {
                    Assert.assertEquals(dueAt, BoxDateFormat.parse(json.get("due_at").asString()));
                } catch (ParseException e) {
                    assert false;
                }
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxTask task = new BoxTask(api, "0");
        BoxTask.Info info = task.new Info();
        info.addPendingChange("message", message);
        info.addPendingChange("action", action);
        info.addPendingChange("due_at", BoxDateFormat.format(dueAt));
        task.updateInfo(info);
    }

    /**
     * Unit test for {@link BoxTask#updateInfo(BoxTask.Info)}
     */
    @Test
    @Category(UnitTest.class)
    public void testUpdateInfoParseAllFieldsCorrectly() throws ParseException {
        final String id = "1839355";
        final String itemID = "7287087200";
        final String itemSequenceID = "0";
        final String itemEtag = "0";
        final String itemSha1 = "0bbd79a105c504f99573e3799756debba4c760cd";
        final String itemName = "box-logo.png";
        final Date dueAt = BoxDateFormat.parse("2014-04-03T11:09:43-07:00");
        final BoxTask.Action action = BoxTask.Action.REVIEW;
        final String message = "REVIEW PLZ K THX";
        final int assignmentCount = 0;
        final boolean isCompleted = false;
        final String createdByID = "11993747";
        final String createdByName = "sean";
        final String createdByLogin = "sean@box.com";
        final Date createdAt = BoxDateFormat.parse("2013-04-03T11:12:54-07:00");

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"task\",\n"
                + "    \"id\": \"1839355\",\n"
                + "    \"item\": {\n"
                + "        \"type\": \"file\",\n"
                + "        \"id\": \"7287087200\",\n"
                + "        \"sequence_id\": \"0\",\n"
                + "        \"etag\": \"0\",\n"
                + "        \"sha1\": \"0bbd79a105c504f99573e3799756debba4c760cd\",\n"
                + "        \"name\": \"box-logo.png\"\n"
                + "    },\n"
                + "    \"due_at\": \"2014-04-03T11:09:43-07:00\",\n"
                + "    \"action\": \"review\",\n"
                + "    \"message\": \"REVIEW PLZ K THX\",\n"
                + "    \"task_assignment_collection\": {\n"
                + "        \"total_count\": 0,\n"
                + "        \"entries\": []\n"
                + "    },\n"
                + "    \"is_completed\": false,\n"
                + "    \"created_by\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"11993747\",\n"
                + "        \"name\": \"sean\",\n"
                + "        \"login\": \"sean@box.com\"\n"
                + "    },\n"
                + "    \"created_at\": \"2013-04-03T11:12:54-07:00\"\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxTask task = new BoxTask(api, id);
        BoxTask.Info info = task.new Info();
        info.addPendingChange("message", message);
        task.updateInfo(info);
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(itemID, info.getItem().getID());
        Assert.assertEquals(itemSequenceID, info.getItem().getSequenceID());
        Assert.assertEquals(itemEtag, info.getItem().getEtag());
        Assert.assertEquals(itemSha1, info.getItem().getSha1());
        Assert.assertEquals(itemName, info.getItem().getName());
        Assert.assertEquals(dueAt, info.getDueAt());
        Assert.assertEquals(action, info.getAction());
        Assert.assertEquals(message, info.getMessage());
        Assert.assertEquals(assignmentCount, info.getTaskAssignments().size());
        Assert.assertEquals(isCompleted, info.isCompleted());
        Assert.assertEquals(createdByID, info.getCreatedBy().getID());
        Assert.assertEquals(createdByName, info.getCreatedBy().getName());
        Assert.assertEquals(createdByLogin, info.getCreatedBy().getLogin());
        Assert.assertEquals(createdAt, info.getCreatedAt());
    }

    /**
     * Unit test for {@link BoxTask#delete()}
     */
    @Test
    @Category(UnitTest.class)
    public void testDeleteSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/tasks/0",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        new BoxTask(api, "0").delete();
    }

    /**
     * Unit test for {@link BoxTask#addAssignmentByLogin(String)}
     */
    @Test
    @Category(UnitTest.class)
    public void testAddAssignmentByLoginSendsCorrectJson() {
        final String taskType = "task";
        final String taskID = "0";
        final String assignToLogin = "login@somewhere.com";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/task_assignments",
                        request.getUrl().toString());
                Assert.assertEquals(taskType, json.get("task").asObject().get("type").asString());
                Assert.assertEquals(taskID, json.get("task").asObject().get("id").asString());
                Assert.assertEquals(assignToLogin, json.get("assign_to").asObject().get("login").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        new BoxTask(api, taskID).addAssignmentByLogin(assignToLogin);
    }

    /**
     * Unit test for {@link BoxTask#addAssignment(BoxUser)}
     */
    @Test
    @Category(UnitTest.class)
    public void testAddAssignmentSendsCorrectJson() {
        final String taskType = "task";
        final String taskID = "0";
        final String assignToID = "1";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/task_assignments",
                        request.getUrl().toString());
                Assert.assertEquals(taskType, json.get("task").asObject().get("type").asString());
                Assert.assertEquals(taskID, json.get("task").asObject().get("id").asString());
                Assert.assertEquals(assignToID, json.get("assign_to").asObject().get("id").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxUser user = new BoxUser(api, assignToID);
        new BoxTask(api, taskID).addAssignment(user);
    }

    /**
     * Unit test for {@link BoxTask#addAssignment(BoxUser)}
     */
    @Test
    @Category(UnitTest.class)
    public void testAddAssignmentParseAllFieldsCorrectly() throws ParseException {
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

        BoxUser user = new BoxUser(api, assignedToID);
        BoxTaskAssignment.Info info = new BoxTask(api, id).addAssignment(user);
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
     * Unit test for {@link BoxTask#getAllAssignments(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAllAssignmentsSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals(
                        "https://api.box.com/2.0/tasks/0/assignments?fields=item%2Cmessage&limit=100&offset=0",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"total_count\": 1, \"offset\": 0, \"entries\":[]}";
                    }
                };
            }
        });

        Iterator<BoxTaskAssignment.Info> iterator
            = new BoxTask(api, "0").getAllAssignments("item", "message").iterator();
        iterator.hasNext();
    }

    /**
     * Unit test for {@link BoxTask#getAllAssignments(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAllAssignmentsParseAllFieldsCorrectly() {
        final String firstId = "2485961";
        final String firstItemID = "7287087200";
        final String firstItemSequenceID = "0";
        final String firstItemEtag = "0";
        final String firstItemSha1 = "0bbd79a105c504f99573e3799756debba4c760cd";
        final String firstItemName = "box-logo.png";
        final String firstAssignedToID = "193425559";
        final String firstAssignedToName = "Rhaegar Targaryen";
        final String firstAssignedToLogin = "rhaegar@box.com";
        final String secondId = "2485963";
        final String secondItemID = "7287087204";
        final String secondItemSequenceID = "1";
        final String secondItemEtag = "1";
        final String secondItemSha1 = "0bbd79a105c504f99573e3799756debb";
        final String secondItemName = "box-icon.png";
        final String secondAssignedToID = "1934255";
        final String secondAssignedToName = "Tyrion Lannister";
        final String secondAssignedToLogin = "littlebig@box.com";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"total_count\": 2,\n"
                + "    \"offset\": 0,\n"
                + "    \"entries\": [\n"
                + "        {\n"
                + "            \"type\": \"task_assignment\",\n"
                + "            \"id\": \"2485961\",\n"
                + "            \"item\": {\n"
                + "                \"type\": \"file\",\n"
                + "                \"id\": \"7287087200\",\n"
                + "                \"sequence_id\": \"0\",\n"
                + "                \"etag\": \"0\",\n"
                + "                \"sha1\": \"0bbd79a105c504f99573e3799756debba4c760cd\",\n"
                + "                \"name\": \"box-logo.png\"\n"
                + "            },\n"
                + "            \"assigned_to\": {\n"
                + "                \"type\": \"user\",\n"
                + "                \"id\": \"193425559\",\n"
                + "                \"name\": \"Rhaegar Targaryen\",\n"
                + "                \"login\": \"rhaegar@box.com\"\n"
                + "            }\n"
                + "        },\n"
                + "        {\n"
                + "            \"type\": \"task_assignment\",\n"
                + "            \"id\": \"2485963\",\n"
                + "            \"item\": {\n"
                + "                \"type\": \"file\",\n"
                + "                \"id\": \"7287087204\",\n"
                + "                \"sequence_id\": \"1\",\n"
                + "                \"etag\": \"1\",\n"
                + "                \"sha1\": \"0bbd79a105c504f99573e3799756debb\",\n"
                + "                \"name\": \"box-icon.png\"\n"
                + "            },\n"
                + "            \"assigned_to\": {\n"
                + "                \"type\": \"user\",\n"
                + "                \"id\": \"1934255\",\n"
                + "                \"name\": \"Tyrion Lannister\",\n"
                + "                \"login\": \"littlebig@box.com\"\n"
                + "            }\n"
                + "        }\n"
                + "    ]\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        Iterator<BoxTaskAssignment.Info> iterator = new BoxTask(api, "0").getAllAssignments().iterator();
        BoxTaskAssignment.Info info = iterator.next();
        Assert.assertEquals(firstId, info.getID());
        Assert.assertEquals(firstItemID, info.getItem().getID());
        Assert.assertEquals(firstItemSequenceID, info.getItem().getSequenceID());
        Assert.assertEquals(firstItemEtag, info.getItem().getEtag());
        Assert.assertEquals(firstItemSha1, ((BoxFile.Info) info.getItem()).getSha1());
        Assert.assertEquals(firstItemName, info.getItem().getName());
        Assert.assertEquals(firstAssignedToID, info.getAssignedTo().getID());
        Assert.assertEquals(firstAssignedToName, info.getAssignedTo().getName());
        Assert.assertEquals(firstAssignedToLogin, info.getAssignedTo().getLogin());
        info = iterator.next();
        Assert.assertEquals(secondId, info.getID());
        Assert.assertEquals(secondItemID, info.getItem().getID());
        Assert.assertEquals(secondItemSequenceID, info.getItem().getSequenceID());
        Assert.assertEquals(secondItemEtag, info.getItem().getEtag());
        Assert.assertEquals(secondItemSha1, ((BoxFile.Info) info.getItem()).getSha1());
        Assert.assertEquals(secondItemName, info.getItem().getName());
        Assert.assertEquals(secondAssignedToID, info.getAssignedTo().getID());
        Assert.assertEquals(secondAssignedToName, info.getAssignedTo().getName());
        Assert.assertEquals(secondAssignedToLogin, info.getAssignedTo().getLogin());
        Assert.assertEquals(false, iterator.hasNext());
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[updateInfoSucceeds] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);
        String originalMessage = "Original message";
        String changedMessage = "Changed message";

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, 1);
        Date dueAt = calendar.getTime();

        BoxTask.Info taskInfo = uploadedFile.addTask(BoxTask.Action.REVIEW, originalMessage, dueAt);

        BoxTask task = taskInfo.getResource();
        taskInfo.setMessage(changedMessage);
        taskInfo.setDueAt(dueAt);
        task.updateInfo(taskInfo);

        assertThat(taskInfo.getMessage(), is(equalTo(changedMessage)));
        assertThat(taskInfo.getDueAt(), is(equalTo(dueAt)));

        uploadedFile.delete();
    }
}
