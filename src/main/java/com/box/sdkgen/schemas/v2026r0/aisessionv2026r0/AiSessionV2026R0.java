package com.box.sdkgen.schemas.v2026r0.aisessionv2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.v2026r0.userbasev2026r0.UserBaseV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * A standard representation of an AI session. An AI session holds the conversation state for a
 * series of interactions with a Box AI agent. Its `id` is the A2A context identifier for that
 * conversation.
 */
@JsonFilter("nullablePropertyFilter")
public class AiSessionV2026R0 extends SerializableObject {

  /** The value is always `ai_session`. */
  @JsonDeserialize(using = AiSessionV2026R0TypeField.AiSessionV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(using = AiSessionV2026R0TypeField.AiSessionV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<AiSessionV2026R0TypeField> type;

  /**
   * The unique identifier for the AI session. This is also the A2A `contextId` for the
   * conversation. Treat the value as opaque.
   */
  protected final String id;

  /**
   * The display name of the AI session. Sessions created through the A2A API are named
   * automatically. The value is `null` only when no name was recorded.
   */
  @Nullable protected String name;

  /**
   * The origin of the AI session. Currently always `api`. New values may be added, so clients must
   * tolerate values they do not recognize.
   */
  @JsonDeserialize(
      using = AiSessionV2026R0SourceField.AiSessionV2026R0SourceFieldDeserializer.class)
  @JsonSerialize(using = AiSessionV2026R0SourceField.AiSessionV2026R0SourceFieldSerializer.class)
  protected EnumWrapper<AiSessionV2026R0SourceField> source;

  /**
   * The OAuth client ID of the application that created the AI session, as shown in the Developer
   * Console. The value is `null` if the application is not known.
   */
  @JsonProperty("client_id")
  @Nullable
  protected String clientId;

  @JsonProperty("created_by")
  protected final UserBaseV2026R0 createdBy;

  @JsonProperty("updated_by")
  protected final UserBaseV2026R0 updatedBy;

  /** The date and time when the AI session was created. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected final OffsetDateTime createdAt;

  /**
   * The date and time when the AI session was last updated. It advances on each turn of session
   * activity, so it also serves as the most recently used ordering.
   */
  @JsonProperty("updated_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected final OffsetDateTime updatedAt;

  public AiSessionV2026R0(
      @JsonProperty("id") String id,
      @JsonProperty("created_by") UserBaseV2026R0 createdBy,
      @JsonProperty("updated_by") UserBaseV2026R0 updatedBy,
      @JsonProperty("created_at") OffsetDateTime createdAt,
      @JsonProperty("updated_at") OffsetDateTime updatedAt) {
    super();
    this.id = id;
    this.createdBy = createdBy;
    this.updatedBy = updatedBy;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.type = new EnumWrapper<AiSessionV2026R0TypeField>(AiSessionV2026R0TypeField.AI_SESSION);
    this.source = new EnumWrapper<AiSessionV2026R0SourceField>(AiSessionV2026R0SourceField.API);
  }

  protected AiSessionV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    this.name = builder.name;
    this.source = builder.source;
    this.clientId = builder.clientId;
    this.createdBy = builder.createdBy;
    this.updatedBy = builder.updatedBy;
    this.createdAt = builder.createdAt;
    this.updatedAt = builder.updatedAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<AiSessionV2026R0TypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public EnumWrapper<AiSessionV2026R0SourceField> getSource() {
    return source;
  }

  public String getClientId() {
    return clientId;
  }

  public UserBaseV2026R0 getCreatedBy() {
    return createdBy;
  }

  public UserBaseV2026R0 getUpdatedBy() {
    return updatedBy;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiSessionV2026R0 casted = (AiSessionV2026R0) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(id, casted.id)
        && Objects.equals(name, casted.name)
        && Objects.equals(source, casted.source)
        && Objects.equals(clientId, casted.clientId)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(updatedBy, casted.updatedBy)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(updatedAt, casted.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        type, id, name, source, clientId, createdBy, updatedBy, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    return "AiSessionV2026R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "source='"
        + source
        + '\''
        + ", "
        + "clientId='"
        + clientId
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "updatedBy='"
        + updatedBy
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "updatedAt='"
        + updatedAt
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<AiSessionV2026R0TypeField> type;

    protected final String id;

    protected String name;

    protected EnumWrapper<AiSessionV2026R0SourceField> source;

    protected String clientId;

    protected final UserBaseV2026R0 createdBy;

    protected final UserBaseV2026R0 updatedBy;

    protected final OffsetDateTime createdAt;

    protected final OffsetDateTime updatedAt;

    public Builder(
        String id,
        UserBaseV2026R0 createdBy,
        UserBaseV2026R0 updatedBy,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {
      super();
      this.id = id;
      this.createdBy = createdBy;
      this.updatedBy = updatedBy;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
    }

    public Builder type(AiSessionV2026R0TypeField type) {
      this.type = new EnumWrapper<AiSessionV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AiSessionV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      this.markNullableFieldAsSet("name");
      return this;
    }

    public Builder source(AiSessionV2026R0SourceField source) {
      this.source = new EnumWrapper<AiSessionV2026R0SourceField>(source);
      return this;
    }

    public Builder source(EnumWrapper<AiSessionV2026R0SourceField> source) {
      this.source = source;
      return this;
    }

    public Builder clientId(String clientId) {
      this.clientId = clientId;
      this.markNullableFieldAsSet("client_id");
      return this;
    }

    public AiSessionV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<AiSessionV2026R0TypeField>(AiSessionV2026R0TypeField.AI_SESSION);
      }
      if (this.source == null) {
        this.source = new EnumWrapper<AiSessionV2026R0SourceField>(AiSessionV2026R0SourceField.API);
      }
      return new AiSessionV2026R0(this);
    }
  }
}
