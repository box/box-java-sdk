package com.box.sdkgen.schemas.v2026r0.shuttlepathmappingv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingaccountlocationv2026r0.ShuttlePathMappingAccountLocationV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappinglocationv2026r0.ShuttlePathMappingLocationV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingsharedspacelocationv2026r0.ShuttlePathMappingSharedSpaceLocationV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * A standard representation of a shuttle path mapping. A path mapping connects a source location on
 * the migrated system to a destination in Box, or marks the source path as skipped.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttlePathMappingV2026R0 extends SerializableObject {

  /** The unique identifier for the shuttle path mapping. */
  protected final String id;

  /** The value will always be `shuttle_path_mapping`. */
  @JsonDeserialize(
      using =
          ShuttlePathMappingV2026R0TypeField.ShuttlePathMappingV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = ShuttlePathMappingV2026R0TypeField.ShuttlePathMappingV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttlePathMappingV2026R0TypeField> type;

  /** The unique identifier of the shuttle job this mapping belongs to. */
  @JsonProperty("job_id")
  protected final String jobId;

  /** Whether this source path is skipped during the migration. */
  @JsonProperty("is_path_skipped")
  protected final boolean isPathSkipped;

  protected final ShuttlePathMappingLocationV2026R0 source;

  /**
   * The destination location. `null` when the source path is skipped or the job has no destination,
   * such as an analysis job.
   */
  protected ShuttlePathMappingLocationV2026R0 target;

  public ShuttlePathMappingV2026R0(
      String id,
      String jobId,
      boolean isPathSkipped,
      ShuttlePathMappingAccountLocationV2026R0 source) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.isPathSkipped = isPathSkipped;
    this.source = new ShuttlePathMappingLocationV2026R0(source);
    this.type =
        new EnumWrapper<ShuttlePathMappingV2026R0TypeField>(
            ShuttlePathMappingV2026R0TypeField.SHUTTLE_PATH_MAPPING);
  }

  public ShuttlePathMappingV2026R0(
      String id,
      String jobId,
      boolean isPathSkipped,
      ShuttlePathMappingSharedSpaceLocationV2026R0 source) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.isPathSkipped = isPathSkipped;
    this.source = new ShuttlePathMappingLocationV2026R0(source);
    this.type =
        new EnumWrapper<ShuttlePathMappingV2026R0TypeField>(
            ShuttlePathMappingV2026R0TypeField.SHUTTLE_PATH_MAPPING);
  }

  public ShuttlePathMappingV2026R0(
      @JsonProperty("id") String id,
      @JsonProperty("job_id") String jobId,
      @JsonProperty("is_path_skipped") boolean isPathSkipped,
      @JsonProperty("source") ShuttlePathMappingLocationV2026R0 source) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.isPathSkipped = isPathSkipped;
    this.source = source;
    this.type =
        new EnumWrapper<ShuttlePathMappingV2026R0TypeField>(
            ShuttlePathMappingV2026R0TypeField.SHUTTLE_PATH_MAPPING);
  }

  protected ShuttlePathMappingV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.jobId = builder.jobId;
    this.isPathSkipped = builder.isPathSkipped;
    this.source = builder.source;
    this.target = builder.target;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShuttlePathMappingV2026R0TypeField> getType() {
    return type;
  }

  public String getJobId() {
    return jobId;
  }

  public boolean getIsPathSkipped() {
    return isPathSkipped;
  }

  public ShuttlePathMappingLocationV2026R0 getSource() {
    return source;
  }

  public ShuttlePathMappingLocationV2026R0 getTarget() {
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
    ShuttlePathMappingV2026R0 casted = (ShuttlePathMappingV2026R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(jobId, casted.jobId)
        && Objects.equals(isPathSkipped, casted.isPathSkipped)
        && Objects.equals(source, casted.source)
        && Objects.equals(target, casted.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, jobId, isPathSkipped, source, target);
  }

  @Override
  public String toString() {
    return "ShuttlePathMappingV2026R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
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

    protected final String id;

    protected EnumWrapper<ShuttlePathMappingV2026R0TypeField> type;

    protected final String jobId;

    protected final boolean isPathSkipped;

    protected final ShuttlePathMappingLocationV2026R0 source;

    protected ShuttlePathMappingLocationV2026R0 target;

    public Builder(
        String id,
        String jobId,
        boolean isPathSkipped,
        ShuttlePathMappingAccountLocationV2026R0 source) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.isPathSkipped = isPathSkipped;
      this.source = new ShuttlePathMappingLocationV2026R0(source);
    }

    public Builder(
        String id,
        String jobId,
        boolean isPathSkipped,
        ShuttlePathMappingSharedSpaceLocationV2026R0 source) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.isPathSkipped = isPathSkipped;
      this.source = new ShuttlePathMappingLocationV2026R0(source);
    }

    public Builder(
        String id, String jobId, boolean isPathSkipped, ShuttlePathMappingLocationV2026R0 source) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.isPathSkipped = isPathSkipped;
      this.source = source;
    }

    public Builder type(ShuttlePathMappingV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttlePathMappingV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttlePathMappingV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder target(ShuttlePathMappingAccountLocationV2026R0 target) {
      this.target = new ShuttlePathMappingLocationV2026R0(target);
      return this;
    }

    public Builder target(ShuttlePathMappingSharedSpaceLocationV2026R0 target) {
      this.target = new ShuttlePathMappingLocationV2026R0(target);
      return this;
    }

    public Builder target(ShuttlePathMappingLocationV2026R0 target) {
      this.target = target;
      return this;
    }

    public ShuttlePathMappingV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttlePathMappingV2026R0TypeField>(
                ShuttlePathMappingV2026R0TypeField.SHUTTLE_PATH_MAPPING);
      }
      return new ShuttlePathMappingV2026R0(this);
    }
  }
}
