package com.box.sdkgen.schemas.skillinvocation;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.event.Event;
import com.box.sdkgen.schemas.file.File;
import com.box.sdkgen.schemas.fileorfolder.FileOrFolder;
import com.box.sdkgen.schemas.folder.Folder;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class SkillInvocation extends SerializableObject {

  @JsonDeserialize(using = SkillInvocationTypeField.SkillInvocationTypeFieldDeserializer.class)
  @JsonSerialize(using = SkillInvocationTypeField.SkillInvocationTypeFieldSerializer.class)
  protected EnumWrapper<SkillInvocationTypeField> type;

  protected String id;

  protected SkillInvocationSkillField skill;

  protected SkillInvocationTokenField token;

  protected SkillInvocationStatusField status;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date createdAt;

  protected String trigger;

  protected SkillInvocationEnterpriseField enterprise;

  protected FileOrFolder source;

  protected Event event;

  public SkillInvocation() {
    super();
  }

  protected SkillInvocation(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    this.skill = builder.skill;
    this.token = builder.token;
    this.status = builder.status;
    this.createdAt = builder.createdAt;
    this.trigger = builder.trigger;
    this.enterprise = builder.enterprise;
    this.source = builder.source;
    this.event = builder.event;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SkillInvocationTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public SkillInvocationSkillField getSkill() {
    return skill;
  }

  public SkillInvocationTokenField getToken() {
    return token;
  }

  public SkillInvocationStatusField getStatus() {
    return status;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public String getTrigger() {
    return trigger;
  }

  public SkillInvocationEnterpriseField getEnterprise() {
    return enterprise;
  }

  public FileOrFolder getSource() {
    return source;
  }

  public Event getEvent() {
    return event;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SkillInvocation casted = (SkillInvocation) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(id, casted.id)
        && Objects.equals(skill, casted.skill)
        && Objects.equals(token, casted.token)
        && Objects.equals(status, casted.status)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(trigger, casted.trigger)
        && Objects.equals(enterprise, casted.enterprise)
        && Objects.equals(source, casted.source)
        && Objects.equals(event, casted.event);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        type, id, skill, token, status, createdAt, trigger, enterprise, source, event);
  }

  @Override
  public String toString() {
    return "SkillInvocation{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "skill='"
        + skill
        + '\''
        + ", "
        + "token='"
        + token
        + '\''
        + ", "
        + "status='"
        + status
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
        + "enterprise='"
        + enterprise
        + '\''
        + ", "
        + "source='"
        + source
        + '\''
        + ", "
        + "event='"
        + event
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SkillInvocationTypeField> type;

    protected String id;

    protected SkillInvocationSkillField skill;

    protected SkillInvocationTokenField token;

    protected SkillInvocationStatusField status;

    protected Date createdAt;

    protected String trigger;

    protected SkillInvocationEnterpriseField enterprise;

    protected FileOrFolder source;

    protected Event event;

    public Builder type(SkillInvocationTypeField type) {
      this.type = new EnumWrapper<SkillInvocationTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<SkillInvocationTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder skill(SkillInvocationSkillField skill) {
      this.skill = skill;
      return this;
    }

    public Builder token(SkillInvocationTokenField token) {
      this.token = token;
      return this;
    }

    public Builder status(SkillInvocationStatusField status) {
      this.status = status;
      return this;
    }

    public Builder createdAt(Date createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder trigger(String trigger) {
      this.trigger = trigger;
      return this;
    }

    public Builder enterprise(SkillInvocationEnterpriseField enterprise) {
      this.enterprise = enterprise;
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

    public Builder event(Event event) {
      this.event = event;
      return this;
    }

    public SkillInvocation build() {
      return new SkillInvocation(this);
    }
  }
}
