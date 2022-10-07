package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.BoxWebHook.Trigger.FOLDER_COPIED;
import static com.box.sdk.BoxWebHook.Trigger.FOLDER_DOWNLOADED;
import static com.box.sdk.BoxWebHook.Trigger.SIGN_REQUEST_COMPLETED;
import static com.box.sdk.BoxWebHook.Trigger.SIGN_REQUEST_DECLINED;
import static com.box.sdk.BoxWebHook.Trigger.SIGN_REQUEST_EXPIRED;
import static com.box.sdk.CleanupTools.deleteFile;
import static com.box.sdk.CleanupTools.deleteFolder;
import static com.box.sdk.UniqueTestFolder.getUniqueFolder;
import static com.box.sdk.UniqueTestFolder.randomizeName;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static com.box.sdk.UniqueTestFolder.uploadFileToUniqueFolderWithSomeContent;
import static com.box.sdk.UniqueTestFolder.uploadSampleFileToUniqueFolder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * {@link BoxWebHook} related integration tests.
 */
public class BoxWebHookIT {

    @BeforeClass
    public static void setup() {
        setupUniqeFolder();
    }

    @AfterClass
    public static void afterClass() {
        removeUniqueFolder();
    }

    @Test
    public void createWebHookFileSucceeds() throws IOException {
        BoxAPIConnection api = jwtApiForServiceAccount();
        String fileName = "[createWebhook] Test File.txt";

        BoxFile uploadedFile = null;

        try {
            uploadedFile = uploadFileToUniqueFolderWithSomeContent(api, fileName);
            URL address = new URL("https://www.google.com");
            BoxWebHook.Info info = BoxWebHook.create(uploadedFile, address,
                BoxWebHook.Trigger.FILE_PREVIEWED, BoxWebHook.Trigger.FILE_LOCKED);

            assertThat(info.getID(), is(notNullValue()));
            assertThat(info.getAddress(), is(equalTo(address)));
            assertThat(info.getTarget().getType(), is(equalTo(BoxResource.getResourceType(BoxFile.class))));
            assertThat(info.getTarget().getId(), is(equalTo(uploadedFile.getID())));
            assertThat(info.getTriggers(), is(equalTo(this.toSet(
                new BoxWebHook.Trigger[]{BoxWebHook.Trigger.FILE_PREVIEWED, BoxWebHook.Trigger.FILE_LOCKED}))));

            info.getResource().delete();
        } finally {
            deleteFile(uploadedFile);
        }
    }

    @Test
    public void createWebHookFolderSucceeds() throws IOException {
        BoxAPIConnection api = jwtApiForServiceAccount();
        BoxFolder rootFolder = getUniqueFolder(api);
        String folderName = "[createWebhook] Folder";
        BoxFolder folder = null;

        try {
            folder = rootFolder.createFolder(folderName).getResource();
            URL address = new URL("https://www.google.com");
            BoxWebHook.Info info = BoxWebHook.create(folder, address, FOLDER_DOWNLOADED, FOLDER_COPIED);

            assertThat(info.getID(), is(notNullValue()));
            assertThat(info.getAddress(), is(equalTo(address)));
            assertThat(info.getTarget().getType(), is(equalTo(BoxResource.getResourceType(BoxFolder.class))));
            assertThat(info.getTarget().getId(), is(equalTo(folder.getID())));
            assertThat(info.getTriggers(),
                is(equalTo(this.toSet(new BoxWebHook.Trigger[]{FOLDER_COPIED, FOLDER_DOWNLOADED}))));

            info.getResource().delete();
        } finally {
            deleteFolder(folder);
        }
    }

