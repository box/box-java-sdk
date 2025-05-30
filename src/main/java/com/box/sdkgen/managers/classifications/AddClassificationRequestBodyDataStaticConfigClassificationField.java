package com.box.sdkgen.managers.classifications;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AddClassificationRequestBodyDataStaticConfigClassificationField
    extends SerializableObject {

  protected String classificationDefinition;

  @JsonProperty("colorID")
  protected Long colorId;

  public AddClassificationRequestBodyDataStaticConfigClassificationField() {
    super();
  }

  protected AddClassificationRequestBodyDataStaticConfigClassificationField(
      AddClassificationRequestBodyDataStaticConfigClassificationFieldBuilder builder) {
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
    AddClassificationRequestBodyDataStaticConfigClassificationField casted =
        (AddClassificationRequestBodyDataStaticConfigClassificationField) o;
    return Objects.equals(classificationDefinition, casted.classificationDefinition)
        && Objects.equals(colorId, casted.colorId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(classificationDefinition, colorId);
  }

  @Override
  public String toString() {
    return "AddClassificationRequestBodyDataStaticConfigClassificationField{"
        + "classificationDefinition='"
        + classificationDefinition
        + '\''
        + ", "
        + "colorId='"
        + colorId
        + '\''
        + "}";
  }

  public static class AddClassificationRequestBodyDataStaticConfigClassificationFieldBuilder {

    protected String classificationDefinition;

    protected Long colorId;

    public AddClassificationRequestBodyDataStaticConfigClassificationFieldBuilder
        classificationDefinition(String classificationDefinition) {
      this.classificationDefinition = classificationDefinition;
      return this;
    }

    public AddClassificationRequestBodyDataStaticConfigClassificationFieldBuilder colorId(
        Long colorId) {
      this.colorId = colorId;
      return this;
    }

    public AddClassificationRequestBodyDataStaticConfigClassificationField build() {
      return new AddClassificationRequestBodyDataStaticConfigClassificationField(this);
    }
  }
}
