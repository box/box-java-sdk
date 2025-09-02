package com.box.sdkgen.managers.events;

import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetEventStreamQueryParams {

  public EnumWrapper<GetEventStreamQueryParamsStreamTypeField> streamType;

  public String streamPosition;

  public Long limit;

  public List<EnumWrapper<GetEventStreamQueryParamsEventTypeField>> eventType;

  public Date createdAfter;

  public Date createdBefore;

  public GetEventStreamQueryParams() {}

  protected GetEventStreamQueryParams(Builder builder) {
    this.streamType = builder.streamType;
    this.streamPosition = builder.streamPosition;
    this.limit = builder.limit;
    this.eventType = builder.eventType;
    this.createdAfter = builder.createdAfter;
    this.createdBefore = builder.createdBefore;
  }

  public EnumWrapper<GetEventStreamQueryParamsStreamTypeField> getStreamType() {
    return streamType;
  }

  public String getStreamPosition() {
    return streamPosition;
  }

  public Long getLimit() {
    return limit;
  }

  public List<EnumWrapper<GetEventStreamQueryParamsEventTypeField>> getEventType() {
    return eventType;
  }

  public Date getCreatedAfter() {
    return createdAfter;
  }

  public Date getCreatedBefore() {
    return createdBefore;
  }

  public static class Builder {

    protected EnumWrapper<GetEventStreamQueryParamsStreamTypeField> streamType;

    protected String streamPosition;

    protected Long limit;

    protected List<EnumWrapper<GetEventStreamQueryParamsEventTypeField>> eventType;

    protected Date createdAfter;

    protected Date createdBefore;

    public Builder streamType(GetEventStreamQueryParamsStreamTypeField streamType) {
      this.streamType = new EnumWrapper<GetEventStreamQueryParamsStreamTypeField>(streamType);
      return this;
    }

    public Builder streamType(EnumWrapper<GetEventStreamQueryParamsStreamTypeField> streamType) {
      this.streamType = streamType;
      return this;
    }

    public Builder streamPosition(String streamPosition) {
      this.streamPosition = streamPosition;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder eventType(List<? extends Valuable> eventType) {
      this.eventType =
          EnumWrapper.wrapValuableEnumList(
              eventType, GetEventStreamQueryParamsEventTypeField.class);
      return this;
    }

    public Builder createdAfter(Date createdAfter) {
      this.createdAfter = createdAfter;
      return this;
    }

    public Builder createdBefore(Date createdBefore) {
      this.createdBefore = createdBefore;
      return this;
    }

    public GetEventStreamQueryParams build() {
      return new GetEventStreamQueryParams(this);
    }
  }

  public static class EventTypeDeserializer
      extends JsonDeserializer<List<EnumWrapper<GetEventStreamQueryParamsEventTypeField>>> {

    public final JsonDeserializer<EnumWrapper<GetEventStreamQueryParamsEventTypeField>>
        elementDeserializer;

    public EventTypeDeserializer() {
      super();
      this.elementDeserializer =
          new GetEventStreamQueryParamsEventTypeField
              .GetEventStreamQueryParamsEventTypeFieldDeserializer();
    }

    @Override
    public List<EnumWrapper<GetEventStreamQueryParamsEventTypeField>> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = p.getCodec().readTree(p);
      List<EnumWrapper<GetEventStreamQueryParamsEventTypeField>> elements = new ArrayList<>();
      for (JsonNode item : node) {
        JsonParser pa = item.traverse(p.getCodec());
        pa.nextToken();
        elements.add(elementDeserializer.deserialize(pa, ctxt));
      }
      return elements;
    }
  }

  public static class EventTypeSerializer
      extends JsonSerializer<List<EnumWrapper<GetEventStreamQueryParamsEventTypeField>>> {

    public final JsonSerializer<EnumWrapper<GetEventStreamQueryParamsEventTypeField>>
        elementSerializer;

    public EventTypeSerializer() {
      super();
      this.elementSerializer =
          new GetEventStreamQueryParamsEventTypeField
              .GetEventStreamQueryParamsEventTypeFieldSerializer();
    }

    @Override
    public void serialize(
        List<EnumWrapper<GetEventStreamQueryParamsEventTypeField>> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeStartArray();
      for (EnumWrapper<GetEventStreamQueryParamsEventTypeField> item : value) {
        elementSerializer.serialize(item, gen, serializers);
      }
      gen.writeEndArray();
    }
  }
}
