package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/** {@link BoxTask} related unit tests. */
public class BoxTaskTest {

  @Rule
  public WireMockRule wireMockRule =
      new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));

  private final BoxAPIConnection api = TestUtils.getAPIConnection();

  @Before
  public void setUpBaseUrl() {
    api.setMaxRetryAttempts(1);
    api.setBaseURL(format("https://localhost:%d", wireMockRule.httpsPort()));
  }

  @Test
  public void testCreateTaskSucceeds() {
    final String taskID = "12345";
    final String fileID = "1111";
    final String taskURL = "/2.0/tasks";
    final String taskMessage = "Please Review";
    final String createdByLogin = "test@user.com";
    Date dueAt = new Date();

    String result = TestUtils.getFixture("BoxTask/CreateTaskOnFile201");

    wireMockRule.stubFor(
        WireMock.post(WireMock.urlPathEqualTo(taskURL))
            .willReturn(
                WireMock.aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withBody(result)));

    BoxFile file = new BoxFile(this.api, fileID);
    BoxTask.Info taskInfo =
        file.addTask(
            BoxTask.Action.REVIEW, taskMessage, dueAt, BoxTask.CompletionRule.ALL_ASSIGNEES);

    assertEquals(taskID, taskInfo.getID());
    assertEquals(fileID, taskInfo.getItem().getID());
    assertEquals(taskMessage, taskInfo.getMessage());
    assertEquals("all_assignees", taskInfo.getCompletionRule());
    assertEquals(createdByLogin, taskInfo.getCreatedBy().getLogin());
  }

  @Test
  public void testGetATaskOnFileSucceeds() {
    final String taskID = "12345";
    final String fileID = "1111";
    final String fileName = "Sample.pdf";
    final String message = "Please Review";
    final String createdByID = "2222";
    final String createdByLogin = "test@user.com";
    final String taskURL = "/2.0/tasks/" + taskID;

    String result = TestUtils.getFixture("BoxTask/GetATaskOnFile200");

    wireMockRule.stubFor(
        WireMock.get(WireMock.urlPathEqualTo(taskURL))
            .willReturn(
                WireMock.aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
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
  public void testGetAllTasksOnFileSucceeds() {
    final String taskID = "12345";
    final String fileID = "1111";
    final String fileName = "Sample.pdf";
    final String taskURL = "/2.0/files/" + fileID + "/tasks";

    String result = TestUtils.getFixture("BoxTask/GetAllTasksOnFile200");

    wireMockRule.stubFor(
        WireMock.get(WireMock.urlPathEqualTo(taskURL))
            .willReturn(
                WireMock.aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withBody(result)));

    BoxFile file = new BoxFile(this.api, fileID);
    List<BoxTask.Info> tasks = file.getTasks();

    assertEquals(taskID, tasks.get(0).getID());
    assertEquals(fileID, tasks.get(0).getItem().getID());
    assertEquals(fileName, tasks.get(0).getItem().getName());
  }

  @Test
  public void testCreateTaskWithActionCompleteSucceeds() {
    final String fileID = "1111";
    final String taskID = "12345";
    final String taskURL = "/2.0/tasks";
    final String taskMessage = "New Message";

    JsonObject fileObject = new JsonObject().add("type", "file").add("id", "1111");

    JsonObject object =
        new JsonObject()
            .add("item", fileObject)
            .add("action", "complete")
            .add("message", taskMessage)
            .add("completion_rule", "all_assignees");

    String result = TestUtils.getFixture("BoxTask/CreateATaskWithActionComplete200");

    wireMockRule.stubFor(
        WireMock.post(WireMock.urlPathEqualTo(taskURL))
            .withRequestBody(WireMock.containing(object.toString()))
            .willReturn(
                WireMock.aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withStatus(201)
                    .withBody(result)));

    BoxFile file = new BoxFile(this.api, fileID);
    BoxTask.Info taskInfo =
        file.addTask(
            BoxTask.Action.COMPLETE, taskMessage, null, BoxTask.CompletionRule.ALL_ASSIGNEES);

    assertEquals(
        BoxTask.Action.COMPLETE.toString().toLowerCase(java.util.Locale.ROOT),
        taskInfo.getTaskType());
    assertEquals(fileID, taskInfo.getItem().getID());
    assertEquals(taskID, taskInfo.getID());
    assertEquals(taskMessage, taskInfo.getMessage());
  }

  @Test
  public void testDeleteATaskSucceeds() {
    final String taskID = "12345";
    final String taskURL = "/2.0/tasks/" + taskID;

    wireMockRule.stubFor(
        WireMock.delete(WireMock.urlPathEqualTo(taskURL))
            .willReturn(
                WireMock.aResponse().withHeader("Content-Type", APPLICATION_JSON).withStatus(204)));

    BoxTask task = new BoxTask(this.api, taskID);
    task.delete();
  }

  @Test
  public void testUpdateTaskInfoSucceedsAndSendsCorrectJson() {
    final String taskID = "12345";
    final String fileID = "1111";
    final String taskMessage = "New Message";
    final String createdByLogin = "test@user.com";
    final String taskURL = "/2.0/tasks/" + taskID;

    String result = TestUtils.getFixture("BoxTask/UpdateATaskInfo200");

    wireMockRule.stubFor(
        WireMock.put(WireMock.urlPathEqualTo(taskURL))
            .willReturn(
                WireMock.aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
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
  public void addTaskParsesCorrectly() {
    final String taskID = "12345";
    final String taskURL = "/2.0/tasks/" + taskID;

    String result = TestUtils.getFixture("BoxTask/GetTaskInfo200");

    wireMockRule.stubFor(
        WireMock.get(WireMock.urlPathEqualTo(taskURL))
            .willReturn(
                WireMock.aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withBody(result)));

    BoxTask task = new BoxTask(this.api, taskID);
    BoxTask.Info taskInfo = task.getInfo();

    assertEquals("review_random_string", taskInfo.getTaskType());
    assertEquals("Please Review", taskInfo.getMessage());
  }
}
