package com.box.sdk;

import java.net.URL;

import com.box.sdk.http.HttpMethod;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.eclipsesource.json.ParseException;

/**
 * Represents a BoxStoragePolicyAssignment.
 */
@BoxResourceType("storage_policy_assignment")
public class BoxStoragePolicyAssignment extends BoxResource {

    /**
     * Storage Policies Assignment URL Template.
     */
    public static final URLTemplate STORAGE_POLICY_ASSIGNMENT_URL_TEMPLATE = new
            URLTemplate("storage_policy_assignments");

    /**
     * Storage Policy Assignment URL Template.
     */
    public static final URLTemplate STORAGE_POLICY_ASSIGNMENT_WITH_ID_URL_TEMPLATE = new
            URLTemplate("storage_policy_assignments/%s");

    /**
     * Constructs a BoxStoragePolicyAssignment for a BoxStoragePolicy with a givenID.
     * @param api the API connection to be used by the file.
     * @param id  the ID of the file.
     */
    public BoxStoragePolicyAssignment(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Create a BoxStoragePolicyAssignment for a BoxStoragePolicy.
     * @param api               the API connection to be used by the resource.
     * @param policyID          the policy ID of the BoxStoragePolicy.
     * @param userID            the user ID of the to assign the BoxStoragePolicy to.
     * @return                  the information about the BoxStoragePolicyAssignment created.
     */
    public static BoxStoragePolicyAssignment.Info create(BoxAPIConnection api, String policyID, String userID) {
        URL url = STORAGE_POLICY_ASSIGNMENT_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, HttpMethod.POST);
        JsonObject requestJSON = new JsonObject()
            .add("storage_policy", new JsonObject()
                .add("type", "storage_policy")
                .add("id", policyID))
            .add("assigned_to", new JsonObject()
                .add("type", "user")
                .add("id", userID));

        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxStoragePolicyAssignment storagePolicyAssignment = new BoxStoragePolicyAssignment(api,
                responseJSON.get("id").asString());

        return storagePolicyAssignment.new Info(responseJSON);
    }

    /**
     * Updates the information about the BoxStoragePolicyAssignment with any info fields that have been
     * modified locally.
     * @param info the updated info.
     */
    public void updateInfo(BoxStoragePolicyAssignment.Info info) {
        URL url = STORAGE_POLICY_ASSIGNMENT_WITH_ID_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(info.getPendingChanges());

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        info.update(responseJSON);
    }

    /**
     * Returns a BoxStoragePolicyAssignment information.
     * @param api                the API connection to be used by the resource.
     * @param resolvedForType    the assigned entity type for the storage policy.
     * @param resolvedForID      the assigned entity id for the storage policy.
     * @return information about this {@link BoxStoragePolicyAssignment}.
     */
    public static BoxStoragePolicyAssignment.Info getAssignmentForTarget(final BoxAPIConnection api,
                                                                         String resolvedForType, String resolvedForID) {
        QueryStringBuilder builder = new QueryStringBuilder();
        builder.appendParam("resolved_for_type", resolvedForType)
               .appendParam("resolved_for_id", resolvedForID);
        URL url = STORAGE_POLICY_ASSIGNMENT_URL_TEMPLATE.buildWithQuery(api.getBaseURL(), builder.toString());
        BoxAPIRequest request = new BoxAPIRequest(api, url, HttpMethod.GET);
        BoxJSONResponse response = (BoxJSONResponse) request.send();

        BoxStoragePolicyAssignment storagePolicyAssignment = new BoxStoragePolicyAssignment(api,
                response.getJsonObject().get("entries").asArray().get(0).asObject().get("id").asString());
        BoxStoragePolicyAssignment.Info info = storagePolicyAssignment.new
                Info(response.getJsonObject().get("entries").asArray().get(0).asObject());

        return info;
    }

    /**
     * @return information about this {@link BoxStoragePolicyAssignment}.
     */
    public BoxStoragePolicyAssignment.Info getInfo() {
        URL url = STORAGE_POLICY_ASSIGNMENT_WITH_ID_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, HttpMethod.GET);
        BoxJSONResponse response = (BoxJSONResponse) request.send();

