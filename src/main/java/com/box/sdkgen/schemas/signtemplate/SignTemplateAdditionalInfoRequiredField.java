package com.box.sdkgen.schemas.signtemplate;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.annotation.JsonFilter;
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
public class SignTemplateAdditionalInfoRequiredField extends SerializableObject {

  /** Required signer fields. */
  @JsonDeserialize(using = SignersDeserializer.class)
  @JsonSerialize(using = SignersSerializer.class)
  protected List<List<EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField>>> signers;

  public SignTemplateAdditionalInfoRequiredField() {
    super();
  }

  protected SignTemplateAdditionalInfoRequiredField(Builder builder) {
    super();
    this.signers = builder.signers;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<List<EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField>>> getSigners() {
    return signers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignTemplateAdditionalInfoRequiredField casted = (SignTemplateAdditionalInfoRequiredField) o;
    return Objects.equals(signers, casted.signers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(signers);
  }

  @Override
  public String toString() {
    return "SignTemplateAdditionalInfoRequiredField{" + "signers='" + signers + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<List<EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField>>> signers;

    public Builder signers(List<List<? extends Valuable>> signers) {
      this.signers =
          EnumWrapper.wrapValuableEnumListOfLists(
              signers, SignTemplateAdditionalInfoRequiredSignersField.class);
      return this;
    }

    public SignTemplateAdditionalInfoRequiredField build() {
      return new SignTemplateAdditionalInfoRequiredField(this);
    }
  }

  public static class SignersDeserializer
      extends JsonDeserializer<
          List<List<EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField>>>> {

    public final JsonDeserializer<EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField>>
        elementDeserializer;

    public SignersDeserializer() {
      super();
      this.elementDeserializer =
          new SignTemplateAdditionalInfoRequiredSignersField
              .SignTemplateAdditionalInfoRequiredSignersFieldDeserializer();
    }

    @Override
    public List<List<EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField>>> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = p.getCodec().readTree(p);
      List<List<EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField>>> elements =
          new ArrayList<>();
      for (JsonNode item : node) {
        List<EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField>> innerElements =
            new ArrayList<>();
        for (JsonNode innerItem : item) {
          JsonParser pa = innerItem.traverse(p.getCodec());
          pa.nextToken();
          innerElements.add(elementDeserializer.deserialize(pa, ctxt));
        }
        elements.add(innerElements);
      }
      return elements;
    }
  }

  public static class SignersSerializer
      extends JsonSerializer<
          List<List<EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField>>>> {

    public final JsonSerializer<EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField>>
        elementSerializer;

    public SignersSerializer() {
      super();
      this.elementSerializer =
          new SignTemplateAdditionalInfoRequiredSignersField
              .SignTemplateAdditionalInfoRequiredSignersFieldSerializer();
    }

    @Override
    public void serialize(
        List<List<EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField>>> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeStartArray();
      for (List<EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField>> item : value) {
        gen.writeStartArray();
        for (EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField> innerItem : item) {
          elementSerializer.serialize(innerItem, gen, serializers);
        }
        gen.writeEndArray();
      }
      gen.writeEndArray();
    }
  }
}
