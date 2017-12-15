package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import com.box.sdk.http.HttpMethod;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a collaboration whitelist between a user and a Box Enterprise. Collaboration Whitelist enables a Box
 * Enterprise(only available if you have Box Governance) to manage a set of approved users that can collaborate
 * with an enterprise.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("collaboration_whitelist_exempt_target")
public class BoxCollaborationWhitelistExemptTarget extends BoxResource {
    /**
     * Collaboration Whitelist Exempt Target Entries URL Template.
     */
    public static final URLTemplate COLLABORATION_WHITELIST_EXEMPT_TARGET_ENTRIES_URL_TEMPLATE =
            new URLTemplate("collaboration_whitelist_exempt_targets");

    /**
     * Collaboration Whitelist Exempt Target Entries URL Template with given ID.
     */
    public static final URLTemplate COLLABORATION_WHITELIST_EXEMPT_TARGET_ENTRY_URL_TEMPLATE =
            new URLTemplate("collaboration_whitelist_exempt_targets/%s");

    /**
     * The default limit of entries per response.
     */
    private static final int DEFAULT_LIMIT = 100;

    /**
     * Constructs a BoxCollaborationWhitelistExemptTarget for a collaboration whitelist with a give ID.
     *
     * @param api   the API connection to be used by the collaboration whitelist.
     * @param id    the ID of the collaboration whitelist.
     */
    public BoxCollaborationWhitelistExemptTarget(BoxAPIConnection api, String id) {

        super(api, id);
    }

    /**
     * Creates a collaboration whitelist for a Box User with a given ID.
     * @param api       the API connection to be used by the collaboration whitelist.
     * @param userID    the ID of the Box User to add to the collaboration whitelist.
     * @return          information about the collaboration whitelist created for user.
     */
    public static BoxCollaborationWhitelistExemptTarget.Info create(final BoxAPIConnection api, String userID) {
        URL url = COLLABORATION_WHITELIST_EXEMPT_TARGET_ENTRIES_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, HttpMethod.POST);
        JsonObject requestJSON = new JsonObject()
                .add("user", new JsonObject()
                    .add("type", "user")
                    .add("id", userID));

        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        BoxCollaborationWhitelistExemptTarget userWhitelist = new BoxCollaborationWhitelistExemptTarget(api,
                responseJSON.get("id").asString());

        return userWhitelist.new Info(responseJSON);
    }

    /**
     * Retrieves information for a collaboration whitelist for a given whitelist ID.
     *
     * @return information about this {@link BoxCollaborationWhitelistExemptTarget}.
     */
    public BoxCollaborationWhitelistExemptTarget.Info getInfo() {
        URL url = COLLABORATION_WHITELIST_EXEMPT_TARGET_ENTRY_URL_TEMPLATE.build(this.getAPI().getBaseURL(),
                this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, HttpMethod.GET);
        BoxJSONResponse response = (BoxJSONResponse) request.send();

        return new Info(JsonObject.readFrom(response.getJSON()));
    }

    /**
     * Returns all the collaboration whitelisting for user with default limit set to 100.
     *
     * @param api       the API connection to be use by the resource.
     * @param fields    the fields to retrieve.
     * @return  an iterable with all the collaboration whitelists for users met search conditions.
     */
    public static Iterable<BoxCollaborationWhitelistExemptTarget.Info> getAll(final BoxAPIConnection api,
                                                                              String ... fields) {
        return getAll(api, DEFAULT_LIMIT, fields);
    }

    /**
     * Returns all the collaboration whitelisting for user with specified filters.
     * @param api       the API connection to be used by the resource.
     * @param limit     the number of collaboration whitelists to retrieve.
     * @param fields    the fields to retrieve.
     * @return  an iterable with all the collaboration whitelists for users met search conditions.
     */
    public static Iterable<BoxCollaborationWhitelistExemptTarget.Info> getAll(final BoxAPIConnection api, int limit,
                                                                               String ... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }

        URL url = COLLABORATION_WHITELIST_EXEMPT_TARGET_ENTRIES_URL_TEMPLATE.buildWithQuery(api.getBaseURL(),
                builder.toString());
        return new BoxResourceIterable<BoxCollaborationWhitelistExemptTarget.Info>(api, url, limit) {

            @Override
            protected BoxCollaborationWhitelistExemptTarget.Info factory(JsonObject jsonObject) {
                BoxCollaborationWhitelistExemptTarget userWhitelist = new BoxCollaborationWhitelistExemptTarget(
                        api, jsonObject.get("id").asString());

                return userWhitelist.new Info(jsonObject);
            }
        };
    }

    /**
     * Deletes this collaboration whitelist entry for user.
     */
    public void delete() {
        BoxAPIConnection api = this.getAPI();
        URL url = COLLABORATION_WHITELIST_EXEMPT_TARGET_ENTRY_URL_TEMPLATE.build(api.getBaseURL(),
                this.getID());

        BoxAPIRequest request = new BoxAPIRequest(api, url, HttpMethod.DELETE);
        BoxAPIResponse response = request.send();
        response.disconnect();
    }
    /**
     * Contains information about a BoxCollaborationWhitelistExemptTarget.
     */
    public class Info extends BoxResource.Info {
        private String type;
        private BoxUser.Info user;
        private BoxEnterprise enterprise;
        private Date createdAt;
        private Date modifiedAt;

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

        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * Gets the type of the collaboration whitelist for user.
         *
         * @return the type of the collaboration whitelist for user.
         */
        public String getType() {

            return this.type;
        }
        /**
         * Gets the user added to the collaboration whitelist.
         *
         * @return the user in the collaboration whitelist.
         */
        public BoxUser.Info getUser() {

            return this.user;
        }

        /**
         * Gets the enterprise that the collaboration whitelist for user belongs to.
         *
         * @return the enterprise that the collaboration whitelist for user belongs to.
         */
        public BoxEnterprise getEnterprise() {

            return this.enterprise;
        }

        /**
         * Gets the time the collaboration whitelist was created for user.
         *
         * @return the time the collaboration whitelist was created for user.
         */
        public Date getCreatedAt() {

            return this.createdAt;
        }

        /**
         * Gets the last modified time of the collaboration whitelist for user.
         *
         * @return the last modified time of the collaboration whitelist for user.
         */
        public Date getModifiedAt() {

            return this.modifiedAt;
        }

        @Override
        public BoxCollaborationWhitelistExemptTarget getResource() {
            return BoxCollaborationWhitelistExemptTarget.this;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            try {
                if (memberName.equals("user")) {
                    JsonObject userJSON = value.asObject();
                    String userID = userJSON.get("id").asString();
                    BoxUser user = new BoxUser(getAPI(), userID);
                    this.user = user.new Info(userJSON);

                } else if (memberName.equals("type")) {
                    this.type = value.asString();

                } else if (memberName.equals("enterprise")) {
                    JsonObject jsonObject = value.asObject();
                    this.enterprise = new BoxEnterprise(jsonObject);

                } else if (memberName.equals("created_at")) {
                    this.createdAt = BoxDateFormat.parse(value.asString());

                } else if (memberName.equals("modified_at")) {
                    this.modifiedAt = BoxDateFormat.parse(value.asString());
                }
            } catch (ParseException e) {
                assert false : "Error in parsing BoxCollaborationWhitelistExemptTarget JSON object";
            }
        }
    }
}
