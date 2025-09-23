package com.box.sdkgen.schemas.folderfull;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FolderFullWatermarkInfoField extends SerializableObject {

  @JsonProperty("is_watermarked")
  protected Boolean isWatermarked;

  public FolderFullWatermarkInfoField() {
    super();
  }

  protected FolderFullWatermarkInfoField(Builder builder) {
    super();
    this.isWatermarked = builder.isWatermarked;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Boolean getIsWatermarked() {
    return isWatermarked;
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
    return Objects.equals(isWatermarked, casted.isWatermarked);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isWatermarked);
  }

  @Override
  public String toString() {
    return "FolderFullWatermarkInfoField{" + "isWatermarked='" + isWatermarked + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Boolean isWatermarked;

    public Builder isWatermarked(Boolean isWatermarked) {
      this.isWatermarked = isWatermarked;
      return this;
    }

    public FolderFullWatermarkInfoField build() {
      return new FolderFullWatermarkInfoField(this);
    }
  }
}
