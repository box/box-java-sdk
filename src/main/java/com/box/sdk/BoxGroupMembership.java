package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a relationship between a user and a group.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("group_membership")
public class BoxGroupMembership extends BoxResource {

    /**
     * The URL template for all group membership requests.
     * @see #getInfo()
     */
    private static final URLTemplate MEMBERSHIP_URL_TEMPLATE = new URLTemplate("group_memberships/%s");

    /**
     * Constructs a BoxGroupMembership for a group membership with a given ID.
     * @param  api the API connection to be used by the group membership.
     * @param  id  the ID of the group membership.
     */
    public BoxGroupMembership(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Gets information about this group membership.
     * @return info about this group membership.
     */
    public Info getInfo() {
        BoxAPIConnection api = this.getAPI();
        URL url = MEMBERSHIP_URL_TEMPLATE.build(api.getBaseURL(), this.getID());

        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        return new Info(jsonObject);
    }

    /**
     * Updates the information about this group membership with any info fields that have been modified locally.
     * @param info the updated info.
     */
    public void updateInfo(Info info) {
        BoxAPIConnection api = this.getAPI();
        URL url = MEMBERSHIP_URL_TEMPLATE.build(api.getBaseURL(), this.getID());

        BoxJSONRequest request = new BoxJSONRequest(api, url, "PUT");
        request.setBody(info.getPendingChanges());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        info.update(jsonObject);
    }

    /**
     * Deletes this group membership.
     */
    public void delete() {
        BoxAPIConnection api = this.getAPI();
        URL url = MEMBERSHIP_URL_TEMPLATE.build(api.getBaseURL(), this.getID());

        BoxAPIRequest request = new BoxAPIRequest(api, url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Contains information about a BoxGroupMembership.
     */
    public class Info extends BoxResource.Info {

        /**
         * @see #getUser()
         */
        private BoxUser.Info user;

        /**
         * @see #getGroup()
         */
        private BoxGroup.Info group;

        /**
         * @see #getRole()
         */
        private Role role;

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
         * Gets the user belonging to the group.
         *
         * <p>Note: the BoxUser.Info returned by this method will only have the ID, name, and login fields
         * populated.</p>
         *
         * @return the user belonging to the group.
         */
        public BoxUser.Info getUser() {
            return this.user;
        }

        /**
         * Gets the group the user belongs to.
         *
         * <p>Note: the BoxGroup.Info returned by this method will only have the ID and name fields populated.</p>
         *
         * @return the group the user belongs to.
         */
        public BoxGroup.Info getGroup() {
            return this.group;
        }

        /**
         * Gets the level of access the user has.
         * @return the level of access the user has.
         */
        public Role getRole() {
            return this.role;
        }

        /**
         * Sets the level of access the user has.
         * @param role the new level of access to give the user.
         */
        public void setRole(Role role) {
            this.role = role;
            this.addPendingChange("role", role.toJSONString());
        }

        /**
         * Gets the time the group membership was created.
         * @return the time the group membership was created.
         */
        public Date getCreatedAt() {
            return this.createdAt;
        }

        /**
         * Gets the time the group membership was last modified.
         * @return the time the group membership was last modified.
         */
        public Date getModifiedAt() {
            return this.modifiedAt;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BoxGroupMembership getResource() {
            return BoxGroupMembership.this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();

            try {
                if (memberName.equals("user")) {
                    JsonObject userJSON = value.asObject();
                    if (this.user == null) {
                        String userID = userJSON.get("id").asString();
                        BoxUser user = new BoxUser(getAPI(), userID);
                        this.user = user.new Info(userJSON);
                    } else {
                        this.user.update(userJSON);
                    }

                } else if (memberName.equals("group")) {
                    JsonObject groupJSON = value.asObject();
                    if (this.group == null) {
                        String userID = groupJSON.get("id").asString();
                        BoxGroup group = new BoxGroup(getAPI(), userID);
                        this.group = group.new Info(groupJSON);
                    } else {
                        this.group.update(groupJSON);
                    }

                } else if (memberName.equals("role")) {
                    this.role = Role.fromJSONString(value.asString());

                } else if (memberName.equals("created_at")) {
                    this.createdAt = BoxDateFormat.parse(value.asString());

                } else if (memberName.equals("modified_at")) {
                    this.modifiedAt = BoxDateFormat.parse(value.asString());

                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }

    /**
     * Enumerates the possible roles that a user can have within a group.
     */
    public enum Role {
        /**
         * The user is an administrator in the group.
         */
        ADMIN ("admin"),

        /**
         * The user is a submaster in the group.
         */
        SUBMASTER ("submaster"),

        /**
         * The user is a regular member in the group.
         */
        MEMBER ("member");

        /**
         * String representation of the role.
         */
        private final String jsonValue;

        /**
         * Constructor.
         * @param jsonValue srting representation of the role.
         */
        private Role(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        /**
         * Creates the role from given string.
         * @param jsonValue string to be converted to role.
         * @return the role, created from string value.
         */
        static Role fromJSONString(String jsonValue) {
            return Role.valueOf(jsonValue.toUpperCase());
        }

        /**
         * @return string representation of the role.
         */
        String toJSONString() {
            return this.jsonValue;
        }
    }
}
