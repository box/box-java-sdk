package com.box.sdkgen.schemas.v2026r0.shuttlepathmappingtargetlocationinputv2026r0;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingaccountlocationtargetinputv2026r0.ShuttlePathMappingAccountLocationTargetInputV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingsharedspacelocationtargetinputv2026r0.ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0;
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
    using =
        ShuttlePathMappingTargetLocationInputV2026R0
            .ShuttlePathMappingTargetLocationInputV2026R0Deserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class ShuttlePathMappingTargetLocationInputV2026R0
    extends OneOfTwo<
        ShuttlePathMappingAccountLocationTargetInputV2026R0,
        ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0> {

  protected final String type;

  protected final String path;

  protected final String pathId;

  public ShuttlePathMappingTargetLocationInputV2026R0(
      ShuttlePathMappingAccountLocationTargetInputV2026R0
          shuttlePathMappingAccountLocationTargetInputV2026R0) {
    super(shuttlePathMappingAccountLocationTargetInputV2026R0, null);
    this.type =
        EnumWrapper.convertToString(shuttlePathMappingAccountLocationTargetInputV2026R0.getType());
    this.path = shuttlePathMappingAccountLocationTargetInputV2026R0.getPath();
    this.pathId = shuttlePathMappingAccountLocationTargetInputV2026R0.getPathId();
  }

  public ShuttlePathMappingTargetLocationInputV2026R0(
      ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0
          shuttlePathMappingSharedSpaceLocationTargetInputV2026R0) {
    super(null, shuttlePathMappingSharedSpaceLocationTargetInputV2026R0);
    this.type =
        EnumWrapper.convertToString(
            shuttlePathMappingSharedSpaceLocationTargetInputV2026R0.getType());
    this.path = shuttlePathMappingSharedSpaceLocationTargetInputV2026R0.getPath();
    this.pathId = shuttlePathMappingSharedSpaceLocationTargetInputV2026R0.getPathId();
  }

  public boolean isShuttlePathMappingAccountLocationTargetInputV2026R0() {
    return value0 != null;
  }

  public ShuttlePathMappingAccountLocationTargetInputV2026R0
      getShuttlePathMappingAccountLocationTargetInputV2026R0() {
    return value0;
  }

  public boolean isShuttlePathMappingSharedSpaceLocationTargetInputV2026R0() {
    return value1 != null;
  }

  public ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0
      getShuttlePathMappingSharedSpaceLocationTargetInputV2026R0() {
    return value1;
  }

  public String getType() {
    return type;
  }

  public String getPath() {
    return path;
  }

  public String getPathId() {
    return pathId;
  }

  static class ShuttlePathMappingTargetLocationInputV2026R0Deserializer
      extends JsonDeserializer<ShuttlePathMappingTargetLocationInputV2026R0> {

    public ShuttlePathMappingTargetLocationInputV2026R0Deserializer() {
      super();
    }

    @Override
    public ShuttlePathMappingTargetLocationInputV2026R0 deserialize(
        JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "account":
            return new ShuttlePathMappingTargetLocationInputV2026R0(
                JsonManager.deserialize(
                    node, ShuttlePathMappingAccountLocationTargetInputV2026R0.class));
          case "shared_space":
            return new ShuttlePathMappingTargetLocationInputV2026R0(
                JsonManager.deserialize(
                    node, ShuttlePathMappingSharedSpaceLocationTargetInputV2026R0.class));
        }
      }
      throw new JsonMappingException(
          jp, "Unable to deserialize ShuttlePathMappingTargetLocationInputV2026R0");
    }
  }
}
