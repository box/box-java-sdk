package com.box.sdkgen.schemas.v2025r0.hubitemoperationv2025r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.filereferencev2025r0.FileReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.folderreferencev2025r0.FolderReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubitemreferencev2025r0.HubItemReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.weblinkreferencev2025r0.WeblinkReferenceV2025R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** An operation to perform on a Box Hub item. */
@JsonFilter("nullablePropertyFilter")
public class HubItemOperationV2025R0 extends SerializableObject {

  /** The action to perform on a Box Hub item. */
  @JsonDeserialize(
      using =
          HubItemOperationV2025R0ActionField.HubItemOperationV2025R0ActionFieldDeserializer.class)
  @JsonSerialize(
      using = HubItemOperationV2025R0ActionField.HubItemOperationV2025R0ActionFieldSerializer.class)
  protected final EnumWrapper<HubItemOperationV2025R0ActionField> action;

  protected final HubItemReferenceV2025R0 item;

  public HubItemOperationV2025R0(
      HubItemOperationV2025R0ActionField action, FileReferenceV2025R0 item) {
    super();
    this.action = new EnumWrapper<HubItemOperationV2025R0ActionField>(action);
    this.item = new HubItemReferenceV2025R0(item);
  }

  public HubItemOperationV2025R0(
      HubItemOperationV2025R0ActionField action, FolderReferenceV2025R0 item) {
    super();
    this.action = new EnumWrapper<HubItemOperationV2025R0ActionField>(action);
    this.item = new HubItemReferenceV2025R0(item);
  }

  public HubItemOperationV2025R0(
      HubItemOperationV2025R0ActionField action, WeblinkReferenceV2025R0 item) {
    super();
    this.action = new EnumWrapper<HubItemOperationV2025R0ActionField>(action);
    this.item = new HubItemReferenceV2025R0(item);
  }

  public HubItemOperationV2025R0(
      HubItemOperationV2025R0ActionField action, HubItemReferenceV2025R0 item) {
    super();
    this.action = new EnumWrapper<HubItemOperationV2025R0ActionField>(action);
    this.item = item;
  }

  public HubItemOperationV2025R0(
      EnumWrapper<HubItemOperationV2025R0ActionField> action, FileReferenceV2025R0 item) {
    super();
    this.action = action;
    this.item = new HubItemReferenceV2025R0(item);
  }

  public HubItemOperationV2025R0(
      EnumWrapper<HubItemOperationV2025R0ActionField> action, FolderReferenceV2025R0 item) {
    super();
    this.action = action;
    this.item = new HubItemReferenceV2025R0(item);
  }

  public HubItemOperationV2025R0(
      EnumWrapper<HubItemOperationV2025R0ActionField> action, WeblinkReferenceV2025R0 item) {
    super();
    this.action = action;
    this.item = new HubItemReferenceV2025R0(item);
  }

  public HubItemOperationV2025R0(
      @JsonProperty("action") EnumWrapper<HubItemOperationV2025R0ActionField> action,
      @JsonProperty("item") HubItemReferenceV2025R0 item) {
    super();
    this.action = action;
    this.item = item;
  }

  public EnumWrapper<HubItemOperationV2025R0ActionField> getAction() {
    return action;
  }

  public HubItemReferenceV2025R0 getItem() {
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
    HubItemOperationV2025R0 casted = (HubItemOperationV2025R0) o;
    return Objects.equals(action, casted.action) && Objects.equals(item, casted.item);
  }

  @Override
  public int hashCode() {
    return Objects.hash(action, item);
  }

  @Override
  public String toString() {
    return "HubItemOperationV2025R0{"
        + "action='"
        + action
        + '\''
        + ", "
        + "item='"
        + item
        + '\''
        + "}";
  }
}
