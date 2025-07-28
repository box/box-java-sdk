package com.box.sdkgen.schemas.shieldinformationbarrierreference;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBase;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ShieldInformationBarrierReference extends SerializableObject {

  @JsonProperty("shield_information_barrier")
  protected ShieldInformationBarrierBase shieldInformationBarrier;

  public ShieldInformationBarrierReference() {
    super();
  }

  protected ShieldInformationBarrierReference(Builder builder) {
    super();
    this.shieldInformationBarrier = builder.shieldInformationBarrier;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public ShieldInformationBarrierBase getShieldInformationBarrier() {
    return shieldInformationBarrier;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldInformationBarrierReference casted = (ShieldInformationBarrierReference) o;
    return Objects.equals(shieldInformationBarrier, casted.shieldInformationBarrier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(shieldInformationBarrier);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierReference{"
        + "shieldInformationBarrier='"
        + shieldInformationBarrier
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected ShieldInformationBarrierBase shieldInformationBarrier;

    public Builder shieldInformationBarrier(ShieldInformationBarrierBase shieldInformationBarrier) {
      this.shieldInformationBarrier = shieldInformationBarrier;
      return this;
    }

    public ShieldInformationBarrierReference build() {
      return new ShieldInformationBarrierReference(this);
    }
  }
}
