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
 * Represents a sign request.
 * A retention policy blocks permanent deletion of content for a specified amount of time.
 * Admins can create retention policies and then later assign them to specific folders or their entire enterprise.
 *
 * @see <a href="https://docs.box.com/reference#sign-request-object">Box sign request</a>
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("sign_request")
public class BoxSignRequest extends BoxResource{

	/**
	 * The URL template used for operation with sign requests.
	 */
	public static final URLTemplate SIGN_REQUESTS_URL_TEMPLATE = new URLTemplate("sign_requests");

	/**
	 * The URL template used for operation with sign request with given ID.
	 */
	public static final URLTemplate SIGN_REQUEST_URL_TEMPLATE = new URLTemplate("sign_requests/%s");

	/**
	 * The URL template used to cancel existing sign request.
	 */
	public static final URLTemplate SIGN_REQUEST_CANCEL_URL_TEMPLATE = new URLTemplate("sign_requests/%s/cancel");

	/**
	 * The URL template used to resend existing sign request.
	 */
	public static final URLTemplate SIGN_REQUEST_RESEND_URL_TEMPLATE = new URLTemplate("sign_requests/%s/resend");

	/**
	 * The default limit of entries per response.
	 */
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

	/**
	 * Used to create a new sign request.
	 * @param api the API connection to be used by the created user.
	 * @param signers the list of signers for this sign request.
	 * @param sourceFiles the list of files to a signing document from.
	 * @param parentFolderId the id of destination folder to place sign request specific data
	 * @return the created sign request's info.               .
	 */
	public static BoxSignRequest.Info createSignRequest(BoxAPIConnection api, List<BoxSignRequestCreateSigner> signers,
														List<BoxSignRequestFile> sourceFiles, String parentFolderId)
	{
		return createSignRequest(api, signers, sourceFiles, parentFolderId, null);
	}

