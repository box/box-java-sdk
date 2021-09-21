package com.box.sdk;

import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * {@link BoxTask} related unit tests.
 */
public class BoxTaskTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private final BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    public void testCreateTaskSucceeds() throws IOException {
        final String taskID = "12345";
        final String fileID = "1111";
        final String taskURL = "/tasks";
        final String taskMessage = "Please Review";
        final String createdByLogin = "test@user.com";
        Date dueAt = new Date();

        String result = TestConfig.getFixture("BoxTask/CreateTaskOnFile201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(taskURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxTask.Info taskInfo = file.addTask(BoxTask.Action.REVIEW, taskMessage, dueAt,
            BoxTask.CompletionRule.ALL_ASSIGNEES);

        assertEquals(taskID, taskInfo.getID());
        assertEquals(fileID, taskInfo.getItem().getID());
        assertEquals(taskMessage, taskInfo.getMessage());
        assertEquals("all_assignees", taskInfo.getCompletionRule());
        assertEquals(createdByLogin, taskInfo.getCreatedBy().getLogin());
    }

    @Test
    public void testGetATaskOnFileSucceeds() throws IOException {
        final String taskID = "12345";
        final String fileID = "1111";
        final String fileName = "Sample.pdf";
        final String message = "Please Review";
        final String createdByID = "2222";
        final String createdByLogin = "test@user.com";
        final String taskURL = "/tasks/" + taskID;

        String result = TestConfig.getFixture("BoxTask/GetATaskOnFile200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(taskURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxTask task = new BoxTask(this.api, taskID);
        BoxTask.Info info = task.getInfo();

        assertEquals(taskID, info.getID());
        assertEquals(fileID, info.getItem().getID());
        assertEquals(fileName, info.getItem().getName());
        assertEquals(message, info.getMessage());
        assertEquals(createdByID, info.getCreatedBy().getID());
        assertEquals(createdByLogin, info.getCreatedBy().getLogin());
    }

    @Test
    public void testGetAllTasksOnFileSucceeds() throws IOException {
        final String taskID = "12345";
        final String fileID = "1111";
        final String fileName = "Sample.pdf";
        final String taskURL = "/files/" + fileID + "/tasks";

        String result = TestConfig.getFixture("BoxTask/GetAllTasksOnFile200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(taskURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        List<BoxTask.Info> tasks = file.getTasks();

        assertEquals(taskID, tasks.get(0).getID());
        assertEquals(fileID, tasks.get(0).getItem().getID());
        assertEquals(fileName, tasks.get(0).getItem().getName());
    }

    @Test
    public void testCreateTaskWithActionCompleteSucceeds() throws IOException {
        final String fileID = "1111";
        final String taskID = "12345";
        final String taskURL = "/tasks";
        final String taskMessage = "New Message";

        JsonObject fileObject = new JsonObject()
            .add("type", "file")
            .add("id", "1111");

        JsonObject object = new JsonObject()
            .add("item", fileObject)
            .add("action", "complete")
            .add("message", taskMessage)
            .add("completion_rule", "all_assignees");

        String result = TestConfig.getFixture("BoxTask/CreateATaskWithActionComplete200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(taskURL))
            .withRequestBody(WireMock.containing(object.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(201)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxTask.Info taskInfo = file.addTask(BoxTask.Action.COMPLETE, taskMessage, null,
            BoxTask.CompletionRule.ALL_ASSIGNEES);

        assertEquals(BoxTask.Action.COMPLETE.toString().toLowerCase(), taskInfo.getTaskType());
        assertEquals(fileID, taskInfo.getItem().getID());
        assertEquals(taskID, taskInfo.getID());
        assertEquals(taskMessage, taskInfo.getMessage());
    }

    @Test
    public void testDeleteATaskSucceeds() {
        final String taskID = "12345";
        final String taskURL = "/tasks/" + taskID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(taskURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(204)));

        BoxTask task = new BoxTask(this.api, taskID);
        task.delete();
    }

    @Test
    public void testUpdateTaskInfoSucceedsAndSendsCorrectJson() throws IOException {
        final String taskID = "12345";
        final String fileID = "1111";
        final String taskMessage = "New Message";
        final String createdByLogin = "test@user.com";
        final String taskURL = "/tasks/" + taskID;

        String result = TestConfig.getFixture("BoxTask/UpdateATaskInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(taskURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxTask task = new BoxTask(this.api, taskID);
        BoxTask.Info info = task.new Info();
        info.setMessage(taskMessage);
        info.setCompletionRule(BoxTask.CompletionRule.ALL_ASSIGNEES);
        task.updateInfo(info);

        assertEquals(taskID, info.getID());
        assertEquals(fileID, info.getItem().getID());
        assertEquals(taskMessage, info.getMessage());
        assertEquals("all_assignees", info.getCompletionRule());
        assertEquals(createdByLogin, info.getCreatedBy().getLogin());
    }

    @Test
    public void addTaskParsesCorrectly() throws IOException {
        final String taskID = "12345";
        final String taskURL = "/tasks/" + taskID;

        String result = TestConfig.getFixture("BoxTask/GetTaskInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(taskURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxTask task = new BoxTask(this.api, taskID);
        BoxTask.Info taskInfo = task.getInfo();

        assertEquals("review_random_string", taskInfo.getTaskType());
        assertEquals("Please Review", taskInfo.getMessage());
    }
}
