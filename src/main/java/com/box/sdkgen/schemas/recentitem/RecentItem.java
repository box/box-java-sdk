package com.box.sdkgen.schemas.recentitem;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.filefullorfolderfullorweblink.FileFullOrFolderFullOrWebLink;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class RecentItem extends SerializableObject {

  protected String type;

  protected FileFullOrFolderFullOrWebLink item;

  @JsonDeserialize(
      using = RecentItemInteractionTypeField.RecentItemInteractionTypeFieldDeserializer.class)
  @JsonSerialize(
      using = RecentItemInteractionTypeField.RecentItemInteractionTypeFieldSerializer.class)
  @JsonProperty("interaction_type")
  protected EnumWrapper<RecentItemInteractionTypeField> interactionType;

  @JsonProperty("interacted_at")
  protected String interactedAt;

  @JsonProperty("interaction_shared_link")
  protected String interactionSharedLink;

  public RecentItem() {
    super();
  }

  protected RecentItem(RecentItemBuilder builder) {
    super();
    this.type = builder.type;
    this.item = builder.item;
    this.interactionType = builder.interactionType;
    this.interactedAt = builder.interactedAt;
    this.interactionSharedLink = builder.interactionSharedLink;
  }

  public String getType() {
    return type;
  }

  public FileFullOrFolderFullOrWebLink getItem() {
    return item;
  }

  public EnumWrapper<RecentItemInteractionTypeField> getInteractionType() {
    return interactionType;
  }

  public String getInteractedAt() {
    return interactedAt;
  }

  public String getInteractionSharedLink() {
    return interactionSharedLink;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecentItem casted = (RecentItem) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(item, casted.item)
        && Objects.equals(interactionType, casted.interactionType)
        && Objects.equals(interactedAt, casted.interactedAt)
        && Objects.equals(interactionSharedLink, casted.interactionSharedLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, item, interactionType, interactedAt, interactionSharedLink);
  }

  @Override
  public String toString() {
    return "RecentItem{"
        + "type='"
        + type
        + '\''
        + ", "
        + "item='"
        + item
        + '\''
        + ", "
        + "interactionType='"
        + interactionType
        + '\''
        + ", "
        + "interactedAt='"
        + interactedAt
        + '\''
        + ", "
        + "interactionSharedLink='"
        + interactionSharedLink
        + '\''
        + "}";
  }

  public static class RecentItemBuilder {

    protected String type;

    protected FileFullOrFolderFullOrWebLink item;

    protected EnumWrapper<RecentItemInteractionTypeField> interactionType;

    protected String interactedAt;

    protected String interactionSharedLink;

    public RecentItemBuilder type(String type) {
      this.type = type;
      return this;
    }

    public RecentItemBuilder item(FileFullOrFolderFullOrWebLink item) {
      this.item = item;
      return this;
    }

    public RecentItemBuilder interactionType(RecentItemInteractionTypeField interactionType) {
      this.interactionType = new EnumWrapper<RecentItemInteractionTypeField>(interactionType);
      return this;
    }

    public RecentItemBuilder interactionType(
        EnumWrapper<RecentItemInteractionTypeField> interactionType) {
      this.interactionType = interactionType;
      return this;
    }

    public RecentItemBuilder interactedAt(String interactedAt) {
      this.interactedAt = interactedAt;
      return this;
    }

    public RecentItemBuilder interactionSharedLink(String interactionSharedLink) {
      this.interactionSharedLink = interactionSharedLink;
      return this;
    }

    public RecentItem build() {
      return new RecentItem(this);
    }
  }
}
