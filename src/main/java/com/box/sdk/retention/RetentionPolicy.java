package com.box.sdk.retention;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxAPIResponse;
import com.box.sdk.BoxJSONRequest;
import com.eclipsesource.json.JsonObject;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.io.IOUtils;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RetentionPolicy {

	private static final String RETENTION_POLICIES_URL_PATH = "retention_policies";

	private final String policyName;
	private final RetentionPolicyType type;
	private final String retentionLength;
	private final RetentionPolicyDispositionAction dispositionAction;

	public RetentionPolicy(String policyName, RetentionPolicyType type, RetentionPolicyDispositionAction dispositionAction) {
		this(policyName, type, null, dispositionAction);
	}

	public RetentionPolicy(String policyName, RetentionPolicyType type, String retentionLength,
						   RetentionPolicyDispositionAction dispositionAction) {
		this.policyName = policyName;
		this.type = type;
		this.retentionLength = retentionLength;
		this.dispositionAction = dispositionAction;
	}

	public static RetentionPolicy.Info createRetentionPolicy(BoxAPIConnection api, String policyName,
														RetentionPolicyType type,
														RetentionPolicyDispositionAction dispositionAction) throws MalformedURLException {

		return createRetentionPolicy(api, policyName, type, null, dispositionAction);
	}

	public static RetentionPolicy.Info createRetentionPolicy(BoxAPIConnection api, String policyName, RetentionPolicyType type,
															 String retentionLength,
															 RetentionPolicyDispositionAction dispositionAction) throws MalformedURLException {

		BoxJSONRequest request = new BoxJSONRequest(api, new URL(api.getBaseURL() + RETENTION_POLICIES_URL_PATH), "POST");

		JsonObject jsonRes = (new JsonObject())
				.add("policy_name", policyName)
				.add("policy_type", type.toString())
				.add("disposition_action", dispositionAction.toString());

		if (retentionLength != null)
			jsonRes.add("retention_length", retentionLength.toString());

		request.setBody(jsonRes.toString());
		BoxAPIResponse response = request.send();

		try {
			return new Gson().fromJson(IOUtils.toString(response.getBody(), "UTF-8"), RetentionPolicy.Info.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}


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

		public Info(String policyName, RetentionPolicyType type, RetentionPolicyStatus status, String id,
					RetentionPolicyDispositionAction dispositionAction, String retentionLength) {
			this.policyName = policyName;
			this.type = type;
			this.status = status;
			this.id = id;
			this.dispositionAction = dispositionAction;
			this.retentionLength = retentionLength;
		}

		public String getPolicyName() {
			return policyName;
		}

		public void setPolicyName(String policyName) {
			this.policyName = policyName;
		}

		public RetentionPolicyType getType() {
			return type;
		}

		public void setType(RetentionPolicyType type) {
			this.type = type;
		}

		public RetentionPolicyStatus getStatus() {
			return status;
		}

		public void setStatus(RetentionPolicyStatus status) {
			this.status = status;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public RetentionPolicyDispositionAction getDispositionAction() {
			return dispositionAction;
		}

		public void setDispositionAction(RetentionPolicyDispositionAction dispositionAction) {
			this.dispositionAction = dispositionAction;
		}

		public String getRetentionLength() {
			return retentionLength;
		}

		public void setRetentionLength(String retentionLength) {
			this.retentionLength = retentionLength;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Info info = (Info) o;

			if (policyName != null ? !policyName.equals(info.policyName) : info.policyName != null) return false;
			if (type != info.type) return false;
			if (status != null ? !status.equals(info.status) : info.status != null) return false;
			if (id != null ? !id.equals(info.id) : info.id != null) return false;
			if (dispositionAction != info.dispositionAction) return false;
			if (retentionLength != null ? !retentionLength.equals(info.retentionLength) : info.retentionLength != null)
				return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = policyName != null ? policyName.hashCode() : 0;
			result = 31 * result + (type != null ? type.hashCode() : 0);
			result = 31 * result + (status != null ? status.hashCode() : 0);
			result = 31 * result + (id != null ? id.hashCode() : 0);
			result = 31 * result + (dispositionAction != null ? dispositionAction.hashCode() : 0);
			result = 31 * result + (retentionLength != null ? retentionLength.hashCode() : 0);
			return result;
		}

		@Override
		public String toString() {
			return "Info{" +
					"policyName='" + policyName + '\'' +
					", type=" + type +
					", status='" + status + '\'' +
					", id='" + id + '\'' +
					", dispositionAction=" + dispositionAction +
					", retentionLength=" + retentionLength +
					'}';
		}
	}
}
