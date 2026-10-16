package com.box.sdkgen.schemas.sharedlinkotpwritable;

import com.box.sdkgen.internal.OneOfThree;
import com.box.sdkgen.schemas.sharedlinkotpwritableallowlist.SharedLinkOtpWritableAllowlist;
import com.box.sdkgen.schemas.sharedlinkotpwritablenone.SharedLinkOtpWritableNone;
import com.box.sdkgen.schemas.sharedlinkotpwritableopen.SharedLinkOtpWritableOpen;
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

@JsonDeserialize(using = SharedLinkOtpWritable.SharedLinkOtpWritableDeserializer.class)
@JsonSerialize(using = OneOfThree.OneOfThreeSerializer.class)
public class SharedLinkOtpWritable
    extends OneOfThree<
        SharedLinkOtpWritableNone, SharedLinkOtpWritableOpen, SharedLinkOtpWritableAllowlist> {

  protected final String mode;

  public SharedLinkOtpWritable(SharedLinkOtpWritableNone sharedLinkOtpWritableNone) {
    super(sharedLinkOtpWritableNone, null, null);
    this.mode = EnumWrapper.convertToString(sharedLinkOtpWritableNone.getMode());
  }

  public SharedLinkOtpWritable(SharedLinkOtpWritableOpen sharedLinkOtpWritableOpen) {
    super(null, sharedLinkOtpWritableOpen, null);
    this.mode = EnumWrapper.convertToString(sharedLinkOtpWritableOpen.getMode());
  }

  public SharedLinkOtpWritable(SharedLinkOtpWritableAllowlist sharedLinkOtpWritableAllowlist) {
    super(null, null, sharedLinkOtpWritableAllowlist);
    this.mode = EnumWrapper.convertToString(sharedLinkOtpWritableAllowlist.getMode());
  }

  public boolean isSharedLinkOtpWritableNone() {
    return value0 != null;
  }

  public SharedLinkOtpWritableNone getSharedLinkOtpWritableNone() {
    return value0;
  }

  public boolean isSharedLinkOtpWritableOpen() {
    return value1 != null;
  }

  public SharedLinkOtpWritableOpen getSharedLinkOtpWritableOpen() {
    return value1;
  }

  public boolean isSharedLinkOtpWritableAllowlist() {
    return value2 != null;
  }

  public SharedLinkOtpWritableAllowlist getSharedLinkOtpWritableAllowlist() {
    return value2;
  }

  public String getMode() {
    return mode;
  }

  static class SharedLinkOtpWritableDeserializer extends JsonDeserializer<SharedLinkOtpWritable> {

    public SharedLinkOtpWritableDeserializer() {
      super();
    }

    @Override
    public SharedLinkOtpWritable deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      try {
        return new SharedLinkOtpWritable(
            OneOfThree.OBJECT_MAPPER.convertValue(node, SharedLinkOtpWritableNone.class));
      } catch (Exception ignored) {
      }
      try {
        return new SharedLinkOtpWritable(
            OneOfThree.OBJECT_MAPPER.convertValue(node, SharedLinkOtpWritableOpen.class));
      } catch (Exception ignored) {
      }
      try {
        return new SharedLinkOtpWritable(
            OneOfThree.OBJECT_MAPPER.convertValue(node, SharedLinkOtpWritableAllowlist.class));
      } catch (Exception ignored) {
      }
      throw new JsonMappingException(jp, "Unable to deserialize SharedLinkOtpWritable");
    }
  }
}
