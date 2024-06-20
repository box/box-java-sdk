package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class BoxClassificationTemplateIT {

    public MetadataTemplate getOrCreateClassificationTemplate(BoxAPIConnection api) {
        try {
            return MetadataTemplate.getMetadataTemplate(api, Metadata.CLASSIFICATION_TEMPLATE_KEY);
        } catch (BoxAPIException e) {
            assertEquals(e.getResponseCode(), 404);
        }

        MetadataTemplate.Field classification = new MetadataTemplate.Field();
        classification.setType("enum");
        classification.setKey("Box__Security__Classification__Key");
        classification.setDisplayName("Classification");
        classification.setIsHidden(false);

        List<String> options = new ArrayList<String>();
        options.add("top_secret");

        classification.setOptions(new ArrayList<String>() {
            {
                add("sensitive");
            }
        });

        List<MetadataTemplate.Field> fields = new ArrayList<MetadataTemplate.Field>();
        fields.add(classification);

        return MetadataTemplate.createMetadataTemplate(api, Metadata.ENTERPRISE_METADATA_SCOPE,
            Metadata.CLASSIFICATION_TEMPLATE_KEY, "Classification", false, fields);
    }

    @Test
    public void getUpdateClassificationTemplate() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        MetadataTemplate template = getOrCreateClassificationTemplate(api);
        String fieldKey = template.getFields().get(0).getKey();
        String optionKey = template.getFields().get(0).getOptionsObjects().get(0).getKey();

        List<MetadataTemplate.FieldOperation> updates = new ArrayList<MetadataTemplate.FieldOperation>();
        String update = "{\n"
                        + "  \"op\": \"editEnumOption\",\n"
                        + "  \"fieldKey\": \"" + fieldKey + "\",\n"
                        + "  \"enumOptionKey\": \"" + optionKey + "\",\n"
                        + "  \"data\": {\n"
                        + "    \"key\": \"" + optionKey + "\",\n"
                        + "    \"staticConfig\": {\n"
                        + "      \"classification\": {\n"
                        + "        \"classificationDefinition\": \"Sensitive information.\",\n"
                        + "        \"colorID\": 4\n"
                        + "      }\n"
                        + "    }\n"
                        + "  }\n"
                        + "}";
        updates.add(new MetadataTemplate.FieldOperation(update));

        MetadataTemplate updatedTemplate = MetadataTemplate.updateMetadataTemplate(api,
            Metadata.ENTERPRISE_METADATA_SCOPE, Metadata.CLASSIFICATION_TEMPLATE_KEY, updates);
        MetadataTemplate.Option updatedOption = updatedTemplate.getFields().get(0).getOptionsObjects().get(0);
        assertEquals(updatedOption.getKey(), "Sensitive");
        assertEquals(
            updatedOption.getStaticConfig().getClassification().getString("classificationDefinition",
                "null"),
            "Sensitive information.");
        assertEquals(updatedOption.getStaticConfig().getClassification().getInt("colorID", 0),
            4);
        update = "{\n"
                 + "  \"op\": \"editEnumOption\",\n"
                 + "  \"fieldKey\": \"" + fieldKey + "\",\n"
                 + "  \"enumOptionKey\": \"" + optionKey + "\",\n"
                 + "  \"data\": {\n"
                 + "    \"key\": \"" + optionKey + "\",\n"
                 + "    \"staticConfig\": {\n"
                 + "      \"classification\": {\n"
                 + "        \"classificationDefinition\": \"Top Sensitive information.\",\n"
                 + "        \"colorID\": 5\n"
                 + "      }\n"
                 + "    }\n"
                 + "  }\n"
                 + "}";
        updates.clear();
        updates.add(new MetadataTemplate.FieldOperation(update));

        updatedTemplate = MetadataTemplate.updateMetadataTemplate(api, Metadata.ENTERPRISE_METADATA_SCOPE,
            Metadata.CLASSIFICATION_TEMPLATE_KEY, updates);
        updatedOption = updatedTemplate.getFields().get(0).getOptionsObjects().get(0);
        assertEquals(updatedOption.getKey(), "Sensitive");
        assertEquals(updatedOption.getStaticConfig().getClassification().getString("classificationDefinition",
            "null"), "Top Sensitive information.");
        assertEquals(updatedOption.getStaticConfig().getClassification().getInt("colorID", 0), 5);
    }
}
