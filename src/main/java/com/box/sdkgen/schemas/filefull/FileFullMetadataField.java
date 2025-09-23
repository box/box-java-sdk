package com.box.sdkgen.schemas.filefull;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.metadatafull.MetadataFull;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Map;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileFullMetadataField extends SerializableObject {

  @JsonAnyGetter @JsonAnySetter protected Map<String, Map<String, MetadataFull>> extraData;

  public FileFullMetadataField() {
    super();
  }

  protected FileFullMetadataField(Builder builder) {
    super();
    this.extraData = builder.extraData;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Map<String, Map<String, MetadataFull>> getExtraData() {
    return extraData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileFullMetadataField casted = (FileFullMetadataField) o;
    return Objects.equals(extraData, casted.extraData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(extraData);
  }

  @Override
  public String toString() {
    return "FileFullMetadataField{" + "extraData='" + extraData + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Map<String, Map<String, MetadataFull>> extraData;

    public Builder extraData(Map<String, Map<String, MetadataFull>> extraData) {
      this.extraData = extraData;
      return this;
    }

    public FileFullMetadataField build() {
      return new FileFullMetadataField(this);
    }
  }
}
