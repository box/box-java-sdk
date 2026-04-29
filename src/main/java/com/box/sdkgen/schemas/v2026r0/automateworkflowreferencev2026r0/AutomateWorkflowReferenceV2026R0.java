package com.box.sdkgen.schemas.v2026r0.automateworkflowreferencev2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A reference to an Automate workflow. */
@JsonFilter("nullablePropertyFilter")
public class AutomateWorkflowReferenceV2026R0 extends SerializableObject {

  /** The identifier for the Automate workflow instance. */
  protected final String id;

  /** The object type. */
  @JsonDeserialize(
      using =
          AutomateWorkflowReferenceV2026R0TypeField
              .AutomateWorkflowReferenceV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          AutomateWorkflowReferenceV2026R0TypeField
              .AutomateWorkflowReferenceV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<AutomateWorkflowReferenceV2026R0TypeField> type;

  /** The display name for the Automate workflow. */
  protected String name;

  public AutomateWorkflowReferenceV2026R0(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<AutomateWorkflowReferenceV2026R0TypeField>(
            AutomateWorkflowReferenceV2026R0TypeField.WORKFLOW);
  }

  protected AutomateWorkflowReferenceV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<AutomateWorkflowReferenceV2026R0TypeField> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AutomateWorkflowReferenceV2026R0 casted = (AutomateWorkflowReferenceV2026R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name);
  }

  @Override
  public String toString() {
    return "AutomateWorkflowReferenceV2026R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<AutomateWorkflowReferenceV2026R0TypeField> type;

    protected String name;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(AutomateWorkflowReferenceV2026R0TypeField type) {
      this.type = new EnumWrapper<AutomateWorkflowReferenceV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AutomateWorkflowReferenceV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public AutomateWorkflowReferenceV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<AutomateWorkflowReferenceV2026R0TypeField>(
                AutomateWorkflowReferenceV2026R0TypeField.WORKFLOW);
      }
      return new AutomateWorkflowReferenceV2026R0(this);
    }
  }
}
