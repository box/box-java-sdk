package com.box.sdkgen.schemas.signtemplate;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.templatesigner.TemplateSigner;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** A Box Sign template object. */
@JsonFilter("nullablePropertyFilter")
public class SignTemplate extends SerializableObject {

  /** The value will always be `sign-template`. */
  @JsonDeserialize(using = SignTemplateTypeField.SignTemplateTypeFieldDeserializer.class)
  @JsonSerialize(using = SignTemplateTypeField.SignTemplateTypeFieldSerializer.class)
  protected EnumWrapper<SignTemplateTypeField> type;

  /** Template identifier. */
  protected String id;

  /** The name of the template. */
  @Nullable protected String name;

  /**
   * Subject of signature request email. This is cleaned by sign request. If this field is not
   * passed, a default subject will be used.
   */
  @JsonProperty("email_subject")
  @Nullable
  protected String emailSubject;

  /**
   * Message to include in signature request email. The field is cleaned through sanitization of
   * specific characters. However, some html tags are allowed. Links included in the message are
   * also converted to hyperlinks in the email. The message may contain the following html tags
   * including `a`, `abbr`, `acronym`, `b`, `blockquote`, `code`, `em`, `i`, `ul`, `li`, `ol`, and
   * `strong`. Be aware that when the text to html ratio is too high, the email may end up in spam
   * filters. Custom styles on these tags are not allowed. If this field is not passed, a default
   * message will be used.
   */
  @JsonProperty("email_message")
  @Nullable
  protected String emailMessage;

  /**
   * Set the number of days after which the created signature request will automatically expire if
   * not completed. By default, we do not apply any expiration date on signature requests, and the
   * signature request does not expire.
   */
  @JsonProperty("days_valid")
  @Nullable
  protected Long daysValid;

  @JsonProperty("parent_folder")
  protected FolderMini parentFolder;

  /**
   * List of files to create a signing document from. Only the ID and type fields are required for
   * each file.
   */
  @JsonProperty("source_files")
  protected List<FileMini> sourceFiles;

  /** Indicates if the template input fields are editable or not. */
  @JsonProperty("are_fields_locked")
  protected Boolean areFieldsLocked;

  /**
   * Indicates if the template document options are editable or not, for example renaming the
   * document.
   */
  @JsonProperty("are_options_locked")
  protected Boolean areOptionsLocked;

  /** Indicates if the template signers are editable or not. */
  @JsonProperty("are_recipients_locked")
  protected Boolean areRecipientsLocked;

  /** Indicates if the template email settings are editable or not. */
  @JsonProperty("are_email_settings_locked")
  protected Boolean areEmailSettingsLocked;

  /**
   * Indicates if the template files are editable or not. This includes deleting or renaming
   * template files.
   */
  @JsonProperty("are_files_locked")
  protected Boolean areFilesLocked;

  /**
   * Array of signers for the template.
   *
   * <p>**Note**: It may happen that some signers specified in the template belong to conflicting
   * [segments](r://shield-information-barrier-segment-member) (user groups). This means that due to
   * the security policies, users are assigned to segments to prevent exchanges or communication
   * that could lead to ethical conflicts. In such a case, an attempt to send a sign request based
   * on a template that lists signers in conflicting segments will result in an error.
   *
   * <p>Read more about [segments and ethical
   * walls](https://support.box.com/hc/en-us/articles/9920431507603-Understanding-Information-Barriers#h_01GFVJEHQA06N7XEZ4GCZ9GFAQ).
   */
  protected List<TemplateSigner> signers;

  /** Additional information on which fields are required and which fields are not editable. */
  @JsonProperty("additional_info")
  protected SignTemplateAdditionalInfoField additionalInfo;

  /**
   * Box's ready-sign link feature enables you to create a link to a signature request that you've
   * created from a template. Use this link when you want to post a signature request on a public
   * form — such as an email, social media post, or web page — without knowing who the signers will
   * be. Note: The ready-sign link feature is limited to Enterprise Plus customers and not available
   * to Box Verified Enterprises.
   */
  @JsonProperty("ready_sign_link")
  @Nullable
  protected SignTemplateReadySignLinkField readySignLink;

  /** Custom branding applied to notifications and signature requests. */
  @JsonProperty("custom_branding")
  @Nullable
  protected SignTemplateCustomBrandingField customBranding;

  public SignTemplate() {
    super();
  }

