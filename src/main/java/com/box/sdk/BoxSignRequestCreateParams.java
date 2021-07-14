package com.box.sdk;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.util.List;

/**
 *
 */
public class BoxSignRequestCreateParams {
	private boolean isDocumentPreparationNeeded;
	private String redirectUrl;
	private String declinedRedirectUrl;
	private List<BoxSignRequestRequiredAttachment> requiredAttachments;
	private boolean areAttachmentsEnabled;
	private boolean areTextSignaturesEnabled;
	private boolean isTextEnabled;
	private boolean areDatesEnabled;
	private boolean areEmailsDisabled;
	private String signatureColor;
	private boolean isPhoneVerificationRequiredToView;
	private String emailSubject;
	private String emailMessage;
	private boolean areRemindersEnabled;
	private String name;
	private List<BoxSignRequestPrefillTag> prefillTags;
	private int daysValid;
	private String externalId;

	public boolean isDocumentPreparationNeeded() {
		return isDocumentPreparationNeeded;
	}

	public BoxSignRequestCreateParams setDocumentPreparationNeeded(boolean isDocumentPreparationNeeded) {
		this.isDocumentPreparationNeeded = isDocumentPreparationNeeded;
		return this;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public BoxSignRequestCreateParams setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
		return this;
	}

	public String getDeclinedRedirectUrl() {
		return declinedRedirectUrl;
	}

	public BoxSignRequestCreateParams setDeclinedRedirectUrl(String declinedRedirectUrl) {
		this.declinedRedirectUrl = declinedRedirectUrl;
		return this;
	}

	public boolean isAreAttachmentsEnabled() {
		return areAttachmentsEnabled;
	}

	public BoxSignRequestCreateParams setAreAttachmentsEnabled(boolean areAttachmentsEnabled) {
		this.areAttachmentsEnabled = areAttachmentsEnabled;
		return this;
	}

	public boolean isAreTextSignaturesEnabled() {
		return areTextSignaturesEnabled;
	}

	public BoxSignRequestCreateParams setAreTextSignaturesEnabled(boolean areTextSignaturesEnabled) {
		this.areTextSignaturesEnabled = areTextSignaturesEnabled;
		return this;
	}

	public boolean isTextEnabled() {
		return isTextEnabled;
	}

	public BoxSignRequestCreateParams setTextEnabled(boolean textEnabled) {
		isTextEnabled = textEnabled;
		return this;
	}

	public boolean isAreDatesEnabled() { return areDatesEnabled; }

	public BoxSignRequestCreateParams setAreDatesEnabled(boolean areDatesEnabled) {
		this.areDatesEnabled = areDatesEnabled;
		return this;
	}

	public boolean isAreEmailsDisabled() {
		return areEmailsDisabled;
	}

	public BoxSignRequestCreateParams setAreEmailsDisabled(boolean areEmailsDisabled) {
		this.areEmailsDisabled = areEmailsDisabled;
		return this;
	}

	public String getSignatureColor() {
		return signatureColor;
	}

	public BoxSignRequestCreateParams setSignatureColor(String signatureColor) {
		this.signatureColor = signatureColor;
		return this;
	}

	public boolean isPhoneVerificationRequiredToView() {
		return isPhoneVerificationRequiredToView;
	}

	public BoxSignRequestCreateParams setPhoneVerificationRequiredToView(boolean isPhoneVerificationRequiredToView) {
		this.isPhoneVerificationRequiredToView = isPhoneVerificationRequiredToView;
		return this;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public BoxSignRequestCreateParams setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
		return this;
	}

	public String getEmailMessage() {
		return emailMessage;
	}

	public BoxSignRequestCreateParams setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
		return this;
	}

	public boolean isAreRemindersEnabled() {
		return areRemindersEnabled;
	}

	public BoxSignRequestCreateParams setAreRemindersEnabled(boolean areRemindersEnabled) {
		this.areRemindersEnabled = areRemindersEnabled;
		return this;
	}

	public String getName() {
		return name;
	}

	public BoxSignRequestCreateParams setName(String name) {
		this.name = name;
		return this;
	}

	public int getDaysValid() {
		return daysValid;
	}

	public BoxSignRequestCreateParams setDaysValid(int daysValid) {
		this.daysValid = daysValid;
		return this;
	}

	public String getExternalId() {
		return externalId;
	}

	public BoxSignRequestCreateParams setExternalId(String externalId) {
		this.externalId = externalId;
		return this;
	}

	public List<BoxSignRequestRequiredAttachment> getRequiredAttachments() { return requiredAttachments; }

	public BoxSignRequestCreateParams setRequiredAttachments(List<BoxSignRequestRequiredAttachment> requiredAttachments) {
		this.requiredAttachments = requiredAttachments;
		return this;
	}

	public List<BoxSignRequestPrefillTag> getPrefillTags() { return prefillTags; }

	public BoxSignRequestCreateParams setPrefillTags(List<BoxSignRequestPrefillTag> prefillTags) {
		this.prefillTags = prefillTags;
		return this;
	}

	public void appendParamsAsJson(JsonObject requestJSON) {
		requestJSON.add("is_document_preparation_needed", isDocumentPreparationNeeded);
		requestJSON.add("redirect_url", redirectUrl);
		requestJSON.add("declined_redirect_url", declinedRedirectUrl);
		requestJSON.add("are_attachments_enabled", areAttachmentsEnabled);
		requestJSON.add("are_text_signatures_enabled", areTextSignaturesEnabled);
		requestJSON.add("is_text_enabled", isTextEnabled);
		requestJSON.add("are_dates_enabled", areDatesEnabled);
		requestJSON.add("signature_color", signatureColor);
		requestJSON.add("is_phone_verification_required_to_view", isPhoneVerificationRequiredToView);
		requestJSON.add("email_subject", emailSubject);
		requestJSON.add("email_message", emailMessage);
		requestJSON.add("are_reminders_enabled", areRemindersEnabled);
		requestJSON.add("name", name);
		requestJSON.add("days_valid", daysValid);
		requestJSON.add("external_id", externalId);

		JsonArray requiredAttachmentsJSON = new JsonArray();
		for(BoxSignRequestRequiredAttachment requiredAttachment : requiredAttachments){
			requiredAttachmentsJSON.add(requiredAttachment.getJSONObject());
		}
		requestJSON.add("required_attachments", requiredAttachmentsJSON);

		JsonArray prefillTagsJSON = new JsonArray();
		for(BoxSignRequestPrefillTag prefillTag : prefillTags){
			prefillTagsJSON.add(prefillTag.getJSONObject());
		}
		requestJSON.add("prefill_tags", prefillTagsJSON);

		return;
	}
}
