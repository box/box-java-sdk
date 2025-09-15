package com.box.sdkgen.schemas.shieldinformationbarriersegment;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBase;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ShieldInformationBarrierSegment extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using =
          ShieldInformationBarrierSegmentTypeField
              .ShieldInformationBarrierSegmentTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShieldInformationBarrierSegmentTypeField
              .ShieldInformationBarrierSegmentTypeFieldSerializer.class)
  protected EnumWrapper<ShieldInformationBarrierSegmentTypeField> type;

  @JsonProperty("shield_information_barrier")
  protected ShieldInformationBarrierBase shieldInformationBarrier;

  protected String name;

  protected String description;

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

  public ShieldInformationBarrierSegment() {
    super();
  }

  protected ShieldInformationBarrierSegment(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.shieldInformationBarrier = builder.shieldInformationBarrier;
    this.name = builder.name;
    this.description = builder.description;
    this.createdAt = builder.createdAt;
    this.createdBy = builder.createdBy;
    this.updatedAt = builder.updatedAt;
    this.updatedBy = builder.updatedBy;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShieldInformationBarrierSegmentTypeField> getType() {
    return type;
  }

  public ShieldInformationBarrierBase getShieldInformationBarrier() {
    return shieldInformationBarrier;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldInformationBarrierSegment casted = (ShieldInformationBarrierSegment) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(shieldInformationBarrier, casted.shieldInformationBarrier)
        && Objects.equals(name, casted.name)
        && Objects.equals(description, casted.description)
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
        shieldInformationBarrier,
        name,
        description,
        createdAt,
        createdBy,
        updatedAt,
        updatedBy);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierSegment{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "shieldInformationBarrier='"
        + shieldInformationBarrier
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "description='"
        + description
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

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<ShieldInformationBarrierSegmentTypeField> type;

    protected ShieldInformationBarrierBase shieldInformationBarrier;

    protected String name;

    protected String description;

    protected OffsetDateTime createdAt;

    protected UserBase createdBy;

    protected OffsetDateTime updatedAt;

    protected UserBase updatedBy;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(ShieldInformationBarrierSegmentTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierSegmentTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShieldInformationBarrierSegmentTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder shieldInformationBarrier(ShieldInformationBarrierBase shieldInformationBarrier) {
      this.shieldInformationBarrier = shieldInformationBarrier;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
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

    public ShieldInformationBarrierSegment build() {
      return new ShieldInformationBarrierSegment(this);
    }
  }
}
