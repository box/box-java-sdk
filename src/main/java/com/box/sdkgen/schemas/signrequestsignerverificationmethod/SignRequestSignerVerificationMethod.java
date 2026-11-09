package com.box.sdkgen.schemas.signrequestsignerverificationmethod;

import com.box.sdkgen.internal.OneOfThree;
import com.box.sdkgen.schemas.signrequestsignerverificationmethodcacpiv.SignRequestSignerVerificationMethodCacPiv;
import com.box.sdkgen.schemas.signrequestsignerverificationmethodlogin.SignRequestSignerVerificationMethodLogin;
import com.box.sdkgen.schemas.signrequestsignerverificationmethodphonesms.SignRequestSignerVerificationMethodPhoneSms;
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
    using =
        SignRequestSignerVerificationMethod.SignRequestSignerVerificationMethodDeserializer.class)
@JsonSerialize(using = OneOfThree.OneOfThreeSerializer.class)
public class SignRequestSignerVerificationMethod
    extends OneOfThree<
        SignRequestSignerVerificationMethodLogin,
        SignRequestSignerVerificationMethodPhoneSms,
        SignRequestSignerVerificationMethodCacPiv> {

  protected final String type;

  public SignRequestSignerVerificationMethod(
      SignRequestSignerVerificationMethodLogin signRequestSignerVerificationMethodLogin) {
    super(signRequestSignerVerificationMethodLogin, null, null);
    this.type = EnumWrapper.convertToString(signRequestSignerVerificationMethodLogin.getType());
  }

  public SignRequestSignerVerificationMethod(
      SignRequestSignerVerificationMethodPhoneSms signRequestSignerVerificationMethodPhoneSms) {
    super(null, signRequestSignerVerificationMethodPhoneSms, null);
    this.type = EnumWrapper.convertToString(signRequestSignerVerificationMethodPhoneSms.getType());
  }

  public SignRequestSignerVerificationMethod(
      SignRequestSignerVerificationMethodCacPiv signRequestSignerVerificationMethodCacPiv) {
    super(null, null, signRequestSignerVerificationMethodCacPiv);
    this.type = EnumWrapper.convertToString(signRequestSignerVerificationMethodCacPiv.getType());
  }

  public boolean isSignRequestSignerVerificationMethodLogin() {
    return value0 != null;
  }

  public SignRequestSignerVerificationMethodLogin getSignRequestSignerVerificationMethodLogin() {
    return value0;
  }

  public boolean isSignRequestSignerVerificationMethodPhoneSms() {
    return value1 != null;
  }

  public SignRequestSignerVerificationMethodPhoneSms
      getSignRequestSignerVerificationMethodPhoneSms() {
    return value1;
  }

  public boolean isSignRequestSignerVerificationMethodCacPiv() {
    return value2 != null;
  }

  public SignRequestSignerVerificationMethodCacPiv getSignRequestSignerVerificationMethodCacPiv() {
    return value2;
  }

  public String getType() {
    return type;
  }

  static class SignRequestSignerVerificationMethodDeserializer
      extends JsonDeserializer<SignRequestSignerVerificationMethod> {

    public SignRequestSignerVerificationMethodDeserializer() {
      super();
    }

    @Override
    public SignRequestSignerVerificationMethod deserialize(
        JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "login":
            return new SignRequestSignerVerificationMethod(
                JsonManager.deserialize(node, SignRequestSignerVerificationMethodLogin.class));
          case "phone":
            return new SignRequestSignerVerificationMethod(
                JsonManager.deserialize(node, SignRequestSignerVerificationMethodPhoneSms.class));
          case "cac_piv":
            return new SignRequestSignerVerificationMethod(
                JsonManager.deserialize(node, SignRequestSignerVerificationMethodCacPiv.class));
        }
      }
      throw new JsonMappingException(
          jp, "Unable to deserialize SignRequestSignerVerificationMethod");
    }
  }
}
