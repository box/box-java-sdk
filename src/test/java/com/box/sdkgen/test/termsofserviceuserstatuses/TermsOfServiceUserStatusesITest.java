package com.box.sdkgen.test.termsofserviceuserstatuses;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.test.commons.CommonsManager.getOrCreateTermsOfServices;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.termsofserviceuserstatuses.CreateTermsOfServiceStatusForUserRequestBody;
import com.box.sdkgen.managers.termsofserviceuserstatuses.CreateTermsOfServiceStatusForUserRequestBodyTosField;
import com.box.sdkgen.managers.termsofserviceuserstatuses.CreateTermsOfServiceStatusForUserRequestBodyUserField;
import com.box.sdkgen.managers.termsofserviceuserstatuses.GetTermsOfServiceUserStatusesQueryParams;
import com.box.sdkgen.managers.termsofserviceuserstatuses.UpdateTermsOfServiceStatusForUserByIdRequestBody;
import com.box.sdkgen.managers.users.CreateUserRequestBody;
import com.box.sdkgen.schemas.termsofservice.TermsOfService;
import com.box.sdkgen.schemas.termsofserviceuserstatus.TermsOfServiceUserStatus;
import com.box.sdkgen.schemas.termsofserviceuserstatuses.TermsOfServiceUserStatuses;
import com.box.sdkgen.schemas.userfull.UserFull;
import org.junit.jupiter.api.Test;

public class TermsOfServiceUserStatusesITest {

  @Test
  public void testGetTermsOfServiceUserStatuses() {
    String adminUserId = getEnvVar("USER_ID");
    BoxClient client = getDefaultClientWithUserSubject(adminUserId);
    TermsOfService tos = getOrCreateTermsOfServices();
    UserFull user =
        client
            .getUsers()
            .createUser(
                new CreateUserRequestBody.Builder(getUuid())
                    .login(String.join("", getUuid(), "@boxdemo.com"))
                    .isPlatformAccessOnly(true)
                    .build());
    TermsOfServiceUserStatus createdTosUserStatus =
        client
            .getTermsOfServiceUserStatuses()
            .createTermsOfServiceStatusForUser(
                new CreateTermsOfServiceStatusForUserRequestBody(
                    new CreateTermsOfServiceStatusForUserRequestBodyTosField(tos.getId()),
                    new CreateTermsOfServiceStatusForUserRequestBodyUserField(user.getId()),
                    false));
    assert createdTosUserStatus.getIsAccepted() == false;
    assert convertToString(createdTosUserStatus.getType()).equals("terms_of_service_user_status");
    assert convertToString(createdTosUserStatus.getTos().getType()).equals("terms_of_service");
    assert convertToString(createdTosUserStatus.getUser().getType()).equals("user");
    assert createdTosUserStatus.getTos().getId().equals(tos.getId());
    assert createdTosUserStatus.getUser().getId().equals(user.getId());
    TermsOfServiceUserStatus updatedTosUserStatus =
        client
            .getTermsOfServiceUserStatuses()
            .updateTermsOfServiceStatusForUserById(
                createdTosUserStatus.getId(),
                new UpdateTermsOfServiceStatusForUserByIdRequestBody(true));
    assert updatedTosUserStatus.getIsAccepted() == true;
    TermsOfServiceUserStatuses listTosUserStatuses =
        client
            .getTermsOfServiceUserStatuses()
            .getTermsOfServiceUserStatuses(
                new GetTermsOfServiceUserStatusesQueryParams.Builder(tos.getId())
                    .userId(user.getId())
                    .build());
    assert listTosUserStatuses.getTotalCount() > 0;
    client.getUsers().deleteUserById(user.getId());
  }
}
