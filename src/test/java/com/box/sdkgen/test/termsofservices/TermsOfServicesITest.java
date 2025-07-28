package com.box.sdkgen.test.termsofservices;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.test.commons.CommonsManager.getOrCreateTermsOfServices;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.termsofservices.UpdateTermsOfServiceByIdRequestBody;
import com.box.sdkgen.managers.termsofservices.UpdateTermsOfServiceByIdRequestBodyStatusField;
import com.box.sdkgen.schemas.termsofservice.TermsOfService;
import com.box.sdkgen.schemas.termsofservices.TermsOfServices;
import org.junit.jupiter.api.Test;

public class TermsOfServicesITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testGetTermsOfServices() {
    TermsOfService tos = getOrCreateTermsOfServices();
    TermsOfService updatedTos1 =
        client
            .getTermsOfServices()
            .updateTermsOfServiceById(
                tos.getId(),
                new UpdateTermsOfServiceByIdRequestBody(
                    UpdateTermsOfServiceByIdRequestBodyStatusField.DISABLED, "TOS"));
    assert convertToString(updatedTos1.getStatus()).equals("disabled");
    assert updatedTos1.getText().equals("TOS");
    TermsOfService updatedTos2 =
        client
            .getTermsOfServices()
            .updateTermsOfServiceById(
                tos.getId(),
                new UpdateTermsOfServiceByIdRequestBody(
                    UpdateTermsOfServiceByIdRequestBodyStatusField.DISABLED, "Updated TOS"));
    assert convertToString(updatedTos2.getStatus()).equals("disabled");
    assert updatedTos2.getText().equals("Updated TOS");
    TermsOfServices listTos = client.getTermsOfServices().getTermsOfService();
    assert listTos.getTotalCount() > 0;
  }
}
