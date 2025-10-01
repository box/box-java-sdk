package com.box.sdkgen.managers.skills;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateAllSkillCardsOnFileRequestBodyFileVersionField extends SerializableObject {

  /** The value will always be `file_version`. */
  @JsonDeserialize(
      using =
          UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeField
              .UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeField
              .UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeFieldSerializer.class)
  protected EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeField> type;

  /** The ID of the file version. */
  protected String id;

  public UpdateAllSkillCardsOnFileRequestBodyFileVersionField() {
    super();
  }

  protected UpdateAllSkillCardsOnFileRequestBodyFileVersionField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateAllSkillCardsOnFileRequestBodyFileVersionField casted =
        (UpdateAllSkillCardsOnFileRequestBodyFileVersionField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "UpdateAllSkillCardsOnFileRequestBodyFileVersionField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeField> type;

    protected String id;

    public Builder type(UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeField type) {
      this.type = new EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeField>(type);
      return this;
    }

    public Builder type(
        EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public UpdateAllSkillCardsOnFileRequestBodyFileVersionField build() {
      return new UpdateAllSkillCardsOnFileRequestBodyFileVersionField(this);
    }
  }
}
