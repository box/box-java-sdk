package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.CleanupTools.deleteFile;
import static com.box.sdk.CleanupTools.deleteFolder;
import static com.box.sdk.UniqueTestFolder.getUniqueFolder;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static com.box.sdk.UniqueTestFolder.uploadSampleFileToUniqueFolder;
import static java.time.ZoneOffset.UTC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * {@link BoxSignRequest} related integration tests.
 */
public class BoxSignRequestIT {

    @BeforeClass
    public static void setup() {
        setupUniqeFolder();
    }

    @AfterClass
    public static void tearDown() {
        removeUniqueFolder();
    }

    @Test
    public void createListAndCancelSignRequest() throws InterruptedException {
        // Test Setup
        BoxAPIConnection api = jwtApiForServiceAccount();
        BoxFolder uniqueFolder = getUniqueFolder(api);
        String fileName = "file_to_sign.pdf";
        BoxFile file = null;
        BoxFolder signedFileFolder = null;
        BoxSignRequest.Info signRequestInfoCancel = null;

        try {
            file = uploadSampleFileToUniqueFolder(api, fileName);
            List<BoxSignRequestFile> files = new ArrayList<>();
            BoxSignRequestFile fileSignRequest = new BoxSignRequestFile(file.getID());
            files.add(fileSignRequest);

            String signerEmail = "user@example.com";
            List<BoxSignRequestSigner> signers = new ArrayList<>();
            BoxSignRequestSigner newSigner = new BoxSignRequestSigner(signerEmail).setInPerson(false);
            signers.add(newSigner);

            signedFileFolder = uniqueFolder.createFolder("Folder - signRequestIntegrationTest").getResource();

            // Do Create
            List<BoxSignRequestPrefillTag> prefillTags = new ArrayList<>();
            Instant dateInstant = LocalDateTime.of(2021, 11, 20, 11, 0, 0)
                .toInstant(UTC);
            prefillTags.add(new BoxSignRequestPrefillTag(file.getID(), Date.from(dateInstant)));
            BoxSignRequestCreateParams createParams = new BoxSignRequestCreateParams()
                .setIsDocumentPreparationNeeded(true)
                .setPrefillTags(prefillTags);
            BoxSignRequest.Info signRequestInfoCreate = BoxSignRequest
                .createSignRequest(api, files, signers, signedFileFolder.getID(), createParams);

            String signRequestIdCreate = signRequestInfoCreate.getID();
            BoxFile.Info fileInfoCreate = signRequestInfoCreate.getSourceFiles().get(0);

            // todo: get signer by role type. Using index=1 is fragile, as order may not be guaranteed.
            //signer at index 0 has role=final_copy_reader
            //signer at index 1 has role=signer
            BoxSignRequestSigner signerCreate = signRequestInfoCreate.getSigners().get(1);

            // Test Create
            assertNotNull(signRequestInfoCreate.getPrepareUrl());
            assertEquals(file.getID(), fileInfoCreate.getID());
            assertEquals(signerEmail, signerCreate.getEmail());
            assertNotNull(signRequestInfoCreate.getID());

            // Do Get by ID
            BoxSignRequest signRequestGetByID = new BoxSignRequest(api, signRequestIdCreate);
            BoxSignRequest.Info signRequestInfoGetByID = signRequestGetByID.getInfo();
            BoxFile.Info fileInfo = signRequestInfoGetByID.getSourceFiles().get(0);
            BoxSignRequestPrefillTag prefillTag = signRequestInfoGetByID.getPrefillTags().get(0);
            assertEquals(prefillTag.getDocumentTagId(), file.getID());
            assertEquals(BoxDateFormat.formatAsDateOnly(prefillTag.getDateValue()), "2021-11-20");

            // Todo: get signer by role type. Using index=1 is fragile, as order may not be guaranteed.
            //signer at index 0 has role=final_copy_reader
            //signer at index 1 has role=signer
            BoxSignRequestSigner signer = signRequestInfoGetByID.getSigners().get(1);

            // Test Get by ID
            assertEquals(file.getID(), fileInfo.getID());
            assertEquals(signerEmail, signer.getEmail());
            assertEquals(signRequestIdCreate, signRequestInfoGetByID.getID());

            // Do Get All
            Iterable<BoxSignRequest.Info> signRequestsGetAll = BoxSignRequest.getAll(api);

            // Test Get All
            assertTrue(signRequestsGetAll.iterator().hasNext());

            // Do Cancel
            // Cancel will fail if it's too soon after creation
            Thread.sleep(3000);
            signRequestInfoCancel = signRequestGetByID.cancel();
            BoxSignRequest signRequestGetByIDAfterCancel = new BoxSignRequest(api, signRequestIdCreate);
            BoxSignRequest.Info signRequestInfoAfterCancel = signRequestGetByIDAfterCancel.getInfo();
            BoxSignRequest.BoxSignRequestStatus signRequestStatusAfterCancel = signRequestInfoAfterCancel.getStatus();

            // Test Cancel
            assertEquals(BoxSignRequest.BoxSignRequestStatus.Cancelled, signRequestInfoCancel.getStatus());
            assertEquals(BoxSignRequest.BoxSignRequestStatus.Cancelled, signRequestStatusAfterCancel);
        } finally {
            if (signRequestInfoCancel != null) {
                // Clean up
                List<BoxFile.Info> signRequestFiles = signRequestInfoCancel.getSignFiles().getFiles();
                for (BoxFile.Info signRequestFile : signRequestFiles) {
                    BoxFile fileToDelete = new BoxFile(api, signRequestFile.getID());
                    fileToDelete.delete();
                }
            }
            deleteFile(file);
            deleteFolder(signedFileFolder);
        }
    }
}

