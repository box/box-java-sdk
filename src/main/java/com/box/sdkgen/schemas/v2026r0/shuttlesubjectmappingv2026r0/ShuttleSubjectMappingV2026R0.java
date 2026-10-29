package com.box.sdkgen.schemas.v2026r0.shuttlesubjectmappingv2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.shuttlesubjectv2026r0.ShuttleSubjectV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttletargetsubjectv2026r0.ShuttleTargetSubjectV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * A standard representation of a shuttle subject mapping. A subject mapping connects a discovered
 * source subject to a Box user or group, or marks the subject as skipped.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttleSubjectMappingV2026R0 extends SerializableObject {

  /** The unique identifier for the shuttle subject mapping. */
  protected final String id;

  /** The value will always be `shuttle_subject_mapping`. */
  @JsonDeserialize(
      using =
          ShuttleSubjectMappingV2026R0TypeField.ShuttleSubjectMappingV2026R0TypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          ShuttleSubjectMappingV2026R0TypeField.ShuttleSubjectMappingV2026R0TypeFieldSerializer
              .class)
  protected EnumWrapper<ShuttleSubjectMappingV2026R0TypeField> type;

  /** The unique identifier of the shuttle job this mapping belongs to. */
  @JsonProperty("job_id")
  protected final String jobId;

  /**
   * The unique identifier of the discovered subject this mapping applies to. `null` for legacy
   * mappings created before this field was tracked.
   */
  @JsonProperty("discovered_subject_id")
  @Nullable
  protected String discoveredSubjectId;

  /**
   * The discovery-requirements revision this mapping was resolved against. `null` for legacy
   * mappings.
   */
  @Nullable protected Integer revision;

  /** Whether this subject is skipped during the migration. */
  @JsonProperty("is_subject_skipped")
  protected final boolean isSubjectSkipped;

  protected final ShuttleSubjectV2026R0 source;

  /** The Box target subject. `null` when the subject is skipped or not yet assigned. */
  protected ShuttleTargetSubjectV2026R0 target;

  public ShuttleSubjectMappingV2026R0(
      @JsonProperty("id") String id,
      @JsonProperty("job_id") String jobId,
      @JsonProperty("is_subject_skipped") boolean isSubjectSkipped,
      @JsonProperty("source") ShuttleSubjectV2026R0 source) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.isSubjectSkipped = isSubjectSkipped;
    this.source = source;
    this.type =
        new EnumWrapper<ShuttleSubjectMappingV2026R0TypeField>(
            ShuttleSubjectMappingV2026R0TypeField.SHUTTLE_SUBJECT_MAPPING);
  }

  protected ShuttleSubjectMappingV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.jobId = builder.jobId;
    this.discoveredSubjectId = builder.discoveredSubjectId;
    this.revision = builder.revision;
    this.isSubjectSkipped = builder.isSubjectSkipped;
    this.source = builder.source;
    this.target = builder.target;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShuttleSubjectMappingV2026R0TypeField> getType() {
    return type;
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

  public boolean getIsSubjectSkipped() {
    return isSubjectSkipped;
  }

  public ShuttleSubjectV2026R0 getSource() {
    return source;
  }

  public ShuttleTargetSubjectV2026R0 getTarget() {
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
    ShuttleSubjectMappingV2026R0 casted = (ShuttleSubjectMappingV2026R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(jobId, casted.jobId)
        && Objects.equals(discoveredSubjectId, casted.discoveredSubjectId)
        && Objects.equals(revision, casted.revision)
        && Objects.equals(isSubjectSkipped, casted.isSubjectSkipped)
        && Objects.equals(source, casted.source)
        && Objects.equals(target, casted.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, type, jobId, discoveredSubjectId, revision, isSubjectSkipped, source, target);
  }

  @Override
  public String toString() {
    return "ShuttleSubjectMappingV2026R0{"
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

    protected EnumWrapper<ShuttleSubjectMappingV2026R0TypeField> type;

    protected final String jobId;

    protected String discoveredSubjectId;

    protected Integer revision;

    protected final boolean isSubjectSkipped;

    protected final ShuttleSubjectV2026R0 source;

    protected ShuttleTargetSubjectV2026R0 target;

    public Builder(
        String id, String jobId, boolean isSubjectSkipped, ShuttleSubjectV2026R0 source) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.isSubjectSkipped = isSubjectSkipped;
      this.source = source;
    }

    public Builder type(ShuttleSubjectMappingV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttleSubjectMappingV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttleSubjectMappingV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder discoveredSubjectId(String discoveredSubjectId) {
      this.discoveredSubjectId = discoveredSubjectId;
      this.markNullableFieldAsSet("discovered_subject_id");
      return this;
    }

    public Builder revision(Integer revision) {
      this.revision = revision;
      this.markNullableFieldAsSet("revision");
      return this;
    }

    public Builder target(ShuttleTargetSubjectV2026R0 target) {
      this.target = target;
      return this;
    }

    public ShuttleSubjectMappingV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttleSubjectMappingV2026R0TypeField>(
                ShuttleSubjectMappingV2026R0TypeField.SHUTTLE_SUBJECT_MAPPING);
      }
      return new ShuttleSubjectMappingV2026R0(this);
    }
  }
}
