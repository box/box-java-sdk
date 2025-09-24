package com.box.sdkgen.schemas.trashfolder;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TrashFolderPathCollectionEntriesField extends SerializableObject {

  @JsonDeserialize(
      using =
          TrashFolderPathCollectionEntriesTypeField
              .TrashFolderPathCollectionEntriesTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          TrashFolderPathCollectionEntriesTypeField
              .TrashFolderPathCollectionEntriesTypeFieldSerializer.class)
  protected EnumWrapper<TrashFolderPathCollectionEntriesTypeField> type;

  protected String id;

  @JsonProperty("sequence_id")
  @Nullable
  protected String sequenceId;

  @Nullable protected String etag;

  protected String name;

  public TrashFolderPathCollectionEntriesField() {
    super();
  }

  protected TrashFolderPathCollectionEntriesField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    this.sequenceId = builder.sequenceId;
    this.etag = builder.etag;
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<TrashFolderPathCollectionEntriesTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public String getSequenceId() {
    return sequenceId;
  }

  public String getEtag() {
    return etag;
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
    TrashFolderPathCollectionEntriesField casted = (TrashFolderPathCollectionEntriesField) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(id, casted.id)
        && Objects.equals(sequenceId, casted.sequenceId)
        && Objects.equals(etag, casted.etag)
        && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, sequenceId, etag, name);
  }

  @Override
  public String toString() {
    return "TrashFolderPathCollectionEntriesField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "sequenceId='"
        + sequenceId
        + '\''
        + ", "
        + "etag='"
        + etag
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<TrashFolderPathCollectionEntriesTypeField> type;

    protected String id;

    protected String sequenceId;

    protected String etag;

    protected String name;

    public Builder type(TrashFolderPathCollectionEntriesTypeField type) {
      this.type = new EnumWrapper<TrashFolderPathCollectionEntriesTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TrashFolderPathCollectionEntriesTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder sequenceId(String sequenceId) {
      this.sequenceId = sequenceId;
      this.markNullableFieldAsSet("sequence_id");
      return this;
    }

    public Builder etag(String etag) {
      this.etag = etag;
      this.markNullableFieldAsSet("etag");
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public TrashFolderPathCollectionEntriesField build() {
      return new TrashFolderPathCollectionEntriesField(this);
    }
  }
}
