package com.box.sdk;

/**
 * Implement this interface to provide a custom access token cache implementation for your environment.
 *
 * <p>For production applications it is recommended to use a distributed cache like Memcached or Redis, and to
 * implement this interface to store and retrieve access tokens appropriately for your environment.</p>
 */
public interface IAccessTokenCache {

    /**
     * Get the access token information from the cache.
     * @param key       key to look for.
     * @return          access token information.
     */
    String get(String key);

    /**
     * Store the access token information in the cache.
     * @param key       key to use.
     * @param value     access token information to store.
     */
    void put(String key, String value);
}
