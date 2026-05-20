package com.box.sdkgen.schemas.v2026r0.folderreferencev2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** Folder reference. */
@JsonFilter("nullablePropertyFilter")
public class FolderReferenceV2026R0 extends SerializableObject {

  /** The value will always be `folder`. */
  @JsonDeserialize(
      using = FolderReferenceV2026R0TypeField.FolderReferenceV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = FolderReferenceV2026R0TypeField.FolderReferenceV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<FolderReferenceV2026R0TypeField> type;

  /** ID of the folder. */
  protected final String id;

  public FolderReferenceV2026R0(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<FolderReferenceV2026R0TypeField>(FolderReferenceV2026R0TypeField.FOLDER);
  }

  protected FolderReferenceV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<FolderReferenceV2026R0TypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FolderReferenceV2026R0 casted = (FolderReferenceV2026R0) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "FolderReferenceV2026R0{" + "type='" + type + '\'' + ", " + "id='" + id + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<FolderReferenceV2026R0TypeField> type;

    protected final String id;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(FolderReferenceV2026R0TypeField type) {
      this.type = new EnumWrapper<FolderReferenceV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<FolderReferenceV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public FolderReferenceV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<FolderReferenceV2026R0TypeField>(
                FolderReferenceV2026R0TypeField.FOLDER);
      }
      return new FolderReferenceV2026R0(this);
    }
  }
}
