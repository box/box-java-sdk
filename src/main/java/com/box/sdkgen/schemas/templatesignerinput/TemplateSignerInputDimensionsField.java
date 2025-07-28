package com.box.sdkgen.schemas.templatesignerinput;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TemplateSignerInputDimensionsField extends SerializableObject {

  protected Double width;

  protected Double height;

  public TemplateSignerInputDimensionsField() {
    super();
  }

  protected TemplateSignerInputDimensionsField(Builder builder) {
    super();
    this.width = builder.width;
    this.height = builder.height;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Double getWidth() {
    return width;
  }

  public Double getHeight() {
    return height;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemplateSignerInputDimensionsField casted = (TemplateSignerInputDimensionsField) o;
    return Objects.equals(width, casted.width) && Objects.equals(height, casted.height);
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height);
  }

  @Override
  public String toString() {
    return "TemplateSignerInputDimensionsField{"
        + "width='"
        + width
        + '\''
        + ", "
        + "height='"
        + height
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Double width;

    protected Double height;

    public Builder width(Double width) {
      this.width = width;
      return this;
    }

    public Builder height(Double height) {
      this.height = height;
      return this;
    }

    public TemplateSignerInputDimensionsField build() {
      return new TemplateSignerInputDimensionsField(this);
    }
  }
}
