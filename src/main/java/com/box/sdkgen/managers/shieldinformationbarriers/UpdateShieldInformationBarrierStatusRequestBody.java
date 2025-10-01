package com.box.sdkgen.managers.shieldinformationbarriers;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateShieldInformationBarrierStatusRequestBody extends SerializableObject {

  /** The ID of the shield information barrier. */
  protected final String id;

  /** The desired status for the shield information barrier. */
  @JsonDeserialize(
      using =
          UpdateShieldInformationBarrierStatusRequestBodyStatusField
              .UpdateShieldInformationBarrierStatusRequestBodyStatusFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateShieldInformationBarrierStatusRequestBodyStatusField
              .UpdateShieldInformationBarrierStatusRequestBodyStatusFieldSerializer.class)
  protected final EnumWrapper<UpdateShieldInformationBarrierStatusRequestBodyStatusField> status;

  public UpdateShieldInformationBarrierStatusRequestBody(
      String id, UpdateShieldInformationBarrierStatusRequestBodyStatusField status) {
    super();
    this.id = id;
    this.status =
        new EnumWrapper<UpdateShieldInformationBarrierStatusRequestBodyStatusField>(status);
  }

  public UpdateShieldInformationBarrierStatusRequestBody(
      @JsonProperty("id") String id,
      @JsonProperty("status")
          EnumWrapper<UpdateShieldInformationBarrierStatusRequestBodyStatusField> status) {
    super();
    this.id = id;
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<UpdateShieldInformationBarrierStatusRequestBodyStatusField> getStatus() {
    return status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateShieldInformationBarrierStatusRequestBody casted =
        (UpdateShieldInformationBarrierStatusRequestBody) o;
    return Objects.equals(id, casted.id) && Objects.equals(status, casted.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status);
  }

  @Override
  public String toString() {
    return "UpdateShieldInformationBarrierStatusRequestBody{"
        + "id='"
        + id
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + "}";
  }
}
