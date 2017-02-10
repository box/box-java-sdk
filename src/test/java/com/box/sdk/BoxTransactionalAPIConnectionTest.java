package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxTransactionalAPIConnectionTest {
    @Test
    @Category(IntegrationTestJWT.class)
    public void successfullyCreatesTransactionalConnection() {
        final String transactionalAccessToken = TestConfig.getTransactionalAccessToken();

        BoxAPIConnection transactionConnection = BoxTransactionalAPIConnection
                .getTransactionConnection(transactionalAccessToken, "item_preview");
        assertThat(transactionConnection.getAccessToken(), is(notNullValue()));
    }

    @Test
    @Category(IntegrationTestJWT.class)
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
        assertThat(transactionalFileOnePreviewLink.toString(), not(isEmptyOrNullString()));

        BoxFile transactionalFileTwo = new BoxFile(transactionConnection, uploadedFileTwo.getID());
        URL transactionalFileTwoPreviewLink = transactionalFileTwo.getPreviewLink();

        assertThat(transactionalFileTwoPreviewLink, is(notNullValue()));
        assertThat(transactionalFileTwoPreviewLink.toString(), not(isEmptyOrNullString()));
    }

    @Test
    @Category(IntegrationTestJWT.class)
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
        assertThat(transactionalFilePreviewLink.toString(), not(isEmptyOrNullString()));
    }

    @Test(expected = BoxAPIException.class)
    @Category(IntegrationTestJWT.class)
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

    @Test(expected = UnsupportedOperationException.class)
    @Category(UnitTest.class)
    public void attemptingToAuthenticateATransactionalConnectionThrowsError() {
        BoxTransactionalAPIConnection transactionalConnection = new BoxTransactionalAPIConnection("accessToken");
        transactionalConnection.authenticate("authCode");
    }

    @Test(expected = UnsupportedOperationException.class)
    @Category(UnitTest.class)
    public void attemptingToRefreshATransactionalConnectionThrowsError() {
        BoxTransactionalAPIConnection transactionalConnection = new BoxTransactionalAPIConnection("accessToken");
        transactionalConnection.refresh();
    }

    @Test(expected = UnsupportedOperationException.class)
    @Category(UnitTest.class)
    public void attemptingToSetAutoRefreshOnTransactionalConnectionThrowsError() {
        BoxTransactionalAPIConnection transactionalConnection = new BoxTransactionalAPIConnection("accessToken");
        transactionalConnection.setAutoRefresh(true);
    }
}
