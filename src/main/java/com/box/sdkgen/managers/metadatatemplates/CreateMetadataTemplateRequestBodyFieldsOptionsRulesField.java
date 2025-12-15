package com.box.sdkgen.managers.metadatatemplates;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateMetadataTemplateRequestBodyFieldsOptionsRulesField extends SerializableObject {

  /** Whether to allow users to select multiple values. */
  protected Boolean multiSelect;

  /** An array of integers defining which levels of the taxonomy are selectable by users. */
  protected List<Long> selectableLevels;

  public CreateMetadataTemplateRequestBodyFieldsOptionsRulesField() {
    super();
  }

  protected CreateMetadataTemplateRequestBodyFieldsOptionsRulesField(Builder builder) {
    super();
    this.multiSelect = builder.multiSelect;
    this.selectableLevels = builder.selectableLevels;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Boolean getMultiSelect() {
    return multiSelect;
  }

  public List<Long> getSelectableLevels() {
    return selectableLevels;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateMetadataTemplateRequestBodyFieldsOptionsRulesField casted =
        (CreateMetadataTemplateRequestBodyFieldsOptionsRulesField) o;
    return Objects.equals(multiSelect, casted.multiSelect)
        && Objects.equals(selectableLevels, casted.selectableLevels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(multiSelect, selectableLevels);
  }

  @Override
  public String toString() {
    return "CreateMetadataTemplateRequestBodyFieldsOptionsRulesField{"
        + "multiSelect='"
        + multiSelect
        + '\''
        + ", "
        + "selectableLevels='"
        + selectableLevels
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Boolean multiSelect;

    protected List<Long> selectableLevels;

    public Builder multiSelect(Boolean multiSelect) {
      this.multiSelect = multiSelect;
      return this;
    }

    public Builder selectableLevels(List<Long> selectableLevels) {
      this.selectableLevels = selectableLevels;
      return this;
    }

    public CreateMetadataTemplateRequestBodyFieldsOptionsRulesField build() {
      return new CreateMetadataTemplateRequestBodyFieldsOptionsRulesField(this);
    }
  }
}
