package com.box.sdkgen.schemas.oauth2error;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class OAuth2Error extends SerializableObject {

  protected String error;

  @JsonProperty("error_description")
  protected String errorDescription;

  public OAuth2Error() {
    super();
  }

  protected OAuth2Error(Builder builder) {
    super();
    this.error = builder.error;
    this.errorDescription = builder.errorDescription;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getError() {
    return error;
  }

  public String getErrorDescription() {
    return errorDescription;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OAuth2Error casted = (OAuth2Error) o;
    return Objects.equals(error, casted.error)
        && Objects.equals(errorDescription, casted.errorDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(error, errorDescription);
  }

  @Override
  public String toString() {
    return "OAuth2Error{"
        + "error='"
        + error
        + '\''
        + ", "
        + "errorDescription='"
        + errorDescription
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String error;

    protected String errorDescription;

    public Builder error(String error) {
      this.error = error;
      return this;
    }

    public Builder errorDescription(String errorDescription) {
      this.errorDescription = errorDescription;
      return this;
    }

    public OAuth2Error build() {
      return new OAuth2Error(this);
    }
  }
}
