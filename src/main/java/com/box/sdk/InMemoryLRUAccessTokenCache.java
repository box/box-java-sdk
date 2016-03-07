package com.box.sdk;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Use this class to create an in-memory LRU (least recently used) access token cache to be
 * passed to BoxDeveloperEditionAPIConnection.
 */
public class InMemoryLRUAccessTokenCache implements IAccessTokenCache {

    private final Map<String, String> cache;

    /**
     * Creates an in-memory LRU access token cache.
     * @param maxEntries    maximum number of entries to store.
     */
    public InMemoryLRUAccessTokenCache(final int maxEntries) {
        this.cache = new LinkedHashMap<String, String>(maxEntries, 0.75F, true) {
            private static final long serialVersionUID = -187234623489L;
            @Override
            protected boolean removeEldestEntry(java.util.Map.Entry<String, String> eldest) {
                return size() > maxEntries;
            }
        };
    }

    /**
     * Add an entry to the cache.
     * @param key       key to use.
     * @param value     access token information to store.
     */
    public void put(String key, String value) {
        synchronized (this.cache) {
            this.cache.put(key, value);
        }
    }

    /**
     * Get an entry from the cache.
     * @param key       key to look for.
     * @return          access token information.
     */
    public String get(String key) {
        synchronized (this.cache) {
            return this.cache.get(key);
        }
    }
}
