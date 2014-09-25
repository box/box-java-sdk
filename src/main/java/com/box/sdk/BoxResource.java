package com.box.sdk;

import java.util.List;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * The abstract base class for all resource types (files, folders, comments, collaborations, etc.) used by the API.
 *
 * <p>Every API resource has an ID and a {@link BoxAPIConnection} that it uses to communicate with the API. Some
 * resources also have an associated {@link Info} class that can contain additional information about the resource.</p>
 *
 */
public abstract class BoxResource {
    private final BoxAPIConnection api;
    private final String id;

    /**
     * Constructs a BoxResource for a resource with a given ID.
     * @param  api the API connection to be used by the resource.
     * @param  id  the ID of the resource.
     */
    public BoxResource(BoxAPIConnection api, String id) {
        this.api = api;
        this.id = id;
    }

    /**
     * Gets the API connection used by this resource.
     * @return the API connection used by this resource.
     */
    public BoxAPIConnection getAPI() {
        return this.api;
    }

    /**
     * Gets the ID of this resource.
     * @return the ID of this resource.
     */
    public String getID() {
        return this.id;
    }

    /**
     * Indicates whether this BoxResource is equal to another BoxResource. Two BoxResources are equal if they have the
     * same type and ID.
     * @param  other the other BoxResource to compare.
     * @return       true if the type and IDs of the two resources are equal; otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other instanceof BoxResource) {
            BoxResource otherResource = (BoxResource) other;
            return this.getID().equals(otherResource.getID());
        }

        return false;
    }

    /**
     * Returns a hash code value for this BoxResource.
     * @return a hash code value for this BoxResource.
     */
    @Override
    public int hashCode() {
        return this.getID().hashCode();
    }

    /**
     * Contains additional information about a BoxResource.
     *
     * <p>Subclasses should track any changes to a resource's information by calling the {@link #addPendingChange}
     * method. The pending changes will then be serialized to JSON when {@link #getPendingChanges} is called.</p>
     *
     * @param <T> the type of the resource associated with this info.
     */
    public abstract class Info<T extends BoxResource> {
        private JsonObject pendingChanges;

        /**
         * Constructs an empty Info object.
         */
        public Info() {
            this.pendingChanges = new JsonObject();
        }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         * @param  json the JSON string to parse.
         */
        public Info(String json) {
            this(JsonObject.readFrom(json));
        }

        /**
         * Constructs an Info object using an already parsed JSON object.
         * @param  jsonObject the parsed JSON object.
         */
        protected Info(JsonObject jsonObject) {
            this.updateFromJSON(jsonObject);
        }

        /**
         * Gets the ID of the resource associated with this Info.
         * @return the ID of the associated resource.
         */
        public String getID() {
            return BoxResource.this.getID();
        }

        /**
         * Gets a list of fields that have pending changes that haven't been sent to the API yet.
         * @return a list of changed fields with pending changes.
         */
        public List<String> getChangedFields() {
            return this.pendingChanges.names();
        }

        /**
         * Gets a JSON object of any pending changes.
         * @return a JSON object containing the pending changes.
         */
        public String getPendingChanges() {
            return this.pendingChanges.toString();
        }

        /**
         * Gets the resource associated with this Info.
         * @return the associated resource.
         */
        public abstract T getResource();

        /**
         * Adds a pending field change that needs to be sent to the API. It will be included in the JSON string the next
         * time {@link #getPendingChanges} is called.
         * @param key   the name of the field.
         * @param value the new value of the field.
         */
        protected void addPendingChange(String key, JsonValue value) {
            this.pendingChanges.set(key, value);
        }

        /**
         * Clears all pending changes.
         */
        protected void clearPendingChanges() {
            this.pendingChanges = new JsonObject();
        }

        /**
         * Updates this Info object using the information in a JSON object.
         * @param jsonObject the JSON object containing updated information.
         */
        protected void updateFromJSON(JsonObject jsonObject) {
            for (JsonObject.Member member : jsonObject) {
                if (member.getValue().isNull()) {
                    continue;
                }

                this.parseJSONMember(member);
            }

            this.clearPendingChanges();
        }

        /**
         * Invoked with a JSON member whenever this Info object is updated or created from a JSON object.
         *
         * <p>Subclasses should override this method in order to parse any JSON members it knows about. This method is a
         * no-op by default.</p>
         *
         * @param member the JSON member to be parsed.
         */
        protected void parseJSONMember(JsonObject.Member member) { }
    }
}
