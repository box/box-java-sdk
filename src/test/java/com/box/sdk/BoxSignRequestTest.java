package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

import com.box.sdk.BoxSignRequestSigner.BoxSignRequestInputContentType;
import com.box.sdk.BoxSignRequestSigner.BoxSignerInput;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * {@link BoxSignRequest} related unit tests.
 */
public class BoxSignRequestTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("https://localhost:%d", wireMockRule.httpsPort()));
    }

    @Test
    public void createSignRequestSucceeds() {
        final String fileId = "12345";
        final String fileName = "Contract.pdf";
        final String signerEmail = "example@gmail.com";
        final String signRequestId = "12345";
        final BoxSignRequestInputContentType contentType = BoxSignRequestInputContentType.Checkbox;
        final String templateId = "93153068-5420-467b-b8ef-aaaaaaaaaaa";

        final String prepareUrl = "https://prepareurl.com";
        final String redirectUrl = "https://box.com/redirect_url";
        final String declinedRedirectUrl = "https://box.com/declined_redirect_url";
        final String iframeableEmedUrl = "https://app.box.com/embed/sign/document/gfhr4222-a331-494b-808b-79bc7f3992a3/f14d7098-a331-494b-808b-79bc7f3992a4";

        final String signerRedirectUrl = "https://box.com/redirect_url_signer_1";
        final String signerDeclinedRedirectUrl = "https://box.com/declined_redirect_url_signer_1";
        final Boolean signerLoginRequired = true;
        final String signerPassword = "password";
        final Boolean signerSuppressNotifications = true;
        final String signerVerficationPhoneNumber = "1234567890";

        String result = TestUtils.getFixture("BoxSignRequest/CreateSignRequest200");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo("/2.0/sign_requests"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        List<BoxSignRequestFile> files = new ArrayList<>();
        BoxSignRequestFile file = new BoxSignRequestFile("12345");
        files.add(file);

        List<BoxSignRequestSigner> signers = new ArrayList<>();
        BoxSignRequestSigner newSigner = new BoxSignRequestSigner("signer@mail.com");
        newSigner.setEmbedUrlExternalUserId("1234");
        newSigner.setLoginRequired(signerLoginRequired);
        newSigner.setPassword(signerPassword);
        newSigner.setSuppressNotifications(signerSuppressNotifications);
        newSigner.setVerificationPhoneNumber(signerVerficationPhoneNumber);
        signers.add(newSigner);

        String parentFolderId = "55555";

        BoxSignRequestCreateParams params = new BoxSignRequestCreateParams();
        params.setTemplateId(templateId);
        params.setPrefillTags(Collections.singletonList(new BoxSignRequestPrefillTag("id", "text")));
        BoxSignRequest.Info signRequestInfo = BoxSignRequest.createSignRequest(this.api, files,
            signers, parentFolderId, params);

        BoxFile.Info fileInfo = signRequestInfo.getSourceFiles().get(0);
        BoxSignRequestSigner signer = signRequestInfo.getSigners().get(0);
        BoxSignerInput input = signer.getInputs().get(0);
        assertEquals(signerRedirectUrl, signer.getRedirectUrl());
        assertEquals(iframeableEmedUrl, signer.getIframeableEmedUrl());
        assertEquals(signerDeclinedRedirectUrl, signer.getDeclinedRedirectUrl());
        assertEquals(signerLoginRequired, signer.getLoginRequired());
        assertEquals(signerSuppressNotifications, signer.getSuppressNotifications());
        assertEquals(signerVerficationPhoneNumber, signer.getVerificationPhoneNumber());

        assertEquals(prepareUrl, signRequestInfo.getPrepareUrl());
        assertEquals(redirectUrl, signRequestInfo.getRedirectUrl());
        assertEquals(declinedRedirectUrl, signRequestInfo.getDeclinedRedirectUrl());
        assertEquals(fileId, fileInfo.getID());
        assertEquals(fileName, fileInfo.getName());
        assertEquals(signerEmail, signer.getEmail());
        assertEquals(signRequestId, signRequestInfo.getID());
        assertEquals(contentType, input.getContentType());
        assertEquals(templateId, signRequestInfo.getTemplateId());

    }

    @Test
    public void getSignRequestInfoSucceeds() {
        final String fileId = "12345";
        final String fileName = "Contract.pdf";
        final String signerEmail = "example@gmail.com";
        final String signRequestId = "12345";
        BoxSignRequestInputContentType contentType = BoxSignRequestInputContentType.Checkbox;

        final String prepareUrl = "https://prepareurl.com";
        final String redirectUrl = "https://box.com/redirect_url";
        final String declinedRedirectUrl = "https://box.com/declined_redirect_url";
        final String iframeableEmedUrl = "https://app.box.com/embed/sign/document/gfhr4222-a331-494b-808b-79bc7f3992a3/f14d7098-a331-494b-808b-79bc7f3992a4";

        final String signerRedirectUrl = "https://box.com/redirect_url_signer_1";
        final String signerDeclinedRedirectUrl = "https://box.com/declined_redirect_url_signer_1";
        final Boolean signerLoginRequired = true;
        final Boolean signerSuppressNotifications = true;
        final String signerVerficationPhoneNumber = "1234567890";
        final String signerDecisionAdditionalInfo = "Requesting changes before signing.";

        final String templateId = "93153068-5420-467b-b8ef-aaaaaaaaaaa";

        final String requestUrl = "/2.0/sign_requests/" + signRequestId;

        String result = TestUtils.getFixture("BoxSignRequest/GetSignRequest200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(requestUrl))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxSignRequest signRequest = new BoxSignRequest(this.api, signRequestId);
        BoxSignRequest.Info signRequestInfo = signRequest.getInfo();

        BoxFile.Info fileInfo = signRequestInfo.getSourceFiles().get(0);
        BoxSignRequestSigner signer = signRequestInfo.getSigners().get(0);
        BoxSignerInput input = signer.getInputs().get(0);
        assertEquals(signerRedirectUrl, signer.getRedirectUrl());
        assertEquals(signerDeclinedRedirectUrl, signer.getDeclinedRedirectUrl());
        assertEquals(iframeableEmedUrl, signer.getIframeableEmedUrl());
        assertEquals(signerLoginRequired, signer.getLoginRequired());
        assertEquals(signerSuppressNotifications, signer.getSuppressNotifications());
        assertEquals(signerVerficationPhoneNumber, signer.getVerificationPhoneNumber());
        assertEquals(signerDecisionAdditionalInfo, signer.getSignerDecision().getAdditionalInfo());

        assertEquals(prepareUrl, signRequestInfo.getPrepareUrl());
        assertEquals(redirectUrl, signRequestInfo.getRedirectUrl());
        assertEquals(declinedRedirectUrl, signRequestInfo.getDeclinedRedirectUrl());
        assertEquals(fileId, fileInfo.getID());
        assertEquals(fileName, fileInfo.getName());
        assertEquals(signerEmail, signer.getEmail());
        assertEquals(signRequestId, signRequestInfo.getID());
        assertEquals(contentType, input.getContentType());
        assertEquals(templateId, signRequestInfo.getTemplateId());
    }

    @Test
    public void getAllSignRequestsSucceeds() {
        final String fileId = "12345";
        final String fileName = "Contract.pdf";
        final String signerEmail = "example@gmail.com";
        final String signRequestId = "12345";
        final BoxSignRequestInputContentType contentType = BoxSignRequestInputContentType.Checkbox;

        final String prepareUrl = "https://prepareurl.com";
        final String redirectUrl = "https://box.com/redirect_url";
        final String declinedRedirectUrl = "https://box.com/declined_redirect_url";
        final String iframeableEmedUrl = "https://app.box.com/embed/sign/document/gfhr4222-a331-494b-808b-79bc7f3992a3/f14d7098-a331-494b-808b-79bc7f3992a4";

        final String signerRedirectUrl = "https://box.com/redirect_url_signer_1";
        final String signerDeclinedRedirectUrl = "https://box.com/declined_redirect_url_signer_1";
        final String templateId = "93153068-5420-467b-b8ef-aaaaaaaaaaa";

        final String requestUrl = "/2.0/sign_requests";

        String result = TestUtils.getFixture("BoxSignRequest/GetAllSignRequests200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(requestUrl))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        Iterator<BoxSignRequest.Info> signRequests = BoxSignRequest.getAll(this.api).iterator();
        BoxSignRequest.Info firstSignRequest = signRequests.next();

        BoxFile.Info fileInfo = firstSignRequest.getSourceFiles().get(0);
        BoxSignRequestSigner signer = firstSignRequest.getSigners().get(0);
        BoxSignerInput input = signer.getInputs().get(0);
        assertEquals(signerRedirectUrl, signer.getRedirectUrl());
        assertEquals(signerDeclinedRedirectUrl, signer.getDeclinedRedirectUrl());
        assertEquals(iframeableEmedUrl, signer.getIframeableEmedUrl());

        assertEquals(prepareUrl, firstSignRequest.getPrepareUrl());
        assertEquals(redirectUrl, firstSignRequest.getRedirectUrl());
        assertEquals(declinedRedirectUrl, firstSignRequest.getDeclinedRedirectUrl());
        assertEquals(fileId, fileInfo.getID());
        assertEquals(fileName, fileInfo.getName());
        assertEquals(signerEmail, signer.getEmail());
        assertEquals(signRequestId, firstSignRequest.getID());
        assertEquals(contentType, input.getContentType());
        assertEquals(templateId, firstSignRequest.getTemplateId());
    }

    @Test
    public void cancelSignRequestSucceeds() {
        final String signRequestId = "12345";

        final String requestUrl = "/2.0/sign_requests/" + signRequestId + "/cancel";

        String result = TestUtils.getFixture("BoxSignRequest/CancelSignRequest200");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(requestUrl))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxSignRequest signRequest = new BoxSignRequest(this.api, signRequestId);
        BoxSignRequest.Info signRequestInfo = signRequest.cancel();

        assertEquals(BoxSignRequest.BoxSignRequestStatus.Cancelled, signRequestInfo.getStatus());
    }

    @Test
    public void resendSignRequestSucceeds() {
        final String signRequestId = "12345";

        final String requestUrl = "/2.0/sign_requests/" + signRequestId + "/resend";

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(requestUrl))
            .willReturn(WireMock.aResponse()
                .withStatus(202)));

        BoxSignRequest signRequest = new BoxSignRequest(this.api, signRequestId);

        signRequest.resend();

        WireMock.verify(1, postRequestedFor(urlPathEqualTo(requestUrl)));
    }
}

