package com.box.sdkgen.managers.metadatacascadepolicies;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ApplyMetadataCascadePolicyRequestBody extends SerializableObject {

  @JsonDeserialize(
      using =
          ApplyMetadataCascadePolicyRequestBodyConflictResolutionField
              .ApplyMetadataCascadePolicyRequestBodyConflictResolutionFieldDeserializer.class)
  @JsonSerialize(
      using =
          ApplyMetadataCascadePolicyRequestBodyConflictResolutionField
              .ApplyMetadataCascadePolicyRequestBodyConflictResolutionFieldSerializer.class)
  @JsonProperty("conflict_resolution")
  protected final EnumWrapper<ApplyMetadataCascadePolicyRequestBodyConflictResolutionField>
      conflictResolution;

  public ApplyMetadataCascadePolicyRequestBody(
      ApplyMetadataCascadePolicyRequestBodyConflictResolutionField conflictResolution) {
    super();
    this.conflictResolution =
        new EnumWrapper<ApplyMetadataCascadePolicyRequestBodyConflictResolutionField>(
            conflictResolution);
  }

  public ApplyMetadataCascadePolicyRequestBody(
      @JsonProperty("conflict_resolution")
          EnumWrapper<ApplyMetadataCascadePolicyRequestBodyConflictResolutionField>
              conflictResolution) {
    super();
    this.conflictResolution = conflictResolution;
  }

  public EnumWrapper<ApplyMetadataCascadePolicyRequestBodyConflictResolutionField>
      getConflictResolution() {
    return conflictResolution;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApplyMetadataCascadePolicyRequestBody casted = (ApplyMetadataCascadePolicyRequestBody) o;
    return Objects.equals(conflictResolution, casted.conflictResolution);
  }

  @Override
  public int hashCode() {
    return Objects.hash(conflictResolution);
  }

  @Override
  public String toString() {
    return "ApplyMetadataCascadePolicyRequestBody{"
        + "conflictResolution='"
        + conflictResolution
        + '\''
        + "}";
  }
}
