package com.box.sdkgen.schemas.shieldinformationbarrierreportdetails;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/** Indicates which folder the report file is located and any errors when generating the report. */
@JsonFilter("nullablePropertyFilter")
public class ShieldInformationBarrierReportDetails extends SerializableObject {

  protected ShieldInformationBarrierReportDetailsDetailsField details;

  public ShieldInformationBarrierReportDetails() {
    super();
  }

  protected ShieldInformationBarrierReportDetails(Builder builder) {
    super();
    this.details = builder.details;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public ShieldInformationBarrierReportDetailsDetailsField getDetails() {
    return details;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldInformationBarrierReportDetails casted = (ShieldInformationBarrierReportDetails) o;
    return Objects.equals(details, casted.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(details);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierReportDetails{" + "details='" + details + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected ShieldInformationBarrierReportDetailsDetailsField details;

    public Builder details(ShieldInformationBarrierReportDetailsDetailsField details) {
      this.details = details;
      return this;
    }

    public ShieldInformationBarrierReportDetails build() {
      return new ShieldInformationBarrierReportDetails(this);
    }
  }
}
