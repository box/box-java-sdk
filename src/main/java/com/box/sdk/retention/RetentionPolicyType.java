package com.box.sdk.retention;

public enum RetentionPolicyType {
	INDEFINITE("indefinite"), FINITE("finite");

	private String value;

	RetentionPolicyType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}
}
