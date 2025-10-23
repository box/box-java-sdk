package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitemv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** The basic data included in each enterprise configuration response object. */
@JsonFilter("nullablePropertyFilter")
public class EnterpriseConfigurationItemV2025R0 extends SerializableObject {

  /** Indicates whether a configuration is used for a given enterprise. */
  @JsonProperty("is_used")
  protected Boolean isUsed;

  public EnterpriseConfigurationItemV2025R0() {
    super();
  }

  protected EnterpriseConfigurationItemV2025R0(Builder builder) {
    super();
    this.isUsed = builder.isUsed;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Boolean getIsUsed() {
    return isUsed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnterpriseConfigurationItemV2025R0 casted = (EnterpriseConfigurationItemV2025R0) o;
    return Objects.equals(isUsed, casted.isUsed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isUsed);
  }

  @Override
  public String toString() {
    return "EnterpriseConfigurationItemV2025R0{" + "isUsed='" + isUsed + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Boolean isUsed;

    public Builder isUsed(Boolean isUsed) {
      this.isUsed = isUsed;
      return this;
    }

    public EnterpriseConfigurationItemV2025R0 build() {
      return new EnterpriseConfigurationItemV2025R0(this);
    }
  }
}
