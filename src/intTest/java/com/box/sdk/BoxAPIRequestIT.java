package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static org.junit.Assert.fail;

import org.junit.Test;

/** Integration test for BoxAPIRequest */
public class BoxAPIRequestIT {

  @Test
  public void loggingWorks() {
    BoxLogger.defaultLogger().setLevelToAll();
    try {
      BoxAPIConnection api = jwtApiForServiceAccount();
      BoxUser.getCurrentUser(api);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exception occured");
    } finally {
      BoxLogger.defaultLogger().resetToDefaultLevel();
    }
  }
}
