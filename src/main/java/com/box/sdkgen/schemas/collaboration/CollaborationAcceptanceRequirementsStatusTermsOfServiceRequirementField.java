package com.box.sdkgen.schemas.collaboration;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.termsofservicebase.TermsOfServiceBase;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField
    extends SerializableObject {

  /**
   * Whether or not the terms of service have been accepted. The field is `null` when there is no
   * terms of service required.
   */
  @JsonProperty("is_accepted")
  @Nullable
  protected Boolean isAccepted;

  @JsonProperty("terms_of_service")
  protected TermsOfServiceBase termsOfService;

  public CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField() {
    super();
  }

  protected CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField(
      Builder builder) {
    super();
    this.isAccepted = builder.isAccepted;
    this.termsOfService = builder.termsOfService;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected Boolean isAccepted;

    protected TermsOfServiceBase termsOfService;

    public Builder isAccepted(Boolean isAccepted) {
      this.isAccepted = isAccepted;
      this.markNullableFieldAsSet("is_accepted");
      return this;
    }

    public Builder termsOfService(TermsOfServiceBase termsOfService) {
      this.termsOfService = termsOfService;
      return this;
    }

    public CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField build() {
      return new CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField(this);
    }
  }
}
