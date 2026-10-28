package com.box.sdkgen.schemas.v2026r0.shuttlereportoutputv2026r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A reference to the generated Box file or folder. */
@JsonFilter("nullablePropertyFilter")
public class ShuttleReportOutputV2026R0 extends SerializableObject {

  /** The unique identifier of the generated Box file or folder. */
  protected final String id;

  /** The Box object type of the generated file or folder. */
  @JsonDeserialize(
      using =
          ShuttleReportOutputV2026R0TypeField.ShuttleReportOutputV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleReportOutputV2026R0TypeField.ShuttleReportOutputV2026R0TypeFieldSerializer.class)
  protected final EnumWrapper<ShuttleReportOutputV2026R0TypeField> type;

  public ShuttleReportOutputV2026R0(String id, ShuttleReportOutputV2026R0TypeField type) {
    super();
    this.id = id;
    this.type = new EnumWrapper<ShuttleReportOutputV2026R0TypeField>(type);
  }

  public ShuttleReportOutputV2026R0(
      @JsonProperty("id") String id,
      @JsonProperty("type") EnumWrapper<ShuttleReportOutputV2026R0TypeField> type) {
    super();
    this.id = id;
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShuttleReportOutputV2026R0TypeField> getType() {
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
    ShuttleReportOutputV2026R0 casted = (ShuttleReportOutputV2026R0) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "ShuttleReportOutputV2026R0{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }
}
