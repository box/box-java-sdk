package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.CleanupTools.deleteFile;
import static com.box.sdk.CleanupTools.deleteFolder;
import static com.box.sdk.CleanupTools.deleteUser;
import static com.box.sdk.Retry.retry;
import static com.box.sdk.UniqueTestFolder.getUniqueFolder;
import static com.box.sdk.UniqueTestFolder.randomizeName;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static com.box.sdk.UniqueTestFolder.uploadSampleFileToUniqueFolder;
import static java.time.ZoneOffset.UTC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/** {@link BoxSignRequest} related integration tests. */
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
    String file2Name = "file_to_sign2.pdf";
    BoxFile file = null;
    BoxFile file2 = null;
    BoxFolder signedFileFolder = null;
    AtomicReference<BoxSignRequest.Info> signRequestInfoCancel = new AtomicReference<>();

    try {

      file = uploadSampleFileToUniqueFolder(api, fileName);
      file2 = uploadSampleFileToUniqueFolder(api, file2Name);
      List<BoxSignRequestFile> files = new ArrayList<>();
      files.add(new BoxSignRequestFile(file.getID()));
      files.add(new BoxSignRequestFile(file2.getID()));

      String signerEmail = "user@example.com";
      Boolean signerLoginRequired = false;
      String signerPassword = "password";
      Boolean signerSuppressNotifications = false;
      String signerVerficationPhoneNumber = "+16314578901";

      List<BoxSignRequestSigner> signers = new ArrayList<>();
      BoxSignRequestSigner newSigner =
          new BoxSignRequestSigner(signerEmail)
              .setInPerson(false)
              .setLoginRequired(signerLoginRequired)
              .setPassword(signerPassword)
              .setSuppressNotifications(signerSuppressNotifications)
              .setVerificationPhoneNumber(signerVerficationPhoneNumber);
      signers.add(newSigner);

      signedFileFolder =
          uniqueFolder.createFolder("Folder - signRequestIntegrationTest").getResource();

      // Do Create
      List<BoxSignRequestPrefillTag> prefillTags = new ArrayList<>();
      Instant dateInstant = LocalDateTime.of(2021, 11, 20, 11, 0, 0).toInstant(UTC);
      prefillTags.add(new BoxSignRequestPrefillTag(file.getID(), Date.from(dateInstant)));
      BoxSignRequestCreateParams createParams =
          new BoxSignRequestCreateParams()
              .setIsDocumentPreparationNeeded(true)
              .setPrefillTags(prefillTags);
      BoxSignRequest.Info signRequestInfoCreate =
          BoxSignRequest.createSignRequest(
              api, files, signers, signedFileFolder.getID(), createParams);

      String signRequestIdCreate = signRequestInfoCreate.getID();
      BoxFile.Info fileInfoCreate = signRequestInfoCreate.getSourceFiles().get(0);
      BoxFile.Info file2InfoCreate = signRequestInfoCreate.getSourceFiles().get(1);

      // todo: get signer by role type. Using index=1 is fragile, as order may not be guaranteed.
      // signer at index 0 has role=final_copy_reader
      // signer at index 1 has role=signer
      BoxSignRequestSigner signerCreate = signRequestInfoCreate.getSigners().get(1);

      // Test Create
      assertNotNull(signRequestInfoCreate.getPrepareUrl());
      // The order of the files in the response is not guaranteed
      if (file.getID().equals(fileInfoCreate.getID())) {
        assertEquals(file2.getID(), file2InfoCreate.getID());
      } else {
        assertEquals(file.getID(), file2InfoCreate.getID());
        assertEquals(file2.getID(), fileInfoCreate.getID());
      }
      assertEquals(signerEmail, signerCreate.getEmail());
      assertNotNull(signRequestInfoCreate.getID());

      // Do Get by ID
      BoxSignRequest signRequestGetByID = new BoxSignRequest(api, signRequestIdCreate);
      BoxSignRequest.Info signRequestInfoGetByID = signRequestGetByID.getInfo();
      BoxFile.Info fileInfo = signRequestInfoGetByID.getSourceFiles().get(0);
      BoxFile.Info file2Info = signRequestInfoGetByID.getSourceFiles().get(1);
      BoxSignRequestPrefillTag prefillTag = signRequestInfoGetByID.getPrefillTags().get(0);
      assertEquals(prefillTag.getDocumentTagId(), file.getID());
      assertEquals(BoxDateFormat.formatAsDateOnly(prefillTag.getDateValue()), "2021-11-20");

      // Todo: get signer by role type. Using index=1 is fragile, as order may not be guaranteed.
      // signer at index 0 has role=final_copy_reader
      // signer at index 1 has role=signer
      BoxSignRequestSigner signer = signRequestInfoGetByID.getSigners().get(1);

      // Test Get by ID
      // The order of the files in the response is not guaranteed
      if (file.getID().equals(fileInfo.getID())) {
        assertEquals(file2.getID(), file2Info.getID());
      } else {
        assertEquals(file.getID(), file2Info.getID());
        assertEquals(file2.getID(), fileInfo.getID());
      }
      assertEquals(signerEmail, signer.getEmail());
      assertEquals(signRequestIdCreate, signRequestInfoGetByID.getID());
      assertEquals(signerLoginRequired, signer.getLoginRequired());
      assertEquals(signerSuppressNotifications, signer.getSuppressNotifications());
      assertEquals(signerVerficationPhoneNumber, signer.getVerificationPhoneNumber());

      // Resend sign request
      retry(signRequestGetByID::resend, 5, 500);

      // Do Get All
      Iterable<BoxSignRequest.Info> signRequestsGetAll = BoxSignRequest.getAll(api);

      // Test Get All
      assertTrue(signRequestsGetAll.iterator().hasNext());

      // Do Cancel
      // Cancel will fail if it's too soon after creation
      retry(() -> signRequestInfoCancel.set(signRequestGetByID.cancel()), 5, 1000);
      BoxSignRequest signRequestGetByIDAfterCancel = new BoxSignRequest(api, signRequestIdCreate);
      BoxSignRequest.Info signRequestInfoAfterCancel = signRequestGetByIDAfterCancel.getInfo();
      BoxSignRequest.BoxSignRequestStatus signRequestStatusAfterCancel =
          signRequestInfoAfterCancel.getStatus();

      // Test Cancel
      assertNotNull("Expected Cancel Sign Request but got null", signRequestInfoCancel.get());
      assertEquals(
          BoxSignRequest.BoxSignRequestStatus.Cancelled, signRequestInfoCancel.get().getStatus());
      assertEquals(BoxSignRequest.BoxSignRequestStatus.Cancelled, signRequestStatusAfterCancel);
    } finally {
      if (signRequestInfoCancel.get() != null) {
        // Clean up
        List<BoxFile.Info> signRequestFiles = signRequestInfoCancel.get().getSignFiles().getFiles();
        for (BoxFile.Info signRequestFile : signRequestFiles) {
          BoxFile fileToDelete = new BoxFile(api, signRequestFile.getID());
          fileToDelete.delete();
        }
      }
      deleteFile(file);
      deleteFile(file2);
      deleteFolder(signedFileFolder);
    }
  }

  @Test
  public void createSignRequestForGroup() throws InterruptedException {
    // Test Setup
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxFolder uniqueFolder = getUniqueFolder(api);
    BoxFile file = null;
    BoxFolder signedFileFolder = null;
    BoxUser groupMemberUser1 = null;
    BoxUser groupMemberUser2 = null;
    String userName1 = randomizeName("login1") + "@boz.com";
    String userName2 = randomizeName("login2") + "@boz.com";
    AtomicReference<BoxSignRequest.Info> signRequestInfoCancel = new AtomicReference<>();
    String signerGroupName = randomizeName("GroupName");

    try {
      groupMemberUser1 = BoxUser.createEnterpriseUser(api, userName1, "userName1").getResource();
      groupMemberUser2 = BoxUser.createEnterpriseUser(api, userName2, "userName2").getResource();

      file = uploadSampleFileToUniqueFolder(api, "file_to_sign.pdf");
      List<BoxSignRequestFile> files =
          Collections.singletonList(new BoxSignRequestFile(file.getID()));

      List<BoxSignRequestSigner> signers = new ArrayList<>();
      signers.add(new BoxSignRequestSigner(userName1).setSignerGroupId(signerGroupName));
      signers.add(new BoxSignRequestSigner(userName2).setSignerGroupId(signerGroupName));

      signedFileFolder =
          uniqueFolder.createFolder("Folder - signRequestGroupIntegrationTest").getResource();

      // Do Create
      BoxSignRequest.Info signRequestInfoCreate =
          BoxSignRequest.createSignRequest(api, files, signers, signedFileFolder.getID());

      String signRequestIdCreate = signRequestInfoCreate.getID();
      List<BoxSignRequestSigner> createdSigners = signRequestInfoCreate.getSigners();
      BoxSignRequestSigner createdSigner1 = createdSigners.get(1);
      BoxSignRequestSigner createdSigner2 = createdSigners.get(2);

      // Test Create
      assertNotNull(signRequestInfoCreate.getID());
      assertEquals(createdSigners.size(), 3);
      assertEquals(createdSigner1.getSignerGroupId(), createdSigner2.getSignerGroupId());
      assertNotEquals(createdSigner1.getSignerGroupId(), signerGroupName);

      // Do Get by ID
      BoxSignRequest signRequestGetByID = new BoxSignRequest(api, signRequestIdCreate);

      List<BoxSignRequestSigner> signersGet = signRequestGetByID.getInfo().getSigners();
      BoxSignRequestSigner signer1Get = signersGet.get(1);
      BoxSignRequestSigner signer2Get = createdSigners.get(2);

      // Test Get
      assertNotNull(signRequestInfoCreate.getID());
      assertEquals(signersGet.size(), 3);
      assertEquals(signer1Get.getSignerGroupId(), signer2Get.getSignerGroupId());
      assertEquals(signer1Get.getSignerGroupId(), createdSigner1.getSignerGroupId());

      retry(() -> signRequestInfoCancel.set(signRequestGetByID.cancel()), 5, 1000);

    } finally {
      deleteUser(groupMemberUser1);
      deleteUser(groupMemberUser2);

      if (signRequestInfoCancel.get() != null) {
        // Clean up
        List<BoxFile.Info> signRequestFiles = signRequestInfoCancel.get().getSignFiles().getFiles();
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
