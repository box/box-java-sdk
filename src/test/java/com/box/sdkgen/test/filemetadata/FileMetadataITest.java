package com.box.sdkgen.test.filemetadata;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.test.commons.CommonsManager.uploadNewFile;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.filemetadata.CreateFileMetadataByIdScope;
import com.box.sdkgen.managers.filemetadata.DeleteFileMetadataByIdScope;
import com.box.sdkgen.managers.filemetadata.GetFileMetadataByIdScope;
import com.box.sdkgen.managers.filemetadata.UpdateFileMetadataByIdRequestBody;
import com.box.sdkgen.managers.filemetadata.UpdateFileMetadataByIdRequestBodyOpField;
import com.box.sdkgen.managers.filemetadata.UpdateFileMetadataByIdScope;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBody;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsField;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsOptionsField;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsTypeField;
import com.box.sdkgen.managers.metadatatemplates.DeleteMetadataTemplateScope;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.metadatafull.MetadataFull;
import com.box.sdkgen.schemas.metadatas.Metadatas;
import com.box.sdkgen.schemas.metadatatemplate.MetadataTemplate;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class FileMetadataITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testUpdatingFileMetadata() {
    FileFull file = uploadNewFile();
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
            .getFileMetadata()
            .createFileMetadataById(
                file.getId(),
                CreateFileMetadataByIdScope.ENTERPRISE,
                templateKey,
                mapOf(
                    entryOf("name", "John"),
                    entryOf("age", 23),
                    entryOf("birthDate", "2001-01-03T02:20:50.520Z"),
                    entryOf("countryCode", "US"),
                    entryOf("sports", Arrays.asList("basketball", "tennis"))));
    MetadataFull updatedMetadata =
        client
            .getFileMetadata()
            .updateFileMetadataById(
                file.getId(),
                UpdateFileMetadataByIdScope.ENTERPRISE,
                templateKey,
                Arrays.asList(
                    new UpdateFileMetadataByIdRequestBody.Builder()
                        .op(UpdateFileMetadataByIdRequestBodyOpField.REPLACE)
                        .path("/name")
                        .value("Jack")
                        .build(),
                    new UpdateFileMetadataByIdRequestBody.Builder()
                        .op(UpdateFileMetadataByIdRequestBodyOpField.REPLACE)
                        .path("/age")
                        .value(24L)
                        .build(),
                    new UpdateFileMetadataByIdRequestBody.Builder()
                        .op(UpdateFileMetadataByIdRequestBodyOpField.REPLACE)
                        .path("/birthDate")
                        .value("2000-01-03T02:20:50.520Z")
                        .build(),
                    new UpdateFileMetadataByIdRequestBody.Builder()
                        .op(UpdateFileMetadataByIdRequestBodyOpField.REPLACE)
                        .path("/countryCode")
                        .value("CA")
                        .build(),
                    new UpdateFileMetadataByIdRequestBody.Builder()
                        .op(UpdateFileMetadataByIdRequestBodyOpField.REPLACE)
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
        .getFileMetadata()
        .deleteFileMetadataById(file.getId(), DeleteFileMetadataByIdScope.ENTERPRISE, templateKey);
    client
        .getMetadataTemplates()
        .deleteMetadataTemplate(DeleteMetadataTemplateScope.ENTERPRISE, templateKey);
    client.getFiles().deleteFileById(file.getId());
  }

  @Test
  public void testGlobalFileMetadata() {
    FileFull file = uploadNewFile();
    Metadatas fileMetadata = client.getFileMetadata().getFileMetadata(file.getId());
    assert fileMetadata.getEntries().size() == 0;
    MetadataFull createdMetadata =
        client
            .getFileMetadata()
            .createFileMetadataById(
                file.getId(),
                CreateFileMetadataByIdScope.GLOBAL,
                "properties",
                mapOf(entryOf("abc", "xyz")));
    assert convertToString(createdMetadata.getTemplate()).equals("properties");
    assert convertToString(createdMetadata.getScope()).equals("global");
    assert createdMetadata.getVersion() == 0;
    MetadataFull receivedMetadata =
        client
            .getFileMetadata()
            .getFileMetadataById(file.getId(), GetFileMetadataByIdScope.GLOBAL, "properties");
    assert convertToString(receivedMetadata.getExtraData().get("abc")).equals("xyz");
    String newValue = "bar";
    client
        .getFileMetadata()
        .updateFileMetadataById(
            file.getId(),
            UpdateFileMetadataByIdScope.GLOBAL,
            "properties",
            Arrays.asList(
                new UpdateFileMetadataByIdRequestBody.Builder()
                    .op(UpdateFileMetadataByIdRequestBodyOpField.REPLACE)
                    .path("/abc")
                    .value(newValue)
                    .build()));
    MetadataFull receivedUpdatedMetadata =
        client
            .getFileMetadata()
            .getFileMetadataById(file.getId(), GetFileMetadataByIdScope.GLOBAL, "properties");
    assert convertToString(receivedUpdatedMetadata.getExtraData().get("abc")).equals(newValue);
    client
        .getFileMetadata()
        .deleteFileMetadataById(file.getId(), DeleteFileMetadataByIdScope.GLOBAL, "properties");
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getFileMetadata()
                .getFileMetadataById(file.getId(), GetFileMetadataByIdScope.GLOBAL, "properties"));
    client.getFiles().deleteFileById(file.getId());
  }

  @Test
  public void testEnterpriseFileMetadata() {
    FileFull file = uploadNewFile();
    String templateKey = String.join("", "key", getUuid());
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
                            CreateMetadataTemplateRequestBodyFieldsTypeField.FLOAT, "age", "age"),
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
                                    new CreateMetadataTemplateRequestBodyFieldsOptionsField("US"),
                                    new CreateMetadataTemplateRequestBodyFieldsOptionsField("CA")))
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
            .getFileMetadata()
            .createFileMetadataById(
                file.getId(),
                CreateFileMetadataByIdScope.ENTERPRISE,
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
        .getFileMetadata()
        .deleteFileMetadataById(file.getId(), DeleteFileMetadataByIdScope.ENTERPRISE, templateKey);
    client
        .getMetadataTemplates()
        .deleteMetadataTemplate(DeleteMetadataTemplateScope.ENTERPRISE, templateKey);
    client.getFiles().deleteFileById(file.getId());
  }
}
