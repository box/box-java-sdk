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
 * Represents a user status on a custom Box Terms of Service object.
 */
@BoxResourceType("terms_of_service_user_status")
public class BoxTermsOfServiceUserStatus extends BoxResource {
    /**
     * All Terms of Services User Statuses URL Template.
     */
    public static final URLTemplate TERMS_OF_SERVICE_USER_STATUSES_TEMPLATE =
            new URLTemplate("terms_of_service_user_statuses/%s");
    /**
     * Terms of Services User Statuses URL Template.
     */
    public static final URLTemplate ALL_TERMS_OF_SERVICE_USER_STATUSES_TEMPLATE =
            new URLTemplate("terms_of_service_user_statuses");

    /**
     * Constructs a BoxTermsOfServiceUserStatus for a resource with a given ID.
     * @param   api the API connection to be used by the resource.
     * @param   id  the ID of the resource.
     */
    public BoxTermsOfServiceUserStatus(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Creates a User Status on a custom Terms of Service.
     * @param   api                     the API connection to be used by the resource.
     * @param   termsOfServiceID        the ID of the terms of service.
     * @param   isAccepted              the indicator for whether the terms of service has been accepted.
     * @return                          information about the User Status for Terms of Service created.
     */
    public static BoxTermsOfServiceUserStatus.Info create(final BoxAPIConnection api, String termsOfServiceID,
                                                          Boolean isAccepted) {
        return create(api, termsOfServiceID, isAccepted, null);
    }

    /**
     * Creates a User Status on a custom Terms of Service.
     * @param   api                     the API connection to be used by the resource.
     * @param   termsOfServiceID        the ID of the terms of service.
     * @param   isAccepted              the indicator for whether the terms of service has been accepted.
     * @param   userID                  the ID of the user for the terms of service.
     * @return                          information about the User Status for Terms of Service created.
     */
    public static BoxTermsOfServiceUserStatus.Info create(final BoxAPIConnection api, String termsOfServiceID,
                                                                    Boolean isAccepted, String userID) {
        URL url = ALL_TERMS_OF_SERVICE_USER_STATUSES_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");
        JsonObject requestJSON = new JsonObject()
                .add("tos", new JsonObject()
                        .add("type", "terms_of_service")
                        .add("id", termsOfServiceID))
                .add("is_accepted", isAccepted);

        if (userID != null) {
            requestJSON.add("user", new JsonObject()
                    .add("type", "user")
                    .add("id", userID));
        }

        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        BoxTermsOfServiceUserStatus termsOfServiceUserStatus = new BoxTermsOfServiceUserStatus(api,
                responseJSON.get("id").asString());

        return termsOfServiceUserStatus.new Info(responseJSON);
    }

    /**
     * Retrieves a list of User Status for Terms of Service as an Iterable.
     * @param api                   the API connection to be used by the resource.
     * @param termsOfServiceID      the ID of the terms of service.
     * @return                      the Iterable of User Status for Terms of Service.
     */
    public static List<BoxTermsOfServiceUserStatus.Info> getInfo(final BoxAPIConnection api, String termsOfServiceID) {
        return getInfo(api, termsOfServiceID);
    }

    /**
     * Retrieves a list of User Status for Terms of Service as an Iterable.
     * @param api                   the API connection to be used by the resource.
     * @param termsOfServiceID      the ID of the terms of service.
     * @param userID                the ID of the user to retrieve terms of service for.
     * @return                      the Iterable of User Status for Terms of Service.
     */
    public static List<BoxTermsOfServiceUserStatus.Info> getInfo(final BoxAPIConnection api,
                                     String termsOfServiceID, String userID) {
        QueryStringBuilder builder = new QueryStringBuilder();
        builder.appendParam("tos_id", termsOfServiceID);
        if (userID != null) {
            builder.appendParam("user_id", userID);
        }

        URL url = ALL_TERMS_OF_SERVICE_USER_STATUSES_TEMPLATE.buildWithQuery(api.getBaseURL(), builder.toString());
        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        int totalCount = responseJSON.get("total_count").asInt();
        List<BoxTermsOfServiceUserStatus.Info> termsOfServiceUserStatuses = new
                ArrayList<BoxTermsOfServiceUserStatus.Info>(totalCount);
        JsonArray entries = responseJSON.get("entries").asArray();
        for (JsonValue value : entries) {
            JsonObject termsOfServiceUserStatusJSON = value.asObject();
            BoxTermsOfServiceUserStatus termsOfServiceUserStatus = new
                    BoxTermsOfServiceUserStatus(api, termsOfServiceUserStatusJSON.get("id").asString());
            BoxTermsOfServiceUserStatus.Info info = termsOfServiceUserStatus.new Info(termsOfServiceUserStatusJSON);
            termsOfServiceUserStatuses.add(info);
        }

        return termsOfServiceUserStatuses;
    }

    /**
     * Updates the information about the user status for this terms of service with any info fields that have
     * been modified locally.
     * @param info the updated info.
     */
    public void updateInfo(BoxTermsOfServiceUserStatus.Info info) {
        URL url = TERMS_OF_SERVICE_USER_STATUSES_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(info.getPendingChanges());

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        info.update(responseJSON);
    }

    /**
     * Contains information about the user status on a terms of service.
     */
    public class Info extends BoxResource.Info {

        /**
         * @see #getTermsOfService()
         */
        private BoxTermsOfService.Info termsOfService;

        /**
         * @see #getUser()
         */
        private BoxUser.Info user;

        /**
         * @see #getType()
         */
        private String termsOfServiceUserStatusType;


        /**
         * @see #getIsAccepted() ()
         */
        private Boolean isAccepted;

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
            return BoxTermsOfServiceUserStatus.this;
        }

        /**
         * @return the terms of service.
         */
        public BoxTermsOfService.Info getTermsOfService() {
            return this.termsOfService;
        }

        /**
         * @return the user.
         */
        public BoxUser.Info getUser() {
            return this.user;
        }

        /**
         * @return the is_accepted state of the terms of service.
         */
        public Boolean getIsAccepted() {
            return this.isAccepted;
        }

        /**
         * Accept or decline the terms of service.
         *
         * @param enabled the new user status of the terms of service.
         */
        public void setIsAccepted(Boolean enabled) {
            this.isAccepted = enabled;
            this.addPendingChange("is_accepted", this.isAccepted);
        }

        /**
         * @return the type of the terms of service user status.
         */
        public String getType() {
            return this.termsOfServiceUserStatusType;
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
                if (memberName.equals("tos")) {
                    JsonObject tosJSON = value.asObject();
                    String termsOfServiceID = tosJSON.get("id").asString();
                    BoxTermsOfService termsOfService = new BoxTermsOfService(getAPI(), termsOfServiceID);
                    this.termsOfService = termsOfService.new Info(tosJSON);
                } else if (memberName.equals("user")) {
                    JsonObject userJSON = value.asObject();
                    String userID = userJSON.get("id").asString();
                    BoxUser user = new BoxUser(getAPI(), userID);
                    this.user = user.new Info(userJSON);
                } else if (memberName.equals("is_accepted")) {
                    this.isAccepted = value.asBoolean();
                } else if (memberName.equals("created_at")) {
                    this.createdAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("modified_at")) {
                    this.modifiedAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("type")) {
                    this.termsOfServiceUserStatusType = value.asString();
                }
            } catch (ParseException e) {
                assert false : "Terms of Service User Status Parsing failed: " + e;
            }
        }
    }
}
