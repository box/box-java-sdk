package com.box.sdkgen.schemas.shieldinformationbarrier;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.enterprisebase.EnterpriseBase;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
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
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  @JsonProperty("created_by")
  protected UserBase createdBy;

  @JsonProperty("updated_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime updatedAt;

  @JsonProperty("updated_by")
  protected UserBase updatedBy;

  @JsonProperty("enabled_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime enabledAt;

  @JsonProperty("enabled_by")
  protected UserBase enabledBy;

  public ShieldInformationBarrier() {
    super();
  }

  protected ShieldInformationBarrier(Builder builder) {
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
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public UserBase getCreatedBy() {
    return createdBy;
  }

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public UserBase getUpdatedBy() {
    return updatedBy;
  }

  public OffsetDateTime getEnabledAt() {
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

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<ShieldInformationBarrierTypeField> type;

    protected EnterpriseBase enterprise;

    protected EnumWrapper<ShieldInformationBarrierStatusField> status;

    protected OffsetDateTime createdAt;

    protected UserBase createdBy;

    protected OffsetDateTime updatedAt;

    protected UserBase updatedBy;

    protected OffsetDateTime enabledAt;

    protected UserBase enabledBy;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(ShieldInformationBarrierTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShieldInformationBarrierTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder enterprise(EnterpriseBase enterprise) {
      this.enterprise = enterprise;
      return this;
    }

    public Builder status(ShieldInformationBarrierStatusField status) {
      this.status = new EnumWrapper<ShieldInformationBarrierStatusField>(status);
      return this;
    }

    public Builder status(EnumWrapper<ShieldInformationBarrierStatusField> status) {
      this.status = status;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder createdBy(UserBase createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder updatedAt(OffsetDateTime updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public Builder updatedBy(UserBase updatedBy) {
      this.updatedBy = updatedBy;
      return this;
    }

    public Builder enabledAt(OffsetDateTime enabledAt) {
      this.enabledAt = enabledAt;
      return this;
    }

    public Builder enabledBy(UserBase enabledBy) {
      this.enabledBy = enabledBy;
      return this;
    }

    public ShieldInformationBarrier build() {
      return new ShieldInformationBarrier(this);
    }
  }
}
