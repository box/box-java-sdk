package com.box.sdkgen.schemas.signtemplate;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class SignTemplateAdditionalInfoField extends SerializableObject {

  @JsonDeserialize(using = NonEditableDeserializer.class)
  @JsonSerialize(using = NonEditableSerializer.class)
  @JsonProperty("non_editable")
  protected List<EnumWrapper<SignTemplateAdditionalInfoNonEditableField>> nonEditable;

  protected SignTemplateAdditionalInfoRequiredField required;

  public SignTemplateAdditionalInfoField() {
    super();
  }

  protected SignTemplateAdditionalInfoField(Builder builder) {
    super();
    this.nonEditable = builder.nonEditable;
    this.required = builder.required;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<EnumWrapper<SignTemplateAdditionalInfoNonEditableField>> getNonEditable() {
    return nonEditable;
  }

  public SignTemplateAdditionalInfoRequiredField getRequired() {
    return required;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignTemplateAdditionalInfoField casted = (SignTemplateAdditionalInfoField) o;
    return Objects.equals(nonEditable, casted.nonEditable)
        && Objects.equals(required, casted.required);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nonEditable, required);
  }

  @Override
  public String toString() {
    return "SignTemplateAdditionalInfoField{"
        + "nonEditable='"
        + nonEditable
        + '\''
        + ", "
        + "required='"
        + required
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<EnumWrapper<SignTemplateAdditionalInfoNonEditableField>> nonEditable;

    protected SignTemplateAdditionalInfoRequiredField required;

    public Builder nonEditable(List<? extends Valuable> nonEditable) {
      this.nonEditable =
          EnumWrapper.wrapValuableEnumList(
              nonEditable, SignTemplateAdditionalInfoNonEditableField.class);
      return this;
    }

    public Builder required(SignTemplateAdditionalInfoRequiredField required) {
      this.required = required;
      return this;
    }

    public SignTemplateAdditionalInfoField build() {
      return new SignTemplateAdditionalInfoField(this);
    }
  }

  public static class NonEditableDeserializer
      extends JsonDeserializer<List<EnumWrapper<SignTemplateAdditionalInfoNonEditableField>>> {

    public final JsonDeserializer<EnumWrapper<SignTemplateAdditionalInfoNonEditableField>>
        elementDeserializer;

    public NonEditableDeserializer() {
      super();
      this.elementDeserializer =
          new SignTemplateAdditionalInfoNonEditableField
              .SignTemplateAdditionalInfoNonEditableFieldDeserializer();
    }

    @Override
    public List<EnumWrapper<SignTemplateAdditionalInfoNonEditableField>> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = p.getCodec().readTree(p);
      List<EnumWrapper<SignTemplateAdditionalInfoNonEditableField>> elements = new ArrayList<>();
      for (JsonNode item : node) {
        JsonParser pa = item.traverse(p.getCodec());
        pa.nextToken();
        elements.add(elementDeserializer.deserialize(pa, ctxt));
      }
      return elements;
    }
  }

  public static class NonEditableSerializer
      extends JsonSerializer<List<EnumWrapper<SignTemplateAdditionalInfoNonEditableField>>> {

    public final JsonSerializer<EnumWrapper<SignTemplateAdditionalInfoNonEditableField>>
        elementSerializer;

    public NonEditableSerializer() {
      super();
      this.elementSerializer =
          new SignTemplateAdditionalInfoNonEditableField
              .SignTemplateAdditionalInfoNonEditableFieldSerializer();
    }

    @Override
    public void serialize(
        List<EnumWrapper<SignTemplateAdditionalInfoNonEditableField>> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeStartArray();
      for (EnumWrapper<SignTemplateAdditionalInfoNonEditableField> item : value) {
        elementSerializer.serialize(item, gen, serializers);
      }
      gen.writeEndArray();
    }
  }
}
