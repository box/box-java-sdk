package com.box.sdk;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.box.sdk.BoxGroupMembership.Role;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

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
    private static final URLTemplate MEMBERSHIPS_URL_TEMPLATE = new URLTemplate("groups/%s/memberships");
    private static final URLTemplate ADD_MEMBERSHIP_URL_TEMPLATE = new URLTemplate("group_memberships");

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
     * Gets information about this group.
     * @return info about this group.
     */
    public Info getInfo() {
        URL url = GROUP_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        return new Info(responseJSON);
    }

    /**
     * Gets information about all of the group memberships for this group.
     * @return a collection of information about the group memberships for this group.
     */
    public Collection<BoxGroupMembership.Info> getMemberships() {
        BoxAPIConnection api = this.getAPI();
        URL url = MEMBERSHIPS_URL_TEMPLATE.build(api.getBaseURL(), this.getID());

        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        int entriesCount = responseJSON.get("total_count").asInt();
        Collection<BoxGroupMembership.Info> memberships = new ArrayList<BoxGroupMembership.Info>(entriesCount);
        JsonArray entries = responseJSON.get("entries").asArray();
        for (JsonValue entry : entries) {
            JsonObject entryObject = entry.asObject();
            BoxGroupMembership membership = new BoxGroupMembership(api, entryObject.get("id").asString());
            BoxGroupMembership.Info info = membership.new Info(entryObject);
            memberships.add(info);
        }

        return memberships;
    }

    /**
     * Adds a member to this group with the default role.
     * @param  user the member to be added to this group.
     * @return      info about the new group membership.
     */
    public BoxGroupMembership.Info addMembership(BoxUser user) {
        return this.addMembership(user, null);
    }

    /**
     * Adds a member to this group with the specified role.
     * @param  user the member to be added to this group.
     * @param  role the role of the user in this group. Can be null to assign the default role.
     * @return      info about the new group membership.
     */
    public BoxGroupMembership.Info addMembership(BoxUser user, Role role) {
        BoxAPIConnection api = this.getAPI();

        JsonObject requestJSON = new JsonObject();
        requestJSON.add("user", new JsonObject().add("id", user.getID()));
        requestJSON.add("group", new JsonObject().add("id", this.getID()));
        if (role != null) {
            requestJSON.add("role", role.toJSONString());
        }

        URL url = ADD_MEMBERSHIP_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxGroupMembership membership = new BoxGroupMembership(api, responseJSON.get("id").asString());
        return membership.new Info(responseJSON);
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
