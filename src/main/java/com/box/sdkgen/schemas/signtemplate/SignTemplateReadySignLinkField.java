package com.box.sdkgen.schemas.signtemplate;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class SignTemplateReadySignLinkField extends SerializableObject {

  protected String url;

  protected String name;

  protected String instructions;

  @JsonProperty("folder_id")
  protected String folderId;

  @JsonProperty("is_notification_disabled")
  protected Boolean isNotificationDisabled;

  @JsonProperty("is_active")
  protected Boolean isActive;

  public SignTemplateReadySignLinkField() {
    super();
  }

  protected SignTemplateReadySignLinkField(SignTemplateReadySignLinkFieldBuilder builder) {
    super();
    this.url = builder.url;
    this.name = builder.name;
    this.instructions = builder.instructions;
    this.folderId = builder.folderId;
    this.isNotificationDisabled = builder.isNotificationDisabled;
    this.isActive = builder.isActive;
  }

  public String getUrl() {
    return url;
  }

  public String getName() {
    return name;
  }

  public String getInstructions() {
    return instructions;
  }

  public String getFolderId() {
    return folderId;
  }

  public Boolean getIsNotificationDisabled() {
    return isNotificationDisabled;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignTemplateReadySignLinkField casted = (SignTemplateReadySignLinkField) o;
    return Objects.equals(url, casted.url)
        && Objects.equals(name, casted.name)
        && Objects.equals(instructions, casted.instructions)
        && Objects.equals(folderId, casted.folderId)
        && Objects.equals(isNotificationDisabled, casted.isNotificationDisabled)
        && Objects.equals(isActive, casted.isActive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url, name, instructions, folderId, isNotificationDisabled, isActive);
  }

  @Override
  public String toString() {
    return "SignTemplateReadySignLinkField{"
        + "url='"
        + url
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "instructions='"
        + instructions
        + '\''
        + ", "
        + "folderId='"
        + folderId
        + '\''
        + ", "
        + "isNotificationDisabled='"
        + isNotificationDisabled
        + '\''
        + ", "
        + "isActive='"
        + isActive
        + '\''
        + "}";
  }

  public static class SignTemplateReadySignLinkFieldBuilder {

    protected String url;

    protected String name;

    protected String instructions;

    protected String folderId;

    protected Boolean isNotificationDisabled;

    protected Boolean isActive;

    public SignTemplateReadySignLinkFieldBuilder url(String url) {
      this.url = url;
      return this;
    }

    public SignTemplateReadySignLinkFieldBuilder name(String name) {
      this.name = name;
      return this;
    }

    public SignTemplateReadySignLinkFieldBuilder instructions(String instructions) {
      this.instructions = instructions;
      return this;
    }

    public SignTemplateReadySignLinkFieldBuilder folderId(String folderId) {
      this.folderId = folderId;
      return this;
    }

    public SignTemplateReadySignLinkFieldBuilder isNotificationDisabled(
        Boolean isNotificationDisabled) {
      this.isNotificationDisabled = isNotificationDisabled;
      return this;
    }

    public SignTemplateReadySignLinkFieldBuilder isActive(Boolean isActive) {
      this.isActive = isActive;
      return this;
    }

    public SignTemplateReadySignLinkField build() {
      return new SignTemplateReadySignLinkField(this);
    }
  }
}
