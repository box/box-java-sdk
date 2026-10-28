package com.box.sdkgen.schemas.v2026r0.shuttlesubjectmappingupdaterequestv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.shuttletargetsubjectinputv2026r0.ShuttleTargetSubjectInputV2026R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * The request body used to update an existing shuttle subject mapping. Only the fields supplied in
 * the request body are changed. `job_id`, `id`, `type`, `source`, `discovered_subject_id`, and
 * `revision` are immutable.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttleSubjectMappingUpdateRequestV2026R0 extends SerializableObject {

  /** Whether this subject should be skipped during the migration. */
  @JsonProperty("is_subject_skipped")
  protected Boolean isSubjectSkipped;

  /**
   * The Box target to map to. Set to `null` only for a skipped subject. Setting it to `null` while
   * the mapping is active fails validation. Updating `is_subject_skipped` alone preserves the
   * existing target.
   */
  protected ShuttleTargetSubjectInputV2026R0 target;

  public ShuttleSubjectMappingUpdateRequestV2026R0() {
    super();
  }

  protected ShuttleSubjectMappingUpdateRequestV2026R0(Builder builder) {
    super();
    this.isSubjectSkipped = builder.isSubjectSkipped;
    this.target = builder.target;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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
    ShuttleSubjectMappingUpdateRequestV2026R0 casted =
        (ShuttleSubjectMappingUpdateRequestV2026R0) o;
    return Objects.equals(isSubjectSkipped, casted.isSubjectSkipped)
        && Objects.equals(target, casted.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isSubjectSkipped, target);
  }

  @Override
  public String toString() {
    return "ShuttleSubjectMappingUpdateRequestV2026R0{"
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

    protected Boolean isSubjectSkipped;

    protected ShuttleTargetSubjectInputV2026R0 target;

    public Builder isSubjectSkipped(Boolean isSubjectSkipped) {
      this.isSubjectSkipped = isSubjectSkipped;
      return this;
    }

    public Builder target(ShuttleTargetSubjectInputV2026R0 target) {
      this.target = target;
      return this;
    }

    public ShuttleSubjectMappingUpdateRequestV2026R0 build() {
      return new ShuttleSubjectMappingUpdateRequestV2026R0(this);
    }
  }
}
