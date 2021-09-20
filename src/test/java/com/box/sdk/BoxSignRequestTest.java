package com.box.sdk;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.jose4j.jwt.NumericDate;
import org.junit.*;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.box.sdk.UniqueTestFolder.*;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.Assert.*;

/**
 * {@link BoxSignRequest} related unit tests.
 */
public class BoxSignRequestTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @BeforeClass
    public static void setup() {
        setupUniqeFolder();
    }

    @AfterClass
    public static void tearDown() {
        removeUniqueFolder();
    }

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

        List<BoxSignRequestFile> files = new ArrayList<>();
        BoxSignRequestFile file = new BoxSignRequestFile("12345");
        files.add(file);

        List<BoxSignRequestSigner> signers = new ArrayList<>();
        BoxSignRequestSigner newSigner = new BoxSignRequestSigner("signer@mail.com");
        signers.add(newSigner);

        String parentFolderId = "55555";
        BoxSignRequest.Info signRequestInfo = BoxSignRequest.createSignRequest(this.api, files,
                signers, parentFolderId);

        BoxFile.Info fileInfo = signRequestInfo.getSourceFiles().get(0);
        BoxSignRequestSigner signer = signRequestInfo.getSigners().get(0);

        assertEquals(prepareUrl, signRequestInfo.getPrepareUrl());
        assertEquals(fileId, fileInfo.getID());
        assertEquals(fileName, fileInfo.getName());
        assertEquals(signerEmail, signer.getEmail());
        assertEquals(signRequestId, signRequestInfo.getID());
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

        assertEquals(prepareUrl, signRequestInfo.getPrepareUrl());
        assertEquals(fileId, fileInfo.getID());
        assertEquals(fileName, fileInfo.getName());
        assertEquals(signerEmail, signer.getEmail());
        assertEquals(signRequestId, signRequestInfo.getID());
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

        assertEquals(prepareUrl, firstSignRequest.getPrepareUrl());
        assertEquals(fileId, fileInfo.getID());
        assertEquals(fileName, fileInfo.getName());
        assertEquals(signerEmail, signer.getEmail());
        assertEquals(signRequestId, firstSignRequest.getID());
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

        assertEquals(BoxSignRequest.BoxSignRequestStatus.Cancelled, signRequestInfo.getStatus());
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
    @Category(IntegrationTest.class)
    @Ignore("500 error is returned")
    public void signRequestIntegrationTest() throws InterruptedException {
        // Test Setup
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder uniqueFolder = getUniqueFolder(api);
        String fileName = "signRequestIntegrationTest " + NumericDate.now().getValue();
        BoxFile file = uploadFileToUniqueFolderWithSomeContent(api, fileName);
        List<BoxSignRequestFile> files = new ArrayList<>();
        BoxSignRequestFile fileSignRequest = new BoxSignRequestFile(file.getID());
        files.add(fileSignRequest);

        String signerEmail = "user@example.com";
        List<BoxSignRequestSigner> signers = new ArrayList<>();
        BoxSignRequestSigner newSigner = new BoxSignRequestSigner(signerEmail).setInPerson(false);
        signers.add(newSigner);

        BoxFolder signedFileFolder = uniqueFolder.createFolder("Folder - signRequestIntegrationTest").getResource();

        // Do Create
        BoxSignRequestCreateParams createParams = new BoxSignRequestCreateParams()
                .setIsDocumentPreparationNeeded(true);
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
        BoxSignRequest.Info signRequestInfoCancel = signRequestGetByID.cancel();
        BoxSignRequest signRequestGetByIDAfterCancel = new BoxSignRequest(api, signRequestIdCreate);
        BoxSignRequest.Info signRequestInfoAfterCancel = signRequestGetByIDAfterCancel.getInfo();
        BoxSignRequest.BoxSignRequestStatus signRequestStatusAfterCancel = signRequestInfoAfterCancel.getStatus();

        // Test Cancel
        assertEquals(BoxSignRequest.BoxSignRequestStatus.Cancelled, signRequestInfoCancel.getStatus());
        assertEquals(BoxSignRequest.BoxSignRequestStatus.Cancelled, signRequestStatusAfterCancel);

        // Clean up
        List<BoxFile.Info> signRequestFiles = signRequestInfoCancel.getSignFiles().getFiles();
        for (BoxFile.Info signRequestFile : signRequestFiles) {
            BoxFile fileToDelete = new BoxFile(api, signRequestFile.getID());
            fileToDelete.delete();
        }

        // Deleting the folder before the above file will cause sign request 500s on future calls
        Thread.sleep(3000);
        signedFileFolder.delete(true);
        file.delete();
    }
}

