package com.box.sdk.retention;

import com.box.sdk.*;
import com.eclipsesource.json.JsonObject;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RetentionPolicyAssignment {

	private static final String RETENTION_POLICY_ASSIGNMENT_URL_PATH = "retention_policy_assignments";
	private final String policyID;
	private final BoxFolder folder;

	private RetentionPolicyAssignment(String policyID, BoxFolder folder) {
		this.policyID = policyID;
		this.folder = folder;
	}


	public static RetentionPolicyAssignment.Info createRetentionPolicyAssignment(BoxAPIConnection api, String policyID, RetentionPolicyTarget target, String folder) throws MalformedURLException {

		BoxJSONRequest request = new BoxJSONRequest(api, new URL(api.getBaseURL() + RETENTION_POLICY_ASSIGNMENT_URL_PATH), "POST");

		JsonObject jsonRes = (new JsonObject())
				.add("policy_id", policyID)
				.add("assign_to", (new JsonObject()).add("type", target.toString()).add("id", folder));

		request.setBody(jsonRes.toString());
		BoxJSONResponse response = (BoxJSONResponse) request.send();

		try {
//			return new Gson().fromJson(IOUtils.toString(response.getBody(), "UTF-8"), RetentionPolicyAssignment.Info.class);
			return new Gson().fromJson(response.getJSON(), RetentionPolicyAssignment.Info.class);
		} catch (Exception e) {
			throw new BoxAPIException(e.getMessage());
		}
	}


	class Info {

		String type;
		String id;
		HashMap<String, String> retention_policy;
		HashMap<String, String> assigned_to;
		HashMap<String, String> assigned_by;
		String assigned_at;
		String timeOfAssignment;


		public Info(String type, String id, RetentionPolicyTarget target, String timeOfAssignment) {
			this.type = type;
			this.id = id;
			this.retention_policy = new HashMap<String, String>();
			this.assigned_by = new HashMap<String, String> ();
			this.assigned_to = new HashMap<String, String> ();
			this.assigned_at = timeOfAssignment;
		}

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

		public HashMap<String, String> getRetention_policy() {
			return retention_policy;
		}

		public void setRetention_policy(HashMap<String, String> retention_policy) {
			this.retention_policy = retention_policy;
		}

		public HashMap<String, String> getAssigned_to() {
			return assigned_to;
		}

		public void setAssigned_to(HashMap<String, String> assigned_to) {
			this.assigned_to = assigned_to;
		}

		public HashMap<String, String> getAssigned_by() {
			return this.assigned_by;
		}

		public void setAssigned_by(HashMap<String, String> assigned_by) {
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
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Info info = (Info) o;

			if (type != null ? !type.equals(info.type) : info.type != null) return false;
			if (id != null ? !id.equals(info.id) : info.id != null) return false;
			if (retention_policy != null ? !retention_policy.equals(info.retention_policy) : info.retention_policy != null)
				return false;
			if (assigned_to != null ? !assigned_to.equals(info.assigned_to) : info.assigned_to != null) return false;
			if (assigned_by != null ? !assigned_by.equals(info.assigned_by) : info.assigned_by != null) return false;
			if (assigned_at != null ? !assigned_at.equals(info.assigned_at) : info.assigned_at != null) return false;
			if (timeOfAssignment != null ? !timeOfAssignment.equals(info.timeOfAssignment) : info.timeOfAssignment != null)
				return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = type != null ? type.hashCode() : 0;
			result = 31 * result + (id != null ? id.hashCode() : 0);
			result = 31 * result + (retention_policy != null ? retention_policy.hashCode() : 0);
			result = 31 * result + (assigned_to != null ? assigned_to.hashCode() : 0);
			result = 31 * result + (assigned_by != null ? assigned_by.hashCode() : 0);
			result = 31 * result + (assigned_at != null ? assigned_at.hashCode() : 0);
			result = 31 * result + (timeOfAssignment != null ? timeOfAssignment.hashCode() : 0);
			return result;
		}

		@Override
		public String toString() {
			return "Info{" +
					"type='" + type + '\'' +
					", id='" + id + '\'' +
					", retention_policy=" + retention_policy +
					", assigned_to=" + assigned_to +
					", assigned_by=" + assigned_by +
					", assigned_at='" + assigned_at + '\'' +
					", timeOfAssignment='" + timeOfAssignment + '\'' +
					'}';
		}
	}

}
