package com.box.sdkgen.schemas.templatesigner;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.templatesignerinput.TemplateSignerInput;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** The schema for a Signer for Templates. */
@JsonFilter("nullablePropertyFilter")
public class TemplateSigner extends SerializableObject {

  protected List<TemplateSignerInput> inputs;

  /** Email address of the signer. */
  @Nullable protected String email;

  /**
   * Defines the role of the signer in the signature request. A role of `signer` needs to sign the
   * document, a role `approver` approves the document and a `final_copy_reader` role only receives
   * the final signed document and signing log.
   */
  @JsonDeserialize(using = TemplateSignerRoleField.TemplateSignerRoleFieldDeserializer.class)
  @JsonSerialize(using = TemplateSignerRoleField.TemplateSignerRoleFieldSerializer.class)
  protected EnumWrapper<TemplateSignerRoleField> role;

  /**
   * Used in combination with an embed URL for a sender. After the sender signs, they will be
   * redirected to the next `in_person` signer.
   */
  @JsonProperty("is_in_person")
  protected Boolean isInPerson;

  /** Order of the signer. */
  protected Long order;

  /**
   * If provided, this value points signers that are assigned the same inputs and belongs to same
   * signer group. A signer group is not a Box Group. It is an entity that belongs to the template
   * itself and can only be used within Box Sign requests created from it.
   */
  @JsonProperty("signer_group_id")
  @Nullable
  protected String signerGroupId;

  /**
   * A placeholder label for the signer set by the template creator to differentiate between
   * signers.
   */
  @Nullable protected String label;

  /** An identifier for the signer. This can be used to identify a signer within the template. */
  @JsonProperty("public_id")
  protected String publicId;

  /**
   * If true for signers with a defined email, the password provided when the template was created
   * is used by default. If true for signers without a specified / defined email, the creator needs
   * to provide a password when using the template.
   */
  @JsonProperty("is_password_required")
  @Nullable
  protected Boolean isPasswordRequired;

  /**
   * If true for signers with a defined email, the phone number provided when the template was
   * created is used by default. If true for signers without a specified / defined email, the
   * template creator needs to provide a phone number when creating a request.
   */
  @JsonProperty("is_phone_number_required")
  @Nullable
  protected Boolean isPhoneNumberRequired;

  /** If true, the signer is required to login to access the document. */
  @JsonProperty("login_required")
  @Nullable
  protected Boolean loginRequired;

  public TemplateSigner() {
    super();
  }

  protected TemplateSigner(Builder builder) {
    super();
    this.inputs = builder.inputs;
    this.email = builder.email;
    this.role = builder.role;
    this.isInPerson = builder.isInPerson;
    this.order = builder.order;
    this.signerGroupId = builder.signerGroupId;
    this.label = builder.label;
    this.publicId = builder.publicId;
    this.isPasswordRequired = builder.isPasswordRequired;
    this.isPhoneNumberRequired = builder.isPhoneNumberRequired;
    this.loginRequired = builder.loginRequired;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<TemplateSignerInput> getInputs() {
    return inputs;
  }

  public String getEmail() {
    return email;
  }

  public EnumWrapper<TemplateSignerRoleField> getRole() {
    return role;
  }

  public Boolean getIsInPerson() {
    return isInPerson;
  }

  public Long getOrder() {
    return order;
  }

  public String getSignerGroupId() {
    return signerGroupId;
  }

  public String getLabel() {
    return label;
  }

  public String getPublicId() {
    return publicId;
  }

  public Boolean getIsPasswordRequired() {
    return isPasswordRequired;
  }

  public Boolean getIsPhoneNumberRequired() {
    return isPhoneNumberRequired;
  }

  public Boolean getLoginRequired() {
    return loginRequired;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemplateSigner casted = (TemplateSigner) o;
    return Objects.equals(inputs, casted.inputs)
        && Objects.equals(email, casted.email)
        && Objects.equals(role, casted.role)
        && Objects.equals(isInPerson, casted.isInPerson)
        && Objects.equals(order, casted.order)
        && Objects.equals(signerGroupId, casted.signerGroupId)
        && Objects.equals(label, casted.label)
        && Objects.equals(publicId, casted.publicId)
        && Objects.equals(isPasswordRequired, casted.isPasswordRequired)
        && Objects.equals(isPhoneNumberRequired, casted.isPhoneNumberRequired)
        && Objects.equals(loginRequired, casted.loginRequired);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        inputs,
        email,
        role,
        isInPerson,
        order,
        signerGroupId,
        label,
        publicId,
        isPasswordRequired,
        isPhoneNumberRequired,
        loginRequired);
  }

  @Override
  public String toString() {
    return "TemplateSigner{"
        + "inputs='"
        + inputs
        + '\''
        + ", "
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
        + "signerGroupId='"
        + signerGroupId
        + '\''
        + ", "
        + "label='"
        + label
        + '\''
        + ", "
        + "publicId='"
        + publicId
        + '\''
        + ", "
        + "isPasswordRequired='"
        + isPasswordRequired
        + '\''
        + ", "
        + "isPhoneNumberRequired='"
        + isPhoneNumberRequired
        + '\''
        + ", "
        + "loginRequired='"
        + loginRequired
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<TemplateSignerInput> inputs;

    protected String email;

    protected EnumWrapper<TemplateSignerRoleField> role;

    protected Boolean isInPerson;

    protected Long order;

    protected String signerGroupId;

    protected String label;

    protected String publicId;

    protected Boolean isPasswordRequired;

    protected Boolean isPhoneNumberRequired;

    protected Boolean loginRequired;

    public Builder inputs(List<TemplateSignerInput> inputs) {
      this.inputs = inputs;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      this.markNullableFieldAsSet("email");
      return this;
    }

    public Builder role(TemplateSignerRoleField role) {
      this.role = new EnumWrapper<TemplateSignerRoleField>(role);
      return this;
    }

    public Builder role(EnumWrapper<TemplateSignerRoleField> role) {
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

    public Builder signerGroupId(String signerGroupId) {
      this.signerGroupId = signerGroupId;
      this.markNullableFieldAsSet("signer_group_id");
      return this;
    }

    public Builder label(String label) {
      this.label = label;
      this.markNullableFieldAsSet("label");
      return this;
    }

    public Builder publicId(String publicId) {
      this.publicId = publicId;
      return this;
    }

    public Builder isPasswordRequired(Boolean isPasswordRequired) {
      this.isPasswordRequired = isPasswordRequired;
      this.markNullableFieldAsSet("is_password_required");
      return this;
    }

    public Builder isPhoneNumberRequired(Boolean isPhoneNumberRequired) {
      this.isPhoneNumberRequired = isPhoneNumberRequired;
      this.markNullableFieldAsSet("is_phone_number_required");
      return this;
    }

    public Builder loginRequired(Boolean loginRequired) {
      this.loginRequired = loginRequired;
      this.markNullableFieldAsSet("login_required");
      return this;
    }

    public TemplateSigner build() {
      return new TemplateSigner(this);
    }
  }
}
