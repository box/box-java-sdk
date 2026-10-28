package com.box.sdkgen.schemas.v2026r0.shuttlepathmappinglocationinputv2026r0;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingaccountlocationinputv2026r0.ShuttlePathMappingAccountLocationInputV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingsharedspacelocationinputv2026r0.ShuttlePathMappingSharedSpaceLocationInputV2026R0;
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
        ShuttlePathMappingLocationInputV2026R0.ShuttlePathMappingLocationInputV2026R0Deserializer
            .class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class ShuttlePathMappingLocationInputV2026R0
    extends OneOfTwo<
        ShuttlePathMappingAccountLocationInputV2026R0,
        ShuttlePathMappingSharedSpaceLocationInputV2026R0> {

  protected final String type;

  protected final String path;

  protected final String pathId;

  protected final Boolean isDirectory;

  public ShuttlePathMappingLocationInputV2026R0(
      ShuttlePathMappingAccountLocationInputV2026R0 shuttlePathMappingAccountLocationInputV2026R0) {
    super(shuttlePathMappingAccountLocationInputV2026R0, null);
    this.type =
        EnumWrapper.convertToString(shuttlePathMappingAccountLocationInputV2026R0.getType());
    this.path = shuttlePathMappingAccountLocationInputV2026R0.getPath();
    this.pathId = shuttlePathMappingAccountLocationInputV2026R0.getPathId();
    this.isDirectory = shuttlePathMappingAccountLocationInputV2026R0.getIsDirectory();
  }

  public ShuttlePathMappingLocationInputV2026R0(
      ShuttlePathMappingSharedSpaceLocationInputV2026R0
          shuttlePathMappingSharedSpaceLocationInputV2026R0) {
    super(null, shuttlePathMappingSharedSpaceLocationInputV2026R0);
    this.type =
        EnumWrapper.convertToString(shuttlePathMappingSharedSpaceLocationInputV2026R0.getType());
    this.path = shuttlePathMappingSharedSpaceLocationInputV2026R0.getPath();
    this.pathId = shuttlePathMappingSharedSpaceLocationInputV2026R0.getPathId();
    this.isDirectory = shuttlePathMappingSharedSpaceLocationInputV2026R0.getIsDirectory();
  }

  public boolean isShuttlePathMappingAccountLocationInputV2026R0() {
    return value0 != null;
  }

  public ShuttlePathMappingAccountLocationInputV2026R0
      getShuttlePathMappingAccountLocationInputV2026R0() {
    return value0;
  }

  public boolean isShuttlePathMappingSharedSpaceLocationInputV2026R0() {
    return value1 != null;
  }

  public ShuttlePathMappingSharedSpaceLocationInputV2026R0
      getShuttlePathMappingSharedSpaceLocationInputV2026R0() {
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

  public boolean getIsDirectory() {
    return isDirectory;
  }

  static class ShuttlePathMappingLocationInputV2026R0Deserializer
      extends JsonDeserializer<ShuttlePathMappingLocationInputV2026R0> {

    public ShuttlePathMappingLocationInputV2026R0Deserializer() {
      super();
    }

    @Override
    public ShuttlePathMappingLocationInputV2026R0 deserialize(
        JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "account":
            return new ShuttlePathMappingLocationInputV2026R0(
                JsonManager.deserialize(node, ShuttlePathMappingAccountLocationInputV2026R0.class));
          case "shared_space":
            return new ShuttlePathMappingLocationInputV2026R0(
                JsonManager.deserialize(
                    node, ShuttlePathMappingSharedSpaceLocationInputV2026R0.class));
        }
      }
      throw new JsonMappingException(
          jp, "Unable to deserialize ShuttlePathMappingLocationInputV2026R0");
    }
  }
}
