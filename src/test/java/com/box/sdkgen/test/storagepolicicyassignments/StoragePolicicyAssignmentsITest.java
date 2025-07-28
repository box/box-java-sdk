package com.box.sdkgen.test.storagepolicicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClientWithUserSubject;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.storagepolicyassignments.CreateStoragePolicyAssignmentRequestBody;
import com.box.sdkgen.managers.storagepolicyassignments.CreateStoragePolicyAssignmentRequestBodyAssignedToField;
import com.box.sdkgen.managers.storagepolicyassignments.CreateStoragePolicyAssignmentRequestBodyAssignedToTypeField;
import com.box.sdkgen.managers.storagepolicyassignments.CreateStoragePolicyAssignmentRequestBodyStoragePolicyField;
import com.box.sdkgen.managers.storagepolicyassignments.GetStoragePolicyAssignmentsQueryParams;
import com.box.sdkgen.managers.storagepolicyassignments.GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField;
import com.box.sdkgen.managers.storagepolicyassignments.UpdateStoragePolicyAssignmentByIdRequestBody;
import com.box.sdkgen.managers.storagepolicyassignments.UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField;
import com.box.sdkgen.managers.users.CreateUserRequestBody;
import com.box.sdkgen.schemas.storagepolicies.StoragePolicies;
import com.box.sdkgen.schemas.storagepolicy.StoragePolicy;
import com.box.sdkgen.schemas.storagepolicyassignment.StoragePolicyAssignment;
import com.box.sdkgen.schemas.storagepolicyassignments.StoragePolicyAssignments;
import com.box.sdkgen.schemas.userfull.UserFull;
import org.junit.jupiter.api.Test;

public class StoragePolicicyAssignmentsITest {

  private static final String adminUserId = getEnvVar("USER_ID");

  public static StoragePolicyAssignment getOrCreateStoragePolicyAssignment(
      BoxClient client, String policyId, String userId) {
    StoragePolicyAssignments storagePolicyAssignments =
        client
            .getStoragePolicyAssignments()
            .getStoragePolicyAssignments(
                new GetStoragePolicyAssignmentsQueryParams(
                    GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField.USER, userId));
    if (storagePolicyAssignments.getEntries().size() > 0) {
      if (convertToString(storagePolicyAssignments.getEntries().get(0).getAssignedTo().getType())
          .equals("user")) {
        return storagePolicyAssignments.getEntries().get(0);
      }
    }
    StoragePolicyAssignment storagePolicyAssignment =
        client
            .getStoragePolicyAssignments()
            .createStoragePolicyAssignment(
                new CreateStoragePolicyAssignmentRequestBody(
                    new CreateStoragePolicyAssignmentRequestBodyStoragePolicyField(policyId),
                    new CreateStoragePolicyAssignmentRequestBodyAssignedToField(
                        CreateStoragePolicyAssignmentRequestBodyAssignedToTypeField.USER, userId)));
    return storagePolicyAssignment;
  }

  @Test
  public void testGetStoragePolicyAssignments() {
    BoxClient client = getDefaultClientWithUserSubject(adminUserId);
    String userName = getUuid();
    UserFull newUser =
        client
            .getUsers()
            .createUser(
                new CreateUserRequestBody.Builder(userName).isPlatformAccessOnly(true).build());
    StoragePolicies storagePolicies = client.getStoragePolicies().getStoragePolicies();
    StoragePolicy storagePolicy1 = storagePolicies.getEntries().get(0);
    StoragePolicy storagePolicy2 = storagePolicies.getEntries().get(1);
    StoragePolicyAssignment storagePolicyAssignment =
        getOrCreateStoragePolicyAssignment(client, storagePolicy1.getId(), newUser.getId());
    assert convertToString(storagePolicyAssignment.getType()).equals("storage_policy_assignment");
    assert convertToString(storagePolicyAssignment.getAssignedTo().getType()).equals("user");
    assert storagePolicyAssignment.getAssignedTo().getId().equals(newUser.getId());
    StoragePolicyAssignment getStoragePolicyAssignment =
        client
            .getStoragePolicyAssignments()
            .getStoragePolicyAssignmentById(storagePolicyAssignment.getId());
    assert getStoragePolicyAssignment.getId().equals(storagePolicyAssignment.getId());
    StoragePolicyAssignment updatedStoragePolicyAssignment =
        client
            .getStoragePolicyAssignments()
            .updateStoragePolicyAssignmentById(
                storagePolicyAssignment.getId(),
                new UpdateStoragePolicyAssignmentByIdRequestBody(
                    new UpdateStoragePolicyAssignmentByIdRequestBodyStoragePolicyField(
                        storagePolicy2.getId())));
    assert updatedStoragePolicyAssignment.getStoragePolicy().getId().equals(storagePolicy2.getId());
    client
        .getStoragePolicyAssignments()
        .deleteStoragePolicyAssignmentById(storagePolicyAssignment.getId());
    client.getUsers().deleteUserById(newUser.getId());
  }
}
