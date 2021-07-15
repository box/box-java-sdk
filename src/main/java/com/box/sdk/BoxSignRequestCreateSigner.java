package com.box.sdk;

import com.eclipsesource.json.JsonObject;

/**
 * Represents a signer during creation of BoxSignRequest.
 */
public class BoxSignRequestCreateSigner {
	private String email;
	private String name;
	private BoxSignRequestSignerRole role;
	private Boolean isInPerson;
	private Integer order;
	private String language;
	private String verificationPhoneNumber;
	private String embedUrlExternalUserId;
	private String redirectUrl;
	private String declinedRedirectUrl;
	private String verificationPassword;

	/**
	 * Constructs a BoxSignRequestNewSigner with an email.
	 *
	 * @param email of signer.
	 * @return BoxSignRequestNewSigner object.
	 */
	public BoxSignRequestCreateSigner(String email) {
		this.email = email;
	}

	/**
	 * Gets the name of signer.
	 * @return name of signer.
	 */
	public String getName() { return name; }

	/**
	 * Sets the name of signer.
	 * @param name of signer.
	 * @return this BoxSignRequestNewSigner object for chaining.
	 */
	public BoxSignRequestCreateSigner setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Gets the role of the signer.
	 * @return role of the signer.
	 */
	public BoxSignRequestSignerRole getRole() { return role; }

	/**
	 * Sets the role of the signer.
	 * @param role of the signer.
	 * @return this BoxSignRequestNewSigner object for chaining.
	 */
	public BoxSignRequestCreateSigner setRole(BoxSignRequestSignerRole role) {
		this.role = role;
		return this;
	}

	/**
	 * Gets the flag that when used in combination with an embed url on the sender, after sender has signed,
	 * they will be redirected to the next InPerson signer.
	 * @return true if is in person signer, otherwise false.
	 */
	public Boolean getInPerson() { return isInPerson; }

	/**
	 * Sets the flag that when used in combination with an embed url on the sender, after sender has signed,
	 * they will be redirected to the next InPerson signer.
	 * @param isInPerson flag.
	 * @return this BoxSignRequestNewSigner object for chaining.
	 */
	public BoxSignRequestCreateSigner setInPerson(Boolean isInPerson) {
		this.isInPerson = isInPerson;
		return this;
	}

	/**
	 * Gets the order of signer.
	 * @return order of signer.
	 */
	public Integer getOrder() { return order; }

	/**
	 * Sets the order of signer.
	 * @param order of signer.
	 * @return this BoxSignRequestNewSigner object for chaining.
	 */
	public BoxSignRequestCreateSigner setOrder(Integer order) {
		this.order = order;
		return this;
	}

	/**
	 * Gets the language for email notifications sent to this signer.
	 * @return language for email notifications sent to this signer.
	 */
	public String getLanguage() { return language; }

	/**
	 * Sets the language for email notifications sent to this signer.
	 * @param language for email notifications sent to this signer.
	 * @return this BoxSignRequestNewSigner object for chaining.
	 */
	public BoxSignRequestCreateSigner setLanguage(String language) {
		this.language = language;
		return this;
	}

	/**
	 * Gets the phone number that will be used to verify the signer before the signer can sign.
	 * This requires a country code (should follow E.164).
	 * @return verification phone number.
	 */
	public String getVerificationPhoneNumber() { return verificationPhoneNumber; }

	/**
	 * Sets the phone number that will be used to verify the signer before the signer can sign.
	 * This requires a country code (should follow E.164).
	 * @param verificationPhoneNumber for this signer.
	 * @return this BoxSignRequestNewSigner object for chaining.
	 */
	public BoxSignRequestCreateSigner setVerificationPhoneNumber(String verificationPhoneNumber) {
		this.verificationPhoneNumber = verificationPhoneNumber;
		return this;
	}

	/**
	 * Gets the user id for this signer in external application responsible for authentication when accessing the embed url.
	 * @return embed url external user id.
	 */
	public String getEmbedUrlExternalUserId() { return embedUrlExternalUserId; }

	/**
	 * Sets the user id for this signer in external application responsible for authentication when accessing the embed url.
	 * @param embedUrlExternalUserId for this signer in external application responsible for authentication when accessing the embed url.
	 * @return this BoxSignRequestNewSigner object for chaining.
	 */
	public BoxSignRequestCreateSigner setEmbedUrlExternalUserId(String embedUrlExternalUserId) {
		this.embedUrlExternalUserId = embedUrlExternalUserId;
		return this;
	}

	/**
	 * Gets the the uri that a signer will be redirect to after signing a document -
	 * this will override the redirect url defined in the general sign request.
	 * If no declined redirect url is specified, this will be used for decline actions as well.
	 * @return redirect url.
	 */
	public String getRedirectUrl() { return redirectUrl; }

	/**
	 * Sets the the uri that a signer will be redirect to after signing a document -
	 * this will override the redirect url defined in the general sign request.
	 * If no declined redirect url is specified, this will be used for decline actions as well.
	 * @param redirectUrl for this signer.
	 * @return this BoxSignRequestNewSigner object for chaining.
	 */
	public BoxSignRequestCreateSigner setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
		return this;
	}

	/**
	 * Gets the uri that a signer will be redirect to after declining to sign a document
	 * - this will override the redirect url defined in the general sign request.
	 * @return declined redirect url.
	 */
	public String getDeclinedRedirectUrl() { return declinedRedirectUrl; }

	/**
	 * Sets the uri that a signer will be redirect to after declining to sign a document
	 * this will override the redirect url defined in the general sign request.
	 * @param declinedRedirectUrl for this signer.
	 * @return this BoxSignRequestNewSigner object for chaining.
	 */
	public BoxSignRequestCreateSigner setDeclinedRedirectUrl(String declinedRedirectUrl) {
		this.declinedRedirectUrl = declinedRedirectUrl;
		return this;
	}

	/**
	 * Gets the signer password that they need to enter before signing a document.
	 * @return declined redirect url.
	 */
	public String getVerificationPassword() { return verificationPassword; }

	/**
	 * Sets the signer password that they need to enter before signing a document.
	 * @param verificationPassword for this signer.
	 * @return this BoxSignRequestNewSigner object for chaining.
	 */
	public BoxSignRequestCreateSigner setVerificationPassword(String verificationPassword) {
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
			jsonObj.add("role", role.name().toLowerCase());
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