        return new Info(JsonObject.readFrom(response.getJSON()));
    }

    /**
     * Deletes this BoxStoragePolicyAssignment.
     */
    public void delete() {
        URL url = STORAGE_POLICY_ASSIGNMENT_WITH_ID_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, HttpMethod.DELETE);

        request.send();
    }

    /**
     * Checks if there is already a Storage Policy Assignment and creates one if one does not exist.
     * @param api                    the API connection to be used by the resource.
     * @param storagePolicyID        the ID of the Storage Policy you want to assign to user.
     * @param userID                 the ID of the user you want to assign the Storage Policy to.
     * @return information about this {@link BoxStoragePolicyAssignment}.
     */
    public static BoxStoragePolicyAssignment.Info assign(BoxAPIConnection api, String storagePolicyID, String userID) {
        BoxStoragePolicyAssignment.Info assignmentInfo = null;
        assignmentInfo = getAssignmentForTarget(api, "user", userID);

        if (assignmentInfo.getStoragePolicyID().equals(storagePolicyID)) {
            return assignmentInfo;
        }

        if (assignmentInfo.getAssignedToType().equals("enterprise")) {
            return create(api, storagePolicyID, userID);
        }

        assignmentInfo.setStoragePolicyID(storagePolicyID);
        BoxStoragePolicyAssignment assignment = new BoxStoragePolicyAssignment(api, assignmentInfo.getID());
        assignment.updateInfo(assignmentInfo);
        return assignmentInfo;
    }

    /**
     * Contains information about a BoxStoragePolicyAssignment.
     */
    public class Info extends BoxResource.Info {

        /**
         * @see #getStoragePolicyID()
         */
        private String storagePolicyID;

        /**
         * @see #getStoragePolicyType()
         */
        private String storagePolicyType;

        /**
         * @see #getAssignedToID()
         */
        private String assignedToID;

        /**
         * @see #getAssignedToType()
         */
        private String assignedToType;

        /**
         * Constructs an empty Info object.
         */
        public Info() {
            super();
        }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         * @param json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
        }

        /**
         * Constructs an Info object using an already parsed JSON object.
         * @param jsonObject the parsed JSON object.
         */
        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        @Override
        public BoxResource getResource() {
            return BoxStoragePolicyAssignment.this;
        }

        /**
         * @return the entity type that this is assigned to.
         */
        public String getAssignedToType() {
            return this.assignedToType;
        }

        /**
         * @return the entity id that this is assigned to.
         */
        public String getAssignedToID() {
            return this.assignedToID;
        }

        /**
         * @return storage policy id that is assigned to.
         */
        public String getStoragePolicyID() {
            return this.storagePolicyID;
        }

        /**
         * @return storage policy type that is assigned to.
         */
        public String getStoragePolicyType() {
            return this.storagePolicyType;
        }

        /**
         * Sets the storage policy of the storage policy assignment.
         * @param storagePolicyID the Id of the storage policy you wish to assign.
         */
        public void setStoragePolicyID(String storagePolicyID) {
            this.storagePolicyID = storagePolicyID;
            JsonObject storagePolicyObject = new JsonObject();
            storagePolicyObject.add("type", "storage_policy");
            storagePolicyObject.add("id", storagePolicyID);

            this.addPendingChange("storage_policy", storagePolicyObject);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);
            String memberName = member.getName();
            JsonValue value = member.getValue();
            try {
                if (memberName.equals("assigned_to")) {
                    JsonObject assignmentJSON = value.asObject();
                    this.assignedToType = assignmentJSON.get("type").asString();
                    this.assignedToID = assignmentJSON.get("id").asString();
                } else if (memberName.equals("storage_policy")) {
                    JsonObject storagePolicyJSON = value.asObject();
                    this.storagePolicyID = storagePolicyJSON.get("id").asString();
                    this.storagePolicyType = storagePolicyJSON.get("type").asString();
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }
}
