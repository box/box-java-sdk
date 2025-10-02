package com.box.sdkgen.managers.externalusers;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import com.box.sdkgen.parameters.v2025r0.boxversionheaderv2025r0.BoxVersionHeaderV2025R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.Map;

public class SubmitJobToDeleteExternalUsersV2025R0Headers {

  /** Version header. */
  public EnumWrapper<BoxVersionHeaderV2025R0> boxVersion;

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public SubmitJobToDeleteExternalUsersV2025R0Headers() {
    this.boxVersion = new EnumWrapper<BoxVersionHeaderV2025R0>(BoxVersionHeaderV2025R0._2025_0);
    this.extraHeaders = mapOf();
  }

  protected SubmitJobToDeleteExternalUsersV2025R0Headers(Builder builder) {
    this.boxVersion = builder.boxVersion;
    this.extraHeaders = builder.extraHeaders;
  }

  public EnumWrapper<BoxVersionHeaderV2025R0> getBoxVersion() {
    return boxVersion;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected EnumWrapper<BoxVersionHeaderV2025R0> boxVersion;

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.boxVersion = new EnumWrapper<BoxVersionHeaderV2025R0>(BoxVersionHeaderV2025R0._2025_0);
      this.extraHeaders = mapOf();
    }

    public Builder boxVersion(BoxVersionHeaderV2025R0 boxVersion) {
      this.boxVersion = new EnumWrapper<BoxVersionHeaderV2025R0>(boxVersion);
      return this;
    }

    public Builder boxVersion(EnumWrapper<BoxVersionHeaderV2025R0> boxVersion) {
      this.boxVersion = boxVersion;
      return this;
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public SubmitJobToDeleteExternalUsersV2025R0Headers build() {
      return new SubmitJobToDeleteExternalUsersV2025R0Headers(this);
    }
  }
}
