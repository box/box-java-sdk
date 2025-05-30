package com.box.sdkgen.managers.usercollaborations;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class CreateCollaborationRequestBodyAccessibleByField extends SerializableObject {

  @JsonDeserialize(
      using =
          CreateCollaborationRequestBodyAccessibleByTypeField
              .CreateCollaborationRequestBodyAccessibleByTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateCollaborationRequestBodyAccessibleByTypeField
              .CreateCollaborationRequestBodyAccessibleByTypeFieldSerializer.class)
  protected final EnumWrapper<CreateCollaborationRequestBodyAccessibleByTypeField> type;

  protected String id;

  protected String login;

  public CreateCollaborationRequestBodyAccessibleByField(
      @JsonProperty("type") EnumWrapper<CreateCollaborationRequestBodyAccessibleByTypeField> type) {
    super();
    this.type = type;
  }

  public CreateCollaborationRequestBodyAccessibleByField(
      CreateCollaborationRequestBodyAccessibleByTypeField type) {
    super();
    this.type = new EnumWrapper<CreateCollaborationRequestBodyAccessibleByTypeField>(type);
  }

  protected CreateCollaborationRequestBodyAccessibleByField(
      CreateCollaborationRequestBodyAccessibleByFieldBuilder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    this.login = builder.login;
  }

  public EnumWrapper<CreateCollaborationRequestBodyAccessibleByTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateCollaborationRequestBodyAccessibleByField casted =
        (CreateCollaborationRequestBodyAccessibleByField) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(id, casted.id)
        && Objects.equals(login, casted.login);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, login);
  }

  @Override
  public String toString() {
    return "CreateCollaborationRequestBodyAccessibleByField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "login='"
        + login
        + '\''
        + "}";
  }

  public static class CreateCollaborationRequestBodyAccessibleByFieldBuilder {

    protected final EnumWrapper<CreateCollaborationRequestBodyAccessibleByTypeField> type;

    protected String id;

    protected String login;

    public CreateCollaborationRequestBodyAccessibleByFieldBuilder(
        EnumWrapper<CreateCollaborationRequestBodyAccessibleByTypeField> type) {
      this.type = type;
    }

    public CreateCollaborationRequestBodyAccessibleByFieldBuilder(
        CreateCollaborationRequestBodyAccessibleByTypeField type) {
      this.type = new EnumWrapper<CreateCollaborationRequestBodyAccessibleByTypeField>(type);
    }

    public CreateCollaborationRequestBodyAccessibleByFieldBuilder id(String id) {
      this.id = id;
      return this;
    }

    public CreateCollaborationRequestBodyAccessibleByFieldBuilder login(String login) {
      this.login = login;
      return this;
    }

    public CreateCollaborationRequestBodyAccessibleByField build() {
      return new CreateCollaborationRequestBodyAccessibleByField(this);
    }
  }
}
