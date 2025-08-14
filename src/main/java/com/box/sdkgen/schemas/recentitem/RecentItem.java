package com.box.sdkgen.schemas.recentitem;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.recentitemresource.RecentItemResource;
import com.box.sdkgen.schemas.weblink.WebLink;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class RecentItem extends SerializableObject {

  protected String type;

  protected RecentItemResource item;

  @JsonDeserialize(
      using = RecentItemInteractionTypeField.RecentItemInteractionTypeFieldDeserializer.class)
  @JsonSerialize(
      using = RecentItemInteractionTypeField.RecentItemInteractionTypeFieldSerializer.class)
  @JsonProperty("interaction_type")
  protected EnumWrapper<RecentItemInteractionTypeField> interactionType;

  @JsonProperty("interacted_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date interactedAt;

  @JsonProperty("interaction_shared_link")
  protected String interactionSharedLink;

  public RecentItem() {
    super();
  }

  protected RecentItem(Builder builder) {
    super();
    this.type = builder.type;
    this.item = builder.item;
    this.interactionType = builder.interactionType;
    this.interactedAt = builder.interactedAt;
    this.interactionSharedLink = builder.interactionSharedLink;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getType() {
    return type;
  }

  public RecentItemResource getItem() {
    return item;
  }

  public EnumWrapper<RecentItemInteractionTypeField> getInteractionType() {
    return interactionType;
  }

  public Date getInteractedAt() {
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

  public static class Builder extends NullableFieldTracker {

    protected String type;

    protected RecentItemResource item;

    protected EnumWrapper<RecentItemInteractionTypeField> interactionType;

    protected Date interactedAt;

    protected String interactionSharedLink;

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder item(FileFull item) {
      this.item = new RecentItemResource(item);
      return this;
    }

    public Builder item(FolderFull item) {
      this.item = new RecentItemResource(item);
      return this;
    }

    public Builder item(WebLink item) {
      this.item = new RecentItemResource(item);
      return this;
    }

    public Builder item(RecentItemResource item) {
      this.item = item;
      return this;
    }

    public Builder interactionType(RecentItemInteractionTypeField interactionType) {
      this.interactionType = new EnumWrapper<RecentItemInteractionTypeField>(interactionType);
      return this;
    }

    public Builder interactionType(EnumWrapper<RecentItemInteractionTypeField> interactionType) {
      this.interactionType = interactionType;
      return this;
    }

    public Builder interactedAt(Date interactedAt) {
      this.interactedAt = interactedAt;
      return this;
    }

    public Builder interactionSharedLink(String interactionSharedLink) {
      this.interactionSharedLink = interactionSharedLink;
      return this;
    }

    public RecentItem build() {
      return new RecentItem(this);
    }
  }
}
