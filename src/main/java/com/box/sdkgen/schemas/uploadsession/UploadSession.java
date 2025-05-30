package com.box.sdkgen.schemas.uploadsession;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class UploadSession extends SerializableObject {

  protected String id;

  @JsonDeserialize(using = UploadSessionTypeField.UploadSessionTypeFieldDeserializer.class)
  @JsonSerialize(using = UploadSessionTypeField.UploadSessionTypeFieldSerializer.class)
  protected EnumWrapper<UploadSessionTypeField> type;

  @JsonProperty("session_expires_at")
  protected String sessionExpiresAt;

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

  protected UploadSession(UploadSessionBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.sessionExpiresAt = builder.sessionExpiresAt;
    this.partSize = builder.partSize;
    this.totalParts = builder.totalParts;
    this.numPartsProcessed = builder.numPartsProcessed;
    this.sessionEndpoints = builder.sessionEndpoints;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<UploadSessionTypeField> getType() {
    return type;
  }

  public String getSessionExpiresAt() {
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

  public static class UploadSessionBuilder {

    protected String id;

    protected EnumWrapper<UploadSessionTypeField> type;

    protected String sessionExpiresAt;

    protected Long partSize;

    protected Integer totalParts;

    protected Integer numPartsProcessed;

    protected UploadSessionSessionEndpointsField sessionEndpoints;

    public UploadSessionBuilder id(String id) {
      this.id = id;
      return this;
    }

    public UploadSessionBuilder type(UploadSessionTypeField type) {
      this.type = new EnumWrapper<UploadSessionTypeField>(type);
      return this;
    }

    public UploadSessionBuilder type(EnumWrapper<UploadSessionTypeField> type) {
      this.type = type;
      return this;
    }

    public UploadSessionBuilder sessionExpiresAt(String sessionExpiresAt) {
      this.sessionExpiresAt = sessionExpiresAt;
      return this;
    }

    public UploadSessionBuilder partSize(Long partSize) {
      this.partSize = partSize;
      return this;
    }

    public UploadSessionBuilder totalParts(Integer totalParts) {
      this.totalParts = totalParts;
      return this;
    }

    public UploadSessionBuilder numPartsProcessed(Integer numPartsProcessed) {
      this.numPartsProcessed = numPartsProcessed;
      return this;
    }

    public UploadSessionBuilder sessionEndpoints(
        UploadSessionSessionEndpointsField sessionEndpoints) {
      this.sessionEndpoints = sessionEndpoints;
      return this;
    }

    public UploadSession build() {
      return new UploadSession(this);
    }
  }
}
