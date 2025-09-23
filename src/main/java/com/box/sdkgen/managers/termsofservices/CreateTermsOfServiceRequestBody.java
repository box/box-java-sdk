package com.box.sdkgen.managers.termsofservices;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateTermsOfServiceRequestBody extends SerializableObject {

  @JsonDeserialize(
      using =
          CreateTermsOfServiceRequestBodyStatusField
              .CreateTermsOfServiceRequestBodyStatusFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateTermsOfServiceRequestBodyStatusField
              .CreateTermsOfServiceRequestBodyStatusFieldSerializer.class)
  protected final EnumWrapper<CreateTermsOfServiceRequestBodyStatusField> status;

  @JsonDeserialize(
      using =
          CreateTermsOfServiceRequestBodyTosTypeField
              .CreateTermsOfServiceRequestBodyTosTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateTermsOfServiceRequestBodyTosTypeField
              .CreateTermsOfServiceRequestBodyTosTypeFieldSerializer.class)
  @JsonProperty("tos_type")
  protected EnumWrapper<CreateTermsOfServiceRequestBodyTosTypeField> tosType;

  protected final String text;

  public CreateTermsOfServiceRequestBody(
      CreateTermsOfServiceRequestBodyStatusField status, String text) {
    super();
    this.status = new EnumWrapper<CreateTermsOfServiceRequestBodyStatusField>(status);
    this.text = text;
  }

  public CreateTermsOfServiceRequestBody(
      @JsonProperty("status") EnumWrapper<CreateTermsOfServiceRequestBodyStatusField> status,
      @JsonProperty("text") String text) {
    super();
    this.status = status;
    this.text = text;
  }

  protected CreateTermsOfServiceRequestBody(Builder builder) {
    super();
    this.status = builder.status;
    this.tosType = builder.tosType;
    this.text = builder.text;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<CreateTermsOfServiceRequestBodyStatusField> getStatus() {
    return status;
  }

  public EnumWrapper<CreateTermsOfServiceRequestBodyTosTypeField> getTosType() {
    return tosType;
  }

  public String getText() {
    return text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateTermsOfServiceRequestBody casted = (CreateTermsOfServiceRequestBody) o;
    return Objects.equals(status, casted.status)
        && Objects.equals(tosType, casted.tosType)
        && Objects.equals(text, casted.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, tosType, text);
  }

  @Override
  public String toString() {
    return "CreateTermsOfServiceRequestBody{"
        + "status='"
        + status
        + '\''
        + ", "
        + "tosType='"
        + tosType
        + '\''
        + ", "
        + "text='"
        + text
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final EnumWrapper<CreateTermsOfServiceRequestBodyStatusField> status;

    protected EnumWrapper<CreateTermsOfServiceRequestBodyTosTypeField> tosType;

    protected final String text;

    public Builder(CreateTermsOfServiceRequestBodyStatusField status, String text) {
      super();
      this.status = new EnumWrapper<CreateTermsOfServiceRequestBodyStatusField>(status);
      this.text = text;
    }

    public Builder(EnumWrapper<CreateTermsOfServiceRequestBodyStatusField> status, String text) {
      super();
      this.status = status;
      this.text = text;
    }

    public Builder tosType(CreateTermsOfServiceRequestBodyTosTypeField tosType) {
      this.tosType = new EnumWrapper<CreateTermsOfServiceRequestBodyTosTypeField>(tosType);
      return this;
    }

    public Builder tosType(EnumWrapper<CreateTermsOfServiceRequestBodyTosTypeField> tosType) {
      this.tosType = tosType;
      return this;
    }

    public CreateTermsOfServiceRequestBody build() {
      return new CreateTermsOfServiceRequestBody(this);
    }
  }
}
