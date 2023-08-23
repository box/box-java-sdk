package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a Sign Template used in the Box Sign API.
 *
 * @see <a href="https://developer.box.com/reference/resources/sign-templates/">Box Sign Templates</a>
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("sign_template")
public class BoxSignTemplate extends BoxResource {

    /**
     * The URL template for Sign Templates.
     */
    public static final URLTemplate SIGN_TEMPLATES_URL_TEMPLATE = new URLTemplate("sign_templates");

    /**
     * The URL template for Sign Templates operations with a given ID.
     */
    public static final URLTemplate SIGN_TEMPLATE_URL_TEMPLATE = new URLTemplate("sign_templates/%s");

    /**
     * The default limit of entries per response.
     */
    public static final int DEFAULT_LIMIT = 100;

    /**
     * Constructs a BoxSignTemplate for a resource with a given ID.
     *
     * @param api the API connection to be used by the resource.
     * @param id  the ID of the resource.
     */
    public BoxSignTemplate(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Return all Sign Templates.
     *
     * @param api the API connection to be used by the resource.
     * @return an iterable with all Sign Templates.
     */
    public static Iterable<BoxSignTemplate.Info> getAll(BoxAPIConnection api) {
        return getAll(api, DEFAULT_LIMIT);
    }

    /**
     * Return all Sign Templates.
     *
     * @param api   the API connection to be used by the resource.
     * @param limit the limit of entries per response.
     * @return an iterable with all Sign Templates.
     */
    public static Iterable<BoxSignTemplate.Info> getAll(BoxAPIConnection api, int limit) {
        QueryStringBuilder builder = new QueryStringBuilder();
        URL url = SIGN_TEMPLATES_URL_TEMPLATE.buildWithQuery(api.getBaseURL(), builder.toString());
        return new BoxResourceIterable<BoxSignTemplate.Info>(api, url, limit) {

            @Override
            protected BoxSignTemplate.Info factory(JsonObject jsonObject) {
                BoxSignTemplate template = new BoxSignTemplate(api, jsonObject.get("id").asString());
                return template.new Info(jsonObject);
            }
        };

    }

    /**
     * Return information about this Sign Template.
     *
     * @return information about this Sign Template.
     */
    public BoxSignTemplate.Info getInfo() {
        URL url = SIGN_TEMPLATE_URL_TEMPLATE.buildAlpha(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        try (BoxJSONResponse response = (BoxJSONResponse) request.send()) {
            JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
            return new Info(jsonObject);
        }
    }

    /**
     * Contains information about a BoxSignTemplate.
     */
    public class Info extends BoxResource.Info {

        private BoxSignTemplateAdditionalInfo additionalInfo;
        private boolean areEmailSettingsLocked;
        private boolean areFieldsLocked;
        private boolean areFilesLocked;
        private boolean areRecipientsLocked;
        private BoxSignTemplateCustomBranding customBranding;
        private Integer daysValid;
        private String emailMessage;
        private String emailSubject;
        private String name;
        private BoxFolder.Info parentFolder;
        private BoxSignTemplateReadySignLink readySignLink;
        private List<BoxSignRequestSigner> signers;
        private List<BoxFile.Info> sourceFiles;

        /**
         * Constructs an empty Info object.
         */
        public Info() {
            super();
        }

        /**
         * Constructs an Info object with the provided JSON string.
         *
         * @param json the JSON string representing the Sign Template.
         */
        public Info(String json) {
            super(json);
        }

        /**
         * Constructs an Info object with the provided JSON object.
         *
         * @param jsonObject the JSON object representing the Sign Template.
         */
        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BoxSignTemplate getResource() {
            return BoxSignTemplate.this;
        }

        /**
         * Gets the additional information about this Sign Template.
         *
         * @return the additional information about this Sign Template.
         */
        public BoxSignTemplateAdditionalInfo getAdditionalInfo() {
            return this.additionalInfo;
        }

        /**
         * Gets whether the templates email settings are editable or not.
         *
         * @return true if the email settings are locked; otherwise false.
         */
        public boolean getAreEmailSettingsLocked() {
            return this.areEmailSettingsLocked;
        }

        /**
         * Gets whether the templates input fields are editable or not.
         * This includes deleting or renaming template files.
         *
         * @return true if the fields are locked; otherwise false.
         */
        public boolean getAreFieldsLocked() {
            return this.areFieldsLocked;
        }

        /**
         * Gets whether the template document options are editable or not,
         * for example renaming the document.
         *
         * @return true if the files are locked; otherwise false.
         */
        public boolean getAreFilesLocked() {
            return this.areFilesLocked;
        }

        /**
         * Gets whether the template signers are editable or not.
         *
         * @return true if the recipients are locked; otherwise false.
         */
        public boolean getAreRecipientsLocked() {
            return this.areRecipientsLocked;
        }

        /**
         * Gets the custom branding applied to notifications and signature requests.
         *
         * @return the custom branding for this Sign Template.
         */
        public BoxSignTemplateCustomBranding getCustomBranding() {
            return this.customBranding;
        }

        /**
         * Gets the number of days after which the created signature request
         * will automatically expire if not completed.
         * By default, we do not apply any expiration date on signature requests,
         * and the signature request does not expire.
         *
         * @return the number of days the template is valid for.
         */
        public Integer getDaysValid() {
            return this.daysValid;
        }

        /**
         * Gets the email message that will be sent to all signers.
         *
         * @return the email message for this Sign Template.
         */
        public String getEmailMessage() {
            return this.emailMessage;
        }

        /**
         * Gets the email subject that will be sent to all signers.
         *
         * @return the email subject for this Sign Template.
         */
        public String getEmailSubject() {
            return this.emailSubject;
        }

        /**
         * Gets the name of this Sign Template.
         *
         * @return the name of this Sign Template.
         */
        public String getName() {
            return this.name;
        }

        /**
         * Gets the parent folder of this Sign Template.
         *
         * @return the parent folder of this Sign Template.
         */
        public BoxFolder.Info getParentFolder() {
            return this.parentFolder;
        }

        /**
         * Gets the ready sign link for this Sign Template.
         *
         * @return the ready sign link for this Sign Template.
         */
        public BoxSignTemplateReadySignLink getReadySignLink() {
            return this.readySignLink;
        }

        /**
         * Gets the signers for this Sign Template.
         *
         * @return the signers for this Sign Template.
         */
        public List<BoxSignRequestSigner> getSigners() {
            return this.signers;
        }

        /**
         * Gets the source files for this Sign Template.
         *
         * @return the source files for this Sign Template.
         */
        public List<BoxFile.Info> getSourceFiles() {
            return this.sourceFiles;
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
                switch (memberName) {
                    case "additional_info":
                        this.additionalInfo = this.parseAdditionalInfo(value.asObject());
                        break;
                    case "are_email_settings_locked":
                        this.areEmailSettingsLocked = value.asBoolean();
                        break;
                    case "are_fields_locked":
                        this.areFieldsLocked = value.asBoolean();
                        break;
                    case "are_files_locked":
                        this.areFilesLocked = value.asBoolean();
                        break;
                    case "are_recipients_locked":
                        this.areRecipientsLocked = value.asBoolean();
                        break;
                    case "custom_branding":
                        this.customBranding = this.parseCustomBranding(value.asObject());
                        break;
                    case "days_valid":
                        this.daysValid = value.asInt();
                        break;
                    case "email_message":
                        this.emailMessage = value.asString();
                        break;
                    case "email_subject":
                        this.emailSubject = value.asString();
                        break;
                    case "name":
                        this.name = value.asString();
                        break;
                    case "parent_folder":
                        JsonObject parentFolderJSON = value.asObject();
                        String parentFolderID = parentFolderJSON.get("id").asString();
                        BoxFolder parentFolder = new BoxFolder(getAPI(), parentFolderID);
                        this.parentFolder = parentFolder.new Info(parentFolderJSON);
                        break;
                    case "ready_sign_link":
                        this.readySignLink = this.parseReadySignLink(value.asObject());
                        break;
                    case "signers":
                        this.signers = this.parseSigners(value.asArray());
                        break;
                    case "source_files":
                        this.sourceFiles = this.parseSourceFiles(value.asArray());
                        break;
                    default:
                }
            } catch (Exception e) {
                throw new BoxDeserializationException(memberName, value.toString(), e);
            }
        }

        private BoxSignTemplateAdditionalInfo parseAdditionalInfo(JsonValue additionalInfoJSON) {
            List<String> nonEditableFields = new ArrayList<String>();
            for (JsonValue fieldJSON : additionalInfoJSON.asObject().get("non_editable").asArray()) {
                nonEditableFields.add(fieldJSON.asString());
            }
            BoxSignTemplateAdditionalInfoRequired required = this.parseAdditionalInfoRequired(
                additionalInfoJSON.asObject().get("required").asObject()
            );
            return new BoxSignTemplateAdditionalInfo(nonEditableFields, required);
        }

        private BoxSignTemplateAdditionalInfoRequired parseAdditionalInfoRequired(JsonObject requiredJSON) {
            List<List<String>> signers = new ArrayList<List<String>>();
            for (JsonValue signerJSON : requiredJSON.get("signers").asArray()) {
                List<String> signer = new ArrayList<String>();
                for (JsonValue fieldJSON : signerJSON.asArray()) {
                    signer.add(fieldJSON.asString());
                }
                signers.add(signer);
            }
            return new BoxSignTemplateAdditionalInfoRequired(signers);
        }

        private List<BoxFile.Info> parseSourceFiles(JsonValue filesArray) {
            List<BoxFile.Info> files = new ArrayList<BoxFile.Info>();
            for (JsonValue fileJSON : filesArray.asArray()) {
                JsonObject fileObj = fileJSON.asObject();
                String fileID = fileObj.get("id").asString();
                BoxFile file = new BoxFile(getAPI(), fileID);
                files.add(file.new Info(fileObj));
            }
            return files;
        }

        private List<BoxSignRequestSigner> parseSigners(JsonValue signersArray) {
            List<BoxSignRequestSigner> signers = new ArrayList<BoxSignRequestSigner>();
            for (JsonValue signerJSON : signersArray.asArray()) {
                JsonObject signerObj = signerJSON.asObject();
                signers.add(new BoxSignRequestSigner(signerObj, getAPI()));
            }
            return signers;
        }

        private BoxSignTemplateCustomBranding parseCustomBranding(JsonObject customBrandingJSON) {
            String brandingColor = customBrandingJSON.get("branding_color").asString();
            String companyName = customBrandingJSON.get("company_name").asString();
            String emailFooterText = customBrandingJSON.get("email_footer_text").asString();
            String logoUri = customBrandingJSON.get("logo_uri").asString();
            return new BoxSignTemplateCustomBranding(brandingColor, companyName, emailFooterText, logoUri);
        }

        private BoxSignTemplateReadySignLink parseReadySignLink(JsonObject readySignLinkJSON) {
            String folderID = readySignLinkJSON.get("folder_id").asString();
            String instructions = readySignLinkJSON.get("instructions").asString();
            boolean isActive = readySignLinkJSON.get("is_active").asBoolean();
            boolean isNofiticationDisabled = readySignLinkJSON.get("is_notification_disabled").asBoolean();
            String name = readySignLinkJSON.get("name").asString();
            String url = readySignLinkJSON.get("url").asString();
            return new BoxSignTemplateReadySignLink(folderID, instructions, isActive,
                isNofiticationDisabled, name, url);
        }

        /**
         * Box Sign Template additional information on which fields are required
         * and which fields are not editable.
         */
        public class BoxSignTemplateAdditionalInfo {
            private final List<String> nonEditable;
            private final BoxSignTemplateAdditionalInfoRequired required;

            public BoxSignTemplateAdditionalInfo(List<String> nonEditable,
                                                 BoxSignTemplateAdditionalInfoRequired required) {
                this.nonEditable = nonEditable;
                this.required = required;
            }

            /**
             * Get non-editable fields.
             *
             * @return list of non-editable fields.\
             */
            public List<String> getNonEditable() {
                return this.nonEditable;
            }

            /**
             * Gets the required fields.
             *
             * @return the required fields.
             */
            public BoxSignTemplateAdditionalInfoRequired getRequired() {
                return this.required;
            }
        }

        /**
         * Box Sign Template additional information on which fields are required.
         */
        public class BoxSignTemplateAdditionalInfoRequired {
            private final List<List<String>> signers;

            /**
             * Constructs a BoxSignTemplateAdditionalInfoRequired object with the provided list of signers.
             */
            public BoxSignTemplateAdditionalInfoRequired(List<List<String>> signers) {
                this.signers = signers;
            }

            /**
             * Gets the required signer fields.
             *
             * @return the required signer fields.
             */
            public List<List<String>> getSigners() {
                return this.signers;
            }
        }

        /**
         * Custom branding applied to notifications and signature requests.
         */
        public class BoxSignTemplateCustomBranding {
            private final String brandingColor;
            private final String companyName;
            private final String emailFooterText;
            private final String logoUri;

            /**
             * Constructs a BoxSignTemplateCustomBranding object with the provided information.
             *
             * @param brandingColor   the branding color.
             * @param companyName     the company name.
             * @param emailFooterText the email footer text.
             * @param logoUri         the logo URI.
             */
            public BoxSignTemplateCustomBranding(String brandingColor, String companyName, String emailFooterText,
                                                 String logoUri) {
                this.brandingColor = brandingColor;
                this.companyName = companyName;
                this.emailFooterText = emailFooterText;
                this.logoUri = logoUri;
            }

            /**
             * Gets the branding color.
             *
             * @return the branding color.
             */
            public String getBrandingColor() {
                return this.brandingColor;
            }

            /**
             * Gets the company name.
             *
             * @return the company name.
             */
            public String getCompanyName() {
                return this.companyName;
            }

            /**
             * Gets the email footer text.
             *
             * @return the email footer text.
             */
            public String getEmailFooterText() {
                return this.emailFooterText;
            }

            /**
             * Gets the logo URI.
             *
             * @return the logo URI.
             */
            public String getLogoUri() {
                return this.logoUri;
            }
        }

        /**
         * Box's ready-sign link feature enables you to create a link to a signature request that you've created from a template.
         * Use this link when you want to post a signature request on a public form — such as an email,
         * social media post, or web page — without knowing who the signers will be.
         * Note: The ready-sign link feature is limited to Enterprise Plus customers and not available to Box Verified Enterprises.
         */
        public class BoxSignTemplateReadySignLink {
            private final String folderID;
            private final String instructions;
            private final boolean isActive;
            private final boolean isNofiticationDisabled;
            private final String name;
            private final String url;

            /**
             * Constructs a BoxSignTemplateReadySignLink object with the provided information.
             *
             * @param folderID               the folder ID.
             * @param instructions           the instructions.
             * @param isActive               whether the link is active or not.
             * @param isNofiticationDisabled whether the notification is disabled or not.
             * @param name                   the name.
             * @param url                    the URL.
             */
            public BoxSignTemplateReadySignLink(String folderID, String instructions, boolean isActive,
                                                boolean isNofiticationDisabled, String name, String url) {
                this.folderID = folderID;
                this.instructions = instructions;
                this.isActive = isActive;
                this.isNofiticationDisabled = isNofiticationDisabled;
                this.name = name;
                this.url = url;
            }

            /**
             * Gets the folder ID.
             *
             * @return the folder ID.
             */
            public String getFolderID() {
                return this.folderID;
            }

            /**
             * Gets the instructions.
             *
             * @return the instructions.
             */
            public String getInstructions() {
                return this.instructions;
            }

            /**
             * Gets whether the link is active or not.
             *
             * @return true if the link is active; otherwise false.
             */
            public boolean getIsActive() {
                return this.isActive;
            }

            /**
             * Gets whether the notification is disabled or not.
             *
             * @return true if the notification is disabled; otherwise false.
             */
            public boolean getIsNofiticationDisabled() {
                return this.isNofiticationDisabled;
            }

            /**
             * Gets the name of the ready-sign link.
             *
             * @return the name.
             */
            public String getName() {
                return this.name;
            }

            /**
             * Gets the URL of the ready-sign link.
             *
             * @return the URL.
             */
            public String getUrl() {
                return this.url;
            }
        }
    }
}
