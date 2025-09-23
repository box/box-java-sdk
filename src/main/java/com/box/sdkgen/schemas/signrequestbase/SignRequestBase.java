package com.box.sdkgen.schemas.signrequestbase;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.signrequestprefilltag.SignRequestPrefillTag;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class SignRequestBase extends SerializableObject {

  @JsonProperty("is_document_preparation_needed")
  protected Boolean isDocumentPreparationNeeded;

  @JsonProperty("redirect_url")
  @Nullable
  protected String redirectUrl;

  @JsonProperty("declined_redirect_url")
  @Nullable
  protected String declinedRedirectUrl;

  @JsonProperty("are_text_signatures_enabled")
  protected Boolean areTextSignaturesEnabled;

  @JsonProperty("email_subject")
  @Nullable
  protected String emailSubject;

  @JsonProperty("email_message")
  @Nullable
  protected String emailMessage;

  @JsonProperty("are_reminders_enabled")
  protected Boolean areRemindersEnabled;

  protected String name;

  @JsonProperty("prefill_tags")
  protected List<SignRequestPrefillTag> prefillTags;

  @JsonProperty("days_valid")
  @Nullable
  protected Long daysValid;

  @JsonProperty("external_id")
  @Nullable
  protected String externalId;

  @JsonProperty("template_id")
  @Nullable
  protected String templateId;

  @JsonProperty("external_system_name")
  @Nullable
  protected String externalSystemName;

  public SignRequestBase() {
    super();
  }

  protected SignRequestBase(Builder builder) {
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
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

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

    public Builder isDocumentPreparationNeeded(Boolean isDocumentPreparationNeeded) {
      this.isDocumentPreparationNeeded = isDocumentPreparationNeeded;
      return this;
    }

    public Builder redirectUrl(String redirectUrl) {
      this.redirectUrl = redirectUrl;
      this.markNullableFieldAsSet("redirect_url");
      return this;
    }

    public Builder declinedRedirectUrl(String declinedRedirectUrl) {
      this.declinedRedirectUrl = declinedRedirectUrl;
      this.markNullableFieldAsSet("declined_redirect_url");
      return this;
    }

    public Builder areTextSignaturesEnabled(Boolean areTextSignaturesEnabled) {
      this.areTextSignaturesEnabled = areTextSignaturesEnabled;
      return this;
    }

    public Builder emailSubject(String emailSubject) {
      this.emailSubject = emailSubject;
      this.markNullableFieldAsSet("email_subject");
      return this;
    }

    public Builder emailMessage(String emailMessage) {
      this.emailMessage = emailMessage;
      this.markNullableFieldAsSet("email_message");
      return this;
    }

    public Builder areRemindersEnabled(Boolean areRemindersEnabled) {
      this.areRemindersEnabled = areRemindersEnabled;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder prefillTags(List<SignRequestPrefillTag> prefillTags) {
      this.prefillTags = prefillTags;
      return this;
    }

    public Builder daysValid(Long daysValid) {
      this.daysValid = daysValid;
      this.markNullableFieldAsSet("days_valid");
      return this;
    }

    public Builder externalId(String externalId) {
      this.externalId = externalId;
      this.markNullableFieldAsSet("external_id");
      return this;
    }

    public Builder templateId(String templateId) {
      this.templateId = templateId;
      this.markNullableFieldAsSet("template_id");
      return this;
    }

    public Builder externalSystemName(String externalSystemName) {
      this.externalSystemName = externalSystemName;
      this.markNullableFieldAsSet("external_system_name");
      return this;
    }

    public SignRequestBase build() {
      return new SignRequestBase(this);
    }
  }
}
