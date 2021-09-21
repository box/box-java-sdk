package com.box.sdk;

import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.Assert.assertEquals;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * {@link BoxSignRequest} related unit tests.
 */
public class BoxSignRequestTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private final BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
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
}

