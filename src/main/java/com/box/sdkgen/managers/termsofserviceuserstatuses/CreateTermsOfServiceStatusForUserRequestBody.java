package com.box.sdkgen.managers.termsofserviceuserstatuses;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateTermsOfServiceStatusForUserRequestBody extends SerializableObject {

  protected final CreateTermsOfServiceStatusForUserRequestBodyTosField tos;

  protected final CreateTermsOfServiceStatusForUserRequestBodyUserField user;

  @JsonProperty("is_accepted")
  protected final boolean isAccepted;

  public CreateTermsOfServiceStatusForUserRequestBody(
      @JsonProperty("tos") CreateTermsOfServiceStatusForUserRequestBodyTosField tos,
      @JsonProperty("user") CreateTermsOfServiceStatusForUserRequestBodyUserField user,
      @JsonProperty("is_accepted") boolean isAccepted) {
    super();
    this.tos = tos;
    this.user = user;
    this.isAccepted = isAccepted;
  }

  public CreateTermsOfServiceStatusForUserRequestBodyTosField getTos() {
    return tos;
  }

  public CreateTermsOfServiceStatusForUserRequestBodyUserField getUser() {
    return user;
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
    CreateTermsOfServiceStatusForUserRequestBody casted =
        (CreateTermsOfServiceStatusForUserRequestBody) o;
    return Objects.equals(tos, casted.tos)
        && Objects.equals(user, casted.user)
        && Objects.equals(isAccepted, casted.isAccepted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tos, user, isAccepted);
  }

  @Override
  public String toString() {
    return "CreateTermsOfServiceStatusForUserRequestBody{"
        + "tos='"
        + tos
        + '\''
        + ", "
        + "user='"
        + user
        + '\''
        + ", "
        + "isAccepted='"
        + isAccepted
        + '\''
        + "}";
  }
}
