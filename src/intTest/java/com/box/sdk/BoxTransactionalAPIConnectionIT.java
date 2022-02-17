package com.box.sdk;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("Setup JWT connection")
public class BoxTransactionalAPIConnectionIT {
    @Test
    public void successfullyCreatesTransactionalConnection() {
        final String transactionalAccessToken = TestConfig.getTransactionalAccessToken();

        BoxAPIConnection transactionConnection = BoxTransactionalAPIConnection
            .getTransactionConnection(transactionalAccessToken, "item_preview");
        assertThat(transactionConnection.getAccessToken(), is(notNullValue()));
    }

    @Test
    public void successfullyCreatesEmbedLinkWithTransactionalConnection() {
        final String transactionalAccessToken = TestConfig.getTransactionalAccessToken();

        BoxAPIConnection api = new BoxAPIConnection(transactionalAccessToken);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[successfullyCreatesEmbedLinkWithTransactionalConnection] Test File.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFileOne = rootFolder.uploadFile(uploadStream, fileName).getResource();
        BoxFile uploadedFileTwo = rootFolder.uploadFile(uploadStream, fileName).getResource();

        BoxAPIConnection transactionConnection = BoxTransactionalAPIConnection
            .getTransactionConnection(transactionalAccessToken, "item_preview");

        // Transactional connection should be able to generate preview
        // link for any file since it is not resource scoped
        BoxFile transactionalFileOne = new BoxFile(transactionConnection, uploadedFileOne.getID());
        URL transactionalFileOnePreviewLink = transactionalFileOne.getPreviewLink();

        assertThat(transactionalFileOnePreviewLink, is(notNullValue()));
        assertThat(transactionalFileOnePreviewLink.toString(), not(is(emptyOrNullString())));

        BoxFile transactionalFileTwo = new BoxFile(transactionConnection, uploadedFileTwo.getID());
        URL transactionalFileTwoPreviewLink = transactionalFileTwo.getPreviewLink();

        assertThat(transactionalFileTwoPreviewLink, is(notNullValue()));
        assertThat(transactionalFileTwoPreviewLink.toString(), not(is(emptyOrNullString())));
    }

    @Test
    public void successfullyCreatesEmbedLinkWithResourceScopedTransactionalConnection() {
        final String transactionalAccessToken = TestConfig.getTransactionalAccessToken();

        BoxAPIConnection api = new BoxAPIConnection(transactionalAccessToken);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[successfullyCreatesEmbedLinkWithTransactionalConnection] Test File.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        BoxAPIConnection transactionConnection = BoxTransactionalAPIConnection
            .getTransactionConnection(
                transactionalAccessToken,
                "item_preview",
                "https://api.box.com/2.0/files/" + uploadedFile.getID()
            );

        BoxFile transactionalFile = new BoxFile(transactionConnection, uploadedFile.getID());
        URL transactionalFilePreviewLink = transactionalFile.getPreviewLink();

        assertThat(transactionalFilePreviewLink, is(notNullValue()));
        assertThat(transactionalFilePreviewLink.toString(), not(is(emptyOrNullString())));
    }

    @Test(expected = BoxAPIException.class)
    public void throwsWhenAttemptingToCreatesEmbedLinkWithAnotherFilesResourceScopedTransactionalConnection() {
        final String transactionalAccessToken = TestConfig.getTransactionalAccessToken();

        BoxAPIConnection api = new BoxAPIConnection(transactionalAccessToken);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[successfullyCreatesEmbedLinkWithTransactionalConnection] Test File.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFileOne = rootFolder.uploadFile(uploadStream, fileName).getResource();
        BoxFile uploadedFileTwo = rootFolder.uploadFile(uploadStream, fileName).getResource();

        BoxAPIConnection transactionConnection = BoxTransactionalAPIConnection
            .getTransactionConnection(
                transactionalAccessToken,
                "item_preview",
                "https://api.box.com/2.0/files/" + uploadedFileOne.getID()
            );

        BoxFile transactionalFileTwo = new BoxFile(transactionConnection, uploadedFileTwo.getID());
        transactionalFileTwo.getPreviewLink();
    }
}
