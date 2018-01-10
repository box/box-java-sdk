package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.eclipsesource.json.JsonObject;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.experimental.categories.Category;


public class BoxCommentTest {
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
    public void testGetCommentInfoIncludesModifiedAt() {

        BoxAPIConnection api = new BoxAPIConnection("");
        final String commentID = "1234";
        final JsonObject commentObj = new JsonObject();
        commentObj.add("id", commentID);
        commentObj.add("type", "comment");
        commentObj.add("modified_at", "1988-11-18T11:18:00-0600");

        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals(
                        "https://api.box.com/2.0/comments/" + commentID,
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return commentObj.toString();
                    }
                };
            }
        });

        BoxComment comment = new BoxComment(api, commentID);
        Date modifiedAtDate = comment.getInfo().getModifiedAt();

        assertEquals("18 Nov 1988 17:18:00 GMT", modifiedAtDate.toGMTString());
    }
}
