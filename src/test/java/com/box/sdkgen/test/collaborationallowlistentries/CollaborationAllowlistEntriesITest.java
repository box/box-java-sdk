package com.box.sdkgen.test.collaborationallowlistentries;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.collaborationallowlistentries.CreateCollaborationWhitelistEntryRequestBody;
import com.box.sdkgen.managers.collaborationallowlistentries.CreateCollaborationWhitelistEntryRequestBodyDirectionField;
import com.box.sdkgen.schemas.collaborationallowlistentries.CollaborationAllowlistEntries;
import com.box.sdkgen.schemas.collaborationallowlistentry.CollaborationAllowlistEntry;
import org.junit.jupiter.api.Test;

public class CollaborationAllowlistEntriesITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCollaborationAllowlistEntries() {
    CollaborationAllowlistEntries allowlist =
        client.getCollaborationAllowlistEntries().getCollaborationWhitelistEntries();
    assert allowlist.getEntries().size() >= 0;
    String domain = String.join("", getUuid(), "example.com");
    CollaborationAllowlistEntry newEntry =
        client
            .getCollaborationAllowlistEntries()
            .createCollaborationWhitelistEntry(
                new CreateCollaborationWhitelistEntryRequestBody(
                    domain, CreateCollaborationWhitelistEntryRequestBodyDirectionField.INBOUND));
    assert convertToString(newEntry.getType()).equals("collaboration_whitelist_entry");
    assert convertToString(newEntry.getDirection()).equals("inbound");
    assert newEntry.getDomain().equals(domain);
    CollaborationAllowlistEntry entry =
        client
            .getCollaborationAllowlistEntries()
            .getCollaborationWhitelistEntryById(newEntry.getId());
    assert entry.getId().equals(newEntry.getId());
    assert convertToString(entry.getDirection()).equals(convertToString(newEntry.getDirection()));
    assert entry.getDomain().equals(domain);
    client.getCollaborationAllowlistEntries().deleteCollaborationWhitelistEntryById(entry.getId());
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getCollaborationAllowlistEntries()
                .getCollaborationWhitelistEntryById(entry.getId()));
  }
}