    @Test
    public void listWebHooksSucceeds() throws IOException {
        BoxAPIConnection api = jwtApiForServiceAccount();
        String fileName = "[listWebhooks] Test File.txt";
        BoxFile uploadedFile = null;

        try {
            uploadedFile = uploadFileToUniqueFolderWithSomeContent(api, fileName);
            URL address = new URL("https://www.google.com");
            BoxWebHook.Info info = BoxWebHook.create(uploadedFile, address, BoxWebHook.Trigger.FILE_PREVIEWED);
            Iterable<BoxWebHook.Info> webhooks = BoxWebHook.all(api);

            assertThat(webhooks, hasItem(Matchers.<BoxWebHook.Info>hasProperty("ID", equalTo(info.getID()))));

            info.getResource().delete();

            webhooks = BoxWebHook.all(api);
            assertThat(webhooks, not(hasItem(Matchers.<BoxWebHook.Info>hasProperty("ID", equalTo(info.getID())))));
        } finally {
            deleteFile(uploadedFile);
        }
    }

    @Test
    public void updateWebHookInfoSucceeds() throws IOException {
        BoxAPIConnection api = jwtApiForServiceAccount();
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[updateWebHookInfoSucceeds] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        try {
            URL address = new URL("https://www.google.com");
            BoxWebHook webHook = BoxWebHook.create(uploadedFile, address,
                BoxWebHook.Trigger.FILE_PREVIEWED, BoxWebHook.Trigger.FILE_LOCKED).getResource();

            URL newAddress = new URL("https://www.yahoo.com");

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
            deleteFile(uploadedFile);
        }
    }

    @Test
    public void createWebHookSignRequestOnFileSucceeds() throws IOException {
        BoxAPIConnection api = jwtApiForServiceAccount();
        String fileName = "file_to_sign.pdf";

        BoxFile file = null;
        try {
            file = uploadSampleFileToUniqueFolder(api, fileName);
            URL address = new URL("https://www.google.com");

            BoxWebHook.Info webHookInfo = BoxWebHook.create(
                file, address, SIGN_REQUEST_DECLINED, SIGN_REQUEST_EXPIRED, SIGN_REQUEST_COMPLETED
            );

            assertThat(webHookInfo.getID(), is(notNullValue()));
            assertThat(webHookInfo.getAddress(), is(equalTo(address)));
            assertThat(webHookInfo.getTarget().getType(), is(equalTo(BoxResource.getResourceType(BoxFile.class))));
            assertThat(webHookInfo.getTarget().getId(), is(equalTo(file.getID())));
            assertThat(webHookInfo.getTriggers(), is(equalTo(this.toSet(
                new BoxWebHook.Trigger[]{SIGN_REQUEST_DECLINED, SIGN_REQUEST_EXPIRED, SIGN_REQUEST_COMPLETED}))));
        } finally {
            deleteFile(file);
        }
    }

    @Test
    public void createWebHookSignRequestOnFolderSucceeds() throws IOException {
        BoxAPIConnection api = jwtApiForServiceAccount();

        BoxFolder folder = null;
        try {
            folder = getUniqueFolder(api)
                .createFolder(randomizeName("createWebHookSignRequestOnFolderSucceeds"))
                .getResource();
            URL address = new URL("https://www.google.com");

            BoxWebHook.Info webHookInfo = BoxWebHook.create(
                folder, address, SIGN_REQUEST_DECLINED, SIGN_REQUEST_EXPIRED, SIGN_REQUEST_COMPLETED
            );

            assertThat(webHookInfo.getID(), is(notNullValue()));
            assertThat(webHookInfo.getAddress(), is(equalTo(address)));
            assertThat(webHookInfo.getTarget().getType(), is(equalTo(BoxResource.getResourceType(BoxFolder.class))));
            assertThat(webHookInfo.getTarget().getId(), is(equalTo(folder.getID())));
            assertThat(webHookInfo.getTriggers(), is(equalTo(this.toSet(
                new BoxWebHook.Trigger[]{SIGN_REQUEST_DECLINED, SIGN_REQUEST_EXPIRED, SIGN_REQUEST_COMPLETED}))));
        } finally {
            deleteFolder(folder);
        }
    }

    private <T> Set<T> toSet(T[] values) {
        return new HashSet<>(Arrays.asList(values));
    }
}
