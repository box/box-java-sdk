package com.box.sdkgen.legalholdpolicyassignments;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.uploadNewFile;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.legalholdpolicies.CreateLegalHoldPolicyRequestBody;
import com.box.sdkgen.managers.legalholdpolicyassignments.CreateLegalHoldPolicyAssignmentRequestBody;
import com.box.sdkgen.managers.legalholdpolicyassignments.CreateLegalHoldPolicyAssignmentRequestBodyAssignToField;
import com.box.sdkgen.managers.legalholdpolicyassignments.CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField;
import com.box.sdkgen.managers.legalholdpolicyassignments.GetLegalHoldPolicyAssignmentsQueryParams;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.filesonhold.FilesOnHold;
import com.box.sdkgen.schemas.legalholdpolicy.LegalHoldPolicy;
import com.box.sdkgen.schemas.legalholdpolicyassignment.LegalHoldPolicyAssignment;
import com.box.sdkgen.schemas.legalholdpolicyassignments.LegalHoldPolicyAssignments;
import org.junit.jupiter.api.Test;

public class LegalHoldPolicyAssignmentsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testLegalHoldPolicyAssignments() {
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
    String legalHoldPolicyId = legalHoldPolicy.getId();
    FileFull file = uploadNewFile();
    String fileId = file.getId();
    LegalHoldPolicyAssignment legalHoldPolicyAssignment =
        client
            .getLegalHoldPolicyAssignments()
            .createLegalHoldPolicyAssignment(
                new CreateLegalHoldPolicyAssignmentRequestBody(
                    legalHoldPolicyId,
                    new CreateLegalHoldPolicyAssignmentRequestBodyAssignToField(
                        CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField.FILE, fileId)));
    assert convertToString(legalHoldPolicyAssignment.getLegalHoldPolicy().getType())
        .equals("legal_hold_policy");
    String legalHoldPolicyAssignmentId = legalHoldPolicyAssignment.getId();
    LegalHoldPolicyAssignment legalHoldPolicyAssignmentFromApi =
        client
            .getLegalHoldPolicyAssignments()
            .getLegalHoldPolicyAssignmentById(legalHoldPolicyAssignmentId);
    assert legalHoldPolicyAssignmentFromApi.getId().equals(legalHoldPolicyAssignmentId);
    LegalHoldPolicyAssignments legalPolicyAssignments =
        client
            .getLegalHoldPolicyAssignments()
            .getLegalHoldPolicyAssignments(
                new GetLegalHoldPolicyAssignmentsQueryParams(legalHoldPolicyId));
    assert legalPolicyAssignments.getEntries().size() == 1;
    FilesOnHold filesOnHold =
        client
            .getLegalHoldPolicyAssignments()
            .getLegalHoldPolicyAssignmentFileOnHold(legalHoldPolicyAssignmentId);
    assert filesOnHold.getEntries().size() == 1;
    assert filesOnHold.getEntries().get(0).getId().equals(fileId);
    client
        .getLegalHoldPolicyAssignments()
        .deleteLegalHoldPolicyAssignmentById(legalHoldPolicyAssignmentId);
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getLegalHoldPolicyAssignments()
                .deleteLegalHoldPolicyAssignmentById(legalHoldPolicyAssignmentId));
    client.getFiles().deleteFileById(fileId);
    try {
      client.getLegalHoldPolicies().deleteLegalHoldPolicyById(legalHoldPolicyId);
    } catch (Exception exception) {
      System.out.print(
          String.join("", "Could not delete Legal Policy with id: ", legalHoldPolicyId));
    }
  }
}
