package com.box.sdkgen.schemas.signrequestsignerverificationmethodcacpiv;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * The verification method to be used when user has to verify themselves using a CAC/PIV smart-card.
 */
@JsonFilter("nullablePropertyFilter")
public class SignRequestSignerVerificationMethodCacPiv extends SerializableObject {

  /** The type of verification method. */
  @JsonDeserialize(
      using =
          SignRequestSignerVerificationMethodCacPivTypeField
              .SignRequestSignerVerificationMethodCacPivTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestSignerVerificationMethodCacPivTypeField
              .SignRequestSignerVerificationMethodCacPivTypeFieldSerializer.class)
  protected EnumWrapper<SignRequestSignerVerificationMethodCacPivTypeField> type;

  public SignRequestSignerVerificationMethodCacPiv() {
    super();
    this.type =
        new EnumWrapper<SignRequestSignerVerificationMethodCacPivTypeField>(
            SignRequestSignerVerificationMethodCacPivTypeField.CAC_PIV);
  }

  protected SignRequestSignerVerificationMethodCacPiv(Builder builder) {
    super();
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerVerificationMethodCacPivTypeField> getType() {
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
    SignRequestSignerVerificationMethodCacPiv casted =
        (SignRequestSignerVerificationMethodCacPiv) o;
    return Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type);
  }

  @Override
  public String toString() {
    return "SignRequestSignerVerificationMethodCacPiv{" + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignRequestSignerVerificationMethodCacPivTypeField> type;

    public Builder() {
      super();
    }

    public Builder type(SignRequestSignerVerificationMethodCacPivTypeField type) {
      this.type = new EnumWrapper<SignRequestSignerVerificationMethodCacPivTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<SignRequestSignerVerificationMethodCacPivTypeField> type) {
      this.type = type;
      return this;
    }

    public SignRequestSignerVerificationMethodCacPiv build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<SignRequestSignerVerificationMethodCacPivTypeField>(
                SignRequestSignerVerificationMethodCacPivTypeField.CAC_PIV);
      }
      return new SignRequestSignerVerificationMethodCacPiv(this);
    }
  }
}
