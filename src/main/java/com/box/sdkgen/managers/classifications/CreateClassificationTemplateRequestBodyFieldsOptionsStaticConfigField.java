package com.box.sdkgen.managers.classifications;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigField
    extends SerializableObject {

  protected CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigClassificationField
      classification;

  public CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigField() {
    super();
  }

  protected CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigField(
      CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigFieldBuilder builder) {
    super();
    this.classification = builder.classification;
  }

  public CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigClassificationField
      getClassification() {
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
    CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigField casted =
        (CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigField) o;
    return Objects.equals(classification, casted.classification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(classification);
  }

  @Override
  public String toString() {
    return "CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigField{"
        + "classification='"
        + classification
        + '\''
        + "}";
  }

  public static class CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigFieldBuilder {

    protected CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigClassificationField
        classification;

    public CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigFieldBuilder
        classification(
            CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigClassificationField
                classification) {
      this.classification = classification;
      return this;
    }

    public CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigField build() {
      return new CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigField(this);
    }
  }
}
