package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.CleanupTools.deleteFile;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.Test;

public class BoxCommentIT {

    @Test
    public void replyToCommentSucceeds() {
        BoxAPIConnection api = jwtApiForServiceAccount();
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

        deleteFile(uploadedFile);
    }

    @Test
    public void getCommentInfoSucceeds() {
        BoxAPIConnection api = jwtApiForServiceAccount();
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

        deleteFile(uploadedFile);
    }

    @Test
    public void changeCommentMessageSucceeds() {
        BoxAPIConnection api = jwtApiForServiceAccount();
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

        deleteFile(uploadedFile);
    }

    @Test
    public void deleteCommentSucceeds() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[deleteCommentSucceeds] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);
        String message = "Comment message";

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        BoxComment.Info commentInfo = uploadedFile.addComment(message);
        BoxComment comment = commentInfo.getResource();
        comment.delete();

        deleteFile(uploadedFile);
    }
}
