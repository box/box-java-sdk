package com.box.sdkgen.schemas.termsofservice;

import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.termsofservicebase.TermsOfServiceBase;
import com.box.sdkgen.schemas.termsofservicebase.TermsOfServiceBaseTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TermsOfService extends TermsOfServiceBase {

  @JsonDeserialize(using = TermsOfServiceStatusField.TermsOfServiceStatusFieldDeserializer.class)
  @JsonSerialize(using = TermsOfServiceStatusField.TermsOfServiceStatusFieldSerializer.class)
  protected EnumWrapper<TermsOfServiceStatusField> status;

  protected TermsOfServiceEnterpriseField enterprise;

  @JsonDeserialize(using = TermsOfServiceTosTypeField.TermsOfServiceTosTypeFieldDeserializer.class)
  @JsonSerialize(using = TermsOfServiceTosTypeField.TermsOfServiceTosTypeFieldSerializer.class)
  @JsonProperty("tos_type")
  protected EnumWrapper<TermsOfServiceTosTypeField> tosType;

  protected String text;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date createdAt;

  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date modifiedAt;

  public TermsOfService(@JsonProperty("id") String id) {
    super(id);
  }

  protected TermsOfService(Builder builder) {
    super(builder);
    this.status = builder.status;
    this.enterprise = builder.enterprise;
    this.tosType = builder.tosType;
    this.text = builder.text;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<TermsOfServiceStatusField> getStatus() {
    return status;
  }

  public TermsOfServiceEnterpriseField getEnterprise() {
    return enterprise;
  }

  public EnumWrapper<TermsOfServiceTosTypeField> getTosType() {
    return tosType;
  }

  public String getText() {
    return text;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getModifiedAt() {
    return modifiedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TermsOfService casted = (TermsOfService) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(status, casted.status)
        && Objects.equals(enterprise, casted.enterprise)
        && Objects.equals(tosType, casted.tosType)
        && Objects.equals(text, casted.text)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, status, enterprise, tosType, text, createdAt, modifiedAt);
  }

  @Override
  public String toString() {
    return "TermsOfService{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "enterprise='"
        + enterprise
        + '\''
        + ", "
        + "tosType='"
        + tosType
        + '\''
        + ", "
        + "text='"
        + text
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "modifiedAt='"
        + modifiedAt
        + '\''
        + "}";
  }

  public static class Builder extends TermsOfServiceBase.Builder {

    protected EnumWrapper<TermsOfServiceStatusField> status;

    protected TermsOfServiceEnterpriseField enterprise;

    protected EnumWrapper<TermsOfServiceTosTypeField> tosType;

    protected String text;

    protected Date createdAt;

    protected Date modifiedAt;

    public Builder(String id) {
      super(id);
    }

    public Builder status(TermsOfServiceStatusField status) {
      this.status = new EnumWrapper<TermsOfServiceStatusField>(status);
      return this;
    }

    public Builder status(EnumWrapper<TermsOfServiceStatusField> status) {
      this.status = status;
      return this;
    }

    public Builder enterprise(TermsOfServiceEnterpriseField enterprise) {
      this.enterprise = enterprise;
      return this;
    }

    public Builder tosType(TermsOfServiceTosTypeField tosType) {
      this.tosType = new EnumWrapper<TermsOfServiceTosTypeField>(tosType);
      return this;
    }

    public Builder tosType(EnumWrapper<TermsOfServiceTosTypeField> tosType) {
      this.tosType = tosType;
      return this;
    }

    public Builder text(String text) {
      this.text = text;
      return this;
    }

    public Builder createdAt(Date createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder modifiedAt(Date modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    @Override
    public Builder type(TermsOfServiceBaseTypeField type) {
      this.type = new EnumWrapper<TermsOfServiceBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<TermsOfServiceBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public TermsOfService build() {
      return new TermsOfService(this);
    }
  }
}
