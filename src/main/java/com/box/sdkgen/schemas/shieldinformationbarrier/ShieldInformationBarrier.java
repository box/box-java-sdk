package com.box.sdkgen.schemas.shieldinformationbarrier;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.enterprisebase.EnterpriseBase;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class ShieldInformationBarrier extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using = ShieldInformationBarrierTypeField.ShieldInformationBarrierTypeFieldDeserializer.class)
  @JsonSerialize(
      using = ShieldInformationBarrierTypeField.ShieldInformationBarrierTypeFieldSerializer.class)
  protected EnumWrapper<ShieldInformationBarrierTypeField> type;

  protected EnterpriseBase enterprise;

  @JsonDeserialize(
      using =
          ShieldInformationBarrierStatusField.ShieldInformationBarrierStatusFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShieldInformationBarrierStatusField.ShieldInformationBarrierStatusFieldSerializer.class)
  protected EnumWrapper<ShieldInformationBarrierStatusField> status;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("created_by")
  protected UserBase createdBy;

  @JsonProperty("updated_at")
  protected String updatedAt;

  @JsonProperty("updated_by")
  protected UserBase updatedBy;

  @JsonProperty("enabled_at")
  protected String enabledAt;

  @JsonProperty("enabled_by")
  protected UserBase enabledBy;

  public ShieldInformationBarrier() {
    super();
  }

  protected ShieldInformationBarrier(ShieldInformationBarrierBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.enterprise = builder.enterprise;
    this.status = builder.status;
    this.createdAt = builder.createdAt;
    this.createdBy = builder.createdBy;
    this.updatedAt = builder.updatedAt;
    this.updatedBy = builder.updatedBy;
    this.enabledAt = builder.enabledAt;
    this.enabledBy = builder.enabledBy;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShieldInformationBarrierTypeField> getType() {
    return type;
  }

  public EnterpriseBase getEnterprise() {
    return enterprise;
  }

  public EnumWrapper<ShieldInformationBarrierStatusField> getStatus() {
    return status;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public UserBase getCreatedBy() {
    return createdBy;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public UserBase getUpdatedBy() {
    return updatedBy;
  }

  public String getEnabledAt() {
    return enabledAt;
  }

  public UserBase getEnabledBy() {
    return enabledBy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldInformationBarrier casted = (ShieldInformationBarrier) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(enterprise, casted.enterprise)
        && Objects.equals(status, casted.status)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(updatedAt, casted.updatedAt)
        && Objects.equals(updatedBy, casted.updatedBy)
        && Objects.equals(enabledAt, casted.enabledAt)
        && Objects.equals(enabledBy, casted.enabledBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        enterprise,
        status,
        createdAt,
        createdBy,
        updatedAt,
        updatedBy,
        enabledAt,
        enabledBy);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrier{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "enterprise='"
        + enterprise
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "updatedAt='"
        + updatedAt
        + '\''
        + ", "
        + "updatedBy='"
        + updatedBy
        + '\''
        + ", "
        + "enabledAt='"
        + enabledAt
        + '\''
        + ", "
        + "enabledBy='"
        + enabledBy
        + '\''
        + "}";
  }

  public static class ShieldInformationBarrierBuilder {

    protected String id;

    protected EnumWrapper<ShieldInformationBarrierTypeField> type;

    protected EnterpriseBase enterprise;

    protected EnumWrapper<ShieldInformationBarrierStatusField> status;

    protected String createdAt;

    protected UserBase createdBy;

    protected String updatedAt;

    protected UserBase updatedBy;

    protected String enabledAt;

    protected UserBase enabledBy;

    public ShieldInformationBarrierBuilder id(String id) {
      this.id = id;
      return this;
    }

    public ShieldInformationBarrierBuilder type(ShieldInformationBarrierTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierTypeField>(type);
      return this;
    }

    public ShieldInformationBarrierBuilder type(
        EnumWrapper<ShieldInformationBarrierTypeField> type) {
      this.type = type;
      return this;
    }

    public ShieldInformationBarrierBuilder enterprise(EnterpriseBase enterprise) {
      this.enterprise = enterprise;
      return this;
    }

    public ShieldInformationBarrierBuilder status(ShieldInformationBarrierStatusField status) {
      this.status = new EnumWrapper<ShieldInformationBarrierStatusField>(status);
      return this;
    }

    public ShieldInformationBarrierBuilder status(
        EnumWrapper<ShieldInformationBarrierStatusField> status) {
      this.status = status;
      return this;
    }

    public ShieldInformationBarrierBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public ShieldInformationBarrierBuilder createdBy(UserBase createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public ShieldInformationBarrierBuilder updatedAt(String updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public ShieldInformationBarrierBuilder updatedBy(UserBase updatedBy) {
      this.updatedBy = updatedBy;
      return this;
    }

    public ShieldInformationBarrierBuilder enabledAt(String enabledAt) {
      this.enabledAt = enabledAt;
      return this;
    }

    public ShieldInformationBarrierBuilder enabledBy(UserBase enabledBy) {
      this.enabledBy = enabledBy;
      return this;
    }

    public ShieldInformationBarrier build() {
      return new ShieldInformationBarrier(this);
    }
  }
}
