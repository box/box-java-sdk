package com.box.sdkgen.schemas.templatesignerverificationmethod;

import com.box.sdkgen.internal.OneOfThree;
import com.box.sdkgen.schemas.templatesignerverificationmethodcacpiv.TemplateSignerVerificationMethodCacPiv;
import com.box.sdkgen.schemas.templatesignerverificationmethodlogin.TemplateSignerVerificationMethodLogin;
import com.box.sdkgen.schemas.templatesignerverificationmethodphonesms.TemplateSignerVerificationMethodPhoneSms;
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
    using = TemplateSignerVerificationMethod.TemplateSignerVerificationMethodDeserializer.class)
@JsonSerialize(using = OneOfThree.OneOfThreeSerializer.class)
public class TemplateSignerVerificationMethod
    extends OneOfThree<
        TemplateSignerVerificationMethodLogin,
        TemplateSignerVerificationMethodPhoneSms,
        TemplateSignerVerificationMethodCacPiv> {

  protected final String type;

  public TemplateSignerVerificationMethod(
      TemplateSignerVerificationMethodLogin templateSignerVerificationMethodLogin) {
    super(templateSignerVerificationMethodLogin, null, null);
    this.type = EnumWrapper.convertToString(templateSignerVerificationMethodLogin.getType());
  }

  public TemplateSignerVerificationMethod(
      TemplateSignerVerificationMethodPhoneSms templateSignerVerificationMethodPhoneSms) {
    super(null, templateSignerVerificationMethodPhoneSms, null);
    this.type = EnumWrapper.convertToString(templateSignerVerificationMethodPhoneSms.getType());
  }

  public TemplateSignerVerificationMethod(
      TemplateSignerVerificationMethodCacPiv templateSignerVerificationMethodCacPiv) {
    super(null, null, templateSignerVerificationMethodCacPiv);
    this.type = EnumWrapper.convertToString(templateSignerVerificationMethodCacPiv.getType());
  }

  public boolean isTemplateSignerVerificationMethodLogin() {
    return value0 != null;
  }

  public TemplateSignerVerificationMethodLogin getTemplateSignerVerificationMethodLogin() {
    return value0;
  }

  public boolean isTemplateSignerVerificationMethodPhoneSms() {
    return value1 != null;
  }

  public TemplateSignerVerificationMethodPhoneSms getTemplateSignerVerificationMethodPhoneSms() {
    return value1;
  }

  public boolean isTemplateSignerVerificationMethodCacPiv() {
    return value2 != null;
  }

  public TemplateSignerVerificationMethodCacPiv getTemplateSignerVerificationMethodCacPiv() {
    return value2;
  }

  public String getType() {
    return type;
  }

  static class TemplateSignerVerificationMethodDeserializer
      extends JsonDeserializer<TemplateSignerVerificationMethod> {

    public TemplateSignerVerificationMethodDeserializer() {
      super();
    }

    @Override
    public TemplateSignerVerificationMethod deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "login":
            return new TemplateSignerVerificationMethod(
                JsonManager.deserialize(node, TemplateSignerVerificationMethodLogin.class));
          case "phone":
            return new TemplateSignerVerificationMethod(
                JsonManager.deserialize(node, TemplateSignerVerificationMethodPhoneSms.class));
          case "cac_piv":
            return new TemplateSignerVerificationMethod(
                JsonManager.deserialize(node, TemplateSignerVerificationMethodCacPiv.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize TemplateSignerVerificationMethod");
    }
  }
}
