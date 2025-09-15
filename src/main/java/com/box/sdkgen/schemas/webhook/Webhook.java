package com.box.sdkgen.schemas.webhook;

import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.schemas.webhookmini.WebhookMini;
import com.box.sdkgen.schemas.webhookmini.WebhookMiniTargetField;
import com.box.sdkgen.schemas.webhookmini.WebhookMiniTypeField;
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
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class Webhook extends WebhookMini {

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  protected String address;

  @JsonDeserialize(using = TriggersDeserializer.class)
  @JsonSerialize(using = TriggersSerializer.class)
  protected List<EnumWrapper<WebhookTriggersField>> triggers;

  public Webhook() {
    super();
  }

  protected Webhook(Builder builder) {
    super(builder);
    this.createdBy = builder.createdBy;
    this.createdAt = builder.createdAt;
    this.address = builder.address;
    this.triggers = builder.triggers;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public UserMini getCreatedBy() {
    return createdBy;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public String getAddress() {
    return address;
  }

  public List<EnumWrapper<WebhookTriggersField>> getTriggers() {
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
    Webhook casted = (Webhook) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(target, casted.target)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(address, casted.address)
        && Objects.equals(triggers, casted.triggers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, target, createdBy, createdAt, address, triggers);
  }

  @Override
  public String toString() {
    return "Webhook{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "target='"
        + target
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
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

  public static class Builder extends WebhookMini.Builder {

    protected UserMini createdBy;

    protected OffsetDateTime createdAt;

    protected String address;

    protected List<EnumWrapper<WebhookTriggersField>> triggers;

    public Builder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder address(String address) {
      this.address = address;
      return this;
    }

    public Builder triggers(List<? extends Valuable> triggers) {
      this.triggers = EnumWrapper.wrapValuableEnumList(triggers, WebhookTriggersField.class);
      return this;
    }

    @Override
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    @Override
    public Builder type(WebhookMiniTypeField type) {
      this.type = new EnumWrapper<WebhookMiniTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<WebhookMiniTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder target(WebhookMiniTargetField target) {
      this.target = target;
      return this;
    }

    public Webhook build() {
      return new Webhook(this);
    }
  }

  public static class TriggersDeserializer
      extends JsonDeserializer<List<EnumWrapper<WebhookTriggersField>>> {

    public final JsonDeserializer<EnumWrapper<WebhookTriggersField>> elementDeserializer;

    public TriggersDeserializer() {
      super();
      this.elementDeserializer = new WebhookTriggersField.WebhookTriggersFieldDeserializer();
    }

    @Override
    public List<EnumWrapper<WebhookTriggersField>> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = p.getCodec().readTree(p);
      List<EnumWrapper<WebhookTriggersField>> elements = new ArrayList<>();
      for (JsonNode item : node) {
        JsonParser pa = item.traverse(p.getCodec());
        pa.nextToken();
        elements.add(elementDeserializer.deserialize(pa, ctxt));
      }
      return elements;
    }
  }

  public static class TriggersSerializer
      extends JsonSerializer<List<EnumWrapper<WebhookTriggersField>>> {

    public final JsonSerializer<EnumWrapper<WebhookTriggersField>> elementSerializer;

    public TriggersSerializer() {
      super();
      this.elementSerializer = new WebhookTriggersField.WebhookTriggersFieldSerializer();
    }

    @Override
    public void serialize(
        List<EnumWrapper<WebhookTriggersField>> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeStartArray();
      for (EnumWrapper<WebhookTriggersField> item : value) {
        elementSerializer.serialize(item, gen, serializers);
      }
      gen.writeEndArray();
    }
  }
}
