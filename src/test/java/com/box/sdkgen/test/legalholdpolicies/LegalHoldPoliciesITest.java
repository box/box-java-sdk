package com.box.sdkgen.test.legalholdpolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.dateTimeFromString;
import static com.box.sdkgen.internal.utils.UtilsManager.dateTimeToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.legalholdpolicies.CreateLegalHoldPolicyRequestBody;
import com.box.sdkgen.managers.legalholdpolicies.UpdateLegalHoldPolicyByIdRequestBody;
import com.box.sdkgen.schemas.legalholdpolicies.LegalHoldPolicies;
import com.box.sdkgen.schemas.legalholdpolicy.LegalHoldPolicy;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class LegalHoldPoliciesITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCreateNotOngoingLegalHoldPolicy() {
    String legalHoldPolicyName = getUuid();
    String legalHoldDescription = "test description";
    Date filterStartedAt = dateTimeFromString("2021-01-01T00:00:00-08:00");
    Date filterEndedAt = dateTimeFromString("2022-01-01T00:00:00-08:00");
    LegalHoldPolicy legalHoldPolicy =
        client
            .getLegalHoldPolicies()
            .createLegalHoldPolicy(
                new CreateLegalHoldPolicyRequestBody.Builder(legalHoldPolicyName)
                    .description(legalHoldDescription)
                    .filterStartedAt(filterStartedAt)
                    .filterEndedAt(filterEndedAt)
                    .isOngoing(false)
                    .build());
    assert legalHoldPolicy.getPolicyName().equals(legalHoldPolicyName);
    assert legalHoldPolicy.getDescription().equals(legalHoldDescription);
    assert dateTimeToString(legalHoldPolicy.getFilterStartedAt())
        .equals(dateTimeToString(filterStartedAt));
    assert dateTimeToString(legalHoldPolicy.getFilterEndedAt())
        .equals(dateTimeToString(filterEndedAt));
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
                new CreateLegalHoldPolicyRequestBody.Builder(legalHoldPolicyName)
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
                new UpdateLegalHoldPolicyByIdRequestBody.Builder()
                    .policyName(updatedLegalHoldPolicyName)
                    .build());
    assert updatedLegalHoldPolicy.getPolicyName().equals(updatedLegalHoldPolicyName);
    client.getLegalHoldPolicies().deleteLegalHoldPolicyById(legalHoldPolicyId);
  }
}
