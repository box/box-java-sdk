package com.box.sdkgen.schemas.shieldinformationbarrierreportdetails;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ShieldInformationBarrierReportDetailsDetailsField extends SerializableObject {

  @JsonProperty("folder_id")
  protected String folderId;

  public ShieldInformationBarrierReportDetailsDetailsField() {
    super();
  }

  protected ShieldInformationBarrierReportDetailsDetailsField(Builder builder) {
    super();
    this.folderId = builder.folderId;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getFolderId() {
    return folderId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldInformationBarrierReportDetailsDetailsField casted =
        (ShieldInformationBarrierReportDetailsDetailsField) o;
    return Objects.equals(folderId, casted.folderId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(folderId);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierReportDetailsDetailsField{"
        + "folderId='"
        + folderId
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String folderId;

    public Builder folderId(String folderId) {
      this.folderId = folderId;
      return this;
    }

    public ShieldInformationBarrierReportDetailsDetailsField build() {
      return new ShieldInformationBarrierReportDetailsDetailsField(this);
    }
  }
}
