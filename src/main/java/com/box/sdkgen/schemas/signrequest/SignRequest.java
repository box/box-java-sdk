package com.box.sdkgen.schemas.signrequest;

import com.box.sdkgen.schemas.filebase.FileBase;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.signrequestbase.SignRequestBase;
import com.box.sdkgen.schemas.signrequestprefilltag.SignRequestPrefillTag;
import com.box.sdkgen.schemas.signrequestsigner.SignRequestSigner;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

public class SignRequest extends SignRequestBase {

  @JsonDeserialize(using = SignRequestTypeField.SignRequestTypeFieldDeserializer.class)
  @JsonSerialize(using = SignRequestTypeField.SignRequestTypeFieldSerializer.class)
  protected EnumWrapper<SignRequestTypeField> type;

  @JsonProperty("source_files")
  protected List<FileBase> sourceFiles;

  protected List<SignRequestSigner> signers;

  @JsonProperty("signature_color")
  protected String signatureColor;

  protected String id;

  @JsonProperty("prepare_url")
  protected String prepareUrl;

  @JsonProperty("signing_log")
  protected FileMini signingLog;

  @JsonDeserialize(using = SignRequestStatusField.SignRequestStatusFieldDeserializer.class)
  @JsonSerialize(using = SignRequestStatusField.SignRequestStatusFieldSerializer.class)
  protected EnumWrapper<SignRequestStatusField> status;

  @JsonProperty("sign_files")
  protected SignRequestSignFilesField signFiles;

  @JsonProperty("auto_expire_at")
  protected String autoExpireAt;

  @JsonProperty("parent_folder")
  protected FolderMini parentFolder;

  @JsonProperty("collaborator_level")
  protected String collaboratorLevel;

  @JsonProperty("sender_email")
  protected String senderEmail;

  @JsonProperty("sender_id")
  protected Long senderId;

  public SignRequest() {
    super();
  }

  protected SignRequest(SignRequestBuilder builder) {
    super(builder);
    this.type = builder.type;
    this.sourceFiles = builder.sourceFiles;
    this.signers = builder.signers;
    this.signatureColor = builder.signatureColor;
    this.id = builder.id;
    this.prepareUrl = builder.prepareUrl;
    this.signingLog = builder.signingLog;
    this.status = builder.status;
    this.signFiles = builder.signFiles;
    this.autoExpireAt = builder.autoExpireAt;
    this.parentFolder = builder.parentFolder;
    this.collaboratorLevel = builder.collaboratorLevel;
    this.senderEmail = builder.senderEmail;
    this.senderId = builder.senderId;
  }

  public EnumWrapper<SignRequestTypeField> getType() {
    return type;
  }

  public List<FileBase> getSourceFiles() {
    return sourceFiles;
  }

  public List<SignRequestSigner> getSigners() {
    return signers;
  }

  public String getSignatureColor() {
    return signatureColor;
  }

  public String getId() {
    return id;
  }

  public String getPrepareUrl() {
    return prepareUrl;
  }

  public FileMini getSigningLog() {
    return signingLog;
  }

  public EnumWrapper<SignRequestStatusField> getStatus() {
    return status;
  }

  public SignRequestSignFilesField getSignFiles() {
    return signFiles;
  }

  public String getAutoExpireAt() {
    return autoExpireAt;
  }

  public FolderMini getParentFolder() {
    return parentFolder;
  }

  public String getCollaboratorLevel() {
    return collaboratorLevel;
  }

  public String getSenderEmail() {
    return senderEmail;
  }

