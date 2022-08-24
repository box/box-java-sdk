package com.box.sdk;

import com.box.sdk.internal.utils.JsonUtils;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

/**
 * Represents a signer in BoxSignRequest.
 */
public class BoxSignRequestSigner extends BoxJSONObject {
    private String email;
    private BoxSignRequestSignerRole role;
    private Boolean isInPerson;
    private Integer order;
    private String embedUrlExternalUserId;
    private Boolean hasViewedEmail;
    private Boolean hasViewedDocument;
    private BoxSignerDecision signerDecision;
    private List<BoxSignerInput> inputs;
    private String embedUrl;
    private BoxAPIConnection api;

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
     * Sets the email address of the signer.
     *
     * @param email address of the signer.
     * @return this BoxSignRequestSigner object for chaining.
     */
    public BoxSignRequestSigner setEmail(String email) {
        this.email = email;
        return this;
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
     * Gets the user id for this signer in external application responsible
     * for authentication when accessing the embed url.
     *
     * @return embed url external user id.
     */
    public String getEmbedUrlExternalUserId() {
        return this.embedUrlExternalUserId;
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
     * {@inheritDoc}
     */
    @Override
    void parseJSONMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        String memberName = member.getName();
        try {
            if ("email".equals(memberName)) {
                this.email = value.asString();
            } else if ("role".equals(memberName)) {
                this.role = BoxSignRequestSignerRole.fromJSONString(value.asString());
            } else if ("is_in_person".equals(memberName)) {
                this.isInPerson = value.asBoolean();
            } else if ("order".equals(memberName)) {
                this.order = value.asInt();
            } else if ("embed_url_external_user_id".equals(memberName)) {
                this.embedUrlExternalUserId = value.asString();
            } else if ("has_viewed_email".equals(memberName)) {
                this.hasViewedEmail = value.asBoolean();
            } else if ("has_viewed_document".equals(memberName)) {
                this.hasViewedDocument = value.asBoolean();
            } else if ("signer_decision".equals(memberName)) {
                JsonObject signerDecisionJSON = value.asObject();
                this.signerDecision = new BoxSignerDecision(signerDecisionJSON);
            } else if ("inputs".equals(memberName)) {
                List<BoxSignerInput> inputs = new ArrayList<>();
                for (JsonValue inputJSON : value.asArray()) {
                    BoxSignerInput input = new BoxSignerInput(inputJSON.asObject());
                    inputs.add(input);
                }
                this.inputs = inputs;
            } else if ("embed_url".equals(memberName)) {
                this.embedUrl = value.asString();
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
        JsonUtils.addIfNotNull(jsonObj, "role", this.role);
        JsonUtils.addIfNotNull(jsonObj, "is_in_person", this.isInPerson);
        JsonUtils.addIfNotNull(jsonObj, "order", this.order);
        JsonUtils.addIfNotNull(jsonObj, "embed_url_external_user_id", this.embedUrlExternalUserId);

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

        BoxSignRequestSignerDecisionType(String jsonValue) {
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

        BoxSignRequestInputType(String jsonValue) {
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

    /**
     * Represents a content type of input.
     */
    public enum BoxSignRequestInputContentType {
        /**
         * Initial content type
         */
        Initial("initial"),
        /**
         * Stamp content type
         */
        Stamp("stamp"),
        /**
         * Signature content type
         */
        Signature("signature"),
        /**
         * Company content type
         */
        Company("company"),
        /**
         * Title content type
         */
        Title("title"),
        /**
         * Email content type
         */
        Email("email"),
        /**
         * Full name content type
         */
        FullName("full_name"),
        /**
         * First name content type
         */
        FirstName("first_name"),
        /**
         * Last name content type
         */
        LastName("last_name"),
        /**
         * Text content type
         */
        Text("text"),
        /**
         * Date content type
         */
        Date("date"),
        /**
         * Checkbox content type
         */
        Checkbox("checkbox");

        private final String jsonValue;

        BoxSignRequestInputContentType(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static BoxSignRequestInputContentType fromJSONString(String jsonValue) {
            switch (jsonValue) {
                case "initial":
                    return Initial;
                case "stamp":
                    return Stamp;
                case "signature":
                    return Signature;
                case "company":
                    return Company;
                case "title":
                    return Title;
                case "email":
                    return Email;
                case "full_name":
                    return FullName;
                case "first_name":
                    return FirstName;
                case "last_name":
                    return LastName;
                case "text":
                    return Text;
                case "date":
                    return Date;
                case "checkbox":
                    return Checkbox;
                default:
                    throw new IllegalArgumentException(
                        format("The provided JSON value '%s' isn't a valid BoxSignRequestInputContentType.", jsonValue)
                    );
            }
        }
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
        private BoxSignRequestInputContentType contentType;
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
         * Gets the content type of the input.
         *
         * @return content type of the input.
         */
        public BoxSignRequestInputContentType getContentType() {
            return this.contentType;
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
                switch (memberName) {
                    case "documentTagId":
                        this.documentTagId = value.asString();
                        break;
                    case "text_value":
                        this.textValue = value.asString();
                        break;
                    case "checkbox_value":
                        this.checkboxValue = value.asBoolean();
                        break;
                    case "content_type":
                        this.contentType = BoxSignRequestInputContentType.fromJSONString(value.asString());
                        break;
                    case "date_value":
                        this.dateValue = BoxDateFormat.parseDateOnly(value.asString());
                        break;
                    case "type":
                        this.type = BoxSignRequestInputType.fromJSONString(value.asString());
                        break;
                    case "page_index":
                        this.pageIndex = value.asInt();
                        break;
                }
            } catch (Exception e) {
                throw new BoxDeserializationException(memberName, value.toString(), e);
            }
        }
    }
}


