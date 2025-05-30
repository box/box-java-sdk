package com.box.sdkgen.managers.skills;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class UpdateAllSkillCardsOnFileRequestBodyFileField extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateAllSkillCardsOnFileRequestBodyFileTypeField
              .UpdateAllSkillCardsOnFileRequestBodyFileTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateAllSkillCardsOnFileRequestBodyFileTypeField
              .UpdateAllSkillCardsOnFileRequestBodyFileTypeFieldSerializer.class)
  protected EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyFileTypeField> type;

  protected String id;

  public UpdateAllSkillCardsOnFileRequestBodyFileField() {
    super();
  }

  protected UpdateAllSkillCardsOnFileRequestBodyFileField(
      UpdateAllSkillCardsOnFileRequestBodyFileFieldBuilder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
  }

  public EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyFileTypeField> getType() {
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
    UpdateAllSkillCardsOnFileRequestBodyFileField casted =
        (UpdateAllSkillCardsOnFileRequestBodyFileField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "UpdateAllSkillCardsOnFileRequestBodyFileField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class UpdateAllSkillCardsOnFileRequestBodyFileFieldBuilder {

    protected EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyFileTypeField> type;

    protected String id;

    public UpdateAllSkillCardsOnFileRequestBodyFileFieldBuilder type(
        UpdateAllSkillCardsOnFileRequestBodyFileTypeField type) {
      this.type = new EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyFileTypeField>(type);
      return this;
    }

    public UpdateAllSkillCardsOnFileRequestBodyFileFieldBuilder type(
        EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyFileTypeField> type) {
      this.type = type;
      return this;
    }

    public UpdateAllSkillCardsOnFileRequestBodyFileFieldBuilder id(String id) {
      this.id = id;
      return this;
    }

    public UpdateAllSkillCardsOnFileRequestBodyFileField build() {
      return new UpdateAllSkillCardsOnFileRequestBodyFileField(this);
    }
  }
}
