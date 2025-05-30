package com.box.sdkgen.managers.shieldinformationbarriersegments;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class UpdateShieldInformationBarrierSegmentByIdRequestBody extends SerializableObject {

  protected String name;

  protected String description;

  public UpdateShieldInformationBarrierSegmentByIdRequestBody() {
    super();
  }

  protected UpdateShieldInformationBarrierSegmentByIdRequestBody(
      UpdateShieldInformationBarrierSegmentByIdRequestBodyBuilder builder) {
    super();
    this.name = builder.name;
    this.description = builder.description;
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
    UpdateShieldInformationBarrierSegmentByIdRequestBody casted =
        (UpdateShieldInformationBarrierSegmentByIdRequestBody) o;
    return Objects.equals(name, casted.name) && Objects.equals(description, casted.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description);
  }

  @Override
  public String toString() {
    return "UpdateShieldInformationBarrierSegmentByIdRequestBody{"
        + "name='"
        + name
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + "}";
  }

  public static class UpdateShieldInformationBarrierSegmentByIdRequestBodyBuilder {

    protected String name;

    protected String description;

    public UpdateShieldInformationBarrierSegmentByIdRequestBodyBuilder name(String name) {
      this.name = name;
      return this;
    }

    public UpdateShieldInformationBarrierSegmentByIdRequestBodyBuilder description(
        String description) {
      this.description = description;
      return this;
    }

    public UpdateShieldInformationBarrierSegmentByIdRequestBody build() {
      return new UpdateShieldInformationBarrierSegmentByIdRequestBody(this);
    }
  }
}
