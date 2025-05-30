package com.box.sdkgen.schemas.signrequestcreatesigner;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class SignRequestCreateSigner extends SerializableObject {

  protected String email;

  @JsonDeserialize(
      using = SignRequestCreateSignerRoleField.SignRequestCreateSignerRoleFieldDeserializer.class)
  @JsonSerialize(
      using = SignRequestCreateSignerRoleField.SignRequestCreateSignerRoleFieldSerializer.class)
  protected EnumWrapper<SignRequestCreateSignerRoleField> role;

  @JsonProperty("is_in_person")
  protected Boolean isInPerson;

  protected Long order;

  @JsonProperty("embed_url_external_user_id")
  protected String embedUrlExternalUserId;

  @JsonProperty("redirect_url")
  protected String redirectUrl;

  @JsonProperty("declined_redirect_url")
  protected String declinedRedirectUrl;

  @JsonProperty("login_required")
  protected Boolean loginRequired;

  @JsonProperty("verification_phone_number")
  protected String verificationPhoneNumber;

  protected String password;

  @JsonProperty("signer_group_id")
  protected String signerGroupId;

  @JsonProperty("suppress_notifications")
  protected Boolean suppressNotifications;

  public SignRequestCreateSigner() {
    super();
  }

  protected SignRequestCreateSigner(SignRequestCreateSignerBuilder builder) {
    super();
    this.email = builder.email;
    this.role = builder.role;
    this.isInPerson = builder.isInPerson;
    this.order = builder.order;
    this.embedUrlExternalUserId = builder.embedUrlExternalUserId;
    this.redirectUrl = builder.redirectUrl;
    this.declinedRedirectUrl = builder.declinedRedirectUrl;
    this.loginRequired = builder.loginRequired;
    this.verificationPhoneNumber = builder.verificationPhoneNumber;
    this.password = builder.password;
    this.signerGroupId = builder.signerGroupId;
    this.suppressNotifications = builder.suppressNotifications;
  }

  public String getEmail() {
    return email;
  }

  public EnumWrapper<SignRequestCreateSignerRoleField> getRole() {
    return role;
  }

  public Boolean getIsInPerson() {
    return isInPerson;
  }

  public Long getOrder() {
    return order;
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

  public Boolean getLoginRequired() {
    return loginRequired;
  }

  public String getVerificationPhoneNumber() {
    return verificationPhoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public String getSignerGroupId() {
    return signerGroupId;
  }

  public Boolean getSuppressNotifications() {
    return suppressNotifications;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignRequestCreateSigner casted = (SignRequestCreateSigner) o;
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
        && Objects.equals(suppressNotifications, casted.suppressNotifications);
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
        suppressNotifications);
  }

  @Override
  public String toString() {
    return "SignRequestCreateSigner{"
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
        + "}";
  }

  public static class SignRequestCreateSignerBuilder {

    protected String email;

    protected EnumWrapper<SignRequestCreateSignerRoleField> role;

    protected Boolean isInPerson;

    protected Long order;

    protected String embedUrlExternalUserId;

    protected String redirectUrl;

    protected String declinedRedirectUrl;

    protected Boolean loginRequired;

    protected String verificationPhoneNumber;

    protected String password;

    protected String signerGroupId;

    protected Boolean suppressNotifications;

    public SignRequestCreateSignerBuilder email(String email) {
      this.email = email;
      return this;
    }

    public SignRequestCreateSignerBuilder role(SignRequestCreateSignerRoleField role) {
      this.role = new EnumWrapper<SignRequestCreateSignerRoleField>(role);
      return this;
    }

    public SignRequestCreateSignerBuilder role(EnumWrapper<SignRequestCreateSignerRoleField> role) {
      this.role = role;
      return this;
    }

    public SignRequestCreateSignerBuilder isInPerson(Boolean isInPerson) {
      this.isInPerson = isInPerson;
      return this;
    }

    public SignRequestCreateSignerBuilder order(Long order) {
      this.order = order;
      return this;
    }

    public SignRequestCreateSignerBuilder embedUrlExternalUserId(String embedUrlExternalUserId) {
      this.embedUrlExternalUserId = embedUrlExternalUserId;
      return this;
    }

    public SignRequestCreateSignerBuilder redirectUrl(String redirectUrl) {
      this.redirectUrl = redirectUrl;
      return this;
    }

    public SignRequestCreateSignerBuilder declinedRedirectUrl(String declinedRedirectUrl) {
      this.declinedRedirectUrl = declinedRedirectUrl;
      return this;
    }

    public SignRequestCreateSignerBuilder loginRequired(Boolean loginRequired) {
      this.loginRequired = loginRequired;
      return this;
    }

    public SignRequestCreateSignerBuilder verificationPhoneNumber(String verificationPhoneNumber) {
      this.verificationPhoneNumber = verificationPhoneNumber;
      return this;
    }

    public SignRequestCreateSignerBuilder password(String password) {
      this.password = password;
      return this;
    }

    public SignRequestCreateSignerBuilder signerGroupId(String signerGroupId) {
      this.signerGroupId = signerGroupId;
      return this;
    }

    public SignRequestCreateSignerBuilder suppressNotifications(Boolean suppressNotifications) {
      this.suppressNotifications = suppressNotifications;
      return this;
    }

    public SignRequestCreateSigner build() {
      return new SignRequestCreateSigner(this);
    }
  }
}
