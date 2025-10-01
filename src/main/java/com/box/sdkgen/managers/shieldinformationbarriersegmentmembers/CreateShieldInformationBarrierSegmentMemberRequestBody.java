package com.box.sdkgen.managers.shieldinformationbarriersegmentmembers;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBase;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateShieldInformationBarrierSegmentMemberRequestBody extends SerializableObject {

  /** A type of the shield barrier segment member. */
  @JsonDeserialize(
      using =
          CreateShieldInformationBarrierSegmentMemberRequestBodyTypeField
              .CreateShieldInformationBarrierSegmentMemberRequestBodyTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateShieldInformationBarrierSegmentMemberRequestBodyTypeField
              .CreateShieldInformationBarrierSegmentMemberRequestBodyTypeFieldSerializer.class)
  protected EnumWrapper<CreateShieldInformationBarrierSegmentMemberRequestBodyTypeField> type;

  @JsonProperty("shield_information_barrier")
  protected ShieldInformationBarrierBase shieldInformationBarrier;

  /** The `type` and `id` of the requested shield information barrier segment. */
  @JsonProperty("shield_information_barrier_segment")
  protected final
  CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentField
      shieldInformationBarrierSegment;

  /** User to which restriction will be applied. */
  protected final UserBase user;

  public CreateShieldInformationBarrierSegmentMemberRequestBody(
      @JsonProperty("shield_information_barrier_segment")
          CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentField
              shieldInformationBarrierSegment,
      @JsonProperty("user") UserBase user) {
    super();
    this.shieldInformationBarrierSegment = shieldInformationBarrierSegment;
    this.user = user;
  }

  protected CreateShieldInformationBarrierSegmentMemberRequestBody(Builder builder) {
    super();
    this.type = builder.type;
    this.shieldInformationBarrier = builder.shieldInformationBarrier;
    this.shieldInformationBarrierSegment = builder.shieldInformationBarrierSegment;
    this.user = builder.user;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<CreateShieldInformationBarrierSegmentMemberRequestBodyTypeField> getType() {
    return type;
  }

  public ShieldInformationBarrierBase getShieldInformationBarrier() {
    return shieldInformationBarrier;
  }

  public CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentField
      getShieldInformationBarrierSegment() {
    return shieldInformationBarrierSegment;
  }

  public UserBase getUser() {
    return user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateShieldInformationBarrierSegmentMemberRequestBody casted =
        (CreateShieldInformationBarrierSegmentMemberRequestBody) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(shieldInformationBarrier, casted.shieldInformationBarrier)
        && Objects.equals(shieldInformationBarrierSegment, casted.shieldInformationBarrierSegment)
        && Objects.equals(user, casted.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, shieldInformationBarrier, shieldInformationBarrierSegment, user);
  }

  @Override
  public String toString() {
    return "CreateShieldInformationBarrierSegmentMemberRequestBody{"
        + "type='"
        + type
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
        + "user='"
        + user
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<CreateShieldInformationBarrierSegmentMemberRequestBodyTypeField> type;

    protected ShieldInformationBarrierBase shieldInformationBarrier;

    protected final
    CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentField
        shieldInformationBarrierSegment;

    protected final UserBase user;

    public Builder(
        CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentField
            shieldInformationBarrierSegment,
        UserBase user) {
      super();
      this.shieldInformationBarrierSegment = shieldInformationBarrierSegment;
      this.user = user;
    }

    public Builder type(CreateShieldInformationBarrierSegmentMemberRequestBodyTypeField type) {
      this.type =
          new EnumWrapper<CreateShieldInformationBarrierSegmentMemberRequestBodyTypeField>(type);
      return this;
    }

    public Builder type(
        EnumWrapper<CreateShieldInformationBarrierSegmentMemberRequestBodyTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder shieldInformationBarrier(ShieldInformationBarrierBase shieldInformationBarrier) {
      this.shieldInformationBarrier = shieldInformationBarrier;
      return this;
    }

    public CreateShieldInformationBarrierSegmentMemberRequestBody build() {
      return new CreateShieldInformationBarrierSegmentMemberRequestBody(this);
    }
  }
}
