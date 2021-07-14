package com.box.sdk;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.text.ParseException;
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
	public void createSignRequestSucceeds() throws IOException, ParseException {
		String result = "";
		final String fileId = "12345";
		final String fileName = "Contract.pdf";
		final String signerEmail = "example@gmail.com";
		final String signerName = "Aaron Levie";
		final String signRequestId = "12345";

		final String prepareUrl = "https://prepareurl.com";

		result = TestConfig.getFixture("BoxSignRequest/CreateSignRequest200");

		WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo("/sign_requests"))
				.willReturn(WireMock.aResponse()
						.withHeader("Content-Type", "application/json")
						.withBody(result)));

		//TODO create
		List<BoxSignRequestNewSigner> signers = new ArrayList<BoxSignRequestNewSigner>();
		List<BoxSignRequestFile> files = new ArrayList<BoxSignRequestFile>();
		String parentFolderId = "55555";

		BoxSignRequest.Info signRequestInfo = BoxSignRequest.createSignRequest(api, signers, files, parentFolderId);

		BoxFile.Info fileInfo = signRequestInfo.getSourceFiles().get(0);
		BoxSignRequestSigner signer = signRequestInfo.getSigners().get(0);

		Assert.assertEquals(prepareUrl, signRequestInfo.getPrepareUrl().toString());
		Assert.assertEquals(fileId, fileInfo.getID());
		Assert.assertEquals(fileName, fileInfo.getName());
		Assert.assertEquals(signerEmail, signer.getEmail());
		Assert.assertEquals(signerName, signer.getName());
		Assert.assertEquals(signRequestId, signRequestInfo.getID());
	}

	@Test
	@Category(UnitTest.class)
	public void getSignRequestInfoSucceeds() throws IOException {
		String result = "";
		final String fileId = "12345";
		final String fileName = "Contract.pdf";
		final String signerEmail = "example@gmail.com";
		final String signerName = "Aaron Levie";
		final String signRequestId = "12345";

		final String prepareUrl = "https://prepareurl.com";

		final String requestUrl = "/sign_requests/" + signRequestId;

		result = TestConfig.getFixture("BoxSignRequest/GetSignRequest200");

		WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(requestUrl))
				.willReturn(WireMock.aResponse()
						.withHeader("Content-Type", "application/json")
						.withBody(result)));

		BoxSignRequest signRequest = new BoxSignRequest(this.api, signRequestId);
		BoxSignRequest.Info signRequestInfo = signRequest.getInfo();

		BoxFile.Info fileInfo = signRequestInfo.getSourceFiles().get(0);
		BoxSignRequestSigner signer = signRequestInfo.getSigners().get(0);

		Assert.assertEquals(prepareUrl, signRequestInfo.getPrepareUrl().toString());
		Assert.assertEquals(fileId, fileInfo.getID());
		Assert.assertEquals(fileName, fileInfo.getName());
		Assert.assertEquals(signerEmail, signer.getEmail());
		Assert.assertEquals(signerName, signer.getName());
		Assert.assertEquals(signRequestId, signRequestInfo.getID());
	}

	@Test
	@Category(UnitTest.class)
	public void getAllSignRequestsSucceeds() throws IOException {
		String result = "";
		final String fileId = "12345";
		final String fileName = "Contract.pdf";
		final String signerEmail = "example@gmail.com";
		final String signerName = "Aaron Levie";
		final String signRequestId = "12345";

		final String prepareUrl = "https://prepareurl.com";

		final String requestUrl = "/sign_requests";

		result = TestConfig.getFixture("BoxSignRequest/GetAllSignRequests200");

		WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(requestUrl))
				.willReturn(WireMock.aResponse()
						.withHeader("Content-Type", "application/json")
						.withBody(result)));

		Iterator<BoxSignRequest.Info> signRequests = BoxSignRequest.getAll(this.api).iterator();
		BoxSignRequest.Info firstSignRequest = signRequests.next();

		BoxFile.Info fileInfo = firstSignRequest.getSourceFiles().get(0);
		BoxSignRequestSigner signer = firstSignRequest.getSigners().get(0);

		Assert.assertEquals(prepareUrl, firstSignRequest.getPrepareUrl().toString());
		Assert.assertEquals(fileId, fileInfo.getID());
		Assert.assertEquals(fileName, fileInfo.getName());
		Assert.assertEquals(signerEmail, signer.getEmail());
		Assert.assertEquals(signerName, signer.getName());
		Assert.assertEquals(signRequestId, firstSignRequest.getID());
	}

	@Test
	@Category(UnitTest.class)
	public void cancelSignRequestSucceeds() throws IOException {
		String result = "";
		final String fileId = "12345";
		final String fileName = "Contract.pdf";
		final String signerEmail = "example@gmail.com";
		final String signerName = "Aaron Levie";
		final String signRequestId = "12345";

		final String requestUrl = "/sign_requests/" + signRequestId + "/cancel";

		result = TestConfig.getFixture("BoxSignRequest/CancelSignRequest200");

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
		signRequest.resend();

		//TODO check statuscode
		WireMock.verify(1, postRequestedFor(urlPathEqualTo(requestUrl)));
	}

/*	@Test
	@Category(IntegrationTest.class)
	public void createAndDownloadZipSucceeds() throws IOException {
		BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

		BoxSignatureDocument doc1 = new BoxSignatureDocument(id, fileVersionId);
		BoxSignatureDocument doc2 = new BoxSignatureDocument(id, fileVersionId);
		ArrayList<BoxSignatureDocument> documents = new ArrayList<BoxZipItem>();
		documents.add(doc1);
		documents.add(doc2);

		BoxSigner signer1 = new BoxSigner(email, name, isInPerson, order, language, verificationPhoneNumber,
				embedUrlExternalUserId, redirectUrl, declinedRedirectUrl, verificationPassword);

		BoxSigner signer1 = new BoxSigner()
				.SetEmail("aaa@")
				.SetName("name");

		BoxSigner signer1 = new BoxSigner(boxUserId);

		BoxSigner signer1 = new BoxSigner(boxUserId, createSignerParams);

		BoxSigner signer2 = new BoxSigner(email, name, isInPerson, order, language, verificationPhoneNumber,
				embedUrlExternalUserId, redirectUrl, declinedRedirectUrl, verificationPassword);

		ArrayList<BoxSigner> signers = new ArrayList<BoxZipItem>();
		signers.add(signer1);
		signers.add(signer2);

		BoxSignRequest response = new BoxSignRequest(api).Create(documents, signers, params...);
		// Can use object to store params like CreateUserParams
		// Use different constructors (?)
		// With Custom

		// Get by id
		BoxSignRequest signRequest = new BoxSignRequestst(this.api, response.Id);
		BoxSignRequest.Info signRequestInfo = signRequest.Info();

		// Get all
		Iterator<BoxSignRequest.Info> signRequests = BoxSignRequest.getAll(this.api).iterator();
		BoxSignRequest.Info firstSignRequest = signRequests.next();

		// Cancel
		BoxSignRequest signRequest = new BoxSignRequest(this.api, signRequestId);
		BoxSignRequestCancelStatus signRequestInfo = signRequest.Cancel();

		// Resend
		BoxSignRequest signRequest = new BoxSignRequest(this.api, signRequestId);
		BoxSignRequestResendStatus signRequestInfo = signRequest.Resend();
	}*/
}

