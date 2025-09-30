package com.box.sdkgen.schemas.signrequestcreatesigner;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class SignRequestCreateSigner extends SerializableObject {

  @Nullable protected String email;

  @JsonDeserialize(
      using = SignRequestCreateSignerRoleField.SignRequestCreateSignerRoleFieldDeserializer.class)
  @JsonSerialize(
      using = SignRequestCreateSignerRoleField.SignRequestCreateSignerRoleFieldSerializer.class)
  protected EnumWrapper<SignRequestCreateSignerRoleField> role;

  @JsonProperty("is_in_person")
  protected Boolean isInPerson;

  protected Long order;

  @JsonProperty("embed_url_external_user_id")
  @Nullable
  protected String embedUrlExternalUserId;

  @JsonProperty("redirect_url")
  @Nullable
  protected String redirectUrl;

  @JsonProperty("declined_redirect_url")
  @Nullable
  protected String declinedRedirectUrl;

  @JsonProperty("login_required")
  @Nullable
  protected Boolean loginRequired;

  @JsonProperty("verification_phone_number")
  @Nullable
  protected String verificationPhoneNumber;

  @Nullable protected String password;

  @JsonProperty("signer_group_id")
  @Nullable
  protected String signerGroupId;

  @JsonProperty("suppress_notifications")
  @Nullable
  protected Boolean suppressNotifications;

  public SignRequestCreateSigner() {
    super();
  }

  protected SignRequestCreateSigner(Builder builder) {
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
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

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

    public Builder email(String email) {
      this.email = email;
      this.markNullableFieldAsSet("email");
      return this;
    }

    public Builder role(SignRequestCreateSignerRoleField role) {
      this.role = new EnumWrapper<SignRequestCreateSignerRoleField>(role);
      return this;
    }

    public Builder role(EnumWrapper<SignRequestCreateSignerRoleField> role) {
      this.role = role;
      return this;
    }

    public Builder isInPerson(Boolean isInPerson) {
      this.isInPerson = isInPerson;
      return this;
    }

    public Builder order(Long order) {
      this.order = order;
      return this;
    }

    public Builder embedUrlExternalUserId(String embedUrlExternalUserId) {
      this.embedUrlExternalUserId = embedUrlExternalUserId;
      this.markNullableFieldAsSet("embed_url_external_user_id");
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

    public Builder loginRequired(Boolean loginRequired) {
      this.loginRequired = loginRequired;
      this.markNullableFieldAsSet("login_required");
      return this;
    }

    public Builder verificationPhoneNumber(String verificationPhoneNumber) {
      this.verificationPhoneNumber = verificationPhoneNumber;
      this.markNullableFieldAsSet("verification_phone_number");
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      this.markNullableFieldAsSet("password");
      return this;
    }

    public Builder signerGroupId(String signerGroupId) {
      this.signerGroupId = signerGroupId;
      this.markNullableFieldAsSet("signer_group_id");
      return this;
    }

    public Builder suppressNotifications(Boolean suppressNotifications) {
      this.suppressNotifications = suppressNotifications;
      this.markNullableFieldAsSet("suppress_notifications");
      return this;
    }

    public SignRequestCreateSigner build() {
      return new SignRequestCreateSigner(this);
    }
  }
}
