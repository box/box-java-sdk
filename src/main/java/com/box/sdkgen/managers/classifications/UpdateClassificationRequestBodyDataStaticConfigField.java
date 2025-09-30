package com.box.sdkgen.managers.classifications;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateClassificationRequestBodyDataStaticConfigField extends SerializableObject {

  protected UpdateClassificationRequestBodyDataStaticConfigClassificationField classification;

  public UpdateClassificationRequestBodyDataStaticConfigField() {
    super();
  }

  protected UpdateClassificationRequestBodyDataStaticConfigField(Builder builder) {
    super();
    this.classification = builder.classification;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public UpdateClassificationRequestBodyDataStaticConfigClassificationField getClassification() {
    return classification;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateClassificationRequestBodyDataStaticConfigField casted =
        (UpdateClassificationRequestBodyDataStaticConfigField) o;
    return Objects.equals(classification, casted.classification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(classification);
  }

  @Override
  public String toString() {
    return "UpdateClassificationRequestBodyDataStaticConfigField{"
        + "classification='"
        + classification
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected UpdateClassificationRequestBodyDataStaticConfigClassificationField classification;

    public Builder classification(
        UpdateClassificationRequestBodyDataStaticConfigClassificationField classification) {
      this.classification = classification;
      return this;
    }

    public UpdateClassificationRequestBodyDataStaticConfigField build() {
      return new UpdateClassificationRequestBodyDataStaticConfigField(this);
    }
  }
}
