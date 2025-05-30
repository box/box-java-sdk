package com.box.sdkgen.schemas.foldermini;

import com.box.sdkgen.schemas.folderbase.FolderBase;
import com.box.sdkgen.schemas.folderbase.FolderBaseTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class FolderMini extends FolderBase {

  @JsonProperty("sequence_id")
  protected String sequenceId;

  protected String name;

  public FolderMini(@JsonProperty("id") String id) {
    super(id);
  }

  protected FolderMini(FolderMiniBuilder builder) {
    super(builder);
    this.sequenceId = builder.sequenceId;
    this.name = builder.name;
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

  public static class FolderMiniBuilder extends FolderBaseBuilder {

    protected String sequenceId;

    protected String name;

    public FolderMiniBuilder(String id) {
      super(id);
    }

    public FolderMiniBuilder sequenceId(String sequenceId) {
      this.sequenceId = sequenceId;
      return this;
    }

    public FolderMiniBuilder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public FolderMiniBuilder etag(String etag) {
      this.etag = etag;
      return this;
    }

    @Override
    public FolderMiniBuilder type(FolderBaseTypeField type) {
      this.type = new EnumWrapper<FolderBaseTypeField>(type);
      return this;
    }

    @Override
    public FolderMiniBuilder type(EnumWrapper<FolderBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public FolderMini build() {
      return new FolderMini(this);
    }
  }
}
