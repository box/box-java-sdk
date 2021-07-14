package com.box.sdk;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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

	private static final int DEFAULT_LIMIT = 100;
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
	public static BoxSignRequest.Info createSignRequest(BoxAPIConnection api, List<BoxSignRequestNewSigner> signers,
														List<BoxSignRequestFile> sourceFiles, String parentFolderId)
	{
		return createSignRequest(api, signers, sourceFiles, parentFolderId, null);
	}


	//TODO we can use BoxFile.Info instead if we want
	public static BoxSignRequest.Info createSignRequest(BoxAPIConnection api, List<BoxSignRequestNewSigner> signers,
														List<BoxSignRequestFile> sourceFiles, String parentFolderId,
														BoxSignRequestCreateParams optionalParams){

		JsonObject requestJSON = new JsonObject();

		JsonArray signersJSON = new JsonArray();
		for(BoxSignRequestNewSigner signer : signers){
			signersJSON.add(signer.getJSONObject());
		}
		requestJSON.add("signers", signersJSON);

		JsonArray sourceFilesJSON = new JsonArray();
		for(BoxSignRequestFile sourceFile : sourceFiles){
			sourceFilesJSON.add(sourceFile.getJSONObject());
		}
		requestJSON.add("source_files", sourceFilesJSON);

		JsonObject parentFolderJSON = new JsonObject();
		parentFolderJSON.add("id", parentFolderId);
		parentFolderJSON.add("type", "folder");
		requestJSON.add("parent_folder", parentFolderJSON);

		if (optionalParams != null) {
			optionalParams.appendParamsAsJson(requestJSON);
		}

		URL url = SIGN_REQUESTS_URL_TEMPLATE.build(api.getBaseURL());
		BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");
		request.setBody(requestJSON.toString());
		BoxJSONResponse response = (BoxJSONResponse) request.send();
		JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
		BoxSignRequest signRequest = new BoxSignRequest(api, responseJSON.get("id").asString());
		return signRequest.new Info(responseJSON);
	}

	public BoxSignRequest.Info getInfo(String ... fields) {
		QueryStringBuilder builder = new QueryStringBuilder();
		if (fields.length > 0) {
			builder.appendParam("fields", fields);
		}
		URL url = SIGN_REQUEST_URL_TEMPLATE.buildWithQuery(
				this.getAPI().getBaseURL(), builder.toString(), this.getID());
		BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
		BoxJSONResponse response = (BoxJSONResponse) request.send();
		JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
		return new BoxSignRequest.Info(responseJSON);
	}

	public static Iterable<BoxSignRequest.Info> getAll(final BoxAPIConnection api, String ... fields) {
		return getAll(DEFAULT_LIMIT, api, fields);
	}

	public static Iterable<BoxSignRequest.Info > getAll(int limit, final BoxAPIConnection api, String ... fields) {
		QueryStringBuilder queryString = new QueryStringBuilder();
		if (fields.length > 0) {
			queryString.appendParam("fields", fields);
		}
		URL url = SIGN_REQUESTS_URL_TEMPLATE.buildWithQuery(api.getBaseURL(), queryString.toString());
		return new BoxResourceIterable<BoxSignRequest.Info>(api, url, limit) {

			@Override
			protected BoxSignRequest.Info factory(JsonObject jsonObject) {
				BoxSignRequest signRequest = new BoxSignRequest(api, jsonObject.get("id").asString());
				return signRequest.new Info(jsonObject);
			}

		};
	}

	public BoxSignRequest.Info cancel() {
		throw new NotImplementedException();
	}

	public void resend() {
		throw new NotImplementedException();
	}

	public class Info extends BoxResource.Info {

		private boolean isDocumentPreparationNeeded;
		private String redirectUrl;
		private String declinedRedirectUrl;
		private List<BoxSignRequestRequiredAttachment> requiredAttachments;
		private boolean areAttachmentsEnabled;
		private boolean areTextSignaturesEnabled;
		private boolean isTextEnabled;
		private boolean areDatesEnabled;
		private boolean areEmailsDisabled;
		private String signatureColor;
		private boolean isPhoneVerificationRequiredToView;
		private String emailSubject;
		private String emailMessage;
		private boolean areRemindersEnabled;
		private List<BoxFile.Info> sourceFiles;
		private BoxFolder.Info parentFolder;
		public List<BoxSignRequestSigner> signers;
		private String name;
		private List<BoxSignRequestPrefillTag> prefillTags;
		private Integer daysValid;
		private String externalId;
		private String prepareUrl;
		private BoxFile.Info signingLog;
		private String status;
		private BoxSignRequestSignFiles signFiles;
		private Date autoExpireAt;
		private Date createdAt;
		private Date updatedAt;

		public boolean isDocumentPreparationNeeded() {
			return isDocumentPreparationNeeded;
		}

		public String getRedirectUrl() {
			return redirectUrl;
		}

		public String getDeclinedRedirectUrl() {
			return declinedRedirectUrl;
		}

		public List<BoxSignRequestRequiredAttachment> getRequiredAttachments() {
			return requiredAttachments;
		}

		public boolean areAttachmentsEnabled() {
			return areAttachmentsEnabled;
		}

		public boolean areTextSignaturesEnabled() {
			return areTextSignaturesEnabled;
		}

		public boolean isTextEnabled() {
			return isTextEnabled;
		}

		public boolean areDatesEnabled() {
			return areDatesEnabled;
		}

		public boolean areEmailsDisabled() {
			return areEmailsDisabled;
		}

		public String getSignatureColor() {
			return signatureColor;
		}

		public boolean isPhoneVerificationRequiredToView() {
			return isPhoneVerificationRequiredToView;
		}

		public String getEmailSubject() {
			return emailSubject;
		}

		public String getEmailMessage() {
			return emailMessage;
		}

		public boolean areRemindersEnabled() {
			return areRemindersEnabled;
		}

		public List<BoxFile.Info> getSourceFiles() {
			return sourceFiles;
		}

		public BoxFolder.Info getParentFolder() {
			return parentFolder;
		}

		public List<BoxSignRequestSigner> getSigners() {
			return signers;
		}

		public String getName() {
			return name;
		}

		public List<BoxSignRequestPrefillTag> getPrefillTags() {
			return prefillTags;
		}

		public Integer getDaysValid() {
			return daysValid;
		}

		public String getExternalId() {
			return externalId;
		}

		public String getPrepareUrl() {
			return prepareUrl;
		}

		public BoxFile.Info getSigningLog() {
			return signingLog;
		}

		public String getStatus() {
			return status;
		}

		public BoxSignRequestSignFiles getSignFiles() {
			return signFiles;
		}

		public Date getAutoExpireAt() {
			return autoExpireAt;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public Date getUpdatedAt() {
			return updatedAt;
		}

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

		/**
		 * {@inheritDoc}
		 */
		@Override
		void parseJSONMember(JsonObject.Member member) {
			super.parseJSONMember(member);
			String memberName = member.getName();
			JsonValue value = member.getValue();
			try {
				if (memberName.equals("is_document_preparation_needed")) {
					this.isDocumentPreparationNeeded = value.asBoolean();
				} else if (memberName.equals("redirect_url")) {
					this.redirectUrl = value.asString();
				} else if (memberName.equals("declined_redirect_url")) {
					this.declinedRedirectUrl = value.asString();
				} else if (memberName.equals("required_attachments")) {
					List<BoxSignRequestRequiredAttachment> attachments = new ArrayList<>();
					for (JsonValue attachmentJSON : value.asArray()) {
						BoxSignRequestRequiredAttachment attachment = new BoxSignRequestRequiredAttachment(attachmentJSON.asObject());
						attachments.add(attachment);
					}
					this.requiredAttachments = attachments;
				} else if (memberName.equals("are_attachments_enabled")) {
					this.areAttachmentsEnabled = value.asBoolean();
				} else if (memberName.equals("are_text_signatures_enabled")) {
					this.areTextSignaturesEnabled = value.asBoolean();
				} else if (memberName.equals("is_text_enabled")) {
					this.isTextEnabled = value.asBoolean();
				} else if (memberName.equals("are_dates_enabled")) {
					this.areDatesEnabled = value.asBoolean();
				} else if (memberName.equals("are_emails_disabled")) {
					this.areEmailsDisabled = value.asBoolean();
				} else if (memberName.equals("signature_color")) {
					this.signatureColor = value.asString();
				} else if (memberName.equals("is_phone_verification_required_to_view")) {
					this.isPhoneVerificationRequiredToView = value.asBoolean();
				} else if (memberName.equals("email_subject")) {
					this.emailSubject = value.asString();
				} else if (memberName.equals("email_message")) {
					this.emailMessage = value.asString();
				} else if (memberName.equals("are_reminders_enabled")) {
					this.areRemindersEnabled = value.asBoolean();
				} else if (memberName.equals("signers")) {
					List<BoxSignRequestSigner> signers = new ArrayList<>();
					for (JsonValue signerJSON : value.asArray()) {
						BoxSignRequestSigner signer = new BoxSignRequestSigner(signerJSON.asObject(), getAPI());
						signers.add(signer);
					}
					this.signers = signers;
				} else if (memberName.equals("source_files")) {
					List<BoxFile.Info> files = new ArrayList<>();
					for (JsonValue fileJSON : value.asArray()) {
						String fileID = fileJSON.asObject().get("id").asString();
						BoxFile file = new BoxFile(getAPI(), fileID);
						files.add(file.new Info(fileJSON.asObject()));
					}
					this.sourceFiles = files;
				} else if (memberName.equals("parent_folder")) {
					JsonObject folderJSON = value.asObject();
					String folderID = folderJSON.get("id").asString();
					BoxFolder folder = new BoxFolder(getAPI(), folderID);
					this.parentFolder = folder.new Info(folderJSON);
				} else if (memberName.equals("name")) {
					this.name = value.asString();
				} else if (memberName.equals("prefill_tags")) {
					List<BoxSignRequestPrefillTag> prefillTags = new ArrayList<>();
					for (JsonValue prefillTagJSON : value.asArray()) {
						BoxSignRequestPrefillTag prefillTag = new BoxSignRequestPrefillTag(prefillTagJSON.asObject());
						prefillTags.add(prefillTag);
					}
					this.prefillTags = prefillTags;
				} else if (memberName.equals("days_valid")) {
					this.daysValid = value.asInt();
				} else if (memberName.equals("external_id")) {
					this.externalId = value.asString();
				} else if (memberName.equals("prepare_url")) {
					this.prepareUrl = value.asString();
				} else if (memberName.equals("signing_log")) {
					JsonObject signingLogJSON = value.asObject();
					String fileID = signingLogJSON.get("id").asString();
					BoxFile file = new BoxFile(getAPI(), fileID);
					this.signingLog = file.new Info(signingLogJSON);
				} else if (memberName.equals("status")) {
					this.status = value.asString();
				} else if (memberName.equals("sign_files")) {
					JsonObject signFilesJSON = value.asObject();
					JsonValue filesArray = signFilesJSON.get("files");
					List<BoxFile.Info> files = new ArrayList<>();
					for (JsonValue fileJSON : filesArray.asArray()) {
						String fileID = fileJSON.asObject().get("id").asString();
						BoxFile file = new BoxFile(getAPI(), fileID);
						files.add(file.new Info(fileJSON.asObject()));
					}
					boolean isReadyForDownload = signFilesJSON.get("is_ready_for_download").asBoolean();
					this.signFiles = new BoxSignRequestSignFiles(files, isReadyForDownload);
				} else if (memberName.equals("auto_expire_at")) {
					this.autoExpireAt = BoxDateFormat.parse(value.asString());
				} else if (memberName.equals("created_at")) {
					this.createdAt = BoxDateFormat.parse(value.asString());
				} else if (memberName.equals("updated_at")) {
					this.updatedAt = BoxDateFormat.parse(value.asString());
				}
			} catch (Exception e) {
				throw new BoxDeserializationException(memberName, value.toString(), e);
			}
		}
	}
}
