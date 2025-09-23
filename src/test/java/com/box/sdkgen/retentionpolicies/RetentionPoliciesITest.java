package com.box.sdkgen.retentionpolicies;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.retentionpolicies.CreateRetentionPolicyRequestBody;
import com.box.sdkgen.managers.retentionpolicies.CreateRetentionPolicyRequestBodyDispositionActionField;
import com.box.sdkgen.managers.retentionpolicies.CreateRetentionPolicyRequestBodyPolicyTypeField;
import com.box.sdkgen.managers.retentionpolicies.CreateRetentionPolicyRequestBodyRetentionTypeField;
import com.box.sdkgen.managers.retentionpolicies.UpdateRetentionPolicyByIdRequestBody;
import com.box.sdkgen.schemas.retentionpolicies.RetentionPolicies;
import com.box.sdkgen.schemas.retentionpolicy.RetentionPolicy;
import org.junit.jupiter.api.Test;

public class RetentionPoliciesITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCreateUpdateGetDeleteRetentionPolicy() {
    String retentionPolicyName = getUuid();
    String retentionDescription = "test description";
    RetentionPolicy retentionPolicy =
        client
            .getRetentionPolicies()
            .createRetentionPolicy(
                new CreateRetentionPolicyRequestBody.Builder(
                        retentionPolicyName,
                        CreateRetentionPolicyRequestBodyPolicyTypeField.FINITE,
                        CreateRetentionPolicyRequestBodyDispositionActionField.REMOVE_RETENTION)
                    .description(retentionDescription)
                    .retentionLength("1")
                    .retentionType(CreateRetentionPolicyRequestBodyRetentionTypeField.MODIFIABLE)
                    .canOwnerExtendRetention(true)
                    .areOwnersNotified(true)
                    .build());
    assert retentionPolicy.getPolicyName().equals(retentionPolicyName);
    assert retentionPolicy.getDescription().equals(retentionDescription);
    RetentionPolicy retentionPolicyById =
        client.getRetentionPolicies().getRetentionPolicyById(retentionPolicy.getId());
    assert retentionPolicyById.getId().equals(retentionPolicy.getId());
    RetentionPolicies retentionPolicies = client.getRetentionPolicies().getRetentionPolicies();
    assert retentionPolicies.getEntries().size() > 0;
    String updatedRetentionPolicyName = getUuid();
    RetentionPolicy updatedRetentionPolicy =
        client
            .getRetentionPolicies()
            .updateRetentionPolicyById(
                retentionPolicy.getId(),
                new UpdateRetentionPolicyByIdRequestBody.Builder()
                    .policyName(updatedRetentionPolicyName)
                    .build());
    assert updatedRetentionPolicy.getPolicyName().equals(updatedRetentionPolicyName);
    client.getRetentionPolicies().deleteRetentionPolicyById(retentionPolicy.getId());
  }
}
