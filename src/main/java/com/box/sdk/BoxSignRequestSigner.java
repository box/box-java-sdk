package com.box.sdk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.box.sdk.internal.utils.JsonUtils;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a signer in BoxSignRequest.
 */
public class BoxSignRequestSigner extends BoxJSONObject {
    private String email;
    private String name;
    private BoxSignRequestSignerRole role;
    private Boolean isInPerson;
    private Integer order;
    private String language;
    private String verificationPhoneNumber;
    private String embedUrlExternalUserId;
    private Boolean hasViewedEmail;
    private Boolean hasViewedDocument;
    private BoxSignerDecision signerDecision;
    private List<BoxSignerInput> inputs;
    private String embedUrl;
    private List<BoxFile.Info> attachments;
    private BoxAPIConnection api;
    private String verificationPassword;

    /**
     * Constructs a BoxSignRequestSigner with an email.
     *
     * @param email of signer.
     */
    public BoxSignRequestSigner(String email) {
        this.email = email;
    }

    /**
     * Construct a BoxSignRequestSigner.
     *
     * @param jsonObject the parsed JSON object.
     * @param api        the API connection to be used to fetch interacted item
     */
    public BoxSignRequestSigner(JsonObject jsonObject, BoxAPIConnection api) {
        super(jsonObject);
        this.api = api;
    }

    /**
     * Gets the email address of the signer.
     *
     * @return email address of the signer.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets the name of signer.
     *
     * @return name of signer.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the role of the signer.
     *
     * @return role of the signer.
     */
    public BoxSignRequestSignerRole getRole() {
        return this.role;
    }

    /**
     * Gets the flag that when used in combination with an embed url on the sender. After the sender signs,
     * they will be redirected to the next InPerson signer.
     *
     * @return true if is in person signer, otherwise false.
     */
    public boolean getIsInPerson() {
        return this.isInPerson;
    }

    /**
     * Gets the order of signer.
     *
     * @return order of signer.
     */
    public int getOrder() {
        return this.order;
    }

    /**
     * Gets the language for email notifications sent to the signer.
     *
     * @return language for email notifications sent to the signer.
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * Gets the phone number that will be used to verify the signer before the signer can sign.
     * This requires a country code (should follow E.164).
     *
     * @return verification phone number.
     */
    public String getVerificationPhoneNumber() {
        return this.verificationPhoneNumber;
    }

    /**
     * Gets the user id for this signer in external application responsible
     * for authentication when accessing the embed url.
     *
     * @return embed url external user id.
     */
    public String getEmbedUrlExternalUserId() {
        return this.embedUrlExternalUserId;
    }

    /**
     * Gets the flag indicating if signer has viewed the sign request email.
     *
     * @return true if the signer has viewed the sign request email, otherwise false.
     */
    public boolean getHasViewedEmail() {
        return this.hasViewedEmail;
    }

    /**
     * Gets the flag indicating if signer has viewed the document.
     *
     * @return true if the signer has viewed the document, otherwise false.
     */
    public boolean getHasViewedDocument() {
        return this.hasViewedDocument;
    }

    /**
     * Gets the final decision made by signer.
     *
     * @return final decision made by signer.
     */
    public BoxSignerDecision getSignerDecision() {
        return this.signerDecision;
    }

    /**
     * Gets the inputs created by a signer on a sign request.
     *
     * @return list of inputs created by a signer on a sign request.
     */
    public List<BoxSignerInput> getInputs() {
        return this.inputs;
    }

    /**
     * Gets the url to direct signer to for signing.
     *
     * @return url to direct signer to for signing.
     */
    public String getEmbedUrl() {
        return this.embedUrl;
    }

    /**
     * Gets the attachments uploaded by the signer.
     *
     * @return list of attachments uploaded by the signer.
     */
    public List<BoxFile.Info> getAttachments() {
        return this.attachments;
    }

