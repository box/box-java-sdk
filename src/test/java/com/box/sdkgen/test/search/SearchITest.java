package com.box.sdkgen.test.search;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.dateTimeFromString;
import static com.box.sdkgen.internal.utils.UtilsManager.delayInSeconds;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.filemetadata.CreateFileMetadataByIdScope;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBody;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsField;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsOptionsField;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsTypeField;
import com.box.sdkgen.managers.metadatatemplates.DeleteMetadataTemplateScope;
import com.box.sdkgen.managers.search.SearchForContentQueryParams;
import com.box.sdkgen.managers.search.SearchForContentQueryParamsTrashContentField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesParentField;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.metadatafieldfilterdaterange.MetadataFieldFilterDateRange;
import com.box.sdkgen.schemas.metadatafieldfilterfloatrange.MetadataFieldFilterFloatRange;
import com.box.sdkgen.schemas.metadatafilter.MetadataFilter;
import com.box.sdkgen.schemas.metadatafilter.MetadataFilterScopeField;
import com.box.sdkgen.schemas.metadatafiltervalue.MetadataFilterValue;
import com.box.sdkgen.schemas.metadatafull.MetadataFull;
import com.box.sdkgen.schemas.metadataquery.MetadataQuery;
import com.box.sdkgen.schemas.metadataqueryresults.MetadataQueryResults;
import com.box.sdkgen.schemas.metadatatemplate.MetadataTemplate;
import com.box.sdkgen.schemas.searchresults.SearchResults;
import com.box.sdkgen.schemas.searchresultsresponse.SearchResultsResponse;
import com.box.sdkgen.schemas.searchresultswithsharedlinks.SearchResultsWithSharedLinks;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class SearchITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCreateMetaDataQueryExecuteRead() {
    String templateKey = String.join("", "key", getUuid());
    MetadataTemplate template =
        client
            .getMetadataTemplates()
            .createMetadataTemplate(
                new CreateMetadataTemplateRequestBody.Builder("enterprise", templateKey)
                    .templateKey(templateKey)
                    .fields(
                        Arrays.asList(
                            new CreateMetadataTemplateRequestBodyFieldsField(
                                CreateMetadataTemplateRequestBodyFieldsTypeField.STRING,
                                "name",
                                "name"),
                            new CreateMetadataTemplateRequestBodyFieldsField(
                                CreateMetadataTemplateRequestBodyFieldsTypeField.FLOAT,
                                "age",
                                "age"),
                            new CreateMetadataTemplateRequestBodyFieldsField(
                                CreateMetadataTemplateRequestBodyFieldsTypeField.DATE,
                                "birthDate",
                                "birthDate"),
                            new CreateMetadataTemplateRequestBodyFieldsField.Builder(
                                    CreateMetadataTemplateRequestBodyFieldsTypeField.ENUM,
                                    "countryCode",
                                    "countryCode")
                                .options(
                                    Arrays.asList(
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "US"),
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "CA")))
                                .build(),
                            new CreateMetadataTemplateRequestBodyFieldsField.Builder(
                                    CreateMetadataTemplateRequestBodyFieldsTypeField.MULTISELECT,
                                    "sports",
                                    "sports")
                                .options(
                                    Arrays.asList(
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "basketball"),
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "football"),
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "tennis")))
                                .build()))
                    .build());
    assert template.getTemplateKey().equals(templateKey);
    Files files =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        getUuid(), new UploadFileRequestBodyAttributesParentField("0")),
                    generateByteStream(10)));
    FileFull file = files.getEntries().get(0);
    MetadataFull metadata =
        client
            .getFileMetadata()
            .createFileMetadataById(
                file.getId(),
                CreateFileMetadataByIdScope.ENTERPRISE,
                templateKey,
                mapOf(
                    entryOf("name", "John"),
                    entryOf("age", 23),
                    entryOf("birthDate", "2001-01-03T02:20:50.520Z"),
                    entryOf("countryCode", "US"),
                    entryOf("sports", Arrays.asList("basketball", "tennis"))));
    assert metadata.getTemplate().equals(templateKey);
    assert metadata.getScope().equals(template.getScope());
    delayInSeconds(5);
    String searchFrom = String.join("", template.getScope(), ".", template.getTemplateKey());
    MetadataQueryResults query =
        client
            .getSearch()
            .searchByMetadataQuery(
                new MetadataQuery.Builder(searchFrom, "0")
                    .query(
                        "name = :name AND age < :age AND birthDate >= :birthDate AND countryCode = :countryCode AND sports = :sports")
                    .queryParams(
                        mapOf(
                            entryOf("name", "John"),
                            entryOf("age", 50),
                            entryOf("birthDate", "2001-01-01T02:20:10.120Z"),
                            entryOf("countryCode", "US"),
                            entryOf("sports", Arrays.asList("basketball", "tennis"))))
                    .build());
    assert query.getEntries().size() >= 0;
    client
        .getMetadataTemplates()
        .deleteMetadataTemplate(DeleteMetadataTemplateScope.ENTERPRISE, template.getTemplateKey());
    client.getFiles().deleteFileById(file.getId());
  }

  @Test
  public void testMetadataFilters() {
    String templateKey = String.join("", "key", getUuid());
    MetadataTemplate template =
        client
            .getMetadataTemplates()
            .createMetadataTemplate(
                new CreateMetadataTemplateRequestBody.Builder("enterprise", templateKey)
                    .templateKey(templateKey)
                    .fields(
                        Arrays.asList(
                            new CreateMetadataTemplateRequestBodyFieldsField(
                                CreateMetadataTemplateRequestBodyFieldsTypeField.FLOAT,
                                "floatField",
                                "floatField"),
                            new CreateMetadataTemplateRequestBodyFieldsField(
                                CreateMetadataTemplateRequestBodyFieldsTypeField.STRING,
                                "stringField",
                                "stringField"),
                            new CreateMetadataTemplateRequestBodyFieldsField(
                                CreateMetadataTemplateRequestBodyFieldsTypeField.DATE,
                                "dateField",
                                "dateField"),
                            new CreateMetadataTemplateRequestBodyFieldsField.Builder(
                                    CreateMetadataTemplateRequestBodyFieldsTypeField.ENUM,
                                    "enumField",
                                    "enumField")
                                .options(
                                    Arrays.asList(
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "enumValue1"),
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "enumValue2")))
                                .build(),
                            new CreateMetadataTemplateRequestBodyFieldsField.Builder(
                                    CreateMetadataTemplateRequestBodyFieldsTypeField.MULTISELECT,
                                    "multiSelectField",
                                    "multiSelectField")
                                .options(
                                    Arrays.asList(
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "multiSelectValue1"),
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "multiSelectValue2")))
                                .build()))
                    .build());
    Files files =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        getUuid(), new UploadFileRequestBodyAttributesParentField("0")),
                    generateByteStream(10)));
    FileFull file = files.getEntries().get(0);
    MetadataFull metadata =
        client
            .getFileMetadata()
            .createFileMetadataById(
                file.getId(),
                CreateFileMetadataByIdScope.ENTERPRISE,
                templateKey,
                mapOf(
                    entryOf("floatField", 10),
                    entryOf("stringField", "stringValue"),
                    entryOf("dateField", "2035-01-02T00:00:00Z"),
                    entryOf("enumField", "enumValue2"),
                    entryOf(
                        "multiSelectField",
                        Arrays.asList("multiSelectValue1", "multiSelectValue2"))));
    Map<String, MetadataFilterValue> searchFilters =
        mapOf(
            entryOf("stringField", new MetadataFilterValue("stringValue")),
            entryOf(
                "dateField",
                new MetadataFilterValue(
                    new MetadataFieldFilterDateRange.Builder()
                        .lt(dateTimeFromString("2035-01-01T00:00:00Z"))
                        .gt(dateTimeFromString("2035-01-03T00:00:00Z"))
                        .build())),
            entryOf(
                "floatField",
                new MetadataFilterValue(
                    new MetadataFieldFilterFloatRange.Builder().lt(9.5).gt(10.5).build())),
            entryOf("enumField", new MetadataFilterValue("enumValue2")),
            entryOf(
                "multiSelectField",
                new MetadataFilterValue(Arrays.asList("multiSelectValue1", "multiSelectValue2"))));
    SearchResultsResponse query =
        client
            .getSearch()
            .searchForContent(
                new SearchForContentQueryParams.Builder()
                    .ancestorFolderIds(Arrays.asList("0"))
                    .mdfilters(
                        Arrays.asList(
                            new MetadataFilter.Builder()
                                .scope(MetadataFilterScopeField.ENTERPRISE)
                                .templateKey(templateKey)
                                .filters(searchFilters)
                                .build()))
                    .build());
    SearchResults queryResults = query.getSearchResults();
    assert queryResults.getEntries().size() >= 0;
    client
        .getMetadataTemplates()
        .deleteMetadataTemplate(DeleteMetadataTemplateScope.ENTERPRISE, template.getTemplateKey());
    client.getFiles().deleteFileById(file.getId());
  }

  @Test
  public void testGetSearch() {
    String keyword = "test";
    SearchResultsResponse search =
        client
            .getSearch()
            .searchForContent(
                new SearchForContentQueryParams.Builder()
                    .query(keyword)
                    .ancestorFolderIds(Arrays.asList("0"))
                    .trashContent(SearchForContentQueryParamsTrashContentField.NON_TRASHED_ONLY)
                    .build());
    assert convertToString(search.getType()).equals("search_results_items");
    SearchResults searchResults = search.getSearchResults();
    assert searchResults.getEntries().size() >= 0;
    SearchResultsResponse searchWithSharedLink =
        client
            .getSearch()
            .searchForContent(
                new SearchForContentQueryParams.Builder()
                    .query(keyword)
                    .ancestorFolderIds(Arrays.asList("0"))
                    .trashContent(SearchForContentQueryParamsTrashContentField.NON_TRASHED_ONLY)
                    .includeRecentSharedLinks(true)
                    .build());
    assert convertToString(searchWithSharedLink.getType())
        .equals("search_results_with_shared_links");
    SearchResultsWithSharedLinks searchResultsWithSharedLink =
        searchWithSharedLink.getSearchResultsWithSharedLinks();
    assert searchResultsWithSharedLink.getEntries().size() >= 0;
  }
}