	/**
	 * Used to create a new sign request with optional parameters.
	 * @param api the API connection to be used by the created user.
	 * @param signers the list of signers for this sign request.
	 * @param sourceFiles the list of files to a signing document from.
	 * @param parentFolderId the id of destination folder to place sign request specific data
	 * @param optionalParams the optional parameters.
	 * @return the created sign request's info.               .
	 */
	public static BoxSignRequest.Info createSignRequest(BoxAPIConnection api, List<BoxSignRequestCreateSigner> signers,
														List<BoxSignRequestFile> sourceFiles, String parentFolderId,
														BoxSignRequestCreateParams optionalParams){

		JsonObject requestJSON = new JsonObject();

		JsonArray signersJSON = new JsonArray();
		for(BoxSignRequestCreateSigner signer : signers){
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

	/**
	 * Returns information about this sign request.
	 * @param fields the fields to retrieve.
	 * @return information about this sign request.
	 */
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

	/**
	 * Returns all the sign requests.
	 * @param api the API connection to be used by the resource.
	 * @param fields the fields to retrieve.
	 * @return an iterable with all the sign requests.
	 */
	public static Iterable<BoxSignRequest.Info> getAll(final BoxAPIConnection api, String ... fields) {
		return getAll(DEFAULT_LIMIT, api, fields);
	}

	/**
	 * Returns all the sign requests.
	 * @param limit the limit of items per single response. The default value is 100.
	 * @param api the API connection to be used by the resource.
	 * @param fields the fields to retrieve.
	 * @return an iterable with all the sign requests.
	 */
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

	/**
	 * Cancels a sign request.
	 * @return the created sign request's info.
	 */
	public BoxSignRequest.Info cancel() {
		URL url = SIGN_REQUEST_CANCEL_URL_TEMPLATE.build(getAPI().getBaseURL(), this.getID());
		BoxJSONRequest request = new BoxJSONRequest(getAPI(), url, "POST");
		BoxJSONResponse response = (BoxJSONResponse) request.send();
		JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
		return new BoxSignRequest.Info(responseJSON);
	}

	/**
	 * Resends a sign request to all signers that have not signed yet.
	 * @return true if request was successful, otherwise false.
	 */
	public boolean resend() {
		URL url = SIGN_REQUEST_RESEND_URL_TEMPLATE.build(getAPI().getBaseURL(), this.getID());
		BoxJSONRequest request = new BoxJSONRequest(getAPI(), url, "POST");
		BoxAPIResponse response = request.send();
		return response.getResponseCode() == 202;
	}

	/**
	 * Contains information about the sign request.
	 */
	public class Info extends BoxResource.Info {

		/**
		 * @see #getIsDocumentPreparationNeeded()
		 */
		private boolean isDocumentPreparationNeeded;

		/**
		 * @see #getRedirectUrl()
		 */
		private String redirectUrl;

		/**
		 * @see #getDeclinedRedirectUrl()
		 */
		private String declinedRedirectUrl;

		/**
		 * @see #getRequiredAttachments()
		 */
		private List<BoxSignRequestRequiredAttachment> requiredAttachments;

		/**
		 * @see #getAreAttachmentsEnabled()
		 */
		private boolean areAttachmentsEnabled;

		/**
		 * @see #getAreTextSignaturesEnabled()
		 */
		private boolean areTextSignaturesEnabled;

		/**
		 * @see #getIsTextEnabled()
		 */
		private boolean isTextEnabled;

		/**
		 * @see #getAreDatesEnabled()
		 */
		private boolean areDatesEnabled;

		/**
		 * @see #getAreEmailsDisabled()
		 */
		private boolean areEmailsDisabled;

		/**
		 * @see #getSignatureColor()
		 */
		private BoxSignRequestSignatureColor signatureColor;

		/**
		 * @see #getIsPhoneVerificationRequiredToView()
		 */
		private boolean isPhoneVerificationRequiredToView;

		/**
		 * @see #getEmailSubject()
		 */
		private String emailSubject;

		/**
		 * @see #getEmailMessage()
		 */
		private String emailMessage;

		/**
		 * @see #getAreRemindersEnabled()
		 */
		private boolean areRemindersEnabled;

		/**
		 * @see #getSourceFiles()
		 */
		private List<BoxFile.Info> sourceFiles;

		/**
		 * @see #getParentFolder()
		 */
		private BoxFolder.Info parentFolder;

		/**
		 * @see #getSigners()
		 */
		public List<BoxSignRequestSigner> signers;

		/**
		 * @see #getName()
		 */
		private String name;

		/**
		 * @see #getPrefillTags()
		 */
		private List<BoxSignRequestPrefillTag> prefillTags;

		/**
		 * @see #getDaysValid()
		 */
		private Integer daysValid;

		/**
		 * @see #getExternalId()
		 */
		private String externalId;

		/**
		 * @see #getPrepareUrl()
		 */
		private String prepareUrl;

		/**
		 * @see #getSigningLog()
		 */
		private BoxFile.Info signingLog;

		/**
		 * @see #getStatus()
		 */
		private String status;

		/**
		 * @see #getSignFiles()
		 */
		private BoxSignRequestSignFiles signFiles;

		/**
		 * @see #getAutoExpireAt()
		 */
		private Date autoExpireAt;

		/**
		 * @see #getCreatedAt()
		 */
		private Date createdAt;

		/**
		 * @see #getUpdatedAt()
		 */
		private Date updatedAt;

		/**
		 * Gets the flag indicating if the sender should be taken into the builder flow to prepare the document.
		 * @return true if document preparation is needed, otherwise false.
		 */
		public boolean getIsDocumentPreparationNeeded() {
			return isDocumentPreparationNeeded;
		}

		/**
		 * Gets the uri that a signer will be redirect to after signing a document.
		 * If no declined redirect url is specified, this will be used for decline actions as well.
		 * @return redirect url.
		 */
		public String getRedirectUrl() {
			return redirectUrl;
		}

		/**
		 * Gets the uri that a signer will be redirect to after declining to sign a document.
		 * @return declined redirect url.
		 */
		public String getDeclinedRedirectUrl() {
			return declinedRedirectUrl;
		}

		/**
		 * Gets the attachments that signers are required to upload.
		 * @return list of attachments.
		 */
		public List<BoxSignRequestRequiredAttachment> getRequiredAttachments() {
			return requiredAttachments;
		}

		/**
		 * Gets the flag indicating if uploading/adding attachments for signers is enabled.
		 * @return true if attachments uploading/adding is enabled for signers, otherwise false.
		 */
		public boolean getAreAttachmentsEnabled() {
			return areAttachmentsEnabled;
		}

		/**
		 * Gets the flag indicating if usage of signatures generated by typing (text) is enabled.
		 * @return true if text signatures are enabled, otherwise false.
		 */
		public boolean getAreTextSignaturesEnabled() {
			return areTextSignaturesEnabled;
		}

		/**
		 * Gets the flag indicating if ability for signer to add text is enabled.
		 * @return true if ability for signer to add text is enabled, otherwise false.
		 */
		public boolean getIsTextEnabled() {
			return isTextEnabled;
		}

		/**
		 * Gets the flag indicating if ability for signer to add dates is enabled.
		 * @return true if ability for signer to add dates is enabled, otherwise false.
		 */
		public boolean getAreDatesEnabled() {
			return areDatesEnabled;
		}

		/**
		 * Gets the flag indicating if all status emails, as well as the original email that contains the sign request are disabled.
		 * @return true if emails are disabled, otherwise false.
		 */
		public boolean getAreEmailsDisabled() {
			return areEmailsDisabled;
		}

		/**
		 * Gets the forced, specific color for the signature.
		 * @return signature color (blue, black, red).
		 */
		public BoxSignRequestSignatureColor getSignatureColor() {
			return signatureColor;
		}

		/**
		 * Gets the flag indicating if signers are forced to verify a text message prior to viewing the document.
		 * @return true if phone verification is required to view document, otherwise false.
		 */
		public boolean getIsPhoneVerificationRequiredToView() {
			return isPhoneVerificationRequiredToView;
		}

		/**
		 * Gets the subject of sign request email.
		 * @return subject of sign request email.
		 */
		public String getEmailSubject() {
			return emailSubject;
		}

		/**
		 * Gets the message to include in sign request email.
		 * @return message of sign request email.
		 */
		public String getEmailMessage() {
			return emailMessage;
		}

		/**
		 * Gets the flag indicating if remind for signers to sign a document on day 3, 8, 13 and 18
		 * (or less if the document has been digitally signed already) is enabled.
		 * @return true if reminders are enabled, otherwise false.
		 */
		public boolean getAreRemindersEnabled() {
			return areRemindersEnabled;
		}

		/**
		 * Gets the list of files to create a signing document from.
		 * @return list of files to create a signing document from.
		 */
		public List<BoxFile.Info> getSourceFiles() {
			return sourceFiles;
		}

		/**
		 * Gets the destination folder to place sign request specific data in (copy of source files, signing log etc.)
		 * @return destination folder to place sign request specific data in.
		 */
		public BoxFolder.Info getParentFolder() {
			return parentFolder;
		}

		/**
		 * Gets the list of signers for this sign request.
		 * @return list of signers for this sign request.
		 */
		public List<BoxSignRequestSigner> getSigners() {
			return signers;
		}

		/**
		 * Gets the name of this sign request.
		 * @return name of this sign request.
		 */
		public String getName() {
			return name;
		}

		/**
		 * Gets the list of prefill tags.
		 * @return list of prefill tags.
		 */
		public List<BoxSignRequestPrefillTag> getPrefillTags() {
			return prefillTags;
		}

		/**
		 * Gets the number of days after which this request will automatically expire if not completed.
		 * @return number of days after which this request will automatically expire if not completed.
		 */
		public Integer getDaysValid() {
			return daysValid;
		}

		/**
		 * Gets the reference id in an external system that this sign request is related to.
		 * @return external id.
		 */
		public String getExternalId() {
			return externalId;
		}

		/**
		 * Gets the URL that can be used by the sign request sender to prepare the document through the UI.
		 * @return prepare url.
		 */
		public String getPrepareUrl() {
			return prepareUrl;
		}

		/**
		 * Gets the reference to a file that will hold a log of all signer activity for this request.
		 * @return signing log.
		 */
		public BoxFile.Info getSigningLog() {
			return signingLog;
		}

		/**
		 * Gets the status of the sign request.
		 * @return sign request's status.
		 */
		public String getStatus() {
			return status;
		}

		/**
		 * Gets the list of files that signing events will occur on - these are copies of the original source files.
		 * These files will be updated as signers add inputs to the file and can be downloaded at any point in the signing process.
		 * @return sign files.
		 */
		public BoxSignRequestSignFiles getSignFiles() {
			return signFiles;
		}

		/**
		 * Gets the date and time calculated using daysValid after which a non finished document will be automatically expired.
		 * @return auto expires at date.
		 */
		public Date getAutoExpireAt() {
			return autoExpireAt;
		}

		/**
		 * Gets the date/time that the sign request was created.
		 * @return created at date.
		 */
		public Date getCreatedAt() {
			return createdAt;
		}

		/**
		 * Gets the date/time that the sign request was last updated.
		 * @return update at date.
		 */
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

		/**
		 * {@inheritDoc}
		 */
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
					this.signatureColor = BoxSignRequestSignatureColor.fromJSONString(value.asString());
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

		/**
		 * List of files that signing events will occur on - these are copies of the original source files.
		 * These files will be updated as signers add inputs to the file and can be downloaded at any point in the signing process.
		 */
		public class BoxSignRequestSignFiles  {
			private List<BoxFile.Info> files;
			private boolean isReadyToDownload;

			/**
			 * Constructs a BoxSignRequestSignFiles.
			 *
			 * @param files list that signing events will occur on.
			 * @param isReadyToDownload indicating whether a change to the document is processing.
			 * @return BoxSignRequestSignFiles object.
			 */
			public BoxSignRequestSignFiles(List<BoxFile.Info> files, boolean isReadyToDownload) {
				this.files = files;
				this.isReadyToDownload = isReadyToDownload;
			}

			/**
			 * Gets the list of files that signing events will occur on - these are copies of the original source files..
			 * @return list of files.
			 */
			public List<BoxFile.Info> getFiles() { return files; }

			/**
			 * Gets the flag indicating whether a change to the document is processing and the PDF may be out of date.
			 * It is recommended to wait until processing has finished before downloading the PDF.
			 * Webhooks are not sent until processing has been completed.
			 * @return true if files are ready to download, otherwise false.
			 */
			public boolean getIsReadyToDownload() { return isReadyToDownload; }
		}

	}
}
