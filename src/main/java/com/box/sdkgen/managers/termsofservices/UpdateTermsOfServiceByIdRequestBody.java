package com.box.sdkgen.managers.termsofservices;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateTermsOfServiceByIdRequestBody extends SerializableObject {

  /** Whether this terms of service is active. */
  @JsonDeserialize(
      using =
          UpdateTermsOfServiceByIdRequestBodyStatusField
              .UpdateTermsOfServiceByIdRequestBodyStatusFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateTermsOfServiceByIdRequestBodyStatusField
              .UpdateTermsOfServiceByIdRequestBodyStatusFieldSerializer.class)
  protected final EnumWrapper<UpdateTermsOfServiceByIdRequestBodyStatusField> status;

  /**
   * The terms of service text to display to users.
   *
   * <p>The text can be set to empty if the `status` is set to `disabled`.
   */
  protected final String text;

  public UpdateTermsOfServiceByIdRequestBody(
      UpdateTermsOfServiceByIdRequestBodyStatusField status, String text) {
    super();
    this.status = new EnumWrapper<UpdateTermsOfServiceByIdRequestBodyStatusField>(status);
    this.text = text;
  }

  public UpdateTermsOfServiceByIdRequestBody(
      @JsonProperty("status") EnumWrapper<UpdateTermsOfServiceByIdRequestBodyStatusField> status,
      @JsonProperty("text") String text) {
    super();
    this.status = status;
    this.text = text;
  }

  public EnumWrapper<UpdateTermsOfServiceByIdRequestBodyStatusField> getStatus() {
    return status;
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
    UpdateTermsOfServiceByIdRequestBody casted = (UpdateTermsOfServiceByIdRequestBody) o;
    return Objects.equals(status, casted.status) && Objects.equals(text, casted.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, text);
  }

  @Override
  public String toString() {
    return "UpdateTermsOfServiceByIdRequestBody{"
        + "status='"
        + status
        + '\''
        + ", "
        + "text='"
        + text
        + '\''
        + "}";
  }
}
