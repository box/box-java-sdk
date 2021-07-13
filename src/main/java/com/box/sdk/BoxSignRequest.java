package com.box.sdk;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.URL;
import java.util.List;

/**
 *
 */

@BoxResourceType("sign_request")
public class BoxSignRequest extends BoxResource{

	public static final URLTemplate SIGN_REQUESTS_URL_TEMPLATE = new URLTemplate("sign_requests");

	public static final URLTemplate SIGN_REQUEST_URL_TEMPLATE = new URLTemplate("sign_requests/%s");

	public static final URLTemplate SIGN_REQUEST_CANCEL_URL_TEMPLATE = new URLTemplate("sign_requests/%s/cancel");

	public static final URLTemplate SIGN_REQUEST_RESEND_URL_TEMPLATE = new URLTemplate("sign_requests/%s/resend");

	public boolean isDocumentPreparationNeeded;
	public String redirectUrl;
	public String declinedRedirectUrl;
	//public List<RequiredAttachment> required_attachments;
	public boolean areAttachmentsEnabled;
	public boolean areTextSignaturesEnabled;
	public boolean isTextEnabled;
	public boolean areDatesEnabled;
	public boolean areEmailsDisabled;
	public String signature_color;
	public boolean isPhoneVerificationRequiredToView;
	public String emailSubject;
	public String emailMessage;
	public boolean areRemindersEnabled;
	//public List<Signer> signers;
	//public List<SourceFile> source_files;
	public List<BoxFile> source_files;
	//public List<BoxSignRequestSigner> signers;
	//public ParentFolder parent_folder;
	public String name;
	//public List<PrefillTag> prefill_tags;
	public int daysValid;
	//public String external_id;
	//public String type;
	//public String id;
	public String prepareUrl;
	//public SigningLog signing_log;
	public String status;
	//public SignFiles sign_files;
	//public Date auto_expire_at;
	//public Date created_at;
	//public Date updated_at;

	/**
	 * Constructs a BoxResource for a resource with a given ID.
	 *
	 * @param api the API connection to be used by the resource.
	 * @param id  the ID of the resource.
	 */
	public BoxSignRequest(BoxAPIConnection api, String id) {
		super(api, id);
	}

	//TODO we can use BoxFile.Info instead if we want
	public static BoxSignRequest.Info createSignRequest(BoxAPIConnection api, List<BoxSignRequestSigner> signers,
														List<BoxSignRequestFile> sourceFiles)
	{
		return createSignRequest(api, signers, sourceFiles);
	}


	//TODO we can use BoxFile.Info instead if we want
	public static BoxSignRequest.Info createSignRequest(BoxAPIConnection api, List<BoxSignRequestSigner> signers,
														List<BoxSignRequestFile> sourceFiles, BoxSignRequestCreateParams optionalParams){

		URL url = SIGN_REQUESTS_URL_TEMPLATE.build(api.getBaseURL());
		BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");

		JsonObject requestJSON = new JsonObject();

		JsonArray signersJSON = new JsonArray();
		for(BoxSignRequestSigner signer : signers){
			signersJSON.add(signer.getJSONObject());
		}
		requestJSON.add("signers", signersJSON);

		JsonArray sourceFilesJSON = new JsonArray();
		for(BoxSignRequestFile sourceFile : sourceFiles){
			sourceFilesJSON.add(sourceFile.getJSONObject());
		}
		requestJSON.add("source_files", sourceFilesJSON);

		if (optionalParams != null) {
			///TODO
		}
		request.setBody(requestJSON.toString());
		BoxJSONResponse response = (BoxJSONResponse) request.send();
		JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
		BoxSignRequest signRequest = new BoxSignRequest(api, responseJSON.get("id").asString());
		return signRequest.new Info(responseJSON);
	}

	public BoxSignRequest.Info getInfo(String ... fields) {
		throw new NotImplementedException();
	}

	public static Iterable<BoxSignRequest.Info > getAll(final BoxAPIConnection api, String ... fields) {
		throw new NotImplementedException();
	}

	public BoxSignRequest.Info cancel() {
		throw new NotImplementedException();
	}

	public void resend() {
		throw new NotImplementedException();
	}

	public class Info extends BoxResource.Info {

		/**
		 * Constructs an empty Info object.
		 */
		public Info() {
			super();
		}

		/**
		 * Constructs an Info object by parsing information from a JSON string.
		 * @param  json the JSON string to parse.
		 */
		public Info(String json) {
			super(json);
		}

		/**
		 * Constructs an Info object using an already parsed JSON object.
		 * @param  jsonObject the parsed JSON object.
		 */
		Info(JsonObject jsonObject) {
			super(jsonObject);
		}

		@Override
		public BoxResource getResource() {
			throw new NotImplementedException();
		}
	}
}