  public Long getSenderId() {
    return senderId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignRequest casted = (SignRequest) o;
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
        && Objects.equals(type, casted.type)
        && Objects.equals(sourceFiles, casted.sourceFiles)
        && Objects.equals(signers, casted.signers)
        && Objects.equals(signatureColor, casted.signatureColor)
        && Objects.equals(id, casted.id)
        && Objects.equals(prepareUrl, casted.prepareUrl)
        && Objects.equals(signingLog, casted.signingLog)
        && Objects.equals(status, casted.status)
        && Objects.equals(signFiles, casted.signFiles)
        && Objects.equals(autoExpireAt, casted.autoExpireAt)
        && Objects.equals(parentFolder, casted.parentFolder)
        && Objects.equals(collaboratorLevel, casted.collaboratorLevel)
        && Objects.equals(senderEmail, casted.senderEmail)
        && Objects.equals(senderId, casted.senderId);
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
        type,
        sourceFiles,
        signers,
        signatureColor,
        id,
        prepareUrl,
        signingLog,
        status,
        signFiles,
        autoExpireAt,
        parentFolder,
        collaboratorLevel,
        senderEmail,
        senderId);
  }

  @Override
  public String toString() {
    return "SignRequest{"
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
        + "type='"
        + type
        + '\''
        + ", "
        + "sourceFiles='"
        + sourceFiles
        + '\''
        + ", "
        + "signers='"
        + signers
        + '\''
        + ", "
        + "signatureColor='"
        + signatureColor
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "prepareUrl='"
        + prepareUrl
        + '\''
        + ", "
        + "signingLog='"
        + signingLog
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "signFiles='"
        + signFiles
        + '\''
        + ", "
        + "autoExpireAt='"
        + autoExpireAt
        + '\''
        + ", "
        + "parentFolder='"
        + parentFolder
        + '\''
        + ", "
        + "collaboratorLevel='"
        + collaboratorLevel
        + '\''
        + ", "
        + "senderEmail='"
        + senderEmail
        + '\''
        + ", "
        + "senderId='"
        + senderId
        + '\''
        + "}";
  }

  public static class SignRequestBuilder extends SignRequestBaseBuilder {

    protected EnumWrapper<SignRequestTypeField> type;

    protected List<FileBase> sourceFiles;

    protected List<SignRequestSigner> signers;

    protected String signatureColor;

    protected String id;

    protected String prepareUrl;

    protected FileMini signingLog;

    protected EnumWrapper<SignRequestStatusField> status;

    protected SignRequestSignFilesField signFiles;

    protected String autoExpireAt;

    protected FolderMini parentFolder;

    protected String collaboratorLevel;

    protected String senderEmail;

    protected Long senderId;

    public SignRequestBuilder type(SignRequestTypeField type) {
      this.type = new EnumWrapper<SignRequestTypeField>(type);
      return this;
    }

    public SignRequestBuilder type(EnumWrapper<SignRequestTypeField> type) {
      this.type = type;
      return this;
    }

    public SignRequestBuilder sourceFiles(List<FileBase> sourceFiles) {
      this.sourceFiles = sourceFiles;
      return this;
    }

    public SignRequestBuilder signers(List<SignRequestSigner> signers) {
      this.signers = signers;
      return this;
    }

    public SignRequestBuilder signatureColor(String signatureColor) {
      this.signatureColor = signatureColor;
      return this;
    }

    public SignRequestBuilder id(String id) {
      this.id = id;
      return this;
    }

    public SignRequestBuilder prepareUrl(String prepareUrl) {
      this.prepareUrl = prepareUrl;
      return this;
    }

    public SignRequestBuilder signingLog(FileMini signingLog) {
      this.signingLog = signingLog;
      return this;
    }

    public SignRequestBuilder status(SignRequestStatusField status) {
      this.status = new EnumWrapper<SignRequestStatusField>(status);
      return this;
    }

    public SignRequestBuilder status(EnumWrapper<SignRequestStatusField> status) {
      this.status = status;
      return this;
    }

    public SignRequestBuilder signFiles(SignRequestSignFilesField signFiles) {
      this.signFiles = signFiles;
      return this;
    }

    public SignRequestBuilder autoExpireAt(String autoExpireAt) {
      this.autoExpireAt = autoExpireAt;
      return this;
    }

    public SignRequestBuilder parentFolder(FolderMini parentFolder) {
      this.parentFolder = parentFolder;
      return this;
    }

    public SignRequestBuilder collaboratorLevel(String collaboratorLevel) {
      this.collaboratorLevel = collaboratorLevel;
      return this;
    }

    public SignRequestBuilder senderEmail(String senderEmail) {
      this.senderEmail = senderEmail;
      return this;
    }

    public SignRequestBuilder senderId(Long senderId) {
      this.senderId = senderId;
      return this;
    }

    @Override
    public SignRequestBuilder isDocumentPreparationNeeded(Boolean isDocumentPreparationNeeded) {
      this.isDocumentPreparationNeeded = isDocumentPreparationNeeded;
      return this;
    }

    @Override
    public SignRequestBuilder redirectUrl(String redirectUrl) {
      this.redirectUrl = redirectUrl;
      return this;
    }

    @Override
    public SignRequestBuilder declinedRedirectUrl(String declinedRedirectUrl) {
      this.declinedRedirectUrl = declinedRedirectUrl;
      return this;
    }

    @Override
    public SignRequestBuilder areTextSignaturesEnabled(Boolean areTextSignaturesEnabled) {
      this.areTextSignaturesEnabled = areTextSignaturesEnabled;
      return this;
    }

    @Override
    public SignRequestBuilder emailSubject(String emailSubject) {
      this.emailSubject = emailSubject;
      return this;
    }

    @Override
    public SignRequestBuilder emailMessage(String emailMessage) {
      this.emailMessage = emailMessage;
      return this;
    }

    @Override
    public SignRequestBuilder areRemindersEnabled(Boolean areRemindersEnabled) {
      this.areRemindersEnabled = areRemindersEnabled;
      return this;
    }

    @Override
    public SignRequestBuilder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public SignRequestBuilder prefillTags(List<SignRequestPrefillTag> prefillTags) {
      this.prefillTags = prefillTags;
      return this;
    }

    @Override
    public SignRequestBuilder daysValid(Long daysValid) {
      this.daysValid = daysValid;
      return this;
    }

    @Override
    public SignRequestBuilder externalId(String externalId) {
      this.externalId = externalId;
      return this;
    }

    @Override
    public SignRequestBuilder templateId(String templateId) {
      this.templateId = templateId;
      return this;
    }

    @Override
    public SignRequestBuilder externalSystemName(String externalSystemName) {
      this.externalSystemName = externalSystemName;
      return this;
    }

    public SignRequest build() {
      return new SignRequest(this);
    }
  }
}
