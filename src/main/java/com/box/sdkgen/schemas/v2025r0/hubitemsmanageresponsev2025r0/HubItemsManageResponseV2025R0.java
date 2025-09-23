package com.box.sdkgen.schemas.v2025r0.hubitemsmanageresponsev2025r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.hubitemoperationresultv2025r0.HubItemOperationResultV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class HubItemsManageResponseV2025R0 extends SerializableObject {

  protected final List<HubItemOperationResultV2025R0> operations;

  public HubItemsManageResponseV2025R0(
      @JsonProperty("operations") List<HubItemOperationResultV2025R0> operations) {
    super();
    this.operations = operations;
  }

  public List<HubItemOperationResultV2025R0> getOperations() {
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
    HubItemsManageResponseV2025R0 casted = (HubItemsManageResponseV2025R0) o;
    return Objects.equals(operations, casted.operations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operations);
  }

  @Override
  public String toString() {
    return "HubItemsManageResponseV2025R0{" + "operations='" + operations + '\'' + "}";
  }
}
