package com.box.sdkgen.test.metadatacascadepolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.test.commons.CommonsManager.createNewFolder;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.foldermetadata.CreateFolderMetadataByIdScope;
import com.box.sdkgen.managers.metadatacascadepolicies.ApplyMetadataCascadePolicyRequestBody;
import com.box.sdkgen.managers.metadatacascadepolicies.ApplyMetadataCascadePolicyRequestBodyConflictResolutionField;
import com.box.sdkgen.managers.metadatacascadepolicies.CreateMetadataCascadePolicyRequestBody;
import com.box.sdkgen.managers.metadatacascadepolicies.CreateMetadataCascadePolicyRequestBodyScopeField;
import com.box.sdkgen.managers.metadatacascadepolicies.GetMetadataCascadePoliciesQueryParams;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBody;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsField;
import com.box.sdkgen.managers.metadatatemplates.CreateMetadataTemplateRequestBodyFieldsTypeField;
import com.box.sdkgen.managers.metadatatemplates.DeleteMetadataTemplateScope;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.metadatacascadepolicies.MetadataCascadePolicies;
import com.box.sdkgen.schemas.metadatacascadepolicy.MetadataCascadePolicy;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class MetadataCascadePoliciesITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testMetadataCascadePolicies() {
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
                            "testName",
                            "testName")))
                .build());
    FolderFull folder = createNewFolder();
    String enterpriseId = getEnvVar("ENTERPRISE_ID");
    MetadataCascadePolicy cascadePolicy =
        client
            .getMetadataCascadePolicies()
            .createMetadataCascadePolicy(
                new CreateMetadataCascadePolicyRequestBody(
                    folder.getId(),
                    CreateMetadataCascadePolicyRequestBodyScopeField.ENTERPRISE,
                    templateKey));
    assert convertToString(cascadePolicy.getType()).equals("metadata_cascade_policy");
    assert convertToString(cascadePolicy.getOwnerEnterprise().getType()).equals("enterprise");
    assert convertToString(cascadePolicy.getOwnerEnterprise().getId()).equals(enterpriseId);
    assert convertToString(cascadePolicy.getParent().getType()).equals("folder");
    assert cascadePolicy.getParent().getId().equals(folder.getId());
    assert convertToString(cascadePolicy.getScope())
        .equals(String.join("", "enterprise_", enterpriseId));
    assert cascadePolicy.getTemplateKey().equals(templateKey);
    String cascadePolicyId = cascadePolicy.getId();
    MetadataCascadePolicy policyFromTheApi =
        client.getMetadataCascadePolicies().getMetadataCascadePolicyById(cascadePolicyId);
    assert cascadePolicyId.equals(policyFromTheApi.getId());
    MetadataCascadePolicies policies =
        client
            .getMetadataCascadePolicies()
            .getMetadataCascadePolicies(new GetMetadataCascadePoliciesQueryParams(folder.getId()));
    assert policies.getEntries().size() == 1;
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getMetadataCascadePolicies()
                .applyMetadataCascadePolicy(
                    cascadePolicyId,
                    new ApplyMetadataCascadePolicyRequestBody(
                        ApplyMetadataCascadePolicyRequestBodyConflictResolutionField.OVERWRITE)));
    client
        .getFolderMetadata()
        .createFolderMetadataById(
            folder.getId(),
            CreateFolderMetadataByIdScope.ENTERPRISE,
            templateKey,
            mapOf(entryOf("testName", "xyz")));
    client
        .getMetadataCascadePolicies()
        .applyMetadataCascadePolicy(
            cascadePolicyId,
            new ApplyMetadataCascadePolicyRequestBody(
                ApplyMetadataCascadePolicyRequestBodyConflictResolutionField.OVERWRITE));
    client.getMetadataCascadePolicies().deleteMetadataCascadePolicyById(cascadePolicyId);
    assertThrows(
        RuntimeException.class,
        () -> client.getMetadataCascadePolicies().getMetadataCascadePolicyById(cascadePolicyId));
    client
        .getMetadataTemplates()
        .deleteMetadataTemplate(DeleteMetadataTemplateScope.ENTERPRISE, templateKey);
    client.getFolders().deleteFolderById(folder.getId());
  }
}
