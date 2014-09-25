package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.longThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxFileTest {
    @Test
    @Category(IntegrationTest.class)
    public void uploadAndDownloadFileSucceeds() throws UnsupportedEncodingException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[uploadAndDownloadFileSucceeds] Test File.txt";
        String fileContent = "Non-empty string";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);
        long fileSize = fileBytes.length;

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName);

        ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
        ProgressListener mockProgressListener = mock(ProgressListener.class);
        uploadedFile.download(downloadStream, mockProgressListener);
        String downloadedContent = downloadStream.toString(StandardCharsets.UTF_8.name());

        assertThat(rootFolder, hasItem(uploadedFile));
        assertThat(downloadedContent, equalTo(fileContent));
        verify(mockProgressListener).onProgressChanged(anyLong(), longThat(is(equalTo(fileSize))));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadAndDownloadMultipleVersionsSucceeds() throws UnsupportedEncodingException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[uploadAndDownloadMultipleVersionsSucceeds] Multi-version File.txt";
        String version1Content = "Version 1";
        String version1Sha = "db3cbc01da600701b9fe4a497fe328e71fa7022f";
        byte[] version1Bytes = version1Content.getBytes(StandardCharsets.UTF_8);
        long version1Size =  version1Bytes.length;
        String version2Content = "Version 2";
        byte[] version2Bytes = version2Content.getBytes(StandardCharsets.UTF_8);
        long version2Size = version1Bytes.length;

        InputStream uploadStream = new ByteArrayInputStream(version1Bytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName);
        uploadStream = new ByteArrayInputStream(version2Bytes);
        uploadedFile.uploadVersion(uploadStream);

        Collection<BoxFileVersion> versions = uploadedFile.getVersions();
        BoxFileVersion previousVersion = versions.iterator().next();

        ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
        ProgressListener mockProgressListener = mock(ProgressListener.class);
        previousVersion.download(downloadStream, mockProgressListener);
        String downloadedContent = downloadStream.toString(StandardCharsets.UTF_8.name());

        assertThat(versions, hasSize(1));
        assertThat(previousVersion.getSha1(), is(equalTo(version1Sha)));
        assertThat(downloadedContent, equalTo(version1Content));
        verify(mockProgressListener).onProgressChanged(anyLong(), longThat(is(equalTo(version1Size))));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoWithOnlyTheNameField() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[getInfoWithOnlyTheNameField] Test File.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName);
        BoxFile.Info uploadedFileInfo = uploadedFile.getInfo("name");

        assertThat(uploadedFileInfo.getName(), is(equalTo(fileName)));
        assertThat(uploadedFileInfo.getDescription(), is(nullValue()));
        assertThat(uploadedFileInfo.getSize(), is(equalTo(0L)));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateFileInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String originalFileName = "[updateFileInfoSucceeds] Original Name.txt";
        String newFileName = "[updateFileInfoSucceeds] New Name.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, originalFileName);

        BoxFile.Info newInfo = uploadedFile.new Info();
        newInfo.setName(newFileName);
        uploadedFile.updateInfo(newInfo);
        BoxFile.Info refreshedInfo = uploadedFile.getInfo();

        assertThat(refreshedInfo.getName(), is(equalTo(newFileName)));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void deleteVersionSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[deleteVersionSucceeds] Multi-version File.txt";
        byte[] version1Bytes = "Version 1".getBytes(StandardCharsets.UTF_8);
        byte[] version2Bytes = "Version 2".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(version1Bytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName);
        uploadStream = new ByteArrayInputStream(version2Bytes);
        uploadedFile.uploadVersion(uploadStream);

        Collection<BoxFileVersion> versions = uploadedFile.getVersions();
        BoxFileVersion previousVersion = versions.iterator().next();
        previousVersion.delete();

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void promoteVersionsSucceeds() throws UnsupportedEncodingException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[promoteVersionsSucceeds] Multi-version File.txt";
        String version1Content = "Version 1";
        byte[] version1Bytes = version1Content.getBytes(StandardCharsets.UTF_8);
        byte[] version2Bytes = "Version 2".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(version1Bytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName);
        uploadStream = new ByteArrayInputStream(version2Bytes);
        uploadedFile.uploadVersion(uploadStream);

        Collection<BoxFileVersion> versions = uploadedFile.getVersions();
        BoxFileVersion previousVersion = versions.iterator().next();
        previousVersion.promote();

        ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
        uploadedFile.download(downloadStream);
        String downloadedContent = downloadStream.toString(StandardCharsets.UTF_8.name());
        assertThat(downloadedContent, equalTo(version1Content));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void copyFileSucceeds() throws UnsupportedEncodingException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String originalFileName = "[copyFileSucceeds] Original File.txt";
        String newFileName = "[copyFileSucceeds] New File.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, originalFileName);

        BoxFile.Info copiedFileInfo = uploadedFile.copy(rootFolder, newFileName);
        BoxFile copiedFile = copiedFileInfo.getResource();

        ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
        copiedFile.download(downloadStream);
        String downloadedContent = downloadStream.toString(StandardCharsets.UTF_8.name());
        assertThat(downloadedContent, equalTo(fileContent));

        uploadedFile.delete();
        copiedFile.delete();
    }
}
