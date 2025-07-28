package com.box.sdkgen.schemas.uploadsession;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UploadSession extends SerializableObject {

  protected String id;

  @JsonDeserialize(using = UploadSessionTypeField.UploadSessionTypeFieldDeserializer.class)
  @JsonSerialize(using = UploadSessionTypeField.UploadSessionTypeFieldSerializer.class)
  protected EnumWrapper<UploadSessionTypeField> type;

  @JsonProperty("session_expires_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date sessionExpiresAt;

  @JsonProperty("part_size")
  protected Long partSize;

  @JsonProperty("total_parts")
  protected Integer totalParts;

  @JsonProperty("num_parts_processed")
  protected Integer numPartsProcessed;

  @JsonProperty("session_endpoints")
  protected UploadSessionSessionEndpointsField sessionEndpoints;

  public UploadSession() {
    super();
  }

  protected UploadSession(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.sessionExpiresAt = builder.sessionExpiresAt;
    this.partSize = builder.partSize;
    this.totalParts = builder.totalParts;
    this.numPartsProcessed = builder.numPartsProcessed;
    this.sessionEndpoints = builder.sessionEndpoints;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<UploadSessionTypeField> getType() {
    return type;
  }

  public Date getSessionExpiresAt() {
    return sessionExpiresAt;
  }

  public Long getPartSize() {
    return partSize;
  }

  public Integer getTotalParts() {
    return totalParts;
  }

  public Integer getNumPartsProcessed() {
    return numPartsProcessed;
  }

  public UploadSessionSessionEndpointsField getSessionEndpoints() {
    return sessionEndpoints;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadSession casted = (UploadSession) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(sessionExpiresAt, casted.sessionExpiresAt)
        && Objects.equals(partSize, casted.partSize)
        && Objects.equals(totalParts, casted.totalParts)
        && Objects.equals(numPartsProcessed, casted.numPartsProcessed)
        && Objects.equals(sessionEndpoints, casted.sessionEndpoints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, type, sessionExpiresAt, partSize, totalParts, numPartsProcessed, sessionEndpoints);
  }

  @Override
  public String toString() {
    return "UploadSession{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "sessionExpiresAt='"
        + sessionExpiresAt
        + '\''
        + ", "
        + "partSize='"
        + partSize
        + '\''
        + ", "
        + "totalParts='"
        + totalParts
        + '\''
        + ", "
        + "numPartsProcessed='"
        + numPartsProcessed
        + '\''
        + ", "
        + "sessionEndpoints='"
        + sessionEndpoints
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<UploadSessionTypeField> type;

    protected Date sessionExpiresAt;

    protected Long partSize;

    protected Integer totalParts;

    protected Integer numPartsProcessed;

    protected UploadSessionSessionEndpointsField sessionEndpoints;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(UploadSessionTypeField type) {
      this.type = new EnumWrapper<UploadSessionTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<UploadSessionTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder sessionExpiresAt(Date sessionExpiresAt) {
      this.sessionExpiresAt = sessionExpiresAt;
      return this;
    }

    public Builder partSize(Long partSize) {
      this.partSize = partSize;
      return this;
    }

    public Builder totalParts(Integer totalParts) {
      this.totalParts = totalParts;
      return this;
    }

    public Builder numPartsProcessed(Integer numPartsProcessed) {
      this.numPartsProcessed = numPartsProcessed;
      return this;
    }

    public Builder sessionEndpoints(UploadSessionSessionEndpointsField sessionEndpoints) {
      this.sessionEndpoints = sessionEndpoints;
      return this;
    }

    public UploadSession build() {
      return new UploadSession(this);
    }
  }
}
