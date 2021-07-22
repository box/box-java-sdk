package com.box.sdk;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a Sign Request used by Box Sign.
 * Sign Requests are used to request e-signatures on documents from signers.
 * A Sign Request can refer to one or more Box Files and can be sent to one or more Box Sign Request Signers.
 *
 * @see <a href="https://developer.box.com/reference/resources/sign-requests/">Box Sign Request</a>
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("sign_request")
public class BoxSignRequest extends BoxResource {

    /**
     * The URL template used for operation Sign Request operations.
     */
    public static final URLTemplate SIGN_REQUESTS_URL_TEMPLATE = new URLTemplate("sign_requests");

    /**
     * The URL template used for Sign Request operations with a given ID.
     */
    public static final URLTemplate SIGN_REQUEST_URL_TEMPLATE = new URLTemplate("sign_requests/%s");

    /**
     * The URL template used to cancel an existing Sign Request.
     */
    public static final URLTemplate SIGN_REQUEST_CANCEL_URL_TEMPLATE = new URLTemplate("sign_requests/%s/cancel");

    /**
     * The URL template used to resend an existing Sign Request.
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
     * Used to create a new sign request using existing BoxFile.Info models.
     *
     * @param api            the API connection to be used by the created user.
     * @param sourceFiles    the list of BoxFile.Info files to create a signing document from.
     * @param signers        the list of signers for this sign request.
     * @param parentFolderId the id of the destination folder to place sign request specific data in.
     * @param optionalParams the optional parameters.
     * @return the created sign request's info.
     */
    public static BoxSignRequest.Info createSignRequestFromFiles(BoxAPIConnection api,
                                                                    List<BoxFile.Info> sourceFiles,
                                                                    List<BoxSignRequestSigner> signers,
                                                                    String parentFolderId,
                                                                    BoxSignRequestCreateParams optionalParams)
    {
        return createSignRequest(api, toBoxSignRequestFiles(sourceFiles), signers, parentFolderId, optionalParams);
    }

    /**
     * Used to create a new sign request using BoxFile.Info models.
     *
     * @param api            the API connection to be used by the created user.
     * @param sourceFiles    the list of BoxFile.Info files to create a signing document from.
     * @param signers        the list of signers for this sign request.
     * @param parentFolderId the id of the destination folder to place sign request specific data in.
     * @return the created sign request's info.
     */
    public static BoxSignRequest.Info createSignRequestFromFiles(BoxAPIConnection api,
                                                                    List<BoxFile.Info> sourceFiles,
                                                                    List<BoxSignRequestSigner> signers,
                                                                    String parentFolderId)
    {

        return createSignRequest(api, toBoxSignRequestFiles(sourceFiles), signers, parentFolderId, null);
    }

    /**
     * Used to create a new sign request.
     *
     * @param api            the API connection to be used by the created user.
     * @param sourceFiles    the list of files to a signing document from.
     * @param signers        the list of signers for this sign request.
     * @param parentFolderId the id of the destination folder to place sign request specific data in.
     * @return the created sign request's info.
     */
    public static BoxSignRequest.Info createSignRequest(BoxAPIConnection api, List<BoxSignRequestFile> sourceFiles,
                                                        List<BoxSignRequestSigner> signers, String parentFolderId) {
        return createSignRequest(api, sourceFiles, signers, parentFolderId, null);
    }

