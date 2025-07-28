package com.box.sdkgen.test.fileclassifications;

import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.test.commons.CommonsManager.getOrCreateClassification;
import static com.box.sdkgen.test.commons.CommonsManager.getOrCreateClassificationTemplate;
import static com.box.sdkgen.test.commons.CommonsManager.uploadNewFile;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.classifications.AddClassificationRequestBody;
import com.box.sdkgen.managers.classifications.AddClassificationRequestBodyDataField;
import com.box.sdkgen.managers.classifications.AddClassificationRequestBodyDataStaticConfigClassificationField;
import com.box.sdkgen.managers.classifications.AddClassificationRequestBodyDataStaticConfigField;
import com.box.sdkgen.managers.fileclassifications.AddClassificationToFileRequestBody;
import com.box.sdkgen.managers.fileclassifications.UpdateClassificationOnFileRequestBody;
import com.box.sdkgen.schemas.classification.Classification;
import com.box.sdkgen.schemas.classificationtemplate.ClassificationTemplate;
import com.box.sdkgen.schemas.classificationtemplate.ClassificationTemplateFieldsOptionsField;
import com.box.sdkgen.schemas.filefull.FileFull;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FileClassificationsITest {

  private static final BoxClient client = getDefaultClient();

  public static ClassificationTemplateFieldsOptionsField getOrCreateSecondClassification(
      ClassificationTemplate classificationTemplate) {
    List<ClassificationTemplateFieldsOptionsField> classifications =
        classificationTemplate.getFields().get(0).getOptions();
    int currentNumberOfClassifications = classifications.size();
    if (currentNumberOfClassifications == 1) {
      ClassificationTemplate classificationTemplateWithNewClassification =
          client
              .getClassifications()
              .addClassification(
                  Arrays.asList(
                      new AddClassificationRequestBody(
                          new AddClassificationRequestBodyDataField.Builder(getUuid())
                              .staticConfig(
                                  new AddClassificationRequestBodyDataStaticConfigField.Builder()
                                      .classification(
                                          new AddClassificationRequestBodyDataStaticConfigClassificationField
                                                  .Builder()
                                              .classificationDefinition("Other description")
                                              .colorId(4L)
                                              .build())
                                      .build())
                              .build())));
      return classificationTemplateWithNewClassification.getFields().get(0).getOptions().get(1);
    }
    return classifications.get(1);
  }

  @Test
  public void testFileClassifications() {
    ClassificationTemplate classificationTemplate = getOrCreateClassificationTemplate();
    ClassificationTemplateFieldsOptionsField classification =
        getOrCreateClassification(classificationTemplate);
    FileFull file = uploadNewFile();
    assertThrows(
        RuntimeException.class,
        () -> client.getFileClassifications().getClassificationOnFile(file.getId()));
    Classification createdFileClassification =
        client
            .getFileClassifications()
            .addClassificationToFile(
                file.getId(),
                new AddClassificationToFileRequestBody.Builder()
                    .boxSecurityClassificationKey(classification.getKey())
                    .build());
    assert createdFileClassification
        .getBoxSecurityClassificationKey()
        .equals(classification.getKey());
    Classification fileClassification =
        client.getFileClassifications().getClassificationOnFile(file.getId());
    assert fileClassification.getBoxSecurityClassificationKey().equals(classification.getKey());
    ClassificationTemplateFieldsOptionsField secondClassification =
        getOrCreateSecondClassification(classificationTemplate);
    Classification updatedFileClassification =
        client
            .getFileClassifications()
            .updateClassificationOnFile(
                file.getId(),
                Arrays.asList(
                    new UpdateClassificationOnFileRequestBody(secondClassification.getKey())));
    assert updatedFileClassification
        .getBoxSecurityClassificationKey()
        .equals(secondClassification.getKey());
    client.getFileClassifications().deleteClassificationFromFile(file.getId());
    assertThrows(
        RuntimeException.class,
        () -> client.getFileClassifications().getClassificationOnFile(file.getId()));
    client.getFiles().deleteFileById(file.getId());
  }
}
