package com.box.sdkgen.schemas.metadatafieldfilterfloatrange;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/**
 * Specifies which `float` field on the template to filter the search results by, specifying a range
 * of values that can match.
 */
@JsonFilter("nullablePropertyFilter")
public class MetadataFieldFilterFloatRange extends SerializableObject {

  /**
   * Specifies the (inclusive) upper bound for the metadata field value. The value of a field must
   * be lower than (`lt`) or equal to this value for the search query to match this template.
   */
  protected Double lt;

  /**
   * Specifies the (inclusive) lower bound for the metadata field value. The value of a field must
   * be greater than (`gt`) or equal to this value for the search query to match this template.
   */
  protected Double gt;

  public MetadataFieldFilterFloatRange() {
    super();
  }

  protected MetadataFieldFilterFloatRange(Builder builder) {
    super();
    this.lt = builder.lt;
    this.gt = builder.gt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Double getLt() {
    return lt;
  }

  public Double getGt() {
    return gt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataFieldFilterFloatRange casted = (MetadataFieldFilterFloatRange) o;
    return Objects.equals(lt, casted.lt) && Objects.equals(gt, casted.gt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lt, gt);
  }

  @Override
  public String toString() {
    return "MetadataFieldFilterFloatRange{" + "lt='" + lt + '\'' + ", " + "gt='" + gt + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Double lt;

    protected Double gt;

    public Builder lt(Double lt) {
      this.lt = lt;
      return this;
    }

    public Builder gt(Double gt) {
      this.gt = gt;
      return this;
    }

    public MetadataFieldFilterFloatRange build() {
      return new MetadataFieldFilterFloatRange(this);
    }
  }
}
