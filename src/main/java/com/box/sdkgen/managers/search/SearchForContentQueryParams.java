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

  /**
   * The string to search for. This query is matched against item names, descriptions, text content
   * of files, and various other fields of the different item types.
   *
   * <p>This parameter supports a variety of operators to further refine the results returns.
   *
   * <p>* `""` - by wrapping a query in double quotes only exact matches are returned by the API.
   * Exact searches do not return search matches based on specific character sequences. Instead,
   * they return matches based on phrases, that is, word sequences. For example: A search for
   * `"Blue-Box"` may return search results including the sequence `"blue.box"`, `"Blue Box"`, and
   * `"Blue-Box"`; any item containing the words `Blue` and `Box` consecutively, in the order
   * specified. * `AND` - returns items that contain both the search terms. For example, a search
   * for `marketing AND BoxWorks` returns items that have both `marketing` and `BoxWorks` within its
   * text in any order. It does not return a result that only has `BoxWorks` in its text. * `OR` -
   * returns items that contain either of the search terms. For example, a search for `marketing OR
   * BoxWorks` returns a result that has either `marketing` or `BoxWorks` within its text. Using
   * this operator is not necessary as we implicitly interpret multi-word queries as `OR` unless
   * another supported boolean term is used. * `NOT` - returns items that do not contain the search
   * term provided. For example, a search for `marketing AND NOT BoxWorks` returns a result that has
   * only `marketing` within its text. Results containing `BoxWorks` are omitted.
   *
   * <p>We do not support lower case (that is, `and`, `or`, and `not`) or mixed case (that is,
   * `And`, `Or`, and `Not`) operators.
   *
   * <p>This field is required unless the `mdfilters` parameter is defined.
   */
  public String query;

  /**
   * Limits the search results to either the files that the user has access to, or to files
   * available to the entire enterprise.
   *
   * <p>The scope defaults to `user_content`, which limits the search results to content that is
   * available to the currently authenticated user.
   *
   * <p>The `enterprise_content` can be requested by an admin through our support channels. Once
   * this scope has been enabled for a user, it will allow that use to query for content across the
   * entire enterprise and not only the content that they have access to.
   */
  public EnumWrapper<SearchForContentQueryParamsScopeField> scope;

  /**
   * Limits the search results to any files that match any of the provided file extensions. This
   * list is a comma-separated list of file extensions without the dots.
   */
  public List<String> fileExtensions;

  /**
   * Limits the search results to any items created within a given date range.
   *
   * <p>Date ranges are defined as comma separated RFC3339 timestamps.
   *
   * <p>If the start date is omitted (`,2014-05-17T13:35:01-07:00`) anything created before the end
   * date will be returned.
   *
   * <p>If the end date is omitted (`2014-05-15T13:35:01-07:00,`) the current date will be used as
   * the end date instead.
   */
  public List<String> createdAtRange;

  /**
   * Limits the search results to any items updated within a given date range.
   *
   * <p>Date ranges are defined as comma separated RFC3339 timestamps.
   *
   * <p>If the start date is omitted (`,2014-05-17T13:35:01-07:00`) anything updated before the end
   * date will be returned.
   *
   * <p>If the end date is omitted (`2014-05-15T13:35:01-07:00,`) the current date will be used as
   * the end date instead.
   */
  public List<String> updatedAtRange;

  /**
   * Limits the search results to any items with a size within a given file size range. This applied
   * to files and folders.
   *
   * <p>Size ranges are defined as comma separated list of a lower and upper byte size limit
   * (inclusive).
   *
   * <p>The upper and lower bound can be omitted to create open ranges.
   */
  public List<Long> sizeRange;

  /**
   * Limits the search results to any items that are owned by the given list of owners, defined as a
   * list of comma separated user IDs.
   *
   * <p>The items still need to be owned or shared with the currently authenticated user for them to
   * show up in the search results. If the user does not have access to any files owned by any of
   * the users an empty result set will be returned.
   *
   * <p>To search across an entire enterprise, we recommend using the `enterprise_content` scope
   * parameter which can be requested with our support team.
   */
  public List<String> ownerUserIds;

  /**
   * Limits the search results to any items that have been updated by the given list of users,
   * defined as a list of comma separated user IDs.
   *
   * <p>The items still need to be owned or shared with the currently authenticated user for them to
   * show up in the search results. If the user does not have access to any files owned by any of
   * the users an empty result set will be returned.
   *
   * <p>This feature only searches back to the last 10 versions of an item.
   */
  public List<String> recentUpdaterUserIds;

  /**
   * Limits the search results to items within the given list of folders, defined as a comma
   * separated lists of folder IDs.
   *
   * <p>Search results will also include items within any subfolders of those ancestor folders.
   *
   * <p>The folders still need to be owned or shared with the currently authenticated user. If the
   * folder is not accessible by this user, or it does not exist, a `HTTP 404` error code will be
   * returned instead.
   *
   * <p>To search across an entire enterprise, we recommend using the `enterprise_content` scope
   * parameter which can be requested with our support team.
   */
  public List<String> ancestorFolderIds;

  /**
   * Limits the search results to any items that match the search query for a specific part of the
   * file, for example the file description.
   *
   * <p>Content types are defined as a comma separated lists of Box recognized content types. The
   * allowed content types are as follows.
   *
   * <p>* `name` - The name of the item, as defined by its `name` field. * `description` - The
   * description of the item, as defined by its `description` field. * `file_content` - The actual
   * content of the file. * `comments` - The content of any of the comments on a file or folder. *
   * `tags` - Any tags that are applied to an item, as defined by its `tags` field.
   */
  public List<EnumWrapper<SearchForContentQueryParamsContentTypesField>> contentTypes;

  /**
   * Limits the search results to any items of this type. This parameter only takes one value. By
   * default the API returns items that match any of these types.
   *
   * <p>* `file` - Limits the search results to files, * `folder` - Limits the search results to
   * folders, * `web_link` - Limits the search results to web links, also known as bookmarks.
   */
  public EnumWrapper<SearchForContentQueryParamsTypeField> type;

  /**
   * Determines if the search should look in the trash for items.
   *
   * <p>By default, this API only returns search results for items not currently in the trash
   * (`non_trashed_only`).
   *
   * <p>* `trashed_only` - Only searches for items currently in the trash * `non_trashed_only` -
   * Only searches for items currently not in the trash * `all_items` - Searches for both trashed
   * and non-trashed items.
   */
  public EnumWrapper<SearchForContentQueryParamsTrashContentField> trashContent;

  /**
   * Limits the search results to any items for which the metadata matches the provided filter. This
   * parameter is a list that specifies exactly **one** metadata template used to filter the search
   * results. The parameter is required unless the `query` parameter is provided.
   */
  public List<MetadataFilter> mdfilters;

  /**
   * Defines the order in which search results are returned. This API defaults to returning items by
   * relevance unless this parameter is explicitly specified.
   *
   * <p>* `relevance` (default) returns the results sorted by relevance to the query search term.
   * The relevance is based on the occurrence of the search term in the items name, description,
   * content, and additional properties. * `modified_at` returns the results ordered in descending
   * order by date at which the item was last modified.
   */
  public EnumWrapper<SearchForContentQueryParamsSortField> sort;

  /**
   * Defines the direction in which search results are ordered. This API defaults to returning items
   * in descending (`DESC`) order unless this parameter is explicitly specified.
   *
   * <p>When results are sorted by `relevance` the ordering is locked to returning items in
   * descending order of relevance, and this parameter is ignored.
   */
  public EnumWrapper<SearchForContentQueryParamsDirectionField> direction;

  /** Defines the maximum number of items to return as part of a page of results. */
  public Long limit;

  /**
   * Defines whether the search results should include any items that the user recently accessed
   * through a shared link.
   *
   * <p>When this parameter has been set to true, the format of the response of this API changes to
   * return a list of [Search Results with Shared Links](r://search_results_with_shared_links).
   */
  public Boolean includeRecentSharedLinks;

  /**
   * A comma-separated list of attributes to include in the response. This can be used to request
   * fields that are not normally returned in a standard response.
   *
   * <p>Be aware that specifying this parameter will have the effect that none of the standard
   * fields are returned in the response unless explicitly specified, instead only fields for the
   * mini representation are returned, additional to the fields requested.
   */
  public List<String> fields;

  /**
   * The offset of the item at which to begin the response.
   *
   * <p>Queries with offset parameter value exceeding 10000 will be rejected with a 400 response.
   */
  public Long offset;

  /**
   * Limits the search results to items that were deleted by the given list of users, defined as a
   * list of comma separated user IDs.
   *
   * <p>The `trash_content` parameter needs to be set to `trashed_only`.
   *
   * <p>If searching in trash is not performed, an empty result set is returned. The items need to
   * be owned or shared with the currently authenticated user for them to show up in the search
   * results.
   *
   * <p>If the user does not have access to any files owned by any of the users, an empty result set
   * is returned.
   *
   * <p>Data available from 2023-02-01 onwards.
   */
  public List<String> deletedUserIds;

  /**
   * Limits the search results to any items deleted within a given date range.
   *
   * <p>Date ranges are defined as comma separated RFC3339 timestamps.
   *
   * <p>If the start date is omitted (`2014-05-17T13:35:01-07:00`), anything deleted before the end
   * date will be returned.
   *
   * <p>If the end date is omitted (`2014-05-15T13:35:01-07:00`), the current date will be used as
   * the end date instead.
   *
   * <p>The `trash_content` parameter needs to be set to `trashed_only`.
   *
   * <p>If searching in trash is not performed, then an empty result is returned.
   *
   * <p>Data available from 2023-02-01 onwards.
   */
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
