package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a retention policy.
 * A retention policy blocks permanent deletion of content for a specified amount of time.
 * Admins can create retention policies and then later assign them to specific folders or their entire enterprise.
 *
 * @see <a href="https://docs.box.com/reference#retention-policy-object">Box retention policy</a>
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("retention_policy")
public class BoxRetentionPolicy extends BoxResource {

    /**
     *  Will cause the content retained by the policy to be permanently deleted.
     */
    public static final String ACTION_PERMANENTLY_DELETE = "permanently_delete";

    /**
     * Will lift the retention policy from the content, allowing it to be deleted by users.
     */
    public static final String ACTION_REMOVE_RETENTION = "remove_retention";

    /**
     * Status corresponding to active retention policy.
     */
    public static final String STATUS_ACTIVE = "active";

    /**
     * Status corresponding to retired retention policy.
     */
    public static final String STATUS_RETIRED = "retired";

    /**
     * Type for finite retention policies. Finite retention policies has the duration.
     */
    private static final String TYPE_FINITE = "finite";

    /**
     * Type for indefinite retention policies. Indefinite retention policies can have only "remove_retention"
     * assigned action.
     */
    private static final String TYPE_INDEFINITE = "indefinite";

    /**
     * The default limit of entries per response.
     */
    private static final int DEFAULT_LIMIT = 100;

    /**
     * The URL template used for operation with retention policies.
     */
    private static final URLTemplate RETENTION_POLICIES_URL_TEMPLATE = new URLTemplate("retention_policies");

    /**
     * The URL template used for operation with retention policy with given ID.
     */
    private static final URLTemplate POLICY_URL_TEMPLATE = new URLTemplate("retention_policies/%s");

    /**
     * The URL template used for operation with retention policy assignments.
     */
    private static final URLTemplate ASSIGNMENTS_URL_TEMPLATE = new URLTemplate("retention_policies/%s/assignments");

