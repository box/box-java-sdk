package com.box.sdkgen.schemas.signrequestsignerinputvalidation;

import com.box.sdkgen.internal.OneOfTwelve;
import com.box.sdkgen.schemas.signrequestsignerinputcustomvalidation.SignRequestSignerInputCustomValidation;
import com.box.sdkgen.schemas.signrequestsignerinputdateasiavalidation.SignRequestSignerInputDateAsiaValidation;
import com.box.sdkgen.schemas.signrequestsignerinputdateeuvalidation.SignRequestSignerInputDateEuValidation;
import com.box.sdkgen.schemas.signrequestsignerinputdateisovalidation.SignRequestSignerInputDateIsoValidation;
import com.box.sdkgen.schemas.signrequestsignerinputdateusvalidation.SignRequestSignerInputDateUsValidation;
import com.box.sdkgen.schemas.signrequestsignerinputemailvalidation.SignRequestSignerInputEmailValidation;
import com.box.sdkgen.schemas.signrequestsignerinputnumberwithcommavalidation.SignRequestSignerInputNumberWithCommaValidation;
import com.box.sdkgen.schemas.signrequestsignerinputnumberwithperiodvalidation.SignRequestSignerInputNumberWithPeriodValidation;
import com.box.sdkgen.schemas.signrequestsignerinputssnvalidation.SignRequestSignerInputSsnValidation;
import com.box.sdkgen.schemas.signrequestsignerinputzip4validation.SignRequestSignerInputZip4Validation;
import com.box.sdkgen.schemas.signrequestsignerinputzipjpvalidation.SignRequestSignerInputZipjpValidation;
import com.box.sdkgen.schemas.signrequestsignerinputzipvalidation.SignRequestSignerInputZipValidation;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

@JsonDeserialize(
    using = SignRequestSignerInputValidation.SignRequestSignerInputValidationDeserializer.class)
