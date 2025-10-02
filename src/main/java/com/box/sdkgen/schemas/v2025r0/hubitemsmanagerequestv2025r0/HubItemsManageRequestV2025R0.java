package com.box.sdkgen.schemas.v2025r0.hubitemsmanagerequestv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.hubitemoperationv2025r0.HubItemOperationV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import java.util.Objects;

/** Request schema for managing Box Hub items. */
@JsonFilter("nullablePropertyFilter")
public class HubItemsManageRequestV2025R0 extends SerializableObject {

  /** List of operations to perform on Box Hub items. */
  protected List<HubItemOperationV2025R0> operations;

  public HubItemsManageRequestV2025R0() {
    super();
  }

  protected HubItemsManageRequestV2025R0(Builder builder) {
    super();
    this.operations = builder.operations;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<HubItemOperationV2025R0> getOperations() {
    return operations;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HubItemsManageRequestV2025R0 casted = (HubItemsManageRequestV2025R0) o;
    return Objects.equals(operations, casted.operations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operations);
  }

  @Override
  public String toString() {
    return "HubItemsManageRequestV2025R0{" + "operations='" + operations + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<HubItemOperationV2025R0> operations;

    public Builder operations(List<HubItemOperationV2025R0> operations) {
      this.operations = operations;
      return this;
    }

    public HubItemsManageRequestV2025R0 build() {
      return new HubItemsManageRequestV2025R0(this);
    }
  }
}
