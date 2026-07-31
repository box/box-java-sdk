package com.box.sdkgen.query;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.uploadNewFile;
import static com.box.sdkgen.internal.utils.UtilsManager.delayInSeconds;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.filemetadata.CreateFileMetadataByIdScope;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBody;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsField;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsOptionsField;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsTypeField;
import com.box.sdkgen.managers.metadatatemplates.DeleteMetadataTemplateScope;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.metadatafull.MetadataFull;
import com.box.sdkgen.schemas.metadatatemplate.MetadataTemplate;
import com.box.sdkgen.schemas.v2026r0.queryancestorreferencev2026r0.QueryAncestorReferenceV2026R0;
import com.box.sdkgen.schemas.v2026r0.queryinsightsgroupbyv2026r0.QueryInsightsGroupByV2026R0;
import com.box.sdkgen.schemas.v2026r0.queryinsightsmetricdefinitionv2026r0.QueryInsightsMetricDefinitionV2026R0;
import com.box.sdkgen.schemas.v2026r0.queryinsightsmetricdefinitionv2026r0.QueryInsightsMetricDefinitionV2026R0TypeField;
import com.box.sdkgen.schemas.v2026r0.queryinsightsrequestbodyv2026r0.QueryInsightsRequestBodyV2026R0;
import com.box.sdkgen.schemas.v2026r0.queryinsightsrequestbodyv2026r0.QueryInsightsRequestBodyV2026R0QueryField;
import com.box.sdkgen.schemas.v2026r0.queryinsightsv2026r0.QueryInsightsV2026R0;
import com.box.sdkgen.schemas.v2026r0.queryrequestbodyv2026r0.QueryRequestBodyV2026R0;
import com.box.sdkgen.schemas.v2026r0.queryrequestbodyv2026r0.QueryRequestBodyV2026R0QueryField;
import com.box.sdkgen.schemas.v2026r0.queryresultsv2026r0.QueryResultsV2026R0;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class QueryITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCreateQueryV2026R0() {
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
                                "birthDate")))
                    .build());
    assert template.getTemplateKey().equals(templateKey);
    FileFull file = uploadNewFile();
    MetadataFull metadata =
        client
            .getFileMetadata()
            .createFileMetadataById(
                file.getId(),
                CreateFileMetadataByIdScope.ENTERPRISE,
                templateKey,
                mapOf(
                    entryOf("name", "John"),
                    entryOf("age", 23),
                    entryOf("birthDate", "2001-01-03T02:20:50.520Z")));
    assert metadata.getTemplate().equals(templateKey);
    assert metadata.getScope().equals(template.getScope());
    delayInSeconds(10);
    String searchFrom = String.join("", template.getScope(), ":", template.getTemplateKey());
    String mdPrefix =
        String.join("", "metadata.", template.getScope(), ".\"", template.getTemplateKey(), "\"");
    String predicate = String.join("", mdPrefix, ".name = :name AND ", mdPrefix, ".age < :age");
    QueryResultsV2026R0 queryResult =
        client
            .getQuery()
            .createQueryV2026R0(
                new QueryRequestBodyV2026R0.Builder(
                        new QueryRequestBodyV2026R0QueryField.Builder(predicate)
                            .params(mapOf(entryOf("name", "John"), entryOf("age", 50)))
                            .ancestors(
                                Arrays.asList(new QueryAncestorReferenceV2026R0("0", "folder")))
                            .build())
                    .limit(10)
                    .fields(Arrays.asList("box:item:name", searchFrom))
                    .build());
    assert queryResult.getEntries().size() >= 0;
    client
        .getMetadataTemplates()
        .deleteMetadataTemplate(DeleteMetadataTemplateScope.ENTERPRISE, template.getTemplateKey());
    client.getFiles().deleteFileById(file.getId());
  }

  @Test
  public void testCreateQueryInsightV2026R0() {
    String templateKey = String.join("", "key", getUuid());
    MetadataTemplate template =
        client
            .getMetadataTemplates()
            .createMetadataTemplate(
                new CreateMetadataTemplateRequestBody.Builder("enterprise", templateKey)
                    .templateKey(templateKey)
                    .fields(
                        Arrays.asList(
                            new CreateMetadataTemplateRequestBodyFieldsField.Builder(
                                    CreateMetadataTemplateRequestBodyFieldsTypeField.ENUM,
                                    "category",
                                    "category")
                                .options(
                                    Arrays.asList(
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "Sales"),
                                        new CreateMetadataTemplateRequestBodyFieldsOptionsField(
                                            "Support")))
                                .build(),
                            new CreateMetadataTemplateRequestBodyFieldsField(
                                CreateMetadataTemplateRequestBodyFieldsTypeField.FLOAT,
                                "amount",
                                "amount")))
                    .build());
    assert template.getTemplateKey().equals(templateKey);
    FileFull file = uploadNewFile();
    MetadataFull metadata =
        client
            .getFileMetadata()
            .createFileMetadataById(
                file.getId(),
                CreateFileMetadataByIdScope.ENTERPRISE,
                templateKey,
                mapOf(entryOf("category", "Sales"), entryOf("amount", 150)));
    assert metadata.getTemplate().equals(templateKey);
    delayInSeconds(5);
    String mdPrefix =
        String.join("", "metadata.", template.getScope(), ".\"", template.getTemplateKey(), "\"");
    String predicate = String.join("", mdPrefix, ".amount > :minAmount");
    Map<String, QueryInsightsMetricDefinitionV2026R0> metrics =
        mapOf(
            entryOf(
                "totalAmount",
                new QueryInsightsMetricDefinitionV2026R0(
                    QueryInsightsMetricDefinitionV2026R0TypeField.SUM,
                    String.join("", mdPrefix, ".amount"))),
            entryOf(
                "countItems",
                new QueryInsightsMetricDefinitionV2026R0(
                    QueryInsightsMetricDefinitionV2026R0TypeField.COUNT,
                    String.join("", mdPrefix, ".category"))));
    QueryInsightsV2026R0 insightResult =
        client
            .getQuery()
            .createQueryInsightV2026R0(
                new QueryInsightsRequestBodyV2026R0(
                    new QueryInsightsRequestBodyV2026R0QueryField.Builder(predicate)
                        .params(mapOf(entryOf("minAmount", 0)))
                        .ancestors(Arrays.asList(new QueryAncestorReferenceV2026R0("0", "folder")))
                        .groupBy(
                            Arrays.asList(
                                new QueryInsightsGroupByV2026R0.Builder(
                                        String.join("", mdPrefix, ".category"))
                                    .bucketLimit(5)
                                    .build()))
                        .build(),
                    metrics));
    assert insightResult.getInsights().size() >= 0;
    client
        .getMetadataTemplates()
        .deleteMetadataTemplate(DeleteMetadataTemplateScope.ENTERPRISE, template.getTemplateKey());
    client.getFiles().deleteFileById(file.getId());
  }
}
