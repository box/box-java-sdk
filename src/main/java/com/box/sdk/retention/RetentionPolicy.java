package com.box.sdk.retention;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxAPIResponse;
import com.box.sdk.BoxJSONRequest;
import com.eclipsesource.json.JsonObject;

import java.net.MalformedURLException;
import java.net.URL;

public class RetentionPolicy {

	private static final String RETENTION_POLICIES_URL_PATH = "retention_policies";

	private final String policyName;
	private final RetentionPolicyType type;
	private final Integer retentionLength;
	private final RetentionPolicyDispositionAction dispositionAction;

	public RetentionPolicy(String policyName, RetentionPolicyType type, RetentionPolicyDispositionAction dispositionAction) {
		this(policyName, type, null, dispositionAction);
	}

	public RetentionPolicy(String policyName, RetentionPolicyType type, Integer retentionLength,
						   RetentionPolicyDispositionAction dispositionAction) {
		this.policyName = policyName;
		this.type = type;
		this.retentionLength = retentionLength;
		this.dispositionAction = dispositionAction;
	}

	public static RetentionPolicy createRetentionPolicy(BoxAPIConnection api, String policyName,
														RetentionPolicyType type,
														RetentionPolicyDispositionAction dispositionAction) throws MalformedURLException {

		return createRetentionPolicy(api, policyName, type, null, dispositionAction);
	}

	public static RetentionPolicy createRetentionPolicy(BoxAPIConnection api, String policyName, RetentionPolicyType type,
														Integer retentionLength,
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

		return new RetentionPolicy(policyName, type, retentionLength, dispositionAction);
	}
}
