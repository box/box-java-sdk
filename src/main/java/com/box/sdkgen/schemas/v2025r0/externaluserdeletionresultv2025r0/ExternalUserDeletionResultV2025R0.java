package com.box.sdkgen.schemas.v2025r0.externaluserdeletionresultv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** Result of a single external user deletion request. */
@JsonFilter("nullablePropertyFilter")
public class ExternalUserDeletionResultV2025R0 extends SerializableObject {

  /** The ID of the external user. */
  @JsonProperty("user_id")
  protected final String userId;

  /** HTTP status code for a specific user's deletion request. */
  protected final long status;

  /**
   * Deletion request status details. This property is only present when the deletion request is not
   * successful.
   */
  protected String detail;

  public ExternalUserDeletionResultV2025R0(
      @JsonProperty("user_id") String userId, @JsonProperty("status") long status) {
    super();
    this.userId = userId;
    this.status = status;
  }

  protected ExternalUserDeletionResultV2025R0(Builder builder) {
    super();
    this.userId = builder.userId;
    this.status = builder.status;
    this.detail = builder.detail;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getUserId() {
    return userId;
  }

  public long getStatus() {
    return status;
  }

  public String getDetail() {
    return detail;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExternalUserDeletionResultV2025R0 casted = (ExternalUserDeletionResultV2025R0) o;
    return Objects.equals(userId, casted.userId)
        && Objects.equals(status, casted.status)
        && Objects.equals(detail, casted.detail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, status, detail);
  }

  @Override
  public String toString() {
    return "ExternalUserDeletionResultV2025R0{"
        + "userId='"
        + userId
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "detail='"
        + detail
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String userId;

    protected final long status;

    protected String detail;

    public Builder(String userId, long status) {
      super();
      this.userId = userId;
      this.status = status;
    }

    public Builder detail(String detail) {
      this.detail = detail;
      return this;
    }

    public ExternalUserDeletionResultV2025R0 build() {
      return new ExternalUserDeletionResultV2025R0(this);
    }
  }
}
