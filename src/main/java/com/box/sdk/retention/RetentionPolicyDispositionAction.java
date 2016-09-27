package com.box.sdk.retention;

public enum RetentionPolicyDispositionAction {
	REMOVE_RETENTION("remove_retention");

	private String value;

	RetentionPolicyDispositionAction(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}
}
