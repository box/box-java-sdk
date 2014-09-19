package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxFileTest {
    @Test
    @Category(IntegrationTest.class)
    public void downloadFileSucceeds() throws UnsupportedEncodingException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        final String fileContent = "Test file";
        final long fileLength = fileContent.length();
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        BoxFile uploadedFile = rootFolder.uploadFile(stream, "Test File.txt", null, null);
        assertThat(rootFolder, hasItem(uploadedFile));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        final boolean[] onProgressChangedCalled = new boolean[]{false};
        uploadedFile.download(output, new ProgressListener() {
            public void onProgressChanged(long numBytes, long totalBytes) {
                onProgressChangedCalled[0] = true;

                assertThat(numBytes, is(not(0L)));
                assertThat(totalBytes, is(equalTo(fileLength)));
            }
        });

        assertThat(onProgressChangedCalled[0], is(true));
        String downloadedFileContent = output.toString(StandardCharsets.UTF_8.name());
        assertThat(downloadedFileContent, equalTo(fileContent));

        uploadedFile.delete();
        assertThat(rootFolder, not(hasItem(uploadedFile)));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoWithOnlyTheNameField() {
        final String expectedName = "[getInfoWithOnlyTheNameField] Test File.txt";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        final String fileContent = "Test file";
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        BoxFile uploadedFile = rootFolder.uploadFile(stream, expectedName, null, null);
        assertThat(rootFolder, hasItem(uploadedFile));

        BoxFile.Info info = uploadedFile.getInfo("name");
        final String actualName = info.getName();
        final String actualDescription = info.getDescription();
        final long actualSize = info.getSize();

        assertThat(expectedName, equalTo(actualName));
        assertThat(actualDescription, is(nullValue()));
        assertThat(actualSize, is(0L));

        uploadedFile.delete();
        assertThat(rootFolder, not(hasItem(uploadedFile)));
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateFileInfoSucceeds() {
        final String originalName = "[updateFileInfoSucceeds] Original Name.txt";
        final String newName = "[updateFileInfoSucceeds] New Name.txt";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        final String fileContent = "Test file";
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        BoxFile uploadedFile = rootFolder.uploadFile(stream, originalName, null, null);
        assertThat(rootFolder, hasItem(uploadedFile));

        BoxFile.Info info = uploadedFile.new Info();
        info.setName(newName);
        uploadedFile.updateInfo(info);

        info = uploadedFile.getInfo();
        assertThat(info.getName(), equalTo(newName));

        uploadedFile.delete();
        assertThat(rootFolder, not(hasItem(uploadedFile)));
    }
}
