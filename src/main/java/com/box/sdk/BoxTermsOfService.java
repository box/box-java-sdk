package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a custom Box Terms of Service object.
 */
@BoxResourceType("terms_of_service")
public class BoxTermsOfService extends BoxResource {
    /**
     * Terms of Services URL Template.
     */
    public static final URLTemplate TERMS_OF_SERVICE_URL_TEMPLATE = new URLTemplate("terms_of_services/%s");
    /**
     * All Terms of Services URL Template.
     */
    public static final URLTemplate ALL_TERMS_OF_SERVICES_URL_TEMPLATE = new URLTemplate("terms_of_services");

    /**
     * Constructs a BoxTermsOfService for a Box Enterprise with a given ID.
     * @param   api the API connection to be used by the resource.
     * @param   id  the ID of the resource.
     */
    public BoxTermsOfService(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Creates a new Terms of Services.
     * @param   api                     the API connection to be used by the resource.
     * @param   termsOfServiceStatus    the current status of the terms of services. Set to "enabled" or "disabled".
     * @param   termsOfServiceType      the scope of terms of service. Set to "external" or "managed".
     * @param   text                    the text field of terms of service containing terms of service agreement info.
     * @return                          information about the Terms of Service created.
     */
    public static BoxTermsOfService.Info create(BoxAPIConnection api,
                                                BoxTermsOfService.TermsOfServiceStatus termsOfServiceStatus,
                                                BoxTermsOfService.TermsOfServiceType termsOfServiceType, String text) {
        URL url = ALL_TERMS_OF_SERVICES_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");
        JsonObject requestJSON = new JsonObject()
                .add("status", termsOfServiceStatus.toString())
                .add("tos_type", termsOfServiceType.toString())
                .add("text", text);

        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        BoxTermsOfService createdTermsOfServices = new BoxTermsOfService(api, responseJSON.get("id").asString());

        return createdTermsOfServices.new Info(responseJSON);
    }

    /**
     * Updates the information about this terms of service with modified locally info.
     * Only status and text can be modified.
     * @param info the updated info.
     */
    public void updateInfo(BoxTermsOfService.Info info) {
        URL url = TERMS_OF_SERVICE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(info.getPendingChanges());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        info.update(responseJSON);
    }

    /**
     * @return Gets information about this {@link BoxTermsOfService}.
     */
    public BoxTermsOfService.Info getInfo() {
        URL url = TERMS_OF_SERVICE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();

        return new Info(JsonObject.readFrom(response.getJSON()));
    }

    /**
     * Retrieves a list of Terms of Services that belong to your Enterprise as an Iterable.
     * @param api   the API connection to be used by the resource.
     * @return      the Iterable of Terms of Service in your Enterprise.
     */
    public static List<BoxTermsOfService.Info> getAllTermsOfServices(final BoxAPIConnection api) {
        return getAllTermsOfServices(api, null);
    }

    /**
     * Retrieves a list of Terms of Service that belong to your Enterprise as an Iterable.
     * @param api                   api the API connection to be used by the resource.
     * @param termsOfServiceType    the type of terms of service to be retrieved. Can be set to "managed" or "external"
     * @return                      the Iterable of Terms of Service in an Enterprise that match the filter parameters.
     */
    public static List<BoxTermsOfService.Info> getAllTermsOfServices(final BoxAPIConnection api,
                                                                     BoxTermsOfService.TermsOfServiceType
                                                                             termsOfServiceType) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (termsOfServiceType != null) {
            builder.appendParam("tos_type", termsOfServiceType.toString());
        }

        URL url = ALL_TERMS_OF_SERVICES_URL_TEMPLATE.buildWithQuery(api.getBaseURL(), builder.toString());
        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        int totalCount = responseJSON.get("total_count").asInt();
        List<BoxTermsOfService.Info> termsOfServices = new ArrayList<BoxTermsOfService.Info>(totalCount);
        JsonArray entries = responseJSON.get("entries").asArray();
        for (JsonValue value : entries) {
            JsonObject termsOfServiceJSON = value.asObject();
            BoxTermsOfService termsOfService = new BoxTermsOfService(api, termsOfServiceJSON.get("id").asString());
            BoxTermsOfService.Info info = termsOfService.new Info(termsOfServiceJSON);
            termsOfServices.add(info);
        }

        return termsOfServices;
    }

    /**
     * Enumerates the possible types of terms of service.
     */
    public enum TermsOfServiceType {
        /**
         * The terms of service is managed by an enterprise.
         */
        MANAGED("managed"),

