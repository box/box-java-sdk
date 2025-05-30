package com.box.sdkgen.foldermetadata;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.commons.CommonsManager.createNewFolder;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.foldermetadata.CreateFolderMetadataByIdScope;
import com.box.sdkgen.managers.foldermetadata.DeleteFolderMetadataByIdScope;
import com.box.sdkgen.managers.foldermetadata.GetFolderMetadataByIdScope;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBody;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsField;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsOptionsField;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsTypeField;
import com.box.sdkgen.managers.metadatatemplates.DeleteMetadataTemplateScope;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.metadatafull.MetadataFull;
import com.box.sdkgen.schemas.metadatas.Metadatas;
import com.box.sdkgen.schemas.metadatatemplate.MetadataTemplate;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class FolderMetadataITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testGlobalFolderMetadata() {
    FolderFull folder = createNewFolder();
    Metadatas folderMetadata = client.getFolderMetadata().getFolderMetadata(folder.getId());
    assert folderMetadata.getEntries().size() == 0;
    MetadataFull createdMetadata =
        client
            .getFolderMetadata()
            .createFolderMetadataById(
                folder.getId(),
                CreateFolderMetadataByIdScope.GLOBAL,
                "properties",
                mapOf(entryOf("abc", "xyz")));
    assert convertToString(createdMetadata.getTemplate()).equals("properties");
    assert convertToString(createdMetadata.getScope()).equals("global");
    assert createdMetadata.getVersion() == 0;
    MetadataFull receivedMetadata =
        client
            .getFolderMetadata()
            .getFolderMetadataById(folder.getId(), GetFolderMetadataByIdScope.GLOBAL, "properties");
    assert convertToString(receivedMetadata.getExtraData().get("abc")).equals("xyz");
    client
        .getFolderMetadata()
        .deleteFolderMetadataById(
            folder.getId(), DeleteFolderMetadataByIdScope.GLOBAL, "properties");
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getFolderMetadata()
                .getFolderMetadataById(
                    folder.getId(), GetFolderMetadataByIdScope.GLOBAL, "properties"));
    client.getFolders().deleteFolderById(folder.getId());
  }

  @Test
  public void testEnterpriseFolderMetadata() {
    FolderFull folder = createNewFolder();
    String templateKey = String.join("", "key", getUuid());
    MetadataTemplate template =
        client
            .getMetadataTemplates()
            .createMetadataTemplate(
                new CreateMetadataTemplateRequestBody.CreateMetadataTemplateRequestBodyBuilder(
                        "enterprise", templateKey)
                    .templateKey(templateKey)
                    .fields(
                        Arrays.asList(
                            new CreateMetadataTemplateRequestBodyFieldsField(
                                CreateMetadataTemplateRequestBodyFieldsTypeField.STRING,
                                "name",
                                "name"),
                            new CreateMetadataTemplateRequestBodyFieldsField(
                                CreateMetadataTemplateRequestBodyFieldsTypeField.FLOAT,
                                "age",
                                "age"),
                            new CreateMetadataTemplateRequestBodyFieldsField(
                                CreateMetadataTemplateRequestBodyFieldsTypeField.DATE,
                                "birthDate",
                                "birthDate"),
                            new CreateMetadataTemplateRequestBodyFieldsField
                                    .CreateMetadataTemplateRequestBodyFieldsFieldBuilder(
                                    CreateMetadataTemplateRequestBodyFieldsTypeField.ENUM,
                                    "countryCode",
                                    "countryCode")
                                .options(
                                    Arrays.asList(
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "US"),
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "CA")))
                                .build(),
                            new CreateMetadataTemplateRequestBodyFieldsField
                                    .CreateMetadataTemplateRequestBodyFieldsFieldBuilder(
                                    CreateMetadataTemplateRequestBodyFieldsTypeField.MULTISELECT,
                                    "sports",
                                    "sports")
                                .options(
                                    Arrays.asList(
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "basketball"),
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "football"),
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "tennis")))
                                .build()))
                    .build());
    MetadataFull createdMetadata =
        client
            .getFolderMetadata()
            .createFolderMetadataById(
                folder.getId(),
                CreateFolderMetadataByIdScope.ENTERPRISE,
                templateKey,
                mapOf(
                    entryOf("name", "John"),
                    entryOf("age", 23),
                    entryOf("birthDate", "2001-01-03T02:20:50.520Z"),
                    entryOf("countryCode", "US"),
                    entryOf("sports", Arrays.asList("basketball", "tennis"))));
    assert convertToString(createdMetadata.getTemplate()).equals(templateKey);
    assert convertToString(createdMetadata.getExtraData().get("name")).equals("John");
    assert convertToString(createdMetadata.getExtraData().get("age")).equals("23");
    assert convertToString(createdMetadata.getExtraData().get("birthDate"))
        .equals("2001-01-03T02:20:50.520Z");
    assert convertToString(createdMetadata.getExtraData().get("countryCode")).equals("US");
    client
        .getFolderMetadata()
        .deleteFolderMetadataById(
            folder.getId(), DeleteFolderMetadataByIdScope.ENTERPRISE, templateKey);
    client
        .getMetadataTemplates()
        .deleteMetadataTemplate(DeleteMetadataTemplateScope.ENTERPRISE, templateKey);
    client.getFolders().deleteFolderById(folder.getId());
  }
}
