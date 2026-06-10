package com.box.sdkgen.schemas.aioptionsrules;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/**
 * An object for a `taxonomy` type template field containing configuration for taxonomy options.
 * Required if using `taxonomy` type field.
 */
@JsonFilter("nullablePropertyFilter")
public class AiOptionsRules extends SerializableObject {

  /**
   * Indicates whether the field is a multi-select field. If true, the field can have multiple
   * values.
   */
  @JsonProperty("multi_select")
  protected Boolean multiSelect;

  /**
   * The selectable levels for the field. This is used to limit the levels of the taxonomy that can
   * be selected.
   */
  @JsonProperty("selectable_levels")
  protected List<Long> selectableLevels;

  public AiOptionsRules() {
    super();
  }

  protected AiOptionsRules(Builder builder) {
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
    AiOptionsRules casted = (AiOptionsRules) o;
    return Objects.equals(multiSelect, casted.multiSelect)
        && Objects.equals(selectableLevels, casted.selectableLevels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(multiSelect, selectableLevels);
  }

  @Override
  public String toString() {
    return "AiOptionsRules{"
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

    public AiOptionsRules build() {
      return new AiOptionsRules(this);
    }
  }
}
