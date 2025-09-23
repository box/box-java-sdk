package com.box.sdkgen.schemas.collaborationaccessgrantee;

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

@JsonDeserialize(using = CollaborationAccessGrantee.CollaborationAccessGranteeDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class CollaborationAccessGrantee extends OneOfTwo<UserCollaborations, GroupMini> {

  protected final String name;

  protected final String id;

  protected final String type;

  public CollaborationAccessGrantee(UserCollaborations userCollaborations) {
    super(userCollaborations, null);
    this.name = userCollaborations.getName();
    this.id = userCollaborations.getId();
    this.type = EnumWrapper.convertToString(userCollaborations.getType());
  }

  public CollaborationAccessGrantee(GroupMini groupMini) {
    super(null, groupMini);
    this.name = groupMini.getName();
    this.id = groupMini.getId();
    this.type = EnumWrapper.convertToString(groupMini.getType());
  }

  public boolean isUserCollaborations() {
    return value0 != null;
  }

  public UserCollaborations getUserCollaborations() {
    return value0;
  }

  public boolean isGroupMini() {
    return value1 != null;
  }

  public GroupMini getGroupMini() {
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

  static class CollaborationAccessGranteeDeserializer
      extends JsonDeserializer<CollaborationAccessGrantee> {

    public CollaborationAccessGranteeDeserializer() {
      super();
    }

    @Override
    public CollaborationAccessGrantee deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "user":
            return new CollaborationAccessGrantee(
                JsonManager.deserialize(node, UserCollaborations.class));
          case "group":
            return new CollaborationAccessGrantee(JsonManager.deserialize(node, GroupMini.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize CollaborationAccessGrantee");
    }
  }
}
