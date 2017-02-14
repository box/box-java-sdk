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
@BoxResourceType("group")
public class BoxGroup extends BoxCollaborator {

    /**
     * @see #getAllGroups(BoxAPIConnection, String...)
     */
    private static final URLTemplate GROUPS_URL_TEMPLATE = new URLTemplate("groups");

    /**
     * @see #getInfo()
     */
    private static final URLTemplate GROUP_URL_TEMPLATE = new URLTemplate("groups/%s");

    /**
     * @see #getMemberships()
     */
    private static final URLTemplate MEMBERSHIPS_URL_TEMPLATE = new URLTemplate("groups/%s/memberships");

    /**
     * @see #addMembership(BoxUser)
     */
    private static final URLTemplate ADD_MEMBERSHIP_URL_TEMPLATE = new URLTemplate("group_memberships");

    /**
     * @see #getCollaborations()
     */
    private static final URLTemplate COLLABORATIONS_URL_TEMPLATE = new URLTemplate("groups/%s/collaborations");

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
        return createGroup(api, name, null, null, null, null, null);
    }

    /**
     * Creates a new group with a specified name.
     * @param  api  the API connection to be used by the group.
     * @param  name the name of the new group.
     * @param provenance the provenance of the new group
     * @param externalSyncIdentifier the external_sync_identifier of the new group
     * @param description the description of the new group
     * @param invitabilityLevel the invitibility_level of the new group
     * @param memberViewabilityLevel the member_viewability_level of the new group
     * @return      info about the created group.
     */
    public static BoxGroup.Info createGroup(BoxAPIConnection api, String name, String provenance,
                                            String externalSyncIdentifier, String description,
                                            String invitabilityLevel, String memberViewabilityLevel) {
        JsonObject requestJSON = new JsonObject();
        requestJSON.add("name", name);

        if (provenance != null) {
            requestJSON.add("provenance", provenance);
        }
        if (externalSyncIdentifier != null) {
            requestJSON.add("external_sync_identifier", externalSyncIdentifier);
        }
        if (description != null) {
            requestJSON.add("description", description);
        }
        if (invitabilityLevel != null) {
            requestJSON.add("invitability_level", invitabilityLevel);
        }
        if (memberViewabilityLevel != null) {
            requestJSON.add("member_viewability_level", memberViewabilityLevel);
        }

        URL url = GROUPS_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxGroup group = new BoxGroup(api, responseJSON.get("id").asString());
        return group.new Info(responseJSON);
    }

    /**
     * Gets an iterable of all the groups in the enterprise.
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
     * Gets an iterable of all the groups in the enterprise.
     * @param  api the API connection to be used when retrieving the groups.
     * @param fields the fields to retrieve.
     * @return     an iterable containing info about all the groups.
     */
    public static Iterable<BoxGroup.Info> getAllGroups(final BoxAPIConnection api, String ... fields) {
        final QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        return new Iterable<BoxGroup.Info>() {
            public Iterator<BoxGroup.Info> iterator() {
                URL url = GROUPS_URL_TEMPLATE.buildWithQuery(api.getBaseURL(), builder.toString());
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
     * Gets information about this group.
     * @param fields the fields to retrieve.
     * @return info about this group.
     */
    public Info getInfo(String ... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        URL url = GROUP_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), builder.toString(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        return new Info(responseJSON);
    }

    /**
     * Gets information about all of the group memberships for this group.
     * Does not support paging.
     * @return a collection of information about the group memberships for this group.
     */
    public Collection<BoxGroupMembership.Info> getMemberships() {
        final BoxAPIConnection api = this.getAPI();
        final String groupID = this.getID();

        Iterable<BoxGroupMembership.Info> iter = new Iterable<BoxGroupMembership.Info>() {
            public Iterator<BoxGroupMembership.Info> iterator() {
                URL url = MEMBERSHIPS_URL_TEMPLATE.build(api.getBaseURL(), groupID);
                return new BoxGroupMembershipIterator(api, url);
            }
        };

        // We need to iterate all results because this method must return a Collection. This logic should be removed in
        // the next major version, and instead return the Iterable directly.
        Collection<BoxGroupMembership.Info> memberships = new ArrayList<BoxGroupMembership.Info>();
        for (BoxGroupMembership.Info membership : iter) {
            memberships.add(membership);
        }
        return memberships;
    }

    /**
     * Gets information about all of the group memberships for this group as iterable with paging support.
     * @param fields the fields to retrieve.
     * @return an iterable with information about the group memberships for this group.
     */
    public Iterable<BoxGroupMembership.Info> getAllMemberships(String ... fields) {
        final QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        return new Iterable<BoxGroupMembership.Info>() {
            public Iterator<BoxGroupMembership.Info> iterator() {
                URL url = MEMBERSHIPS_URL_TEMPLATE.buildWithQuery(
                        BoxGroup.this.getAPI().getBaseURL(), builder.toString(), BoxGroup.this.getID());
                return new BoxGroupMembershipIterator(BoxGroup.this.getAPI(), url);
            }
        };
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
     * Gets information about all of the collaborations for this group.
     * @return a collection of information about the collaborations for this group.
     */
    public Collection<BoxCollaboration.Info> getCollaborations() {
        BoxAPIConnection api = this.getAPI();
        URL url = COLLABORATIONS_URL_TEMPLATE.build(api.getBaseURL(), this.getID());

        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        int entriesCount = responseJSON.get("total_count").asInt();
        Collection<BoxCollaboration.Info> collaborations = new ArrayList<BoxCollaboration.Info>(entriesCount);
        JsonArray entries = responseJSON.get("entries").asArray();
        for (JsonValue entry : entries) {
            JsonObject entryObject = entry.asObject();
            BoxCollaboration collaboration = new BoxCollaboration(api, entryObject.get("id").asString());
            BoxCollaboration.Info info = collaboration.new Info(entryObject);
            collaborations.add(info);
        }

        return collaborations;
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
     * Updates the information about this group with any info fields that have been modified locally.
     * @param info the updated info.
     */
    public void updateInfo(BoxGroup.Info info) {
        URL url = GROUP_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(info.getPendingChanges());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        info.update(jsonObject);
    }

    /**
     * Contains information about a BoxGroup.
     */
    public class Info extends BoxCollaborator.Info {

        /**
         * @see #getProvenance()
         */
        private String provenance;

        /**
         * @see #getExternalSyncIdentifier()
         */
        private String externalSyncIdentifier;

        /**
         * @see #getDescription()
         */
        private String description;

        /**
         * @see #getInvitabilityLevel()
         */
        private String invitabilityLevel;

        /**
         * @see #getMemberViewabilityLevel()
         */
        private String memberViewabilityLevel;

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
         * {@inheritDoc}
         */
        @Override
        public BoxGroup getResource() {
            return BoxGroup.this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            if (memberName.equals("description")) {
                this.description = value.asString();
            } else if (memberName.equals("external_sync_identifier")) {
                this.externalSyncIdentifier = value.asString();
            } else if (memberName.equals("invitability_level")) {
                this.invitabilityLevel = value.asString();
            } else if (memberName.equals("member_viewability_level")) {
                this.memberViewabilityLevel = value.asString();
            } else if (memberName.equals("provenance")) {
                this.provenance = value.asString();
            }
        }

        /**
         * Gets the description for the group.
         * @return the description for the group.
         */
        public String getDescription() {
            return this.description;
        }

        /**
         * Sets the description for the group.
         * @param description the description for the group.
         */
        public void setDescription(String description) {
            this.description = description;
            addPendingChange("description", description);
        }

        /**
         * Gets the external_sync_identifier for the group.
         * @return the external_sync_identifier for the group.
         */
        public String getExternalSyncIdentifier() {
            return this.externalSyncIdentifier;
        }

        /**
         * Sets the external_sync_identifier for the group.
         * @param externalSyncIdentifier the external_sync_identifier for the group.
         */
        public void setExternalSyncIdentifier(String externalSyncIdentifier) {
            this.externalSyncIdentifier = externalSyncIdentifier;
            addPendingChange("external_sync_identifier", externalSyncIdentifier);
        }

        /**
         * Gets the invitability_level for the group.
         * @return the invitability_level for the group.
         */
        public String getInvitabilityLevel() {
            return this.invitabilityLevel;
        }

        /**
         * Sets the invitability_level for the group.
         * @param invitabilityLevel the invitability_level for the group.
         */
        public void setInvitabilityLevel(String invitabilityLevel) {
            this.invitabilityLevel = invitabilityLevel;
            addPendingChange("invitability_level", invitabilityLevel);
        }

        /**
         * Gets the member_viewability_level for the group.
         * @return the member_viewability_level for the group.
         */
        public String getMemberViewabilityLevel() {
            return this.memberViewabilityLevel;
        }

        /**
         * Sets the member_viewability_level for the group.
         * @param memberViewabilityLevel the member_viewability_level for the group.
         */
        public void setMemberViewabilityLevel(String memberViewabilityLevel) {
            this.memberViewabilityLevel = memberViewabilityLevel;
            addPendingChange("member_viewability_level", memberViewabilityLevel);
        }

        /**
         * Gets the provenance for the group.
         * @return the provenance for the group.
         */
        public String getProvenance() {
            return this.provenance;
        }

        /**
         * Sets the provenance for the group.
         * @param provenance the provenance for the group.
         */
        public void setProvenance(String provenance) {
            this.provenance = provenance;
            addPendingChange("provenance", provenance);
        }
    }
}
