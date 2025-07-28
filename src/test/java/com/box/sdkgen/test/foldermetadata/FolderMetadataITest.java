package com.box.sdkgen.test.foldermetadata;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.test.commons.CommonsManager.createNewFolder;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.foldermetadata.CreateFolderMetadataByIdScope;
import com.box.sdkgen.managers.foldermetadata.DeleteFolderMetadataByIdScope;
import com.box.sdkgen.managers.foldermetadata.GetFolderMetadataByIdScope;
import com.box.sdkgen.managers.foldermetadata.UpdateFolderMetadataByIdRequestBody;
import com.box.sdkgen.managers.foldermetadata.UpdateFolderMetadataByIdRequestBodyOpField;
import com.box.sdkgen.managers.foldermetadata.UpdateFolderMetadataByIdScope;
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
  public void testUpdatingFolderMetadata() {
    FolderFull folder = createNewFolder();
    String templateKey = String.join("", "key", getUuid());
    MetadataTemplate template =
        client
            .getMetadataTemplates()
            .createMetadataTemplate(
                new CreateMetadataTemplateRequestBody.Builder("enterprise", templateKey)
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
                            new CreateMetadataTemplateRequestBodyFieldsField.Builder(
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
                            new CreateMetadataTemplateRequestBodyFieldsField.Builder(
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
    MetadataFull updatedMetadata =
        client
            .getFolderMetadata()
            .updateFolderMetadataById(
                folder.getId(),
                UpdateFolderMetadataByIdScope.ENTERPRISE,
                templateKey,
                Arrays.asList(
                    new UpdateFolderMetadataByIdRequestBody.Builder()
                        .op(UpdateFolderMetadataByIdRequestBodyOpField.REPLACE)
                        .path("/name")
                        .value("Jack")
                        .build(),
                    new UpdateFolderMetadataByIdRequestBody.Builder()
                        .op(UpdateFolderMetadataByIdRequestBodyOpField.REPLACE)
                        .path("/age")
                        .value(24L)
                        .build(),
                    new UpdateFolderMetadataByIdRequestBody.Builder()
                        .op(UpdateFolderMetadataByIdRequestBodyOpField.REPLACE)
                        .path("/birthDate")
                        .value("2000-01-03T02:20:50.520Z")
                        .build(),
                    new UpdateFolderMetadataByIdRequestBody.Builder()
                        .op(UpdateFolderMetadataByIdRequestBodyOpField.REPLACE)
                        .path("/countryCode")
                        .value("CA")
                        .build(),
                    new UpdateFolderMetadataByIdRequestBody.Builder()
                        .op(UpdateFolderMetadataByIdRequestBodyOpField.REPLACE)
                        .path("/sports")
                        .value(Arrays.asList("football"))
                        .build()));
    assert convertToString(updatedMetadata.getTemplate()).equals(templateKey);
    assert convertToString(updatedMetadata.getExtraData().get("name")).equals("Jack");
    assert convertToString(updatedMetadata.getExtraData().get("age")).equals("24");
    assert convertToString(updatedMetadata.getExtraData().get("birthDate"))
        .equals("2000-01-03T02:20:50.520Z");
    assert convertToString(updatedMetadata.getExtraData().get("countryCode")).equals("CA");
    client
        .getFolderMetadata()
        .deleteFolderMetadataById(
            folder.getId(), DeleteFolderMetadataByIdScope.ENTERPRISE, templateKey);
    client
        .getMetadataTemplates()
        .deleteMetadataTemplate(DeleteMetadataTemplateScope.ENTERPRISE, templateKey);
    client.getFolders().deleteFolderById(folder.getId());
  }

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
    String newValue = "bar";
    client
        .getFolderMetadata()
        .updateFolderMetadataById(
            folder.getId(),
            UpdateFolderMetadataByIdScope.GLOBAL,
            "properties",
            Arrays.asList(
                new UpdateFolderMetadataByIdRequestBody.Builder()
                    .op(UpdateFolderMetadataByIdRequestBodyOpField.REPLACE)
                    .path("/abc")
                    .value(newValue)
                    .build()));
    MetadataFull receivedUpdatedMetadata =
        client
            .getFolderMetadata()
            .getFolderMetadataById(folder.getId(), GetFolderMetadataByIdScope.GLOBAL, "properties");
    assert convertToString(receivedUpdatedMetadata.getExtraData().get("abc")).equals(newValue);
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
                new CreateMetadataTemplateRequestBody.Builder("enterprise", templateKey)
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
                            new CreateMetadataTemplateRequestBodyFieldsField.Builder(
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
                            new CreateMetadataTemplateRequestBodyFieldsField.Builder(
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
