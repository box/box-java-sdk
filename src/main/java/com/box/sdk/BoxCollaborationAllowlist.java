package com.box.sdk;

import com.box.sdk.http.HttpMethod;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.net.URL;
import java.util.Date;


/**
 * Represents a collaboration allowlist between a domain and a Box Enterprise. Collaboration Allowlist enables a Box
 * Enterprise(only available if you have Box Governance) to manage a set of approved domains that can collaborate
 * with an enterprise.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("collaboration_allowlist_entry")
public class BoxCollaborationAllowlist extends BoxResource {
    /**
     * Collaboration Allowlist Entries URL Template.
     */
    public static final URLTemplate COLLABORATION_ALLOWLIST_ENTRIES_URL_TEMPLATE =
        new URLTemplate("collaboration_whitelist_entries");

    /**
     * Collaboration Allowlist Entries URL Template with given ID.
     */
    public static final URLTemplate COLLABORATION_ALLOWLIST_ENTRY_URL_TEMPLATE =
        new URLTemplate("collaboration_whitelist_entries/%s");

    /**
     * The default limit of entries per response.
     */
    private static final int DEFAULT_LIMIT = 100;

    /**
     * Constructs a BoxCollaborationAllowlist for a collaboration allowlist with a given ID.
     *
     * @param api the API connection to be used by the collaboration allowlist.
     * @param id  the ID of the collaboration allowlist.
     */
    public BoxCollaborationAllowlist(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Creates a new Collaboration Allowlist for a domain.
     *
     * @param api       the API connection to be used by the resource.
     * @param domain    the domain to be added to a collaboration allowlist for a Box Enterprise.
     * @param direction an enum representing the direction of the collaboration allowlist. Can be set to
     *                  inbound, outbound, or both.
     * @return information about the collaboration allowlist created.
     */
    public static BoxCollaborationAllowlist.Info create(final BoxAPIConnection api, String domain,
                                                        AllowlistDirection direction) {

        URL url = COLLABORATION_ALLOWLIST_ENTRIES_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, HttpMethod.POST);
        JsonObject requestJSON = new JsonObject()
            .add("domain", domain)
            .add("direction", direction.toString());

        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
        BoxCollaborationAllowlist domainAllowlist =
            new BoxCollaborationAllowlist(api, responseJSON.get("id").asString());

        return domainAllowlist.new Info(responseJSON);
    }

    /**
     * Returns all the collaboration allowlisting with specified filters.
     *
     * @param api    the API connection to be used by the resource.
     * @param fields the fields to retrieve.
     * @return an iterable with all the collaboration allowlists met search conditions.
     */
    public static Iterable<BoxCollaborationAllowlist.Info> getAll(final BoxAPIConnection api, String... fields) {

        return getAll(api, DEFAULT_LIMIT, fields);
    }

    /**
     * Returns all the collaboration allowlisting with specified filters.
     *
     * @param api    the API connection to be used by the resource.
     * @param limit  the limit of items per single response. The default value is 100.
     * @param fields the fields to retrieve.
     * @return an iterable with all the collaboration allowlists met search conditions.
     */
    public static Iterable<BoxCollaborationAllowlist.Info> getAll(final BoxAPIConnection api, int limit,
                                                                  String... fields) {

        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }

        URL url = COLLABORATION_ALLOWLIST_ENTRIES_URL_TEMPLATE.buildWithQuery(api.getBaseURL(), builder.toString());
        return new BoxResourceIterable<BoxCollaborationAllowlist.Info>(api, url, limit) {

            @Override
            protected BoxCollaborationAllowlist.Info factory(JsonObject jsonObject) {
                BoxCollaborationAllowlist allowlist = new BoxCollaborationAllowlist(
                    api, jsonObject.get("id").asString());

                return allowlist.new Info(jsonObject);
            }
        };
    }

    /**
     * @return information about this {@link BoxCollaborationAllowlist}.
     */
    public BoxCollaborationAllowlist.Info getInfo() {
        URL url = COLLABORATION_ALLOWLIST_ENTRY_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, HttpMethod.GET);
        BoxJSONResponse response = (BoxJSONResponse) request.send();

        return new Info(Json.parse(response.getJSON()).asObject());
    }

    /**
     * Deletes this collaboration allowlist.
     */
    public void delete() {
        BoxAPIConnection api = this.getAPI();
        URL url = COLLABORATION_ALLOWLIST_ENTRY_URL_TEMPLATE.build(api.getBaseURL(), this.getID());

        BoxAPIRequest request = new BoxAPIRequest(api, url, HttpMethod.DELETE);
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Enumerates the direction of the collaboration allowlist.
     */
    public enum AllowlistDirection {
        /**
         * Allowlist inbound collaboration.
         */
        INBOUND("inbound"),

        /**
         * Allowlist outbound collaboration.
         */
        OUTBOUND("outbound"),

        /**
         * Allowlist both inbound and outbound collaboration.
         */
        BOTH("both");

        private final String direction;

        AllowlistDirection(String direction) {

            this.direction = direction;
        }

        static AllowlistDirection fromDirection(String direction) {
            if (direction.equals("inbound")) {
                return AllowlistDirection.INBOUND;
            } else if (direction.equals("outbound")) {
                return AllowlistDirection.OUTBOUND;
            } else if (direction.equals("both")) {
                return AllowlistDirection.BOTH;
            } else {
                return null;
            }
        }

        /**
         * Returns a String containing the current direction of the collaboration allowlisting.
         *
         * @return a String containing information about the direction of the collaboration allowlisting.
         */
        public String toString() {

            return this.direction;
        }
    }

    /**
     * Contains information about a BoxCollaborationAllowlist.
     */
    public class Info extends BoxResource.Info {
        private String type;
        private String domain;
        private AllowlistDirection direction;
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
         * Gets the type of the collaboration allowlist.
         *
         * @return the type for the collaboration allowlist.
         */
        public String getType() {

            return this.type;
        }

        /**
         * Gets the domain added to the collaboration allowlist.
         *
         * @return the domain in the collaboration allowlist
         */
        public String getDomain() {

            return this.domain;
        }

        /**
         * Get the direction of the collaboration allowlist. Values can be inbound, outbound, or
         * both.
         *
         * @return the direction set for the collaboration allowlist. Values can be inbound, outbound, or both.
         */
        public AllowlistDirection getDirection() {

            return this.direction;
        }

        /**
         * Gets the enterprise that the collaboration allowlist belongs to.
         *
         * @return the enterprise that the collaboration allowlist belongs to.
         */
        public BoxEnterprise getEnterprise() {

            return this.enterprise;
        }

        /**
         * Gets the time the collaboration allowlist was created.
         *
         * @return the time the collaboration allowlist was created.
         */
        public Date getCreatedAt() {

            return this.createdAt;
        }

        /**
         * Gets the time the collaboration allowlist was last modified.
         *
         * @return the time the collaboration allowlist was last modified.
         */
        public Date getModifiedAt() {

            return this.modifiedAt;
        }

        @Override
        public BoxCollaborationAllowlist getResource() {
            return BoxCollaborationAllowlist.this;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            try {
                if (memberName.equals("domain")) {
                    this.domain = value.asString();

                } else if (memberName.equals("type")) {
                    this.type = value.asString();

                } else if (memberName.equals("direction")) {
                    this.direction = AllowlistDirection.fromDirection(value.asString());

                } else if (memberName.equals("enterprise")) {
                    JsonObject jsonObject = value.asObject();
                    this.enterprise = new BoxEnterprise(jsonObject);

                } else if (memberName.equals("created_at")) {
                    this.createdAt = BoxDateFormat.parse(value.asString());

                } else if (memberName.equals("modified_at")) {
                    this.modifiedAt = BoxDateFormat.parse(value.asString());

                }
            } catch (Exception e) {
                throw new BoxDeserializationException(memberName, value.toString(), e);
            }
        }
    }
}
