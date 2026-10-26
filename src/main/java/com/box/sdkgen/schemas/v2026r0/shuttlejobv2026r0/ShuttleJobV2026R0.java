package com.box.sdkgen.schemas.v2026r0.shuttlejobv2026r0;

import com.box.sdkgen.internal.Nullable;
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
 * A standard representation of a shuttle job. A shuttle job describes a migration of content from a
 * source system into Box, including its current state, mapping-preparation state, and mapping
 * configuration.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttleJobV2026R0 extends SerializableObject {

  /** The unique identifier for the shuttle job. */
  protected final String id;

  /** The value will always be `shuttle_job`. */
  @JsonDeserialize(using = ShuttleJobV2026R0TypeField.ShuttleJobV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(using = ShuttleJobV2026R0TypeField.ShuttleJobV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttleJobV2026R0TypeField> type;

  /** The human-readable name of the shuttle job. */
  protected final String name;

  /** An optional free-form comment describing the shuttle job. */
  @Nullable protected String comment;

  /** The type of work the shuttle job performs. */
  @JsonDeserialize(
      using = ShuttleJobV2026R0JobTypeField.ShuttleJobV2026R0JobTypeFieldDeserializer.class)
  @JsonSerialize(
      using = ShuttleJobV2026R0JobTypeField.ShuttleJobV2026R0JobTypeFieldSerializer.class)
  @JsonProperty("job_type")
  protected final EnumWrapper<ShuttleJobV2026R0JobTypeField> jobType;

  /**
   * The current state of the shuttle job. This is an open string whose set of values is expected to
   * grow; the currently documented values include `created`, `started`, `stopped`, `completed`,
   * `failed`, `invalid`, and `unknown`. Clients should treat any value they do not recognize as
   * `unknown`.
   */
  protected final String state;

  /**
   * The current mapping-preparation state of the job. This is an open string whose set of values is
   * expected to grow; the currently documented values include `awaiting_path_mapping`,
   * `importing_paths`, `paths_import_rejected`, `paths_invalid`, `paths_ready`, `scanning_source`,
   * `source_scan_failed`, `source_scan_out_of_date`, `awaiting_subject_mapping`,
   * `importing_subjects`, `subjects_import_rejected`, `mapping_complete`, `building_map`,
   * `map_build_failed`, `map_out_of_date`, and `ready`. `paths_import_rejected` means the last
   * path-mapping spreadsheet was rejected or failed and the path mappings have not been changed
   * since; it clears on the next import or the next manual path-mapping change.
   * `subjects_import_rejected` means the last subject-mapping spreadsheet was rejected or failed
   * and the subject mappings have not been changed since; it clears on the next import or the next
   * manual subject-mapping change.
   */
  @JsonProperty("mapping_state")
  protected final String mappingState;

  /** The source system the shuttle job migrates content from. */
  @JsonProperty("source_system")
  protected final ShuttleJobV2026R0SourceSystemField sourceSystem;

  /**
   * The account mapping configuration that controls how source identities and permissions are
   * translated into Box.
   */
  @JsonProperty("account_mapping")
  protected ShuttleJobV2026R0AccountMappingField accountMapping;

  /** A list of free-form tags associated with the shuttle job. */
  protected List<String> tags;

  /** The tunable settings that control how the shuttle job runs. */
  protected ShuttleJobV2026R0SettingsField settings;

  public ShuttleJobV2026R0(
      String id,
      String name,
      ShuttleJobV2026R0JobTypeField jobType,
      String state,
      String mappingState,
      ShuttleJobV2026R0SourceSystemField sourceSystem) {
    super();
    this.id = id;
    this.name = name;
    this.jobType = new EnumWrapper<ShuttleJobV2026R0JobTypeField>(jobType);
    this.state = state;
    this.mappingState = mappingState;
    this.sourceSystem = sourceSystem;
    this.type = new EnumWrapper<ShuttleJobV2026R0TypeField>(ShuttleJobV2026R0TypeField.SHUTTLE_JOB);
  }

  public ShuttleJobV2026R0(
      @JsonProperty("id") String id,
      @JsonProperty("name") String name,
      @JsonProperty("job_type") EnumWrapper<ShuttleJobV2026R0JobTypeField> jobType,
      @JsonProperty("state") String state,
      @JsonProperty("mapping_state") String mappingState,
      @JsonProperty("source_system") ShuttleJobV2026R0SourceSystemField sourceSystem) {
    super();
    this.id = id;
    this.name = name;
    this.jobType = jobType;
    this.state = state;
    this.mappingState = mappingState;
    this.sourceSystem = sourceSystem;
    this.type = new EnumWrapper<ShuttleJobV2026R0TypeField>(ShuttleJobV2026R0TypeField.SHUTTLE_JOB);
  }

  protected ShuttleJobV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    this.comment = builder.comment;
    this.jobType = builder.jobType;
    this.state = builder.state;
    this.mappingState = builder.mappingState;
    this.sourceSystem = builder.sourceSystem;
    this.accountMapping = builder.accountMapping;
    this.tags = builder.tags;
    this.settings = builder.settings;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShuttleJobV2026R0TypeField> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getComment() {
    return comment;
  }

  public EnumWrapper<ShuttleJobV2026R0JobTypeField> getJobType() {
    return jobType;
  }

  public String getState() {
    return state;
  }

  public String getMappingState() {
    return mappingState;
  }

  public ShuttleJobV2026R0SourceSystemField getSourceSystem() {
    return sourceSystem;
  }

  public ShuttleJobV2026R0AccountMappingField getAccountMapping() {
    return accountMapping;
  }

  public List<String> getTags() {
    return tags;
  }

  public ShuttleJobV2026R0SettingsField getSettings() {
    return settings;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleJobV2026R0 casted = (ShuttleJobV2026R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(comment, casted.comment)
        && Objects.equals(jobType, casted.jobType)
        && Objects.equals(state, casted.state)
        && Objects.equals(mappingState, casted.mappingState)
        && Objects.equals(sourceSystem, casted.sourceSystem)
        && Objects.equals(accountMapping, casted.accountMapping)
        && Objects.equals(tags, casted.tags)
        && Objects.equals(settings, casted.settings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        name,
        comment,
        jobType,
        state,
        mappingState,
        sourceSystem,
        accountMapping,
        tags,
        settings);
  }

  @Override
  public String toString() {
    return "ShuttleJobV2026R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "comment='"
        + comment
        + '\''
        + ", "
        + "jobType='"
        + jobType
        + '\''
        + ", "
        + "state='"
        + state
        + '\''
        + ", "
        + "mappingState='"
        + mappingState
        + '\''
        + ", "
        + "sourceSystem='"
        + sourceSystem
        + '\''
        + ", "
        + "accountMapping='"
        + accountMapping
        + '\''
        + ", "
        + "tags='"
        + tags
        + '\''
        + ", "
        + "settings='"
        + settings
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<ShuttleJobV2026R0TypeField> type;

    protected final String name;

    protected String comment;

    protected final EnumWrapper<ShuttleJobV2026R0JobTypeField> jobType;

    protected final String state;

    protected final String mappingState;

    protected final ShuttleJobV2026R0SourceSystemField sourceSystem;

    protected ShuttleJobV2026R0AccountMappingField accountMapping;

    protected List<String> tags;

    protected ShuttleJobV2026R0SettingsField settings;

    public Builder(
        String id,
        String name,
        ShuttleJobV2026R0JobTypeField jobType,
        String state,
        String mappingState,
        ShuttleJobV2026R0SourceSystemField sourceSystem) {
      super();
      this.id = id;
      this.name = name;
      this.jobType = new EnumWrapper<ShuttleJobV2026R0JobTypeField>(jobType);
      this.state = state;
      this.mappingState = mappingState;
      this.sourceSystem = sourceSystem;
    }

    public Builder(
        String id,
        String name,
        EnumWrapper<ShuttleJobV2026R0JobTypeField> jobType,
        String state,
        String mappingState,
        ShuttleJobV2026R0SourceSystemField sourceSystem) {
      super();
      this.id = id;
      this.name = name;
      this.jobType = jobType;
      this.state = state;
      this.mappingState = mappingState;
      this.sourceSystem = sourceSystem;
    }

    public Builder type(ShuttleJobV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttleJobV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttleJobV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder comment(String comment) {
      this.comment = comment;
      this.markNullableFieldAsSet("comment");
      return this;
    }

    public Builder accountMapping(ShuttleJobV2026R0AccountMappingField accountMapping) {
      this.accountMapping = accountMapping;
      return this;
    }

    public Builder tags(List<String> tags) {
      this.tags = tags;
      return this;
    }

    public Builder settings(ShuttleJobV2026R0SettingsField settings) {
      this.settings = settings;
      return this;
    }

    public ShuttleJobV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttleJobV2026R0TypeField>(ShuttleJobV2026R0TypeField.SHUTTLE_JOB);
      }
      return new ShuttleJobV2026R0(this);
    }
  }
}
