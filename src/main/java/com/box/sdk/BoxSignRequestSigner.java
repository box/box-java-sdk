package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class BoxSignRequestSigner extends BoxJSONObject {
	private String email;
	private String name;
	private String role;
	private boolean isInPerson;
	private int order;
	private String language;
	private String verificationPhoneNumber;
	private String embedUrlExternalUserId;
	private String redirectUrl;
	private String declinedRedirectUrl;
	private boolean hasViewedEmail;
	private boolean hasViewedDocument;
	private BoxSignerDecision signerDecision;
	private List<BoxSignerInput> inputs;
	private String embedUrl;
	private List<BoxFile.Info> attachments;
	private BoxAPIConnection api;

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

	public boolean isInPerson() {
		return isInPerson;
	}

	public int getOrder() {
		return order;
	}

	public String getLanguage() {
		return language;
	}

	public String getVerificationPhoneNumber() {
		return verificationPhoneNumber;
	}

	public String getEmbedUrlExternalUserId() {
		return embedUrlExternalUserId;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public String getDeclinedRedirectUrl() {
		return declinedRedirectUrl;
	}

	public boolean hasViewedEmail() {
		return hasViewedEmail;
	}

	public boolean hasViewedDocument() {
		return hasViewedDocument;
	}

	public BoxSignerDecision getSignerDecision() {
		return signerDecision;
	}

	public List<BoxSignerInput> getInputs() {
		return inputs;
	}

	public String getEmbedUrl() {
		return embedUrl;
	}

	public List<BoxFile.Info> getAttachments() {
		return attachments;
	}

	/**
	 * Construct a BoxRecentItem.
	 * @param jsonObject the parsed JSON object.
	 * @param api the API connection to be used to fetch interacted item
	 */
	public BoxSignRequestSigner(JsonObject jsonObject, BoxAPIConnection api) {
		super(jsonObject);
		this.api = api;
	}

	public class BoxSignerDecision extends BoxJSONObject {
		private String type;
		private Date finalizedAt;

		public BoxSignerDecision(JsonObject jsonObject) {
			super(jsonObject);
		}

		@Override
		void parseJSONMember(JsonObject.Member member){
			JsonValue value = member.getValue();
			String memberName = member.getName();
			try{
				if (memberName.equals("type")) {
					this.type = value.asString();
				} else if (memberName.equals("finalized_at")) {
					this.finalizedAt = BoxDateFormat.parse(value.asString());
				}
			}catch (Exception e) {
				throw new BoxDeserializationException(memberName, value.toString(), e);
			}
		}
	}

	public class BoxSignerInput extends BoxJSONObject{
		public String documentTagId;
		public String textValue;
		public boolean checkboxValue;
		public Date dateValue;
		public String type;
		public int pageIndex;

		public BoxSignerInput(JsonObject jsonObject) {
			super(jsonObject);
		}

		@Override
		void parseJSONMember(JsonObject.Member member){
			JsonValue value = member.getValue();
			String memberName = member.getName();
			try{
				if (memberName.equals("documentTagId")) {
					this.documentTagId = value.asString();
				} else if (memberName.equals("text_value")) {
					this.textValue = value.asString();
				} else if (memberName.equals("checkbox_value")) {
					this.checkboxValue = value.asBoolean();
				} else if (memberName.equals("date_value")) {
					this.dateValue = BoxDateFormat.parse(value.asString());
				} else if (memberName.equals("type")) {
					this.type = value.asString();
				} else if (memberName.equals("page_index")) {
					this.pageIndex = value.asInt();
				}
			}catch (Exception e) {
				throw new BoxDeserializationException(memberName, value.toString(), e);
			}
		}
	}

	@Override
	void parseJSONMember(JsonObject.Member member){
		JsonValue value = member.getValue();
		String memberName = member.getName();
		try{
			if (memberName.equals("email")) {
				this.email = value.asString();
			} else if (memberName.equals("name")) {
				this.name = value.asString();
			} else if (memberName.equals("role")) {
				this.role = value.asString();
			} else if (memberName.equals("is_in_person")) {
				this.isInPerson = value.asBoolean();
			} else if (memberName.equals("order")) {
				this.order = value.asInt();
			} else if (memberName.equals("language")) {
				this.language = value.asString();
			} else if (memberName.equals("verification_phone_number")) {
				this.verificationPhoneNumber = value.asString();
			} else if (memberName.equals("embed_url_external_user_id")) {
				this.embedUrlExternalUserId = value.asString();
			} else if (memberName.equals("redirect_url")) {
				this.redirectUrl = value.asString();
			} else if (memberName.equals("declined_redirect_url")) {
				this.declinedRedirectUrl = value.asString();
			} else if (memberName.equals("has_viewed_email")) {
				this.hasViewedEmail = value.asBoolean();
			} else if (memberName.equals("has_viewed_document")) {
				this.hasViewedDocument = value.asBoolean();
			} else if (memberName.equals("signer_decision")) {
				JsonObject signerDecisionJSON = value.asObject();
				BoxSignerDecision signerDecision = new BoxSignerDecision(signerDecisionJSON);
				this.signerDecision = signerDecision;
			} else if (memberName.equals("inputs")) {
				List<BoxSignerInput> inputs = new ArrayList<>();
				for (JsonValue inputJSON : value.asArray()) {
					BoxSignerInput input = new BoxSignerInput(inputJSON.asObject());
					inputs.add(input);
				}
				this.inputs = inputs;
			} else if (memberName.equals("embed_url")) {
				this.embedUrl = value.asString();
			} else if (memberName.equals("attachments")) {
				List<BoxFile.Info> attachments = new ArrayList<>();
				for (JsonValue attachmentJSON : value.asArray()) {
					String fileID = attachmentJSON.asObject().get("id").asString();
					BoxFile file = new BoxFile(api, fileID);
					attachments.add(file.new Info(attachmentJSON.asObject()));
				}
				this.attachments = attachments;
			}
		}catch (Exception e) {
			throw new BoxDeserializationException(memberName, value.toString(), e);
		}
	}

}
