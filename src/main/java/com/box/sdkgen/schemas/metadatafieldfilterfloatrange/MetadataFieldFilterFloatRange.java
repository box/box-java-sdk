package com.box.sdkgen.schemas.metadatafieldfilterfloatrange;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class MetadataFieldFilterFloatRange extends SerializableObject {

  protected Double lt;

  protected Double gt;

  public MetadataFieldFilterFloatRange() {
    super();
  }

  protected MetadataFieldFilterFloatRange(MetadataFieldFilterFloatRangeBuilder builder) {
    super();
    this.lt = builder.lt;
    this.gt = builder.gt;
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

  public static class MetadataFieldFilterFloatRangeBuilder {

    protected Double lt;

    protected Double gt;

    public MetadataFieldFilterFloatRangeBuilder lt(Double lt) {
      this.lt = lt;
      return this;
    }

    public MetadataFieldFilterFloatRangeBuilder gt(Double gt) {
      this.gt = gt;
      return this;
    }

    public MetadataFieldFilterFloatRange build() {
      return new MetadataFieldFilterFloatRange(this);
    }
  }
}