    /**
     * Gets the signer password that they need to enter before signing a document.
     *
     * @return declined redirect url.
     */
    public String getVerificationPassword() {
        return this.verificationPassword;
    }

    /**
     * Sets the email address of the signer.
     *
     * @param email adresss of the signer.
     * @return this BoxSignRequestSigner object for chaining.
     */
    public BoxSignRequestSigner setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Sets the name of signer.
     *
     * @param name of signer.
     * @return this BoxSignRequestSigner object for chaining.
     */
    public BoxSignRequestSigner setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the role of the signer. If role is not set it's FinalCopyReader by default.
     *
     * @param role of the signer.
     * @return this BoxSignRequestSigner object for chaining.
     */
    public BoxSignRequestSigner setRole(BoxSignRequestSignerRole role) {
        this.role = role;
        return this;
    }

    /**
     * Gets the flag that is used in combination with an embed url for a the sender. After the sender signs,
     * they will be redirected to the next InPerson signer.
     *
     * @return true if is in person signer, otherwise false.
     */
    public Boolean getInPerson() {
        return this.isInPerson;
    }

    /**
     * Sets the flag that is used in combination with an embed url for a the sender. After the sender signs,
     * they will be redirected to the next InPerson signer.
     *
     * @param isInPerson flag.
     * @return this BoxSignRequestSigner object for chaining.
     */
    public BoxSignRequestSigner setInPerson(Boolean isInPerson) {
        this.isInPerson = isInPerson;
        return this;
    }

    /**
     * Sets the order of signer.
     *
     * @param order of signer.
     * @return this BoxSignRequestSigner object for chaining.
     */
    public BoxSignRequestSigner setOrder(Integer order) {
        this.order = order;
        return this;
    }

    /**
     * Sets the language for email notifications sent to this signer.
     *
     * @param language for email notifications sent to this signer.
     * @return this BoxSignRequestSigner object for chaining.
     */
    public BoxSignRequestSigner setLanguage(String language) {
        this.language = language;
        return this;
    }

    /**
     * Sets the phone number that will be used to verify the signer before the signer can sign.
     * This requires a country code (should follow E.164).
     *
     * @param verificationPhoneNumber for this signer.
     * @return this BoxSignRequestSigner object for chaining.
     */
    public BoxSignRequestSigner setVerificationPhoneNumber(String verificationPhoneNumber) {
        this.verificationPhoneNumber = verificationPhoneNumber;
        return this;
    }

    /**
     * Sets the user id for this signer in external application responsible
     * for authentication when accessing the embed url.
     *
     * @param embedUrlExternalUserId for this signer in external application responsible
     *                               for authentication when accessing the embed url.
     * @return this BoxSignRequestSigner object for chaining.
     */
    public BoxSignRequestSigner setEmbedUrlExternalUserId(String embedUrlExternalUserId) {
        this.embedUrlExternalUserId = embedUrlExternalUserId;
        return this;
    }

    /**
     * Sets the signer password that they need to enter before signing a document.
     *
     * @param verificationPassword for this signer.
     * @return this BoxSignRequestSigner object for chaining.
     */
    public BoxSignRequestSigner setVerificationPassword(String verificationPassword) {
        this.verificationPassword = verificationPassword;
        return this;
    }

    /**
     * Represents a final decision made by signer (type and time the decision was made).
     */
    public class BoxSignerDecision extends BoxJSONObject {
        private BoxSignRequestSignerDecisionType type;
        private Date finalizedAt;

