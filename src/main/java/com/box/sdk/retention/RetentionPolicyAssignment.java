package com.box.sdk.retention;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxAPIException;
import com.box.sdk.BoxAPIRequest;
import com.box.sdk.BoxJSONRequest;
import com.box.sdk.BoxJSONResponse;
import com.eclipsesource.json.JsonObject;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;


/**
 * Retention Policy Assignment.
 */
public final class RetentionPolicyAssignment {

    private static final String RETENTION_POLICY_ASSIGNMENT_URL_PATH = "retention_policy_assignments";

    private RetentionPolicyAssignment() {
    }

    /**
     * Factory to create a Retention Policy Assignment.
     *
     * @param api      Reference to API connection
     * @param policyID the Retention Policy ID
     * @param target   entity type on which to perform assignment (folder, fileversion)
     * @param folder   the folder where data is held
     * @return a new Retention Policy Assignment
     * @throws MalformedURLException when something terrible happens
     */
    public static RetentionPolicyAssignment.Info createRetentionPolicyAssignment(BoxAPIConnection api, String policyID,
                                                                                 RetentionPolicyTarget target, String
                                                                                         folder) throws
            MalformedURLException {

        BoxJSONRequest request =
                new BoxJSONRequest(api, new URL(api.getBaseURL() + RETENTION_POLICY_ASSIGNMENT_URL_PATH), "POST");

        JsonObject jsonRes = (new JsonObject())
                .add("policy_id", policyID)
                .add("assign_to", (new JsonObject()).add("type", target.toString()).add("id", folder));

        request.setBody(jsonRes.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();

        try {
            return new Gson().fromJson(response.getJSON(), RetentionPolicyAssignment.Info.class);
        } catch (Exception e) {
            throw new BoxAPIException(e.getMessage());
        }
    }

    /**
     * Get an existing retention policy assignment.
     *
     * @param api Reference to API connection where command will be sent and executed
     * @param id  the retention policy assignment ID
     * @return an existing Retention Policy Assignment
     * @throws MalformedURLException when something terrible happens
     */
    public static Info getRetentionPolicy(BoxAPIConnection api, String id) throws MalformedURLException {
        BoxAPIRequest request = new BoxAPIRequest(api, new URL(api.getBaseURL()
                + RETENTION_POLICY_ASSIGNMENT_URL_PATH + "/" + id), "GET");

        BoxJSONResponse response = (BoxJSONResponse) request.send();

        try {
            return new Gson().fromJson(response.getJSON(), RetentionPolicyAssignment.Info.class);
        } catch (Exception e) {
            throw new BoxAPIException(e.getMessage());
        }

    }

    /**
     * Retention Policy Assignment DTO.
     */
    class Info {

        @SerializedName("type")
        private String type;

        @SerializedName("id")
        private String id;

        @SerializedName("retention_policy")
        private Map<String, String> retentionPolicy;

        @SerializedName("assigneed_to")
        private Map<String, String> assignedTo;

        @SerializedName("assigned_by")
        private Map<String, String> assignedBy;

        @SerializedName("assigned_at")
        private String assignedAt;

        /**
         * Consructor.
         *
         * @param type             the type of this entity
         * @param id               the Retention Policy ID
         * @param timeOfAssignment
         */
        public Info(String type, String id, String assignedAt) {
            this.type = type;
            this.id = id;
            this.retentionPolicy = new HashMap<String, String>();
            this.assignedBy = new HashMap<String, String>();
            this.assignedTo = new HashMap<String, String>();
            this.assignedAt = assignedAt;
        }

        /**
         * Get retention policy type.
         *
         * @return The type of retention policy assignment
         */
        public String getType() {
            return this.type;
        }

        /**
         * Set the retention policy type.
         *
         * @param type type of RetentionPolicyType
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * Get retention policy assignment id.
         *
         * @return ID as string
         */
        public String getId() {
            return this.id;
        }

        /**
         * Set the retention policy assignment id.
         *
         * @param id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * Get the retention policy.
         *
         * @return
         */
        public Map<String, String> getRetentionPolicy() {
            return this.retentionPolicy;
        }

        /**
         * Set the retention policy.
         *
         * @param retentionPolicy
         */
        public void setRetentionPolicy(Map<String, String> retentionPolicy) {
            this.retentionPolicy = retentionPolicy;
        }

        /**
         * Get entity this policy assignment.
         *
         * @return
         */
        public Map<String, String> getAssignedTo() {
            return this.assignedTo;
        }

        /**
         * Set Assigned to.
         *
         * @param assignedTo
         */
        public void setAssignedTo(Map<String, String> assignedTo) {
            this.assignedTo = assignedTo;
        }

        /**
         * Get Assigned by.
         *
         * @return
         */
        public Map<String, String> getAssignedBy() {
            return this.assignedBy;
        }

        /**
         * Set Assigned by.
         *
         * @param assignedBy
         */
        public void setAssignedBy(Map<String, String> assignedBy) {
            this.assignedBy = assignedBy;
        }

        /**
         * Get time the policy was assigned to entity.
         *
         * @return
         */
        public String getAssignedAt() {
            return this.assignedAt;
        }

        /**
         * Set time of assignment.
         *
         * @param assignedAt
         */
        public void setAssignedAt(String assignedAt) {
            this.assignedAt = assignedAt;
        }

        @Override
        public boolean equals(Object o) {
            return EqualsBuilder.reflectionEquals(this, o);
        }

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this);
        }
    }

}
