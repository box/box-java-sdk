package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

/**
 * {@link MetadataTemplate} related unit tests.
 */
public class MetadataTemplateTest {

    /**
     * Wiremock
     */
    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(8080);

    /**
     * Unit test for {@link MetadataTemplate#getMetadataTemplate(BoxAPIConnection, String, String, String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetMetadataTemplateSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals(
                        "https://api.box.com/2.0/metadata_templates/global/properties/schema"
                                + "?fields=displayName%2Chidden",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        MetadataTemplate.getMetadataTemplate(api, "properties", "global", "displayName", "hidden");
    }

    /**
     * Unit test for {@link MetadataTemplate#getMetadataTemplate(BoxAPIConnection)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetMetadataTemplateParseAllFieldsCorrectly() {
        final String templateKey = "productInfo";
        final String scope = "enterprise_12345";
        final String displayName = "Product Info";
        final Boolean isHidden = false;
        final String firstFieldType = "float";
        final String firstFieldKey = "skuNumber";
        final String firstFieldDisplayName = "SKU Number";
        final Boolean firstFieldIsHidden = false;
        final String secondFieldType = "enum";
        final String secondFieldKey = "department";
        final String secondFieldDisplayName = "Department";
        final Boolean secondFieldIsHidden = false;
        final String secondFieldFirstOption = "Beauty";
        final String secondFieldSecondOption = "Accessories";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");
        WireMock.stubFor(WireMock.get(WireMock.urlMatching("/metadata_templates/global/properties/schema"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                                + "    \"templateKey\": \"productInfo\",\n"
                                + "    \"scope\": \"enterprise_12345\",\n"
                                + "    \"displayName\": \"Product Info\",\n"
                                + "    \"hidden\": false,\n"
                                + "    \"fields\": [\n"
                                + "        {\n"
                                + "            \"type\": \"float\",\n"
                                + "            \"key\": \"skuNumber\",\n"
                                + "            \"displayName\": \"SKU Number\",\n"
                                + "            \"hidden\": false\n"
                                + "        },\n"
                                + "        {\n"
                                + "            \"type\": \"enum\",\n"
                                + "            \"key\": \"department\",\n"
                                + "            \"displayName\": \"Department\",\n"
                                + "            \"hidden\": false,\n"
                                + "            \"options\": [\n"
                                + "                {\n"
                                + "                    \"key\": \"Beauty\"\n"
                                + "                },\n"
                                + "                {\n"
                                + "                    \"key\": \"Accessories\"\n"
                                + "                }\n"
                                + "            ]\n"
                                + "        }\n"
                                + "    ]\n"
                                + "}")));

        MetadataTemplate template = MetadataTemplate.getMetadataTemplate(api);
        Assert.assertEquals(templateKey, template.getTemplateKey());
        Assert.assertEquals(scope, template.getScope());
        Assert.assertEquals(displayName, template.getDisplayName());
        Assert.assertEquals(isHidden, template.getIsHidden());
        List<MetadataTemplate.Field> templateFields = template.getFields();
        Assert.assertEquals(firstFieldType, templateFields.get(0).getType());
        Assert.assertEquals(firstFieldKey, templateFields.get(0).getKey());
        Assert.assertEquals(firstFieldDisplayName, templateFields.get(0).getDisplayName());
        Assert.assertEquals(firstFieldIsHidden, templateFields.get(0).getIsHidden());
        Assert.assertEquals(secondFieldType, templateFields.get(1).getType());
        Assert.assertEquals(secondFieldKey, templateFields.get(1).getKey());
        Assert.assertEquals(secondFieldDisplayName, templateFields.get(1).getDisplayName());
        Assert.assertEquals(secondFieldIsHidden, templateFields.get(1).getIsHidden());
        Assert.assertEquals(secondFieldFirstOption, templateFields.get(1).getOptions().get(0));
        Assert.assertEquals(secondFieldSecondOption, templateFields.get(1).getOptions().get(1));

    }

    /**
     * Unit test for {@link MetadataTemplate#getEnterpriseMetadataTemplates(BoxAPIConnection, String...)}.
     */
    @Test(expected = NoSuchElementException.class)
    @Category(UnitTest.class)
    public void testGetEnterpriseMetadataTemplatesSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals(
                        "https://api.box.com/2.0/metadata_templates/enterprise?fields=displayName%2Chidden&limit=100",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\":[]}";
                    }
                };
            }
        });

        Iterator<MetadataTemplate> iterator =
                MetadataTemplate.getEnterpriseMetadataTemplates(api, "displayName", "hidden").iterator();
        iterator.next();
    }

    /**
     * Unit test for {@link MetadataTemplate#getEnterpriseMetadataTemplates(BoxAPIConnection, String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetEnterpriseMetadataTemplatesParseAllFieldsCorrectly() {
        final String firstEntryTemplateKey = "documentFlow";
        final String firstEntryScope = "enterprise_12345";
        final String firstEntryDisplayName = "Document Flow";
        final Boolean firstEntryIsHidden = false;
        final String firstEntryFieldType = "string";
        final String firstEntryFieldKey = "currentDocumentStage";
        final String firstEntryFieldDisplayName = "Current Document Stage";
        final Boolean firstEntryFieldIsHidden = false;
        final String firstEntryFieldDescription = "What stage in the process the document is in";
        final String secondEntryTemplateKey = "productInfo";
        final String secondEntryScope = "enterprise_12345";
        final String secondEntryDisplayName = "Product Info";
        final Boolean secondEntryIsHidden = false;
        final String secondEntryFieldType = "enum";
        final String secondEntryFieldKey = "department";
        final String secondEntryFieldDisplayName = "Department";
        final Boolean secondEntryFieldIsHidden = false;
        final String secondEntryFieldFirstOption = "Beauty";
        final String secondEntryFieldSecondOption = "Shoes";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"limit\": 100,\n"
                + "    \"entries\": [\n"
                + "        {\n"
                + "            \"templateKey\": \"documentFlow\",\n"
                + "            \"scope\": \"enterprise_12345\",\n"
                + "            \"displayName\": \"Document Flow\",\n"
                + "            \"hidden\": false,\n"
                + "            \"fields\": [\n"
                + "                {\n"
                + "                    \"type\": \"string\",\n"
                + "                    \"key\": \"currentDocumentStage\",\n"
                + "                    \"displayName\": \"Current Document Stage\",\n"
                + "                    \"hidden\": false,\n"
                + "                    \"description\": \"What stage in the process the document is in\"\n"
                + "                }\n"
                + "            ]\n"
                + "        },\n"
                + "        {\n"
                + "            \"templateKey\": \"productInfo\",\n"
                + "            \"scope\": \"enterprise_12345\",\n"
                + "            \"displayName\": \"Product Info\",\n"
                + "            \"hidden\": false,\n"
                + "            \"fields\": [\n"
                + "                {\n"
                + "                    \"type\": \"enum\",\n"
                + "                    \"key\": \"department\",\n"
                + "                    \"displayName\": \"Department\",\n"
                + "                    \"hidden\": false,\n"
                + "                    \"options\": [\n"
                + "                        {\n"
                + "                            \"key\": \"Beauty\"\n"
                + "                        },\n"
                + "                        {\n"
                + "                            \"key\": \"Shoes\"\n"
                + "                        }\n"
                + "                    ]\n"
                + "                }\n"
                + "            ]\n"
                + "        }\n"
                + "    ],\n"
                + "    \"next_marker\": null,\n"
                + "    \"prev_marker\": null\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        Iterator<MetadataTemplate> iterator = MetadataTemplate.getEnterpriseMetadataTemplates(api).iterator();
        MetadataTemplate template = iterator.next();
        Assert.assertEquals(firstEntryTemplateKey, template.getTemplateKey());
        Assert.assertEquals(firstEntryScope, template.getScope());
        Assert.assertEquals(firstEntryDisplayName, template.getDisplayName());
        Assert.assertEquals(firstEntryIsHidden, template.getIsHidden());
        Assert.assertEquals(firstEntryFieldType, template.getFields().get(0).getType());
        Assert.assertEquals(firstEntryFieldKey, template.getFields().get(0).getKey());
        Assert.assertEquals(firstEntryFieldDisplayName, template.getFields().get(0).getDisplayName());
        Assert.assertEquals(firstEntryFieldIsHidden, template.getFields().get(0).getIsHidden());
        Assert.assertEquals(firstEntryFieldDescription, template.getFields().get(0).getDescription());
        template = iterator.next();
        Assert.assertEquals(secondEntryTemplateKey, template.getTemplateKey());
        Assert.assertEquals(secondEntryScope, template.getScope());
        Assert.assertEquals(secondEntryDisplayName, template.getDisplayName());
        Assert.assertEquals(secondEntryIsHidden, template.getIsHidden());
        Assert.assertEquals(secondEntryFieldType, template.getFields().get(0).getType());
        Assert.assertEquals(secondEntryFieldKey, template.getFields().get(0).getKey());
        Assert.assertEquals(secondEntryFieldDisplayName, template.getFields().get(0).getDisplayName());
        Assert.assertEquals(secondEntryFieldIsHidden, template.getFields().get(0).getIsHidden());
        Assert.assertEquals(secondEntryFieldFirstOption, template.getFields().get(0).getOptions().get(0));
        Assert.assertEquals(secondEntryFieldSecondOption, template.getFields().get(0).getOptions().get(1));
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    @Category(IntegrationTest.class)
    public void createMetadataTemplateSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        MetadataTemplate.Field ctField = new MetadataTemplate.Field();
        ctField.setType("string");
        ctField.setKey("customerTeam");
        ctField.setDisplayName("Customer Team");

        MetadataTemplate.Field fyField = new MetadataTemplate.Field();
        fyField.setType("enum");
        fyField.setKey("fy");
        fyField.setDisplayName("FY");

        List<String> options = new ArrayList<String>();
        options.add("FY16");
        options.add("FY17");
        fyField.setOptions(options);

        List<MetadataTemplate.Field> fields = new ArrayList<MetadataTemplate.Field>();
        fields.add(ctField);
        fields.add(fyField);

        try {
            MetadataTemplate template = MetadataTemplate.createMetadataTemplate(api, "enterprise",
                    "documentFlow03", "Document Flow 03", false, fields);
        } catch (BoxAPIException apiEx) {
            //Delete MetadataTemplate is yet to be supported. Due to that template might be existing already.
            //This expects the conflict error. To check the MetadataTemplate creation, please replace the id.
            Assert.assertEquals(apiEx.getResponseCode(), 409);
            Assert.assertTrue(apiEx.getResponse().contains("Template key already exists in this scope"));
        }

        MetadataTemplate storedTemplate = MetadataTemplate.getMetadataTemplate(api, "documentFlow03");
        Assert.assertNotNull(storedTemplate);
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateMetadataTemplateSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        List<MetadataTemplate.FieldOperation> fieldOperations = new ArrayList<MetadataTemplate.FieldOperation>();
        MetadataTemplate.FieldOperation editField = new MetadataTemplate.FieldOperation();
        editField.setOp(MetadataTemplate.Operation.editField);
        editField.setFieldKey("customerTeam");

        MetadataTemplate.Field customerTeam = new MetadataTemplate.Field();
        customerTeam.setDisplayName("Customer Team modified");
        editField.setData(customerTeam);
        fieldOperations.add(editField);

        MetadataTemplate.FieldOperation newField = new MetadataTemplate.FieldOperation();
        newField.setOp(MetadataTemplate.Operation.addField);

        MetadataTemplate.Field deptField = new MetadataTemplate.Field();
        deptField.setType("enum");
        deptField.setKey("department");
        deptField.setDisplayName("Department");

        List<String> options = new ArrayList<String>();
        options.add("Beauty");
        options.add("Shoes");
        deptField.setOptions(options);
        newField.setData(deptField);

        fieldOperations.add(newField);

        try {
            MetadataTemplate template = MetadataTemplate.updateMetadataTemplate(api,
                    "enterprise", "documentFlow03", fieldOperations);
            Assert.assertNotNull(template);
        } catch (BoxAPIException apiEx) {
            //Delete MetadataTemplate is yet to be supported. Due to that template might be existing already.
            //This 400 invalid request error if the field already exists.
            Assert.assertEquals(apiEx.getResponseCode(), 400);
        }

        MetadataTemplate updatedTemplate = MetadataTemplate.getMetadataTemplate(api, "documentFlow03");
        List<MetadataTemplate.Field> fields = updatedTemplate.getFields();

        boolean found = false;
        for (MetadataTemplate.Field field: fields) {
            if ("department".equals(field.getKey())) {
                Assert.assertEquals("enum", field.getType());
                Assert.assertEquals("Department", field.getDisplayName());
                Assert.assertEquals(2, field.getOptions().size());

                found = true;
            }
        }

        Assert.assertEquals(found, true);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllMetadataSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[getAllMetadataSucceeds] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        uploadedFile.createMetadata(new Metadata().add("/firstName", "John").add("/lastName", "Smith"));
        Metadata check1 = uploadedFile.getMetadata();
        Assert.assertNotNull(check1);
        Assert.assertEquals("John", check1.get("/firstName"));
        Assert.assertEquals("Smith", check1.get("/lastName"));

        MetadataTemplate.Field ctField = new MetadataTemplate.Field();
        ctField.setType("string");
        ctField.setKey("customerTeam");
        ctField.setDisplayName("Customer Team");

        MetadataTemplate.Field fyField = new MetadataTemplate.Field();
        fyField.setType("enum");
        fyField.setKey("fy");
        fyField.setDisplayName("FY");

        List<String> options = new ArrayList<String>();
        options.add("FY16");
        options.add("FY17");
        fyField.setOptions(options);

        List<MetadataTemplate.Field> fields = new ArrayList<MetadataTemplate.Field>();
        fields.add(ctField);
        fields.add(fyField);

        try {
            MetadataTemplate template = MetadataTemplate.createMetadataTemplate(api, "enterprise",
                    "documentFlow03", "Document Flow 03", false, fields);
        } catch (BoxAPIException apiEx) {
            //Delete MetadataTemplate is yet to be supported. Due to that template might be existing already.
            //This expects the conflict error.
            Assert.assertEquals(apiEx.getResponseCode(), 409);
            Assert.assertTrue(apiEx.getResponse().contains("Template key already exists in this scope"));
        }

        MetadataTemplate storedTemplate = MetadataTemplate.getMetadataTemplate(api, "documentFlow03");
        Assert.assertNotNull(storedTemplate);

        Metadata customerMetaData = new Metadata();
        customerMetaData.add("/customerTeam", "MyTeam");
        customerMetaData.add("/fy", "FY17");

        uploadedFile.createMetadata("documentFlow03", "enterprise", customerMetaData);

        Iterable<Metadata> allMetadata = uploadedFile.getAllMetadata("/firstName", "/lastName");
        Assert.assertNotNull(allMetadata);
        Iterator<Metadata> iter = allMetadata.iterator();
        int numTemplates = 0;
        while (iter.hasNext()) {
            Metadata metadata = iter.next();
            numTemplates++;
            if (metadata.getTemplateName().equals("properties")) {
                Assert.assertEquals(metadata.get("/firstName"), "John");
                Assert.assertEquals(metadata.get("/lastName"), "Smith");
            }
            if (metadata.getTemplateName().equals("documentFlow03")) {
                Assert.assertEquals(metadata.get("/customerTeam"), "MyTeam");
                Assert.assertEquals(metadata.get("/fy"), "FY17");
            }
        }
        Assert.assertEquals(numTemplates, 2);
        uploadedFile.delete();
    }
}
