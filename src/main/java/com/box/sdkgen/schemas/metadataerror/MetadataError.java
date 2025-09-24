package com.box.sdkgen.schemas.metadataerror;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class MetadataError extends SerializableObject {

  protected String code;

  protected String message;

  @JsonProperty("request_id")
  protected String requestId;

  public MetadataError() {
    super();
  }

  protected MetadataError(Builder builder) {
    super();
    this.code = builder.code;
    this.message = builder.message;
    this.requestId = builder.requestId;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public String getRequestId() {
    return requestId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataError casted = (MetadataError) o;
    return Objects.equals(code, casted.code)
        && Objects.equals(message, casted.message)
        && Objects.equals(requestId, casted.requestId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, requestId);
  }

  @Override
  public String toString() {
    return "MetadataError{"
        + "code='"
        + code
        + '\''
        + ", "
        + "message='"
        + message
        + '\''
        + ", "
        + "requestId='"
        + requestId
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String code;

    protected String message;

    protected String requestId;

    public Builder code(String code) {
      this.code = code;
      return this;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder requestId(String requestId) {
      this.requestId = requestId;
      return this;
    }

    public MetadataError build() {
      return new MetadataError(this);
    }
  }
}
