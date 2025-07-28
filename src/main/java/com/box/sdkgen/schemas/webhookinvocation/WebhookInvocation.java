package com.box.sdkgen.schemas.webhookinvocation;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.file.File;
import com.box.sdkgen.schemas.fileorfolder.FileOrFolder;
import com.box.sdkgen.schemas.folder.Folder;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.schemas.webhook.Webhook;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class WebhookInvocation extends SerializableObject {

  protected String id;

  @JsonDeserialize(using = WebhookInvocationTypeField.WebhookInvocationTypeFieldDeserializer.class)
  @JsonSerialize(using = WebhookInvocationTypeField.WebhookInvocationTypeFieldSerializer.class)
  protected EnumWrapper<WebhookInvocationTypeField> type;

  protected Webhook webhook;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date createdAt;

  @JsonDeserialize(
      using = WebhookInvocationTriggerField.WebhookInvocationTriggerFieldDeserializer.class)
  @JsonSerialize(
      using = WebhookInvocationTriggerField.WebhookInvocationTriggerFieldSerializer.class)
  protected EnumWrapper<WebhookInvocationTriggerField> trigger;

  protected FileOrFolder source;

  public WebhookInvocation() {
    super();
  }

  protected WebhookInvocation(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.webhook = builder.webhook;
    this.createdBy = builder.createdBy;
    this.createdAt = builder.createdAt;
    this.trigger = builder.trigger;
    this.source = builder.source;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public Date getCreatedAt() {
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

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<WebhookInvocationTypeField> type;

    protected Webhook webhook;

    protected UserMini createdBy;

    protected Date createdAt;

    protected EnumWrapper<WebhookInvocationTriggerField> trigger;

    protected FileOrFolder source;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(WebhookInvocationTypeField type) {
      this.type = new EnumWrapper<WebhookInvocationTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<WebhookInvocationTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder webhook(Webhook webhook) {
      this.webhook = webhook;
      return this;
    }

    public Builder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder createdAt(Date createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder trigger(WebhookInvocationTriggerField trigger) {
      this.trigger = new EnumWrapper<WebhookInvocationTriggerField>(trigger);
      return this;
    }

    public Builder trigger(EnumWrapper<WebhookInvocationTriggerField> trigger) {
      this.trigger = trigger;
      return this;
    }

    public Builder source(File source) {
      this.source = new FileOrFolder(source);
      return this;
    }

    public Builder source(Folder source) {
      this.source = new FileOrFolder(source);
      return this;
    }

    public Builder source(FileOrFolder source) {
      this.source = source;
      return this;
    }

    public WebhookInvocation build() {
      return new WebhookInvocation(this);
    }
  }
}
