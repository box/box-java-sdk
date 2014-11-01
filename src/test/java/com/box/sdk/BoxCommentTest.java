package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
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
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName);

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
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName);
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
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName);
        BoxComment.Info commentInfo = uploadedFile.addComment(originalMessage);
        BoxComment comment = commentInfo.getResource();
        commentInfo = comment.changeMessage(changedMessage);

        assertThat(commentInfo.getMessage(), is(equalTo(changedMessage)));

        uploadedFile.delete();
    }
}