  protected SignTemplate(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    this.name = builder.name;
    this.emailSubject = builder.emailSubject;
    this.emailMessage = builder.emailMessage;
    this.daysValid = builder.daysValid;
    this.parentFolder = builder.parentFolder;
    this.sourceFiles = builder.sourceFiles;
    this.areFieldsLocked = builder.areFieldsLocked;
    this.areOptionsLocked = builder.areOptionsLocked;
    this.areRecipientsLocked = builder.areRecipientsLocked;
    this.areEmailSettingsLocked = builder.areEmailSettingsLocked;
    this.areFilesLocked = builder.areFilesLocked;
    this.signers = builder.signers;
    this.additionalInfo = builder.additionalInfo;
    this.readySignLink = builder.readySignLink;
    this.customBranding = builder.customBranding;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignTemplateTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmailSubject() {
    return emailSubject;
  }

  public String getEmailMessage() {
    return emailMessage;
  }

  public Long getDaysValid() {
    return daysValid;
  }

  public FolderMini getParentFolder() {
    return parentFolder;
  }

  public List<FileMini> getSourceFiles() {
    return sourceFiles;
  }

  public Boolean getAreFieldsLocked() {
    return areFieldsLocked;
  }

  public Boolean getAreOptionsLocked() {
    return areOptionsLocked;
  }

  public Boolean getAreRecipientsLocked() {
    return areRecipientsLocked;
  }

  public Boolean getAreEmailSettingsLocked() {
    return areEmailSettingsLocked;
  }

  public Boolean getAreFilesLocked() {
    return areFilesLocked;
  }

  public List<TemplateSigner> getSigners() {
    return signers;
  }

  public SignTemplateAdditionalInfoField getAdditionalInfo() {
    return additionalInfo;
  }

  public SignTemplateReadySignLinkField getReadySignLink() {
    return readySignLink;
  }

  public SignTemplateCustomBrandingField getCustomBranding() {
    return customBranding;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignTemplate casted = (SignTemplate) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(id, casted.id)
        && Objects.equals(name, casted.name)
        && Objects.equals(emailSubject, casted.emailSubject)
        && Objects.equals(emailMessage, casted.emailMessage)
        && Objects.equals(daysValid, casted.daysValid)
        && Objects.equals(parentFolder, casted.parentFolder)
        && Objects.equals(sourceFiles, casted.sourceFiles)
        && Objects.equals(areFieldsLocked, casted.areFieldsLocked)
        && Objects.equals(areOptionsLocked, casted.areOptionsLocked)
        && Objects.equals(areRecipientsLocked, casted.areRecipientsLocked)
        && Objects.equals(areEmailSettingsLocked, casted.areEmailSettingsLocked)
        && Objects.equals(areFilesLocked, casted.areFilesLocked)
        && Objects.equals(signers, casted.signers)
        && Objects.equals(additionalInfo, casted.additionalInfo)
        && Objects.equals(readySignLink, casted.readySignLink)
        && Objects.equals(customBranding, casted.customBranding);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        type,
        id,
        name,
        emailSubject,
        emailMessage,
        daysValid,
        parentFolder,
        sourceFiles,
        areFieldsLocked,
        areOptionsLocked,
        areRecipientsLocked,
        areEmailSettingsLocked,
        areFilesLocked,
        signers,
        additionalInfo,
        readySignLink,
        customBranding);
  }

  @Override
  public String toString() {
    return "SignTemplate{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "name='"
        + name
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
        + "daysValid='"
        + daysValid
        + '\''
        + ", "
        + "parentFolder='"
        + parentFolder
        + '\''
        + ", "
        + "sourceFiles='"
        + sourceFiles
        + '\''
        + ", "
        + "areFieldsLocked='"
        + areFieldsLocked
        + '\''
        + ", "
        + "areOptionsLocked='"
        + areOptionsLocked
        + '\''
        + ", "
        + "areRecipientsLocked='"
        + areRecipientsLocked
        + '\''
        + ", "
        + "areEmailSettingsLocked='"
        + areEmailSettingsLocked
        + '\''
        + ", "
        + "areFilesLocked='"
        + areFilesLocked
        + '\''
        + ", "
        + "signers='"
        + signers
        + '\''
        + ", "
        + "additionalInfo='"
        + additionalInfo
        + '\''
        + ", "
        + "readySignLink='"
        + readySignLink
        + '\''
        + ", "
        + "customBranding='"
        + customBranding
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignTemplateTypeField> type;

    protected String id;

    protected String name;

    protected String emailSubject;

    protected String emailMessage;

    protected Long daysValid;

    protected FolderMini parentFolder;

    protected List<FileMini> sourceFiles;

    protected Boolean areFieldsLocked;

    protected Boolean areOptionsLocked;

    protected Boolean areRecipientsLocked;

    protected Boolean areEmailSettingsLocked;

    protected Boolean areFilesLocked;

    protected List<TemplateSigner> signers;

    protected SignTemplateAdditionalInfoField additionalInfo;

    protected SignTemplateReadySignLinkField readySignLink;

    protected SignTemplateCustomBrandingField customBranding;

    public Builder type(SignTemplateTypeField type) {
      this.type = new EnumWrapper<SignTemplateTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<SignTemplateTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      this.markNullableFieldAsSet("name");
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

    public Builder daysValid(Long daysValid) {
      this.daysValid = daysValid;
      this.markNullableFieldAsSet("days_valid");
      return this;
    }

    public Builder parentFolder(FolderMini parentFolder) {
      this.parentFolder = parentFolder;
      return this;
    }

    public Builder sourceFiles(List<FileMini> sourceFiles) {
      this.sourceFiles = sourceFiles;
      return this;
    }

    public Builder areFieldsLocked(Boolean areFieldsLocked) {
      this.areFieldsLocked = areFieldsLocked;
      return this;
    }

    public Builder areOptionsLocked(Boolean areOptionsLocked) {
      this.areOptionsLocked = areOptionsLocked;
      return this;
    }

    public Builder areRecipientsLocked(Boolean areRecipientsLocked) {
      this.areRecipientsLocked = areRecipientsLocked;
      return this;
    }

    public Builder areEmailSettingsLocked(Boolean areEmailSettingsLocked) {
      this.areEmailSettingsLocked = areEmailSettingsLocked;
      return this;
    }

    public Builder areFilesLocked(Boolean areFilesLocked) {
      this.areFilesLocked = areFilesLocked;
      return this;
    }

    public Builder signers(List<TemplateSigner> signers) {
      this.signers = signers;
      return this;
    }

    public Builder additionalInfo(SignTemplateAdditionalInfoField additionalInfo) {
      this.additionalInfo = additionalInfo;
      return this;
    }

    public Builder readySignLink(SignTemplateReadySignLinkField readySignLink) {
      this.readySignLink = readySignLink;
      this.markNullableFieldAsSet("ready_sign_link");
      return this;
    }

    public Builder customBranding(SignTemplateCustomBrandingField customBranding) {
      this.customBranding = customBranding;
      this.markNullableFieldAsSet("custom_branding");
      return this;
    }

    public SignTemplate build() {
      return new SignTemplate(this);
    }
  }
}
