package com.box.sdkgen.schemas.v2026r0.shuttlejobv2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ShuttleJobV2026R0SettingsField extends SerializableObject {

  /** The maximum transfer bandwidth in bytes per second. `null` when unlimited. */
  @JsonProperty("bandwidth_limit_bytes_per_sec")
  @Nullable
  protected Long bandwidthLimitBytesPerSec;

  /** Whether deletions in the source system are mirrored in Box. */
  @JsonProperty("are_deletions_mirrored")
  protected Boolean areDeletionsMirrored;

  /** Whether prior file versions are synced during the migration. */
  @JsonProperty("are_file_versions_synced")
  protected Boolean areFileVersionsSynced;

  /** The maximum number of file versions to sync. `null` when unlimited. */
  @JsonProperty("max_file_versions")
  @Nullable
  protected Integer maxFileVersions;

  /** Whether existing files in Box are overwritten. */
  @JsonProperty("is_overwrite_enabled")
  protected Boolean isOverwriteEnabled;

  /** Whether mappings that already completed successfully are skipped when the job is rerun. */
  @JsonProperty("are_completed_mappings_skipped_on_rerun")
  protected Boolean areCompletedMappingsSkippedOnRerun;

  /**
   * Whether migrated content is scanned during the migration. Applies only when the source system
   * supports content scanning; ignored otherwise.
   */
  @JsonProperty("is_content_scanning_enabled")
  protected Boolean isContentScanningEnabled;

  /**
   * Whether shared folders on the source are included in the migration. Applies only when the
   * source system supports shared folders; ignored otherwise.
   */
  @JsonProperty("include_shared_source_folders")
  protected Boolean includeSharedSourceFolders;

  /**
   * Whether shared folders on the destination are included in the migration. Applies only when the
   * destination system supports shared folders; ignored otherwise.
   */
  @JsonProperty("include_shared_destination_folders")
  protected Boolean includeSharedDestinationFolders;

  public ShuttleJobV2026R0SettingsField() {
    super();
  }

  protected ShuttleJobV2026R0SettingsField(Builder builder) {
    super();
    this.bandwidthLimitBytesPerSec = builder.bandwidthLimitBytesPerSec;
    this.areDeletionsMirrored = builder.areDeletionsMirrored;
    this.areFileVersionsSynced = builder.areFileVersionsSynced;
    this.maxFileVersions = builder.maxFileVersions;
    this.isOverwriteEnabled = builder.isOverwriteEnabled;
    this.areCompletedMappingsSkippedOnRerun = builder.areCompletedMappingsSkippedOnRerun;
    this.isContentScanningEnabled = builder.isContentScanningEnabled;
    this.includeSharedSourceFolders = builder.includeSharedSourceFolders;
    this.includeSharedDestinationFolders = builder.includeSharedDestinationFolders;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Long getBandwidthLimitBytesPerSec() {
    return bandwidthLimitBytesPerSec;
  }

  public Boolean getAreDeletionsMirrored() {
    return areDeletionsMirrored;
  }

  public Boolean getAreFileVersionsSynced() {
    return areFileVersionsSynced;
  }

  public Integer getMaxFileVersions() {
    return maxFileVersions;
  }

  public Boolean getIsOverwriteEnabled() {
    return isOverwriteEnabled;
  }

  public Boolean getAreCompletedMappingsSkippedOnRerun() {
    return areCompletedMappingsSkippedOnRerun;
  }

  public Boolean getIsContentScanningEnabled() {
    return isContentScanningEnabled;
  }

  public Boolean getIncludeSharedSourceFolders() {
    return includeSharedSourceFolders;
  }

  public Boolean getIncludeSharedDestinationFolders() {
    return includeSharedDestinationFolders;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleJobV2026R0SettingsField casted = (ShuttleJobV2026R0SettingsField) o;
    return Objects.equals(bandwidthLimitBytesPerSec, casted.bandwidthLimitBytesPerSec)
        && Objects.equals(areDeletionsMirrored, casted.areDeletionsMirrored)
        && Objects.equals(areFileVersionsSynced, casted.areFileVersionsSynced)
        && Objects.equals(maxFileVersions, casted.maxFileVersions)
        && Objects.equals(isOverwriteEnabled, casted.isOverwriteEnabled)
        && Objects.equals(
            areCompletedMappingsSkippedOnRerun, casted.areCompletedMappingsSkippedOnRerun)
        && Objects.equals(isContentScanningEnabled, casted.isContentScanningEnabled)
        && Objects.equals(includeSharedSourceFolders, casted.includeSharedSourceFolders)
        && Objects.equals(includeSharedDestinationFolders, casted.includeSharedDestinationFolders);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        bandwidthLimitBytesPerSec,
        areDeletionsMirrored,
        areFileVersionsSynced,
        maxFileVersions,
        isOverwriteEnabled,
        areCompletedMappingsSkippedOnRerun,
        isContentScanningEnabled,
        includeSharedSourceFolders,
        includeSharedDestinationFolders);
  }

  @Override
  public String toString() {
    return "ShuttleJobV2026R0SettingsField{"
        + "bandwidthLimitBytesPerSec='"
        + bandwidthLimitBytesPerSec
        + '\''
        + ", "
        + "areDeletionsMirrored='"
        + areDeletionsMirrored
        + '\''
        + ", "
        + "areFileVersionsSynced='"
        + areFileVersionsSynced
        + '\''
        + ", "
        + "maxFileVersions='"
        + maxFileVersions
        + '\''
        + ", "
        + "isOverwriteEnabled='"
        + isOverwriteEnabled
        + '\''
        + ", "
        + "areCompletedMappingsSkippedOnRerun='"
        + areCompletedMappingsSkippedOnRerun
        + '\''
        + ", "
        + "isContentScanningEnabled='"
        + isContentScanningEnabled
        + '\''
        + ", "
        + "includeSharedSourceFolders='"
        + includeSharedSourceFolders
        + '\''
        + ", "
        + "includeSharedDestinationFolders='"
        + includeSharedDestinationFolders
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Long bandwidthLimitBytesPerSec;

    protected Boolean areDeletionsMirrored;

    protected Boolean areFileVersionsSynced;

    protected Integer maxFileVersions;

    protected Boolean isOverwriteEnabled;

    protected Boolean areCompletedMappingsSkippedOnRerun;

    protected Boolean isContentScanningEnabled;

    protected Boolean includeSharedSourceFolders;

    protected Boolean includeSharedDestinationFolders;

    public Builder bandwidthLimitBytesPerSec(Long bandwidthLimitBytesPerSec) {
      this.bandwidthLimitBytesPerSec = bandwidthLimitBytesPerSec;
      this.markNullableFieldAsSet("bandwidth_limit_bytes_per_sec");
      return this;
    }

    public Builder areDeletionsMirrored(Boolean areDeletionsMirrored) {
      this.areDeletionsMirrored = areDeletionsMirrored;
      return this;
    }

    public Builder areFileVersionsSynced(Boolean areFileVersionsSynced) {
      this.areFileVersionsSynced = areFileVersionsSynced;
      return this;
    }

    public Builder maxFileVersions(Integer maxFileVersions) {
      this.maxFileVersions = maxFileVersions;
      this.markNullableFieldAsSet("max_file_versions");
      return this;
    }

    public Builder isOverwriteEnabled(Boolean isOverwriteEnabled) {
      this.isOverwriteEnabled = isOverwriteEnabled;
      return this;
    }

    public Builder areCompletedMappingsSkippedOnRerun(Boolean areCompletedMappingsSkippedOnRerun) {
      this.areCompletedMappingsSkippedOnRerun = areCompletedMappingsSkippedOnRerun;
      return this;
    }

    public Builder isContentScanningEnabled(Boolean isContentScanningEnabled) {
      this.isContentScanningEnabled = isContentScanningEnabled;
      return this;
    }

    public Builder includeSharedSourceFolders(Boolean includeSharedSourceFolders) {
      this.includeSharedSourceFolders = includeSharedSourceFolders;
      return this;
    }

    public Builder includeSharedDestinationFolders(Boolean includeSharedDestinationFolders) {
      this.includeSharedDestinationFolders = includeSharedDestinationFolders;
      return this;
    }

    public ShuttleJobV2026R0SettingsField build() {
      return new ShuttleJobV2026R0SettingsField(this);
    }
  }
}
