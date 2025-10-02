package com.box.sdkgen.schemas.aiagentinfo;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AiAgentInfoModelsField extends SerializableObject {

  /** The name of the model used for the request. */
  protected String name;

  /** The provider that owns the model used for the request. */
  protected String provider;

  /** The supported purpose utilized by the model used for the request. */
  @JsonProperty("supported_purpose")
  protected String supportedPurpose;

  public AiAgentInfoModelsField() {
    super();
  }

  protected AiAgentInfoModelsField(Builder builder) {
    super();
    this.name = builder.name;
    this.provider = builder.provider;
    this.supportedPurpose = builder.supportedPurpose;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public String getProvider() {
    return provider;
  }

  public String getSupportedPurpose() {
    return supportedPurpose;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiAgentInfoModelsField casted = (AiAgentInfoModelsField) o;
    return Objects.equals(name, casted.name)
        && Objects.equals(provider, casted.provider)
        && Objects.equals(supportedPurpose, casted.supportedPurpose);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, provider, supportedPurpose);
  }

  @Override
  public String toString() {
    return "AiAgentInfoModelsField{"
        + "name='"
        + name
        + '\''
        + ", "
        + "provider='"
        + provider
        + '\''
        + ", "
        + "supportedPurpose='"
        + supportedPurpose
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String name;

    protected String provider;

    protected String supportedPurpose;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder provider(String provider) {
      this.provider = provider;
      return this;
    }

    public Builder supportedPurpose(String supportedPurpose) {
      this.supportedPurpose = supportedPurpose;
      return this;
    }

    public AiAgentInfoModelsField build() {
      return new AiAgentInfoModelsField(this);
    }
  }
}
