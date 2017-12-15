package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import com.box.sdk.http.HttpMethod;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;


/**
 * Represents a collaboration whitelist between a domain and a Box Enterprise. Collaboration Whitelist enables a Box
 * Enterprise(only available if you have Box Governance) to manage a set of approved domains that can collaborate
 * with an enterprise.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("collaboration_whitelist_entry")
public class BoxCollaborationWhitelist extends BoxResource {
    /**
     * Collaboration Whitelist Entries URL Template.
     */
    public static final URLTemplate COLLABORATION_WHITELIST_ENTRIES_URL_TEMPLATE =
            new URLTemplate("collaboration_whitelist_entries");

    /**
     * Collaboration Whitelist Entries URL Template with given ID.
     */
    public static final URLTemplate COLLABORATION_WHITELIST_ENTRY_URL_TEMPLATE =
            new URLTemplate("collaboration_whitelist_entries/%s");

    /**
     * The default limit of entries per response.
     */
    private static final int DEFAULT_LIMIT = 100;

    /**
     * Constructs a BoxCollaborationWhitelist for a collaboration whitelist with a given ID.
     *
     * @param api the API connection to be used by the collaboration whitelist.
     * @param id  the ID of the collaboration whitelist.
     */
    public BoxCollaborationWhitelist(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Creates a new Collaboration Whitelist for a domain.
     * @param   api                     the API connection to be used by the resource.
     * @param   domain                  the domain to be added to a collaboration whitelist for a Box Enterprise.
     * @param   direction               an enum representing the direction of the collaboration whitelist. Can be set to
     *                                  inbound, outbound, or both.
     * @return                          information about the collaboration whitelist created.
     */
    public static BoxCollaborationWhitelist.Info create(final BoxAPIConnection api, String domain,
                                                        WhitelistDirection direction) {

        URL url = COLLABORATION_WHITELIST_ENTRIES_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, HttpMethod.POST);
        JsonObject requestJSON = new JsonObject()
                .add("domain", domain)
                .add("direction", direction.toString());

        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        BoxCollaborationWhitelist domainWhitelist =
                new BoxCollaborationWhitelist(api, responseJSON.get("id").asString());

        return domainWhitelist.new Info(responseJSON);
    }

    /**
     * @return information about this {@link BoxCollaborationWhitelist}.
     */
    public BoxCollaborationWhitelist.Info getInfo() {
        URL url = COLLABORATION_WHITELIST_ENTRY_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, HttpMethod.GET);
        BoxJSONResponse response = (BoxJSONResponse) request.send();

        return new Info(JsonObject.readFrom(response.getJSON()));
    }

    /**
     * Returns all the collaboration whitelisting with specified filters.
     * @param api        the API connection to be used by the resource.
     * @param fields     the fields to retrieve.
     * @return an iterable with all the collaboration whitelists met search conditions.
     */
    public static Iterable<BoxCollaborationWhitelist.Info> getAll(final BoxAPIConnection api, String ... fields) {

        return getAll(api, DEFAULT_LIMIT, fields);
    }

    /**
     * Returns all the collaboration whitelisting with specified filters.
     * @param api       the API connection to be used by the resource.
     * @param limit     the limit of items per single response. The default value is 100.
     * @param fields    the fields to retrieve.
     * @return an iterable with all the collaboration whitelists met search conditions.
     */
    public static Iterable<BoxCollaborationWhitelist.Info> getAll(final BoxAPIConnection api, int limit,
                                                                  String ... fields) {

        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }

        URL url = COLLABORATION_WHITELIST_ENTRIES_URL_TEMPLATE.buildWithQuery(api.getBaseURL(), builder.toString());
        return new BoxResourceIterable<BoxCollaborationWhitelist.Info>(api, url, limit) {

            @Override
            protected BoxCollaborationWhitelist.Info factory(JsonObject jsonObject) {
                BoxCollaborationWhitelist whitelist = new BoxCollaborationWhitelist(
                        api, jsonObject.get("id").asString());

                return whitelist.new Info(jsonObject);
            }
        };
    }

    /**
     * Deletes this collaboration whitelist.
     */
    public void delete() {
        BoxAPIConnection api = this.getAPI();
        URL url = COLLABORATION_WHITELIST_ENTRY_URL_TEMPLATE.build(api.getBaseURL(), this.getID());

        BoxAPIRequest request = new BoxAPIRequest(api, url, HttpMethod.DELETE);
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Contains information about a BoxCollaborationWhitelist.
     */
    public class Info extends BoxResource.Info {
        private String type;
        private String domain;
        private WhitelistDirection direction;
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

        Info(JsonObject jsonObject)  {
            super(jsonObject);
        }

        /**
         * Gets the type of the collaboration whitelist.
         *
         * @return the type for the collaboration whitelist.
         */
        public String getType() {

            return this.type;
        }

        /**
         * Gets the domain added to the collaboration whitelist.
         *
         * @return the domain in the collaboration whitelist
         */
        public String getDomain() {

            return this.domain;
        }

        /**
         * Get the direction of the collaboration whitelist. Values can be inbound, outbound, or
         * both.
         *
         * @return the direction set for the collaboration whitelist. Values can be inbound, outbound, or both.
         */
        public WhitelistDirection getDirection() {

            return this.direction;
        }

        /**
         * Gets the enterprise that the collaboration whitelist belongs to.
         *
         * @return the enterprise that the collaboration whitelist belongs to.
         */
        public BoxEnterprise getEnterprise() {

            return this.enterprise;
        }

        /**
         * Gets the time the collaboration whitelist was created.
         *
         * @return the time the collaboration whitelist was created.
         */
        public Date getCreatedAt() {

            return this.createdAt;
        }

        /**
         * Gets the time the collaboration whitelist was last modified.
         *
         * @return the time the collaboration whitelist was last modified.
         */
        public Date getModifiedAt() {

            return this.modifiedAt;
        }

        @Override
        public BoxCollaborationWhitelist getResource() {
            return BoxCollaborationWhitelist.this;
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
                    this.direction = WhitelistDirection.fromDirection(value.asString());

                } else if (memberName.equals("enterprise")) {
                    JsonObject jsonObject = value.asObject();
                    this.enterprise = new BoxEnterprise(jsonObject);

                } else if (memberName.equals("created_at")) {
                    this.createdAt = BoxDateFormat.parse(value.asString());

                } else if (memberName.equals("modified_at")) {
                    this.modifiedAt = BoxDateFormat.parse(value.asString());

                }
            } catch (ParseException e) {
                assert false : "Error in parsing BoxCollaborationWhitelist JSON Object";
            }
        }
    }

    /**
     * Enumerates the direction of the collaboration whitelist.
     */
    public enum WhitelistDirection {
        /**
         * Whitelist inbound collaboration.
         */
        INBOUND("inbound"),

        /**
         * Whitelist outbound collaboration.
         */
        OUTBOUND("outbound"),

        /**
         * Whitelist both inbound and outbound collaboration.
         */
        BOTH("both");

        private final String direction;

        WhitelistDirection(String direction) {

            this.direction = direction;
        }

        static WhitelistDirection fromDirection(String direction) {
            if (direction.equals("inbound")) {
                return WhitelistDirection.INBOUND;
            } else if (direction.equals("outbound")) {
                return WhitelistDirection.OUTBOUND;
            } else if (direction.equals("both")) {
                return WhitelistDirection.BOTH;
            } else {
                return null;
            }
        }

        /**
         * Returns a String containing the current direction of the collaboration whitelisting.
         * @return a String containing information about the direction of the collaboration whitelisting.
         */
        public String toString() {

            return this.direction;
        }
    }
}
