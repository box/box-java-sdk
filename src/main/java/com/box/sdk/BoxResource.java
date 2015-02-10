package com.box.sdk;

import com.eclipsesource.json.JsonObject;

/**
 * The abstract base class for all resource types (files, folders, comments, collaborations, etc.) used by the API.
 *
 * <p>Every API resource has an ID and a {@link BoxAPIConnection} that it uses to communicate with the API. Some
 * resources also have an associated {@link Info} class that contains information about the resource.</p>
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

    static BoxResource.Info parseInfo(BoxAPIConnection api, JsonObject jsonObject) {
        String type = jsonObject.get("type").asString();
        String id = jsonObject.get("id").asString();

        if (type.equals("folder")) {
            BoxFolder folder = new BoxFolder(api, id);
            return folder.new Info(jsonObject);
        } else if (type.equals("file")) {
            BoxFile file = new BoxFile(api, id);
            return file.new Info(jsonObject);
        } else if (type.equals("comment")) {
            BoxComment comment = new BoxComment(api, id);
            return comment.new Info(jsonObject);
        } else if (type.equals("collaboration")) {
            BoxCollaboration collaboration = new BoxCollaboration(api, id);
            return collaboration.new Info(jsonObject);
        } else if (type.equals("user")) {
            BoxUser user = new BoxUser(api, id);
            return user.new Info(jsonObject);
        } else if (type.equals("group")) {
            BoxGroup group = new BoxGroup(api, id);
            return group.new Info(jsonObject);
        } else {
            return null;
        }
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

        if (this.getClass().equals(other.getClass())) {
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
     * Contains information about a BoxResource.
     */
    public abstract class Info extends BoxJSONObject {
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
         * Gets the ID of the resource associated with this Info.
         * @return the ID of the associated resource.
         */
        public String getID() {
            return BoxResource.this.getID();
        }

        /**
         * Gets the resource associated with this Info.
         * @return the associated resource.
         */
        public abstract BoxResource getResource();
    }
}
