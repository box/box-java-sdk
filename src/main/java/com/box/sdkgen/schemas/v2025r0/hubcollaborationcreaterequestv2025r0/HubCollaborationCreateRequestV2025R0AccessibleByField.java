package com.box.sdkgen.schemas.v2025r0.hubcollaborationcreaterequestv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class HubCollaborationCreateRequestV2025R0AccessibleByField extends SerializableObject {

  /** The type of collaborator to invite. Possible values are `user` or `group`. */
  protected final String type;

  /**
   * The ID of the user or group.
   *
   * <p>Alternatively, use `login` to specify a user by email address.
   */
  protected String id;

  /**
   * The email address of the user who gets access to the item.
   *
   * <p>Alternatively, use `id` to specify a user by user ID.
   */
  protected String login;

  public HubCollaborationCreateRequestV2025R0AccessibleByField(@JsonProperty("type") String type) {
    super();
    this.type = type;
  }

  protected HubCollaborationCreateRequestV2025R0AccessibleByField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    this.login = builder.login;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getType() {
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
    HubCollaborationCreateRequestV2025R0AccessibleByField casted =
        (HubCollaborationCreateRequestV2025R0AccessibleByField) o;
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
    return "HubCollaborationCreateRequestV2025R0AccessibleByField{"
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

  public static class Builder extends NullableFieldTracker {

    protected final String type;

    protected String id;

    protected String login;

    public Builder(String type) {
      super();
      this.type = type;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder login(String login) {
      this.login = login;
      return this;
    }

    public HubCollaborationCreateRequestV2025R0AccessibleByField build() {
      return new HubCollaborationCreateRequestV2025R0AccessibleByField(this);
    }
  }
}
