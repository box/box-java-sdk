package com.box.sdkgen.schemas.signrequestsignerverificationmethodlogin;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * When used as a verification method the signer will need to log in to a Box account before signing
 * the request. If the signer does not have an existing account, they will have the option to create
 * a free Box account.
 */
@JsonFilter("nullablePropertyFilter")
public class SignRequestSignerVerificationMethodLogin extends SerializableObject {

  /** The type of verification method. */
  @JsonDeserialize(
      using =
          SignRequestSignerVerificationMethodLoginTypeField
              .SignRequestSignerVerificationMethodLoginTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestSignerVerificationMethodLoginTypeField
              .SignRequestSignerVerificationMethodLoginTypeFieldSerializer.class)
  protected EnumWrapper<SignRequestSignerVerificationMethodLoginTypeField> type;

  public SignRequestSignerVerificationMethodLogin() {
    super();
    this.type =
        new EnumWrapper<SignRequestSignerVerificationMethodLoginTypeField>(
            SignRequestSignerVerificationMethodLoginTypeField.LOGIN);
  }

  protected SignRequestSignerVerificationMethodLogin(Builder builder) {
    super();
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerVerificationMethodLoginTypeField> getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignRequestSignerVerificationMethodLogin casted = (SignRequestSignerVerificationMethodLogin) o;
    return Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type);
  }

  @Override
  public String toString() {
    return "SignRequestSignerVerificationMethodLogin{" + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignRequestSignerVerificationMethodLoginTypeField> type;

    public Builder() {
      super();
    }

    public Builder type(SignRequestSignerVerificationMethodLoginTypeField type) {
      this.type = new EnumWrapper<SignRequestSignerVerificationMethodLoginTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<SignRequestSignerVerificationMethodLoginTypeField> type) {
      this.type = type;
      return this;
    }

    public SignRequestSignerVerificationMethodLogin build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<SignRequestSignerVerificationMethodLoginTypeField>(
                SignRequestSignerVerificationMethodLoginTypeField.LOGIN);
      }
      return new SignRequestSignerVerificationMethodLogin(this);
    }
  }
}
