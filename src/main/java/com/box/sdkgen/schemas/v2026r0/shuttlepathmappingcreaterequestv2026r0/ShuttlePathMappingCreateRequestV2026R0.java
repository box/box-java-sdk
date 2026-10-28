package com.box.sdkgen.schemas.v2026r0.shuttlepathmappingcreaterequestv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingaccountlocationinputv2026r0.ShuttlePathMappingAccountLocationInputV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingaccountlocationtargetinputv2026r0.ShuttlePathMappingAccountLocationTargetInputV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappinglocationinputv2026r0.ShuttlePathMappingLocationInputV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingsharedspacelocationinputv2026r0.ShuttlePathMappingSharedSpaceLocationInputV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingsharedspacelocationtargetinputv2026r0.ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingtargetlocationinputv2026r0.ShuttlePathMappingTargetLocationInputV2026R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** The request body used to create a new shuttle path mapping. */
@JsonFilter("nullablePropertyFilter")
public class ShuttlePathMappingCreateRequestV2026R0 extends SerializableObject {

  /** The unique identifier of the shuttle job this mapping belongs to. */
  @JsonProperty("job_id")
  protected final String jobId;

  /** Whether this source path should be skipped during the migration. Defaults to `false`. */
  @JsonProperty("is_path_skipped")
  protected Boolean isPathSkipped;

  protected final ShuttlePathMappingLocationInputV2026R0 source;

  /**
   * The destination location to migrate to. Omit or set to `null` when the source path is skipped.
   */
  protected ShuttlePathMappingTargetLocationInputV2026R0 target;

  public ShuttlePathMappingCreateRequestV2026R0(
      String jobId, ShuttlePathMappingAccountLocationInputV2026R0 source) {
    super();
    this.jobId = jobId;
    this.source = new ShuttlePathMappingLocationInputV2026R0(source);
  }

  public ShuttlePathMappingCreateRequestV2026R0(
      String jobId, ShuttlePathMappingSharedSpaceLocationInputV2026R0 source) {
    super();
    this.jobId = jobId;
    this.source = new ShuttlePathMappingLocationInputV2026R0(source);
  }

  public ShuttlePathMappingCreateRequestV2026R0(
      @JsonProperty("job_id") String jobId,
      @JsonProperty("source") ShuttlePathMappingLocationInputV2026R0 source) {
    super();
    this.jobId = jobId;
    this.source = source;
  }

  protected ShuttlePathMappingCreateRequestV2026R0(Builder builder) {
    super();
    this.jobId = builder.jobId;
    this.isPathSkipped = builder.isPathSkipped;
    this.source = builder.source;
    this.target = builder.target;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getJobId() {
    return jobId;
  }

  public Boolean getIsPathSkipped() {
    return isPathSkipped;
  }

  public ShuttlePathMappingLocationInputV2026R0 getSource() {
    return source;
  }

  public ShuttlePathMappingTargetLocationInputV2026R0 getTarget() {
    return target;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttlePathMappingCreateRequestV2026R0 casted = (ShuttlePathMappingCreateRequestV2026R0) o;
    return Objects.equals(jobId, casted.jobId)
        && Objects.equals(isPathSkipped, casted.isPathSkipped)
        && Objects.equals(source, casted.source)
        && Objects.equals(target, casted.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jobId, isPathSkipped, source, target);
  }

  @Override
  public String toString() {
    return "ShuttlePathMappingCreateRequestV2026R0{"
        + "jobId='"
        + jobId
        + '\''
        + ", "
        + "isPathSkipped='"
        + isPathSkipped
        + '\''
        + ", "
        + "source='"
        + source
        + '\''
        + ", "
        + "target='"
        + target
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String jobId;

    protected Boolean isPathSkipped;

    protected final ShuttlePathMappingLocationInputV2026R0 source;

    protected ShuttlePathMappingTargetLocationInputV2026R0 target;

    public Builder(String jobId, ShuttlePathMappingAccountLocationInputV2026R0 source) {
      super();
      this.jobId = jobId;
      this.source = new ShuttlePathMappingLocationInputV2026R0(source);
    }

    public Builder(String jobId, ShuttlePathMappingSharedSpaceLocationInputV2026R0 source) {
      super();
      this.jobId = jobId;
      this.source = new ShuttlePathMappingLocationInputV2026R0(source);
    }

    public Builder(String jobId, ShuttlePathMappingLocationInputV2026R0 source) {
      super();
      this.jobId = jobId;
      this.source = source;
    }

    public Builder isPathSkipped(Boolean isPathSkipped) {
      this.isPathSkipped = isPathSkipped;
      return this;
    }

    public Builder target(ShuttlePathMappingAccountLocationTargetInputV2026R0 target) {
      this.target = new ShuttlePathMappingTargetLocationInputV2026R0(target);
      return this;
    }

    public Builder target(ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0 target) {
      this.target = new ShuttlePathMappingTargetLocationInputV2026R0(target);
      return this;
    }

    public Builder target(ShuttlePathMappingTargetLocationInputV2026R0 target) {
      this.target = target;
      return this;
    }

    public ShuttlePathMappingCreateRequestV2026R0 build() {
      return new ShuttlePathMappingCreateRequestV2026R0(this);
    }
  }
}
