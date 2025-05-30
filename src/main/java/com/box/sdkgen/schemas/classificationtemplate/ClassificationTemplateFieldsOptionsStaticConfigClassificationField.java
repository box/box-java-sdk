package com.box.sdkgen.schemas.classificationtemplate;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class ClassificationTemplateFieldsOptionsStaticConfigClassificationField
    extends SerializableObject {

  protected String classificationDefinition;

  @JsonProperty("colorID")
  protected Long colorId;

  public ClassificationTemplateFieldsOptionsStaticConfigClassificationField() {
    super();
  }

  protected ClassificationTemplateFieldsOptionsStaticConfigClassificationField(
      ClassificationTemplateFieldsOptionsStaticConfigClassificationFieldBuilder builder) {
    super();
    this.classificationDefinition = builder.classificationDefinition;
    this.colorId = builder.colorId;
  }

  public String getClassificationDefinition() {
    return classificationDefinition;
  }

  public Long getColorId() {
    return colorId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClassificationTemplateFieldsOptionsStaticConfigClassificationField casted =
        (ClassificationTemplateFieldsOptionsStaticConfigClassificationField) o;
    return Objects.equals(classificationDefinition, casted.classificationDefinition)
        && Objects.equals(colorId, casted.colorId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(classificationDefinition, colorId);
  }

  @Override
  public String toString() {
    return "ClassificationTemplateFieldsOptionsStaticConfigClassificationField{"
        + "classificationDefinition='"
        + classificationDefinition
        + '\''
        + ", "
        + "colorId='"
        + colorId
        + '\''
        + "}";
  }

  public static class ClassificationTemplateFieldsOptionsStaticConfigClassificationFieldBuilder {

    protected String classificationDefinition;

    protected Long colorId;

    public ClassificationTemplateFieldsOptionsStaticConfigClassificationFieldBuilder
        classificationDefinition(String classificationDefinition) {
      this.classificationDefinition = classificationDefinition;
      return this;
    }

    public ClassificationTemplateFieldsOptionsStaticConfigClassificationFieldBuilder colorId(
        Long colorId) {
      this.colorId = colorId;
      return this;
    }

    public ClassificationTemplateFieldsOptionsStaticConfigClassificationField build() {
      return new ClassificationTemplateFieldsOptionsStaticConfigClassificationField(this);
    }
  }
}
