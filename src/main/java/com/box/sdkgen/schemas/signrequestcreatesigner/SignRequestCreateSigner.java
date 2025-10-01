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

/** The schema for a Signer object used in for creating a Box Sign request object. */
@JsonFilter("nullablePropertyFilter")
public class SignRequestCreateSigner extends SerializableObject {

  /**
   * Email address of the signer. The email address of the signer is required when making signature
   * requests, except when using templates that are configured to include emails.
   */
  @Nullable protected String email;

  /**
   * Defines the role of the signer in the signature request. A `signer` must sign the document and
   * an `approver` must approve the document. A `final_copy_reader` only receives the final signed
   * document and signing log.
   */
  @JsonDeserialize(
      using = SignRequestCreateSignerRoleField.SignRequestCreateSignerRoleFieldDeserializer.class)
  @JsonSerialize(
      using = SignRequestCreateSignerRoleField.SignRequestCreateSignerRoleFieldSerializer.class)
  protected EnumWrapper<SignRequestCreateSignerRoleField> role;

  /**
   * Used in combination with an embed URL for a sender. After the sender signs, they are redirected
   * to the next `in_person` signer.
   */
  @JsonProperty("is_in_person")
  protected Boolean isInPerson;

  /** Order of the signer. */
  protected Long order;

  /**
   * User ID for the signer in an external application responsible for authentication when accessing
   * the embed URL.
   */
  @JsonProperty("embed_url_external_user_id")
  @Nullable
  protected String embedUrlExternalUserId;

  /**
   * The URL that a signer will be redirected to after signing a document. Defining this URL
   * overrides default or global redirect URL settings for a specific signer. If no declined
   * redirect URL is specified, this URL will be used for decline actions as well.
   */
  @JsonProperty("redirect_url")
  @Nullable
  protected String redirectUrl;

  /**
   * The URL that a signer will be redirect to after declining to sign a document. Defining this URL
   * overrides default or global declined redirect URL settings for a specific signer.
   */
  @JsonProperty("declined_redirect_url")
  @Nullable
  protected String declinedRedirectUrl;

  /**
   * If set to true, the signer will need to log in to a Box account before signing the request. If
   * the signer does not have an existing account, they will have the option to create a free Box
   * account.
   */
  @JsonProperty("login_required")
  @Nullable
  protected Boolean loginRequired;

  /**
   * If set, this phone number will be used to verify the signer via two-factor authentication
   * before they are able to sign the document. Cannot be selected in combination with
   * `login_required`.
   */
  @JsonProperty("verification_phone_number")
  @Nullable
  protected String verificationPhoneNumber;

  /**
   * If set, the signer is required to enter the password before they are able to sign a document.
   * This field is write only.
   */
  @Nullable protected String password;

  /**
   * If set, signers who have the same value will be assigned to the same input and to the same
   * signer group. A signer group is not a Box Group. It is an entity that belongs to a Sign Request
   * and can only be used/accessed within this Sign Request. A signer group is expected to have more
   * than one signer. If the provided value is only used for one signer, this value will be ignored
   * and request will be handled as it was intended for an individual signer. The value provided can
   * be any string and only used to determine which signers belongs to same group. A successful
   * response will provide a generated UUID value instead for signers in the same signer group.
   */
  @JsonProperty("signer_group_id")
  @Nullable
  protected String signerGroupId;

  /** If true, no emails about the sign request will be sent. */
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
