package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationcontentandsharingv2025r0;

import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitemv2025r0.EnterpriseConfigurationItemV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class EnterpriseConfigurationContentAndSharingV2025R0CollaborationRestrictionsField
    extends EnterpriseConfigurationItemV2025R0 {

  protected List<String> value;

  public EnterpriseConfigurationContentAndSharingV2025R0CollaborationRestrictionsField() {
    super();
  }

  protected EnterpriseConfigurationContentAndSharingV2025R0CollaborationRestrictionsField(
      Builder builder) {
    super(builder);
    this.value = builder.value;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<String> getValue() {
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

    protected List<String> value;

    public Builder value(List<String> value) {
      this.value = value;
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
}
