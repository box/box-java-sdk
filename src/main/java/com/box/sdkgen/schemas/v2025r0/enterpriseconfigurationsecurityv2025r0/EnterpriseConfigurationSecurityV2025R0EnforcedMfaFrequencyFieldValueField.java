package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationsecurityv2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class EnterpriseConfigurationSecurityV2025R0EnforcedMfaFrequencyFieldValueField
    extends SerializableObject {

  /** Number of days before the user is required to authenticate again. */
  @Nullable protected Long days;

  /** Number of hours before the user is required to authenticate again. */
  @Nullable protected Long hours;

  public EnterpriseConfigurationSecurityV2025R0EnforcedMfaFrequencyFieldValueField() {
    super();
  }

  protected EnterpriseConfigurationSecurityV2025R0EnforcedMfaFrequencyFieldValueField(
      Builder builder) {
    super();
    this.days = builder.days;
    this.hours = builder.hours;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Long getDays() {
    return days;
  }

  public Long getHours() {
    return hours;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnterpriseConfigurationSecurityV2025R0EnforcedMfaFrequencyFieldValueField casted =
        (EnterpriseConfigurationSecurityV2025R0EnforcedMfaFrequencyFieldValueField) o;
    return Objects.equals(days, casted.days) && Objects.equals(hours, casted.hours);
  }

  @Override
  public int hashCode() {
    return Objects.hash(days, hours);
  }

  @Override
  public String toString() {
    return "EnterpriseConfigurationSecurityV2025R0EnforcedMfaFrequencyFieldValueField{"
        + "days='"
        + days
        + '\''
        + ", "
        + "hours='"
        + hours
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Long days;

    protected Long hours;

    public Builder days(Long days) {
      this.days = days;
      this.markNullableFieldAsSet("days");
      return this;
    }

    public Builder hours(Long hours) {
      this.hours = hours;
      this.markNullableFieldAsSet("hours");
      return this;
    }

    public EnterpriseConfigurationSecurityV2025R0EnforcedMfaFrequencyFieldValueField build() {
      return new EnterpriseConfigurationSecurityV2025R0EnforcedMfaFrequencyFieldValueField(this);
    }
  }
}
