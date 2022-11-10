package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.CleanupTools.deleteFile;
import static com.box.sdk.UniqueTestFolder.getUniqueFolder;
import static com.box.sdk.UniqueTestFolder.randomizeName;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoxCommentIT {

    @BeforeClass
    public static void setup() {
        setupUniqeFolder();
    }

    @AfterClass
    public static void tearDown() {
        removeUniqueFolder();
    }
    @Test
    public void replyToCommentSucceeds() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        BoxFolder rootFolder = getUniqueFolder(api);
        String fileName = randomizeName("[replyToCommentSucceeds] Test File.txt");
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);
        String firstMessage = "First message";
        String replyMessage = "Reply message";

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        try {
            BoxComment.Info firstCommentInfo = uploadedFile.addComment(firstMessage);
            BoxComment firstComment = firstCommentInfo.getResource();

            BoxComment.Info replyCommentInfo = firstComment.reply(replyMessage);

            assertThat(replyCommentInfo.getMessage(), is(equalTo(replyMessage)));
            assertThat(replyCommentInfo.getIsReplyComment(), is(true));
            assertThat(replyCommentInfo.getItem().getID(), is(equalTo(uploadedFile.getID())));
        } finally {
            deleteFile(uploadedFile);
        }
    }

    @Test
    public void getCommentInfoSucceeds() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        BoxFolder rootFolder = getUniqueFolder(api);
        String fileName = randomizeName("[getCommentInfoSucceeds] Test File.txt");
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);
        String message = "Comment message";

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        try {
            BoxComment.Info commentInfo = uploadedFile.addComment(message);
            BoxComment comment = commentInfo.getResource();
            commentInfo = comment.getInfo();

            assertThat(commentInfo.getMessage(), is(equalTo(message)));
            assertThat(commentInfo.getItem().getID(), is(equalTo(uploadedFile.getID())));
        } finally {
            deleteFile(uploadedFile);
        }
    }

    @Test
    public void changeCommentMessageSucceeds() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        BoxFolder rootFolder = getUniqueFolder(api);
        String fileName = randomizeName("[changeCommentMessageSucceeds] Test File.txt");
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);
        String originalMessage = "Original message";
        String changedMessage = "Changed message";

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        try {
            BoxComment.Info commentInfo = uploadedFile.addComment(originalMessage);
            BoxComment comment = commentInfo.getResource();
            commentInfo = comment.changeMessage(changedMessage);

            assertThat(commentInfo.getMessage(), is(equalTo(changedMessage)));
        } finally {
            deleteFile(uploadedFile);
        }
    }

    @Test
    public void deleteCommentSucceeds() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        BoxFolder rootFolder = getUniqueFolder(api);
        String fileName = randomizeName("[deleteCommentSucceeds] Test File.txt");
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);
        String message = "Comment message";

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        try {
            BoxComment.Info commentInfo = uploadedFile.addComment(message);
            BoxComment comment = commentInfo.getResource();
            comment.delete();
        } finally {
            deleteFile(uploadedFile);
        }
    }
}
