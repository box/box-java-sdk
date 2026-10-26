package com.box.sdkgen.schemas.v2026r0.shuttlepathentryscopev2026r0;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.v2026r0.shuttlepathentryaccountscopev2026r0.ShuttlePathEntryAccountScopeV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathentrysharedspacescopev2026r0.ShuttlePathEntrySharedSpaceScopeV2026R0;
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
    using = ShuttlePathEntryScopeV2026R0.ShuttlePathEntryScopeV2026R0Deserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class ShuttlePathEntryScopeV2026R0
    extends OneOfTwo<ShuttlePathEntryAccountScopeV2026R0, ShuttlePathEntrySharedSpaceScopeV2026R0> {

  protected final String type;

  public ShuttlePathEntryScopeV2026R0(
      ShuttlePathEntryAccountScopeV2026R0 shuttlePathEntryAccountScopeV2026R0) {
    super(shuttlePathEntryAccountScopeV2026R0, null);
    this.type = EnumWrapper.convertToString(shuttlePathEntryAccountScopeV2026R0.getType());
  }

  public ShuttlePathEntryScopeV2026R0(
      ShuttlePathEntrySharedSpaceScopeV2026R0 shuttlePathEntrySharedSpaceScopeV2026R0) {
    super(null, shuttlePathEntrySharedSpaceScopeV2026R0);
    this.type = EnumWrapper.convertToString(shuttlePathEntrySharedSpaceScopeV2026R0.getType());
  }

  public boolean isShuttlePathEntryAccountScopeV2026R0() {
    return value0 != null;
  }

  public ShuttlePathEntryAccountScopeV2026R0 getShuttlePathEntryAccountScopeV2026R0() {
    return value0;
  }

  public boolean isShuttlePathEntrySharedSpaceScopeV2026R0() {
    return value1 != null;
  }

  public ShuttlePathEntrySharedSpaceScopeV2026R0 getShuttlePathEntrySharedSpaceScopeV2026R0() {
    return value1;
  }

  public String getType() {
    return type;
  }

  static class ShuttlePathEntryScopeV2026R0Deserializer
      extends JsonDeserializer<ShuttlePathEntryScopeV2026R0> {

    public ShuttlePathEntryScopeV2026R0Deserializer() {
      super();
    }

    @Override
    public ShuttlePathEntryScopeV2026R0 deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "account":
            return new ShuttlePathEntryScopeV2026R0(
                JsonManager.deserialize(node, ShuttlePathEntryAccountScopeV2026R0.class));
          case "shared_space":
            return new ShuttlePathEntryScopeV2026R0(
                JsonManager.deserialize(node, ShuttlePathEntrySharedSpaceScopeV2026R0.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize ShuttlePathEntryScopeV2026R0");
    }
  }
}
