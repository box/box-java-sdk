package com.box.sdkgen.schemas.v2026r0.shuttlepathmappingupdaterequestv2026r0;

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

/**
 * The request body used to update an existing shuttle path mapping. Only the fields supplied in the
 * request body are changed. `job_id`, `id`, and `type` are immutable.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttlePathMappingUpdateRequestV2026R0 extends SerializableObject {

  /** Whether this source path should be skipped during the migration. */
  @JsonProperty("is_path_skipped")
  protected Boolean isPathSkipped;

  protected ShuttlePathMappingLocationInputV2026R0 source;

  /**
   * The destination location to migrate to. Set to `null` to clear the destination, for example
   * when skipping the source path.
   */
  protected ShuttlePathMappingTargetLocationInputV2026R0 target;

  public ShuttlePathMappingUpdateRequestV2026R0() {
    super();
  }

  protected ShuttlePathMappingUpdateRequestV2026R0(Builder builder) {
    super();
    this.isPathSkipped = builder.isPathSkipped;
    this.source = builder.source;
    this.target = builder.target;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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
    ShuttlePathMappingUpdateRequestV2026R0 casted = (ShuttlePathMappingUpdateRequestV2026R0) o;
    return Objects.equals(isPathSkipped, casted.isPathSkipped)
        && Objects.equals(source, casted.source)
        && Objects.equals(target, casted.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isPathSkipped, source, target);
  }

  @Override
  public String toString() {
    return "ShuttlePathMappingUpdateRequestV2026R0{"
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

    protected Boolean isPathSkipped;

    protected ShuttlePathMappingLocationInputV2026R0 source;

    protected ShuttlePathMappingTargetLocationInputV2026R0 target;

    public Builder isPathSkipped(Boolean isPathSkipped) {
      this.isPathSkipped = isPathSkipped;
      return this;
    }

    public Builder source(ShuttlePathMappingAccountLocationInputV2026R0 source) {
      this.source = new ShuttlePathMappingLocationInputV2026R0(source);
      return this;
    }

    public Builder source(ShuttlePathMappingSharedSpaceLocationInputV2026R0 source) {
      this.source = new ShuttlePathMappingLocationInputV2026R0(source);
      return this;
    }

    public Builder source(ShuttlePathMappingLocationInputV2026R0 source) {
      this.source = source;
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

    public ShuttlePathMappingUpdateRequestV2026R0 build() {
      return new ShuttlePathMappingUpdateRequestV2026R0(this);
    }
  }
}
