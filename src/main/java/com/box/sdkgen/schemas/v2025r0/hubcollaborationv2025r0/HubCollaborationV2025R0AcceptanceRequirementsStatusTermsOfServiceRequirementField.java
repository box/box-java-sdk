package com.box.sdkgen.schemas.v2025r0.hubcollaborationv2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.termsofservicebasev2025r0.TermsOfServiceBaseV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class HubCollaborationV2025R0AcceptanceRequirementsStatusTermsOfServiceRequirementField
    extends SerializableObject {

  @JsonProperty("is_accepted")
  @Nullable
  protected Boolean isAccepted;

  @JsonProperty("terms_of_service")
  protected TermsOfServiceBaseV2025R0 termsOfService;

  public HubCollaborationV2025R0AcceptanceRequirementsStatusTermsOfServiceRequirementField() {
    super();
  }

  protected HubCollaborationV2025R0AcceptanceRequirementsStatusTermsOfServiceRequirementField(
      Builder builder) {
    super();
    this.isAccepted = builder.isAccepted;
    this.termsOfService = builder.termsOfService;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Boolean getIsAccepted() {
    return isAccepted;
  }

  public TermsOfServiceBaseV2025R0 getTermsOfService() {
    return termsOfService;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HubCollaborationV2025R0AcceptanceRequirementsStatusTermsOfServiceRequirementField casted =
        (HubCollaborationV2025R0AcceptanceRequirementsStatusTermsOfServiceRequirementField) o;
    return Objects.equals(isAccepted, casted.isAccepted)
        && Objects.equals(termsOfService, casted.termsOfService);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isAccepted, termsOfService);
  }

  @Override
  public String toString() {
    return "HubCollaborationV2025R0AcceptanceRequirementsStatusTermsOfServiceRequirementField{"
        + "isAccepted='"
        + isAccepted
        + '\''
        + ", "
        + "termsOfService='"
        + termsOfService
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Boolean isAccepted;

    protected TermsOfServiceBaseV2025R0 termsOfService;

    public Builder isAccepted(Boolean isAccepted) {
      this.isAccepted = isAccepted;
      this.markNullableFieldAsSet("is_accepted");
      return this;
    }

    public Builder termsOfService(TermsOfServiceBaseV2025R0 termsOfService) {
      this.termsOfService = termsOfService;
      return this;
    }

    public HubCollaborationV2025R0AcceptanceRequirementsStatusTermsOfServiceRequirementField
        build() {
      return new HubCollaborationV2025R0AcceptanceRequirementsStatusTermsOfServiceRequirementField(
          this);
    }
  }
}
