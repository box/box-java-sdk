package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;

/**
 * Represents a set of Box users.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
public class BoxGroup extends BoxCollaborator {
    private static final URLTemplate GROUPS_URL_TEMPLATE = new URLTemplate("groups");
    private static final URLTemplate GROUP_URL_TEMPLATE = new URLTemplate("groups/%s");

    /**
     * Constructs a BoxGroup for a group with a given ID.
     * @param  api the API connection to be used by the group.
     * @param  id  the ID of the group.
     */
    public BoxGroup(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Creates a new group with a specified name.
     * @param  api  the API connection to be used by the group.
     * @param  name the name of the new group.
     * @return      info about the created group.
     */
    public static BoxGroup.Info createGroup(BoxAPIConnection api, String name) {
        JsonObject requestJSON = new JsonObject();
        requestJSON.add("name", name);

        URL url = GROUPS_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxGroup group = new BoxGroup(api, responseJSON.get("id").asString());
        return group.new Info(responseJSON);
    }

    /**
     * Gets an iterable of all the groups that the current user is a member of.
     * @param  api the API connection to be used when retrieving the groups.
     * @return     an iterable containing info about all the groups.
     */
    public static Iterable<BoxGroup.Info> getAllGroups(final BoxAPIConnection api) {
        return new Iterable<BoxGroup.Info>() {
            public Iterator<BoxGroup.Info> iterator() {
                URL url = GROUPS_URL_TEMPLATE.build(api.getBaseURL());
                return new BoxGroupIterator(api, url);
            }
        };
    }

    /**
     * Deletes this group.
     */
    public void delete() {
        URL url = GROUP_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Contains information about a BoxGroup.
     */
    public class Info extends BoxCollaborator.Info {
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

        @Override
        public BoxGroup getResource() {
            return BoxGroup.this;
        }
    }
}
