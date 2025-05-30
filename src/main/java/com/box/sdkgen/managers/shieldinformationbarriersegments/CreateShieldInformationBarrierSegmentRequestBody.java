package com.box.sdkgen.managers.shieldinformationbarriersegments;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBase;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class CreateShieldInformationBarrierSegmentRequestBody extends SerializableObject {

  @JsonProperty("shield_information_barrier")
  protected final ShieldInformationBarrierBase shieldInformationBarrier;

  protected final String name;

  protected String description;

  public CreateShieldInformationBarrierSegmentRequestBody(
      @JsonProperty("shield_information_barrier")
          ShieldInformationBarrierBase shieldInformationBarrier,
      @JsonProperty("name") String name) {
    super();
    this.shieldInformationBarrier = shieldInformationBarrier;
    this.name = name;
  }

  protected CreateShieldInformationBarrierSegmentRequestBody(
      CreateShieldInformationBarrierSegmentRequestBodyBuilder builder) {
    super();
    this.shieldInformationBarrier = builder.shieldInformationBarrier;
    this.name = builder.name;
    this.description = builder.description;
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

  public static class CreateShieldInformationBarrierSegmentRequestBodyBuilder {

    protected final ShieldInformationBarrierBase shieldInformationBarrier;

    protected final String name;

    protected String description;

    public CreateShieldInformationBarrierSegmentRequestBodyBuilder(
        ShieldInformationBarrierBase shieldInformationBarrier, String name) {
      this.shieldInformationBarrier = shieldInformationBarrier;
      this.name = name;
    }

    public CreateShieldInformationBarrierSegmentRequestBodyBuilder description(String description) {
      this.description = description;
      return this;
    }

    public CreateShieldInformationBarrierSegmentRequestBody build() {
      return new CreateShieldInformationBarrierSegmentRequestBody(this);
    }
  }
}
