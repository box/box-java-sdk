package com.box.sdkgen.schemas.integrationmappingslackoptions;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class IntegrationMappingSlackOptions extends SerializableObject {

  @JsonProperty("is_access_management_disabled")
  protected Boolean isAccessManagementDisabled;

  public IntegrationMappingSlackOptions() {
    super();
  }

  protected IntegrationMappingSlackOptions(IntegrationMappingSlackOptionsBuilder builder) {
    super();
    this.isAccessManagementDisabled = builder.isAccessManagementDisabled;
  }

  public Boolean getIsAccessManagementDisabled() {
    return isAccessManagementDisabled;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntegrationMappingSlackOptions casted = (IntegrationMappingSlackOptions) o;
    return Objects.equals(isAccessManagementDisabled, casted.isAccessManagementDisabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isAccessManagementDisabled);
  }

  @Override
  public String toString() {
    return "IntegrationMappingSlackOptions{"
        + "isAccessManagementDisabled='"
        + isAccessManagementDisabled
        + '\''
        + "}";
  }

  public static class IntegrationMappingSlackOptionsBuilder {

    protected Boolean isAccessManagementDisabled;

    public IntegrationMappingSlackOptionsBuilder isAccessManagementDisabled(
        Boolean isAccessManagementDisabled) {
      this.isAccessManagementDisabled = isAccessManagementDisabled;
      return this;
    }

    public IntegrationMappingSlackOptions build() {
      return new IntegrationMappingSlackOptions(this);
    }
  }
}
