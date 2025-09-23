package com.box.sdkgen.managers.legalholdpolicies;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateLegalHoldPolicyByIdRequestBody extends SerializableObject {

  @JsonProperty("policy_name")
  protected String policyName;

  protected String description;

  @JsonProperty("release_notes")
  protected String releaseNotes;

  public UpdateLegalHoldPolicyByIdRequestBody() {
    super();
  }

  protected UpdateLegalHoldPolicyByIdRequestBody(Builder builder) {
    super();
    this.policyName = builder.policyName;
    this.description = builder.description;
    this.releaseNotes = builder.releaseNotes;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected String policyName;

    protected String description;

    protected String releaseNotes;

    public Builder policyName(String policyName) {
      this.policyName = policyName;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder releaseNotes(String releaseNotes) {
      this.releaseNotes = releaseNotes;
      return this;
    }

    public UpdateLegalHoldPolicyByIdRequestBody build() {
      return new UpdateLegalHoldPolicyByIdRequestBody(this);
    }
  }
}
