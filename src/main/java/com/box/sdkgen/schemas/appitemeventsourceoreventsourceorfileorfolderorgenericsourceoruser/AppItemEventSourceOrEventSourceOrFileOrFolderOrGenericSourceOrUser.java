package com.box.sdkgen.schemas.appitemeventsourceoreventsourceorfileorfolderorgenericsourceoruser;

import com.box.sdkgen.internal.OneOfSix;
import com.box.sdkgen.schemas.appitemeventsource.AppItemEventSource;
import com.box.sdkgen.schemas.eventsource.EventSource;
import com.box.sdkgen.schemas.file.File;
import com.box.sdkgen.schemas.folder.Folder;
import com.box.sdkgen.schemas.user.User;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.util.Map;

@JsonDeserialize(
    using =
        AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser
            .AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUserDeserializer.class)
@JsonSerialize(using = OneOfSix.OneOfSixSerializer.class)
public class AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser
    extends OneOfSix<AppItemEventSource, EventSource, File, Folder, Map<String, Object>, User> {

  public AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(
      AppItemEventSource appItemEventSource) {
    super(appItemEventSource, null, null, null, null, null);
  }

  public AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(
      EventSource eventSource) {
    super(null, eventSource, null, null, null, null);
  }

  public AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(File file) {
    super(null, null, file, null, null, null);
  }

  public AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(Folder folder) {
    super(null, null, null, folder, null, null);
  }

  public AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(
      Map<String, Object> map) {
    super(null, null, null, null, map, null);
  }

  public AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(User user) {
    super(null, null, null, null, null, user);
  }

  public boolean isAppItemEventSource() {
    return value0 != null;
  }

  public AppItemEventSource getAppItemEventSource() {
    return value0;
  }

  public boolean isEventSource() {
    return value1 != null;
  }

  public EventSource getEventSource() {
    return value1;
  }

  public boolean isFile() {
    return value2 != null;
  }

  public File getFile() {
    return value2;
  }

  public boolean isFolder() {
    return value3 != null;
  }

  public Folder getFolder() {
    return value3;
  }

  public boolean isMap() {
    return value4 != null;
  }

  public Map<String, Object> getMap() {
    return value4;
  }

  public boolean isUser() {
    return value5 != null;
  }

  public User getUser() {
    return value5;
  }

  static class AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUserDeserializer
      extends JsonDeserializer<AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser> {

    public AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUserDeserializer() {
      super();
    }

    @Override
    public AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser deserialize(
        JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "app_item":
            return new AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(
                JsonManager.deserialize(node, AppItemEventSource.class));
          case "file":
            return new AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(
                JsonManager.deserialize(node, File.class));
          case "folder":
            return new AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(
                JsonManager.deserialize(node, Folder.class));
          case "user":
            return new AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(
                JsonManager.deserialize(node, User.class));
        }
      }
      JsonNode discriminant1 = node.get("item_type");
      if (!(discriminant1 == null)) {
        switch (discriminant1.asText()) {
          case "file":
            return new AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(
                JsonManager.deserialize(node, EventSource.class));
          case "folder":
            return new AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(
                JsonManager.deserialize(node, EventSource.class));
        }
      }
      try {
        return new AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser(
            OneOfSix.OBJECT_MAPPER.convertValue(node, Map.class));
      } catch (Exception ignored) {
      }
      throw new JsonMappingException(
          jp,
          "Unable to deserialize AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser");
    }
  }
}
