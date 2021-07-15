package com.box.sdk;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
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
		final String signerName = "Aaron Levie";
		final String signRequestId = "12345";

		final String prepareUrl = "https://prepareurl.com";

		String result = TestConfig.getFixture("BoxSignRequest/CreateSignRequest200");

		WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo("/sign_requests"))
				.willReturn(WireMock.aResponse()
						.withHeader("Content-Type", "application/json")
						.withBody(result)));

		List<BoxSignRequestCreateSigner> signers = new ArrayList<>();
		BoxSignRequestCreateSigner newSigner = new BoxSignRequestCreateSigner("signer@mail.com");
		signers.add(newSigner);

		List<BoxSignRequestFile> files = new ArrayList<>();
		BoxSignRequestFile file = new BoxSignRequestFile("12345");
		files.add(file);

		String parentFolderId = "55555";

		BoxSignRequest.Info signRequestInfo = BoxSignRequest.createSignRequest(api, signers, files, parentFolderId);

		BoxFile.Info fileInfo = signRequestInfo.getSourceFiles().get(0);
		BoxSignRequestSigner signer = signRequestInfo.getSigners().get(0);

		Assert.assertEquals(prepareUrl, signRequestInfo.getPrepareUrl());
		Assert.assertEquals(fileId, fileInfo.getID());
		Assert.assertEquals(fileName, fileInfo.getName());
		Assert.assertEquals(signerEmail, signer.getEmail());
		Assert.assertEquals(signerName, signer.getName());
		Assert.assertEquals(signRequestId, signRequestInfo.getID());
	}

	@Test
	@Category(UnitTest.class)
	public void getSignRequestInfoSucceeds() throws IOException {
		final String fileId = "12345";
		final String fileName = "Contract.pdf";
		final String signerEmail = "example@gmail.com";
		final String signerName = "Aaron Levie";
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
		Assert.assertEquals(signerName, signer.getName());
		Assert.assertEquals(signRequestId, signRequestInfo.getID());
	}

	@Test
	@Category(UnitTest.class)
	public void getAllSignRequestsSucceeds() throws IOException {
		final String fileId = "12345";
		final String fileName = "Contract.pdf";
		final String signerEmail = "example@gmail.com";
		final String signerName = "Aaron Levie";
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
		Assert.assertEquals(signerName, signer.getName());
		Assert.assertEquals(signRequestId, firstSignRequest.getID());
	}

	@Test
	@Category(UnitTest.class)
	public void cancelSignRequestSucceeds() throws IOException {
		final String fileId = "12345";
		final String fileName = "Contract.pdf";
		final String signerEmail = "example@gmail.com";
		final String signerName = "Aaron Levie";
		final String signRequestId = "12345";

		final String requestUrl = "/sign_requests/" + signRequestId + "/cancel";

		String result = TestConfig.getFixture("BoxSignRequest/CancelSignRequest200");

		WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(requestUrl))
				.willReturn(WireMock.aResponse()
						.withHeader("Content-Type", "application/json")
						.withBody(result)));

		BoxSignRequest signRequest = new BoxSignRequest(this.api, signRequestId);
		BoxSignRequest.Info signRequestInfo = signRequest.cancel();

		BoxFile.Info fileInfo = signRequestInfo.getSourceFiles().get(0);
		BoxSignRequestSigner signer = signRequestInfo.getSigners().get(0);

		//TODO check status?
		Assert.assertEquals(fileId, fileInfo.getID());
		Assert.assertEquals(fileName, fileInfo.getName());
		Assert.assertEquals(signerEmail, signer.getEmail());
		Assert.assertEquals(signerName, signer.getName());
		Assert.assertEquals(signRequestId, signRequest.getID());
	}

	@Test
	@Category(UnitTest.class)
	public void resendSignRequestSucceeds(){
		final String signRequestId = "12345";

		final String requestUrl = "/sign_requests/" + signRequestId + "/resend";

		WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(requestUrl))
				.willReturn(WireMock.aResponse()
						.withStatus(202)));

		BoxSignRequest signRequest = new BoxSignRequest(this.api, signRequestId);
		boolean isSuccess = signRequest.resend();

		WireMock.verify(1, postRequestedFor(urlPathEqualTo(requestUrl)));
		Assert.assertTrue(isSuccess);
	}

	@Test
	@Category(IntegrationTest.class)
	public void createSignRequestIntegrationTest() {
		BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

		String signerEmail = "mwoda+staging@boxdemo.com";
		List<BoxSignRequestCreateSigner> signers = new ArrayList<>();
		BoxSignRequestCreateSigner newSigner = new BoxSignRequestCreateSigner(signerEmail)
				.setInPerson(false);
		signers.add(newSigner);

		String fileId = "11438710730";
		List<BoxSignRequestFile> files = new ArrayList<>();
		BoxSignRequestFile file = new BoxSignRequestFile(fileId);
		files.add(file);

		String parentFolderId = "6732314306";

		BoxSignRequestCreateParams createParams = new BoxSignRequestCreateParams()
				.setIsDocumentPreparationNeeded(true);
		BoxSignRequest.Info signRequestInfo = BoxSignRequest.createSignRequest(api, signers, files, parentFolderId, createParams);

		BoxFile.Info fileInfo = signRequestInfo.getSourceFiles().get(0);
		BoxSignRequestSigner signer = signRequestInfo.getSigners().get(0);

		Assert.assertNotNull(signRequestInfo.getPrepareUrl());
		Assert.assertEquals(fileId, fileInfo.getID());
		Assert.assertEquals(signerEmail, signer.getEmail());
		Assert.assertNotNull(signRequestInfo.getID());
	}

	@Test
	@Category(IntegrationTest.class)
	public void getSignRequestIntegrationTest() {
		BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

		final String fileId = "11438710730";
		final String signerEmail = "mwoda+staging@boxdemo.com";
		final String signerName = "Mateusz Woda";
		final String signRequestId = "11446635701-544a1854-c108-4100-9497-4fb7dfd0bcb5";

		BoxSignRequest signRequest = new BoxSignRequest(api, signRequestId);
		BoxSignRequest.Info signRequestInfo = signRequest.getInfo();

		BoxFile.Info fileInfo = signRequestInfo.getSourceFiles().get(0);
		BoxSignRequestSigner signer = signRequestInfo.getSigners().get(0);

		Assert.assertEquals(fileId, fileInfo.getID());
		Assert.assertEquals(signerEmail, signer.getEmail());
		Assert.assertEquals(signerName, signer.getName());
		Assert.assertEquals(signRequestId, signRequestInfo.getID());
	}

	@Test
	@Category(IntegrationTest.class)
	public void getSignRequestsIntegrationTest() {
		BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

		Iterable<BoxSignRequest.Info> signRequests = BoxSignRequest.getAll(api);

		Assert.assertTrue(signRequests.iterator().hasNext());
	}

	//TODO this test is failing because of "cooling-off" period on resend
	@Test
	@Category(IntegrationTest.class)
	public void resendAndCancelSignRequestIntegrationTest() {
		BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

		String signerEmail = "mwoda+staging@boxdemo.com";
		List<BoxSignRequestCreateSigner> signers = new ArrayList<>();
		BoxSignRequestCreateSigner newSigner = new BoxSignRequestCreateSigner(signerEmail)
				.setInPerson(false);
		signers.add(newSigner);

		String fileId = "11438710730";
		List<BoxSignRequestFile> files = new ArrayList<>();
		BoxSignRequestFile file = new BoxSignRequestFile(fileId);
		files.add(file);

		String parentFolderId = "6732314306";

		BoxSignRequest.Info signRequestInfo = BoxSignRequest.createSignRequest(api, signers, files, parentFolderId);

		BoxSignRequest signRequest = new BoxSignRequest(api, signRequestInfo.getID());
		boolean resendResult = signRequest.resend();
		Assert.assertTrue(resendResult);

		signRequestInfo = signRequest.cancel();
		Assert.assertEquals(BoxSignRequest.BoxSignRequestStatus.Converting, signRequestInfo.getStatus());

	}
}

