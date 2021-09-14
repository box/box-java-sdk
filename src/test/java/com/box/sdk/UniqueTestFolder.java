package com.box.sdk;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Use to create and delete unique folder for test.
 */
public class UniqueTestFolder {
    private static final AtomicReference<String> uniqueFolderName = new AtomicReference<>(UUID.randomUUID().toString());
    private static final AtomicReference<String> uniqueFolder = new AtomicReference<>();

    /**
     * Creates a unique folder in root folder. Unique name cames from UUID.
     */
    public static void setupUniqeFolder() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder.Info folderInfo = rootFolder.createFolder(uniqueFolderName.get());
        uniqueFolder.set(folderInfo.getID());
    }

    public static void removeUniqueFolder() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder folder = new BoxFolder(api, uniqueFolder.get());
        folder.delete(true);
    }

    /**
     * Returns the unique folder
     * @param api Api used to get the unique folder
     * @return BoxFolder representing unique folder
     */
    public static BoxFolder getUniqueFolder(BoxAPIConnection api) {
        return new BoxFolder(api,uniqueFolder.get());
    }

    /**
     * Used to upload sample files to unique folder.
     * @param api Api used to upload the file.
     * @param fileName Sample file name.
     * @return BoxFile.Info representing status of uploaded file.
     */
    public static BoxFile.Info uploadSampleFileToUniqueFolder(BoxAPIConnection api, String fileName) {
        return uploadSampleFileToFolder(api, fileName, uniqueFolder.get());
    }

    /**
     * Used to upload sample files to specific folder. Sample files are located in src/test/resources/sample-files
     * @param api Api used to upload the file.
     * @param fileName Sample file name. Sample files are located in src/test/resources/sample-files.
     * @param folderId ID of the folder file must be uplodaed to.
     * @return BoxFile.Info representing status of uploaded file.
     */
    public static BoxFile.Info uploadSampleFileToFolder(BoxAPIConnection api, String fileName, String folderId) {
        BoxFolder folder = new BoxFolder(api, folderId);
        URL fileURL = UniqueTestFolder.class.getResource("/sample-files/" + fileName);
        try {
            String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
            InputStream uploadStream = new FileInputStream(filePath);
            return folder.uploadFile(uploadStream, fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
