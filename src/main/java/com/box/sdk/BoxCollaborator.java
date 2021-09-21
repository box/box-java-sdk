package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.util.Date;

/**
 * The abstract base class for types that can be added to collaborations.
 */
public abstract class BoxCollaborator extends BoxResource {

    /**
     * Constructs a BoxCollaborator for a collaborator with a given ID.
     *
     * @param api the API connection to be used by the collaborator.
     * @param id  the ID of the collaborator.
     */
    public BoxCollaborator(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Enumerates the possible types of collaborations.
     */
    public enum CollaboratorType {
        /**
         * A user.
         */
        USER("user"),

        /**
         * A group.
         */
        GROUP("group");

        private final String jsonValue;

        CollaboratorType(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static CollaboratorType fromJSONValue(String jsonValue) {
            return CollaboratorType.valueOf(jsonValue.toUpperCase());
        }

        String toJSONValue() {
            return this.jsonValue;
        }
    }

    /**
     * Enumerates the possible types of groups.
     */
    public enum GroupType {
        /**
         * A users group.
         */
        ALL_USERS_GROUP("all_users_group"),

        /**
         * A managed group.
         */
        MANAGED_GROUP("managed_group");

        private final String jsonValue;

        GroupType(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static GroupType fromJSONValue(String jsonValue) {
            return GroupType.valueOf(jsonValue.toUpperCase());
        }

        String toJSONValue() {
            return this.jsonValue;
        }
    }

    /**
     * Contains information about a BoxCollaborator.
     */
    public abstract class Info extends BoxResource.Info {
        private CollaboratorType type;
        private String name;
        private Date createdAt;
        private Date modifiedAt;
        private String login;
        private GroupType groupType;

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
         * Gets the type of the collaborator.
         *
         * @return the type of the collaborator.
         */
        public CollaboratorType getType() {
            return this.type;
        }

        /**
         * Gets the name of the collaborator.
         *
         * @return the name of the collaborator.
         */
        public String getName() {
            return this.name;
        }

        /**
         * Sets the name of the collaborator.
         *
         * @param name the new name of the collaborator.
         */
        public void setName(String name) {
            this.name = name;
            this.addPendingChange("name", name);
        }

        /**
         * Gets the date that the collaborator was created.
         *
         * @return the date that the collaborator was created.
         */
        public Date getCreatedAt() {
            return this.createdAt;
        }

        /**
         * Gets the date that the collaborator was modified.
         *
         * @return the date that the collaborator was modified.
         */
        public Date getModifiedAt() {
            return this.modifiedAt;
        }

        /**
         * Gets the login for the collaborator if the collaborator is a user.
         *
         * @return the login of the collaboraor.
         */
        public String getLogin() {
            return this.login;
        }

        /**
         * Gets the group type for the collaborator if the collaborator is a group.
         *
         * @return the group type of the collaboraor.
         */
        public GroupType getGroupType() {
            return this.groupType;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);
            JsonValue value = member.getValue();
            String name = member.getName();

            try {

                if (name.equals("type")) {
                    this.type = CollaboratorType.fromJSONValue(value.asString());
                } else if (name.equals("name")) {
                    this.name = value.asString();
                } else if (name.equals("created_at")) {
                    this.createdAt = BoxDateFormat.parse(value.asString());
                } else if (name.equals("modified_at")) {
                    this.modifiedAt = BoxDateFormat.parse(value.asString());
                } else if (name.equals("login")) {
                    this.login = value.asString();
                } else if (name.equals("group_type")) {
                    this.groupType = GroupType.fromJSONValue(value.asString());
                }
            } catch (Exception e) {
                throw new BoxDeserializationException(name, value.toString(), e);
            }
        }
    }
}
