package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationcontentandsharingv2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitemv2025r0.EnterpriseConfigurationItemV2025R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusField
    extends EnterpriseConfigurationItemV2025R0 {

  /** The external collaboration status. */
  @JsonDeserialize(
      using =
          EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueField
              .EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueField
              .EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueFieldSerializer
              .class)
  @Nullable
  protected EnumWrapper<
          EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueField>
      value;

  public EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusField() {
    super();
  }

  protected EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusField(
      Builder builder) {
    super(builder);
    this.value = builder.value;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<
          EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueField>
      getValue() {
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

    protected EnumWrapper<
            EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueField>
        value;

    public Builder value(
        EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueField
            value) {
      this.value =
          new EnumWrapper<
              EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueField>(
              value);
      this.markNullableFieldAsSet("value");
      return this;
    }

    public Builder value(
        EnumWrapper<
                EnterpriseConfigurationContentAndSharingV2025R0ExternalCollaborationStatusFieldValueField>
            value) {
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
