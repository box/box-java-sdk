package com.box.sdkgen.schemas.webhookinvocation;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.fileorfolder.FileOrFolder;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.schemas.webhook.Webhook;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class WebhookInvocation extends SerializableObject {

  protected String id;

  @JsonDeserialize(using = WebhookInvocationTypeField.WebhookInvocationTypeFieldDeserializer.class)
  @JsonSerialize(using = WebhookInvocationTypeField.WebhookInvocationTypeFieldSerializer.class)
  protected EnumWrapper<WebhookInvocationTypeField> type;

  protected Webhook webhook;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonDeserialize(
      using = WebhookInvocationTriggerField.WebhookInvocationTriggerFieldDeserializer.class)
  @JsonSerialize(
      using = WebhookInvocationTriggerField.WebhookInvocationTriggerFieldSerializer.class)
  protected EnumWrapper<WebhookInvocationTriggerField> trigger;

  protected FileOrFolder source;

  public WebhookInvocation() {
    super();
  }

  protected WebhookInvocation(WebhookInvocationBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.webhook = builder.webhook;
    this.createdBy = builder.createdBy;
    this.createdAt = builder.createdAt;
    this.trigger = builder.trigger;
    this.source = builder.source;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<WebhookInvocationTypeField> getType() {
    return type;
  }

  public Webhook getWebhook() {
    return webhook;
  }

  public UserMini getCreatedBy() {
    return createdBy;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public EnumWrapper<WebhookInvocationTriggerField> getTrigger() {
    return trigger;
  }

  public FileOrFolder getSource() {
    return source;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WebhookInvocation casted = (WebhookInvocation) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(webhook, casted.webhook)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(trigger, casted.trigger)
        && Objects.equals(source, casted.source);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, webhook, createdBy, createdAt, trigger, source);
  }

  @Override
  public String toString() {
    return "WebhookInvocation{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "webhook='"
        + webhook
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "trigger='"
        + trigger
        + '\''
        + ", "
        + "source='"
        + source
        + '\''
        + "}";
  }

  public static class WebhookInvocationBuilder {

    protected String id;

    protected EnumWrapper<WebhookInvocationTypeField> type;

    protected Webhook webhook;

    protected UserMini createdBy;

    protected String createdAt;

    protected EnumWrapper<WebhookInvocationTriggerField> trigger;

    protected FileOrFolder source;

    public WebhookInvocationBuilder id(String id) {
      this.id = id;
      return this;
    }

    public WebhookInvocationBuilder type(WebhookInvocationTypeField type) {
      this.type = new EnumWrapper<WebhookInvocationTypeField>(type);
      return this;
    }

    public WebhookInvocationBuilder type(EnumWrapper<WebhookInvocationTypeField> type) {
      this.type = type;
      return this;
    }

    public WebhookInvocationBuilder webhook(Webhook webhook) {
      this.webhook = webhook;
      return this;
    }

    public WebhookInvocationBuilder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public WebhookInvocationBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public WebhookInvocationBuilder trigger(WebhookInvocationTriggerField trigger) {
      this.trigger = new EnumWrapper<WebhookInvocationTriggerField>(trigger);
      return this;
    }

    public WebhookInvocationBuilder trigger(EnumWrapper<WebhookInvocationTriggerField> trigger) {
      this.trigger = trigger;
      return this;
    }

    public WebhookInvocationBuilder source(FileOrFolder source) {
      this.source = source;
      return this;
    }

    public WebhookInvocation build() {
      return new WebhookInvocation(this);
    }
  }
}
