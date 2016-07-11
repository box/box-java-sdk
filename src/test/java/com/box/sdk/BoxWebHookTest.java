package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.experimental.categories.Category;


public class BoxWebHookTest {
    @Test
    @Category(IntegrationTest.class)
    public void createWebHookFileSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[createWebhook] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        try {
            URL address = new URL("https://0.0.0.0");
            BoxWebHook.Info info = BoxWebHook.create(uploadedFile, address,
                    BoxWebHook.Trigger.FILE_PREVIEWED, BoxWebHook.Trigger.FILE_LOCKED);

            assertThat(info.getID(), is(notNullValue()));
            assertThat(info.getAddress(), is(equalTo(address)));
            assertThat(info.getTarget().getType(), is(equalTo(BoxWebHook.TargetType.FILE)));
            assertThat(info.getTarget().getId(), is(equalTo(uploadedFile.getID())));
            assertThat(info.getTriggers(), is(equalTo(this.toSet(
                    new BoxWebHook.Trigger[] {BoxWebHook.Trigger.FILE_PREVIEWED, BoxWebHook.Trigger.FILE_LOCKED }))));

            info.getResource().delete();
        } finally {
            uploadedFile.delete();
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void createWebHookFolderSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String folderName = "[createWebhook] Folder";
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();

        try {
            URL address = new URL("https://0.0.0.0");
            BoxWebHook.Info info = BoxWebHook.create(folder, address,
                    BoxWebHook.Trigger.FOLDER_DOWNLOADED, BoxWebHook.Trigger.FOLDER_COPIED);

            assertThat(info.getID(), is(notNullValue()));
            assertThat(info.getAddress(), is(equalTo(address)));
            assertThat(info.getTarget().getType(), is(equalTo(BoxWebHook.TargetType.FOLDER)));
            assertThat(info.getTarget().getId(), is(equalTo(folder.getID())));
            assertThat(info.getTriggers(), is(equalTo(this.toSet(new BoxWebHook.Trigger[] {
                BoxWebHook.Trigger.FOLDER_COPIED, BoxWebHook.Trigger.FOLDER_DOWNLOADED }))));

            info.getResource().delete();
        } finally {
            folder.delete(true);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void listWebHooksSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[listWebhooks] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        try {
            URL address = new URL("https://0.0.0.0");
            BoxWebHook.Info info = BoxWebHook.create(uploadedFile, address, BoxWebHook.Trigger.FILE_PREVIEWED);
            Iterable<BoxWebHook.Info> webhooks = BoxWebHook.all(api);

            assertThat(webhooks, hasItem(Matchers.<BoxWebHook.Info>hasProperty("ID", equalTo(info.getID()))));

            info.getResource().delete();

            webhooks = BoxWebHook.all(api);
            assertThat(webhooks, not(hasItem(Matchers.<BoxWebHook.Info>hasProperty("ID", equalTo(info.getID())))));
        } finally {
            uploadedFile.delete();
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateWebHookInfoSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[updateWebHookInfoSucceeds] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        try {
            URL address = new URL("https://0.0.0.0");
            BoxWebHook webHook = BoxWebHook.create(uploadedFile, address,
                    BoxWebHook.Trigger.FILE_PREVIEWED, BoxWebHook.Trigger.FILE_LOCKED).getResource();

            URL newAddress = new URL("https://0.0.0.1");

            BoxWebHook.Info newInfo = webHook.new Info();
            newInfo.setTriggers(BoxWebHook.Trigger.FILE_UNLOCKED);
            newInfo.setAddress(newAddress);

            webHook.updateInfo(newInfo);

            assertThat(newInfo.getAddress(), is(equalTo(newAddress)));
            assertThat(newInfo.getTriggers(), is(equalTo(
                    this.toSet(new BoxWebHook.Trigger[]{BoxWebHook.Trigger.FILE_UNLOCKED})
            )));

            webHook.delete();
        } finally {
            uploadedFile.delete();
        }
    }

    private <T> Set<T> toSet(T[] values) {
        return new HashSet<T>(Arrays.asList(values));
    }
}
