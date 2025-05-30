package com.box.sdkgen.schemas.webhook;

import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public enum WebhookTriggersField implements Valuable {
  FILE_UPLOADED("FILE.UPLOADED"),
  FILE_PREVIEWED("FILE.PREVIEWED"),
  FILE_DOWNLOADED("FILE.DOWNLOADED"),
  FILE_TRASHED("FILE.TRASHED"),
  FILE_DELETED("FILE.DELETED"),
  FILE_RESTORED("FILE.RESTORED"),
  FILE_COPIED("FILE.COPIED"),
  FILE_MOVED("FILE.MOVED"),
  FILE_LOCKED("FILE.LOCKED"),
  FILE_UNLOCKED("FILE.UNLOCKED"),
  FILE_RENAMED("FILE.RENAMED"),
  COMMENT_CREATED("COMMENT.CREATED"),
  COMMENT_UPDATED("COMMENT.UPDATED"),
  COMMENT_DELETED("COMMENT.DELETED"),
  TASK_ASSIGNMENT_CREATED("TASK_ASSIGNMENT.CREATED"),
  TASK_ASSIGNMENT_UPDATED("TASK_ASSIGNMENT.UPDATED"),
  METADATA_INSTANCE_CREATED("METADATA_INSTANCE.CREATED"),
  METADATA_INSTANCE_UPDATED("METADATA_INSTANCE.UPDATED"),
  METADATA_INSTANCE_DELETED("METADATA_INSTANCE.DELETED"),
  FOLDER_CREATED("FOLDER.CREATED"),
  FOLDER_RENAMED("FOLDER.RENAMED"),
  FOLDER_DOWNLOADED("FOLDER.DOWNLOADED"),
  FOLDER_RESTORED("FOLDER.RESTORED"),
  FOLDER_DELETED("FOLDER.DELETED"),
  FOLDER_COPIED("FOLDER.COPIED"),
  FOLDER_MOVED("FOLDER.MOVED"),
  FOLDER_TRASHED("FOLDER.TRASHED"),
  WEBHOOK_DELETED("WEBHOOK.DELETED"),
  COLLABORATION_CREATED("COLLABORATION.CREATED"),
  COLLABORATION_ACCEPTED("COLLABORATION.ACCEPTED"),
  COLLABORATION_REJECTED("COLLABORATION.REJECTED"),
  COLLABORATION_REMOVED("COLLABORATION.REMOVED"),
  COLLABORATION_UPDATED("COLLABORATION.UPDATED"),
  SHARED_LINK_DELETED("SHARED_LINK.DELETED"),
  SHARED_LINK_CREATED("SHARED_LINK.CREATED"),
  SHARED_LINK_UPDATED("SHARED_LINK.UPDATED"),
  SIGN_REQUEST_COMPLETED("SIGN_REQUEST.COMPLETED"),
  SIGN_REQUEST_DECLINED("SIGN_REQUEST.DECLINED"),
  SIGN_REQUEST_EXPIRED("SIGN_REQUEST.EXPIRED"),
  SIGN_REQUEST_SIGNER_EMAIL_BOUNCED("SIGN_REQUEST.SIGNER_EMAIL_BOUNCED");

  private final String value;

  WebhookTriggersField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class WebhookTriggersFieldDeserializer
      extends JsonDeserializer<EnumWrapper<WebhookTriggersField>> {

    public WebhookTriggersFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<WebhookTriggersField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(WebhookTriggersField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<WebhookTriggersField>(value));
    }
  }

  public static class WebhookTriggersFieldSerializer
      extends JsonSerializer<EnumWrapper<WebhookTriggersField>> {

    public WebhookTriggersFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<WebhookTriggersField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
