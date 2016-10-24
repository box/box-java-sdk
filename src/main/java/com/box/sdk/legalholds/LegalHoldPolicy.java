package com.box.sdk.legalholds;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxAPIException;
import com.box.sdk.BoxAPIResponse;
import com.box.sdk.BoxJSONRequest;
import com.eclipsesource.json.JsonObject;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Legal Hold Policy.
 */
public final class LegalHoldPolicy {

    private static final String LEGAL_HOLD_POLICY_URL_PATH = "legal_hold_policies";

    private LegalHoldPolicy() {
    }

    /**
     * Constructor.
     *
     * @param api        The agent that communicates with API
     * @param policyName The name of the policy
     * @return Legal Hold Policy message
     * @throws MalformedURLException If error in URL
     */
    public static LegalHoldPolicy.Info createLegalHoldPolicy(BoxAPIConnection api, String policyName) throws
            MalformedURLException {

        return createLegalHoldPolicy(api, policyName, "", "", "");
    }

    /**
     * Constructor.
     * @param api        The agent that communicates with API
     * @param policyName The name of the policy
     * @param policyDescription The description of the policy
     * @return Legal Hold Policy message
     * @throws MalformedURLException If error in URL
     */
    public static LegalHoldPolicy.Info createLegalHoldPolicy(BoxAPIConnection api,
                                                             String policyName,
                                                             String policyDescription) throws MalformedURLException
    {
        return createLegalHoldPolicy(api, policyName, policyDescription, "", "");
    }


    /**
     * Create Legal Hold Policy.
     *
     * @param api             agent that makes calls against API
     * @param policyName      name of the policy
     * @param description     description of policy (limit to 500 characters)
     * @param filterStartedAt start date
     * @param filterEndedAt   end date
     * @return Legal Hold Policy
     * @throws MalformedURLException If error in URL
     */
    public static LegalHoldPolicy.Info createLegalHoldPolicy(BoxAPIConnection api,
                                                             String policyName,
                                                             String description,
                                                             String filterStartedAt,
                                                             String filterEndedAt)
            throws MalformedURLException {

        BoxJSONRequest request = new BoxJSONRequest(api,
                new URL(api.getBaseURL() + LEGAL_HOLD_POLICY_URL_PATH),
                "POST"
        );

        JsonObject jsonRes = (new JsonObject()).add("policy_name", policyName);

        if (!description.equals("")) {
            jsonRes.add("description", description);
        }
        if (!filterStartedAt.equals("")) {
            jsonRes.add("filter_started_at", filterStartedAt);
        }
        if (!filterEndedAt.equals("")) {
            jsonRes.add("filter_ended_at", filterEndedAt);
        }

        request.setBody(jsonRes.toString());
        BoxAPIResponse response = request.send();

        try {
            return new Gson().fromJson(IOUtils.toString(response.getBody(), "UTF-8"), LegalHoldPolicy.Info.class);
        } catch (IOException e) {
            throw new BoxAPIException("Unable to map JSON response to LegalHoldPolicy", e);
        }

    }

    /**
     * Obtain Legal Hold Policy.
     *
     * @param api      agent that makes calls against API
     * @param policyId ID of policy
     * @return Legal Hold Policy
     * @throws MalformedURLException If error in URL
     */
    public static LegalHoldPolicy.Info getLegalHoldPolicy(BoxAPIConnection api, String policyId) throws
            MalformedURLException {

        BoxJSONRequest request = new BoxJSONRequest(api,
                new URL(api.getBaseURL() + LEGAL_HOLD_POLICY_URL_PATH + String.format("/%s", policyId)),
                "GET"
        );

        BoxAPIResponse response = request.send();

        try {
            return new Gson().fromJson(IOUtils.toString(response.getBody(), "UTF-8"), LegalHoldPolicy.Info.class);
        } catch (IOException e) {
            throw new BoxAPIException("Unable to map JSON response to LegalHoldPolicy", e);
        }

    }

    /**
     * Delete Legal Hold Policy.
     *
     * @param api      agent that makes calls against API
     * @param policyId ID of policy
     * @return Legal Hold Policy
     * @throws MalformedURLException If error in URL
     */
    public static BoxAPIResponse deleteLegalHoldPolicy(BoxAPIConnection api, String policyId) throws
            MalformedURLException {
        BoxJSONRequest request = new BoxJSONRequest(api,
                new URL(api.getBaseURL() + LEGAL_HOLD_POLICY_URL_PATH + String.format("/%s", policyId)),
                "DELETE"
        );

        BoxAPIResponse response = request.send();
        return response;
    }

    /**
     * Legal Hold message for serialization.
     */
    public static class Info {

        @SerializedName("type")
        @Expose
        private String type;

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("policy_name")
        @Expose
        private String policyName;

        @SerializedName("description")
        @Expose
        private String description;

        @SerializedName("status")
        @Expose
        private String status;

        @SerializedName("assignment_counts")
        @Expose
        private Map<String, String> assignmentCounts;

        @SerializedName("created_by")
        @Expose
        private Map<String, String> createdBy;

        @SerializedName("created_at")
        @Expose
        private String createdAt;

