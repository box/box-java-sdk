package com.box.sdkgen.schemas.shieldinformationbarrierreportdetails;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class ShieldInformationBarrierReportDetailsDetailsField extends SerializableObject {

  @JsonProperty("folder_id")
  protected String folderId;

  public ShieldInformationBarrierReportDetailsDetailsField() {
    super();
  }

  protected ShieldInformationBarrierReportDetailsDetailsField(
      ShieldInformationBarrierReportDetailsDetailsFieldBuilder builder) {
    super();
    this.folderId = builder.folderId;
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

  public static class ShieldInformationBarrierReportDetailsDetailsFieldBuilder {

    protected String folderId;

    public ShieldInformationBarrierReportDetailsDetailsFieldBuilder folderId(String folderId) {
      this.folderId = folderId;
      return this;
    }

    public ShieldInformationBarrierReportDetailsDetailsField build() {
      return new ShieldInformationBarrierReportDetailsDetailsField(this);
    }
  }
}
