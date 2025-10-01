package com.box.sdkgen.managers.classifications;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigClassificationField
    extends SerializableObject {

  /** A longer description of the classification. */
  protected String classificationDefinition;

  /**
   * An identifier used to assign a color to a classification label.
   *
   * <p>Mapping between a `colorID` and a color may change without notice. Currently, the color
   * mappings are as follows.
   *
   * <p>* `0`: Yellow. * `1`: Orange. * `2`: Watermelon red. * `3`: Purple rain. * `4`: Light blue.
   * * `5`: Dark blue. * `6`: Light green. * `7`: Gray.
   */
  @JsonProperty("colorID")
  protected Long colorId;

  public CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigClassificationField() {
    super();
  }

  protected CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigClassificationField(
      Builder builder) {
    super();
    this.classificationDefinition = builder.classificationDefinition;
    this.colorId = builder.colorId;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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
    CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigClassificationField casted =
        (CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigClassificationField) o;
    return Objects.equals(classificationDefinition, casted.classificationDefinition)
        && Objects.equals(colorId, casted.colorId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(classificationDefinition, colorId);
  }

  @Override
  public String toString() {
    return "CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigClassificationField{"
        + "classificationDefinition='"
        + classificationDefinition
        + '\''
        + ", "
        + "colorId='"
        + colorId
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String classificationDefinition;

    protected Long colorId;

    public Builder classificationDefinition(String classificationDefinition) {
      this.classificationDefinition = classificationDefinition;
      return this;
    }

    public Builder colorId(Long colorId) {
      this.colorId = colorId;
      return this;
    }

    public CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigClassificationField
        build() {
      return new CreateClassificationTemplateRequestBodyFieldsOptionsStaticConfigClassificationField(
          this);
    }
  }
}
