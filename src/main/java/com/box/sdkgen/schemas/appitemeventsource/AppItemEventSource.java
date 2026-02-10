package com.box.sdkgen.schemas.appitemeventsource;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.groupmini.GroupMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** The AppItem that triggered an event in the event stream. */
@JsonFilter("nullablePropertyFilter")
public class AppItemEventSource extends SerializableObject {

  /** The id of the `AppItem`. */
  protected final String id;

  /** The type of the source that this event represents. Can only be `app_item`. */
  @JsonDeserialize(
      using = AppItemEventSourceTypeField.AppItemEventSourceTypeFieldDeserializer.class)
  @JsonSerialize(using = AppItemEventSourceTypeField.AppItemEventSourceTypeFieldSerializer.class)
  protected EnumWrapper<AppItemEventSourceTypeField> type;

  /** The type of the `AppItem`. */
  @JsonProperty("app_item_type")
  protected final String appItemType;

  protected UserMini user;

  protected GroupMini group;

  public AppItemEventSource(
      @JsonProperty("id") String id, @JsonProperty("app_item_type") String appItemType) {
    super();
    this.id = id;
    this.appItemType = appItemType;
    this.type = new EnumWrapper<AppItemEventSourceTypeField>(AppItemEventSourceTypeField.APP_ITEM);
  }

  protected AppItemEventSource(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.appItemType = builder.appItemType;
    this.user = builder.user;
    this.group = builder.group;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<AppItemEventSourceTypeField> getType() {
    return type;
  }

  public String getAppItemType() {
    return appItemType;
  }

  public UserMini getUser() {
    return user;
  }

  public GroupMini getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppItemEventSource casted = (AppItemEventSource) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(appItemType, casted.appItemType)
        && Objects.equals(user, casted.user)
        && Objects.equals(group, casted.group);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, appItemType, user, group);
  }

  @Override
  public String toString() {
    return "AppItemEventSource{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "appItemType='"
        + appItemType
        + '\''
        + ", "
        + "user='"
        + user
        + '\''
        + ", "
        + "group='"
        + group
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<AppItemEventSourceTypeField> type;

    protected final String appItemType;

    protected UserMini user;

    protected GroupMini group;

    public Builder(String id, String appItemType) {
      super();
      this.id = id;
      this.appItemType = appItemType;
    }

    public Builder type(AppItemEventSourceTypeField type) {
      this.type = new EnumWrapper<AppItemEventSourceTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AppItemEventSourceTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder user(UserMini user) {
      this.user = user;
      return this;
    }

    public Builder group(GroupMini group) {
      this.group = group;
      return this;
    }

    public AppItemEventSource build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<AppItemEventSourceTypeField>(AppItemEventSourceTypeField.APP_ITEM);
      }
      return new AppItemEventSource(this);
    }
  }
}
