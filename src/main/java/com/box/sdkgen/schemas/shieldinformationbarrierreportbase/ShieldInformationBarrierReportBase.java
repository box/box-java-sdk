package com.box.sdkgen.schemas.shieldinformationbarrierreportbase;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class ShieldInformationBarrierReportBase extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using =
          ShieldInformationBarrierReportBaseTypeField
              .ShieldInformationBarrierReportBaseTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShieldInformationBarrierReportBaseTypeField
              .ShieldInformationBarrierReportBaseTypeFieldSerializer.class)
  protected EnumWrapper<ShieldInformationBarrierReportBaseTypeField> type;

  public ShieldInformationBarrierReportBase() {
    super();
  }

  protected ShieldInformationBarrierReportBase(ShieldInformationBarrierReportBaseBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShieldInformationBarrierReportBaseTypeField> getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldInformationBarrierReportBase casted = (ShieldInformationBarrierReportBase) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierReportBase{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }

  public static class ShieldInformationBarrierReportBaseBuilder {

    protected String id;

    protected EnumWrapper<ShieldInformationBarrierReportBaseTypeField> type;

    public ShieldInformationBarrierReportBaseBuilder id(String id) {
      this.id = id;
      return this;
    }

    public ShieldInformationBarrierReportBaseBuilder type(
        ShieldInformationBarrierReportBaseTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierReportBaseTypeField>(type);
      return this;
    }

    public ShieldInformationBarrierReportBaseBuilder type(
        EnumWrapper<ShieldInformationBarrierReportBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public ShieldInformationBarrierReportBase build() {
      return new ShieldInformationBarrierReportBase(this);
    }
  }
}
