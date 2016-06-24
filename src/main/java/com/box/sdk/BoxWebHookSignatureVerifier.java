package com.box.sdk;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
     * @return true, if given payload is successfully verified against given primary and secondary signatures, false otherwise
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public boolean verify(String webhookPayload, String primarySignature, String secondarySignature) throws NoSuchAlgorithmException, InvalidKeyException {
        //check primary key signature if primary key exists
        if (primarySignatureKey != null && !checkSignature(webhookPayload, primarySignatureKey, primarySignature)) {
            return false;
        }
        //check secondary key signature if secondary key exists
        if (secondarySignatureKey != null && !checkSignature(webhookPayload, secondarySignatureKey, secondarySignature)) {
            return false;
        }
        //
        return true;
    }

    private boolean checkSignature(String webhookPayload, String signatureKey, String signature) throws InvalidKeyException, NoSuchAlgorithmException {
        if (signature == null) {
            return false;
        }

        final Mac hmac = Mac.getInstance(ALGORITHM);

        hmac.init(new SecretKeySpec(signatureKey.getBytes(), ALGORITHM));

        return Base64.encode(hmac.doFinal(webhookPayload.getBytes()))
                .equals(signature);
    }
}
