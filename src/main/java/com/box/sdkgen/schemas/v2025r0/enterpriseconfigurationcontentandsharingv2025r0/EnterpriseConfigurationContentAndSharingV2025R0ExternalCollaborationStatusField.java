package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationcontentandsharingv2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitemv2025r0.EnterpriseConfigurationItemV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusField
    extends EnterpriseConfigurationItemV2025R0 {

  /** The external collaboration status. */
  @Nullable protected String value;

  public EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusField() {
    super();
  }

  protected EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusField(
      Builder builder) {
    super(builder);
    this.value = builder.value;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getValue() {
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
    EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusField casted =
        (EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusField) o;
    return Objects.equals(isUsed, casted.isUsed) && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isUsed, value);
  }

  @Override
  public String toString() {
    return "EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusField{"
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

    protected String value;

    public Builder value(String value) {
      this.value = value;
      this.markNullableFieldAsSet("value");
      return this;
    }

    @Override
    public Builder isUsed(Boolean isUsed) {
      this.isUsed = isUsed;
      return this;
    }

    public EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusField build() {
      return new EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusField(
          this);
    }
  }
}
