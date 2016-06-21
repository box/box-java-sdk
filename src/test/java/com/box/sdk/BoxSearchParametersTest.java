package com.box.sdk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxSearchParametersTest {
    @Test(expected = BoxAPIException.class)
    @Category(UnitTest.class)
    public void searchWithoutQueryAndMetadataThrowsError() {
        BoxSearchParameters searchParams = new BoxSearchParameters();
        searchParams.getQueryParameters();
    }

    @Test
    @Category(UnitTest.class)
    public void shouldCorrectlySetAndGetQueryParam() {
        final String query = "A Query";
        BoxSearchParameters searchParams = new BoxSearchParameters();
        searchParams.setQuery(query);
        QueryStringBuilder queryParams = searchParams.getQueryParameters();

        Assert.assertEquals(queryParams.toString(), "?query=A+Query");
    }

    @Test
    @Category(UnitTest.class)
    public void shouldCorrectlySetAndGetFieldParam() {
        final List<String> fields = new ArrayList<String>();
        fields.add("field1");
        fields.add("field2");
        BoxSearchParameters searchParams = new BoxSearchParameters();
        searchParams.setFields(fields);
        searchParams.setQuery("query");
        QueryStringBuilder queryParams = searchParams.getQueryParameters();

        Assert.assertEquals(queryParams.toString(), "?query=query&fields=field1%2cfield2");
    }

    @Test
    @Category(UnitTest.class)
    public void shouldCorrectlySetAndGetFileExtensionsParam() {
        final List<String> fields = new ArrayList<String>();
        fields.add("pdf");
        fields.add("doc");
        BoxSearchParameters searchParams = new BoxSearchParameters();
        searchParams.setFileExtensions(fields);
        searchParams.setQuery("query");
        QueryStringBuilder queryParams = searchParams.getQueryParameters();

        Assert.assertEquals(queryParams.toString(), "?query=query&file_extensions=pdf%2cdoc");
    }

    @Test
    @Category(UnitTest.class)
    public void shouldCorrectlySetAndGetOwnerUserIdsParam() {
        final List<String> owners = new ArrayList<String>();
        owners.add("123");
        owners.add("456");
        BoxSearchParameters searchParams = new BoxSearchParameters();
        searchParams.setOwnerUserIds(owners);
        searchParams.setQuery("query");
        QueryStringBuilder queryParams = searchParams.getQueryParameters();

        Assert.assertEquals(queryParams.toString(), "?query=query&owner_user_ids=123%2c456");
    }

    @Test
    @Category(UnitTest.class)
    public void shouldCorrectlySetAndGetAncestorFolderIdsParam() {
        final List<String> folderIds = new ArrayList<String>();
        folderIds.add("123");
        folderIds.add("456");
        BoxSearchParameters searchParams = new BoxSearchParameters();
        searchParams.setAncestorFolderIds(folderIds);
        searchParams.setQuery("query");
        QueryStringBuilder queryParams = searchParams.getQueryParameters();

        Assert.assertEquals(queryParams.toString(), "?query=query&ancestor_folder_ids=123%2c456");
    }

    @Test
    @Category(UnitTest.class)
    public void shouldCorrectlySetAndGetContentTypesParam() {
        final List<String> contentTypes = new ArrayList<String>();
        contentTypes.add("description");
        contentTypes.add("file_content");
        BoxSearchParameters searchParams = new BoxSearchParameters();
        searchParams.setContentTypes(contentTypes);
        searchParams.setQuery("query");
        QueryStringBuilder queryParams = searchParams.getQueryParameters();

        Assert.assertEquals(queryParams.toString(), "?query=query&content_types=description%2cfile_content");
    }

    @Test
    @Category(UnitTest.class)
    public void shouldCorrectlySetAndGetScopeParam() {
        BoxSearchParameters searchParams = new BoxSearchParameters();
        searchParams.setScope("enterprise");
        searchParams.setQuery("query");
        QueryStringBuilder queryParams = searchParams.getQueryParameters();

        Assert.assertEquals(queryParams.toString(), "?query=query&scope=enterprise");
    }

    @Test
    @Category(UnitTest.class)
    public void shouldCorrectlySetAndGetTypeParam() {
        BoxSearchParameters searchParams = new BoxSearchParameters();
        searchParams.setType("file");
        searchParams.setQuery("query");
        QueryStringBuilder queryParams = searchParams.getQueryParameters();

        Assert.assertEquals(queryParams.toString(), "?query=query&type=file");
    }

    @Test
    @Category(UnitTest.class)
    public void shouldCorrectlySetAndGetTrashContentParam() {
        BoxSearchParameters searchParams = new BoxSearchParameters();
        searchParams.setTrashContent("trashed_only");
        searchParams.setQuery("query");
        QueryStringBuilder queryParams = searchParams.getQueryParameters();

        Assert.assertEquals(queryParams.toString(), "?query=query&trash_content=trashed_only");
    }

    @Test
    @Category(UnitTest.class)
    public void shouldCorrectlySetAndGetCreatedRangeParam() {
        BoxSearchParameters searchParams = new BoxSearchParameters();
        SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy hh:mm:ss");
        String createdFromDateString = "01-31-2016 00:00:00";
        String createdToDateString = "04-01-2016 00:00:00";

        try {
            Date createdFromDate = sdf.parse(createdFromDateString);
            Date createdToDate = sdf.parse(createdToDateString);
            DateRange createdRange = new DateRange(createdFromDate, createdToDate);
            searchParams.setCreatedRange(createdRange);
        } catch (ParseException e) { /* no op */ }

        searchParams.setQuery("query");
        QueryStringBuilder queryParams = searchParams.getQueryParameters();

        Assert.assertEquals(
            queryParams.toString(),
            "?query=query&created_at_range=2016-01-31T00%3a00%3a00-0800%2c2016-04-01T00%3a00%3a00-0700"
        );
    }

    @Test
    @Category(UnitTest.class)
    public void shouldCorrectlySetAndGetUpdatedRangeParam() {
        BoxSearchParameters searchParams = new BoxSearchParameters();
        SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy hh:mm:ss");
        String updatedFromDateString = "01-31-2016 00:00:00";
        String updatedToDateString = "04-01-2016 00:00:00";

        try {
            Date updatedFromDate = sdf.parse(updatedFromDateString);
            Date updatedToDate = sdf.parse(updatedToDateString);
            DateRange updatedRange = new DateRange(updatedFromDate, updatedToDate);
            searchParams.setUpdatedRange(updatedRange);
        } catch (ParseException e) { /* no op */ }

        searchParams.setQuery("query");
        QueryStringBuilder queryParams = searchParams.getQueryParameters();

        Assert.assertEquals(
            queryParams.toString(),
            "?query=query&updated_at_range=2016-01-31T00%3a00%3a00-0800%2c2016-04-01T00%3a00%3a00-0700"
        );
    }

    @Test
    @Category(UnitTest.class)
    public void shouldCorrectlySetAndGetMetadataFilterParam() {
        BoxSearchParameters searchParams = new BoxSearchParameters();
        BoxMetadataFilter metadataFilter = new BoxMetadataFilter();
        metadataFilter.setScope("enterprise");
        metadataFilter.setTemplateKey("test");

        SizeRange sizeRange = new SizeRange(12, 19);
        metadataFilter.addNumberRangeFilter("testnumber", sizeRange);

        metadataFilter.addFilter("test", "example");

        searchParams.setMetadataFilter(metadataFilter);

        QueryStringBuilder queryParams = searchParams.getQueryParameters();

        Assert.assertEquals(
            queryParams.toString(),
            "?mdfilters=%5b%7b%22templateKey%22%3a%22test%22%2c%22scope%22%3a%22enterprise"
                + "%22%2c%22filters%22%3a%7b%22testnumber%22%3a%7b%22gt%22%3a12%2c%22lt"
                + "%22%3a19%7d%2c%22test%22%3a%22example%22%7d%7d%5d"
        );
    }
}
