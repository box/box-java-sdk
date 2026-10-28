package com.box.sdkgen.schemas.v2026r0.shuttlesubjectmappingcreaterequestv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.shuttletargetsubjectinputv2026r0.ShuttleTargetSubjectInputV2026R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** The request body used to create a new shuttle subject mapping. */
@JsonFilter("nullablePropertyFilter")
public class ShuttleSubjectMappingCreateRequestV2026R0 extends SerializableObject {

  /** The unique identifier of the shuttle job this mapping belongs to. */
  @JsonProperty("job_id")
  protected final String jobId;

  /** The unique identifier of the discovered subject to map. */
  @JsonProperty("discovered_subject_id")
  protected final String discoveredSubjectId;

  /** The discovery-requirements revision to resolve against. Omit to use the latest revision. */
  protected Integer revision;

  /** Whether this subject should be skipped during the migration. Defaults to `false`. */
  @JsonProperty("is_subject_skipped")
  protected Boolean isSubjectSkipped;

  /** The Box target to map to. Omit or set to `null` when the subject is skipped. */
  protected ShuttleTargetSubjectInputV2026R0 target;

  public ShuttleSubjectMappingCreateRequestV2026R0(
      @JsonProperty("job_id") String jobId,
      @JsonProperty("discovered_subject_id") String discoveredSubjectId) {
    super();
    this.jobId = jobId;
    this.discoveredSubjectId = discoveredSubjectId;
  }

  protected ShuttleSubjectMappingCreateRequestV2026R0(Builder builder) {
    super();
    this.jobId = builder.jobId;
    this.discoveredSubjectId = builder.discoveredSubjectId;
    this.revision = builder.revision;
    this.isSubjectSkipped = builder.isSubjectSkipped;
    this.target = builder.target;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getJobId() {
    return jobId;
  }

  public String getDiscoveredSubjectId() {
    return discoveredSubjectId;
  }

  public Integer getRevision() {
    return revision;
  }

  public Boolean getIsSubjectSkipped() {
    return isSubjectSkipped;
  }

  public ShuttleTargetSubjectInputV2026R0 getTarget() {
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
    ShuttleSubjectMappingCreateRequestV2026R0 casted =
        (ShuttleSubjectMappingCreateRequestV2026R0) o;
    return Objects.equals(jobId, casted.jobId)
        && Objects.equals(discoveredSubjectId, casted.discoveredSubjectId)
        && Objects.equals(revision, casted.revision)
        && Objects.equals(isSubjectSkipped, casted.isSubjectSkipped)
        && Objects.equals(target, casted.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jobId, discoveredSubjectId, revision, isSubjectSkipped, target);
  }

  @Override
  public String toString() {
    return "ShuttleSubjectMappingCreateRequestV2026R0{"
        + "jobId='"
        + jobId
        + '\''
        + ", "
        + "discoveredSubjectId='"
        + discoveredSubjectId
        + '\''
        + ", "
        + "revision='"
        + revision
        + '\''
        + ", "
        + "isSubjectSkipped='"
        + isSubjectSkipped
        + '\''
        + ", "
        + "target='"
        + target
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String jobId;

    protected final String discoveredSubjectId;

    protected Integer revision;

    protected Boolean isSubjectSkipped;

    protected ShuttleTargetSubjectInputV2026R0 target;

    public Builder(String jobId, String discoveredSubjectId) {
      super();
      this.jobId = jobId;
      this.discoveredSubjectId = discoveredSubjectId;
    }

    public Builder revision(Integer revision) {
      this.revision = revision;
      return this;
    }

    public Builder isSubjectSkipped(Boolean isSubjectSkipped) {
      this.isSubjectSkipped = isSubjectSkipped;
      return this;
    }

    public Builder target(ShuttleTargetSubjectInputV2026R0 target) {
      this.target = target;
      return this;
    }

    public ShuttleSubjectMappingCreateRequestV2026R0 build() {
      return new ShuttleSubjectMappingCreateRequestV2026R0(this);
    }
  }
}
