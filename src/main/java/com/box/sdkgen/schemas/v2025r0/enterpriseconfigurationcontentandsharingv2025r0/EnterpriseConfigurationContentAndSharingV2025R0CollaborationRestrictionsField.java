package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationcontentandsharingv2025r0;

import com.box.sdkgen.schemas.v2025r0.collaborationrestrictionv2025r0.CollaborationRestrictionV2025R0;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitemv2025r0.EnterpriseConfigurationItemV2025R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class EnterpriseConfigurationContentAndSharingV2025R0CollaborationRestrictionsField
    extends EnterpriseConfigurationItemV2025R0 {

  @JsonDeserialize(using = ValueDeserializer.class)
  @JsonSerialize(using = ValueSerializer.class)
  protected List<EnumWrapper<CollaborationRestrictionV2025R0>> value;

  public EnterpriseConfigurationContentAndSharingV2025R0CollaborationRestrictionsField() {
    super();
  }

  protected EnterpriseConfigurationContentAndSharingV2025R0CollaborationRestrictionsField(
      Builder builder) {
    super(builder);
    this.value = builder.value;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<EnumWrapper<CollaborationRestrictionV2025R0>> getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnterpriseConfigurationContentAndSharingV2025R0CollaborationRestrictionsField casted =
        (EnterpriseConfigurationContentAndSharingV2025R0CollaborationRestrictionsField) o;
    return Objects.equals(isUsed, casted.isUsed) && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isUsed, value);
  }

  @Override
  public String toString() {
    return "EnterpriseConfigurationContentAndSharingV2025R0CollaborationRestrictionsField{"
        + "isUsed='"
        + isUsed
        + '\''
        + ", "
        + "value='"
        + value
        + '\''
        + "}";
  }

  public static class Builder extends EnterpriseConfigurationItemV2025R0.Builder {

    protected List<EnumWrapper<CollaborationRestrictionV2025R0>> value;

    public Builder value(List<? extends Valuable> value) {
      this.value = EnumWrapper.wrapValuableEnumList(value, CollaborationRestrictionV2025R0.class);
      return this;
    }

    @Override
    public Builder isUsed(Boolean isUsed) {
      this.isUsed = isUsed;
      return this;
    }

    public EnterpriseConfigurationContentAndSharingV2025R0CollaborationRestrictionsField build() {
      return new EnterpriseConfigurationContentAndSharingV2025R0CollaborationRestrictionsField(
          this);
    }
  }

  public static class ValueDeserializer
      extends JsonDeserializer<List<EnumWrapper<CollaborationRestrictionV2025R0>>> {

    public final JsonDeserializer<EnumWrapper<CollaborationRestrictionV2025R0>> elementDeserializer;

    public ValueDeserializer() {
      super();
      this.elementDeserializer =
          new CollaborationRestrictionV2025R0.CollaborationRestrictionV2025R0Deserializer();
    }

    @Override
    public List<EnumWrapper<CollaborationRestrictionV2025R0>> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = p.getCodec().readTree(p);
      List<EnumWrapper<CollaborationRestrictionV2025R0>> elements = new ArrayList<>();
      for (JsonNode item : node) {
        JsonParser pa = item.traverse(p.getCodec());
        pa.nextToken();
        elements.add(elementDeserializer.deserialize(pa, ctxt));
      }
      return elements;
    }
  }

  public static class ValueSerializer
      extends JsonSerializer<List<EnumWrapper<CollaborationRestrictionV2025R0>>> {

    public final JsonSerializer<EnumWrapper<CollaborationRestrictionV2025R0>> elementSerializer;

    public ValueSerializer() {
      super();
      this.elementSerializer =
          new CollaborationRestrictionV2025R0.CollaborationRestrictionV2025R0Serializer();
    }

    @Override
    public void serialize(
        List<EnumWrapper<CollaborationRestrictionV2025R0>> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeStartArray();
      for (EnumWrapper<CollaborationRestrictionV2025R0> item : value) {
        elementSerializer.serialize(item, gen, serializers);
      }
      gen.writeEndArray();
    }
  }
}
