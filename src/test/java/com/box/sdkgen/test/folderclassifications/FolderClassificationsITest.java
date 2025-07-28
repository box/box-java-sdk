package com.box.sdkgen.test.folderclassifications;

import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.createNewFolder;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.test.commons.CommonsManager.getOrCreateClassification;
import static com.box.sdkgen.test.commons.CommonsManager.getOrCreateClassificationTemplate;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.classifications.AddClassificationRequestBody;
import com.box.sdkgen.managers.classifications.AddClassificationRequestBodyDataField;
import com.box.sdkgen.managers.classifications.AddClassificationRequestBodyDataStaticConfigClassificationField;
import com.box.sdkgen.managers.classifications.AddClassificationRequestBodyDataStaticConfigField;
import com.box.sdkgen.managers.folderclassifications.AddClassificationToFolderRequestBody;
import com.box.sdkgen.managers.folderclassifications.UpdateClassificationOnFolderRequestBody;
import com.box.sdkgen.schemas.classification.Classification;
import com.box.sdkgen.schemas.classificationtemplate.ClassificationTemplate;
import com.box.sdkgen.schemas.classificationtemplate.ClassificationTemplateFieldsOptionsField;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FolderClassificationsITest {

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
  public void testFolderClassifications() {
    ClassificationTemplate classificationTemplate = getOrCreateClassificationTemplate();
    ClassificationTemplateFieldsOptionsField classification =
        getOrCreateClassification(classificationTemplate);
    FolderFull folder = createNewFolder();
    assertThrows(
        RuntimeException.class,
        () -> client.getFolderClassifications().getClassificationOnFolder(folder.getId()));
    Classification createdFolderClassification =
        client
            .getFolderClassifications()
            .addClassificationToFolder(
                folder.getId(),
                new AddClassificationToFolderRequestBody.Builder()
                    .boxSecurityClassificationKey(classification.getKey())
                    .build());
    assert createdFolderClassification
        .getBoxSecurityClassificationKey()
        .equals(classification.getKey());
    Classification folderClassification =
        client.getFolderClassifications().getClassificationOnFolder(folder.getId());
    assert folderClassification.getBoxSecurityClassificationKey().equals(classification.getKey());
    ClassificationTemplateFieldsOptionsField secondClassification =
        getOrCreateSecondClassification(classificationTemplate);
    Classification updatedFolderClassification =
        client
            .getFolderClassifications()
            .updateClassificationOnFolder(
                folder.getId(),
                Arrays.asList(
                    new UpdateClassificationOnFolderRequestBody(secondClassification.getKey())));
    assert updatedFolderClassification
        .getBoxSecurityClassificationKey()
        .equals(secondClassification.getKey());
    client.getFolderClassifications().deleteClassificationFromFolder(folder.getId());
    assertThrows(
        RuntimeException.class,
        () -> client.getFolderClassifications().getClassificationOnFolder(folder.getId()));
    client.getFolders().deleteFolderById(folder.getId());
  }
}
