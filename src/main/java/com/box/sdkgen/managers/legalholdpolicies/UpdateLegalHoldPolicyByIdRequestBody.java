package com.box.sdkgen.managers.legalholdpolicies;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UpdateLegalHoldPolicyByIdRequestBody extends SerializableObject {

  @JsonProperty("policy_name")
  protected String policyName;

  protected String description;

  @JsonProperty("release_notes")
  protected String releaseNotes;

  public UpdateLegalHoldPolicyByIdRequestBody() {
    super();
  }

  protected UpdateLegalHoldPolicyByIdRequestBody(
      UpdateLegalHoldPolicyByIdRequestBodyBuilder builder) {
    super();
    this.policyName = builder.policyName;
    this.description = builder.description;
    this.releaseNotes = builder.releaseNotes;
  }

  public String getPolicyName() {
    return policyName;
  }

  public String getDescription() {
    return description;
  }

  public String getReleaseNotes() {
    return releaseNotes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateLegalHoldPolicyByIdRequestBody casted = (UpdateLegalHoldPolicyByIdRequestBody) o;
    return Objects.equals(policyName, casted.policyName)
        && Objects.equals(description, casted.description)
        && Objects.equals(releaseNotes, casted.releaseNotes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(policyName, description, releaseNotes);
  }

  @Override
  public String toString() {
    return "UpdateLegalHoldPolicyByIdRequestBody{"
        + "policyName='"
        + policyName
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "releaseNotes='"
        + releaseNotes
        + '\''
        + "}";
  }

  public static class UpdateLegalHoldPolicyByIdRequestBodyBuilder {

    protected String policyName;

    protected String description;

    protected String releaseNotes;

    public UpdateLegalHoldPolicyByIdRequestBodyBuilder policyName(String policyName) {
      this.policyName = policyName;
      return this;
    }

    public UpdateLegalHoldPolicyByIdRequestBodyBuilder description(String description) {
      this.description = description;
      return this;
    }

    public UpdateLegalHoldPolicyByIdRequestBodyBuilder releaseNotes(String releaseNotes) {
      this.releaseNotes = releaseNotes;
      return this;
    }

    public UpdateLegalHoldPolicyByIdRequestBody build() {
      return new UpdateLegalHoldPolicyByIdRequestBody(this);
    }
  }
}
