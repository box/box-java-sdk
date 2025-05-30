package com.box.sdkgen.schemas.skillcardsmetadata;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.keywordskillcardorstatusskillcardortimelineskillcardortranscriptskillcard.KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class SkillCardsMetadata extends SerializableObject {

  @JsonProperty("$canEdit")
  protected Boolean canEdit;

  @JsonProperty("$id")
  protected String id;

  @JsonProperty("$parent")
  protected String parent;

  @JsonProperty("$scope")
  protected String scope;

  @JsonProperty("$template")
  protected String template;

  @JsonProperty("$type")
  protected String type;

  @JsonProperty("$typeVersion")
  protected Long typeVersion;

  @JsonProperty("$version")
  protected Long version;

  protected List<KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard> cards;

  public SkillCardsMetadata() {
    super();
  }

  protected SkillCardsMetadata(SkillCardsMetadataBuilder builder) {
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

  public List<KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard>
      getCards() {
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

  public static class SkillCardsMetadataBuilder {

    protected Boolean canEdit;

    protected String id;

    protected String parent;

    protected String scope;

    protected String template;

    protected String type;

    protected Long typeVersion;

    protected Long version;

    protected List<KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard> cards;

    public SkillCardsMetadataBuilder canEdit(Boolean canEdit) {
      this.canEdit = canEdit;
      return this;
    }

    public SkillCardsMetadataBuilder id(String id) {
      this.id = id;
      return this;
    }

    public SkillCardsMetadataBuilder parent(String parent) {
      this.parent = parent;
      return this;
    }

    public SkillCardsMetadataBuilder scope(String scope) {
      this.scope = scope;
      return this;
    }

    public SkillCardsMetadataBuilder template(String template) {
      this.template = template;
      return this;
    }

    public SkillCardsMetadataBuilder type(String type) {
      this.type = type;
      return this;
    }

    public SkillCardsMetadataBuilder typeVersion(Long typeVersion) {
      this.typeVersion = typeVersion;
      return this;
    }

    public SkillCardsMetadataBuilder version(Long version) {
      this.version = version;
      return this;
    }

    public SkillCardsMetadataBuilder cards(
        List<KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard> cards) {
      this.cards = cards;
      return this;
    }

    public SkillCardsMetadata build() {
      return new SkillCardsMetadata(this);
    }
  }
}
