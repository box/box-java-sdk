package com.box.sdk;

import com.eclipsesource.json.JsonObject;

/**
 *
 */
public class BoxSignRequestNewSigner {
	private String email;
	private String name;
	private String role;
	private Boolean isInPerson;
	private Integer order;
	private String language;
	private String verificationPhoneNumber;
	private String embedUrlExternalUserId;
	private String redirectUrl;
	private String declinedRedirectUrl;
	private String verificationPassword;

	public BoxSignRequestNewSigner(String email) {
		this.email = email;
	}

	public String getName() { return name; }

	public BoxSignRequestNewSigner setName(String name) {
		this.name = name;
		return this;
	}

	public String getRole() { return role; }

	public BoxSignRequestNewSigner setRole(String role) {
		this.role = role;
		return this;
	}

	public Boolean getInPerson() { return isInPerson; }

	public BoxSignRequestNewSigner setInPerson(Boolean inPerson) {
		isInPerson = inPerson;
		return this;
	}

	public Integer getOrder() { return order; }

	public BoxSignRequestNewSigner setOrder(Integer order) {
		this.order = order;
		return this;
	}

	public String getLanguage() { return language; }

	public BoxSignRequestNewSigner setLanguage(String language) {
		this.language = language;
		return this;
	}

	public String getVerificationPhoneNumber() { return verificationPhoneNumber; }

	public BoxSignRequestNewSigner setVerificationPhoneNumber(String verificationPhoneNumber) {
		this.verificationPhoneNumber = verificationPhoneNumber;
		return this;
	}

	public String getEmbedUrlExternalUserId() { return embedUrlExternalUserId; }

	public BoxSignRequestNewSigner setEmbedUrlExternalUserId(String embedUrlExternalUserId) {
		this.embedUrlExternalUserId = embedUrlExternalUserId;
		return this;
	}

	public String getRedirectUrl() { return redirectUrl; }

	public BoxSignRequestNewSigner setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
		return this;
	}

	public String getDeclinedRedirectUrl() { return declinedRedirectUrl; }

	public BoxSignRequestNewSigner setDeclinedRedirectUrl(String declinedRedirectUrl) {
		this.declinedRedirectUrl = declinedRedirectUrl;
		return this;
	}

	public String getVerificationPassword() { return verificationPassword; }

	public BoxSignRequestNewSigner setVerificationPassword(String verificationPassword) {
		this.verificationPassword = verificationPassword;
		return this;
	}

	/**
	 * Gets a JSON object reprsenting this class.
	 *
	 * @return the JSON object reprsenting this class.
	 */
	public JsonObject getJSONObject() {
		JsonObject jsonObj = new JsonObject();
		if(email != null){
			jsonObj.add("email", email);
		}
		if(name != null){
			jsonObj.add("name", name);
		}
		if(role != null){
			jsonObj.add("role", role);
		}
		if(isInPerson != null){
			jsonObj.add("is_in_person", isInPerson);
		}
		if(order != null){
			jsonObj.add("order", order);
		}
		if(language != null){
			jsonObj.add("language", language);
		}
		if(verificationPhoneNumber != null){
			jsonObj.add("verification_phone_number", verificationPhoneNumber);
		}
		if(embedUrlExternalUserId != null){
			jsonObj.add("embed_url_external_user_id", embedUrlExternalUserId);
		}
		if(redirectUrl != null){
			jsonObj.add("redirect_url", redirectUrl);
		}
		if(declinedRedirectUrl != null){
			jsonObj.add("declined_redirect_url", declinedRedirectUrl);
		}
		if(verificationPassword != null){
			jsonObj.add("verification_password", verificationPassword);
		}

		return jsonObj;
	}
}
