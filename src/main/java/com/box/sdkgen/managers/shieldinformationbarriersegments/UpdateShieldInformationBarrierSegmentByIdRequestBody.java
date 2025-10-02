package com.box.sdkgen.managers.shieldinformationbarriersegments;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateShieldInformationBarrierSegmentByIdRequestBody extends SerializableObject {

  /** The updated name for the shield information barrier segment. */
  protected String name;

  /** The updated description for the shield information barrier segment. */
  @Nullable protected String description;

  public UpdateShieldInformationBarrierSegmentByIdRequestBody() {
    super();
  }

  protected UpdateShieldInformationBarrierSegmentByIdRequestBody(Builder builder) {
    super();
    this.name = builder.name;
    this.description = builder.description;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected String name;

    protected String description;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      this.markNullableFieldAsSet("description");
      return this;
    }

    public UpdateShieldInformationBarrierSegmentByIdRequestBody build() {
      return new UpdateShieldInformationBarrierSegmentByIdRequestBody(this);
    }
  }
}
