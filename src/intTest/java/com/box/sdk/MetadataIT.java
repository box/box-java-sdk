package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.CleanupTools.deleteFolder;
import static com.box.sdk.UniqueTestFolder.getUniqueFolder;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MetadataIT {

    @BeforeClass
    public static void beforeClass() {
        setupUniqeFolder();
    }

    @AfterClass
    public static void afterClass() {
        removeUniqueFolder();
    }

    @Test
    public void testMetadataPrecisionDouble() {
        final double valueDouble = 233333333333333340.0;
        BoxAPIConnection api = jwtApiForServiceAccount();

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
        try {
            assertEquals("float", template.getFields().get(0).getType());

            // Add template to item
            Metadata mdValues = new Metadata();
            mdValues.add("/" + fieldKey, valueDouble);
            BoxFolder rootFolder = getUniqueFolder(api);
            BoxFolder.Info folder = rootFolder.createFolder("Metadata Precision Test " + timestamp);
            Metadata actualMD = folder.getResource().createMetadata(templateKey, mdValues);

            assertEquals(templateKey, actualMD.getTemplateName());
            assertEquals(valueDouble, actualMD.getDouble("/" + fieldKey), 0);
        } finally {
            this.deleteMetadata(api, template);
        }
    }

    @Test
    public void testMultiSelectMetadataCRUD() {
        BoxAPIConnection api = jwtApiForServiceAccount();
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

            assertThat(updatedTemplate.getFields(), Matchers.hasSize(2));
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
            values = new ArrayList<>();
            values.add("two");
            values.add("one");
            actualMD.add("/otherMultiSelect", values);
            actualMD.remove("/" + fieldKey + "/0");
            actualMD.add("/" + fieldKey + "/-", "blargh");
            Metadata updatedMD = folder.updateMetadata(actualMD);

            multiSelectValues = updatedMD.getMultiSelect("/" + fieldKey);
            assertThat(multiSelectValues, Matchers.hasSize(2));
            assertThat(multiSelectValues, containsInAnyOrder("foooooo", "bar"));
            multiSelectValues = updatedMD.getMultiSelect("/otherMultiSelect");
            assertThat(multiSelectValues, hasSize(2));
            assertThat(multiSelectValues, containsInAnyOrder("one", "two"));

            // Delete metadata template and folder
        } finally {
            System.out.printf("Template [ID %s] [Key %s]%n", template.getID(), template.getTemplateKey());
            System.out.printf("Folder [ID %s]%n", folder.getID());
            this.deleteMetadata(api, template);
            deleteFolder(folder);

        }
    }

    private void deleteMetadata(BoxAPIConnection api, MetadataTemplate template) {
        if (template != null) {
            MetadataTemplate.deleteMetadataTemplate(api, template.getScope(), template.getTemplateKey());
        }
    }

}