        @SerializedName("modified_at")
        @Expose
        private String modifiedAt;

        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;

        @SerializedName("filter_started_at")
        @Expose
        private String filterStartedAt;

        @SerializedName("filter_ended_at")
        @Expose
        private String filterEndedAt;

        /**
         * No args constructor for use in serialization.
         */
        public Info() {
        }

        /**
         * @return The type
         */
        public String getType() {
            return this.type;
        }

        /**
         * @param type The type
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * The type.
         *
         * @param type The type
         * @return Info
         */
        public Info withType(String type) {
            this.type = type;
            return this;
        }

        /**
         * @return The id of the policy
         */
        public String getId() {
            return this.id;
        }

        /**
         * @param id The id of the policy
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * The ID of the policy.
         *
         * @param id The id of the policy
         * @return Info
         */
        public Info withId(String id) {
            this.id = id;
            return this;
        }

        /**
         * @return The policyName
         */
        public String getPolicyName() {
            return this.policyName;
        }

        /**
         * @param policyName The policy_name
         */
        public void setPolicyName(String policyName) {
            this.policyName = policyName;
        }

        /**
         * The policy name.
         *
         * @param policyName policy name
         * @return Info
         */
        public Info withPolicyName(String policyName) {
            this.policyName = policyName;
            return this;
        }

        /**
         * @return The description
         */
        public String getDescription() {
            return this.description;
        }

        /**
         * @param description The description
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * The Description.
         *
         * @param description The description
         * @return Info
         */
        public Info withDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * @return The status
         */
        public String getStatus() {
            return this.status;
        }

        /**
         * @param status The status
         */
        public void setStatus(String status) {
            this.status = status;
        }

        /**
         * The status.
         *
         * @param status status of policy
         * @return Info
         */
        public Info withStatus(String status) {
            this.status = status;
            return this;
        }

        /**
         * @return The assignmentCounts
         */
        public Map<String, String> getAssignmentCounts() {
            return this.assignmentCounts;
        }

        /**
         * @param assignmentCounts The assignment_counts
         */
        public void setAssignmentCounts(Map<String, String> assignmentCounts) {
            this.assignmentCounts = assignmentCounts;
        }

        /**
         * Assignment counts.
         *
         * @param assignmentCounts further details on policy
         * @return Info
         */
        public Info withAssignmentCounts(Map<String, String> assignmentCounts) {
            this.assignmentCounts = assignmentCounts;
            return this;
        }

        /**
         * @return The createdBy
         */
        public Map<String, String> getCreatedBy() {
            return this.createdBy;
        }

        /**
         * @param createdBy The created_by
         */
        public void setCreatedBy(Map<String, String> createdBy) {
            this.createdBy = createdBy;
        }

        /**
         * Created By.
         *
         * @param createdBy who created the policy
         * @return Info
         */
        public Info withCreatedBy(Map<String, String> createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        /**
         * @return The createdAt
         */
        public String getCreatedAt() {
            return this.createdAt;
        }

        /**
         * @param createdAt The created_at
         */
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        /**
         * Created at.
         *
         * @param createdAt when policy was created
         * @return Info
         */
        public Info withCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * @return The modifiedAt
         */
        public String getModifiedAt() {
            return this.modifiedAt;
        }

        /**
         * @param modifiedAt The modified_at
         */
        public void setModifiedAt(String modifiedAt) {
            this.modifiedAt = modifiedAt;
        }

        /**
         * Modified At.
         *
         * @param modifiedAt time when policy was modified
         * @return Info
         */
        public Info withModifiedAt(String modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        /**
         * @return The deletedAt
         */
        public Object getDeletedAt() {
            return this.deletedAt;
        }

        /**
         * @param deletedAt The deleted_at
         */
        public void setDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
        }

        /**
         * Deleted At.
         *
         * @param deletedAt when policy was deleted
         * @return Info
         */
        public Info withDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }

        /**
         * @return The filterStartedAt
         */
        public String getFilterStartedAt() {
            return this.filterStartedAt;
        }

        /**
         * @param filterStartedAt The filter_started_at
         */
        public void setFilterStartedAt(String filterStartedAt) {
            this.filterStartedAt = filterStartedAt;
        }

        /**
         * Filter Started At.
         *
         * @param filterStartedAt Start time
         * @return Info
         */
        public Info withFilterStartedAt(String filterStartedAt) {
            this.filterStartedAt = filterStartedAt;
            return this;
        }

        /**
         * @return The filterEndedAt
         */
        public String getFilterEndedAt() {
            return this.filterEndedAt;
        }

        /**
         * @param filterEndedAt The filter_ended_at
         */
        public void setFilterEndedAt(String filterEndedAt) {
            this.filterEndedAt = filterEndedAt;
        }

        /**
         * Filter Ended At.
         *
         * @param filterEndedAt end time
         * @return Info
         */
        public Info withFilterEndedAt(String filterEndedAt) {
            this.filterEndedAt = filterEndedAt;
            return this;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }

        @Override
        public boolean equals(Object other) {
            return EqualsBuilder.reflectionEquals(this, other);
        }

    }
}
