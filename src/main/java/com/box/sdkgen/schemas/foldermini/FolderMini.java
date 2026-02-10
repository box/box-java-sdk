package com.box.sdkgen.schemas.foldermini;

import com.box.sdkgen.schemas.folderbase.FolderBase;
import com.box.sdkgen.schemas.folderbase.FolderBaseTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** A mini representation of a file version, used when nested under another resource. */
@JsonFilter("nullablePropertyFilter")
public class FolderMini extends FolderBase {

  @JsonProperty("sequence_id")
  protected String sequenceId;

  /** The name of the folder. */
  protected String name;

  public FolderMini(@JsonProperty("id") String id) {
    super(id);
  }

  protected FolderMini(Builder builder) {
    super(builder);
    this.sequenceId = builder.sequenceId;
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getSequenceId() {
    return sequenceId;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FolderMini casted = (FolderMini) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(etag, casted.etag)
        && Objects.equals(type, casted.type)
        && Objects.equals(sequenceId, casted.sequenceId)
        && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, etag, type, sequenceId, name);
  }

  @Override
  public String toString() {
    return "FolderMini{"
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
        + ", "
        + "sequenceId='"
        + sequenceId
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + "}";
  }

  public static class Builder extends FolderBase.Builder {

    protected String sequenceId;

    protected String name;

    public Builder(String id) {
      super(id);
    }

    public Builder sequenceId(String sequenceId) {
      this.sequenceId = sequenceId;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public Builder etag(String etag) {
      this.etag = etag;
      this.markNullableFieldAsSet("etag");
      return this;
    }

    @Override
    public Builder type(FolderBaseTypeField type) {
      this.type = new EnumWrapper<FolderBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<FolderBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public FolderMini build() {
      if (this.type == null) {
        this.type = new EnumWrapper<FolderBaseTypeField>(FolderBaseTypeField.FOLDER);
      }
      return new FolderMini(this);
    }
  }
}
