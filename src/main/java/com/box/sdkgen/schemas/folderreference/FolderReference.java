package com.box.sdkgen.schemas.folderreference;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

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

  protected FolderReference(FolderReferenceBuilder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
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

  public static class FolderReferenceBuilder {

    protected EnumWrapper<FolderReferenceTypeField> type;

    protected final String id;

    public FolderReferenceBuilder(String id) {
      this.id = id;
      this.type = new EnumWrapper<FolderReferenceTypeField>(FolderReferenceTypeField.FOLDER);
    }

    public FolderReferenceBuilder type(FolderReferenceTypeField type) {
      this.type = new EnumWrapper<FolderReferenceTypeField>(type);
      return this;
    }

    public FolderReferenceBuilder type(EnumWrapper<FolderReferenceTypeField> type) {
      this.type = type;
      return this;
    }

    public FolderReference build() {
      return new FolderReference(this);
    }
  }
}