@JsonSerialize(using = OneOfTwelve.OneOfTwelveSerializer.class)
public class SignRequestSignerInputValidation
    extends OneOfTwelve<
        SignRequestSignerInputEmailValidation,
        SignRequestSignerInputCustomValidation,
        SignRequestSignerInputZipValidation,
        SignRequestSignerInputZip4Validation,
        SignRequestSignerInputZipjpValidation,
        SignRequestSignerInputSsnValidation,
        SignRequestSignerInputNumberWithPeriodValidation,
        SignRequestSignerInputNumberWithCommaValidation,
        SignRequestSignerInputDateIsoValidation,
        SignRequestSignerInputDateUsValidation,
        SignRequestSignerInputDateEuValidation,
        SignRequestSignerInputDateAsiaValidation> {

  protected final String validationType;

  public SignRequestSignerInputValidation(
      SignRequestSignerInputEmailValidation signRequestSignerInputEmailValidation) {
    super(
        signRequestSignerInputEmailValidation,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null);
    this.validationType =
        EnumWrapper.convertToString(signRequestSignerInputEmailValidation.getValidationType());
  }

  public SignRequestSignerInputValidation(
      SignRequestSignerInputCustomValidation signRequestSignerInputCustomValidation) {
    super(
        null,
        signRequestSignerInputCustomValidation,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null);
    this.validationType =
        EnumWrapper.convertToString(signRequestSignerInputCustomValidation.getValidationType());
  }

  public SignRequestSignerInputValidation(
      SignRequestSignerInputZipValidation signRequestSignerInputZipValidation) {
    super(
        null,
        null,
        signRequestSignerInputZipValidation,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null);
    this.validationType =
        EnumWrapper.convertToString(signRequestSignerInputZipValidation.getValidationType());
  }

  public SignRequestSignerInputValidation(
      SignRequestSignerInputZip4Validation signRequestSignerInputZip4Validation) {
    super(
        null,
        null,
        null,
        signRequestSignerInputZip4Validation,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null);
    this.validationType =
        EnumWrapper.convertToString(signRequestSignerInputZip4Validation.getValidationType());
  }

  public SignRequestSignerInputValidation(
      SignRequestSignerInputZipjpValidation signRequestSignerInputZipjpValidation) {
    super(
        null,
        null,
        null,
        null,
        signRequestSignerInputZipjpValidation,
        null,
        null,
        null,
        null,
        null,
        null,
        null);
    this.validationType =
        EnumWrapper.convertToString(signRequestSignerInputZipjpValidation.getValidationType());
  }

  public SignRequestSignerInputValidation(
      SignRequestSignerInputSsnValidation signRequestSignerInputSsnValidation) {
    super(
        null,
        null,
        null,
        null,
        null,
        signRequestSignerInputSsnValidation,
        null,
        null,
        null,
        null,
        null,
        null);
    this.validationType =
        EnumWrapper.convertToString(signRequestSignerInputSsnValidation.getValidationType());
  }

  public SignRequestSignerInputValidation(
      SignRequestSignerInputNumberWithPeriodValidation
          signRequestSignerInputNumberWithPeriodValidation) {
    super(
        null,
        null,
        null,
        null,
        null,
        null,
        signRequestSignerInputNumberWithPeriodValidation,
        null,
        null,
        null,
        null,
        null);
    this.validationType =
        EnumWrapper.convertToString(
            signRequestSignerInputNumberWithPeriodValidation.getValidationType());
  }

  public SignRequestSignerInputValidation(
      SignRequestSignerInputNumberWithCommaValidation
          signRequestSignerInputNumberWithCommaValidation) {
    super(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        signRequestSignerInputNumberWithCommaValidation,
        null,
        null,
        null,
        null);
    this.validationType =
        EnumWrapper.convertToString(
            signRequestSignerInputNumberWithCommaValidation.getValidationType());
  }

  public SignRequestSignerInputValidation(
      SignRequestSignerInputDateIsoValidation signRequestSignerInputDateIsoValidation) {
    super(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        signRequestSignerInputDateIsoValidation,
        null,
        null,
        null);
    this.validationType =
        EnumWrapper.convertToString(signRequestSignerInputDateIsoValidation.getValidationType());
  }

  public SignRequestSignerInputValidation(
      SignRequestSignerInputDateUsValidation signRequestSignerInputDateUsValidation) {
    super(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        signRequestSignerInputDateUsValidation,
        null,
        null);
    this.validationType =
        EnumWrapper.convertToString(signRequestSignerInputDateUsValidation.getValidationType());
  }

  public SignRequestSignerInputValidation(
      SignRequestSignerInputDateEuValidation signRequestSignerInputDateEuValidation) {
    super(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        signRequestSignerInputDateEuValidation,
        null);
    this.validationType =
        EnumWrapper.convertToString(signRequestSignerInputDateEuValidation.getValidationType());
  }

  public SignRequestSignerInputValidation(
      SignRequestSignerInputDateAsiaValidation signRequestSignerInputDateAsiaValidation) {
    super(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        signRequestSignerInputDateAsiaValidation);
    this.validationType =
        EnumWrapper.convertToString(signRequestSignerInputDateAsiaValidation.getValidationType());
  }

  public boolean isSignRequestSignerInputEmailValidation() {
    return value0 != null;
  }

  public SignRequestSignerInputEmailValidation getSignRequestSignerInputEmailValidation() {
    return value0;
  }

  public boolean isSignRequestSignerInputCustomValidation() {
    return value1 != null;
  }

  public SignRequestSignerInputCustomValidation getSignRequestSignerInputCustomValidation() {
    return value1;
  }

  public boolean isSignRequestSignerInputZipValidation() {
    return value2 != null;
  }

  public SignRequestSignerInputZipValidation getSignRequestSignerInputZipValidation() {
    return value2;
  }

  public boolean isSignRequestSignerInputZip4Validation() {
    return value3 != null;
  }

  public SignRequestSignerInputZip4Validation getSignRequestSignerInputZip4Validation() {
    return value3;
  }

  public boolean isSignRequestSignerInputZipjpValidation() {
    return value4 != null;
  }

  public SignRequestSignerInputZipjpValidation getSignRequestSignerInputZipjpValidation() {
    return value4;
  }

  public boolean isSignRequestSignerInputSsnValidation() {
    return value5 != null;
  }

  public SignRequestSignerInputSsnValidation getSignRequestSignerInputSsnValidation() {
    return value5;
  }

  public boolean isSignRequestSignerInputNumberWithPeriodValidation() {
    return value6 != null;
  }

  public SignRequestSignerInputNumberWithPeriodValidation
      getSignRequestSignerInputNumberWithPeriodValidation() {
    return value6;
  }

  public boolean isSignRequestSignerInputNumberWithCommaValidation() {
    return value7 != null;
  }

  public SignRequestSignerInputNumberWithCommaValidation
      getSignRequestSignerInputNumberWithCommaValidation() {
    return value7;
  }

  public boolean isSignRequestSignerInputDateIsoValidation() {
    return value8 != null;
  }

  public SignRequestSignerInputDateIsoValidation getSignRequestSignerInputDateIsoValidation() {
    return value8;
  }

  public boolean isSignRequestSignerInputDateUsValidation() {
    return value9 != null;
  }

  public SignRequestSignerInputDateUsValidation getSignRequestSignerInputDateUsValidation() {
    return value9;
  }

  public boolean isSignRequestSignerInputDateEuValidation() {
    return value10 != null;
  }

  public SignRequestSignerInputDateEuValidation getSignRequestSignerInputDateEuValidation() {
    return value10;
  }

  public boolean isSignRequestSignerInputDateAsiaValidation() {
    return value11 != null;
  }

  public SignRequestSignerInputDateAsiaValidation getSignRequestSignerInputDateAsiaValidation() {
    return value11;
  }

  public String getValidationType() {
    return validationType;
  }

  static class SignRequestSignerInputValidationDeserializer
      extends JsonDeserializer<SignRequestSignerInputValidation> {

    public SignRequestSignerInputValidationDeserializer() {
      super();
    }

    @Override
    public SignRequestSignerInputValidation deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      try {
        return new SignRequestSignerInputValidation(
            OneOfTwelve.OBJECT_MAPPER.convertValue(
                node, SignRequestSignerInputEmailValidation.class));
      } catch (Exception ignored) {
      }
      try {
        return new SignRequestSignerInputValidation(
            OneOfTwelve.OBJECT_MAPPER.convertValue(
                node, SignRequestSignerInputCustomValidation.class));
      } catch (Exception ignored) {
      }
      try {
        return new SignRequestSignerInputValidation(
            OneOfTwelve.OBJECT_MAPPER.convertValue(
                node, SignRequestSignerInputZipValidation.class));
      } catch (Exception ignored) {
      }
      try {
        return new SignRequestSignerInputValidation(
            OneOfTwelve.OBJECT_MAPPER.convertValue(
                node, SignRequestSignerInputZip4Validation.class));
      } catch (Exception ignored) {
      }
      try {
        return new SignRequestSignerInputValidation(
            OneOfTwelve.OBJECT_MAPPER.convertValue(
                node, SignRequestSignerInputZipjpValidation.class));
      } catch (Exception ignored) {
      }
      try {
        return new SignRequestSignerInputValidation(
            OneOfTwelve.OBJECT_MAPPER.convertValue(
                node, SignRequestSignerInputSsnValidation.class));
      } catch (Exception ignored) {
      }
      try {
        return new SignRequestSignerInputValidation(
            OneOfTwelve.OBJECT_MAPPER.convertValue(
                node, SignRequestSignerInputNumberWithPeriodValidation.class));
      } catch (Exception ignored) {
      }
      try {
        return new SignRequestSignerInputValidation(
            OneOfTwelve.OBJECT_MAPPER.convertValue(
                node, SignRequestSignerInputNumberWithCommaValidation.class));
      } catch (Exception ignored) {
      }
      try {
        return new SignRequestSignerInputValidation(
            OneOfTwelve.OBJECT_MAPPER.convertValue(
                node, SignRequestSignerInputDateIsoValidation.class));
      } catch (Exception ignored) {
      }
      try {
        return new SignRequestSignerInputValidation(
            OneOfTwelve.OBJECT_MAPPER.convertValue(
                node, SignRequestSignerInputDateUsValidation.class));
      } catch (Exception ignored) {
      }
      try {
        return new SignRequestSignerInputValidation(
            OneOfTwelve.OBJECT_MAPPER.convertValue(
                node, SignRequestSignerInputDateEuValidation.class));
      } catch (Exception ignored) {
      }
      try {
        return new SignRequestSignerInputValidation(
            OneOfTwelve.OBJECT_MAPPER.convertValue(
                node, SignRequestSignerInputDateAsiaValidation.class));
      } catch (Exception ignored) {
      }
      throw new JsonMappingException(jp, "Unable to deserialize SignRequestSignerInputValidation");
    }
  }
}
