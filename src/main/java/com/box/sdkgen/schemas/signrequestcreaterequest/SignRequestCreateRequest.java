package com.box.sdkgen.schemas.signrequestcreaterequest;

import com.box.sdkgen.schemas.filebase.FileBase;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.signrequestbase.SignRequestBase;
import com.box.sdkgen.schemas.signrequestcreatesigner.SignRequestCreateSigner;
import com.box.sdkgen.schemas.signrequestprefilltag.SignRequestPrefillTag;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

public class SignRequestCreateRequest extends SignRequestBase {

  @JsonProperty("source_files")
  protected List<FileBase> sourceFiles;

  @JsonDeserialize(
      using =
          SignRequestCreateRequestSignatureColorField
              .SignRequestCreateRequestSignatureColorFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestCreateRequestSignatureColorField
              .SignRequestCreateRequestSignatureColorFieldSerializer.class)
  @JsonProperty("signature_color")
  protected EnumWrapper<SignRequestCreateRequestSignatureColorField> signatureColor;

  protected final List<SignRequestCreateSigner> signers;

  @JsonProperty("parent_folder")
  protected FolderMini parentFolder;

  public SignRequestCreateRequest(@JsonProperty("signers") List<SignRequestCreateSigner> signers) {
    super();
    this.signers = signers;
  }

  protected SignRequestCreateRequest(SignRequestCreateRequestBuilder builder) {
    super(builder);
    this.sourceFiles = builder.sourceFiles;
    this.signatureColor = builder.signatureColor;
    this.signers = builder.signers;
    this.parentFolder = builder.parentFolder;
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

  public static class SignRequestCreateRequestBuilder extends SignRequestBaseBuilder {

    protected List<FileBase> sourceFiles;

    protected EnumWrapper<SignRequestCreateRequestSignatureColorField> signatureColor;

    protected final List<SignRequestCreateSigner> signers;

    protected FolderMini parentFolder;

    public SignRequestCreateRequestBuilder(List<SignRequestCreateSigner> signers) {
      super();
      this.signers = signers;
    }

    public SignRequestCreateRequestBuilder sourceFiles(List<FileBase> sourceFiles) {
      this.sourceFiles = sourceFiles;
      return this;
    }

    public SignRequestCreateRequestBuilder signatureColor(
        SignRequestCreateRequestSignatureColorField signatureColor) {
      this.signatureColor =
          new EnumWrapper<SignRequestCreateRequestSignatureColorField>(signatureColor);
      return this;
    }

    public SignRequestCreateRequestBuilder signatureColor(
        EnumWrapper<SignRequestCreateRequestSignatureColorField> signatureColor) {
      this.signatureColor = signatureColor;
      return this;
    }

    public SignRequestCreateRequestBuilder parentFolder(FolderMini parentFolder) {
      this.parentFolder = parentFolder;
      return this;
    }

    @Override
    public SignRequestCreateRequestBuilder isDocumentPreparationNeeded(
        Boolean isDocumentPreparationNeeded) {
      this.isDocumentPreparationNeeded = isDocumentPreparationNeeded;
      return this;
    }

    @Override
    public SignRequestCreateRequestBuilder redirectUrl(String redirectUrl) {
      this.redirectUrl = redirectUrl;
      return this;
    }

    @Override
    public SignRequestCreateRequestBuilder declinedRedirectUrl(String declinedRedirectUrl) {
      this.declinedRedirectUrl = declinedRedirectUrl;
      return this;
    }

    @Override
    public SignRequestCreateRequestBuilder areTextSignaturesEnabled(
        Boolean areTextSignaturesEnabled) {
      this.areTextSignaturesEnabled = areTextSignaturesEnabled;
      return this;
    }

    @Override
    public SignRequestCreateRequestBuilder emailSubject(String emailSubject) {
      this.emailSubject = emailSubject;
      return this;
    }

    @Override
    public SignRequestCreateRequestBuilder emailMessage(String emailMessage) {
      this.emailMessage = emailMessage;
      return this;
    }

    @Override
    public SignRequestCreateRequestBuilder areRemindersEnabled(Boolean areRemindersEnabled) {
      this.areRemindersEnabled = areRemindersEnabled;
      return this;
    }

    @Override
    public SignRequestCreateRequestBuilder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public SignRequestCreateRequestBuilder prefillTags(List<SignRequestPrefillTag> prefillTags) {
      this.prefillTags = prefillTags;
      return this;
    }

    @Override
    public SignRequestCreateRequestBuilder daysValid(Long daysValid) {
      this.daysValid = daysValid;
      return this;
    }

    @Override
    public SignRequestCreateRequestBuilder externalId(String externalId) {
      this.externalId = externalId;
      return this;
    }

    @Override
    public SignRequestCreateRequestBuilder templateId(String templateId) {
      this.templateId = templateId;
      return this;
    }

    @Override
    public SignRequestCreateRequestBuilder externalSystemName(String externalSystemName) {
      this.externalSystemName = externalSystemName;
      return this;
    }

    public SignRequestCreateRequest build() {
      return new SignRequestCreateRequest(this);
    }
  }
}
