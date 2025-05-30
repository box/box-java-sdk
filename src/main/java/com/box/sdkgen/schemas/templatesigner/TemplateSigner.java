package com.box.sdkgen.schemas.templatesigner;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.templatesignerinput.TemplateSignerInput;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

public class TemplateSigner extends SerializableObject {

  protected List<TemplateSignerInput> inputs;

  protected String email;

  @JsonDeserialize(using = TemplateSignerRoleField.TemplateSignerRoleFieldDeserializer.class)
  @JsonSerialize(using = TemplateSignerRoleField.TemplateSignerRoleFieldSerializer.class)
  protected EnumWrapper<TemplateSignerRoleField> role;

  @JsonProperty("is_in_person")
  protected Boolean isInPerson;

  protected Long order;

  @JsonProperty("signer_group_id")
  protected String signerGroupId;

  protected String label;

  @JsonProperty("public_id")
  protected String publicId;

  @JsonProperty("is_password_required")
  protected Boolean isPasswordRequired;

  @JsonProperty("is_phone_number_required")
  protected Boolean isPhoneNumberRequired;

  @JsonProperty("login_required")
  protected Boolean loginRequired;

  public TemplateSigner() {
    super();
  }

  protected TemplateSigner(TemplateSignerBuilder builder) {
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

  public static class TemplateSignerBuilder {

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

    public TemplateSignerBuilder inputs(List<TemplateSignerInput> inputs) {
      this.inputs = inputs;
      return this;
    }

    public TemplateSignerBuilder email(String email) {
      this.email = email;
      return this;
    }

    public TemplateSignerBuilder role(TemplateSignerRoleField role) {
      this.role = new EnumWrapper<TemplateSignerRoleField>(role);
      return this;
    }

    public TemplateSignerBuilder role(EnumWrapper<TemplateSignerRoleField> role) {
      this.role = role;
      return this;
    }

    public TemplateSignerBuilder isInPerson(Boolean isInPerson) {
      this.isInPerson = isInPerson;
      return this;
    }

    public TemplateSignerBuilder order(Long order) {
      this.order = order;
      return this;
    }

    public TemplateSignerBuilder signerGroupId(String signerGroupId) {
      this.signerGroupId = signerGroupId;
      return this;
    }

    public TemplateSignerBuilder label(String label) {
      this.label = label;
      return this;
    }

    public TemplateSignerBuilder publicId(String publicId) {
      this.publicId = publicId;
      return this;
    }

    public TemplateSignerBuilder isPasswordRequired(Boolean isPasswordRequired) {
      this.isPasswordRequired = isPasswordRequired;
      return this;
    }

    public TemplateSignerBuilder isPhoneNumberRequired(Boolean isPhoneNumberRequired) {
      this.isPhoneNumberRequired = isPhoneNumberRequired;
      return this;
    }

    public TemplateSignerBuilder loginRequired(Boolean loginRequired) {
      this.loginRequired = loginRequired;
      return this;
    }

    public TemplateSigner build() {
      return new TemplateSigner(this);
    }
  }
}
