package com.box.sdkgen.schemas.folderfull;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FolderFullWatermarkInfoField extends SerializableObject {

  /** Specifies if this item has a watermark applied. */
  @JsonProperty("is_watermarked")
  protected Boolean isWatermarked;

  /** Specifies if the watermark is inherited from any parent folder in the hierarchy. */
  @JsonProperty("is_watermark_inherited")
  protected Boolean isWatermarkInherited;

  /** Specifies if the watermark is enforced by an access policy. */
  @JsonProperty("is_watermarked_by_access_policy")
  protected Boolean isWatermarkedByAccessPolicy;

  public FolderFullWatermarkInfoField() {
    super();
  }

  protected FolderFullWatermarkInfoField(Builder builder) {
    super();
    this.isWatermarked = builder.isWatermarked;
    this.isWatermarkInherited = builder.isWatermarkInherited;
    this.isWatermarkedByAccessPolicy = builder.isWatermarkedByAccessPolicy;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Boolean getIsWatermarked() {
    return isWatermarked;
  }

  public Boolean getIsWatermarkInherited() {
    return isWatermarkInherited;
  }

  public Boolean getIsWatermarkedByAccessPolicy() {
    return isWatermarkedByAccessPolicy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FolderFullWatermarkInfoField casted = (FolderFullWatermarkInfoField) o;
    return Objects.equals(isWatermarked, casted.isWatermarked)
        && Objects.equals(isWatermarkInherited, casted.isWatermarkInherited)
        && Objects.equals(isWatermarkedByAccessPolicy, casted.isWatermarkedByAccessPolicy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isWatermarked, isWatermarkInherited, isWatermarkedByAccessPolicy);
  }

  @Override
  public String toString() {
    return "FolderFullWatermarkInfoField{"
        + "isWatermarked='"
        + isWatermarked
        + '\''
        + ", "
        + "isWatermarkInherited='"
        + isWatermarkInherited
        + '\''
        + ", "
        + "isWatermarkedByAccessPolicy='"
        + isWatermarkedByAccessPolicy
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Boolean isWatermarked;

    protected Boolean isWatermarkInherited;

    protected Boolean isWatermarkedByAccessPolicy;

    public Builder isWatermarked(Boolean isWatermarked) {
      this.isWatermarked = isWatermarked;
      return this;
    }

    public Builder isWatermarkInherited(Boolean isWatermarkInherited) {
      this.isWatermarkInherited = isWatermarkInherited;
      return this;
    }

    public Builder isWatermarkedByAccessPolicy(Boolean isWatermarkedByAccessPolicy) {
      this.isWatermarkedByAccessPolicy = isWatermarkedByAccessPolicy;
      return this;
    }

    public FolderFullWatermarkInfoField build() {
      return new FolderFullWatermarkInfoField(this);
    }
  }
}
