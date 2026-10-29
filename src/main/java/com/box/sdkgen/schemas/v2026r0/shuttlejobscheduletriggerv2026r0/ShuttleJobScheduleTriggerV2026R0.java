package com.box.sdkgen.schemas.v2026r0.shuttlejobscheduletriggerv2026r0;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.v2026r0.shuttlejobscheduletriggerbyjobv2026r0.ShuttleJobScheduleTriggerByJobV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobscheduletriggerbytimev2026r0.ShuttleJobScheduleTriggerByTimeV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

@JsonDeserialize(
    using = ShuttleJobScheduleTriggerV2026R0.ShuttleJobScheduleTriggerV2026R0Deserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class ShuttleJobScheduleTriggerV2026R0
    extends OneOfTwo<
        ShuttleJobScheduleTriggerByTimeV2026R0, ShuttleJobScheduleTriggerByJobV2026R0> {

  protected final String type;

  public ShuttleJobScheduleTriggerV2026R0(
      ShuttleJobScheduleTriggerByTimeV2026R0 shuttleJobScheduleTriggerByTimeV2026R0) {
    super(shuttleJobScheduleTriggerByTimeV2026R0, null);
    this.type = EnumWrapper.convertToString(shuttleJobScheduleTriggerByTimeV2026R0.getType());
  }

  public ShuttleJobScheduleTriggerV2026R0(
      ShuttleJobScheduleTriggerByJobV2026R0 shuttleJobScheduleTriggerByJobV2026R0) {
    super(null, shuttleJobScheduleTriggerByJobV2026R0);
    this.type = EnumWrapper.convertToString(shuttleJobScheduleTriggerByJobV2026R0.getType());
  }

  public boolean isShuttleJobScheduleTriggerByTimeV2026R0() {
    return value0 != null;
  }

  public ShuttleJobScheduleTriggerByTimeV2026R0 getShuttleJobScheduleTriggerByTimeV2026R0() {
    return value0;
  }

  public boolean isShuttleJobScheduleTriggerByJobV2026R0() {
    return value1 != null;
  }

  public ShuttleJobScheduleTriggerByJobV2026R0 getShuttleJobScheduleTriggerByJobV2026R0() {
    return value1;
  }

  public String getType() {
    return type;
  }

  static class ShuttleJobScheduleTriggerV2026R0Deserializer
      extends JsonDeserializer<ShuttleJobScheduleTriggerV2026R0> {

    public ShuttleJobScheduleTriggerV2026R0Deserializer() {
      super();
    }

    @Override
    public ShuttleJobScheduleTriggerV2026R0 deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "by_time":
            return new ShuttleJobScheduleTriggerV2026R0(
                JsonManager.deserialize(node, ShuttleJobScheduleTriggerByTimeV2026R0.class));
          case "by_job":
            return new ShuttleJobScheduleTriggerV2026R0(
                JsonManager.deserialize(node, ShuttleJobScheduleTriggerByJobV2026R0.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize ShuttleJobScheduleTriggerV2026R0");
    }
  }
}
