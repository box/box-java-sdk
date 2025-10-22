package com.box.sdkgen.schemas.v2025r0.enterprisefeaturesettingv2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class EnterpriseFeatureSettingV2025R0FeatureField extends SerializableObject {

  /** The identifier of the feature. */
  @Nullable protected String id;

  public EnterpriseFeatureSettingV2025R0FeatureField() {
    super();
  }

  protected EnterpriseFeatureSettingV2025R0FeatureField(Builder builder) {
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
    EnterpriseFeatureSettingV2025R0FeatureField casted =
        (EnterpriseFeatureSettingV2025R0FeatureField) o;
    return Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "EnterpriseFeatureSettingV2025R0FeatureField{" + "id='" + id + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    public Builder id(String id) {
      this.id = id;
      this.markNullableFieldAsSet("id");
      return this;
    }

    public EnterpriseFeatureSettingV2025R0FeatureField build() {
      return new EnterpriseFeatureSettingV2025R0FeatureField(this);
    }
  }
}
