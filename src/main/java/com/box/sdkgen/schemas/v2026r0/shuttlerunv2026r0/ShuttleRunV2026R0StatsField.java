package com.box.sdkgen.schemas.v2026r0.shuttlerunv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ShuttleRunV2026R0StatsField extends SerializableObject {

  /** Bytes transferred during the run. */
  @JsonProperty("bytes_sent")
  protected Long bytesSent;

  /** Files transferred during the run. */
  @JsonProperty("files_sent")
  protected Long filesSent;

  /** Folders created on the destination during the run. */
  @JsonProperty("folders_created")
  protected Long foldersCreated;

  /** Whether these figures are estimates rather than final counts. */
  @JsonProperty("is_estimated")
  protected Boolean isEstimated;

  public ShuttleRunV2026R0StatsField() {
    super();
  }

  protected ShuttleRunV2026R0StatsField(Builder builder) {
    super();
    this.bytesSent = builder.bytesSent;
    this.filesSent = builder.filesSent;
    this.foldersCreated = builder.foldersCreated;
    this.isEstimated = builder.isEstimated;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Long getBytesSent() {
    return bytesSent;
  }

  public Long getFilesSent() {
    return filesSent;
  }

  public Long getFoldersCreated() {
    return foldersCreated;
  }

  public Boolean getIsEstimated() {
    return isEstimated;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleRunV2026R0StatsField casted = (ShuttleRunV2026R0StatsField) o;
    return Objects.equals(bytesSent, casted.bytesSent)
        && Objects.equals(filesSent, casted.filesSent)
        && Objects.equals(foldersCreated, casted.foldersCreated)
        && Objects.equals(isEstimated, casted.isEstimated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bytesSent, filesSent, foldersCreated, isEstimated);
  }

  @Override
  public String toString() {
    return "ShuttleRunV2026R0StatsField{"
        + "bytesSent='"
        + bytesSent
        + '\''
        + ", "
        + "filesSent='"
        + filesSent
        + '\''
        + ", "
        + "foldersCreated='"
        + foldersCreated
        + '\''
        + ", "
        + "isEstimated='"
        + isEstimated
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Long bytesSent;

    protected Long filesSent;

    protected Long foldersCreated;

    protected Boolean isEstimated;

    public Builder bytesSent(Long bytesSent) {
      this.bytesSent = bytesSent;
      return this;
    }

    public Builder filesSent(Long filesSent) {
      this.filesSent = filesSent;
      return this;
    }

    public Builder foldersCreated(Long foldersCreated) {
      this.foldersCreated = foldersCreated;
      return this;
    }

    public Builder isEstimated(Boolean isEstimated) {
      this.isEstimated = isEstimated;
      return this;
    }

    public ShuttleRunV2026R0StatsField build() {
      return new ShuttleRunV2026R0StatsField(this);
    }
  }
}
