package com.box.sdkgen.schemas.v2026r0.shuttlereportcreaterequestv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/**
 * The request body used to generate a report for a shuttle run. Generation is asynchronous: the
 * created report is returned immediately in `pending` or `processing` status. Poll `GET
 * /shuttle_reports/{shuttle_report_id}` while `status` is `pending` or `processing`. Stop when
 * `status` is `complete`, `failed`, `canceled`, or `unknown`. Repeating this request with the same
 * parameters reuses the existing report instead of creating a duplicate.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttleReportCreateRequestV2026R0 extends SerializableObject {

  /** The unique identifier of the shuttle run to generate a report for. */
  @JsonProperty("run_id")
  protected final String runId;

  /** The output file format of the report. */
  @JsonDeserialize(
      using =
          ShuttleReportCreateRequestV2026R0FormatField
              .ShuttleReportCreateRequestV2026R0FormatFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleReportCreateRequestV2026R0FormatField
              .ShuttleReportCreateRequestV2026R0FormatFieldSerializer.class)
  protected final EnumWrapper<ShuttleReportCreateRequestV2026R0FormatField> format;

  /**
   * The sections to include in the report. Must be a non-empty subset of the run's
   * `available_report_sections`. This is an open list whose set of values is expected to grow; the
   * currently documented values include `files_and_folders`, `event_log`, `transfer_overview`,
   * `source_users`, `target_users`, `source_collaborators`, `target_collaborators`,
   * `source_collaborations`, and `target_collaborations`.
   */
  protected final List<String> sections;

  /**
   * Which users to include in user-related report sections. Defaults to `migrated` when omitted.
   */
  @JsonDeserialize(
      using =
          ShuttleReportCreateRequestV2026R0UsersScopeField
              .ShuttleReportCreateRequestV2026R0UsersScopeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleReportCreateRequestV2026R0UsersScopeField
              .ShuttleReportCreateRequestV2026R0UsersScopeFieldSerializer.class)
  @JsonProperty("users_scope")
  protected EnumWrapper<ShuttleReportCreateRequestV2026R0UsersScopeField> usersScope;

  public ShuttleReportCreateRequestV2026R0(
      String runId, ShuttleReportCreateRequestV2026R0FormatField format, List<String> sections) {
    super();
    this.runId = runId;
    this.format = new EnumWrapper<ShuttleReportCreateRequestV2026R0FormatField>(format);
    this.sections = sections;
  }

  public ShuttleReportCreateRequestV2026R0(
      @JsonProperty("run_id") String runId,
      @JsonProperty("format") EnumWrapper<ShuttleReportCreateRequestV2026R0FormatField> format,
      @JsonProperty("sections") List<String> sections) {
    super();
    this.runId = runId;
    this.format = format;
    this.sections = sections;
  }

  protected ShuttleReportCreateRequestV2026R0(Builder builder) {
    super();
    this.runId = builder.runId;
    this.format = builder.format;
    this.sections = builder.sections;
    this.usersScope = builder.usersScope;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getRunId() {
    return runId;
  }

  public EnumWrapper<ShuttleReportCreateRequestV2026R0FormatField> getFormat() {
    return format;
  }

  public List<String> getSections() {
    return sections;
  }

  public EnumWrapper<ShuttleReportCreateRequestV2026R0UsersScopeField> getUsersScope() {
    return usersScope;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleReportCreateRequestV2026R0 casted = (ShuttleReportCreateRequestV2026R0) o;
    return Objects.equals(runId, casted.runId)
        && Objects.equals(format, casted.format)
        && Objects.equals(sections, casted.sections)
        && Objects.equals(usersScope, casted.usersScope);
  }

  @Override
  public int hashCode() {
    return Objects.hash(runId, format, sections, usersScope);
  }

  @Override
  public String toString() {
    return "ShuttleReportCreateRequestV2026R0{"
        + "runId='"
        + runId
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
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String runId;

    protected final EnumWrapper<ShuttleReportCreateRequestV2026R0FormatField> format;

    protected final List<String> sections;

    protected EnumWrapper<ShuttleReportCreateRequestV2026R0UsersScopeField> usersScope;

    public Builder(
        String runId, ShuttleReportCreateRequestV2026R0FormatField format, List<String> sections) {
      super();
      this.runId = runId;
      this.format = new EnumWrapper<ShuttleReportCreateRequestV2026R0FormatField>(format);
      this.sections = sections;
    }

    public Builder(
        String runId,
        EnumWrapper<ShuttleReportCreateRequestV2026R0FormatField> format,
        List<String> sections) {
      super();
      this.runId = runId;
      this.format = format;
      this.sections = sections;
    }

    public Builder usersScope(ShuttleReportCreateRequestV2026R0UsersScopeField usersScope) {
      this.usersScope =
          new EnumWrapper<ShuttleReportCreateRequestV2026R0UsersScopeField>(usersScope);
      return this;
    }

    public Builder usersScope(
        EnumWrapper<ShuttleReportCreateRequestV2026R0UsersScopeField> usersScope) {
      this.usersScope = usersScope;
      return this;
    }

    public ShuttleReportCreateRequestV2026R0 build() {
      return new ShuttleReportCreateRequestV2026R0(this);
    }
  }
}
