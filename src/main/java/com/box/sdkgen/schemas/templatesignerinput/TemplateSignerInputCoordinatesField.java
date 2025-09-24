package com.box.sdkgen.schemas.templatesignerinput;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TemplateSignerInputCoordinatesField extends SerializableObject {

  protected Double x;

  protected Double y;

  public TemplateSignerInputCoordinatesField() {
    super();
  }

  protected TemplateSignerInputCoordinatesField(Builder builder) {
    super();
    this.x = builder.x;
    this.y = builder.y;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Double getX() {
    return x;
  }

  public Double getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemplateSignerInputCoordinatesField casted = (TemplateSignerInputCoordinatesField) o;
    return Objects.equals(x, casted.x) && Objects.equals(y, casted.y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    return "TemplateSignerInputCoordinatesField{"
        + "x='"
        + x
        + '\''
        + ", "
        + "y='"
        + y
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Double x;

    protected Double y;

    public Builder x(Double x) {
      this.x = x;
      return this;
    }

    public Builder y(Double y) {
      this.y = y;
      return this;
    }

    public TemplateSignerInputCoordinatesField build() {
      return new TemplateSignerInputCoordinatesField(this);
    }
  }
}
