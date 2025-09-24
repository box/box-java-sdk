package com.box.sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("Terms of servcie are disabled on test account")
public class BoxTermsOfServiceIT {

  @Test
  public void getAllTermsOfServicesWithNoParamSucceeds() {
    final String tosType = "terms_of_service";

    BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

    List<BoxTermsOfService.Info> termsOfServicesInfo = BoxTermsOfService.getAllTermsOfServices(api);

    for (BoxTermsOfService.Info info : termsOfServicesInfo) {
      assertNotNull(info);
      assertNotNull(info.getEnterprise());
      assertEquals(tosType, info.getType());
    }
  }

  @Test
  public void getAllTermsOfServicesWithParamSucceeds() {
    final String type = "terms_of_service";
    final BoxTermsOfService.TermsOfServiceType tosType =
        BoxTermsOfService.TermsOfServiceType.MANAGED;

    BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

    List<BoxTermsOfService.Info> termsOfServicesInfo =
        BoxTermsOfService.getAllTermsOfServices(api, BoxTermsOfService.TermsOfServiceType.MANAGED);

    for (BoxTermsOfService.Info info : termsOfServicesInfo) {
      assertNotNull(info);
      assertNotNull(info.getEnterprise());
      assertEquals(tosType, info.getTosType());
      assertEquals(type, info.getType());
    }
  }
}
