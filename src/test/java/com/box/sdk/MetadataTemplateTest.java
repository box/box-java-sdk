package com.box.sdk;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * {@link MetadataTemplate} related unit tests.
 */
public class MetadataTemplateTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private final BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    public void testGetAllEnterpriseMetadataTemplatesSucceeds() throws IOException {
        final String firstEntryID = "12345";
        final String firstTemplateKey = "Test Template";
        final String secondEntryID = "23131";
        final String secondTemplateKey = "Test Template 2";
        final String metadataTemplateURL = "/metadata_templates/enterprise";

        String result = TestConfig.getFixture("BoxMetadataTemplate/GetAllEnterpriseTemplates200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataTemplateURL))
            .withQueryParam("limit", WireMock.containing("100"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
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
    public void testGetOptionsReturnsListOfStrings() throws IOException {
        final String templateID = "f7a9891f";
        final String metadataTemplateURL = "/metadata_templates/" + templateID;
        final ArrayList<String> list = new ArrayList<String>() {
            {
                add("Beauty");
                add("Shoes");
            }
        };
        String result = TestConfig.getFixture("BoxMetadataTemplate/GetMetadataTemplateOptionInfo200");
        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataTemplateURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
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
    public void testGetOptionsReturnsListOfOptionsObject() throws IOException {
        final String templateID = "f7a9891f";
        final String metadataTemplateURL = "/metadata_templates/" + templateID;
        String result = TestConfig.getFixture("BoxMetadataTemplate/GetMetadataTemplateOptionInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataTemplateURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
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
    public void testSetOptionReturnsCorrectly() throws IOException {
        final String metadataTemplateURL = "/metadata_templates/schema";
        String result = TestConfig.getFixture("BoxMetadataTemplate/CreateMetadataTemplate200");

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

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataTemplateURL))
            .withRequestBody(WireMock.equalToJson(templateBody.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
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
    public void testUpdateMetadataReturnsCorrectly() throws IOException {
        final String metadataTemplateURL = "/metadata_templates/enterprise/documentFlow03/schema";
        String result = TestConfig.getFixture("BoxMetadataTemplate/UpdateMetadataTemplate200");

        JsonObject editCopyDataObject = new JsonObject();
        editCopyDataObject.add("copyInstanceOnItemCopy", true);

        JsonObject editCopyOperation = new JsonObject();
        editCopyOperation.add("op", "editTemplate");
        editCopyOperation.add("data", editCopyDataObject);

        JsonArray body = new JsonArray();
        body.add(editCopyOperation);

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataTemplateURL))
            .withRequestBody(WireMock.equalToJson(body.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
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
    public void testDeprecatedExecuteMetadataQuery() throws IOException {
        final String metadataQueryURL = "/metadata_queries/execute_read";

        final String from = "enterprise_67890.relayWorkflowInformation";
        final String query = "templateName >= :arg";
        final JsonObject queryParameters = new JsonObject().add("arg", "Templ Name");
        final String ancestorFolderId = "0";
        final String indexName = null;
        final JsonArray orderBy = null;
        final int limit = 2;
        final String marker = null;

        // First request will return a page of results with two items
        String request1 = TestConfig.getFixture("BoxMetadataTemplate/MetadataQuery1stRequest");
        String result1 = TestConfig.getFixture("BoxMetadataTemplate/MetadataQuery1stResponse200");
        WIRE_MOCK_CLASS_RULE.stubFor(post(urlPathEqualTo(metadataQueryURL))
            .withRequestBody(equalToJson(request1))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result1)));

        // Second request will contain a marker and will return a page of results with remaining one item
        String result2 = TestConfig.getFixture("BoxMetadataTemplate/MetadataQuery2ndResponse200");
        String request2 = TestConfig.getFixture("BoxMetadataTemplate/MetadataQuery2ndRequest");
        WIRE_MOCK_CLASS_RULE.stubFor(post(urlPathEqualTo(metadataQueryURL))
            .withRequestBody(equalToJson(request2))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result2)));

        // Make the first request and get the result
        BoxResourceIterable<BoxMetadataQueryItem> results = MetadataTemplate.executeMetadataQuery(
            this.api,
            from,
            query,
            queryParameters,
            ancestorFolderId,
            indexName,
            orderBy,
            limit,
            marker
        );

        // First item on the first page of results
        BoxMetadataQueryItem currBoxItem = results.iterator().next();
        assertEquals("file", currBoxItem.getItem().getType());
        assertEquals("123450", currBoxItem.getItem().getID());
        assertEquals("1.jpg", currBoxItem.getItem().getName());
        HashMap<String, ArrayList<Metadata>> metadata = currBoxItem.getMetadata();
        assertEquals("relayWorkflowInformation", metadata.get("enterprise_67890").get(0).getTemplateName());
        assertEquals("enterprise_67890", metadata.get("enterprise_67890").get(0).getScope());
        assertEquals("Werk Flow 0", metadata.get("enterprise_67890").get(0).getString("/workflowName"));

        // Second item on the first page of results
        currBoxItem = results.iterator().next();
        assertEquals("file", currBoxItem.getItem().getType());
        assertEquals("123451", currBoxItem.getItem().getID());
        assertEquals("2.jpg", currBoxItem.getItem().getName());
        metadata = currBoxItem.getMetadata();
        assertEquals("relayWorkflowInformation", metadata.get("enterprise_67890").get(0).getTemplateName());
        assertEquals("randomTemplate", metadata.get("enterprise_67890").get(1).getTemplateName());
        assertEquals("someTemplate", metadata.get("enterprise_123456").get(0).getTemplateName());

        // First item on the second page of results (this next call makes the second request to get the second page)
        currBoxItem = results.iterator().next();
        assertEquals("file", currBoxItem.getItem().getType());
        assertEquals("123452", currBoxItem.getItem().getID());
        assertEquals("3.jpg", currBoxItem.getItem().getName());
        metadata = currBoxItem.getMetadata();
        assertEquals("relayWorkflowInformation", metadata.get("enterprise_67890").get(0).getTemplateName());
    }

    @Test
    public void testExecuteMetadataQueryWithFields() throws IOException {
        final String metadataQueryURL = "/metadata_queries/execute_read";

        final String from = "enterprise_67890.catalogImages";
        final String query = "photographer = :arg";
        final JsonObject queryParameters = new JsonObject().add("arg", "Bob Dylan");
        final String ancestorFolderId = "0";
        final String indexName = null;
        final String field1 = "sha1";
        final String field2 = "name";
        final String field3 = "id";
        final String field4 = "metadata.enterprise_240748.catalogImages.photographer";
        final JsonArray orderBy = null;
        final int limit = 2;
        final String marker = null;

        JsonArray fields = new JsonArray();
        fields.add(field1);
        fields.add(field2);
        fields.add(field3);
        fields.add(field4);
        JsonObject body = new JsonObject();
        body.add("from", from);
        body.add("query", query);
        body.add("query_params", queryParameters);
        body.add("ancestor_folder_id", ancestorFolderId);
        body.add("limit", limit);
        body.add("fields", fields);

        // First request will return a page of results with two items
        String result = TestConfig.getFixture("BoxMetadataTemplate/MetadataQueryResponseForFields200");
        WIRE_MOCK_CLASS_RULE.stubFor(post(urlPathEqualTo(metadataQueryURL))
            .withRequestBody(equalToJson(body.toString()))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        // Make the first request and get the result
        BoxResourceIterable<BoxItem.Info> results = MetadataTemplate.executeMetadataQuery(this.api, from, query,
            queryParameters, ancestorFolderId, indexName, orderBy, limit, marker, field1, field2, field3, field4);

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
}
