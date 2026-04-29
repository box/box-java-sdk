package com.box.sdkgen.managers.automateworkflows;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import com.box.sdkgen.parameters.v2026r0.boxversionheaderv2026r0.BoxVersionHeaderV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.Map;

public class CreateAutomateWorkflowStartV2026R0Headers {

  /** Version header. */
  public EnumWrapper<BoxVersionHeaderV2026R0> boxVersion;

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public CreateAutomateWorkflowStartV2026R0Headers() {
    this.boxVersion = new EnumWrapper<BoxVersionHeaderV2026R0>(BoxVersionHeaderV2026R0._2026_0);
    this.extraHeaders = mapOf();
  }

  protected CreateAutomateWorkflowStartV2026R0Headers(Builder builder) {
    this.boxVersion = builder.boxVersion;
    this.extraHeaders = builder.extraHeaders;
  }

  public EnumWrapper<BoxVersionHeaderV2026R0> getBoxVersion() {
    return boxVersion;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected EnumWrapper<BoxVersionHeaderV2026R0> boxVersion;

    protected Map<String, String> extraHeaders;

    public Builder() {}

    public Builder boxVersion(BoxVersionHeaderV2026R0 boxVersion) {
      this.boxVersion = new EnumWrapper<BoxVersionHeaderV2026R0>(boxVersion);
      return this;
    }

    public Builder boxVersion(EnumWrapper<BoxVersionHeaderV2026R0> boxVersion) {
      this.boxVersion = boxVersion;
      return this;
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateAutomateWorkflowStartV2026R0Headers build() {
      if (this.boxVersion == null) {
        this.boxVersion = new EnumWrapper<BoxVersionHeaderV2026R0>(BoxVersionHeaderV2026R0._2026_0);
      }
      if (this.extraHeaders == null) {
        this.extraHeaders = mapOf();
      }
      return new CreateAutomateWorkflowStartV2026R0Headers(this);
    }
  }
}
