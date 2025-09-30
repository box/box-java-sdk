package com.box.sdkgen.emailaliases;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.emailaliases.CreateUserEmailAliasRequestBody;
import com.box.sdkgen.managers.users.CreateUserRequestBody;
import com.box.sdkgen.schemas.emailalias.EmailAlias;
import com.box.sdkgen.schemas.emailaliases.EmailAliases;
import com.box.sdkgen.schemas.userfull.UserFull;
import org.junit.jupiter.api.Test;

public class EmailAliasesITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testEmailAliases() {
    String newUserName = getUuid();
    String newUserLogin = String.join("", getUuid(), "@boxdemo.com");
    UserFull newUser =
        client
            .getUsers()
            .createUser(new CreateUserRequestBody.Builder(newUserName).login(newUserLogin).build());
    EmailAliases aliases = client.getEmailAliases().getUserEmailAliases(newUser.getId());
    assert aliases.getTotalCount() == 0;
    String newAliasEmail = String.join("", newUser.getId(), "@boxdemo.com");
    EmailAlias newAlias =
        client
            .getEmailAliases()
            .createUserEmailAlias(
                newUser.getId(), new CreateUserEmailAliasRequestBody(newAliasEmail));
    EmailAliases updatedAliases = client.getEmailAliases().getUserEmailAliases(newUser.getId());
    assert updatedAliases.getTotalCount() == 1;
    assert updatedAliases.getEntries().get(0).getEmail().equals(newAliasEmail);
    client.getEmailAliases().deleteUserEmailAliasById(newUser.getId(), newAlias.getId());
    EmailAliases finalAliases = client.getEmailAliases().getUserEmailAliases(newUser.getId());
    assert finalAliases.getTotalCount() == 0;
    client.getUsers().deleteUserById(newUser.getId());
  }
}
