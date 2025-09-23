package com.box.sdkgen.schemas.folderreference;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FolderReference extends SerializableObject {

  @JsonDeserialize(using = FolderReferenceTypeField.FolderReferenceTypeFieldDeserializer.class)
  @JsonSerialize(using = FolderReferenceTypeField.FolderReferenceTypeFieldSerializer.class)
  protected EnumWrapper<FolderReferenceTypeField> type;

  protected final String id;

  public FolderReference(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<FolderReferenceTypeField>(FolderReferenceTypeField.FOLDER);
  }

  protected FolderReference(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<FolderReferenceTypeField> getType() {
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
    FolderReference casted = (FolderReference) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "FolderReference{" + "type='" + type + '\'' + ", " + "id='" + id + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<FolderReferenceTypeField> type;

    protected final String id;

    public Builder(String id) {
      super();
      this.id = id;
      this.type = new EnumWrapper<FolderReferenceTypeField>(FolderReferenceTypeField.FOLDER);
    }

    public Builder type(FolderReferenceTypeField type) {
      this.type = new EnumWrapper<FolderReferenceTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<FolderReferenceTypeField> type) {
      this.type = type;
      return this;
    }

    public FolderReference build() {
      return new FolderReference(this);
    }
  }
}
