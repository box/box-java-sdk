package com.box.sdkgen.managers.termsofserviceuserstatuses;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateTermsOfServiceStatusForUserByIdRequestBody extends SerializableObject {

  @JsonProperty("is_accepted")
  protected final boolean isAccepted;

  public UpdateTermsOfServiceStatusForUserByIdRequestBody(
      @JsonProperty("is_accepted") boolean isAccepted) {
    super();
    this.isAccepted = isAccepted;
  }

  public boolean getIsAccepted() {
    return isAccepted;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateTermsOfServiceStatusForUserByIdRequestBody casted =
        (UpdateTermsOfServiceStatusForUserByIdRequestBody) o;
    return Objects.equals(isAccepted, casted.isAccepted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isAccepted);
  }

  @Override
  public String toString() {
    return "UpdateTermsOfServiceStatusForUserByIdRequestBody{"
        + "isAccepted='"
        + isAccepted
        + '\''
        + "}";
  }
}
