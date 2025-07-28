package com.box.sdkgen.schemas.metadatafieldfilterdaterange;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class MetadataFieldFilterDateRange extends SerializableObject {

  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date lt;

  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date gt;

  public MetadataFieldFilterDateRange() {
    super();
  }

  protected MetadataFieldFilterDateRange(Builder builder) {
    super();
    this.lt = builder.lt;
    this.gt = builder.gt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Date getLt() {
    return lt;
  }

  public Date getGt() {
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

    protected Date lt;

    protected Date gt;

    public Builder lt(Date lt) {
      this.lt = lt;
      return this;
    }

    public Builder gt(Date gt) {
      this.gt = gt;
      return this;
    }

    public MetadataFieldFilterDateRange build() {
      return new MetadataFieldFilterDateRange(this);
    }
  }
}
