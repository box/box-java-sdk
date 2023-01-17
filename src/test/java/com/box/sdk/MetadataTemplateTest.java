package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * {@link MetadataTemplate} related unit tests.
 */
public class MetadataTemplateTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("https://localhost:%d", wireMockRule.httpsPort()));
    }

    @Test
    public void testGetAllEnterpriseMetadataTemplatesSucceeds() {
        final String firstEntryID = "12345";
        final String firstTemplateKey = "Test Template";
        final String secondEntryID = "23131";
        final String secondTemplateKey = "Test Template 2";
        final String metadataTemplateURL = "/2.0/metadata_templates/enterprise";

        String result = TestUtils.getFixture("BoxMetadataTemplate/GetAllEnterpriseTemplates200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataTemplateURL))
            .withQueryParam("limit", WireMock.containing("100"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        Iterator<MetadataTemplate> templates = MetadataTemplate.getEnterpriseMetadataTemplates(this.api).iterator();
        MetadataTemplate template = templates.next();

        assertEquals(firstEntryID, template.getID());
        assertEquals(firstTemplateKey, template.getTemplateKey());

        MetadataTemplate secondTemplate = templates.next();

        assertEquals(secondEntryID, secondTemplate.getID());
        assertEquals(secondTemplateKey, secondTemplate.getTemplateKey());
    }

    @Test
    public void testGetOptionsReturnsListOfStrings() {
        final String templateID = "f7a9891f";
        final String metadataTemplateURL = "/2.0/metadata_templates/" + templateID;
        final ArrayList<String> list = new ArrayList<>();
        list.add("Beauty");
        list.add("Shoes");
        String result = TestUtils.getFixture("BoxMetadataTemplate/GetMetadataTemplateOptionInfo200");
        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataTemplateURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        MetadataTemplate template = MetadataTemplate.getMetadataTemplateByID(this.api, templateID);
        List<MetadataTemplate.Field> fields = template.getFields();
        for (MetadataTemplate.Field field : fields) {
            if (field.getKey().equals("department")) {
                assertEquals(list, field.getOptions());
            }
        }
    }

    @Test
    public void testGetOptionsReturnsListOfOptionsObject() {
        final String templateID = "f7a9891f";
        final String metadataTemplateURL = "/2.0/metadata_templates/" + templateID;
        String result = TestUtils.getFixture("BoxMetadataTemplate/GetMetadataTemplateOptionInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataTemplateURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        MetadataTemplate template = MetadataTemplate.getMetadataTemplateByID(this.api, templateID);
        List<MetadataTemplate.Field> fields = template.getFields();

        for (MetadataTemplate.Field field : fields) {
            if (field.getKey().equals("department")) {
                List<MetadataTemplate.Option> options = field.getOptionsObjects();
                MetadataTemplate.Option firstOption = options.get(0);
                MetadataTemplate.Option secondOption = options.get(1);
                assertEquals("Beauty", firstOption.getKey());
                assertEquals("f7a9895f", firstOption.getID());
                assertEquals("Shoes", secondOption.getKey());
                assertEquals("f7a9896f", secondOption.getID());
            }
        }
    }

    @Test
    public void testSetOptionReturnsCorrectly() {
        final String metadataTemplateURL = "/2.0/metadata_templates/schema";
        String result = TestUtils.getFixture("BoxMetadataTemplate/CreateMetadataTemplate200");

        JsonObject keyObject = new JsonObject();
        keyObject.add("key", "FY16");

        JsonObject secondKeyObject = new JsonObject();
        secondKeyObject.add("key", "FY17");

        JsonArray optionsArray = new JsonArray();
        optionsArray.add(keyObject);
        optionsArray.add(secondKeyObject);

        JsonObject enumObject = new JsonObject();
        enumObject.add("type", "enum")
            .add("key", "fy")
            .add("displayName", "FY")
            .add("options", optionsArray);

        JsonArray fieldsArray = new JsonArray();
        fieldsArray.add(enumObject);

        JsonObject templateBody = new JsonObject();
        templateBody.add("scope", "enterprise")
            .add("displayName", "Document Flow 03")
            .add("hidden", false)
            .add("templateKey", "documentFlow03")
            .add("fields", fieldsArray);

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataTemplateURL))
            .withRequestBody(WireMock.equalToJson(templateBody.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        MetadataTemplate.Field fyField = new MetadataTemplate.Field();
        fyField.setType("enum");
        fyField.setKey("fy");
        fyField.setDisplayName("FY");

        List<String> options = new ArrayList<>();
        options.add("FY16");
        options.add("FY17");
        fyField.setOptions(options);

        List<MetadataTemplate.Field> fields = new ArrayList<>();
        fields.add(fyField);

        MetadataTemplate template = MetadataTemplate.createMetadataTemplate(this.api, "enterprise",
            "documentFlow03", "Document Flow 03", false, fields);

        assertEquals("FY16", template.getFields().get(0).getOptions().get(0));
        assertEquals("FY17", template.getFields().get(0).getOptions().get(1));
    }

    @Test
    public void testUpdateMetadataReturnsCorrectly() {
        final String metadataTemplateURL = "/2.0/metadata_templates/enterprise/documentFlow03/schema";
        String result = TestUtils.getFixture("BoxMetadataTemplate/UpdateMetadataTemplate200");

        JsonObject editCopyDataObject = new JsonObject();
        editCopyDataObject.add("copyInstanceOnItemCopy", true);

        JsonObject editCopyOperation = new JsonObject();
        editCopyOperation.add("op", "editTemplate");
        editCopyOperation.add("data", editCopyDataObject);

        JsonArray body = new JsonArray();
        body.add(editCopyOperation);

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataTemplateURL))
            .withRequestBody(WireMock.equalToJson(body.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        List<MetadataTemplate.FieldOperation> fieldOperations = new ArrayList<>();

        MetadataTemplate.FieldOperation editTemplate = new MetadataTemplate.FieldOperation();
        editTemplate.setOp(MetadataTemplate.Operation.editTemplate);
        MetadataTemplate.Field copyInstanceField = new MetadataTemplate.Field();
        copyInstanceField.setCopyInstanceOnItemCopy(Boolean.TRUE);
        editTemplate.setData(copyInstanceField);

        fieldOperations.add(editTemplate);

        MetadataTemplate template = MetadataTemplate.updateMetadataTemplate(this.api, "enterprise",
            "documentFlow03", fieldOperations);
        assertEquals("Copy instance on item copy not set", true, template.getCopyInstanceOnItemCopy());
    }

    @Test
    public void testExecuteMetadataQueryWithFields() {
        final String metadataQueryURL = "/2.0/metadata_queries/execute_read";

        final String from = "enterprise_67890.catalogImages";
        final String query = "photographer = :arg";
        final String ancestorFolderId = "0";
        final String field1 = "sha1";
        final String field2 = "name";
        final String field3 = "id";
        final String field4 = "metadata.enterprise_240748.catalogImages.photographer";
        final int limit = 2;

        JsonArray fields = new JsonArray();
        fields.add(field1);
        fields.add(field2);
        fields.add(field3);
        fields.add(field4);
        JsonObject body = new JsonObject();
        body.add("from", from);
        body.add("query", query);
        body.add("query_params", new JsonObject().add("arg", "Bob Dylan"));
        body.add("ancestor_folder_id", ancestorFolderId);
        body.add("limit", limit);
        body.add("fields", fields);

        // First request will return a page of results with two items
        String result = TestUtils.getFixture("BoxMetadataTemplate/MetadataQueryResponseForFields200");
        wireMockRule.stubFor(post(urlPathEqualTo(metadataQueryURL))
            .withRequestBody(equalToJson(body.toString()))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        // Make the first request and get the result
        MetadataQuery queryBody = new MetadataQuery(from, limit)
            .setQuery(query)
            .addParameter("arg", "Bob Dylan")
            .setAncestorFolderId(ancestorFolderId)
            .setFields(field1, field2, field3, field4);
        BoxResourceIterable<BoxItem.Info> results = MetadataTemplate.executeMetadataQuery(this.api, queryBody);

        // First item on the first page of results
        BoxFile.Info fileBoxItem = (BoxFile.Info) results.iterator().next();
        assertEquals("file", fileBoxItem.getType());
        assertEquals("1244738582", fileBoxItem.getID());
        assertEquals("Very Important.docx", fileBoxItem.getName());
        Metadata fileMetadata = fileBoxItem.getMetadata("catalogImages", "enterprise_67890");
        assertEquals("catalogImages", fileMetadata.getTemplateName());
        assertEquals("enterprise_67890", fileMetadata.getScope());
        assertEquals("Bob Dylan", fileMetadata.getString("/photographer"));

        // Second item on the first page of results
        BoxFolder.Info folderBoxItem = (BoxFolder.Info) results.iterator().next();
        assertEquals("folder", folderBoxItem.getType());
        assertEquals("124242482", folderBoxItem.getID());
        assertEquals("Also Important.docx", folderBoxItem.getName());
        Metadata folderMetadata = folderBoxItem.getMetadata("catalogImages", "enterprise_67890");
        assertEquals("catalogImages", folderMetadata.getTemplateName());
        assertEquals("enterprise_67890", folderMetadata.getScope());
        assertEquals("Bob Dylan", folderMetadata.getString("/photographer"));
    }

    @Test
    public void canSetOptionsAsNullOnAField() {
        MetadataTemplate.Field field = new MetadataTemplate.Field();

        field.setOptions(null);

        assertThat(field.getOptions(), nullValue());
    }
}
