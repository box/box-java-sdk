package com.box.sdk;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

@Ignore("Configure enterprise connection")
public class BoxDeveloperEditionAPIConnectionIT {


    private String jtiClaim = null;

    @Test
    public void retriesWithNewJWTAssertionOnNetworkErrorAndSucceeds() {
        boolean allTestsPassed = true;
        BoxConfig boxConfig = new BoxConfig(TestConfig.getClientID(), TestConfig.getClientSecret());
        for (int i = 0; i < 100; i++) {
            try {
                System.out.print("Attempt #" + i);
                BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig);
                System.out.println(" passed");
            } catch (BoxAPIException e) {
                allTestsPassed = false;
                System.out.println(" failed");
                e.printStackTrace();
                System.out.println();
                break;
            }
        }

        assertTrue(allTestsPassed);
    }

}
