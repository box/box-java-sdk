package com.box.sdk;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vladimir Hrusovsky
 */
public class BoxWebHookSignatureVerifierTest {
    private static final String PRIMARY_SIGNATURE_KEY = "testPrimaryKey";
    private static final String SECONDARY_SIGNATURE_KEY = "testSecondaryKey";

    private static final String WEBHOOK_PAYLOAD = "testPayload";
    private static final String PRIMARY_SIGNATURE = "EIH1n2YSw5ufsm0ipsU7EtdH4Kt8EXM9jMKUXVEBwF8=";
    private static final String SECONDARY_SIGNATURE = "nAOLG+rVaprSONFSbO8AiwxGFThc7V8NgRpHiokqcB8=";

    @Test
    public void verifyNoKeys() throws Exception {
        final BoxWebHookSignatureVerifier verifier = new BoxWebHookSignatureVerifier(
                null,
                null
        );

        Assert.assertTrue(verifier.verify(WEBHOOK_PAYLOAD, "", ""));
        Assert.assertTrue(verifier.verify(WEBHOOK_PAYLOAD, null, null));
        Assert.assertTrue(verifier.verify(WEBHOOK_PAYLOAD, PRIMARY_SIGNATURE, SECONDARY_SIGNATURE));
    }

    @Test
    public void verifyPrimaryKeyOnly() throws Exception {
        final BoxWebHookSignatureVerifier verifier = new BoxWebHookSignatureVerifier(
                PRIMARY_SIGNATURE_KEY,
                null
        );

        Assert.assertFalse(verifier.verify(WEBHOOK_PAYLOAD, "", ""));
        Assert.assertFalse(verifier.verify(WEBHOOK_PAYLOAD, null, null));
        Assert.assertFalse(verifier.verify(WEBHOOK_PAYLOAD, "", SECONDARY_SIGNATURE));
        Assert.assertFalse(verifier.verify(WEBHOOK_PAYLOAD, null, SECONDARY_SIGNATURE));
        Assert.assertTrue(verifier.verify(WEBHOOK_PAYLOAD, PRIMARY_SIGNATURE, null));
        Assert.assertTrue(verifier.verify(WEBHOOK_PAYLOAD, PRIMARY_SIGNATURE, SECONDARY_SIGNATURE));
    }

    @Test
    public void verifySecondaryKeyOnly() throws Exception {
        final BoxWebHookSignatureVerifier verifier = new BoxWebHookSignatureVerifier(
                null,
                SECONDARY_SIGNATURE_KEY
        );

        Assert.assertFalse(verifier.verify(WEBHOOK_PAYLOAD, "", ""));
        Assert.assertFalse(verifier.verify(WEBHOOK_PAYLOAD, null, null));
        Assert.assertFalse(verifier.verify(WEBHOOK_PAYLOAD, PRIMARY_SIGNATURE, ""));
        Assert.assertFalse(verifier.verify(WEBHOOK_PAYLOAD, PRIMARY_SIGNATURE, null));
        Assert.assertTrue(verifier.verify(WEBHOOK_PAYLOAD, null, SECONDARY_SIGNATURE));
        Assert.assertTrue(verifier.verify(WEBHOOK_PAYLOAD, PRIMARY_SIGNATURE, SECONDARY_SIGNATURE));
    }

    @Test
    public void verifyBothKeys() throws Exception {
        final BoxWebHookSignatureVerifier verifier = new BoxWebHookSignatureVerifier(
                PRIMARY_SIGNATURE_KEY,
                SECONDARY_SIGNATURE_KEY
        );

        Assert.assertFalse(verifier.verify(WEBHOOK_PAYLOAD, "", ""));
        Assert.assertFalse(verifier.verify(WEBHOOK_PAYLOAD, null, null));
        Assert.assertFalse(verifier.verify(WEBHOOK_PAYLOAD, "", SECONDARY_SIGNATURE));
        Assert.assertFalse(verifier.verify(WEBHOOK_PAYLOAD, null, SECONDARY_SIGNATURE));
        Assert.assertFalse(verifier.verify(WEBHOOK_PAYLOAD, PRIMARY_SIGNATURE, ""));
        Assert.assertFalse(verifier.verify(WEBHOOK_PAYLOAD, PRIMARY_SIGNATURE, null));
        Assert.assertTrue(verifier.verify(WEBHOOK_PAYLOAD, PRIMARY_SIGNATURE, SECONDARY_SIGNATURE));
    }

}
