package com.box.sdkgen.test.storagepolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClientWithUserSubject;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.schemas.storagepolicies.StoragePolicies;
import com.box.sdkgen.schemas.storagepolicy.StoragePolicy;
import org.junit.jupiter.api.Test;

public class StoragePoliciesITest {

  private static final String userId = getEnvVar("USER_ID");

  @Test
  public void testGetStoragePolicies() {
    BoxClient client = getDefaultClientWithUserSubject(userId);
    StoragePolicies storagePolicies = client.getStoragePolicies().getStoragePolicies();
    StoragePolicy storagePolicy = storagePolicies.getEntries().get(0);
    assert convertToString(storagePolicy.getType()).equals("storage_policy");
    StoragePolicy getStoragePolicy =
        client.getStoragePolicies().getStoragePolicyById(storagePolicy.getId());
    assert getStoragePolicy.getId().equals(storagePolicy.getId());
  }
}
