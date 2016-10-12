package com.box.sdk.retention;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxAPIException;
import com.box.sdk.BoxJSONRequest;
import com.box.sdk.BoxJSONResponse;
import com.eclipsesource.json.JsonObject;
import com.google.gson.Gson;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RetentionPolicyAssignment {

	private static final String RETENTION_POLICY_ASSIGNMENT_URL_PATH = "retention_policy_assignments";

	public static RetentionPolicyAssignment.Info createRetentionPolicyAssignment(BoxAPIConnection api, String policyID, RetentionPolicyTarget target, String folder) throws MalformedURLException {

		BoxJSONRequest request = new BoxJSONRequest(api, new URL(api.getBaseURL() + RETENTION_POLICY_ASSIGNMENT_URL_PATH), "POST");

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

	class Info {

		String type;
		String id;
		Map<String, String> retention_policy;
		Map<String, String> assigned_to;
		Map<String, String> assigned_by;
		String assigned_at;
		String timeOfAssignment;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Map<String, String> getRetention_policy() {
			return retention_policy;
		}

		public void setRetention_policy(Map<String, String> retention_policy) {
			this.retention_policy = retention_policy;
		}

		public Map<String, String> getAssigned_to() {
			return assigned_to;
		}

		public void setAssigned_to(Map<String, String> assigned_to) {
			this.assigned_to = assigned_to;
		}

		public Map<String, String> getAssigned_by() {
			return this.assigned_by;
		}

		public void setAssigned_by(Map<String, String> assigned_by) {
			this.assigned_by = assigned_by;
		}

		public String getAssigned_at() {
			return assigned_at;
		}

		public void setAssigned_at(String assigned_at) {
			this.assigned_at = assigned_at;
		}

		public String getTimeOfAssignment() {
			return timeOfAssignment;
		}

		public void setTimeOfAssignment(String timeOfAssignment) {
			this.timeOfAssignment = timeOfAssignment;
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
