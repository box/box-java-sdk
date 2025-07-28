package com.box.sdkgen.schemas.groupminiorusercollaborations;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.groupmini.GroupMini;
import com.box.sdkgen.schemas.usercollaborations.UserCollaborations;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

@JsonDeserialize(
    using = GroupMiniOrUserCollaborations.GroupMiniOrUserCollaborationsDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class GroupMiniOrUserCollaborations extends OneOfTwo<GroupMini, UserCollaborations> {

  protected final String name;

  protected final String id;

  protected final String type;

  public GroupMiniOrUserCollaborations(GroupMini groupMini) {
    super(groupMini, null);
    this.name = groupMini.getName();
    this.id = groupMini.getId();
    this.type = EnumWrapper.convertToString(groupMini.getType());
  }

  public GroupMiniOrUserCollaborations(UserCollaborations userCollaborations) {
    super(null, userCollaborations);
    this.name = userCollaborations.getName();
    this.id = userCollaborations.getId();
    this.type = EnumWrapper.convertToString(userCollaborations.getType());
  }

  public boolean isGroupMini() {
    return value0 != null;
  }

  public GroupMini getGroupMini() {
    return value0;
  }

  public boolean isUserCollaborations() {
    return value1 != null;
  }

  public UserCollaborations getUserCollaborations() {
    return value1;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  static class GroupMiniOrUserCollaborationsDeserializer
      extends JsonDeserializer<GroupMiniOrUserCollaborations> {

    public GroupMiniOrUserCollaborationsDeserializer() {
      super();
    }

    @Override
    public GroupMiniOrUserCollaborations deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "group":
            return new GroupMiniOrUserCollaborations(
                JsonManager.deserialize(node, GroupMini.class));
          case "user":
            return new GroupMiniOrUserCollaborations(
                JsonManager.deserialize(node, UserCollaborations.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize GroupMiniOrUserCollaborations");
    }
  }
}
