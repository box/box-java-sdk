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
        private boolean areOptionsLocked;
        private boolean areFilesLocked;
        private boolean areRecipientsLocked;
        private BoxSignTemplateCustomBranding customBranding;
        private Integer daysValid;
        private String emailMessage;
        private String emailSubject;
        private String name;
        private BoxFolder.Info parentFolder;
        private BoxSignTemplateReadySignLink readySignLink;
        private List<BoxSignTemplateSigner> signers;
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
         * Gets weather the templates input options are editable or not.
         *
         * @return true if the options are editable; otherwise false.
         */
        public boolean getAreOptionsLocked() {
            return this.areOptionsLocked;
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
        public List<BoxSignTemplateSigner> getSigners() {
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
                    case "are_options_locked":
                        this.areOptionsLocked = value.asBoolean();
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

        private List<BoxSignTemplateSigner> parseSigners(JsonValue signersArray) {
            List<BoxSignTemplateSigner> signers = new ArrayList<BoxSignTemplateSigner>();
            for (JsonValue signerJSON : signersArray.asArray()) {
                JsonObject signerObj = signerJSON.asObject();
                signers.add(new BoxSignTemplateSigner(signerObj, getAPI()));
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
    }
}
