package com.box.sdkgen.schemas.templatesignerverificationmethodcacpiv;

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
public class TemplateSignerVerificationMethodCacPiv extends SerializableObject {

  /** The type of verification method. */
  @JsonDeserialize(
      using =
          TemplateSignerVerificationMethodCacPivTypeField
              .TemplateSignerVerificationMethodCacPivTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          TemplateSignerVerificationMethodCacPivTypeField
              .TemplateSignerVerificationMethodCacPivTypeFieldSerializer.class)
  protected EnumWrapper<TemplateSignerVerificationMethodCacPivTypeField> type;

  public TemplateSignerVerificationMethodCacPiv() {
    super();
    this.type =
        new EnumWrapper<TemplateSignerVerificationMethodCacPivTypeField>(
            TemplateSignerVerificationMethodCacPivTypeField.CAC_PIV);
  }

  protected TemplateSignerVerificationMethodCacPiv(Builder builder) {
    super();
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<TemplateSignerVerificationMethodCacPivTypeField> getType() {
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
    TemplateSignerVerificationMethodCacPiv casted = (TemplateSignerVerificationMethodCacPiv) o;
    return Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type);
  }

  @Override
  public String toString() {
    return "TemplateSignerVerificationMethodCacPiv{" + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<TemplateSignerVerificationMethodCacPivTypeField> type;

    public Builder() {
      super();
    }

    public Builder type(TemplateSignerVerificationMethodCacPivTypeField type) {
      this.type = new EnumWrapper<TemplateSignerVerificationMethodCacPivTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TemplateSignerVerificationMethodCacPivTypeField> type) {
      this.type = type;
      return this;
    }

    public TemplateSignerVerificationMethodCacPiv build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<TemplateSignerVerificationMethodCacPivTypeField>(
                TemplateSignerVerificationMethodCacPivTypeField.CAC_PIV);
      }
      return new TemplateSignerVerificationMethodCacPiv(this);
    }
  }
}
