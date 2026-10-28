package com.box.sdkgen.schemas.v2026r0.shuttlereportv2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.v2026r0.shuttlereportoutputv2026r0.ShuttleReportOutputV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

/**
 * A report generated asynchronously for a shuttle job run. Keep polling while `status` is `pending`
 * or `processing`. Stop when `status` is `complete`, `failed`, `canceled`, or `unknown`. The
 * generated Box file or folder is `null` until generation finishes.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttleReportV2026R0 extends SerializableObject {

  /** The unique identifier for the shuttle report. */
  protected final String id;

  /** The value will always be `shuttle_report`. */
  @JsonDeserialize(
      using = ShuttleReportV2026R0TypeField.ShuttleReportV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = ShuttleReportV2026R0TypeField.ShuttleReportV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttleReportV2026R0TypeField> type;

  /** The unique identifier of the shuttle run this report was generated for. */
  @JsonProperty("run_id")
  protected final String runId;

  /**
   * The generation status of the report. This is an open string whose set of values is expected to
   * grow; the currently documented values include `pending`, `processing`, `complete`, `failed`,
   * `canceled`, and `unknown`. Keep polling `pending` and `processing`. Stop on `complete`,
   * `failed`, `canceled`, and `unknown`. Treat any value that is not recognized the same as
   * `unknown`: do not fail parsing, and do not keep polling.
   */
  protected final String status;

  /** The output file format of the report. */
  @JsonDeserialize(
      using = ShuttleReportV2026R0FormatField.ShuttleReportV2026R0FormatFieldDeserializer.class)
  @JsonSerialize(
      using = ShuttleReportV2026R0FormatField.ShuttleReportV2026R0FormatFieldSerializer.class)
  protected final EnumWrapper<ShuttleReportV2026R0FormatField> format;

  /**
   * The sections included in the report. Echoes the sections requested on create. This is an open
   * list whose set of values is expected to grow; the currently documented values include
   * `files_and_folders`, `event_log`, `transfer_overview`, `source_users`, `target_users`,
   * `source_collaborators`, `target_collaborators`, `source_collaborations`, and
   * `target_collaborations`. Omitted on older reports that have no stored section list. New reports
   * always include this field.
   */
  protected List<String> sections;

  /** Which users are included in user-related report sections. */
  @JsonDeserialize(
      using =
          ShuttleReportV2026R0UsersScopeField.ShuttleReportV2026R0UsersScopeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleReportV2026R0UsersScopeField.ShuttleReportV2026R0UsersScopeFieldSerializer.class)
  @JsonProperty("users_scope")
  protected EnumWrapper<ShuttleReportV2026R0UsersScopeField> usersScope;

  /** The generated Box file or folder. Always present; `null` until `status` is `complete`. */
  protected ShuttleReportOutputV2026R0 output;

  /** When report generation was requested. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  /**
   * When report generation finished. Always present; `null` while generation is still in progress.
   */
  @JsonProperty("completed_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime completedAt;

  public ShuttleReportV2026R0(
      String id, String runId, String status, ShuttleReportV2026R0FormatField format) {
    super();
    this.id = id;
    this.runId = runId;
    this.status = status;
    this.format = new EnumWrapper<ShuttleReportV2026R0FormatField>(format);
    this.type =
        new EnumWrapper<ShuttleReportV2026R0TypeField>(
            ShuttleReportV2026R0TypeField.SHUTTLE_REPORT);
  }

  public ShuttleReportV2026R0(
      @JsonProperty("id") String id,
      @JsonProperty("run_id") String runId,
      @JsonProperty("status") String status,
      @JsonProperty("format") EnumWrapper<ShuttleReportV2026R0FormatField> format) {
    super();
    this.id = id;
    this.runId = runId;
    this.status = status;
    this.format = format;
    this.type =
        new EnumWrapper<ShuttleReportV2026R0TypeField>(
            ShuttleReportV2026R0TypeField.SHUTTLE_REPORT);
  }

  protected ShuttleReportV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.runId = builder.runId;
    this.status = builder.status;
    this.format = builder.format;
    this.sections = builder.sections;
    this.usersScope = builder.usersScope;
    this.output = builder.output;
    this.createdAt = builder.createdAt;
    this.completedAt = builder.completedAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShuttleReportV2026R0TypeField> getType() {
    return type;
  }

  public String getRunId() {
    return runId;
  }

  public String getStatus() {
    return status;
  }

  public EnumWrapper<ShuttleReportV2026R0FormatField> getFormat() {
    return format;
  }

  public List<String> getSections() {
    return sections;
  }

  public EnumWrapper<ShuttleReportV2026R0UsersScopeField> getUsersScope() {
    return usersScope;
  }

  public ShuttleReportOutputV2026R0 getOutput() {
    return output;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getCompletedAt() {
    return completedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleReportV2026R0 casted = (ShuttleReportV2026R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(runId, casted.runId)
        && Objects.equals(status, casted.status)
        && Objects.equals(format, casted.format)
        && Objects.equals(sections, casted.sections)
        && Objects.equals(usersScope, casted.usersScope)
        && Objects.equals(output, casted.output)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(completedAt, casted.completedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, type, runId, status, format, sections, usersScope, output, createdAt, completedAt);
  }

  @Override
  public String toString() {
    return "ShuttleReportV2026R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "runId='"
        + runId
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "format='"
        + format
        + '\''
        + ", "
        + "sections='"
        + sections
        + '\''
        + ", "
        + "usersScope='"
        + usersScope
        + '\''
        + ", "
        + "output='"
        + output
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "completedAt='"
        + completedAt
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<ShuttleReportV2026R0TypeField> type;

    protected final String runId;

    protected final String status;

    protected final EnumWrapper<ShuttleReportV2026R0FormatField> format;

    protected List<String> sections;

    protected EnumWrapper<ShuttleReportV2026R0UsersScopeField> usersScope;

    protected ShuttleReportOutputV2026R0 output;

    protected OffsetDateTime createdAt;

    protected OffsetDateTime completedAt;

    public Builder(String id, String runId, String status, ShuttleReportV2026R0FormatField format) {
      super();
      this.id = id;
      this.runId = runId;
      this.status = status;
      this.format = new EnumWrapper<ShuttleReportV2026R0FormatField>(format);
    }

    public Builder(
        String id,
        String runId,
        String status,
        EnumWrapper<ShuttleReportV2026R0FormatField> format) {
      super();
      this.id = id;
      this.runId = runId;
      this.status = status;
      this.format = format;
    }

    public Builder type(ShuttleReportV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttleReportV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttleReportV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder sections(List<String> sections) {
      this.sections = sections;
      return this;
    }

    public Builder usersScope(ShuttleReportV2026R0UsersScopeField usersScope) {
      this.usersScope = new EnumWrapper<ShuttleReportV2026R0UsersScopeField>(usersScope);
      return this;
    }

    public Builder usersScope(EnumWrapper<ShuttleReportV2026R0UsersScopeField> usersScope) {
      this.usersScope = usersScope;
      return this;
    }

    public Builder output(ShuttleReportOutputV2026R0 output) {
      this.output = output;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder completedAt(OffsetDateTime completedAt) {
      this.completedAt = completedAt;
      this.markNullableFieldAsSet("completed_at");
      return this;
    }

    public ShuttleReportV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttleReportV2026R0TypeField>(
                ShuttleReportV2026R0TypeField.SHUTTLE_REPORT);
      }
      return new ShuttleReportV2026R0(this);
    }
  }
}
