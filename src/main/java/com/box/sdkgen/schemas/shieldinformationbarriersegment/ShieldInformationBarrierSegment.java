package com.box.sdkgen.schemas.shieldinformationbarriersegment;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBase;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

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
  protected String createdAt;

  @JsonProperty("created_by")
  protected UserBase createdBy;

  @JsonProperty("updated_at")
  protected String updatedAt;

  @JsonProperty("updated_by")
  protected UserBase updatedBy;

  public ShieldInformationBarrierSegment() {
    super();
  }

  protected ShieldInformationBarrierSegment(ShieldInformationBarrierSegmentBuilder builder) {
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

  public static class ShieldInformationBarrierSegmentBuilder {

    protected String id;

    protected EnumWrapper<ShieldInformationBarrierSegmentTypeField> type;

    protected ShieldInformationBarrierBase shieldInformationBarrier;

    protected String name;

    protected String description;

    protected String createdAt;

    protected UserBase createdBy;

    protected String updatedAt;

    protected UserBase updatedBy;

    public ShieldInformationBarrierSegmentBuilder id(String id) {
      this.id = id;
      return this;
    }

    public ShieldInformationBarrierSegmentBuilder type(
        ShieldInformationBarrierSegmentTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierSegmentTypeField>(type);
      return this;
    }

    public ShieldInformationBarrierSegmentBuilder type(
        EnumWrapper<ShieldInformationBarrierSegmentTypeField> type) {
      this.type = type;
      return this;
    }

    public ShieldInformationBarrierSegmentBuilder shieldInformationBarrier(
        ShieldInformationBarrierBase shieldInformationBarrier) {
      this.shieldInformationBarrier = shieldInformationBarrier;
      return this;
    }

    public ShieldInformationBarrierSegmentBuilder name(String name) {
      this.name = name;
      return this;
    }

    public ShieldInformationBarrierSegmentBuilder description(String description) {
      this.description = description;
      return this;
    }

    public ShieldInformationBarrierSegmentBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public ShieldInformationBarrierSegmentBuilder createdBy(UserBase createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public ShieldInformationBarrierSegmentBuilder updatedAt(String updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public ShieldInformationBarrierSegmentBuilder updatedBy(UserBase updatedBy) {
      this.updatedBy = updatedBy;
      return this;
    }

    public ShieldInformationBarrierSegment build() {
      return new ShieldInformationBarrierSegment(this);
    }
  }
}
