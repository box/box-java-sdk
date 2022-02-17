package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Use to create and delete unique folder for test.
 */
public final class UniqueTestFolder {
    private static final AtomicReference<String> UNIQUE_FOLDER_NAME =
        new AtomicReference<>(UUID.randomUUID().toString());
    private static final AtomicReference<String> UNIQUE_FOLDER = new AtomicReference<>();

    private UniqueTestFolder() {
    }

    /**
     * Creates a unique folder in root folder. Unique name cames from UUID.
     */
    public static void setupUniqeFolder() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder.Info folderInfo = rootFolder.createFolder(UNIQUE_FOLDER_NAME.get());
        UNIQUE_FOLDER.set(folderInfo.getID());
    }

    public static void removeUniqueFolder() {
        if (UNIQUE_FOLDER.get() != null) {
            BoxAPIConnection api = jwtApiForServiceAccount();
            BoxFolder folder = new BoxFolder(api, UNIQUE_FOLDER.get());
            folder.delete(true);
            BoxTrash trash = new BoxTrash(api);
            trash.deleteFolder(folder.getID());
        }
    }

    /**
     * Returns the unique folder.
     *
     * @param api Api used to get the unique folder
     * @return BoxFolder representing unique folder
     */
    public static BoxFolder getUniqueFolder(BoxAPIConnection api) {
        return new BoxFolder(api, UNIQUE_FOLDER.get());
    }

    /**
     * Used to upload sample files to unique folder.
     *
     * @param api      Api used to upload the file.
     * @param fileName Sample file name.
     * @return BoxFile Returns uploaded BoxFile.
     */
    public static BoxFile uploadSampleFileToUniqueFolder(BoxAPIConnection api, String fileName) {
        return uploadSampleFileToFolder(api, fileName, UNIQUE_FOLDER.get());
    }

    /**
     * Used to upload sample files to specific folder. Sample files are located in src/test/resources/sample-files
     *
     * @param api      Api used to upload the file.
     * @param fileName Sample file name. Sample files are located in src/test/resources/sample-files.
     * @param folderId ID of the folder file must be uplodaed to.
     * @return BoxFile Returns uploaded BoxFile.
     */
    public static BoxFile uploadSampleFileToFolder(BoxAPIConnection api, String fileName, String folderId) {
        BoxFolder folder = new BoxFolder(api, folderId);
        URL fileURL = UniqueTestFolder.class.getResource("/sample-files/" + fileName);
        try {
            String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
            InputStream uploadStream = new FileInputStream(filePath);
            return folder.uploadFile(uploadStream, fileName).getResource();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static BoxFile uploadFileToUniqueFolder(BoxAPIConnection api, String fileName, String fileContent) {
        return uploadFileWithContentToSpecifiedFolder(fileName, fileContent, getUniqueFolder(api));
    }

    public static BoxFile uploadFileToUniqueFolderWithSomeContent(BoxAPIConnection api, String fileName) {
        return uploadFileWithContentToSpecifiedFolder(fileName, "Test file", getUniqueFolder(api));
    }

    public static BoxFile uploadFileWithSomeContent(String fileName, BoxFolder folder) {
        return uploadFileWithContentToSpecifiedFolder(fileName, "Test file", folder);
    }

    private static BoxFile uploadFileWithContentToSpecifiedFolder(
        String fileName, String fileContent, BoxFolder folder
    ) {
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        return folder.uploadFile(uploadStream, fileName).getResource();
    }

    public static String getUniqueFolderName() {
        return UNIQUE_FOLDER_NAME.get();
    }

    public static BoxFile uploadTwoFileVersionsToUniqueFolder(
        String fileName,
        String version1Content,
        String version2Content,
        ProgressListener mockUploadListener
    ) {
        BoxAPIConnection api = jwtApiForServiceAccount();
        return uploadTwoFileVersionsToSpecifiedFolder(
            fileName, version1Content, version2Content, getUniqueFolder(api), mockUploadListener
        );
    }

    public static BoxFile uploadTwoFileVersionsToSpecifiedFolder(
        String fileName,
        String version1Content,
        String version2Content,
        BoxFolder folder,
        ProgressListener mockUploadListener
    ) {
        byte[] version1Bytes = version1Content.getBytes(StandardCharsets.UTF_8);
        byte[] version2Bytes = version2Content.getBytes(StandardCharsets.UTF_8);
        long version2Size = version1Bytes.length;

        InputStream uploadStream = new ByteArrayInputStream(version1Bytes);
        BoxFile uploadedFile = folder.uploadFile(uploadStream, fileName).getResource();

        uploadStream = new ByteArrayInputStream(version2Bytes);
        uploadedFile.uploadNewVersion(uploadStream, null, version2Size, mockUploadListener);
        return uploadedFile;
    }

    static String randomizeName(String name) {
        return name + "_" + UUID.randomUUID();
    }
}
