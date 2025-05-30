package com.box.sdkgen.schemas.appitemeventsource;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.groupmini.GroupMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AppItemEventSource extends SerializableObject {

  protected final String id;

  @JsonDeserialize(
      using = AppItemEventSourceTypeField.AppItemEventSourceTypeFieldDeserializer.class)
  @JsonSerialize(using = AppItemEventSourceTypeField.AppItemEventSourceTypeFieldSerializer.class)
  protected EnumWrapper<AppItemEventSourceTypeField> type;

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

  protected AppItemEventSource(AppItemEventSourceBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.appItemType = builder.appItemType;
    this.user = builder.user;
    this.group = builder.group;
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

  public static class AppItemEventSourceBuilder {

    protected final String id;

    protected EnumWrapper<AppItemEventSourceTypeField> type;

    protected final String appItemType;

    protected UserMini user;

    protected GroupMini group;

    public AppItemEventSourceBuilder(String id, String appItemType) {
      this.id = id;
      this.appItemType = appItemType;
      this.type =
          new EnumWrapper<AppItemEventSourceTypeField>(AppItemEventSourceTypeField.APP_ITEM);
    }

    public AppItemEventSourceBuilder type(AppItemEventSourceTypeField type) {
      this.type = new EnumWrapper<AppItemEventSourceTypeField>(type);
      return this;
    }

    public AppItemEventSourceBuilder type(EnumWrapper<AppItemEventSourceTypeField> type) {
      this.type = type;
      return this;
    }

    public AppItemEventSourceBuilder user(UserMini user) {
      this.user = user;
      return this;
    }

    public AppItemEventSourceBuilder group(GroupMini group) {
      this.group = group;
      return this;
    }

    public AppItemEventSource build() {
      return new AppItemEventSource(this);
    }
  }
}
