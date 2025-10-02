package com.box.sdkgen.managers.webhooks;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateWebhookByIdRequestBodyTargetField extends SerializableObject {

  /** The ID of the item to trigger a webhook. */
  protected String id;

  /** The type of item to trigger a webhook. */
  @JsonDeserialize(
      using =
          UpdateWebhookByIdRequestBodyTargetTypeField
              .UpdateWebhookByIdRequestBodyTargetTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateWebhookByIdRequestBodyTargetTypeField
              .UpdateWebhookByIdRequestBodyTargetTypeFieldSerializer.class)
  protected EnumWrapper<UpdateWebhookByIdRequestBodyTargetTypeField> type;

  public UpdateWebhookByIdRequestBodyTargetField() {
    super();
  }

  protected UpdateWebhookByIdRequestBodyTargetField(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<UpdateWebhookByIdRequestBodyTargetTypeField> getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateWebhookByIdRequestBodyTargetField casted = (UpdateWebhookByIdRequestBodyTargetField) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "UpdateWebhookByIdRequestBodyTargetField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<UpdateWebhookByIdRequestBodyTargetTypeField> type;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(UpdateWebhookByIdRequestBodyTargetTypeField type) {
      this.type = new EnumWrapper<UpdateWebhookByIdRequestBodyTargetTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<UpdateWebhookByIdRequestBodyTargetTypeField> type) {
      this.type = type;
      return this;
    }

    public UpdateWebhookByIdRequestBodyTargetField build() {
      return new UpdateWebhookByIdRequestBodyTargetField(this);
    }
  }
}
