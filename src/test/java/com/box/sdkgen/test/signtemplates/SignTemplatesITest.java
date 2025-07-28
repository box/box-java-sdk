package com.box.sdkgen.test.signtemplates;

import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClientWithUserSubject;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.signtemplates.GetSignTemplatesQueryParams;
import com.box.sdkgen.schemas.signtemplate.SignTemplate;
import com.box.sdkgen.schemas.signtemplates.SignTemplates;
import org.junit.jupiter.api.Test;

public class SignTemplatesITest {

  @Test
  public void testGetSignTemplates() {
    BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));
    SignTemplates signTemplates =
        client
            .getSignTemplates()
            .getSignTemplates(new GetSignTemplatesQueryParams.Builder().limit(2L).build());
    assert signTemplates.getEntries().size() >= 0;
  }

  @Test
  public void testGetSignTemplate() {
    BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));
    SignTemplates signTemplates =
        client
            .getSignTemplates()
            .getSignTemplates(new GetSignTemplatesQueryParams.Builder().limit(2L).build());
    assert signTemplates.getEntries().size() >= 0;
    if (signTemplates.getEntries().size() > 0) {
      SignTemplate signTemplate =
          client.getSignTemplates().getSignTemplateById(signTemplates.getEntries().get(0).getId());
      assert signTemplate.getId().equals(signTemplates.getEntries().get(0).getId());
      assert signTemplate.getSourceFiles().size() > 0;
      assert !(signTemplate.getName().equals(""));
      assert !(signTemplate.getParentFolder().getId().equals(""));
    }
  }
}
