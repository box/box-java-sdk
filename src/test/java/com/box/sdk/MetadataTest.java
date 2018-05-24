package com.box.sdk;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MetadataTest {

    @Test
    @Category(UnitTest.class)
    public void testConstructor() {
        Metadata m = new Metadata();
        Assert.assertEquals("{}", m.toString());
    }

    @Test
    @Category(UnitTest.class)
    public void testCopyConstructor() {
        Metadata m1 = new Metadata().add("/foo", "bar");
        Metadata m2 = new Metadata(m1);
        Assert.assertEquals("{\"foo\":\"bar\"}", m2.toString());
    }

    @Test
    @Category(UnitTest.class)
    public void testAdd() {
        Metadata m = new Metadata().add("/foo", "bar");
        JsonArray operations = JsonArray.readFrom(m.getPatch());
        Assert.assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        Assert.assertEquals("add", op.get("op").asString());
        Assert.assertEquals("/foo", op.get("path").asString());
        Assert.assertEquals("bar", op.get("value").asString());
    }

    @Test
    @Category(UnitTest.class)
    public void testReplace() {
        Metadata m = new Metadata().replace("/foo", "bar");
        JsonArray operations = JsonArray.readFrom(m.getPatch());
        Assert.assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        Assert.assertEquals("replace", op.get("op").asString());
        Assert.assertEquals("/foo", op.get("path").asString());
        Assert.assertEquals("bar", op.get("value").asString());
    }

    @Test
    @Category(UnitTest.class)
    public void testTest() {
        Metadata m = new Metadata().test("/foo", "bar");
        JsonArray operations = JsonArray.readFrom(m.getPatch());
        Assert.assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        Assert.assertEquals("test", op.get("op").asString());
        Assert.assertEquals("/foo", op.get("path").asString());
        Assert.assertEquals("bar", op.get("value").asString());
    }

    @Test
    @Category(UnitTest.class)
    public void testMultiSelect() {
        String expectedList = "[\"public test 1\",\"public test 2\",\"public test 3\"]";
        List<String> list = new ArrayList<>();
        list.add("public test 1");
        list.add("public test 2");
        list.add("public test 3");
        Metadata m = new Metadata().test("/foo", list);
        JsonArray operations = JsonArray.readFrom(m.getPatch());
        JsonObject op = operations.get(0).asObject();
        Assert.assertEquals("test", op.get("op").asString());
        Assert.assertEquals("/foo", op.get("path").asString());
        Assert.assertEquals(expectedList, op.get("value").toString());
    }

    @Test
    @Category(UnitTest.class)
    public void testRemove() {
        Metadata m = new Metadata().remove("/foo");
        JsonArray operations = JsonArray.readFrom(m.getPatch());
        Assert.assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        Assert.assertEquals("remove", op.get("op").asString());
        Assert.assertEquals("/foo", op.get("path").asString());
    }

    @Test
    @Category(UnitTest.class)
    public void testInvalidGet() {
        Metadata m = new Metadata();
        Assert.assertEquals(null, m.getValue("/foo"));
    }

    @Test(expected = IllegalArgumentException.class)
    @Category(UnitTest.class)
    public void testNullPath() {
        new Metadata().add(null, "value");
    }

    @Test(expected = IllegalArgumentException.class)
    @Category(UnitTest.class)
    public void testInvalidPath() {
        new Metadata().add("key", "value");
    }

    @Test
    @Category(UnitTest.class)
    public void testMetaProperties() {
        String json = "{\"$id\":\"123\",\"$type\":\"my type\",\"$parent\":\"456\"}";
        Metadata m = new Metadata(JsonObject.readFrom(json));
        Assert.assertEquals("123", m.getID());
        Assert.assertEquals("my type", m.getTypeName());
        Assert.assertEquals("456", m.getParentID());
    }

    @Test
    @Category(UnitTest.class)
    public void testMissingMetaProperties() {
        Metadata m = new Metadata();
        Assert.assertEquals(null, m.getID());
        Assert.assertEquals(null, m.getTypeName());
        Assert.assertEquals(null, m.getParentID());
    }

    @Test
    @Category(IntegrationTest.class)
    public void testMultiSelectMetadataCRUD() {

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        long timestamp = Calendar.getInstance().getTimeInMillis();
        String templateKey = "multiselect" + timestamp;
        String fieldKey = "testMultiSelect";

        // Create new template with multiselect field
        List<String> fieldOptions = new ArrayList<String>();
        fieldOptions.add("foo");
        fieldOptions.add("bar");
        fieldOptions.add("baz");
        fieldOptions.add("quux");

        List<MetadataTemplate.Field> fields = new ArrayList<MetadataTemplate.Field>();
        MetadataTemplate.Field multiSelectField = new MetadataTemplate.Field();
        multiSelectField.setKey(fieldKey);
        multiSelectField.setType("multiSelect");
        multiSelectField.setDisplayName("MultiSelect Field");
        multiSelectField.setOptions(fieldOptions);
        fields.add(multiSelectField);

        MetadataTemplate template = MetadataTemplate.createMetadataTemplate(api, "enterprise",
                templateKey, "MultiSelect " + timestamp, false, fields);

        Assert.assertEquals("multiSelect", template.getFields().get(0).getType());
        List<String> actualOptions = template.getFields().get(0).getOptions();
        Assert.assertEquals("foo", actualOptions.get(0));
        Assert.assertEquals("bar", actualOptions.get(1));
        Assert.assertEquals("baz", actualOptions.get(2));
        Assert.assertEquals("quux", actualOptions.get(3));

        // Add template to item
        Metadata mdValues = new Metadata();
        List<String> values = new ArrayList<String>();
        values.add("foo");
        values.add("bar");
        mdValues.add("/" + fieldKey, values);
        BoxFolder.Info folder = BoxFolder.getRootFolder(api).createFolder("Metadata Test " + timestamp);
        Metadata actualMD = folder.getResource().createMetadata(templateKey, mdValues);

        Assert.assertEquals(templateKey, actualMD.getTemplateName());
        List<String> multiSelectValues = actualMD.getMultiSelect("/" + fieldKey);
        Assert.assertEquals(2, multiSelectValues.size());
        Assert.assertEquals("foo", multiSelectValues.get(0));
        Assert.assertEquals("bar", multiSelectValues.get(1));

        // Update template with multiselect operations - change existing field and add another multiselect field
        List<MetadataTemplate.FieldOperation> updates = new ArrayList<MetadataTemplate.FieldOperation>();

        MetadataTemplate.Field newOption = new MetadataTemplate.Field();
        newOption.setKey("blargh");
        MetadataTemplate.FieldOperation add = new MetadataTemplate.FieldOperation();
        add.setOp(MetadataTemplate.Operation.addMultiSelectOption);
        add.setFieldKey(fieldKey);
        add.setData(newOption);
        updates.add(add);

        MetadataTemplate.Field updatedField = new MetadataTemplate.Field();
        updatedField.setKey("foooooo");
        MetadataTemplate.FieldOperation edit = new MetadataTemplate.FieldOperation();
        edit.setOp(MetadataTemplate.Operation.editMultiSelectOption);
        edit.setFieldKey(fieldKey);
        edit.setMultiSelectOptionKey("foo");
        edit.setData(updatedField);
        updates.add(edit);

        MetadataTemplate.FieldOperation remove = new MetadataTemplate.FieldOperation();
        remove.setOp(MetadataTemplate.Operation.removeMultiSelectOption);
        remove.setFieldKey(fieldKey);
        remove.setMultiSelectOptionKey("baz");
        updates.add(remove);

        MetadataTemplate.FieldOperation reorder = new MetadataTemplate.FieldOperation();
        reorder.setOp(MetadataTemplate.Operation.reorderMultiSelectOptions);
        reorder.setFieldKey(fieldKey);
        List<String> reorderedFields = new ArrayList<String>();
        reorderedFields.add("quux");
        reorderedFields.add("blargh");
        reorderedFields.add("bar");
        reorderedFields.add("foooooo");
        reorder.setMultiSelectOptionKeys(reorderedFields);
        updates.add(reorder);

        MetadataTemplate.FieldOperation addField = new MetadataTemplate.FieldOperation();
        MetadataTemplate.Field newField = new MetadataTemplate.Field();
        List<String> opts = new ArrayList<String>();
        opts.add("one");
        opts.add("two");
        newField.setKey("otherMultiSelect");
        newField.setDisplayName("Another MultiSelect");
        newField.setType("multiSelect");
        newField.setOptions(opts);
        addField.setOp(MetadataTemplate.Operation.addField);
        addField.setData(newField);
        updates.add(addField);

        MetadataTemplate updatedTemplate = MetadataTemplate.updateMetadataTemplate(api, "enterprise",
                templateKey, updates);

        Assert.assertEquals(2, updatedTemplate.getFields().size());
        for (MetadataTemplate.Field field : updatedTemplate.getFields()) {

            if (field.getKey().equals(fieldKey)) {
                Assert.assertEquals("multiSelect", field.getType());
                actualOptions = field.getOptions();
                Assert.assertEquals("quux", actualOptions.get(0));
                Assert.assertEquals("blargh", actualOptions.get(1));
                Assert.assertEquals("bar", actualOptions.get(2));
                Assert.assertEquals("foooooo", actualOptions.get(3));
            } else if (field.getKey().equals("otherMultiSelect")) {
                Assert.assertEquals("multiSelect", field.getType());
                actualOptions = field.getOptions();
                Assert.assertEquals("one", actualOptions.get(0));
                Assert.assertEquals("two", actualOptions.get(1));
            } else {
                Assert.fail("Incorrect field found on metadata template: " + field.getKey());
            }
        }

        // Update instance multiselect field
        values = new ArrayList<String>();
        values.add("two");
        values.add("one");
        actualMD.add("/otherMultiSelect", values);
        actualMD.test("/" + fieldKey + "/0", "foooooo");
        actualMD.test("/" + fieldKey + "/1", "bar");
        actualMD.remove("/" + fieldKey + "/0");
        actualMD.add("/" + fieldKey + "/-", "blargh");
        Metadata updatedMD = folder.getResource().updateMetadata(actualMD);

        multiSelectValues = updatedMD.getMultiSelect("/" + fieldKey);
        Assert.assertEquals(2, multiSelectValues.size());
        Assert.assertEquals("bar", multiSelectValues.get(0));
        Assert.assertEquals("blargh", multiSelectValues.get(1));
        multiSelectValues = updatedMD.getMultiSelect("/otherMultiSelect");
        Assert.assertEquals(2, multiSelectValues.size());
        Assert.assertEquals("two", multiSelectValues.get(0));
        Assert.assertEquals("one", multiSelectValues.get(1));

        // Delete metadata template and folder
        MetadataTemplate.deleteMetadataTemplate(api, "enterprise", template.getTemplateKey());
        folder.getResource().delete(true);
    }

    @Test
    @Category(UnitTest.class)
    public void getValues() {

        final String stringValue = "Q1 plans";
        final int intValue = 123456;
        final String[] arrayValue = new String[2];
        arrayValue[0] = "internal";
        arrayValue[1] = "internalEng";
        Date dateValue = new Date();
        dateValue.setTime(1507702200000L);

        String json = "{\n"
                + "    \"audiences\": [\"internal\", \"internalEng\"],\n"
                + "    \"documentType\": \"" + stringValue + "\",\n"
                + "    \"competitiveDocument\": \"no\",\n"
                + "    \"status\": \"active\",\n"
                + "    \"deadline\": \"2017-10-10T22:10:00-08:00\",\n"
                + "    \"capacity\": " + intValue + ",\n"
                + "    \"currentState\": \"proposal\",\n"
                + "    \"$type\": \"marketingCollateral-d086c908-2498-4d3e-8a1f-01e82bfc2abe\",\n"
                + "    \"$parent\": \"file_5010739061\",\n"
                + "    \"$id\": \"2094c584-68e1-475c-a581-534a4609594e\",\n"
                + "    \"$version\": 0,\n"
                + "    \"$typeVersion\": 0,\n"
                + "    \"$template\": \"marketingCollateral\",\n"
                + "    \"$scope\": \"enterprise_12345\"\n"
                + "}";
        Metadata md = new Metadata(JsonObject.readFrom(json));

        Assert.assertEquals(stringValue, md.getValue("/documentType").asString());
        Assert.assertEquals(stringValue, md.getString("/documentType"));

        Assert.assertEquals(intValue, md.getValue("/capacity").asInt());
        Assert.assertEquals((float) intValue, md.getFloat("/capacity"), 0);

        try {
            Assert.assertEquals(dateValue, md.getDate("/deadline"));
        } catch (ParseException ex) {
            Assert.fail("Could not parse date in metadata");
        }

        for (int i = 0; i < arrayValue.length; i++) {
            Assert.assertEquals(arrayValue[i], md.getValue("/audiences").asArray().get(i).asString());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void getHandlesArrayValue() {

        String json = "{\n"
                + "    \"audiences\": [\"internal\", \"internalEng\"],\n"
                + "    \"$type\": \"marketingCollateral-d086c908-2498-4d3e-8a1f-01e82bfc2abe\",\n"
                + "    \"$parent\": \"file_5010739061\",\n"
                + "    \"$id\": \"2094c584-68e1-475c-a581-534a4609594e\",\n"
                + "    \"$version\": 0,\n"
                + "    \"$typeVersion\": 0,\n"
                + "    \"$template\": \"marketingCollateral\",\n"
                + "    \"$scope\": \"enterprise_12345\"\n"
                + "}";
        Metadata md = new Metadata(JsonObject.readFrom(json));

        String value = md.get("/audiences");
        Assert.assertEquals("[\"internal\",\"internalEng\"]", value);
    }
}
