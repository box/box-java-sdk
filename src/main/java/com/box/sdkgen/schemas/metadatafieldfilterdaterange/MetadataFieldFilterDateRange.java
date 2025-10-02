package com.box.sdkgen.schemas.metadatafieldfilterdaterange;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Specifies which `date` field on the template to filter the search results by, specifying a range
 * of dates that can match.
 */
@JsonFilter("nullablePropertyFilter")
public class MetadataFieldFilterDateRange extends SerializableObject {

  /**
   * Specifies the (inclusive) upper bound for the metadata field value. The value of a field must
   * be lower than (`lt`) or equal to this value for the search query to match this template.
   */
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime lt;

  /**
   * Specifies the (inclusive) lower bound for the metadata field value. The value of a field must
   * be greater than (`gt`) or equal to this value for the search query to match this template.
   */
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime gt;

  public MetadataFieldFilterDateRange() {
    super();
  }

  protected MetadataFieldFilterDateRange(Builder builder) {
    super();
    this.lt = builder.lt;
    this.gt = builder.gt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public OffsetDateTime getLt() {
    return lt;
  }

  public OffsetDateTime getGt() {
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
    MetadataFieldFilterDateRange casted = (MetadataFieldFilterDateRange) o;
    return Objects.equals(lt, casted.lt) && Objects.equals(gt, casted.gt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lt, gt);
  }

  @Override
  public String toString() {
    return "MetadataFieldFilterDateRange{" + "lt='" + lt + '\'' + ", " + "gt='" + gt + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected OffsetDateTime lt;

    protected OffsetDateTime gt;

    public Builder lt(OffsetDateTime lt) {
      this.lt = lt;
      return this;
    }

    public Builder gt(OffsetDateTime gt) {
      this.gt = gt;
      return this;
    }

    public MetadataFieldFilterDateRange build() {
      return new MetadataFieldFilterDateRange(this);
    }
  }
}
