package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationcontentandsharingv2025r0;

import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public enum
    EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueField
    implements Valuable {
  ENABLE_EXTERNAL_COLLABORATION("enable_external_collaboration"),
  LIMIT_COLLABORATION_TO_ALLOWLISTED_DOMAINS("limit_collaboration_to_allowlisted_domains");

  private final String value;

  EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueField(
      String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static
  class EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<
              EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueField>> {

    public
    EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<
            EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueField>
        deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(
              EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueField
                  .values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<
                  EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueField>(
                  value));
    }
  }

  public static
  class EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueFieldSerializer
      extends JsonSerializer<
          EnumWrapper<
              EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueField>> {

    public
    EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<
                EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueField>
            value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
