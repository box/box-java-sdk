package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxTask} related tests.
 */
public class BoxTaskTest {

    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(53620);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

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

    @Test
    @Category(UnitTest.class)
    public void testCreateTaskSucceeds() throws IOException {
        String result = "";
        final String taskID = "12345";
        final String fileID = "1111";
        final String taskURL = "/tasks";
        final String taskMessage = "Please Review";
        final String taskAction = "review";
        final String createdByLogin = "test@user.com";
        Date dueAt = new Date();

        result = TestConfig.getFixture("BoxTask/CreateTaskOnFile201");

        this.wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(taskURL))
           .willReturn(WireMock.aResponse()
                   .withHeader("Content-Type", "application/json")
                   .withBody(result)));

        BoxFile file = new BoxFile(api, fileID);
        BoxTask.Info taskInfo = file.addTask(BoxTask.Action.REVIEW, taskMessage, dueAt);

        Assert.assertEquals(taskID, taskInfo.getID());
        Assert.assertEquals(fileID, taskInfo.getItem().getID());
        Assert.assertEquals(taskMessage, taskInfo.getMessage());
        Assert.assertEquals(createdByLogin, taskInfo.getCreatedBy().getLogin());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetATaskOnFileSucceeds() throws IOException {
        String result = "";
        final String taskID = "12345";
        final String fileID = "1111";
        final String fileName = "Sample.pdf";
        final String message = "Please Review";
        final String createdByID = "2222";
        final String createdByLogin = "test@user.com";
        final String taskURL = "/tasks/" + taskID;

        result = TestConfig.getFixture("BoxTask/GetATaskOnFile200");

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(taskURL))
           .willReturn(WireMock.aResponse()
                   .withHeader("Content-Type", "application/json")
                   .withBody(result)));

        BoxTask task = new BoxTask(api, taskID);
        BoxTask.Info info = task.getInfo();

        Assert.assertEquals(taskID, info.getID());
        Assert.assertEquals(fileID, info.getItem().getID());
        Assert.assertEquals(fileName, info.getItem().getName());
        Assert.assertEquals(message, info.getMessage());
        Assert.assertEquals(createdByID, info.getCreatedBy().getID());
        Assert.assertEquals(createdByLogin, info.getCreatedBy().getLogin());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllTasksOnFileSucceeds() throws IOException {
        String result = "";
        final String taskID = "12345";
        final String fileID = "1111";
        final String fileName = "Sample.pdf";
        final String taskURL = "/files/" + fileID + "/tasks";

        result = TestConfig.getFixture("BoxTask/GetAllTasksOnFile200");

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(taskURL))
           .willReturn(WireMock.aResponse()
                   .withHeader("Content-Type", "application/json")
                   .withBody(result)));

        BoxFile file = new BoxFile(api, fileID);
        List<BoxTask.Info> tasks = file.getTasks();

        Assert.assertEquals(taskID, tasks.get(0).getID());
        Assert.assertEquals(fileID, tasks.get(0).getItem().getID());
        Assert.assertEquals(fileName, tasks.get(0).getItem().getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteATaskSucceeds() {
        final String taskID = "12345";
        final String taskURL = "/tasks/" + taskID;

        this.wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(taskURL))
           .willReturn(WireMock.aResponse()
                   .withHeader("Content-Type", "application/json")
                   .withStatus(204)));

        BoxTask task = new BoxTask(api, taskID);
        task.delete();
    }

    @Test
    @Category(UnitTest.class)
    public void testUpdateTaskInfoSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String taskID = "12345";
        final String fileID = "1111";
        final String taskMessage = "New Message";
        final String createdByLogin = "test@user.com";
        final String taskURL = "/tasks/" + taskID;

        result = TestConfig.getFixture("BoxTask/UpdateATaskInfo200");

        this.wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(taskURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxTask task = new BoxTask(api, taskID);
        BoxTask.Info info = task.new Info();
        info.setMessage(taskMessage);
        task.updateInfo(info);

        Assert.assertEquals(taskID, info.getID());
        Assert.assertEquals(fileID, info.getItem().getID());
        Assert.assertEquals(taskMessage, info.getMessage());
        Assert.assertEquals(createdByLogin, info.getCreatedBy().getLogin());
    }
}
