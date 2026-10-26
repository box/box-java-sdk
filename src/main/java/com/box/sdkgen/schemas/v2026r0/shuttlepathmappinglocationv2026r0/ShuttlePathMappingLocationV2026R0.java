package com.box.sdkgen.schemas.v2026r0.shuttlepathmappinglocationv2026r0;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingaccountlocationv2026r0.ShuttlePathMappingAccountLocationV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingsharedspacelocationv2026r0.ShuttlePathMappingSharedSpaceLocationV2026R0;
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
    using = ShuttlePathMappingLocationV2026R0.ShuttlePathMappingLocationV2026R0Deserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class ShuttlePathMappingLocationV2026R0
    extends OneOfTwo<
        ShuttlePathMappingAccountLocationV2026R0, ShuttlePathMappingSharedSpaceLocationV2026R0> {

  protected final String type;

  protected final String path;

  protected final String pathId;

  protected final Boolean isDirectory;

  public ShuttlePathMappingLocationV2026R0(
      ShuttlePathMappingAccountLocationV2026R0 shuttlePathMappingAccountLocationV2026R0) {
    super(shuttlePathMappingAccountLocationV2026R0, null);
    this.type = EnumWrapper.convertToString(shuttlePathMappingAccountLocationV2026R0.getType());
    this.path = shuttlePathMappingAccountLocationV2026R0.getPath();
    this.pathId = shuttlePathMappingAccountLocationV2026R0.getPathId();
    this.isDirectory = shuttlePathMappingAccountLocationV2026R0.getIsDirectory();
  }

  public ShuttlePathMappingLocationV2026R0(
      ShuttlePathMappingSharedSpaceLocationV2026R0 shuttlePathMappingSharedSpaceLocationV2026R0) {
    super(null, shuttlePathMappingSharedSpaceLocationV2026R0);
    this.type = EnumWrapper.convertToString(shuttlePathMappingSharedSpaceLocationV2026R0.getType());
    this.path = shuttlePathMappingSharedSpaceLocationV2026R0.getPath();
    this.pathId = shuttlePathMappingSharedSpaceLocationV2026R0.getPathId();
    this.isDirectory = shuttlePathMappingSharedSpaceLocationV2026R0.getIsDirectory();
  }

  public boolean isShuttlePathMappingAccountLocationV2026R0() {
    return value0 != null;
  }

  public ShuttlePathMappingAccountLocationV2026R0 getShuttlePathMappingAccountLocationV2026R0() {
    return value0;
  }

  public boolean isShuttlePathMappingSharedSpaceLocationV2026R0() {
    return value1 != null;
  }

  public ShuttlePathMappingSharedSpaceLocationV2026R0
      getShuttlePathMappingSharedSpaceLocationV2026R0() {
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

  static class ShuttlePathMappingLocationV2026R0Deserializer
      extends JsonDeserializer<ShuttlePathMappingLocationV2026R0> {

    public ShuttlePathMappingLocationV2026R0Deserializer() {
      super();
    }

    @Override
    public ShuttlePathMappingLocationV2026R0 deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "account":
            return new ShuttlePathMappingLocationV2026R0(
                JsonManager.deserialize(node, ShuttlePathMappingAccountLocationV2026R0.class));
          case "shared_space":
            return new ShuttlePathMappingLocationV2026R0(
                JsonManager.deserialize(node, ShuttlePathMappingSharedSpaceLocationV2026R0.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize ShuttlePathMappingLocationV2026R0");
    }
  }
}
