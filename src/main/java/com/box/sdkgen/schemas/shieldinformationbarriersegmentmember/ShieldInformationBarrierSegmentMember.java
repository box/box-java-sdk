package com.box.sdkgen.schemas.shieldinformationbarriersegmentmember;

import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBase;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentmemberbase.ShieldInformationBarrierSegmentMemberBaseTypeField;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentmembermini.ShieldInformationBarrierSegmentMemberMini;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class ShieldInformationBarrierSegmentMember
    extends ShieldInformationBarrierSegmentMemberMini {

  @JsonProperty("shield_information_barrier")
  protected ShieldInformationBarrierBase shieldInformationBarrier;

  @JsonProperty("shield_information_barrier_segment")
  protected ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentField
      shieldInformationBarrierSegment;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("created_by")
  protected UserBase createdBy;

  @JsonProperty("updated_at")
  protected String updatedAt;

  @JsonProperty("updated_by")
  protected UserBase updatedBy;

  public ShieldInformationBarrierSegmentMember() {
    super();
  }

  protected ShieldInformationBarrierSegmentMember(
      ShieldInformationBarrierSegmentMemberBuilder builder) {
    super(builder);
    this.shieldInformationBarrier = builder.shieldInformationBarrier;
    this.shieldInformationBarrierSegment = builder.shieldInformationBarrierSegment;
    this.createdAt = builder.createdAt;
    this.createdBy = builder.createdBy;
    this.updatedAt = builder.updatedAt;
    this.updatedBy = builder.updatedBy;
  }

  public ShieldInformationBarrierBase getShieldInformationBarrier() {
    return shieldInformationBarrier;
  }

  public ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentField
      getShieldInformationBarrierSegment() {
    return shieldInformationBarrierSegment;
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

  public static class ShieldInformationBarrierSegmentMemberBuilder
      extends ShieldInformationBarrierSegmentMemberMiniBuilder {

    protected ShieldInformationBarrierBase shieldInformationBarrier;

    protected ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentField
        shieldInformationBarrierSegment;

    protected String createdAt;

    protected UserBase createdBy;

    protected String updatedAt;

    protected UserBase updatedBy;

    public ShieldInformationBarrierSegmentMemberBuilder shieldInformationBarrier(
        ShieldInformationBarrierBase shieldInformationBarrier) {
      this.shieldInformationBarrier = shieldInformationBarrier;
      return this;
    }

    public ShieldInformationBarrierSegmentMemberBuilder shieldInformationBarrierSegment(
        ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentField
            shieldInformationBarrierSegment) {
      this.shieldInformationBarrierSegment = shieldInformationBarrierSegment;
      return this;
    }

    public ShieldInformationBarrierSegmentMemberBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public ShieldInformationBarrierSegmentMemberBuilder createdBy(UserBase createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public ShieldInformationBarrierSegmentMemberBuilder updatedAt(String updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public ShieldInformationBarrierSegmentMemberBuilder updatedBy(UserBase updatedBy) {
      this.updatedBy = updatedBy;
      return this;
    }

    @Override
    public ShieldInformationBarrierSegmentMemberBuilder id(String id) {
      this.id = id;
      return this;
    }

    @Override
    public ShieldInformationBarrierSegmentMemberBuilder type(
        ShieldInformationBarrierSegmentMemberBaseTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField>(type);
      return this;
    }

    @Override
    public ShieldInformationBarrierSegmentMemberBuilder type(
        EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public ShieldInformationBarrierSegmentMemberBuilder user(UserBase user) {
      this.user = user;
      return this;
    }

    public ShieldInformationBarrierSegmentMember build() {
      return new ShieldInformationBarrierSegmentMember(this);
    }
  }
}
