package com.box.sdkgen.schemas.v2025r0.hubitemoperationresultv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.filereferencev2025r0.FileReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.folderreferencev2025r0.FolderReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubitemreferencev2025r0.HubItemReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.weblinkreferencev2025r0.WeblinkReferenceV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** Result of a Box Hub item operation. */
@JsonFilter("nullablePropertyFilter")
public class HubItemOperationResultV2025R0 extends SerializableObject {

  /** The action performed on the item. */
  protected String action;

  protected HubItemReferenceV2025R0 item;

  /** The ID of the parent block the item was added to. */
  @JsonProperty("parent_id")
  protected String parentId;

  /** The HTTP status code of the operation. */
  protected Long status;

  /** Error message if the operation failed. */
  protected String error;

  public HubItemOperationResultV2025R0() {
    super();
  }

  protected HubItemOperationResultV2025R0(Builder builder) {
    super();
    this.action = builder.action;
    this.item = builder.item;
    this.parentId = builder.parentId;
    this.status = builder.status;
    this.error = builder.error;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getAction() {
    return action;
  }

  public HubItemReferenceV2025R0 getItem() {
    return item;
  }

  public String getParentId() {
    return parentId;
  }

  public Long getStatus() {
    return status;
  }

  public String getError() {
    return error;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HubItemOperationResultV2025R0 casted = (HubItemOperationResultV2025R0) o;
    return Objects.equals(action, casted.action)
        && Objects.equals(item, casted.item)
        && Objects.equals(parentId, casted.parentId)
        && Objects.equals(status, casted.status)
        && Objects.equals(error, casted.error);
  }

  @Override
  public int hashCode() {
    return Objects.hash(action, item, parentId, status, error);
  }

  @Override
  public String toString() {
    return "HubItemOperationResultV2025R0{"
        + "action='"
        + action
        + '\''
        + ", "
        + "item='"
        + item
        + '\''
        + ", "
        + "parentId='"
        + parentId
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "error='"
        + error
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String action;

    protected HubItemReferenceV2025R0 item;

    protected String parentId;

    protected Long status;

    protected String error;

    public Builder action(String action) {
      this.action = action;
      return this;
    }

    public Builder item(FileReferenceV2025R0 item) {
      this.item = new HubItemReferenceV2025R0(item);
      return this;
    }

    public Builder item(FolderReferenceV2025R0 item) {
      this.item = new HubItemReferenceV2025R0(item);
      return this;
    }

    public Builder item(WeblinkReferenceV2025R0 item) {
      this.item = new HubItemReferenceV2025R0(item);
      return this;
    }

    public Builder item(HubItemReferenceV2025R0 item) {
      this.item = item;
      return this;
    }

    public Builder parentId(String parentId) {
      this.parentId = parentId;
      return this;
    }

    public Builder status(Long status) {
      this.status = status;
      return this;
    }

    public Builder error(String error) {
      this.error = error;
      return this;
    }

    public HubItemOperationResultV2025R0 build() {
      return new HubItemOperationResultV2025R0(this);
    }
  }
}
