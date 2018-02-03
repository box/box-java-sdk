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
    public final WireMockRule wireMockRule = new WireMockRule(53620);

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

    @Test
    @Category(UnitTest.class)
    public void testDeleteMetadataTemplateSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("DELETE", request.getMethod());
                Assert.assertEquals(
                        "https://api.box.com/2.0/metadata_templates/enterprise/testtemplate/schema",
                        request.getUrl().toString());
                return new BoxAPIResponse() {
                    public String getJSON() {
                        return "{\"{}\"}";
                    }
                };
            }
        });
        MetadataTemplate.deleteMetadataTemplate(api, "enterprise", "testtemplate");
    }

    @Test
    @Category(IntegrationTest.class)
    public void testDeleteMetadataTemplateSucceeds() {
        String scope = "enterprise";
        String template = "testtemplate";
        String displayName = "Test Template";
        Boolean templateIsHidden = false;
        int errorResponseStatusCode = 404;

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        try {
            MetadataTemplate.createMetadataTemplate(api, scope, template, displayName, templateIsHidden, null);
        } catch (BoxAPIException e) {
            System.out.print("Error while making callout to createMetdataTemplate(): " + e);
        }

        MetadataTemplate.deleteMetadataTemplate(api, scope, template);

        try {
            MetadataTemplate.getMetadataTemplate(api, template);
        } catch (BoxAPIException e) {
            Assert.assertEquals(errorResponseStatusCode, e.getResponseCode());
        }
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
        api.setBaseURL("http://localhost:53620/");
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
            Assert.assertEquals(409, apiEx.getResponseCode());
            Assert.assertTrue(apiEx.getResponse().contains("Template key already exists in this scope"));
        }

        MetadataTemplate storedTemplate = MetadataTemplate.getMetadataTemplate(api, "documentFlow03");
        Assert.assertNotNull(storedTemplate);
    }

    private List<MetadataTemplate.FieldOperation> addFieldsHelper() {
        List<MetadataTemplate.FieldOperation> fieldOperations = new ArrayList<MetadataTemplate.FieldOperation>();
        MetadataTemplate.FieldOperation customerFieldOp = new MetadataTemplate.FieldOperation();
        customerFieldOp.setOp(MetadataTemplate.Operation.addField);

        MetadataTemplate.Field customerTeam = new MetadataTemplate.Field();
        customerTeam.setType("string");
        customerTeam.setKey("customerTeam");
        customerTeam.setDisplayName("Customer Team");
        customerFieldOp.setData(customerTeam);
        fieldOperations.add(customerFieldOp);

        MetadataTemplate.FieldOperation departmentFieldOp = new MetadataTemplate.FieldOperation();
        departmentFieldOp.setOp(MetadataTemplate.Operation.addField);

        MetadataTemplate.Field deptField = new MetadataTemplate.Field();
        deptField.setType("enum");
        deptField.setKey("department");
        deptField.setDisplayName("Department");

        List<String> options = new ArrayList<String>();
        options.add("Beauty");
        options.add("Shoes");
        deptField.setOptions(options);
        departmentFieldOp.setData(deptField);

        fieldOperations.add(departmentFieldOp);
        return fieldOperations;
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateMetadataTemplateFieldsSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        try {
            //Test adding fields
            List<MetadataTemplate.FieldOperation> fieldOperations = this.addFieldsHelper();
            MetadataTemplate template = MetadataTemplate.updateMetadataTemplate(api,
                "enterprise", "documentFlow03", fieldOperations);
            Assert.assertNotNull(template);

            boolean foundDeptField = false;
            boolean foundCustField = false;
            for (MetadataTemplate.Field field : template.getFields()) {
                if ("department".equals(field.getKey())) {
                    Assert.assertEquals("enum", field.getType());
                    Assert.assertEquals("Department", field.getDisplayName());
                    Assert.assertEquals(2, field.getOptions().size());
                    foundDeptField = true;
                } else if ("customerTeam".equals(field.getKey())) {
                    foundCustField = true;
                }
            }
            Assert.assertEquals("department field was not found", true, foundDeptField);
            Assert.assertEquals("customer field was not found", true, foundCustField);

            //Test editing fields
            fieldOperations.clear();

            MetadataTemplate.FieldOperation customerFieldOp = new MetadataTemplate.FieldOperation();
            customerFieldOp.setOp(MetadataTemplate.Operation.editField);
            customerFieldOp.setFieldKey("customerTeam");

            MetadataTemplate.Field customerTeam = new MetadataTemplate.Field();
            customerTeam.setDisplayName("Customer Team Renamed");
            customerTeam.setKey("newCustomerTeamKey");
            customerFieldOp.setData(customerTeam);
            fieldOperations.add(customerFieldOp);

            MetadataTemplate.FieldOperation editEnumOption = new MetadataTemplate.FieldOperation();
            editEnumOption.setOp(MetadataTemplate.Operation.editEnumOption);
            editEnumOption.setFieldKey("department");
            editEnumOption.setEnumOptionKey("Shoes");


            MetadataTemplate.Field deptField = new MetadataTemplate.Field();
            deptField.setKey("Baby");
            editEnumOption.setData(deptField);

            fieldOperations.add(editEnumOption);

            template = MetadataTemplate.updateMetadataTemplate(api,
                "enterprise", "documentFlow03", fieldOperations);
            boolean foundBabyEnumOption = false;
            for (MetadataTemplate.Field field : template.getFields()) {
                if ("customerTeam".equals(field.getKey())) {
                    Assert.fail("'customerTeam' field key should have been changed to 'newCustomerTeamKey'");
                } else if ("department".equals(field.getKey())) {
                    for (String option : field.getOptions()) {
                        if ("Baby".equals(option)) {
                            foundBabyEnumOption = true;
                        }
                    }
                } else if ("newCustomerTeamKey".equals(field.getKey())) {
                    Assert.assertEquals("Display name should have been updated",
                        "Customer Team Renamed", field.getDisplayName());
                }
            }
            Assert.assertEquals("Baby enum option was not found", true, foundBabyEnumOption);

            //Test removing fields
            fieldOperations.clear();

            MetadataTemplate.FieldOperation deleteDeptField = new MetadataTemplate.FieldOperation();
            deleteDeptField.setOp(MetadataTemplate.Operation.removeField);
            deleteDeptField.setFieldKey("newCustomerTeamKey");
            fieldOperations.add(deleteDeptField);

            MetadataTemplate.FieldOperation deleteEnumOption = new MetadataTemplate.FieldOperation();
            deleteEnumOption.setOp(MetadataTemplate.Operation.removeEnumOption);
            deleteEnumOption.setFieldKey("department");
            deleteEnumOption.setEnumOptionKey("Baby");

            fieldOperations.add(deleteEnumOption);

            template = MetadataTemplate.updateMetadataTemplate(api, "enterprise", "documentFlow03", fieldOperations);

            for (MetadataTemplate.Field field : template.getFields()) {
                if ("newCustomerTeamKey".equals(field.getKey())) {
                    Assert.fail("newCustomerTeamKey field key should have been deleted");
                } else if ("department".equals(field.getKey())) {
                    for (String option : field.getOptions()) {
                        if ("Baby".equals(option)) {
                            Assert.fail("Baby enum option should have been deleted");
                        }
                    }
                }
            }
        } finally {
            this.tearDownFields(api);
        }
    }

    @Test
    @Category(UnitTest.class)
    public void testUpdateMetadataTemplateMakesCorrectRequestAndReturnsTemplate() {

        final String responseJSON = "{\n"
                + "    \"templateKey\": \"customer\",\n"
                + "    \"scope\": \"enterprise_490685\",\n"
                + "    \"displayName\": \"Customer\",\n"
                + "    \"fields\": [\n"
                + "        {\n"
                + "            \"type\": \"string\",\n"
                + "            \"key\": \"customerTeam\",\n"
                + "            \"displayName\": \"Customer team\",\n"
                + "            \"hidden\": false\n"
                + "        }\n"
                + "     ]\n"
                + "}";

        final String updateOpJSON = "{\n"
                + "\"op\":\"editField\",\n"
                + "\"fieldKey\":\"customerTeam\",\n"
                + "\"data\":{\"displayName\":\"Customer team\"}}";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals(
                        "https://api.box.com/2.0/metadata_templates/global/properties/schema",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return responseJSON;
                    }
                };
            }
        });

        List<MetadataTemplate.FieldOperation> updates = new ArrayList<MetadataTemplate.FieldOperation>();
        updates.add(new MetadataTemplate.FieldOperation(updateOpJSON));
        MetadataTemplate.updateMetadataTemplate(api, "global", "properties", updates);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllMetadataSucceeds() {
        BoxFile uploadedFile = null;
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        try {
            BoxFolder rootFolder = BoxFolder.getRootFolder(api);
            String fileName = "[getAllMetadataSucceeds] Test File.txt";
            byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);

            InputStream uploadStream = new ByteArrayInputStream(fileBytes);
            uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

            uploadedFile.createMetadata(new Metadata().add("/firstName", "John").add("/lastName", "Smith"));
            Metadata check1 = uploadedFile.getMetadata();
            Assert.assertNotNull(check1);
            Assert.assertEquals("John", check1.getString("/firstName"));
            Assert.assertEquals("Smith", check1.getString("/lastName"));

            //Create fields before test
            List<MetadataTemplate.FieldOperation> fieldOperations = this.addFieldsHelper();
            MetadataTemplate template = MetadataTemplate.updateMetadataTemplate(api,
                "enterprise", "documentFlow03", fieldOperations);
            Assert.assertNotNull(template);

            Metadata customerMetaData = new Metadata();
            customerMetaData.add("/customerTeam", "MyTeam");
            customerMetaData.add("/department", "Beauty");

            uploadedFile.createMetadata("documentFlow03", "enterprise", customerMetaData);

            Iterable<Metadata> allMetadata = uploadedFile.getAllMetadata("/firstName", "/lastName");
            Assert.assertNotNull(allMetadata);
            Iterator<Metadata> iter = allMetadata.iterator();
            int numTemplates = 0;
            while (iter.hasNext()) {
                Metadata metadata = iter.next();
                numTemplates++;
                if (metadata.getTemplateName().equals("properties")) {
                    Assert.assertEquals("John", metadata.getString("/firstName"));
                    Assert.assertEquals("Smith", metadata.getString("/lastName"));
                }
                if (metadata.getTemplateName().equals("documentFlow03")) {
                    Assert.assertEquals("MyTeam", metadata.getString("/customerTeam"));
                    Assert.assertEquals("Beauty", metadata.getString("/department"));
                }
            }
            Assert.assertTrue("Should have at least 2 templates", numTemplates >= 2);
        } finally {
            if (uploadedFile != null) {
                uploadedFile.delete();
            } else {
                Assert.fail("File ");
            }
            this.tearDownFields(api);
        }
    }

    private void tearDownFields(BoxAPIConnection api) {
        List<MetadataTemplate.FieldOperation> fieldOperations = new ArrayList<MetadataTemplate.FieldOperation>();
        MetadataTemplate template = MetadataTemplate.getMetadataTemplate(api, "documentFlow03", "enterprise");
        for (MetadataTemplate.Field field : template.getFields()) {
            MetadataTemplate.FieldOperation deleteField = new MetadataTemplate.FieldOperation();
            deleteField.setOp(MetadataTemplate.Operation.removeField);
            deleteField.setFieldKey(field.getKey());
            fieldOperations.add(deleteField);
        }
        MetadataTemplate updatedTemplate = MetadataTemplate.updateMetadataTemplate(api,
            "enterprise", "documentFlow03", fieldOperations);
    }
}
