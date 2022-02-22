package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
     *
     * @see #getInfo()
     */
    public static final URLTemplate MEMBERSHIP_URL_TEMPLATE = new URLTemplate("group_memberships/%s");

    /**
     * Constructs a BoxGroupMembership for a group membership with a given ID.
     *
     * @param api the API connection to be used by the group membership.
     * @param id  the ID of the group membership.
     */
    public BoxGroupMembership(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Gets information about this group membership.
     *
     * @return info about this group membership.
     */
    public Info getInfo() {
        BoxAPIConnection api = this.getAPI();
        URL url = MEMBERSHIP_URL_TEMPLATE.build(api.getBaseURL(), this.getID());

        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
        return new Info(jsonObject);
    }

    /**
     * Updates the information about this group membership with any info fields that have been modified locally.
     *
     * @param info the updated info.
     */
    public void updateInfo(Info info) {
        BoxAPIConnection api = this.getAPI();
        URL url = MEMBERSHIP_URL_TEMPLATE.build(api.getBaseURL(), this.getID());

        BoxJSONRequest request = new BoxJSONRequest(api, url, "PUT");
        request.setBody(info.getPendingChanges());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
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
     * Enumerates the possible roles that a user can have within a group.
     *
     * @deprecated use GroupRole instead.
     */
    @Deprecated
    public enum Role {
        /**
         * The user is an administrator in the group.
         */
        ADMIN("admin"),

        /**
         * The user is a submaster in the group.
         */
        SUBMASTER("submaster"),

        /**
         * The user is a regular member in the group.
         */
        MEMBER("member");

        /**
         * String representation of the role.
         */
        private final String jsonValue;

        /**
         * Constructor.
         *
         * @param jsonValue srting representation of the role.
         */
        Role(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        /**
         * Creates the role from given string.
         *
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

    /**
     * Enumerates the possible roles that a user can have within a group.
     */
    public enum GroupRole {
        /**
         * The user is an administrator in the group.
         */
        ADMIN("admin"),

        /**
         * The user is a coadmin in the group.
         */
        COADMIN("submaster"),

        /**
         * The user is a regular member in the group.
         */
        MEMBER("member");

        /**
         * String representation of the groupRole.
         */
        private final String jsonValue;

        /**
         * Constructor.
         *
         * @param jsonValue string representation of the role.
         */
        GroupRole(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        /**
         * Creates the groupRole from given string.
         *
         * @param jsonValue string to be converted to role.
         * @return the role, created from string value.
         */
        static GroupRole fromJSONString(String jsonValue) {
            for (GroupRole role : GroupRole.values()) {
                if (role.jsonValue.equalsIgnoreCase(jsonValue)) {
                    return role;
                }
            }
            throw new IllegalArgumentException("Invalid value for enum GroupRole: " + jsonValue);
        }

        /**
         * @return string representation of the groupRole.
         */
        String toJSONString() {
            return this.jsonValue;
        }
    }

    /**
     * Enumerates the possible permissions that a user can have as a group admin.
     */
    public enum Permission {
        /**
         * The user can create accounts.
         */
        CAN_CREATE_ACCOUNTS("can_create_accounts"),

        /**
         * The user can edit accounts.
         */
        CAN_EDIT_ACCOUNTS("can_edit_accounts"),

        /**
         * The user can instant login as another user.
         */
        CAN_INSTANT_LOGIN("can_instant_login"),

        /**
         * The user can run reports.
         */
        CAN_RUN_REPORTS("can_run_reports");

        private final String jsonValue;

        Permission(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static Permission fromJSONValue(String jsonValue) {
            return Permission.valueOf(jsonValue.toUpperCase());
        }

        String toJSONValue() {
            return this.jsonValue;
        }
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
         * @see #getGroupRole()
         */
        private GroupRole groupRole;

        /**
         * @see #getCreatedAt()
         */
        private Date createdAt;

        /**
         * @see #getModifiedAt()
         */
        private Date modifiedAt;

        /**
         * @see #getConfigurablePermissions()
         */
        private Map<Permission, Boolean> configurablePermissions;

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

        /**
         * Constructs an Info object using an already parsed JSON object.
         *
         * @param jsonObject the parsed JSON object.
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
         *
         * @return the level of access the user has.
         * @deprecated use getGroupRole() instead.
         */
        @Deprecated
        public Role getRole() {
            return this.role;
        }

        /**
         * Sets the level of access the user has.
         *
         * @param role the new level of access to give the user.
         * @deprecated use setGroupRole() instead.
         */
        @Deprecated
        public void setRole(Role role) {
            this.role = role;
            this.addPendingChange("role", role.toJSONString());
        }

        /**
         * Gets the level of access the user has.
         *
         * @return the level of access the user has.
         */
        public GroupRole getGroupRole() {
            return this.groupRole;
        }

        /**
         * Sets the level of access the user has.
         *
         * @param role the new level of access to give the user.
         */
        public void setGroupRole(GroupRole role) {
            this.groupRole = role;
            this.addPendingChange("role", role.toJSONString());
        }

        /**
         * Gets the time the group membership was created.
         *
         * @return the time the group membership was created.
         */
        public Date getCreatedAt() {
            return this.createdAt;
        }

        /**
         * Gets the time the group membership was last modified.
         *
         * @return the time the group membership was last modified.
         */
        public Date getModifiedAt() {
            return this.modifiedAt;
        }

        /**
         * Gets the configurablePermissions that the current user has on the group as group admin.
         *
         * @return the configurablePermissions that the current user has on the group as group admin.
         */
        public Map<Permission, Boolean> getConfigurablePermissions() {
            return this.configurablePermissions;
        }

        /**
         * Sets the configurablePermissions that the current user has on the group as group admin.
         *
         * @param configurablePermissions a Map representing the group admin configurable permissions
         */
        public void setConfigurablePermissions(Map<Permission, Boolean> configurablePermissions) {
            this.configurablePermissions = configurablePermissions;
            this.addPendingChange("configurable_permissions", this.configurablePermissionJson());
        }

        /**
         * append new configurable permissions to the previous existing list.
         *
         * @param permission the group admin permission one wants to enable or disable of the user on the group.
         * @param value      the true/false value of the attribute to set.
         */
        public void appendConfigurablePermissions(Permission permission, Boolean value) {
            this.configurablePermissions.put(permission, value);
            this.addPendingChange("configurable_permissions", this.configurablePermissionJson());
        }

        private JsonObject configurablePermissionJson() {
            JsonObject configurablePermissionJson = new JsonObject();
            for (Permission attrKey : this.configurablePermissions.keySet()) {
                configurablePermissionJson.set(attrKey.toJSONValue(), this.configurablePermissions.get(attrKey));
            }
            return configurablePermissionJson;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BoxGroupMembership getResource() {
            return BoxGroupMembership.this;
        }

        private Map<Permission, Boolean> parseConfigurablePermissions(JsonObject jsonObject) {
            if (jsonObject == null) {
                return null;
            }
            Map<Permission, Boolean> permissions = new HashMap<>();
            for (JsonObject.Member member : jsonObject) {
                String memberName = member.getName();
                boolean memberValue = member.getValue().asBoolean();
                if (memberName.equals("can_create_accounts")) {
                    permissions.put(Permission.CAN_CREATE_ACCOUNTS, memberValue);
                } else if (memberName.equals("can_edit_accounts")) {
                    permissions.put(Permission.CAN_EDIT_ACCOUNTS, memberValue);
                } else if (memberName.equals("can_instant_login")) {
                    permissions.put(Permission.CAN_INSTANT_LOGIN, memberValue);
                } else if (memberName.equals("can_run_reports")) {
                    permissions.put(Permission.CAN_RUN_REPORTS, memberValue);
                }
            }
            return permissions;
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
                    this.groupRole = GroupRole.fromJSONString(value.asString());

                } else if (memberName.equals("created_at")) {
                    this.createdAt = BoxDateFormat.parse(value.asString());

                } else if (memberName.equals("modified_at")) {
                    this.modifiedAt = BoxDateFormat.parse(value.asString());

                } else if (memberName.equals("configurable_permissions")) {
                    this.configurablePermissions = this.parseConfigurablePermissions(value.asObject());

                }
            } catch (Exception e) {
                throw new BoxDeserializationException(memberName, value.toString(), e);
            }
        }
    }
}
