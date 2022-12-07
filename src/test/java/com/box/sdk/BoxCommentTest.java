package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BoxCommentTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("https://localhost:%d", wireMockRule.httpsPort()));
    }

    @Test
    public void testDeleteACommentSucceeds() {
        final String commentID = "12345";
        final String deleteCommentURL = "/2.0/comments/" + commentID;

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteCommentURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(204)));

        new BoxComment(this.api, commentID).delete();
    }

    @Test
    public void testChangeACommentsMessageSucceedsAndSendCorrectJson() {
        final String commentID = "12345";
        final String changeCommentURL = "/2.0/comments/" + commentID;
        final String updatedMessage = "This is an updated message.";

        JsonObject updateCommentObject = new JsonObject()
            .add("message", updatedMessage);

        String result = TestUtils.getFixture("BoxComment/UpdateCommentsMessage200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(changeCommentURL))
            .withRequestBody(WireMock.equalToJson(updateCommentObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxComment comment = new BoxComment(this.api, commentID);
        BoxComment.Info commentInfo = comment.changeMessage(updatedMessage);

        assertEquals(updatedMessage, commentInfo.getMessage());
    }

    @Test
    public void testCreateCommentSucceedsAndSendsCorrectJson() {
        final String createCommentURL = "/2.0/comments";
        final String fileID = "2222";
        final String commentID = "12345";
        final String testCommentMesssage = "This is a test message.";
        final String createdByLogin = "test@user.com";

        JsonObject itemObject = new JsonObject()
            .add("type", "file")
            .add("id", fileID);

        JsonObject postCommentObject = new JsonObject()
            .add("item", itemObject)
            .add("message", testCommentMesssage);

        String result = TestUtils.getFixture("BoxComment/CreateComment201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(createCommentURL))
            .withRequestBody(WireMock.equalToJson(postCommentObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxComment.Info commentInfo = file.addComment(testCommentMesssage);

        assertFalse(commentInfo.getIsReplyComment());
        assertEquals(commentID, commentInfo.getID());
        assertEquals(testCommentMesssage, commentInfo.getMessage());
        assertEquals(createdByLogin, commentInfo.getCreatedBy().getLogin());
        assertEquals(fileID, commentInfo.getItem().getID());
    }

    @Test
    public void testGetCommentsOnFileSucceeds() {
        final String fileID = "12345";
        final String fileCommentURL = "/2.0/files/" + fileID + "/comments";
        final String firstCommentMessage = "@Test User default comment.";
        final String firstCommentID = "1111";
        final String firstCommentCreatedByLogin = "example@user.com";
        final String secondCommentMessage = "@Example User This works.";
        final String secondCommentID = "2222";
        final String secondCommentCreatedByLogin = "test@user.com";

        String result = TestUtils.getFixture("BoxComment/GetCommentsOnFile200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileCommentURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        List<BoxComment.Info> comments = file.getComments();
        BoxComment.Info firstComment = comments.get(0);
        BoxComment.Info secondComment = comments.get(1);

        assertEquals(3, comments.size());
        assertEquals(firstCommentMessage, firstComment.getMessage());
        assertEquals(firstCommentID, firstComment.getID());
        assertEquals(firstCommentCreatedByLogin, firstComment.getCreatedBy().getLogin());
        assertEquals(secondCommentMessage, secondComment.getMessage());
        assertEquals(secondCommentID, secondComment.getID());
        assertEquals(secondCommentCreatedByLogin, secondComment.getCreatedBy().getLogin());
    }

    @Test
    public void testGetCommentInfoSucceeds() {
        final String commentID = "12345";
        final String getCommentURL = "/2.0/comments/" + commentID;
        final String commentMessage = "@Test User  yes";
        final String createdByName = "Example User";
        final String itemID = "2222";

        String result = TestUtils.getFixture("BoxComment/GetCommentInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(getCommentURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxComment.Info commentInfo = new BoxComment(this.api, commentID).getInfo();

        assertFalse(commentInfo.getIsReplyComment());
        assertEquals(commentMessage, commentInfo.getMessage());
        assertEquals(commentID, commentInfo.getID());
        assertEquals(createdByName, commentInfo.getCreatedBy().getName());
        assertEquals(itemID, commentInfo.getItem().getID());
    }
}
