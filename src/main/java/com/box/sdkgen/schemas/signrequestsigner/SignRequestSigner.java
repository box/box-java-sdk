package com.box.sdkgen.schemas.signrequestsigner;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.schemas.signrequestcreatesigner.SignRequestCreateSigner;
import com.box.sdkgen.schemas.signrequestcreatesigner.SignRequestCreateSignerRoleField;
import com.box.sdkgen.schemas.signrequestsignerinput.SignRequestSignerInput;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class SignRequestSigner extends SignRequestCreateSigner {

  @JsonProperty("has_viewed_document")
  protected Boolean hasViewedDocument;

  @JsonProperty("signer_decision")
  @Nullable
  protected SignRequestSignerSignerDecisionField signerDecision;

  protected List<SignRequestSignerInput> inputs;

  @JsonProperty("embed_url")
  @Nullable
  protected String embedUrl;

  @JsonProperty("iframeable_embed_url")
  @Nullable
  protected String iframeableEmbedUrl;

  public SignRequestSigner() {
    super();
  }

  protected SignRequestSigner(Builder builder) {
    super(builder);
    this.hasViewedDocument = builder.hasViewedDocument;
    this.signerDecision = builder.signerDecision;
    this.inputs = builder.inputs;
    this.embedUrl = builder.embedUrl;
    this.iframeableEmbedUrl = builder.iframeableEmbedUrl;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends SignRequestCreateSigner.Builder {

    protected Boolean hasViewedDocument;

    protected SignRequestSignerSignerDecisionField signerDecision;

    protected List<SignRequestSignerInput> inputs;

    protected String embedUrl;

    protected String iframeableEmbedUrl;

    public Builder hasViewedDocument(Boolean hasViewedDocument) {
      this.hasViewedDocument = hasViewedDocument;
      return this;
    }

    public Builder signerDecision(SignRequestSignerSignerDecisionField signerDecision) {
      this.signerDecision = signerDecision;
      this.markNullableFieldAsSet("signer_decision");
      return this;
    }

    public Builder inputs(List<SignRequestSignerInput> inputs) {
      this.inputs = inputs;
      return this;
    }

    public Builder embedUrl(String embedUrl) {
      this.embedUrl = embedUrl;
      this.markNullableFieldAsSet("embed_url");
      return this;
    }

    public Builder iframeableEmbedUrl(String iframeableEmbedUrl) {
      this.iframeableEmbedUrl = iframeableEmbedUrl;
      this.markNullableFieldAsSet("iframeable_embed_url");
      return this;
    }

    @Override
    public Builder email(String email) {
      this.email = email;
      this.markNullableFieldAsSet("email");
      return this;
    }

    @Override
    public Builder role(SignRequestCreateSignerRoleField role) {
      this.role = new EnumWrapper<SignRequestCreateSignerRoleField>(role);
      return this;
    }

    @Override
    public Builder role(EnumWrapper<SignRequestCreateSignerRoleField> role) {
      this.role = role;
      return this;
    }

    @Override
    public Builder isInPerson(Boolean isInPerson) {
      this.isInPerson = isInPerson;
      return this;
    }

    @Override
    public Builder order(Long order) {
      this.order = order;
      return this;
    }

    @Override
    public Builder embedUrlExternalUserId(String embedUrlExternalUserId) {
      this.embedUrlExternalUserId = embedUrlExternalUserId;
      this.markNullableFieldAsSet("embed_url_external_user_id");
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
    public Builder loginRequired(Boolean loginRequired) {
      this.loginRequired = loginRequired;
      this.markNullableFieldAsSet("login_required");
      return this;
    }

    @Override
    public Builder verificationPhoneNumber(String verificationPhoneNumber) {
      this.verificationPhoneNumber = verificationPhoneNumber;
      this.markNullableFieldAsSet("verification_phone_number");
      return this;
    }

    @Override
    public Builder password(String password) {
      this.password = password;
      this.markNullableFieldAsSet("password");
      return this;
    }

    @Override
    public Builder signerGroupId(String signerGroupId) {
      this.signerGroupId = signerGroupId;
      this.markNullableFieldAsSet("signer_group_id");
      return this;
    }

    @Override
    public Builder suppressNotifications(Boolean suppressNotifications) {
      this.suppressNotifications = suppressNotifications;
      this.markNullableFieldAsSet("suppress_notifications");
      return this;
    }

    public SignRequestSigner build() {
      return new SignRequestSigner(this);
    }
  }
}
