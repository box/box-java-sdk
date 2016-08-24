package com.box.sdk;

import org.junit.Assert;
import org.junit.Test;

/**
 * A {@link BoxWebHookSignatureVerifier} related tests.
 */
public class BoxWebHookSignatureVerifierTest {

    private static final String SIGNATURE_VERSION = "1";
    private static final String SIGNATURE_ALGORITHM = "HmacSHA256";

    private static final String PRIMARY_SIGNATURE_KEY = "faqZQdZ2dbNVaFdPWRsVMjZOC4nVwgp0";
    private static final String SECONDARY_SIGNATURE_KEY = "4SidLLVJLIx3L3RAvMhybvvpmJHUd4iD";

    private static final String DELIVERY_TIMESTAMP = "2016-07-08T01:20:32-07:00";
    private static final String WEB_HOOK_PAYLOAD = "{ \"payload\" : \"test\" }";

    private static final String PRIMARY_SIGNATURE = "R54s9jpedqP/Og92+77Ip8hVtfWjR4pnaJXcvRGafCQ=";
    private static final String SECONDARY_SIGNATURE = "V4b6jfPoCaTpcPvUDaaYLfVC4+DUZ3/B6F0pz44shEE=";

    /**
     * Unit test that version has to be supported.
     */
    @Test
    public void testInvalidVersion() {
        BoxWebHookSignatureVerifier verifier = new BoxWebHookSignatureVerifier(PRIMARY_SIGNATURE_KEY,
                SECONDARY_SIGNATURE_KEY);
        Assert.assertTrue(verifier.verify(SIGNATURE_VERSION, SIGNATURE_ALGORITHM, PRIMARY_SIGNATURE, null,
                WEB_HOOK_PAYLOAD, DELIVERY_TIMESTAMP));
        Assert.assertTrue(verifier.verify(SIGNATURE_VERSION, SIGNATURE_ALGORITHM, null, SECONDARY_SIGNATURE,
                WEB_HOOK_PAYLOAD, DELIVERY_TIMESTAMP));
        Assert.assertFalse(verifier.verify("-1", SIGNATURE_ALGORITHM, PRIMARY_SIGNATURE, null, WEB_HOOK_PAYLOAD,
                DELIVERY_TIMESTAMP));
        Assert.assertFalse(verifier.verify("-1", SIGNATURE_ALGORITHM, null, SECONDARY_SIGNATURE, WEB_HOOK_PAYLOAD,
                DELIVERY_TIMESTAMP));
    }

    /**
     * Unit test that algorithm has to be supported.
     */
    @Test
    public void testInvalidAlgorithm() {
        BoxWebHookSignatureVerifier verifier = new BoxWebHookSignatureVerifier(PRIMARY_SIGNATURE_KEY,
                SECONDARY_SIGNATURE_KEY);
        Assert.assertTrue(verifier.verify(SIGNATURE_VERSION, SIGNATURE_ALGORITHM, PRIMARY_SIGNATURE, null,
                WEB_HOOK_PAYLOAD, DELIVERY_TIMESTAMP));
        Assert.assertTrue(verifier.verify(SIGNATURE_VERSION, SIGNATURE_ALGORITHM, null, SECONDARY_SIGNATURE,
                WEB_HOOK_PAYLOAD, DELIVERY_TIMESTAMP));
        Assert.assertFalse(verifier.verify(SIGNATURE_VERSION, "none", PRIMARY_SIGNATURE, null, WEB_HOOK_PAYLOAD,
                DELIVERY_TIMESTAMP));
        Assert.assertFalse(verifier.verify(SIGNATURE_VERSION, "none", null, SECONDARY_SIGNATURE, WEB_HOOK_PAYLOAD,
                DELIVERY_TIMESTAMP));
    }

    @Test
    public void verifyPrimaryKeyOnly() throws Exception {
        final BoxWebHookSignatureVerifier verifier = new BoxWebHookSignatureVerifier(PRIMARY_SIGNATURE_KEY, null);

        Assert.assertFalse(
                verifier.verify(SIGNATURE_VERSION, SIGNATURE_ALGORITHM, "", "", WEB_HOOK_PAYLOAD, DELIVERY_TIMESTAMP));
        Assert.assertFalse(verifier.verify(SIGNATURE_VERSION, SIGNATURE_ALGORITHM, null, null, WEB_HOOK_PAYLOAD,
                DELIVERY_TIMESTAMP));
        Assert.assertFalse(verifier.verify(SIGNATURE_VERSION, SIGNATURE_ALGORITHM, "", SECONDARY_SIGNATURE,
                WEB_HOOK_PAYLOAD, DELIVERY_TIMESTAMP));
        Assert.assertFalse(verifier.verify(SIGNATURE_VERSION, SIGNATURE_ALGORITHM, null, SECONDARY_SIGNATURE,
                WEB_HOOK_PAYLOAD, DELIVERY_TIMESTAMP));
        Assert.assertTrue(verifier.verify(SIGNATURE_VERSION, SIGNATURE_ALGORITHM, PRIMARY_SIGNATURE, null,
                WEB_HOOK_PAYLOAD, DELIVERY_TIMESTAMP));
        Assert.assertTrue(verifier.verify(SIGNATURE_VERSION, SIGNATURE_ALGORITHM, PRIMARY_SIGNATURE,
                SECONDARY_SIGNATURE, WEB_HOOK_PAYLOAD, DELIVERY_TIMESTAMP));
    }

    @Test
    public void verifyRotatedKeys() throws Exception {
        final BoxWebHookSignatureVerifier verifier = new BoxWebHookSignatureVerifier(PRIMARY_SIGNATURE_KEY,
                SECONDARY_SIGNATURE_KEY);

        // no key is valid
        Assert.assertFalse(
                verifier.verify(SIGNATURE_VERSION, SIGNATURE_ALGORITHM, "", "", WEB_HOOK_PAYLOAD, DELIVERY_TIMESTAMP));
        Assert.assertFalse(verifier.verify(SIGNATURE_VERSION, SIGNATURE_ALGORITHM, null, null, WEB_HOOK_PAYLOAD,
                DELIVERY_TIMESTAMP));

        // primary signature is valid
        Assert.assertTrue(verifier.verify(SIGNATURE_VERSION, SIGNATURE_ALGORITHM, PRIMARY_SIGNATURE, "",
                WEB_HOOK_PAYLOAD, DELIVERY_TIMESTAMP));
        Assert.assertTrue(verifier.verify(SIGNATURE_VERSION, SIGNATURE_ALGORITHM, PRIMARY_SIGNATURE, null,
                WEB_HOOK_PAYLOAD, DELIVERY_TIMESTAMP));

        // secondary signature is valid
        Assert.assertTrue(verifier.verify(SIGNATURE_VERSION, SIGNATURE_ALGORITHM, "", SECONDARY_SIGNATURE,
                WEB_HOOK_PAYLOAD, DELIVERY_TIMESTAMP));
        Assert.assertTrue(verifier.verify(SIGNATURE_VERSION, SIGNATURE_ALGORITHM, null, SECONDARY_SIGNATURE,
                WEB_HOOK_PAYLOAD, DELIVERY_TIMESTAMP));
    }

}
