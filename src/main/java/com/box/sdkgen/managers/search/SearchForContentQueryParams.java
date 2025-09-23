package com.box.sdkgen.managers.search;

import com.box.sdkgen.schemas.metadatafilter.MetadataFilter;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchForContentQueryParams {

  public String query;

  public EnumWrapper<SearchForContentQueryParamsScopeField> scope;

  public List<String> fileExtensions;

  public List<String> createdAtRange;

  public List<String> updatedAtRange;

  public List<Long> sizeRange;

  public List<String> ownerUserIds;

  public List<String> recentUpdaterUserIds;

  public List<String> ancestorFolderIds;

  public List<EnumWrapper<SearchForContentQueryParamsContentTypesField>> contentTypes;

  public EnumWrapper<SearchForContentQueryParamsTypeField> type;

  public EnumWrapper<SearchForContentQueryParamsTrashContentField> trashContent;

  public List<MetadataFilter> mdfilters;

  public EnumWrapper<SearchForContentQueryParamsSortField> sort;

  public EnumWrapper<SearchForContentQueryParamsDirectionField> direction;

  public Long limit;

  public Boolean includeRecentSharedLinks;

  public List<String> fields;

  public Long offset;

  public List<String> deletedUserIds;

  public List<String> deletedAtRange;

  public SearchForContentQueryParams() {}

  protected SearchForContentQueryParams(Builder builder) {
    this.query = builder.query;
    this.scope = builder.scope;
    this.fileExtensions = builder.fileExtensions;
    this.createdAtRange = builder.createdAtRange;
    this.updatedAtRange = builder.updatedAtRange;
    this.sizeRange = builder.sizeRange;
    this.ownerUserIds = builder.ownerUserIds;
    this.recentUpdaterUserIds = builder.recentUpdaterUserIds;
    this.ancestorFolderIds = builder.ancestorFolderIds;
    this.contentTypes = builder.contentTypes;
    this.type = builder.type;
    this.trashContent = builder.trashContent;
    this.mdfilters = builder.mdfilters;
    this.sort = builder.sort;
    this.direction = builder.direction;
    this.limit = builder.limit;
    this.includeRecentSharedLinks = builder.includeRecentSharedLinks;
    this.fields = builder.fields;
    this.offset = builder.offset;
    this.deletedUserIds = builder.deletedUserIds;
    this.deletedAtRange = builder.deletedAtRange;
  }

  public String getQuery() {
    return query;
  }

  public EnumWrapper<SearchForContentQueryParamsScopeField> getScope() {
    return scope;
  }

  public List<String> getFileExtensions() {
    return fileExtensions;
  }

  public List<String> getCreatedAtRange() {
    return createdAtRange;
  }

  public List<String> getUpdatedAtRange() {
    return updatedAtRange;
  }

  public List<Long> getSizeRange() {
    return sizeRange;
  }

  public List<String> getOwnerUserIds() {
    return ownerUserIds;
  }

  public List<String> getRecentUpdaterUserIds() {
    return recentUpdaterUserIds;
  }

  public List<String> getAncestorFolderIds() {
    return ancestorFolderIds;
  }

  public List<EnumWrapper<SearchForContentQueryParamsContentTypesField>> getContentTypes() {
    return contentTypes;
  }

  public EnumWrapper<SearchForContentQueryParamsTypeField> getType() {
    return type;
  }

  public EnumWrapper<SearchForContentQueryParamsTrashContentField> getTrashContent() {
    return trashContent;
  }

  public List<MetadataFilter> getMdfilters() {
    return mdfilters;
  }

  public EnumWrapper<SearchForContentQueryParamsSortField> getSort() {
    return sort;
  }

  public EnumWrapper<SearchForContentQueryParamsDirectionField> getDirection() {
    return direction;
  }

  public Long getLimit() {
    return limit;
  }

  public Boolean getIncludeRecentSharedLinks() {
    return includeRecentSharedLinks;
  }

  public List<String> getFields() {
    return fields;
  }

  public Long getOffset() {
    return offset;
  }

  public List<String> getDeletedUserIds() {
    return deletedUserIds;
  }

  public List<String> getDeletedAtRange() {
    return deletedAtRange;
  }

  public static class Builder {

    protected String query;

    protected EnumWrapper<SearchForContentQueryParamsScopeField> scope;

    protected List<String> fileExtensions;

    protected List<String> createdAtRange;

    protected List<String> updatedAtRange;

    protected List<Long> sizeRange;

    protected List<String> ownerUserIds;

    protected List<String> recentUpdaterUserIds;

    protected List<String> ancestorFolderIds;

    protected List<EnumWrapper<SearchForContentQueryParamsContentTypesField>> contentTypes;

    protected EnumWrapper<SearchForContentQueryParamsTypeField> type;

    protected EnumWrapper<SearchForContentQueryParamsTrashContentField> trashContent;

    protected List<MetadataFilter> mdfilters;

    protected EnumWrapper<SearchForContentQueryParamsSortField> sort;

    protected EnumWrapper<SearchForContentQueryParamsDirectionField> direction;

    protected Long limit;

    protected Boolean includeRecentSharedLinks;

    protected List<String> fields;

    protected Long offset;

    protected List<String> deletedUserIds;

    protected List<String> deletedAtRange;

    public Builder query(String query) {
      this.query = query;
      return this;
    }

    public Builder scope(SearchForContentQueryParamsScopeField scope) {
      this.scope = new EnumWrapper<SearchForContentQueryParamsScopeField>(scope);
      return this;
    }

    public Builder scope(EnumWrapper<SearchForContentQueryParamsScopeField> scope) {
      this.scope = scope;
      return this;
    }

    public Builder fileExtensions(List<String> fileExtensions) {
      this.fileExtensions = fileExtensions;
      return this;
    }

    public Builder createdAtRange(List<String> createdAtRange) {
      this.createdAtRange = createdAtRange;
      return this;
    }

    public Builder updatedAtRange(List<String> updatedAtRange) {
      this.updatedAtRange = updatedAtRange;
      return this;
    }

    public Builder sizeRange(List<Long> sizeRange) {
      this.sizeRange = sizeRange;
      return this;
    }

    public Builder ownerUserIds(List<String> ownerUserIds) {
      this.ownerUserIds = ownerUserIds;
      return this;
    }

    public Builder recentUpdaterUserIds(List<String> recentUpdaterUserIds) {
      this.recentUpdaterUserIds = recentUpdaterUserIds;
      return this;
    }

    public Builder ancestorFolderIds(List<String> ancestorFolderIds) {
      this.ancestorFolderIds = ancestorFolderIds;
      return this;
    }

    public Builder contentTypes(List<? extends Valuable> contentTypes) {
      this.contentTypes =
          EnumWrapper.wrapValuableEnumList(
              contentTypes, SearchForContentQueryParamsContentTypesField.class);
      return this;
    }

    public Builder type(SearchForContentQueryParamsTypeField type) {
      this.type = new EnumWrapper<SearchForContentQueryParamsTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<SearchForContentQueryParamsTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder trashContent(SearchForContentQueryParamsTrashContentField trashContent) {
      this.trashContent =
          new EnumWrapper<SearchForContentQueryParamsTrashContentField>(trashContent);
      return this;
    }

    public Builder trashContent(
        EnumWrapper<SearchForContentQueryParamsTrashContentField> trashContent) {
      this.trashContent = trashContent;
      return this;
    }

    public Builder mdfilters(List<MetadataFilter> mdfilters) {
      this.mdfilters = mdfilters;
      return this;
    }

    public Builder sort(SearchForContentQueryParamsSortField sort) {
      this.sort = new EnumWrapper<SearchForContentQueryParamsSortField>(sort);
      return this;
    }

    public Builder sort(EnumWrapper<SearchForContentQueryParamsSortField> sort) {
      this.sort = sort;
      return this;
    }

    public Builder direction(SearchForContentQueryParamsDirectionField direction) {
      this.direction = new EnumWrapper<SearchForContentQueryParamsDirectionField>(direction);
      return this;
    }

    public Builder direction(EnumWrapper<SearchForContentQueryParamsDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder includeRecentSharedLinks(Boolean includeRecentSharedLinks) {
      this.includeRecentSharedLinks = includeRecentSharedLinks;
      return this;
    }

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public Builder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public Builder deletedUserIds(List<String> deletedUserIds) {
      this.deletedUserIds = deletedUserIds;
      return this;
    }

    public Builder deletedAtRange(List<String> deletedAtRange) {
      this.deletedAtRange = deletedAtRange;
      return this;
    }

    public SearchForContentQueryParams build() {
      return new SearchForContentQueryParams(this);
    }
  }

  public static class ContentTypesDeserializer
      extends JsonDeserializer<List<EnumWrapper<SearchForContentQueryParamsContentTypesField>>> {

    public final JsonDeserializer<EnumWrapper<SearchForContentQueryParamsContentTypesField>>
        elementDeserializer;

    public ContentTypesDeserializer() {
      super();
      this.elementDeserializer =
          new SearchForContentQueryParamsContentTypesField
              .SearchForContentQueryParamsContentTypesFieldDeserializer();
    }

    @Override
    public List<EnumWrapper<SearchForContentQueryParamsContentTypesField>> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = p.getCodec().readTree(p);
      List<EnumWrapper<SearchForContentQueryParamsContentTypesField>> elements = new ArrayList<>();
      for (JsonNode item : node) {
        JsonParser pa = item.traverse(p.getCodec());
        pa.nextToken();
        elements.add(elementDeserializer.deserialize(pa, ctxt));
      }
      return elements;
    }
  }

  public static class ContentTypesSerializer
      extends JsonSerializer<List<EnumWrapper<SearchForContentQueryParamsContentTypesField>>> {

    public final JsonSerializer<EnumWrapper<SearchForContentQueryParamsContentTypesField>>
        elementSerializer;

    public ContentTypesSerializer() {
      super();
      this.elementSerializer =
          new SearchForContentQueryParamsContentTypesField
              .SearchForContentQueryParamsContentTypesFieldSerializer();
    }

    @Override
    public void serialize(
        List<EnumWrapper<SearchForContentQueryParamsContentTypesField>> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeStartArray();
      for (EnumWrapper<SearchForContentQueryParamsContentTypesField> item : value) {
        elementSerializer.serialize(item, gen, serializers);
      }
      gen.writeEndArray();
    }
  }
}
