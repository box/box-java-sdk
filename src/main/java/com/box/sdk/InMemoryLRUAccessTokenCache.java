package com.box.sdk;

import java.util.Map;
import java.util.LinkedHashMap;

/**
 * Use this class to create an in-memory LRU (least recently used) access token cache to be
 * passed to BoxDeveloperEditionAPIConnection.
 */
public class InMemoryLRUAccessTokenCache implements IAccessTokenCache {

    private final Map<String,String> cache;

    /**
     * Creates an in-memory LRU access token cache.
     * @param maxEntries    maximum number of entries to store.
     */
    public InMemoryLRUAccessTokenCache(final int maxEntries) {
        this.cache = new LinkedHashMap<String,String>(maxEntries, 0.75F, true) {
            private static final long serialVersionUID = -187234623489L;
            @Override
            protected boolean removeEldestEntry(java.util.Map.Entry<String,String> eldest){
                return size() > maxEntries;
            }
        };
    }

    public void put(String key, String value) {
        synchronized(cache) {
            cache.put(key, value);
        }
    }
    public String get(String key) {
        synchronized(cache) {
            return cache.get(key);
        }
    }
}
