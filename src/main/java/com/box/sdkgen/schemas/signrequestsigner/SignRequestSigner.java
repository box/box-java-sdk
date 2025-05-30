package com.box.sdkgen.schemas.signrequestsigner;

import com.box.sdkgen.schemas.signrequestcreatesigner.SignRequestCreateSigner;
import com.box.sdkgen.schemas.signrequestcreatesigner.SignRequestCreateSignerRoleField;
import com.box.sdkgen.schemas.signrequestsignerinput.SignRequestSignerInput;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class SignRequestSigner extends SignRequestCreateSigner {

  @JsonProperty("has_viewed_document")
  protected Boolean hasViewedDocument;

  @JsonProperty("signer_decision")
  protected SignRequestSignerSignerDecisionField signerDecision;

  protected List<SignRequestSignerInput> inputs;

  @JsonProperty("embed_url")
  protected String embedUrl;

  @JsonProperty("iframeable_embed_url")
  protected String iframeableEmbedUrl;

  public SignRequestSigner() {
    super();
  }

  protected SignRequestSigner(SignRequestSignerBuilder builder) {
    super(builder);
    this.hasViewedDocument = builder.hasViewedDocument;
    this.signerDecision = builder.signerDecision;
    this.inputs = builder.inputs;
    this.embedUrl = builder.embedUrl;
    this.iframeableEmbedUrl = builder.iframeableEmbedUrl;
  }

  public Boolean getHasViewedDocument() {
    return hasViewedDocument;
  }

  public SignRequestSignerSignerDecisionField getSignerDecision() {
    return signerDecision;
  }

  public List<SignRequestSignerInput> getInputs() {
    return inputs;
  }

  public String getEmbedUrl() {
    return embedUrl;
  }

  public String getIframeableEmbedUrl() {
    return iframeableEmbedUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignRequestSigner casted = (SignRequestSigner) o;
    return Objects.equals(email, casted.email)
        && Objects.equals(role, casted.role)
        && Objects.equals(isInPerson, casted.isInPerson)
        && Objects.equals(order, casted.order)
        && Objects.equals(embedUrlExternalUserId, casted.embedUrlExternalUserId)
        && Objects.equals(redirectUrl, casted.redirectUrl)
        && Objects.equals(declinedRedirectUrl, casted.declinedRedirectUrl)
        && Objects.equals(loginRequired, casted.loginRequired)
        && Objects.equals(verificationPhoneNumber, casted.verificationPhoneNumber)
        && Objects.equals(password, casted.password)
        && Objects.equals(signerGroupId, casted.signerGroupId)
        && Objects.equals(suppressNotifications, casted.suppressNotifications)
        && Objects.equals(hasViewedDocument, casted.hasViewedDocument)
        && Objects.equals(signerDecision, casted.signerDecision)
        && Objects.equals(inputs, casted.inputs)
        && Objects.equals(embedUrl, casted.embedUrl)
        && Objects.equals(iframeableEmbedUrl, casted.iframeableEmbedUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        email,
        role,
        isInPerson,
        order,
        embedUrlExternalUserId,
        redirectUrl,
        declinedRedirectUrl,
        loginRequired,
        verificationPhoneNumber,
        password,
        signerGroupId,
        suppressNotifications,
        hasViewedDocument,
        signerDecision,
        inputs,
        embedUrl,
        iframeableEmbedUrl);
  }

  @Override
  public String toString() {
    return "SignRequestSigner{"
        + "email='"
        + email
        + '\''
        + ", "
        + "role='"
        + role
        + '\''
        + ", "
        + "isInPerson='"
        + isInPerson
        + '\''
        + ", "
        + "order='"
        + order
        + '\''
        + ", "
        + "embedUrlExternalUserId='"
        + embedUrlExternalUserId
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
        + "loginRequired='"
        + loginRequired
        + '\''
        + ", "
        + "verificationPhoneNumber='"
        + verificationPhoneNumber
        + '\''
        + ", "
        + "password='"
        + password
        + '\''
        + ", "
        + "signerGroupId='"
        + signerGroupId
        + '\''
        + ", "
        + "suppressNotifications='"
        + suppressNotifications
        + '\''
        + ", "
        + "hasViewedDocument='"
        + hasViewedDocument
        + '\''
        + ", "
        + "signerDecision='"
        + signerDecision
        + '\''
        + ", "
        + "inputs='"
        + inputs
        + '\''
        + ", "
        + "embedUrl='"
        + embedUrl
        + '\''
        + ", "
        + "iframeableEmbedUrl='"
        + iframeableEmbedUrl
        + '\''
        + "}";
  }

  public static class SignRequestSignerBuilder extends SignRequestCreateSignerBuilder {

    protected Boolean hasViewedDocument;

    protected SignRequestSignerSignerDecisionField signerDecision;

    protected List<SignRequestSignerInput> inputs;

    protected String embedUrl;

    protected String iframeableEmbedUrl;

    public SignRequestSignerBuilder hasViewedDocument(Boolean hasViewedDocument) {
      this.hasViewedDocument = hasViewedDocument;
      return this;
    }

    public SignRequestSignerBuilder signerDecision(
        SignRequestSignerSignerDecisionField signerDecision) {
      this.signerDecision = signerDecision;
      return this;
    }

    public SignRequestSignerBuilder inputs(List<SignRequestSignerInput> inputs) {
      this.inputs = inputs;
      return this;
    }

    public SignRequestSignerBuilder embedUrl(String embedUrl) {
      this.embedUrl = embedUrl;
      return this;
    }

    public SignRequestSignerBuilder iframeableEmbedUrl(String iframeableEmbedUrl) {
      this.iframeableEmbedUrl = iframeableEmbedUrl;
      return this;
    }

    @Override
    public SignRequestSignerBuilder email(String email) {
      this.email = email;
      return this;
    }

    @Override
    public SignRequestSignerBuilder role(SignRequestCreateSignerRoleField role) {
      this.role = new EnumWrapper<SignRequestCreateSignerRoleField>(role);
      return this;
    }

    @Override
    public SignRequestSignerBuilder role(EnumWrapper<SignRequestCreateSignerRoleField> role) {
      this.role = role;
      return this;
    }

    @Override
    public SignRequestSignerBuilder isInPerson(Boolean isInPerson) {
      this.isInPerson = isInPerson;
      return this;
    }

    @Override
    public SignRequestSignerBuilder order(Long order) {
      this.order = order;
      return this;
    }

    @Override
    public SignRequestSignerBuilder embedUrlExternalUserId(String embedUrlExternalUserId) {
      this.embedUrlExternalUserId = embedUrlExternalUserId;
      return this;
    }

    @Override
    public SignRequestSignerBuilder redirectUrl(String redirectUrl) {
      this.redirectUrl = redirectUrl;
      return this;
    }

    @Override
    public SignRequestSignerBuilder declinedRedirectUrl(String declinedRedirectUrl) {
      this.declinedRedirectUrl = declinedRedirectUrl;
      return this;
    }

    @Override
    public SignRequestSignerBuilder loginRequired(Boolean loginRequired) {
      this.loginRequired = loginRequired;
      return this;
    }

    @Override
    public SignRequestSignerBuilder verificationPhoneNumber(String verificationPhoneNumber) {
      this.verificationPhoneNumber = verificationPhoneNumber;
      return this;
    }

    @Override
    public SignRequestSignerBuilder password(String password) {
      this.password = password;
      return this;
    }

    @Override
    public SignRequestSignerBuilder signerGroupId(String signerGroupId) {
      this.signerGroupId = signerGroupId;
      return this;
    }

    @Override
    public SignRequestSignerBuilder suppressNotifications(Boolean suppressNotifications) {
      this.suppressNotifications = suppressNotifications;
      return this;
    }

    public SignRequestSigner build() {
      return new SignRequestSigner(this);
    }
  }
}
