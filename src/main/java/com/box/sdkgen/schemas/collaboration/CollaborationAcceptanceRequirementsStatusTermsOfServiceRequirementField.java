package com.box.sdkgen.schemas.collaboration;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.termsofservicebase.TermsOfServiceBase;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField
    extends SerializableObject {

  @JsonProperty("is_accepted")
  protected Boolean isAccepted;

  @JsonProperty("terms_of_service")
  protected TermsOfServiceBase termsOfService;

  public CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField() {
    super();
  }

  protected CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField(
      CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementFieldBuilder builder) {
    super();
    this.isAccepted = builder.isAccepted;
    this.termsOfService = builder.termsOfService;
  }

  public Boolean getIsAccepted() {
    return isAccepted;
  }

  public TermsOfServiceBase getTermsOfService() {
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
    CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField casted =
        (CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField) o;
    return Objects.equals(isAccepted, casted.isAccepted)
        && Objects.equals(termsOfService, casted.termsOfService);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isAccepted, termsOfService);
  }

  @Override
  public String toString() {
    return "CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField{"
        + "isAccepted='"
        + isAccepted
        + '\''
        + ", "
        + "termsOfService='"
        + termsOfService
        + '\''
        + "}";
  }

  public static
  class CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementFieldBuilder {

    protected Boolean isAccepted;

    protected TermsOfServiceBase termsOfService;

    public CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementFieldBuilder
        isAccepted(Boolean isAccepted) {
      this.isAccepted = isAccepted;
      return this;
    }

    public CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementFieldBuilder
        termsOfService(TermsOfServiceBase termsOfService) {
      this.termsOfService = termsOfService;
      return this;
    }

    public CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField build() {
      return new CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField(this);
    }
  }
}
