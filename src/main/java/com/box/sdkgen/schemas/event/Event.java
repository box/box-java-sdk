package com.box.sdkgen.schemas.event;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.appitemeventsource.AppItemEventSource;
import com.box.sdkgen.schemas.appitemeventsourceoreventsourceorfileorfolderorgenericsourceoruser.AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser;
import com.box.sdkgen.schemas.eventsource.EventSource;
import com.box.sdkgen.schemas.file.File;
import com.box.sdkgen.schemas.folder.Folder;
import com.box.sdkgen.schemas.user.User;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class Event extends SerializableObject {

  protected String type;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date createdAt;

  @JsonProperty("recorded_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date recordedAt;

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

  protected Event(Builder builder) {
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
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getType() {
    return type;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getRecordedAt() {
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

  public static class Builder extends NullableFieldTracker {

    protected String type;

    protected Date createdAt;

    protected Date recordedAt;

    protected String eventId;

    protected UserMini createdBy;

    protected EnumWrapper<EventEventTypeField> eventType;

    protected String sessionId;

    protected AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser source;

    protected EventAdditionalDetailsField additionalDetails;

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder createdAt(Date createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder recordedAt(Date recordedAt) {
      this.recordedAt = recordedAt;
      return this;
    }

    public Builder eventId(String eventId) {
      this.eventId = eventId;
      return this;
    }

    public Builder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder eventType(EventEventTypeField eventType) {
      this.eventType = new EnumWrapper<EventEventTypeField>(eventType);
      return this;
    }

    public Builder eventType(EnumWrapper<EventEventTypeField> eventType) {
      this.eventType = eventType;
      return this;
    }

    public Builder sessionId(String sessionId) {
      this.sessionId = sessionId;
      return this;
    }

    public Builder source(AppItemEventSource source) {
      this.source = new AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(source);
      return this;
    }

    public Builder source(EventSource source) {
      this.source = new AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(source);
      return this;
    }

    public Builder source(File source) {
      this.source = new AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(source);
      return this;
    }

    public Builder source(Folder source) {
      this.source = new AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(source);
      return this;
    }

    public Builder source(Map<String, Object> source) {
      this.source = new AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(source);
      return this;
    }

    public Builder source(User source) {
      this.source = new AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(source);
      return this;
    }

    public Builder source(
        AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser source) {
      this.source = source;
      return this;
    }

    public Builder additionalDetails(EventAdditionalDetailsField additionalDetails) {
      this.additionalDetails = additionalDetails;
      return this;
    }

    public Event build() {
      return new Event(this);
    }
  }
}
