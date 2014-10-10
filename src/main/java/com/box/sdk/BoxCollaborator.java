package com.box.sdk;

import java.text.ParseException;
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
     * Contains additional information about a BoxCollaborator.
     *
     * @param <T> the type of the collaborator associated with this info.
     */
    public abstract class Info<T extends BoxCollaborator> extends BoxResource.Info<T> {
        private String name;
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
         * @param  json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
        }

        /**
         * Constructs an Info object using an already parsed JSON object.
         * @param  jsonObject the parsed JSON object.
         */
        protected Info(JsonObject jsonObject) {
            super(jsonObject);
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

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            try {
                JsonValue value = member.getValue();
                switch (member.getName()) {
                    case "name":
                        this.name = value.asString();
                        break;
                    case "created_at":
                        this.createdAt = BoxDateParser.parse(value.asString());
                        break;
                    case "modified_at":
                        this.modifiedAt = BoxDateParser.parse(value.asString());
                        break;
                    default:
                        break;
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }
}
