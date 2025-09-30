package com.box.sdkgen.schemas.v2025r0.shieldlistcontentintegrationv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ShieldListContentIntegrationV2025R0 extends SerializableObject {

  @JsonDeserialize(
      using =
          ShieldListContentIntegrationV2025R0TypeField
              .ShieldListContentIntegrationV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShieldListContentIntegrationV2025R0TypeField
              .ShieldListContentIntegrationV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<ShieldListContentIntegrationV2025R0TypeField> type;

  protected final List<ShieldListContentIntegrationV2025R0IntegrationsField> integrations;

  public ShieldListContentIntegrationV2025R0(
      @JsonProperty("integrations")
          List<ShieldListContentIntegrationV2025R0IntegrationsField> integrations) {
    super();
    this.integrations = integrations;
    this.type =
        new EnumWrapper<ShieldListContentIntegrationV2025R0TypeField>(
            ShieldListContentIntegrationV2025R0TypeField.INTEGRATION);
  }

  protected ShieldListContentIntegrationV2025R0(Builder builder) {
    super();
    this.type = builder.type;
    this.integrations = builder.integrations;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShieldListContentIntegrationV2025R0TypeField> getType() {
    return type;
  }

  public List<ShieldListContentIntegrationV2025R0IntegrationsField> getIntegrations() {
    return integrations;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldListContentIntegrationV2025R0 casted = (ShieldListContentIntegrationV2025R0) o;
    return Objects.equals(type, casted.type) && Objects.equals(integrations, casted.integrations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, integrations);
  }

  @Override
  public String toString() {
    return "ShieldListContentIntegrationV2025R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "integrations='"
        + integrations
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ShieldListContentIntegrationV2025R0TypeField> type;

    protected final List<ShieldListContentIntegrationV2025R0IntegrationsField> integrations;

    public Builder(List<ShieldListContentIntegrationV2025R0IntegrationsField> integrations) {
      super();
      this.integrations = integrations;
      this.type =
          new EnumWrapper<ShieldListContentIntegrationV2025R0TypeField>(
              ShieldListContentIntegrationV2025R0TypeField.INTEGRATION);
    }

    public Builder type(ShieldListContentIntegrationV2025R0TypeField type) {
      this.type = new EnumWrapper<ShieldListContentIntegrationV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShieldListContentIntegrationV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public ShieldListContentIntegrationV2025R0 build() {
      return new ShieldListContentIntegrationV2025R0(this);
    }
  }
}
