package com.box.sdkgen.schemas.classificationtemplate;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ClassificationTemplateFieldsOptionsStaticConfigField extends SerializableObject {

  /**
   * Additional information about the classification.
   *
   * <p>This is not an exclusive list of properties, and more object fields might be returned. These
   * fields are used for internal Box Shield and Box Governance purposes and no additional value
   * must be derived from these fields.
   */
  protected ClassificationTemplateFieldsOptionsStaticConfigClassificationField classification;

  public ClassificationTemplateFieldsOptionsStaticConfigField() {
    super();
  }

  protected ClassificationTemplateFieldsOptionsStaticConfigField(Builder builder) {
    super();
    this.classification = builder.classification;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public ClassificationTemplateFieldsOptionsStaticConfigClassificationField getClassification() {
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
    ClassificationTemplateFieldsOptionsStaticConfigField casted =
        (ClassificationTemplateFieldsOptionsStaticConfigField) o;
    return Objects.equals(classification, casted.classification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(classification);
  }

  @Override
  public String toString() {
    return "ClassificationTemplateFieldsOptionsStaticConfigField{"
        + "classification='"
        + classification
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected ClassificationTemplateFieldsOptionsStaticConfigClassificationField classification;

    public Builder classification(
        ClassificationTemplateFieldsOptionsStaticConfigClassificationField classification) {
      this.classification = classification;
      return this;
    }

    public ClassificationTemplateFieldsOptionsStaticConfigField build() {
      return new ClassificationTemplateFieldsOptionsStaticConfigField(this);
    }
  }
}
