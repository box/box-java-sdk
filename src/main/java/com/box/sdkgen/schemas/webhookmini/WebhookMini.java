package com.box.sdkgen.schemas.webhookmini;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** Represents a configured webhook. */
@JsonFilter("nullablePropertyFilter")
public class WebhookMini extends SerializableObject {

  /** The unique identifier for this webhook. */
  protected String id;

  /** The value will always be `webhook`. */
  @JsonDeserialize(using = WebhookMiniTypeField.WebhookMiniTypeFieldDeserializer.class)
  @JsonSerialize(using = WebhookMiniTypeField.WebhookMiniTypeFieldSerializer.class)
  protected EnumWrapper<WebhookMiniTypeField> type;

  /** The item that will trigger the webhook. */
  protected WebhookMiniTargetField target;

  public WebhookMini() {
    super();
  }

  protected WebhookMini(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.target = builder.target;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<WebhookMiniTypeField> getType() {
    return type;
  }

  public WebhookMiniTargetField getTarget() {
    return target;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WebhookMini casted = (WebhookMini) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(target, casted.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, target);
  }

  @Override
  public String toString() {
    return "WebhookMini{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "target='"
        + target
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<WebhookMiniTypeField> type;

    protected WebhookMiniTargetField target;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(WebhookMiniTypeField type) {
      this.type = new EnumWrapper<WebhookMiniTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<WebhookMiniTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder target(WebhookMiniTargetField target) {
      this.target = target;
      return this;
    }

    public WebhookMini build() {
      return new WebhookMini(this);
    }
  }
}
