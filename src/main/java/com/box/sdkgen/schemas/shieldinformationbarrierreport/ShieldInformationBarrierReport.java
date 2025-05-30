package com.box.sdkgen.schemas.shieldinformationbarrierreport;

import com.box.sdkgen.schemas.shieldinformationbarrierreference.ShieldInformationBarrierReference;
import com.box.sdkgen.schemas.shieldinformationbarrierreportbase.ShieldInformationBarrierReportBase;
import com.box.sdkgen.schemas.shieldinformationbarrierreportbase.ShieldInformationBarrierReportBaseTypeField;
import com.box.sdkgen.schemas.shieldinformationbarrierreportdetails.ShieldInformationBarrierReportDetails;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class ShieldInformationBarrierReport extends ShieldInformationBarrierReportBase {

  @JsonProperty("shield_information_barrier")
  protected ShieldInformationBarrierReference shieldInformationBarrier;

  @JsonDeserialize(
      using =
          ShieldInformationBarrierReportStatusField
              .ShieldInformationBarrierReportStatusFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShieldInformationBarrierReportStatusField
              .ShieldInformationBarrierReportStatusFieldSerializer.class)
  protected EnumWrapper<ShieldInformationBarrierReportStatusField> status;

  protected ShieldInformationBarrierReportDetails details;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("created_by")
  protected UserBase createdBy;

  @JsonProperty("updated_at")
  protected String updatedAt;

  public ShieldInformationBarrierReport() {
    super();
  }

  protected ShieldInformationBarrierReport(ShieldInformationBarrierReportBuilder builder) {
    super(builder);
    this.shieldInformationBarrier = builder.shieldInformationBarrier;
    this.status = builder.status;
    this.details = builder.details;
    this.createdAt = builder.createdAt;
    this.createdBy = builder.createdBy;
    this.updatedAt = builder.updatedAt;
  }

  public ShieldInformationBarrierReference getShieldInformationBarrier() {
    return shieldInformationBarrier;
  }

  public EnumWrapper<ShieldInformationBarrierReportStatusField> getStatus() {
    return status;
  }

  public ShieldInformationBarrierReportDetails getDetails() {
    return details;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public UserBase getCreatedBy() {
    return createdBy;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldInformationBarrierReport casted = (ShieldInformationBarrierReport) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(shieldInformationBarrier, casted.shieldInformationBarrier)
        && Objects.equals(status, casted.status)
        && Objects.equals(details, casted.details)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(updatedAt, casted.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, type, shieldInformationBarrier, status, details, createdAt, createdBy, updatedAt);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierReport{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "shieldInformationBarrier='"
        + shieldInformationBarrier
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "details='"
        + details
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "updatedAt='"
        + updatedAt
        + '\''
        + "}";
  }

  public static class ShieldInformationBarrierReportBuilder
      extends ShieldInformationBarrierReportBaseBuilder {

    protected ShieldInformationBarrierReference shieldInformationBarrier;

    protected EnumWrapper<ShieldInformationBarrierReportStatusField> status;

    protected ShieldInformationBarrierReportDetails details;

    protected String createdAt;

    protected UserBase createdBy;

    protected String updatedAt;

    public ShieldInformationBarrierReportBuilder shieldInformationBarrier(
        ShieldInformationBarrierReference shieldInformationBarrier) {
      this.shieldInformationBarrier = shieldInformationBarrier;
      return this;
    }

    public ShieldInformationBarrierReportBuilder status(
        ShieldInformationBarrierReportStatusField status) {
      this.status = new EnumWrapper<ShieldInformationBarrierReportStatusField>(status);
      return this;
    }

    public ShieldInformationBarrierReportBuilder status(
        EnumWrapper<ShieldInformationBarrierReportStatusField> status) {
      this.status = status;
      return this;
    }

    public ShieldInformationBarrierReportBuilder details(
        ShieldInformationBarrierReportDetails details) {
      this.details = details;
      return this;
    }

    public ShieldInformationBarrierReportBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public ShieldInformationBarrierReportBuilder createdBy(UserBase createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public ShieldInformationBarrierReportBuilder updatedAt(String updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    @Override
    public ShieldInformationBarrierReportBuilder id(String id) {
      this.id = id;
      return this;
    }

    @Override
    public ShieldInformationBarrierReportBuilder type(
        ShieldInformationBarrierReportBaseTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierReportBaseTypeField>(type);
      return this;
    }

    @Override
    public ShieldInformationBarrierReportBuilder type(
        EnumWrapper<ShieldInformationBarrierReportBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public ShieldInformationBarrierReport build() {
      return new ShieldInformationBarrierReport(this);
    }
  }
}
