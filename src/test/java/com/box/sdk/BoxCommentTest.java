package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Assert;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.ClassRule;

import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxCommentTest {

    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(IntegrationTest.class)
    public void replyToCommentSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[replyToCommentSucceeds] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);
        String firstMessage = "First message";
        String replyMessage = "Reply message";

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        BoxComment.Info firstCommentInfo = uploadedFile.addComment(firstMessage);
        BoxComment firstComment = firstCommentInfo.getResource();

        BoxComment.Info replyCommentInfo = firstComment.reply(replyMessage);

        assertThat(replyCommentInfo.getMessage(), is(equalTo(replyMessage)));
        assertThat(replyCommentInfo.getIsReplyComment(), is(true));
        assertThat(replyCommentInfo.getItem().getID(), is(equalTo(uploadedFile.getID())));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void getCommentInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[getCommentInfoSucceeds] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);
        String message = "Comment message";

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        BoxComment.Info commentInfo = uploadedFile.addComment(message);
        BoxComment comment = commentInfo.getResource();
        commentInfo = comment.getInfo();

        assertThat(commentInfo.getMessage(), is(equalTo(message)));
        assertThat(commentInfo.getItem().getID(), is(equalTo(uploadedFile.getID())));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void changeCommentMessageSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[changeCommentMessageSucceeds] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);
        String originalMessage = "Original message";
        String changedMessage = "Changed message";

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        BoxComment.Info commentInfo = uploadedFile.addComment(originalMessage);
        BoxComment comment = commentInfo.getResource();
        commentInfo = comment.changeMessage(changedMessage);

        assertThat(commentInfo.getMessage(), is(equalTo(changedMessage)));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void deleteCommentSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[deleteCommentSucceeds] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);
        String message = "Comment message";

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        BoxComment.Info commentInfo = uploadedFile.addComment(message);
        BoxComment comment = commentInfo.getResource();
        comment.delete();

        uploadedFile.delete();
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteACommentSucceeds() throws IOException {
        String result = "";
        final String commentID = "12345";
        final String deleteCommentURL = "/comments/" + commentID;

        result = TestConfig.getFixture("BoxComment/UpdateCommentsMessage200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteCommentURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        new BoxComment(this.api, commentID).delete();
    }

    @Test
    @Category(UnitTest.class)
    public void testChangeACommentsMessageSucceedsAndSendCorrectJson() throws IOException, InterruptedException {
        String result = "";
        final String commentID = "12345";
        final String changeCommentURL = "/comments/" + commentID;
        final String updatedMessage = "This is an updated message.";

        JsonObject updateCommentObject = new JsonObject()
                .add("message", updatedMessage);

        result = TestConfig.getFixture("BoxComment/UpdateCommentsMessage200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(changeCommentURL))
                .withRequestBody(WireMock.equalToJson(updateCommentObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Thread.sleep(5000);

        BoxComment comment = new BoxComment(this.api, commentID);
        BoxComment.Info commentInfo = comment.changeMessage(updatedMessage);

        Assert.assertEquals(updatedMessage, commentInfo.getMessage());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateCommentSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String createCommentURL = "/comments";
        final String fileID = "2222";
        final String commentID = "12345";
        final String testCommentMesssage =  "This is a test message.";
        final String createdByLogin = "test@user.com";

        JsonObject itemObject = new JsonObject()
                .add("type", "file")
                .add("id", fileID);

        JsonObject postCommentObject = new JsonObject()
                .add("item", itemObject)
                .add("message", testCommentMesssage);

        result = TestConfig.getFixture("BoxComment/CreateComment201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(createCommentURL))
                .withRequestBody(WireMock.equalToJson(postCommentObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxComment.Info commentInfo = file.addComment(testCommentMesssage);

        Assert.assertFalse(commentInfo.getIsReplyComment());
        Assert.assertEquals(commentID, commentInfo.getID());
        Assert.assertEquals(testCommentMesssage, commentInfo.getMessage());
        Assert.assertEquals(createdByLogin, commentInfo.getCreatedBy().getLogin());
        Assert.assertEquals(fileID, commentInfo.getItem().getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetCommentsOnFileSucceeds() throws  IOException {
        String result = "";
        final String fileID = "12345";
        final String fileCommentURL = "/files/" + fileID + "/comments";
        final String firstCommentMessage = "@Test User default comment.";
        final String firstCommentID = "1111";
        final String firstCommentCreatedByLogin = "example@user.com";
        final String secondCommentMessage = "@Example User This works.";
        final String secondCommentID = "2222";
        final String secondCommentCreatedByLogin = "test@user.com";

        result = TestConfig.getFixture("BoxComment/GetCommentsOnFile200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileCommentURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        List<BoxComment.Info> comments = file.getComments();
        BoxComment.Info firstComment = comments.get(0);
        BoxComment.Info secondComment = comments.get(1);

        Assert.assertEquals(3, comments.size());
        Assert.assertEquals(firstCommentMessage, firstComment.getMessage());
        Assert.assertEquals(firstCommentID, firstComment.getID());
        Assert.assertEquals(firstCommentCreatedByLogin, firstComment.getCreatedBy().getLogin());
        Assert.assertEquals(secondCommentMessage, secondComment.getMessage());
        Assert.assertEquals(secondCommentID, secondComment.getID());
        Assert.assertEquals(secondCommentCreatedByLogin, secondComment.getCreatedBy().getLogin());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetCommentInfoSucceeds() throws IOException {
        String result = "";
        final String commentID = "12345";
        final String getCommentURL = "/comments/" + commentID;
        final String commentMessage = "@Test User  yes";
        final String createdByName = "Example User";
        final String itemID = "2222";

        result = TestConfig.getFixture("BoxComment/GetCommentInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getCommentURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxComment.Info commentInfo = new BoxComment(this.api, commentID).getInfo();

        Assert.assertFalse(commentInfo.getIsReplyComment());
        Assert.assertEquals(commentMessage, commentInfo.getMessage());
        Assert.assertEquals(commentID, commentInfo.getID());
        Assert.assertEquals(createdByName, commentInfo.getCreatedBy().getName());
        Assert.assertEquals(itemID, commentInfo.getItem().getID());
    }
}
