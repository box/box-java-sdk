package com.box.sdkgen.test.signrequests;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.dateFromString;
import static com.box.sdkgen.internal.utils.UtilsManager.dateToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.createNewFolder;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.test.commons.CommonsManager.uploadNewFile;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.folders.DeleteFolderByIdQueryParams;
import com.box.sdkgen.schemas.filebase.FileBase;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.signrequest.SignRequest;
import com.box.sdkgen.schemas.signrequestcreaterequest.SignRequestCreateRequest;
import com.box.sdkgen.schemas.signrequestcreatesigner.SignRequestCreateSigner;
import com.box.sdkgen.schemas.signrequestcreatesigner.SignRequestCreateSignerRoleField;
import com.box.sdkgen.schemas.signrequestprefilltag.SignRequestPrefillTag;
import com.box.sdkgen.schemas.signrequests.SignRequests;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class SignRequestsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCreateGetCancelAndListSignRequest() {
    String signerEmail = String.join("", getUuid(), "@box.com");
    FileFull fileToSign = uploadNewFile();
    FolderFull destinationFolder = createNewFolder();
    SignRequest createdSignRequest =
        client
            .getSignRequests()
            .createSignRequest(
                new SignRequestCreateRequest.Builder(
                        Arrays.asList(
                            new SignRequestCreateSigner.Builder()
                                .email(signerEmail)
                                .role(SignRequestCreateSignerRoleField.SIGNER)
                                .isInPerson(false)
                                .embedUrlExternalUserId("123")
                                .declinedRedirectUrl("https://www.box.com")
                                .loginRequired(false)
                                .password("password")
                                .suppressNotifications(true)
                                .build()))
                    .sourceFiles(Arrays.asList(new FileBase(fileToSign.getId())))
                    .parentFolder(new FolderMini(destinationFolder.getId()))
                    .isDocumentPreparationNeeded(false)
                    .redirectUrl("https://www.box.com")
                    .declinedRedirectUrl("https://www.box.com")
                    .areTextSignaturesEnabled(true)
                    .emailSubject("Sign this document")
                    .emailMessage("Please sign this document")
                    .areRemindersEnabled(true)
                    .name("Sign Request")
                    .prefillTags(
                        Arrays.asList(
                            new SignRequestPrefillTag.Builder()
                                .documentTagId("0")
                                .dateValue(dateFromString("2035-01-01"))
                                .build()))
                    .daysValid(30L)
                    .externalId("123")
                    .externalSystemName("BoxSignIntegration")
                    .build());
    assert createdSignRequest.getAreRemindersEnabled() == true;
    assert createdSignRequest.getAreTextSignaturesEnabled() == true;
    assert createdSignRequest.getDaysValid() == 30;
    assert createdSignRequest.getDeclinedRedirectUrl().equals("https://www.box.com");
    assert createdSignRequest.getEmailMessage().equals("Please sign this document");
    assert createdSignRequest.getEmailSubject().equals("Sign this document");
    assert createdSignRequest.getExternalId().equals("123");
    assert createdSignRequest.getExternalSystemName().equals("BoxSignIntegration");
    assert createdSignRequest.getIsDocumentPreparationNeeded() == false;
    assert createdSignRequest.getName().equals("Sign Request.pdf");
    assert createdSignRequest.getRedirectUrl().equals("https://www.box.com");
    assert createdSignRequest
        .getSignFiles()
        .getFiles()
        .get(0)
        .getName()
        .equals(createdSignRequest.getName());
    assert createdSignRequest.getSigners().get(1).getEmail().equals(signerEmail);
    assert createdSignRequest.getSigners().get(1).getSuppressNotifications() == true;
    assert createdSignRequest
        .getSigners()
        .get(1)
        .getDeclinedRedirectUrl()
        .equals("https://www.box.com");
    assert createdSignRequest.getSigners().get(1).getEmbedUrlExternalUserId().equals("123");
    assert createdSignRequest.getSigners().get(1).getIsInPerson() == false;
    assert createdSignRequest.getSigners().get(1).getLoginRequired() == false;
    assert convertToString(createdSignRequest.getSigners().get(1).getRole()).equals("signer");
    assert createdSignRequest.getParentFolder().getId().equals(destinationFolder.getId());
    assert dateToString(createdSignRequest.getPrefillTags().get(0).getDateValue())
        .equals("2035-01-01");
    SignRequest newSignRequest =
        client.getSignRequests().getSignRequestById(createdSignRequest.getId());
    assert newSignRequest
        .getSignFiles()
        .getFiles()
        .get(0)
        .getName()
        .equals(createdSignRequest.getName());
    assert newSignRequest.getSigners().get(1).getEmail().equals(signerEmail);
    assert newSignRequest.getParentFolder().getId().equals(destinationFolder.getId());
    SignRequest cancelledSignRequest =
        client.getSignRequests().cancelSignRequest(createdSignRequest.getId());
    assert convertToString(cancelledSignRequest.getStatus()).equals("cancelled");
    SignRequests signRequests = client.getSignRequests().getSignRequests();
    assert convertToString(signRequests.getEntries().get(0).getType()).equals("sign-request");
    client
        .getFolders()
        .deleteFolderById(
            destinationFolder.getId(),
            new DeleteFolderByIdQueryParams.Builder().recursive(true).build());
    client.getFiles().deleteFileById(fileToSign.getId());
  }

  @Test
  public void testCreateSignRequestWithSignerGroupId() {
    String signer1Email = String.join("", getUuid(), "@box.com");
    String signer2Email = String.join("", getUuid(), "@box.com");
    FileFull fileToSign = uploadNewFile();
    FolderFull destinationFolder = createNewFolder();
    SignRequest createdSignRequest =
        client
            .getSignRequests()
            .createSignRequest(
                new SignRequestCreateRequest.Builder(
                        Arrays.asList(
                            new SignRequestCreateSigner.Builder()
                                .email(signer1Email)
                                .signerGroupId("user")
                                .build(),
                            new SignRequestCreateSigner.Builder()
                                .email(signer2Email)
                                .signerGroupId("user")
                                .build()))
                    .sourceFiles(Arrays.asList(new FileBase(fileToSign.getId())))
                    .parentFolder(new FolderMini(destinationFolder.getId()))
                    .build());
    assert createdSignRequest.getSigners().size() == 3;
    assert !(createdSignRequest.getSigners().get(1).getSignerGroupId() == null);
    assert createdSignRequest
        .getSigners()
        .get(1)
        .getSignerGroupId()
        .equals(createdSignRequest.getSigners().get(2).getSignerGroupId());
    client
        .getFolders()
        .deleteFolderById(
            destinationFolder.getId(),
            new DeleteFolderByIdQueryParams.Builder().recursive(true).build());
    client.getFiles().deleteFileById(fileToSign.getId());
  }
}
