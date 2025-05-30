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
import java.util.List;

public class GetEventsQueryParams {

  public EnumWrapper<GetEventsQueryParamsStreamTypeField> streamType;

  public String streamPosition;

  public Long limit;

  public List<EnumWrapper<GetEventsQueryParamsEventTypeField>> eventType;

  public String createdAfter;

  public String createdBefore;

  public GetEventsQueryParams() {}

  protected GetEventsQueryParams(GetEventsQueryParamsBuilder builder) {
    this.streamType = builder.streamType;
    this.streamPosition = builder.streamPosition;
    this.limit = builder.limit;
    this.eventType = builder.eventType;
    this.createdAfter = builder.createdAfter;
    this.createdBefore = builder.createdBefore;
  }

  public EnumWrapper<GetEventsQueryParamsStreamTypeField> getStreamType() {
    return streamType;
  }

  public String getStreamPosition() {
    return streamPosition;
  }

  public Long getLimit() {
    return limit;
  }

  public List<EnumWrapper<GetEventsQueryParamsEventTypeField>> getEventType() {
    return eventType;
  }

  public String getCreatedAfter() {
    return createdAfter;
  }

  public String getCreatedBefore() {
    return createdBefore;
  }

  public static class GetEventsQueryParamsBuilder {

    protected EnumWrapper<GetEventsQueryParamsStreamTypeField> streamType;

    protected String streamPosition;

    protected Long limit;

    protected List<EnumWrapper<GetEventsQueryParamsEventTypeField>> eventType;

    protected String createdAfter;

    protected String createdBefore;

    public GetEventsQueryParamsBuilder streamType(GetEventsQueryParamsStreamTypeField streamType) {
      this.streamType = new EnumWrapper<GetEventsQueryParamsStreamTypeField>(streamType);
      return this;
    }

    public GetEventsQueryParamsBuilder streamType(
        EnumWrapper<GetEventsQueryParamsStreamTypeField> streamType) {
      this.streamType = streamType;
      return this;
    }

    public GetEventsQueryParamsBuilder streamPosition(String streamPosition) {
      this.streamPosition = streamPosition;
      return this;
    }

    public GetEventsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetEventsQueryParamsBuilder eventType(List<? extends Valuable> eventType) {
      this.eventType =
          EnumWrapper.wrapValuableEnumList(eventType, GetEventsQueryParamsEventTypeField.class);
      return this;
    }

    public GetEventsQueryParamsBuilder createdAfter(String createdAfter) {
      this.createdAfter = createdAfter;
      return this;
    }

    public GetEventsQueryParamsBuilder createdBefore(String createdBefore) {
      this.createdBefore = createdBefore;
      return this;
    }

    public GetEventsQueryParams build() {
      return new GetEventsQueryParams(this);
    }
  }

  public static class EventTypeDeserializer
      extends JsonDeserializer<List<EnumWrapper<GetEventsQueryParamsEventTypeField>>> {

    public final JsonDeserializer<EnumWrapper<GetEventsQueryParamsEventTypeField>>
        elementDeserializer;

    public EventTypeDeserializer() {
      super();
      this.elementDeserializer =
          new GetEventsQueryParamsEventTypeField.GetEventsQueryParamsEventTypeFieldDeserializer();
    }

    @Override
    public List<EnumWrapper<GetEventsQueryParamsEventTypeField>> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = p.getCodec().readTree(p);
      List<EnumWrapper<GetEventsQueryParamsEventTypeField>> elements = new ArrayList<>();
      for (JsonNode item : node) {
        JsonParser pa = item.traverse(p.getCodec());
        pa.nextToken();
        elements.add(elementDeserializer.deserialize(pa, ctxt));
      }
      return elements;
    }
  }

  public static class EventTypeSerializer
      extends JsonSerializer<List<EnumWrapper<GetEventsQueryParamsEventTypeField>>> {

    public final JsonSerializer<EnumWrapper<GetEventsQueryParamsEventTypeField>> elementSerializer;

    public EventTypeSerializer() {
      super();
      this.elementSerializer =
          new GetEventsQueryParamsEventTypeField.GetEventsQueryParamsEventTypeFieldSerializer();
    }

    @Override
    public void serialize(
        List<EnumWrapper<GetEventsQueryParamsEventTypeField>> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeStartArray();
      for (EnumWrapper<GetEventsQueryParamsEventTypeField> item : value) {
        elementSerializer.serialize(item, gen, serializers);
      }
      gen.writeEndArray();
    }
  }
}
