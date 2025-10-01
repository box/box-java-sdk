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
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class GetEventStreamQueryParams {

  /**
   * Defines the type of events that are returned
   *
   * <p>* `all` returns everything for a user and is the default * `changes` returns events that may
   * cause file tree changes such as file updates or collaborations. * `sync` is similar to
   * `changes` but only applies to synced folders * `admin_logs` returns all events for an entire
   * enterprise and requires the user making the API call to have admin permissions. This stream
   * type is for programmatically pulling from a 1 year history of events across all users within
   * the enterprise and within a `created_after` and `created_before` time frame. The complete
   * history of events will be returned in chronological order based on the event time, but latency
   * will be much higher than `admin_logs_streaming`. * `admin_logs_streaming` returns all events
   * for an entire enterprise and requires the user making the API call to have admin permissions.
   * This stream type is for polling for recent events across all users within the enterprise.
   * Latency will be much lower than `admin_logs`, but events will not be returned in chronological
   * order and may contain duplicates.
   */
  public EnumWrapper<GetEventStreamQueryParamsStreamTypeField> streamType;

  /**
   * The location in the event stream to start receiving events from.
   *
   * <p>* `now` will return an empty list events and the latest stream position for initialization.
   * * `0` or `null` will return all events.
   */
  public String streamPosition;

  /**
   * Limits the number of events returned.
   *
   * <p>Note: Sometimes, the events less than the limit requested can be returned even when there
   * may be more events remaining. This is primarily done in the case where a number of events have
   * already been retrieved and these retrieved events are returned rather than delaying for an
   * unknown amount of time to see if there are any more results.
   */
  public Long limit;

  /**
   * A comma-separated list of events to filter by. This can only be used when requesting the events
   * with a `stream_type` of `admin_logs` or `adming_logs_streaming`. For any other `stream_type`
   * this value will be ignored.
   */
  public List<EnumWrapper<GetEventStreamQueryParamsEventTypeField>> eventType;

  /**
   * The lower bound date and time to return events for. This can only be used when requesting the
   * events with a `stream_type` of `admin_logs`. For any other `stream_type` this value will be
   * ignored.
   */
  public OffsetDateTime createdAfter;

  /**
   * The upper bound date and time to return events for. This can only be used when requesting the
   * events with a `stream_type` of `admin_logs`. For any other `stream_type` this value will be
   * ignored.
   */
  public OffsetDateTime createdBefore;

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

  public OffsetDateTime getCreatedAfter() {
    return createdAfter;
  }

  public OffsetDateTime getCreatedBefore() {
    return createdBefore;
  }

  public static class Builder {

    protected EnumWrapper<GetEventStreamQueryParamsStreamTypeField> streamType;

    protected String streamPosition;

    protected Long limit;

    protected List<EnumWrapper<GetEventStreamQueryParamsEventTypeField>> eventType;

    protected OffsetDateTime createdAfter;

    protected OffsetDateTime createdBefore;

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

    public Builder createdAfter(OffsetDateTime createdAfter) {
      this.createdAfter = createdAfter;
      return this;
    }

    public Builder createdBefore(OffsetDateTime createdBefore) {
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
