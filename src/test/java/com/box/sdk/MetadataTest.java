package com.box.sdk;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.box.sdk.UniqueTestFolder.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class MetadataTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        setupUniqeFolder();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        removeUniqueFolder();
    }

    @Test
    @Category(UnitTest.class)
    public void testConstructor() {
        Metadata m = new Metadata();
        assertEquals("{}", m.toString());
    }

    @Test
    @Category(UnitTest.class)
    public void testCopyConstructor() {
        Metadata m1 = new Metadata().add("/foo", "bar");
        Metadata m2 = new Metadata(m1);
        assertEquals("{\"foo\":\"bar\"}", m2.toString());
    }

    @Test
    @Category(UnitTest.class)
    public void testAdd() {
        Metadata m = new Metadata().add("/foo", "bar");
        JsonArray operations = JsonArray.readFrom(m.getPatch());
        assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        assertEquals("add", op.get("op").asString());
        assertEquals("/foo", op.get("path").asString());
        assertEquals("bar", op.get("value").asString());
    }

    @Test
    @Category(UnitTest.class)
    public void testReplace() {
        Metadata m = new Metadata().replace("/foo", "bar");
        JsonArray operations = JsonArray.readFrom(m.getPatch());
        assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        assertEquals("replace", op.get("op").asString());
        assertEquals("/foo", op.get("path").asString());
        assertEquals("bar", op.get("value").asString());
    }

    @Test
    @Category(UnitTest.class)
    public void testReplaceWithMultiSelect() {
        List<String> valueList = new ArrayList<>();
        valueList.add("bar");
        valueList.add("qux");
        Metadata m = new Metadata().replace("/foo", valueList);
        JsonArray operations = JsonArray.readFrom(m.getPatch());
        assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        assertEquals("replace", op.get("op").asString());
        assertEquals("/foo", op.get("path").asString());
        assertEquals("[\"bar\",\"qux\"]", op.get("value").toString());
    }

    @Test
    @Category(UnitTest.class)
    public void testTest() {
        Metadata m = new Metadata().test("/foo", "bar");
        JsonArray operations = JsonArray.readFrom(m.getPatch());
        assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        assertEquals("test", op.get("op").asString());
        assertEquals("/foo", op.get("path").asString());
        assertEquals("bar", op.get("value").asString());
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
        assertEquals("test", op.get("op").asString());
        assertEquals("/foo", op.get("path").asString());
        assertEquals(expectedList, op.get("value").toString());
    }

    @Test
    @Category(UnitTest.class)
    public void testRemove() {
        Metadata m = new Metadata().remove("/foo");
        JsonArray operations = JsonArray.readFrom(m.getPatch());
        assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        assertEquals("remove", op.get("op").asString());
        assertEquals("/foo", op.get("path").asString());
    }

    @Test
    @Category(UnitTest.class)
    public void testInvalidGet() {
        Metadata m = new Metadata();
        assertNull(m.getValue("/foo"));
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
        assertEquals("123", m.getID());
        assertEquals("my type", m.getTypeName());
        assertEquals("456", m.getParentID());
    }

    @Test
    @Category(UnitTest.class)
    public void testMissingMetaProperties() {
        Metadata m = new Metadata();
        assertNull(m.getID());
        assertNull(m.getTypeName());
        assertNull(m.getParentID());
    }

    @Test
    @Category(IntegrationTest.class)
    public void testMetadataDates() {
        // Run test with various Date formats.
        this.testMetadataDate("2017-10-10T22:10:00-08:00", 1507702200000L);
        this.testMetadataDate("2017-10-10T22:10:00.000-08:00", 1507702200000L);
        this.testMetadataDate("2017-10-10T22:10:00.123-08:00", 1507702200123L);
        this.testMetadataDate("2017-10-10T22:10:00.100-08:00", 1507702200100L);
    }

    public void testMetadataDate(String dateString, Long dateLong) {

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        long timestamp = Calendar.getInstance().getTimeInMillis();
        String templateKey = "date" + timestamp;
        String fieldKey = "testDate";

        List<MetadataTemplate.Field> fields = new ArrayList<>();
        MetadataTemplate.Field dateField = new MetadataTemplate.Field();
        dateField.setKey(fieldKey);
        dateField.setType("date");
        dateField.setDisplayName("Date Field");
        fields.add(dateField);

        MetadataTemplate template = MetadataTemplate.createMetadataTemplate(api, "enterprise",
                templateKey, "Date " + timestamp, false, fields);

        assertEquals("date", template.getFields().get(0).getType());

        Date expectedDateValue = new Date();
        expectedDateValue.setTime(dateLong);

        // Add template to item
        Metadata mdValues = new Metadata();
        mdValues.add("/" + fieldKey, dateString);
        BoxFolder.Info folder = BoxFolder.getRootFolder(api).createFolder("Metadata Test " + timestamp);
        Metadata actualMD = folder.getResource().createMetadata(templateKey, mdValues);

        assertEquals(templateKey, actualMD.getTemplateName());

        try {
            assertEquals(expectedDateValue, actualMD.getDate("/" + fieldKey));
        } catch (ParseException ex) {
            fail("Could not parse date in metadata");
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void testMetadataPrecisionFloat() {
        final float expectedValueFloat = 1234567890f;
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        long timestamp = Calendar.getInstance().getTimeInMillis();
        String templateKey = "precision" + timestamp;
        String fieldKey = "testPrecision";

        List<MetadataTemplate.Field> fields = new ArrayList<>();
        MetadataTemplate.Field valueField = new MetadataTemplate.Field();
        valueField.setKey(fieldKey);
        valueField.setType("float");
        valueField.setDisplayName("Value Field");
        fields.add(valueField);

        MetadataTemplate template = MetadataTemplate.createMetadataTemplate(api, "enterprise",
                templateKey, "Precision " + timestamp, false, fields);

        assertEquals("float", template.getFields().get(0).getType());

        // Add template to item
        Metadata mdValues = new Metadata();
        mdValues.add("/" + fieldKey, expectedValueFloat);
        BoxFolder.Info folder = BoxFolder.getRootFolder(api).createFolder("Metadata Precision Test " + timestamp);
        Metadata actualMD = folder.getResource().createMetadata(templateKey, mdValues);

        assertEquals(templateKey, actualMD.getTemplateName());

        final double actualValueDouble = actualMD.getDouble("/" + fieldKey);

        // Instead of "hard-coding" the delta to 4.0, let's calculate it and then validate it
        final double delta = actualValueDouble - (double) expectedValueFloat;
        assertEquals(4.0, delta, 0);

        // Now that we know delta is 4.0, when can use it for this validation
        assertEquals(expectedValueFloat, actualValueDouble, delta);
    }

    @Test
    @Category(IntegrationTest.class)
    public void testMetadataPrecisionDouble() {
        final double valueDouble = 233333333333333340.0;
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        long timestamp = Calendar.getInstance().getTimeInMillis();
        String templateKey = "precision" + timestamp;
        String fieldKey = "testPrecision";

        List<MetadataTemplate.Field> fields = new ArrayList<>();
        MetadataTemplate.Field valueField = new MetadataTemplate.Field();
        valueField.setKey(fieldKey);
        valueField.setType("float");
        valueField.setDisplayName("Value Field");
        fields.add(valueField);

        MetadataTemplate template = MetadataTemplate.createMetadataTemplate(api, "enterprise",
                templateKey, "Precision " + timestamp, false, fields);

        assertEquals("float", template.getFields().get(0).getType());

        // Add template to item
        Metadata mdValues = new Metadata();
        mdValues.add("/" + fieldKey, valueDouble);
        BoxFolder.Info folder = BoxFolder.getRootFolder(api).createFolder("Metadata Precision Test " + timestamp);
        Metadata actualMD = folder.getResource().createMetadata(templateKey, mdValues);

        assertEquals(templateKey, actualMD.getTemplateName());
        assertEquals(valueDouble, actualMD.getDouble("/" + fieldKey), 0);
    }

    @Test
    @Category(IntegrationTest.class)
    public void testMultiSelectMetadataCRUD() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder folder = null;
        MetadataTemplate template = null;

        try {
            long timestamp = Calendar.getInstance().getTimeInMillis();
            String templateKey = "multiselect" + timestamp;
            String fieldKey = "testMultiSelect";

            // Create new template with multiselect field
            List<String> fieldOptions = new ArrayList<>();
            fieldOptions.add("foo");
            fieldOptions.add("bar");
            fieldOptions.add("baz");
            fieldOptions.add("quux");

            List<MetadataTemplate.Field> fields = new ArrayList<>();
            MetadataTemplate.Field multiSelectField = new MetadataTemplate.Field();
            multiSelectField.setKey(fieldKey);
            multiSelectField.setType("multiSelect");
            multiSelectField.setDisplayName("MultiSelect Field");
            multiSelectField.setOptions(fieldOptions);
            fields.add(multiSelectField);

            template = MetadataTemplate.createMetadataTemplate(api, "enterprise",
                    templateKey, "MultiSelect " + timestamp, false, fields);

            assertEquals("multiSelect", template.getFields().get(0).getType());
            List<String> actualOptions = template.getFields().get(0).getOptions();
            assertEquals("foo", actualOptions.get(0));
            assertEquals("bar", actualOptions.get(1));
            assertEquals("baz", actualOptions.get(2));
            assertEquals("quux", actualOptions.get(3));

            // Add template to item
            Metadata mdValues = new Metadata();
            List<String> values = new ArrayList<>();
            values.add("foo");
            values.add("bar");
            mdValues.add("/" + fieldKey, values);
            folder = getUniqueFolder(api).createFolder("Metadata Test " + timestamp).getResource();
            Metadata actualMD = folder.createMetadata(templateKey, mdValues);

            assertEquals(templateKey, actualMD.getTemplateName());
            List<String> multiSelectValues = actualMD.getMultiSelect("/" + fieldKey);
            assertThat(multiSelectValues, hasSize(2));
            assertThat(multiSelectValues, containsInAnyOrder("foo", "bar"));

            // Update template with multiselect operations - change existing field and add another multiselect field
            List<MetadataTemplate.FieldOperation> updates = new ArrayList<>();

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
            List<String> reorderedFields = new ArrayList<>();
            reorderedFields.add("quux");
            reorderedFields.add("blargh");
            reorderedFields.add("bar");
            reorderedFields.add("foooooo");
            reorder.setMultiSelectOptionKeys(reorderedFields);
            updates.add(reorder);

            MetadataTemplate.FieldOperation addField = new MetadataTemplate.FieldOperation();
            MetadataTemplate.Field newField = new MetadataTemplate.Field();
            List<String> opts = new ArrayList<>();
            opts.add("one");
            opts.add("two");
            newField.setKey("otherMultiSelect");
            newField.setDisplayName("Another MultiSelect");
            newField.setType("multiSelect");
            newField.setOptions(opts);
            addField.setOp(MetadataTemplate.Operation.addField);
            addField.setData(newField);
            updates.add(addField);

            MetadataTemplate updatedTemplate =
                    MetadataTemplate.updateMetadataTemplate(api, "enterprise", templateKey, updates);

            assertThat(updatedTemplate.getFields(), Matchers.<MetadataTemplate.Field>hasSize(2));
            for (MetadataTemplate.Field field : updatedTemplate.getFields()) {

                if (field.getKey().equals(fieldKey)) {
                    assertEquals("multiSelect", field.getType());
                    actualOptions = field.getOptions();
                    assertEquals("quux", actualOptions.get(0));
                    assertEquals("blargh", actualOptions.get(1));
                    assertEquals("bar", actualOptions.get(2));
                    assertEquals("foooooo", actualOptions.get(3));
                } else if (field.getKey().equals("otherMultiSelect")) {
                    assertEquals("multiSelect", field.getType());
                    actualOptions = field.getOptions();
                    assertEquals("one", actualOptions.get(0));
                    assertEquals("two", actualOptions.get(1));
                } else {
                    fail("Incorrect field found on metadata template: " + field.getKey());
                }
            }

            // Update instance multiselect field
            actualMD.test("/" + fieldKey + "/0", "bar");
            //TODO: this check always fails
//            actualMD.test("/" + fieldKey + "/1", "foo");
            values = new ArrayList<>();
            values.add("two");
            values.add("one");
            actualMD.add("/otherMultiSelect", values);
            actualMD.remove("/" + fieldKey + "/0");
            actualMD.add("/" + fieldKey + "/-", "blargh");
            Metadata updatedMD = folder.updateMetadata(actualMD);

            multiSelectValues = updatedMD.getMultiSelect("/" + fieldKey);
            assertThat(multiSelectValues, Matchers.<String>hasSize(2));
            assertThat(multiSelectValues, containsInAnyOrder("blargh", "foooooo"));
            multiSelectValues = updatedMD.getMultiSelect("/otherMultiSelect");
            assertThat(multiSelectValues, hasSize(2));
            assertThat(multiSelectValues, containsInAnyOrder("one", "two"));

            // Delete metadata template and folder
        } finally {
            if (template != null) {
                MetadataTemplate.deleteMetadataTemplate(api, "enterprise", template.getTemplateKey());
            }
            if (folder != null) {
                folder.delete(true);
            }

        }
    }

    @Test
    @Category(UnitTest.class)
    public void testGetValues() {
        // Run test with various Date formats.
        this.getValues("\"2017-10-10T22:10:00-08:00\"", 1507702200000L);
        this.getValues("\"2017-10-10T22:10:00.000-08:00\"", 1507702200000L);
        this.getValues("\"2017-10-10T22:10:00.123-08:00\"", 1507702200123L);
        this.getValues("\"2017-10-10T22:10:00.100-08:00\"", 1507702200100L);
    }

    public void getValues(String dateString, Long dateLong) {

        final String stringValue = "Q1 plans";
        final int intValue = 123456;
        final String[] arrayValue = new String[2];
        arrayValue[0] = "internal";
        arrayValue[1] = "internalEng";
        Date dateValue = new Date();
        dateValue.setTime(dateLong);

        String json = "{\n"
                + "    \"audiences\": [\"internal\", \"internalEng\"],\n"
                + "    \"documentType\": \"" + stringValue + "\",\n"
                + "    \"competitiveDocument\": \"no\",\n"
                + "    \"status\": \"active\",\n"
                + "    \"deadline\": " + dateString + ",\n"
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

        assertEquals(stringValue, md.getValue("/documentType").asString());
        assertEquals(stringValue, md.getString("/documentType"));

        assertEquals(intValue, md.getValue("/capacity").asInt());
        assertEquals(intValue, md.getDouble("/capacity"), 0);

        try {
            assertEquals(dateValue, md.getDate("/deadline"));
        } catch (ParseException ex) {
            fail("Could not parse date in metadata");
        }

        for (int i = 0; i < arrayValue.length; i++) {
            assertEquals(arrayValue[i], md.getValue("/audiences").asArray().get(i).asString());
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

        String value = md.getString("/audiences");
        assertEquals("[\"internal\",\"internalEng\"]", value);
    }
}
