package com.box.sdkgen.schemas.signrequestbase;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.signrequestprefilltag.SignRequestPrefillTag;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class SignRequestBase extends SerializableObject {

  @JsonProperty("is_document_preparation_needed")
  protected Boolean isDocumentPreparationNeeded;

  @JsonProperty("redirect_url")
  protected String redirectUrl;

  @JsonProperty("declined_redirect_url")
  protected String declinedRedirectUrl;

  @JsonProperty("are_text_signatures_enabled")
  protected Boolean areTextSignaturesEnabled;

  @JsonProperty("email_subject")
  protected String emailSubject;

  @JsonProperty("email_message")
  protected String emailMessage;

  @JsonProperty("are_reminders_enabled")
  protected Boolean areRemindersEnabled;

  protected String name;

  @JsonProperty("prefill_tags")
  protected List<SignRequestPrefillTag> prefillTags;

  @JsonProperty("days_valid")
  protected Long daysValid;

  @JsonProperty("external_id")
  protected String externalId;

  @JsonProperty("template_id")
  protected String templateId;

  @JsonProperty("external_system_name")
  protected String externalSystemName;

  public SignRequestBase() {
    super();
  }

  protected SignRequestBase(SignRequestBaseBuilder builder) {
    super();
    this.isDocumentPreparationNeeded = builder.isDocumentPreparationNeeded;
    this.redirectUrl = builder.redirectUrl;
    this.declinedRedirectUrl = builder.declinedRedirectUrl;
    this.areTextSignaturesEnabled = builder.areTextSignaturesEnabled;
    this.emailSubject = builder.emailSubject;
    this.emailMessage = builder.emailMessage;
    this.areRemindersEnabled = builder.areRemindersEnabled;
    this.name = builder.name;
    this.prefillTags = builder.prefillTags;
    this.daysValid = builder.daysValid;
    this.externalId = builder.externalId;
    this.templateId = builder.templateId;
    this.externalSystemName = builder.externalSystemName;
  }

  public Boolean getIsDocumentPreparationNeeded() {
    return isDocumentPreparationNeeded;
  }

  public String getRedirectUrl() {
    return redirectUrl;
  }

  public String getDeclinedRedirectUrl() {
    return declinedRedirectUrl;
  }

  public Boolean getAreTextSignaturesEnabled() {
    return areTextSignaturesEnabled;
  }

  public String getEmailSubject() {
    return emailSubject;
  }

  public String getEmailMessage() {
    return emailMessage;
  }

  public Boolean getAreRemindersEnabled() {
    return areRemindersEnabled;
  }

  public String getName() {
    return name;
  }

  public List<SignRequestPrefillTag> getPrefillTags() {
    return prefillTags;
  }

  public Long getDaysValid() {
    return daysValid;
  }

  public String getExternalId() {
    return externalId;
  }

  public String getTemplateId() {
    return templateId;
  }

  public String getExternalSystemName() {
    return externalSystemName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignRequestBase casted = (SignRequestBase) o;
    return Objects.equals(isDocumentPreparationNeeded, casted.isDocumentPreparationNeeded)
        && Objects.equals(redirectUrl, casted.redirectUrl)
        && Objects.equals(declinedRedirectUrl, casted.declinedRedirectUrl)
        && Objects.equals(areTextSignaturesEnabled, casted.areTextSignaturesEnabled)
        && Objects.equals(emailSubject, casted.emailSubject)
        && Objects.equals(emailMessage, casted.emailMessage)
        && Objects.equals(areRemindersEnabled, casted.areRemindersEnabled)
        && Objects.equals(name, casted.name)
        && Objects.equals(prefillTags, casted.prefillTags)
        && Objects.equals(daysValid, casted.daysValid)
        && Objects.equals(externalId, casted.externalId)
        && Objects.equals(templateId, casted.templateId)
        && Objects.equals(externalSystemName, casted.externalSystemName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        isDocumentPreparationNeeded,
        redirectUrl,
        declinedRedirectUrl,
        areTextSignaturesEnabled,
        emailSubject,
        emailMessage,
        areRemindersEnabled,
        name,
        prefillTags,
        daysValid,
        externalId,
        templateId,
        externalSystemName);
  }

  @Override
  public String toString() {
    return "SignRequestBase{"
        + "isDocumentPreparationNeeded='"
        + isDocumentPreparationNeeded
        + '\''
        + ", "
        + "redirectUrl='"
        + redirectUrl
        + '\''
        + ", "
        + "declinedRedirectUrl='"
        + declinedRedirectUrl
        + '\''
        + ", "
        + "areTextSignaturesEnabled='"
        + areTextSignaturesEnabled
        + '\''
        + ", "
        + "emailSubject='"
        + emailSubject
        + '\''
        + ", "
        + "emailMessage='"
        + emailMessage
        + '\''
        + ", "
        + "areRemindersEnabled='"
        + areRemindersEnabled
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "prefillTags='"
        + prefillTags
        + '\''
        + ", "
        + "daysValid='"
        + daysValid
        + '\''
        + ", "
        + "externalId='"
        + externalId
        + '\''
        + ", "
        + "templateId='"
        + templateId
        + '\''
        + ", "
        + "externalSystemName='"
        + externalSystemName
        + '\''
        + "}";
  }

  public static class SignRequestBaseBuilder {

    protected Boolean isDocumentPreparationNeeded;

    protected String redirectUrl;

    protected String declinedRedirectUrl;

    protected Boolean areTextSignaturesEnabled;

    protected String emailSubject;

    protected String emailMessage;

    protected Boolean areRemindersEnabled;

    protected String name;

    protected List<SignRequestPrefillTag> prefillTags;

    protected Long daysValid;

    protected String externalId;

    protected String templateId;

    protected String externalSystemName;

    public SignRequestBaseBuilder isDocumentPreparationNeeded(Boolean isDocumentPreparationNeeded) {
      this.isDocumentPreparationNeeded = isDocumentPreparationNeeded;
      return this;
    }

    public SignRequestBaseBuilder redirectUrl(String redirectUrl) {
      this.redirectUrl = redirectUrl;
      return this;
    }

    public SignRequestBaseBuilder declinedRedirectUrl(String declinedRedirectUrl) {
      this.declinedRedirectUrl = declinedRedirectUrl;
      return this;
    }

    public SignRequestBaseBuilder areTextSignaturesEnabled(Boolean areTextSignaturesEnabled) {
      this.areTextSignaturesEnabled = areTextSignaturesEnabled;
      return this;
    }

    public SignRequestBaseBuilder emailSubject(String emailSubject) {
      this.emailSubject = emailSubject;
      return this;
    }

    public SignRequestBaseBuilder emailMessage(String emailMessage) {
      this.emailMessage = emailMessage;
      return this;
    }

    public SignRequestBaseBuilder areRemindersEnabled(Boolean areRemindersEnabled) {
      this.areRemindersEnabled = areRemindersEnabled;
      return this;
    }

    public SignRequestBaseBuilder name(String name) {
      this.name = name;
      return this;
    }

    public SignRequestBaseBuilder prefillTags(List<SignRequestPrefillTag> prefillTags) {
      this.prefillTags = prefillTags;
      return this;
    }

    public SignRequestBaseBuilder daysValid(Long daysValid) {
      this.daysValid = daysValid;
      return this;
    }

    public SignRequestBaseBuilder externalId(String externalId) {
      this.externalId = externalId;
      return this;
    }

    public SignRequestBaseBuilder templateId(String templateId) {
      this.templateId = templateId;
      return this;
    }

    public SignRequestBaseBuilder externalSystemName(String externalSystemName) {
      this.externalSystemName = externalSystemName;
      return this;
    }

    public SignRequestBase build() {
      return new SignRequestBase(this);
    }
  }
}
