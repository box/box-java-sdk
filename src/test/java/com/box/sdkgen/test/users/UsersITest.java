package com.box.sdkgen.test.users;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.users.CreateUserRequestBody;
import com.box.sdkgen.managers.users.UpdateUserByIdRequestBody;
import com.box.sdkgen.schemas.userfull.UserFull;
import com.box.sdkgen.schemas.users.Users;
import org.junit.jupiter.api.Test;

public class UsersITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testGetUsers() {
    Users users = client.getUsers().getUsers();
    assert users.getTotalCount() >= 0;
  }

  @Test
  public void testGetUserMe() {
    UserFull currentUser = client.getUsers().getUserMe();
    assert convertToString(currentUser.getType()).equals("user");
  }

  @Test
  public void testCreateUpdateGetDeleteUser() {
    String userName = getUuid();
    String userLogin = String.join("", getUuid(), "@gmail.com");
    UserFull user =
        client
            .getUsers()
            .createUser(
                new CreateUserRequestBody.Builder(userName)
                    .login(userLogin)
                    .isPlatformAccessOnly(true)
                    .build());
    assert user.getName().equals(userName);
    UserFull userById = client.getUsers().getUserById(user.getId());
    assert userById.getId().equals(user.getId());
    String updatedUserName = getUuid();
    UserFull updatedUser =
        client
            .getUsers()
            .updateUserById(
                user.getId(),
                new UpdateUserByIdRequestBody.Builder().name(updatedUserName).build());
    assert updatedUser.getName().equals(updatedUserName);
    client.getUsers().deleteUserById(user.getId());
  }
}
