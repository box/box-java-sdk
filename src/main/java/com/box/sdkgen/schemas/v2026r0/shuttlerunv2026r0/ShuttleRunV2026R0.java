package com.box.sdkgen.schemas.v2026r0.shuttlerunv2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.v2026r0.userbasev2026r0.UserBaseV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

/**
 * A historical instance of a shuttle job run. A run is created each time a job is started. Run IDs
 * have the form `{job_id}:{run_number}`.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttleRunV2026R0 extends SerializableObject {

  /** The unique identifier for the shuttle run, in the form `{job_id}:{run_number}`. */
  protected final String id;

  /** The value will always be `shuttle_run`. */
  @JsonDeserialize(using = ShuttleRunV2026R0TypeField.ShuttleRunV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(using = ShuttleRunV2026R0TypeField.ShuttleRunV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttleRunV2026R0TypeField> type;

  /** The unique identifier of the shuttle job this run belongs to. */
  @JsonProperty("job_id")
  protected final String jobId;

  /**
   * The mode the run was started in. This is an open string whose set of values is expected to
   * grow; the currently documented values include `transfer`, `simulation`, `analysis`, `scan`, and
   * `unknown`. Clients should treat any value they do not recognize as `unknown`.
   */
  @JsonProperty("run_mode")
  protected final String runMode;

  /**
   * The current state of the shuttle run. This is an open string whose set of values is expected to
   * grow; the currently documented values include `running`, `stopping`, `completed`,
   * `completed_with_errors`, `stopped`, `failed`, `timed_out`, and `unknown`. Clients should treat
   * any value they do not recognize as `unknown`.
   */
  protected final String state;

  /** When the run started. */
  @JsonProperty("started_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime startedAt;

  /** When the run ended. `null` while the run is still in progress. */
  @JsonProperty("ended_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime endedAt;

  /** Total duration of the run in seconds. `null` while the run is still in progress. */
  @JsonProperty("duration_seconds")
  @Nullable
  protected Long durationSeconds;

  /** Transfer statistics collected during the run. */
  protected ShuttleRunV2026R0StatsField stats;

  /** The user who started the run. `null` for schedule- or system-initiated runs. */
  @JsonProperty("created_by")
  protected UserBaseV2026R0 createdBy;

  /**
   * Report sections that can be requested for this run. This is an open list whose set of values is
   * expected to grow; the currently documented values include `files_and_folders`, `event_log`,
   * `transfer_overview`, `source_users`, `target_users`, `source_collaborators`,
   * `target_collaborators`, `source_collaborations`, and `target_collaborations`.
   */
  @JsonProperty("available_report_sections")
  protected List<String> availableReportSections;

  public ShuttleRunV2026R0(
      @JsonProperty("id") String id,
      @JsonProperty("job_id") String jobId,
      @JsonProperty("run_mode") String runMode,
      @JsonProperty("state") String state) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.runMode = runMode;
    this.state = state;
    this.type = new EnumWrapper<ShuttleRunV2026R0TypeField>(ShuttleRunV2026R0TypeField.SHUTTLE_RUN);
  }

  protected ShuttleRunV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.jobId = builder.jobId;
    this.runMode = builder.runMode;
    this.state = builder.state;
    this.startedAt = builder.startedAt;
    this.endedAt = builder.endedAt;
    this.durationSeconds = builder.durationSeconds;
    this.stats = builder.stats;
    this.createdBy = builder.createdBy;
    this.availableReportSections = builder.availableReportSections;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShuttleRunV2026R0TypeField> getType() {
    return type;
  }

  public String getJobId() {
    return jobId;
  }

  public String getRunMode() {
    return runMode;
  }

  public String getState() {
    return state;
  }

  public OffsetDateTime getStartedAt() {
    return startedAt;
  }

  public OffsetDateTime getEndedAt() {
    return endedAt;
  }

  public Long getDurationSeconds() {
    return durationSeconds;
  }

  public ShuttleRunV2026R0StatsField getStats() {
    return stats;
  }

  public UserBaseV2026R0 getCreatedBy() {
    return createdBy;
  }

  public List<String> getAvailableReportSections() {
    return availableReportSections;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleRunV2026R0 casted = (ShuttleRunV2026R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(jobId, casted.jobId)
        && Objects.equals(runMode, casted.runMode)
        && Objects.equals(state, casted.state)
        && Objects.equals(startedAt, casted.startedAt)
        && Objects.equals(endedAt, casted.endedAt)
        && Objects.equals(durationSeconds, casted.durationSeconds)
        && Objects.equals(stats, casted.stats)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(availableReportSections, casted.availableReportSections);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        jobId,
        runMode,
        state,
        startedAt,
        endedAt,
        durationSeconds,
        stats,
        createdBy,
        availableReportSections);
  }

  @Override
  public String toString() {
    return "ShuttleRunV2026R0{"
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
        + "runMode='"
        + runMode
        + '\''
        + ", "
        + "state='"
        + state
        + '\''
        + ", "
        + "startedAt='"
        + startedAt
        + '\''
        + ", "
        + "endedAt='"
        + endedAt
        + '\''
        + ", "
        + "durationSeconds='"
        + durationSeconds
        + '\''
        + ", "
        + "stats='"
        + stats
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "availableReportSections='"
        + availableReportSections
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<ShuttleRunV2026R0TypeField> type;

    protected final String jobId;

    protected final String runMode;

    protected final String state;

    protected OffsetDateTime startedAt;

    protected OffsetDateTime endedAt;

    protected Long durationSeconds;

    protected ShuttleRunV2026R0StatsField stats;

    protected UserBaseV2026R0 createdBy;

    protected List<String> availableReportSections;

    public Builder(String id, String jobId, String runMode, String state) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.runMode = runMode;
      this.state = state;
    }

    public Builder type(ShuttleRunV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttleRunV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttleRunV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder startedAt(OffsetDateTime startedAt) {
      this.startedAt = startedAt;
      return this;
    }

    public Builder endedAt(OffsetDateTime endedAt) {
      this.endedAt = endedAt;
      this.markNullableFieldAsSet("ended_at");
      return this;
    }

    public Builder durationSeconds(Long durationSeconds) {
      this.durationSeconds = durationSeconds;
      this.markNullableFieldAsSet("duration_seconds");
      return this;
    }

    public Builder stats(ShuttleRunV2026R0StatsField stats) {
      this.stats = stats;
      return this;
    }

    public Builder createdBy(UserBaseV2026R0 createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder availableReportSections(List<String> availableReportSections) {
      this.availableReportSections = availableReportSections;
      return this;
    }

    public ShuttleRunV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttleRunV2026R0TypeField>(ShuttleRunV2026R0TypeField.SHUTTLE_RUN);
      }
      return new ShuttleRunV2026R0(this);
    }
  }
}
