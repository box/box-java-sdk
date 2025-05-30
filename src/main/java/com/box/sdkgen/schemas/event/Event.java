package com.box.sdkgen.schemas.event;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.appitemeventsourceoreventsourceorfileorfolderorgenericsourceoruser.AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class Event extends SerializableObject {

  protected String type;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("recorded_at")
  protected String recordedAt;

  @JsonProperty("event_id")
  protected String eventId;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonDeserialize(using = EventEventTypeField.EventEventTypeFieldDeserializer.class)
  @JsonSerialize(using = EventEventTypeField.EventEventTypeFieldSerializer.class)
  @JsonProperty("event_type")
  protected EnumWrapper<EventEventTypeField> eventType;

  @JsonProperty("session_id")
  protected String sessionId;

  protected AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser source;

  @JsonProperty("additional_details")
  protected EventAdditionalDetailsField additionalDetails;

  public Event() {
    super();
  }

  protected Event(EventBuilder builder) {
    super();
    this.type = builder.type;
    this.createdAt = builder.createdAt;
    this.recordedAt = builder.recordedAt;
    this.eventId = builder.eventId;
    this.createdBy = builder.createdBy;
    this.eventType = builder.eventType;
    this.sessionId = builder.sessionId;
    this.source = builder.source;
    this.additionalDetails = builder.additionalDetails;
  }

  public String getType() {
    return type;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getRecordedAt() {
    return recordedAt;
  }

  public String getEventId() {
    return eventId;
  }

  public UserMini getCreatedBy() {
    return createdBy;
  }

  public EnumWrapper<EventEventTypeField> getEventType() {
    return eventType;
  }

  public String getSessionId() {
    return sessionId;
  }

  public AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser getSource() {
    return source;
  }

  public EventAdditionalDetailsField getAdditionalDetails() {
    return additionalDetails;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Event casted = (Event) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(recordedAt, casted.recordedAt)
        && Objects.equals(eventId, casted.eventId)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(eventType, casted.eventType)
        && Objects.equals(sessionId, casted.sessionId)
        && Objects.equals(source, casted.source)
        && Objects.equals(additionalDetails, casted.additionalDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        type,
        createdAt,
        recordedAt,
        eventId,
        createdBy,
        eventType,
        sessionId,
        source,
        additionalDetails);
  }

  @Override
  public String toString() {
    return "Event{"
        + "type='"
        + type
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "recordedAt='"
        + recordedAt
        + '\''
        + ", "
        + "eventId='"
        + eventId
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "eventType='"
        + eventType
        + '\''
        + ", "
        + "sessionId='"
        + sessionId
        + '\''
        + ", "
        + "source='"
        + source
        + '\''
        + ", "
        + "additionalDetails='"
        + additionalDetails
        + '\''
        + "}";
  }

  public static class EventBuilder {

    protected String type;

    protected String createdAt;

    protected String recordedAt;

    protected String eventId;

    protected UserMini createdBy;

    protected EnumWrapper<EventEventTypeField> eventType;

    protected String sessionId;

    protected AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser source;

    protected EventAdditionalDetailsField additionalDetails;

    public EventBuilder type(String type) {
      this.type = type;
      return this;
    }

    public EventBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public EventBuilder recordedAt(String recordedAt) {
      this.recordedAt = recordedAt;
      return this;
    }

    public EventBuilder eventId(String eventId) {
      this.eventId = eventId;
      return this;
    }

    public EventBuilder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public EventBuilder eventType(EventEventTypeField eventType) {
      this.eventType = new EnumWrapper<EventEventTypeField>(eventType);
      return this;
    }

    public EventBuilder eventType(EnumWrapper<EventEventTypeField> eventType) {
      this.eventType = eventType;
      return this;
    }

    public EventBuilder sessionId(String sessionId) {
      this.sessionId = sessionId;
      return this;
    }

    public EventBuilder source(
        AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser source) {
      this.source = source;
      return this;
    }

    public EventBuilder additionalDetails(EventAdditionalDetailsField additionalDetails) {
      this.additionalDetails = additionalDetails;
      return this;
    }

    public Event build() {
      return new Event(this);
    }
  }
}