        /**
         * The terms of service is external to an enterprise.
         */
        EXTERNAL("external");

        private final String tosType;

        TermsOfServiceType(String tosType) {
            this.tosType = tosType;
        }

        static TermsOfServiceType fromTosType(String tosType) {
            if (tosType.equals("managed")) {
                return TermsOfServiceType.MANAGED;
            } else if (tosType.equals("external")) {
                return TermsOfServiceType.EXTERNAL;
            } else {
                System.out.print("Invalid Terms of Service Type");
                return null;
            }
        }

        /**
         * Returns a String containing terms of service type.
         * @return a String containing information about terms of service type.
         */
        public String toString() {
            return this.tosType;
        }
    }

    /**
     * Enumerates the possible status that a terms of service can have.
     */
    public enum TermsOfServiceStatus {
        /**
         * The terms of service is enabled.
         */
        ENABLED("enabled"),

        /**
         * The terms of service is disabled.
         */
        DISABLED("disabled");

        private final String status;

        TermsOfServiceStatus(String status) {
            this.status = status;
        }

        static TermsOfServiceStatus fromStatus(String status) {
            if (status.equals("enabled")) {
                return TermsOfServiceStatus.ENABLED;
            } else if (status.equals("disabled")) {
                return TermsOfServiceStatus.DISABLED;
            } else {
                System.out.print("Invalid Terms of Service Status");
                return null;
            }
        }

        /**
         * Returns a String containing current status of the terms of service.
         * @return a String containing information about the status of the terms of service.
         */
        public String toString() {
            return this.status;
        }
    }

    /**
     * Contains information about the terms of service.
     */
    public class Info extends BoxResource.Info {

        /**
         * @see #getStatus()
         */
        private TermsOfServiceStatus status;

        /**
         * @see #getType()
         */
        private String type;

        /**
         * @see #getTosType()
         */
        private TermsOfServiceType tosType;

        /**
         * @see #getEnterprise()
         */
        private BoxEnterprise enterprise;

        /**
         * @see #getText()
         */
        private String text;

        /**
         * @see #getCreatedAt()
         */
        private Date createdAt;

        /**
         * @see #getModifiedAt()
         */
        private Date modifiedAt;

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
            return BoxTermsOfService.this;
        }

        /**
         * TermsOfServiceStatus can be "enabled" or "disabled".
         * @return the status of the terms of service.
         */
        public TermsOfServiceStatus getStatus() {
            return this.status;
        }

        /**
         * Sets the status of the terms of service in order to enable or disable it.
         *
         * @param status the new status of the terms of service.
         */
        public void setStatus(TermsOfServiceStatus status) {
            this.status = status;
            this.addPendingChange("status", status.toString());
        }

        /**
         * The type is terms_of_service.
         * @return the type terms_of_service.
         */
        public String getType() {
            return this.type;
        }

        /**
         * TermsOfServiceType can be "managed" or "external".
         * @return the type of the terms of service.
         */
        public TermsOfServiceType getTosType() {
            return this.tosType;
        }


        /**
         * @return the enterprise for the terms of service.
         */
        public BoxEnterprise getEnterprise() {
            return this.enterprise;
        }


        /**
         * @return the text of the terms of service.
         */
        public String getText() {
            return this.text;
        }

        /**
         * Sets the text of the terms of service.
         *
         * @param text the new text of the terms of service.
         */
        public void setText(String text) {
            this.text = text;
            this.addPendingChange("text", text);
        }


        /**
         * @return time the policy was created.
         */
        public Date getCreatedAt() {
            return this.createdAt;
        }

        /**
         * @return time the policy was modified.
         */
        public Date getModifiedAt() {
            return this.modifiedAt;
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
                if (memberName.equals("status")) {
                    this.status = TermsOfServiceStatus.fromStatus(value.asString());
                } else if (memberName.equals("enterprise")) {
                    JsonObject jsonObject = value.asObject();
                    this.enterprise = new BoxEnterprise(jsonObject);
                } else if (memberName.equals("type")) {
                    this.type = value.asString();
                } else if (memberName.equals("tos_type")) {
                    this.tosType = TermsOfServiceType.fromTosType(value.asString());
                } else if (memberName.equals("text")) {
                    this.text = value.asString();
                } else if (memberName.equals("created_at")) {
                    this.createdAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("modified_at")) {
                    this.modifiedAt = BoxDateFormat.parse(value.asString());
                }
            } catch (ParseException e) {
                assert false : "Terms of Service Parsing failed: " + e;
            }
        }
    }
}
