package com.box.sdkgen.managers.webhooks;

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
public class CreateWebhookRequestBody extends SerializableObject {

  /** The item that will trigger the webhook. */
  protected final CreateWebhookRequestBodyTargetField target;

  /** The URL that is notified by this webhook. */
  protected final String address;

  /** An array of event names that this webhook is to be triggered for. */
  @JsonDeserialize(using = TriggersDeserializer.class)
  @JsonSerialize(using = TriggersSerializer.class)
  protected final List<EnumWrapper<CreateWebhookRequestBodyTriggersField>> triggers;

  public CreateWebhookRequestBody(
      @JsonProperty("target") CreateWebhookRequestBodyTargetField target,
      @JsonProperty("address") String address,
      @JsonProperty("triggers") List<? extends Valuable> triggers) {
    super();
    this.target = target;
    this.address = address;
    this.triggers =
        EnumWrapper.wrapValuableEnumList(triggers, CreateWebhookRequestBodyTriggersField.class);
  }

  public CreateWebhookRequestBodyTargetField getTarget() {
    return target;
  }

  public String getAddress() {
    return address;
  }

  public List<EnumWrapper<CreateWebhookRequestBodyTriggersField>> getTriggers() {
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
    CreateWebhookRequestBody casted = (CreateWebhookRequestBody) o;
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
    return "CreateWebhookRequestBody{"
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

  public static class TriggersDeserializer
      extends JsonDeserializer<List<EnumWrapper<CreateWebhookRequestBodyTriggersField>>> {

    public final JsonDeserializer<EnumWrapper<CreateWebhookRequestBodyTriggersField>>
        elementDeserializer;

    public TriggersDeserializer() {
      super();
      this.elementDeserializer =
          new CreateWebhookRequestBodyTriggersField
              .CreateWebhookRequestBodyTriggersFieldDeserializer();
    }

    @Override
    public List<EnumWrapper<CreateWebhookRequestBodyTriggersField>> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = p.getCodec().readTree(p);
      List<EnumWrapper<CreateWebhookRequestBodyTriggersField>> elements = new ArrayList<>();
      for (JsonNode item : node) {
        JsonParser pa = item.traverse(p.getCodec());
        pa.nextToken();
        elements.add(elementDeserializer.deserialize(pa, ctxt));
      }
      return elements;
    }
  }

  public static class TriggersSerializer
      extends JsonSerializer<List<EnumWrapper<CreateWebhookRequestBodyTriggersField>>> {

    public final JsonSerializer<EnumWrapper<CreateWebhookRequestBodyTriggersField>>
        elementSerializer;

    public TriggersSerializer() {
      super();
      this.elementSerializer =
          new CreateWebhookRequestBodyTriggersField
              .CreateWebhookRequestBodyTriggersFieldSerializer();
    }

    @Override
    public void serialize(
        List<EnumWrapper<CreateWebhookRequestBodyTriggersField>> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeStartArray();
      for (EnumWrapper<CreateWebhookRequestBodyTriggersField> item : value) {
        elementSerializer.serialize(item, gen, serializers);
      }
      gen.writeEndArray();
    }
  }
}
