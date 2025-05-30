package com.box.sdkgen.schemas.folderbase;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class FolderBase extends SerializableObject {

  protected final String id;

  protected String etag;

  @JsonDeserialize(using = FolderBaseTypeField.FolderBaseTypeFieldDeserializer.class)
  @JsonSerialize(using = FolderBaseTypeField.FolderBaseTypeFieldSerializer.class)
  protected EnumWrapper<FolderBaseTypeField> type;

  public FolderBase(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<FolderBaseTypeField>(FolderBaseTypeField.FOLDER);
  }

  protected FolderBase(FolderBaseBuilder builder) {
    super();
    this.id = builder.id;
    this.etag = builder.etag;
    this.type = builder.type;
  }

  public String getId() {
    return id;
  }

  public String getEtag() {
    return etag;
  }

  public EnumWrapper<FolderBaseTypeField> getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FolderBase casted = (FolderBase) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(etag, casted.etag)
        && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, etag, type);
  }

  @Override
  public String toString() {
    return "FolderBase{"
        + "id='"
        + id
        + '\''
        + ", "
        + "etag='"
        + etag
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }

  public static class FolderBaseBuilder {

    protected final String id;

    protected String etag;

    protected EnumWrapper<FolderBaseTypeField> type;

    public FolderBaseBuilder(String id) {
      this.id = id;
      this.type = new EnumWrapper<FolderBaseTypeField>(FolderBaseTypeField.FOLDER);
    }

    public FolderBaseBuilder etag(String etag) {
      this.etag = etag;
      return this;
    }

    public FolderBaseBuilder type(FolderBaseTypeField type) {
      this.type = new EnumWrapper<FolderBaseTypeField>(type);
      return this;
    }

    public FolderBaseBuilder type(EnumWrapper<FolderBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public FolderBase build() {
      return new FolderBase(this);
    }
  }
}
