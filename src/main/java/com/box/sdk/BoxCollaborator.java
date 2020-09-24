package com.box.sdk;

import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * The abstract base class for types that can be added to collaborations.
 */
public abstract class BoxCollaborator extends BoxResource {

    /**
     * Constructs a BoxCollaborator for a collaborator with a given ID.
     * @param  api the API connection to be used by the collaborator.
     * @param  id  the ID of the collaborator.
     */
    public BoxCollaborator(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Contains information about a BoxCollaborator.
     */
    public abstract class Info extends BoxResource.Info {
        private String type;
        private String name;
        private Date createdAt;
        private Date modifiedAt;
        private String login;
        private String groupType;

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
         * Gets the type of the collaborator.
         * @return the type of the collaborator.
         */
        public String getType() {
            return this.type;
        }

        /**
         * Gets the name of the collaborator.
         * @return the name of the collaborator.
         */
        public String getName() {
            return this.name;
        }

        /**
         * Sets the name of the collaborator.
         * @param name the new name of the collaborator.
         */
        public void setName(String name) {
            this.name = name;
            this.addPendingChange("name", name);
        }

        /**
         * Gets the date that the collaborator was created.
         * @return the date that the collaborator was created.
         */
        public Date getCreatedAt() {
            return this.createdAt;
        }

        /**
         * Gets the date that the collaborator was modified.
         * @return the date that the collaborator was modified.
         */
        public Date getModifiedAt() {
            return this.modifiedAt;
        }

        /**
         * Gets the login for the collaborator if the collaborator is a user.
         * @return the login of the collaboraor.
         */
        public String getLogin() {
            return this.login;
        }

        /**
         * Gets the group type for the collaborator if the collaborator is a group.
         * @return the group type of the collaboraor.
         */
        public String getGroupType() {
            return this.groupType;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);
            JsonValue value = member.getValue();
            String name = member.getName();

            try {

                if (name.equals("type")) {
                    this.type = value.asString();
                } else if (name.equals("name")) {
                    this.name = value.asString();
                } else if (name.equals("created_at")) {
                    this.createdAt = BoxDateFormat.parse(value.asString());
                } else if (name.equals("modified_at")) {
                    this.modifiedAt = BoxDateFormat.parse(value.asString());
                } else if (name.equals("login")) {
                    this.login = value.asString();
                } else if (name.equals("group_type")) {
                    this.groupType = value.asString();
                }
            } catch (Exception e) {
                throw new BoxDeserializationException(name, value.toString(), e);
            }
        }
    }
}
