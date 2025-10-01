package com.box.sdkgen.managers.emailaliases;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateUserEmailAliasRequestBody extends SerializableObject {

  /**
   * The email address to add to the account as an alias.
   *
   * <p>Note: The domain of the email alias needs to be registered to your enterprise. See the
   * [domain verification guide](
   * https://support.box.com/hc/en-us/articles/4408619650579-Domain-Verification ) for steps to add
   * a new domain.
   */
  protected final String email;

  public CreateUserEmailAliasRequestBody(@JsonProperty("email") String email) {
    super();
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateUserEmailAliasRequestBody casted = (CreateUserEmailAliasRequestBody) o;
    return Objects.equals(email, casted.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email);
  }

  @Override
  public String toString() {
    return "CreateUserEmailAliasRequestBody{" + "email='" + email + '\'' + "}";
  }
}
