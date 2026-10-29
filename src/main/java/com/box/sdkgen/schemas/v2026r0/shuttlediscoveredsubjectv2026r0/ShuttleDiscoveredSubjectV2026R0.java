package com.box.sdkgen.schemas.v2026r0.shuttlediscoveredsubjectv2026r0;

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
 * A subject discovered on the source system during a scan, with an optional suggested Box target.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttleDiscoveredSubjectV2026R0 extends SerializableObject {

  /** The unique identifier for the discovered subject. */
  protected final String id;

  /** The value will always be `shuttle_discovered_subject`. */
  @JsonDeserialize(
      using =
          ShuttleDiscoveredSubjectV2026R0TypeField
              .ShuttleDiscoveredSubjectV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleDiscoveredSubjectV2026R0TypeField
              .ShuttleDiscoveredSubjectV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttleDiscoveredSubjectV2026R0TypeField> type;

  /** The unique identifier of the shuttle job this subject belongs to. */
  @JsonProperty("job_id")
  protected final String jobId;

  /** The scan revision this discovered subject belongs to. */
  protected final int revision;

  protected final ShuttleSubjectV2026R0 source;

  /** A suggested Box target inferred during the scan. `null` when no suggestion was made. */
  @JsonProperty("suggested_target")
  protected ShuttleTargetSubjectV2026R0 suggestedTarget;

  public ShuttleDiscoveredSubjectV2026R0(
      @JsonProperty("id") String id,
      @JsonProperty("job_id") String jobId,
      @JsonProperty("revision") int revision,
      @JsonProperty("source") ShuttleSubjectV2026R0 source) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.revision = revision;
    this.source = source;
    this.type =
        new EnumWrapper<ShuttleDiscoveredSubjectV2026R0TypeField>(
            ShuttleDiscoveredSubjectV2026R0TypeField.SHUTTLE_DISCOVERED_SUBJECT);
  }

  protected ShuttleDiscoveredSubjectV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.jobId = builder.jobId;
    this.revision = builder.revision;
    this.source = builder.source;
    this.suggestedTarget = builder.suggestedTarget;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShuttleDiscoveredSubjectV2026R0TypeField> getType() {
    return type;
  }

  public String getJobId() {
    return jobId;
  }

  public int getRevision() {
    return revision;
  }

  public ShuttleSubjectV2026R0 getSource() {
    return source;
  }

  public ShuttleTargetSubjectV2026R0 getSuggestedTarget() {
    return suggestedTarget;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleDiscoveredSubjectV2026R0 casted = (ShuttleDiscoveredSubjectV2026R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(jobId, casted.jobId)
        && Objects.equals(revision, casted.revision)
        && Objects.equals(source, casted.source)
        && Objects.equals(suggestedTarget, casted.suggestedTarget);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, jobId, revision, source, suggestedTarget);
  }

  @Override
  public String toString() {
    return "ShuttleDiscoveredSubjectV2026R0{"
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
        + "revision='"
        + revision
        + '\''
        + ", "
        + "source='"
        + source
        + '\''
        + ", "
        + "suggestedTarget='"
        + suggestedTarget
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<ShuttleDiscoveredSubjectV2026R0TypeField> type;

    protected final String jobId;

    protected final int revision;

    protected final ShuttleSubjectV2026R0 source;

    protected ShuttleTargetSubjectV2026R0 suggestedTarget;

    public Builder(String id, String jobId, int revision, ShuttleSubjectV2026R0 source) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.revision = revision;
      this.source = source;
    }

    public Builder type(ShuttleDiscoveredSubjectV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttleDiscoveredSubjectV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttleDiscoveredSubjectV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder suggestedTarget(ShuttleTargetSubjectV2026R0 suggestedTarget) {
      this.suggestedTarget = suggestedTarget;
      return this;
    }

    public ShuttleDiscoveredSubjectV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttleDiscoveredSubjectV2026R0TypeField>(
                ShuttleDiscoveredSubjectV2026R0TypeField.SHUTTLE_DISCOVERED_SUBJECT);
      }
      return new ShuttleDiscoveredSubjectV2026R0(this);
    }
  }
}
