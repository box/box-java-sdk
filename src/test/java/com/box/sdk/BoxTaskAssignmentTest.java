package com.box.sdk;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;


/**
 * {@link BoxTaskAssignment} related tests.
 */
public class BoxTaskAssignmentTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(UnitTest.class)
    public void testCreateTaskAssignmentOnUserSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String assignmentID = "12345";
        final String assignedToID = "33333";
        final String policyID = "11111";
        final String assignedByLogin = "testuser@example.com";
        final String taskAssignmentURL = "/task_assignments";

        JsonObject taskObject = new JsonObject()
                .add("type", "task")
                .add("id", policyID);

        JsonObject assignedToObject = new JsonObject()
                .add("id", assignedToID);

        JsonObject assignmentObject = new JsonObject()
                .add("task", taskObject)
                .add("assign_to", assignedToObject);

        result = TestConfig.getFixture("BoxTask/CreateTaskAssignment201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(taskAssignmentURL))
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
    @Category(UnitTest.class)
    public void testGetTaskAssignmentInfoByIDSucceeds() throws IOException {
        String result = "";
        final String assignmentID = "12345";
        final String fileID = "22222";
        final String assignedToID = "33333";
        final String assignedToLogin = "testuser@example.com";
        final String assignedByID = "33333";
        final String status = "incomplete";
        final String assignmentURL = "/task_assignments/" + assignmentID;

        result = TestConfig.getFixture("BoxTask/CreateTaskAssignment201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
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
    @Category(UnitTest.class)
    public void testSetTaskOnTaskAssignmentSucceeds() throws IOException {
        String result = "";
        final String assignmentID = "12345";
        final String assignmentURL = "/task_assignments/" + assignmentID;
        final String status = "incomplete";
        JsonObject taskObject = new JsonObject()
                .add("status", status);

        result = TestConfig.getFixture("BoxTask/CreateTaskAssignment201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(assignmentURL))
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
    @Category(UnitTest.class)
    public void testGetTaskAssignmentsSucceeds() throws IOException {
        String result = "";
        final String policyID = "11111";
        final String assignmentID = "12345";
        final String fileID = "22222";
        final String assignedToID = "33333";
        final String assignedToLogin = "testuser@example.com";
        final String assignmentURL = "/tasks/" + policyID + "/assignments";

        result = TestConfig.getFixture("BoxTask/GetAllTaskAssignments200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
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
    @Category(UnitTest.class)
    public void testGetAllTaskAssignmentsSucceeds() throws IOException {
        String result = "";
        final String policyID = "11111";
        final String assignmentID = "12345";
        final String fileID = "22222";
        final String assignmentURL = "/tasks/" + policyID + "/assignments";

        result = TestConfig.getFixture("BoxTask/GetAllTaskAssignments200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
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
    @Category(UnitTest.class)
    public void testUpdateTaskAssignmentInfoSucceeds() throws IOException {
        String result = "";
        String getResult = "";
        final String policyID = "11111";
        final String assignmentID = "12345";
        final String assignedToID = "33333";
        final String assignedToLogin = "testuser@example.com";
        final String updatedMessage = "Looks good to me!";
        final String updatedState = "completed";
        final String assignmentURL = "/task_assignments/" + assignmentID;

        JsonObject updateObject = new JsonObject()
                .add("message", updatedMessage)
                .add("resolution_state", "completed");

        getResult = TestConfig.getFixture("BoxTask/GetTaskAssignment200");

        result = TestConfig.getFixture("BoxTask/UpdateATaskAssignmentInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(getResult)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(assignmentURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxTaskAssignment taskAssignment = new BoxTaskAssignment(this.api, assignmentID);
        BoxTaskAssignment.Info info = taskAssignment.getInfo();
        info.addPendingChange("resolution_state", updatedState);
        taskAssignment.updateInfo(info);

        Assert.assertEquals(assignmentID, info.getID());
        Assert.assertEquals(assignedToID, info.getAssignedTo().getID());
        Assert.assertEquals(assignedToLogin, info.getAssignedTo().getLogin());
        Assert.assertEquals(updatedMessage, info.getMessage());
        Assert.assertEquals(BoxTaskAssignment.ResolutionState.COMPLETED, info.getResolutionState());
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteTaskAssignmentSucceeds() throws IOException {
        final String assignmentID = "12345";
        final String assignmentURL = "/task_assignments/" + assignmentID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(assignmentURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        BoxTaskAssignment taskAssignment = new BoxTaskAssignment(this.api, assignmentID);
        taskAssignment.delete();
    }
}
