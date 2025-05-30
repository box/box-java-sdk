package com.box.sdkgen.legalholdpolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.legalholdpolicies.CreateLegalHoldPolicyRequestBody;
import com.box.sdkgen.managers.legalholdpolicies.UpdateLegalHoldPolicyByIdRequestBody;
import com.box.sdkgen.schemas.legalholdpolicies.LegalHoldPolicies;
import com.box.sdkgen.schemas.legalholdpolicy.LegalHoldPolicy;
import org.junit.jupiter.api.Test;

public class LegalHoldPoliciesITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCreateNotOngoingLegalHoldPolicy() {
    String legalHoldPolicyName = getUuid();
    String legalHoldDescription = "test description";
    String filterStartedAt = "2021-01-01T00:00:00-08:00";
    String filterEndedAt = "2022-01-01T00:00:00-08:00";
    LegalHoldPolicy legalHoldPolicy =
        client
            .getLegalHoldPolicies()
            .createLegalHoldPolicy(
                new CreateLegalHoldPolicyRequestBody.CreateLegalHoldPolicyRequestBodyBuilder(
                        legalHoldPolicyName)
                    .description(legalHoldDescription)
                    .filterStartedAt(filterStartedAt)
                    .filterEndedAt(filterEndedAt)
                    .isOngoing(false)
                    .build());
    assert legalHoldPolicy.getPolicyName().equals(legalHoldPolicyName);
    assert legalHoldPolicy.getDescription().equals(legalHoldDescription);
    assert legalHoldPolicy.getFilterStartedAt().equals(filterStartedAt);
    assert legalHoldPolicy.getFilterEndedAt().equals(filterEndedAt);
    client.getLegalHoldPolicies().deleteLegalHoldPolicyById(legalHoldPolicy.getId());
  }

  @Test
  public void testCreateUpdateGetDeleteLegalHoldPolicy() {
    String legalHoldPolicyName = getUuid();
    String legalHoldDescription = "test description";
    LegalHoldPolicy legalHoldPolicy =
        client
            .getLegalHoldPolicies()
            .createLegalHoldPolicy(
                new CreateLegalHoldPolicyRequestBody.CreateLegalHoldPolicyRequestBodyBuilder(
                        legalHoldPolicyName)
                    .description(legalHoldDescription)
                    .isOngoing(true)
                    .build());
    assert legalHoldPolicy.getPolicyName().equals(legalHoldPolicyName);
    assert legalHoldPolicy.getDescription().equals(legalHoldDescription);
    String legalHoldPolicyId = legalHoldPolicy.getId();
    LegalHoldPolicy legalHoldPolicyById =
        client.getLegalHoldPolicies().getLegalHoldPolicyById(legalHoldPolicyId);
    assert legalHoldPolicyById.getId().equals(legalHoldPolicyId);
    LegalHoldPolicies legalHoldPolicies = client.getLegalHoldPolicies().getLegalHoldPolicies();
    assert legalHoldPolicies.getEntries().size() > 0;
    String updatedLegalHoldPolicyName = getUuid();
    LegalHoldPolicy updatedLegalHoldPolicy =
        client
            .getLegalHoldPolicies()
            .updateLegalHoldPolicyById(
                legalHoldPolicyId,
                new UpdateLegalHoldPolicyByIdRequestBody
                        .UpdateLegalHoldPolicyByIdRequestBodyBuilder()
                    .policyName(updatedLegalHoldPolicyName)
                    .build());
    assert updatedLegalHoldPolicy.getPolicyName().equals(updatedLegalHoldPolicyName);
    client.getLegalHoldPolicies().deleteLegalHoldPolicyById(legalHoldPolicyId);
  }
}
