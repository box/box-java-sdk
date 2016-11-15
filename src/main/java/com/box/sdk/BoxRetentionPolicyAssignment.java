package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a retention policy assignment.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("retention_policy_assignment")
public class BoxRetentionPolicyAssignment extends BoxResource {

    /**
     * Type for folder policy assignment.
     */
    public static final String TYPE_FOLDER = "folder";

    /**
     * Type for enterprise policy assignment.
     */
    public static final String TYPE_ENTERPRISE = "enterprise";

    /**
     * The URL template used for operation with retention policy assignments.
     */
    private static final URLTemplate ASSIGNMENTS_URL_TEMPLATE = new URLTemplate("retention_policy_assignments");

    /**
     * The URL template used for operation with retention policy assignment with given ID.
     */
    private static final URLTemplate RETENTION_POLICY_ASSIGNMENT_URL_TEMPLATE
        = new URLTemplate("retention_policy_assignments/%s");

    /**
     * Constructs a BoxResource for a resource with a given ID.
     *
     * @param api the API connection to be used by the resource.
     * @param id  the ID of the resource.
     */
    public BoxRetentionPolicyAssignment(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Assigns retention policy with givenID to the enterprise.
     * @param api the API connection to be used by the created assignment.
     * @param policyID id of the assigned retention policy.
     * @return info about created assignment.
     */
    public static BoxRetentionPolicyAssignment.Info createAssignmentToEnterprise(BoxAPIConnection api,
                                                                                 String policyID) {
        return createAssignment(api, policyID, new JsonObject().add("type", TYPE_ENTERPRISE));
    }

    /**
     * Assigns retention policy with givenID to the folder.
     * @param api the API connection to be used by the created assignment.
     * @param policyID id of the assigned retention policy.
     * @param folderID id of the folder to assign policy to.
     * @return info about created assignment.
     */
    public static BoxRetentionPolicyAssignment.Info createAssignmentToFolder(BoxAPIConnection api, String policyID,
                                                                             String folderID) {
        return createAssignment(api, policyID, new JsonObject().add("type", TYPE_FOLDER).add("id", folderID));
    }

    /**
     * Assigns retention policy with givenID to folder or enterprise.
     * @param api the API connection to be used by the created assignment.
     * @param policyID id of the assigned retention policy.
     * @param assignTo object representing folder or enterprise to assign policy to.
     * @return info about created assignment.
     */
    private static BoxRetentionPolicyAssignment.Info createAssignment(BoxAPIConnection api, String policyID,
                                                                      JsonObject assignTo) {
        URL url = ASSIGNMENTS_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");

        JsonObject requestJSON = new JsonObject()
                .add("policy_id", policyID)
                .add("assign_to", assignTo);
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        BoxRetentionPolicyAssignment createdAssignment
            = new BoxRetentionPolicyAssignment(api, responseJSON.get("id").asString());
        return createdAssignment.new Info(responseJSON);
    }

    /**
     * @param fields the fields to retrieve.
     * @return information about this retention policy assignment.
     */
    public BoxRetentionPolicyAssignment.Info getInfo(String ... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        URL url = RETENTION_POLICY_ASSIGNMENT_URL_TEMPLATE.buildWithQuery(
                this.getAPI().getBaseURL(), builder.toString(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        return new Info(responseJSON);
    }

    /**
     * Contains information about the retention policy.
     */
    public class Info extends BoxResource.Info {

        /**
         * @see #getRetentionPolicy()
         */
        private BoxRetentionPolicy.Info retentionPolicy;

        /**
         * @see #getAssignedBy()
         */
        private BoxUser.Info assignedBy;

        /**
         * @see #getAssignedAt()
         */
        private Date assignedAt;

        /**
         * @see #getAssignedToType()
         */
        private String assignedToType;

        /**
         * @see #getAssignedToID()
         */
        private String assignedToID;

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
        public BoxResource getResource() {
            return BoxRetentionPolicyAssignment.this;
        }

        /**
         * @return the retention policy that has been assigned to this content.
         */
        public BoxRetentionPolicy.Info getRetentionPolicy() {
            return this.retentionPolicy;
        }

        /**
         * @return the info about the user that created the retention policy assignment.
         */
        public BoxUser.Info getAssignedBy() {
            return this.assignedBy;
        }

        /**
         * @return the time that the retention policy assignment was created.
         */
        public Date getAssignedAt() {
            return this.assignedAt;
        }

        /**
         * @return type of the content that is under retention. Can either be "enterprise" or "folder".
         */
        public String getAssignedToType() {
            return this.assignedToType;
        }

        /**
         * @return id of the folder that is under retention.
         */
        public String getAssignedToID() {
            return this.assignedToID;
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
                if (memberName.equals("retention_policy")) {
                    JsonObject policyJSON = value.asObject();
                    if (this.retentionPolicy == null) {
                        String policyID = policyJSON.get("id").asString();
                        BoxRetentionPolicy policy = new BoxRetentionPolicy(getAPI(), policyID);
                        this.retentionPolicy = policy.new Info(policyJSON);
                    } else {
                        this.retentionPolicy.update(policyJSON);
                    }
                } else if (memberName.equals("assigned_to")) {
                    JsonObject assignmentJSON = value.asObject();
                    this.assignedToType = assignmentJSON.get("type").asString();
                    if (this.assignedToType.equals(TYPE_FOLDER)) {
                        this.assignedToID = assignmentJSON.get("id").asString();
                    } else {
                        this.assignedToID = null;
                    }
                } else if (memberName.equals("assigned_by")) {
                    JsonObject userJSON = value.asObject();
                    if (this.assignedBy == null) {
                        String userID = userJSON.get("id").asString();
                        BoxUser user = new BoxUser(getAPI(), userID);
                        this.assignedBy = user.new Info(userJSON);
                    } else {
                        this.assignedBy.update(userJSON);
                    }
                } else if (memberName.equals("assigned_at")) {
                    this.assignedAt = BoxDateFormat.parse(value.asString());
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }
}
