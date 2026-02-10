package com.box.sdkgen.schemas.appitemassociation;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.appitem.AppItem;
import com.box.sdkgen.schemas.appitemassociateditem.AppItemAssociatedItem;
import com.box.sdkgen.schemas.filebase.FileBase;
import com.box.sdkgen.schemas.folderbase.FolderBase;
import com.box.sdkgen.schemas.weblinkbase.WebLinkBase;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * An app item association represents an association between a file or folder and an app item.
 * Associations between a folder and an app item cascade down to all descendants of the folder.
 */
@JsonFilter("nullablePropertyFilter")
public class AppItemAssociation extends SerializableObject {

  /** The unique identifier for this app item association. */
  protected final String id;

  /** The value will always be `app_item_association`. */
  @JsonDeserialize(
      using = AppItemAssociationTypeField.AppItemAssociationTypeFieldDeserializer.class)
  @JsonSerialize(using = AppItemAssociationTypeField.AppItemAssociationTypeFieldSerializer.class)
  protected EnumWrapper<AppItemAssociationTypeField> type;

  @JsonProperty("app_item")
  protected final AppItem appItem;

  protected final AppItemAssociatedItem item;

  public AppItemAssociation(String id, AppItem appItem, FileBase item) {
    super();
    this.id = id;
    this.appItem = appItem;
    this.item = new AppItemAssociatedItem(item);
    this.type =
        new EnumWrapper<AppItemAssociationTypeField>(
            AppItemAssociationTypeField.APP_ITEM_ASSOCIATION);
  }

  public AppItemAssociation(String id, AppItem appItem, FolderBase item) {
    super();
    this.id = id;
    this.appItem = appItem;
    this.item = new AppItemAssociatedItem(item);
    this.type =
        new EnumWrapper<AppItemAssociationTypeField>(
            AppItemAssociationTypeField.APP_ITEM_ASSOCIATION);
  }

  public AppItemAssociation(String id, AppItem appItem, WebLinkBase item) {
    super();
    this.id = id;
    this.appItem = appItem;
    this.item = new AppItemAssociatedItem(item);
    this.type =
        new EnumWrapper<AppItemAssociationTypeField>(
            AppItemAssociationTypeField.APP_ITEM_ASSOCIATION);
  }

  public AppItemAssociation(
      @JsonProperty("id") String id,
      @JsonProperty("app_item") AppItem appItem,
      @JsonProperty("item") AppItemAssociatedItem item) {
    super();
    this.id = id;
    this.appItem = appItem;
    this.item = item;
    this.type =
        new EnumWrapper<AppItemAssociationTypeField>(
            AppItemAssociationTypeField.APP_ITEM_ASSOCIATION);
  }

  protected AppItemAssociation(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.appItem = builder.appItem;
    this.item = builder.item;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<AppItemAssociationTypeField> getType() {
    return type;
  }

  public AppItem getAppItem() {
    return appItem;
  }

  public AppItemAssociatedItem getItem() {
    return item;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppItemAssociation casted = (AppItemAssociation) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(appItem, casted.appItem)
        && Objects.equals(item, casted.item);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, appItem, item);
  }

  @Override
  public String toString() {
    return "AppItemAssociation{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "appItem='"
        + appItem
        + '\''
        + ", "
        + "item='"
        + item
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<AppItemAssociationTypeField> type;

    protected final AppItem appItem;

    protected final AppItemAssociatedItem item;

    public Builder(String id, AppItem appItem, FileBase item) {
      super();
      this.id = id;
      this.appItem = appItem;
      this.item = new AppItemAssociatedItem(item);
    }

    public Builder(String id, AppItem appItem, FolderBase item) {
      super();
      this.id = id;
      this.appItem = appItem;
      this.item = new AppItemAssociatedItem(item);
    }

    public Builder(String id, AppItem appItem, WebLinkBase item) {
      super();
      this.id = id;
      this.appItem = appItem;
      this.item = new AppItemAssociatedItem(item);
    }

    public Builder(String id, AppItem appItem, AppItemAssociatedItem item) {
      super();
      this.id = id;
      this.appItem = appItem;
      this.item = item;
    }

    public Builder type(AppItemAssociationTypeField type) {
      this.type = new EnumWrapper<AppItemAssociationTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AppItemAssociationTypeField> type) {
      this.type = type;
      return this;
    }

    public AppItemAssociation build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<AppItemAssociationTypeField>(
                AppItemAssociationTypeField.APP_ITEM_ASSOCIATION);
      }
      return new AppItemAssociation(this);
    }
  }
}
