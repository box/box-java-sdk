package com.box.sdkgen.managers.shieldinformationbarriersegments;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBase;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateShieldInformationBarrierSegmentRequestBody extends SerializableObject {

  @JsonProperty("shield_information_barrier")
  protected final ShieldInformationBarrierBase shieldInformationBarrier;

  /** Name of the shield information barrier segment. */
  protected final String name;

  /** Description of the shield information barrier segment. */
  protected String description;

  public CreateShieldInformationBarrierSegmentRequestBody(
      @JsonProperty("shield_information_barrier")
          ShieldInformationBarrierBase shieldInformationBarrier,
      @JsonProperty("name") String name) {
    super();
    this.shieldInformationBarrier = shieldInformationBarrier;
    this.name = name;
  }

  protected CreateShieldInformationBarrierSegmentRequestBody(Builder builder) {
    super();
    this.shieldInformationBarrier = builder.shieldInformationBarrier;
    this.name = builder.name;
    this.description = builder.description;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateShieldInformationBarrierSegmentRequestBody casted =
        (CreateShieldInformationBarrierSegmentRequestBody) o;
    return Objects.equals(shieldInformationBarrier, casted.shieldInformationBarrier)
        && Objects.equals(name, casted.name)
        && Objects.equals(description, casted.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(shieldInformationBarrier, name, description);
  }

  @Override
  public String toString() {
    return "CreateShieldInformationBarrierSegmentRequestBody{"
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
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final ShieldInformationBarrierBase shieldInformationBarrier;

    protected final String name;

    protected String description;

    public Builder(ShieldInformationBarrierBase shieldInformationBarrier, String name) {
      super();
      this.shieldInformationBarrier = shieldInformationBarrier;
      this.name = name;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public CreateShieldInformationBarrierSegmentRequestBody build() {
      return new CreateShieldInformationBarrierSegmentRequestBody(this);
    }
  }
}
