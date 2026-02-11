package com.box.sdkgen.schemas.signrequestcancelrequest;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/** Request body for cancelling a sign request. */
@JsonFilter("nullablePropertyFilter")
public class SignRequestCancelRequest extends SerializableObject {

  /** An optional reason for cancelling the sign request. */
  protected String reason;

  public SignRequestCancelRequest() {
    super();
  }

  protected SignRequestCancelRequest(Builder builder) {
    super();
    this.reason = builder.reason;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getReason() {
    return reason;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignRequestCancelRequest casted = (SignRequestCancelRequest) o;
    return Objects.equals(reason, casted.reason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reason);
  }

  @Override
  public String toString() {
    return "SignRequestCancelRequest{" + "reason='" + reason + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String reason;

    public Builder reason(String reason) {
      this.reason = reason;
      return this;
    }

    public SignRequestCancelRequest build() {
      return new SignRequestCancelRequest(this);
    }
  }
}