    /**
     * Constructs a retention policy for a resource with a given ID.
     *
     * @param api the API connection to be used by the resource.
     * @param id  the ID of the resource.
     */
    public BoxRetentionPolicy(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Used to create a new indefinite retention policy.
     * @param api the API connection to be used by the created user.
     * @param name the name of the retention policy.
     * @return the created retention policy's info.
     */
    public static BoxRetentionPolicy.Info createIndefinitePolicy(BoxAPIConnection api, String name) {
        return createRetentionPolicy(api, name, TYPE_INDEFINITE, 0, ACTION_REMOVE_RETENTION);
    }

    /**
     * Used to create a new finite retention policy.
     * @param api the API connection to be used by the created user.
     * @param name the name of the retention policy.
     * @param length the duration in days that the retention policy will be active for after being assigned to content.
     * @param action the disposition action can be "permanently_delete" or "remove_retention".
     * @return the created retention policy's info.
     */
    public static BoxRetentionPolicy.Info createFinitePolicy(BoxAPIConnection api, String name, int length,
                                                                      String action) {
        return createRetentionPolicy(api, name, TYPE_FINITE, length, action);
    }

    /**
     * Used to create a new retention policy.
     * @param api the API connection to be used by the created user.
     * @param name the name of the retention policy.
     * @param type the type of the retention policy. Can be "finite" or "indefinite".
     * @param length the duration in days that the retention policy will be active for after being assigned to content.
     * @param action the disposition action can be "permanently_delete" or "remove_retention".
     * @return the created retention policy's info.
     */
    private static BoxRetentionPolicy.Info createRetentionPolicy(BoxAPIConnection api, String name, String type,
                                                                int length, String action) {
        URL url = RETENTION_POLICIES_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");
        JsonObject requestJSON = new JsonObject()
                .add("policy_name", name)
                .add("policy_type", type)
                .add("disposition_action", action);
        if (!type.equals(TYPE_INDEFINITE)) {
            requestJSON.add("retention_length", length);
        }
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        BoxRetentionPolicy createdPolicy = new BoxRetentionPolicy(api, responseJSON.get("id").asString());
        return createdPolicy.new Info(responseJSON);
    }

    /**
     * Returns iterable with all folder assignments of this retention policy.
     * @param fields the fields to retrieve.
     * @return an iterable containing all folder assignments.
     */
    public Iterable<BoxRetentionPolicyAssignment.Info> getFolderAssignments(String ... fields) {
        return this.getFolderAssignments(DEFAULT_LIMIT, fields);
    }

    /**
     * Returns iterable with all folder assignments of this retention policy.
     * @param limit the limit of entries per response. The default value is 100.
     * @param fields the fields to retrieve.
     * @return an iterable containing all folder assignments.
     */
    public Iterable<BoxRetentionPolicyAssignment.Info> getFolderAssignments(int limit, String ... fields) {
        return this.getAssignments(BoxRetentionPolicyAssignment.TYPE_FOLDER, limit, fields);
    }

    /**
     * Returns iterable with all enterprise assignments of this retention policy.
     * @param fields the fields to retrieve.
     * @return an iterable containing all enterprise assignments.
     */
    public Iterable<BoxRetentionPolicyAssignment.Info> getEnterpriseAssignments(String ... fields) {
        return this.getEnterpriseAssignments(DEFAULT_LIMIT, fields);
    }

    /**
     * Returns iterable with all enterprise assignments of this retention policy.
     * @param limit the limit of entries per response. The default value is 100.
     * @param fields the fields to retrieve.
     * @return an iterable containing all enterprise assignments.
     */
    public Iterable<BoxRetentionPolicyAssignment.Info> getEnterpriseAssignments(int limit, String ... fields) {
        return this.getAssignments(BoxRetentionPolicyAssignment.TYPE_ENTERPRISE, limit, fields);
    }

    /**
     * Returns iterable with all assignments of this retention policy.
     * @param fields the fields to retrieve.
     * @return an iterable containing all assignments.
     */
    public Iterable<BoxRetentionPolicyAssignment.Info> getAllAssignments(String ... fields) {
        return this.getAllAssignments(DEFAULT_LIMIT, fields);
    }

    /**
     * Returns iterable with all assignments of this retention policy.
     * @param limit the limit of entries per response. The default value is 100.
     * @param fields the fields to retrieve.
     * @return an iterable containing all assignments.
     */
    public Iterable<BoxRetentionPolicyAssignment.Info> getAllAssignments(int limit, String ... fields) {
        return this.getAssignments(null, limit, fields);
    }

    /**
     * Returns iterable with all assignments of given type of this retention policy.
     * @param type the type of the retention policy assignment to retrieve. Can either be "folder" or "enterprise".
     * @param limit the limit of entries per response. The default value is 100.
     * @param fields the fields to retrieve.
     * @return an iterable containing all assignments of given type.
     */
    private Iterable<BoxRetentionPolicyAssignment.Info> getAssignments(String type, int limit, String ... fields) {
        QueryStringBuilder queryString = new QueryStringBuilder();
        if (type != null) {
            queryString.appendParam("type", type);
        }
        if (fields.length > 0) {
            queryString.appendParam("fields", fields);
        }
        URL url = ASSIGNMENTS_URL_TEMPLATE.buildWithQuery(getAPI().getBaseURL(), queryString.toString(), getID());
        return new BoxResourceIterable<BoxRetentionPolicyAssignment.Info>(getAPI(), url, limit) {

            @Override
            protected BoxRetentionPolicyAssignment.Info factory(JsonObject jsonObject) {
                BoxRetentionPolicyAssignment assignment
                    = new BoxRetentionPolicyAssignment(getAPI(), jsonObject.get("id").asString());
                return assignment.new Info(jsonObject);
            }

        };
    }

    /**
     * Assigns this retention policy to folder.
     * @param folder the folder to assign policy to.
     * @return info about created assignment.
     */
    public BoxRetentionPolicyAssignment.Info assignTo(BoxFolder folder) {
        return BoxRetentionPolicyAssignment.createAssignmentToFolder(this.getAPI(), this.getID(), folder.getID());
    }

    /**
     * Assigns this retention policy to the current enterprise.
     * @return info about created assignment.
     */
    public BoxRetentionPolicyAssignment.Info assignToEnterprise() {
        return BoxRetentionPolicyAssignment.createAssignmentToEnterprise(this.getAPI(), this.getID());
    }

    /**
     * Updates the information about this retention policy with any info fields that have been modified locally.
     * @param info the updated info.
     */
    public void updateInfo(BoxRetentionPolicy.Info info) {
        URL url = POLICY_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(info.getPendingChanges());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        info.update(responseJSON);
    }

    /**
     * Returns information about this retention policy.
     * @param fields the fields to retrieve.
     * @return information about this retention policy.
     */
    public BoxRetentionPolicy.Info getInfo(String ... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        URL url = POLICY_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), builder.toString(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        return new Info(responseJSON);
    }


    /**
     * Returns all the retention policies.
     * @param api the API connection to be used by the resource.
     * @param fields the fields to retrieve.
     * @return an iterable with all the retention policies.
     */
    public static Iterable<BoxRetentionPolicy.Info> getAll(final BoxAPIConnection api, String ... fields) {
        return getAll(null, null, null, DEFAULT_LIMIT, api, fields);
    }

    /**
     * Returns all the retention policies with specified filters.
     * @param name a name to filter the retention policies by. A trailing partial match search is performed.
     *             Set to null if no name filtering is required.
     * @param type a policy type to filter the retention policies by. Set to null if no type filtering is required.
     * @param userID a user id to filter the retention policies by. Set to null if no type filtering is required.
     * @param limit the limit of items per single response. The default value is 100.
     * @param api the API connection to be used by the resource.
     * @param fields the fields to retrieve.
     * @return an iterable with all the retention policies met search conditions.
     */
    public static Iterable<BoxRetentionPolicy.Info> getAll(
            String name, String type, String userID, int limit, final BoxAPIConnection api, String ... fields) {
        QueryStringBuilder queryString = new QueryStringBuilder();
        if (name != null) {
            queryString.appendParam("policy_name", name);
        }
        if (type != null) {
            queryString.appendParam("policy_type", type);
        }
        if (userID != null) {
            queryString.appendParam("created_by_user_id", userID);
        }
        if (fields.length > 0) {
            queryString.appendParam("fields", fields);
        }
        URL url = RETENTION_POLICIES_URL_TEMPLATE.buildWithQuery(api.getBaseURL(), queryString.toString());
        return new BoxResourceIterable<BoxRetentionPolicy.Info>(api, url, limit) {

            @Override
            protected BoxRetentionPolicy.Info factory(JsonObject jsonObject) {
                BoxRetentionPolicy policy = new BoxRetentionPolicy(api, jsonObject.get("id").asString());
                return policy.new Info(jsonObject);
            }

        };
    }

    /**
     * Contains information about the retention policy.
     */
    public class Info extends BoxResource.Info {

        /**
         * @see #getPolicyName()
         */
        private String policyName;

        /**
         * @see #getPolicyType()
         */
        private String policyType;

        /**
         * @see #getRetentionLength()
         */
        private int retentionLength;

        /**
         * @see #getDispositionAction()
         */
        private String dispositionAction;

        /**
         * @see #getStatus()
         */
        private String status;

        /**
         * @see #getCreatedBy()
         */
        private BoxUser.Info createdBy;

        /**
         * @see #getCreatedAt()
         */
        private Date createdAt;

        /**
         * @see #getModifiedAt()
         */
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
        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BoxResource getResource() {
            return BoxRetentionPolicy.this;
        }

        /**
         * Gets the name given to the retention policy.
         * @return name given to the retention policy.
         */
        public String getPolicyName() {
            return this.policyName;
        }

        /**
         * Gets the type of the retention policy.
         * A retention policy type can either be "finite",
         * where a specific amount of time to retain the content is known upfront,
         * or "indefinite", where the amount of time to retain the content is still unknown.
         * @return the type of the retention policy.
         */
        public String getPolicyType() {
            return this.policyType;
        }

        /**
         * Gets the length of the retention policy. This length specifies the duration
         * in days that the retention policy will be active for after being assigned to content.
         * @return the length of the retention policy.
         */
        public int getRetentionLength() {
            return this.retentionLength;
        }

        /**
         * Gets the disposition action of the retention policy.
         * This action can be "permanently_delete", or "remove_retention".
         * @return the disposition action of the retention policy.
         */
        public String getDispositionAction() {
            return this.dispositionAction;
        }

        /**
         * Gets the status of the retention policy.
         * The status can be "active" or "retired".
         * @return the status of the retention policy.
         */
        public String getStatus() {
            return this.status;
        }

        /**
         * Gets info about the user created the retention policy.
         * @return info about the user created the retention policy.
         */
        public BoxUser.Info getCreatedBy() {
            return this.createdBy;
        }

        /**
         * Gets the time that the retention policy was created.
         * @return the time that the retention policy was created.
         */
        public Date getCreatedAt() {
            return this.createdAt;
        }

        /**
         * Gets the time that the retention policy was last modified.
         * @return the time that the retention policy was last modified.
         */
        public Date getModifiedAt() {
            return this.modifiedAt;
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
                if (memberName.equals("policy_name")) {
                    this.policyName = value.asString();
                } else if (memberName.equals("policy_type")) {
                    this.policyType = value.asString();
                } else if (memberName.equals("retention_length")) {
                    this.retentionLength = value.asInt();
                } else if (memberName.equals("disposition_action")) {
                    this.dispositionAction = value.asString();
                } else if (memberName.equals("status")) {
                    this.status = value.asString();
                } else if (memberName.equals("created_by")) {
                    JsonObject userJSON = value.asObject();
                    if (this.createdBy == null) {
                        String userID = userJSON.get("id").asString();
                        BoxUser user = new BoxUser(getAPI(), userID);
                        this.createdBy = user.new Info(userJSON);
                    } else {
                        this.createdBy.update(userJSON);
                    }
                } else if (memberName.equals("created_at")) {
                    this.createdAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("modified_at")) {
                    this.modifiedAt = BoxDateFormat.parse(value.asString());
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }
}
