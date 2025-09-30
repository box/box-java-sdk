package com.box.sdkgen.schemas.v2025r0.hubitemoperationresultv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.filereferencev2025r0.FileReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.folderreferencev2025r0.FolderReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubitemreferencev2025r0.HubItemReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.weblinkreferencev2025r0.WeblinkReferenceV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class HubItemOperationResultV2025R0 extends SerializableObject {

  protected String action;

  protected HubItemReferenceV2025R0 item;

  protected Long status;

  protected String error;

  public HubItemOperationResultV2025R0() {
    super();
  }

  protected HubItemOperationResultV2025R0(Builder builder) {
    super();
    this.action = builder.action;
    this.item = builder.item;
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
        && Objects.equals(status, casted.status)
        && Objects.equals(error, casted.error);
  }

  @Override
  public int hashCode() {
    return Objects.hash(action, item, status, error);
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
