package com.box.sdkgen.schemas.v2026r0.shuttlemappingerrorv2026r0;

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
 * A problem encountered while preparing path, subject, or permission mappings for a shuttle job.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttleMappingErrorV2026R0 extends SerializableObject {

  /** The unique identifier for the mapping error. */
  protected final String id;

  /** The value will always be `shuttle_mapping_error`. */
  @JsonDeserialize(
      using =
          ShuttleMappingErrorV2026R0TypeField.ShuttleMappingErrorV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleMappingErrorV2026R0TypeField.ShuttleMappingErrorV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttleMappingErrorV2026R0TypeField> type;

  /** The unique identifier of the shuttle job this error belongs to. */
  @JsonProperty("job_id")
  protected final String jobId;

  /** The mapping revision this error belongs to. */
  protected final int revision;

  /**
   * The category of mapping work that produced the error. This is an open string whose set of
   * values can grow.
   */
  protected final String category;

  /** The path or subject that the mapping error refers to. */
  protected final String path;

  /** Human-readable messages describing the mapping error. */
  protected final List<String> messages;

  public ShuttleMappingErrorV2026R0(
      @JsonProperty("id") String id,
      @JsonProperty("job_id") String jobId,
      @JsonProperty("revision") int revision,
      @JsonProperty("category") String category,
      @JsonProperty("path") String path,
      @JsonProperty("messages") List<String> messages) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.revision = revision;
    this.category = category;
    this.path = path;
    this.messages = messages;
    this.type =
        new EnumWrapper<ShuttleMappingErrorV2026R0TypeField>(
            ShuttleMappingErrorV2026R0TypeField.SHUTTLE_MAPPING_ERROR);
  }

  protected ShuttleMappingErrorV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.jobId = builder.jobId;
    this.revision = builder.revision;
    this.category = builder.category;
    this.path = builder.path;
    this.messages = builder.messages;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShuttleMappingErrorV2026R0TypeField> getType() {
    return type;
  }

  public String getJobId() {
    return jobId;
  }

  public int getRevision() {
    return revision;
  }

  public String getCategory() {
    return category;
  }

  public String getPath() {
    return path;
  }

  public List<String> getMessages() {
    return messages;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleMappingErrorV2026R0 casted = (ShuttleMappingErrorV2026R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(jobId, casted.jobId)
        && Objects.equals(revision, casted.revision)
        && Objects.equals(category, casted.category)
        && Objects.equals(path, casted.path)
        && Objects.equals(messages, casted.messages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, jobId, revision, category, path, messages);
  }

  @Override
  public String toString() {
    return "ShuttleMappingErrorV2026R0{"
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
        + "category='"
        + category
        + '\''
        + ", "
        + "path='"
        + path
        + '\''
        + ", "
        + "messages='"
        + messages
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<ShuttleMappingErrorV2026R0TypeField> type;

    protected final String jobId;

    protected final int revision;

    protected final String category;

    protected final String path;

    protected final List<String> messages;

    public Builder(
        String id,
        String jobId,
        int revision,
        String category,
        String path,
        List<String> messages) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.revision = revision;
      this.category = category;
      this.path = path;
      this.messages = messages;
    }

    public Builder type(ShuttleMappingErrorV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttleMappingErrorV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttleMappingErrorV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public ShuttleMappingErrorV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttleMappingErrorV2026R0TypeField>(
                ShuttleMappingErrorV2026R0TypeField.SHUTTLE_MAPPING_ERROR);
      }
      return new ShuttleMappingErrorV2026R0(this);
    }
  }
}
