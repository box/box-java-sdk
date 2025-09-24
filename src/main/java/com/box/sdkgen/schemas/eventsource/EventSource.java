package com.box.sdkgen.schemas.eventsource;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class EventSource extends SerializableObject {

  @JsonDeserialize(using = EventSourceItemTypeField.EventSourceItemTypeFieldDeserializer.class)
  @JsonSerialize(using = EventSourceItemTypeField.EventSourceItemTypeFieldSerializer.class)
  @JsonProperty("item_type")
  protected final EnumWrapper<EventSourceItemTypeField> itemType;

  @JsonProperty("item_id")
  protected final String itemId;

  @JsonProperty("item_name")
  protected final String itemName;

  protected EventSourceClassificationField classification;

  @Nullable protected FolderMini parent;

  @JsonProperty("owned_by")
  protected UserMini ownedBy;

  public EventSource(EventSourceItemTypeField itemType, String itemId, String itemName) {
    super();
    this.itemType = new EnumWrapper<EventSourceItemTypeField>(itemType);
    this.itemId = itemId;
    this.itemName = itemName;
  }

  public EventSource(
      @JsonProperty("item_type") EnumWrapper<EventSourceItemTypeField> itemType,
      @JsonProperty("item_id") String itemId,
      @JsonProperty("item_name") String itemName) {
    super();
    this.itemType = itemType;
    this.itemId = itemId;
    this.itemName = itemName;
  }

  protected EventSource(Builder builder) {
    super();
    this.itemType = builder.itemType;
    this.itemId = builder.itemId;
    this.itemName = builder.itemName;
    this.classification = builder.classification;
    this.parent = builder.parent;
    this.ownedBy = builder.ownedBy;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<EventSourceItemTypeField> getItemType() {
    return itemType;
  }

  public String getItemId() {
    return itemId;
  }

  public String getItemName() {
    return itemName;
  }

  public EventSourceClassificationField getClassification() {
    return classification;
  }

  public FolderMini getParent() {
    return parent;
  }

  public UserMini getOwnedBy() {
    return ownedBy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventSource casted = (EventSource) o;
    return Objects.equals(itemType, casted.itemType)
        && Objects.equals(itemId, casted.itemId)
        && Objects.equals(itemName, casted.itemName)
        && Objects.equals(classification, casted.classification)
        && Objects.equals(parent, casted.parent)
        && Objects.equals(ownedBy, casted.ownedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemType, itemId, itemName, classification, parent, ownedBy);
  }

  @Override
  public String toString() {
    return "EventSource{"
        + "itemType='"
        + itemType
        + '\''
        + ", "
        + "itemId='"
        + itemId
        + '\''
        + ", "
        + "itemName='"
        + itemName
        + '\''
        + ", "
        + "classification='"
        + classification
        + '\''
        + ", "
        + "parent='"
        + parent
        + '\''
        + ", "
        + "ownedBy='"
        + ownedBy
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final EnumWrapper<EventSourceItemTypeField> itemType;

    protected final String itemId;

    protected final String itemName;

    protected EventSourceClassificationField classification;

    protected FolderMini parent;

    protected UserMini ownedBy;

    public Builder(EventSourceItemTypeField itemType, String itemId, String itemName) {
      super();
      this.itemType = new EnumWrapper<EventSourceItemTypeField>(itemType);
      this.itemId = itemId;
      this.itemName = itemName;
    }

    public Builder(EnumWrapper<EventSourceItemTypeField> itemType, String itemId, String itemName) {
      super();
      this.itemType = itemType;
      this.itemId = itemId;
      this.itemName = itemName;
    }

    public Builder classification(EventSourceClassificationField classification) {
      this.classification = classification;
      return this;
    }

    public Builder parent(FolderMini parent) {
      this.parent = parent;
      this.markNullableFieldAsSet("parent");
      return this;
    }

    public Builder ownedBy(UserMini ownedBy) {
      this.ownedBy = ownedBy;
      return this;
    }

    public EventSource build() {
      return new EventSource(this);
    }
  }
}
