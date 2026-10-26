package com.box.sdkgen.schemas.v2026r0.shuttlejobcreaterequestv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** The request body used to create a new shuttle job. */
@JsonFilter("nullablePropertyFilter")
public class ShuttleJobCreateRequestV2026R0 extends SerializableObject {

  /** The human-readable name of the shuttle job. */
  protected final String name;

  /** The type of work the shuttle job performs. */
  @JsonDeserialize(
      using =
          ShuttleJobCreateRequestV2026R0JobTypeField
              .ShuttleJobCreateRequestV2026R0JobTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleJobCreateRequestV2026R0JobTypeField
              .ShuttleJobCreateRequestV2026R0JobTypeFieldSerializer.class)
  @JsonProperty("job_type")
  protected final EnumWrapper<ShuttleJobCreateRequestV2026R0JobTypeField> jobType;

  /** The unique identifier of the source system to migrate from. */
  @JsonProperty("source_system_id")
  protected final String sourceSystemId;

  /** An optional free-form comment describing the shuttle job. */
  protected String comment;

  /** A list of free-form tags associated with the shuttle job. */
  protected List<String> tags;

  /**
   * The account mapping configuration that controls how source identities and permissions are
   * translated into Box. When this object is omitted, the job is created with `is_enabled` set to
   * `false`.
   */
  @JsonProperty("account_mapping")
  protected ShuttleJobCreateRequestV2026R0AccountMappingField accountMapping;

  /** The tunable settings that control how the shuttle job runs. */
  protected ShuttleJobCreateRequestV2026R0SettingsField settings;

  public ShuttleJobCreateRequestV2026R0(
      String name, ShuttleJobCreateRequestV2026R0JobTypeField jobType, String sourceSystemId) {
    super();
    this.name = name;
    this.jobType = new EnumWrapper<ShuttleJobCreateRequestV2026R0JobTypeField>(jobType);
    this.sourceSystemId = sourceSystemId;
  }

  public ShuttleJobCreateRequestV2026R0(
      @JsonProperty("name") String name,
      @JsonProperty("job_type") EnumWrapper<ShuttleJobCreateRequestV2026R0JobTypeField> jobType,
      @JsonProperty("source_system_id") String sourceSystemId) {
    super();
    this.name = name;
    this.jobType = jobType;
    this.sourceSystemId = sourceSystemId;
  }

  protected ShuttleJobCreateRequestV2026R0(Builder builder) {
    super();
    this.name = builder.name;
    this.jobType = builder.jobType;
    this.sourceSystemId = builder.sourceSystemId;
    this.comment = builder.comment;
    this.tags = builder.tags;
    this.accountMapping = builder.accountMapping;
    this.settings = builder.settings;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public EnumWrapper<ShuttleJobCreateRequestV2026R0JobTypeField> getJobType() {
    return jobType;
  }

  public String getSourceSystemId() {
    return sourceSystemId;
  }

  public String getComment() {
    return comment;
  }

  public List<String> getTags() {
    return tags;
  }

  public ShuttleJobCreateRequestV2026R0AccountMappingField getAccountMapping() {
    return accountMapping;
  }

  public ShuttleJobCreateRequestV2026R0SettingsField getSettings() {
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
    ShuttleJobCreateRequestV2026R0 casted = (ShuttleJobCreateRequestV2026R0) o;
    return Objects.equals(name, casted.name)
        && Objects.equals(jobType, casted.jobType)
        && Objects.equals(sourceSystemId, casted.sourceSystemId)
        && Objects.equals(comment, casted.comment)
        && Objects.equals(tags, casted.tags)
        && Objects.equals(accountMapping, casted.accountMapping)
        && Objects.equals(settings, casted.settings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, jobType, sourceSystemId, comment, tags, accountMapping, settings);
  }

  @Override
  public String toString() {
    return "ShuttleJobCreateRequestV2026R0{"
        + "name='"
        + name
        + '\''
        + ", "
        + "jobType='"
        + jobType
        + '\''
        + ", "
        + "sourceSystemId='"
        + sourceSystemId
        + '\''
        + ", "
        + "comment='"
        + comment
        + '\''
        + ", "
        + "tags='"
        + tags
        + '\''
        + ", "
        + "accountMapping='"
        + accountMapping
        + '\''
        + ", "
        + "settings='"
        + settings
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String name;

    protected final EnumWrapper<ShuttleJobCreateRequestV2026R0JobTypeField> jobType;

    protected final String sourceSystemId;

    protected String comment;

    protected List<String> tags;

    protected ShuttleJobCreateRequestV2026R0AccountMappingField accountMapping;

    protected ShuttleJobCreateRequestV2026R0SettingsField settings;

    public Builder(
        String name, ShuttleJobCreateRequestV2026R0JobTypeField jobType, String sourceSystemId) {
      super();
      this.name = name;
      this.jobType = new EnumWrapper<ShuttleJobCreateRequestV2026R0JobTypeField>(jobType);
      this.sourceSystemId = sourceSystemId;
    }

    public Builder(
        String name,
        EnumWrapper<ShuttleJobCreateRequestV2026R0JobTypeField> jobType,
        String sourceSystemId) {
      super();
      this.name = name;
      this.jobType = jobType;
      this.sourceSystemId = sourceSystemId;
    }

    public Builder comment(String comment) {
      this.comment = comment;
      return this;
    }

    public Builder tags(List<String> tags) {
      this.tags = tags;
      return this;
    }

    public Builder accountMapping(
        ShuttleJobCreateRequestV2026R0AccountMappingField accountMapping) {
      this.accountMapping = accountMapping;
      return this;
    }

    public Builder settings(ShuttleJobCreateRequestV2026R0SettingsField settings) {
      this.settings = settings;
      return this;
    }

    public ShuttleJobCreateRequestV2026R0 build() {
      return new ShuttleJobCreateRequestV2026R0(this);
    }
  }
}
