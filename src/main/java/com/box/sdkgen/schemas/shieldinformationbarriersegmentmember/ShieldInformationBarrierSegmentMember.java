package com.box.sdkgen.schemas.shieldinformationbarriersegmentmember;

import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBase;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentmemberbase.ShieldInformationBarrierSegmentMemberBaseTypeField;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentmembermini.ShieldInformationBarrierSegmentMemberMini;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ShieldInformationBarrierSegmentMember
    extends ShieldInformationBarrierSegmentMemberMini {

  @JsonProperty("shield_information_barrier")
  protected ShieldInformationBarrierBase shieldInformationBarrier;

  @JsonProperty("shield_information_barrier_segment")
  protected ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentField
      shieldInformationBarrierSegment;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date createdAt;

  @JsonProperty("created_by")
  protected UserBase createdBy;

  @JsonProperty("updated_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date updatedAt;

  @JsonProperty("updated_by")
  protected UserBase updatedBy;

  public ShieldInformationBarrierSegmentMember() {
    super();
  }

  protected ShieldInformationBarrierSegmentMember(Builder builder) {
    super(builder);
    this.shieldInformationBarrier = builder.shieldInformationBarrier;
    this.shieldInformationBarrierSegment = builder.shieldInformationBarrierSegment;
    this.createdAt = builder.createdAt;
    this.createdBy = builder.createdBy;
    this.updatedAt = builder.updatedAt;
    this.updatedBy = builder.updatedBy;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public ShieldInformationBarrierBase getShieldInformationBarrier() {
    return shieldInformationBarrier;
  }

  public ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentField
      getShieldInformationBarrierSegment() {
    return shieldInformationBarrierSegment;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public UserBase getCreatedBy() {
    return createdBy;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public UserBase getUpdatedBy() {
    return updatedBy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldInformationBarrierSegmentMember casted = (ShieldInformationBarrierSegmentMember) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(user, casted.user)
        && Objects.equals(shieldInformationBarrier, casted.shieldInformationBarrier)
        && Objects.equals(shieldInformationBarrierSegment, casted.shieldInformationBarrierSegment)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(updatedAt, casted.updatedAt)
        && Objects.equals(updatedBy, casted.updatedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        user,
        shieldInformationBarrier,
        shieldInformationBarrierSegment,
        createdAt,
        createdBy,
        updatedAt,
        updatedBy);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierSegmentMember{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "user='"
        + user
        + '\''
        + ", "
        + "shieldInformationBarrier='"
        + shieldInformationBarrier
        + '\''
        + ", "
        + "shieldInformationBarrierSegment='"
        + shieldInformationBarrierSegment
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
        + "}";
  }

  public static class Builder extends ShieldInformationBarrierSegmentMemberMini.Builder {

    protected ShieldInformationBarrierBase shieldInformationBarrier;

    protected ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentField
        shieldInformationBarrierSegment;

    protected Date createdAt;

    protected UserBase createdBy;

    protected Date updatedAt;

    protected UserBase updatedBy;

    public Builder shieldInformationBarrier(ShieldInformationBarrierBase shieldInformationBarrier) {
      this.shieldInformationBarrier = shieldInformationBarrier;
      return this;
    }

    public Builder shieldInformationBarrierSegment(
        ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentField
            shieldInformationBarrierSegment) {
      this.shieldInformationBarrierSegment = shieldInformationBarrierSegment;
      return this;
    }

    public Builder createdAt(Date createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder createdBy(UserBase createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder updatedAt(Date updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public Builder updatedBy(UserBase updatedBy) {
      this.updatedBy = updatedBy;
      return this;
    }

    @Override
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    @Override
    public Builder type(ShieldInformationBarrierSegmentMemberBaseTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder user(UserBase user) {
      this.user = user;
      return this;
    }

    public ShieldInformationBarrierSegmentMember build() {
      return new ShieldInformationBarrierSegmentMember(this);
    }
  }
}
