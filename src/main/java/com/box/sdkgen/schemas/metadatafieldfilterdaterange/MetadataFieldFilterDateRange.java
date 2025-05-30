package com.box.sdkgen.schemas.metadatafieldfilterdaterange;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class MetadataFieldFilterDateRange extends SerializableObject {

  protected String lt;

  protected String gt;

  public MetadataFieldFilterDateRange() {
    super();
  }

  protected MetadataFieldFilterDateRange(MetadataFieldFilterDateRangeBuilder builder) {
    super();
    this.lt = builder.lt;
    this.gt = builder.gt;
  }

  public String getLt() {
    return lt;
  }

  public String getGt() {
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

  public static class MetadataFieldFilterDateRangeBuilder {

    protected String lt;

    protected String gt;

    public MetadataFieldFilterDateRangeBuilder lt(String lt) {
      this.lt = lt;
      return this;
    }

    public MetadataFieldFilterDateRangeBuilder gt(String gt) {
      this.gt = gt;
      return this;
    }

    public MetadataFieldFilterDateRange build() {
      return new MetadataFieldFilterDateRange(this);
    }
  }
}
