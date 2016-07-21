package com.box.sdk;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.box.sdk.internal.pool.MacPool;

/**
 * Signature verifier for Webhook Payload.
 *
 * @since 2.2.1
 *
 */
public class BoxWebHookSignatureVerifier {

    /**
     * Reference to UTF_8 {@link Charset}.
     */
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    /**
     * Versions supported by this implementation.
     */
    private static final Set<String> SUPPORTED_VERSIONS = Collections.singleton("1");

    /**
     * Algorithms supported by this implementation.
     */
    private static final Set<BoxSignatureAlgorithm> SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(
            EnumSet.of(BoxSignatureAlgorithm.HMAC_SHA256));

    /**
     * {@link Mac}-s pool.
     */
    private static final MacPool MAC_POOL = new MacPool();

    /**
     * Primary key setup within the Box.
     */
    private final String primarySignatureKey;

    /**
     * Secondary key setup within the Box.
     */
    private final String secondarySignatureKey;

    /**
     * Creates a new instance of verifier specified with given primary and secondary keys. Primary key and secondary key
     * are needed for rotating purposes, at least at one has to be valid.
     *
     * @param primarySignatureKey
     *            primary signature key for web-hooks (can not be null)
     * @param secondarySignatureKey
     *            secondary signature key for web-hooks (can be null)
     * @throws IllegalArgumentException
     *             primary key can not be null
     */
    public BoxWebHookSignatureVerifier(String primarySignatureKey, String secondarySignatureKey) {
        if (primarySignatureKey == null && secondarySignatureKey == null) {
            throw new IllegalArgumentException("At least primary or secondary signature key must be provided!");
        }

        this.primarySignatureKey = primarySignatureKey;
        this.secondarySignatureKey = secondarySignatureKey;
    }

    /**
     * Verifies given web-hook information.
     *
     * @param signatureVersion
     *            signature version received from web-hook
     * @param signatureAlgorithm
     *            signature algorithm received from web-hook
     * @param primarySignature
     *            primary signature received from web-hook
     * @param secondarySignature
     *            secondary signature received from web-hook
     * @param webHookPayload
     *            payload of web-hook
     * @param deliveryTimestamp
     *            devilery timestamp received from web-hook
     * @return true, if given payload is successfully verified against primary and secondary signatures, false otherwise
     */
    public boolean verify(String signatureVersion, String signatureAlgorithm, String primarySignature,
            String secondarySignature, String webHookPayload, String deliveryTimestamp) {

        // enforce versions supported by this implementation
        if (!SUPPORTED_VERSIONS.contains(signatureVersion)) {
            return false;
        }

        // enforce algorithms supported by this implementation
        BoxSignatureAlgorithm algorithm = BoxSignatureAlgorithm.byName(signatureAlgorithm);
        if (!SUPPORTED_ALGORITHMS.contains(algorithm)) {
            return false;
        }

        // check primary key signature if primary key exists
        if (this.primarySignatureKey != null && this.verify(this.primarySignatureKey, algorithm, primarySignature,
                webHookPayload, deliveryTimestamp)) {
            return true;
        }

        // check secondary key signature if secondary key exists
        if (this.secondarySignatureKey != null && this.verify(this.secondarySignatureKey, algorithm, secondarySignature,
                webHookPayload, deliveryTimestamp)) {
            return true;
        }

        // default strategy is false, to minimize security issues
        return false;
    }

    /**
     * Verifies a provided signature.
     *
     * @param key
     *            for which signature key
     * @param actualAlgorithm
     *            current signature algorithm
     * @param actualSignature
     *            current signature
     * @param webHookPayload
     *            for signing
     * @param deliveryTimestamp
     *            for signing
     * @return true if verification passed
     */
    private boolean verify(String key, BoxSignatureAlgorithm actualAlgorithm, String actualSignature,
            String webHookPayload, String deliveryTimestamp) {
        if (actualSignature == null) {
            return false;
        }

        byte[] actual = Base64.decode(actualSignature);
        byte[] expected = this.signRaw(actualAlgorithm, key, webHookPayload, deliveryTimestamp);

        return Arrays.equals(expected, actual);
    }

    /**
     * Calculates signature for a provided information.
     *
     * @param algorithm
     *            for which algorithm
     * @param key
     *            used by signing
     * @param webHookPayload
     *            for singing
     * @param deliveryTimestamp
     *            for signing
     * @return calculated signature
     */
    public String sign(BoxSignatureAlgorithm algorithm, String key, String webHookPayload, String deliveryTimestamp) {
        return Base64.encode(this.signRaw(algorithm, key, webHookPayload, deliveryTimestamp));
    }

    /**
     * Calculates signature for a provided information.
     *
     * @param algorithm
     *            for which algorithm
     * @param key
     *            used by signing
     * @param webHookPayload
     *            for singing
     * @param deliveryTimestamp
     *            for signing
     * @return calculated signature
     */
    private byte[] signRaw(BoxSignatureAlgorithm algorithm, String key, String webHookPayload,
            String deliveryTimestamp) {
        Mac mac = MAC_POOL.acquire(algorithm.javaProviderName);
        try {
            mac.init(new SecretKeySpec(key.getBytes(UTF_8), algorithm.javaProviderName));
            mac.update(UTF_8.encode(webHookPayload));
            mac.update(UTF_8.encode(deliveryTimestamp));
            return mac.doFinal();
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException("Invalid key: ", e);
        } finally {
            MAC_POOL.release(mac);
        }
    }

    /**
     * Box Signature Algorithms.
     */
    public enum BoxSignatureAlgorithm {

        /**
         * HmacSHA256 algorithm.
         */
        HMAC_SHA256("HmacSHA256", "HmacSHA256");

        /**
         * @see #byName(String)
         */
        private static final Map<String, BoxSignatureAlgorithm> ALGORITHM_BY_NAME;

        /**
         * Algorithm name by Box.
         */
        private final String name;

        /**
         * Algorithm name according to the Java provider.
         */
        private final String javaProviderName;

        static {
            Map<String, BoxSignatureAlgorithm> algorithmByName = new ConcurrentHashMap<String, BoxSignatureAlgorithm>();
            for (BoxSignatureAlgorithm algorithm : BoxSignatureAlgorithm.values()) {
                algorithmByName.put(algorithm.name, algorithm);
            }
            ALGORITHM_BY_NAME = Collections.unmodifiableMap(algorithmByName);
        }

        /**
         * Constructor.
         *
         * @param name
         *            algorithm name by Box
         * @param javaProviderName
         *            algorithm name according to the Java provider
         */
        BoxSignatureAlgorithm(String name, String javaProviderName) {
            this.name = javaProviderName;
            this.javaProviderName = javaProviderName;
        }

        /**
         * Resolves {@link BoxSignatureAlgorithm} according to its name.
         *
         * @param name
         *            of algorithm
         * @return resolved {@link BoxSignatureAlgorithm} or null if does not exist
         */
        private static BoxSignatureAlgorithm byName(String name) {
            return ALGORITHM_BY_NAME.get(name);
        }
    }

}
