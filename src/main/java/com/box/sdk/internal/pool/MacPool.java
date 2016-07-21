package com.box.sdk.internal.pool;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import javax.crypto.Mac;

/**
 * Reusable thread-safe pool for {@link Mac} instances.
 *
 * Example:
 *
 * <pre>
 *  {@code
 *      Mac mac = macPool.acquire();
 *      try {
 *          ...
 *      } finally {
 *          macPool.release(mac);
 *      }
 *  }
 * </pre>
 *
 */
public class MacPool {

    /**
     * Pool of {@link Mac}-s by algorithm.
     */
    private final Map<String, Queue<Mac>> macPoolByAlgorithm = new ConcurrentHashMap<String, Queue<Mac>>();

    /**
     * Constructor.
     */
    public MacPool() {
    }

    /**
     * Acquires reusable {@link Mac}, has to be also released!
     *
     * @param algorithm
     *            {@link Mac#getAlgorithm()}
     * @return shared {@link Mac}
     *
     * @see #release(Mac)
     */
    public Mac acquire(String algorithm) {
        Mac result = null;

        Queue<Mac> pool = this.macPoolByAlgorithm.get(algorithm);
        if (pool != null) {
            result = pool.poll();
        }

        if (result != null) {
            // it has to be synchronized, for the case that some memory parts of Mac provider are changed,
            // but not yet visible for this thread
            synchronized (result) {
                result.reset();
            }
            return result;
        }

        try {
            return Mac.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(String.format("NoSuchAlgorithm '%s':", algorithm), e);
        }
    }

    /**
     * Releases a previously acquired {@link Mac}.
     *
     * @param mac
     *            for release
     *
     * @see #acquire(String)
     */
    public void release(Mac mac) {
        Queue<Mac> pool = this.macPoolByAlgorithm.get(mac.getAlgorithm());
        if (pool == null) {
            pool = new LinkedBlockingQueue<Mac>();
            this.macPoolByAlgorithm.put(mac.getAlgorithm(), pool);
        }
        pool.offer(mac);
    }

}
