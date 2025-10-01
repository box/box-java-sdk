package com.box.sdkgen.schemas.skillcardsmetadata;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.skillcard.SkillCard;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** The metadata assigned to a using for Box skills. */
@JsonFilter("nullablePropertyFilter")
public class SkillCardsMetadata extends SerializableObject {

  /** Whether the user can edit this metadata. */
  @JsonProperty("$canEdit")
  protected Boolean canEdit;

  /** A UUID to identify the metadata object. */
  @JsonProperty("$id")
  protected String id;

  /** An ID for the parent folder. */
  @JsonProperty("$parent")
  protected String parent;

  /** An ID for the scope in which this template has been applied. */
  @JsonProperty("$scope")
  protected String scope;

  /** The name of the template. */
  @JsonProperty("$template")
  protected String template;

  /**
   * A unique identifier for the "type" of this instance. This is an internal system property and
   * should not be used by a client application.
   */
  @JsonProperty("$type")
  protected String type;

  /**
   * The last-known version of the template of the object. This is an internal system property and
   * should not be used by a client application.
   */
  @JsonProperty("$typeVersion")
  protected Long typeVersion;

  /**
   * The version of the metadata object. Starts at 0 and increases every time a user-defined
   * property is modified.
   */
  @JsonProperty("$version")
  protected Long version;

  /** A list of Box Skill cards that have been applied to this file. */
  protected List<SkillCard> cards;

  public SkillCardsMetadata() {
    super();
  }

  protected SkillCardsMetadata(Builder builder) {
    super();
    this.canEdit = builder.canEdit;
    this.id = builder.id;
    this.parent = builder.parent;
    this.scope = builder.scope;
    this.template = builder.template;
    this.type = builder.type;
    this.typeVersion = builder.typeVersion;
    this.version = builder.version;
    this.cards = builder.cards;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Boolean getCanEdit() {
    return canEdit;
  }

  public String getId() {
    return id;
  }

  public String getParent() {
    return parent;
  }

  public String getScope() {
    return scope;
  }

  public String getTemplate() {
    return template;
  }

  public String getType() {
    return type;
  }

  public Long getTypeVersion() {
    return typeVersion;
  }

  public Long getVersion() {
    return version;
  }

  public List<SkillCard> getCards() {
    return cards;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SkillCardsMetadata casted = (SkillCardsMetadata) o;
    return Objects.equals(canEdit, casted.canEdit)
        && Objects.equals(id, casted.id)
        && Objects.equals(parent, casted.parent)
        && Objects.equals(scope, casted.scope)
        && Objects.equals(template, casted.template)
        && Objects.equals(type, casted.type)
        && Objects.equals(typeVersion, casted.typeVersion)
        && Objects.equals(version, casted.version)
        && Objects.equals(cards, casted.cards);
  }

  @Override
  public int hashCode() {
    return Objects.hash(canEdit, id, parent, scope, template, type, typeVersion, version, cards);
  }

  @Override
  public String toString() {
    return "SkillCardsMetadata{"
        + "canEdit='"
        + canEdit
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "parent='"
        + parent
        + '\''
        + ", "
        + "scope='"
        + scope
        + '\''
        + ", "
        + "template='"
        + template
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "typeVersion='"
        + typeVersion
        + '\''
        + ", "
        + "version='"
        + version
        + '\''
        + ", "
        + "cards='"
        + cards
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Boolean canEdit;

    protected String id;

    protected String parent;

    protected String scope;

    protected String template;

    protected String type;

    protected Long typeVersion;

    protected Long version;

    protected List<SkillCard> cards;

    public Builder canEdit(Boolean canEdit) {
      this.canEdit = canEdit;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder parent(String parent) {
      this.parent = parent;
      return this;
    }

    public Builder scope(String scope) {
      this.scope = scope;
      return this;
    }

    public Builder template(String template) {
      this.template = template;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder typeVersion(Long typeVersion) {
      this.typeVersion = typeVersion;
      return this;
    }

    public Builder version(Long version) {
      this.version = version;
      return this;
    }

    public Builder cards(List<SkillCard> cards) {
      this.cards = cards;
      return this;
    }

    public SkillCardsMetadata build() {
      return new SkillCardsMetadata(this);
    }
  }
}
