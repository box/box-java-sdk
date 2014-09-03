package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
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
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        BoxFile uploadedFile = rootFolder.uploadFile(stream, "Test File.txt", null, null);
        assertThat(rootFolder, hasItem(uploadedFile));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        uploadedFile.download(output);
        String downloadedFileContent = output.toString(StandardCharsets.UTF_8.name());
        assertThat(fileContent, equalTo(downloadedFileContent));

        uploadedFile.delete();
        assertThat(rootFolder, not(hasItem(uploadedFile)));
    }
}
