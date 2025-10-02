package com.box.sdkgen.schemas.collection;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * A collection of items, including files and folders.
 *
 * <p>Currently, the only collection available is the `favorites` collection.
 *
 * <p>The contents of a collection can be explored in a similar way to which the contents of a
 * folder is explored.
 */
@JsonFilter("nullablePropertyFilter")
public class Collection extends SerializableObject {

  /** The unique identifier for this collection. */
  protected String id;

  /** The value will always be `collection`. */
  @JsonDeserialize(using = CollectionTypeField.CollectionTypeFieldDeserializer.class)
  @JsonSerialize(using = CollectionTypeField.CollectionTypeFieldSerializer.class)
  protected EnumWrapper<CollectionTypeField> type;

  /** The name of the collection. */
  @JsonDeserialize(using = CollectionNameField.CollectionNameFieldDeserializer.class)
  @JsonSerialize(using = CollectionNameField.CollectionNameFieldSerializer.class)
  protected EnumWrapper<CollectionNameField> name;

  /**
   * The type of the collection. This is used to determine the proper visual treatment for
   * collections.
   */
  @JsonDeserialize(
      using = CollectionCollectionTypeField.CollectionCollectionTypeFieldDeserializer.class)
  @JsonSerialize(
      using = CollectionCollectionTypeField.CollectionCollectionTypeFieldSerializer.class)
  @JsonProperty("collection_type")
  protected EnumWrapper<CollectionCollectionTypeField> collectionType;

  public Collection() {
    super();
  }

  protected Collection(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    this.collectionType = builder.collectionType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<CollectionTypeField> getType() {
    return type;
  }

  public EnumWrapper<CollectionNameField> getName() {
    return name;
  }

  public EnumWrapper<CollectionCollectionTypeField> getCollectionType() {
    return collectionType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Collection casted = (Collection) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(collectionType, casted.collectionType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, collectionType);
  }

  @Override
  public String toString() {
    return "Collection{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "collectionType='"
        + collectionType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<CollectionTypeField> type;

    protected EnumWrapper<CollectionNameField> name;

    protected EnumWrapper<CollectionCollectionTypeField> collectionType;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(CollectionTypeField type) {
      this.type = new EnumWrapper<CollectionTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<CollectionTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder name(CollectionNameField name) {
      this.name = new EnumWrapper<CollectionNameField>(name);
      return this;
    }

    public Builder name(EnumWrapper<CollectionNameField> name) {
      this.name = name;
      return this;
    }

    public Builder collectionType(CollectionCollectionTypeField collectionType) {
      this.collectionType = new EnumWrapper<CollectionCollectionTypeField>(collectionType);
      return this;
    }

    public Builder collectionType(EnumWrapper<CollectionCollectionTypeField> collectionType) {
      this.collectionType = collectionType;
      return this;
    }

    public Collection build() {
      return new Collection(this);
    }
  }
}
