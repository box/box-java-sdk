package com.box.sdk;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Signature verifier for Webhook Payload.
 *
 * @author Vladimir Hrusovsky
 */
public class BoxWebHookSignatureVerifier {
    private static final String ALGORITHM = "HmacSHA256";

    private final String primarySignatureKey;
    private final String secondarySignatureKey;

    /**
     * Creates a new instance of verifier specified with given primary and secondary keys.
     *
     * @param primarySignatureKey   primary signature key for Webhooks (can be null)
     * @param secondarySignatureKey secondary signature key for Webhooks (can be null)
     */
    public BoxWebHookSignatureVerifier(String primarySignatureKey, String secondarySignatureKey) {
        this.primarySignatureKey = primarySignatureKey;
        this.secondarySignatureKey = secondarySignatureKey;
    }

    /**
     * Verifies given webhook payload with primary and secondary signatures.
     *
     * @param webhookPayload     payload of webhook
     * @param primarySignature   primary signature received from webhook
     * @param secondarySignature secondary signature received from webhook
     * @return true, if given payload is successfully verified against primary and secondary signatures, false otherwise
     * @throws NoSuchAlgorithmException on invalid algorithm specified
     * @throws InvalidKeyException on invalid signature key provided
     * @throws UnsupportedEncodingException on invalid encoding used
     */
    public boolean verify(String webhookPayload, String primarySignature, String secondarySignature)
            throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {

        //check primary key signature if primary key exists
        if (this.primarySignatureKey != null
                && !this.checkSignature(webhookPayload, this.primarySignatureKey, primarySignature)) {
            return false;
        }
        //check secondary key signature if secondary key exists
        if (this.secondarySignatureKey != null
                && !this.checkSignature(webhookPayload, this.secondarySignatureKey, secondarySignature)) {
            return false;
        }
        //
        return true;
    }

    private boolean checkSignature(String webhookPayload, String signatureKey, String signature)
            throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {

        if (signature == null) {
            return false;
        }

        final Mac hmac = Mac.getInstance(ALGORITHM);

        hmac.init(new SecretKeySpec(signatureKey.getBytes("UTF-8"), ALGORITHM));

        return Arrays.equals(
                Base64.decode(signature),
                hmac.doFinal(webhookPayload.getBytes("UTF-8"))
        );
    }
}
