package com.box.sdkgen.schemas.v2025r0.shieldlistcontentintegrationv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ShieldListContentIntegrationV2025R0IntegrationsField extends SerializableObject {

  /** The ID of the integration. */
  protected String id;

  public ShieldListContentIntegrationV2025R0IntegrationsField() {
    super();
  }

  protected ShieldListContentIntegrationV2025R0IntegrationsField(Builder builder) {
    super();
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldListContentIntegrationV2025R0IntegrationsField casted =
        (ShieldListContentIntegrationV2025R0IntegrationsField) o;
    return Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "ShieldListContentIntegrationV2025R0IntegrationsField{" + "id='" + id + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public ShieldListContentIntegrationV2025R0IntegrationsField build() {
      return new ShieldListContentIntegrationV2025R0IntegrationsField(this);
    }
  }
}
