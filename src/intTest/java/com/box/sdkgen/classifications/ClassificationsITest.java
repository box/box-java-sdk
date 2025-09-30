package com.box.sdkgen.classifications;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.getOrCreateClassification;
import static com.box.sdkgen.commons.CommonsManager.getOrCreateClassificationTemplate;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.classifications.UpdateClassificationRequestBody;
import com.box.sdkgen.managers.classifications.UpdateClassificationRequestBodyDataField;
import com.box.sdkgen.managers.classifications.UpdateClassificationRequestBodyDataStaticConfigClassificationField;
import com.box.sdkgen.managers.classifications.UpdateClassificationRequestBodyDataStaticConfigField;
import com.box.sdkgen.schemas.classificationtemplate.ClassificationTemplate;
import com.box.sdkgen.schemas.classificationtemplate.ClassificationTemplateFieldsOptionsField;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ClassificationsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testClassifications() {
    ClassificationTemplate classificationTemplate = getOrCreateClassificationTemplate();
    ClassificationTemplateFieldsOptionsField classification =
        getOrCreateClassification(classificationTemplate);
    assert !(classification.getKey().equals(""));
    assert !(classification.getStaticConfig().getClassification().getColorId() == 100);
    assert !(classification
        .getStaticConfig()
        .getClassification()
        .getClassificationDefinition()
        .equals(""));
    String updatedClassificationName = getUuid();
    String updatedClassificationDescription = getUuid();
    ClassificationTemplate classificationTemplateWithUpdatedClassification =
        client
            .getClassifications()
            .updateClassification(
                Arrays.asList(
                    new UpdateClassificationRequestBody(
                        classification.getKey(),
                        new UpdateClassificationRequestBodyDataField.Builder(
                                updatedClassificationName)
                            .staticConfig(
                                new UpdateClassificationRequestBodyDataStaticConfigField.Builder()
                                    .classification(
                                        new UpdateClassificationRequestBodyDataStaticConfigClassificationField
                                                .Builder()
                                            .classificationDefinition(
                                                updatedClassificationDescription)
                                            .colorId(2L)
                                            .build())
                                    .build())
                            .build())));
    List<ClassificationTemplateFieldsOptionsField> updatedClassifications =
        classificationTemplateWithUpdatedClassification.getFields().get(0).getOptions();
    ClassificationTemplateFieldsOptionsField updatedClassification = updatedClassifications.get(0);
    assert updatedClassification.getKey().equals(updatedClassificationName);
    assert updatedClassification.getStaticConfig().getClassification().getColorId() == 2;
    assert updatedClassification
        .getStaticConfig()
        .getClassification()
        .getClassificationDefinition()
        .equals(updatedClassificationDescription);
  }
}
