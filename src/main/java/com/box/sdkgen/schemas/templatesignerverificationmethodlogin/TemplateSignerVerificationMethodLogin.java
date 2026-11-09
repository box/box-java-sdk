package com.box.sdkgen.schemas.templatesignerverificationmethodlogin;

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
public class TemplateSignerVerificationMethodLogin extends SerializableObject {

  /** The type of verification method. */
  @JsonDeserialize(
      using =
          TemplateSignerVerificationMethodLoginTypeField
              .TemplateSignerVerificationMethodLoginTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          TemplateSignerVerificationMethodLoginTypeField
              .TemplateSignerVerificationMethodLoginTypeFieldSerializer.class)
  protected EnumWrapper<TemplateSignerVerificationMethodLoginTypeField> type;

  public TemplateSignerVerificationMethodLogin() {
    super();
    this.type =
        new EnumWrapper<TemplateSignerVerificationMethodLoginTypeField>(
            TemplateSignerVerificationMethodLoginTypeField.LOGIN);
  }

  protected TemplateSignerVerificationMethodLogin(Builder builder) {
    super();
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<TemplateSignerVerificationMethodLoginTypeField> getType() {
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
    TemplateSignerVerificationMethodLogin casted = (TemplateSignerVerificationMethodLogin) o;
    return Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type);
  }

  @Override
  public String toString() {
    return "TemplateSignerVerificationMethodLogin{" + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<TemplateSignerVerificationMethodLoginTypeField> type;

    public Builder() {
      super();
    }

    public Builder type(TemplateSignerVerificationMethodLoginTypeField type) {
      this.type = new EnumWrapper<TemplateSignerVerificationMethodLoginTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TemplateSignerVerificationMethodLoginTypeField> type) {
      this.type = type;
      return this;
    }

    public TemplateSignerVerificationMethodLogin build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<TemplateSignerVerificationMethodLoginTypeField>(
                TemplateSignerVerificationMethodLoginTypeField.LOGIN);
      }
      return new TemplateSignerVerificationMethodLogin(this);
    }
  }
}