    /**
     * Used to create a new sign request with optional parameters.
     *
     * @param api            the API connection to be used by the created user.
     * @param signers        the list of signers for this sign request.
     * @param sourceFiles    the list of files to a signing document from.
     * @param parentFolderId the id of the destination folder to place sign request specific data in.
     * @param optionalParams the optional parameters.
     * @return the created sign request's info.
     */
    public static BoxSignRequest.Info createSignRequest(BoxAPIConnection api, List<BoxSignRequestFile> sourceFiles,
                                                        List<BoxSignRequestSigner> signers, String parentFolderId,
                                                        BoxSignRequestCreateParams optionalParams) {

        JsonObject requestJSON = new JsonObject();

        JsonArray sourceFilesJSON = new JsonArray();
        for (BoxSignRequestFile sourceFile : sourceFiles) {
            sourceFilesJSON.add(sourceFile.getJSONObject());
        }
        requestJSON.add("source_files", sourceFilesJSON);

        JsonArray signersJSON = new JsonArray();
        for (BoxSignRequestSigner signer : signers) {
            signersJSON.add(signer.getJSONObject());
        }
        requestJSON.add("signers", signersJSON);

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
     *
     * @param fields the fields to retrieve.
     * @return information about this sign request.
     */
    public BoxSignRequest.Info getInfo(String... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        URL url = SIGN_REQUEST_URL_TEMPLATE.buildAlphaWithQuery(
                this.getAPI().getBaseURL(), builder.toString(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        return new BoxSignRequest.Info(responseJSON);
    }

    /**
     * Returns all the sign requests.
     *
     * @param api    the API connection to be used by the resource.
     * @param fields the fields to retrieve.
     * @return an iterable with all the sign requests.
     */
    public static Iterable<BoxSignRequest.Info> getAll(final BoxAPIConnection api, String... fields) {
        return getAll(api, DEFAULT_LIMIT, fields);
    }

    /**
     * Returns all the sign requests.
     * @param api    the API connection to be used by the resource.
     * @param limit  the limit of items per single response. The default value is 100.
     * @param fields the fields to retrieve.
     * @return an iterable with all the sign requests.
     */
    public static Iterable<BoxSignRequest.Info> getAll(final BoxAPIConnection api, int limit,  String... fields) {
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
     * Cancels a sign request if it has not yet been signed or declined.
     * Any outstanding signers will no longer be able to sign the document.
     *
     * @return the cancelled sign request's info.
     */
    public BoxSignRequest.Info cancel() {
        URL url = SIGN_REQUEST_CANCEL_URL_TEMPLATE.buildAlphaWithQuery(getAPI().getBaseURL(), "", this.getID());
        BoxJSONRequest request = new BoxJSONRequest(getAPI(), url, "POST");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        return new BoxSignRequest.Info(responseJSON);
    }

    /**
     * Attempts to resend a Sign Request to all signers that have not signed yet.
     * There is a 10 minute cooling-off period between each resend request.
     * If you make a resend call during the cooling-off period, a BoxAPIException will be thrown.
     *
     */
    public void resend() {
        URL url = SIGN_REQUEST_RESEND_URL_TEMPLATE.buildAlphaWithQuery(getAPI().getBaseURL(), "", this.getID());
        BoxJSONRequest request = new BoxJSONRequest(getAPI(), url, "POST");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Contains information about the Sign Request.
     */
    public class Info extends BoxResource.Info {

        private boolean isDocumentPreparationNeeded;
        private boolean areTextSignaturesEnabled;
        private boolean areDatesEnabled;
        private boolean areEmailsDisabled;
        private BoxSignRequestSignatureColor signatureColor;
        private String emailSubject;
        private String emailMessage;
        private boolean areRemindersEnabled;
        private List<BoxFile.Info> sourceFiles;
        private BoxFolder.Info parentFolder;
        private List<BoxSignRequestSigner> signers;
        private String name;
        private List<BoxSignRequestPrefillTag> prefillTags;
        private Integer daysValid;
        private String externalId;
        private String prepareUrl;
        private BoxFile.Info signingLog;
        private BoxSignRequestStatus status;
        private BoxSignRequestSignFiles signFiles;
        private Date autoExpireAt;

        /**
         * Constructs an empty Info object.
         */
        public Info() {
            super();
        }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         *
         * @param json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
        }

        /**
         * Constructs an Info object using an already parsed JSON object.
         *
         * @param jsonObject the parsed JSON object.
         */
        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * Indicates if the sender should receive a prepare_url in the response to complete document preparation via UI.
         *
         * @return true if document preparation is needed, otherwise false.
         */
        public boolean getIsDocumentPreparationNeeded() {
            return this.isDocumentPreparationNeeded;
        }

        /**
         * Gets the flag indicating if usage of signatures generated by typing (text) is enabled.
         *
         * @return true if text signatures are enabled, otherwise false.
         */
        public boolean getAreTextSignaturesEnabled() {
            return this.areTextSignaturesEnabled;
        }

        /**
         * Gets the flag indicating if ability for signer to add dates is enabled.
         *
         * @return true if ability for signer to add dates is enabled, otherwise false.
         */
        public boolean getAreDatesEnabled() {
            return this.areDatesEnabled;
        }

        /**
         * Gets the flag indicating if all status emails,
         * as well as the original email that contains the sign request, are disabled.
         *
         * @return true if emails are disabled, otherwise false.
         */
        public boolean getAreEmailsDisabled() {
            return this.areEmailsDisabled;
        }

        /**
         * Gets the forced, specific color for the signature.
         *
         * @return signature color (blue, black, red).
         */
        public BoxSignRequestSignatureColor getSignatureColor() {
            return this.signatureColor;
        }

        /**
         * Gets the subject of the sign request email.
         *
         * @return subject of the sign request email.
         */
        public String getEmailSubject() {
            return this.emailSubject;
        }

        /**
         * Gets the message to include in the sign request email.
         *
         * @return message of sign request email.
         */
        public String getEmailMessage() {
            return this.emailMessage;
        }

        /**
         * Gets the flag indicating if sending reminders for signers to sign a document on day 3, 8, 13 and 18
         * (or less if the document has been digitally signed already) is enabled.
         *
         * @return true if reminders are enabled, otherwise false.
         */
        public boolean getAreRemindersEnabled() {
            return this.areRemindersEnabled;
        }

        /**
         * Gets the list of files to create a signing document from.
         *
         * @return list of files to create a signing document from.
         */
        public List<BoxFile.Info> getSourceFiles() {
            return this.sourceFiles;
        }

        /**
         * Gets the destination folder to place sign request specific data in (copy of source files, signing log etc.).
         *
         * @return destination folder to place sign request specific data in.
         */
        public BoxFolder.Info getParentFolder() {
            return this.parentFolder;
        }

        /**
         * Gets the list of signers for this sign request.
         *
         * @return list of signers for this sign request.
         */
        public List<BoxSignRequestSigner> getSigners() {
            return this.signers;
        }

        /**
         * Gets the name of this sign request.
         *
         * @return name of this sign request.
         */
        public String getName() {
            return this.name;
        }

        /**
         * Gets the list of prefill tags.
         *
         * @return list of prefill tags.
         */
        public List<BoxSignRequestPrefillTag> getPrefillTags() {
            return this.prefillTags;
        }

        /**
         * Gets the number of days after which this request will automatically expire if not completed.
         *
         * @return number of days after which this request will automatically expire if not completed.
         */
        public Integer getDaysValid() {
            return this.daysValid;
        }

        /**
         * Gets the reference id in an external system that this sign request is related to.
         *
         * @return external id.
         */
        public String getExternalId() {
            return this.externalId;
        }

        /**
         * Gets the URL that can be used by the sign request sender to prepare the document through the UI.
         *
         * @return prepare url.
         */
        public String getPrepareUrl() {
            return this.prepareUrl;
        }

        /**
         * Gets the reference to a file that will hold a log of all signer activity for this request.
         *
         * @return signing log.
         */
        public BoxFile.Info getSigningLog() {
            return this.signingLog;
        }

        /**
         * Gets the status of the sign request.
         *
         * @return sign request's status.
         */
        public BoxSignRequestStatus getStatus() {
            return this.status;
        }

        /**
         * List of files that will be signed, which are copies of the original source files.
         * A new version of these files are created as signers sign and can be downloaded
         * at any point in the signing process.
         *
         * @return sign files.
         */
        public BoxSignRequestSignFiles getSignFiles() {
            return this.signFiles;
        }

        /**
         * Uses days_valid to calculate the date and time that
         * the sign request will expire, if unsigned.
         *
         * @return auto expires at date.
         */
        public Date getAutoExpireAt() {
            return this.autoExpireAt;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BoxSignRequest getResource() {
            return BoxSignRequest.this;
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
                if ("is_document_preparation_needed".equals(memberName)) {
                    this.isDocumentPreparationNeeded = value.asBoolean();
                } else if ("are_text_signatures_enabled".equals(memberName)) {
                    this.areTextSignaturesEnabled = value.asBoolean();
                } else if ("are_dates_enabled".equals(memberName)) {
                    this.areDatesEnabled = value.asBoolean();
                } else if ("are_emails_disabled".equals(memberName)) {
                    this.areEmailsDisabled = value.asBoolean();
                } else if ("signature_color".equals(memberName)) {
                    this.signatureColor = BoxSignRequestSignatureColor.fromJSONString(value.asString());
                } else if ("email_subject".equals(memberName)) {
                    this.emailSubject = value.asString();
                } else if ("email_message".equals(memberName)) {
                    this.emailMessage = value.asString();
                } else if ("are_reminders_enabled".equals(memberName)) {
                    this.areRemindersEnabled = value.asBoolean();
                } else if ("signers".equals(memberName)) {
                    List<BoxSignRequestSigner> signers = new ArrayList<BoxSignRequestSigner>();
                    for (JsonValue signerJSON : value.asArray()) {
                        BoxSignRequestSigner signer = new BoxSignRequestSigner(signerJSON.asObject(), getAPI());
                        signers.add(signer);
                    }
                    this.signers = signers;
                } else if ("source_files".equals(memberName)) {
                    List<BoxFile.Info> files = this.getFiles(value.asArray());
                    this.sourceFiles = files;
                } else if ("parent_folder".equals(memberName)) {
                    JsonObject folderJSON = value.asObject();
                    String folderID = folderJSON.get("id").asString();
                    BoxFolder folder = new BoxFolder(getAPI(), folderID);
                    this.parentFolder = folder.new Info(folderJSON);
                } else if ("name".equals(memberName)) {
                    this.name = value.asString();
                } else if ("prefill_tags".equals(memberName)) {
                    List<BoxSignRequestPrefillTag> prefillTags = new ArrayList<BoxSignRequestPrefillTag>();
                    for (JsonValue prefillTagJSON : value.asArray()) {
                        BoxSignRequestPrefillTag prefillTag =
                                new BoxSignRequestPrefillTag(prefillTagJSON.asObject());
                        prefillTags.add(prefillTag);
                    }
                    this.prefillTags = prefillTags;
                } else if ("days_valid".equals(memberName)) {
                    this.daysValid = value.asInt();
                } else if ("external_id".equals(memberName)) {
                    this.externalId = value.asString();
                } else if ("prepare_url".equals(memberName)) {
                    this.prepareUrl = value.asString();
                } else if ("signing_log".equals(memberName)) {
                    JsonObject signingLogJSON = value.asObject();
                    String fileID = signingLogJSON.get("id").asString();
                    BoxFile file = new BoxFile(getAPI(), fileID);
                    this.signingLog = file.new Info(signingLogJSON);
                } else if ("status".equals(memberName)) {
                    this.status = BoxSignRequestStatus.fromJSONString(value.asString());
                } else if ("sign_files".equals(memberName)) {
                    JsonObject signFilesJSON = value.asObject();
                    JsonValue filesArray = signFilesJSON.get("files");
                    List<BoxFile.Info> signFiles = this.getFiles(filesArray);
                    boolean isReadyForDownload = signFilesJSON.get("is_ready_for_download").asBoolean();
                    this.signFiles = new BoxSignRequestSignFiles(signFiles, isReadyForDownload);
                } else if ("auto_expire_at".equals(memberName)) {
                    this.autoExpireAt = BoxDateFormat.parse(value.asString());
                }
            } catch (Exception e) {
                throw new BoxDeserializationException(memberName, value.toString(), e);
            }
        }

        private List<BoxFile.Info> getFiles(JsonValue filesArray) {
            List<BoxFile.Info> files = new ArrayList<BoxFile.Info>();
            for (JsonValue fileJSON : filesArray.asArray()) {
                String fileID = fileJSON.asObject().get("id").asString();
                BoxFile file = new BoxFile(getAPI(), fileID);
                files.add(file.new Info(fileJSON.asObject()));
            }
            return files;
        }

        /**
         * List of files that will be signed, which are copies of the original source files.
         * A new version of these files are created as signers sign and can be downloaded
         * at any point in the signing process.
         */
        public class BoxSignRequestSignFiles {
            private List<BoxFile.Info> files;
            private boolean isReadyToDownload;

            /**
             * Constructs a BoxSignRequestSignFiles.
             *
             * @param files             list that signing events will occur on.
             * @param isReadyToDownload indicating whether a change to the document is processing.
             */
            public BoxSignRequestSignFiles(List<BoxFile.Info> files, boolean isReadyToDownload) {
                this.files = files;
                this.isReadyToDownload = isReadyToDownload;
            }

            /**
             * Gets the list of files that signing events will occur on - these are copies of the original source files.
             *
             * @return list of files.
             */
            public List<BoxFile.Info> getFiles() {
                return this.files;
            }

            /**
             * Gets the flag indicating whether a change to the document is processing and the PDF may be out of date.
             * It is recommended to wait until processing has finished before downloading the PDF.
             * Webhooks are not sent until processing has been completed.
             *
             * @return true if files are ready to download, otherwise false.
             */
            public boolean getIsReadyToDownload() {
                return this.isReadyToDownload;
            }
        }
    }

    /**
     * Represents a status of the sign request.
     */
    public enum BoxSignRequestStatus {

        /**
         * Converting status.
         */
        Converting("converting"),

        /**
         * Created status.
         */
        Created("created"),

        /**
         * Sent status.
         */
        Sent("sent"),

        /**
         * Viewed status.
         */
        Viewed("viewed"),

        /**
         * Signed status.
         */
        Signed("signed"),

        /**
         * Cancelled status.
         */
        Cancelled("cancelled"),

        /**
         * Declined status.
         */
        Declined("declined"),

        /**
         * Error converting status.
         */
        ErrorConverting("error_converting"),

        /**
         * Error sending status.
         */
        ErrorSending("error_sending"),

        /**
         * Expired status.
         */
        Expired("expired");

        private final String jsonValue;

        private BoxSignRequestStatus(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static BoxSignRequestStatus fromJSONString(String jsonValue) {
            if ("converting".equals(jsonValue)) {
                return Converting;
            } else if ("created".equals(jsonValue)) {
                return Created;
            } else if ("sent".equals(jsonValue)) {
                return Sent;
            } else if ("viewed".equals(jsonValue)) {
                return Viewed;
            } else if ("signed".equals(jsonValue)) {
                return Signed;
            } else if ("cancelled".equals(jsonValue)) {
                return Cancelled;
            } else if ("declined".equals(jsonValue)) {
                return Declined;
            } else if ("error_converting".equals(jsonValue)) {
                return ErrorConverting;
            } else if ("error_sending".equals(jsonValue)) {
                return ErrorSending;
            } else if ("expired".equals(jsonValue)) {
                return Expired;
            }
            throw new IllegalArgumentException("The provided JSON value isn't a valid BoxSignRequestStatus value.");
        }
    }

    private static List<BoxSignRequestFile> toBoxSignRequestFiles(List<BoxFile.Info> sourceFiles) {
        List<BoxSignRequestFile> files = new ArrayList<BoxSignRequestFile>();
        for (BoxFile.Info sourceFile : sourceFiles) {
            BoxSignRequestFile file = BoxSignRequestFile.fromFile(sourceFile);
            files.add(file);
        }

        return files;
    }
}
