package com.box.sdkgen.managers.webhooks;

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
public class UpdateWebhookByIdRequestBody extends SerializableObject {

  protected UpdateWebhookByIdRequestBodyTargetField target;

  protected String address;

  @JsonDeserialize(using = TriggersDeserializer.class)
  @JsonSerialize(using = TriggersSerializer.class)
  protected List<EnumWrapper<UpdateWebhookByIdRequestBodyTriggersField>> triggers;

  public UpdateWebhookByIdRequestBody() {
    super();
  }

  protected UpdateWebhookByIdRequestBody(Builder builder) {
    super();
    this.target = builder.target;
    this.address = builder.address;
    this.triggers = builder.triggers;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public UpdateWebhookByIdRequestBodyTargetField getTarget() {
    return target;
  }

  public String getAddress() {
    return address;
  }

  public List<EnumWrapper<UpdateWebhookByIdRequestBodyTriggersField>> getTriggers() {
    return triggers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateWebhookByIdRequestBody casted = (UpdateWebhookByIdRequestBody) o;
    return Objects.equals(target, casted.target)
        && Objects.equals(address, casted.address)
        && Objects.equals(triggers, casted.triggers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(target, address, triggers);
  }

  @Override
  public String toString() {
    return "UpdateWebhookByIdRequestBody{"
        + "target='"
        + target
        + '\''
        + ", "
        + "address='"
        + address
        + '\''
        + ", "
        + "triggers='"
        + triggers
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected UpdateWebhookByIdRequestBodyTargetField target;

    protected String address;

    protected List<EnumWrapper<UpdateWebhookByIdRequestBodyTriggersField>> triggers;

    public Builder target(UpdateWebhookByIdRequestBodyTargetField target) {
      this.target = target;
      return this;
    }

    public Builder address(String address) {
      this.address = address;
      return this;
    }

    public Builder triggers(List<? extends Valuable> triggers) {
      this.triggers =
          EnumWrapper.wrapValuableEnumList(
              triggers, UpdateWebhookByIdRequestBodyTriggersField.class);
      return this;
    }

    public UpdateWebhookByIdRequestBody build() {
      return new UpdateWebhookByIdRequestBody(this);
    }
  }

  public static class TriggersDeserializer
      extends JsonDeserializer<List<EnumWrapper<UpdateWebhookByIdRequestBodyTriggersField>>> {

    public final JsonDeserializer<EnumWrapper<UpdateWebhookByIdRequestBodyTriggersField>>
        elementDeserializer;

    public TriggersDeserializer() {
      super();
      this.elementDeserializer =
          new UpdateWebhookByIdRequestBodyTriggersField
              .UpdateWebhookByIdRequestBodyTriggersFieldDeserializer();
    }

    @Override
    public List<EnumWrapper<UpdateWebhookByIdRequestBodyTriggersField>> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = p.getCodec().readTree(p);
      List<EnumWrapper<UpdateWebhookByIdRequestBodyTriggersField>> elements = new ArrayList<>();
      for (JsonNode item : node) {
        JsonParser pa = item.traverse(p.getCodec());
        pa.nextToken();
        elements.add(elementDeserializer.deserialize(pa, ctxt));
      }
      return elements;
    }
  }

  public static class TriggersSerializer
      extends JsonSerializer<List<EnumWrapper<UpdateWebhookByIdRequestBodyTriggersField>>> {

    public final JsonSerializer<EnumWrapper<UpdateWebhookByIdRequestBodyTriggersField>>
        elementSerializer;

    public TriggersSerializer() {
      super();
      this.elementSerializer =
          new UpdateWebhookByIdRequestBodyTriggersField
              .UpdateWebhookByIdRequestBodyTriggersFieldSerializer();
    }

    @Override
    public void serialize(
        List<EnumWrapper<UpdateWebhookByIdRequestBodyTriggersField>> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeStartArray();
      for (EnumWrapper<UpdateWebhookByIdRequestBodyTriggersField> item : value) {
        elementSerializer.serialize(item, gen, serializers);
      }
      gen.writeEndArray();
    }
  }
}
