package com.box.sdk;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


/**
 * {@link BoxTaskAssignment} related tests.
 */
public class BoxTaskAssignmentTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    private final BoxAPIConnection api = new BoxAPIConnection("");

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("http://localhost:%d", wireMockRule.port()));
    }

    @Test
    public void testCreateTaskAssignmentOnUserSucceedsAndSendsCorrectJson() throws IOException {
        final String assignmentID = "12345";
        final String assignedToID = "33333";
        final String policyID = "11111";
        final String assignedByLogin = "testuser@example.com";
        final String taskAssignmentURL = "/2.0/task_assignments";

        JsonObject taskObject = new JsonObject()
            .add("type", "task")
            .add("id", policyID);

        JsonObject assignedToObject = new JsonObject()
            .add("id", assignedToID);

        JsonObject assignmentObject = new JsonObject()
            .add("task", taskObject)
            .add("assign_to", assignedToObject);

        String result = TestUtils.getFixture("BoxTask/CreateTaskAssignment201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(taskAssignmentURL))
            .withRequestBody(WireMock.equalToJson(assignmentObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxUser user = new BoxUser(this.api, assignedToID);
        BoxTask task = new BoxTask(this.api, policyID);
        BoxTaskAssignment.Info assignmentInfo = task.addAssignment(user);

        Assert.assertEquals(assignmentID, assignmentInfo.getID());
        Assert.assertEquals(assignedToID, assignmentInfo.getAssignedTo().getID());
        Assert.assertEquals(assignedByLogin, assignmentInfo.getAssignedBy().getLogin());
    }

    @Test
    public void testGetTaskAssignmentInfoByIDSucceeds() throws IOException {
        final String assignmentID = "12345";
        final String fileID = "22222";
        final String assignedToID = "33333";
        final String assignedByID = "33333";
        final String status = "incomplete";
        final String assignmentURL = "/2.0/task_assignments/" + assignmentID;

        String result = TestUtils.getFixture("BoxTask/CreateTaskAssignment201");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxTaskAssignment.Info assignmentInfo = new BoxTaskAssignment(this.api, assignmentID).getInfo();

        Assert.assertEquals(assignmentID, assignmentInfo.getID());
        Assert.assertEquals(fileID, assignmentInfo.getItem().getID());
        Assert.assertEquals(assignedToID, assignmentInfo.getAssignedTo().getID());
        Assert.assertEquals(assignedByID, assignmentInfo.getAssignedBy().getID());
        Assert.assertEquals(status, assignmentInfo.getStatus());
    }

    @Test
    public void testSetTaskOnTaskAssignmentSucceeds() throws IOException {
        final String assignmentID = "12345";
        final String assignmentURL = "/2.0/task_assignments/" + assignmentID;
        final String status = "incomplete";
        JsonObject taskObject = new JsonObject()
            .add("status", status);

        String result = TestUtils.getFixture("BoxTask/CreateTaskAssignment201");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(assignmentURL))
            .withRequestBody(WireMock.equalToJson(taskObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxTaskAssignment assignment = new BoxTaskAssignment(this.api, assignmentID);
        BoxTaskAssignment.Info assignmentInfo = assignment.getInfo();
        assignmentInfo.setStatus(status);
        assignment.updateInfo(assignmentInfo);


        Assert.assertEquals(status, assignment.getInfo().getStatus());
    }

    @Test
    public void testGetTaskAssignmentsSucceeds() throws IOException {
        final String policyID = "11111";
        final String assignmentID = "12345";
        final String fileID = "22222";
        final String assignedToID = "33333";
        final String assignedToLogin = "testuser@example.com";
        final String assignmentURL = "/2.0/tasks/" + policyID + "/assignments";

        String result = TestUtils.getFixture("BoxTask/GetAllTaskAssignments200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxTask task = new BoxTask(this.api, policyID);
        List<BoxTaskAssignment.Info> assignmentList = task.getAssignments();

        Assert.assertEquals(assignmentID, assignmentList.get(0).getID());
        Assert.assertEquals(fileID, assignmentList.get(0).getItem().getID());
        Assert.assertEquals(assignedToID, assignmentList.get(0).getAssignedTo().getID());
        Assert.assertEquals(assignedToLogin, assignmentList.get(0).getAssignedTo().getLogin());
    }

    @Test
    public void testGetAllTaskAssignmentsSucceeds() throws IOException {
        final String policyID = "11111";
        final String assignmentID = "12345";
        final String fileID = "22222";
        final String assignmentURL = "/2.0/tasks/" + policyID + "/assignments";

        String result = TestUtils.getFixture("BoxTask/GetAllTaskAssignments200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
            .withQueryParam("limit", WireMock.containing("100"))
            .withQueryParam("offset", WireMock.containing("0"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxTask task = new BoxTask(this.api, policyID);
        Iterator<BoxTaskAssignment.Info> assignmentList = task.getAllAssignments().iterator();

        BoxTaskAssignment.Info firstAssignment = assignmentList.next();

        Assert.assertEquals(assignmentID, firstAssignment.getID());
        Assert.assertEquals(fileID, firstAssignment.getItem().getID());
    }

    @Test
    public void testUpdateTaskAssignmentInfoSucceeds() throws IOException {
        final String assignmentID = "12345";
        final String assignedToID = "33333";
        final String assignedToLogin = "testuser@example.com";
        final String updatedMessage = "Looks good to me!";
        final BoxTaskAssignment.ResolutionState updatedState = BoxTaskAssignment.ResolutionState.COMPLETED;
        final String assignmentURL = "/2.0/task_assignments/" + assignmentID;

        String getResult = TestUtils.getFixture("BoxTask/GetTaskAssignment200");

        String result = TestUtils.getFixture("BoxTask/UpdateATaskAssignmentInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(getResult)));

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(assignmentURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxTaskAssignment taskAssignment = new BoxTaskAssignment(this.api, assignmentID);
        BoxTaskAssignment.Info info = taskAssignment.getInfo();
        info.setResolutionState(updatedState);
        info.setMessage(updatedMessage);
        taskAssignment.updateInfo(info);

        Assert.assertEquals(assignmentID, info.getID());
        Assert.assertEquals(assignedToID, info.getAssignedTo().getID());
        Assert.assertEquals(assignedToLogin, info.getAssignedTo().getLogin());
        Assert.assertEquals(updatedMessage, info.getMessage());
        Assert.assertEquals(BoxTaskAssignment.ResolutionState.COMPLETED, info.getResolutionState());
    }

    @Test
    public void testDeleteTaskAssignmentSucceeds() {
        final String assignmentID = "12345";
        final String assignmentURL = "/2.0/task_assignments/" + assignmentID;

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(assignmentURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(204)));

        BoxTaskAssignment taskAssignment = new BoxTaskAssignment(this.api, assignmentID);
        taskAssignment.delete();
    }
}