        /**
         * Constructs a BoxSignerDecision object using an already parsed JSON object.
         *
         * @param jsonObject the parsed JSON object.
         */
        public BoxSignerDecision(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * Gets the type of decision made by signer.
         *
         * @return type of decision made by signer.
         */
        public BoxSignRequestSignerDecisionType getType() {
            return this.type;
        }

        /**
         * Gets the date/time that the decision was made.
         *
         * @return date/time that the decision was made.
         */
        public Date getFinalizedAt() {
            return this.finalizedAt;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        void parseJSONMember(JsonObject.Member member) {
            JsonValue value = member.getValue();
            String memberName = member.getName();
            try {
                if (memberName.equals("type")) {
                    this.type = BoxSignRequestSignerDecisionType.fromJSONString(value.asString());
                } else if (memberName.equals("finalized_at")) {
                    this.finalizedAt = BoxDateFormat.parse(value.asString());
                }
            } catch (Exception e) {
                throw new BoxDeserializationException(memberName, value.toString(), e);
            }
        }
    }

    /**
     * Represents an input created by a signer on a sign request.
     */
    public class BoxSignerInput extends BoxJSONObject {
        private String documentTagId;
        private String textValue;
        private boolean checkboxValue;
        private Date dateValue;
        private BoxSignRequestInputType type;
        private int pageIndex;

        /**
         * Constructs a BoxSignerInput object using an already parsed JSON object.
         *
         * @param jsonObject the parsed JSON object.
         */
        public BoxSignerInput(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * Gets the reference of the id of a particular tag added to the content
         * of the files being used to create the sign request.
         *
         * @return document tag id.
         */
        public String getDocumentTagId() {
            return this.documentTagId;
        }

        /**
         * Gets the text prefill value.
         *
         * @return text prefill value.
         */
        public String getTextValue() {
            return this.textValue;
        }

        /**
         * Gets the checkbox prefill value.
         *
         * @return checkbox prefill value.
         */
        public boolean getIsCheckboxValue() {
            return this.checkboxValue;
        }

        /**
         * Gets the date prefill value.
         *
         * @return date prefill value.
         */
        public Date getDateValue() {
            return this.dateValue;
        }

        /**
         * Gets the type of input.
         *
         * @return type of input.
         */
        public BoxSignRequestInputType getType() {
            return this.type;
        }

        /**
         * Gets the index of page that input is on.
         *
         * @return index of page that input is on.
         */
        public int getPageIndex() {
            return this.pageIndex;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        void parseJSONMember(JsonObject.Member member) {
            JsonValue value = member.getValue();
            String memberName = member.getName();
            try {
                if ("documentTagId".equals(memberName)) {
                    this.documentTagId = value.asString();
                } else if ("text_value".equals(memberName)) {
                    this.textValue = value.asString();
                } else if ("checkbox_value".equals(memberName)) {
                    this.checkboxValue = value.asBoolean();
                } else if ("date_value".equals(memberName)) {
                    this.dateValue = BoxDateFormat.parse(value.asString());
                } else if ("type".equals(memberName)) {
                    this.type = BoxSignRequestInputType.fromJSONString(value.asString());
                } else if ("page_index".equals(memberName)) {
                    this.pageIndex = value.asInt();
                }
            } catch (Exception e) {
                throw new BoxDeserializationException(memberName, value.toString(), e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void parseJSONMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        String memberName = member.getName();
        try {
            if ("email".equals(memberName)) {
                this.email = value.asString();
            } else if ("name".equals(memberName)) {
                this.name = value.asString();
            } else if ("role".equals(memberName)) {
                this.role = BoxSignRequestSignerRole.fromJSONString(value.asString());
            } else if ("is_in_person".equals(memberName)) {
                this.isInPerson = value.asBoolean();
            } else if ("order".equals(memberName)) {
                this.order = value.asInt();
            } else if ("language".equals(memberName)) {
                this.language = value.asString();
            } else if ("verification_phone_number".equals(memberName)) {
                this.verificationPhoneNumber = value.asString();
            } else if ("embed_url_external_user_id".equals(memberName)) {
                this.embedUrlExternalUserId = value.asString();
            } else if ("has_viewed_email".equals(memberName)) {
                this.hasViewedEmail = value.asBoolean();
            } else if ("has_viewed_document".equals(memberName)) {
                this.hasViewedDocument = value.asBoolean();
            } else if ("signer_decision".equals(memberName)) {
                JsonObject signerDecisionJSON = value.asObject();
                BoxSignerDecision signerDecision = new BoxSignerDecision(signerDecisionJSON);
                this.signerDecision = signerDecision;
            } else if ("inputs".equals(memberName)) {
                List<BoxSignerInput> inputs = new ArrayList<BoxSignerInput>();
                for (JsonValue inputJSON : value.asArray()) {
                    BoxSignerInput input = new BoxSignerInput(inputJSON.asObject());
                    inputs.add(input);
                }
                this.inputs = inputs;
            } else if ("embed_url".equals(memberName)) {
                this.embedUrl = value.asString();
            } else if ("attachments".equals(memberName)) {
                List<BoxFile.Info> attachments = new ArrayList<BoxFile.Info>();
                for (JsonValue attachmentJSON : value.asArray()) {
                    String fileID = attachmentJSON.asObject().get("id").asString();
                    BoxFile file = new BoxFile(this.api, fileID);
                    attachments.add(file.new Info(attachmentJSON.asObject()));
                }
                this.attachments = attachments;
            }
        } catch (Exception e) {
            throw new BoxDeserializationException(memberName, value.toString(), e);
        }
    }

    /**
     * Gets a JSON object representing this class.
     *
     * @return the JSON object representing this class.
     */
    public JsonObject getJSONObject() {
        JsonObject jsonObj = new JsonObject();
        JsonUtils.addIfNotNull(jsonObj, "email", this.email);
        JsonUtils.addIfNotNull(jsonObj, "name", this.name);
        JsonUtils.addIfNotNull(jsonObj, "role", this.role);
        JsonUtils.addIfNotNull(jsonObj, "is_in_person", this.isInPerson);
        JsonUtils.addIfNotNull(jsonObj, "order", this.order);
        JsonUtils.addIfNotNull(jsonObj, "language", this.language);
        JsonUtils.addIfNotNull(jsonObj, "verification_phone_number", this.verificationPhoneNumber);
        JsonUtils.addIfNotNull(jsonObj, "embed_url_external_user_id", this.embedUrlExternalUserId);
        JsonUtils.addIfNotNull(jsonObj, "verification_password", this.verificationPassword);

        return jsonObj;
    }

    /**
     * Type of decision made by signer.
     */
    public enum BoxSignRequestSignerDecisionType {

        /**
         * Signed decision.
         */
        Signed("signed"),

        /**
         * Declined decision.
         */
        Declined("declined");

        private final String jsonValue;

        private BoxSignRequestSignerDecisionType(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static BoxSignRequestSignerDecisionType fromJSONString(String jsonValue) {
            if ("signed".equals(jsonValue)) {
                return Signed;
            } else if ("declined".equals(jsonValue)) {
                return Declined;
            }
            throw new IllegalArgumentException("The provided JSON value isn't a valid "
                    + "BoxSignRequestSignerDecisionType.");
        }
    }

    /**
     * Represents a type of input.
     */
    public enum BoxSignRequestInputType {

        /**
         * Signature input.
         */
        Signature("signature"),

        /**
         * Text input.
         */
        Text("text"),

        /**
         * Checkbox input.
         */
        Checkbox("checkbox"),

        /**
         * Date input.
         */
        Date("date");

        private final String jsonValue;

        private BoxSignRequestInputType(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static BoxSignRequestInputType fromJSONString(String jsonValue) {
            if ("signature".equals(jsonValue)) {
                return Signature;
            } else if ("text".equals(jsonValue)) {
                return Text;
            } else if ("checkbox".equals(jsonValue)) {
                return Checkbox;
            } else if ("date".equals(jsonValue)) {
                return Date;
            }
            throw new IllegalArgumentException("The provided JSON value isn't a valid "
                    + "BoxSignRequestInputType.");
        }
    }
}


