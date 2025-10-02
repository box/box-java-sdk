package com.box.sdkgen.schemas.signrequestcreaterequest;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.schemas.filebase.FileBase;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.signrequestbase.SignRequestBase;
import com.box.sdkgen.schemas.signrequestcreatesigner.SignRequestCreateSigner;
import com.box.sdkgen.schemas.signrequestprefilltag.SignRequestPrefillTag;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** Creates a Box Sign request object. */
@JsonFilter("nullablePropertyFilter")
public class SignRequestCreateRequest extends SignRequestBase {

  /**
   * List of files to create a signing document from. This is currently limited to ten files. Only
   * the ID and type fields are required for each file.
   */
  @JsonProperty("source_files")
  @Nullable
  protected List<FileBase> sourceFiles;

  /** Force a specific color for the signature (blue, black, or red). */
  @JsonDeserialize(
      using =
          SignRequestCreateRequestSignatureColorField
              .SignRequestCreateRequestSignatureColorFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestCreateRequestSignatureColorField
              .SignRequestCreateRequestSignatureColorFieldSerializer.class)
  @JsonProperty("signature_color")
  @Nullable
  protected EnumWrapper<SignRequestCreateRequestSignatureColorField> signatureColor;

  /**
   * Array of signers for the signature request. 35 is the max number of signers permitted.
   *
   * <p>**Note**: It may happen that some signers belong to conflicting
   * [segments](r://shield-information-barrier-segment-member) (user groups). This means that due to
   * the security policies, users are assigned to segments to prevent exchanges or communication
   * that could lead to ethical conflicts. In such a case, an attempt to send the sign request will
   * result in an error.
   *
   * <p>Read more about [segments and ethical
   * walls](https://support.box.com/hc/en-us/articles/9920431507603-Understanding-Information-Barriers#h_01GFVJEHQA06N7XEZ4GCZ9GFAQ).
   */
  protected final List<SignRequestCreateSigner> signers;

  @JsonProperty("parent_folder")
  protected FolderMini parentFolder;

  public SignRequestCreateRequest(@JsonProperty("signers") List<SignRequestCreateSigner> signers) {
    super();
    this.signers = signers;
  }

  protected SignRequestCreateRequest(Builder builder) {
    super(builder);
    this.sourceFiles = builder.sourceFiles;
    this.signatureColor = builder.signatureColor;
    this.signers = builder.signers;
    this.parentFolder = builder.parentFolder;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<FileBase> getSourceFiles() {
    return sourceFiles;
  }

  public EnumWrapper<SignRequestCreateRequestSignatureColorField> getSignatureColor() {
    return signatureColor;
  }

  public List<SignRequestCreateSigner> getSigners() {
    return signers;
  }

  public FolderMini getParentFolder() {
    return parentFolder;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignRequestCreateRequest casted = (SignRequestCreateRequest) o;
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
        && Objects.equals(externalSystemName, casted.externalSystemName)
        && Objects.equals(sourceFiles, casted.sourceFiles)
        && Objects.equals(signatureColor, casted.signatureColor)
        && Objects.equals(signers, casted.signers)
        && Objects.equals(parentFolder, casted.parentFolder);
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
        externalSystemName,
        sourceFiles,
        signatureColor,
        signers,
        parentFolder);
  }

  @Override
  public String toString() {
    return "SignRequestCreateRequest{"
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
        + ", "
        + "sourceFiles='"
        + sourceFiles
        + '\''
        + ", "
        + "signatureColor='"
        + signatureColor
        + '\''
        + ", "
        + "signers='"
        + signers
        + '\''
        + ", "
        + "parentFolder='"
        + parentFolder
        + '\''
        + "}";
  }

  public static class Builder extends SignRequestBase.Builder {

    protected List<FileBase> sourceFiles;

    protected EnumWrapper<SignRequestCreateRequestSignatureColorField> signatureColor;

    protected final List<SignRequestCreateSigner> signers;

    protected FolderMini parentFolder;

    public Builder(List<SignRequestCreateSigner> signers) {
      super();
      this.signers = signers;
    }

    public Builder sourceFiles(List<FileBase> sourceFiles) {
      this.sourceFiles = sourceFiles;
      this.markNullableFieldAsSet("source_files");
      return this;
    }

    public Builder signatureColor(SignRequestCreateRequestSignatureColorField signatureColor) {
      this.signatureColor =
          new EnumWrapper<SignRequestCreateRequestSignatureColorField>(signatureColor);
      this.markNullableFieldAsSet("signature_color");
      return this;
    }

    public Builder signatureColor(
        EnumWrapper<SignRequestCreateRequestSignatureColorField> signatureColor) {
      this.signatureColor = signatureColor;
      this.markNullableFieldAsSet("signature_color");
      return this;
    }

    public Builder parentFolder(FolderMini parentFolder) {
      this.parentFolder = parentFolder;
      return this;
    }

    @Override
    public Builder isDocumentPreparationNeeded(Boolean isDocumentPreparationNeeded) {
      this.isDocumentPreparationNeeded = isDocumentPreparationNeeded;
      return this;
    }

    @Override
    public Builder redirectUrl(String redirectUrl) {
      this.redirectUrl = redirectUrl;
      this.markNullableFieldAsSet("redirect_url");
      return this;
    }

    @Override
    public Builder declinedRedirectUrl(String declinedRedirectUrl) {
      this.declinedRedirectUrl = declinedRedirectUrl;
      this.markNullableFieldAsSet("declined_redirect_url");
      return this;
    }

    @Override
    public Builder areTextSignaturesEnabled(Boolean areTextSignaturesEnabled) {
      this.areTextSignaturesEnabled = areTextSignaturesEnabled;
      return this;
    }

    @Override
    public Builder emailSubject(String emailSubject) {
      this.emailSubject = emailSubject;
      this.markNullableFieldAsSet("email_subject");
      return this;
    }

    @Override
    public Builder emailMessage(String emailMessage) {
      this.emailMessage = emailMessage;
      this.markNullableFieldAsSet("email_message");
      return this;
    }

    @Override
    public Builder areRemindersEnabled(Boolean areRemindersEnabled) {
      this.areRemindersEnabled = areRemindersEnabled;
      return this;
    }

    @Override
    public Builder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public Builder prefillTags(List<SignRequestPrefillTag> prefillTags) {
      this.prefillTags = prefillTags;
      return this;
    }

    @Override
    public Builder daysValid(Long daysValid) {
      this.daysValid = daysValid;
      this.markNullableFieldAsSet("days_valid");
      return this;
    }

    @Override
    public Builder externalId(String externalId) {
      this.externalId = externalId;
      this.markNullableFieldAsSet("external_id");
      return this;
    }

    @Override
    public Builder templateId(String templateId) {
      this.templateId = templateId;
      this.markNullableFieldAsSet("template_id");
      return this;
    }

    @Override
    public Builder externalSystemName(String externalSystemName) {
      this.externalSystemName = externalSystemName;
      this.markNullableFieldAsSet("external_system_name");
      return this;
    }

    public SignRequestCreateRequest build() {
      return new SignRequestCreateRequest(this);
    }
  }
}
