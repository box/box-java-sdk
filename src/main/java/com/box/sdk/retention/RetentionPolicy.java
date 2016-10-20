package com.box.sdk.retention;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxAPIException;
import com.box.sdk.BoxAPIRequest;
import com.box.sdk.BoxAPIResponse;
import com.box.sdk.BoxJSONRequest;
import com.eclipsesource.json.JsonObject;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;


/**
 * Retention Policy.
 */
public final class RetentionPolicy {

    private static final String RETENTION_POLICIES_URL_PATH = "retention_policies";

    private RetentionPolicy() {
    }

    /**
     * Create a new policy.
     *
     * @param api               agent that makes calls against API
     * @param policyName        name of new policy
     * @param type              the time qualifier of the policy (finite, inifinte)
     * @param dispositionAction action to apply on policy (e.g.: remove_retention)
     * @return A new policy
     * @throws MalformedURLException If error in URL
     */
    public static RetentionPolicy.Info createRetentionPolicy(BoxAPIConnection api, String policyName,
                                                             RetentionPolicyType type,
                                                             RetentionPolicyDispositionAction dispositionAction)
            throws MalformedURLException {

        return createRetentionPolicy(api, policyName, type, null, dispositionAction);
    }

    /**
     * Create a new policy.
     *
     * @param api               agent that makes calls against API
     * @param policyName        name of new policy
     * @param type              the time qualifier of the policy (finite, inifinte)
     * @param retentionLength   duration of time to uphold the policy
     * @param dispositionAction action to apply on policy (e.g.: remove_retention)
     * @return A new policy
     * @throws MalformedURLException If error in URL
     */
    public static RetentionPolicy.Info createRetentionPolicy(BoxAPIConnection api, String policyName,
                                                             RetentionPolicyType type,
                                                             String retentionLength,
                                                             RetentionPolicyDispositionAction dispositionAction)
            throws MalformedURLException {

        BoxJSONRequest request = new BoxJSONRequest(api, new URL(api.getBaseURL() + RETENTION_POLICIES_URL_PATH),
                "POST");

        JsonObject jsonRes = (new JsonObject())
                .add("policy_name", policyName)
                .add("policy_type", type.toString())
                .add("disposition_action", dispositionAction.toString());

        if (retentionLength != null) {
            jsonRes.add("retention_length", retentionLength.toString());
        }

        request.setBody(jsonRes.toString());
        BoxAPIResponse response = request.send();

        try {
            return new Gson().fromJson(IOUtils.toString(response.getBody(), "UTF-8"), RetentionPolicy.Info.class);
        } catch (IOException e) {
            throw new BoxAPIException("Unable to map JSON response to RetentionPolicy", e);
        }
    }

    /**
     * Retrieve a policy.
     *
     * @param api      agent that makes calls against API
     * @param policyId ID of target policy
     * @return An exsting policy
     * @throws MalformedURLException If error in URL
     */
    public static RetentionPolicy.Info getRetentionPolicy(BoxAPIConnection api, String policyId) throws
            MalformedURLException {

        BoxAPIRequest request = new BoxAPIRequest(api, new URL(api.getBaseURL() + RETENTION_POLICIES_URL_PATH + "/"
                + policyId), "GET");
        BoxAPIResponse response = request.send();

        try {
            return new Gson().fromJson(IOUtils.toString(response.getBody(), "UTF-8"), RetentionPolicy.Info.class);
        } catch (IOException e) {
            throw new BoxAPIException("Unable to map JSON response to RetentionPolicy", e);
        }
    }

    /**
     * Retention Policy DTO.
     */
    public static class Info {

        @SerializedName("policy_name")
        private String policyName;

        @SerializedName("policy_type")
        private RetentionPolicyType type;

        @SerializedName("status")
        private RetentionPolicyStatus status;

        @SerializedName("id")
        private String id;

        @SerializedName("disposition_action")
        private RetentionPolicyDispositionAction dispositionAction;

        @SerializedName("retention_length")
        private String retentionLength;

        /**
         * Constructor.
         *
         * @param policyName        Name of Policy
         * @param type              Type of policy
         * @param status            Status of policy
         * @param id                policy ID
         * @param dispositionAction policy disposition
         * @param retentionLength   length of time policy should be active
         */
        public Info(String policyName, RetentionPolicyType type, RetentionPolicyStatus status, String id,
                    RetentionPolicyDispositionAction dispositionAction, String retentionLength) {
            this.policyName = policyName;
            this.type = type;
            this.status = status;
            this.id = id;
            this.dispositionAction = dispositionAction;
            this.retentionLength = retentionLength;
        }

        /**
         * Get Policy Name.
         *
         * @return policy name
         */
        public String getPolicyName() {
            return this.policyName;
        }

        /**
         * Set Policy Name.
         *
         * @param policyName The name of the policy
         */
        public void setPolicyName(String policyName) {
            this.policyName = policyName;
        }

        /**
         * Get Policy Type.
         *
         * @return policy type
         */
        public RetentionPolicyType getType() {
            return this.type;
        }

        /**
         * Set Policy Type.
         *
         * @param type The type of policy
         */
        public void setType(RetentionPolicyType type) {
            this.type = type;
        }

        /**
         * Get Policy Status.
         *
         * @return the status of the policy
         */
        public RetentionPolicyStatus getStatus() {
            return this.status;
        }

        /**
         * Set Policy Status.
         *
         * @param status the status of the policy
         */
        public void setStatus(RetentionPolicyStatus status) {
            this.status = status;
        }

        /**
         * Get Policy ID.
         *
         * @return policy ID
         */
        public String getId() {
            return this.id;
        }

        /**
         * Set Policy ID.
         *
         * @param id the policy ID
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * Get Disposition Action.
         *
         * @return disposition action of policy
         */
        public RetentionPolicyDispositionAction getDispositionAction() {
            return this.dispositionAction;
        }

        /**
         * Get Disposition Action.
         *
         * @param dispositionAction disposition action of policy
         */
        public void setDispositionAction(RetentionPolicyDispositionAction dispositionAction) {
            this.dispositionAction = dispositionAction;
        }

        /**
         * Get Retention Length.
         *
         * @return length of time for policy
         */
        public String getRetentionLength() {
            return this.retentionLength;
        }

        /**
         * Set Retention Length.
         *
         * @param retentionLength length of time for policy
         */
        public void setRetentionLength(String retentionLength) {
            this.retentionLength = retentionLength;
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
