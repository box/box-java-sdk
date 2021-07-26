package com.box.sdk;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.jose4j.jwt.NumericDate;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

/**
 * {@link BoxSignRequest} related unit tests.
 */
public class BoxSignRequestTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(UnitTest.class)
    public void createSignRequestSucceeds() throws IOException {
        final String fileId = "12345";
        final String fileName = "Contract.pdf";
        final String signerEmail = "example@gmail.com";
        final String signRequestId = "12345";

        final String prepareUrl = "https://prepareurl.com";

        String result = TestConfig.getFixture("BoxSignRequest/CreateSignRequest200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo("/sign_requests"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        List<BoxSignRequestFile> files = new ArrayList<BoxSignRequestFile>();
        BoxSignRequestFile file = new BoxSignRequestFile("12345");
        files.add(file);

        List<BoxSignRequestSigner> signers = new ArrayList<BoxSignRequestSigner>();
        BoxSignRequestSigner newSigner = new BoxSignRequestSigner("signer@mail.com");
        signers.add(newSigner);

        String parentFolderId = "55555";
        BoxSignRequest.Info signRequestInfo = BoxSignRequest.createSignRequest(this.api, files,
                signers, parentFolderId);

        BoxFile.Info fileInfo = signRequestInfo.getSourceFiles().get(0);
        BoxSignRequestSigner signer = signRequestInfo.getSigners().get(0);

        Assert.assertEquals(prepareUrl, signRequestInfo.getPrepareUrl());
        Assert.assertEquals(fileId, fileInfo.getID());
        Assert.assertEquals(fileName, fileInfo.getName());
        Assert.assertEquals(signerEmail, signer.getEmail());
        Assert.assertEquals(signRequestId, signRequestInfo.getID());
    }

    @Test
    @Category(UnitTest.class)
    public void getSignRequestInfoSucceeds() throws IOException {
        final String fileId = "12345";
        final String fileName = "Contract.pdf";
        final String signerEmail = "example@gmail.com";
        final String signRequestId = "12345";

        final String prepareUrl = "https://prepareurl.com";

        final String requestUrl = "/sign_requests/" + signRequestId;

        String result = TestConfig.getFixture("BoxSignRequest/GetSignRequest200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(requestUrl))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxSignRequest signRequest = new BoxSignRequest(this.api, signRequestId);
        BoxSignRequest.Info signRequestInfo = signRequest.getInfo();

        BoxFile.Info fileInfo = signRequestInfo.getSourceFiles().get(0);
        BoxSignRequestSigner signer = signRequestInfo.getSigners().get(0);

        Assert.assertEquals(prepareUrl, signRequestInfo.getPrepareUrl());
        Assert.assertEquals(fileId, fileInfo.getID());
        Assert.assertEquals(fileName, fileInfo.getName());
        Assert.assertEquals(signerEmail, signer.getEmail());
        Assert.assertEquals(signRequestId, signRequestInfo.getID());
    }

    @Test
    @Category(UnitTest.class)
    public void getAllSignRequestsSucceeds() throws IOException {
        final String fileId = "12345";
        final String fileName = "Contract.pdf";
        final String signerEmail = "example@gmail.com";
        final String signRequestId = "12345";

        final String prepareUrl = "https://prepareurl.com";

        final String requestUrl = "/sign_requests";

        String result = TestConfig.getFixture("BoxSignRequest/GetAllSignRequests200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(requestUrl))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Iterator<BoxSignRequest.Info> signRequests = BoxSignRequest.getAll(this.api).iterator();
        BoxSignRequest.Info firstSignRequest = signRequests.next();

        BoxFile.Info fileInfo = firstSignRequest.getSourceFiles().get(0);
        BoxSignRequestSigner signer = firstSignRequest.getSigners().get(0);

        Assert.assertEquals(prepareUrl, firstSignRequest.getPrepareUrl());
        Assert.assertEquals(fileId, fileInfo.getID());
        Assert.assertEquals(fileName, fileInfo.getName());
        Assert.assertEquals(signerEmail, signer.getEmail());
        Assert.assertEquals(signRequestId, firstSignRequest.getID());
    }

    @Test
    @Category(UnitTest.class)
    public void cancelSignRequestSucceeds() throws IOException {
        final String signRequestId = "12345";

        final String requestUrl = "/sign_requests/" + signRequestId + "/cancel";

        String result = TestConfig.getFixture("BoxSignRequest/CancelSignRequest200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(requestUrl))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxSignRequest signRequest = new BoxSignRequest(this.api, signRequestId);
        BoxSignRequest.Info signRequestInfo = signRequest.cancel();

        Assert.assertEquals(BoxSignRequest.BoxSignRequestStatus.Cancelled, signRequestInfo.getStatus());
    }

    @Test
    @Category(UnitTest.class)
    public void resendSignRequestSucceeds() {
        final String signRequestId = "12345";

        final String requestUrl = "/sign_requests/" + signRequestId + "/resend";

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(requestUrl))
                .willReturn(WireMock.aResponse()
                        .withStatus(202)));

        BoxSignRequest signRequest = new BoxSignRequest(this.api, signRequestId);

        signRequest.resend();

        WireMock.verify(1, postRequestedFor(urlPathEqualTo(requestUrl)));
    }

    @Test
    @Category(IntegrationTestSignRequest.class)
    public void signRequestIntegrationTest() throws InterruptedException {
        // Test Setup
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        String fileId = "837638072808";
        List<BoxSignRequestFile> files = new ArrayList<BoxSignRequestFile>();
        BoxSignRequestFile file = new BoxSignRequestFile(fileId);
        files.add(file);

        String signerEmail = "user@example.com";
        List<BoxSignRequestSigner> signers = new ArrayList<BoxSignRequestSigner>();
        BoxSignRequestSigner newSigner = new BoxSignRequestSigner(signerEmail)
                .setInPerson(false);
        signers.add(newSigner);

        BoxFolder testFolder = new BoxFolder(api, "141841942473");
        BoxFolder.Info parentFolderInfo = testFolder.createFolder("Test " + NumericDate.now().getValue());
        String parentFolderId = parentFolderInfo.getID();

        // Do Create
        BoxSignRequestCreateParams createParams = new BoxSignRequestCreateParams()
                .setIsDocumentPreparationNeeded(true);
        BoxSignRequest.Info signRequestInfoCreate = BoxSignRequest.createSignRequest(api, files,
                signers, parentFolderId, createParams);

        String signRequestIdCreate = signRequestInfoCreate.getID();
        BoxFile.Info fileInfoCreate = signRequestInfoCreate.getSourceFiles().get(0);

        // Todo: get signer by role type. Using index=1 is fragile, as order may not be guaranteed.
        //signer at index 0 has role=final_copy_reader
        //signer at index 1 has role=signer
        BoxSignRequestSigner signerCreate = signRequestInfoCreate.getSigners().get(1);

        // Test Create
        Assert.assertNotNull(signRequestInfoCreate.getPrepareUrl());
        Assert.assertEquals(fileId, fileInfoCreate.getID());
        Assert.assertEquals(signerEmail, signerCreate.getEmail());
        Assert.assertNotNull(signRequestInfoCreate.getID());

        // Do Get by ID
        BoxSignRequest signRequestGetByID = new BoxSignRequest(api, signRequestIdCreate);
        BoxSignRequest.Info signRequestInfoGetByID = signRequestGetByID.getInfo();
        BoxFile.Info fileInfo = signRequestInfoGetByID.getSourceFiles().get(0);

        // Todo: get signer by role type. Using index=1 is fragile, as order may not be guaranteed.
        //signer at index 0 has role=final_copy_reader
        //signer at index 1 has role=signer
        BoxSignRequestSigner signer = signRequestInfoGetByID.getSigners().get(1);

        // Test Get by ID
        Assert.assertEquals(fileId, fileInfo.getID());
        Assert.assertEquals(signerEmail, signer.getEmail());
        Assert.assertEquals(signRequestIdCreate, signRequestInfoGetByID.getID());

        // Do Get All
        Iterable<BoxSignRequest.Info> signRequestsGetAll = BoxSignRequest.getAll(api);

        // Test Get All
        Assert.assertTrue(signRequestsGetAll.iterator().hasNext());

        // Do Cancel
        // Cancel will fail if it's too soon after creation
        Thread.sleep(3000);
        BoxSignRequest.Info signRequestInfoCancel = signRequestGetByID.cancel();
        BoxSignRequest signRequestGetByIDAfterCancel = new BoxSignRequest(api, signRequestIdCreate);
        BoxSignRequest.Info signRequestInfoAfterCancel = signRequestGetByID.getInfo();
        BoxSignRequest.BoxSignRequestStatus signRequestStatusAfterCancel = signRequestInfoAfterCancel.getStatus();

        // Test Cancel
        Assert.assertEquals(BoxSignRequest.BoxSignRequestStatus.Cancelled, signRequestInfoCancel.getStatus());
        Assert.assertTrue(signRequestStatusAfterCancel == BoxSignRequest.BoxSignRequestStatus.Cancelled);

        // Clean up
        List<BoxFile.Info> signRequestFiles = signRequestInfoCancel.getSignFiles().getFiles();
        for (BoxFile.Info signRequestFile : signRequestFiles) {
            BoxFile fileToDelete = new BoxFile(api, signRequestFile.getID());
            fileToDelete.delete();
        }

        // Deleting the folder before the above file will cause sign request 500s on future calls
        Thread.sleep(3000);
        BoxFolder folderToDelete = new BoxFolder(api, parentFolderId);
        folderToDelete.delete(true);
    }
}

